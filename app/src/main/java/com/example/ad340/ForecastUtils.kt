package com.example.ad340

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun formatTempDisplay(temp: Float, tempDisplaySetting: TempDisplaySetting): String{
        return when (tempDisplaySetting) {
            TempDisplaySetting.Fahrenheit -> String.format("%.1f°F", temp)
            TempDisplaySetting.Celsius -> {
                val temp = (temp - 32f) * (5f / 9f)
                String.format("%.1f°C", temp)
            }
        }
    }

fun showTempDisplaySettingDialog(context: Context, tempDisplaySettingManager: TempDisplaySettingManager) {
    val dialogBuilder = AlertDialog.Builder(context)
        .setTitle("Chose Display Unit")
        .setMessage("Choose which temperature unit to use for temperature display")
        .setPositiveButton("F°"){ _, _ ->
            tempDisplaySettingManager.updateSetting(TempDisplaySetting.Fahrenheit)
        }
        .setNeutralButton("C°"){_, _ ->
            tempDisplaySettingManager.updateSetting(TempDisplaySetting.Celsius)
        }
        .setOnDismissListener{
            Toast.makeText(context, "Setting will take affect after app restart", Toast.LENGTH_SHORT).show()
        }
    dialogBuilder.show()
}

