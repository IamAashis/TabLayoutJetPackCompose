package com.jetpack.tablayoutjetpaccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import com.jetpack.tablayoutjetpaccompose.ui.theme.TabLayoutJetPacComposeTheme
import com.jetpack.tablayoutjetpaccompose.ui.theme.blue
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabLayoutJetPacComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainContent() {

    val list = listOf(TabItem.Home, TabItem.Cart, TabItem.Profile)
    val pagerState = rememberPagerState(initialPage = 0)

    Column(modifier = Modifier.fillMaxSize()) {
        Tabs(tabs = list, pagerState = pagerState)
        TabContent(tabs = list, pagerState = pagerState)
    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = blue,
        indicator = { tabPositions ->
            Modifier.pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions)
        }) {
        tabs.forEachIndexed { index, tabItem ->

            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(tabItem.title) },
                icon = { Icon(imageVector = tabItem.icons, contentDescription = null) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.White,
                enabled = true
            )
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screens()
    }
}


@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "This is Home Screen", fontSize = 25.sp, color = Color.White
        )
    }
}


@Composable
fun CartScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cart Screen", fontSize = 25.sp, color = Color.White)
    }
}


@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Screen", fontSize = 25.sp, color = Color.White)
    }
}