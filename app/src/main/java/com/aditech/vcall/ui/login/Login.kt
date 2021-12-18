package com.aditech.vcall.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.aditech.vcall.MainDashBoardActivity
import com.aditech.vcall.R

private const val REQUIRED="Required Fields"
private const val TAG = "Login"
class Login : Fragment() {

    private lateinit var registerAccount:TextView
    private lateinit var loginButton:Button
    private lateinit var loginUserName:EditText
    private lateinit var loginPassword:EditText
    private lateinit var viewModal: LoginViewModal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View=inflater.inflate(R.layout.fragment_login, container, false)
        viewModal = ViewModelProvider(this).get(LoginViewModal::class.java)
        loginButton=view.findViewById(R.id.login_button)
        loginUserName=view.findViewById(R.id.login_username)
        loginPassword=view.findViewById(R.id.login_password)
        registerAccount=view.findViewById(R.id.register_account)
       /* loginButton.setOnClickListener {
            if (loginUserName.text.toString().equals("")) {
                loginUserName.error = REQUIRED
                loginUserName.requestFocus()
                return@setOnClickListener
            }
            if (loginPassword.text.toString().equals("")) {
                loginPassword.error = REQUIRED
                loginPassword.requestFocus()
                return@setOnClickListener
            }
            viewModal.login(loginUserName.text.toString(),loginPassword.text.toString())
        }

        viewModal.userModal.observe(viewLifecycleOwner,{
            if(it.loginStatus) {
                startActivity(Intent(context, MainDashBoardActivity::class.java))
            }
        })*/

        loginButton.setOnClickListener {
            startActivity(Intent(context, MainDashBoardActivity::class.java))
        }


        registerAccount.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_login2_to_registration)
        }

        return view
    }

}