package com.example.hw4_database.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hw4_database.databinding.FragmentInputInfoBinding

class InputInfoFragment : Fragment() {

    private var _binding: FragmentInputInfoBinding? = null
    private val binding:FragmentInputInfoBinding
    get() = requireNotNull(_binding){
        "View was destroyed"
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
        with(binding){
            button.setOnClickListener {
                // жмем на кнопку - инфа отрпавляется в нашу бд и уже оттуда грузится в ресайклер и обновляется список.
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}