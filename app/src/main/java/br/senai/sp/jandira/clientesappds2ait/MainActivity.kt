package br.senai.sp.jandira.clientesappds2ait

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge


import androidx.compose.runtime.LaunchedEffect

import br.senai.sp.jandira.clientesappds2ait.model.Cliente
import br.senai.sp.jandira.clientesappds2ait.screens.cliente.ListaDeClientes

import kotlinx.coroutines.Dispatchers

import br.senai.sp.jandira.clientesappds2ait.service.Conexao
import br.senai.sp.jandira.clientesappds2ait.ui.theme.ClientesAPPDS2AITTheme

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClientesAPPDS2AITTheme {
                ListaDeClientes()

            }
        }
    }
}