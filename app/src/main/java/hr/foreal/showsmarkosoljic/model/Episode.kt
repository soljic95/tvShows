package hr.foreal.showsmarkosoljic.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Episode(
    var title: String,
    var season: String,
    var episode: String,
    var description: String
) : Parcelable
