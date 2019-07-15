package hr.foreal.showsmarkosoljic.ui.ui.welcome


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.base.BaseFragment
import hr.foreal.showsmarkosoljic.ui.base.BasePresenter
import hr.foreal.showsmarkosoljic.ui.dagger.component.FragmentComponent
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_welcome.*
import javax.inject.Inject

class WelcomeFragment : BaseFragment(), WelcomeContract.View {

    @Inject
    lateinit var presenter: WelcomeContract.Presenter

    companion object {
        val BUNDLE_KEY = "BUNDLE_KEY"
        @JvmStatic
        fun newInstance(userName: String): WelcomeFragment {
            val fragment = WelcomeFragment()
            val bundle = Bundle()
            bundle.putString(BUNDLE_KEY, userName)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        setWelcomeText(arguments?.getString(BUNDLE_KEY))
        showTvShowsListScreen()
        (activity as MainActivity).getToolbar().visibility = View.GONE
    }

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun getPresenter(): BasePresenter {
        return presenter as BasePresenter
    }

    private fun showTvShowsListScreen() {
        presenter.init()
    }

    private fun setWelcomeText(userName: String?) {
        tvWelcomeMessage.text = String.format(resources.getString(R.string.main_activity_welcome_d), userName)
    }


}
