package dev.Innocent.DTO.request;

import lombok.Data;

@Data
public class PropertyDTO {
    private Long id;
    private String name;
    private String description;
    private String address;
    private double pricePerNight;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private boolean isAvailable;
    private boolean drinkAllowed;
    private boolean petAllowed;
    private int maxCheckoutTimeInNights;
    private double extraCharges;
}