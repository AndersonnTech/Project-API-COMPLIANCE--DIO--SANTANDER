package project.compliance.infrastructure.rest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.compliance.infrastructure.rest.dto.SanctionResult;

import java.util.List;

@FeignClient(name = "sanction-client", fallback = SanctionClient.Fallback.class)
public interface SanctionClient {

    @GetMapping("/sanctions/companies/{registrationNumber}")
    SanctionResult getCompanyRisk(@PathVariable String registrationNumber);

    @Component
    public class Fallback implements SanctionClient {
        @Override
        public SanctionResult getCompanyRisk (String registrationNumber) {
            return new SanctionResult(List.of());
        }
    }
}
