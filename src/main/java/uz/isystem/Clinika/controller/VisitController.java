package uz.isystem.Clinika.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.isystem.Clinika.model.Visit;
import uz.isystem.Clinika.service.DoctorService;
import uz.isystem.Clinika.service.PatientService;
import uz.isystem.Clinika.service.VisitService;

@RestController
@RequestMapping("/visit")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Visit visit){
        Visit result = visitService.create(visit);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        Visit result = visitService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody Visit visit){
        Visit result = visitService.update(id,visit);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = visitService.delete(id);
        return ResponseEntity.ok(result);
    }
}
