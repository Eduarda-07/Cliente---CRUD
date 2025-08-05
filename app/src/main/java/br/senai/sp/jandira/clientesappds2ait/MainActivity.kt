package br.senai.sp.jandira.clientesappds2ait

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.clientesappds2ait.model.Cliente
import br.senai.sp.jandira.clientesappds2ait.screens.cliente.ListaDeClientes
import br.senai.sp.jandira.clientesappds2ait.ui.theme.ClientesAPPDS2AITTheme
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClientesAPPDS2AITTheme {
                ListaDeClientes()

//                val cliente = Cliente(
//                    id = null,
//                    nome = "Cadastro de Eduarda",
//                    email = "cadastroEduarda@gmail.com"
//                )

//                val conexao = Conexao().getClienteService()
//                LaunchedEffect(Dispatchers.IO){
//                    conexao.cadastrarCliente(cliente).await
//                }
            }
        }
    }
}