package info.excela.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import info.excela.model.ExcelReadWriteModel;

@Repository
public interface ExcelReadWriteRepository extends CrudRepository<ExcelReadWriteModel, String>{

	public List<ExcelReadWriteModel> findAll();
}
