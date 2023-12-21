package info.excela.serviceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import info.excela.model.ClientModel;
import info.excela.model.ExcelReadWriteModel;
import info.excela.repository.ClientRepository;
import info.excela.repository.ExcelReadWriteRepository;
import info.excela.service.ExcelReadWriteService;

@Service
public class ExcelreadWriteServiceImpl implements ExcelReadWriteService{


	@Autowired
	private ExcelReadWriteRepository excelRepo;
	
	@Autowired
	private ClientRepository clRepo;
	
	@Override
	public void generateExcel(HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<ExcelReadWriteModel> register= (List<ExcelReadWriteModel>) excelRepo.findAll();
//		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sh = wb.createSheet("ReadWriteXL");
		
		XSSFRow row = sh.createRow(0);
		row.createCell(0).setCellValue("Id");
		
		XSSFRow row1 = sh.createRow(1);
		row.createCell(1).setCellValue("name");
		
//		XSSFRow rows1 = sh.createRow(2);
//		row.createCell(1).setCellValue("age");
		
//		XSSFRow rows2 = sh.createRow(3);
//		row.createCell(2).setCellValue("Mobile-Number");
//		
		XSSFRow rows3 = sh.createRow(2);
		row.createCell(2).setCellValue("email");
		
		int dataRowIndex =1;
		for(ExcelReadWriteModel ro : register ) {
			XSSFRow rows = sh.createRow(dataRowIndex);
			rows.createCell(0).setCellValue(ro.getRegisterId());
			rows.createCell(1).setCellValue(ro.getName());
			rows.createCell(2).setCellValue(ro.getEmail());
			dataRowIndex++;
		}
		ServletOutputStream servletOutputStream; 
        servletOutputStream  = response.getOutputStream(); 
		wb.write(servletOutputStream);
		wb.close();
//		ops.close();
	}


	@Override
	public List<ExcelReadWriteModel> getalldetails() {
		// TODO Auto-generated method stub
		return excelRepo.findAll() ;
	}


	@Override
	public void saveNewRegister(MultipartFile file) {
		// TODO Auto-generated method stub
		if(ExcelReadServiceImpl.isValidExcelFile(file)) {
			try {
				List<ExcelReadWriteModel> readxcel = ExcelReadServiceImpl.getExceldata(file.getInputStream());
				this.excelRepo.saveAll(readxcel);
			    List<ClientModel> clxl = ExcelReadServiceImpl.getExcela(file.getInputStream());
			    this.clRepo.saveAll(clxl);
			}catch(IOException e) {
				throw new IllegalArgumentException("This file is not excel file");
			}
		}
	}
	
	
}
