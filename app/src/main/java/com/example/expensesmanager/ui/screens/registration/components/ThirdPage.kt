package com.example.expensesmanager.ui.screens.registration.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.domain.model.Settings
import com.example.expensesmanager.ui.screens.registration.RegistrationViewModel

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdPage(viewModel: RegistrationViewModel, language: Language, settings: Settings) {
    var budget by remember { mutableStateOf("0") }

    var createCategoriesDialog by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = budget,
        onValueChange = { value ->
            budget = viewModel.sumValue(value)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 48.dp, bottom = 24.dp),
        label = { Text(text = language.budget) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = Color.LightGray,
            cursorColor = Color.LightGray
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        singleLine = true
    )

    Currency(
        viewModel = viewModel, settings = settings, language = language
    )

    Row(modifier = Modifier
        .height(104.dp)
        .padding(24.dp)
        .clip(RoundedCornerShape(10))
        .background(MaterialTheme.colorScheme.background)
        .fillMaxWidth()
        .clickable { createCategoriesDialog = !createCategoriesDialog }
        .border(
            1.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(10)
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        Text(
            text = language.createCategories,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 15.dp)
        )

        Spacer(modifier = Modifier.weight(1F))

        if (createCategoriesDialog) Checkbox(
            checked = viewModel.isCreateCategory,
            onCheckedChange = { value ->
                viewModel.isCreateCategory = value
                viewModel.isCreateList.replaceAll { value }
            },
        )

        Icon(
            imageVector = if (!createCategoriesDialog) Icons.Filled.KeyboardArrowLeft else Icons.Filled.KeyboardArrowDown,
            contentDescription = "Close",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(end = 8.dp)
        )
    }

    AnimatedVisibility(
        visible = createCategoriesDialog,
        enter = scaleIn() + expandVertically() + fadeIn(),
        exit = scaleOut() + shrinkVertically() + fadeOut(),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .background(MaterialTheme.colorScheme.background)
                .border(1.dp, MaterialTheme.colorScheme.tertiary)
        ) {
            item {
                Text(
                    text = language.costs,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    textAlign = TextAlign.Center
                )
            }

            items(viewModel.defaultCategories(language).dropLast(1)) { category ->
                DefaultCategoryItem(
                    category = category,
                    index = viewModel.defaultCategories(language).indexOf(category),
                    viewModel = viewModel
                )
            }

            item {
                Text(
                    text = language.income,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    textAlign = TextAlign.Center
                )
            }

            item {
                DefaultCategoryItem(
                    category = viewModel.defaultCategories(language).last(),
                    index = 8,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun DefaultCategoryItem(category: Category, index: Int, viewModel: RegistrationViewModel) {
    Row(Modifier.padding(vertical = 24.dp), verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = category.name,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.weight(1F))

        Checkbox(
            checked = viewModel.isCreateList[index],
            onCheckedChange = {
                viewModel.isCreateList[index] = it
            },
        )

        Spacer(modifier = Modifier.width(15.dp))
    }

}