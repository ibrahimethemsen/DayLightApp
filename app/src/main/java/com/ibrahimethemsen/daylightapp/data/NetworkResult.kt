package com.ibrahimethemsen.daylightapp.data

sealed class NetworkResult<out T : Any>(){
    class Success<out T : Any>(val data : T) : NetworkResult<T>()
    class Error(val exception : Exception) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}
