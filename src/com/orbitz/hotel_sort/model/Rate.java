package com.orbitz.hotel_sort.model;

public interface Rate {
	public String RoomName();

	public String getRoomId();

	public double getBaseRoomPrice();
	
	public double getRoomPrice(double tax);
}
