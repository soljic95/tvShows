package hr.foreal.showsmarkosoljic.dagger.module

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import hr.foreal.showsmarkosoljic.ui.main.MainContract
import hr.foreal.showsmarkosoljic.ui.main.MainPresenter
import hr.foreal.showsmarkosoljic.router.Router
import hr.foreal.showsmarkosoljic.router.RouterImpl
import hr.foreal.showsmarkosoljic.ui.login.LoginContract
import hr.foreal.showsmarkosoljic.ui.login.LoginPresenter

@Module
class ActivityModule(private val context: Context, private val fragmentManager: FragmentManager) {

    @Provides
    fun provideActivityContext(): Context {
        return context
    }

    @Provides
    fun provideFragmentManager(): FragmentManager {
        return fragmentManager
    }

    @Provides
    fun provideRouter(): Router {
        return RouterImpl(context as Activity, fragmentManager)
    }

    @Provides
    fun provideLoginPresenter(router: Router): LoginContract.Presenter {
        return LoginPresenter(router)
    }

    @Provides
    fun provideMainPresenter(router: Router): MainContract.Presenter {
        return MainPresenter(router)
    }

    /*@Provides
    fun provideLayoutInflater(): LayoutInflater { gdje sve koristiti dagger? da sve providam preko njega?
        return LayoutInflater.from(context)
    }*/

    interface Exposes {

        /*fun getLayoutInflater(): LayoutInflater*/

        fun getRouter(): Router
    }


}