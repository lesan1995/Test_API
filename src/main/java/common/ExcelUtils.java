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
			int totalCols=ExcelWSheet.getRow(0).getPhysicalNumberOfCells()-3;

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
	public static void WriteResult(int row,String actual,String result) {
		int totalCols=ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
		XSSFRow rowCurrent=ExcelWSheet.getRow(row);
		XSSFCell cellActual=rowCurrent.getCell(totalCols-2);
		if (cellActual == null) {
			cellActual = rowCurrent.createCell(totalCols-2);
	    }
		cellActual.setCellType(Cell.CELL_TYPE_STRING);
		cellActual.setCellValue(actual);
		XSSFCell cellResult=rowCurrent.getCell(totalCols-1);
		if (cellResult == null) {
			cellResult = rowCurrent.createCell(totalCols-1);
	    }
		cellResult.setCellType(Cell.CELL_TYPE_STRING);
		cellResult.setCellValue(result);
		
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
