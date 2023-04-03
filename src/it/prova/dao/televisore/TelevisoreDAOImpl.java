package it.prova.dao.televisore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.prova.dao.AbstractMySQLDAO;
import it.prova.model.Televisore;

public class TelevisoreDAOImpl extends AbstractMySQLDAO implements TelevisoreDAO {

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public List<Televisore> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Televisore> result = new ArrayList<Televisore>();

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from televisore")) {

			while (rs.next()) {
				Televisore televisoreTemp = new Televisore();
				televisoreTemp.setMarca(rs.getString("marca"));
				televisoreTemp.setModello(rs.getString("modello"));
				televisoreTemp.setPollici(rs.getInt("pollici"));
				televisoreTemp.setDataProduzione(
						rs.getDate("dataproduzione") != null ? rs.getDate("dataproduzione").toLocalDate() : null);
				televisoreTemp.setId(rs.getLong("ID"));
				result.add(televisoreTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public Televisore get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Televisore result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from televisore where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Televisore();
					result.setMarca(rs.getString("marca"));
					result.setModello(rs.getString("modello"));
					result.setPollici(rs.getInt("pollici"));
					result.setDataProduzione(
							rs.getDate("dataproduzione") != null ? rs.getDate("dataproduzione").toLocalDate() : null);
					result.setId(rs.getLong("id"));
				} else {
					result = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public int update(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE televisore SET marca=?, modello=?, pollici=?, dataProduzione=? where id=?;")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			ps.setDate(4, java.sql.Date.valueOf(input.getDataProduzione()));
			ps.setLong(5, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public int insert(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO televisore (marca, modello, pollici, dataProduzione) VALUES (?, ?, ?, ?);")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setDate(4, java.sql.Date.valueOf(input.getDataProduzione()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public int delete(Long input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM televisore WHERE ID=?")) {
			ps.setLong(1, input);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public List<Televisore> findByExample(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Televisore> result = new ArrayList<Televisore>();

		String query = "select * from televisore where 1=1 ";
		if (input.getMarca() != null && !input.getMarca().isEmpty()) {
			query += " and marca like '" + input.getMarca() + "%' ";
		}

		if (input.getModello() != null && !input.getModello().isEmpty()) {
			query += " and modello like '" + input.getModello() + "%' ";
		}

		if (input.getPollici() != 0) {
			query += " and pollici = '" + input.getPollici() + " ' ";
		}

		if (input.getDataProduzione() != null) {
			query += " and dataproduzione='" + java.sql.Date.valueOf(input.getDataProduzione()) + "' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				Televisore televisoreTemp = new Televisore();
				televisoreTemp.setMarca(rs.getString("marca"));
				televisoreTemp.setModello(rs.getString("modello"));
				televisoreTemp.setPollici(rs.getInt("pollici"));
				televisoreTemp.setDataProduzione(
						rs.getDate("dataproduzione") != null ? rs.getDate("dataproduzione").toLocalDate() : null);
				;
				televisoreTemp.setId(rs.getLong("id"));
				result.add(televisoreTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public Televisore whichTelevisionIsTheBiggest() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		Televisore temp = null;
		try (PreparedStatement ps = connection.prepareStatement(
				"select * from televisore t where t.pollici = (select max(t.pollici) from televisore)")) {
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				temp = new Televisore();
				temp.setMarca(rs.getString("marca"));
				temp.setModello(rs.getString("modello"));
				temp.setPollici(rs.getInt("pollici"));
				temp.setDataProduzione(
						rs.getDate("dataproduzione") != null ? rs.getDate("dataproduzione").toLocalDate() : null);
				temp.setId(rs.getLong("id"));
			} else {
				temp = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return temp;
	}

	public int howManyTelevisionsWereProducedInARange(LocalDate data1, LocalDate data2) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (data1 == null) {
			throw new Exception("Valore non ammesso.");
		}
		if (data2 == null) {
			throw new Exception("valore non ammesso.");
		}
		int count = 0;

		try (PreparedStatement ps = connection.prepareStatement(
				"select count(*) from televisore where dataproduzione > ? and dataproduzione < ? ;")) {
			ps.setDate(1, java.sql.Date.valueOf(data1));
			ps.setDate(2, java.sql.Date.valueOf(data2));

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					count = rs.getInt("count(*)");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}

	public List<String> marcaOfTelevisionsProducedInTheLastSixMonths() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		List<String> marche = new ArrayList<String>();
		try (PreparedStatement ps = connection
				.prepareStatement("select distinct (marca) from televisore t where t.dataproduzione > ?")) {
			ps.setDate(1, java.sql.Date.valueOf(LocalDate.now().minusMonths(6)));
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					String marcaTemp = "";
					marcaTemp = rs.getString("marca");
					marche.add(marcaTemp);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return marche;
		}
	}
}
