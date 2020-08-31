package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.contracts.Contract;

public interface ContractDAO {
	
	public void insertContract(int offerID,double downPayment) throws SQLException;
	
	public List<Contract> getContractList() throws SQLException;
}
