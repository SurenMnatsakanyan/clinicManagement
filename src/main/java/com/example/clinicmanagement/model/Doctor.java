package com.example.clinicmanagement.model;
import com.example.clinicmanagement.dto.DoctorDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
@Table
public class Doctor {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;


    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;


    @ManyToMany(cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "doctor_specification",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specification_id")
    )
    private Set<Specification> specifications =  new HashSet<>();
    @NotNull
    private LocalDate date;
    @Min(0)
    @Max(80)
    private int yearsOfExperience;
    @Column(
            columnDefinition = "TEXT"
    )
    @NotBlank
    private String description;

    public void setDoctor(Doctor doctor){
        this.firstName = doctor.firstName;
        this.lastName = doctor.lastName;
        this.date =doctor.date;
        this.yearsOfExperience = doctor.yearsOfExperience;
        this.description = doctor.description;
    }

    public void setDoctorDto(DoctorDTO doctor){
        this.firstName = doctor.getFirstName();
        this.lastName = doctor.getLastName();
        this.date =doctor.getDate();
        this.yearsOfExperience = doctor.getYearsOfExperience();
        this.description = doctor.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Set<Specification> specifications) {
        this.specifications = specifications;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return yearsOfExperience == doctor.yearsOfExperience && Objects.equals(id, doctor.id) && Objects.equals(firstName, doctor.firstName) && Objects.equals(lastName, doctor.lastName) && Objects.equals(specifications, doctor.specifications) && Objects.equals(date, doctor.date) && Objects.equals(description, doctor.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, specifications, date, yearsOfExperience, description);
    }
}
