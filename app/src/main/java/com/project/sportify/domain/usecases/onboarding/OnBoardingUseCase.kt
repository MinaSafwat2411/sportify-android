package com.project.sportify.domain.usecases.onboarding

import com.project.sportify.data.repositories.onboarding.IOnBoardingRepository
import javax.inject.Inject

class OnBoardingUseCase @Inject constructor(
    private val onBoardingRepository: IOnBoardingRepository
) : IOnBoardingUseCase  {
    override fun isDarkMode(): Boolean {
        return onBoardingRepository.isDarkMode()
    }

    override fun open() {
        onBoardingRepository.open()
    }
}