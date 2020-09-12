package com.hegazy.ebtikar.model

import com.google.gson.annotations.SerializedName

class PeopleResponse {
    @SerializedName("page")
    val page: Int = 0


    @SerializedName("page")
    val page: Int = 0
    ]
    @SerializedName("message")
    val message: String = ""

    @SerializedName("response")
    val response: Response = Response()

    inner class Response {
        @SerializedName("pageIndex")
        val pageIndex: Int = 0

        @SerializedName("totalPages")
        val totalPages: Int = 0

        @SerializedName("totalCount")
        val totalCount: Int = 0

        @SerializedName("hasPreviousPage")
        val hasPreviousPage: Boolean = false

        @SerializedName("hasNextPage")
        val hasNextPage: Boolean = true

        @SerializedName("items")
        val items: MutableList<PeopleItem> = mutableListOf()


    }


}