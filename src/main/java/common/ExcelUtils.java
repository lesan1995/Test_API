package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;
	
	private static String filePath;

	@SuppressWarnings("unused")
	private static XSSFRow Row;
	/**
	 * Get data from table
	 * @param FilePath
	 * @param SheetName
	 * @param totalCols
	 * @return
	 * @throws Exception
	 */

	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {

		String[][] tabArray = null;
		filePath=FilePath;

		try {

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startRow = 1;

			int startCol = 1;

			int ci, cj;

			int totalRows = ExcelWSheet.getLastRowNum();
			System.out.println("totalRows:"+totalRows);
			//int totalCols=ExcelWSheet.getRow(0).getPhysicalNumberOfCells()-1;
			int totalCols=ExcelWSheet.getRow(0).getPhysicalNumberOfCells()-5;

			// you can write a function as well to get Column count


			tabArray = new String[totalRows][totalCols];

			ci = 0;

			for (int i = startRow; i <= totalRows; i++, ci++) {

				cj = 0;

				for (int j = startCol; j <= totalCols; j++, cj++) {

					tabArray[ci][cj] = getCellData(i, j);

					//System.out.println(tabArray[ci][cj]);

				}

			}

		}

		catch (FileNotFoundException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (tabArray);

	}
	/**
	 * Get cell data
	 * @param RowNum
	 * @param ColNum
	 * @return
	 * @throws Exception
	 */

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			@SuppressWarnings("deprecation")
			int dataType = Cell.getCellType();

			if (dataType == 3) {

				return "";

			} else {

				String CellData = Cell.getStringCellValue();

				return CellData;

			}
		} catch (Exception e) {

			System.out.println(e.getMessage());

			throw (e);

		}

	}
	/**
	 * Write Result and actual to excel file
	 * @param row
	 * @param actual
	 * @param result
	 */
	public static void WriteResult(int row,String messageActual,String codeActual,String dataActual,String result) {
		int totalCols=ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
		XSSFRow rowCurrent=ExcelWSheet.getRow(row);
		
		XSSFCell cellMessageActual=rowCurrent.getCell(8);
		if (cellMessageActual == null) {
			cellMessageActual = rowCurrent.createCell(8);
	    }
		cellMessageActual.setCellType(Cell.CELL_TYPE_STRING);
		cellMessageActual.setCellValue(messageActual);
		
		XSSFCell cellCodeActual=rowCurrent.getCell(9);
		if (cellCodeActual == null) {
			cellCodeActual = rowCurrent.createCell(9);
	    }
		cellCodeActual.setCellType(Cell.CELL_TYPE_STRING);
		cellCodeActual.setCellValue(codeActual);
		
		XSSFCell cellDataActual=rowCurrent.getCell(10);
		if (cellDataActual == null) {
			cellDataActual = rowCurrent.createCell(10);
	    }
		cellDataActual.setCellType(Cell.CELL_TYPE_STRING);
		cellDataActual.setCellValue(dataActual);
		
		XSSFCell cellResultActual=rowCurrent.getCell(11);
		if (cellResultActual == null) {
			cellResultActual = rowCurrent.createCell(11);
	    }
		cellResultActual.setCellType(Cell.CELL_TYPE_STRING);
		cellResultActual.setCellValue(result);
		
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filePath);
			ExcelWBook.write(fos);
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
