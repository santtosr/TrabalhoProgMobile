package com.example.trabalho2progmobile.aplicacao.cadastro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.trabalho2progmobile.R
import com.example.trabalho2progmobile.bancoDeDados.usuario.Usuario
import com.example.trabalho2progmobile.utils.mvvm.abstracts.dadosDoUsuario.AbstractDadosDoUsuarioFragment
import com.example.trabalho2progmobile.utils.retorno.Resultado
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bloco_dados_usuario.*
import kotlinx.android.synthetic.main.cadastrar_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastrarFragment: AbstractDadosDoUsuarioFragment() {
    private val _viewModel: CadastrarViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cadastrar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().toolbar.setTitle(R.string.cadastrar)
        setupUi()
        subscribeUi()
    }

    private fun setupUi(){
        btn_cadastrar.setOnClickListener {
            _viewModel.verificarCampos(
                input_nome_completo.text.toString(),
                input_email.text.toString(),
                input_password.text.toString(),
                input_confirm_password.text.toString()
            )
        }
        imgViewFoto.setOnClickListener {
            capturePhoto()
        }
    }

    private fun subscribeUi(){
        _viewModel.erroNome.observe(viewLifecycleOwner) {
            mostrarErroDoTextInputLayout(textInputLayoutNome, it)
        }
        _viewModel.erroEmail.observe(viewLifecycleOwner) {
            mostrarErroDoTextInputLayout(textInputLayoutEmailDados, it)
        }
        _viewModel.erroSenha.observe(viewLifecycleOwner) {
            mostrarErroDoTextInputLayout(textInputLayoutSenhaDados, it)
        }
        _viewModel.erroConfirmarSenha.observe(viewLifecycleOwner) {
            mostrarErroDoTextInputLayout(textInputLayoutConfirmarSenhaDados, it)
        }
        _viewModel.dadosCorretos.observe(viewLifecycleOwner) {
            if(it)
            _viewModel.inserirUsuarioNoBanco(
                Usuario(
                    0,
                    input_nome_completo.text.toString(),
                    input_email.text.toString(),
                    input_password.text.toString(),
                    verificarSeEFotoOuDrawable()
                )
            )
        }
        _viewModel.usuarioInserido.observe(viewLifecycleOwner, ::processarResultado)
    }

    private fun processarResultado(
        resultado: Resultado
    ){
        if(resultado.resultadoStatus == Resultado.ResultadoStatus.CARREGANDO){
            opcoesDialog(R.string.dialog_mostrar, R.string.salvando_usuario)
        }
        else{
            if(resultado.correto){
                opcoesDialog(R.string.dialog_remover, R.string.salvando_usuario)
                exibirMensagem(getString(R.string.usuario_inserido))
                findNavController().popBackStack()
            }
            else{
                opcoesDialog(R.string.dialog_remover, R.string.salvando_usuario)
                exibirMensagem(getString(R.string.erro_inserir_usuario))
            }
        }
    }
}