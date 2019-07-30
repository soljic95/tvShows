package hr.foreal.showsmarkosoljic.viewModel

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding2.InitialValueObservable
import hr.foreal.showsmarkosoljic.networkModels.RegisterUserResponse
import hr.foreal.showsmarkosoljic.networkModels.TokenData
import hr.foreal.showsmarkosoljic.networkModels.UserLoginModel
import hr.foreal.showsmarkosoljic.repository.TvShowRepository
import hr.foreal.showsmarkosoljic.router.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val repository: TvShowRepository) : ViewModel() {
    companion object {
        @JvmStatic
        val INTENT_KEY = "KEY"
    }


    private lateinit var router: Router
    private var registerUserResponse: MutableLiveData<RegisterUserResponse> = MutableLiveData()
    private var loginUserResponse: MutableLiveData<TokenData> = MutableLiveData()


    fun setRouter(router: Router) {
        this.router = router
    }

    fun showLoginFragment() {
        router.showLoginFragment()
    }

    fun onUpButtonClicked() {
        router.goBack()
    }

    fun showCreateAccount() {
        router.showCreateAccount()
    }

    fun createAccount(email: String, password: String) {
        observeRegisterUserResponseInRepo()
        observeLoginUserResponseInRepo()
        repository.createAccount(UserLoginModel(email, password))
    }

    fun login(email: String, password: String) {
        observeLoginUserResponseInRepo()
        repository.login(UserLoginModel(email, password))
    }

    private fun observeRegisterUserResponseInRepo() {
        repository.observeRegisterUserResponse().observeForever {
            registerUserResponse.value = it
        }
    }

    fun getRegisterUserResponse(): LiveData<RegisterUserResponse> {
        return registerUserResponse
    }

    fun getLoginUserResponse(): LiveData<TokenData> {
        return loginUserResponse
    }

    private fun observeLoginUserResponseInRepo() {
        repository.observeLoginResponseData().observeForever {
            loginUserResponse.value = it
        }
    }


}