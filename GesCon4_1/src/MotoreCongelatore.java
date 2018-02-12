/**
 * 
 * @author mitic
 *
 * E' il fulcro e appunto il motore di tutta l'applicazione.
 * E' divisa in sezioni per meglio trovare i vari metodi
 * 1-Parte Grafica
 * 2-Controlli sugli oggetti
 * 3-Controlli sull'applicazione
 * 4-Generali
 */
import java.awt.*;
import java.io.File;
import java.io.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class MotoreCongelatore
{
	//variabili
	private Toolkit toolkit;
	private String titoloFinestre;
	private Image iconaProgramma;
	private String messaggioInformativo;
	private Color colorePannelloSopra;
	private Color colorePannelloCentro;
	private Color colorePannelloSotto;
	private Color colorePannelloSinistra;
	private Color colorePannelloDestra;
	
	private ArrayList<Prodotto> elencoProdotti;
	private ArrayList<Prodotto> listaRicerca;
	
	private String[]listaNomiCaratteriNelSistema;
	private String caratterePerVisualVoci;
	
	private JTextArea areaNote;
	private JLabel etichettaNomeProdotto;
	private JLabel etichettaDataInserimentoProdotto;
	private JLabel etichettaDataScadenzaProdotto;
	private JLabel etichettaUnit‡DiMisuraProdotto;
	private JLabel etichettaQuantit‡Prodotto;
	private JLabel etichettaIdProdotto;
	private JLabel etichettaDescrizioneDataInserimento;
	private JLabel etichettaDescrizioneDataScadenza;
	private JLabel etichettaDescrizioneQuantit‡;
	private JScrollPane pannelloScorrevoleAreaNote;
	
	private JLabel etichettaMessaggioInformativoPannelloPrincipale;
	
	private boolean avvioRicerca;
	private boolean avvioVisual;
	
	private Prodotto prodottoVisualizzato;
	private Prodotto prodottoSelezionato;
	
	private ArrayList<String>elencoUnit‡DiMisura;
	
	private boolean rosso;
	private boolean giallo;
	private boolean verde;
	
	private int iteratore = 0;
	private int ultimoID;
	
	private JPanel pannelloCentroPannelloPrincipale;
	private JPanel pannelloDestraPannelloPrincipale;
	private JPanel pannelloSottoPannelloPrincipale;
	
	private boolean esitoPartizionamento;
	
	public MotoreCongelatore()
	{
		this.setToolkit();
		this.setTitoloFinestre();
		this.setIconaProgramma();
		this.setColorePannelloSopra();
		this.setColorePannelloCentro();
		this.setColorePannelloSotto();
		this.setColorePannelloSinistra();
		this.setColorePannelloDestra();
		this.caricamentoCaratteriNelSistema();
		this.caricaDatabase();
		this.setAvvioRicerca(false);
		this.setAvvioVisual(false);
		this.setElencoUnit‡DiMisura();
	}
	
	//PARTE GRAFICA
	
	//imposto la parte iniziale del titolo delle finestre
	public void setTitoloFinestre()
	{
		this.titoloFinestre = "GesCon Vers4_1";
	}
	public String getTitoloFinestre()
	{
		return this.titoloFinestre;
	}
	
	//scelta dell'icona del programma
	public void setIconaProgramma()
	{
		this.iconaProgramma = this.getToolkit().getImage(getClass().getClassLoader().getResource("iconaProg1.gif"));  
	}
	public Image getIconaProgramma()
	{
		return this.iconaProgramma;
	}
	
	//setter e getter dei colori dei pannelli
	public void setColorePannelloSopra()
	{
		this.colorePannelloSopra = new Color(111,157,247);
	}
	public void setColorePannelloSopra(Color c)
	{
		//questo nel caso di impostazioni
		this.colorePannelloSopra = c;
	}
	public Color getColorePannelloSopra()
	{
		return this.colorePannelloSopra;
	}
	public void setColorePannelloCentro()
	{
		this.colorePannelloCentro = Color.BLACK;
	}
	public void setColorePannelloCentro(Color c)
	{
		//questo nel caso di impostazioni
		this.colorePannelloCentro = c;
	}
	public Color getColorePannelloCentro()
	{
		return this.colorePannelloCentro;
	}
	public void setColorePannelloSotto()
	{
		this.colorePannelloSotto = Color.YELLOW;
	}
	public void setColorePannelloSotto(Color c)
	{
		//questo nel caso di impostazioni
		this.colorePannelloSotto = c;
	}
	public Color getColorePannelloSotto()
	{
		return this.colorePannelloSotto;
	}
	public void setColorePannelloDestra()
	{
		this.colorePannelloDestra = new Color(111,157,247);
	}
	public void setColorePannelloDestra(Color c)
	{
		//questo nel caso di impostazioni
		this.colorePannelloDestra = c;
	}
	public Color getColorePannelloDestra()
	{
		return this.colorePannelloDestra;
	}
	public void setColorePannelloSinistra()
	{
		this.colorePannelloSinistra = new Color(111,157,247);
	}
	public void setColorePannelloSinistra(Color c)
	{
		//questo nel caso di impostazioni
		this.colorePannelloSinistra = c;
	}
	public Color getColorePannelloSinistra()
	{
		return this.colorePannelloSinistra;
	}
	
	public void setPannelloCentroPannelloPrincipale(JPanel pannello)
	{
		this.pannelloCentroPannelloPrincipale = pannello;
	}
	public JPanel getPannelloCentroPannelloPrincipale()
	{
		return this.pannelloCentroPannelloPrincipale;
	}
	public void setPannelloDestraPannelloPrincipale(JPanel pannello)
	{
		this.pannelloDestraPannelloPrincipale = pannello;
	}
	public JPanel getPannelloDestraPannelloPrincipale()
	{
		return this.pannelloDestraPannelloPrincipale;
	}
	public void setPannelloSottoPannelloPrincipale(JPanel pannello)
	{
		this.pannelloSottoPannelloPrincipale = pannello;
	}
	public JPanel getPannelloSottoPannelloPrincipale()
	{
		return this.pannelloSottoPannelloPrincipale;
	}
	
	//PARTE CONTROLLI SUGLI OGGETTI
	
	/**
	 * setta l'elenco dei prodotti
	 * @param lista
	 * Ë l'arraylist dei prodotti da settare
	 */
	public void setElencoProdotti(ArrayList<Prodotto>lista)
	{
		this.elencoProdotti = lista;
	}
	
	/**
	 * Ritorna l'elenco dei prodotti contenuti nel database
	 * @return l'Array di Prodotto
	 */
	public ArrayList<Prodotto> getElencoProdotti()
	{
		return this.elencoProdotti;
	}
	
	/**
	 * setta l'elenco dei prodotti da visualizzare e su cui lavorare in modalit‡ ricerca
	 * @param lista
	 * Ë la lista dei prodotti su cui Ë stata effettuata una ricerca
	 */
	public void setElencoRicerca(ArrayList<Prodotto>lista)
	{
		this.listaRicerca = lista;
	}
	
	/**
	 * Restituisce l'elenco dei prodotti inseriti in una ArrayList<Prodotto>
	 * @param lista Ë l'ArrayList di Prodotto inserito nel database e su cui lavorare
	 */
	public ArrayList<Prodotto> getElencoRicerca()
	{
		return this.listaRicerca;
	}
	
	/**
	 * Setta l'elenco delle unit‡ di misura da usare per le quantit‡
	 */
	public void setElencoUnit‡DiMisura()
	{
		this.elencoUnit‡DiMisura = new ArrayList<String>();
		//Aggiungere le varie unit‡ di misura che si intende usare
		this.elencoUnit‡DiMisura.add("Kg");
		this.elencoUnit‡DiMisura.add("Porzioni");
		this.elencoUnit‡DiMisura.add("grammi");
		this.elencoUnit‡DiMisura.add("Sacchetti");
	}
	
	/**
	 * Restituisce l'elenco delle unit‡ di misura usate per le quantit‡ del prodotto
	 * @return l'ArrayList di stringhe contenenti l'elenco delle unit‡ di misura adottate
	 */
	public ArrayList<String> getElencoUnit‡DiMisura()
	{
		return this.elencoUnit‡DiMisura;
	}
	
	public void setProdottoVisualizzato(Prodotto prodottoVisualizzato)
	{
		this.prodottoVisualizzato = prodottoVisualizzato;
	}
	public Prodotto getProdottoVisualizzato()
	{
		return this.prodottoVisualizzato;
	}
	public void setProdottoSelezionato(Prodotto prodottoSelezionato)
	{
		this.prodottoSelezionato = prodottoSelezionato;
	}
	public Prodotto getProdottoSelezionato()
	{
		return this.prodottoSelezionato;
	}
	
	public void setUltimoId()
	{
		try
		{
			int ultimo = this.getElencoProdotti().get(this.getElencoProdotti().size()-1).getIdProdotto();
			this.ultimoID = ultimo+1;
		}
		catch(Exception e)
		{
			this.ultimoID =1;
		}
		
	}
	public void setUltimoId(int i)
	{
		this.ultimoID = i;
	}
	public int getUltimoId()
	{
		return this.ultimoID;
	}
	
	
	//PARTE CONTROLLI SULLA LOGICA DELL'APPLICAZIONE
	
	//gestione del messaggio informativo da passare nel pannello in basso
	public void setMessaggioInformativo(String messaggioInformativo)
	{
		this.messaggioInformativo = messaggioInformativo;
	}
	public String getMessaggioInformativo()
	{
		return this.messaggioInformativo;
	}
	
	//caricamento dei file
	/**
	 * Serve per caricare il file degli oggetti dei prodotti. Se non ne trova imposta la variabile "cercandoIlFile"
	 * a true in modo che dal costruttore si possa ripetere l'operazione con un file vuoto su cui
	 * inserire prodotti... Questo Ë dovuto al fatto che nelle eccezioni che non trovi il file ne immetta uno
	 * vuoto.
	 */
	public void caricaDatabase()
	{
		try
		{
			FileInputStream f = new FileInputStream(new File("src" + File.separator + "database" + File.separator + "prodotti.ges"));
			ObjectInputStream s = new ObjectInputStream(f);
			@SuppressWarnings("unchecked")
			ArrayList<Prodotto> l = (ArrayList<Prodotto>)s.readObject();
			this.setElencoProdotti(l);
			s.close();
			System.out.println("Caricamento andato a buon fine");
			this.setMessaggioInformativo("Caricamento andato a buon fine");
		}
		catch (IOException e)
		{			
			System.out.println("Err004 Non Ë stato trovato il file ne creo uno vuoto");
			//cercandoIlFileDatabase = true;//serve per capire se Ë la prima volta che viene aperto il programma
			ArrayList<Prodotto>listaVuota = new ArrayList<Prodotto>();
			this.setElencoProdotti(listaVuota);
			this.salvaDatabase();
			this.setMessaggioInformativo("Err004 Non Ë stato trovato il file ne creo uno vuoto");
		}
		catch(Exception e)
		{
			System.out.println("Err005 Exception e durante il caricamento del database");
			this.setMessaggioInformativo("Err005 Exception e durante il caricamento del database");
		}
	}
	
	//salvataggio dei file
	/**
	 * Questo metodo inserisce i dati (oggetti) in un file chiamato "prodotti.ges" nella cartella in ci sono
	 * i file class. E' opportuno e doveroso prima caricare il file e poi salvarlo a meno di errori
	 */
	public void salvaDatabase()
	{
		try
		{
			FileOutputStream f = new FileOutputStream(new File("src"+ File.separator + "database" + File.separator + "prodotti.ges"));
			ObjectOutputStream s = new ObjectOutputStream(f);
			s.writeObject(this.getElencoProdotti());
			s.close();
			System.out.println("DataBase salvato correttamente");
			this.setMessaggioInformativo("DataBase salvato correttamente");
		}
		catch (IOException e)
		{
			System.out.println("Err002 IOException nel salvataggio dati");
			this.setMessaggioInformativo("Err002 IOException nel salvataggio dati");
		}
		catch (Exception e)
		{
			System.out.println("Err003 Problema dovuto ad eccezione ultima nel salvataggio dati");
			this.setMessaggioInformativo("Err003 Problema dovuto ad eccezione ultima nel salvataggio dati");
		}
	}
	
	/**
	 * imposta un valore booleano che tramite il metodo getter ci definisce se la sezione visual Ë aperta oppure no
	 * @param avvio
	 * Se Ë true vuol dire aperto
	 * Se Ë false vuol dire disattivato
	 */
	public void setAvvioVisual(boolean avvio)
	{
		this.avvioVisual = avvio;
	}
	public boolean getAvvioVisual()
	{
		return this.avvioVisual;
	}
	
	/**
	 * imposta un valore booleano che tramite il metodo getter ci definisce se la sezione ricerca Ë aperta oppure no
	 * @param ricerca
	 * Se Ë true vuol dire aperto
	 * Se Ë false vuol dire disattivato
	 */
	public void setAvvioRicerca(boolean ricerca)
	{
		this.avvioRicerca = ricerca;
	}
	public boolean getAvvioRicerca()
	{
		return this.avvioRicerca;
	}
	
	public void settaIlColorePerLeInformazioni()
	{
		//verifica vicinanza scadenza
		GregorianCalendar dataScadenza = this.getProdottoVisualizzato().getDataScadenza();
		GregorianCalendar dataOdierna = new GregorianCalendar();
		
		long millisecondiDataScadenza = dataScadenza.getTimeInMillis();
		long millisecondiDataOdierna = dataOdierna.getTimeInMillis();
		System.out.println(millisecondiDataScadenza);
		System.out.println(millisecondiDataOdierna);
		long differenza = millisecondiDataScadenza - millisecondiDataOdierna;
		System.out.println(differenza);
		double differenzaInGiorni = (double)differenza/86400000;
		System.out.println(differenzaInGiorni);
		if((differenzaInGiorni<10)&&(differenzaInGiorni>=5))
		{
			this.setColoreGiallo(true);
			this.setColoreRosso(false);
			this.setColoreVerde(false);
		}
		else if((differenzaInGiorni<5)&&(differenzaInGiorni>=0))
		{
			this.setColoreGiallo(true);
			this.setColoreRosso(true);
			this.setColoreVerde(false);
		}
		else if(differenzaInGiorni>=10)
		{
			this.setColoreGiallo(false);
			this.setColoreRosso(false);
			this.setColoreVerde(true);
		}
		else if(differenzaInGiorni<0)
		{
			this.setColoreGiallo(false);
			this.setColoreRosso(true);
			this.setColoreVerde(false);
		}
		
		if(this.getProdottoVisualizzato().getVerificaIntegrit‡()==true)
		{
			this.setColoreGiallo(true);
		}
	}
	public void setColoreGiallo(boolean scelta)
	{
		this.giallo = scelta;
	}
	public void setColoreRosso(boolean scelta)
	{
		this.rosso = scelta;
	}
	public void setColoreVerde(boolean scelta)
	{
		this.verde = scelta;
	}
	public boolean getColoreGiallo()
	{
		return this.giallo;
	}
	public boolean getColoreRosso()
	{
		return this.rosso;
	}
	public boolean getColoreVerde()
	{
		return this.verde;
	}
	
	
	public void setIteratore(int iteratore)
	{
		this.iteratore = iteratore;
	}
	public int getIteratore()
	{
		return this.iteratore;
	}
	
	
	public boolean verificaValidit‡Data(int gg, int mm, int aaaa)
	{
		boolean verifica = false;
		int mese = mm-1;
		GregorianCalendar dataImpostata = new GregorianCalendar(aaaa, mm-1, gg);
		if(mese==dataImpostata.get(Calendar.MONTH))
		{
			verifica=true;
		}
		System.out.println(verifica);
		return verifica;
	}
	
	public void partizionaProdotto(double quantit‡DaTogliere)
	{
		this.setEsitoPartizionamento(false);
		if(this.getProdottoSelezionato().getQuantit‡Prodotto()==0)
		{
			this.setMessaggioInformativo("Il Prodotto Non Contiente Nulla");
		}
		else if(this.getProdottoSelezionato().getQuantit‡Prodotto()>0)
		{
			double iniziale = this.getProdottoSelezionato().getQuantit‡Prodotto();
			double risultato = iniziale-quantit‡DaTogliere;
			if(risultato<0)
			{
				this.setMessaggioInformativo("E' stato tolto pi˘ del dovuto");
			}
			else if(risultato==0)
			{
				this.getProdottoSelezionato().setVerificaIntegrit‡(true);
				this.setMessaggioInformativo("Hai praticamente preso tutto il prodotto, eliminalo!");
			}
			else if(risultato>0)
			{
				this.setMessaggioInformativo("Prodotto partizionato");
				System.out.println(risultato);
				//in questo caso settare un boolean a true affinchË si possa verificare se la confezione Ë stata aperta
				this.getProdottoSelezionato().setQuantit‡Prodotto(risultato);
				this.getProdottoSelezionato().setVerificaIntegrit‡(true);
				this.setProdottoVisualizzato(this.getProdottoSelezionato());
				this.setEsitoPartizionamento(true);
				this.getEtichettaQuantit‡Prodotto().setText(getProdottoSelezionato().getQuantit‡Prodotto()+"");
				this.salvaDatabase();
			}
		}
	}
	public void setEsitoPartizionamento(boolean esitoPartizionamento)
	{
		this.esitoPartizionamento = esitoPartizionamento;
	}
	public boolean getEsitoPartizionamento()
	{
		return this.esitoPartizionamento;
	}
	
	public void eliminaProdotto(int id)
	{
		for(int i=0; i<this.getElencoProdotti().size(); i++)
		{
			if(id==this.getElencoProdotti().get(i).getIdProdotto())
			{				
				getElencoProdotti().remove(i);
				this.setMessaggioInformativo("Eliminato il prodotto con Id " + id);
				this.salvaDatabase();
			}
		}
	}
	
	//GENERALI
	
	//avvio il toolkit per le varie funzionali‡ che offre e che servono in pi˘ pagine
	public void setToolkit()
	{
		this.toolkit = Toolkit.getDefaultToolkit();
	}
	public Toolkit getToolkit()
	{
		return this.toolkit;
	}
	
	/**
	 * Attivando questo metodo si caricano nel motore tutta la lista dei caratteri esistenti nel sistema.
	 * Sar‡ successivamente possibile prendere con certezza uno dei caratteri effettivamente esistente.
	 */
	public void caricamentoCaratteriNelSistema()
	{
		this.listaNomiCaratteriNelSistema =
			GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	}
	
	/**
	 * 
	 * @return ritorna una array di stringhe contenenti tutti i nomi dei caratteri presenti nel sistema
	 */
	public String[] getListaNomiCaratteriNelSistema()
	{
		return this.listaNomiCaratteriNelSistema;
	}
	
	/**
	 * metodo che, se invocato imposta il carattere da utilizzare per il pannello centrale.
	 * Questo metodo controlla che nel sistema ci sia un carattere impostato. Se c'Ë lo usa altrimenti
	 * ne imposta uno che sicuramente esiste nel sistema dell'utente.
	 * Per funzionare occore prima chiamare il metodo caricamentoCaratteriNelSistema
	 */
	public void impostaCaratterePerVisualVoci()
	{
		String carattere = "Monospaced";
		for(int i = 0; i < this.getListaNomiCaratteriNelSistema().length; i++)
		{
			
			if(this.getListaNomiCaratteriNelSistema()[i].equalsIgnoreCase("Verdana"))
			{
				carattere = getListaNomiCaratteriNelSistema()[i];
			}
		}
		this.caratterePerVisualVoci = carattere;
	}
	
	/**
	 * 
	 * @return ritorna il carattere impostato da utilizzare nel pannello centrale.
	 */
	public String getCarattereVisualVoci()
	{
		return this.caratterePerVisualVoci;
	}
	
	//impostazioni delle etichette del pannello principale centrale per poterle rendere visibili o no anche da altre parti del programma
	public void setEtichettaNomeProdotto(JLabel etichettaNomeProdotto)
	{
		this.etichettaNomeProdotto = etichettaNomeProdotto;
	}
	public JLabel getEtichettaNomeProdotto()
	{
		return this.etichettaNomeProdotto;
	}
	
	public void setEtichettaDataInserimentoProdotto(JLabel etichettaDataInserimentoProdotto)
	{
		this.etichettaDataInserimentoProdotto = etichettaDataInserimentoProdotto;
	}
	public JLabel getEtichettaDataInserimentoProdotto()
	{
		return this.etichettaDataInserimentoProdotto;
	}
	
	public void setEtichettaDataScadenzaProdotto(JLabel etichettaDataScadenzaProdotto)
	{
		this.etichettaDataScadenzaProdotto = etichettaDataScadenzaProdotto;
	}
	public JLabel getEtichettaDataScadenzaProdotto()
	{
		return this.etichettaDataScadenzaProdotto;
	}
	
	public void setEtichettaUnit‡DiMisuraProdotto(JLabel etichettaUnit‡DiMisuraProdotto)
	{
		this.etichettaUnit‡DiMisuraProdotto = etichettaUnit‡DiMisuraProdotto;
	}
	public JLabel getEtichettaUnit‡DiMisuraProdotto()
	{
		return this.etichettaUnit‡DiMisuraProdotto;
	}
	
	public void setEtichettaQuantit‡Prodotto(JLabel etichettaQuantit‡Prodotto)
	{
		this.etichettaQuantit‡Prodotto = etichettaQuantit‡Prodotto;
	}
	public JLabel getEtichettaQuantit‡Prodotto()
	{
		return this.etichettaQuantit‡Prodotto;
	}
	
	public void setEtichettaIdProdotto(JLabel etichettaIdProdotto)
	{
		this.etichettaIdProdotto = etichettaIdProdotto;
	}
	public JLabel getEtichettaIdProdotto()
	{
		return this.etichettaIdProdotto;
	}
	
	public void setEtichettaDescrizioneDataInserimento(JLabel etichettaDescrizioneDataInserimento)
	{
		this.etichettaDescrizioneDataInserimento = etichettaDescrizioneDataInserimento;
	}
	public JLabel getEtichettaDescrizioneDataInserimento()
	{
		return this.etichettaDescrizioneDataInserimento;
	}
	
	public void setEtichettaDescrizioneDataScadenza(JLabel etichettaDescrizioneDataScadenza)
	{
		this.etichettaDescrizioneDataScadenza = etichettaDescrizioneDataScadenza;
	}
	public JLabel getEtichettaDescrizioneDataScadenza()
	{
		return this.etichettaDescrizioneDataScadenza;
	}
	
	public void setAreaNote(JTextArea areaNote)
	{
		this.areaNote = areaNote;
	}
	public JTextArea getAreaNote()
	{
		return this.areaNote;
	}
	
	public void setPannelloScorrevoleAreaNote(JScrollPane pannelloScorrevoleAreaNote)
	{
		this.pannelloScorrevoleAreaNote = pannelloScorrevoleAreaNote;
	}
	
	public void setEtichettaDescrizioneQuantit‡(JLabel etichettaDescrizioneQuantit‡)
	{
		this.etichettaDescrizioneQuantit‡ = etichettaDescrizioneQuantit‡;
	}
	public JLabel getEtichettaDescrizioneQuantit‡()
	{
		return this.etichettaDescrizioneQuantit‡;
	}
	
	public void setEtichettaMessaggioInformativoPannelloPrincipale(JLabel etichettaMessaggioInformativoPannelloPrincipale)
	{
		this.etichettaMessaggioInformativoPannelloPrincipale = etichettaMessaggioInformativoPannelloPrincipale;
	}
	public JLabel getEtichettaMessaggioInformativoPannelloPrincipale()
	{
		return this.etichettaMessaggioInformativoPannelloPrincipale;
	}
	
	//test
	public static void main(String[]args)
	{
		MotoreCongelatore mot = new MotoreCongelatore();
		mot.caricaDatabase();
		mot.salvaDatabase();
		mot.impostaCaratterePerVisualVoci();
		System.out.println(mot.getCarattereVisualVoci());
	}
	
	
}
