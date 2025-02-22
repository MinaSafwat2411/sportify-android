package com.project.sportify.domain.usecases.splash

import com.project.sportify.data.repositories.splash.ISplashRepository
import com.project.sportify.ui.main.NavigationScreen
import com.project.sportify.utils.alternate
import javax.inject.Inject

class SplashUseCase @Inject constructor(
    private val splashRepository: ISplashRepository
):ISplashUseCase {
    override fun shouldNavigateToWhichScreen(): Boolean {
        return  splashRepository.shouldNavigateToWhichScreen()
    }

    override fun isDarkMode(): Boolean {
        return splashRepository.isDarkMode()
    }

    fun isOpened(): Boolean {
        return  splashRepository.isOpened()
    }
}