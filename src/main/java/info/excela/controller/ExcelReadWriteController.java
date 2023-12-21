package info.excela.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import info.excela.model.ExcelReadWriteModel;
import info.excela.service.ExcelReadWriteService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ExcelReadWriteController {

	@Autowired
	private ExcelReadWriteService excelreadwriteService;
	

	@PostMapping("/upload-data")
	public ResponseEntity<?> uploadExcel(@RequestParam ("file") MultipartFile file){
		this.excelreadwriteService.saveNewRegister(file);
		return new ResponseEntity<>("ExcelContent uploaded to DB Successfully", HttpStatus.OK);
	}	
	
	@GetMapping("/Excel-data")
	public ResponseEntity<List<ExcelReadWriteModel>> getdataofexcel(){
		return new ResponseEntity<List<ExcelReadWriteModel>>(excelreadwriteService.getalldetails(),HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void readexcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "content-Disposition";
		String headerValue = "attachment;filename=ReadWriteXL.xlsx";
		response.setHeader(headerKey,headerValue);
		excelreadwriteService.generateExcel(response);
		
		
	}
}
