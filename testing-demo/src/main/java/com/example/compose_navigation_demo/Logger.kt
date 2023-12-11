package com.example.compose_navigation_demo

import org.json.JSONArray
import org.json.JSONObject


// :: STEP 1
sealed interface LoggerInfo {
    data class Error(
        val errorTag: String, // Unique Identifire:: feature/class/method name
        val throwable: Throwable, // Crash exception details
        val message: String? = null, // additional message if any have

    ) : LoggerInfo

    data class Warning(
        val warningTag: String, // Unique Identifire:: feature/class/method name
        val throwable: Throwable, // Crash exception details
        val message: String? = null, // additional message if any have
    ) : LoggerInfo

    data class Info(
        val debugTag: String, // Unique Identifier:: feature/class/method name
        val message: String,
    ) : LoggerInfo

    data class TrackEvents(
        val eventMap: Map<EventsType, List<String>>
    ) : LoggerInfo
}

// :: STEP 2

interface LoggerHandler {
    fun log(loggerInfo: LoggerInfo)
}

interface UserDetailsShare {
    fun setUser() // to send user details to required sdks
    fun unsetUser() // to reset user in case of logout/session expire
}
// :: STEP 3

class Logger constructor(private val loggerHandler: LoggerHandler) {

    fun error(tag: String, throwable: Throwable, message: String? = null) {
        loggerHandler.log(LoggerInfo.Error(errorTag = tag, throwable = throwable, message = message?:throwable.message))
    }

    fun warning(tag: String, throwable: Throwable, message: String? = null) {
        loggerHandler.log(LoggerInfo.Warning(warningTag = tag, throwable = throwable, message = message?:throwable.message))
    }

    fun debug(tag: String, message: String) {
        loggerHandler.log(LoggerInfo.Info(debugTag = tag, message = message))
    }

    fun trackEvents(events: LoggerInfo.TrackEvents) {
        loggerHandler.log(events)
    }

    fun setUser() {
        if (loggerHandler is UserDetailsShare) {
            loggerHandler.setUser()
        }
    }

    fun unsetUser() {
        if (loggerHandler is UserDetailsShare) {
            loggerHandler.unsetUser()
        }
    }
}

// :: STEP 4::a
class FirebaseLogger(val crashlytics: FirebaseCrashlytics) : LoggerHandler {
    override fun log(loggerInfo: LoggerInfo) {
        when (loggerInfo) {
            is LoggerInfo.Info -> crashlytics.reportTag()
            is LoggerInfo.Error -> crashlytics.reportError()
            is LoggerInfo.Warning -> crashlytics.reportWarns()
            is LoggerInfo.TrackEvents -> {
                val jsonMapper = JSONObject()
                loggerInfo.eventMap.forEach {
                    val jsonValue = JSONArray()
                    it.value.forEach {
                        jsonValue.put(it)
                    }
                    jsonMapper.put(it.key.toString(), jsonValue)
                }
                crashlytics.logEvent(jsonMapper)
            }
        }
    }
}
//-----SDK layer-

// this should be part of firebase Sdk
object FirebaseCrashlytics {
    fun reportError() {}
    fun reportWarns() {}
    fun reportTag() {}
    fun logEvent(event: JSONObject) {}
}
//-------

// :: STEP 4::b
class ClevertapLogger(val clevertap: Clevertap) : LoggerHandler {
    override fun log(loggerInfo: LoggerInfo) {
        when (loggerInfo) {
            is LoggerInfo.Info -> clevertap.addBradCrumbs()
            is LoggerInfo.Error -> clevertap.uploadErrorStack(errorName = loggerInfo.errorTag)
            is LoggerInfo.Warning -> clevertap.reportWarns()
            is LoggerInfo.TrackEvents -> {

                loggerInfo.eventMap.forEach { key, value ->
                    key.let {

                    }
                    value.let {

                    }
                }

                clevertap.trackEvent()
            }
        }
    }
}
//-----SDK layer-

// this should be part of Clevertap Sdk
object Clevertap {
    fun uploadErrorStack(errorName: String) {}
    fun reportWarns() {}
    fun addBradCrumbs() {}

    fun trackEvent() {}
}
//-------

// :: STEP 4
// Note:- this code should be in Dagger/Hilt
object LoggerProvider {
    fun provideClevertapLogger(clevertap: Clevertap = Clevertap): Logger {
        return Logger(ClevertapLogger(clevertap = clevertap))
    }
    fun provideFirebaseLogger(firebaseCrashlytics: FirebaseCrashlytics = FirebaseCrashlytics): Logger {
        return Logger(FirebaseLogger(firebaseCrashlytics))
    }
}

// :: STEP 5

sealed class EventsType(val event: String) {
    object Name : EventsType("name")
    object View : EventsType("view")
    object Click : EventsType("click")
}

class AnalyticsManager private constructor(private vararg val logger: Logger) {
    companion object {
        fun getInstance(vararg logger: Logger) = AnalyticsManager(logger = logger)
    }

    private fun logEvents(handler: Logger.() -> Unit) {
        logger.forEach {
            with(it) {
                handler()
            }
        }
    }

    fun trackErrorDetails(tag: String, throwable: Throwable, message: String? = null) {
        logEvents {
            error(tag, throwable, message)
        }
    }

    fun viewHomeFeedEvents() {
        logEvents {
            val eventMap = mutableMapOf<EventsType, List<String>>()
            eventMap[EventsType.View] = listOf("HomeFeed-View")
            trackEvents(LoggerInfo.TrackEvents(eventMap))
        }
    }
}

// :: Step 6 :: validate all above


class ValidatorViewModel /*@Inject constructor*/(
    /*@FirebaseQualifier*/ val firebaseLogger:Logger = LoggerProvider.provideFirebaseLogger(),
    /*@ClevertapQualifier*/ val clevertapLogger: Logger = LoggerProvider.provideClevertapLogger()
) {

    private val analyticsManager by lazy {
        AnalyticsManager.getInstance(firebaseLogger, clevertapLogger)
    }

    fun checkLog() {
        firebaseLogger.debug("VALIDATORVIEWMODEL", "testing logs")
        firebaseLogger.warning("VALIDATORVIEWMODEL", Exception("checking"))
        firebaseLogger.warning("VALIDATORVIEWMODEL", Exception("checking"), "message checking")
        firebaseLogger.error("VALIDATORVIEWMODEL", Exception("checking"))
        firebaseLogger.error("VALIDATORVIEWMODEL", Exception("checking"), "message checking")

        // Optional
        analyticsManager.trackErrorDetails("VALIDATORVIEWMODEL", Exception("checking"), "message checking")
        analyticsManager.viewHomeFeedEvents()
    }
}
