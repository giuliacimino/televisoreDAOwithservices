package it.prova.service.televisore;

import java.time.LocalDate;
import java.util.List;

import it.prova.dao.televisore.TelevisoreDAO;
import it.prova.model.Televisore;

public interface TelevisoreService {
	public void setTelevisoreDao(TelevisoreDAO televisoreDao);
	public List<Televisore> listAll() throws Exception;

	public Televisore findById(Long idInput) throws Exception;

	public int aggiorna(Televisore input) throws Exception;

	public int inserisciNuovo(Televisore input) throws Exception;

	public int rimuovi(Long input) throws Exception;
	
	public Televisore qualeEIlTelevisorePiuGrande() throws Exception;
	
	public int quantiTelevisoriSonoStatiProdottiInUnIntervalloDiDate(LocalDate data1, LocalDate data2) throws Exception;
	 
	public List<String> marcaTelevisoriProdottiNegliUltimiSeiMesi () throws Exception;
	
	

}
