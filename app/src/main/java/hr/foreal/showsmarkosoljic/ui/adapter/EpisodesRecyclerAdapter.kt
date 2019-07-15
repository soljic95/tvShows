package hr.foreal.showsmarkosoljic.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.model.Episode
import kotlinx.android.synthetic.main.episodes_list_item.view.*

class EpisodesRecyclerAdapter(private val inflater: LayoutInflater) :
    RecyclerView.Adapter<EpisodesRecyclerAdapter.MyViewHolder>() {

    private var listOfEpisodes = arrayListOf<Episode>()

    fun addEpisode(episode: Episode) {
        listOfEpisodes.add(episode)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(inflater.inflate(R.layout.episodes_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listOfEpisodes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder.itemView) {
            tvEpisodeNumber.text = "${position + 1}. "
            tvEpisodeName.text = listOfEpisodes[position].title
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}