package com.revature.system;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.cars.Car;
import com.revature.contracts.Contract;
import com.revature.contracts.Offer;
import com.revature.daoimp.CarDAOImp;
import com.revature.daoimp.ContractDAOImp;
import com.revature.daoimp.OfferDAOImp;
import com.revature.daoimp.PaymentDAOImp;
import com.revature.daoimp.UserDAOImp;
import com.revature.users.User;

public class Lot {
	private static List<User> users = new ArrayList<>();
	private static List<Car> cars = new ArrayList<>();
	private static List<Contract> contracts = new ArrayList<>();
	private static List<Offer> offers = new ArrayList<>();
	
	private static CarDAOImp cardi = new CarDAOImp();
	private static ContractDAOImp condi = new ContractDAOImp();
	private static OfferDAOImp offdi = new OfferDAOImp();
	private static UserDAOImp usedi = new UserDAOImp();
	
	private static User currentUser;
	
	private static Lot instance;
	
	private Lot() {
		super();
		this.currentUser = null;
		try {
			this.users = usedi.getUserList();
			this.cars = cardi.getCarList();
			this.offers = offdi.getOfferList();
			this.contracts = condi.getContractList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static synchronized Lot getLotData() {
		if(instance == null)
			instance = new Lot();
		return instance;
	}
	public static void updateLotData() {
		try {
			users = usedi.getUserList();
			cars = cardi.getCarList();
			offers = offdi.getOfferList();
			contracts = condi.getContractList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<User> getUsers() {
		return users;
	}

	public List<Car> getCars() {
		return cars;
	}

	public List<Contract> getContracts() {
		return contracts;
	}
	public List<Offer> getOffers(){
		return offers;
	}
	public static User getCurrentUser() {
		return currentUser;
	}
	
	public static void setCurrentUser(User currentUser) {
		Lot.currentUser = currentUser;
	}

	@Override
	public String toString() {
		return "LotData "+
				"\nUsers\n" + users +
				"\nCars\n" + cars +
				"\nOffers\n" +offers+
				"\nContracts\n" + contracts;
	}


	
	
	
}
