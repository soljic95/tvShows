package hr.foreal.showsmarkosoljic.ui.ui.tvShowsList


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.adapter.TvShowsRecyclerAdapter
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import hr.foreal.showsmarkosoljic.ui.model.TvShow

class TvShowsListFragment : Fragment(), TvShowsContract.View, TvShowsRecyclerAdapter.OnContainerClicked {
    companion object {
        @JvmStatic
        fun newInstance(): TvShowsListFragment {
            return TvShowsListFragment()
        }
    }


    private val presenter: TvShowsContract.Presenter = TvShowsPresenter()
    private lateinit var adapter: TvShowsRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var showList: ArrayList<TvShow>
    private val toolbarTitle = "Shows"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_shows_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        initToolbar()
        initShows()
        initRecyclerView()


    }

    override fun openShowDetails(bundle: Bundle) {
        presenter.onItemClicked(bundle)
    }

    private fun initRecyclerView() {
        adapter = TvShowsRecyclerAdapter(LayoutInflater.from(context), this)
        recyclerView = view!!.findViewById(R.id.tvShowsRecyclerView)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        adapter.addShows(showList)
        adapter.notifyDataSetChanged()

    }

    private fun initShows() {
        showList = arrayListOf()
        val theOffice = TvShow(
            1,
            1,
            "Office",
            2005,
            arrayListOf(),
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam luctus id ante at rutrum. Fusce sodales quam dui, in pellentesque ligula accumsan et. Pellentesque lobortis ultrices magna, at dignissim enim cursus a. Morbi eget nunc rhoncus, interdum mauris ornare, pretium turpis. Nunc at nulla ipsum."
        )
        val bigBang = TvShow(
            2,
            1,
            "bigBang",
            2007,
            arrayListOf(),
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam luctus id ante at rutrum. Fusce sodales quam dui, in pellentesque ligula accumsan et."
        )
        val janeTheVirgin = TvShow(
            3,
            1,
            "janeTheVirgin",
            2019,
            arrayListOf(),
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam luctus id ante at rutrum. Fusce sodales quam dui, in pellentesque ligula accumsan et."
        )
        val sherlock = TvShow(
            4,
            1,
            "sherlock",
            2010,
            arrayListOf(),
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam luctus id ante at rutrum. Fusce sodales quam dui, in pellentesque ligula accumsan et."
        )
        with(showList) {
            add(theOffice)
            add(bigBang)
            add(janeTheVirgin)
            add(sherlock)
        }
    }

    private fun initToolbar() {
        var toolbar = (activity as MainActivity).getToolbar()
        toolbar.visibility = View.VISIBLE
        toolbar.title = toolbarTitle
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

    }


}
