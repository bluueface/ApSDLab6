package com.lab6;

import com.lab6.entity.*;
import com.lab6.repository.AppointmentRepository;
import com.lab6.repository.DentistRepository;
import com.lab6.repository.PatientRepository;
import com.lab6.repository.SurgeryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PatientRepository patientRepository;
    private final DentistRepository dentistRepository;
    private final SurgeryRepository surgeryRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public void run(String... args) throws Exception {

        // Create Address for Patient 1
        Address address1 = new Address();
        address1.setStreet("123 Main St");
        address1.setCity("Fairfield");
        address1.setState("IA");
        address1.setZipCode("52557");
        // Create Address for Patient 2
        Address address2 = new Address();
        address2.setStreet("456 North St");
        address2.setCity("Ottumwa");
        address2.setState("IA");
        address2.setZipCode("52501");

        // Create Address for Patient 3
        Address address3 = new Address();
        address3.setStreet("789 West St");
        address3.setCity("Des Moines");
        address3.setState("IA");
        address3.setZipCode("50309");

        Patient p1 = new Patient();
        p1.setPatientNo("P100");
        p1.setFullName("Gillian White");
        p1.setAddress(address1);

        Patient p2 = new Patient();
        p2.setPatientNo("P105");
        p2.setFullName("Jill Bell");
        p2.setAddress(address2);

        Patient p3 = new Patient();
        p3.setPatientNo("P108");
        p3.setFullName("Ian MacKay");
        p3.setAddress(address3);

        patientRepository.saveAll(List.of(p1, p2, p3));

        // Create Dentists
        Dentist d1 = new Dentist();
        d1.setFullName("Tony Smith");

        Dentist d2 = new Dentist();
        d2.setFullName("Helen Pearson");

        Dentist d3 = new Dentist();
        d3.setFullName("Robin Plevin");

        dentistRepository.saveAll(List.of(d1, d2, d3));

        // Create Surgeries with Address


        // Create Address for Patient 1
        Address addressS1 = new Address();
        addressS1.setStreet("56 Adele St");
        addressS1.setCity("Fairfield");
        addressS1.setState("IA");
        addressS1.setZipCode("52557");
        // Create Address for Patient 2
        Address addressS2 = new Address();
        addressS2.setStreet("400 West St");
        addressS2.setCity("Ottumwa");
        addressS2.setState("IA");
        addressS2.setZipCode("52501");

        Surgery s1 = new Surgery();
        s1.setSurgeryNo("S15");
        s1.setAddress(addressS1);

        Surgery s2 = new Surgery();
        s2.setSurgeryNo("S10");
        s2.setAddress(addressS2);

        surgeryRepository.saveAll(List.of(s1, s2));

        // Create Appointments
        Appointment a1 = new Appointment();
        a1.setPatient(p1);
        a1.setDentist(d1);
        a1.setSurgery(s1);
        a1.setAppointmentDate(LocalDateTime.of(2013, 9, 12, 10, 0));

        Appointment a2 = new Appointment();
        a2.setPatient(p2);
        a2.setDentist(d1);
        a2.setSurgery(s1);
        a2.setAppointmentDate(LocalDateTime.of(2013, 9, 12, 12, 0));

        Appointment a3 = new Appointment();
        a3.setPatient(p3);
        a3.setDentist(d2);
        a3.setSurgery(s2);
        a3.setAppointmentDate(LocalDateTime.of(2013, 9, 12, 10, 0));

        Appointment a4 = new Appointment();
        a4.setPatient(p3);
        a4.setDentist(d2);
        a4.setSurgery(s2);
        a4.setAppointmentDate(LocalDateTime.of(2013, 9, 14, 14, 0));

        Appointment a5 = new Appointment();
        a5.setPatient(p2);
        a5.setDentist(d3);
        a5.setSurgery(s1);
        a5.setAppointmentDate(LocalDateTime.of(2013, 9, 14, 16, 30));

        appointmentRepository.saveAll(List.of(a1, a2, a3, a4, a5));
    }
}