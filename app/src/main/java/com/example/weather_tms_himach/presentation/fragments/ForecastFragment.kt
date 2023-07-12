package com.example.weather_tms_himach.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_tms_himach.R
import com.example.weather_tms_himach.databinding.FragmentForecastBinding
import com.example.weather_tms_himach.di.base.DaggerDaggerComponent
import com.example.weather_tms_himach.di.modules.ViewModelFactory
import com.example.weather_tms_himach.domain.models.CurrentCondition
import com.example.weather_tms_himach.domain.models.FiveDaysForecast
import com.example.weather_tms_himach.domain.models.TwelveHoursForecast
import com.example.weather_tms_himach.presentation.adapters.FiveForAdapter
import com.example.weather_tms_himach.presentation.adapters.TwelveForAdapter
import com.example.weather_tms_himach.presentation.view_models.ForecastViewModel
import com.example.weather_tms_himach.utils.observeWithLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import javax.inject.Inject


class ForecastFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var _forecastViewModel: ForecastViewModel? = null
    private val forecastViewModel: ForecastViewModel
        get() = _forecastViewModel ?: throw IllegalStateException("ForecastViewModel is not found")
    private lateinit var binding: FragmentForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.application?.let {
            DaggerDaggerComponent.factory().create(it).inject(this)
        }
        _forecastViewModel = viewModelFactory.create(ForecastViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onObserveForEvents()
        onObserveGeo()


        //TODO
        binding.testButton.setOnClickListener {
            findNavController().navigate(R.id.action_ForecastFragment_to_VisitedSitesFragment)
        }
    }

    private fun onObserveGeo() {
        viewLifecycleOwner.lifecycleScope.launch {
            forecastViewModel.geoChannel.collect() {
                when(it) {
                    is ForecastViewModel.GeoEvent.InitGeo -> {
                        it.geo.locationKey?.let { key -> forecastViewModel.uploadForecast(key) }
                        it.geo.city?.let { city -> onSetGeo(city) }
                    }
                }
            }
        }
    }

    private fun onObserveForEvents() =
        forecastViewModel.getEventForecast().observeWithLifecycle(
            fragment = this@ForecastFragment,
            action = ::handelEvent
        )


    private fun handelEvent(event: ForecastViewModel.ForecastEvent) {
        when(event) {
            is ForecastViewModel.ForecastEvent.Default -> return
            is ForecastViewModel.ForecastEvent.InitCurrentCondition -> {
                onSetCurrentCond(event.currentCondition)
            }
            is ForecastViewModel.ForecastEvent.InitFiveForAdapter -> {
                onCreateFiveForRecycler(event.fiveDaysFor)
            }
            is ForecastViewModel.ForecastEvent.InitTwelveForAdapter -> {
                onCreateTwelveForRecycler(event.twelveHoursFor)
            }
        }
    }

    private fun onSetCurrentCond(currentCond: List<CurrentCondition>?) {
        val forecast = currentCond?.get(0)
        val temp = buildString {
            append(forecast?.realFellTempMetVal)
            append(
                getString(R.string.degree_sign)
            )
            append(forecast?.realFellTempMetUn)
        }
        val humid = buildString {
            append(forecast?.relativeHumidity)
            append(getString(R.string.percent))
        }
        binding.apply {
            currentTemp.text = temp
            currentWeatherText.text = forecast?.weatherText
            uvIndex.text = forecast?.uVIndex.toString()
            humidity.text = humid
            wind.text = forecast?.windSpeedMetVal.toString()
            windUn.text = forecast?.windSpeedMetUn
            visibility.text = forecast?.visibilityMetVal.toString()
            visibilityUn.text = forecast?.visibilityMetUn
            pressure.text = forecast?.pressureMetVal.toString()
            pressureUn.text = forecast?.pressureMetUn
            apparent.text = forecast?.apparentTempMetVal.toString()
            apparentUn.text = forecast?.apparentTempMetUn
        }
    }

    private fun onCreateFiveForRecycler(fiveDaysFor: List<FiveDaysForecast>?) {
        binding.fiveForRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = fiveDaysFor?.let {
                FiveForAdapter(
                    items = it, context = context
                )
            }
        }
    }

    private fun onCreateTwelveForRecycler(twelveHoursForecast: List<TwelveHoursForecast>?) {
        binding.twelveForRecycler.apply {
            layoutManager = LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
            )
            adapter = twelveHoursForecast?.let {
                TwelveForAdapter(
                    items = it, context = context
                )
            }
        }
    }

    private fun onSetGeo(geo: String?) {
        binding.city.text = geo
    }
}