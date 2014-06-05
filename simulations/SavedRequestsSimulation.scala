import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import com.excilys.ebi.gatling.jdbc.Predef._
import com.excilys.ebi.gatling.http.Headers.Names._
import akka.util.duration._
import bootstrap._
import assertions._

class SavedRequestsSimulation extends Simulation {

	val savedRequests = Array("https://www.google.fr",
		"http://www.lequipe.fr",
		"http://www.eurosport.fr"
	);

	var chain: com.excilys.ebi.gatling.core.structure.ChainBuilder = null;

	for (savedRequest <- savedRequests) {
		if (chain == null) {
			chain = exec(http(savedRequest).get(savedRequest));
		} else {
			chain = chain.exec(http(savedRequest).get(savedRequest));
		}
	}

	val scn = scenario("Global scenario")
		.exec(chain)

	setUp(scn.users(1))
}