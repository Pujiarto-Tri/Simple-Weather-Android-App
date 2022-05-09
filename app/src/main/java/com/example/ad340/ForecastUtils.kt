package com.example.ad340

    fun formatTempDisplay(temp: Float, tempDisplaySetting: TempDisplaySetting): String{
        return when (tempDisplaySetting) {
            TempDisplaySetting.Fahrenheit -> String.format("%.1f°F", temp)
            TempDisplaySetting.Celsius -> {
                val temp = (temp - 32f) * (5f / 9f)
                String.format("%.1f°C", temp)
            }
        }
    }
