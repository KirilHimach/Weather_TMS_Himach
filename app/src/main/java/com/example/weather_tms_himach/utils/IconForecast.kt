package com.example.weather_tms_himach.utils

import com.example.weather_tms_himach.R

enum class IconForecast {
    ICONS {
        override fun getIconForecast(value: Int?): Int {
            when (value) {
                1 -> return R.drawable.icon_1
                in 2..6 -> return R.drawable.icon_2
                7, 8 -> return R.drawable.icon_3
                11 -> return R.drawable.icon_4
                12, 18 -> return R.drawable.icon_5
                13, 14, 16, 17 -> return R.drawable.icon_6
                15, 41, 42 -> return R.drawable.icon_7
                19, 22, in 24..29 -> return R.drawable.icon_8
                20, 21, 23 -> return R.drawable.icon_9
                30 -> return R.drawable.icon_11
                31 -> return R.drawable.icon_12
                33 -> return R.drawable.icon_14
                in 34..38 -> return R.drawable.icon_15
                39, 40 -> return R.drawable.icon_16
                43, 44 -> return R.drawable.icon_17
                else -> return R.drawable.icon_2
            }
        }
    };
    abstract fun getIconForecast(value: Int?): Int
}