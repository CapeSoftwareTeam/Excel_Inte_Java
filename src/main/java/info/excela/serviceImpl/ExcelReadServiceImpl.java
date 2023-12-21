package info.excela.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import info.excela.model.ClientModel;
import info.excela.model.ExcelReadWriteModel;

public class ExcelReadServiceImpl {

	public static boolean isValidExcelFile(MultipartFile file) {
		return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}
	
	public static List<ExcelReadWriteModel> getExceldataOriginal(InputStream inputStream){
		String data=""; int cellData = 0;
		List<ExcelReadWriteModel> readxl= new ArrayList<>();
		try {
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wb.getSheetAt(0);
			int rowIndex=0;
			for(Row ro:sh) {
				if(rowIndex==0){
					rowIndex++;
					continue;
				}
				Iterator<Cell> cellIterator = ro.iterator();
				int cellIndex=0;
				ExcelReadWriteModel xlread= new ExcelReadWriteModel();
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					
					if(cell.getCellType()==CellType.STRING) {
					    data = cell.getStringCellValue(); 
					   
					}
					else if(cell.getCellType()==CellType.NUMERIC) {
					    data = String.valueOf(cell.getNumericCellValue());
					    try {
						    cellData = Integer.parseInt(data);
						} catch(NumberFormatException e) {
						    double d = Double.parseDouble(data); 
						    cellData = (int) d;
						}
					}
					
//					cellData=Integer.valueOf(data);
//					System.out.println(cellData);
					switch(cellIndex){
//						case 1 : xlread.setRegisterId((int) cell.getNumericCellValue());
//						case 2 : xlread.setName(cell.getStringCellValue());
//						case 3 : xlread.setAge((int) cell.getNumericCellValue());
//						case 4 : xlread.setMobileNumber((int) cell.getNumericCellValue());
//						case 5 : xlread.setEmail(cell.getStringCellValue());
					
					
					    case 0 : xlread.setRegisterId(cellData);
					 	case 1 : xlread.setName(data);
						case 2 : xlread.setEmail(data);
						default :{
							
						}
					}
					cellIndex++;
				}
				readxl.add(xlread);
			}
		}catch(IOException e) {
			e.getStackTrace();
		}
		return readxl;
	}
	
	
	
	public static List<ExcelReadWriteModel> getExceldatasingleData(InputStream inputStream){
		String data=""; int cellData = 0;
		System.out.println("method is called");
		List<ExcelReadWriteModel> readxl= new ArrayList<>();
		
		try {
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sh = wb.getSheetAt(0);
			int rowIndex=0;
			
			for(int rowNumber = 0; rowNumber < sh.getLastRowNum(); rowNumber++) {
			    XSSFRow rowin = sh.getRow(rowNumber);
			   
			    if(rowin!=null) {	
			    	
			    for(int columnNumber = 1; columnNumber < rowin.getLastCellNum(); columnNumber++) {
			    	
			    	 ExcelReadWriteModel xlread= new ExcelReadWriteModel();
			         XSSFCell cell = rowin.getCell(columnNumber);
			        
			        	switch(rowNumber) {
			        	case 0:		        		
			        		xlread.setName(sh.getRow(rowNumber).getCell(columnNumber).getStringCellValue());		        	
			        	case 1: 
			        		xlread.setEmail(sh.getRow(rowNumber+1).getCell(columnNumber).getStringCellValue());
			        	default :{}			        	
			        	}
			        	
			        	System.out.println(rowNumber);
			        	System.out.println(columnNumber);
			        	System.out.println("Name : " +xlread.getName());			        	
			        	System.out.println("Email : " +xlread.getEmail());			        	
			        	readxl.add(xlread);	        	 
			      }	
			   break;
			   }

			}	

	    }catch(IOException e) {
		   e.getStackTrace();
	    }
		return readxl;
	}



public static List<ExcelReadWriteModel> getExceldata(InputStream inputStream){
	String data=""; int cellData = 0;
	List<ExcelReadWriteModel> readxl= new ArrayList<>();
	
	try {
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
//		for(int h=0; h<wb.getActiveSheetIndex();h++) {
//			
//		}
		XSSFSheet sh = wb.getSheetAt(0);
		  int rowCount = sh.getPhysicalNumberOfRows();
	        int colCount = sh.getRow(0).getPhysicalNumberOfCells();

	        // Iterate through columns
	        for (int colIndex = 1; colIndex < colCount; colIndex++) {
	            Iterator<Row> rowIterator = sh.iterator();
	            int rowIndex = 0;

	            // Create a Register object for each column
	            ExcelReadWriteModel xlread= new ExcelReadWriteModel();
 	            xlread.setColumnIndex(colIndex);

	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();

	                // Get the column name dynamically
	                String columnName = sh.getRow(rowIndex).getCell(0).getStringCellValue();
	                System.out.println("read column name: " +columnName);
	                Cell cell = row.getCell(colIndex,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	                System.out.println("read cell name" +cell);
	                // Set the property based on the column name using reflection
	                setPropertyValue(xlread, columnName, cell.getStringCellValue());
	                System.out.println(xlread);
	                rowIndex++;
	            }

	            readxl.add(xlread);
	        }

	        wb.close();
    }catch(IOException e) {
	   e.getStackTrace();
    }
	return readxl;
}

private static void setPropertyValue(ExcelReadWriteModel xl, String propertyName, String cellValue) {
    try {
        // Use reflection to get the setter method for the property
        Method setterMethod = ExcelReadWriteModel.class.getMethod("set" + capitalize(propertyName), String.class);

        // Invoke the setter method with the cell value
        setterMethod.invoke(xl, cellValue);
    } catch (Exception e) {
        // Handle exception as needed
        e.printStackTrace();
    }
}

private static String capitalize(String s) {
    if (s == null || s.length() == 0) {
        return s;
    }
    return s.substring(0, 1).toUpperCase() + s.substring(1);
}


public static List<ClientModel> getExcela (InputStream inputStream){
	String data=""; int cellData = 0; String cellValue;int rowvalue=0;
	int dataNo=0;
	List<ClientModel> readxl= new ArrayList<>();
	
	try {
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sh = wb.getSheetAt(0);
		int rowIndex=0;
		
		Iterator<Row> rowIterator = sh.iterator();
		System.out.println(rowIterator);
		while (rowIterator.hasNext()) {

		  Row row = rowIterator.next();
//		  System.out.println(row.getCell(0).getStringCellValue());
//		  if(row.getCell(0).getStringCellValue().equals("Details of the client")) {
			  
			  Iterator<Cell> cellIterator = row.cellIterator();
			   
			  while (cellIterator.hasNext()) {

			    Cell cell = cellIterator.next();
			    System.out.print(cell.getStringCellValue() + "\t"); 
 
                   
			    //Check the cell type and format accordingly
     
			  }
//		  }
			  
		  //For each row, iterate through all the columns
		 
		  System.out.println("");
		}
		wb.close();
		
		
//		int rowCount = sh.getLastRowNum()-sh.getFirstRowNum();
//		System.out.println("line num 217 "+rowCount);
//		for(int i=0;i<=rowCount;i++) {
//			System.out.println("line no 219 "+i);
//			 System.out.println("line no 220 "+sh.getRow(i).getCell(0).getStringCellValue());
//			 

//		  if(sh.getRow(i).getCell(0).getStringCellValue() == "Details of the client") {
//			  int rowNo = sh.getRow(i).getRowNum();
//			  System.out.println("line no 222 "+rowNo);
//			  System.out.println("line num 223"+sh.getPhysicalNumberOfRows());
//				for(int y=rowNo;y<sh.getLastRowNum();y++){
//					ClientModel xlread= new ClientModel();
//					
//					int cellCount = sh.getRow(i).getLastCellNum();
//					for(int j=0;j<cellCount;j++) {
//						
////						setPropertyValues(xlread, sh.getRow(i).getCell(j).getStringCellValue(), sh.getRow(i).getCell(j).getStringCellValue());
//						System.out.println("line no 231 "+sh.getRow(y).getCell(j).getStringCellValue());
//					}
//					y++;
//				
//		  }
//	
////			 readxl.add(xlread);
//		  } 
//		
//		}
//		
//		for(Row ro :sh) {
//			cellValue= ro.getCell(0).getStringCellValue();
//			if(cellValue == "Details of the client") {
//				
//				rowIndex++de
//				rowvalue = ro.getRowNum();
//				int rowIndextotal=ro.getLastCellNum()-rowvalue;
//				int colIndextotal=sh.getRow(rowvalue).getPhysicalNumberOfCells();
//				
//				for(int rowIndx=0;rowIndx<rowIndextotal;rowIndx++) {
//					Iterator<Cell> cellIterator = ro.iterator();
//					ClientModel xlread= new ClientModel();
//					
////						Cell cell = cellIterator.next();
//					while(cellIterator.hasNext()) {
//						  Cell cellin = cellIterator.next();
//						String RowName = cellin.getStringCellValue();
//						Cell cell = ro.getCell(rowIndex,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//		                System.out.println("read cell name : " +cell);
//		                // Set the property based on the column name using reflection
//		                setPropertyValues(xlread, RowName, cell.getStringCellValue());
//		                System.out.println(xlread);
//		                rowIndex++;
//					}	
//				}
				
				
				
				
				
				
				
				
				
				
				
				
//				Iterator<Cell> cellIterator = ro.iterator();
//				int cellIndex=0;
//				ClientModel xlread= new ClientModel();
//				while(cellIterator.hasNext()) {
//					  Cell cellin = cellIterator.next();
//					String columnName = sh.getRow(rowIndex).getCell(0).getStringCellValue();
//					Cell cell = ro.getCell(cellIndex,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//	                System.out.println("read cell name : " +cell);
//	                // Set the property based on the column name using reflection
//	                setPropertyValues(xlread, columnName, cell.getStringCellValue());
//	                System.out.println(xlread);
//	                cellIndex++;
//				}		
				
//			}else {
//				rowIndex++;
//			}
//		}
		 
    }catch(IOException e) {
	   e.getStackTrace();
    }
	return readxl;
}



private static void setPropertyValues(ClientModel xl, String propertyName, String cellValue) {
    try {
        // Use reflection to get the setter method for the property
        Method setterMethod = ClientModel.class.getMethod("set" + capitalize(propertyName), String.class);

        // Invoke the setter method with the cell value
        setterMethod.invoke(xl, cellValue);
    } catch (Exception e) {
        // Handle exception as needed
        e.printStackTrace();
    }
}





}

