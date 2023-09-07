

package academy.compose.messaging

import academy.compose.messaging.model.Contact

object ContactFactory {

    fun makeContacts(): List<Contact> {
        return listOf(
            Contact("Joe"),
            Contact("Ellie"),
            Contact("Anna"),
            Contact("Rachel"),
            Contact("Ross"),
            Contact("Mark"),
            Contact("Jake")
        )
    }

}