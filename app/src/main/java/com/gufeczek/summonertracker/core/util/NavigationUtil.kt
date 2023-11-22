package com.gufeczek.summonertracker.core.util

import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarItemView
import com.gufeczek.summonertracker.R
import com.gufeczek.summonertracker.SummonerTrackerApp

object NavigationUtil {
    private val context: FragmentActivity = checkNotNull(SummonerTrackerApp.getCurrentActivity())
    private lateinit var bottomNavigationView: BottomNavigationView

    private val activeIconSize = R.dimen.bottom_nav_item_icon_size
    private val inactiveIconSize = R.dimen.bottom_nav_item_inactive_icon_size

    fun setupNavigationAppearance(bottomNavigationView: BottomNavigationView) {
        NavigationUtil.bottomNavigationView = bottomNavigationView

        setDefaultMenuIconsAppearance()
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            setActiveMenuItem(menuItem)
            true
        }
    }

    private fun setDefaultMenuIconsAppearance() {
        val items = getMenuItems()
        val defaultItem = items[0]
        changeMenuAppearance(items, defaultItem)
    }

    private fun setActiveMenuItem(selectedMenuItem: MenuItem) {
        val items = getMenuItems()
        changeMenuAppearance(items, selectedMenuItem)
    }

    private fun changeMenuAppearance(items: List<MenuItem>, selectedMenuItem: MenuItem) {
        for (item in items) {
            if (item == selectedMenuItem) {
                setIconSize(item, size = activeIconSize)
            } else {
                setIconSize(item, size = inactiveIconSize)
            }
        }
    }

    private fun setIconSize(item: MenuItem, size: Int) {
        val iconSize = getDimensionInDp(size) ?: return

        val navigationBarItemView: NavigationBarItemView = bottomNavigationView.findViewById(item.itemId)
        val iconView: View = navigationBarItemView.findViewById(com.google.android.material.R.id.navigation_bar_item_icon_view)
        val iconViewParams: FrameLayout.LayoutParams = iconView.layoutParams as FrameLayout.LayoutParams
        iconViewParams.width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, iconSize, context.resources.displayMetrics).toInt()
        iconViewParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, iconSize, context.resources.displayMetrics).toInt()
        iconView.layoutParams = iconViewParams
    }

    private fun getMenuItems(): List<MenuItem> {
        val menu = bottomNavigationView.menu
        val menuItems = mutableListOf<MenuItem>()

        for (i in 0 until menu.size()) {
            val menuItem = menu.getItem(i)
            menuItems.add(menuItem)
        }
        return menuItems
    }
}
