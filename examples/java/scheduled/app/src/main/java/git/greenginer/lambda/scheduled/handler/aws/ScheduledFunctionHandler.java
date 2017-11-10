package git.greenginer.lambda.scheduled.handler.aws;

import git.greenginer.lambda.scheduled.domain.LambdaRequest;
import git.greenginer.lambda.scheduled.domain.LambdaResponse;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class ScheduledFunctionHandler extends SpringBootRequestHandler<LambdaRequest, LambdaResponse> {
}
