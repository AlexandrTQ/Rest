package alexandr.entitys;

import lombok.Getter;
import javax.persistence.*;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Entity
public class Worker {
    private @Id @GeneratedValue(strategy = AUTO) Long personalId;
    private String firstname;
    private String surname;
    private String phone;
    @ManyToOne(targetEntity =  Department.class)
    @JoinColumn(name = "department_id", foreignKey =  @ForeignKey(name = "fk_departments"))
    private Department department;

    public Worker() {}

    public Worker(String firstname, String surname, String phone, Department department) {
        this.firstname = firstname;
        this.surname = surname;
        this.department = department;
        this.phone = phone;
    }

    public Worker(String firstname, String surname) {
        this.firstname = firstname;
        this.surname = surname;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("Worker %s %s, id %d, phone %s working in "
                + department.getName() + "\n", firstname, surname, personalId, phone);
    }
}
