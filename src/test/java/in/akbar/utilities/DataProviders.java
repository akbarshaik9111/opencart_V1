package in.akbar.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "logindata")
	public String[][] data() throws IOException {
		String path = ".\\testdata\\Opencart_LoginData.xlsx"; //TAKING XL FILE FROM TESTDATA
		
		ExcelUtility xlutil = new ExcelUtility(path); //CREATING AN OBJECT FOR XLUTILITY
		
		int rowCount = xlutil.getRowCount("Sheet1");
		int cellCount = xlutil.getCellCount("Sheet1", 1);
		
		String[][] loginData = new String[rowCount][cellCount]; //CREATED FOR TWO DIMENSION ARRAY WHICH CAN STORE THE DATA USER AND PASSWORD
		
		for(int r = 1; r <= rowCount; r++) { //1   /READ THE DATA FROM XL STORING IN TWO DEMINSIONAL ARRAY
			
			for(int c = 0; c < cellCount; c++) { //0    r IS ROWS c IS COL
				loginData[r-1][c] = xlutil.getCellData("Sheet1", r, c); //1,0
			}
			
		}
		
		return loginData; //RETURNING TWO DIMENSION ARRAY
	}
}