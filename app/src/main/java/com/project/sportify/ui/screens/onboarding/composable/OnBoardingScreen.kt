package com.project.sportify.ui.screens.onboarding.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.project.sportify.R
import com.project.sportify.ui.base.composables.SportifyPageIndicator
import com.project.sportify.ui.screens.onboarding.contract.OnBoardingContract
import com.project.sportify.ui.theme.AzureRadiance
import com.project.sportify.ui.theme.Black
import com.project.sportify.ui.theme.HintColorD9
import com.project.sportify.ui.theme.Mirage
import com.project.sportify.ui.theme.White
import com.project.sportify.ui.theme.dimens
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun OnBoardingScreen(
    onEventSent: (event: OnBoardingContract.Event) -> Unit,
    modifier: Modifier = Modifier,
    state: OnBoardingContract.State,
    onNavigationRequested: (navigationEffect: OnBoardingContract.Effect.Navigation) -> Unit,
    effectFlow: Flow<OnBoardingContract.Effect?>
) {
    val pagerState = rememberPagerState(pageCount = { 5 }, initialPage = 0)
    LaunchedEffect(effectFlow) {
        effectFlow.onEach { effect ->
            when (effect) {
                is OnBoardingContract.Effect.Navigation.ChooseWay -> onNavigationRequested(effect)
                else -> {}
            }
        }.collect()
    }
    LaunchedEffect(state.currentPage) {
        pagerState.animateScrollToPage(state.currentPage)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(if (state.isDarkMode) Mirage else White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SportifyPageIndicator(
            isDarkMode = state.isDarkMode,
            pagerState = pagerState
        )
        HorizontalPager(
            userScrollEnabled = true,
            state = pagerState,
            modifier = modifier.wrapContentHeight(),
        ) {
            when (pagerState.currentPage) {
                0 -> OnBoardingItem(
                    image = R.drawable.ic_onboarding1,
                    title = R.string.onboarding_title_1,
                    description = R.string.onboarding_des_1,
                    isDarkMode = state.isDarkMode
                )

                1 -> OnBoardingItem(
                    image = R.drawable.ic_onboarding2,
                    title = R.string.onboarding_title_2,
                    description = R.string.onboarding_des_2,
                    isDarkMode = state.isDarkMode
                )

                2 -> OnBoardingItem(
                    image = R.drawable.ic_onboarding3,
                    title = R.string.onboarding_title_3,
                    description = R.string.onboarding_des_3
                )

                3 -> OnBoardingItem(
                    image = R.drawable.ic_onboarding4,
                    title = R.string.onboarding_title_4,
                    description = R.string.onboarding_des_4,
                    isDarkMode = state.isDarkMode
                )

                4 -> OnBoardingItem(
                    image = R.drawable.ic_onboarding5,
                    title = R.string.onboarding_title_4,
                    description = R.string.onboarding_des_5,
                    isDarkMode = state.isDarkMode
                )
            }
        }
        Spacer(modifier = modifier.weight(1f))
        Button(
            onClick = {
                onEventSent(OnBoardingContract.Event.NavigateToNextScreen(pagerState.currentPage+1))
            },
            modifier
                .width(MaterialTheme.dimens.size150dp)
                .height(MaterialTheme.dimens.size50dp),
            colors = ButtonColors(
                contentColor = White,
                containerColor = if (state.isDarkMode) White else Mirage,
                disabledContentColor = White,
                disabledContainerColor = White,
            )
        ) {
            Text(
                text = if (pagerState.currentPage == 4) stringResource(R.string.lets_Go) else stringResource(
                    R.string.next
                ),
                style = TextStyle(
                    fontSize = MaterialTheme.dimens.size20sp,
                    fontWeight = FontWeight(500),
                    color = if (state.isDarkMode) Mirage else White,

                    )
            )
        }
        Spacer(modifier = modifier.height(MaterialTheme.dimens.size10dp))
        if (pagerState.currentPage != 4) Text(
            text = stringResource(R.string.skip),
            style = TextStyle(
                fontSize = MaterialTheme.dimens.size20sp,
                fontWeight = FontWeight(600),
                color = if (state.isDarkMode) White else Black,
            ),
            modifier = modifier.clickable {
                onEventSent(OnBoardingContract.Event.OnSkipClicked)
            }
        ) else Spacer(modifier = modifier.height(MaterialTheme.dimens.size30dp))
        Spacer(modifier = modifier.height(MaterialTheme.dimens.size8dp))
    }
}