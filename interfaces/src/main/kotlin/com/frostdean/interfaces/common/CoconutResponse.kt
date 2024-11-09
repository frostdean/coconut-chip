package com.frostdean.interfaces.common

import org.springframework.http.HttpStatus

data class CoconutResponse<T>(
    val code: HttpStatus,
    val data: T? = null,
    val message: String? = null,
    val displayMessage: String? = null
) {
    companion object {
        fun <T> of(
            code: HttpStatus,
            data: T? = null,
            message: String? = null,
            displayMessage: String? = null
        ): CoconutResponse<T> {
            return CoconutResponse(code, data, message, displayMessage)
        }

        fun emptyOk(): CoconutResponse<Unit> {
            return of(HttpStatus.OK)
        }

        fun <T> success(data: T? = null): CoconutResponse<T> {
            return of(HttpStatus.OK, data)
        }

        fun <T> internalServerError(
            data: T? = null,
            message: String? = null,
            displayMessage: String? = null
        ): CoconutResponse<T> {
            return of(HttpStatus.INTERNAL_SERVER_ERROR, data, message, displayMessage)
        }

        fun <T> badRequest(
            data: T? = null,
            message: String? = null,
            displayMessage: String? = null
        ): CoconutResponse<T> {
            return of(HttpStatus.BAD_REQUEST, data, message, displayMessage)
        }
    }

}