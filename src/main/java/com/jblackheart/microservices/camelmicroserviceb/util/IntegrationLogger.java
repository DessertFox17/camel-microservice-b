package com.jblackheart.microservices.camelmicroserviceb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

@Component
public class IntegrationLogger {
    @Autowired
    private Environment env;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    protected static final String linea = "=====================================================================================================";

    public void info(String message, String source) {
        String code = "\nCode           : -> ".concat(env.getRequiredProperty(ConstantVariables.LOGGER_APP_NAME));
        String date = "\nDate           : -> ".concat(new Date().toString());
        String sourceClass = "\nInfo source    : -> ".concat(source);
        String customMessage = "\nMessage        : -> ".concat(message);
        String finalMessage = buildLog(code, date, sourceClass, customMessage, "");

        LOGGER.info(finalMessage);
    }

    public void auditInfo(String userId, String ip, String message, Class source, String patientId) {
        String code = "\nCode           : -> ".concat(env.getRequiredProperty(ConstantVariables.LOGGER_APP_NAME));
        String date = "\nDate           : -> ".concat(new Date().toString());
        String sourceClass = "\nInfo source    : -> ".concat(source.getCanonicalName());
        String customMessage = "\nMessage        : -> ".concat(message);
        String finalMessage = buildLog(code, date, sourceClass, customMessage, "");

        LOGGER.info(finalMessage);
    }

    public void warn(String message, String source) {
        String code = "\nCode           : -> ".concat(env.getRequiredProperty(ConstantVariables.LOGGER_APP_NAME));
        String date = "\nDate           : -> ".concat(new Date().toString());
        String sourceClass = "\nWarn source    : -> ".concat(source);
        String customMessage = "\nMessage        : -> ".concat(message);
        String finalMessage = buildLog(code, date, sourceClass, customMessage, "");

        LOGGER.warn(finalMessage);
    }

    public void debug(String message, String source) {
        String code = "\nCode           : -> ".concat(env.getRequiredProperty(ConstantVariables.LOGGER_APP_NAME));
        String date = "\nDate           : -> ".concat(new Date().toString());
        String sourceClass = "\nDebug source   : -> ".concat(source);
        String customMessage = "\nMessage        : -> ".concat(message);
        String finalMessage = buildLog(code, date, sourceClass, customMessage, "");

        LOGGER.debug(finalMessage);
    }

    public void error(String message, Exception ex, String source) {
        String code = "\nCode           : -> ".concat(env.getRequiredProperty(ConstantVariables.LOGGER_APP_NAME));
        String date = "\nDate           : -> ".concat(new Date().toString());
        String sourceClass = "\nError source   : -> ".concat(source);
        String customMessage = "\nMessage        : -> ".concat(message).concat(": ").concat(ex.getLocalizedMessage());
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
