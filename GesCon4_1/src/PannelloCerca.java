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
	private JButton buttonInizioRicerca;
	private JButton buttonNuovaRicerca;
	
	private boolean booleanSceltaRicercaPerNome = true; //impostato a true in quanto è la scelta di controllo che viene attivata subito
	private boolean booleanSceltaRicercaDataScadenza;
	private boolean booleanSceltaRicercaDataInserimento;
	private boolean booleanSceltaRicercaPerConfezioneAperta;
	
	//i component che saranno più o meno visibili in base alla scelta effettuata
	private JLabel etichettaDescrizioneRicercaPerNome;
	private JTextField campoDiTestoRicercaPerNome;
	
	private JLabel etichettaDescrizioneRicercaPerDataScadenza;
	private JComboBox<Integer> casellaCombinataGiornoScadenza;
	private JComboBox<Integer> casellaCombinataMeseScadenza;
	private JComboBox<Integer> casellaCombinataAnnoScadenza;
	
	public PannelloCerca(MotoreCongelatore motoreCongelatore, JFrame finestraSuperiore)
	{
		this.setFinestraSuperiore(finestraSuperiore);
		this.setMotoreCongelatore(motoreCongelatore);
		this.setBooleanSceltaRicercaPerNome(true); //impostata a true perchè è la casella di controllo inizialmente attiva
		this.setBooleanSceltaRicercaPerDataInserimento(false);
		this.setBooleanSceltaRicercaPerDataScadenza(false);
		this.setBooleanSceltaRicercaPerConfezioneAperta(false);
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
	
	/**
	 * 
	 * @author mitic
	 * Metodo che serve per impostare i valori boolen delle caselle di controllo
	 * 
	 */
	public void setBooleanSceltaRicercaPerNome(boolean scelta)
	{
		this.booleanSceltaRicercaPerNome = scelta;
	}
	public boolean getBooleanSceltaRicercaPerNome()
	{
		return this.booleanSceltaRicercaPerNome;
	}
	
	public void setBooleanSceltaRicercaPerDataInserimento(boolean scelta)
	{
		this.booleanSceltaRicercaDataInserimento = scelta;
	}
	public boolean getBooleanSceltaRicercaPerDataInserimento()
	{
		return this.booleanSceltaRicercaDataInserimento;
	}
	
	public void setBooleanSceltaRicercaPerDataScadenza(boolean scelta)
	{
		this.booleanSceltaRicercaDataScadenza = scelta;
	}
	public boolean getBooleanSceltaRicercaPerDataScadenza()
	{
		return this.booleanSceltaRicercaDataScadenza;
	}
	
	public void setBooleanSceltaRicercaPerConfezioneAperta(boolean scelta)
	{
		this.booleanSceltaRicercaPerConfezioneAperta = scelta;
	}
	
	public boolean getBooleanSceltaRicercaConfezioneAperta()
	{
		return this.booleanSceltaRicercaPerConfezioneAperta;
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
			buttonInizioRicerca = new JButton("Ricerca");
			this.add(buttonInizioRicerca);
			buttonNuovaRicerca = new JButton("New Ric.");
			this.add(new JLabel(""));
			this.add(new JLabel(""));
			this.add(new JLabel(""));
			this.add(buttonNuovaRicerca);
			
			//test verifica variabili boolean
			System.out.println("Nome " + getBooleanSceltaRicercaPerNome());
			System.out.println("Ins " + getBooleanSceltaRicercaPerDataInserimento());
			System.out.println("Sca " + getBooleanSceltaRicercaPerDataScadenza());
			System.out.println("Conf " + getBooleanSceltaRicercaConfezioneAperta());
			
			
			
			//azioni
			sceltaRicercaNome.addActionListener(new ActionListener()
			{				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// TODO Auto-generated method stub
					System.out.println("controlloCasellaTestPerNome");
					
					//imposto tutti i valori delle variabili boolean
					setBooleanSceltaRicercaPerNome(true);
					setBooleanSceltaRicercaPerDataInserimento(false);
					setBooleanSceltaRicercaPerDataScadenza(false);
					setBooleanSceltaRicercaPerConfezioneAperta(false);
				}
			});
			
			sceltaRicercaDataInserimento.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					System.out.println("casello controllo scelta ricerca per data inserimento");
					setBooleanSceltaRicercaPerNome(false);
					setBooleanSceltaRicercaPerDataInserimento(true);
					setBooleanSceltaRicercaPerDataScadenza(false);
					setBooleanSceltaRicercaPerConfezioneAperta(false);
				}
			});
			
			sceltaRicercaDataScadenza.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// TODO Auto-generated method stub
					System.out.println("casello controllo scelta ricerca per data scadenza");
					setBooleanSceltaRicercaPerNome(false);
					setBooleanSceltaRicercaPerDataInserimento(false);
					setBooleanSceltaRicercaPerDataScadenza(true);
					setBooleanSceltaRicercaPerConfezioneAperta(false);
					
				}
			});
			
			sceltaRicercaPerConfezioneAperta.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					System.out.println("casello controllo scelta ricerca per confezione aperta");
					setBooleanSceltaRicercaPerNome(false);
					setBooleanSceltaRicercaPerDataInserimento(false);
					setBooleanSceltaRicercaPerDataScadenza(false);
					setBooleanSceltaRicercaPerConfezioneAperta(true);
					
				}
			});
			
			
			
		}
	}
	
}
