package com.orbitz.hotel_sort.sort;

import com.orbitz.hotel_sort.model.Hotel;

class AverageUserRating implements HotelCompare<Hotel> {
	private SortOrder sortOrder;

	@Override
	public int compare(Hotel h1, Hotel h2) {
		Double r1 = (Double) getSortValue(h1);
		Double r2 = (Double) getSortValue(h2);
		if (sortOrder == SortOrder.ASCE) {
			return Double.compare(r1, r2);
		} else {
			return Double.compare(r2, r1);
		}
	}

	@Override
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public Object getSortValue(Hotel hotel) {
		return hotel.getAverageUserRating();
	}

}
