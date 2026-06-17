package com.naufalabdullah0109.assessment3.network

import com.naufalabdullah0109.assessment3.model.Komik
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://6a32a4e6c6ca2aee43856036.mockapi.io/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()
interface KomikApiService {

    @GET("komik")
    suspend fun getKomik(
        @Query("userId") userId: String
    ): List<Komik>

    @POST("komik")
    suspend fun postKomik(
        @Body komik: Komik
    ): Komik

    @DELETE("komik/{id}")
    suspend fun deleteKomik(
        @Path("id") id: String
    ): Komik
}

object KomikApi {
    val service: KomikApiService by lazy {
        retrofit.create(KomikApiService::class.java)
    }
}