package com.example.hw4_database.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw4_database.R
import com.example.hw4_database.adapter.UserAdapter
import com.example.hw4_database.databinding.FragmentListBinding
import com.example.hw4_database.extensions.addSpaceDecoration

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

   private val adapter = UserAdapter()
//    private val userDao by lazy {
//        requireContext().appDataBase.userDaoFun()
//    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val layoutManager = LinearLayoutManager(view.context)
//
//        with(binding){
//            recyclerView.addSpaceDecoration(resources.getDimensionPixelSize(R.dimen.bottom_space))
//            recyclerView.adapter = adapter
//            recyclerView.layoutManager = layoutManager
//
//        }
//        with(binding){
//
//        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}