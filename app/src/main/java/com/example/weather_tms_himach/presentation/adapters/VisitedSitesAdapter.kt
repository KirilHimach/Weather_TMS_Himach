package com.example.weather_tms_himach.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_tms_himach.R
import com.example.weather_tms_himach.databinding.ItemLastVisitedSitesBinding
import com.example.weather_tms_himach.domain.models.VisitedCityStatistics
import com.example.weather_tms_himach.utils.IconForecast

internal class VisitedSitesAdapter(
    private val items: List<VisitedCityStatistics>,
    private val context: Context
) : RecyclerView.Adapter<VisitedSitesAdapter.VisitedSitesHolder>() {

    class VisitedSitesHolder(
        itemBinding: ItemLastVisitedSitesBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        val city: TextView = itemBinding.city
        val icon: ImageView = itemBinding.icon
        val temp: TextView = itemBinding.temperature
        val weatherText: TextView = itemBinding.weatherText
        val date: TextView = itemBinding.date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitedSitesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLastVisitedSitesBinding.inflate(inflater, parent, false)
        return VisitedSitesHolder(binding)
    }

    override fun onBindViewHolder(holder: VisitedSitesHolder, position: Int) {
        holder.apply {
            city.text = items[position].city
            temp.text = getConvertTemp(items[position].temp)
            weatherText.text = items[position].weatherText
            date.text = items[position].date
            icon.apply {
                setImageResource(
                    getIcon(items[position].icon)
                )
            }
        }
    }

    private fun getIcon(icon: Int?): Int =
        IconForecast.ICON.getIconForecast(icon)

    private fun getConvertTemp(temp: Double?): String =
        buildString {
            append(temp)
            append(
                context.getString(R.string.degree_sign)
            )
        }

    override fun getItemCount(): Int =
        items.size
}