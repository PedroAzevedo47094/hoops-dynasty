package com.dam.hoopsdynasty.ui.simulation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.ui.viewmodel.GamesSimulationViewModel
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

class GameSimulation(mainViewModel: MainViewModel, game: Game, theGamesSimulationViewModel: GamesSimulationViewModel) {
    val managerViewModel = mainViewModel.managerViewModel
    val teamViewModel = mainViewModel.teamViewModel
    private val gameViewModel = mainViewModel.gameViewModel
    private var gamesSimulationViewModel: GamesSimulationViewModel = theGamesSimulationViewModel




    private val theManagerLiveData = managerViewModel.getManager()
    private val theManager = theManagerLiveData.value

    private val currentGame = game

    private var homeTeam: Team? = null
    private var awayTeam: Team? = null


    private var homeTeamScore by mutableStateOf(0)
        private set
    private var awayTeamScore by mutableStateOf(0)
        private set



    private var homeTeamWin = false
    private var awayTeamWin = false

    private var attackingTeam: Team? = null
    private var defendingTeam: Team? = null

    private var plays = 0
    private var maxPlays = 30
    private var qNumber = 1

    private var time = "48:00"


    fun setHomeTeam(team:Team) {
        homeTeam = team
        attackingTeam = homeTeam
    }

    fun setAwayTeam(team: Team) {
        awayTeam = team
        defendingTeam = awayTeam
    }

    fun updateTime(newTime: String) {
        time = newTime
        gamesSimulationViewModel.setTime(time)
    }



    fun subtractSecondsFromTime(timeString: String, secondsToSubtract: Int): String {
        val parts = timeString.split(":")
        val minutes = parts[0].toInt()
        val seconds = parts[1].toInt()

        val totalSeconds = minutes * 60 + seconds - secondsToSubtract
        val newMinutes = totalSeconds / 60
        val newSeconds = totalSeconds % 60

        return String.format("%02d:%02d", newMinutes, newSeconds)
    }



    fun startGame() {
        CoroutineScope(Dispatchers.Default).launch {

            val homeCenter = homeTeam?.positions?.get("C")
            val awayCenter = awayTeam?.positions?.get("C")

            if (homeCenter != null && awayCenter != null) {
                val jumpBall = jb(homeCenter, awayCenter)

                if (jumpBall) {
                    changePossetion()
                }
            }

            withContext(Dispatchers.Main) {
                runGameSimulation()
                saveGame()
            }
        }
    }


    private suspend fun runGameSimulation() = withContext(Dispatchers.Default) {
        while (qNumber < 4) {
            playQuarter()
        }

        while (homeTeamScore == awayTeamScore) {
            maxPlays = 12
            playQuarter()
        }

        homeTeamWin = homeTeamScore > awayTeamScore
        awayTeamWin = !homeTeamWin

    }


    private fun jb(homeCenter: Player, awayCenter: Player): Boolean {
        return ((awayCenter.rpg - homeCenter.rpg) * (if (Random.nextBoolean()) 1 else -1)) > 0
    }

    private fun changePossetion() {
        val attTeam = attackingTeam
        val defTeam = defendingTeam
        attackingTeam = defTeam
        defendingTeam = attTeam
    }

    private fun playQuarter() = runBlocking {
        while (plays < maxPlays) {

            play()

            delay(1500L)
        }
        qNumber++
        plays = 0
    }

    private fun play() {
        val scoreIncrement = playResult(attackingTeam, defendingTeam)
        if (attackingTeam == homeTeam) homeTeamScore += scoreIncrement else awayTeamScore += scoreIncrement

        gamesSimulationViewModel.updateScores(homeTeamScore, awayTeamScore)
        val newTime = subtractSecondsFromTime(time, 24)
        updateTime(newTime)


        Log.d("GameSimulation", "Play: $homeTeamScore - $awayTeamScore")

        plays++
        changePossetion()

    }


    private fun playResult(attackingTeam: Team?, defendingTeam: Team?): Int {
        val value = (calculateDefensePts(defendingTeam) - calculateAttackPts(attackingTeam)).toInt()
        val valueRand = Random.nextInt(-1, 1)
        val valueFinal = (value * valueRand)


        return if (valueFinal < 0) 0 else valueFinal
    }


    data class PositionWeights(
        val ppg: Float,
        val apg: Float,
        val orepg: Float,
        val tpg: Float,
        val ftm: Float,
        val fta: Float,
        val topg: Float,
        val drepg: Float,
        val spg: Float,
        val bpg: Float
    )

    private val positionWeights = mapOf(
        "PG" to PositionWeights(.2f, .4f, .1f, .2f, .3f, .1f, -.3f, .05f, .2f, .05f),
        "SG" to PositionWeights(.3f, .2f, .05f, .3f, .2f, .1f, -.2f, .1f, .25f, .05f),
        "SF" to PositionWeights(.4f, .15f, .1f, .3f, .2f, .1f, -.25f, .15f, .3f, .1f),
        "PF" to PositionWeights(.4f, .1f, .2f, .1f, .3f, .15f, -.3f, .2f, .35f, .15f),
        "C" to PositionWeights(.3f, .05f, .3f, .05f, .4f, .2f, -.4f, .25f, .4f, .2f)
    )


    private fun calculateOverallRatingWeight(overallRating: Float): Float {
        return when (overallRating) {
            in 90f..100f -> 1.0f
            in 80f..89f -> 0.9f
            in 70f..79f -> 0.8f
            in 60f..69f -> 0.7f
            in 50f..59f -> 0.6f
            in 40f..49f -> 0.5f
            else -> 0.4f
        }
    }

    private fun calculateAttackPts(attackingTeam: Team?): Float {
        val randomFloat = 0.8f + Random.nextFloat() * (1.2f - 0.8f)
        val attackPts = (attackingTeam?.positions?.map { (position, player) ->
            calculateAttackPosition(player!!, position)
        }?.sum() ?: 0f) * .1f * randomFloat
        Log.d("GameSimulation", "AttackPts: $attackPts")
        val roundedNumber = BigDecimal(attackPts.toDouble()).setScale(1, RoundingMode.HALF_EVEN)
        return roundedNumber.toFloat()

    }


    private fun calculateAttackPosition(player: Player, position: String): Float {

        val ppgW = positionWeights[position]!!.ppg
        val apgW = positionWeights[position]!!.apg
        val orepgW = positionWeights[position]!!.orepg
        val tpgW = positionWeights[position]!!.tpg
        val ftmW = positionWeights[position]!!.ftm
        val ftaW = positionWeights[position]!!.fta
        val topgW = positionWeights[position]!!.topg


        val overallRatingW = calculateOverallRatingWeight(player.rating)


        return ((ppgW * player.ppg) +
                (apgW * player.apg) +
                (orepgW * player.orepg) +
                (tpgW * player.tpgm) +
                (ftmW * player.ftm) +
                (ftaW * player.fta) +
                (topgW * player.topg)) * (overallRatingW)

    }

    private fun calculateDefensePts(defendingTeam: Team?): Float {
        val randomFloat = 0.8f + Random.nextFloat() * (1.2f - 0.8f)

        val defendingPts = (defendingTeam?.positions?.map { (position, player) ->
            calculateDefensePosition(player!!, position)
        }?.sum() ?: 0f) * .1f * randomFloat

        Log.d("GameSimulation", "DefensePts: $defendingPts")
        val roundedNumber = BigDecimal(defendingPts.toDouble()).setScale(1, RoundingMode.HALF_EVEN)
        return roundedNumber.toFloat()
    }

    private fun calculateDefensePosition(player: Player, position: String): Float {
        val drepgW = positionWeights[position]!!.drepg
        val spgW = positionWeights[position]!!.spg
        val bpgW = positionWeights[position]!!.bpg

        val overallRatingW = calculateOverallRatingWeight(player.rating)

        return ((drepgW * player.drebpg) +
                (spgW * player.spg) +
                (bpgW * player.bpg)) * (overallRatingW)

    }


    private fun saveGame() {
        homeTeamScore = homeTeamScore
        awayTeamScore = awayTeamScore
        currentGame.homeScore = homeTeamScore
        currentGame.awayScore = awayTeamScore
        currentGame.winner = if (homeTeamWin) homeTeam?.abbreviation else awayTeam?.abbreviation
        currentGame.loser = if (homeTeamWin) awayTeam?.abbreviation else homeTeam?.abbreviation
        gameViewModel.updateGame(currentGame)

        if (homeTeamWin) {
            homeTeam?.wins = homeTeam?.wins!! + 1
            awayTeam?.losses = awayTeam?.losses!! + 1
        } else {
            homeTeam?.losses = homeTeam?.losses!! + 1
            awayTeam?.wins = awayTeam?.wins!! + 1
        }

        teamViewModel.updateTeam(homeTeam!!)
        teamViewModel.updateTeam(awayTeam!!)

        if (homeTeam == theManager?.team) theManager?.team = homeTeam else theManager?.team =
            awayTeam
        if (theManager != null) {
            managerViewModel.updateManager(theManager)
        }

    }


}