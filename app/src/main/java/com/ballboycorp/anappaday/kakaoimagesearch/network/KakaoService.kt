package com.ballboycorp.anappaday.kakaoimagesearch.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by musooff on 2019-08-02.
 */
class KakaoService {

    companion object {

        private const val BASE_URL = "https://dapi.kakao.com"

        @Volatile
        private var api: KakaoAPI? = null

        fun getApi(): KakaoAPI =
            api ?: synchronized(this) {
                api ?: startService(BASE_URL)
                    .also { api = it }
            }

        private fun startService(baseUrl: String): KakaoAPI {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor {
                    val newRequest = it.request().newBuilder()
                        .addHeader("Authorization", "KakaoAK 1bd17ca9d3ec61b17d9fcb01ce672181")
                        .build()
                    it.proceed(newRequest)
                }
                .addNetworkInterceptor(StethoInterceptor())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(
                    GsonConverterFactory
                        .create()
                )
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

            return retrofit.create(KakaoAPI::class.java)
        }
    }

    fun images(query: String, page: Int, perPage: Int) = getApi().images(query, page, perPage)
}