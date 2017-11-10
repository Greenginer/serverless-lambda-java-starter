package git.greenginer.lambda.scheduled.service;


import git.greenginer.lambda.scheduled.domain.LambdaRequest;
import git.greenginer.lambda.scheduled.domain.LambdaResponse;

import java.util.List;

public interface ScheduledService{

  List<LambdaResponse> process(LambdaRequest lambdaRequest);
}
