package alexandr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkerSetDepartmentDto {
    private final long id;
    private final String departmentName;
}
