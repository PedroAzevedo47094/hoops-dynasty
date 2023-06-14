package com.dam.hoopsdynasty.data

import androidx.room.TypeConverter
import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Season
import com.dam.hoopsdynasty.data.model.Team
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromPlayersList(playersList: List<Player>?): String? {
        return Gson().toJson(playersList)
    }

    @TypeConverter
    @JvmStatic
    fun toPlayersList(playersListString: String?): List<Player>? {
        return Gson().fromJson(playersListString, object : TypeToken<List<Player>?>() {}.type)
    }

    @TypeConverter
    fun fromString(value: String): Map<String, Player> {
        val mapType = object : TypeToken<Map<String, Player>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromMap(map: Map<String, Player>): String {
        return Gson().toJson(map)
    }

    @TypeConverter
    @JvmStatic
    fun fromGamesList(gamesList: List<Game>?): String? {
        return Gson().toJson(gamesList)
    }

    @TypeConverter
    @JvmStatic
    fun toGamesList(gamesListString: String?): List<Game>? {
        return Gson().fromJson(gamesListString, object : TypeToken<List<Game>?>() {}.type)
    }

    //conver A Team to a String
    @TypeConverter
    @JvmStatic
    fun fromTeam(team: Team?): String? {
        return Gson().toJson(team)
    }

    //convert a String to a Team
    @TypeConverter
    @JvmStatic
    fun toTeam(teamString: String?): Team? {
        return Gson().fromJson(teamString, object : TypeToken<Team?>() {}.type)
    }

    //convert a List<Team> to a String
    @TypeConverter
    @JvmStatic
    fun fromTeamsList(teamsList: List<Team>?): String? {
        return Gson().toJson(teamsList)
    }

    //convert a String to a List<Team>
    @TypeConverter
    @JvmStatic
    fun toTeamsList(teamsListString: String?): List<Team>? {
        return Gson().fromJson(teamsListString, object : TypeToken<List<Team>?>() {}.type)
    }

    //convert a Season to a String
    @TypeConverter
    @JvmStatic
    fun fromSeason(season: Season?): String? {
        return Gson().toJson(season)
    }

    //convert a String to a Season
    @TypeConverter
    @JvmStatic
    fun toSeason(seasonString: String?): Season? {
        return Gson().fromJson(seasonString, object : TypeToken<Season?>() {}.type)
    }

    //convert List<Float> to a String
    @TypeConverter
    @JvmStatic
    fun fromFloatList(floatList: List<Float>?): String? {
        return Gson().toJson(floatList)
    }

    //convert a String to a List<Float>
    @TypeConverter
    @JvmStatic
    fun toFloatList(floatListString: String?): List<Float>? {
        return Gson().fromJson(floatListString, object : TypeToken<List<Float>?>() {}.type)
    }



    @TypeConverter
    fun fromMap2(map: Map<String, Float>): String {
        return Gson().toJson(map)
    }

    @TypeConverter
    fun fromString2(value: String): Map<String, Float> {
        val mapType = object : TypeToken<Map<String, Float>>() {}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(",")
    }
}
