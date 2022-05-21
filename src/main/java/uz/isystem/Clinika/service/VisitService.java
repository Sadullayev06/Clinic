package uz.isystem.Clinika.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.isystem.Clinika.dto.VisitDto;
import uz.isystem.Clinika.model.Visit;
import uz.isystem.Clinika.repository.VisitRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;


    public VisitDto create(VisitDto dto) {
        Visit visit = new Visit();
        //TODO:check doctor
        doctorService.check(dto.getDoctorId());
        visit.setDoctorId(dto.getDoctorId());
        //TODO:check patient
        patientService.getEntity(dto.getPatientId());
        visit.setPatientId(dto.getPatientId());
        visit.setDiagnosis(dto.getDiagnosis());
        visit.setCreateAt(LocalDateTime.now());
        visit.setStatus(true);
        visitRepository.save(visit);
        return dto;
    }

    public VisitDto get(Integer id){
        Visit visit = getEntity(id);
        VisitDto visitDto = new VisitDto();
        visitDto.setPatient(patientService.get(visit.getPatientId()));
        visitDto.setDoctor(doctorService.get(visit.getDoctorId()));
        visitDto.setDiagnosis(visit.getDiagnosis());
        return visitDto;
    }

    public Visit getEntity(Integer id) {
        Optional <Visit> optional = visitRepository.findById(id);
        if (optional.isEmpty()){
            throw new  IllegalArgumentException("User not found");
        }
        return optional.get();
    }

    public boolean update(Integer id, VisitDto dto) {

        Visit visit = getEntity(id);
        //TODO:check doctor
        doctorService.check(dto.getDoctorId());
        visit.setDoctorId(dto.getDoctorId());
        //TODO:check patient
        patientService.getEntity(dto.getPatientId());
        visit.setPatientId(dto.getPatientId());
        visit.setDiagnosis(dto.getDiagnosis());
        visitRepository.save(visit);
        return true;
    }

    public boolean delete(Integer id) {
        Visit visit = getEntity(id);
        visitRepository.delete(visit);
        return true;
    }
}
