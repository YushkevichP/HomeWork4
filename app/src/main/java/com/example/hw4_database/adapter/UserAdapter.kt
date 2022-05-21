package com.example.hw4_database.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw4_database.R
import com.example.hw4_database.database.UserDao
import com.example.hw4_database.databinding.ItemDbBinding
import com.example.hw4_database.model.User

class UserAdapter(
    private val onUserClicked: (User, View) -> Unit,
) : ListAdapter<User, UserViewHolder>(DIFF_UTILS) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder(
            binding = ItemDbBinding.inflate(layoutInflater, parent, false),
            onUserClicked = onUserClicked
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_UTILS = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.firstName == newItem.firstName
                        && oldItem.secondName == newItem.secondName
            }
        }
    }
}

class UserViewHolder(
    private val binding: ItemDbBinding,
    private val onUserClicked: (user:User, view:View) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {

        with(binding) {

            imageButton.setOnClickListener {
                onUserClicked(user,imageButton)
            }


            idTextView.text = user.id.toString()
            firstNameTextView.text = user.firstName.toString()
            secondNameTextView.text = user.secondName.toString()
        }
    }


}