package ru.bagaev.rehab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bagaev.rehab.dao.PatientDAO;
import ru.bagaev.rehab.models.Patient;

import javax.validation.Valid;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientDAO patientDAO;

    public PatientController(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @GetMapping()
    public String showAllPatients(Model model) {
        model.addAttribute("patients", patientDAO.getAll());
        return "patients/all-patients";
    }

    @GetMapping("/{insuranceNumber}")
    public String showPatient(@PathVariable("insuranceNumber") String insuranceNumber, Model model) {
        model.addAttribute("patient", patientDAO.getPatient(insuranceNumber));
        return "patients/patient";
    }

    @GetMapping("/add-user-form")
    public String getAddPatientForm(@ModelAttribute ("patient") Patient patient) {
        return "patients/add-user-form";
    }

    @PostMapping()
    public String createNewPatient(@ModelAttribute("patient") @Valid Patient patient,
                                   BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "patients/add-user-form";

        patientDAO.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/{insuranceNumber}/edit-patient")
    public String editPatient(@PathVariable("insuranceNumber") String insuranceNumber, Model model) {
        model.addAttribute("patient", patientDAO.getPatient(insuranceNumber));
        return "patients/edit-patient";
    }

    @PatchMapping("/{insuranceNumber}")
    public String update(@ModelAttribute("patient") @Valid Patient patient,
                         BindingResult bindingResult,
                         @PathVariable("insuranceNumber") String insuranceNumber) {

        if(bindingResult.hasErrors())
            return "patients/edit-patient";

        patientDAO.update(insuranceNumber, patient);
        return "redirect:/patients/{insuranceNumber}";
    }

    @DeleteMapping("/{insuranceNumber}")
    public String delete(@PathVariable("insuranceNumber") String insuranceNumber) {
        patientDAO.delete(insuranceNumber);
        return "redirect:/patients";
    }
}
