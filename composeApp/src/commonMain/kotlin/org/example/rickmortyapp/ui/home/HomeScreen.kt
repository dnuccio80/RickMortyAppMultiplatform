package org.example.rickmortyapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.example.rickmortyapp.ui.core.NavBottomType
import org.example.rickmortyapp.ui.core.NavBottomType.Characters
import org.example.rickmortyapp.ui.core.NavBottomType.Episodes
import org.example.rickmortyapp.ui.core.navigation.BottomNavigationWrapper

@Composable
fun HomeScreen() {

    val bottomNavList = listOf(
        Episodes(),
        Characters()
    )
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomBar(navController, bottomNavList) }) { innerPadding ->
        Box {
            BottomNavigationWrapper(navController, innerPadding)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController, bottomNavList: List<NavBottomType>) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        bottomNavList.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = item.icon,
                label = { Text(item.title) }
            )
        }
    }
}