package com.atmecs.phphotelbooking.helper;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This method is used to read excel files by giving row name and column name
 * 
 * @author magesh.suriyamoorthi
 *
 */
public class ExcelReader {
	private int rowIndex = 0;
	private int columnIndex = -1;
	private FileInputStream fileInputStream;
	private File file;
	private Workbook workBook = null;
	private Sheet sheet = null;
	private Row row = null;
	private Cell cell = null;
	private String cellValue;

	private int getColIndex(Row row, String columnName) {
		for (int colIndex = 0; colIndex < row.getLastCellNum(); colIndex++) {
			if (row.getCell(colIndex).getStringCellValue().equals(columnName.trim())) {
				columnIndex = colIndex;
				break;
			}
		}
		return columnIndex;
	}

	private int getRowIndex(String testcase) {
		for (Row row : sheet)
			for (Cell cell : row)
				if (cell.getCellType() == CellType.STRING) {
					if (cell.getRichStringCellValue().getString().trim().equals(testcase)) {
						rowIndex = row.getRowNum();
						break;
					}
					break;
				}
		return rowIndex;
	}

	private Workbook getFile(String filename) {
		file = new File(filename);
		try {
			fileInputStream = new FileInputStream(file);
			workBook = new XSSFWorkbook(fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workBook;
	}

	private Row getInitialRowInaSheet(String sheetName) {
		int index = workBook.getSheetIndex(sheetName);
		sheet = workBook.getSheetAt(index);
		row = sheet.getRow(0);

		return row;
	}

	public String getData(String filename, String sheetName, String columnName, String testcase) {
		workBook = getFile(filename);
		row = getInitialRowInaSheet(sheetName);

		columnIndex = getColIndex(row, columnName);
		rowIndex = getRowIndex(testcase);

		row = sheet.getRow(rowIndex);
		cell = row.getCell(columnIndex);

		cellValue = cell.getStringCellValue();

		try {
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellValue;
	}

}