package com.example.hw4_database.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hw4_database.databinding.FragmentInputInfoBinding
import com.example.hw4_database.model.User

class InputInfoFragment : Fragment() {

    private var _binding: FragmentInputInfoBinding? = null
    private val binding: FragmentInputInfoBinding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private val userDao by lazy {
        requireContext().appDataBase.userDaoFun()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentInputInfoBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //todo
        with(binding) {
            button.setOnClickListener {
                // жмем на кнопку - инфа отрпавляется в нашу бд и уже оттуда грузится в ресайклер и обновляется список.
                editFirstName.text?.takeIf {
                    it.isNotEmpty()
                }?.let {
                    user ->
                    userDao.insertUser(User(firstName = editFirstName.text.toString(),
                        secondName = editLastName.text.toString()))
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}