package hr.foreal.showsmarkosoljic.ui.login

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.jakewharton.rxbinding2.widget.textChanges
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private val presenter: LoginContract.Presenter = LoginPresenter()
    private var usernameValid = false
    private var passwordValid = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.setView(this)

        btnLogin.setOnClickListener { onBtnLoginClicked() }

        etUsername.doAfterTextChanged { userNameTextChanged() }
        etPassword.doAfterTextChanged { passwordTextChanged() }

    }

    private fun onBtnLoginClicked() {

        if (usernameValid && passwordValid) {
            passwordEtInputLayout.isPasswordVisibilityToggleEnabled = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(LoginPresenter.INTENT_KEY, etUsername.text.toString())
            presenter.login(intent)
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

    private fun userNameTextChanged() {
        presenter.subscribeToUserNameObservable(etUsername.textChanges())

    }

    private fun passwordTextChanged() {
        passwordEtInputLayout.isPasswordVisibilityToggleEnabled = true
        presenter.subscribeToPasswordObservable(etPassword.textChanges())

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
