package com.orbitz.hotel_sort.model;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class CenterLocationImpl implements CenterLocation {
	private String centerName;
	private long centerId;
	private ArrayList<Tax> taxes;
	private ArrayList<Hotel> hotels;
	private Location location;

	void populate(JSONObject json) {
		if (json != null) {
			centerName = (String) json.get("name");
			centerId = (long) json.get("location_id");
			location = ModelFactory.getInstance().newLocation(json);

			taxes = new ArrayList<Tax>();
			JSONArray jArray = (JSONArray) json.get("taxes");
			for (int i = 0; i < jArray.size(); ++i) {
				JSONObject obj = (JSONObject) jArray.get(i);
				Tax tax = ModelFactory.getInstance().newTax(obj);
				taxes.add(tax);
			}

			hotels = new ArrayList<Hotel>();
			jArray = (JSONArray) json.get("hotels");
			for (int i = 0; i < jArray.size(); ++i) {
				JSONObject obj = (JSONObject) jArray.get(i);
				Hotel hotel = ModelFactory.getInstance().newHotel(this, obj);
				hotels.add(hotel);
			}
		}
	}

	@Override
	public String getCenterName() {
		return centerName;
	}

	@Override
	public long getCenterId() {
		return centerId;
	}

	@Override
	public List<Tax> getTaxes() {
		return taxes;
	}

	@Override
	public List<Hotel> getHotels() {
		return hotels;
	}

	@Override
	public double getTotalTaxRate() {
		double totalTax = 0;
		if (taxes != null) {
			for (Tax tax : taxes) {
				totalTax += tax.getTaxRate();
			}
		}
		return totalTax;
	}

	@Override
	public Location getLocation() {
		return location;
	}

}
