package com.aditech.vcall.ui.setting

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aditech.vcall.R
import com.aditech.vcall.ui.login.LoginViewModal
import com.co_vision.co_vision.Localstorage_Room.SharedPreference.LoginCredentials
import java.util.regex.Pattern

class Setting : Fragment() {

    private lateinit var myAccount: LinearLayout
    private lateinit var changePassword: LinearLayout
    private lateinit var faq: LinearLayout
    private lateinit var support: LinearLayout
    private lateinit var viewModal: LoginViewModal
    private lateinit var name:TextView
    private lateinit var userId:TextView

    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{6,}" +  // at least 4 characters
                "$"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_setting, container, false)
        viewModal = ViewModelProvider(this).get(LoginViewModal::class.java)

        myAccount = view.findViewById(R.id.myaccount)
        changePassword = view.findViewById(R.id.changepassword)
        name = view.findViewById(R.id.loginName)
        userId = view.findViewById(R.id.loginUserID)
        viewModal.getUserModal(LoginCredentials.sharedPreferences.getString("UID", "NAN").toString())
        viewModal.modal.observe(viewLifecycleOwner, Observer {
            it?.let {
                name.text=it.Name
                userId.text=it.UserID
            }
        })
        faq = view.findViewById(R.id.faq)
        support = view.findViewById(R.id.support)
        faq.setOnClickListener { FAQ() }
        changePassword.setOnClickListener { changePassword() }
        support.setOnClickListener { supportTechnology() }
        return view
    }

    private fun supportTechnology() {
        alertDialogSupport()
    }

    private fun changePassword() {
        showChangePassword()
    }

    private fun FAQ() {
        alertDialogFaq()
    }

    private fun alertDialogSupport() {

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        val customLayout: View = layoutInflater
            .inflate(R.layout.supporttechnology,null )
        alertDialogBuilder.setView(customLayout)
        alertDialogBuilder.setPositiveButton("Yes") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Close") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

    private fun alertDialogFaq() {

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        val customLayout: View = layoutInflater
            .inflate(R.layout.inflate_faq,null )
        alertDialogBuilder.setView(customLayout)
        alertDialogBuilder.setPositiveButton("Yes") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Close") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

    private fun showChangePassword() {

        val newPassword: EditText
        val confirmPassword: EditText
        val submitButton: Button
        val close: ImageView

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)

        val customLayoutForUpdatePassword: View = layoutInflater
            .inflate(
                R.layout.inflate_change_password,
                null
            )

        newPassword = customLayoutForUpdatePassword.findViewById(R.id.update_new_password)
        submitButton = customLayoutForUpdatePassword.findViewById(R.id.submitbtn)
        confirmPassword =
            customLayoutForUpdatePassword.findViewById(R.id.update_confirm_password)
        close = customLayoutForUpdatePassword.findViewById(R.id.new_password_close)

        builder.setView(customLayoutForUpdatePassword)

        submitButton.setOnClickListener {
            if (newPassword.text.isEmpty()) {
                newPassword.error = "Required Fields"
                return@setOnClickListener
            }
            if (confirmPassword.text.isEmpty()) {
                confirmPassword.error = "Required Fields"
                return@setOnClickListener
            }
            if (newPassword.text.toString() != confirmPassword.text.toString()) {
                newPassword.error = "Password Required Same"
                return@setOnClickListener
            }
            if (!PASSWORD_PATTERN.matcher(newPassword.text.toString()).matches()) {
                newPassword.error = "Password is too weak Required [@#$%^&*] and numeric"
                return@setOnClickListener
            }

        }

        val dialog: AlertDialog = builder.create()

        close.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

}