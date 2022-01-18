package br.com.eduardomaxwell.testeappkotlin.ui.user.adduser

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.eduardomaxwell.testeappkotlin.R
import br.com.eduardomaxwell.testeappkotlin.databinding.FragmentAddUserBinding
import br.com.eduardomaxwell.testeappkotlin.model.UserModel
import br.com.eduardomaxwell.testeappkotlin.util.isValidCPF
import br.com.eduardomaxwell.testeappkotlin.util.isValidEmail
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {
    private val viewModel: UserViewModel by viewModel()
    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: UserFragmentArgs by navArgs()
    private lateinit var user: UserModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
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
                    showToast("Preencha todos os campos")
                }
            }
        }
    }

    private fun addUser() {
        val mat = binding.edtMatricula.text
        val cpf = binding.edtCpf.text
        val email = binding.edtEmail.text

        tirarCaractersCPF(cpf.toString())

        if (!email.toString().isValidEmail()) {
            showToast("Digite um e-mail válido")
        } else if (!tirarCaractersCPF(cpf.toString()).isValidCPF()) {
            showToast("Digite um CPF válido")
        } else {
            viewModel.addUser(mat.toString(), cpf.toString(), email.toString())
            showToast("Usuário salvo com sucesso")
            findNavController().navigate(R.id.action_addUserFragment_to_usersListFragment)
        }
    }

    private fun tirarCaractersCPF(cpf: String): String {
        val pattern = Regex("[^0-9]")
        return cpf.replace(pattern, "")
    }

    private fun bind(user: UserModel) {

        binding.edtMatricula.setText(user.matricula)
        binding.edtCpf.setText(user.cpf)
        binding.edtEmail.setText(user.email)

        binding.btnSalvar.setText(R.string.btn_update)
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
            showToast("Usuário atualizado com sucesso")

            val action = UserFragmentDirections.actionAddUserFragmentToUsersListFragment()
            this.findNavController().navigate(action)
        } else {
            showToast("Preencha todos os campos")
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.edtMatricula.text.toString(),
            binding.edtCpf.text.toString(),
            binding.edtEmail.text.toString()
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (navigationArgs.userId > 0)
            inflater.inflate(R.menu.menu_delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteUser -> {
                callConfirmationDelete()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun callConfirmationDelete() {
        showConfirmationDialog()
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Atenção")
            .setMessage("Esta ação irá excluir o usuário. Deseja continuar?")
            .setCancelable(false)
            .setNegativeButton("Não") { _, _ -> }
            .setPositiveButton("Sim") { _, _ -> deleteUser() }.show()
    }

    private fun deleteUser() {
        if (isEntryValid()) {
            viewModel.deleteUser(
                this.navigationArgs.userId,
                this.binding.edtMatricula.text.toString(),
                this.binding.edtCpf.text.toString(),
                this.binding.edtEmail.text.toString()
            )
            val action = UserFragmentDirections.actionAddUserFragmentToUsersListFragment()
            this.findNavController().navigate(action)
            showToast("Usuário excluído com sucesso!")
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(
            requireContext(),
            text,
            Toast.LENGTH_SHORT
        )
            .show()
    }
}