package ru.pg13lac.nbanews.data.service

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TestApiHolder {
    private val URL = "https://api-nba-v1.p.rapidapi.com/"
    private val httpClientBuilder = OkHttpClient.Builder()
    private val logging = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val api = Retrofit.Builder()
        .client(httpClientBuilder.addInterceptor(logging).build())
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(TestApi::class.java)
}