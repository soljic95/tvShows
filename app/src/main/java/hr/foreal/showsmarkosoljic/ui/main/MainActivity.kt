package hr.foreal.showsmarkosoljic.ui.main

import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.ui.login.LoginActivity
import hr.foreal.showsmarkosoljic.ui.ui.login.LoginPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_welcome.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        presenter.setView(this)
        if (savedInstanceState == null) {
            presenter.showWelcomeFragment(intent.getStringExtra(LoginPresenter.INTENT_KEY))
        }
        setSupportActionBar(toolbar)

    }

    private fun init() {
        this.toolbar = findViewById(R.id.toolbar)
        presenter = MainPresenter()
    }

    fun getToolbar(): androidx.appcompat.widget.Toolbar {
        return this.toolbar
    }


}
