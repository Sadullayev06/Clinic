package uz.isystem.Clinika.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.isystem.Clinika.model.Visit;
import uz.isystem.Clinika.repository.VisitRepository;

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


    public Visit create(Visit visit) {
        //TODO:check doctor
        doctorService.check(visit.getDoctorId());
        //TODO:check patient
        patientService.getEntity(visit.getPatientId());

        visit.setStatus(true);
        visit.setCreateAt(LocalDateTime.now());
        visitRepository.save(visit);
        return visit;
    }

    public Visit get(Integer id) {
        Optional <Visit> optional = visitRepository.findById(id);
        if (optional.isEmpty()){
            throw new  IllegalArgumentException("User not found");
        }
        return optional.get();
    }

    public Visit update(Integer id, Visit visit) {
        Visit old  = get(id);
        //TODO:check doctor
        doctorService.check(visit.getDoctorId());
        old.setDoctorId(visit.getDoctorId());
        //TODO:check patient
        patientService.getEntity(visit.getPatientId());
        old.setPatientId(visit.getPatientId());

        old.setDiagnosis(visit.getDiagnosis());
        visitRepository.save(old);
        return old;
    }

    public boolean delete(Integer id) {
        Visit visit = get(id);
        visitRepository.delete(visit);
        return true;
    }
}
