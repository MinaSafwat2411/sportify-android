package com.project.sportify.data.sharedprefrences



interface IPreferencesDataSource {
    fun shouldNavigateToWhichScreen():Boolean
    fun isDarkMode():Boolean
    fun isOpened():Boolean
    fun  open()
}