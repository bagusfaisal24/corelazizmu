package portal.core.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import portal.core.exception.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
@SuppressWarnings("unused")
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    public RestResponseEntityExceptionHandler() {
    }

    @Data
    private static class Response {
        private String error;
        private String exception;
        private String message;
        private String path;
        private Integer status;
        private Timestamp timestamp;
        private List<ObjectError> errors;
    }

    private Response responseFactory(HttpStatus status, Exception e, String message, String path) {
        Response response = new Response();
        response.setError(status.name().toLowerCase());
        response.setException(e.getClass().getName());
        response.setMessage(message);
        response.setPath(path);
        response.setStatus(status.value());
        response.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return response;
    }

    @Data
    private static class ImportResponse {
        private String error;
        private String exception;
        private String message;
        private String path;
        private Integer status;
        private Timestamp timestamp;
        private List<List<ObjectError>> errors;
    }

    private ImportResponse factory(Exception e, String path) {
        ImportResponse response = new ImportResponse();
        response.setError(HttpStatus.CONFLICT.name().toLowerCase());
        response.setException(e.getClass().getName());
        response.setMessage(ImportValidationErrorException.message);
        response.setPath(path);
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        return response;
    }

    @ExceptionHandler(value = {ValidationErrorException.class})
    protected ResponseEntity<Object> handleConflict(ValidationErrorException ex, WebRequest r,
                                                    HttpServletRequest request) {
        Response body = responseFactory(HttpStatus.CONFLICT, ex, ValidationErrorException.message,
                request.getRequestURI());
        body.setErrors(ex.getErrors());
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, r);
    }

    @ExceptionHandler(value = {ImportValidationErrorException.class})
    protected ResponseEntity<Object> handleImportConflict(ImportValidationErrorException ex, WebRequest r,
                                                          HttpServletRequest request) {
        ImportResponse body = factory(ex, request.getRequestURI());
        body.setErrors(ex.getErrors());
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, r);
    }

    protected ResponseEntity<Object> handleDataNotFound(Exception ex, WebRequest r, HttpServletRequest request) {
        Response body = responseFactory(HttpStatus.BAD_REQUEST, ex, ex.getMessage(), request.getRequestURI());
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, r);
    }

    @ExceptionHandler(value = {InconsistentDataException.class, JsonMappingException.class})
    protected ResponseEntity<Object> handleDuplicateData(Exception ex, WebRequest r, HttpServletRequest request) {
        Response body = responseFactory(HttpStatus.CONFLICT, ex, ex.getMessage(), request.getRequestURI());
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, r);
    }

    @ExceptionHandler(value = {UnrecognizedPropertyException.class})
    protected ResponseEntity<Object> handleUnrecognizedProperty(UnrecognizedPropertyException ex, WebRequest r,
                                                                HttpServletRequest request) {
        Response body = responseFactory(HttpStatus.CONFLICT, ex, "invalid data", request.getRequestURI());
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, r);
    }

    @ExceptionHandler(value = {
            DataHasBeenTerminatedException.class,
            DataIsBeingUsedException.class,
            DuplicateDataException.class,
            InvalidActionException.class,
            InvalidAmountException.class,
            InvalidAttachmentTypeException.class,
            InvalidProductTypeException.class,
            InvalidStatusException.class,
            InvalidTransactionDateException.class,
            MissingFileException.class,
            MissmatchValueException.class,
            TemplateNotFoundException.class,
            TemplateNotSetupException.class,
            UnknownTaskeException.class
    })
    protected ResponseEntity<Object> handleCustomException(Exception ex, WebRequest r, HttpServletRequest request) {
        Response body = responseFactory(HttpStatus.BAD_REQUEST, ex, ex.getMessage(), request.getRequestURI());
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, r);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {
        Response body = responseFactory(HttpStatus.BAD_REQUEST, ex, ex.getMessage(), request.getContextPath());
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}