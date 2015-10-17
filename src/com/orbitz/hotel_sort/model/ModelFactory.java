package com.orbitz.hotel_sort.model;

import org.json.simple.JSONObject;

public class ModelFactory {
	private static ModelFactory singleton = new ModelFactory();

	private ModelFactory() {

	}

	public static ModelFactory getInstance() {
		return singleton;
	}

	public Tax newTax(JSONObject json) {
		TaxImpl tax = new TaxImpl();
		tax.populate(json);
		return tax;
	}

	public CenterLocation newCenterLocation(JSONObject json) {
		CenterLocationImpl centerLocation = new CenterLocationImpl();
		centerLocation.populate(json);
		return centerLocation;
	}

	public Hotel newHotel(CenterLocation centerLocation, JSONObject json) {
		HotelImpl hotel = new HotelImpl(centerLocation);
		hotel.populate(json);
		return hotel;
	}

	public Rate newRate(JSONObject json) {
		RateImpl rate = new RateImpl();
		rate.populate(json);
		return rate;
	}

	public UserRating newUserRating(JSONObject json) {
		UserRatingImpl userRating = new UserRatingImpl();
		userRating.populate(json);
		return userRating;
	}
	
	Location newLocation(JSONObject json) {
		LocationImpl location = new LocationImpl();
		location.populate(json);
		return location;
	}
}
