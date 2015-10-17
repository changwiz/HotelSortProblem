package com.orbitz.hotel_sort.model;

import java.util.List;

public interface CenterLocation {
	public String getCenterName();

	public long getCenterId();

	public List<Tax> getTaxes();

	public List<Hotel> getHotels();
	
	public double getTotalTaxRate();
	
	public Location getLocation();
}
