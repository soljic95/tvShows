package hr.foreal.showsmarkosoljic.ui.login

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
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


    }


    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }


}
