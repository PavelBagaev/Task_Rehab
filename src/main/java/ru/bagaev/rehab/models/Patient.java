package ru.bagaev.rehab.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Component
public class Patient {

    @NotBlank(message = "Name shouldn't be empty")
    @NotNull
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters")
    private String name;

    private String diagnosis;

    @NotBlank(message = "Insurance number shouldn't be empty")
    @NotNull
    @Pattern(regexp = "\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}",
            message = "Insurance number should contain 4 groups of 4 digits separated by spaces")
    private String insuranceNumber;

    @NotBlank(message = "Doctor's name number shouldn't be empty")
    @NotNull
    @Size(min = 2, max = 45, message = "Doctor's name should be between 2 and 45 characters")
    private String doctorsName;

    private boolean status;

    public Patient() {
    }

    public Patient(String name, String diagnosis, String insuranceNumber, String doctorsName, boolean status) {
        this.name = name;
        this.diagnosis = diagnosis;
        this.insuranceNumber = insuranceNumber;
        this.doctorsName = doctorsName;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getDoctorsName() {
        return doctorsName;
    }

    public void setDoctorsName(String doctorsName) {
        this.doctorsName = doctorsName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
