package br.com.eduardomaxwell.testeappkotlin.ui.home.adduser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.eduardomaxwell.testeappkotlin.R
import br.com.eduardomaxwell.testeappkotlin.data.UserModel
import br.com.eduardomaxwell.testeappkotlin.databinding.FragmentAddUserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddUserFragment : Fragment() {
    private val viewModel: AddUserViewModel by viewModel()
    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mat = binding.edtMatricula.text
        val cpf = binding.edtCpf.text
        val email = binding.edtEmail.text


        binding.btnSalvar.setOnClickListener {
            if (isEntryValid()) {
                viewModel.addUser(mat.toString(), cpf.toString(), email.toString())
                Toast.makeText(requireContext(), "Usuário salvo com sucesso", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_addUserFragment_to_usersListFragment)
            }else{
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT)
                    .show()
            }
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