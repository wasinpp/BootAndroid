package com.boot.pagingcompose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.boot.pagingcompose.R
import com.boot.pagingcompose.domain.Movie
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun MovieItem(movie: Movie) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MovieTitle(
            movie.title ?: "NO",
        )
        MovieImage(
            movie.imgURL,
            modifier = Modifier.padding(start = 16.dp).preferredSize(90.dp)
        )
    }
}

@Composable
fun MovieImage(
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    when (imageUrl) {
        null -> Text("No Image Display")
        else -> CoilImage(
            data = imageUrl,
            modifier = modifier,
            fadeIn = true,
            contentScale = ContentScale.Crop,
            loading = {
//                Box(Modifier.fillMaxSize()) {
//                    CircularProgressIndicator(Modifier.align(Alignment.Center))
//                }
                Text("loading")
            },
            error = {
                val deferredImage = loadImageResource(
                    id = R.drawable.ic_error,
                )

                deferredImage.resource.resource?.let {
                    Image(it, alpha = 0.45f)
                }
            }
        )
    }

}

@Composable
fun MovieTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 2,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis
    )
}