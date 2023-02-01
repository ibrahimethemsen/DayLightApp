package com.daylightapp.data

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source

/** API'a yapılan istek için yanıt olarak dosyayı okuyup
 *  kuyruga ekliyoruz.Istek atıldıktan sonra okunan dosya
 *  yanıt olarak gonderiliyor
 * */
fun MockWebServer.enqueueResponse(fileName: String) {
    javaClass.classLoader?.let {
        val inputStream = it.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
            .setBody(source.readString(Charsets.UTF_8))
        this.enqueue(mockResponse)
    }
}
