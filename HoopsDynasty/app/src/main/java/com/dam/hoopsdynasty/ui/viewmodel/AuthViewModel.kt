package com.dam.hoopsdynasty.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun registerUser(email: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                return@withContext result.user != null
            } catch (e: Exception) {
                // Handle registration error
                return@withContext false
            }
        }
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                return@withContext result.user != null
            } catch (e: Exception) {
                // Handle login error
                return@withContext false
            }
        }
    }

    fun logoutUser() {
        auth.signOut()
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}
