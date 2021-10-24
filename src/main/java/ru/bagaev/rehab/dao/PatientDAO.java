package ru.bagaev.rehab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bagaev.rehab.models.Patient;

import java.util.List;

@Component
public class PatientDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PatientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Patient> getAll() {
        return jdbcTemplate.query("SELECT * FROM Patient", new PatientMapper());
    }

    public Patient getPatient(String insuranceNumber) {
        return jdbcTemplate.query("SELECT * FROM Patient where insuranceNumber=?", new Object[]{insuranceNumber},
                new PatientMapper()).stream().findAny().orElse(null);
    }

    public void save(Patient patient) {
        jdbcTemplate.update("INSERT INTO Patient(name, diagnosis, insuranceNumber, doctorsName) VALUES (?, ?, ?, ?)",
                patient.getName(), patient.getDiagnosis(), patient.getInsuranceNumber(), patient.getDoctorsName());
    }

    public void update(String insuranceNumber, Patient updatedPatient) {
        jdbcTemplate.update("UPDATE Patient SET name=?, diagnosis=?, insuranceNumber=?, doctorsName=?, status=? where insuranceNumber=?",
                updatedPatient.getName(), updatedPatient.getDiagnosis(), updatedPatient.getInsuranceNumber(),
                updatedPatient.getDoctorsName(), updatedPatient.getStatus(), insuranceNumber);
    }

    public void delete(String insuranceNumber) {
        jdbcTemplate.update("DELETE FROM Patient WHERE insuranceNumber=?", insuranceNumber);
    }
}
