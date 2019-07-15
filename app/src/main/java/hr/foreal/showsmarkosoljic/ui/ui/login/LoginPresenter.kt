package hr.foreal.showsmarkosoljic.ui.ui.login

import android.content.Intent
import com.jakewharton.rxbinding2.InitialValueObservable
import hr.foreal.showsmarkosoljic.ui.base.BasePresenter
import hr.foreal.showsmarkosoljic.ui.router.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter(router: Router) : LoginContract.Presenter, BasePresenter(router) {
    companion object {
        @JvmStatic
        val INTENT_KEY = "KEY"
    }

    private val PASSWORD_LENGTH = 8
    private lateinit var view: LoginContract.View


    override fun setView(view: LoginContract.View) {
        this.view = view
    }


    override fun subscribeToUserNameObservable(observable: InitialValueObservable<CharSequence>) {
        addDisposable(observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                checkUsername(it.toString())
            })
    }

    override fun subscribeToPasswordObservable(observable: InitialValueObservable<CharSequence>) {
        addDisposable(observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                checkPassword(it.toString())
            })
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

    override fun login(intent: Intent) {

        getRouter().showMainScreen(intent)

    }


}