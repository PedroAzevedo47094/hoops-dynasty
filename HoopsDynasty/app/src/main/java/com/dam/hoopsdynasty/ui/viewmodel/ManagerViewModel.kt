package com.dam.hoopsdynasty.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.viewModelScope
import com.dam.hoopsdynasty.data.GameAdapter
import com.dam.hoopsdynasty.data.ManagerAdapter
import com.dam.hoopsdynasty.data.PlayerAdapter
import com.dam.hoopsdynasty.data.SeasonAdapter
import com.dam.hoopsdynasty.data.TeamAdapter
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase
import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Manager
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Season
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.data.repository.FirestoreAuthRepository
import com.dam.hoopsdynasty.data.repository.ManagerRepository
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch

class ManagerViewModel(application: Application) : AndroidViewModel(application) {


    private val authViewModel = AuthViewModel()
    private val firestoreAuthRepository = FirestoreAuthRepository()
    private val managerRepository: ManagerRepository =
        ManagerRepository(HoopsDynastyDatabase.getDatabase(application).managerDao())


    fun getManagerTeam(): Team? {
        return getManager().value?.team
    }

    fun registerManager(email: String, name: String, password: String, team: Team) {
        viewModelScope.launch {
            val isUserRegistered = authViewModel.registerUser(email, password)

            if (isUserRegistered) {
                val uid = authViewModel.getCurrentUser()?.uid
                if (uid != null) {
                    val manager = Manager(
                        uid = uid,
                        email = email,
                        name = name,
                        password = password,
                        team = team
                    )
                    managerRepository.insertManager(manager)

                    backupData()


                } else {
                    // print error message to user

                }
            }
        }
    }


    private fun backupData() {
        val application = getApplication<Application>()
        val mainViewModel = MainViewModel(application)
        val teamViewModel = mainViewModel.teamViewModel
        val playerViewModel = mainViewModel.playerViewModel
        val gameViewModel = mainViewModel.gameViewModel
        val seasonViewModel = mainViewModel.seasonViewModel


        val uid = authViewModel.getCurrentUser()?.uid
        val database = Firebase.database
        val myRef = database.getReference("user/${uid}")

        val gson = GsonBuilder()
            .registerTypeAdapter(Manager::class.java, ManagerAdapter())
            .registerTypeAdapter(Team::class.java, TeamAdapter())
            .registerTypeAdapter(Player::class.java, PlayerAdapter())
            .registerTypeAdapter(Game::class.java, GameAdapter())
            .registerTypeAdapter(Season::class.java, SeasonAdapter())
            .create()

        // Step 2: Convert and backup 'managers' table
        getManager().observe(ProcessLifecycleOwner.get()) { managersList ->
            val managersJson = gson.toJson(managersList)
            myRef.child("managers").setValue(managersJson)
        }

        // Step 3: Convert and backup 'teams' table
        teamViewModel.getAllTeams().observe(ProcessLifecycleOwner.get()) { teamsList ->
            val teamsJson = gson.toJson(teamsList)
            myRef.child("teams").setValue(teamsJson)
        }

        // Step 4: Convert and backup 'players' table
        playerViewModel.getAllPlayers().observe(ProcessLifecycleOwner.get()) { playersList ->
            val playersJson = gson.toJson(playersList)
            myRef.child("players").setValue(playersJson)
        }

        seasonViewModel.getSeason().observe(ProcessLifecycleOwner.get()) { season ->
            val seasonJson = gson.toJson(season)
            myRef.child("season").setValue(seasonJson)
        }

        gameViewModel.getAllGames().observe(ProcessLifecycleOwner.get()) { gamesList ->
            val gamesJson = gson.toJson(gamesList)
            myRef.child("games").setValue(gamesJson)
        }

        // Step 5: Convert and backup 'games' table

//        val games = gameViewModel.getAllGames().value
//        //print in the logcat
//        Log.d("games", games.toString())
//
//
//        gameViewModel.getAllGames().observe(ProcessLifecycleOwner.get()) { gamesList ->
//            val gamesJson = gson.toJson(gamesList)
//            myRef.child("games").setValue(gamesJson)
//        }


    }

    private fun readData() {
        val uid = authViewModel.getCurrentUser()?.uid
        val database = Firebase.database
        val myRef = database.getReference("user/$uid")

        val application = getApplication<Application>()
        val mainViewModel = MainViewModel(application)
        val teamViewModel = mainViewModel.teamViewModel
        val playerViewModel = mainViewModel.playerViewModel
        val gameViewModel = mainViewModel.gameViewModel
        val seasonViewModel = mainViewModel.seasonViewModel

        val gson = GsonBuilder()
            .registerTypeAdapter(Manager::class.java, ManagerAdapter())
            .registerTypeAdapter(Team::class.java, TeamAdapter())
            .registerTypeAdapter(Player::class.java, PlayerAdapter())
            .registerTypeAdapter(Game::class.java, GameAdapter())
            .registerTypeAdapter(Season::class.java, SeasonAdapter())
            .create()

        // Read 'managers' data
        myRef.child("managers").get().addOnSuccessListener { snapshot ->
            val managerJson = snapshot.value.toString()
            val manager = gson.fromJson(managerJson, Manager::class.java)

            viewModelScope.launch {
                managerRepository.insertManager(manager)
            }

        }

        // Read 'teams' data
        myRef.child("teams").get().addOnSuccessListener { snapshot ->
            val teamsJson = snapshot.value.toString()
            val teamsList = gson.fromJson(teamsJson, Array<Team>::class.java).toList()
            teamsList.forEach { team ->
                viewModelScope.launch {
                    teamViewModel.insertTeam(team)
                }
            }
        }

        // Read 'players' data
        myRef.child("players").get().addOnSuccessListener { snapshot ->
            val playersJson = snapshot.value.toString()
            val playersList = gson.fromJson(playersJson, Array<Player>::class.java).toList()
            playersList.forEach { player ->
                viewModelScope.launch {
                    playerViewModel.insertPlayer(player)
                }
            }
        }

// Read 'season' data
        myRef.child("season").get().addOnSuccessListener { snapshot ->
            val seasonJson = snapshot.value.toString()
            val season = gson.fromJson(seasonJson, Season::class.java)

            viewModelScope.launch {
                seasonViewModel.insertSeason(season)
            }

        }

        // Read 'games' data
//        myRef.child("games").get().addOnSuccessListener { snapshot ->
//            val gamesJson = snapshot.value.toString()
//            val gamesList = gson.fromJson(gamesJson, Array<Game>::class.java).toList()
//            gamesList.forEach { games ->
//                viewModelScope.launch {
//                    gameViewModel.insertGame(games)
//                }
//            }
//        }
    }

    fun getManager(): LiveData<Manager?> {
        return managerRepository.getManager()
    }

    fun updateManagerWithTeam(manager: Manager, team: Team) {
        viewModelScope.launch {
            manager.team = team
            managerRepository.updateManager(manager)
        }
    }



    fun updateManager(manager: Manager) = viewModelScope.launch {
        managerRepository.updateManager(manager)
    }

    fun logoutUser() {
        authViewModel.logoutUser()
    }

    fun loginManager(email: String, password: String) {
        viewModelScope.launch {
            val isUserLoggedIn = authViewModel.loginUser(email, password)
            if (isUserLoggedIn) {
                readData()

            } else {
                // print error message to user
            }
        }

    }
}


