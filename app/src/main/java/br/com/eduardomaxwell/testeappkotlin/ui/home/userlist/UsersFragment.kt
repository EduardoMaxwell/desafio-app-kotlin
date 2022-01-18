package br.com.eduardomaxwell.testeappkotlin.ui.home.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.eduardomaxwell.testeappkotlin.R
import br.com.eduardomaxwell.testeappkotlin.databinding.FragmentUsersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {

    private val viewModel: UsersViewModel by viewModel()

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userAdapter = UserAdapter()

        setupRecycler(userAdapter)
        setupListeners()
    }

    private fun setupListeners() {
        binding.fabAddUser.setOnClickListener {
            findNavController().navigate(R.id.action_users_fragment_to_add_user_fragment)
        }
    }

    private fun setupRecycler(userAdapter: UserAdapter) {
        binding.rvUsers.layoutManager = LinearLayoutManager(this.context)
        binding.rvUsers.adapter = userAdapter
        binding.rvUsers.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.users.observe(viewLifecycleOwner, { users ->
            users.let {
                userAdapter.submitList(it)
            }
        })
    }

}