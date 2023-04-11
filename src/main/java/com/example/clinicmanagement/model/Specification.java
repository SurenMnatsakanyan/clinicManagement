package com.example.clinicmanagement.model;
import com.example.clinicmanagement.dto.SpecificationDTO;
import jakarta.persistence.*;

@Entity
@Table
public class Specification {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            nullable = false,
            unique = true
    )
    private String name;

    public Specification() {

    }

    public Specification(String name) {
        this.name = name;
    }
    public void setSpecificationDTO(SpecificationDTO specificationDTO){
        this.id = specificationDTO.getId();
        this.name = specificationDTO.getName();
    }
    public void setSpecification(Specification specification){
        this.id = specification.getId();
        this.name = specification.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
