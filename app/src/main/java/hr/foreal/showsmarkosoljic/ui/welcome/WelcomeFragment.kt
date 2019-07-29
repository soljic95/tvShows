package hr.foreal.showsmarkosoljic.ui.welcome


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment(), WelcomeContract.View {


    lateinit var viewModel: MainViewModel

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
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        setWelcomeText(arguments?.getString(BUNDLE_KEY))


        showTvShowsListScreen()
    }


    private fun showTvShowsListScreen() {
        viewModel.init()
    }

    private fun setWelcomeText(userName: String?) {
        tvWelcomeMessage.text = String.format(resources.getString(R.string.main_activity_welcome_d), userName)
    }


}
