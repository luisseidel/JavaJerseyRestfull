package com.seidelsoft.rest;


import com.seidelsoft.model.Ebook;
import com.seidelsoft.util.MediaTypes;
import com.seidelsoft.webservices.EbookService;
import com.seidelsoft.webservices.Response;
import jakarta.ws.rs.*;

import java.sql.SQLException;
import java.util.List;

@Path("/ebooks")
@Produces(MediaTypes.APPLICATION_JSON_UTF_8)
@Consumes(MediaTypes.APPLICATION_JSON_UTF_8)
public class EbookResource {

    private EbookService service = new EbookService();

    @GET
    @Path("{id}")
    public Ebook getById(@PathParam("id") Long id) {
        return service.getById(id);
    }

    @GET
    @Path("/nome/{nome}")
    public List<Ebook> getByName(@PathParam("nome") String nome) {
        return service.getByName(nome);
    }

    @POST
    public Response post(Ebook ebook) throws SQLException {
        service.save(ebook);
        return Response.Ok("Registro incluído");
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) throws SQLException {
        service.delete(id);
        return Response.Ok("Registro Excluído");
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Ebook ebook) throws SQLException, IllegalAccessException, NoSuchFieldException {
        ebook = service.update(id, ebook);
        return Response.Ok("Registro atualizado", ebook.toString());
    }
}
