package com.example.weather_tms_himach.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_tms_himach.R
import com.example.weather_tms_himach.databinding.ItemTwelveForBinding
import com.example.weather_tms_himach.domain.models.TwelveHoursForecast
import com.example.weather_tms_himach.utils.IconForecast

class TwelveForAdapter(
    private val items: List<TwelveHoursForecast>,
    private val context: Context
) : RecyclerView.Adapter<TwelveForAdapter.TwelveForHolder>() {

    class TwelveForHolder(
        itemBinding: ItemTwelveForBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        val timeFor: TextView = itemBinding.timeItem
        val iconFor: ImageView = itemBinding.iconItem
        val valueFor: TextView = itemBinding.valueItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TwelveForHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTwelveForBinding.inflate(inflater, parent, false)
        return TwelveForHolder(binding)
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(
        holder: TwelveForHolder, position: Int
    ) {
        holder.apply {
            timeFor.text = items[position].date
            valueFor.text = getConvertTemp(items[position].temperature)
            iconFor.apply {
                setImageResource(
                    getIcon(items[position].weatherIcon)
                )
            }
        }
    }

    private fun getIcon(icon: Int?): Int =
        IconForecast.ICONS.getIconForecast(icon)

    private fun getConvertTemp(temp: Double?): String =
        buildString {
            append(temp)
            append(
                context.getString(R.string.degree_sign)
            )
        }
}