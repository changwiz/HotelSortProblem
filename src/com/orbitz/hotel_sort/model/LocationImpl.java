package com.orbitz.hotel_sort.model;

import org.json.simple.JSONObject;

public class LocationImpl implements Location {

	private double locationX;
	private double locationY;
	private double locationZ;

	void populate(JSONObject json) {
		if (json != null) {
			locationX = (double) json.get("location_x");
			locationY = (double) json.get("location_y");
			locationZ = (double) json.get("location_z");
		}
	}

	@Override
	public double getLocationX() {
		return locationX;
	}

	@Override
	public double getLocationY() {
		return locationY;
	}

	@Override
	public double getLocationZ() {
		return locationZ;
	}

	@Override
	public double getDistance(Location location) {
		if (location == null) {
			return 0;
		}
		double xSquare = Math.pow(this.getLocationX() - location.getLocationX(), 2);
		double ySquare = Math.pow(this.getLocationY() - location.getLocationY(), 2);
		double zSquare = Math.pow(this.getLocationZ() - location.getLocationZ(), 2);
		return Math.sqrt(xSquare + ySquare + zSquare);
	}

}
