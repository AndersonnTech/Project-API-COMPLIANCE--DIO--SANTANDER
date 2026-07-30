package project.compliance.application;

import org.springframework.stereotype.Service;
import project.compliance.domain.Company;
import project.compliance.domain.CompanyRepository;
import project.compliance.domain.CompliancePolicy;
import project.compliance.domain.ComplianceScreening;
import project.compliance.infrastructure.rest.client.AntiMoneyLaunderingClient;
import project.compliance.infrastructure.rest.client.SanctionClient;

@Service
public class AnalyzeCompanyRiskUseCase {
    private final SanctionClient sanctionClient;
    private final AntiMoneyLaunderingClient antiMoneyLaunderingClient;
    private final CompanyRepository companyRepository;

    public AnalyzeCompanyRiskUseCase(SanctionClient sanctionClient,
                                     AntiMoneyLaunderingClient antiMoneyLaunderingClient,
                                     CompanyRepository companyRepository) {
        this.sanctionClient = sanctionClient;
        this.antiMoneyLaunderingClient = antiMoneyLaunderingClient;
        this.companyRepository = companyRepository;
    }

    public void execute(Company company) {
       var sanctions = sanctionClient.getCompanyRisk(company.getRegistrationNumber()).toDomain();
       var amlProfile = antiMoneyLaunderingClient.screening(company.getRegistrationNumber()).toDomain();

       var screening = new ComplianceScreening(sanctions, amlProfile);
       var riskAssessment = CompliancePolicy.evaluate(screening);

       company.applyRiskAssessment(riskAssessment);
       companyRepository.save(company);
    }
}
