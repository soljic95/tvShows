package hr.foreal.showsmarkosoljic.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    var id: Int = 1,
    var listItemImageId: Int,
    var showDetailsImageId: Int,
    var name: String,
    var airDate: Long,
    var listOfEpisodes: ArrayList<String> = arrayListOf(),
    var showDescription: String


) : Parcelable
