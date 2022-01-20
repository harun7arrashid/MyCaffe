package com.harun.testtechnopartner.utils

import android.content.Context
import android.content.SharedPreferences
import com.harun.testtechnopartner.R

class SharedPref(context: Context) {

    private var pref: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    private val TOKEN_TYPE = "token_type"
    private val EXPIRES_IN = "expires_in"
    private val ACCESS_TOKEN = "access_token"
    private val REFRESH_TOKEN = "refresh_token"
    private val LOGIN = "login"

    init {
        pref   = context.getSharedPreferences("my_caffe_pref", Context.MODE_PRIVATE)
        editor = pref?.edit()
    }

    var login: Boolean?
        get() = pref?.getBoolean(LOGIN, false)
        set(login) {
            if (login != null) editor?.putBoolean(LOGIN, login)
            editor?.apply()
        }

    var token_type: String?
    get() = pref?.getString(TOKEN_TYPE, "")
    set(value) {
        editor?.putString(TOKEN_TYPE, value)
        editor?.apply()
    }

    var expires_in: Int?
        get() = pref?.getInt(EXPIRES_IN, 0)
        set(value) {
            editor?.putInt(EXPIRES_IN, value ?: 0)
            editor?.apply()
        }

    var access_token: String?
        get() = pref?.getString(ACCESS_TOKEN, "")
        set(value) {
            editor?.putString(ACCESS_TOKEN, value)
            editor?.apply()
        }

    var refresh_token: String?
        get() = pref?.getString(REFRESH_TOKEN, "")
        set(value) {
            editor?.putString(REFRESH_TOKEN, value)
            editor?.apply()
        }
}