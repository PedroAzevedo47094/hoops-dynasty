package com.dam.hoopsdynasty.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase
import com.dam.hoopsdynasty.data.model.Manager
import com.dam.hoopsdynasty.data.repository.ManagerRepository
import com.dam.hoopsdynasty.data.repository.PlayerRepository
import kotlinx.coroutines.launch

class ManagerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ManagerRepository =
        ManagerRepository(HoopsDynastyDatabase.getDatabase(application).managerDao())

    fun insertManager(name: String, password: String) {
        val manager = Manager(name = name, password = password, team = null)

        viewModelScope.launch {
            repository.insertManager(manager)
        }
    }

    fun getManager() {
        viewModelScope.launch {
            repository.getManager()
        }
    }

    fun updateManager(manager: Manager) {
        viewModelScope.launch {
            repository.updateManager(manager)
        }
    }

}
