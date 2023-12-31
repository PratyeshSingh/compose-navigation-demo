package academy.compose.gallery.ui

import academy.compose.gallery.Tags.TAG_DENIED_PERMISSION
import academy.compose.gallery.Tags.TAG_IMAGE_GALLERY
import academy.compose.gallery.Tags.TAG_PROGRESS
import academy.compose.gallery.model.Image
import academy.compose.gallery.ui.theme.PracticalJetpackComposeTheme
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@Composable
fun GalleryContent(
    modifier: Modifier = Modifier,
    media: List<Image>? = null,
    permissionState: PermissionState,
    openSettings: () -> Unit
) {
    when {
        permissionState.hasPermission -> {
            if (media == null) {
                Box(
                    modifier = modifier.testTag(TAG_PROGRESS),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                ImageGallery(
                    modifier = modifier.testTag(TAG_IMAGE_GALLERY),
                    images = media
                )
            }
        }
        !permissionState.permissionRequested -> {
            PermissionExplainer(
                modifier = Modifier.fillMaxSize()
            ) {
                permissionState.launchPermissionRequest()
            }
        }
        else -> {
            DeniedPermission(
                modifier = modifier.testTag(TAG_DENIED_PERMISSION),
                handleLaunchSettings = {
                    openSettings()
                }
            )
        }
    }
}

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Preview
@Composable
fun Preview_GalleryContent_Loading() {
    PracticalJetpackComposeTheme {
        GalleryContent(
            modifier = Modifier.fillMaxSize(),
            media = null,
            permissionState = object : PermissionState {
                override val hasPermission: Boolean
                    get() = true
                override val permission: String
                    get() = ""
                override val permissionRequested: Boolean
                    get() = false
                override val shouldShowRationale: Boolean
                    get() = false

                override fun launchPermissionRequest() { }
            },
            openSettings = { })
    }
}

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Preview
@Composable
fun Preview_GalleryContent_Images() {
    PracticalJetpackComposeTheme {
        GalleryContent(
            modifier = Modifier.fillMaxSize(),
            media = listOf(Image(id = 0L, uri = Uri.EMPTY, name = "image")),
            permissionState = object : PermissionState {
                override val hasPermission: Boolean
                    get() = true
                override val permission: String
                    get() = ""
                override val permissionRequested: Boolean
                    get() = false
                override val shouldShowRationale: Boolean
                    get() = false

                override fun launchPermissionRequest() { }
            },
            openSettings = { })
    }
}

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Preview
@Composable
fun Preview_GalleryContent_Denied() {
    PracticalJetpackComposeTheme {
        GalleryContent(
            modifier = Modifier.fillMaxSize(),
            media = listOf(Image(id = 0L, uri = Uri.EMPTY, name = "image")),
            permissionState = object : PermissionState {
                override val hasPermission: Boolean
                    get() = false
                override val permission: String
                    get() = ""
                override val permissionRequested: Boolean
                    get() = true
                override val shouldShowRationale: Boolean
                    get() = false

                override fun launchPermissionRequest() { }
            },
            openSettings = { })
    }
}