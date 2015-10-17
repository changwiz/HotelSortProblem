package com.orbitz.hotel_sort.model;

import org.json.simple.JSONObject;

class RateImpl implements Rate {
	private String roomName;
	private String roomId;
	private double basePrice;

	void populate(JSONObject json) {
		if (json != null) {
			roomName = (String) json.get("name");
			roomId = (String) json.get("room_id");
			basePrice = (double) json.get("base_price");
		}
	}

	@Override
	public String RoomName() {
		return roomName;
	}

	@Override
	public String getRoomId() {
		return roomId;
	}

	@Override
	public double getBaseRoomPrice() {
		return basePrice;
	}

	@Override
	public double getRoomPrice(double tax) {
		return basePrice * (1d + tax);
	}

}
