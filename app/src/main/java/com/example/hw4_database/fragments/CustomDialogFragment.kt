package com.example.hw4_database.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.hw4_database.databinding.FragmentEditUserDialogBinding
import com.example.hw4_database.model.User

class CustomDialogFragment private constructor() : DialogFragment() {

    private var _binding: FragmentEditUserDialogBinding? = null
    private val binding: FragmentEditUserDialogBinding get() = requireNotNull(_binding)

    private val userDao by lazy {
        requireContext().appDataBase.userDaoFun()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        return FragmentEditUserDialogBinding.inflate(inflater, container, false)
            .also {
                _binding = it
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editNameField = requireArguments().getString(FIRST_NAME_KEY)
        val editLastNameField = requireArguments().getString(LAST_NAME_KEY)
        val userId = requireArguments().getLong(USER_ID)

        with(binding) {

            editFirstName.text?.append(editNameField)
            editLastName.text?.append(editLastNameField)
            cancelButton.setOnClickListener {
                dismiss()
            }
            submitButton.setOnClickListener {
                val name = editFirstName.text.toString()
                val last = editLastName.text.toString()
                userDao.updateUser(User(id = userId, firstName = name, secondName = last))
                dismiss()
            }
        }
    }

    companion object {
        const val FIRST_NAME_KEY = "firstNameKey"
        const val LAST_NAME_KEY = "lastNameKey"
        const val USER_ID = "user_id"

        fun getInstance(user: User) = CustomDialogFragment().apply {
            val userFirstName = user.firstName.toString()
            val userLastName = user.secondName.toString()
            val userId = user.id

            arguments = bundleOf(FIRST_NAME_KEY to userFirstName,
                LAST_NAME_KEY to userLastName,
                USER_ID to userId)
        }
    }
}