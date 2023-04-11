package com.example.clinicmanagement.controller;
import com.example.clinicmanagement.model.Specification;
import com.example.clinicmanagement.dto.SpecificationDTO;
import com.example.clinicmanagement.service.SpecificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("api/specifications")
public class SpecificationController {
    private final SpecificationService specificationService;

    @Autowired
    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @GetMapping
    public List<SpecificationDTO> getAllSpecifications(){
        List<Specification> specifications = specificationService.getSpecifications();
        List<SpecificationDTO> specificationDTOs = new ArrayList<>();
        int size = specifications.size();
        for(int i=0; i<size; i++){
            specificationDTOs.add(new SpecificationDTO());
        }

        for(int i=0; i<size; i++){
            SpecificationDTO specificationDTO = specificationDTOs.get(i);
            specificationDTO.setSpecification(specifications.get(i));
        }
        return specificationDTOs;
    }

    @PostMapping
    public ResponseEntity<SpecificationDTO> registerNewSpecification(@Valid @RequestBody SpecificationDTO specificationDTO){
       Specification specification = new Specification();
       specification.setSpecificationDTO(specificationDTO);
        specificationService.addNewSpecification(specification);
        return new ResponseEntity<>(specificationDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{specificationId}")
    public void deleteSpecification(@PathVariable("specificationId") Long specificationId){
        specificationService.deleteSpecificationById(specificationId);
    }

    @PutMapping(path = "/{specificationId}")
    public ResponseEntity<SpecificationDTO> updateSpecification(@PathVariable("specificationId") Long specificationId,
                                                  @RequestBody @Valid SpecificationDTO specificationDTO
    ){
        Specification specification = new Specification();
        specification.setSpecificationDTO(specificationDTO);
        specificationService.modifySpecificationInformation(specificationId,specification);
        return  new ResponseEntity<>(specificationDTO,HttpStatus.OK);
    }
}
