package it.prova.service.televisore;

import java.time.LocalDate;
import java.util.List;

import it.prova.dao.televisore.TelevisoreDAO;
import it.prova.model.Televisore;

public class TelevisoreServiceImpl implements TelevisoreService {

	public void setTelevisoreDao(TelevisoreDAO televisoreDao) {
		// TODO Auto-generated method stub
		
	}

	public List listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Televisore findById(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int aggiorna(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int inserisciNuovo(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public int rimuovi(Televisore input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public Televisore qualeEIlTelevisorePiuGrande() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int quantiTelevisoriSonoStatiProdottiInUnIntervalloDiDate(LocalDate data1, LocalDate data2)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List marcaTelevisoriProdottiNegliUltimiSeiMesi() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
