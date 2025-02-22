package com.project.sportify.ui.screens.onboarding.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.project.sportify.R
import com.project.sportify.ui.theme.AzureRadiance
import com.project.sportify.ui.theme.Black
import com.project.sportify.ui.theme.Mirage
import com.project.sportify.ui.theme.White
import com.project.sportify.ui.theme.dimens

@Composable
fun OnBoardingItem(
    modifier: Modifier = Modifier,
    image: Int,
    title: Int,
    description: Int,
    isDarkMode: Boolean = false
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.dimens.size20dp,
                end = MaterialTheme.dimens.size20dp,
                top = MaterialTheme.dimens.size13dp,
                bottom = MaterialTheme.dimens.size8dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(painter = painterResource(image), contentDescription = "",modifier.height(
            MaterialTheme.dimens.size300dp)
            .width(MaterialTheme.dimens.size300dp))
        Spacer(modifier = modifier.height(MaterialTheme.dimens.size60dp))
        Text(text = stringResource(title),style = TextStyle(
            fontSize = MaterialTheme.dimens.size36sp,
            fontWeight = FontWeight(700),
            color = if (isDarkMode) White else Mirage,
        )
        )
        Spacer(modifier = modifier.height(MaterialTheme.dimens.size7dp))
        Text(text = stringResource(description),style = TextStyle(
            fontSize = MaterialTheme.dimens.size16sp,
            fontWeight = FontWeight(300),
            color = if (isDarkMode) White else Mirage,
        )
        )
    }
}
