package com.example.expensesmanager.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensesmanager.R
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.navigation.NavigationTree

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel, language: Language) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val color = Color(0xffEA8D8D)
    val color2 = Color(0xffA890FE)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.mipmap.expense_icon), contentDescription = "Background"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            color.copy(0.3f), color2.copy(0.3f)
                        )
                    )
                )
        ) {
            OutlinedTextField(
                value = login,
                onValueChange = {
                    login = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 48.dp, bottom = 24.dp),
                label = { Text(text = language.nickname) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.LightGray,
                    cursorColor = Color.LightGray
                ),
                singleLine = true
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    text = language.forgotPassword,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(end = 32.dp).clickable { navController.navigate(NavigationTree.ForgotPassword.screenRoute) },
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontStyle = FontStyle.Italic
                )
            }

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp, top = 36.dp, start = 24.dp, end = 24.dp),
                label = { Text(text = language.password) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.LightGray,
                    cursorColor = Color.LightGray
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true
            )

            Spacer(modifier = Modifier.weight(1.2F))

            OutlinedButton(
                onClick = {
                    navController.navigate(NavigationTree.Start.screenRoute)
                },
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = language.login,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 22.sp
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    text = language.signIn,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(end = 40.dp).clickable { navController.navigate(NavigationTree.Registration.screenRoute) },
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontStyle = FontStyle.Italic
                )
            }

            Spacer(modifier = Modifier.weight(0.6F))
        }
    }
}