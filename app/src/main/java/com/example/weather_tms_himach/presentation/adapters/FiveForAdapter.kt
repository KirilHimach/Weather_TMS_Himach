package com.example.weather_tms_himach.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_tms_himach.R
import com.example.weather_tms_himach.databinding.ItemFiveForBinding
import com.example.weather_tms_himach.domain.models.FiveDaysForecast
import com.example.weather_tms_himach.utils.IconForecast
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FiveForAdapter(
    private val items: List<FiveDaysForecast>,
    private val context: Context
) : RecyclerView.Adapter<FiveForAdapter.FiveForHolder>() {

    class FiveForHolder(
        itemBinding: ItemFiveForBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        val day: TextView = itemBinding.dayOfWeekItem
        val iconDay: ImageView = itemBinding.iconDayItem
        val minTemp: TextView = itemBinding.minTempItem
        val iconNight: ImageView = itemBinding.iconNightItem
        val maxTemp: TextView = itemBinding.maxTempItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): FiveForHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFiveForBinding.inflate(inflater, parent, false)
        return FiveForHolder(binding)
    }

    override fun onBindViewHolder(holder: FiveForHolder, position: Int) {
        holder.apply {
            day.text = getDay(items[position].dayOfWeek.orEmpty())
            minTemp.text = getConvertTemp(items[position].temperatureMin)
            maxTemp.text = getConvertTemp(items[position].temperatureMax)
            iconDay.apply {
                setImageResource(
                    getIcon(items[position].dayIcon)
                )
            }
            iconNight.apply {
                setImageResource(
                    getIcon(items[position].nightIcon)
                )
            }
        }
    }

    override fun getItemCount(): Int =
        items.size

    private fun getIcon(icon: Int?): Int =
        IconForecast.ICONS.getIconForecast(icon)

    private fun getConvertTemp(temp: Double?): String =
        buildString {
            append(temp)
            append(
                context.getString(R.string.degree_sign)
            )
        }

    private fun getDay(dayOfWeek: String): String =
        LocalDate.parse(
            dayOfWeek,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME
        ).format(DateTimeFormatter.ofPattern("E"))

    companion object {
        private const val DEGREE_SIGN = '\u00B0'
    }
}