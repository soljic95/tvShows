package hr.foreal.showsmarkosoljic.ui.ui.tvShowsList


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            R.drawable.office_list_poster,
            R.drawable.the_affair_poster,
            "The Office",
            2005,
            arrayListOf(),
            "A mockumentary on a group of typical office workers, where the workday consists of ego clashes, inappropriate behavior, and tedium."
        )
        val bigBang = TvShow(
            2,
            R.drawable.bang_list_poster, R.drawable.bang_details_poster,
            "The Big Bang Theory",
            2007,
            arrayListOf(),
            "A woman who moves into an apartment across the hall from two brilliant but socially awkward physicists shows them how little they know about life outside of the laboratory."
        )
        val janeTheVirgin = TvShow(
            3,
            R.drawable.jane_list_poster, R.drawable.jane_details_poster,
            "Jane the Virgin",
            2019,
            arrayListOf(),
            "A young, devout Catholic woman discovers that she was accidentally artificially inseminated."
        )

        val house = TvShow(
            3,
            R.drawable.house_list_poster, R.drawable.house_details_poster,
            "House M.D.",
            2019,
            arrayListOf(),
            "An antisocial maverick doctor who specializes in diagnostic medicine does whatever it takes to solve puzzling cases that come his way using his crack team of doctors and his wits."
        )
        val sherlock = TvShow(
            4,
            R.drawable.sherlock_list_poster, R.drawable.sherlock_details_poster,
            "Sherlock",
            2010,
            arrayListOf(),
            "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London."
        )
        with(showList) {
            add(theOffice)
            add(bigBang)
            add(house)
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
