package project.compliance.infrastructure.persistence.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import project.compliance.application.AnalyzeCompanyRiskUseCase;
import project.compliance.infrastructure.persistence.entity.CompanyEntity;

@Component
@RepositoryEventHandler
public class CompanyEventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CompanyEventHandler.class);

    private final AnalyzeCompanyRiskUseCase analyzeCompanyRiskUseCase;

    public CompanyEventHandler(AnalyzeCompanyRiskUseCase analyzeCompanyRiskUseCase) {
        this.analyzeCompanyRiskUseCase = analyzeCompanyRiskUseCase;
    }

    @HandleAfterCreate
    public void handlerAfterCreateEvent(CompanyEntity entity) {
        LOG.info("handleAfterCreateEvent {}", entity);
        this.analyzeCompanyRiskUseCase.execute(entity.toDomain());
    }
}
