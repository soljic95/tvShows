package hr.foreal.showsmarkosoljic.ui.login

import com.jakewharton.rxbinding2.InitialValueObservable

class LoginContract {

    interface View {
        fun onUserNameEmpty()

        fun onPasswordInvalid()

        fun onUsernameValid()

        fun onPasswordValid()

    }

    interface Presenter {

        fun setView(view: View)

        fun subscribeToUserNameObservable(observable: InitialValueObservable<CharSequence>)

        fun subscribeToPasswordObservable(observable: InitialValueObservable<CharSequence>)

        fun login(username: String)

    }
}