package com.example.moviescope.util.interceptor

import com.example.moviescope.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ApiAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val myRequest = chain.request().newBuilder()
            .addHeader(Constants.AUTHORIZATION_PARAM, Constants.AUTHORIZATION_KEY)
            .build()
        return chain.proceed(myRequest)
    }
}