package uz.isystem.Clinika.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import uz.isystem.Clinika.dto.DoctorDto;
import uz.isystem.Clinika.dto.PatientDto;
import uz.isystem.Clinika.exception.BadRequest;
import uz.isystem.Clinika.model.Doctor;
import uz.isystem.Clinika.repository.DoctorRepository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorDto create(DoctorDto doctorDto){
        Doctor doctor = new Doctor();
        convertDtoToEntity(doctorDto,doctor);
        doctor.setStatus(true);
        doctor.setCreateAt(LocalDateTime.now());
        doctorRepository.save(doctor);
        doctorDto.setId(doctor.getId());
        return doctorDto;
    }

    public DoctorDto get(Integer id){
        Doctor doctor = check(id);
        DoctorDto doctorDto = new DoctorDto();
        convertEntityToDto(doctor,doctorDto);
        return doctorDto;
    }

    public boolean update(Integer id,DoctorDto doctorDto){
        Doctor update = check(id);
        convertDtoToEntity(doctorDto,update);
        doctorRepository.save(update);
        return true;
    }

    public boolean delete(Integer id){
        Doctor doctor = check(id);
        doctorRepository.delete(doctor);
        return true;
    }

    public Doctor check(Integer id) {
        Optional<Doctor> optional = doctorRepository.findById  (id);
        if (optional.isEmpty()){
            throw new BadRequest("Doctor not found");//throw new BadRequest("Doctor not found");
        }
        return optional.get();
    }


    private void convertEntityToDto(Doctor entity, DoctorDto dto) {
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setDirection(entity.getDirection());
        dto.setContact(entity.getContact());
        dto.setExperience(entity.getExperience());
    }

    private void convertDtoToEntity(DoctorDto dto, Doctor entity) {
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setContact(dto.getContact());
        entity.setDirection(dto.getDirection());
        entity.setExperience(dto.getExperience());
    }

}
