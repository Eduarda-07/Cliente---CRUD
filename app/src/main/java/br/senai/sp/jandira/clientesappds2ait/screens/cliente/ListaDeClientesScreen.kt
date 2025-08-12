package br.senai.sp.jandira.clientesappds2ait.screens.cliente

import Conteudo
import android.content.res.Configuration
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.clientesappds2ait.screens.cliente.componentes.BarraDeTitulo
import br.senai.sp.jandira.clientesappds2ait.screens.cliente.componentes.BarraInferior
import br.senai.sp.jandira.clientesappds2ait.screens.cliente.componentes.BotaoFlutuante
import br.senai.sp.jandira.clientesappds2ait.ui.theme.ClientesAPPDS2AITTheme

@Composable
fun ListaDeClientes(modifier: Modifier = Modifier) {

    //memorizar as telas que já passaram
    var controleNavegacao = rememberNavController()

    Scaffold (
        topBar = {
            BarraDeTitulo()
        },
        bottomBar = {
            BarraInferior(controleNavegacao)
        },
        floatingActionButton = {
            BotaoFlutuante(controleNavegacao)
        },
        content = { padding ->
            NavHost(
                navController = controleNavegacao,
                startDestination = "conteudo"
            ){
                composable(route = "conteudo"){Conteudo(padding)}
                composable(route = "cadastro"){ ClienteForm(padding, controleNavegacao) }

            }
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ListaDeClientesPreview(){
    ClientesAPPDS2AITTheme {
        ListaDeClientes()
    }
}