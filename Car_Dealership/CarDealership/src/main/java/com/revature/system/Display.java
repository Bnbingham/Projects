package com.revature.system;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.revature.cars.Car;
import com.revature.contracts.Contract;
import com.revature.contracts.Offer;
import com.revature.users.User;

public class Display {
	private Lot lot = Lot.getLotData();
	private List<Car> cars = lot.getCars();
	private List<Offer> offers = lot.getOffers();
	private List<Contract> contracts = lot.getContracts();
	
	
	public ArrayList<Car> carRecords(String filter){
		String thisUserID = filter;
		List<Car> filteredCars = new ArrayList<>();
		switch (filter) {
		case "all":
			System.out.println("\033[0;4mCarID\tOwnerID\tModel\tYear\tColor\tPrice\033[0m");
			for (Car car : cars) {
				filteredCars.add(car);
				int carID = car.getID();
				int ownerID = car.getOwnerID();
				String model = car.getModel().getValue();
				int year = car.getYear().getValue();
				String color = car.getColor().getValue();
				double price = car.getPrice().getValue();
				System.out.println(carID+"\t"+ownerID+"\t"+model+"\t"+year+"\t"+color+"\t"+price);
			}
			break;
		case "available":
			System.out.println("\033[0;4mCarID\tModel\tYear\tColor\tPrice\033[0m");
			for (Car car : cars) {
				if(car.getOwnerID() == 0) {
					filteredCars.add(car);
					int carID = car.getID();
					String model = car.getModel().getValue();
					int year = car.getYear().getValue();
					String color = car.getColor().getValue();
					double price = car.getPrice().getValue();
					System.out.println(carID+"\t"+model+"\t"+year+"\t"+color+"\t"+price);
				};
			}
			break;
		default:
			System.out.println("\033[0;4mCarID\tModel\tYear\tColor\tPrice\033[0m");
			for (Car car : cars) {
				if(car.getOwnerID() == Integer.parseInt(thisUserID)) {
					filteredCars.add(car);
					int carID = car.getID();
					String model = car.getModel().getValue();
					int year = car.getYear().getValue();
					String color = car.getColor().getValue();
					double price = car.getPrice().getValue();
					System.out.println(carID+"\t"+model+"\t"+year+"\t"+color+"\t"+price);
				};
			}
			break;
		}
		return (ArrayList<Car>) filteredCars;
	
	}
	
	public ArrayList<Offer> offerRecords() {
		System.out.println("\033[0;4mOfferID\tCarID\tAskingPrice\tuserID\t$Down\tMonths/Payment\033[0m");
		ArrayList<Offer> pendingOffers = new ArrayList<>();
		for (Offer offer : offers) {
			if(offer.getOfferStatus().getValue().equals("Pending") ) {
				pendingOffers.add(offer);
				ContractDetail details = new ContractDetail();
				int offerID = offer.getOfferID();
				int carID = offer.getCarID();
				int userID = offer.getUserID();
				double downPayment = offer.getDownPayment().getValue();
				int termLoanLength = offer.getTermLoanLength().getLength();
				Car thisCar = details.carByID(carID);
				double askingPrice = thisCar.getPrice().getValue();
				Double monthlyPrice = (askingPrice - downPayment)/termLoanLength;
				NumberFormat nf = NumberFormat.getInstance();
				nf.setMaximumFractionDigits(2);
				System.out.println(
						offerID+
						"\t"+carID+
						"\t"+askingPrice+
						"\t"+userID+
						"\t"+downPayment+
						"\t"+termLoanLength+"  /  "+nf.format(monthlyPrice));
			}
		}
		return pendingOffers;
	}
	
	//Contracts user:selectSingle employee:all
	public void contractRecords(int userID) {
		System.out.println("\033[0;4mConID\tCar\tPayments Balance\033[0m");
		List<Contract> myContracts = new ArrayList<>();
		for (Car car : cars) {
			if(car.getOwnerID() == userID) {
//				System.out.println(car);
				for (Offer offer : offers) {
					if(offer.getCarID() == car.getID()) {
//						System.out.println(offer);
						for (Contract contract: contracts) {
							if(contract.getOfferID() == offer.getOfferID()) {
								ContractDetail cd = new ContractDetail(contract.getContractID());
								System.out.println(
										contract.getContractID()+
										"\t"+car.getModel().getValue()+
										"\t"+contract.getPayments().size()+
										"\t "+cd.getRemaingBalance());
							}
						}
					}
				}
			}
		}
	}
	public void contractRecords() {
		System.out.println("\033[0;4mConID\tCarID\tOwnerID\tPayments Balance\033[0m");
		for (Contract contract: contracts) {
			ContractDetail cd = new ContractDetail(contract.getContractID());
			System.out.println(
					contract.getContractID()+
					"\t"+cd.getCar().getID()+
					"\t"+cd.getCar().getOwnerID()+
					"\t"+contract.getPayments().size()+
					"\t "+cd.getRemaingBalance());		
		}
	}
	
}
