package za.co.workshyelec.core.api

class ApiError(val errorResponse: ApiErrorResponse) : Exception(errorResponse.message)