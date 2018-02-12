import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.*;

/**
 * 
 * @author mitic
 *
 */
public class PannelloAggiungiModificaProdotto extends JPanel
{
	private MotoreCongelatore motoreCongelatore;
	private JFrame finestraSuperiore;
	private Prodotto prodotto;
	
	private PannelloSopra pannelloSopra;
	private PannelloCentro pannelloCentro;
	private PannelloSotto pannelloSotto;
	
	private JButton bottoneConferma;
	private JButton bottoneEsci;
	
	private JLabel etichettaDenominazioneProdotto;
	private JTextField casellaDenominazioneProdotto;
	private JLabel etichettaDataInserimento;
	private JLabel etichettaDataScadenza;
	private JLabel etichettaQuantit‡;
	private JLabel etichettaNote;
	private JComboBox<Integer> sceltaGiornoInserimento;
	private JComboBox<Integer> sceltaMeseInserimento;
	private JComboBox<Integer> sceltaAnnoInserimento;
	private JComboBox<Integer> sceltaGiornoScadenza;
	private JComboBox<Integer> sceltaMeseScadenza;
	private JComboBox<Integer> sceltaAnnoScadenza;
	private JComboBox<String>sceltaUnit‡DiMusura;
	private JTextField casellaQuantit‡;
	private JTextArea areaNote;
	
	private JLabel etichettaMessaggioInformativo;
	
	public PannelloAggiungiModificaProdotto(JFrame finestraSuperiore, MotoreCongelatore motoreCongelatore)
	{
		this.setFinestraSuperiore(finestraSuperiore);
		this.setMotoreCongelatore(motoreCongelatore);
		getMotoreCongelatore().setMessaggioInformativo("Aggiungi Prodotto");
		this.setVisualizzazionePannello();
		etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
	}
	
	public PannelloAggiungiModificaProdotto(JFrame finestraSuperiore, MotoreCongelatore motoreCongelatore, Prodotto prodotto)
	{
		this.setFinestraSuperiore(finestraSuperiore);
		this.setMotoreCongelatore(motoreCongelatore);
		this.setProdotto(prodotto);
		getMotoreCongelatore().setMessaggioInformativo("Modifica Prodotto");
		this.setVisualizzazionePannello();
		etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
	}
	
	/**Metodo che serve per visualizzare in base al fatto che sia un "aggiungi prodotto" o un 
	 * "modifica prodotto
	 */
	public void setVisualizzazionePannello()
	{
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
		
		pannelloSotto = new PannelloSotto();
		pannelloSotto.setBackground(this.getMotoreCongelatore().getColorePannelloSotto());
		pannelloSotto.setBorder(bordoPanel);
		Dimension prefSizeSotto = pannelloSotto.getPreferredSize();
        prefSizeSotto.height = 30;
        pannelloSotto.setPreferredSize(prefSizeSotto);
		this.add(pannelloSotto,BorderLayout.SOUTH);
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
	
	public void setFinestraSuperiore(JFrame finestraSuperiore)
	{
		this.finestraSuperiore = finestraSuperiore;
	}
	public JFrame getFinestraSuperiore()
	{
		return this.finestraSuperiore;
	}
	
	public void setProdotto(Prodotto prodotto)
	{
		this.prodotto = prodotto;
	}
	public Prodotto getProdotto()
	{
		return this.prodotto;
	}
	
	//pannelli
	public class PannelloSopra extends JPanel
	{
		public PannelloSopra()
		{
			//BOTTONE E AZIONE CONFERMA
			Icon iconaBottoneConferma = new ImageIcon(getClass().getClassLoader().getResource("pulsante_ok.gif"));
			bottoneConferma = new JButton("Conferma", iconaBottoneConferma);
			bottoneConferma.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					getMotoreCongelatore().setUltimoId();
					int ggIns = (Integer)sceltaGiornoInserimento.getSelectedItem();
					int  mmIns = (Integer)sceltaMeseInserimento.getSelectedItem();
					int aaIns = (Integer)sceltaAnnoInserimento.getSelectedItem();
					
					int ggSca = (Integer)sceltaGiornoScadenza.getSelectedItem();
					int mmSca = (Integer)sceltaMeseScadenza.getSelectedItem();
					int aaSca = (Integer)sceltaAnnoScadenza.getSelectedItem();
					
					//effettuo una verifica che siano date giuste
					boolean verificaDataInserimento = getMotoreCongelatore().verificaValidit‡Data(ggIns, mmIns, aaIns);
					boolean verificaDataScadenza = getMotoreCongelatore().verificaValidit‡Data(ggSca,mmSca,aaSca);
					
					double quantit‡Numero = Double.parseDouble(casellaQuantit‡.getText());
					if(getProdotto()!=null)
					{
						//modificarne uno che c'Ë
						
						if(verificaDataInserimento&&verificaDataScadenza)
						{
							getProdotto().setNomeProdotto(casellaDenominazioneProdotto.getText());
							getProdotto().setDataInserimento(ggIns, mmIns, aaIns);
							getProdotto().setDataScadenza(ggSca, mmSca, aaSca);
							getProdotto().setUnit‡DiMisuraProdotto(sceltaUnit‡DiMusura.getSelectedItem()+"");
							getProdotto().setNote(areaNote.getText());
							
							//aggiorno le etichette
							getMotoreCongelatore().getEtichettaNomeProdotto().setText(getProdotto().getNomeProdotto());
							getMotoreCongelatore().getEtichettaDataInserimentoProdotto().setText(getProdotto().getStringaDataInserimento());
							getMotoreCongelatore().getEtichettaDataScadenzaProdotto().setText(getProdotto().getStringaDataScadenza());
							getMotoreCongelatore().getEtichettaQuantit‡Prodotto().setText(getProdotto().getQuantit‡Prodotto()+"");
							getMotoreCongelatore().getEtichettaUnit‡DiMisuraProdotto().setText(getProdotto().getUnit‡DiMisuraProdotto());
							getMotoreCongelatore().getAreaNote().setText(getProdotto().getNote());
							
							getMotoreCongelatore().salvaDatabase();
							getMotoreCongelatore().setMessaggioInformativo("Modificato un Prodotto");
							etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
							getMotoreCongelatore().getPannelloCentroPannelloPrincipale().repaint();
							getMotoreCongelatore().getPannelloDestraPannelloPrincipale().repaint();
							getFinestraSuperiore().dispose();
						}
						else if(verificaDataInserimento==true&&verificaDataScadenza==false)
						{
							getMotoreCongelatore().setMessaggioInformativo("Data Scadenza Non Valida");
							etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
						}
						else if(verificaDataInserimento==false&&verificaDataScadenza==true)
						{
							getMotoreCongelatore().setMessaggioInformativo("Data Inserimento Non Valida");
							etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
						}
					}
					else if(getProdotto()==null)
					{
						//aggiungerne uno nuovo
						if(quantit‡Numero==0)
						{
							getMotoreCongelatore().setMessaggioInformativo("Quantit‡ zero");
							etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
						}
						if(verificaDataInserimento&&verificaDataScadenza&&quantit‡Numero!=0)
						{
							Prodotto p = new Prodotto(getMotoreCongelatore().getUltimoId(),
									casellaDenominazioneProdotto.getText(),
									ggIns, mmIns, aaIns, ggSca, mmSca, aaSca, 
									sceltaUnit‡DiMusura.getSelectedItem()+"",
									quantit‡Numero, false, areaNote.getText());
							
							getMotoreCongelatore().getElencoProdotti().add(p);
							getMotoreCongelatore().salvaDatabase();
							getMotoreCongelatore().setProdottoVisualizzato(p);
							//getMotoreCongelatore().setProdotto(p); NON CAPISCO A COSA SERVA... non andrebbe gestito dal motore
							getMotoreCongelatore().setMessaggioInformativo("Inserito un nuovo prodotto: " + p.getNomeProdotto());
							etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
							getFinestraSuperiore().dispose();
						}
						else if(verificaDataInserimento==true&&verificaDataScadenza==false)
						{
							//info
							getMotoreCongelatore().setMessaggioInformativo("Data Scadenza Non Valida");
							etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
						}
						else if(verificaDataInserimento==false&&verificaDataScadenza==true)
						{
							//info
							getMotoreCongelatore().setMessaggioInformativo("Data Inserimento Non Valida");
							etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
						}
					}
					
				}
			});
			this.add(bottoneConferma);
			
			//BOTTONE E AZIONE ESCI ANNULLA
			Icon iconaEsci = new ImageIcon(getClass().getClassLoader().getResource("pulsante_esci.gif"));
			bottoneEsci = new JButton("Esci/Annulla",iconaEsci);
			bottoneEsci.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int  i = JOptionPane.showConfirmDialog(getFinestraSuperiore(),"Confermando verr‡ annullato\n"
						+ "tutto il lavoro eseguito!","ATTENZIONE",JOptionPane.YES_NO_OPTION);
					if(i==0)
					{
						getFinestraSuperiore().dispose();
					}
				}
			});
			this.add(bottoneEsci);
		}
	}
	
	public class PannelloCentro extends JPanel
	{
		public PannelloCentro()
		{
			//creazione caselle e etichette
			Box boxVerticale = Box.createVerticalBox();
			
			//prima riga
			Box boxNomeProdotto = Box.createHorizontalBox();
			etichettaDenominazioneProdotto = new JLabel("Denominazione");
			etichettaDenominazioneProdotto.setForeground(Color.yellow);
			casellaDenominazioneProdotto = new JTextField(15);
			casellaDenominazioneProdotto.setMaximumSize(casellaDenominazioneProdotto.getPreferredSize());
			boxNomeProdotto.add(etichettaDenominazioneProdotto);
			boxNomeProdotto.add(Box.createHorizontalStrut(20));
			boxNomeProdotto.add(casellaDenominazioneProdotto);
			boxNomeProdotto.add(Box.createHorizontalStrut(15));
			
			//secondariga
			GregorianCalendar oggi = new GregorianCalendar();
			Box boxDataInserimento = Box.createHorizontalBox();
			etichettaDataInserimento = new JLabel("Inser.");
			etichettaDataInserimento.setForeground(Color.yellow);
			sceltaGiornoInserimento = new JComboBox<Integer>();
			this.riempimentoJComboBoxDataGiorno(sceltaGiornoInserimento);
			sceltaGiornoInserimento.setSelectedItem(oggi.get(Calendar.DAY_OF_MONTH));
			sceltaMeseInserimento = new JComboBox<Integer>();
			this.riempimentoJComboBoxDataMese(sceltaMeseInserimento);
			sceltaMeseInserimento.setSelectedItem(oggi.get(Calendar.MONTH)+1);
			sceltaAnnoInserimento = new JComboBox<Integer>();
			int annoAttuale = oggi.get(Calendar.YEAR);
			sceltaAnnoInserimento.addItem(annoAttuale);
			sceltaAnnoInserimento.addItem(annoAttuale-1);
			
			boxDataInserimento.add(etichettaDataInserimento);
			boxDataInserimento.add(Box.createHorizontalStrut(10));
			boxDataInserimento.add(sceltaGiornoInserimento);
			boxDataInserimento.add(Box.createHorizontalStrut(3));
			boxDataInserimento.add(sceltaMeseInserimento);
			boxDataInserimento.add(Box.createHorizontalStrut(3));
			boxDataInserimento.add(sceltaAnnoInserimento);
			boxDataInserimento.add(Box.createHorizontalStrut(35));
			
			//terza riga
			Box boxDataScadenza = Box.createHorizontalBox();
			etichettaDataScadenza = new JLabel("Scadenza");
			etichettaDataScadenza.setForeground(Color.YELLOW);			
			sceltaGiornoScadenza = new JComboBox<Integer>();
			this.riempimentoJComboBoxDataGiorno(sceltaGiornoScadenza);
			sceltaGiornoScadenza.setSelectedItem(oggi.get(Calendar.DAY_OF_MONTH));
			sceltaMeseScadenza = new JComboBox<Integer>();
			this.riempimentoJComboBoxDataMese(sceltaMeseScadenza);
			sceltaMeseScadenza.setSelectedItem(oggi.get(Calendar.MONTH)+1);
			sceltaAnnoScadenza = new JComboBox<Integer>();
			for(int i=annoAttuale; i<=(annoAttuale+10); i++)
			{
				sceltaAnnoScadenza.addItem(i);
			}
			sceltaAnnoScadenza.setSelectedItem(oggi.get(Calendar.YEAR));
			boxDataScadenza.add(etichettaDataScadenza);
			boxDataScadenza.add(Box.createHorizontalStrut(10));
			boxDataScadenza.add(sceltaGiornoScadenza);
			boxDataScadenza.add(Box.createHorizontalStrut(3));
			boxDataScadenza.add(sceltaMeseScadenza);
			boxDataScadenza.add(Box.createHorizontalStrut(3));
			boxDataScadenza.add(sceltaAnnoScadenza);
			boxDataScadenza.add(Box.createHorizontalStrut(35));
			
			//quarta riga
			Box boxQuantit‡ = Box.createHorizontalBox();
			etichettaQuantit‡ = new JLabel("Quantit‡");
			etichettaQuantit‡.setForeground(Color.YELLOW);
			sceltaUnit‡DiMusura = new JComboBox<String>();
			for(int i=0; i<getMotoreCongelatore().getElencoUnit‡DiMisura().size(); i++)
			{
				sceltaUnit‡DiMusura.addItem(getMotoreCongelatore().getElencoUnit‡DiMisura().get(i));
			}
			casellaQuantit‡ = new JTextField(6);
			casellaQuantit‡.setMaximumSize(casellaQuantit‡.getPreferredSize());
			casellaQuantit‡.setText("0");
			boxQuantit‡.add(etichettaQuantit‡);
			boxQuantit‡.add(Box.createHorizontalStrut(10));
			boxQuantit‡.add(sceltaUnit‡DiMusura);
			boxQuantit‡.add(Box.createHorizontalStrut(3));
			boxQuantit‡.add(casellaQuantit‡);
			
			//quinta riga
			Box boxNote = Box.createHorizontalBox();
			etichettaNote = new JLabel("Note");
			etichettaNote.setForeground(Color.YELLOW);
			areaNote = new JTextArea(5,5);
			JScrollPane pannelloScorrevole = new JScrollPane(areaNote);
			boxNote.add(etichettaNote);
			boxNote.add(Box.createHorizontalStrut(10));
			boxNote.add(pannelloScorrevole);
			
			boxVerticale.add(Box.createVerticalStrut(5));
			boxVerticale.add(boxNomeProdotto);
			boxVerticale.add(Box.createVerticalStrut(20));
			boxVerticale.add(boxDataInserimento);
			boxVerticale.add(Box.createVerticalStrut(20));
			boxVerticale.add(boxDataScadenza);
			boxVerticale.add(Box.createVerticalStrut(20));
			boxVerticale.add(boxQuantit‡);
			boxVerticale.add(Box.createVerticalStrut(20));
			boxVerticale.add(boxNote);
			
			this.add(boxVerticale);
			
			if(getProdotto()!=null)
			{
				casellaDenominazioneProdotto.setText(getProdotto().getNomeProdotto());
				sceltaGiornoInserimento.setSelectedItem(getProdotto().getGiornoInserimento());
				sceltaMeseInserimento.setSelectedItem(getProdotto().getMeseInserimento());
				sceltaAnnoInserimento.setSelectedItem(getProdotto().getAnnoInserimento());
				sceltaGiornoScadenza.setSelectedItem(getProdotto().getGiornoScadenza());
				sceltaMeseScadenza.setSelectedItem(getProdotto().getMeseScadenza());
				sceltaAnnoScadenza.setSelectedItem(getProdotto().getAnnoScadenza());
				sceltaUnit‡DiMusura.setSelectedItem(getProdotto().getUnit‡DiMisuraProdotto());
				casellaQuantit‡.setText(getProdotto().getQuantit‡Prodotto()+"");
				casellaQuantit‡.setEditable(false);//poi verificare
				areaNote.setText(getProdotto().getNote());
			}
		}
		
		public void riempimentoJComboBoxDataGiorno(JComboBox<Integer> sceltaCombinata)
		{
			for(int i=1; i<=31; i++)
			{
				sceltaCombinata.addItem(i);
			}
		}
		public void riempimentoJComboBoxDataMese(JComboBox<Integer> sceltaCombinata)
		{
			for(int i=1; i<=12; i++)
			{
				sceltaCombinata.addItem(i);
			}
		}
	}
	
	public class PannelloSotto extends JPanel
	{
		public PannelloSotto()
		{
			etichettaMessaggioInformativo = new JLabel(getMotoreCongelatore().getMessaggioInformativo());
			if(etichettaMessaggioInformativo==null||etichettaMessaggioInformativo.getText()==null)
			{
				etichettaMessaggioInformativo.setText("Messaggio Informativo");
			}
			else
			{
				etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
			}
			this.add(etichettaMessaggioInformativo);
		}
	}
}
