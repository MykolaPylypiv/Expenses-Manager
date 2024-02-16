package com.example.expensesmanager.ui.screens.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.navigation.NavigationTree

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(navController: NavController, viewModel: AccountViewModel, language: Language) {
    val dropdownMenu = remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = language.account, fontSize = 24.sp, color = MaterialTheme.colorScheme.primary
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.navigate(NavigationTree.Start.screenRoute) }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Back")
            }
        }, actions = {
            IconButton(onClick = { dropdownMenu.value = true }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Back")
            }
        })
    }) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Account",
                    modifier = Modifier.size(180.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Column {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = language.nickname,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.tertiary
                    )

                    Text(
                        text = "Микола Пилипів",
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        modifier = Modifier.padding(vertical = 6.dp),
                        fontSize = 22.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                DropdownMenu(
                    expanded = dropdownMenu.value,
                    onDismissRequest = { dropdownMenu.value = false },
                    offset = DpOffset(x = 280.dp, y = (-200).dp)
                ) {
                    DropdownMenuItem(text = {
                        Text(
                            "Edit"
                        )
                    }, onClick = {
                        dropdownMenu.value = false
                    })
                }
            }

            Text(
                text = language.description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.tertiary
            )

            Text(
                text = "Вчусь в Львівській плітехніці. Люблю шось там і шось там...",
                color = MaterialTheme.colorScheme.primary,
                maxLines = 5,
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp),
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.weight(1F))

            OutlinedButton(
                onClick = { navController.navigate(NavigationTree.Login.screenRoute) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 72.dp)
            ) {
                Text(text = language.exit, color = MaterialTheme.colorScheme.primary)

            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}