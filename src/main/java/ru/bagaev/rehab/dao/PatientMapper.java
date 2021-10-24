package ru.bagaev.rehab.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.bagaev.rehab.models.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientMapper implements RowMapper<Patient> {
    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Patient patient = new Patient();
        patient.setName(rs.getString("name"));
        patient.setDiagnosis(rs.getString("diagnosis"));
        patient.setInsuranceNumber(rs.getString("insuranceNumber"));
        patient.setDoctorsName(rs.getString("doctorsName"));
        patient.setStatus(rs.getBoolean("status"));
        return patient;
    }
}
