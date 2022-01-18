package br.com.eduardomaxwell.testeappkotlin.ui.home.adduser

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.eduardomaxwell.testeappkotlin.R
import br.com.eduardomaxwell.testeappkotlin.databinding.FragmentAddUserBinding
import br.com.eduardomaxwell.testeappkotlin.model.UserModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddUserFragment : Fragment() {
    private val viewModel: AddUserViewModel by viewModel()
    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: AddUserFragmentArgs by navArgs()
    private lateinit var user: UserModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = navigationArgs.userId
        if (userId > 0) {
            viewModel.getUserBy(userId).observe(this.viewLifecycleOwner) { userSelected ->
                user = userSelected
                bind(user)
            }
        } else {
            binding.btnSalvar.setOnClickListener {
                if (isEntryValid()) {
                    addUser()
                } else {
                    Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

    private fun addUser() {
        val mat = binding.edtMatricula.text
        val cpf = binding.edtCpf.text
        val email = binding.edtEmail.text

        viewModel.addUser(mat.toString(), cpf.toString(), email.toString())
        Toast.makeText(
            requireContext(),
            "Usuário salvo com sucesso",
            Toast.LENGTH_SHORT
        )
            .show()
        findNavController().navigate(R.id.action_addUserFragment_to_usersListFragment)
    }

    private fun bind(user: UserModel) {

        binding.edtMatricula.setText(user.matricula)
        binding.edtCpf.setText(user.cpf)
        binding.edtEmail.setText(user.email)

        binding.btnSalvar.setOnClickListener { updateUser() }

    }

    private fun updateUser() {
        if (isEntryValid()) {
            viewModel.updateUser(
                this.navigationArgs.userId,
                this.binding.edtMatricula.text.toString(),
                this.binding.edtCpf.text.toString(),
                this.binding.edtEmail.text.toString(),
            )
            Toast.makeText(requireContext(), "Usuário atualizado com sucesso", Toast.LENGTH_SHORT)
                .show()
            val action = AddUserFragmentDirections.actionAddUserFragmentToUsersListFragment()
            this.findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.edtMatricula.text.toString(),
            binding.edtCpf.text.toString(),
            binding.edtEmail.text.toString()
        )
    }
}