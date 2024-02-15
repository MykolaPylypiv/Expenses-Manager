package com.example.expensesmanager.ui.screens.registration

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.navigation.NavigationTree
import com.example.expensesmanager.ui.screens.registration.components.FirstPage
import com.example.expensesmanager.ui.screens.registration.components.SelectLanguage
import com.example.expensesmanager.ui.screens.registration.components.SelectTheme
import com.example.expensesmanager.ui.screens.registration.components.ThirdPage

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavController, viewModel: RegistrationViewModel, language: Language
) {
    val color = Color(0xffEA8D8D)
    val color2 = Color(0xffA890FE)

    val settings by viewModel.settings.collectAsState(initial = Settings())

    var page by remember { mutableIntStateOf(0) }

    val context = LocalContext.current

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "${language.registration}   ${page + 1}/3",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.navigate(NavigationTree.Login.screenRoute) }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Back")
            }
        })
    }) { padding ->

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
                .padding(padding)
                .animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (page) {
                0 -> FirstPage(language = language, viewModel = viewModel)

                1 -> {
                    SelectLanguage(language = language, viewModel = viewModel, settings = settings)

                    SelectTheme(language = language, viewModel = viewModel, settings = settings)
                }

                2 -> ThirdPage(language = language, viewModel = viewModel, settings = settings)
            }

            Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 72.dp)) {
                if (page != 0) OutlinedButton(
                    onClick = { page -= 1 },
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
                    modifier = Modifier
                        .weight(1F)
                        .padding(horizontal = 2.dp)
                ) {
                    Text(
                        text = language.back,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 22.sp
                    )
                }

                OutlinedButton(
                    onClick = {
                        if (page == 2 && viewModel.isValidateThirdPage(
                                language = language,
                                context = context,
                                currency = viewModel.selectCurrency
                            )
                        ) {
                            viewModel.createDefaultCategory(
                                language = language, isCreateList = viewModel.isCreateList
                            )
                            navController.navigate(NavigationTree.Start.screenRoute)
                        } else if (page != 2 && viewModel.isValidateFirstPage(
                                language = language,
                                login = viewModel.login,
                                password = viewModel.password,
                                repeatPassword = viewModel.repeatPassword,
                                context = context
                            )
                        ) {
                            page += 1
                        }
                    },
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
                    modifier = Modifier
                        .weight(1F)
                        .padding(horizontal = 2.dp)
                ) {
                    Text(
                        text = if (page != 2) language.next else language.complete,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 22.sp
                    )
                }
            }
        }
    }
}