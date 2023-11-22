package com.gufeczek.summonertracker

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.HiltAndroidApp
import java.lang.ref.WeakReference

@HiltAndroidApp
class SummonerTrackerApp : Application() {
    companion object {
        private var currentActivityRef: WeakReference<FragmentActivity>? = null

        fun getCurrentActivity(): FragmentActivity? {
            return currentActivityRef?.get()
        }
        private fun setCurrentActivity(activity: Activity) {
            currentActivityRef = WeakReference(activity as FragmentActivity)
        }
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                setCurrentActivity(activity)
            }
            override fun onActivityStarted(activity: Activity) {
                val current = currentActivityRef?.get()
                if (current == activity) {
                    currentActivityRef = null
                }
            }

            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, savedInstanceState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
    }
}
