package uz.isystem.Clinika.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.isystem.Clinika.dto.DoctorDto;
import uz.isystem.Clinika.service.DoctorService;

import javax.validation.Valid;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid DoctorDto doctor){
        DoctorDto result = doctorService.create(doctor);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        DoctorDto result = doctorService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid DoctorDto doctor){
        boolean result = doctorService.update(id, doctor);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = doctorService.delete(id);
        return ResponseEntity.ok(result);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<?> getAll(@RequestParam("s") Integer size,
                                    @RequestParam("p") Integer page){
        List<DoctorDto> result = doctorService.findAllByPage(page,size);
        return ResponseEntity.ok(result);
    }*/


    /*@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Doctor doctor){
        Doctor result = doctorService.create(doctor);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        Doctor result = doctorService.get(id);
        return ResponseEntity.ok(result);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody Doctor doctor){
        Doctor result = doctorService.update(id,doctor);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = doctorService.delete(id);
        return ResponseEntity.ok(result);
    }*/
}
