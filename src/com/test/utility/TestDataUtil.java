package com.test.utility;

import java.util.ArrayList;

import com.excel.utility.Xls_Reader;

public class TestDataUtil {

	static Xls_Reader reader;
	
	public static ArrayList<Object[]> getDataFromExcel(){
		ArrayList<Object[]> myData=new ArrayList<Object[]>();
		try {
			reader=new Xls_Reader("G:/Eclipse-workspace/FreeCRM/src/com/testdata/freecrm.xlsx");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		for (int rowNum=2; rowNum<=reader.getRowCount("NewContact"); rowNum++) {
			
			String FirstName= reader.getCellData("NewContact", "FirstName", rowNum);
			String LastName= reader.getCellData("NewContact", "LastName", rowNum);
			String Company= reader.getCellData("NewContact", "Company", rowNum);
			String Description= reader.getCellData("NewContact", "Description", rowNum);
			String Position= reader.getCellData("NewContact", "Position", rowNum);
			String Department= reader.getCellData("NewContact", "Department", rowNum);
			
			myData.add(new Object[]{FirstName, LastName, Company, Description, Position, Department});
		}
		
		return myData;
	}
	
}
