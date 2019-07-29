package hr.foreal.showsmarkosoljic.ui.registerUser


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.networkModels.UserLoginModel
import hr.foreal.showsmarkosoljic.network.IsaApi
import hr.foreal.showsmarkosoljic.network.RetrofitClient
import hr.foreal.showsmarkosoljic.networkModels.ApiLoginUserResponse
import hr.foreal.showsmarkosoljic.networkModels.ApiRegisterUserResponse
import hr.foreal.showsmarkosoljic.ui.login.LoginActivity
import hr.foreal.showsmarkosoljic.ui.main.MainActivity
import hr.foreal.showsmarkosoljic.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login_user.*
import kotlinx.android.synthetic.main.fragment_register_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterUserFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): RegisterUserFragment {
            return RegisterUserFragment()
        }

        private lateinit var viewModel: LoginViewModel

    }

    private var api: IsaApi? = null

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
        api = RetrofitClient.retrofitInstance?.create(IsaApi::class.java)
        btnCreateAccount.setOnClickListener { createAccount() }

    }

    private fun createAccount() {
        var loginModel = UserLoginModel(etEmail.text.toString(), etPasswordFirst.text.toString())
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(R.layout.progress_dialog)
            .create()
        alertDialog.show()
        api?.registerUser(loginModel)
            ?.enqueue(object : Callback<ApiRegisterUserResponse> {
                override fun onResponse(
                    call: Call<ApiRegisterUserResponse>,
                    registerUserResponse: Response<ApiRegisterUserResponse>
                ) {
                    if (registerUserResponse.isSuccessful && registerUserResponse.body() != null) {
                        Toast.makeText(requireContext(), registerUserResponse.body()?.token, Toast.LENGTH_SHORT)
                            .show()
                        api?.loginUser(loginModel)?.enqueue(object : Callback<ApiLoginUserResponse> {

                            override fun onResponse(
                                call: Call<ApiLoginUserResponse>,
                                response: Response<ApiLoginUserResponse>
                            ) {
                                if (response.isSuccessful && response.body() != null) {
                                    val intent = Intent(requireActivity(), MainActivity::class.java)
                                    intent.putExtra(LoginViewModel.INTENT_KEY, etEmail.text.toString())
                                    viewModel.showMainScreen(intent)
                                    alertDialog.dismiss()
                                }

                            }

                            override fun onFailure(call: Call<ApiLoginUserResponse>, t: Throwable) {

                            }


                        })
                    }

                }

                override fun onFailure(call: Call<ApiRegisterUserResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT)
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
