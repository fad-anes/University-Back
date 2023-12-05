package com.university.tn.university.Controlleur;

import com.university.tn.university.Model.Dto.FoyerDto;
import com.university.tn.university.Model.Dto.MyResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.university.tn.university.Service.FoyerServiceimpl;
import com.university.tn.university.Model.Entity.Foyer;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Foyercontrolleur {
    public final static MyResponse FOUND = new MyResponse("OBJECT FOUND");
    public final static MyResponse BAD_REQUEST = new MyResponse("BAD_REQUEST");
    public final static MyResponse NULL =  new MyResponse("OBJECT NULL DETECTED");
    public final static MyResponse NOT_FOUND = new MyResponse("OBJECT NOT_FOUND");


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FoyerServiceimpl Foyerservice;
    public  Foyercontrolleur(FoyerServiceimpl FoyerServiceimpl){
        super();
        this.Foyerservice=FoyerServiceimpl;
    }

    @GetMapping("/Foyer/AllFoyer")
    public List<Foyer> retrieveAllFoyer() {
        return Foyerservice.retrieveAllFoyers();
    }

    @PostMapping("/Foyer/AddFoyer")
    public ResponseEntity<Object> AddFoyer(@RequestBody FoyerDto FoyerDto){
        Foyer FoyerReq = modelMapper.map(FoyerDto, Foyer.class);
        ResponseEntity<Foyer> Foyer =Foyerservice.addFoyer(FoyerReq);
        if (Foyer.getStatusCodeValue() == 200) {
            return new ResponseEntity<>(FoyerReq, HttpStatus.OK);
        } else if (Foyer.getStatusCodeValue() == 400) {
            return new ResponseEntity<>(BAD_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(FOUND.getMessage(), HttpStatus.FOUND);
        }
    }

    @PutMapping("/Foyer/UpdateFoyer")
    public ResponseEntity<Object> UpdateFoyer(@RequestBody FoyerDto FoyerDto){
        Foyer FoyerReq = modelMapper.map(FoyerDto, Foyer.class);
        ResponseEntity<Foyer> Foyer = Foyerservice.updateFoyer(FoyerReq);
        if (Foyer.getStatusCodeValue() == 200) {
            return new ResponseEntity<>(FoyerReq, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/Foyer/OneFoyer/{id}")
    public Foyer OneFoyer(@PathVariable("id") Long id) {
        return Foyerservice.retrieveFoyer(id);
    }

    @PutMapping("/Foyer/Archiver/{idFoyer}")
    public void Archiver(@PathVariable("idFoyer") Long id){
        Foyerservice.archiverFoyer(id);
    }

    @PostMapping("/Foyer/addFoyerWithBloc")
    public ResponseEntity<Object> addFoyerWithBloc(@RequestBody FoyerDto FoyerDto){
        Foyer FoyerReq = modelMapper.map(FoyerDto, Foyer.class);
        ResponseEntity<Foyer> Foyer =Foyerservice.addFoyerWithBloc(FoyerReq);
        if (Foyer.getStatusCodeValue() == 200) {
            return new ResponseEntity<>(FoyerReq, HttpStatus.OK);
        }else if (Foyer.getStatusCodeValue() == 400) {
            return new ResponseEntity<>(BAD_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
