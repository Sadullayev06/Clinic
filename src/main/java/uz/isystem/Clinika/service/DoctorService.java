package uz.isystem.Clinika.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.isystem.Clinika.model.Doctor;
import uz.isystem.Clinika.repository.DoctorRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor get(Integer id){
        return check(id);
    }

    public Doctor create(Doctor doctor) {
        doctor.setStatus(true);
        doctor.setCreateAt(LocalDateTime.now());
        doctorRepository.save(doctor);
        return doctor;
    }

    public Doctor check(Integer id) {
        Optional <Doctor> optional = doctorRepository.findById(id);
        if (optional.isEmpty()){
            throw new IllegalArgumentException("Doctor not found");
        }
        return optional.get();
    }

    public Doctor update(Integer id,Doctor doctor) {
        Doctor old = get(id);
        old.setName(doctor.getName());
        old.setSurname(doctor.getSurname());
        old.setContact(doctor.getContact());
        old.setDirection(doctor.getDirection());
        old.setExperience(doctor.getExperience());
        old.setUpdateAt(LocalDateTime.now());
        doctorRepository.save(old);
        return old;
    }

    public boolean delete(Integer id) {
        Doctor doctor = get(id);
        doctorRepository.delete(doctor);
        return true;
    }
}
