package org.example.rickmortyapp.ui.core

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.vectorResource
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.ic_home
import rickmortyapp.composeapp.generated.resources.ic_person

sealed class NavBottomBarItem {

    abstract val title:String
    abstract val route:String
    abstract val icon: @Composable () -> Unit

    data class Episodes(
        override val title: String = "Episodes",
        override val route: String = Routes.Episodes.route,
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = vectorResource(Res.drawable.ic_home), contentDescription = "")
        }
    ):NavBottomBarItem()

    data class Characters(
        override val title: String = "Characters",
        override val route: String = Routes.Characters.route,
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = vectorResource(Res.drawable.ic_person), contentDescription = "")
        }
    ):NavBottomBarItem()

}