package com.example.hw4_database.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.SearchView
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

    private val layoutManager = LinearLayoutManager(view?.context)
    private val adapter by lazy {
        UserAdapter { user, view ->
            showPopUpMenu(user, view)
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

        with(binding) {
            recyclerView.addSpaceDecoration(resources.getDimensionPixelSize(R.dimen.bottom_space))
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
            val list = userDao.getAllUsers()
            adapter.submitList(list)
            searchUserMenu()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun initRecyclerView() {
//        with(binding) {
//            recyclerView.addSpaceDecoration(resources.getDimensionPixelSize(R.dimen.bottom_space))
//            recyclerView.adapter = adapter
//            recyclerView.layoutManager = layoutManager
//            val list = userDao.getAllUsers()
//            adapter.submitList(list)
//        }
//    }

    private fun showPopUpMenu(user: User, view: View) {
        val popUp = PopupMenu(requireContext(), view, Gravity.FILL)
        popUp.menuInflater.inflate(R.menu.menu_delete_edit, popUp.menu)
        popUp.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.edit_user -> {
                    showCustomEditDialog(user)
                    true
                }
                R.id.delete_user -> {
                    showDeleteDialog(user)
                    true
                }
                else -> false
            }
        }
        popUp.show()
    }

    private fun showDeleteDialog(user: User) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.removing_user)
            .setMessage(R.string.are_you_sure_you_want_to_remove_this_user)
            .setPositiveButton(R.string.yes) { _, _ ->
                userDao.delete(user = user)
                val updateList = userDao.getAllUsers()
                adapter.submitList(updateList)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun showCustomEditDialog(user: User) {
        CustomDialogFragment.getInstance(user).show(childFragmentManager, null)

        val updateList = userDao.getAllUsers()
        adapter.submitList(updateList)

    }

    private fun searchUserMenu() {
        with(binding) {
            toolbar.inflateMenu(R.menu.menu_search)
            toolbar.setOnMenuItemClickListener {
                //https://www.youtube.com/watch?v=CTvzoVtKoJ8&ab_channel=yoursTRULY
                when (it.itemId) {
                    R.id.search_users -> {
                        val searchView = it.actionView as? SearchView
                        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                            override fun onQueryTextSubmit(query: String?): Boolean {
                                return false
                            }

                            override fun onQueryTextChange(newText: String?): Boolean {
                                val listUser = userDao.getAllUsers()
                                listUser.toMutableList()
                                val filteredListUsers = listUser.filter {
                                    it.firstName.toString().contains(newText ?: "", true)
                                            || it.secondName.toString()
                                        .contains(newText ?: "", true)
                                }
                                adapter.submitList(filteredListUsers)
                                //Toast.makeText(requireContext(),"$sortedList", Toast.LENGTH_LONG).show()
                                return true
                            }
                        })
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }
    }
}


