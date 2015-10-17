package com.orbitz.hotel_sort.model;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class HotelImpl implements Hotel {
	private String hotelName;
	private long starRating;
	private List<Rate> rates;
	private List<UserRating> userRatings;
	private Rate lowestRate;
	private double averageRating = -1d;
	private CenterLocation centerLocation;
	private Location location;

	HotelImpl(CenterLocation centerLocation) {
		this.centerLocation = centerLocation;
	}

	void populate(JSONObject json) {
		if (json != null) {
			hotelName = (String) json.get("name");
			location = ModelFactory.getInstance().newLocation(json);
			starRating = (long) json.get("star_rating");

			rates = new ArrayList<Rate>();
			lowestRate = null;
			JSONArray jArray = (JSONArray) json.get("rates");
			for (int i = 0; i < jArray.size(); ++i) {
				JSONObject obj = (JSONObject) jArray.get(i);
				Rate rate = ModelFactory.getInstance().newRate(obj);
				rates.add(rate);
			}

			userRatings = new ArrayList<UserRating>();
			averageRating = -1d;
			jArray = (JSONArray) json.get("user_ratings");
			for (int i = 0; i < jArray.size(); ++i) {
				JSONObject obj = (JSONObject) jArray.get(i);
				UserRating userRating = ModelFactory.getInstance().newUserRating(obj);
				userRatings.add(userRating);
			}
		}
	}

	@Override
	public String getName() {
		return hotelName;
	}

	@Override
	public long getStarRating() {
		return starRating;
	}

	@Override
	public List<Rate> getRates() {
		return rates;
	}

	@Override
	public List<UserRating> getUserRatings() {
		return userRatings;
	}

	@Override
	public Rate getLowestRate() {
		if (lowestRate == null && rates != null) {
			double lowestPrice = 0;
			for (Rate r : rates) {
				double price = r.getRoomPrice(centerLocation.getTotalTaxRate());
				if (Double.compare(lowestPrice, 0) == 0 || Double.compare(price, lowestPrice) < 0) {
					lowestPrice = price;
					lowestRate = r;
				}
			}
		}
		return lowestRate;
	}

	@Override
	public double getAverageUserRating() {
		if (Double.compare(averageRating, 0) < 0 && userRatings != null) {
			double sum = 0;
			for (UserRating r : userRatings) {
				sum += r.getUserRating();
			}
			averageRating = sum / userRatings.size();
		}
		return Double.compare(averageRating, 0) >= 0 ? averageRating : 0;
	}

	@Override
	public void addUserRating(UserRating ur) {
		if (ur != null) {
			if (userRatings == null) {
				userRatings = new ArrayList<UserRating>();
			}
			double totalRatings = Double.compare(averageRating, -1d) == 0 ? 0 : averageRating * userRatings.size();
			userRatings.add(ur);
			averageRating = (totalRatings + ur.getUserRating()) / userRatings.size();
		}
	}

	@Override
	public void addRate(Rate rate) {
		if (rate != null) {
			if (rates == null) {
				rates = new ArrayList<Rate>();
			}
			rates.add(rate);
			if (lowestRate == null || Double.compare(rate.getBaseRoomPrice(), lowestRate.getBaseRoomPrice()) < 0) {
				lowestRate = rate;
			}
		}
	}

	@Override
	public CenterLocation getCenterLocation() {
		return centerLocation;
	}

	@Override
	public Location getLocation() {
		return location;
	}

}
