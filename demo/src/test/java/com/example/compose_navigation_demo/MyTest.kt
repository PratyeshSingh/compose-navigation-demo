package com.example.compose_navigation_demo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

data class MyTest(
    val data :Int
){
    var isEnable:Boolean =false
}

fun main() {
    println("Hello")

    val temp = MyTest(2)

    temp.apply {
        isEnable = true
    }
    println(temp)
    println(temp.isEnable)

//    val squareLambda: (Int) -> Int = {
//        it * it
//    }
//
//    println("Result : " + squareLambda(2))
//
//    callAction(squareLambda)
//
//    val value = listOf<Any>("abcd", 1, 3, 6).filterSomething<Int>()
//    println(value)
}

fun callAction(someAction: (Int) -> Int) {
    println("inside callAction :: ${someAction(2)}")
}

//This can use with JSON parsing (GSON or MOSHI also with custom logic)
inline fun <reified T> List<Any>.filterSomething() : List<T> {
    return this.filter { it is T }.map { it as T }
}



/*


Showing item in particular location in list/screen
Solid principle

Person detail :- name /sex/ Age Marital
Location detail:- City , state , Nationality Passport



class Person{
name
}



Show Hide
Interface Segre Prin :-

NationalityManager{
fun Nationality()
}

PassportManager{
fun Passport()
}


Location {
 fun citi() : String
 fun state() : String
}

LocationEligibility{
fun show(): Boolean
}


DefaultScreen: Location, LocationEligibility, {
}




Liskiov Substib prin:-

NationalityManager{
fun Nationality()
}
PassportManager{
fun Passport()
}

Location {
 fun citi() : String
 fun state() : String
}

DefaultLocation,Location,NationalityManager,PassportManager  {
fun citi() :  = Bglr
fun state() :  = KA
fun Nationality() == IN
fun Passport() == Yes
}

USALocation,Location,NationalityManager {
fun citi() :  = Bglr
 fun state() :  = KA
fun Nationality() == IN
}

 */


/*


// NotificationDetails == POJO
// appLaunchDate // Long
// skinAiUnlockUseCase : ManagerIPLML == FakeImaple:::SkinAiUnlockUseCase


List<NotificationDetails> getExclusiveUnlockNotificationDetailsList(long appLaunchDate) {
    final List<NotificationDetails> notificationDetailsList = new ArrayList<>();

    if (!skinAiUnlockUseCase.enableExclusiveUnlockNotification() &&
        skinAiUnlockUseCase.isDevicePremium()) {

        long startTimeTenDays = appLaunchDate + NotificationUtils.NOTIFICATION_INTERVAL_TEN_DAYS;
        long startTimeTwentyDays = appLaunchDate + NotificationUtils.NOTIFICATION_INTERVAL_TWENTY_DAYS;
        final List<Long> notificationDates = new ArrayList<>();
        notificationDates.add(startTimeTenDays);
        notificationDates.add(startTimeTwentyDays);
        setExclusiveUnlockNotificationDetailList(notificationDates, notificationDetailsList);


    }

    return notificationDetailsList;
}

*/
