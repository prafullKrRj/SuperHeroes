package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.SuperHeroes
import com.example.superheroes.model.Hero
import com.example.superheroes.ui.theme.SuperHeroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val heroes = SuperHeroes.heroes
            SuperHeroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperHeroesApp(heroes = heroes)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroesApp(heroes: List<Hero>) {
    Scaffold (
        topBar = {
            SuperHeroesAppBar(title = R.string.app_name)
        }
    ){paddingValues ->
        SuperHeroesList(heroes = heroes, contentPadding = paddingValues)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SuperHeroesList(heroes: List<Hero>, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues(0.dp)) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(
                dampingRatio = DampingRatioLowBouncy
            )
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn(contentPadding = contentPadding) {
            itemsIndexed(heroes) {index, hero ->
                SuperHeroCard(modifier = Modifier.padding(horizontal = 16.dp).animateEnterExit(
                    enter = slideInVertically (
                        animationSpec = spring(
                            dampingRatio = DampingRatioLowBouncy,
                            stiffness = StiffnessVeryLow
                        ),
                        initialOffsetY = {it * (index + 1)}
                    )
                ), hero = hero)
            }
        }
    }
}
@Composable
fun SuperHeroCard(modifier: Modifier, hero: Hero) {
    Card (elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
    ){
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ){
            SuperHeroContent(modifier = Modifier
                .weight(2.8f)
                .padding(horizontal = 16.dp)
                , hero = hero)
            SuperHeroImage(modifier = Modifier
                .weight(1.2f)
                .padding(end = 16.dp), hero)
        }
    }
}

@Composable
fun SuperHeroContent(hero: Hero, modifier: Modifier = Modifier) {
    Column (modifier = modifier){
        Text(text = stringResource(id = hero.name), style = MaterialTheme.typography.displaySmall)
        Text(text = stringResource(id = hero.about), style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun SuperHeroImage(modifier: Modifier = Modifier, hero: Hero) {
    Box(modifier = modifier.clip(RoundedCornerShape(8.dp))){
        Image(painter = painterResource(id = hero.image), contentDescription = null)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroesAppBar(title: Int, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(title = {
        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
            Text(text = stringResource(id = title), style = MaterialTheme.typography.displayLarge)
        }
    })
}