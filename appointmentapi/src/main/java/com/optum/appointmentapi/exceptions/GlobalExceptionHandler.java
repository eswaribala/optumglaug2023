package com.optum.appointmentapi.exceptions;

import com.netflix.graphql.types.errors.TypedGraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class GlobalExceptionHandler implements DataFetcherExceptionHandler {


    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
        if(handlerParameters.getException() instanceof NoDataFoundError noDataFoundError) {
            Map<String, Object> debugInfo = new HashMap<>();
            debugInfo.put("errorCode", noDataFoundError.getErrorCode());

            TypedGraphQLError graphqlError = TypedGraphQLError
                    .newInternalErrorBuilder()
                    .message(noDataFoundError.getMessage())
                    .debugInfo(debugInfo)
                    .path(handlerParameters.getPath()).build();
            DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError).build();
            return CompletableFuture.completedFuture(result);
        } else {
            return DataFetcherExceptionHandler.super.handleException(handlerParameters);
        }
    }
}
