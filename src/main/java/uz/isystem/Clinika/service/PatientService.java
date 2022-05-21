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

    /*public PatientDto get(Integer id){
        uz.isystem.Clinika.model.Patient patient = getEntity(id);
        PatientDto patientDto = new PatientDto();
        convertEntityToDto(patient, patientDto);
        return patientDto;
    }

    public PatientDto create(PatientDto patientDto) {
        uz.isystem.Clinika.model.Patient patient = new uz.isystem.Clinika.model.Patient();
        patient.setName(patientDto.getName());
        patient.setSurname(patientDto.getSurname());
        patient.setBirthday(patientDto.getBirthday());
        patient.setAge(LocalDate.now().getYear() - patientDto.getBirthday().getYear());
        patient.setContact(patientDto.getContact());
        //convertDtoToEntity(patientDto,patient);
        patient.setStatus(true);
        patient.setCreateAt(LocalDateTime.now());
        patientRepository.save(patient);
        patientDto.setId(patient.getId());
        return patientDto;
    }

    public uz.isystem.Clinika.model.Patient getEntity(Integer id) {
        Optional <uz.isystem.Clinika.model.Patient> optional = patientRepository.findById(id);
        if(optional.isEmpty()){
            throw new BadRequest("Patient not found");
        }
        return optional.get();
    }

    public boolean update(Integer id, PatientDto patientDto) {
        uz.isystem.Clinika.model.Patient old = getEntity(id);
        convertDtoToEntity(patientDto,old);
        patientRepository.save(old);
        return true;
    }

    public boolean delete(Integer id) {
        uz.isystem.Clinika.model.Patient patient = getEntity(id);
        patientRepository.delete(patient);
        return true;
    }

    public void convertDtoToEntity(PatientDto dto, uz.isystem.Clinika.model.Patient entity){
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setBirthday(dto.getBirthday());
        entity.setAge(LocalDate.now().getYear() - dto.getBirthday().getYear());
        entity.setContact(dto.getContact());
        entity.setUpdateAt(LocalDateTime.now());
    }

    public void convertEntityToDto(uz.isystem.Clinika.model.Patient entity, PatientDto dto){
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setBirthday(LocalDateTime.now().toLocalDate());
        dto.setContact(entity.getContact());
    }

    public List<PatientDto> findAllByPage(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<uz.isystem.Clinika.model.Patient> result = patientRepository.findAll(pageable);
        List<PatientDto> response = new LinkedList<>();
        for (uz.isystem.Clinika.model.Patient patient:result) {
            PatientDto dto = new PatientDto();
            convertEntityToDto(patient,dto);
            response.add(dto);
        }
        return response;
    }*/
}
