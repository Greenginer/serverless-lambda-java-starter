package git.greenginer.lambda.scheduled.service.impl;

import git.greenginer.lambda.scheduled.domain.LambdaRequest;
import git.greenginer.lambda.scheduled.domain.LambdaResponse;
import git.greenginer.lambda.scheduled.service.ScheduledService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;

@Service
public class ScheduledServiceImpl implements ScheduledService {

  Logger log = Logger.getLogger(this.getClass().getName());

  @Override
  public List<LambdaResponse> process(LambdaRequest lambdaRequest) {
    List<LambdaResponse> lambdaResponseList = new ArrayList<>();
    LambdaResponse lambdaResponse = new LambdaResponse();
    lambdaResponse.setResult(format("Hello %s", lambdaRequest.getId()));

    lambdaResponseList.add(lambdaResponse);
    return lambdaResponseList;
  }
}