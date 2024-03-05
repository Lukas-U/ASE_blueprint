package com.tuwien.ASE_blueprint.todo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TodoTest {

    @Test
    public void givenSampleRestApi_whenApiIsConsumedByHttpClient_thenCompareJsonString() throws Exception {
        TodoAppClient sampleApi = new TodoAppClient();
        assertNotNull(sampleApi.sampleApiRequest());
    }

    @Test
    public void givenSampleApiCall_whenResponseIsMappedByJackson_thenCompareMappedResponseByJackson() throws Exception {
        Todo expectedResult = new Todo(1, 1, "delectus aut autem", false);
        TodoAppClient client = new TodoAppClient();
        Todo todoFromAPI = client.mapJSONStringToTodoObject_Jackson();
        assertThat(todoFromAPI).isEqualToComparingFieldByField(expectedResult);
    }

    @Test
    public void givenSampleApiCall_whenResponseIsMappedByGson_thenCompareMappedGsonJsonResponse() throws Exception {
        Todo expectedResult = new Todo(1, 1, "delectus aut autem", false);
        TodoAppClient client = new TodoAppClient();
        Todo todoFromGson = client.mapJSONStringToTodoObject_Gson();
        assertThat(todoFromGson).isEqualToComparingFieldByField(expectedResult);
    }
}
