package com.orbitz.hotel_sort.sort;

import com.orbitz.hotel_sort.model.Hotel;
import com.orbitz.hotel_sort.model.Rate;

class LowestPrice implements HotelCompare<Hotel> {
	private SortOrder sortOrder = SortOrder.ASCE;

	@Override
	public int compare(Hotel h1, Hotel h2) {
		Double p1 = (Double) getSortValue(h1);
		Double p2 = (Double) getSortValue(h2);
		if (sortOrder == SortOrder.ASCE) {
			return Double.compare(p1, p2);
		} else {
			return Double.compare(p2, p1);
		}
	}

	@Override
	public void setSortOrder(HotelCompare.SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public Object getSortValue(Hotel hotel) {
		Rate rate = hotel.getLowestRate();
		return rate != null ? rate.getRoomPrice(hotel.getCenterLocation().getTotalTaxRate()) : 0;
	}

}
