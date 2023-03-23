package com.example.artspace

import android.content.res.Configuration
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme() {
                if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE)
                    ArtSpaceScreenLandscape()
                else
                    ArtSpaceScreenPortrait()
            }
        }
    }
}


/**
 * Defines how the artwork, the artwork informations and the
 * Next and Previous buttons are displayed.
 * The buttons are defined so that the user can navigate over the artworks.
 */
@Composable
fun ArtSpaceScreenPortrait() {
    var result by remember { mutableStateOf(1)}
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Artwork(result)
            ArtworkInfo(result)

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                val buttonColors: ButtonColors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                )

                Button(
                    onClick = { if (result <= 1) result = 4 else result-- },
                    colors = buttonColors,
                    modifier = Modifier.weight(1f).padding(start = 16.dp, end = 50.dp, top = 16.dp)
                ) {
                    Text(stringResource(R.string.Previous_Button))
                }

                Button(
                    onClick = { if (result >= 4) result = 1 else result++ },
                    colors = buttonColors,
                    modifier = Modifier.weight(1f).padding(start = 50.dp, end = 16.dp, top = 16.dp)
                ) {
                    Text(stringResource(R.string.Next_Button))
                }
            }

        }

    }


/**
 * Defines how the artwork, the artwork informations and the
 * Next and Previous buttons are displayed.
 * The buttons are defined so that the user can navigate over the artworks.
 */
@Composable
fun ArtSpaceScreenLandscape() {
    var result by remember { mutableStateOf(1)}
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        Artwork(result)
        Column() {
            ArtworkInfo(result)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                val buttonColors: ButtonColors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                )

                Button(
                    onClick = { if (result <= 1) result = 4 else result-- },
                    colors = buttonColors,
                    modifier = Modifier.weight(1f).padding(top = 16.dp)
                ) {
                    Text(stringResource(R.string.Previous_Button))
                }

                Button(
                    onClick = { if (result >= 4) result = 1 else result++ },
                    colors = buttonColors,
                    modifier = Modifier.weight(1f).padding(end = 16.dp, top = 16.dp)
                ) {
                    Text(stringResource(R.string.Next_Button))
                }
            }
        }
    }

}


 /**
  * Selects the artwork and defines how it is displayed.
  * */
    @Composable
    private fun Artwork(result: Int) {
        var img = when (result) {
            1 -> painterResource(R.drawable.artwork_1)
            2 -> painterResource(R.drawable.artwork_2)
            3 -> painterResource(R.drawable.artwork_3)
            else -> painterResource(R.drawable.artwork_4)
        }

        var description = when (result) {
            1 -> stringResource(R.string.content_description_1)
            2 -> stringResource(R.string.content_description_2)
            3 -> stringResource(R.string.content_description_3)
            else -> stringResource(R.string.content_description_4)
        }
        Surface(
            border = BorderStroke(5.dp, MaterialTheme.colors.primary),
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = img,
                contentDescription = description,
                modifier = Modifier.padding(30.dp).size(400.dp)
            )
        }
    }


/**
 * Selects the title of the artwork, the artists name and
 * the year of publication and defines how they are displayed.
 * */
@Composable
    private fun ArtworkInfo(result: Int) {
        var title = when (result) {
            1 -> stringResource(R.string.title_1)
            2 -> stringResource(R.string.title_2)
            3 -> stringResource(R.string.title_3)
            else -> stringResource(R.string.title_4)
        }

        var artist_Year = when (result) {
            1 -> stringResource(R.string.artist_year_1)
            2 -> stringResource(R.string.artist_year_2)
            3 -> stringResource(R.string.artist_year_3)
            else -> stringResource(R.string.artist_year_4)
        }

        Text(text = title, fontSize = 40.sp)
        Text(text = artist_Year, fontSize = 20.sp)
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE)
            ArtSpaceScreenLandscape()
        else
            ArtSpaceScreenPortrait()
    }
}