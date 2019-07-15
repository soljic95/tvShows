package hr.foreal.showsmarkosoljic.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.login.LoginPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        tvWelcomeMessage.text = String.format(
            resources.getString(R.string.main_activity_welcome_d),
            intent.getStringExtra(LoginPresenter.INTENT_KEY)
        )
    }

}
