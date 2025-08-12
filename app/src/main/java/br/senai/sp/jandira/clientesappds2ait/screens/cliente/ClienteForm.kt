package br.senai.sp.jandira.clientesappds2ait.screens.cliente

import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.clientesappds2ait.model.Cliente
import br.senai.sp.jandira.clientesappds2ait.service.Conexao
import br.senai.sp.jandira.clientesappds2ait.ui.theme.ClientesAPPDS2AITTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await

@Composable
fun ClienteForm(padding: PaddingValues, controleNavegacao: NavHostController?) {
    var nomeCliente by remember{ mutableStateOf("") }
    var emailCliente by remember{ mutableStateOf("") }
    var isNameError by remember{ mutableStateOf(false) }
    var isEmailError by remember{ mutableStateOf(false) }

    fun validar(): Boolean{
        isNameError = nomeCliente.length < 3
        isEmailError = !Patterns.EMAIL_ADDRESS.matcher(emailCliente).matches()
        return !isNameError && !isEmailError
    }

    //criar uma instancia da conxão com a API
    val clienteAPI = Conexao().getClienteService()
    
    Surface (
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
        ){
            Row (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person"
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Novo cliente",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            OutlinedTextField(
                value = nomeCliente,
                onValueChange = {nomeCliente = it},
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                label = {
                    Text(text = "Nome do cliente")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                isError = isEmailError,
                supportingText = {
                    if (isNameError){
                        Text(text = "Nome é obrigatório")
                    }
                },
                trailingIcon = {
                    if (isNameError){
                        Icon(imageVector = Icons.Default.Info, contentDescription = "")
                    }
                }
                
            )
            OutlinedTextField(
                value = emailCliente,
                onValueChange = {emailCliente = it},
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                label = {
                    Text(text = "E-mail do cliente")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                    
                ),
                isError = isEmailError,
                supportingText = {
                    if (isEmailError){
                        Text(text = "Email é obrigatório")
                    }
                },
                trailingIcon = {
                    if (isEmailError){
                        Icon(imageVector = Icons.Default.Info, contentDescription = "")
                    }
                }
            )

            Button(
                onClick = {
                   if (validar()){
                       val cliente = Cliente(
                           id = null,
                           nome = nomeCliente,
                           email = emailCliente
                       )
                       GlobalScope.launch (Dispatchers.IO){
                           val clienteNovo = clienteAPI.cadastrarCliente(cliente).await()
                           println("******************${clienteNovo}**********************")

                       }
                       controleNavegacao!!.navigate("conteudo")
                   }else{
                       println("************* DADOS INVALIDOS ***************")
                   }
                },
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            ) {
                Text(text = "Gravar cliente")
            }
        }
    }

}



@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ClienteFormPreview(){
    ClientesAPPDS2AITTheme {
        ClienteForm(PaddingValues(0.dp), null)
    }
}