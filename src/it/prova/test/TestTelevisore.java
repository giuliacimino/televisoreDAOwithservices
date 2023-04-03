package it.prova.test;

import java.time.LocalDate;
import java.util.List;

import it.prova.model.Televisore;
import it.prova.service.MyServiceFactory;
import it.prova.service.televisore.TelevisoreService;

public class TestTelevisore {

	public static void main(String[] args) {
		
		TelevisoreService televisoreService = MyServiceFactory.getTelevisoreServiceImpl();
		
		try {
			
			System.out.println("in tabella ci sono " +televisoreService.listAll().size()+ " elementi.");
			
			testInserisciNuovo(televisoreService);
			System.out.println("in tabella ci sono " +televisoreService.listAll().size()+ " elementi.");
			
			testAggiorna(televisoreService);
			System.out.println("in tabella ci sono " +televisoreService.listAll().size()+ " elementi.");
			
			testRimuovi(televisoreService);
			System.out.println("in tabella ci sono " +televisoreService.listAll().size()+ " elementi.");
			
			testQualeEIlTelevisorePiuGrande(televisoreService);
			System.out.println("in tabella ci sono " +televisoreService.listAll().size()+ " elementi.");

			

			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	} //chiusura main
	
	
	//CRUD
	
	private static void testInserisciNuovo (TelevisoreService televisoreService) throws Exception{
		System.out.println(".......testInserimentoNuovo inizio.............");
		Televisore newTelevisoreInstance = new Televisore("lg", "lg1", 50, LocalDate.of(2015,12, 30));
		if (televisoreService.inserisciNuovo(newTelevisoreInstance) != 1)
			throw new RuntimeException("testInserimentoNuovoUser FAILED ");

		System.out.println(".......testInserimentoNuovo PASSED.............");
	}
	
	
	
	private static void testAggiorna (TelevisoreService televisoreService) throws Exception {
			System.out.println(".......testAggiorna inizio.............");
			
			List<Televisore> elencoTelevisori= televisoreService.listAll();
			if(elencoTelevisori.size()<1){
				throw new RuntimeException("errore: non sono presenti televisori sul DB");
			}

			if (televisoreService.inserisciNuovo(new Televisore("lg","lg1",70, LocalDate.of(2017, 11,11))) !=1) {
				throw new RuntimeException("testAggiorna: inserimento preliminare FAILED ");
			}

			String nuovoModello = "lg111";
			Televisore toBeUpdated = elencoTelevisori.get(0);
			toBeUpdated.setModello(nuovoModello);
			if (televisoreService.aggiorna(toBeUpdated) != 1)
				throw new RuntimeException("testUpdateUser FAILED ");

			System.out.println(".......testUpdate PASSED.............");
	}
	
	
	private static void testRimuovi (TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testRimuovi inizio.............");
		// recupero tutti gli user
		List<Televisore> interoContenutoTabella = televisoreService.listAll();
		if (interoContenutoTabella.isEmpty() || interoContenutoTabella.get(0) == null)
			throw new Exception("Non ho nulla da rimuovere");

		Long idDelPrimo = interoContenutoTabella.get(0).getId();
		if (televisoreService.rimuovi(idDelPrimo) != 1)
			throw new RuntimeException("testRimuovi FAILED ");

		System.out.println(".......testRimuovi PASSED.............");
		
	}
	
	private static void testQualeEIlTelevisorePiuGrande(TelevisoreService televisoreService) throws Exception{
		System.out.println(".........testQualeEIlTelevisorePiuGrande inizio..............");
		List<Televisore> interoContenutoTabella = televisoreService.listAll();
		if (interoContenutoTabella.isEmpty() || interoContenutoTabella.get(0) == null)
			throw new Exception("errore: non ci sono voci");
		Televisore televisorePiuGrande= televisoreService.qualeEIlTelevisorePiuGrande();
		System.out.println(televisorePiuGrande);
		System.out.println("........testQualeEIlTelevisorePiuGrande fine.........");
	}
	

}
