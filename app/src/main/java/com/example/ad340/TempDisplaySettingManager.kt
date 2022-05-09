package com.example.ad340

import android.content.Context

enum class TempDisplaySetting {
    Fahrenheit, Celsius
}

class TempDisplaySettingManager(context: Context) {
    private val prefrences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun updateSetting(setting: TempDisplaySetting) {
        prefrences.edit().putString("key_temp_display", setting.name).commit()
    }

    fun getTempDisplaySetting() : TempDisplaySetting {
        val settingValue = prefrences.getString("key_temp_display", TempDisplaySetting.Fahrenheit.name)?:TempDisplaySetting.Fahrenheit.name
        return TempDisplaySetting.valueOf(settingValue)
    }
}