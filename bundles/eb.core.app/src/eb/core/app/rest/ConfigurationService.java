package eb.core.app.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import eb.core.shared.DBConnection;

@Path("/configuration")
public class ConfigurationService {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@POST
	@Path("/dbConnection")
	@Consumes({ MediaType.APPLICATION_XML })
	public Response setDBConnection(DBConnection connection) {
		System.out.println(connection.connectionString);
		return Response.ok().build();
	}

	@GET
	@Path("/dbConnection")
	public String getDBConnection() {
		return "OK";
	}
}
