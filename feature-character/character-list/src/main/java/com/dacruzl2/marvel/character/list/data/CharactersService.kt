package com.dacruzl2.marvel.character.list.data

import com.dacruzl2.marvel.rest.BaseRawReponse
import com.dacruzl2.marvel.character.list.data.response.RawCharacter
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {

    /**
     * Retrieve list of characters
     */
    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") publicKey: String,
        @Query("hash") md5Digest: String,
        @Query("ts") timestamp: Long,
        @Query("offset") offset: Int = 50,
        @Query("limit") limit: Int = 100
    ): BaseRawReponse<List<RawCharacter>>
}