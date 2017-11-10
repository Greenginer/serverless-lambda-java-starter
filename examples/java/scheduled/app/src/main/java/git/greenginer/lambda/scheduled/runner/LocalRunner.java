package git.greenginer.lambda.scheduled.runner;

import com.amazonaws.services.lambda.runtime.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import git.greenginer.lambda.scheduled.domain.LambdaRequest;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Logger;

public final class LocalRunner {

  private Object output = null;
  Logger log = Logger.getLogger(this.getClass().getName());

  public static void main(final String[] args) throws
      Exception {
    LocalRunner localRunner = new LocalRunner();
    localRunner.run(args);
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

  public <I, O> Object run(final String[] args) throws Exception {
    Context context = new Context() {
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

    if (args.length != 1) {
      throw new RuntimeException("You should give handler class name as an argument");
    }

    String handlerClassName = args[0];

    Object object;
    try {
      Class<?> clazz = Class.forName(handlerClassName);
      Constructor<?> ctor = clazz.getConstructor();
      object = ctor.newInstance();

    } catch (ClassNotFoundException e) {
      throw new RuntimeException(handlerClassName + " not found in classpath");
    }

    if (!(object instanceof RequestHandler)) {
      throw new RuntimeException("Request handler class does not implement " + RequestHandler.class + " interface");
    }

    @SuppressWarnings("unchecked")
    RequestHandler<I, O> requestHandler = (RequestHandler<I, O>) object;
    I eventData = getEventData();

    try {
      output = requestHandler.handleRequest(eventData, context);
      String message = String.format("SUCCESS: %s", (new ObjectMapper().writeValueAsString(output)));
      log.info(message);
    } catch (RuntimeException e) {
      String errMessage = String.format("FAIL: %s", Arrays.toString(e.getStackTrace()));
      log.severe(errMessage);
    }
    return output;
  }
}