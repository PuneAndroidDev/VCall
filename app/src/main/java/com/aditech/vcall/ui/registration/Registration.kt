package com.aditech.vcall.ui.registration

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aditech.vcall.R
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern
import android.content.DialogInterface
import androidx.navigation.Navigation


private const val TAG = "Registration"

class Registration : Fragment() {

    private lateinit var viewModal: RegistrationViewModal
    private lateinit var name: TextView
    private lateinit var userID: TextView
    private lateinit var password: TextView
    private lateinit var confirmPassword: TextView
    private lateinit var phoneNumber: TextView
    private lateinit var registerButton: Button
    private lateinit var uidStatus: TextInputLayout
    private var REQUIRED: String = "Required Field"
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
        viewModal = ViewModelProvider(this).get(RegistrationViewModal::class.java)
        val view: View = inflater.inflate(R.layout.fragment_registration, container, false)
        name = view.findViewById(R.id.registration_name)
        userID = view.findViewById(R.id.registration_user_id)
        phoneNumber = view.findViewById(R.id.phonenumber)
        confirmPassword = view.findViewById(R.id.confirm_password)
        password = view.findViewById(R.id.register_password)
        registerButton = view.findViewById(R.id.profilecompltionnext)
        uidStatus = view.findViewById(R.id.r2)
        name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModal.generateUID(name.text.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        userID.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModal.checkUserID(userID.text.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        viewModal.checkUserId.observe(viewLifecycleOwner, {
            if (!it) {
                userID.error = "ID already taken"
            }
        })

        viewModal.generatedUserID.observe(viewLifecycleOwner, {
            userID.text = it
        })

        registerButton.setOnClickListener {

            if (userID.text.toString().equals("")) {
                userID.error = REQUIRED
                userID.requestFocus()
                return@setOnClickListener
            }
            if (name.text.toString().equals("")) {
                name.error = REQUIRED
                name.requestFocus()
                return@setOnClickListener
            }
            if (phoneNumber.text.toString().equals("")) {
                phoneNumber.error = REQUIRED
                phoneNumber.requestFocus()
                return@setOnClickListener
            }
            if (password.text.toString().equals("")) {
                password.error = REQUIRED
                password.requestFocus()
                return@setOnClickListener
            }
            if (confirmPassword.text.toString().equals("")) {
                confirmPassword.error = REQUIRED
                confirmPassword.requestFocus()
                return@setOnClickListener
            }
/*
            if (password.text.toString().length<=6) {
                password.error = "Length must greater than 6 characters"
                password.requestFocus()
                return@setOnClickListener
            }
            if (!PASSWORD_PATTERN.matcher(password.text.toString()).matches()) {
                password.error = "Password is too weak"
                return@setOnClickListener
            }*/
            viewModal.checkUserId.observe(viewLifecycleOwner, {
                if (!it) {
                    userID.error = "ID already taken"
                }
                else{
                    viewModal.completeprofile(
                        userID.text.toString(),
                        name.text.toString(),
                        phoneNumber.text.toString(),
                        password.text.toString()
                    )
                }
            })


        }

        viewModal.registrationStatus.observe(viewLifecycleOwner,{
            if(it == true)
            {
                alertBox(true,view)
            }else{
                alertBox(false,view )
            }
        })

        return view
    }

    private fun alertBox(status:Boolean,view: View )
    {
        var title="Done"
        var message="Successfully register"
        var icon:Int=R.drawable.ic_baseline_check_24
        if(!status)
        {
            title="Sorry"
            message="Un-Successful"
            icon=R.drawable.ic_baseline_close_24
        }
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                android.R.string.yes
            ) { dialog, which ->
                Navigation.findNavController(view)
                    .navigate(R.id.action_registration_to_login2)
            }
            .setNegativeButton(android.R.string.no, null)
            .setIcon(icon)
            .show()
    }

}