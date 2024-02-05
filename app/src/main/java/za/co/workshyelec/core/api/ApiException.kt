package za.co.workshyelec.core.api

class ApiException(private val errorResponse: ApiErrorResponse) : Exception(errorResponse.message)