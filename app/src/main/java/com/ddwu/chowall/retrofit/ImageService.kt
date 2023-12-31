package com.ddwu.chowall.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageService {
    @GET("image/{id}")
    fun getImage(@Path("id") id : Int) : Call<ResponseBody>
}