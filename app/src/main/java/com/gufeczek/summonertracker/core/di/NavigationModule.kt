package com.gufeczek.summonertracker.core.di

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gufeczek.summonertracker.R
import com.gufeczek.summonertracker.core.ui.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
    @Singleton
    fun provideNavHostFragment(@ActivityContext context: MainActivity): NavHostFragment {
        return context.supportFragmentManager.findFragmentById(R.id.mainNavFragment) as NavHostFragment
    }

    @Provides
    @Singleton
    fun provideNavController(navHostFragment: NavHostFragment): NavController {
        return navHostFragment.navController
    }
}
