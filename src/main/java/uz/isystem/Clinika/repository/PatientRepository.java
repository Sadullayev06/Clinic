package uz.isystem.Clinika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.isystem.Clinika.model.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
}
