package project.compliance.application;

import org.springframework.stereotype.Service;
import project.compliance.domain.Company;
import project.compliance.infrastructure.rest.client.SanctionClient;

@Service
public class AnalyzeCompanyRiskUseCase {
    private final SanctionClient sanctionClient;

    public AnalyzeCompanyRiskUseCase(SanctionClient sanctionClient) {
        this.sanctionClient = sanctionClient;
    }

    public void execute(Company domain) {
       var sanctions = sanctionClient.getCompanyRisk(domain.getRegistrationNumber());

    }
}
