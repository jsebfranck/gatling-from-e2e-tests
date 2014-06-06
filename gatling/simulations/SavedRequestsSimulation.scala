import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import com.excilys.ebi.gatling.jdbc.Predef._
import com.excilys.ebi.gatling.http.Headers.Names._
import akka.util.duration._
import bootstrap._
import assertions._

class SavedRequestsSimulation extends Simulation {

	val filename = "/Users/home/Desktop/JS/sources/gatling-from-e2e-tests/savedRequests.txt";
	var savedRequests = io.Source.fromFile(filename).getLines();

	var chain: com.excilys.ebi.gatling.core.structure.ChainBuilder = null;
	for (savedRequest <- savedRequests) {
		val requestName = savedRequest.substring(0, 20);

		if (chain == null) {
			chain = exec(http(requestName).get(savedRequest));
		} else {
			chain = chain.exec(http(requestName).get(savedRequest));
		}
	}

	val scn = scenario("Global scenario")
		.exec(chain)

	setUp(scn.users(1))
}