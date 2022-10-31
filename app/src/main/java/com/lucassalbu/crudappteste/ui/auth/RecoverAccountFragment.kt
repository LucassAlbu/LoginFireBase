package com.lucassalbu.crudappteste.ui.auth

import  android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lucassalbu.crudappteste.databinding.FragmentRecoverAcountBinding


class RecoverAcountFragment : Fragment() {

    private var _binding: FragmentRecoverAcountBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecoverAcountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        initClicks()
    }

    private fun initClicks() {
        binding.btnSend.setOnClickListener { validadeData() }
    }

    private fun validadeData() {
        val email = binding.edtEmail.text.toString().trim()

        if (email.isNotEmpty()) {
            binding.progressBrar.isVisible = true

            recoverAccountUser(email)

        } else {
            Toast.makeText(requireContext(), "Informe seu E-mail", Toast.LENGTH_SHORT).show()
        }
    }

    private fun recoverAccountUser(email: String) {

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Link enviado para Seu E-mail",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(requireContext(), "deu ruim", Toast.LENGTH_SHORT).show()
                }
                binding.progressBrar.isVisible = false
            }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}

