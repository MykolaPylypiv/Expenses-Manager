package com.example.expensesmanager.ui.screens.statistics.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.ui.composable.BarChart
import com.example.expensesmanager.ui.composable.Chart
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ChartLayer(
    chartValues: List<Float>, chartColors: List<Color>, language: Language
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pagerState = rememberPagerState()

        HorizontalPager(
            count = 3,
            state = pagerState,
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) { currentPage ->
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                if (chartValues.isNotEmpty()) {
                    when (currentPage) {

                        0 -> Chart(
                            modifier = Modifier
                                .padding(40.dp)
                                .fillMaxWidth(),
                            colors = chartColors,
                            inputValues = chartValues,
                            withCenter = true
                        )

                        1 -> Chart(
                            modifier = Modifier
                                .padding(40.dp)
                                .fillMaxWidth(),
                            colors = chartColors,
                            inputValues = chartValues,
                            withCenter = false
                        )

                        2 -> BarChart(
                            modifier = Modifier
                                .padding(40.dp)
                                .fillMaxWidth(),
                            colors = chartColors,
                            values = chartValues,
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(96.dp))

                    Text(
                        text = language.empty,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 28.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.weight(1f))
                }

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    inactiveColor = Color.Gray,
                    activeColor = Color.LightGray
                )
            }
        }
    }
}