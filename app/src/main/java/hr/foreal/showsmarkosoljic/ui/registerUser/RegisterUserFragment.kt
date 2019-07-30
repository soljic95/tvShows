package hr.foreal.showsmarkosoljic.ui.registerUser


import android.app.AlertDialog
import android.os.Bundle
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

        btnCreateAccount.setOnClickListener { createAccount() }


    }

    private fun createAccount() {
        observeRegResponse()
        var loginModel = UserLoginModel(etEmail.text.toString(), etPasswordSecond.text.toString())
        alertDialog = AlertDialog.Builder(requireContext())
            .setView(R.layout.progress_dialog)
            .setCancelable(false)
            .create()
        alertDialog.show()
        viewModel.createNewAccount(loginModel)

    }

    private fun observeRegResponse() {
        viewModel.observeRegisterResponse().observe(this, Observer {
            if (it != null) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                alertDialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                alertDialog.dismiss()
            }
        })
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


}


/*

api?.registerUser(loginModel)
?.enqueue(object : Callback<UserInfoResponse> {
    override fun onResponse(
        call: Call<UserInfoResponse>,
        registerUserResponse: Response<UserInfoResponse>
    ) {
        if (registerUserResponse.isSuccessful && registerUserResponse.body() != null) {
            Toast.makeText(requireContext(), registerUserResponse.body()?.id, Toast.LENGTH_SHORT)
                .show()
            api?.loginUser(loginModel)?.enqueue(object : Callback<UserTokenInfo> {

                override fun onResponse(
                    call: Call<UserTokenInfo>,
                    response: Response<UserTokenInfo>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        intent.putExtra(LoginViewModel.INTENT_KEY, etEmail.text.toString())
                        RegisterUserFragment.viewModel.showMainScreen(intent)
                        alertDialog.dismiss()
                    }

                }

                override fun onFailure(call: Call<UserTokenInfo>, t: Throwable) {

                }


            })
        }

    }

    override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
        Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT)
    }


})
*/
