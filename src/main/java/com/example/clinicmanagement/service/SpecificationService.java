package com.example.clinicmanagement.service;
import com.example.clinicmanagement.model.Specification;
import com.example.clinicmanagement.repository.SpecificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecificationService {
    private final SpecificationRepository specificationRepository;

    public List<Specification> getSpecifications() {
        return specificationRepository.findAll();
    }

    public void addNewSpecification(Specification specification) {
        specificationRepository.save(specification);
    }

    public void deleteSpecificationById(Long specificationId) {
        specificationRepository.deleteById(specificationId);
    }

    @Transactional
    public void modifySpecificationInformation(Long specificationId, Specification specification) {
        Specification specificationAssumed = specificationRepository.findById(specificationId).orElseThrow(() -> new RuntimeException("Doctor with " + specificationId + " doesn't exist"));
        specification.setSpecification(specificationAssumed);
    }
}
