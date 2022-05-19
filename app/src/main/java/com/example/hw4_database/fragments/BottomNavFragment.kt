package com.example.hw4_database.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hw4_database.R
import com.example.hw4_database.databinding.FragmentBottomNavigationBinding

class BottomNavFragment : Fragment() {

    private var _binding: FragmentBottomNavigationBinding? = null
    private val binding: FragmentBottomNavigationBinding
        get() = requireNotNull(_binding) {
            "OOPS"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentBottomNavigationBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            //находим наш внутренний контроллер
            val nestedController =
                (childFragmentManager.findFragmentById(R.id.page_container) as NavHostFragment)
                    .navController
            bottomNavigation.setupWithNavController(nestedController)

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}