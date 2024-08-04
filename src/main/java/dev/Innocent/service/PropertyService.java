package dev.Innocent.service;

import dev.Innocent.DTO.request.PropertyDTO;

import java.util.List;

public interface PropertyService {
    PropertyDTO createProperty(PropertyDTO propertyDTO);
    List<PropertyDTO> getAllProperties(Integer pageNumber, Integer pageSize, String sortBy, String order);
    PropertyDTO getPropertyById(Long id);
    PropertyDTO updateProperty(Long id, PropertyDTO propertyDetails);
    String deleteProperty(Long id);
    List<PropertyDTO> getPropertiesByUserId(Long userId, Integer pageNumber, Integer pageSize, String sortBy, String order);
}
