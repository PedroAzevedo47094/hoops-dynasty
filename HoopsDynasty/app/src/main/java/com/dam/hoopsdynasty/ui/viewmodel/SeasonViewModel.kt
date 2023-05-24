package com.dam.hoopsdynasty.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase
import com.dam.hoopsdynasty.data.model.Season
import com.dam.hoopsdynasty.data.repository.SeasonRepository
import kotlinx.coroutines.launch

class SeasonViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SeasonRepository =
        SeasonRepository(HoopsDynastyDatabase.getDatabase(application).seasonDao())

    fun insertSeason(season: Season) {
        viewModelScope.launch {
            repository.insertSeason(season)
        }
    }

    fun getSeason() {
        viewModelScope.launch {
            repository.getSeason()
        }
    }

    fun updateSeason(season: Season) {
        viewModelScope.launch {
            repository.updateSeason(season)
        }
    }


}
