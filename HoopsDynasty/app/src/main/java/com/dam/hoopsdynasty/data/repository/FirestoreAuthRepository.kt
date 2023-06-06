package com.dam.hoopsdynasty.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirestoreAuthRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun registerUser(email: String, name: String, password: String): FirebaseUser? {
        try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            return authResult.user
        } catch (e: Exception) {
            // Handle registration error
            return null
        }
    }

    suspend fun loginUser(email: String, password: String): FirebaseUser? {
        try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            return authResult.user
        } catch (e: Exception) {
            // Handle login error
            return null
        }
    }

    suspend fun logoutUser() {
        firebaseAuth.signOut()
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}