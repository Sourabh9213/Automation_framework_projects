package com.blazedemo.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataUtil {

	XSSFWorkbook workbook;
	XSSFSheet sheet;

	public ExcelDataUtil(String excelPath, String sheetName) {
		try {
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getData(int row, int col) {
		return sheet.getRow(row).getCell(col).toString();
	}

	public int getRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}
	
	public Object[][] getCardData() {
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rowCount - 1][colCount];
		
		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				 XSSFCell cell = sheet.getRow(i).getCell(j);
	                if (cell == null) {
	                    data[i - 1][j] = "";
	                } else {
	                    data[i - 1][j] = cell.toString();
	                }}}
		System.out.println("Excel data read successfully: " + (rowCount - 1) + " rows and " + colCount + " columns.");
		return data;
	}
}
