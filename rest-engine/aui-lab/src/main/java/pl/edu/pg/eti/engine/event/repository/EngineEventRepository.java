package pl.edu.pg.eti.engine.event.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.eti.engine.entity.Engine;
import pl.edu.pg.eti.engine.event.dto.CreateEngineRequest;

@Repository
public class EngineEventRepository {

    private RestTemplate restTemplate;

    public EngineEventRepository(@Value("${aui-lab.cars.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Engine engine) {
        restTemplate.delete("/engines/{name}", engine.getName());
    }

    public void create(Engine engine) {
        restTemplate.postForLocation("/engines", CreateEngineRequest.entityToDtoMapper().apply(engine));
    }
}
