import java.io.Serializable;
import java.util.*;
public class Prodotto implements Serializable
{
	private int id;
	private static int nextID = 1;
	private String nomeProdotto;
	private GregorianCalendar dataInserimento;
	private GregorianCalendar dataScadenza;
	private String unit‡DiMisura;
	private double quantit‡;
	private boolean verificaIntegrit‡;
	private String note;
	
	private int giornoInserimento;
	private int meseInserimento;;
	private int annoInserimento;
	private int giornoScadenza;
	private int meseScadenza;
	private int annoScadenza;
	
	private String stringaDataInserimento;
	private String stringaDataScadenza;
	
	public Prodotto(String nomeProdotto,int imGG, int imMM,int imAAAA, int scGG, int scMM, int scAAAA, String unit‡
			, double quantit‡, boolean integrit‡, String note)
	{
		this.setIdProdotto(this.nextID);
		this.setNomeProdotto(nomeProdotto);
		this.setDataInserimento(imGG, imMM, imAAAA);
		this.setDataScadenza(scGG, scMM, scAAAA);
		this.setUnit‡DiMisuraProdotto(unit‡);
		this.setQuantit‡Prodotto(quantit‡);
		this.setVerificaIntegrit‡(integrit‡);
		this.setNote(note);
		
	}
	public Prodotto(int Id, String nomeProdotto,int imGG, int imMM,int imAAAA, int scGG, int scMM, int scAAAA, String unit‡
			, double quantit‡, boolean integrit‡, String note)
	{
		this.setIdProdotto(Id);
		this.setNomeProdotto(nomeProdotto);
		this.setDataInserimento(imGG, imMM, imAAAA);
		this.setDataScadenza(scGG, scMM, scAAAA);
		this.setUnit‡DiMisuraProdotto(unit‡);
		this.setQuantit‡Prodotto(quantit‡);
		this.setVerificaIntegrit‡(integrit‡);
		this.setNote(note);
	}
	
	//metodi setter e getter
	
	//imposto ID univoco
	public void setIdProdotto(int IdProdotto)
	{
		this.nextID = IdProdotto;
		this.id = nextID;
		this.nextID++;
	}
	public int getIdProdotto()
	{
		return this.id;
	}
	
	//imposto il nome del prodotto e lo incapsulo
	public void setNomeProdotto(String nomeProdotto)
	{
		this.nomeProdotto=nomeProdotto;
	}
	public String getNomeProdotto()
	{
		return this.nomeProdotto;
	}
	
	//imposto la data di inserimento e incapsulo
	public void setDataInserimento(int gg, int mm, int aa)
	{
		this.dataInserimento = new GregorianCalendar(aa,mm-1,gg);
		
		//incapsulo i vari dati della data per ottenere una stringa nel formato italiano
		this.setGiornoInserimento(gg);
		this.setMeseInserimento(mm);
		this.setAnnoInserimento(aa);
		this.setStringaDataInserimento();
	}
	public GregorianCalendar getDataInserimento()
	{
		return this.dataInserimento;
	}
	
	//imposto la data di scadenza e incapsulo
	public void setDataScadenza(int gg, int mm, int aa)
	{
		this.dataScadenza = new GregorianCalendar(aa,mm-1,gg);
		//incapsulo i vari dati della data per ottenere una stringa nel formato italiano
		this.setGiornoScadenza(gg);
		this.setMeseScadenza(mm);
		this.setAnnoScadenza(aa);
		this.setStringaDataScadenza();
	}
	public GregorianCalendar getDataScadenza()
	{
		return this.dataScadenza;
	}
	
	public void setGiornoInserimento(int giornoImmissione)
	{
		this.giornoInserimento = giornoImmissione;
	}
	public int getGiornoInserimento()
	{
		return this.giornoInserimento;
	}
	public void setMeseInserimento(int meseImmissione)
	{
		this.meseInserimento = meseImmissione;
	}
	public int getMeseInserimento()
	{
		return this.meseInserimento;
	}
	public void setAnnoInserimento(int annoImmissione)
	{
		this.annoInserimento = annoImmissione;
	}
	public int getAnnoInserimento()
	{
		return this.annoInserimento;
	}
	public void setGiornoScadenza(int giornoScadenza)
	{
		this.giornoScadenza = giornoScadenza;
	}
	public int getGiornoScadenza()
	{
		return this.giornoScadenza;
	}
	public void setMeseScadenza(int meseScadenza)
	{
		this.meseScadenza = meseScadenza;
	}
	public int getMeseScadenza()
	{
		return this.meseScadenza;
	}
	public void setAnnoScadenza(int annoScadenza)
	{
		this.annoScadenza = annoScadenza;
	}
	public int getAnnoScadenza()
	{
		return this.annoScadenza;
	}
	
	//creo la stringa della data di inserimento nel congelatore
	public void setStringaDataInserimento()
	{
		this.stringaDataInserimento = this.getGiornoInserimento() + " / " + this.getMeseInserimento() + " / " + this.getAnnoInserimento();
	}
	public String getStringaDataInserimento()
	{
		return this.stringaDataInserimento;
	}
	
	//creo la stringa della data di scadenza del prodotto
	public void setStringaDataScadenza()
	{
		this.stringaDataScadenza = this.getGiornoScadenza() + " / " + this.getMeseScadenza() + " / " + this.getAnnoScadenza();
	}
	public String getStringaDataScadenza()
	{
		return this.stringaDataScadenza;
	}
	
	//incapsulo l'unit‡ di misura utilizzata
	public void setUnit‡DiMisuraProdotto(String unit‡DiMisura)
	{
		this.unit‡DiMisura = unit‡DiMisura;
	}
	public String getUnit‡DiMisuraProdotto()
	{
		return this.unit‡DiMisura;
	}
	
	//imposto e incapsulo la quantit‡
	public void setQuantit‡Prodotto(double quantit‡)
	{
		this.quantit‡ = quantit‡;
	}
	public double getQuantit‡Prodotto()
	{
		return this.quantit‡;
	}
	
	/**
	 * @param integrit‡
	 * Indica se il prodotto Ë stato aperto oppure no.
	 * False = integro;
	 * True = Aperto e/o partizionato
	 */
	public void setVerificaIntegrit‡(boolean integrit‡)
	{
		this.verificaIntegrit‡ = integrit‡;
	}
	public boolean getVerificaIntegrit‡()
	{
		return this.verificaIntegrit‡;
	}
	
	//incapsulo le note riguardanti il prodotto
	public void setNote(String note)
	{
		if(note==null||note.length()==0)
		{
			note = "####";
		}
		this.note = note;
	}
	public String getNote()
	{
		return this.note;
	}
	
	//test verifica funzionali‡
	public static void main(String[]args)
	{
		Prodotto prodotto = new Prodotto(3,"carne", 27, 1, 2018, 30, 3, 2019, "sacchetti", 4.5, true, "");
		System.out.println(prodotto.getIdProdotto());
		System.out.println(prodotto.getNomeProdotto());
		System.out.println(prodotto.getStringaDataInserimento());
		System.out.println(prodotto.getStringaDataScadenza());
		System.out.println(prodotto.getUnit‡DiMisuraProdotto() + " " + prodotto.getQuantit‡Prodotto());
		System.out.println("Integrit‡ = " + prodotto.getVerificaIntegrit‡());
		System.out.println(prodotto.getNote());
		
		Prodotto prodotto2 = new Prodotto("carne2", 27, 1, 2018, 30, 3, 2019, "sacchetti", 4.5, true, "");
		System.out.println(prodotto2.getIdProdotto());
		System.out.println(prodotto2.getNomeProdotto());
		System.out.println(prodotto2.getStringaDataInserimento());
		System.out.println(prodotto2.getStringaDataScadenza());
		System.out.println(prodotto2.getUnit‡DiMisuraProdotto() + " " + prodotto2.getQuantit‡Prodotto());
		System.out.println("Integrit‡ = " + prodotto2.getVerificaIntegrit‡());
		System.out.println(prodotto2.getNote());
	}
}
