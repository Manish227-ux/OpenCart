package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "LoginData")
	public Object[][] getData() throws IOException{
		
		String path = "C:\\Users\\manis\\eclipse-workspace\\OpenCart\\testData\\loginData.xlsx";
		
		ExcelUtility ex = new ExcelUtility(path);
		
		int rowCount = ex.getRowCount("Sheet1");
		int cellCount = ex.getCellCount("Sheet1", rowCount);
		
		String[][] data = new String[rowCount][cellCount];
		
		for(int i = 1; i<=rowCount; i++) {
			
			for(int j = 0; j<cellCount; j++) {
				
				data[i-1][j] = ex.getCellData("Sheet1", i, j);
			}
		}
		
		return data;
	}

}
