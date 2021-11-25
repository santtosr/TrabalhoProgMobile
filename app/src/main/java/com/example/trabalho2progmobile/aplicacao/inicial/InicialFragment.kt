package com.example.trabalho2progmobile.aplicacao.inicial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.trabalho2progmobile.R
import kotlinx.android.synthetic.main.inicial_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class InicialFragment : Fragment() {

    private val _viewModel: InicialViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.inicial_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUi()
    }

    private fun setupUi(){
        btnLogin.setOnClickListener {
            val navController = findNavController()
            val action = InicialFragmentDirections.actionInicialFragmentToLoginFragment()
            navController.navigate(action)
        }

        btnRegistrar.setOnClickListener {
            val navController = findNavController()
            val action = InicialFragmentDirections.actionInicialFragmentToCadastrarFragment()
            navController.navigate(action)
        }
    }

}