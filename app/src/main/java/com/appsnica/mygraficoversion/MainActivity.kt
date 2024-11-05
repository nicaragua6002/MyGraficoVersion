package com.appsnica.mygraficoversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
//import androidx.compose.ui.tooling.data.EmptyGroup.data
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsnica.mygraficoversion.ui.theme.MyGraficoVersionTheme
import ir.ehsannarmani.compose_charts.ColumnChart
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.Pie

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyGraficoVersionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val valores = remember {
        listOf(
            Bars(
                label = "Jan",
                values = listOf(
                    Bars.Data(label = "Linux", value = 50.0, color = SolidColor(Color.Blue)),
                    Bars.Data(label = "Windows", value = 70.0, color = SolidColor(Color.Red))
                )
            ),
            Bars(
                label = "Feb",
                values = listOf(
                    Bars.Data(label = "Linux", value = 50.0, color = SolidColor(Color.Blue)),
                    Bars.Data(label = "Windows", value = 70.0, color = SolidColor(Color.Red))
                )
            ),
            Bars(
                label = "Mar",
                values = listOf(
                    Bars.Data(label = "Linux", value = 80.0, color = SolidColor(Color.Blue)),
                    Bars.Data(label = "Windows", value = 60.0, color = SolidColor(Color.Red))
                )
            )

        )
    }
    Column {
        Text(text="Grafico Lineal", style = MaterialTheme.typography.displayMedium)
        Card{
            LineChart(
                modifier = Modifier
                    .fillMaxWidth().height(200.dp)
                    .padding(horizontal = 22.dp),
                data = remember {
                    listOf(
                        Line(
                            label = "Windows",
                            values = listOf(28.0, 41.0, 5.0, 10.0, 35.0),
                            color = SolidColor(Color(0xFF23af92)),
                            firstGradientFillColor = Color(0xFF2BC0A1).copy(alpha = .5f),
                            secondGradientFillColor = Color.Transparent,
                            strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                            gradientAnimationDelay = 1000,
                            drawStyle = DrawStyle.Stroke(width = 2.dp),
                        ),
                        Line(
                            label = "Linux",
                            values = listOf(20.0, 50.0, 5.0, 12.0, 25.0),
                            color = SolidColor(Color(0xFF232292)),
                            firstGradientFillColor = Color(0xFF2B22A1).copy(alpha = .5f),
                            secondGradientFillColor = Color.Transparent,
                            strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                            gradientAnimationDelay = 1000,
                            drawStyle = DrawStyle.Stroke(width = 2.dp),
                        )
                    )
                },
                animationMode = AnimationMode.Together(delayBuilder = {
                    it * 500L
                }),
            )
        }

        Text(text="Grafico Pastel", style = MaterialTheme.typography.displayMedium)
        Card {
            PieChart(
                modifier = Modifier.size(200.dp),
                data = remember {
                    listOf(
                        Pie(label = "Android", data = 20.0, color = Color.Red, selectedColor = Color.Green),
                        Pie(label = "Windows", data = 45.0, color = Color.Cyan, selectedColor = Color.Blue),
                        Pie(label = "Linux", data = 35.0, color = Color.Gray, selectedColor = Color.Yellow),
                    )
                },
                onPieClick = {
                    println("${it.label}")
                },
                /*onPieClick = {
                    println("${it.label} Clicked")
                    val pieIndex = data.indexOf(it)
                    data = data.mapIndexed { mapIndex, pie -> pie.copy(selected = pieIndex == mapIndex) }
                },*/
                selectedScale = 1.2f,
                scaleAnimEnterSpec = spring<Float>(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                ),
                colorAnimEnterSpec = tween(300),
                colorAnimExitSpec = tween(300),
                scaleAnimExitSpec = tween(300),
                spaceDegreeAnimExitSpec = tween(300),
                style = Pie.Style.Fill
            )
        }
        Text(text="Grafico Barras agrupadas", style = MaterialTheme.typography.displayMedium)
        Card {
            ColumnChart(
                modifier= Modifier
                    .fillMaxSize()
                    .padding(horizontal = 22.dp),
                data = valores,
                barProperties = BarProperties(
                    cornerRadius = Bars.Data.Radius.Rectangle(topRight = 6.dp, topLeft = 6.dp),
                    spacing = 3.dp,
                    thickness = 20.dp
                ),
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                ),
            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MyGraficoVersionTheme {
        Greeting("Android")
    }
}