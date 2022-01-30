package alexandr.dto;

import alexandr.entitys.Worker;
import lombok.Data;

@Data
public class WorkerFullNameDto {
    private final String firstName;
    private final String lastName;
    private final String phone;

    public WorkerFullNameDto(Worker worker) {
        this.firstName = worker.getFirstname();
        this.lastName = worker.getSurname();
        this.phone = worker.getPhone();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " phone: " + phone;
    }
}
