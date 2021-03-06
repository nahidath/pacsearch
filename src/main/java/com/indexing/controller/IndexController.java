package com.indexing.controller;

import com.indexing.service.IndexService;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created bu PacLab
 * User: sami
 */

@Path("/index")
@Produces(value = {MediaType.APPLICATION_JSON})
public class IndexController {

    // static block
    static {
        // System.out.println("<debug> IndexController loaded in memory.");
    }

    /* data members */
    private IndexService indexService;

    // non-static block
    {
        indexService = new IndexService();
    }

    /* constructors */

    // default constructor
    public IndexController() {

    }

    /* methods */

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response indexFile(
            @FormDataParam("file") List<FormDataBodyPart> formDataBodyParts
    ) {
        try {
            for (FormDataBodyPart part : formDataBodyParts) {
                System.out.println(
                        "Indexing file : "
                                + part.getContentDisposition()
                                .getFileName()
                );

                indexService.indexFile(
                        part.getEntityAs(InputStream.class),
                        part.getContentDisposition()
                );

                System.out.println(
                        "Successfully indexed file  : "
                                + part.getContentDisposition()
                                .getFileName()
                );
            }
            return (
                    Response.status(Response.Status.OK)
                            .entity("Indexing files went successfully")
                            .build()
            );
        } catch (IOException ex) {
            return (
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(ex)
                            .build()
            );
        }
    }

}