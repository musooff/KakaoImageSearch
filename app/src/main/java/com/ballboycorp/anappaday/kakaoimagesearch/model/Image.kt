package com.ballboycorp.anappaday.kakaoimagesearch.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by musooff on 2019-08-03.
 */

class Image {

    var collection: String? = null
    var datetime: Date? = null
    @SerializedName("display_sitename")
    var displaySitename: String? = null
    @SerializedName("doc_url")
    var docUrl: String? = null
    var height: Int = 0
    @SerializedName("image_url")
    var imageUrl: String? = null
    @SerializedName("thumbnail_url")
    var thumbnailUrl: String? = null
    var width: Int = 0

    inner class ImageSearchResult {
        var meta: Meta? = null
        var documents: List<Image> = listOf()
    }

    inner class Meta {
        @SerializedName("is_end")
        var isEng = false
        @SerializedName("pageable_count")
        var pageableCount: Int = 0
        @SerializedName("total_count")
        var totalCount: Int = 0
    }
}