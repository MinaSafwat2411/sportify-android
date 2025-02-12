
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.project.sportify.data.sharedprefrences.IPreferencesDataSource
import com.project.sportify.ui.main.NavigationScreen
import com.project.sportify.utils.Constants

class PreferencesDataSource(context: Context, gson: Gson): IPreferencesDataSource {

    companion object {
        private const val PREF_NAME = "app_preferences"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveInt(key: String,value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun saveString(key: String,value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    private fun getString(key: String, route: Any): String? {
        return sharedPreferences.getString(key, null)
    }

    fun saveBoolean(key: String,value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    private fun getBoolean(key:String): Boolean {
        return sharedPreferences.getBoolean(key,false)
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    override fun shouldNavigateToWhichScreen(): Boolean {
        return  sharedPreferences.getBoolean(Constants.SharedPreference.IS_OPENED,false)
    }
}
