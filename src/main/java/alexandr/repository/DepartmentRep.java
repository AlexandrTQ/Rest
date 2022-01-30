package alexandr.repository;

import alexandr.entitys.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRep extends JpaRepository<Department, Long> {
    Department findByName(String name);
}
