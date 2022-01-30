package alexandr.repository;

import alexandr.entitys.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRep extends JpaRepository<Worker, Long> {

    Worker findByPersonalId(@Param("workerId") long workerId);

    @Modifying
    void deleteByPersonalId(long id);

    @Modifying
    @Query(
            value = "update worker set department_id = "
                    + "(select department_id from department where name =:dep) where personal_Id = :workerId",
            nativeQuery = true)
    void setDep(@Param("workerId") long workerId, @Param("dep") String dep);

    @Query(
            value = "select worker.* from worker join Department on "
                    + "department.department_id = worker.department_id where name = :name",
            nativeQuery = true)
    List<Worker> findAllWorkersByDepartName(@Param("name") String name);
}
