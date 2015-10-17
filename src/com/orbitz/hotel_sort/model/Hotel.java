package com.orbitz.hotel_sort.model;

import java.util.List;

public interface Hotel {
	public String getName();

	public long getStarRating();

	public List<Rate> getRates();

	public List<UserRating> getUserRatings();

	public Rate getLowestRate();

	public double getAverageUserRating();

	public void addUserRating(UserRating ur);

	public void addRate(Rate rate);

	public CenterLocation getCenterLocation();
	
	public Location getLocation();
}
