package academy.compose.gallery.ui

import academy.compose.gallery.model.Image
import academy.compose.gallery.retrieveMedia
import academy.compose.gallery.ui.theme.PracticalJetpackComposeTheme
import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/***
 *
 * setContent {
 *             PracticalJetpackComposeTheme {
 *                 Gallery()
 *             }
 *         }
 * **/

@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalFoundationApi
@Composable
fun Gallery() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var retrievedMedia by remember { mutableStateOf<List<Image>?>(null) }
    val permissionState = rememberPermissionState(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        Manifest.permission.READ_MEDIA_IMAGES
        else Manifest.permission.READ_EXTERNAL_STORAGE,
    )
    LaunchedEffect(key1 = permissionState.hasPermission) {
        if (permissionState.hasPermission) {
            scope.launch(Dispatchers.IO) {
                val retrieveMedia = retrieveMedia(context)
                withContext(Dispatchers.Main) {
                    retrievedMedia = retrieveMedia
                }
            }
        }
    }
    MaterialTheme {
        GalleryContent(
            modifier = Modifier.fillMaxSize(),
            media = retrievedMedia,
            permissionState = permissionState,
            openSettings = {
                context.startActivity(
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:${context.packageName}")
                    )
                )
            }
        )
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun Preview_Gallery() {
    PracticalJetpackComposeTheme {
        Gallery()
    }
}
