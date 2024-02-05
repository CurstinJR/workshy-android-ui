package za.co.workshyelec.core.common

import za.co.workshyelec.core.api.ApiErrorResponse

/**
 * A sealed class representing different states of a UI component.
 * This class is generic and can hold any type of data.
 *
 * @param T The type of data this UI state can hold.
 */
sealed class UiState<out T> {

    /**
     * Represents a state where no data is available.
     */
    data object None : UiState<Nothing>()

    /**
     * Represents a successful state with data.
     *
     * @param data The data associated with the successful state.
     */
    data class Success<T>(val data: T) : UiState<T>()

    /**
     * Represents a loading state, optionally with progress information.
     *
     * @param progress The progress of the loading state, if available.
     */
    data class Loading(val progress: Int? = null) : UiState<Nothing>()

    /**
     * Represents an error state with a message and an optional exception.
     *
     * @param errorResponse The error message.
     * @param exception The exception associated with the error, if available.
     */
    data class Error(val errorResponse: ApiErrorResponse, val exception: Exception? = null) :
        UiState<Nothing>()

    /**
     * Represents a state where the data is empty.
     */
    data object Empty : UiState<Nothing>()
}

/**
 * Extension function for the UiState class to handle different states.
 *
 * @param T The type of data this UI state can hold.
 * @param onNone Lambda function to be executed when the state is None.
 * @param onEmpty Lambda function to be executed when the state is Empty.
 * @param onLoading Lambda function to be executed when the state is Loading. It takes the progress as a parameter.
 * @param onError Lambda function to be executed when the state is Error. It takes the error message and exception as parameters.
 * @param onSuccess Lambda function to be executed when the state is Success. It takes the data as a parameter.
 */
inline fun <T> UiState<T>.handle(
    onNone: () -> Unit = {},
    onEmpty: () -> Unit = {},
    onLoading: (Int?) -> Unit = {},
    onError: (ApiErrorResponse, Exception?) -> Unit = { _, _ -> },
    onSuccess: (T) -> Unit = {},
) {
    when (this) {
        is UiState.None -> onNone()
        is UiState.Empty -> onEmpty()
        is UiState.Loading -> onLoading(progress)
        is UiState.Error -> onError(errorResponse, exception)
        is UiState.Success -> onSuccess(data)
    }
}