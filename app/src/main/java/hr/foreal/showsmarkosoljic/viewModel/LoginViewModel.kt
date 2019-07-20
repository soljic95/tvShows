package hr.foreal.showsmarkosoljic.viewModel

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding2.InitialValueObservable
import hr.foreal.showsmarkosoljic.router.Router
import hr.foreal.showsmarkosoljic.ui.login.LoginContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val router: Router) : ViewModel() {
    companion object {
        @JvmStatic
        val INTENT_KEY = "KEY"
    }

    private val PASSWORD_LENGTH = 8
    private lateinit var view: LoginContract.View


    fun setView(view: LoginContract.View) {
        this.view = view
    }


    fun subscribeToUserNameObservable(observable: InitialValueObservable<CharSequence>) {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                checkUsername(it.toString())
            }
    }

    fun subscribeToPasswordObservable(observable: InitialValueObservable<CharSequence>) {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                checkPassword(it.toString())
            }
    }

    private fun checkUsername(username: String) {
        if (username.isEmpty()) {
            view.onUserNameEmpty()
        } else {
            view.onUsernameValid()
        }
    }

    private fun checkPassword(password: String) {
        if (password.length < PASSWORD_LENGTH) {
            view.onPasswordInvalid()
        } else {
            view.onPasswordValid()
        }

    }

    fun login(intent: Intent) {

        router.showMainScreen(intent)

    }


}