package com.daylightapp.presentation.register

import androidx.lifecycle.ViewModel
import com.daylightapp.common.NetworkResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth : FirebaseAuth
): ViewModel() {
    fun createAccountEmail(
        email : String,
        password : String) : Flow<NetworkResult<Any>> {
        return callbackFlow {
            try {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    trySend(NetworkResult.Success(true))
                }.addOnFailureListener {
                    trySend(NetworkResult.Error(it))
                }
            }catch (e : Exception){
                trySend(NetworkResult.Error(e))
            }
            awaitClose()
        }
    }

    fun isEmpty(
        email: String?,
        password: String?
    ): Boolean{
        return !email.isNullOrBlank() && !password.isNullOrBlank()
    }
}