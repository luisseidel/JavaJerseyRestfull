package com.seidelsoft.rest;


import com.seidelsoft.model.Ebook;
import com.seidelsoft.util.MediaTypes;
import com.seidelsoft.webservices.EbookService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.List;

@Path("/ebooks")
@Produces(MediaTypes.APPLICATION_JSON_UTF_8)
@Consumes(MediaTypes.APPLICATION_JSON_UTF_8)
public class EbookResource {

    private EbookService service = new EbookService();

    @GET
    public List<Ebook> get(){
        return service.getList();
    }
}
