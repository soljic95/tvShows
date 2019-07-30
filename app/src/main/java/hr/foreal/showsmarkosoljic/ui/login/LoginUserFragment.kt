package hr.foreal.showsmarkosoljic.ui.login


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login_user.*
import kotlinx.android.synthetic.main.fragment_register_user.*


class LoginUserFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): LoginUserFragment {
            return LoginUserFragment()
        }
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var alertDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(LoginViewModel::class.java)


        btnLogin.setOnClickListener { login(etUsername.text.toString(), etPassword.text.toString()) }
        tvCreateAnAccount.setOnClickListener { showCreateAccountScreen() }
    }

    private fun showCreateAccountScreen() {
        viewModel.showCreateAccount()
    }

    private fun login(email: String, password: String) {
        createAndShowProgressDialog()
        observeLogin()
        viewModel.login(email, password)
    }

    private fun observeLogin() {
        viewModel.getLoginUserResponse().observe(this, Observer {
            if (alertDialog.isShowing) {
                alertDialog.dismiss()
            }
        })
    }

    private fun createAndShowProgressDialog() {
        alertDialog = AlertDialog.Builder(requireContext())
            .setView(R.layout.progress_dialog)
            .setCancelable(false)
            .create()
        alertDialog.show()
    }

}
