package com.project.sportify.data.repositories.splash

import com.project.sportify.domain.base.IBaseRepository

interface ISplashRepository: IBaseRepository {
    fun shouldNavigateToWhichScreen(): Boolean
}