package hr.foreal.showsmarkosoljic.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

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
