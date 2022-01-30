package alexandr.service;

import org.springframework.stereotype.Service;

@Service
public class Validator {

    private final String namePattern = "[A-Z][a-z]{2,30}";
    private final String phonePattern = "[0-9]{2,11}";

    public boolean checkName(String name) {
        return name.matches(namePattern);
    }
    public boolean checkPhone(String phone) {
        return phone.matches(phonePattern);
    }
}
