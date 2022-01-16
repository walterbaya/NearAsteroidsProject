package com.example.Asteroids.model;

import lombok.*;

@Data
public class Asteroid {
    private String id;
    private String neo_reference_id;
    private String name;
    private String nasaJplUrl;
    private String absoluteMagnitudeH;
    private String estimatedDiameterMinMeters;
    private String estimatedDiameterMinFeet;
    private String estimatedDiameterMaxMeters;
    private String estimatedDiameterMaxFeet;
    private String isPotentiallyHazardousAsteroid;
    private String orbitingBody;
    private String missDistanceMiles;
    private String missDistanceKilometers;
    private String closeApproachDateFull;
    private String relativeVelocityKilometersPerHour;
    private String relativeVelocityMilesPerHour;
    private String isSentryObject;
}