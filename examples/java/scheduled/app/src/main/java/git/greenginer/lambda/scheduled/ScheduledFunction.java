package git.greenginer.lambda.scheduled;

import git.greenginer.lambda.scheduled.domain.LambdaRequest;
import git.greenginer.lambda.scheduled.domain.LambdaResponse;
import git.greenginer.lambda.scheduled.service.ScheduledService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Function;

@Component("ScheduledFunction")
@ComponentScan("git.greenginer.lambda.scheduled")
public class ScheduledFunction implements
  Function<Flux<LambdaRequest>, Flux<LambdaResponse>> {

  @Autowired
  private ScheduledService scheduledService;

  private Logger log = Logger.getLogger(ScheduledFunction.class);


  @Override
  public Flux<LambdaResponse> apply(
    final Flux<LambdaRequest> lambdaRequestFlux) {
    final LambdaResponse result = new LambdaResponse();

    LambdaRequest lambdaRequest = lambdaRequestFlux.blockFirst();

    log.debug(lambdaRequest.getClass().getName());

    List<LambdaResponse> lambdaResponses = scheduledService.process(lambdaRequest);
    String resultMessage = lambdaResponses.get(0).getResult();

    log.info(resultMessage);
    result.setResult(resultMessage);
    return Flux.just(result);
  }
}