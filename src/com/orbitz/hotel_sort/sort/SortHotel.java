package com.orbitz.hotel_sort.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.orbitz.hotel_sort.model.Hotel;
import com.orbitz.hotel_sort.sort.HotelCompare.SortOrder;
/**
 * 
 * @author wentaochang
 *	Use Strategy design pattern to implement different hotel sorting algorithms
 *
 */
public class SortHotel {

	private HotelCompare<Hotel> hotelCompare;

	/**
	 * 
	 * @param sortingAlgorithm 
	 * Constructor for sorting algorithm
	 */
	public SortHotel(HotelCompare<Hotel> sortingAlgorithm) {
		this.hotelCompare = sortingAlgorithm;
	}

	/**
	 * 
	 * @param hotels
	 * @return A sorted list following ascending order
	 */
	public List<Hotel> sortHotel(List<Hotel> hotels) {
		return sortHotel(hotels, SortOrder.ASCE);
	}

	/**
	 * 
	 * @param hotels
	 * @param sortOrder
	 * @return A sorted list following specified sortOrder
	 */
	public List<Hotel> sortHotel(List<Hotel> hotels, SortOrder sortOrder) {
		ArrayList<Hotel> sorted = new ArrayList<Hotel>();
		sorted.addAll(hotels);
		if (this.hotelCompare != null) {
			this.hotelCompare.setSortOrder(sortOrder);
			Collections.sort(sorted, this.hotelCompare);
		}
		return sorted;
	}
}
