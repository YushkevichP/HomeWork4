package com.example.hw4_database

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.hw4_database.databinding.FragmentContentBinding

class ContentFragment : Fragment() {

    private var _binding: FragmentContentBinding? = null
    private val binding: FragmentContentBinding
        get() = requireNotNull(_binding) {
            "OOPS"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentContentBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {


        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}