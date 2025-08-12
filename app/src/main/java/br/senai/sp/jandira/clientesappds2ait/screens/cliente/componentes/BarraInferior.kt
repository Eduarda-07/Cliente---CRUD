package br.senai.sp.jandira.clientesappds2ait.screens.cliente.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import br.senai.sp.jandira.clientesappds2ait.ui.theme.ClientesAPPDS2AITTheme

@Composable
// interrogação para rodar o preview sem colocar a navegação, quer dizer que pode ser nulo
fun BarraInferior(controleNavegacao: NavHostController?) {
    NavigationBar(
        containerColor = MaterialTheme
            .colorScheme.primaryContainer
    ) {
        NavigationBarItem(
            onClick = {
                // usar !! quando usar a interrogação
                controleNavegacao!!.navigate("conteudo")
            },
            selected = false,
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },
            label = {
                Text(text = "Home")
            }
        )
        NavigationBarItem(
            onClick = {},
            selected = false,
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite"
                )
            },
            label = {
                Text(text = "Favorite")
            }
        )
        NavigationBarItem(
            onClick = {
                // usar !! quando usar a interrogação
                controleNavegacao!!.navigate("cadastro")
            },
            selected = false,
            icon = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Novo"
                )
            },
            label = {
                Text(text = "Novo cliente")
            }
        )
    }

}

@Preview()
@Composable
private fun BarraInferiorPreview(){
    ClientesAPPDS2AITTheme {
        BarraInferior(null)
    }
}