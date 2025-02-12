package com.project.sportify.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseStorage: FirebaseStorage
) : IRemoteDataSource {

    fun loginUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = firebaseAuth.currentUser

            }
            .addOnFailureListener {

            }
    }
}