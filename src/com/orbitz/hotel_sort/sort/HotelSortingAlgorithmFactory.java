package com.orbitz.hotel_sort.sort;

import com.orbitz.hotel_sort.model.CenterLocation;
import com.orbitz.hotel_sort.model.Hotel;


public class HotelSortingAlgorithmFactory {
	private static HotelSortingAlgorithmFactory singleton = new HotelSortingAlgorithmFactory();

	private HotelSortingAlgorithmFactory() {

	}

	public static HotelSortingAlgorithmFactory getInstance() {
		return singleton;
	}
	
	public HotelCompare<Hotel> sortByDistanceToCenter(CenterLocation centerLocation) {
		return new DistanceToCenter(centerLocation);
	}
	
	public HotelCompare<Hotel> sortByLowestPrice() {
		return new LowestPrice();
	}
	
	public HotelCompare<Hotel> sortByAverageUserRating() {
		return new AverageUserRating();
	}
}
