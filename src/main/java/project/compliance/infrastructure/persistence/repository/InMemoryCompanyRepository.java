package project.compliance.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import project.compliance.domain.Company;
import project.compliance.domain.CompanyRepository;
import project.compliance.infrastructure.persistence.entity.CompanyEntity;

@Repository
public class InMemoryCompanyRepository implements CompanyRepository {
    private final CompanyEntityRepository repository;

    public InMemoryCompanyRepository(CompanyEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Company company) {
        var entity = CompanyEntity.from(company);
        repository.save(entity);
    }
}
