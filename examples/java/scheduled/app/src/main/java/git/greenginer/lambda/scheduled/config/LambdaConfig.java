package git.greenginer.lambda.scheduled.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class LambdaConfig {
  @Autowired
  public ObjectMapper objectMapper;

  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
      MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
      converter.setObjectMapper(objectMapper);
      return converter;
  }
}
