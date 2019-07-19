package hr.foreal.showsmarkosoljic.ui.welcome


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.base.BaseFragment
import hr.foreal.showsmarkosoljic.base.BasePresenter
import hr.foreal.showsmarkosoljic.router.RouterImpl
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : BaseFragment(), WelcomeContract.View {


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
    }

    override fun setPresenter() {
        presenter = WelcomePresenter(RouterImpl(requireActivity(), requireFragmentManager()))
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
