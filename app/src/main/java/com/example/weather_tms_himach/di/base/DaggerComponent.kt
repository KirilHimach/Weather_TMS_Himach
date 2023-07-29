package com.example.weather_tms_himach.di.base

import android.app.Application
import com.example.weather_tms_himach.di.modules.UseCasesModule
import com.example.weather_tms_himach.di.modules.ViewModelModule
import com.example.weather_tms_himach.di.modules.repositories.DataRepoModule
import com.example.weather_tms_himach.di.modules.repositories.LocalRepoModule
import com.example.weather_tms_himach.di.modules.repositories.RemRepoModule
import com.example.weather_tms_himach.presentation.fragments.ForecastFragment
import com.example.weather_tms_himach.presentation.fragments.SignUpFragment
import com.example.weather_tms_himach.presentation.fragments.SignInFragment
import com.example.weather_tms_himach.presentation.fragments.VisitedSitesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        DBModule::class,
        DataRepoModule::class,
        RemRepoModule::class,
        LocalRepoModule::class,
        UseCasesModule::class,
        ViewModelModule::class,
        AuthModule::class,
        LocationModule::class,
        ResourcesModule::class
    ]
)
internal interface DaggerComponent {
    fun inject(fragment: SignUpFragment)
    fun inject(fragment: SignInFragment)
    fun inject(fragment: ForecastFragment)
    fun inject(fragment: VisitedSitesFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): DaggerComponent
    }
}