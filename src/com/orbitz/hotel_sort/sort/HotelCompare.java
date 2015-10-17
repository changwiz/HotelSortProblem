package com.orbitz.hotel_sort.sort;

import java.util.Comparator;

public interface HotelCompare<Hotel> extends Comparator<Hotel> {
	public enum SortOrder {
		ASCE, DESC
	}

	public int compare(Hotel h1, Hotel h2);

	public boolean equals(Object obj);

	public void setSortOrder(SortOrder sortOrder);
	
	public Object getSortValue(Hotel hotel);
}
