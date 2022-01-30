package alexandr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkerCreateDto {
    private final String firstname;
    private final String surname;
    private final String phone;
    private final String department;
}
