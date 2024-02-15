package za.co.workshyelec.features.job.jobDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import za.co.workshyelec.composables.WSCircularLoadingIndicator
import za.co.workshyelec.core.common.UiState
import za.co.workshyelec.features.job.composables.JobEmployeeCard
import za.co.workshyelec.features.job.models.Employee

@Composable
fun JobEmployeeListView(
    employeeListState: UiState<List<Employee>>
) {
    Column(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        employeeListState.let { state ->
            when (state) {
                is UiState.Loading -> {
                    WSCircularLoadingIndicator()
                }

                is UiState.Success -> {
                    Column {
                        Text(
                            text = "Members",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        state.data.forEach { employee ->
                            JobEmployeeCard(employee = employee)
                        }
                    }
                }

                is UiState.Error -> {
                    Text(text = state.errorResponse.message, modifier = Modifier.padding(16.dp))
                }

                else -> {
                    Text(text = "No job employees", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}