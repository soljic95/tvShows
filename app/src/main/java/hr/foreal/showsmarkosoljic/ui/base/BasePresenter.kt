package hr.foreal.showsmarkosoljic.ui.base

import hr.foreal.showsmarkosoljic.ui.router.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter(private val router: Router) {

    private var compositeDisposable = CompositeDisposable()


    fun activate() {
        //do something
    }

    fun deactivate() {
        compositeDisposable.clear()
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun getRouter(): Router {
        return router
    }


}