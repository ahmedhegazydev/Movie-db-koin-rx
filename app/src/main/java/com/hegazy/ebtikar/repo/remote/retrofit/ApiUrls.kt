package com.hegazy.ebtikar.repo.remote.retrofit

object ApiUrls {

    private const val API_KEY = "242d544fe443aa59e56d47a3d5f2d6c4"
    const val BASE_IMAGE_PATH = "https://image.tmdb.org/t/p/w780//"
    const val GET_ALL_POPULAR_PEOPLE = "person/popular?api_key=$API_KEY&language=en-US&page="
    const val GET_PEOPLE_IMAGES = "person/{person_id}/images"

}
