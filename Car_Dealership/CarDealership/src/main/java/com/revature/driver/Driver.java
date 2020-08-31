package com.revature.driver;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.cars.Car;
import com.revature.contracts.Contract;
import com.revature.contracts.Offer;
import com.revature.daoimp.CarDAOImp;
import com.revature.daoimp.OfferDAOImp;
import com.revature.daoimp.PaymentDAOImp;
import com.revature.daoimp.UserDAOImp;
import com.revature.system.ContractDetail;
import com.revature.system.Display;
import com.revature.system.LogThis;
import com.revature.system.Lot;
import com.revature.users.User;

public class Driver {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		LogThis.LogIt("info", "Ran program");
		Lot lot = Lot.getLotData();
		
		System.out.println("Welcome to Brad's Car Lot");
		System.out.println("What would you like to do?");
		System.out.println("1. Register a new account.");
		System.out.println("2. Log in");
		System.out.print("Make your selection: ");
		int choice = -1;
		int range = 2;
		while(choice <= 0 || choice > range) {
			try {
				choice = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection.\nPlease enter a number between 1 and ");
				break;
			}
			if(choice <= 0 || choice > range)
				System.out.print("Invalid selection.\nPlease enter a different selection");
		}
		switch (choice) {
		case 1: 
			String regUsername =null;
			String regPassword = null;
			boolean availableUsername = false;
			UserDAOImp udi = new UserDAOImp();
			System.out.println("Register a user");
			System.out.println("Please enter a username");
			while(!availableUsername) {
				availableUsername = true;
				String checkUsername = scan.nextLine();
				for (User user : lot.getUsers()) {
					if(user.getUsername().getValue().equals(checkUsername)) {
						availableUsername = false;
					}
				}
				if(availableUsername) {
					regUsername = checkUsername;
				}else {
					System.out.print("Invalid selection\nPlease enter your username");
				}
			}
			System.out.println("please enter your password");
			String checkPassword = scan.nextLine();
			regPassword =checkPassword;
			try {
				udi.insertUser(regUsername, regPassword);
				LogThis.LogIt("info", "Created user "+regUsername);
			} catch (SQLException e) {
				LogThis.LogIt("error", "SQL exception while making new User");
				e.printStackTrace();
			}
			System.out.println("Successfully created user, one moment...");
			lot.updateLotData();
			for (User user : lot.getUsers()) {
				if(user.getUsername().getValue().equals(regUsername)) {
					lot.setCurrentUser(user);
				} 
			}
			customerMenu();
			System.exit(0); break;
		case 2: 
			User logInUser = null;
			boolean locatedUsername = false;
			boolean matchingPassword1 = false;
			System.out.println("Welcome to login");
			System.out.println("Please enter your username");
			while(!locatedUsername) {
				String checkUsername = scan.nextLine();
				for (User user : lot.getUsers()) {
					if(user.getUsername().getValue().equals(checkUsername)) {
						logInUser = user;
						locatedUsername = true;
					} 
				}
				if(!locatedUsername)
					System.out.print("Invalid selection\nPlease enter your username");
			}
			System.out.println("Please enter your password");
			while(!matchingPassword1) {
				String checkPassword1 = scan.nextLine();
				if(logInUser.getPassword().getValue().equals(checkPassword1)) {
					matchingPassword1 = true;
				}  
				
				if(!matchingPassword1)
					System.out.print("Invalid selection\nPlease enter your Password");
			}
			System.out.println("Successfully logged in");
			lot.setCurrentUser(logInUser);
			if(lot.getCurrentUser().getID() == 0) {
				employeeMenu();
			} else {
				customerMenu();
			}
			System.exit(0); break;
		}

	}

	private static void customerMenu() {
		LogThis.LogIt("info", Lot.getCurrentUser()+"Logged in");
		Lot lot = Lot.getLotData();
		lot.updateLotData();
		System.out.println();
		System.out.println("Welcome "+lot.getCurrentUser().getUsername());
		System.out.println("What would you like to do?");
		System.out.println("1. View available cars");
		System.out.println("2. View my cars");
		System.out.println("3. Exit");
		System.out.print("Please make a selection: ");
		int choice = -1;
		while(choice <= 0 || choice > 3) {
			choice = Integer.parseInt(scan.nextLine());
			if(choice <= 0 || choice > 3)
				System.out.print("Invalid selection,\nPlease enter a different selection");
		}
		switch(choice) {
		case 1:
			viewAvailableCars();
			break;
		case 2:
			viewMyCars();
			break;
		case 3:
			System.out.println("Thank you, goodbye");
			System.exit(0);
			break;
		}
	}
	

	private static void viewAvailableCars() {
		Lot lot = Lot.getLotData();
		lot.updateLotData();
		Display display = new Display();
		
		boolean isvalidChoice = false;
		Car chosenCar = null;
		System.out.println();
		ArrayList<Car> availableCars = display.carRecords("available");
		System.out.println("Enter carID to view or 0 to exit");
		System.out.print("Please make a selection: ");
		while(!isvalidChoice) {
			int choice1 = Integer.parseInt(scan.nextLine());
			Car carById = null;
			if(choice1 == 0) { isvalidChoice =true; break; }
			for (Car car : lot.getCars()) {
				if(car.getID() == choice1) {
					carById = car;
				}
			}
			if(availableCars.contains(carById)) {
				isvalidChoice = true;
				chosenCar = carById;
			}else {
				System.out.println("Invalid selection,\nPlease enter a different selection");
			}
		}
		System.out.println();
		System.out.println("Selected car");
		System.out.println(chosenCar);
		System.out.println("What would you like to do?");
		System.out.println("1. Make an offer");
		System.out.println("2. Select another car");
		System.out.println("3. Return to the main menu");
		System.out.print("Please make a selection: ");
		int choice2 = -1;
		while(choice2 <= 0 || choice2 > 3) {
			choice2 = Integer.parseInt(scan.nextLine());
			if(choice2 <= 0 || choice2 > 3)
				System.out.print("Invalid selection,\nPlease enter a different selection");
		}
		switch (choice2) {
		case 1:
			makeAnOffer(chosenCar);
			break;
		case 2:
			viewAvailableCars();
			break;
		case 3:
			customerMenu();
			break;
		}
		
	}
	private static void viewMyCars() {
		Lot lot = Lot.getLotData();
		lot.updateLotData();
		boolean isvalidChoice = false;
		Car chosenCar = null;
		Display display = new Display();
		String thisUserID = String.valueOf(Lot.getCurrentUser().getID());
		System.out.println();
		ArrayList<Car> myCars = display.carRecords(thisUserID);
		if(myCars.size() == 0) { System.out.println("None");};
		System.out.println("Enter carID to view or 0 to exit");
		System.out.print("Please make a selection: ");
		while(!isvalidChoice) {
			int choice1 = Integer.parseInt(scan.nextLine());
			Car carById = null;
			if(choice1 == 0) { isvalidChoice =true; break; }
			for (Car car : lot.getCars()) {
				if(car.getID() == choice1) {
					carById = car;
				}
			}
			if(myCars.contains(carById)) {
				isvalidChoice = true;
				chosenCar = carById;
			}else {
				System.out.println("Invalid selection,\nPlease enter a different selection");
			}
		}
		System.out.println();
		System.out.println("Selected car");
		System.out.println(chosenCar);
		System.out.println("What would you like to do?");
		System.out.println("1. View Payment information");
		System.out.println("2. Select another car");
		System.out.println("3. Return to the main menu");
		System.out.print("Please make a selection: ");
		int choice2 = -1;
		while(choice2 <= 0 || choice2 > 3) {
			choice2 = Integer.parseInt(scan.nextLine());
			if(choice2 <= 0 || choice2 > 3)
				System.out.print("Invalid selection,\nPlease enter a different selection");
		}
		switch (choice2) {
		case 1:
			viewPayments(chosenCar);
			break;
		case 2:
			viewMyCars();
			break;
		case 3:
			customerMenu();
			break;
		}
		
	}
	private static void makeAnOffer(Car chosenCar) {
		Lot lot = Lot.getLotData();
		lot.updateLotData();
		int carID = chosenCar.getID();
		int userID = lot.getCurrentUser().getID();
		boolean validDownpayment = false;
		double downPayment = 0;
		System.out.println();
		System.out.println("Create your Offer");
		System.out.print("Please enter your downpayment offer: ");
		while(!validDownpayment) {
			double checkpayment = Double.parseDouble(scan.nextLine());
			if(checkpayment > 1 && checkpayment <= chosenCar.getPrice().getValue()) {
				downPayment = checkpayment;
				validDownpayment = true;
			}else {
				System.out.print("Invalid selection\nPlease enter your username");
			}
		}
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		System.out.println("Please select term loan option");
		System.out.println("1. 12 months / $"+nf.format((chosenCar.getPrice().getValue() - downPayment)/12)+" per month");
		System.out.println("2. 36 months / $"+nf.format((chosenCar.getPrice().getValue() - downPayment)/36)+" per month");
		System.out.println("3. 72 months / $"+nf.format((chosenCar.getPrice().getValue() - downPayment)/72)+" per month");
		System.out.println("4. Cancel offer");
		System.out.print("Please make a selection: ");
		int choice = -1;
		while(choice <= 0 || choice > 4) {
			choice = Integer.parseInt(scan.nextLine());
			if(choice <= 0 || choice > 4)
				System.out.print("Invalid selection,\nPlease enter a different selection");
		}
		
		OfferDAOImp odi = new OfferDAOImp();
		switch (choice) {
		case 1:
			try {
				odi.insertOffer(carID, userID, downPayment, 12);
				System.out.println("Offer succesfully submitted");
				LogThis.LogIt("info", "Offer created for"+carID+", "+userID+", "+downPayment+", "+12 + " months");
			} catch (SQLException e) {
				LogThis.LogIt("error", "SQLException while inserting offer");
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				odi.insertOffer(carID, userID, downPayment, 36);
				System.out.println("Offer succesfully submitted");
				LogThis.LogIt("info", "Offer created for"+carID+", "+userID+", "+downPayment+", "+36+" months");
			} catch (SQLException e) {
				LogThis.LogIt("error", "SQLException while inserting offer");
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				odi.insertOffer(carID, userID, downPayment, 72);
				System.out.println("Offer succesfully submitted");
				LogThis.LogIt("info", "Offer created for"+carID+", "+userID+", "+downPayment+", "+72+" months");
			} catch (SQLException e) {
				LogThis.LogIt("error", "SQLException while inserting offer");
				e.printStackTrace();
			}
			break;
		case 4:
			break;
		}
		System.out.println("What would you like to do?");
		System.out.println("1. Make another offer");
		System.out.println("2. Select another car");
		System.out.println("3. Return to the main menu");
		System.out.print("Please make a selection: ");
		int choice2 = -1;
		while(choice2 <= 0 || choice2 > 3) {
			choice2 = Integer.parseInt(scan.nextLine());
			if(choice2 <= 0 || choice2 > 3)
				System.out.print("Invalid selection,\nPlease enter a different selection");
		}
		switch (choice2) {
		case 1:
			makeAnOffer(chosenCar);
			break;
		case 2:
			viewAvailableCars();
			break;
		case 3:
			customerMenu();
			break;
		}
	}
	private static void viewPayments(Car chosenCar) {
		Lot lot = Lot.getLotData();
		lot.updateLotData();
		Contract thisContract = null;
		for (Offer offer : lot.getOffers()) {
			if(offer.getCarID() == chosenCar.getID()) {
				for (Contract contract : lot.getContracts()) {
					if(offer.getID() == contract.getOfferID()) {
						thisContract = contract;
					}
				}
			}
		}
		System.out.println();
		System.out.println("Payments");
		System.out.println(thisContract);
		ContractDetail cd = new ContractDetail(thisContract.getContractID());
		System.out.println("Remaining Balance: "+cd.getRemaingBalance());
		System.out.println("Monthly Payment: "+cd.getMonthlyPayment());
		System.out.println("Loan Length Remaining: "+cd.getMonthsLeftOnLoan()+" months");
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1. Make a payment");
		System.out.println("2. Select another car");
		System.out.println("3. Return to the main menu");
		System.out.print("Please make a selection: ");
		int choice2 = -1;
		while(choice2 <= 0 || choice2 > 2) {
			choice2 = Integer.parseInt(scan.nextLine());
			if(choice2 <= 0 || choice2 > 2)
				System.out.print("Invalid selection,\nPlease enter a different selection");
		}
		switch (choice2) {
		case 1:
			makeAPayment(chosenCar);
		case 2:
			viewMyCars();
			break;
		case 3:
			customerMenu();
			break;
		}
	}

	private static void makeAPayment(Car chosenCar) {
		Lot lot = Lot.getLotData();
		lot.updateLotData();
		double thisPayment = 0;
		Contract thisContract = null;
		for (Offer offer : lot.getOffers()) {
			if(offer.getCarID() == chosenCar.getID()) {
				for (Contract contract : lot.getContracts()) {
					if(offer.getID() == contract.getOfferID()) {
						thisContract = contract;
					}
				}
			}
		}
		ContractDetail cd = new ContractDetail(thisContract.getContractID());
		boolean validPayment = false;
		System.out.println();
		System.out.println("Make a Payment");
		System.out.print("Please enter your payment amount");
		while(!validPayment) {
			double checkpayment = Double.parseDouble(scan.nextLine());
			if(checkpayment > 1 && checkpayment <= cd.getRemaingBalance()) {
				thisPayment = checkpayment;
				validPayment = true;
			}else {
				System.out.print("Invalid selection\nPlease enter another amount");
			}
		}
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		System.out.println("Process a payment of "+thisPayment+"?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		System.out.print("Please make a selection: ");
		int choice = -1;
		while(choice <= 0 || choice > 2) {
			choice = Integer.parseInt(scan.nextLine());
			if(choice <= 0 || choice > 2)
				System.out.print("Invalid selection,\nPlease enter a different selection");
		}
		
		switch (choice) {
		case 1:
			PaymentDAOImp pdi = new PaymentDAOImp();
			try {
				pdi.insertPayment(thisPayment, thisContract.getContractID());
				LogThis.LogIt("info", "Payment Created for contract"+ thisContract.getContractID());
			} catch (SQLException e) {
				LogThis.LogIt("error", "SQLException while inserting offer");
				e.printStackTrace();
			}
			break;
		case 2:
		
		}
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1. Make another offer");
		System.out.println("2. Select another car");
		System.out.println("3. Return to the main menu");
		System.out.print("Please make a selection: ");
		int choice2 = -1;
		while(choice2 <= 0 || choice2 > 3) {
			choice2 = Integer.parseInt(scan.nextLine());
			if(choice2 <= 0 || choice2 > 3)
				System.out.print("Invalid selection,\nPlease enter a different selection");
		}
		switch (choice2) {
		case 1:
			makeAnOffer(chosenCar);
			break;
		case 2:
			viewAvailableCars();
			break;
		case 3:
			customerMenu();
			break;
		}
		
	}
	private static void employeeMenu() {
		Lot lot = Lot.getLotData();
		lot.updateLotData();
		System.out.println();
		System.out.println("Welcome "+lot.getCurrentUser().getUsername());
		System.out.println("What would you like to do?");
		System.out.println("1. View available cars");
		System.out.println("2. View pending offers");
		System.out.println("3. Register a new car");
		System.out.println("4. Exit");
		System.out.print("Please make a selection: ");
		int choice = -1;
		while(choice <= 0 || choice > 4) {
			choice = Integer.parseInt(scan.nextLine());
			if(choice <= 0 || choice > 4)
				System.out.print("Invalid selection,\nPlease enter a different selection");
		}
		switch(choice) {
		case 1:
			viewAllCars();
			break;
		case 2:
			viewPendingOffers();
			break;
		case 3:
			addNewCar();
		case 4:
			System.out.println("Thank you");
			System.exit(0);
			break;
		}
	}



	

	private static void viewPendingOffers() {
		Lot lot = Lot.getLotData();
		lot.updateLotData();
		boolean isvalidChoice = false;
		Offer chosenOffer= null;
		Display display = new Display();
		String thisUserID = String.valueOf(Lot.getCurrentUser().getID());
		System.out.println();
		ArrayList<Offer>myOffers = display.offerRecords();
		System.out.println("Enter offerID to view or 0 to exit");
		System.out.println("Please make a selection: ");
		while(!isvalidChoice) {
			int choice1 = Integer.parseInt(scan.nextLine());
			Offer offerById = null;
			if(choice1 == 0) { employeeMenu(); }
			for (Offer offer : lot.getOffers()) {
				if(offer.getID() == choice1) {
					offerById = offer;
				}
			}
			if(myOffers.contains(offerById)) {
				isvalidChoice = true;
				chosenOffer = offerById;
			}else {
				System.out.println("Invalid selection,\nPlease enter a different selection");
			}
		}
		System.out.println();
		System.out.println("Change Offer Status");
		System.out.println("1. Accept Offer");
		System.out.println("2. Decline Offer");
		System.out.println("3. Return to main menu");
		System.out.print("Please enter your selection: ");
		int choice = -1;
		while(choice <= 0 || choice > 3) {
			choice = Integer.parseInt(scan.nextLine());
			if(choice <= 0 || choice > 3)
				System.out.print("Invalid selection,\nPlease enter a different selection");
		}
		OfferDAOImp odi = new OfferDAOImp();
		int offerID = chosenOffer.getID();
		int carID = chosenOffer.getCarID();
		int userID = chosenOffer.getUserID();
		double downPayment = chosenOffer.getDownPayment().getValue();
		int termLength = chosenOffer.getTermLoanLength().getLength();
		switch(choice) {
		case 1:
			try {
				odi.updateOfferStatus(offerID, carID, userID, downPayment, "Accepted");
				System.out.println("Offer accepted");
				LogThis.LogIt("info", "Offer "+ offerID + " Accepted");
			} catch (SQLException e) {
				LogThis.LogIt("error", "SQLException while inserting offer");
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				odi.updateOfferStatus(offerID, carID, userID, downPayment, "Declined");
				System.out.println("Offer declined");
				LogThis.LogIt("info", "Offer "+ offerID + " declined");
			} catch (SQLException e) {
				LogThis.LogIt("error", "SQLException while inserting offer");
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("Thank you, goodbye");
			System.exit(0);
			break;
		}
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1. Return to pending offers");
		System.out.println("2. Return to the main menu");
		System.out.print("Please make a selection: ");
		int choice2 = -1;
		while(choice2 <= 0 || choice2 > 2) {
			choice2 = Integer.parseInt(scan.nextLine());
			if(choice2 <= 0 || choice2 > 2)
				System.out.print("Invalid selection,\nPlease enter a different selection");
		}
		switch (choice2) {
		case 1:
			viewPendingOffers();
			break;
		case 2:
			employeeMenu();
			break;
		}
	
		
	}
	private static void viewAllCars() {
		Lot lot = Lot.getLotData();
		lot.updateLotData();
		Display display = new Display();
		
		boolean isvalidChoice = false;
		Car chosenCar = null;
		ArrayList<Car> availableCars = display.carRecords("all");
		System.out.println("Enter carID to view or 0 to exit");
		System.out.print("Please make a selection: ");
		while(!isvalidChoice) {
			int choice1 = Integer.parseInt(scan.nextLine());
			Car carById = null;
			if(choice1 == 0) { isvalidChoice =true; break; }
			for (Car car : lot.getCars()) {
				if(car.getID() == choice1) {
					carById = car;
				}
			}
			if(availableCars.contains(carById)) {
				isvalidChoice = true;
				chosenCar = carById;
			}else {
				System.out.println("Invalid selection,\nPlease enter a different selection");
			}
		}
		System.out.println(chosenCar);
		if(chosenCar.getOwnerID() != 0) {
			Contract thisContract = null;
			for (Offer offer : lot.getOffers()) {
				if(offer.getCarID() == chosenCar.getID()) {
					for (Contract contract : lot.getContracts()) {
						if(contract.getOfferID() == offer.getID()) {
							thisContract = contract;
						}
					}
				}
			}
			System.out.println(thisContract);
			ContractDetail cd = new ContractDetail(thisContract.getContractID());
			System.out.println("Remaining Balance: "+cd.getRemaingBalance());
			System.out.println("Monthly Payment: "+cd.getMonthlyPayment());
			System.out.println("Loan Length Remaining: "+cd.getMonthsLeftOnLoan()+" months");
			
		}
		System.out.println("what would you like to do?");
		System.out.println("1. Remove car");
		System.out.println("2. Select another car");
		System.out.println("3. Return to the main menu");
		System.out.print("Please make a selection: ");
		int choice2 = -1;
		while(choice2 <= 0 || choice2 > 3) {
			choice2 = Integer.parseInt(scan.nextLine());
			if(choice2 <= 0 || choice2 > 3)
				System.out.print("Invalid selection,\nPlease enter a different selection");
			if(chosenCar.getOwnerID() != 0 && choice2 == 1) {
				choice2 = -1;
				System.out.println("Invalid selection, cannot remove this car");
			}
		}
		switch (choice2) {
		case 1:
			removeCar(chosenCar);
			break;
		case 2:
			viewAllCars();
			break;
		case 3:
			employeeMenu();
			break;
		}
		
	}

	private static void removeCar(Car chosenCar) {
		System.out.println("Are you sure you want to remove carID "+chosenCar.getID()+" from the lot?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		System.out.print("Please make a selection: ");
		int choice2 = -1;
		while(choice2 <= 0 || choice2 > 2) {
			choice2 = Integer.parseInt(scan.nextLine());
			if(choice2 <= 0 || choice2 > 2)
				System.out.print("Invalid selection,\nPlease enter a different selection");
		}
		switch (choice2) {
		case 1:
			try {
				CarDAOImp cdi = new CarDAOImp();
				cdi.removeCar(chosenCar.getID());
				System.out.println("Car succefully removed");
				LogThis.LogIt("info", "Car "+chosenCar.getID()+" removed");
			} catch (SQLException e) {
				LogThis.LogIt("error", "SQLException while removing car "+chosenCar.getID());
				e.printStackTrace();
			}
		case 2:
			System.out.println();
			System.out.println("what would you like to do?");
			System.out.println("1. Select another car");
			System.out.println("2. Return to the main menu");
			System.out.print("Please make a selection: ");
			int choice = -1;
			while(choice <= 0 || choice > 2) {
				choice = Integer.parseInt(scan.nextLine());
				if(choice <= 0 || choice > 2)
					System.out.print("Invalid selection,\nPlease make a different selection");
			}
			switch (choice) {
			case 1:
				viewAllCars();
				break;
			case 2:
				employeeMenu();
				break;
			}
			break;
		
		}
	}
	private static void addNewCar() {
		String model = null;
		int year = 0;
		String color = null;
		double price = 0;
		System.out.println();
		System.out.println("Register a new car");
		System.out.print("Enter the car's model: ");
		while(model == null) {
			String modelCheck = scan.nextLine();
			if(modelCheck != null) {
				model = modelCheck;
			}else {
				System.out.print("Invalid selection\nPlease enter another amount");
			}
		}
		System.out.print("Enter the car's year: ");
		while(year == 0) {
			int yearCheck = Integer.parseInt(scan.nextLine());
			if(yearCheck > 1950 && yearCheck <= 2020) {
				year = yearCheck;
			}else {
				System.out.print("Invalid selection\nPlease enter another amount");
			}
		}
		System.out.print("Enter the car's color: ");
		while(color == null) {
			String colorCheck = scan.nextLine();
			if(colorCheck != null) {
				color = colorCheck;
			}else {
				System.out.print("Invalid selection\nPlease enter another amount");
			}
		}
		System.out.print("Enter the car's total price: ");
		while(price == 0) {
			double priceCheck = Double.parseDouble(scan.nextLine());
			if(priceCheck > 1) {
				price = priceCheck;
			}else {
				System.out.print("Invalid selection\nPlease enter another amount");
			}
		}
		System.out.println();
		System.out.println("Would you like to register this car?");
		System.out.println("Model: "+model+", Year: "+year+", Color: "+color+", Price: "+price);
		System.out.println("1. Yes");
		System.out.println("2. No");
		System.out.print("Please make a selection: ");
		int choice2 = -1;
		while(choice2 <= 0 || choice2 > 2) {
			choice2 = Integer.parseInt(scan.nextLine());
			if(choice2 <= 0 || choice2 > 2)
				System.out.print("Invalid selection,\nPlease enter a different selection");
		}
		switch (choice2) {
		case 1:
			try {
				CarDAOImp cdi = new CarDAOImp();
				cdi.insertCar(model, year, color, price);
				System.out.println("Car succefully registerd");
				LogThis.LogIt("info", "Car ["+"Model: "+model+", Year: "+year+", Color: "+color+", Price: "+price+"] added");
			} catch (SQLException e) {
				LogThis.LogIt("error", "SQLEceptions during car registration");
				e.printStackTrace();
			}
		case 2:
			System.out.println("What would you like to do?");
			System.out.println("1. Add another car");
			System.out.println("2. Return to the main menu");
			System.out.print("Please make a selection: ");
			int choice = -1;
			while(choice <= 0 || choice > 2) {
				choice = Integer.parseInt(scan.nextLine());
				if(choice <= 0 || choice > 2)
					System.out.print("Invalid selection,\nPlease make a different selection");
			}
			switch (choice) {
			case 1:
				addNewCar();
				break;
			case 2:
				employeeMenu();
				break;
			}
			break;
		}
	}
}
