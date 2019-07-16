package hr.foreal.showsmarkosoljic.ui.tvShowsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.model.TvShow
import kotlinx.android.synthetic.main.tv_show_list_item.view.*

class TvShowsRecyclerAdapter(private val listener: OnContainerClicked) :
    RecyclerView.Adapter<TvShowsRecyclerAdapter.MyViewHolder>() {
    companion object {

    }

    private var showList = arrayListOf<TvShow>()

    fun setShows(showList: ArrayList<TvShow>) {
        this.showList = showList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.tv_show_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        return showList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder.itemView) {
            tvEpisodeNumber.text = showList[position].name
            tvEpisodeName.text = showList[position].airDate.toString()
            tvShowsItemLayoutContainer.setOnClickListener { listener.openShowDetails(showList[position]) }

        }//todo pitaj
        Glide.with(holder.itemView)
            .load(showList[position].listItemImageId)
            .into(holder.itemView.tvShowImage)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnContainerClicked {
        fun openShowDetails(show: TvShow)
    }


}