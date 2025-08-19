
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.clientesappds2ait.model.Cliente
import br.senai.sp.jandira.clientesappds2ait.service.ClienteService
import br.senai.sp.jandira.clientesappds2ait.service.Conexao
import br.senai.sp.jandira.clientesappds2ait.ui.theme.ClientesAPPDS2AITTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await


@Composable
fun Conteudo(paddingValues: PaddingValues){
    val clienteApi = Conexao().getClienteService()
    var clientes by remember { mutableStateOf(listOf<Cliente>()) }


    LaunchedEffect(Dispatchers.IO){
        clientes = clienteApi.listarTodos().await()
    }
    Column (
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),

    ){
        Row (
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Person"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text ="Lista de clientes" )
        }
        LazyColumn (
            contentPadding = PaddingValues(bottom = 80.dp)
        ){
            items(clientes){ cliente ->
                CardCliente(cliente, clienteApi, controleNavegacao = null)
            }

        }
    }

}

@Composable
private fun CardCliente(
    cliente: Cliente,
    clienteApi: ClienteService,
    controleNavegacao: NavHostController?

    ) {
    var mostrarMensagemDelete by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = cliente.nome)
                Text(text = cliente.email)
            }
            IconButton(
                onClick = {
                    mostrarMensagemDelete = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
        if (mostrarMensagemDelete) {
            AlertDialog(
                onDismissRequest = {
                    mostrarMensagemDelete = false

                },
                title = {
                    Text(text = "Confirmar")
                },
                text = {
                    Text(text = "Deseja apagar este cliente?!")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val cliente = Cliente()
                            GlobalScope.launch (Dispatchers.IO) {
                                val delete = clienteApi.excluir(id = "").await()
                            }

                            controleNavegacao!!.navigate(Conteudo())
                        }
                    ) {
                        Text(
                            text = "Confirmar"
                        )
                    }

                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            mostrarMensagemDelete = false
                        }
                    ) {
                        Text(text = "Cancelar")
                    }
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "CUIDADO"
                    )
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ConteudoPreview(){
    ClientesAPPDS2AITTheme {
        Conteudo(PaddingValues(16.dp))
    }
}

