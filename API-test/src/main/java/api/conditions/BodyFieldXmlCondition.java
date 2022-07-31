package api.conditions;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matcher;

@RequiredArgsConstructor
public class BodyFieldXmlCondition implements Condition {

    private final String xPath; /* for response body element */
    private final Matcher matcher;

    @Override
    public void check(Response response) {
        response.then().assertThat().body(xPath, matcher);
    }

    @Override
    public String toString() {
        return "Body field {" + xPath + "} " + matcher;
    }
}
