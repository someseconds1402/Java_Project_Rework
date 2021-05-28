package task.volume;

import Oxyz.room.Room;

public class Volume {
	public static void main(String[] args) {
		Room room = new Room("D:\\Don't Click Here\\2020-2\\OOP\\Project\\Demo1\\src\\input\\input.txt");
		double n = room.couldSeeVolume();
		System.out.println(n);
	}
}
