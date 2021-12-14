package com.dacruzl2.marvel.character.list.presentation

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dacruzl2.marvel.character.list.R
import com.dacruzl2.marvel.character.list.domain.model.DomainCharacter
import com.dacruzl2.marvel.character.list.domain.model.DomainImage
import com.dacruzl2.marvel.character.list.domain.model.DomainUrl
import com.dacruzl2.marvel.character.list.theme.MarvelTheme
import com.skydoves.landscapist.coil.CoilImage
import org.koin.androidx.compose.getViewModel

@Composable
internal fun CharactersSection(onUpPress: () -> Unit) {
    CharactersLoader(onUpPress = onUpPress)
}

@Suppress("UndocumentedPublicFunction")
@Preview
@Composable
fun MarvelTollbarPreview() {
    MarvelTheme {
        MarvelToolbar(onUpPress = {})
    }
}

@Composable
fun MarvelToolbar(onUpPress: () -> Unit) {
    TopAppBar(
        backgroundColor = Color.Red,
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Marvel",
                    fontSize = 30.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )

                Row {
                    IconButton(onClick = onUpPress, modifier = Modifier.align(Alignment.CenterVertically)) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "",
                            tint = Color.White,
                        )
                    }
                }
            }
        }
    )
}

@Composable
internal fun CharactersLoader(viewModel: CharactersListViewModel = getViewModel(), onUpPress: () -> Unit) {
    val data by remember { viewModel.getCharacters() }
        .collectAsState(initial = CharacterListViewState.Loading)

    Scaffold(topBar = { MarvelToolbar(onUpPress = onUpPress) }) {
        Crossfade(data) { state ->
            when (state) {
                CharacterListViewState.Loading -> LoadingContent()
                is CharacterListViewState.Error -> CharacterError(error = state.cause)
                is CharacterListViewState.Loaded -> CharactersList(list = state.value)
                CharacterListViewState.Empty -> Unit
            }
        }
    }
}

/**
 * Basic loading screen to be used when the screen is loading, making the transition smoother.
 */
@Composable
fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), content = {})
}

@Composable
private fun CharacterError(error: Throwable) {

    val (image, message) = ErrorStateResources(error = error)

    DefaultIconTextContent(
        icon = image,
        modifier = Modifier.padding(16.dp),
        header = message,
        iconContentDescription = message
    )
}

@Composable
fun DefaultIconTextContent(
    icon: ImageVector,
    @StringRes iconContentDescription: Int,
    @StringRes header: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = iconContentDescription),
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = header),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSecondary,
        )
    }
}

@Composable
fun CharactersList(list: List<DomainCharacter>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(list) { item ->
            CharacterItem(item)
        }
    }
}

@Composable
private fun CharacterItem(item: DomainCharacter, modifier: Modifier = Modifier) {
    Box {
        item.thumbnail?.let { NetworkImage(url = it) }
        Text(
            text = item.name ?: "",
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier
                .align(
                    if (item.thumbnail?.path?.contains("image_not_available") == true) {
                        Alignment.Center
                    } else Alignment.BottomStart
                )
                .padding(16.dp)
        )
    }
}

@Suppress("UndocumentedPublicFunction")
@Composable
fun CharacterItemPreview() {
    MarvelTheme {
        val item = DomainCharacter(
            name = "example1",
            comics = null,
            description = "example2",
            events = null,
            id = 0,
            series = null,
            stories = null,
            thumbnail = null,
            urls = listOf<DomainUrl>(),
            modified = "",
            resourceURI = ""
        )

        CharacterItem(item)
    }
}

@Composable
fun NetworkImage(
    url: DomainImage
) {
    CoilImage(
        imageModel = url(),
        previewPlaceholder = R.drawable.poster
    )
}