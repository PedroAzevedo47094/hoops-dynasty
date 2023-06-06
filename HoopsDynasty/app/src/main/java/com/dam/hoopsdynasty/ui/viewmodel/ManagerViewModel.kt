package com.dam.hoopsdynasty.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase
import com.dam.hoopsdynasty.data.model.Manager
import com.dam.hoopsdynasty.data.repository.FirestoreAuthRepository
import com.dam.hoopsdynasty.data.repository.ManagerRepository
import kotlinx.coroutines.launch

class ManagerViewModel(application: Application) : AndroidViewModel(application) {

    private val authViewModel = AuthViewModel()
    private val firestoreAuthRepository = FirestoreAuthRepository()
    private val managerRepository: ManagerRepository =
        ManagerRepository(HoopsDynastyDatabase.getDatabase(application).managerDao())

    fun registerManager(email: String, name: String, password: String) {
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
                        team = null
                    )
                    managerRepository.insertManager(manager)
                    managerRepository.insertManager(manager)


                } else {
                    // print error message to user

                }
            }
        }

        fun getManager() {
            viewModelScope.launch {
                val manager = managerRepository.getManager()
                // Handle manager retrieval
            }
        }

        fun updateManager(manager: Manager) {
            viewModelScope.launch {
                managerRepository.updateManager(manager)
            }
        }

        fun logoutUser() {
            authViewModel.logoutUser()
        }
    }
}