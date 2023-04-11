package com.example.clinicmanagement.dto;
import com.example.clinicmanagement.model.Doctor;
import com.example.clinicmanagement.model.Specification;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Set;
@Data
public class SpecificationDTO {
    private Long id;
    @NotEmpty
    private String name;


    public SpecificationDTO() {
    }

    public SpecificationDTO(String name) {
        this.name = name;
    }

    public void setSpecification(Specification specification){
        this.id  = specification.getId();
        this.name= specification.getName();
    }
}
