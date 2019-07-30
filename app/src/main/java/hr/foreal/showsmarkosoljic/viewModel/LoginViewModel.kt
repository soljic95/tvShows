package hr.foreal.showsmarkosoljic.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.foreal.showsmarkosoljic.networkModels.RegisterUserResponse
import hr.foreal.showsmarkosoljic.networkModels.TokenData
import hr.foreal.showsmarkosoljic.networkModels.UserLoginModel
import hr.foreal.showsmarkosoljic.repository.TvShowRepository
import hr.foreal.showsmarkosoljic.router.Router

class LoginViewModel(private val repository: TvShowRepository) : ViewModel() {
    companion object {
        @JvmStatic
        val INTENT_KEY = "KEY"
    }

    private lateinit var router: Router
    private var registerUserResponse: MutableLiveData<RegisterUserResponse> = MutableLiveData()
    private var loginUserResponse: MutableLiveData<TokenData> = MutableLiveData()

    //navigation
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

    //create and login
    fun createAccount(email: String, password: String) {
        observeRegisterUserResponseInRepo()
        observeLoginUserResponseInRepo()
        repository.createAccount(UserLoginModel(email, password))
    }

    fun login(email: String, password: String) {
        observeLoginUserResponseInRepo()
        repository.login(UserLoginModel(email, password))
    }

    //fill up livedata
    private fun observeRegisterUserResponseInRepo() {
        repository.observeRegisterUserResponse().observeForever {
            registerUserResponse.value = it
        }
    }

    private fun observeLoginUserResponseInRepo() {
        repository.observeLoginResponseData().observeForever {
            loginUserResponse.value = it
        }
    }

    //for observing livedata in view
    fun getRegisterUserResponse(): LiveData<RegisterUserResponse> {
        return registerUserResponse
    }

    fun getLoginUserResponse(): LiveData<TokenData> {
        return loginUserResponse
    }


}