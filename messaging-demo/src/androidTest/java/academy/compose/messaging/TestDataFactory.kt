

package academy.compose.messaging

import academy.compose.messaging.model.Message
import academy.compose.messaging.model.MessageDirection
import java.util.*

object TestDataFactory {

    fun randomString() = UUID.randomUUID().toString()

    fun makeMessage(
        text: String? = null,
        image: Int? = null,
    ): Message {
        return Message(
            "0",
            MessageDirection.SENT,
            Calendar.getInstance().also {
                it.add(Calendar.DAY_OF_YEAR, -5)
            },
            "Joe Birch",
            message = text,
            image = image
        )
    }
}