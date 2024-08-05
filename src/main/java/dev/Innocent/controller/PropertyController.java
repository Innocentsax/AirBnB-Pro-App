package dev.Innocent.controller;

import dev.Innocent.DTO.ApiResponseBody;
import dev.Innocent.DTO.request.PropertyDTO;
import dev.Innocent.enums.AppConstants;
import dev.Innocent.enums.InternalCodeEnum;
import dev.Innocent.model.User;
import dev.Innocent.service.PropertyService;
import dev.Innocent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponseBody<PropertyDTO>> createProperty(
            @RequestBody PropertyDTO propertyDTO) throws Exception {
        PropertyDTO createdProperty = propertyService.createProperty(propertyDTO);
        return ResponseEntity.status(InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_003.getHttpStatus())
                .body(new ApiResponseBody<>(true, InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_003.getCodeDescription(),
                        InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_003, createdProperty));
    }

    @GetMapping
    public ResponseEntity<ApiResponseBody<List<PropertyDTO>>> getAllProperties(
            @RequestHeader("Authorization") String jwt,
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_ORDER, required = false) String order) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<PropertyDTO> properties = propertyService.getAllProperties(pageNumber, pageSize, sortBy, order, user);
        return ResponseEntity.status(InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_001.getHttpStatus())
                .body(new ApiResponseBody<>(true, InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_001.getCodeDescription(), InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_001, properties));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseBody<PropertyDTO>> getPropertyById(
            @PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        PropertyDTO property = propertyService.getPropertyById(id);
        if (property == null) {
            return ResponseEntity.status(InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_002.getHttpStatus())
                    .body(new ApiResponseBody<>(false, InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_002.getCodeDescription(),
                            InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_002, null));
        }
        return ResponseEntity.status(InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_001.getHttpStatus())
                .body(new ApiResponseBody<>(true, InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_001.getCodeDescription(),
                        InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_001, property));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseBody<PropertyDTO>> updateProperty(@PathVariable Long id, @RequestBody PropertyDTO propertyDTO) {
        PropertyDTO updatedProperty = propertyService.updateProperty(id, propertyDTO);
        if (updatedProperty == null) {
            return ResponseEntity.status(InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_002.getHttpStatus())
                    .body(new ApiResponseBody<>(false, InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_002.getCodeDescription(),
                            InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_002, null));
        }
        return ResponseEntity.status(InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_004.getHttpStatus())
                .body(new ApiResponseBody<>(true, InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_004.getCodeDescription(),
                        InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_004, updatedProperty));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseBody<String>> deleteProperty(
            @PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        propertyService.deleteProperty(id);
        return ResponseEntity.status(InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_005.getHttpStatus())
                .body(new ApiResponseBody<>(true, InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_005.getCodeDescription(),
                        InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_005, null));
    }

    @GetMapping("/users/{userId}/properties")
    public ResponseEntity<ApiResponseBody<List<PropertyDTO>>> getPropertiesByUserId(
            @RequestHeader("Authorization") String jwt,
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_ORDER, required = false) String order) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<PropertyDTO> properties = propertyService.getPropertiesByUserId(user.getId(), pageNumber, pageSize, sortBy, order);
        return ResponseEntity.status(InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_006.getHttpStatus())
                .body(new ApiResponseBody<>(true, InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_006.getCodeDescription(),
                        InternalCodeEnum.PROPERTY_MANAGEMENT_CODE_006, properties));
    }
}
