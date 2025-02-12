package com.project.sportify.domain.usecases.splash

import com.project.sportify.ui.base.IBaseUseCase

interface ISplashUseCase:IBaseUseCase {
    fun shouldNavigateToWhichScreen(): Boolean
}