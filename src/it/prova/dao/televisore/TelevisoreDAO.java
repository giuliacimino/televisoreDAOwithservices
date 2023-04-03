package it.prova.dao.televisore;

import java.time.LocalDate;
import java.util.List;

import it.prova.dao.IBaseDAO;
import it.prova.model.Televisore;

public interface TelevisoreDAO extends IBaseDAO<Televisore>{
	public Televisore whichTelevisionIsTheBiggest() throws Exception;
	public int howManyTelevisionsWereProducedInARange (LocalDate data1, LocalDate data2) throws Exception;
	public List<String> marcaOfTelevisionsProducedInTheLastSixMonths () throws Exception;
	
	

}
