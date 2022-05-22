package uz.isystem.Clinika.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.isystem.Clinika.dto.PatientDto;
import uz.isystem.Clinika.exception.BadRequest;
import uz.isystem.Clinika.model.Patient;
import uz.isystem.Clinika.repository.PatientRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public PatientDto create(PatientDto patientDto){
        Patient patient = new Patient();
        convertDtoToEntity(patientDto,patient);
        patient.setStatus(true);
        patient.setCreateAt(LocalDateTime.now());
        patientRepository.save(patient);
        patientDto.setId(patient.getId());
        return patientDto;
    }

    public PatientDto get(Integer id){
        Patient patient = getEntity(id);
        PatientDto patientDto = new PatientDto();
        convertEntityToDto(patient,patientDto);
        return patientDto;
    }

    public boolean update(Integer id,PatientDto patientDto){
        Patient update = getEntity(id);
        convertDtoToEntity(patientDto,update);
        patientRepository.save(update);
        return true;
    }

    public boolean delete(Integer id){
        Patient patient = getEntity(id);
        patientRepository.delete(patient);
        return true;
    }

    private void convertEntityToDto(Patient entity, PatientDto dto) {
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setBirthday(entity.getBirthday());
        dto.setContact(entity.getContact());
    }

    public Patient getEntity(Integer id) {
        Optional<Patient> optional = patientRepository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequest("Patient not found");
        }
        return optional.get();
    }

    private void convertDtoToEntity(PatientDto dto, Patient entity) {
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setBirthday(dto.getBirthday());
        entity.setAge(LocalDate.now().getYear() - dto.getBirthday().getYear());
        entity.setContact(dto.getContact());
    }

    public List<PatientDto> findAllByPage(Integer page,Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Patient> resultPage = patientRepository.findAll(pageable);
        List<PatientDto> response = new LinkedList<>();
        for (Patient patient:resultPage) {
            PatientDto dto = new PatientDto();
            convertEntityToDto(patient,dto);
            response.add(dto);
        }
        return response;
    }

}
