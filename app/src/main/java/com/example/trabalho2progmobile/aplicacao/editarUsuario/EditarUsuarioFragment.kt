package com.example.trabalho2progmobile.aplicacao.editarUsuario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.trabalho2progmobile.R
import com.example.trabalho2progmobile.bancoDeDados.usuario.Usuario
import com.example.trabalho2progmobile.utils.converters.Converters
import com.example.trabalho2progmobile.utils.mvvm.abstracts.dadosDoUsuario.AbstractDadosDoUsuarioFragment
import com.example.trabalho2progmobile.utils.retorno.Resultado
import kotlinx.android.synthetic.main.bloco_dados_usuario.*
import kotlinx.android.synthetic.main.editar_usuario_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditarUsuarioFragment: AbstractDadosDoUsuarioFragment() {
    private val _viewModel: EditarUsuarioViewModel by viewModel()
    private val args: EditarUsuarioFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.editar_usuario_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        _viewModel.buscarUsuario(args.usuario)
        setupUi()
        subscribeUi()
    }

    private fun preencherDadosDoUsuario(usuario: Usuario){
        imgViewFoto.setImageBitmap(Converters().getRoundedCornerBitmap(usuario.foto, 360))
        input_nome_completo.setText(usuario.nome)
        input_email.setText(usuario.email)
    }

    private fun setupUi(){
        btn_salvar.setOnClickListener {
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
        _viewModel.usuario.observe(viewLifecycleOwner, ::preencherDadosDoUsuario)
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
                _viewModel.atualizarUsuarioNoBanco(
                    Usuario(
                        args.usuario.usuarioId,
                        input_nome_completo.text.toString(),
                        input_email.text.toString(),
                        _viewModel.verificarSeSenhaFoiInserida(
                            input_password.text.toString(),
                            args.usuario.senha
                        ),
                        imgViewFoto.drawable.toBitmap()
                    )
                )
        }
        _viewModel.usuarioAtualizado.observe(viewLifecycleOwner, ::processarResultado)
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
                exibirMensagem(getString(R.string.usuario_atualizado))
                findNavController().popBackStack()
            }
            else{
                opcoesDialog(R.string.dialog_remover, R.string.salvando_usuario)
                exibirMensagem(getString(R.string.erro_inserir_usuario))
            }
        }
    }
}