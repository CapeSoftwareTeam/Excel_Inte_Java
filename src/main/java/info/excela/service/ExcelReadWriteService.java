package info.excela.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import info.excela.model.ExcelReadWriteModel;

public interface ExcelReadWriteService {

	public void generateExcel(HttpServletResponse response) throws IOException;

	public List<ExcelReadWriteModel> getalldetails();

	public void saveNewRegister(MultipartFile file);
}
