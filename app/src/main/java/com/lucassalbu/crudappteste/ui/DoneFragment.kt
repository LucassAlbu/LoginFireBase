package com.lucassalbu.crudappteste.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucassalbu.crudappteste.R
import com.lucassalbu.crudappteste.databinding.FragmentDoneBinding
import com.lucassalbu.crudappteste.databinding.FragmentHomeBinding
import com.lucassalbu.crudappteste.databinding.FragmentTodoBinding


class DoneFragment : Fragment() {

    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}