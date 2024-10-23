package com.example.nit3213assessment2.data

import com.example.nit3213assessment2.network.ApiService
import javax.inject.Inject

class ApiRepo @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getKeyPass(newObject: LoginSend): LoginReceive {
        return apiService.getKeyPass(newObject)
    }

    suspend fun getAllEntities(): ResponseItem {
        return apiService.getAllEntities()
    }
}