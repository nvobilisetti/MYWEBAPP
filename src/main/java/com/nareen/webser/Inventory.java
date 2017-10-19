package com.nareen.webser;

import java.util.ArrayList;

/**
 * Singleton class to maintain Car Inventory
 * 
 * @author vobil
 *
 */
public class Inventory {

	private static Inventory instance = null;

	private ArrayList<Car> list = new ArrayList<Car>();;

	private Inventory() {

	}

	public synchronized static Inventory getInstance() {
		if (instance == null) {
			instance = new Inventory();
		}
		return instance;
	}

	public ArrayList<Car> getList() {
		return list;
	}
}
