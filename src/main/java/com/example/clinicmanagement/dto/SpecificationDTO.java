package com.example.clinicmanagement.dto;

import com.example.clinicmanagement.model.Specification;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class SpecificationDTO {
    private Long id;
    @NotEmpty
    private String name;


    public SpecificationDTO() {
    }

    public SpecificationDTO(String name) {
        this.name = name;
    }

    public void setSpecification(Specification specification) {
        this.id = specification.getId();
        this.name = specification.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificationDTO that = (SpecificationDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
