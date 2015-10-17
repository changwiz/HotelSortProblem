package com.orbitz.hotel_sort.model;

public interface Location {
	public double getLocationX();

	public double getLocationY();

	public double getLocationZ();
	
	public double getDistance(Location location);
}
