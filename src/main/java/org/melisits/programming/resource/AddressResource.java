package org.melisits.programming.resource;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.melisits.programming.dto.AddressDTO;
import org.melisits.programming.persistence.Address;
import org.melisits.programming.service.AddressService;

import java.util.List;

@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {

    private final AddressService addressService;

    public AddressResource(AddressService addressService) {
        this.addressService = addressService;
    }

    @GET
    public Response getAll() {
        return Response.ok(addressService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        return addressService.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(
            @QueryParam("query") String query,
            @QueryParam("limit") @DefaultValue("20") int limit,
            @QueryParam("offset") @DefaultValue("0") int offset) {
        if (query == null || query.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Query parameter 'query' must not be blank.")
                    .build();
        }

        query = query.trim();
        List<AddressDTO> results = addressService.search(query, limit, offset);
        return Response.ok(results).build();
    }


}
