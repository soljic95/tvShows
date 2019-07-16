package hr.foreal.showsmarkosoljic.base

import hr.foreal.showsmarkosoljic.router.Router
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