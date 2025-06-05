package navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.core.presentation.theme.bottomBar
import com.example.core.presentation.theme.darkenRed
import com.example.core.presentation.theme.primaryWhite
import com.example.navigation.tabs.FavoritesTab
import com.example.navigation.tabs.HomeTab
import com.example.navigation.tabs.SearchTab

@Composable
fun Navigation() {
    TabNavigator(HomeTab) {
        Scaffold(bottomBar = {
            BottomNavigation {
                TabNavigationItem(HomeTab)
                TabNavigationItem(SearchTab)
                TabNavigationItem(FavoritesTab)
            }
        }) { padding ->
            Column(Modifier.padding(padding)) {
                CurrentTab()
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = tab.options.title,
                    modifier = Modifier.size(22.dp)
                )
            }
        },
        modifier = Modifier.background(bottomBar).padding(bottom = 30.dp),
        selectedContentColor = darkenRed,
        unselectedContentColor = primaryWhite.copy(0.5F),
        label = {
            Text(
                text = tab.options.title, fontWeight = FontWeight.SemiBold, fontSize = 10.sp
            )
        })
}