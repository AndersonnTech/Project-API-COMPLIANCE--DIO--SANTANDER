package project.compliance.infrastructure.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import project.compliance.infrastructure.persistence.entity.CompanyEntity;

import java.util.UUID;

@RepositoryRestResource(path = "companies")
public interface CompanyEntityRepository extends CrudRepository<CompanyEntity, UUID> {
}
