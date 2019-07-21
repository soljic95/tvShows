package hr.foreal.showsmarkosoljic.ui.login

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding2.widget.textChanges
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.router.RouterImpl
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import hr.foreal.showsmarkosoljic.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {
//Todo remove view, implement live data for observing

    private lateinit var viewModel: LoginViewModel
    private var usernameValid = false
    private var passwordValid = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel(
                    RouterImpl(this@LoginActivity, supportFragmentManager)
                ) as T
            }
        })[LoginViewModel::class.java]
        viewModel.setView(this)
        btnLogin.setOnClickListener { onBtnLoginClicked() }

        etEpisodeName.doAfterTextChanged { userNameTextChanged() }
        etPassword.doAfterTextChanged { passwordTextChanged() }

    }


    private fun onBtnLoginClicked() {

        if (usernameValid && passwordValid) {
            passwordEtInputLayout.isPasswordVisibilityToggleEnabled = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(LoginViewModel.INTENT_KEY, etEpisodeName.text.toString())
            viewModel.login(intent)
        } else {
            if (!usernameValid) {
                etEpisodeName.error = "Username cant be empty"
            }

            if (!passwordValid) {
                passwordEtInputLayout.isPasswordVisibilityToggleEnabled = false
                etPassword.error = "At least 8 characters"

            }
        }

    }

    private fun userNameTextChanged() {
        viewModel.subscribeToUserNameObservable(etEpisodeName.textChanges())

    }

    private fun passwordTextChanged() {
        passwordEtInputLayout.isPasswordVisibilityToggleEnabled = true
        viewModel.subscribeToPasswordObservable(etPassword.textChanges())

    }

    override fun onUserNameEmpty() {
        usernameValid = false
        btnLogin.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.buttonDisabledPink))
    }

    override fun onPasswordInvalid() {
        passwordValid = false
        btnLogin.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.buttonDisabledPink))
    }

    override fun onUsernameValid() {
        usernameValid = true
        if (passwordValid) {
            btnLogin.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.buttonEnabledPink))
        }
    }

    override fun onPasswordValid() {
        passwordValid = true
        if (usernameValid) {
            btnLogin.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.buttonEnabledPink))
        }

    }

}
