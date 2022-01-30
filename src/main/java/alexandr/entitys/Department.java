package alexandr.entitys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "department_id")
    private long departmentId;
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public Department() {
    }

    @Override
    public String toString() {
        return "department " + name;
    }
}
