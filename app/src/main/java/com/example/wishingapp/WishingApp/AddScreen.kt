package com.example.wishingapp.WishingApp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun AddScreen(
    id:Long,
    viewModel: WishViewModel,
    navController: NavController
) {
//    val snackMessage = remember {
//        mutableStateOf("")
//    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if (id != 0L) {
        val wish = viewModel.getWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel._wish = wish.value.title
        viewModel._wishDes = wish.value.description
    }
    else {
        viewModel._wish = ""
        viewModel._wishDes = ""
    }
    Scaffold(

        topBar = { AppBarView(
            title = if (id != 0L) "Update Wish" else "Add Wish",
            onBackNavClicked = navController::navigateUp)
        },
        scaffoldState = scaffoldState
    ){
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Your Wish",
                value = viewModel._wish,
                onValueChanged = {
                    viewModel.onWishChanged(it)
                })
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Your Description",
                value = viewModel._wishDes,
                onValueChanged = {
                    viewModel.onDescriptionChanged(it)
                })
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if(viewModel._wish.isNotEmpty() && viewModel._wishDes.isNotEmpty()) {
                    if(id!=0L) {
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel._wish.trim(),
                                description = viewModel._wishDes.trim())
                        )

                    }
                    else{
                        viewModel.addWish(
                            Wish(title = viewModel._wish.trim(),
                                description = viewModel._wishDes.trim())
                        )
//                        snackMessage.value = "Created new Wish"
                    }
                }
                else {
//                    snackMessage.value =  "Enter fields to create a wish"
                }
                scope.launch {
//                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp() }
            }){
                Text(text = if(id != 0L) "Update Wish" else "Add Wish",
                    fontSize = 18.sp)
            }
        }
    }

}
@Composable
fun WishTextField(
    label:String,
    value:String,
    onValueChanged:(String)->Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = { Text(text = label ,color = Color.Black)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            cursorColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black
        )
    )
}


@Preview(showBackground = true)
@Composable
fun preview(){
    WishTextField(label = "", value = "Hai Yen", onValueChanged = {} )
}