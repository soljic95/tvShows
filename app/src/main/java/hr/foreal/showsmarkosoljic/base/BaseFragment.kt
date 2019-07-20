package hr.foreal.showsmarkosoljic.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setPresenter()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun getPresenter(): BasePresenter

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