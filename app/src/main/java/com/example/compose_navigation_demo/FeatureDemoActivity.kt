package com.example.compose_navigation_demo

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.compose_navigation_demo.component.features.FeatureNavigation

const val TAG = "DEEP_LINK"

class FeatureDemoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deepLinkChecker()
        setContent {
            FeatureNavigation()
        }
    }


    /**
     *
     *I/P=> adb shell am start -W -a android.intent.action.VIEW -d "example://about_screen/'Hi Pratyesh is making Demo app to verify deeplink property'?name=Android\&app=compose"
     *
     *O/P=> intent.data() :: example://about_screen/Hi Pratyesh is making Demo app to verify deeplink property?name=Android&app=compose
     *      intent.data.query() :: name=Android&app=compose
     *      myIntent.data() :: example://about_screen/Hi Pratyesh is making Demo app to verify deeplink property?name=Android&app=compose
     *      myIntent.data.query() :: name=Android&app=compose
     * */
    private fun deepLinkChecker() {
        val deepLinkDataChecker = StringBuilder()
        deepLinkDataChecker.append("\n intent.data() :: ${intent.data}")
        deepLinkDataChecker.append("\n intent.data.query() :: ${intent.data?.query}")

        val myIntent = Intent().apply {
            data =
                Uri.parse("example://about_screen/Hi Pratyesh is making Demo app to verify deeplink property?name=Android&app=compose")
            action = "android.intent.action.VIEW"
            component = ComponentName(
                "com.example.compose_navigation_demo",
                "com.example.compose_navigation_demo.FeatureDemoActivity"
            )
        }
        deepLinkDataChecker.append("\n myIntent.data() :: ${myIntent.data}")
        deepLinkDataChecker.append("\n myIntent.data.query() :: ${myIntent.data?.query}")

        Log.d(TAG, deepLinkDataChecker.toString())
    }
}
