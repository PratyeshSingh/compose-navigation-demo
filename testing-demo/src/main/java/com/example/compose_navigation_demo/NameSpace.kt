package com.example.compose_navigation_demo


class ProvideNamespace(private val n: LoggingNamespace) :
    NamespacedComponent {
    override fun namespace() = n
}


interface NamespacedComponent {
    fun namespace(): LoggingNamespace
}

sealed class LoggingNamespace(override val namespace: String) : BaseLoggingNamespace {
    override fun toString(): String = namespace
    object AppInit : LoggingNamespace("APPLICATION_INITIALIZATION")

    object DemoActivity : LoggingNamespace("DEMO_ACTIVITY")
}

interface BaseLoggingNamespace {
    val namespace: String
}
