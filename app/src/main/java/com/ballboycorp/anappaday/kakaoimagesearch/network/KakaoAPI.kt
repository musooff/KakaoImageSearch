package com.ballboycorp.anappaday.kakaoimagesearch.network

import com.ballboycorp.anappaday.kakaoimagesearch.model.Image
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by musooff on 2019-08-02.
 */
interface KakaoAPI {

    @GET("/v2/search/image")
    fun images(@Query(value = "query") query: String, @Query(value = "page") page: Int, @Query(value = "size") perPage: Int): Single<Image.ImageSearchResult>
}