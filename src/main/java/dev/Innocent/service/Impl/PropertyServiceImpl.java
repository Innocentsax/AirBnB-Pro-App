package dev.Innocent.service.Impl;

import dev.Innocent.DTO.request.PropertyDTO;
import dev.Innocent.exception.PropertyNotFoundException;
import dev.Innocent.mapper.PropertyMapper;
import dev.Innocent.model.Property;
import dev.Innocent.model.User;
import dev.Innocent.repository.PropertyRepository;
import dev.Innocent.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    @Override
    public PropertyDTO createProperty(PropertyDTO propertyDTO, User user) {
        Property property = propertyMapper.mapToEntity(propertyDTO, user);
        Property savedProperty = propertyRepository.save(property);
        return propertyMapper.mapToDTO(savedProperty);
    }

    public List<PropertyDTO> getAllProperties(Integer pageNumber, Integer pageSize, String sortBy, String order, User user) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        List<Property> properties = propertyRepository.findAll(pageable).getContent();
        return properties.stream().map(propertyMapper::mapToDTO).collect(Collectors.toList());
    }

    public PropertyDTO getPropertyById(Long id) {
        Property property = propertyRepository.findById(id).orElseThrow(() ->
                new PropertyNotFoundException("Property not found with id: " + id));
        return property != null ? propertyMapper.mapToDTO(property) : null;
    }


    public PropertyDTO updateProperty(Long id, PropertyDTO propertyDetails) {
        Property property = propertyRepository.findById(id).orElseThrow(() ->
                new PropertyNotFoundException("Property not found with id: " + id));
        if (property != null) {
            property.setName(propertyDetails.getName());
            property.setDescription(propertyDetails.getDescription());
            property.setAddress(propertyDetails.getAddress());
            property.setPricePerNight(propertyDetails.getPricePerNight());
            property.setNumberOfBedrooms(propertyDetails.getNumberOfBedrooms());
            property.setNumberOfBathrooms(propertyDetails.getNumberOfBathrooms());
            property.setAvailable(propertyDetails.isAvailable());
            property.setDrinkAllowed(propertyDetails.isDrinkAllowed());
            property.setPetAllowed(propertyDetails.isPetAllowed());
            property.setMaxCheckoutTimeInNights(propertyDetails.getMaxCheckoutTimeInNights());
            property.setExtraCharges(propertyDetails.getExtraCharges());
            Property updatedProperty = propertyRepository.save(property);
            return propertyMapper.mapToDTO(updatedProperty);
        }
        return null;
    }

    public String deleteProperty(Long id) {
        Property property =  propertyRepository.findById(id).orElseThrow(() ->
                new PropertyNotFoundException("Property not found with id: " + id));
        propertyRepository.delete(property);
        return "Property deleted successfully with Id = " + id;
    }

    public List<PropertyDTO> getPropertiesByUserId(User user, Integer pageNumber, Integer pageSize, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        List<Property> properties = propertyRepository.findByUser(user, pageable).getContent();
        return properties.stream().map(propertyMapper::mapToDTO).collect(Collectors.toList());
    }
}
