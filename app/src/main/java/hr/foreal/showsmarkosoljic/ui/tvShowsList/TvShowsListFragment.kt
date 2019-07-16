package hr.foreal.showsmarkosoljic.ui.tvShowsList


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.base.BaseFragment
import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.dagger.component.FragmentComponent
import hr.foreal.showsmarkosoljic.model.StaticEpisodes
import hr.foreal.showsmarkosoljic.model.TvShow
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tv_shows_list.*
import javax.inject.Inject

class TvShowsListFragment : BaseFragment(), TvShowsContract.View, TvShowsRecyclerAdapter.OnContainerClicked {


    companion object {
        @JvmStatic
        fun newInstance(): TvShowsListFragment {
            return TvShowsListFragment()
        }

        @JvmStatic
        val TV_SHOW_BUNDLE_KEY = "tv_show_bundle_key"
    }

    @Inject
    lateinit var presenter: TvShowsContract.Presenter

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
        presenter.setView(this)
        initRecyclerView()


    }

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun getPresenter(): BasePresenter {
        return presenter as BasePresenter
    }

    override fun openShowDetails(show: TvShow) {
        var bundle = Bundle()
        bundle.putParcelable(TV_SHOW_BUNDLE_KEY,show)
        presenter.listItemClicked(bundle)
    }

    private fun initRecyclerView() {
        adapter = TvShowsRecyclerAdapter(this)
        recyclerView = view!!.findViewById(R.id.tvShowsRecyclerView)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        adapter.setShows(StaticEpisodes.getShows())

    }





}
