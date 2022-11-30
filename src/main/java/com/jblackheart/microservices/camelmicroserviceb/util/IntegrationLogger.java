package com.jblackheart.microservices.camelmicroserviceb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

@Component
public class IntegrationLogger {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String APP_NAME = "camel.microservice-b";
    protected static final String linea = "=====================================================================================================";

    public void info(String message, String className) {
        String code = "\nCode           : -> ".concat(APP_NAME);
        String date = "\nDate           : -> ".concat(new Date().toString());
        String sourceClass = "\nInfo source    : -> ".concat(className);
        String customMessage = "\nMessage        : -> ".concat(message);
        String finalMessage = buildLog(code, date, sourceClass, customMessage, "");

        LOGGER.info(finalMessage);
    }

    public void auditInfo(String userId, String ip, String message, Class source, String patientId) {
        String code = "\nCode           : -> ".concat(APP_NAME);
        String date = "\nDate           : -> ".concat(new Date().toString());
        String sourceClass = "\nInfo source    : -> ".concat(source.getCanonicalName());
        String customMessage = "\nMessage        : -> ".concat(message);
        String finalMessage = buildLog(code, date, sourceClass, customMessage, "");

        LOGGER.info(finalMessage);
    }

    public void warn(String message, String className) {
        String code = "\nCode           : -> ".concat(APP_NAME);
        String date = "\nDate           : -> ".concat(new Date().toString());
        String sourceClass = "\nWarn source    : -> ".concat(className);
        String customMessage = "\nMessage        : -> ".concat(message);
        String finalMessage = buildLog(code, date, sourceClass, customMessage, "");

        LOGGER.warn(finalMessage);
    }

    public void debug(String message, Class source) {
        String code = "\nCode           : -> ".concat(APP_NAME);
        String date = "\nDate           : -> ".concat(new Date().toString());
        String sourceClass = "\nDebug source   : -> ".concat(source.getCanonicalName());
        String customMessage = "\nMessage        : -> ".concat(message);
        String finalMessage = buildLog(code, date, sourceClass, customMessage, "");

        LOGGER.debug(finalMessage);
    }

    public void error(String message, Exception ex, Class source) {
        String code = "\nCode           : -> ".concat(APP_NAME);
        String date = "\nDate           : -> ".concat(new Date().toString());
        String sourceClass = "\nError source   : -> ".concat(source.getCanonicalName());
        String customMessage = "\nMessage        : -> ".concat(message).concat(": ").concat(ex.getMessage());
        String trace = "\nTrace          : -> ".concat(getStackTrace(ex));
        String finalMessage = buildLog(code, date, sourceClass, customMessage, trace);

        LOGGER.error(finalMessage);
    }

    private String buildLog(String code, String date, String sourceClass, String customMessage, String trace) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n").append(linea)
                .append(code).append(date)
                .append(sourceClass)
                .append(customMessage)
                .append(trace)
                .append("\n").append(linea);
        return builder.toString();
    }

    protected String getStackTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
