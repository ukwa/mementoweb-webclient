/**
 * 
 */
package uk.bl.wap.memento;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Andrew Jackson <Andrew.Jackson@bl.uk>
 *
 */
@Path("/")
public class TimeGateService {
	 
		@GET
		@Path("json")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getMementos(
			@QueryParam("url") String url) {
	 
			MementoSearchBean msb = new MementoSearchBean();
			msb.setUrl(url);

			return Response
			   .status(200)
			   .entity( msb ).build();
	 
		}
	 
}
