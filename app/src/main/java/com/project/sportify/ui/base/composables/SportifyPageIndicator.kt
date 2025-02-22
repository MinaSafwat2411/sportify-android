package com.project.sportify.ui.base.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.sportify.ui.theme.AzureRadiance
import com.project.sportify.ui.theme.HintColorD9
import com.project.sportify.ui.theme.Mirage
import com.project.sportify.ui.theme.dimens

@Composable
fun SportifyPageIndicator(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean,
    pagerState: PagerState
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.dimens.size20dp,
                vertical = MaterialTheme.dimens.size40dp
            ),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            userScrollEnabled = false,
            modifier = modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimens.size14dp)
        ) {
            items(4) { index ->
                Card(
                    modifier = modifier
                        .height(MaterialTheme.dimens.size6dp)
                        .width(MaterialTheme.dimens.size83dp),
                    colors = CardColors(
                        containerColor = if (isDarkMode && pagerState.currentPage > index) AzureRadiance else if (pagerState.currentPage > index && !isDarkMode) Mirage  else HintColorD9,
                        contentColor = HintColorD9,
                        disabledContentColor = HintColorD9,
                        disabledContainerColor = HintColorD9
                    ),
                    content = {}
                )
            }
        }
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            userScrollEnabled = false,
            modifier = modifier.fillMaxWidth()
        ) {
            items(5) { index ->
                Card(
                    modifier = modifier.size(MaterialTheme.dimens.size20dp),
                    colors = CardColors(
                        containerColor = if (isDarkMode && pagerState.currentPage >= index) AzureRadiance else if (pagerState.currentPage >= index && !isDarkMode) Mirage  else HintColorD9,
                        contentColor = HintColorD9,
                        disabledContentColor = HintColorD9,
                        disabledContainerColor = HintColorD9
                    ),
                    shape = RoundedCornerShape(MaterialTheme.dimens.size20dp),
                    content = {}
                )
            }
        }
    }
}