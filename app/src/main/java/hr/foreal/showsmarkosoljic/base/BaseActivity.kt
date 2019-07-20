package hr.foreal.showsmarkosoljic.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {



    protected abstract fun getPresenter(): BasePresenter

    override fun onPause() {
        getPresenter().deactivate()
        super.onPause()
    }

    override fun onResume() {
        getPresenter().activate()
        super.onResume()
    }

    abstract fun setPresenter()
}