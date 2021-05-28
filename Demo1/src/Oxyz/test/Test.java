package Oxyz.test;

import Oxyz.room.Room;


public class Test {

	public static void main(String[] args) {
		
		Room room = new Room("D:\\Don't Click Here\\2020-2\\OOP\\Project\\Demo1\\src\\input\\input.txt");
		room.exportFile("D:\\Don't Click Here\\2020-2\\OOP\\Project\\Demo1\\src\\input\\export.txt");
		Room room1 = new Room("D:\\Don't Click Here\\2020-2\\OOP\\Project\\Demo1\\src\\input\\export.txt", 1);

//		System.out.println(room.getWidthRoom());
//		System.out.println(room.getLengthRoom());
//		System.out.println(room.getHeightRoom());
		System.out.println(room1.getRectList().size());
		System.out.println(room1.getCameraList().size());
	}

}

//  D:\Don't Click Here\2020-2\OOP\Project\Demo1\src\input\input.txt