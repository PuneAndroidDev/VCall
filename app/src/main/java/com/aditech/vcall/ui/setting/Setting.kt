package com.aditech.vcall.ui.setting

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.aditech.vcall.R
import com.aditech.vcall.ui.videoStreams.VideoCallActivity
import java.util.regex.Pattern

class Setting : Fragment() {

    private lateinit var myAccount: LinearLayout
    private lateinit var changePassword: LinearLayout
    private lateinit var faq: LinearLayout
    private lateinit var support: LinearLayout
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
        myAccount = view.findViewById(R.id.myaccount)
        changePassword = view.findViewById(R.id.changepassword)
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
        alertDialogBuilder.setPositiveButton("Yes",DialogInterface.OnClickListener {
                dialog, which ->
            dialog.dismiss()
        })
        alertDialogBuilder.setNegativeButton("Close",DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

    private fun alertDialogFaq() {

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        val customLayout: View = layoutInflater
            .inflate(R.layout.inflate_faq,null )
        alertDialogBuilder.setView(customLayout)
        alertDialogBuilder.setPositiveButton("Yes",DialogInterface.OnClickListener {
                dialog, which ->
            dialog.dismiss()
        })
        alertDialogBuilder.setNegativeButton("Close",DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
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
            if (!newPassword.text.toString().equals(confirmPassword.text.toString())) {
                newPassword.error = "Password Required Same"
                return@setOnClickListener
            }
            if (!PASSWORD_PATTERN.matcher(newPassword.text.toString()).matches()) {
                newPassword.setError("Password is too weak Requierd [@#$%^&*] and numeric")
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