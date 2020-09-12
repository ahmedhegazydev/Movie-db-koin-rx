package com.hegazy.ebtikar.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DetailsResponse : Serializable {

    @SerializedName("profiles")
    val profiles: MutableList<Profile> = mutableListOf()


    data class Profile(

        @SerializedName("width")
        val width: Int = 0,

        @SerializedName("height")
        val height: Int = 0,

        @SerializedName("file_path")
        val file_path: String = ""
    ) {
        companion object {
            fun generateDummyData(): MutableList<Profile> {
                val list: MutableList<Profile> = mutableListOf()
                repeat(10) {
                    list.add(Profile(100, 100, ""))
                }
                return list
            }

        }
    }

}