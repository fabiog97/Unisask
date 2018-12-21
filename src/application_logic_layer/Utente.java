package application_logic_layer;

/**
 * Oggetto Utente che identifica un utente
 * @author AntonioVitiello
 */

public class Utente 
{
	private String nome;
	private String cognome;
	private String matricola;
	private String email;
	private String nazionalita;
	private String password;
	private String username;
	
	/**
	 *  Costruttore vuoto
	 */
	public Utente()
	{
		nome = null;
		cognome = null;
		matricola = null;
		email = null;
		nazionalita = null;
		password = null;
		username = null;
	}
	
	/**
	 *  Costruttore con parametri
	 *  @param nome indica il codice univoco
	 *  @param cognome indica la risposta data al quesito
	 *  @param matricola indica la data in cui è stato posto il quesito
	 *  @param email indica la domanda data al quesito
	 *  @param nazionalita indica la domanda data al quesito
	 *  @param password indica la domanda data al quesito
	 *  @param username indica la domanda data al quesito
	 */
	public Utente(String nome, String cognome, String matricola, String email, String nazionalita, String password, String username)
	{
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
		this.email = email;
		this.nazionalita = nazionalita;
		this.password = password;
		this.username = username;
	}

	/**
	 * Restituisce il nome dell'utente
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setta nome dell'utente
	 * @param nome
	 * 		indica il nuovo nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Restituisce il cognome dell'utente
	 * @return cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Setta cognome dell'utente
	 * @param cognome
	 * 		indica il nuovo cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Restituisce la matricola dell'utente
	 * @return matricola
	 */
	public String getMatricola() {
		return matricola;
	}

	/**
	 * Setta matricola dell'utente
	 * @param matricola
	 * 		indica la nuova matricola
	 */
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	/**
	 * Restituisce l'email dell'utente
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setta email dell'utente
	 * @param email
	 * 		indica la nuova email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Restituisce la nazionalità dell'utente
	 * @return nazionalita
	 */
	public String getNazionalita() {
		return nazionalita;
	}

	/**
	 * Setta nazionalita dell'utente
	 * @param nazionalita
	 * 		indica la nuova nazionalità
	 */
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	/**
	 * Restituisce la password dell'utente
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setta password dell'utente
	 * @param password
	 * 		indica la nuova password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Restituisce l'username dell'utente
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setta username dell'utente
	 * @param username
	 * 		indica il nuovo username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", matricola=" + matricola + ", email=" + email
				+ ", nazionalita=" + nazionalita + ", password=" + password + ", username=" + username + "]";
	}
	

}
