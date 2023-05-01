package com.seidelsoft.rest;


import com.seidelsoft.model.Empresa;
import com.seidelsoft.util.MediaTypes;
import com.seidelsoft.webservices.EmpresaService;
import com.seidelsoft.webservices.Response;
import jakarta.ws.rs.*;

import java.sql.SQLException;
import java.util.List;

@Path("/empresas")
@Produces(MediaTypes.APPLICATION_JSON_UTF_8)
@Consumes(MediaTypes.APPLICATION_JSON_UTF_8)
public class EmpresaResource {

    private EmpresaService service = new EmpresaService();

    @GET
    @Path("{id}")
    public Empresa getById(@PathParam("id") Long id) {
        return service.getById(id);
    }

    @GET
    @Path("/nome/{nome}")
    public List<Empresa> getByName(@PathParam("nome") String nome) {
        return service.getByName(nome);
    }

    @POST
    public Response post(Empresa empresa) throws SQLException {
        service.save(empresa);
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
    public Response put(@PathParam("id") Long id, Empresa empresa) throws SQLException, IllegalAccessException, NoSuchFieldException {
        empresa = service.update(id, empresa);
        return Response.Ok("Registro atualizado", empresa.toString());
    }
}
