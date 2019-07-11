package hr.foreal.showsmarkosoljic.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.model.TvShow
import kotlinx.android.synthetic.main.tv_show_list_item.view.*

class TvShowsRecyclerAdapter(private val inflater: LayoutInflater, private val listener: OnContainerClicked) :
    RecyclerView.Adapter<TvShowsRecyclerAdapter.MyViewHolder>() {
    companion object {
        @JvmStatic
        val TV_SHOW_BUNDLE_KEY = "tv_show_bundle_key"
    }

    private var showList = arrayListOf<TvShow>()

    fun addShows(showList: ArrayList<TvShow>) {
        this.showList = showList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(inflater.inflate(R.layout.tv_show_list_item, parent, false))
    }

    override fun getItemCount(): Int {

        return showList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder.itemView) {
            tvShowsName.text = showList[position].name
            tvShowsAirDate.text = showList[position].airDate.toString()
            tvShowsItemLayoutContainer.setOnClickListener { listener.openShowDetails(createBundle(showList[position])) }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnContainerClicked {
        fun openShowDetails(bundle: Bundle)
    }

    private fun createBundle(tvShow: TvShow): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(TV_SHOW_BUNDLE_KEY, tvShow)
        return bundle
    }
}