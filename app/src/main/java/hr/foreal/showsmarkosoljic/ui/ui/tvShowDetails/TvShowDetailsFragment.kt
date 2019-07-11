package hr.foreal.showsmarkosoljic.ui.ui.tvShowDetails


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar

import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.main.MainActivity


class TvShowDetailsFragment : Fragment(), TvShowDetailsContract.View {
    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): TvShowDetailsFragment {
            var fragment = TvShowDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var presenter: TvShowDetailsContract.Presenter = TvShowDetailsPresenter()
    private var actionBar: ActionBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        setHasOptionsMenu(true)
        actionBar = (activity as MainActivity).supportActionBar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = "Office"

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> presenter.onUpButtonClicked()
        }
        return true

    }


}
