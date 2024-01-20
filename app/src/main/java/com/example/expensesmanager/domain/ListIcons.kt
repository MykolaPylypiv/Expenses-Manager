package com.example.expensesmanager.domain

import androidx.compose.ui.graphics.Color
import com.example.expensesmanager.R
import com.example.expensesmanager.app.Language

sealed interface ListIcons {

    val list: List<Int>
    
    val name: String

    val color: Color

    class Food(language: Language, iconColor: Color) : ListIcons {

        override val list: List<Int> = listOf(
            R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4
        )
        
        override val name = language.food

        override val color = iconColor
    }

    class Cafe(language: Language, iconColor: Color) : ListIcons {

        override val list: List<Int> = listOf(
            R.drawable.cafe1, R.drawable.cafe2, R.drawable.cafe3, R.drawable.cafe4
        )

        override val name = language.cafe

        override val color = iconColor
    }

    class Transport(language: Language, iconColor: Color) : ListIcons {

        override val list: List<Int> = listOf(
            R.drawable.transport1,
            R.drawable.transport2,
            R.drawable.transport3,
            R.drawable.transport4
        )

        override val name = language.transport

        override val color = iconColor
    }

    class Health(language: Language, iconColor: Color) : ListIcons {

        override val list: List<Int> = listOf(
            R.drawable.health1, R.drawable.health2, R.drawable.health3, R.drawable.health4
        )

        override val name = language.health

        override val color = iconColor
    }

    class Pets(language: Language, iconColor: Color) : ListIcons {

        override val list: List<Int> = listOf(
            R.drawable.pets1, R.drawable.pets2, R.drawable.pets3, R.drawable.pets4
        )

        override val name = language.pets

        override val color = iconColor
    }

    class Family(language: Language, iconColor: Color): ListIcons {

        override val list: List<Int> = listOf(
            R.drawable.family1, R.drawable.family2, R.drawable.family3, R.drawable.family4
        )

        override val name = language.family

        override val color = iconColor
    }

    class Clothes(language: Language, iconColor: Color) : ListIcons {

        override val list: List<Int> = listOf(
            R.drawable.clothes1, R.drawable.clothes2, R.drawable.clothes3, R.drawable.clothes4
        )

        override val name = language.clothes

        override val color = iconColor
    }

    class Entertainment(language: Language, iconColor: Color) : ListIcons {

        override val list: List<Int> = listOf(
            R.drawable.entertainment1,
            R.drawable.entertainment2,
            R.drawable.entertainment3,
            R.drawable.entertainment4
        )

        override val name = language.entertainment

        override val color = iconColor
    }

    class Salary(language: Language, iconColor: Color) : ListIcons {

        override val list: List<Int> = listOf(
            R.drawable.salary1, R.drawable.salary2, R.drawable.salary3, R.drawable.salary4
        )

        override val name = language.salary

        override val color = iconColor
    }

}