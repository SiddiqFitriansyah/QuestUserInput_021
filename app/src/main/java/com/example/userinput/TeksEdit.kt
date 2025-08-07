package com.example.userinput

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.serialization.internal.throwMissingFieldException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloContent() {
    var coba by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    var passw by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Contoh TextField tanpa status",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        TextField(
            value = coba,
            onValueChange = {coba = it},
            label = {Text("Prodi")}
        )
        OutlinedTextField(
            value = nama,
            onValueChange = {nama = it},
            label = {Text("Nama")}
        )
        TextField(
            value = passw,
            onValueChange = {passw = it},
            label = {Text("Enter Password")},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )
    }
}

@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) }
    Column (verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Count: $count")
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {count++},
        ) {
            Text(text = "Increment Counter")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RememberSaveableExample() {
    var savedText by rememberSaveable { mutableStateOf("Initial Text") }
    TextField(
        value = savedText,
        onValueChange = { savedText = it }
    )
    Text(text = "Text Entered: $savedText")
}

@Composable
fun ContohRadioButton() {
    val jk = listOf("Laki-Laki", "Perempuan", "Lainnya")
    var pilihan by remember { mutableStateOf(jk[0]) }

    Column {
        Text("Pilih Jenis Kelamin:", style = MaterialTheme.typography.titleMedium)

        jk.forEach { item ->
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                RadioButton(
                    selected = (item == pilihan),
                    onClick = { pilihan = item }
                )
                Text(
                    text = item,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Text(
            text = "Terpilih: $pilihan",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormDataDiri() {
    //variabel-variabel untuk mengingat masukan dari keyboard
    var textNama by remember { mutableStateOf("") }
    var textAlamat by remember { mutableStateOf("") }
    var textJK by remember { mutableStateOf("") }

    //variabel-variabel untuk menyimpan data yang diperoleh dari komponen UI
    var nama by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var jenis by remember { mutableStateOf("") }

    val gender:List<String> = listOf("Laki-Laki","Perempuan")

    Column(modifier = Modifier.padding(top = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = textNama,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.width(250.dp),
            label = {Text(text = "Nama Lengkap") },
            onValueChange = {textNama = it}
        )
    }
    Row {
        gender.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selected = textJK == item,
                    onClick = { textJK = item }
                ), verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = textJK == item,
                    onClick = {
                        textJK = item
                    })
                Text(text= item)
            }
        }
    }
    OutlinedTextField(
        value = textAlamat,
        singleLine = true,
        modifier = Modifier.width(250.dp),
        label = {Text(text = "Alamat Lengkap")},
        onValueChange = {
            textAlamat = it
        }
    )

    HorizontalDivider(
        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium), top = dimensionResource(
            id = R.dimen.padding_medium
        ))
    )
    Button(
        modifier = Modifier.fillMaxWidth(1f),
        //the button is enabled when the user makes a selection
        enabled = textAlamat.isNotEmpty(),
        onClick = {
            nama=textNama
            jenis=textJK
            alamat=textAlamat
        }
    ){
        Text(text = stringResource(id =R.string.submit))
    }
    HorizontalDivider(
        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_medium),
            top = dimensionResource(id = R.dimen.padding_medium)),
        thickness = dimensionResource(id = R.dimen.divider_tipis),
        color = Color.Red
    )

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black),
        modifier = Modifier.height(height = 100.dp).width(width = 300.dp)
    ) {
        Column (modifier = Modifier.padding(horizontal = 5.dp, vertical = 15.dp),){
            Text(text = "Nama   :"+nama, color = Color.White)
            Text(text = "Gender :"+jenis, color = Color.White)
            Text(text = "Alamat   :"+alamat, color = Color.White)
        }
    }
}