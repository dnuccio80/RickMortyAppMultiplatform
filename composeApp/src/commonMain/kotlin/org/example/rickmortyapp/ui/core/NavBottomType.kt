package org.example.rickmortyapp.ui.core

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.painterResource
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.ic_home
import rickmortyapp.composeapp.generated.resources.ic_person

sealed class NavBottomType() {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data class Episodes(
        override val route: String = Routes.Episodes.route,
        override val title: String = "Episodes",
        override val icon: @Composable () -> Unit = {
            Icon(
                painter = painterResource(Res.drawable.ic_home),
                contentDescription = ""
            )
        }
    ) : NavBottomType()

    data class Characters(
        override val route: String = Routes.Characters.route,
        override val title: String = "Characters",
        override val icon: @Composable () -> Unit = {
            Icon(
                painter = painterResource(Res.drawable.ic_person),
                contentDescription = ""
            )
        }
    ) : NavBottomType()

}
