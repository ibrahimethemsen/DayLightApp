package com.daylightapp.presentation.feedback

import androidx.lifecycle.ViewModel
import com.daylightapp.common.NetworkResult
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@HiltViewModel
class FeedBackViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
) : ViewModel() {


    fun sendFeedBack(
        collectionId : String,
        message : String
    ) : Flow<NetworkResult<Boolean>> {
        val message = hashMapOf(
            "message" to message
        )
        return callbackFlow {
            try {
                firestore.collection(collectionId).add(
                    message
                ).addOnSuccessListener {
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
}