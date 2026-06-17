package com.naufalabdullah0109.assessment3.network

import com.naufalabdullah0109.assessment3.model.Komik
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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