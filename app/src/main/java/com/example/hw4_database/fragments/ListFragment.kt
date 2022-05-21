package com.example.hw4_database.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw4_database.R
import com.example.hw4_database.adapter.UserAdapter
import com.example.hw4_database.databinding.FragmentListBinding
import com.example.hw4_database.extensions.addSpaceDecoration
import com.example.hw4_database.model.User

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private val adapter by lazy {
        UserAdapter { user, view ->
           showPopUpMenu(user,view)

        }
    }

    private val userDao by lazy {
        requireContext().appDataBase.userDaoFun()
    }

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

        val layoutManager = LinearLayoutManager(view.context)

        with(binding) {

            recyclerView.addSpaceDecoration(resources.getDimensionPixelSize(R.dimen.bottom_space))
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager


        }
        with(binding) {
            val list = userDao.getAllUsers()
            adapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showPopUpMenu(user: User, view: View) {

        val popUp = PopupMenu(requireContext(), view, Gravity.FILL)
        popUp.menuInflater.inflate(R.menu.menu_delete_edit, popUp.menu)
        popUp.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.edit_user -> {
                    Toast.makeText(requireContext(), "Should be EDIT Dialog", Toast.LENGTH_SHORT)
                        .show()
                    true
                }
                R.id.delete_user -> {
                    showDeleteDialog()
                    true
                }
                else -> false
            }
        }
        popUp.show()
    }

    private fun showDeleteDialog(){
        AlertDialog.Builder(requireContext())
            .setTitle("DELETE TITLE")
            .setMessage("Are you sure?")
            .setPositiveButton(android.R.string.ok){
                    dialog, buttonId -> //todo deleting element

            }
            .setNegativeButton(android.R.string.cancel,null)
            .show()
    }

    private fun showCustomEditDialog(){

    }
}





//private fun showPopUpMenu(view: View) {
//
//    val popUp = PopupMenu(requireContext(), view, Gravity.FILL)
//    popUp.menuInflater.inflate(R.menu.menu_delete_edit, popUp.menu)
//    popUp.setOnMenuItemClickListener {
//        when (it.itemId) {
//            R.id.edit_user -> {
//                Toast.makeText(requireContext(), "Should be EDIT Dialog", Toast.LENGTH_SHORT)
//                    .show()
//                true
//            }
//            R.id.delete_user -> {
//                showDeleteDialog()
//                true
//            }
//            else -> false
//        }
//    }
//    popUp.show()
//}
//
//private fun showDeleteDialog(){
//    AlertDialog.Builder(requireContext())
//        .setTitle("DELETE TITLE")
//        .setMessage("Are you sure?")
//        .setPositiveButton(android.R.string.ok){
//                dialog, buttonId -> //todo deleting element
//
//        }
//        .setNegativeButton(android.R.string.cancel,null)
//        .show()
//}

