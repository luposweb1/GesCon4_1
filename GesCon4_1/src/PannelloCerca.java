import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class PannelloCerca extends JPanel
{
	private MotoreCongelatore motoreCongelatore;
	private JFrame finestraSuperiore;
	
	private PannelloSopra pannelloSopra;
	private PannelloCentro pannelloCentro;
	private PannelloSotto pannelloSotto;
	private PannelloSinistra pannelloSinistra;
	
	private JLabel etichettaMessaggioInformativo;
	
	private JLabel etichettaDenominazioneRicerca;
	private JTextField casellaRicercaPerNome;
	
	private ButtonGroup gruppoSceltaRicerca;
	private JRadioButton sceltaRicercaNome;
	private JRadioButton sceltaRicercaDataScadenza;
	private JRadioButton sceltaRicercaDataInserimento;
	private JRadioButton sceltaRicercaPerConfezioneAperta;
	
	public PannelloCerca(MotoreCongelatore motoreCongelatore, JFrame finestraSuperiore)
	{
		this.setFinestraSuperiore(finestraSuperiore);
		this.setMotoreCongelatore(motoreCongelatore);
		this.setLayout(new BorderLayout());
		Border bordoPanel = BorderFactory.createRaisedSoftBevelBorder();
		
		pannelloSopra = new PannelloSopra();
		pannelloSopra.setBackground(this.getMotoreCongelatore().getColorePannelloSopra());
		pannelloSopra.setBorder(bordoPanel);
		Dimension prefSizeSopra = pannelloSopra.getPreferredSize();
		prefSizeSopra.height = 40;
		pannelloSopra.setPreferredSize(prefSizeSopra);
		this.add(pannelloSopra,BorderLayout.NORTH);
		
		pannelloCentro = new PannelloCentro();
		pannelloCentro.setBackground(this.getMotoreCongelatore().getColorePannelloCentro());
		pannelloCentro.setBorder(bordoPanel);
		this.add(pannelloCentro,BorderLayout.CENTER);
		
		getMotoreCongelatore().setMessaggioInformativo("Ricerca nel congelatore");
		pannelloSotto = new PannelloSotto();
		pannelloSotto.setBackground(this.getMotoreCongelatore().getColorePannelloSotto());
		pannelloSotto.setBorder(bordoPanel);
		Dimension prefSizeSotto = pannelloSotto.getPreferredSize();
        prefSizeSotto.height = 30;
       	pannelloSotto.setPreferredSize(prefSizeSotto);
		this.add(pannelloSotto,BorderLayout.SOUTH);
		
		pannelloSinistra = new PannelloSinistra();
		pannelloSinistra.setBackground(this.getMotoreCongelatore().getColorePannelloSinistra());
		Border titoloBordoPannelloSinistra = BorderFactory.createTitledBorder(bordoPanel,"Cerca Per:");
		pannelloSinistra.setBorder(titoloBordoPannelloSinistra);
		this.add(pannelloSinistra, BorderLayout.WEST);
		
		
	}
	
	//metodi getter e setter delle parametri passati al Pannello
	public void setMotoreCongelatore(MotoreCongelatore motoreCongelatore)
	{
		this.motoreCongelatore = motoreCongelatore;
	}
	public MotoreCongelatore getMotoreCongelatore()
	{
		return this.motoreCongelatore;
	}
	public void setFinestraSuperiore(JFrame finestraSuperiore)
	{
		this.finestraSuperiore = finestraSuperiore;
	}
	public JFrame getFinestraSuperiore()
	{
		return this.finestraSuperiore;
	}
	
	//Pannelli
	public class PannelloSopra extends JPanel
	{
		public PannelloSopra()
		{
			
		}
	}
	
	public class PannelloCentro extends JPanel
	{
		public PannelloCentro()
		{
			
		}
	}
	
	public class PannelloSotto extends JPanel
	{
		public PannelloSotto()
		{
			etichettaMessaggioInformativo = new JLabel(getMotoreCongelatore().getMessaggioInformativo());
			this.add(etichettaMessaggioInformativo);
		}
	}
	
	public class PannelloSinistra extends JPanel
	{
		public PannelloSinistra()
		{
			this.setLayout(new GridLayout(12,1));
			gruppoSceltaRicerca = new ButtonGroup();
			sceltaRicercaNome = new JRadioButton("per Nome",true);
			//sceltaRicercaNome.setForeground(getMotoreCongelatore().getColorePannelloSinistra());
			sceltaRicercaNome.setBackground(getMotoreCongelatore().getColorePannelloSinistra());
			sceltaRicercaDataInserimento = new JRadioButton("per Data Ins",false);
			sceltaRicercaDataInserimento.setBackground(getMotoreCongelatore().getColorePannelloSinistra());
			sceltaRicercaDataScadenza = new JRadioButton("per Data Sca",false);
			sceltaRicercaDataScadenza.setBackground(getMotoreCongelatore().getColorePannelloSinistra());
			sceltaRicercaPerConfezioneAperta = new JRadioButton("partiz.", false);
			sceltaRicercaPerConfezioneAperta.setBackground(getMotoreCongelatore().getColorePannelloSinistra());
			gruppoSceltaRicerca.add(sceltaRicercaNome);
			gruppoSceltaRicerca.add(sceltaRicercaDataInserimento);
			gruppoSceltaRicerca.add(sceltaRicercaDataScadenza);
			gruppoSceltaRicerca.add(sceltaRicercaPerConfezioneAperta);
			this.add(sceltaRicercaNome);
			this.add(sceltaRicercaDataInserimento);
			this.add(sceltaRicercaDataScadenza);
			this.add(sceltaRicercaPerConfezioneAperta);
		}
	}
}
