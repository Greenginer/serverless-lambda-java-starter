package git.greenginer.lambda.scheduled.domain;

import lombok.Getter;
import lombok.Setter;

public class LambdaRequest {

  @Setter
  @Getter
  private String id;

  @Setter
  private String detailType;

  @Setter
  private String source;

  @Setter
  private String account;

  @Setter
  private String region;

  @Setter
  private String[] resources;

  @Setter
  private Object detail;

  @Setter
  private String time;
}