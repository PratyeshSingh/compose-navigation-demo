package com.example.compose_navigation_demo

//Case: 1
interface Printable {
    fun prettyPrint(): String
}

@JvmInline
value class Password(private val secret: String) : Printable {
    init {
        require(secret.isNotEmpty() && secret.length > 10) {
            "password length should be > 10 , Current is :: ${secret.length}"
        }
    }

    override fun prettyPrint(): String = "Let's $secret!"
}

fun main() {
    val password = Password("Kotlin Learn")
//    val password = Password("")
    println(password.prettyPrint())

    compute(UserId("abc"))
}

//// Case:-2
@JvmInline
value class UserId<T>(val value: T)

fun compute(s: UserId<String>) {

}

// Case :3

interface I

@JvmInline
value class Foo(val i: Int) : I

fun asInline(f: Foo) {}
fun <T> asGeneric(x: T) {}
fun asInterface(i: I) {}
fun asNullable(i: Foo?) {}

fun <T> id(x: T): T = x

fun main123() {
    val f = Foo(42)

    asInline(f)    // unboxed: used as Foo itself
    asGeneric(f)   // boxed: used as generic type T
    asInterface(f) // boxed: used as type I
    asNullable(f)  // boxed: used as Foo?, which is different from Foo

    // below, 'f' first is boxed (while being passed to 'id') and then unboxed (when returned from 'id')
    // In the end, 'c' contains unboxed representation (just '42'), as 'f'
    val c = id(f)
}

// Case :4
interface MyInterface {
    fun bar()
    fun foo() = "foo"
}

@JvmInline
value class MyInterfaceWrapper(val myInterface: MyInterface) : MyInterface by myInterface

fun main1() {
    val my = MyInterfaceWrapper(object : MyInterface {
        override fun bar() {
            // body
        }
    })
    println(my.foo()) // prints "foo"
}
