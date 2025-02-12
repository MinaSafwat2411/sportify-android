package com.project.sportify.ui.base

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.project.sportify.R

@Composable
fun SportifyLogo() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = stringResource(
            id = R.string.app_name
        )
    )
}