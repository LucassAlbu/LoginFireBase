package com.lucassalbu.crudappteste.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lucassalbu.crudappteste.R
import com.lucassalbu.crudappteste.databinding.FragmentRegisterBinding
import com.lucassalbu.crudappteste.helper.FireBaseHelper

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        initiClicks()
    }

    private fun initiClicks() {
        binding.btnRegister.setOnClickListener { validadeData() }
    }


    private fun validadeData() {
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtSenha.text.toString().trim()

        if (email.isNotEmpty()) {

            if (password.isNotEmpty()) {

                binding.progressBrar.isVisible = true

                registerUser(email, password)

            } else {
                Toast.makeText(requireContext(), "Informe sua senha", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Informe seu E-mail", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerUser(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_global_homeFragment2)
                } else {
                    Toast.makeText(
                        requireContext(),
                        FireBaseHelper.validError(task.exception?.message ?: ""),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.progressBrar.isVisible = false

                }
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}