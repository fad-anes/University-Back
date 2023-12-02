package com.university.tn.university.Controlleur;

import com.university.tn.university.Model.Dto.MyResponse;
import com.university.tn.university.Model.Dto.UniversityDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.university.tn.university.Service.UniversteServiceimpl;
import com.university.tn.university.Model.Entity.University;
import java.util.List;

@RestController
public class UniversteControlleur {
    public final static MyResponse FOUND = new MyResponse("OBJECT FOUND");
    public final static MyResponse BAD_REQUEST = new MyResponse("BAD_REQUEST");
    public final static MyResponse NULL =  new MyResponse("OBJECT NULL DETECTED");
    public final static MyResponse NOT_FOUND = new MyResponse("OBJECT NOT_FOUND");


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UniversteServiceimpl UniverstyService;

    public UniversteControlleur(UniversteServiceimpl UniversteServiceimpl){
        super();
        this.UniverstyService=UniversteServiceimpl;
    }

    @GetMapping("/University/AllUniversty")
    public List<University> retrieveAllUniversty() {
        return UniverstyService.retrieveAllUniversities();
    }

    @PostMapping("/University/AddUniversty")
    public ResponseEntity<Object> AddUniversty(@RequestBody University e) {
        ResponseEntity<University> University = UniverstyService.addUniversity(e);
        if (University.getStatusCodeValue() == 200) {
            UniversityDto UniversityDto = modelMapper.map(University.getBody(), UniversityDto.class);
            return new ResponseEntity<>(UniversityDto, HttpStatus.OK);
        } else if (University.getStatusCodeValue() == 302) {
            return new ResponseEntity<>(FOUND.getMessage(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(NULL.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/University/UpdateUniversty")
    public ResponseEntity<Object>  UpdateUniversty(@RequestBody University e){
        ResponseEntity<University> University = UniverstyService.updateUniversity(e);
        if (University.getStatusCodeValue() == 200) {
            UniversityDto UniversityDto = modelMapper.map(University.getBody(), UniversityDto.class);
            return new ResponseEntity<>(UniversityDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/University/OneUniversty/{id}")
    public University OneUniversty(@PathVariable("id") Long id) {
        return UniverstyService.retrieveUniversity(id);
    }

    @DeleteMapping("/University/DeleteUniversty/{id}")
    public void DeleteUniversty(@PathVariable("id") Long id) {
        UniverstyService.removeUniversity(id);
    }

    @PutMapping("/University/affecterFoyerAUniversite/{id}/{nom}")
    public ResponseEntity<Object> affecterFoyerAUniversite(@PathVariable("id") Long id,@PathVariable("nom") String nom){
        ResponseEntity<University> University= UniverstyService.affecterFoyerAUniversite(id,nom);
        if (University.getStatusCodeValue() == 200) {
            UniversityDto UniversityDto = modelMapper.map(University.getBody(), UniversityDto.class);
            return new ResponseEntity<>(UniversityDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @PutMapping("/University/desaffecterFoyerAUniversite/{idf}/{idu}")
    public ResponseEntity<Object> affecterFoyerAUniversite(@PathVariable("idf") Long idf,@PathVariable("idu") Long idu){
        ResponseEntity<University> University= UniverstyService.desaffecterFoyerAUniversite(idf,idu);
        if (University.getStatusCodeValue() == 200) {
            UniversityDto UniversityDto = modelMapper.map(University.getBody(), UniversityDto.class);
            return new ResponseEntity<>(UniversityDto, HttpStatus.OK);
        }else if (University.getStatusCodeValue() == 400) {
            return new ResponseEntity<>(BAD_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);

        }
    }
}
