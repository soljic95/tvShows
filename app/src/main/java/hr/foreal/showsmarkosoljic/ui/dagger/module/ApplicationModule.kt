package hr.foreal.showsmarkosoljic.ui.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule( val context: Context) {

    @Provides
    fun provideApplicationContext(): Context {
        return context
    }


    interface Exposes {

    }
}