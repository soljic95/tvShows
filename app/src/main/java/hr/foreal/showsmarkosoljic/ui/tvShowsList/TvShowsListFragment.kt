package hr.foreal.showsmarkosoljic.ui.tvShowsList


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.model.TvShow
import hr.foreal.showsmarkosoljic.viewModel.MainViewModel

class TvShowsListFragment : Fragment(), TvShowsRecyclerAdapter.OnContainerClicked {
    companion object {
        @JvmStatic
        fun newInstance(): TvShowsListFragment {
            return TvShowsListFragment()
        }

        @JvmStatic
        val TV_SHOW_BUNDLE_KEY = "tv_show_bundle_key"
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: TvShowsRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_shows_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        viewModel.getAllShows().observe(this, Observer {
            adapter.setShows(it)
        })

    }


    override fun openShowDetails(show: TvShow) {
        var bundle = Bundle()
        bundle.putParcelable(TV_SHOW_BUNDLE_KEY, show)
        viewModel.listItemClicked(bundle)
    }

    private fun initRecyclerView() {
        adapter = TvShowsRecyclerAdapter(this)
        recyclerView = view!!.findViewById(R.id.tvShowsRecyclerView)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter


    }


}
