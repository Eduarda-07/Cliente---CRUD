package br.senai.sp.jandira.clientesappds2ait.screens.cliente.componentes

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.clientesappds2ait.R
import br.senai.sp.jandira.clientesappds2ait.screens.cliente.ListaDeClientes
import br.senai.sp.jandira.clientesappds2ait.ui.theme.ClientesAPPDS2AITTheme

import br.senai.sp.jandira.clientesappds2ait.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraDeTitulo(modifier: Modifier = Modifier){
    TopAppBar(
        title = {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Column {
                    Text(
                        text = "Sofia Carson",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "sofiaCarson@gmail.com",
                        style = MaterialTheme.typography.titleSmall)
                }
                Card(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(4.dp),
                    shape = CircleShape
                ){
                    Image(
                        painter = painterResource(R.drawable.sofia_carson),
                        contentDescription = "Foto de perfil",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        },
        colors = TopAppBarDefaults
            .topAppBarColors(
                containerColor = MaterialTheme
                    .colorScheme.primaryContainer
            )
    )
}

@Preview()
@Composable
private fun BarraDeTituloPreview(){
    ClientesAPPDS2AITTheme {
        BarraDeTitulo()
    }
}