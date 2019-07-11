package hr.foreal.showsmarkosoljic.ui.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.jakewharton.rxbinding2.InitialValueObservable
import hr.foreal.showsmarkosoljic.ui.router.Router
import hr.foreal.showsmarkosoljic.ui.router.RouterImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter : LoginContract.Presenter {
    companion object {
        @JvmStatic
        val INTENT_KEY = "KEY"
    }

    private lateinit var view: LoginContract.View
    private lateinit var router: Router


    override fun setView(view: LoginContract.View) {
        this.view = view
        this.router = RouterImpl(view as Activity)

    }


    override fun subscribeToUserNameObservable(observable: InitialValueObservable<CharSequence>) {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                checkUsername(it.toString())
                Log.d("marko", it.toString())
            }
    }

    override fun subscribeToPasswordObservable(observable: InitialValueObservable<CharSequence>) {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())//ovo bi trebala biti cista kotlin klasa? Nista android specific? Jel smije tu onda rx ici?
            .subscribe {
                checkPassword(it.toString());
                Log.d("marko", it.toString())
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
        if (password.length < 8) {
            view.onPasswordInvalid()
        } else {
            view.onPasswordValid()
        }

    }

    override fun login(intent: Intent) {

        router.showMainScreen(intent)

    }


}