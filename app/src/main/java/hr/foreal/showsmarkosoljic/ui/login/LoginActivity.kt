package hr.foreal.showsmarkosoljic.ui.login

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.*
import com.jakewharton.rxbinding2.widget.textChanges
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.repository.TvShowRepositoryImpl
import hr.foreal.showsmarkosoljic.router.RouterImpl
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import hr.foreal.showsmarkosoljic.viewModel.LoginViewModel
import hr.foreal.showsmarkosoljic.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_register_user.*

class LoginActivity : AppCompatActivity(), LifecycleOwner {


    private lateinit var viewModel: LoginViewModel
    private lateinit var lifecycleRegistry: LifecycleRegistry


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)

        if (savedInstanceState == null) {
            viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return LoginViewModel(
                        TvShowRepositoryImpl()
                    ) as T
                }
            })[LoginViewModel::class.java]
            viewModel.setRouter(RouterImpl(this@LoginActivity, supportFragmentManager))
            viewModel.showLoginFragment()
        } else {
            viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
            viewModel.setRouter(RouterImpl(this@LoginActivity, supportFragmentManager))
        }

        //da ne stavljamo intent preko viewModela
        viewModel.getLoginUserResponse().observe(this, Observer {
            if(it !=null){
                if (it.tokenData.userToken == "null") {
                    Toast.makeText(this, "This combination does not exist", Toast.LENGTH_SHORT).show()
                }else {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra(LoginViewModel.INTENT_KEY, it.tokenData.userToken)
                    startActivity(intent)
                    finish()
                }
            }

        })


    }


    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }


}
