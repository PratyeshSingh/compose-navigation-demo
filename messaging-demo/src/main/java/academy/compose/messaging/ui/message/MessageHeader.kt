

package academy.compose.messaging.ui.message

import academy.compose.messaging.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessageHeader(
    modifier: Modifier = Modifier,
    isToday: Boolean,
    date: Calendar
) {
    val label = if (isToday) {
        stringResource(id = R.string.label_today)
    } else {
        val dateFormat = remember { SimpleDateFormat("dd MMM yyyy") }
        dateFormat.format(date.time)
    }

    Text(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.05f
                )
            )
            .padding(vertical = 4.dp),
        text = label,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun Preview_MessageHeader() {
    MaterialTheme {
        MessageHeader(
            isToday = true,
            date = Calendar.getInstance()
        )
    }
}