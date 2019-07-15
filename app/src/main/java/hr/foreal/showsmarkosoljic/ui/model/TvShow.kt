package hr.foreal.showsmarkosoljic.ui.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "TvShow")
data class TvShow(
    var id: Int = 1,
    var listItemImageId: Int,
    var showDetailsImageId: Int,
    var name: String,
    var airDate: Long,
    var listOfEpisodes: ArrayList<String> = arrayListOf(),
    var showDescription: String


) : Parcelable
