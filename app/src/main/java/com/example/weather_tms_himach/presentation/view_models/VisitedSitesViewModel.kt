package com.example.weather_tms_himach.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_tms_himach.domain.models.VisitedCityStatistics
import com.example.weather_tms_himach.domain.use_cases.VisitedSitesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class VisitedSitesViewModel @Inject constructor(
    private val visitedSitesUseCase: VisitedSitesUseCase
) : ViewModel() {
    private val statisticsEvent: MutableStateFlow<StatisticsEvent> =
        MutableStateFlow(value = StatisticsEvent.Default)

    internal fun getVisitedSites(): Flow<StatisticsEvent> = statisticsEvent

    internal fun uploadVisitedSites() =
        viewModelScope.launch { getAllStatistics() }

    internal suspend fun deleteAllStatistics() = //TODO
        visitedSitesUseCase.onDeleteAllStatistics()

    internal suspend fun deleteParamById(id: Long) = //TODO
        visitedSitesUseCase.onDeleteStatisticsParamById(id = id)


    private fun onStatisticsEventHandled(event: StatisticsEvent) =
        viewModelScope.launch { statisticsEvent.emit(event) }

    private suspend fun getAllStatistics() {
        val allVisitedSites = visitedSitesUseCase.getVisitedSitesStatistics()
        onStatisticsEventHandled(StatisticsEvent.InitStatistics(allVisitedSites))
    }

    internal sealed class StatisticsEvent {
        object Default : StatisticsEvent()
        data class InitStatistics(
            val statistics: List<VisitedCityStatistics>
        ) : StatisticsEvent()
    }
}