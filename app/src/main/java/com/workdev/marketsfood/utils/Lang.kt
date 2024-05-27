package com.mg_group.womniz.utils

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import java.util.Locale

 fun setLocate(Lang: String,context: Context) {

    val locale = Locale(Lang)

    Locale.setDefault(locale)

    val config = Configuration()

    config.locale = locale
    context.resources.updateConfiguration(config,context.resources?.displayMetrics)
    val editor = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)!!.edit()
    editor.putString("lang", Lang)
    editor.apply()
}


 fun loadLocate(context: Context):String {
    val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
    var language = sharedPreferences?.getString("lang", "")
   // Toast.makeText(context,"lang"+language, Toast.LENGTH_LONG).show()

    setLocate(language!!,context)
    return language
}