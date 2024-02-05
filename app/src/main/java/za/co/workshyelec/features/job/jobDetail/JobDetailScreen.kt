package za.co.workshyelec.features.job.jobDetail

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import za.co.workshyelec.composables.BaseScreen
import za.co.workshyelec.composables.CircularLoadingIndicator
import za.co.workshyelec.core.common.UiState
import za.co.workshyelec.core.common.handle
import za.co.workshyelec.core.navigation.NavigationHandlerImpl
import za.co.workshyelec.features.job.composables.JobDetailBottomBar
import za.co.workshyelec.features.job.composables.JobDetailTopBar
import za.co.workshyelec.features.job.models.Job

data class JobDetailScreenArgs(
    val jobId: String
)

@Destination(
    navArgsDelegate = JobDetailScreenArgs::class
)
@Composable
fun JobDetailScreen(
    navController: NavController,
    jobDetailScreenArgs: JobDetailScreenArgs,
    jobDetailViewModel: JobDetailViewModel = koinViewModel { parametersOf(jobDetailScreenArgs.jobId) }
) {
    val navigationHandler = remember { NavigationHandlerImpl(navController) }

    val jobDetailState by jobDetailViewModel.jobDetail.collectAsState()

    BaseScreen(
        navController = navController,
        topBar = {
            JobDetailTopBar(
                jobId = jobDetailScreenArgs.jobId,
                onBackClick = { navigationHandler.goBack() }
            )
        },
        bottomBar = { JobDetailBottomBar() }
    ) {
        JobDetailScreenContent(state = jobDetailState)
    }
}

@Composable
fun JobDetailScreenContent(state: UiState<Job>) {
    val scrollState = rememberScrollState()

    state.handle(
        onNone = { Text(text = "No job detail") },
        onLoading = { CircularLoadingIndicator() },
        onError = { errorResponse, _ ->
            Text(text = errorResponse.message)
        },
    ) { job ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Job name
            Text(
                text = job.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
            )

            Spacer(modifier = Modifier.padding(4.dp))

            // Job location
            Text(
                text = job.location,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollState)
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Button 1")
                }

                Spacer(modifier = Modifier.padding(4.dp))

                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Button 2")
                }

                Spacer(modifier = Modifier.padding(4.dp))

                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Button 3")
                }

                Spacer(modifier = Modifier.padding(4.dp))

                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Button 4")
                }

                Spacer(modifier = Modifier.padding(4.dp))

                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Button 5")
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = "Work Detail",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
            )
        }
    }
}