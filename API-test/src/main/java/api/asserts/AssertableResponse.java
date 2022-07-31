package api.asserts;

import api.conditions.Condition;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AssertableResponse {

    private final Response response;

    //custom check
    public AssertableResponse expectedResult(Condition condition) {
        log.info("About to check condition: {}", condition);
        condition.check(response);
        return this;
    }

    //for check with response as class with possibility get item body
    public <T> T asPojo(Class<T> tclass) {
        return response.as(tclass);
    }

    public Headers headers() {
        return response.getHeaders();
    }
}
