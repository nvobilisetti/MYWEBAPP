package com.nareen.webser;

public class Car {

	String carName;
	String carModel;
	int carPrice;
	int carYear;

	public Car() {
		this.carName = "";
		this.carModel = "";
		this.carPrice = 0;
		this.carYear = 0;
	}

	@Override
	public String toString() {
		String S = "\n Car :" + this.carName + " " + this.carModel + " manufactured in " + this.carYear
				+ " available with price " + this.carPrice + ". \n";
		return S;
	}
	public String getCarName() {
		return carName;
	}

	public String getCarModel() {
		return carModel;
	}

	public int getCarPrice() {
		return carPrice;
	}

	public int getCarYear() {
		return carYear;
	}

}