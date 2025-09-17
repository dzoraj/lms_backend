package lmsprojekat.dto.exportdto;

import java.util.ArrayList;
import java.util.List;

public class ValidationReport {

    private boolean valid;
    private List<String> errors;

    public ValidationReport() {
        this.errors = new ArrayList<>();
    }

    public ValidationReport(boolean valid, List<String> errors) {
        this.valid = valid;
        this.errors = (errors != null) ? errors : new ArrayList<>();
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}