package hr.foreal.showsmarkosoljic.ui.login

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.*
import com.jakewharton.rxbinding2.widget.textChanges
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.router.RouterImpl
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import hr.foreal.showsmarkosoljic.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LifecycleOwner {


    private lateinit var viewModel: LoginViewModel
    private var usernameValid = false
    private var passwordValid = false
    private lateinit var lifecycleRegistry: LifecycleRegistry


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModel.isEmailValid().observe(this, Observer {
            when (it) {
                true -> onUsernameValid()
                false -> onUserNameEmpty()
            }
        })
        viewModel.isPasswordValid().observe(this, Observer {
            if (it) {
                onPasswordValid()
            } else {
                onPasswordInvalid()
            }
        })
        btnLogin.setOnClickListener { onBtnLoginClicked() }

        etEpisodeName.doAfterTextChanged { userNameTextChanged() }
        etPassword.doAfterTextChanged { passwordTextChanged() }

    }


    private fun onBtnLoginClicked() {

        if (usernameValid && passwordValid) {
            passwordEtInputLayout.isPasswordVisibilityToggleEnabled = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(LoginViewModel.INTENT_KEY, etEpisodeName.text.toString())
            showMainScreen(intent)
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

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    //navigation

    private fun showMainScreen(intent: Intent) {
        startActivity(intent)
        finish()
    }

}
