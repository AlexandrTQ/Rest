package alexandr.service;

import alexandr.dto.SuccessDto;
import alexandr.dto.WorkerCreateDto;
import alexandr.dto.WorkerFullNameDto;
import alexandr.dto.WorkerSetDepartmentDto;
import alexandr.entitys.Department;
import alexandr.entitys.Worker;

import java.util.List;

public interface WorkerServiceImpl {

    List<Worker> getAllWorkers();

    List<Department> getAllDepartments();

    Worker getWorkerById(long id);

    Department getDepartmentByName(String name);

    List<WorkerFullNameDto> getWorkersInDep(String depName);

    SuccessDto deleteWorker(long id);

    Worker addWorker(WorkerCreateDto dto);

    SuccessDto setDepartmentForWorker(WorkerSetDepartmentDto setDto);
}
