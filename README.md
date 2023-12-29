# compose-navigation-demo
    - implementation(libraries.androidXNavigation)

# Cryptography
    - String value
    - DataStore data
    - SQLite DB
# Features (With Compose test case coverage)
    - Gallery access with permisson handling
    - Inbox (Email UI demo)
    - Messaging (Chat UI)
    - Setting (App, Theam etc)
    - Video (Exoplayer sdk demo)
    - Record Video & Capture Image demo
    - Musice app UI demo
    - Simple UI with internal navigation along with deeplink (Details + About option demo)

# Deeplink 
    - ADB command line (same can be config/utilise for http as well as inapp navigation)
    
        1.  I/P=> adb shell am start -W -a android.intent.action.VIEW -d "example://about_screen/'Hi Pratyesh is making Demo app to verify deeplink property'?name=Android\&app=compose"
            O/P=> intent.data() :: example://about_screen/Hi Pratyesh is making Demo app to verify deeplink property?name=Android&app=compose
              intent.data.query() :: name=Android&app=compose
              myIntent.data() :: example://about_screen/Hi Pratyesh is making Demo app to verify deeplink property?name=Android&app=compose
              myIntent.data.query() :: name=Android&app=compose
            
            # with nullable support
            I/P=> adb shell am start -W -a android.intent.action.VIEW -d "example://about_screen/"
              
              
        2. I/P=> adb shell am start -W -a android.intent.action.VIEW -d "example://detail_screen/'Pratyesh cooming via Deeplink'"
                 # below will not work as it is not nullable param 
                 adb shell am start -W -a android.intent.action.VIEW -d "example://detail_screen" 
