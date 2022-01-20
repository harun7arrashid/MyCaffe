package com.harun.testtechnopartner.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harun.testtechnopartner.network.repository.Repository
import com.harun.testtechnopartner.network.response.ResponseHome
import com.harun.testtechnopartner.network.response.ResponseLogin
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CaffeViewModel: ViewModel() {

    private val repository = Repository()

    private val _responseLogin = MutableLiveData<ResponseLogin>()
    private val _responseHome  = MutableLiveData<ResponseHome>()
    private val _isToast = MutableLiveData<String>()

    val responseLogin: LiveData<ResponseLogin> = _responseLogin
    val responseHome: LiveData<ResponseHome> = _responseHome
    val isToast: LiveData<String> = _isToast

    fun login(username: String, password: String) {
        viewModelScope.launch(IO) {
            try {
                val result = repository.login(username, password)
                _responseLogin.postValue(result)
            } catch (e: Throwable) {
                _isToast.postValue(e.localizedMessage)
            }
        }
    }

    fun home(token: String) {
        viewModelScope.launch(IO) {
            try {
                val result = repository.home(token)
                _responseHome.postValue(result)
            } catch (e: Throwable) {
                _isToast.postValue(e.localizedMessage)
            }
        }
    }
}

