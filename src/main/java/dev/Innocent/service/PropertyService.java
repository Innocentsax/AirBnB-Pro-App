package dev.Innocent.service;

import dev.Innocent.DTO.request.PropertyDTO;
import dev.Innocent.model.User;

import java.util.List;

public interface PropertyService {
    PropertyDTO createProperty(PropertyDTO propertyDTO, User user);
    List<PropertyDTO> getAllProperties(Integer pageNumber, Integer pageSize, String sortBy, String order, User user);
    PropertyDTO getPropertyById(Long id);
    PropertyDTO updateProperty(Long id, PropertyDTO propertyDetails);
    String deleteProperty(Long id);
    List<PropertyDTO> getPropertiesByUserId(User user, Integer pageNumber, Integer pageSize, String sortBy, String order);
}
