package eb.core.app.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/isAlive")
public class IsAliveService {
	@GET
	public String helloWorld() {
		return Long.toString(new Date().getTime());
	}
}
