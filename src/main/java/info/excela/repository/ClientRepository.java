package info.excela.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import info.excela.model.ClientModel;
import info.excela.model.ExcelReadWriteModel;


@Repository
public interface ClientRepository extends CrudRepository<ClientModel, String>{

	public List<ClientModel> findAll();

}
 


