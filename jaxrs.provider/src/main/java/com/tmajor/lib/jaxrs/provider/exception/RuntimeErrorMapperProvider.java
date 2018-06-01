package com.tmajor.lib.jaxrs.provider.exception;

import com.tmajoir.lib.core.error.Exceptions;
import com.tmajoir.lib.core.model.Message;
import com.tmajoir.lib.core.model.MessageResponse;
import com.tmajoir.lib.core.model.TechnicalDetails;
import com.tmajoir.lib.core.registry.ApplicationRegistry;
import com.tmajoir.lib.core.registry.ErrorRegistry;
import com.tmajoir.lib.core.registry.TranslationRegistry;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Locale;

@Provider
public class RuntimeErrorMapperProvider implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException e) {
        MessageResponse response = new MessageResponse();
        response.addMessage(new Message(TranslationRegistry.getInstance(Locale.getDefault()).translateError(ErrorRegistry.GENERIC, ApplicationRegistry.APP_DEF), ErrorRegistry.GENERIC, Message.LEVEL.ERROR, null));
        TechnicalDetails tach = new TechnicalDetails();
        tach.setStackTrace(Exceptions.stackTrace(e));
        response.setTechnicalDetails(tach);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON_TYPE).entity(response).build();
    }

}
