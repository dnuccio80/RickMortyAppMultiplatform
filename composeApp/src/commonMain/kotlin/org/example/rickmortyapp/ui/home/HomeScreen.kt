package org.example.rickmortyapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.example.rickmortyapp.ui.core.BackgroundPrimaryColor
import org.example.rickmortyapp.ui.core.BackgroundSecondaryColor
import org.example.rickmortyapp.ui.core.BackgroundTertiaryColor
import org.example.rickmortyapp.ui.core.DefaultTextColor
import org.example.rickmortyapp.ui.core.Green
import org.example.rickmortyapp.ui.core.NavBottomType
import org.example.rickmortyapp.ui.core.NavBottomType.Characters
import org.example.rickmortyapp.ui.core.NavBottomType.Episodes
import org.example.rickmortyapp.ui.core.navigation.BottomNavigationWrapper
import org.jetbrains.compose.resources.painterResource
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.logo

@Composable
fun HomeScreen(mainNavController: NavHostController) {

    val bottomNavList = listOf(
        Episodes(),
        Characters()
    )
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController, bottomNavList) },
        topBar = { TopBar() }) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            BottomNavigationWrapper(navController, mainNavController)
        }
    }
}

@Composable
fun TopBar() {
    Box(modifier = Modifier.fillMaxWidth().background(BackgroundPrimaryColor), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(Res.drawable.logo),
            contentDescription = null,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController, bottomNavList: List<NavBottomType>) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = BackgroundSecondaryColor
    ) {
        bottomNavList.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Green,
                    unselectedIconColor = DefaultTextColor,
                    selectedTextColor = Green,
                    unselectedTextColor = DefaultTextColor,
                    indicatorColor = BackgroundTertiaryColor,
                ),
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