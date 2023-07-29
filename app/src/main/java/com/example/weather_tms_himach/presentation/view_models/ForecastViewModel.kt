package com.example.weather_tms_himach.presentation.view_models

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_tms_himach.domain.models.CurrentCondition
import com.example.weather_tms_himach.domain.models.FiveDaysForecast
import com.example.weather_tms_himach.domain.models.Geolocation
import com.example.weather_tms_himach.domain.models.TwelveHoursForecast
import com.example.weather_tms_himach.domain.use_cases.ForecastUseCase
import com.example.weather_tms_himach.utils.locale.Locale
import com.example.weather_tms_himach.utils.location.CurrentLocation
import com.google.android.gms.location.LocationListener
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

internal class ForecastViewModel @Inject constructor(
    private val forecastUseCase: ForecastUseCase,
    private val currentLocation: CurrentLocation,
    private val mainLocale: Locale
) : ViewModel() {
    private val metric = true
    private val eventForecast: MutableStateFlow<ForecastEvent> =
        MutableStateFlow(value = ForecastEvent.Default)
    private val _locationChannel = Channel<LocationEvent>()
    private val locationChannel = _locationChannel.receiveAsFlow()
    private val _geoChannel = Channel<GeoEvent>()
    internal val geoChannel = _geoChannel.receiveAsFlow()
    private lateinit var locationListener: LocationListener
    private lateinit var langTag: String

    internal fun uploadLocation() {
        viewModelScope.launch {
            onObserveLocation()
            getLastLocation()
            onInitLocale()
        }
    }

    internal fun getEventForecast(): Flow<ForecastEvent> = eventForecast

    internal fun uploadForecast(locationKey: String) {
        viewModelScope.launch {
            getCurrentCond(locationKey = locationKey, langTag = langTag)
            getFiveDaysFor(locationKey = locationKey, langTag = langTag, metric = metric)
            getTwelveFor(locationKey = locationKey, langTag = langTag, metric = metric)
        }
    }

    private suspend fun onObserveLocation() {
        viewModelScope.launch {
            locationChannel.collect() { locationEvent ->
                when (locationEvent) {
                    is LocationEvent.InitLocation -> {
                        getGeolocationKey(
                            latAndLon = getFormatLatLon(locationEvent.location),
                            langTag = langTag
                        )
                    }
                }
            }
        }
    }

    private suspend fun getLastLocation() {
        val lastLocation = currentLocation.getLastLocation()
        if (lastLocation != null) {
            _locationChannel.send(LocationEvent.InitLocation(lastLocation))
        } else {
            locationListener = LocationListener { updatedLastLocation ->
                viewModelScope.launch {
                    _locationChannel.send(LocationEvent.InitLocation(updatedLastLocation))
                    onRemoveUpdates()
                }
            }
            currentLocation.startLocationUpdates(locationListener)
        }
    }

    private suspend fun onRemoveUpdates() {
        currentLocation.removeUpdates(locationListener)
    }

    private suspend fun getGeolocationKey(latAndLon: String, langTag: String) {
        try {
            val geo = forecastUseCase.getGeo(
                latAndLon = latAndLon, language = langTag
            )
            _geoChannel.send(GeoEvent.InitGeo(geo))
        } catch (e: Exception) {
            uploadLocation()
        }
    }

    private fun onForecastEventHandled(event: ForecastEvent) =
        viewModelScope.launch { eventForecast.emit(event) }

    private suspend fun getCurrentCond(
        locationKey: String, langTag: String
    ) {
        val currentCond =
            forecastUseCase.getCurCond(
                locationKey = locationKey, language = langTag
            )
        onForecastEventHandled(ForecastEvent.InitCurrentCondition(currentCond))
    }

    private suspend fun getFiveDaysFor(
        locationKey: String, langTag: String, metric: Boolean
    ) {
        val fiveDaysForecast =
            forecastUseCase.getFiveDaysFor(
                locationKey = locationKey, language = langTag, metric = metric
            )
        onForecastEventHandled(ForecastEvent.InitFiveForAdapter(fiveDaysForecast))
    }

    private suspend fun getTwelveFor(
        locationKey: String, langTag: String, metric: Boolean
    ) {
        val twelveHoursForecast = forecastUseCase.getTwelveHouFor(
            locationKey = locationKey,
            language = langTag,
            metric = metric
        )
        onForecastEventHandled(ForecastEvent.InitTwelveForAdapter(twelveHoursForecast))
    }

    private suspend fun onInitLocale() {
        langTag = mainLocale.getMainLanguageTag()
    }

    private fun getFormatLatLon(location: Location?): String {
        val lat = location?.latitude
        val lon = location?.longitude
        val pattern = DecimalFormat("#.##")
        return "${pattern.format(lat)},${pattern.format(lon)}"
    }

    internal sealed class ForecastEvent {
        object Default : ForecastEvent()
        data class InitFiveForAdapter(
            val fiveDaysFor: List<FiveDaysForecast>
        ) : ForecastEvent()
        data class InitTwelveForAdapter(
            val twelveHoursFor: List<TwelveHoursForecast>
        ) :
            ForecastEvent()
        data class InitCurrentCondition(
            val currentCondition: List<CurrentCondition>
        ) :
            ForecastEvent()
    }

    internal sealed class GeoEvent {
        data class InitGeo(val geo: Geolocation) : GeoEvent()
    }

    internal sealed class LocationEvent {
        data class InitLocation(val location: Location?) : LocationEvent()
    }
}