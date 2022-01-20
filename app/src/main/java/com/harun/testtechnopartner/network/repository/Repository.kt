package com.harun.testtechnopartner.network.repository

import com.harun.testtechnopartner.network.ApiConfig

class Repository {

    suspend fun login(username: String, password: String) = ApiConfig.getApiService().login(username = username, password = password)

    suspend fun home(authorizen: String) = ApiConfig.getApiService().home(authorizen)

}