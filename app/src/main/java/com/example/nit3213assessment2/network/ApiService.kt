package com.example.nit3213assessment2.network

import com.example.nit3213assessment2.data.ResponseItem
import com.example.nit3213assessment2.data.LoginSend
import com.example.nit3213assessment2.data.LoginReceive
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/footscray/auth")
    suspend fun getKeyPass(@Body data: LoginSend): LoginReceive

    @GET("/dashboard/books")
    suspend fun getAllEntities(): ResponseItem
}