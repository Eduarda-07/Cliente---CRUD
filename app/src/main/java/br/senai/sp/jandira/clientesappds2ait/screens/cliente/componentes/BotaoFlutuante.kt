package br.senai.sp.jandira.clientesappds2ait.screens.cliente.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import br.senai.sp.jandira.clientesappds2ait.ui.theme.ClientesAPPDS2AITTheme

@Composable
fun BotaoFlutuante(controleNavegacao: NavHostController?) {
    FloatingActionButton(
        onClick = {
            controleNavegacao!!.navigate("cadastro")
        }
    )  {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Botão adicionar"
        )
    }

}

@Preview()
@Composable
private fun BotaoFlutuantePreview(){
    ClientesAPPDS2AITTheme {
        BotaoFlutuante(null)
    }
}