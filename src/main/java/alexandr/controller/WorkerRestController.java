package alexandr.controller;

import alexandr.dto.SuccessDto;
import alexandr.dto.WorkerCreateDto;
import alexandr.dto.WorkerFullNameDto;
import alexandr.dto.WorkerSetDepartmentDto;
import alexandr.entitys.Department;
import alexandr.entitys.Worker;
import alexandr.service.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkerRestController {

    private final WorkerService workerService;

    public WorkerRestController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/workers")
    public ResponseEntity<List<Worker>> getAllWorker() {
        return new ResponseEntity<>(workerService.getAllWorkers(), HttpStatus.OK);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getDepartments() {
        return new ResponseEntity<>(workerService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/workers/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable Long id) {
        return new ResponseEntity<>(workerService.getWorkerById(id), HttpStatus.OK);
    }

    @GetMapping("/workers/bydep/{department}")
    public ResponseEntity<List<WorkerFullNameDto>> getAllWorkerInDepartment
            (@PathVariable(value = "department") String department) {
        return new ResponseEntity<>(workerService.getWorkersInDep(department), HttpStatus.OK);
    }

    @DeleteMapping("/workers/{id}")
    public ResponseEntity<SuccessDto> deleteWorkerById(@PathVariable Long id) {
        return new ResponseEntity<>(workerService.deleteWorker(id), HttpStatus.OK);
    }

    @PostMapping("/workers")
    public ResponseEntity<Worker> addNewWorker(@RequestBody WorkerCreateDto workerCreateDto) {
        return new ResponseEntity<>(workerService.addWorker(workerCreateDto), HttpStatus.OK);
    }

    @PutMapping("/workers")
    public ResponseEntity<SuccessDto> setDepartmentForWorker(@RequestBody WorkerSetDepartmentDto setDto) {
        return new ResponseEntity<>(workerService.setDepartmentForWorker(setDto), HttpStatus.OK);
    }
}
