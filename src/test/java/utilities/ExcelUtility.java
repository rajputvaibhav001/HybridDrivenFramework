package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	static String path;
	
	public ExcelUtility(String path) {
		ExcelUtility.path= path;
	}
	
	public int getRowCount(String xlSheet) throws IOException {
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		sheet= wb.getSheet(xlSheet);
		int rowCount = sheet.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
		
	}
	
	public int getCellCount(String xlsheet, int rownum) throws IOException {
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		sheet= wb.getSheet(xlsheet);
		row= sheet.getRow(rownum);
		int cellCount= row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
		
	}
	
	public String getCellData(String xlsheet, int rownum, int colnum) throws IOException {
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		sheet= wb.getSheet(xlsheet);
		row= sheet.getRow(rownum);
		cell= row.getCell(colnum);
		
		String data;
		try {
			//data= cell.toString();  OR
			DataFormatter format= new DataFormatter();
			data= format.formatCellValue(cell);
		}
		catch(Exception e) {
			data=" ";
		}
		
		
		wb.close();
		fi.close();
		return data;
		
	}
	
	public void setCellValue(String xlsheet, int rownum, int colnum, String Data) throws IOException {
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		sheet= wb.getSheet(xlsheet);
		row= sheet.getRow(rownum);
		cell= row.createCell(colnum);
		cell.setCellValue(Data);
		fo= new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
	}
	
	public void fillGreenColor(String xlsheet, int rownum, int colnum) throws IOException {
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		sheet= wb.getSheet(xlsheet);
		row= sheet.getRow(rownum);
		cell= row.getCell(colnum);
		
		style= wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo= new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public void fillRedColor(String xlsheet, int rownum, int colnum) throws IOException {
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		sheet= wb.getSheet(xlsheet);
		row= sheet.getRow(rownum);
		cell= row.getCell(colnum);
		
		style= wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo= new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
}
