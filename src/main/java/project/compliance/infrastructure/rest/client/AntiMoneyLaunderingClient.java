package project.compliance.infrastructure.rest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.compliance.infrastructure.rest.dto.AmlResult;

@FeignClient("aml-client")
public interface AntiMoneyLaunderingClient {
    @GetMapping("/aml/v1/screening/{registrationNumber}")
    AmlResult screening(@PathVariable String registrationNumber);
}
