package com.hegazy.ebtikar.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PeopleResponse {
    @SerializedName("page")
    val page: Int = 0

    @SerializedName("total_results")
    val total_results: Int = 0

    @SerializedName("total_pages")
    val total_pages: Int = 0

    @SerializedName("results")
    val results: MutableList<Result> = mutableListOf()

    inner class Result : Serializable {
        @SerializedName("name")
        val name: String = ""

        @SerializedName("profile_path")
        val profile_path: String = ""

        @SerializedName("known_for_department")
        val known_for_department: String = ""

        @SerializedName("popularity")
        val popularity: Float = 0f


        @SerializedName("id")
        val id: Int = 0

        @SerializedName("adult")
        val adult: Boolean = false


        @SerializedName("known_for")
        val knownFor: MutableList<KnownFor> = mutableListOf()

        inner class KnownFor {


            @SerializedName("title")
            val title: String = ""

            @SerializedName("release_date")
            val release_date: String = ""

            @SerializedName("original_title")
            val original_title: String = ""


            @SerializedName("backdrop_path")
            val backdrop_path: String = ""

            @SerializedName("overview")
            val overview: String = ""

            @SerializedName("poster_path")
            val poster_path: String = ""


        }

    }


}