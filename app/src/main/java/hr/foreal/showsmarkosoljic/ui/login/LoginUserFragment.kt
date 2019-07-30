package hr.foreal.showsmarkosoljic.ui.login


import android.app.AlertDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding2.widget.textChanges
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.network.IsaApi
import hr.foreal.showsmarkosoljic.network.RetrofitClient
import hr.foreal.showsmarkosoljic.networkModels.UserTokenInfo
import hr.foreal.showsmarkosoljic.networkModels.UserLoginModel
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import hr.foreal.showsmarkosoljic.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginUserFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): LoginUserFragment {
            return LoginUserFragment()
        }
    }

    private lateinit var viewModel: LoginViewModel
    private var usernameValid = false
    private var passwordValid = false
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
        viewModel.isEmailValid().observe(this, androidx.lifecycle.Observer {
            if (it) {
                onUsernameValid()
            } else {
                onUserNameEmpty()
            }
        })

        viewModel.isPasswordValid().observe(this, androidx.lifecycle.Observer {
            if (it) {
                onPasswordValid()
            } else {
                onPasswordInvalid()
            }
        })

        btnLogin.setOnClickListener { login() }

        etUsername.doAfterTextChanged { userNameTextChanged() }
        etPassword.doAfterTextChanged { passwordTextChanged() }

        tvCreateAnAccount.setOnClickListener { createAccount() }
    }

    private fun createAccount() {
        viewModel.showCreateAccount()
    }

    private fun userNameTextChanged() {
        viewModel.subscribeToUserNameObservable(etUsername.textChanges())
    }

    private fun passwordTextChanged() {
        passwordEtInputLayout.isPasswordVisibilityToggleEnabled = true
        viewModel.subscribeToPasswordObservable(etPassword.textChanges())
    }

    private fun onUserNameEmpty() {
        usernameValid = false
        btnLogin.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.buttonDisabledPink))
    }

    private fun onPasswordInvalid() {
        passwordValid = false
        btnLogin.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.buttonDisabledPink))
    }

    private fun onUsernameValid() {
        usernameValid = true
        if (passwordValid) {
            btnLogin.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.buttonEnabledPink))
        }
    }

    private fun onPasswordValid() {
        passwordValid = true
        if (usernameValid) {
            btnLogin.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.buttonEnabledPink))
        }
    }

    private fun onBtnLoginClicked() {

        if (usernameValid && passwordValid) {
            passwordEtInputLayout.isPasswordVisibilityToggleEnabled = true
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.putExtra(LoginViewModel.INTENT_KEY, etUsername.text.toString())
            showMainScreen(intent)
        } else {
            if (!usernameValid) {
                etUsername.error = "Username cant be empty"
            }

            if (!passwordValid) {
                passwordEtInputLayout.isPasswordVisibilityToggleEnabled = false
                etPassword.error = "At least 8 characters"

            }
        }

    }


    private fun login() {
        observeToken()
        alertDialog = AlertDialog.Builder(requireContext())
            .setView(R.layout.progress_dialog)
            .create()
        alertDialog.show()

    }

    private fun observeToken() {
        viewModel.observeToken(UserLoginModel(etUsername.text.toString(), etPassword.text.toString()))
            .observe(this, Observer {
                if (it != null) {
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    intent.putExtra(LoginViewModel.INTENT_KEY, it)
                    showMainScreen(intent)
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                } else {
                    alertDialog.dismiss()
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun showMainScreen(intent: Intent) {
        startActivity(intent)
        requireActivity().finish()
    }

}
