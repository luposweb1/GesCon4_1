import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 * Questa Finestra ha il compito di far partire il pannello che gestirà, a seconda dei casi,
 * la modifica o l'aggiunta di un prodotto
 * @author mitic
 *
 */
public class FinestraAggiungiModificaProdotto extends JFrame
{
	private MotoreCongelatore motoreCongelatore;
	private Prodotto prodotto;
	private final int LARGHEZZA_FINESTRA = 300;
	private final int ALTEZZA_FINESTRA = 400;
	/**
	 * Costruttore che viene utilizzato per aggiungere un prodotto
	 * @param motoreCongelatore
	 */
	public FinestraAggiungiModificaProdotto(MotoreCongelatore motoreCongelatore)
	{
		this.setMotoreCongelatore(motoreCongelatore);
		this.setFinestra("AGG.");
	}
	
	/**
	 * Costruttore che viene utilizzare per modificare un prodotto
	 * @param motoreCongelatore
	 * @param prodotto è il Prodotto che deve essere modificato
	 */
	public FinestraAggiungiModificaProdotto(MotoreCongelatore motoreCongelatore, Prodotto prodotto)
	{
		this.setMotoreCongelatore(motoreCongelatore);
		this.setProdotto(prodotto);
		this.setFinestra("MOD.");
	}
	
	//metodi setter e getter per l'incapsulamento
	public void setMotoreCongelatore(MotoreCongelatore motoreCongelatore)
	{
		this.motoreCongelatore = motoreCongelatore;
	}
	public MotoreCongelatore getMotoreCongelatore()
	{
		return this.motoreCongelatore;
	}
	
	public void setProdotto(Prodotto prodotto)
	{
		this.prodotto = prodotto;
	}
	public Prodotto getProdotto()
	{
		return this.prodotto;
	}
	
	/**
	 * Metodo utilizzato per impostare le caratteristiche della finestra in base al fatto che sia "aggiungi prodotto" o
	 * "modifica prodotto".
	 * @param titoloFinestra è il nome della finestra da aggiungere al titolo predefinito
	 */
	public void setFinestra(String titoloFinestra)
	{
		this.setTitle(this.getMotoreCongelatore().getTitoloFinestre() + " " + titoloFinestra);
		this.setSize(LARGHEZZA_FINESTRA,ALTEZZA_FINESTRA);
		Dimension d = getMotoreCongelatore().getToolkit().getScreenSize();
		this.setLocation((d.width-LARGHEZZA_FINESTRA)/2, (d.height-ALTEZZA_FINESTRA)/2);
		this.setResizable(false);
		this.setIconImage(this.getMotoreCongelatore().getIconaProgramma());
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				int  i = JOptionPane.showConfirmDialog(null,"Confermando non verrà\n"
					+ "aggiunto\\modificato nessun prodotto!","ATTENZIONE",JOptionPane.YES_NO_OPTION);
				if(i==0)
				{
					dispose();
				}
				if(i==1)
				{
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		Container contenitore = this.getContentPane();
		PannelloAggiungiModificaProdotto panel;
		if(this.getProdotto()==null)
		{
			//PER L'INSERIMENTO
			panel = new PannelloAggiungiModificaProdotto(this,getMotoreCongelatore());
		}
		else
		{
			//PER LA MODIFICA
			panel = new PannelloAggiungiModificaProdotto(this,getMotoreCongelatore(),this.getProdotto());
		}
		contenitore.add(panel);
	}
}
