package academy.compose.gallery

import academy.compose.gallery.ui.Gallery
import academy.compose.gallery.ui.theme.PracticalJetpackComposeTheme
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable

@ExperimentalFoundationApi
@Composable
fun GalleryScreen() {
    PracticalJetpackComposeTheme {
        Gallery()
    }
}