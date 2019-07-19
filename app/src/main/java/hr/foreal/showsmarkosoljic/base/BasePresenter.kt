package hr.foreal.showsmarkosoljic.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter {

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


}