package hr.foreal.showsmarkosoljic.ui.registerUser


import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.networkModels.UserLoginModel
import hr.foreal.showsmarkosoljic.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_register_user.*

class RegisterUserFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): RegisterUserFragment {
            return RegisterUserFragment()
        }

        private lateinit var viewModel: LoginViewModel

    }

    private lateinit var alertDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(LoginViewModel::class.java)
        initToolbar()


        btnCreateAccount.setOnClickListener {
            onCreateAccBtnClicked(
                etEmail.text.toString(),
                etPasswordSecond.text.toString()
            )
        }


    }


    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { backButtonClicked() }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> backButtonClicked()
        }
        return true
    }

    private fun backButtonClicked() {
        viewModel.onUpButtonClicked()
    }

    private fun onCreateAccBtnClicked(email: String, password: String) {
        alertDialog = AlertDialog.Builder(requireContext())
            .setView(R.layout.progress_dialog)
            .create()

        alertDialog.show()

        viewModel.createAccount(email, password)


        viewModel.getRegisterUserResponse().observe(this, Observer {
            if (it != null) {
                Log.d("marko", "the response is nut null, its this : ${it.data.email} ")
            } else {
                alertDialog.dismiss()
                Log.d("marko", "something went wrong and the response is null")
            }
        })
    }


}


