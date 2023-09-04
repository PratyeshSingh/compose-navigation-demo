package com.example.compose_navigation_demo


sealed class HomeAction {
    data class AddToCart(val itemId: String) : HomeAction()
    object LoadMore : HomeAction()
}

typealias HomeClickHandler = (HomeAction) -> Unit


annotation class Token



