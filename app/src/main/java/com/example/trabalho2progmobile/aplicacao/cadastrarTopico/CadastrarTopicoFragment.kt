package com.example.trabalho2progmobile.aplicacao.cadastrarTopico

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.trabalho2progmobile.R
import com.example.trabalho2progmobile.bancoDeDados.topico.Topico
import com.example.trabalho2progmobile.bancoDeDados.usuario.Usuario
import com.example.trabalho2progmobile.utils.dataHora.DataHora.Companion.gerarDataHora
import com.example.trabalho2progmobile.utils.mvvm.abstracts.base.BaseFragment
import com.example.trabalho2progmobile.utils.retorno.Resultado
import kotlinx.android.synthetic.main.cadastrar_fragment.*
import kotlinx.android.synthetic.main.cadastrar_topico_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastrarTopicoFragment : BaseFragment() {

    private val _viewModel: CadastrarTopicoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cadastrar_topico_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUi()
        subscribeUi()
    }
    private fun setupUi(){
        btn_cadastrar_Topico.setOnClickListener {
            _viewModel.verificarCampos(
                input_nome_topico.text.toString(),
                input_descricao.text.toString(),
                )
        }
    }

    private fun subscribeUi(){

        _viewModel.erroNome.observe(viewLifecycleOwner) {
            mostrarErroDoMaskEditText(input_nome_topico, it)
        }
        _viewModel.erroDescricao.observe(viewLifecycleOwner) {
            mostrarErroDoMaskEditText(input_descricao, it)
        }

        _viewModel.dadosCorretos.observe(viewLifecycleOwner) {
            if(it)
                _viewModel.inserirTopicoNoBanco(
                    Topico(
                        0,
                        nome=input_nome_topico.text.toString(),
                        descricao = input_descricao.text.toString(),
                        hora = gerarDataHora()
                    )
                )
        }
        _viewModel.topicoInserido.observe(viewLifecycleOwner, ::processarResultado)

    }

    private fun processarResultado(
        resultado: Resultado
    ){
        if(resultado.resultadoStatus == Resultado.ResultadoStatus.CARREGANDO){
            opcoesDialog(R.string.dialog_mostrar, R.string.salvando_topico)
        }
        else{
            if(resultado.correto){
                opcoesDialog(R.string.dialog_remover, R.string.salvando_topico)
                exibirMensagem(getString(R.string.topico_inserido))
                findNavController().popBackStack()
            }
            else{
                opcoesDialog(R.string.dialog_remover, R.string.salvando_topico)
                exibirMensagem(getString(R.string.erro_inserir_topico))
            }
        }
    }
}