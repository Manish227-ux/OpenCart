package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	String path;
	
	FileInputStream file;
	XSSFWorkbook myWorkbook;
	XSSFSheet mySheet;
	XSSFRow myRow;
	XSSFCell myCell;
	
	
	public ExcelUtility(String path) {
		
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		
		file = new FileInputStream(path);
		myWorkbook = new XSSFWorkbook(file);
		mySheet = myWorkbook.getSheet(sheetName);
		//
		
		int rowNum = mySheet.getLastRowNum();
		
		return rowNum;
		
	}
	
	
	public int getCellCount(String sheetName, int rowNo) throws IOException {
		
		file = new FileInputStream(path);
		myWorkbook = new XSSFWorkbook(file);
		mySheet = myWorkbook.getSheet(sheetName);
		myRow = mySheet.getRow(rowNo);
		
		int cellNum = myRow.getPhysicalNumberOfCells();	
		
		myWorkbook.close();
		file.close();
		
		return cellNum;
		
	}
	
	public String getCellData(String sheetName, int rowNo, int cellNo) throws IOException{
		
		file = new FileInputStream(path);
		myWorkbook = new XSSFWorkbook(file);
		mySheet = myWorkbook.getSheet(sheetName);
		myRow = mySheet.getRow(rowNo);
		myCell = myRow.getCell(cellNo);
		
		DataFormatter df = new DataFormatter();
		String data;
		try {
			data = df.formatCellValue(myCell);
		} catch (Exception e) {
			
			data = "";
		}
		
		
		myWorkbook.close();
		file.close();
		
		return data;
		
		
	}

}
