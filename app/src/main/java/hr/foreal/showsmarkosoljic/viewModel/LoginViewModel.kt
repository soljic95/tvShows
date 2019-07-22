package hr.foreal.showsmarkosoljic.viewModel

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding2.InitialValueObservable
import hr.foreal.showsmarkosoljic.router.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val router: Router) : ViewModel() {
    companion object {
        @JvmStatic
        val INTENT_KEY = "KEY"
    }


    private val isEmailValid: MutableLiveData<Boolean> = MutableLiveData()
    private val isPasswordValid: MutableLiveData<Boolean> = MutableLiveData()
    private val PASSWORD_LENGTH = 8



    fun isEmailValid(): LiveData<Boolean> {
        return isEmailValid
    }

    fun isPasswordValid(): LiveData<Boolean> {
        return isPasswordValid
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
        isEmailValid.value = username.isNotEmpty()
    }

    private fun checkPassword(password: String) {
        isPasswordValid.value = password.length >= PASSWORD_LENGTH

    }

    fun login(intent: Intent) {

        router.showMainScreen(intent)

    }


}