package com.orbitz.hotel_sort;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.orbitz.hotel_sort.model.CenterLocation;
import com.orbitz.hotel_sort.model.Hotel;
import com.orbitz.hotel_sort.model.ModelFactory;
import com.orbitz.hotel_sort.sort.HotelCompare;
import com.orbitz.hotel_sort.sort.HotelCompare.SortOrder;
import com.orbitz.hotel_sort.sort.HotelSortingAlgorithmFactory;
import com.orbitz.hotel_sort.sort.SortHotel;
/**
 * 
 * @author wentaochang
 *	A few highlights:
 *	(1) Abstract location representation and distance calculation, so it can be easily extended or modified without changing too much code
 *  (2) Leverage strategy design pattern to implement different sorting algorithms, each algorithm is made interchangeable within the family; 
 *  the algorithm library can be easily augmented
 *  (3) Use singleton and factory design pattern to generate data model or algorithm from a single point; it is easier to maintain and extend model classes
 *  (4) Program to interface, implementation classes are all package private.
 *  (5) Check the correct format of input json via simple json library; check null whenever possible when accessing data model
 *	
 */
public class HotelSort {
	public static void main(String[] args) {
		try {
			String inputString = readFile("input.txt", Charset.forName("utf-8"));
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(inputString);
			CenterLocation centerLocation = ModelFactory.getInstance().newCenterLocation(json);
			List<Hotel> hotels = centerLocation.getHotels();

			sortByDistance(hotels, centerLocation);
			sortByLowestPrice(hotels);
			sortByUserRatings(hotels);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	private static String printHotels(List<Hotel> hotels, List<String> values) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < hotels.size(); ++i) {
			if (i != 0) {
				builder.append(" --> ");
			}
			builder.append(hotels.get(i).getName() + "(" + values.get(i) + ")");
		}
		return builder.toString();
	}

	private static void sortByDistance(List<Hotel> hotels, CenterLocation centerLocation) {
		System.out.println("Sort hotels by their distance to center");
		HotelCompare<Hotel> sortingAlgorithm = HotelSortingAlgorithmFactory.getInstance().sortByDistanceToCenter(centerLocation);
		SortHotel sortHotel = new SortHotel(sortingAlgorithm);

		List<Hotel> sortedAsce = sortHotel.sortHotel(hotels);
		System.out.println("Ascending order:");
		System.out.println(printHotels(sortedAsce, constructSortValues(sortedAsce, sortingAlgorithm)));

		List<Hotel> sortedDesc = sortHotel.sortHotel(hotels, SortOrder.DESC);
		System.out.println("Descending order:");
		System.out.println(printHotels(sortedDesc, constructSortValues(sortedDesc, sortingAlgorithm)));

		System.out.println();

	}

	private static void sortByLowestPrice(List<Hotel> hotels) {
		System.out.println("Sort hotels by their lowest rates");
		HotelCompare<Hotel> sortingAlgorithm = HotelSortingAlgorithmFactory.getInstance().sortByLowestPrice();
		SortHotel sortHotel = new SortHotel(sortingAlgorithm);

		List<Hotel> sortedAsce = sortHotel.sortHotel(hotels);
		System.out.println("Ascending order:");
		System.out.println(printHotels(sortedAsce, constructSortValues(sortedAsce, sortingAlgorithm)));

		List<Hotel> sortedDesc = sortHotel.sortHotel(hotels, SortOrder.DESC);
		System.out.println("Descending order:");
		System.out.println(printHotels(sortedDesc, constructSortValues(sortedDesc, sortingAlgorithm)));

		System.out.println();
	}

	private static void sortByUserRatings(List<Hotel> hotels) {
		System.out.println("Sort hotels by their average user ratings");
		HotelCompare<Hotel> sortingAlgorithm = HotelSortingAlgorithmFactory.getInstance().sortByAverageUserRating();
		SortHotel sortHotel = new SortHotel(sortingAlgorithm);

		List<Hotel> sortedAsce = sortHotel.sortHotel(hotels);
		System.out.println("Ascending order:");
		System.out.println(printHotels(sortedAsce, constructSortValues(sortedAsce, sortingAlgorithm)));

		List<Hotel> sortedDesc = sortHotel.sortHotel(hotels, SortOrder.DESC);
		System.out.println("Descending order:");
		System.out.println(printHotels(sortedDesc, constructSortValues(sortedDesc, sortingAlgorithm)));

		System.out.println();
	}

	private static List<String> constructSortValues(List<Hotel> hotels, HotelCompare<Hotel> algorithm) {
		List<String> values = new ArrayList<String>();
		for (Hotel hotel : hotels) {
			values.add(algorithm.getSortValue(hotel).toString());
		}
		return values;
	}
}
