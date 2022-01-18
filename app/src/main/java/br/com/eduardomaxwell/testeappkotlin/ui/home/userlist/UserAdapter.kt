package br.com.eduardomaxwell.testeappkotlin.ui.home.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.eduardomaxwell.testeappkotlin.model.UserModel
import br.com.eduardomaxwell.testeappkotlin.databinding.UserItemBinding

class UserAdapter : ListAdapter<UserModel, UserAdapter.UserViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        return UserViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    class UserViewHolder(
        binding: UserItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val matricula = binding.tvItemMatricula
        private val cpf = binding.tvItemCpf
        private val email = binding.tvItemEmail

        fun bind(user: UserModel) {
            matricula.text = user.matricula
            cpf.text = user.cpf
            email.text = user.email
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<UserModel>() {
            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}