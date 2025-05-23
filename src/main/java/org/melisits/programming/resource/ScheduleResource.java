package org.melisits.programming.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.melisits.programming.persistence.Schedule;
import org.melisits.programming.service.ScheduleService;

import java.net.URI;
import java.util.List;

@Path("/schedules")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScheduleResource {

    @Inject
    ScheduleService scheduleService;

    @GET
    public List<Schedule> getAll() {
        return scheduleService.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return scheduleService.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Schedule schedule) {
        Schedule created = scheduleService.create(schedule);

        URI location = UriBuilder.fromResource(ScheduleResource.class)
                .path(String.valueOf(created.getId()))
                .build();

        return Response.created(location) // sets 201 + Location header
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Schedule schedule) {
        try {
            Schedule updated = scheduleService.update(id, schedule);
            return Response.ok(updated).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = scheduleService.delete(id);
        if (deleted) {
            return Response.noContent().build();  // 204 No Content on success
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Schedule with id " + id + " not found.")
                    .build();
        }
    }

}