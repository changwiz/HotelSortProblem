package com.orbitz.hotel_sort.model;

import org.json.simple.JSONObject;

public class UserRatingImpl implements UserRating {
	private String userId;
	private long userRating;

	void populate(JSONObject json) {
		if (json != null) {
			userId = (String) json.get("user_id");
			userRating = (long) json.get("rating");
		}
	}

	@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public long getUserRating() {
		return userRating;
	}

}
