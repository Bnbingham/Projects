package com.revature.daoimp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.contracts.Contract;
import com.revature.contracts.Payment;
import com.revature.dao.ContractDAO;
import com.revature.system.ConnFactory;

public class ContractDAOImp implements ContractDAO{
	public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public void insertContract(int offerID, double downPayment) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call INSERTCONTRACT(?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, offerID);
		call.setDouble(2, downPayment);
		call.execute();
		call.close();
	}
	
		
	@Override
	public List<Contract> getContractList() throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM CONTRACT_TBL";
		List<Contract> contractList = new ArrayList<>();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Contract c = null;
		PaymentDAOImp pdi = new PaymentDAOImp();
		ArrayList<Payment> paymentList = (ArrayList<Payment>) pdi.getPaymentList();
		while(rs.next()) {
			ArrayList<Payment> list = new ArrayList<>();
			paymentList.stream()
			.filter(x -> {
				try {
					return x.getContractID() == rs.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return false;
			}).forEach(x -> list.add(x));;
			
			c = new Contract(
					rs.getInt(1),
					rs.getInt(2)
					,list
					);
			contractList.add(c);
		}
		return contractList;
	}
	 

}
