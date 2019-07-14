package hr.foreal.showsmarkosoljic.ui.ui.tvShowDetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.adapter.TvShowsRecyclerAdapter
import hr.foreal.showsmarkosoljic.ui.model.TvShow
import kotlinx.android.synthetic.main.fragment_tv_show_details.*


class TvShowDetailsFragment : Fragment(), TvShowDetailsContract.View {
    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): TvShowDetailsFragment {
            var fragment = TvShowDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var tvShow: TvShow
    private var presenter: TvShowDetailsContract.Presenter = TvShowDetailsPresenter()

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
        tvShow = arguments!!.getParcelable(TvShowsRecyclerAdapter.TV_SHOW_BUNDLE_KEY)
        setToolbar()
        init()


    }


    private fun setToolbar() {
        tvShowDetailToolbar.setNavigationOnClickListener { presenter.onUpButtonClicked() }
        toolbar_image.setBackgroundResource(tvShow.showDetailsImageId)

    }

    private fun init() {
        tvShowDescription.text = tvShow.showDescription
        tvShowName.text = tvShow.name

    }


}
