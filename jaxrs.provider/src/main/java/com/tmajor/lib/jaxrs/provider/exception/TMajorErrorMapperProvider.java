package com.tmajor.lib.jaxrs.provider.exception;

import com.tmajoir.lib.core.error.TMajorException;
import com.tmajoir.lib.core.model.Message;
import com.tmajoir.lib.core.model.MessageResponse;
import com.tmajoir.lib.core.model.TechnicalDetails;
import com.tmajoir.lib.core.registry.TranslationRegistry;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Locale;

@Provider
public class TMajorErrorMapperProvider implements ExceptionMapper<TMajorException> {

    @Override
    public Response toResponse(TMajorException e) {
        MessageResponse response = new MessageResponse();
        Integer errorStatus = Integer.valueOf(e.getCode());
        Response.Status status = Response.Status.fromStatusCode(errorStatus);
        boolean statusFound = status != null;
        if (!statusFound) {
            switch (errorStatus) {
                case 0:
                    status = Response.Status.INTERNAL_SERVER_ERROR;
                    break;
            }
        }
        response.addMessage(new Message(TranslationRegistry.getInstance(Locale.getDefault()).translateError(e.getCode(), e.getApp()), e.getCode(), Message.LEVEL.ERROR, e.getUuid()));
        response.setTechnicalDetails(TechnicalDetails.build(e));
        return Response.status(status).type(MediaType.APPLICATION_JSON_TYPE).entity(response).build();
    }

}
