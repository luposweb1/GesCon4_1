import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.border.*;

public class PannelloPartizionaProdotto extends JPanel
{
	private MotoreCongelatore motoreCongelatore;
	private JFrame finestraSuperiore;
	private Prodotto prodottoSelezionato;
	private PannelloSopra pannelloSopra;
	private PannelloCentro pannelloCentro;
	private PannelloSotto pannelloSotto;
	
	private JButton bottoneConferma;
	private JButton bottoneEsci;
	
	private JLabel etichettaDenominazioneProdotto;
	private JTextField casellaInserimentoDati;
	private JLabel etichettaDescrizioneQuantit‡1;
	private JLabel etichettaDescrizioneQuantit‡2;
	
	private JLabel etichettaMessaggioInformativo;
	
	
	public PannelloPartizionaProdotto(MotoreCongelatore motoreCongelatore, JFrame finestraSuperiore, Prodotto prodottoSelezionato)
	{
		this.setMotoreCongelatore(motoreCongelatore);
		this.setFinestraSuperiore(finestraSuperiore);
		this.setProdottoSelezionato(prodottoSelezionato);
		
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
	
	//metodi setter e getter dei parametri passati al costruttore
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
	
	public void setProdottoSelezionato(Prodotto prodottoSelezionato)
	{
		this.prodottoSelezionato = prodottoSelezionato;
	}
	public Prodotto getProdottoSelezionato()
	{
		return this.prodottoSelezionato;
	}
	
	//pannello sopra
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
				public void actionPerformed(ActionEvent event)
				{
					// TODO Auto-generated method stub
					//imposto esito partizionamento a false
					getMotoreCongelatore().setEsitoPartizionamento(false);
					
					try
					{
						double input = Double.parseDouble(casellaInserimentoDati.getText());
						getMotoreCongelatore().partizionaProdotto(input);
						etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
						getMotoreCongelatore().getEtichettaMessaggioInformativoPannelloPrincipale().setText(getMotoreCongelatore().getMessaggioInformativo());
					}
					catch(Exception e)
					{
						getMotoreCongelatore().setMessaggioInformativo("Numero Inserito Non Valido");
						etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
						getMotoreCongelatore().getEtichettaMessaggioInformativoPannelloPrincipale().setText(getMotoreCongelatore().getMessaggioInformativo());
					}
					
					getMotoreCongelatore().getPannelloCentroPannelloPrincipale().repaint();
					getMotoreCongelatore().getPannelloDestraPannelloPrincipale().repaint();
					//getMotoreCongelatore().getPannelloSottoPannelloPrincipale().repaint(); Non serve per l'etichetta nel pannello principale. In questo caso usare direttamente l'etichetta
										
					if(getMotoreCongelatore().getEsitoPartizionamento())
					{
						getFinestraSuperiore().dispose();
					}
					
				}
			});
			this.add(bottoneConferma);
			
			//BOTTONE E AZIONE ESCI ANNULLA
			Icon iconaEsci = new ImageIcon(getClass().getClassLoader().getResource("pulsante_esci.gif"));
			bottoneEsci = new JButton("Esci/Annulla",iconaEsci);
			bottoneEsci.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// TODO Auto-generated method stub
					int  i = JOptionPane.showConfirmDialog(getFinestraSuperiore(),"Sicuro di voler chiudere\n"
							+ "questa finestra?","ATTENZIONE",JOptionPane.YES_NO_OPTION);
					if(i==0)
					{
						getFinestraSuperiore().dispose();
					}
					
				}
			});
			this.add(bottoneEsci);
			
		}
	}
	
	//pannello centro
	public class PannelloCentro extends JPanel
	{
		public PannelloCentro()
		{
			Box boxVerticale = Box.createVerticalBox();
			
			//prima riga
			Box boxNomeProdotto = Box.createHorizontalBox();
			etichettaDenominazioneProdotto = new JLabel(getProdottoSelezionato().getNomeProdotto());
			etichettaDenominazioneProdotto.setFont(new Font(getMotoreCongelatore().getCarattereVisualVoci(),Font.PLAIN,18));
			etichettaDenominazioneProdotto.setForeground(Color.yellow);
			boxNomeProdotto.add(etichettaDenominazioneProdotto);
			
			Box boxDescrizioneQuantit‡1 = Box.createHorizontalBox();
			etichettaDescrizioneQuantit‡1 = new JLabel("Quanto desideri togliere da");
			etichettaDescrizioneQuantit‡1.setForeground(Color.MAGENTA);
			boxDescrizioneQuantit‡1.add(etichettaDescrizioneQuantit‡1);
			
			Box boxDescrizioneQuantit‡2 = Box.createHorizontalBox();
			etichettaDescrizioneQuantit‡2 = new JLabel(getProdottoSelezionato().getUnit‡DiMisuraProdotto() + " " + getProdottoSelezionato().getQuantit‡Prodotto() + "?");
			etichettaDescrizioneQuantit‡2.setForeground(Color.MAGENTA);
			boxDescrizioneQuantit‡2.add(etichettaDescrizioneQuantit‡2);
			
			Box boxInserimentoDati = Box.createHorizontalBox();
			casellaInserimentoDati = new JTextField(15);
			casellaInserimentoDati.setMaximumSize(casellaInserimentoDati.getPreferredSize());
			boxInserimentoDati.add(casellaInserimentoDati);
			
			boxVerticale.add(Box.createVerticalStrut(5));
			boxVerticale.add(boxNomeProdotto);
			boxVerticale.add(Box.createVerticalStrut(20));
			boxVerticale.add(boxDescrizioneQuantit‡1);
			boxVerticale.add(Box.createVerticalStrut(15));
			boxVerticale.add(boxDescrizioneQuantit‡2);
			boxVerticale.add(Box.createVerticalStrut(15));
			boxVerticale.add(boxInserimentoDati);
			
			this.add(boxVerticale);
		}
	}
	
	//pannello sotto
	public class PannelloSotto extends JPanel
	{
		public PannelloSotto()
		{
			etichettaMessaggioInformativo = new JLabel();
			if(etichettaMessaggioInformativo==null||etichettaMessaggioInformativo.getText()==null)
			{
				getMotoreCongelatore().setMessaggioInformativo("Messaggio Informativo");
				etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
			}
			else
			{
				etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
			}
			this.add(etichettaMessaggioInformativo);
		}
	}
}
