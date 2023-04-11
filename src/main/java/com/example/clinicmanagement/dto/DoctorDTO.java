package com.example.clinicmanagement.dto;
import com.example.clinicmanagement.model.Doctor;
import com.example.clinicmanagement.model.Specification;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class DoctorDTO {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<@NotNull Long> specificationIds = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<SpecificationDTO> specificationDTOs = new HashSet<>();
    @NotNull
    private LocalDate date;
    @Range(
            min = 0,
            max = 80
    )
    private int yearsOfExperience;
    @NotBlank
    private String description;

    public void toDTO(Doctor doctor){
        this.id  = doctor.getId();
        this.firstName = doctor.getFirstName();
        this.lastName = doctor.getLastName();
        this.date =doctor.getDate();
        this.yearsOfExperience = doctor.getYearsOfExperience();
        this.description = doctor.getDescription();
        for(Specification specification: doctor.getSpecifications()){
            SpecificationDTO specificationDTO = new SpecificationDTO();
            specificationDTO.setSpecification(specification);
            specificationDTOs.add(specificationDTO);
        }
    }

    public Set<SpecificationDTO> getSpecificationDTOs() {
        return specificationDTOs;
    }

    public void setSpecificationDTOs(Set<SpecificationDTO> specificationDTOs) {
        this.specificationDTOs = specificationDTOs;
    }

    public List<Long> getSpecificationIds() {
        return specificationIds;
    }

    public void setSpecificationIds(List<Long> specificationIds) {
        this.specificationIds = specificationIds;
    }
}
