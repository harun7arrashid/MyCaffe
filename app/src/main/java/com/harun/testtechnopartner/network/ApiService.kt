package com.harun.testtechnopartner.network

import com.harun.testtechnopartner.network.response.ResponseHome
import com.harun.testtechnopartner.network.response.ResponseLogin
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun login(
        @Field("grant_type") grant_type: String = "password",
        @Field("client_secret") client_secret: String = "0a40f69db4e5fd2f4ac65a090f31b823",
        @Field("client_id") client_id: String = "e78869f77986684a ",
        @Field("username") username : String = "support@technopartner.id",
        @Field("password") password: String = "1234567"
        ): ResponseLogin

    @GET("api/home")
    suspend fun home(
        @Header("Authorization") Authorization: String
    ): ResponseHome
}