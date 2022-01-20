package com.harun.testtechnopartner.network.response

import com.google.gson.annotations.SerializedName

data class ResponseHome(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Result(

	@field:SerializedName("qrcode")
	val qrcode: String? = null,

	@field:SerializedName("greeting")
	val greeting: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("banner")
	val banner: List<String>? = null,

	@field:SerializedName("saldo")
	val saldo: Int? = null,

	@field:SerializedName("point")
	val point: Int? = null
)
