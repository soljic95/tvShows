package hr.foreal.showsmarkosoljic.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import hr.foreal.showsmarkosoljic.ui.dagger.component.DaggerFragmentComponent
import hr.foreal.showsmarkosoljic.ui.dagger.component.FragmentComponent

abstract class BaseFragment : Fragment() {

    private lateinit var fragmentComponent: FragmentComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentComponent = DaggerFragmentComponent.builder()
            .activityComponent((activity as BaseActivity).getActivityComponent())
            .build()

        inject(fragmentComponent)
    }

    abstract fun inject(fragmentComponent: FragmentComponent)

    abstract fun getPresenter(): BasePresenter

    override fun onPause() {
        getPresenter().deactivate()
        super.onPause()
    }

    override fun onResume() {
        getPresenter().activate()
        super.onResume()
    }


}