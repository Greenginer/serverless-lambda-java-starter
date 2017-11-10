package git.greenginer.lambda.scheduled;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import git.greenginer.lambda.scheduled.domain.LambdaRequest;
import git.greenginer.lambda.scheduled.domain.LambdaResponse;
import git.greenginer.lambda.scheduled.handler.aws.ScheduledFunctionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

public class StepDefinitions {

  private LambdaResponse lambdaResult;
  private ScheduledFunctionHandler requestHandler;

  @Given("^a scheduled lambda$")
  public void aScheduledLambda() throws Throwable {

    requestHandler = new ScheduledFunctionHandler();
  }

  @When("^the lambda is invoked$")
  public void theLambdaIsInvoked() throws Throwable {

    ScheduledFunctionHandler requestHanlder = new ScheduledFunctionHandler();
    try {
      lambdaResult = (LambdaResponse) requestHandler.handleRequest(getEventData(), getContext());
      System.out.println("SUCCESS: " + (new ObjectMapper().writeValueAsString(lambdaResult)));
    } catch (RuntimeException e) {
      System.out.println("FAIL:");
      e.printStackTrace();
    }
    assertThat(lambdaResult, notNullValue());
  }

  @Then("^the lambda result begins with ([^\"]*)$")
  public void theResultIsSUCCESS(String result) throws Throwable {
    assertThat(lambdaResult, notNullValue());
    assertThat(lambdaResult.getResult(), startsWith(result));
  }

  private Context getContext() {
    return new Context() {
      public String getAwsRequestId() {
        return null;
      }

      public String getLogGroupName() {
        return null;
      }

      public String getLogStreamName() {
        return null;
      }

      public String getFunctionName() {
        return null;
      }

      public String getFunctionVersion() {
        return null;
      }

      public String getInvokedFunctionArn() {
        return null;
      }

      public CognitoIdentity getIdentity() {
        return null;
      }

      public ClientContext getClientContext() {
        return null;
      }

      public int getRemainingTimeInMillis() {
        return 0;
      }

      public int getMemoryLimitInMB() {
        return 0;
      }

      public LambdaLogger getLogger() {
        return null;
      }
    };
  }
  private static <I> I getEventData() throws IOException {
    LambdaRequest lambdaRequest = new LambdaRequest();
    lambdaRequest.setId(UUID.randomUUID().toString());
    lambdaRequest.setDetailType("Scheduled Event");
    lambdaRequest.setSource("aws.events");
    lambdaRequest.setAccount("123456789012");
    lambdaRequest.setTime("2017-09-08T16:53:06Z");
    lambdaRequest.setRegion("us-west-2");
    lambdaRequest.setResources(new String[]{"arn:aws:events:us-west-2:123456789012:rule/MyScheduledRule"});
    lambdaRequest.setDetail(new Object());

    return (I) lambdaRequest;
  }

  private String buildTestBody(String status) {
    List<LambdaRequest> lambdaRequestList = new ArrayList<LambdaRequest>();
    for (String status_items : status.split("_")) {
      lambdaRequestList.add(buildlambdaRequest(status_items));
    }

    return Arrays.toString(lambdaRequestList.toArray());
  }

  private LambdaRequest buildlambdaRequest(String status) {
    LambdaRequest lambdaRequest = new LambdaRequest();
    lambdaRequest.setId(UUID.randomUUID().toString());
    lambdaRequest.setDetailType("Scheduled Event");
    lambdaRequest.setSource("aws.events");
    lambdaRequest.setAccount("123456789012");
    lambdaRequest.setTime("2017-09-08T16:53:06Z");
    lambdaRequest.setRegion("us-west-2");
    lambdaRequest.setResources(new String[]{"arn:aws:events:us-west-2:123456789012:rule/MyScheduledRule"});
    lambdaRequest.setDetail(new Object());

    return lambdaRequest;
  }
}
