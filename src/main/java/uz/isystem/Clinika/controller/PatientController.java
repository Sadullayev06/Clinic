package uz.isystem.Clinika.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.isystem.Clinika.dto.PatientDto;

import uz.isystem.Clinika.service.PatientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid PatientDto patient){
        PatientDto result = patientService.create(patient);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        PatientDto result = patientService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid PatientDto patient){
        boolean result = patientService.update(id,patient);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = patientService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam("s") Integer size,
                                    @RequestParam("p") Integer page){
        List<PatientDto> result = patientService.findAllByPage(page,size);
        return ResponseEntity.ok(result);
    }

}
