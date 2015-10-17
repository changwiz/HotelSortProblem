package com.orbitz.hotel_sort.model;

import org.json.simple.JSONObject;

public class TaxImpl implements Tax {
	private String taxName;
	private double taxRate;

	void populate(JSONObject json) {
		if (json != null) {
			taxName = (String) json.get("name");
			taxRate = (double) json.get("rate");
		}
	}

	@Override
	public String getTaxName() {
		return taxName;
	}

	@Override
	public double getTaxRate() {
		return taxRate;
	}

}
