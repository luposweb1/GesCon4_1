import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FinestraPartizionaProdotto extends JFrame
{
	private MotoreCongelatore motoreCongelatore;
	private Prodotto prodottoSelezionato;
	private final int LARGHEZZA_FINESTRA = 300;
	private final int ALTEZZA_FINESTRA = 250;
	public FinestraPartizionaProdotto(MotoreCongelatore motoreCongelatore, Prodotto prodottoSelezionato)
	{
		this.setMotoreCongelatore(motoreCongelatore);
		this.setProdottoSelezionato(prodottoSelezionato);
		this.setTitle(getMotoreCongelatore().getTitoloFinestre() + " Partiziona");
		this.setSize(LARGHEZZA_FINESTRA, ALTEZZA_FINESTRA);
		Dimension d = getMotoreCongelatore().getToolkit().getScreenSize();
		this.setLocation((d.width-LARGHEZZA_FINESTRA)/2, (d.height-ALTEZZA_FINESTRA)/2);
		this.setResizable(false);
		this.setIconImage(this.getMotoreCongelatore().getIconaProgramma());
		
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				int  i = JOptionPane.showConfirmDialog(null,"Confermando non verrà\n"
					+ "partizonato nessun prodotto!","ATTENZIONE",JOptionPane.YES_NO_OPTION);
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
		PannelloPartizionaProdotto pannelloPartizionaProdotto = 
				new PannelloPartizionaProdotto(getMotoreCongelatore(), this, getProdottoSelezionato());
		contenitore.add(pannelloPartizionaProdotto);
	}
	
	//setter e getter dei parametri passati al costruttore
	public void setMotoreCongelatore(MotoreCongelatore motoreCongelatore)
	{
		this.motoreCongelatore = motoreCongelatore;
	}
	public MotoreCongelatore getMotoreCongelatore()
	{
		return this.motoreCongelatore;
	}
	
	public void setProdottoSelezionato(Prodotto prodottoSelezionato)
	{
		this.prodottoSelezionato = prodottoSelezionato;
	}
	public Prodotto getProdottoSelezionato()
	{
		return this.prodottoSelezionato;
	}
}
