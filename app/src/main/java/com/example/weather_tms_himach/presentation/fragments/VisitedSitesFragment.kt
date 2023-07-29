package com.example.weather_tms_himach.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_tms_himach.databinding.FragmentVisitedSitesBinding
import com.example.weather_tms_himach.di.base.DaggerDaggerComponent
import com.example.weather_tms_himach.di.modules.ViewModelFactory
import com.example.weather_tms_himach.domain.models.VisitedCityStatistics
import com.example.weather_tms_himach.presentation.activity.BaseActivity
import com.example.weather_tms_himach.presentation.adapters.VisitedSitesAdapter
import com.example.weather_tms_himach.presentation.view_models.VisitedSitesViewModel
import com.example.weather_tms_himach.utils.observeWithLifecycle
import java.lang.IllegalStateException
import javax.inject.Inject

internal class VisitedSitesFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private var _visitedCitesViewModel: VisitedSitesViewModel? = null
    private val visitedCitesViewModel: VisitedSitesViewModel
        get() = _visitedCitesViewModel ?: throw IllegalStateException("VisitedSitesViewModel is not found")
    private lateinit var binding: FragmentVisitedSitesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.application?.let {
            DaggerDaggerComponent.factory().create(it).inject(this)
        }
        _visitedCitesViewModel = viewModelFactory.create(VisitedSitesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVisitedSitesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onObserveSites()
        onObserveStatisticsEvent()
    }

    override fun onResume() {
        super.onResume()
        val baseActivity = activity as BaseActivity
        baseActivity.setBottomNavViewVisibility(View.VISIBLE)
    }

    private fun onObserveSites() =
        visitedCitesViewModel.uploadVisitedSites()

    private fun onObserveStatisticsEvent() =
        visitedCitesViewModel.getVisitedSites().observeWithLifecycle(
            fragment = this@VisitedSitesFragment,
            action = ::handleStatisticsEvent
        )

    private fun handleStatisticsEvent(event: VisitedSitesViewModel.StatisticsEvent) {
        when(event) {
            is VisitedSitesViewModel.StatisticsEvent.Default -> return
            is VisitedSitesViewModel.StatisticsEvent.InitStatistics -> {
                onCreateStatisticsAdapter(event.statistics)
            }
        }
    }

    private fun onCreateStatisticsAdapter(statistics: List<VisitedCityStatistics>) {
        binding.lastVisitedSitesRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = VisitedSitesAdapter(
                items = statistics,
                context = context
            )
        }
    }
}