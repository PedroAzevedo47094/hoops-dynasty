package com.dam.hoopsdynasty.util

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseUtils {
    private var databaseReference: DatabaseReference? = null

    fun getDatabaseReference(): DatabaseReference {
        if (databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().reference
        }
        return databaseReference!!
    }
}
