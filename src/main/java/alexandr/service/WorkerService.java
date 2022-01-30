package alexandr.service;

import alexandr.dto.SuccessDto;
import alexandr.dto.WorkerCreateDto;
import alexandr.dto.WorkerFullNameDto;
import alexandr.dto.WorkerSetDepartmentDto;
import alexandr.entitys.Department;
import alexandr.entitys.Worker;
import alexandr.exeptions.*;
import alexandr.repository.DepartmentRep;
import alexandr.repository.WorkerRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WorkerService implements WorkerServiceImpl {
    private final DepartmentRep departmentRep;
    private final WorkerRep workerRep;
    private final Validator validator;

    public WorkerService(DepartmentRep departmentRep, WorkerRep workerRep, Validator validator) {
        this.departmentRep = departmentRep;
        this.workerRep = workerRep;
        this.validator = validator;
    }

    @Override
    public List<Worker> getAllWorkers() {
        log.info("getAllWorkers");
        return workerRep.findAll();
    }

    @Override
    public List<Department> getAllDepartments() {
        log.info("getAllDepartments");
        return departmentRep.findAll();
    }

    @Override
    public Worker getWorkerById(long id) {
        log.info("getWorkerById {}", id);
        return workerRep.findByPersonalId(id);
    }

    @Override
    public Department getDepartmentByName(String name) {
        log.info("getDepartmentById {}", name);
        return departmentRep.findByName(name);
    }


    @Override
    public List<WorkerFullNameDto> getWorkersInDep(String depName) {
        log.info("getWorkersInDep {}", depName);
        if (Objects.isNull(departmentRep.findByName(depName))) {
            throw new DepartmentDoesNotExistException("department didn't found");
        }
        List<Worker> workers = workerRep.findAllWorkersByDepartName(depName);
        return workers.stream().map((worker) -> new WorkerFullNameDto(worker)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SuccessDto deleteWorker(long id) {
        Worker worker =  workerRep.findByPersonalId(id);
        log.info("deleteWorker {}", worker);
        if (Objects.isNull(worker)) {
            throw new WorkerDoesNotExistException("worker didn't found");
        }
        workerRep.deleteByPersonalId(id);
        return new SuccessDto("Success delete");
    }

    @Override
    @Transactional
    public Worker addWorker(WorkerCreateDto dto) {
        log.info("addWorker {}", dto);
        if (!validator.checkPhone(dto.getPhone())) {
            throw new IllegalPhoneException("incorrect phone");
        }
        if (!validator.checkName(dto.getFirstname()) || !validator.checkName(dto.getSurname())) {
            throw new IllegalWorkerNameException("Incorrect name");
        }
        if (Objects.isNull(departmentRep.findByName(dto.getDepartment()))) {
            throw new DepartmentDoesNotExistException("Department doesn't exist");
        }
        return workerRep.save(new Worker(dto.getFirstname(), dto.getSurname(),
                dto.getPhone(), departmentRep.findByName(dto.getDepartment())));
    }

    @Override
    @Transactional
    public SuccessDto setDepartmentForWorker(WorkerSetDepartmentDto setDto) {
        log.info("setDepartmentForWorker {}, {}", setDto.getId(), setDto.getDepartmentName());
        Department targetDepartment = departmentRep.findByName(setDto.getDepartmentName());
        Worker worker = workerRep.findByPersonalId(setDto.getId());
        if (Objects.isNull(worker)) {
            throw new WorkerDoesNotExistException("Worker didn't found");
        }
        if (Objects.isNull(targetDepartment)) {
            throw new DepartmentDoesNotExistException("Department didn't found");
        }
        if (targetDepartment.getName().equals(worker.getDepartment().getName())) {
            throw new WorkerAlreadyInThisDepartmentException("Worker already in this department");
        }
        workerRep.setDep(setDto.getId(), setDto.getDepartmentName());
        return new SuccessDto("success set");
    }
}
