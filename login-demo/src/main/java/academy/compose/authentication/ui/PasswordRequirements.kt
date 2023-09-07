

package academy.compose.authentication.ui

import academy.compose.authentication.model.PasswordRequirement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PasswordRequirements(
    modifier: Modifier = Modifier,
    satisfiedRequirements: List<PasswordRequirement>
) {
    Column(modifier = modifier) {
        PasswordRequirement.values().forEach { requirement ->
            Requirement(
                message = stringResource(id = requirement.label),
                satisfied = satisfiedRequirements.contains(requirement)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_PasswordRequirements() {
    MaterialTheme {
        PasswordRequirements(
            modifier = Modifier.fillMaxWidth(),
            satisfiedRequirements = listOf(PasswordRequirement.CAPITAL_LETTER)
        )
    }
}