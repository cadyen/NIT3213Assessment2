package com.example.nit3213assessment2.data

data class LoginSend(
    val password: String,
    val username: String,
)

data class LoginReceive(
    val keypass: String,
)
