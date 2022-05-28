package dev.group1.pusatinformasi.network

import dev.group1.pusatinformasi.model.AuthResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Service {

    // Register
    @FormUrlEncoded
    @POST("auth/register")
    fun register(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("alamat") alamat:String
    ):Call<AuthResponse>

    // Login
    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<AuthResponse>

}