package com.orbitz.hotel_sort.sort;

import com.orbitz.hotel_sort.model.CenterLocation;
import com.orbitz.hotel_sort.model.Hotel;

class DistanceToCenter implements HotelCompare<Hotel> {
	private SortOrder sortOrder = SortOrder.ASCE;
	private CenterLocation centerLocation;

	public DistanceToCenter(CenterLocation centerLocation) {
		this.centerLocation = centerLocation;
	}

	@Override
	public int compare(Hotel h1, Hotel h2) {
		Double d1 = (Double) getSortValue(h1);
		Double d2 = (Double) getSortValue(h2);
		if (sortOrder == SortOrder.ASCE) {
			return Double.compare(d1, d2);
		} else {
			return Double.compare(d2, d1);
		}
	}

	@Override
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public Object getSortValue(Hotel hotel) {
		if (hotel.getLocation() != null) {
			return hotel.getLocation().getDistance(centerLocation.getLocation());
		}
		return 0;
	}
}
