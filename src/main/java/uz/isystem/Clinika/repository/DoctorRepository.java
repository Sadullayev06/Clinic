package uz.isystem.Clinika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.isystem.Clinika.model.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

}
