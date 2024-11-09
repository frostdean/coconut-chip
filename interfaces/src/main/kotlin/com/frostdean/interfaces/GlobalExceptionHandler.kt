package com.frostdean.interfaces

import com.frostdean.interfaces.common.CoconutResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class, IllegalStateException::class, AssertionError::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun commonClientException(e: Exception): CoconutResponse<Unit> {
        log.warn("bad request !", e)
        return CoconutResponse.badRequest(message = e.message, displayMessage = e.message)
    }

    @ExceptionHandler(Throwable::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun unknownException(e: Throwable): CoconutResponse<Unit> {
        log.error("Internal Server Error", e)
        return CoconutResponse.internalServerError(displayMessage = "시스템 오류가 발생했습니다")
    }

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }
}