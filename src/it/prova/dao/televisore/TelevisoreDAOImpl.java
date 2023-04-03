package it.prova.dao.televisore;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import it.prova.dao.AbstractMySQLDAO;
import it.prova.model.Televisore;

public class TelevisoreDAOImpl extends AbstractMySQLDAO implements TelevisoreDAO {

	public List list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Televisore get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int update(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List findByExample(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void setConnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}

	public Televisore whichTelevisionIsTheBiggest() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int howManyTelevisionsWereProducedInARange(LocalDate data1, LocalDate data2) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List MarcaOfTelevisionsProducedInTheLastSixMonths() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
