package org.melisits.programming.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.melisits.programming.dto.ScheduleDTO;
import org.melisits.programming.persistence.Schedule;
import org.melisits.programming.service.ScheduleService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Path("/schedules")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScheduleResource {

    @Inject
    ScheduleService scheduleService;

    @GET
    public List<ScheduleDTO> getAll() {
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

    @GET
    @Path("/range")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSchedulesInRange(@QueryParam("from") LocalDate from,
                                        @QueryParam("to") LocalDate to) {
        if (from == null || to == null || from.isAfter(to)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid date range. 'from' and 'to' must be valid and 'from' must not be after 'to'.")
                    .build();
        }

        List<ScheduleDTO> schedules = scheduleService.findInRange(from, to);
        return Response.ok(schedules).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ScheduleDTO schedule) {
        ScheduleDTO created = scheduleService.create(schedule);

        URI location = UriBuilder.fromResource(ScheduleResource.class)
                .path(String.valueOf(created.getId()))
                .build();

        return Response.created(location) // sets 201 + Location header
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ScheduleDTO schedule) {
        try {
            ScheduleDTO updated = scheduleService.update(id, schedule);
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