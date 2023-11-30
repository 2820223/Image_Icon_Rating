package com.gowtham.compose_ratingbar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gowtham.compose_ratingbar.MainActivity.Companion.initialRating
import com.gowtham.compose_ratingbar.ui.theme.JetpackComposeTheme
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    companion object {
        var initialRating = 3.2f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}


@Composable
fun MyApp() {
    JetpackComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            MainScreen()


        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val preferencesManager = remember { RatingManager(context) }
    val data = remember { mutableStateOf(preferencesManager.getData("myKey", "")) }

    val gottonRatingOne = preferencesManager.getData("ratingOne","0")
    var ratingOne: Float by rememberSaveable { mutableFloatStateOf(gottonRatingOne.toFloat()) }
    var ratingTwo: Float by rememberSaveable { mutableFloatStateOf(initialRating) }


    // Use the data variable in your Composable

    // Update data and save to SharedPreferences
    preferencesManager.saveData("ratingOne", ratingOne.toString())
    data.value = ratingOne.toString()

Row {

    Icon(
        painter = painterResource(R.drawable.book),
        contentDescription = "Home Icon",
        modifier = Modifier.size(50.dp)
    )

    Text(
        text = "Rate the book!",

        color = Color.Black,

        fontSize = 36.sp,

        fontWeight = FontWeight.SemiBold,

        fontFamily = FontFamily.Monospace,

        letterSpacing = 0.25.sp,

        modifier = Modifier.padding(5.dp),

        )

}
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        

        Image(
            painter = painterResource(id = R.drawable.fourth_wing), // Replace R.drawable.my_image with your image resource ID
            contentDescription = "Image content description",
            modifier = Modifier
                .size(200.dp)
                .clip(MaterialTheme.shapes.medium) // You can use clip to apply rounded corners or other shapes
                .padding(16.dp), // Adjust padding as needed
            contentScale = ContentScale.Crop // Adjust content scale as needed
        )

        Spacer(modifier = Modifier.height(30.dp))
        RatingBar(
            value = ratingOne,
            stepSize = StepSize.HALF,
            style = RatingBarStyle.Fill(),
            onValueChange = {
                ratingOne = it

            },
            onRatingChanged = {

                Log.d("TAG", "onRatingChanged: $it")
                gottonRatingOne == preferencesManager.getData("ratingOne", "0")
            }


        )
        Spacer(modifier = Modifier.height(30.dp))
//        RatingBar(
//            value = ratingTwo,
//            painterEmpty = painterResource(id = R.drawable.icon_empty),
//            painterFilled = painterResource(id = R.drawable.icon_filled),
//            onValueChange = {
//                ratingTwo = it
//            },
//            onRatingChanged = {
//                Log.d("TAG", "onRatingChanged: $it")
//            }
//        )
        Row {
            Text(text = "Your rating:    ")
            Text(text = "$gottonRatingOne", Modifier.size(20.dp))

            Icon(

                painter = painterResource(R.drawable.rating),
                modifier = Modifier.size(20.dp),
                contentDescription = "Home Icon"

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}