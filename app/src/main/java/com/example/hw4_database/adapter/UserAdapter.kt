package com.example.hw4_database.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw4_database.database.UserDao
import com.example.hw4_database.databinding.ItemDbBinding
import com.example.hw4_database.model.User

class UserAdapter(context: Context) : ListAdapter<User, UserViewHolder>(DIFF_UTILS) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            binding = ItemDbBinding.inflate(layoutInflater, parent, false)

        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       holder.bind(getItem(position))
    }

    companion object{
        val DIFF_UTILS = object : DiffUtil.ItemCallback<User>(){
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
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {

        with(binding) {

           firstNameTextView.text = user.firstName.toString()
           secondNameTextView.text = user.secondName.toString()

        }

    }
}