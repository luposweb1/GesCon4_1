/**
 * @author mitic
 * 
 * 
 */

import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class PannelloPrincipale extends JPanel
{
	//logica
	private JFrame finestraSuperiore;
	private MotoreCongelatore motoreCongelatore;
	private int iteratoreVoceProdotto;
		
	//pulsanti
	private JButton bottoneAggiungi;
	private JButton bottonePartiziona;
	private JButton bottoneModifica;
	private JButton bottoneElimina;
	private JButton bottoneVisual;
	private JButton bottoneCerca;
	private JButton bottoneSalva;
	private JButton bottoneEsci;
	private JButton bottoneStampa;
	
	private JButton bottoneAvanti;
	private JButton bottoneAvantiPlus;
	private JButton bottoneIndietro;
	private JButton bottoneIndietroPlus;
	
	private JButton bottoneRicettario;
	
	private JCheckBox checkSceltaProdotto;
	
	//immagini
	private Image copertina;
	private Image semaforo;
	private Image lente;
	private Ellipse2D semaforoRosso;//si accende per problemi di scadenza
	private Ellipse2D semaforoArancione;//si accende se la VoceProdotto non Ë ben definita
	private Ellipse2D semaforoVerde;//si accende quando la scadenza Ë ok
	private MediaTracker tracker;
	
	//Pannelli
	private PannelloSopra pannelloSopra;
	private PannelloCentro pannelloCentro;
	private PannelloSotto pannelloSotto;
	private PannelloSinistra pannelloSinistra;
	private PannelloDestra pannelloDestra;
	
	//altro
	private JLabel etichettaMessaggioInformativo;
	private Graphics2D graficaPannelloDestro;
	
	//grafica visual
	private JTextArea areaNote;
	private JLabel etichettaNomeProdotto;
	private JLabel etichettaIdProdotto;
	private JLabel etichettaDescrizioneDataScadenza;
	private JLabel etichettaDescrizioneDataInserimento;
	private JLabel etichettaDataInserimentoProdotto;
	private JLabel etichettaDataScadenzaProdotto;
	private JLabel etichettaDescrizioneQuantit‡;
	private JLabel etichettaUnit‡DiMisuraProdotto;
	private JLabel etichettaQuantit‡Prodotto;
	private JScrollPane pannelloScorrevole;
	
	
	public PannelloPrincipale(MotoreCongelatore motoreCongelatore, JFrame finestraSuperiore)
	{
		this.setMotoreCongelatore(motoreCongelatore);
		this.setFinestraSuperiore(finestraSuperiore);
		
		semaforo = getMotoreCongelatore().getToolkit().getImage(getClass().getClassLoader().getResource("semaforo.gif"));
		copertina = getMotoreCongelatore().getToolkit().getImage(getClass().getClassLoader().getResource("copertina_1.gif"));
		lente = getMotoreCongelatore().getToolkit().getImage(getClass().getClassLoader().getResource("lente.gif"));
		tracker = new MediaTracker(this);
		tracker.addImage(semaforo,0);
		tracker.addImage(copertina,1);
		tracker.addImage(lente,2);
		try
		{
			tracker.waitForAll();
		}
		catch(InterruptedException exc)
		{
			this.getMotoreCongelatore().setMessaggioInformativo("problema con il caricamento delle immagini Err001");
			System.out.println(this.getMotoreCongelatore().getMessaggioInformativo());
			etichettaMessaggioInformativo.setText(this.getMotoreCongelatore().getMessaggioInformativo());
		}
		
		
		//impostazione dei pannelli e loro divisione all'interno di quello principale
		this.setLayout(new BorderLayout());
		Border bordoPanel = BorderFactory.createRaisedSoftBevelBorder();
		
		pannelloSopra = new PannelloSopra();
		pannelloSopra.setBackground(this.getMotoreCongelatore().getColorePannelloSopra());
		pannelloSopra.setBorder(bordoPanel);
		Dimension prefSizeSopra = pannelloSopra.getPreferredSize();
		prefSizeSopra.height = 40;
		pannelloSopra.setPreferredSize(prefSizeSopra);
		this.add(pannelloSopra, BorderLayout.NORTH);
		
		pannelloCentro = new PannelloCentro();
		pannelloCentro.setBackground(this.getMotoreCongelatore().getColorePannelloCentro());
		pannelloCentro.setBorder(bordoPanel);
		this.add(pannelloCentro, BorderLayout.CENTER);
		
		pannelloSotto = new PannelloSotto();
		pannelloSotto.setBackground(this.getMotoreCongelatore().getColorePannelloSotto());
		pannelloSotto.setBorder(bordoPanel);
		this.add(pannelloSotto, BorderLayout.SOUTH);
		
		pannelloSinistra = new PannelloSinistra();
		pannelloSinistra.setBackground(this.getMotoreCongelatore().getColorePannelloSinistra());
		Border titoloBordoPannelloSinistra = BorderFactory.createTitledBorder(bordoPanel,"Iter");
		pannelloSinistra.setBorder(titoloBordoPannelloSinistra);
		this.add(pannelloSinistra, BorderLayout.WEST);
		
		pannelloDestra = new PannelloDestra();
		pannelloDestra.setBackground(this.getMotoreCongelatore().getColorePannelloDestra());
		Dimension prefSizeDestra = pannelloDestra.getPreferredSize();
		prefSizeDestra.width = 200;
		pannelloDestra.setPreferredSize(prefSizeDestra);
		Border titoloBordoPannelloDestra = BorderFactory.createTitledBorder(bordoPanel,"Control");
		pannelloDestra.setBorder(titoloBordoPannelloDestra);
		this.add(pannelloDestra,BorderLayout.EAST);
	}
	
	//getter e setter di motore e finestra superiore
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
	
	
	//PannelloSopra
	public class PannelloSopra extends JPanel
	{
		public PannelloSopra()
		{
			//BOTTONE AGGIUNGI
			Icon iconaAggiungi = new ImageIcon(getClass().getClassLoader().getResource("pulsante_aggiungi.gif"));
			AzioneBottoneAggiungi azioneBottoneAggiungi = new AzioneBottoneAggiungi("Aggiungi",iconaAggiungi);
			bottoneAggiungi = new JButton(azioneBottoneAggiungi);
			this.add(bottoneAggiungi);
			//FINE BOTTONE AGGIUNGI
			
			//BOTTONE PARTIZIONA
			Icon iconaPartiziona = new ImageIcon(getClass().getClassLoader().getResource("pulsante_partiziona.gif"));
			AzioneBottonePartiziona azioneBottonePartiziona = new AzioneBottonePartiziona("Partiziona", iconaPartiziona);
			bottonePartiziona = new JButton(azioneBottonePartiziona);
			this.add(bottonePartiziona);
			//FINE BOTTONE PARTIZIONA
			
			//BOTTONE MODIFICA
			Icon iconaModifica = new ImageIcon(getClass().getClassLoader().getResource("pulsante_modifica.gif"));
			AzioneBottoneModifica azioneBottoneModifica = new AzioneBottoneModifica("Modifica", iconaModifica);
			bottoneModifica = new JButton(azioneBottoneModifica);
			this.add(bottoneModifica);
			//FINE BOTTONE MODIFICA
			
			//BOTTONE ELIMINA
			Icon iconaElimina = new ImageIcon(getClass().getClassLoader().getResource("pulsante_elimina.gif"));
			AzioneBottoneElimina azioneBottoneElimina = new AzioneBottoneElimina("Elimina", iconaElimina);
			bottoneElimina = new JButton(azioneBottoneElimina);
			this.add(bottoneElimina);
			//FINE BOTTONE ELIMINA
			
			//BOTTONE CERCA
			Icon iconaCerca = new ImageIcon(getClass().getClassLoader().getResource("pulsante_cerca.gif"));
			AzioneBottoneCerca azioneBottoneCerca = new AzioneBottoneCerca("Cerca", iconaCerca);
			bottoneCerca = new JButton(azioneBottoneCerca);
			this.add(bottoneCerca);
			//FINE BOTTONE CERCA
			
			//BOTTONE VISUAL
			Icon iconaVisual = new ImageIcon(getClass().getClassLoader().getResource("pulsante_visualizza.gif"));
			AzioneBottoneVisual azioneBottoneVisual = new AzioneBottoneVisual("Visual", iconaVisual);
			bottoneVisual = new JButton(azioneBottoneVisual);
			this.add(bottoneVisual);
			//FINE BOTTONE VISUAL
			
			//BOTTONE STAMPA
			Icon iconaStampa = new ImageIcon(getClass().getClassLoader().getResource("pulsante_stampa.gif"));
			AzioneBottoneStampa azioneBottoneStampa = new AzioneBottoneStampa("Stampa", iconaStampa);
			bottoneStampa = new JButton(azioneBottoneStampa);
			this.add(bottoneStampa);
			//FINE BOTTONE STAMPA
			
			//BOTTONE SALVA
			Icon iconaSalva = new ImageIcon(getClass().getClassLoader().getResource("pulsante_salva.gif"));
			AzioneBottoneSalva azioneBottoneSalva = new AzioneBottoneSalva("Salva", iconaSalva);
			bottoneSalva = new JButton(azioneBottoneSalva);
			this.add(bottoneSalva);
			//FINE BOTTONE SALVA
			
			//BOTTONE ESCI
			Icon iconaEsci = new ImageIcon(getClass().getClassLoader().getResource("pulsante_esci.gif"));
			AzioneBottoneEsci azioneBottoneEsci = new AzioneBottoneEsci("Esci", iconaEsci);
			bottoneEsci = new JButton(azioneBottoneEsci);
			this.add(bottoneEsci);
			//FINE BOTTONE ESCI
			
			//funzioni per i tasti di scelta rapida e descrizione
			InputMap imap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			imap.put(KeyStroke.getKeyStroke("ctrl A"),"Aggiungi Voce");
			imap.put(KeyStroke.getKeyStroke("ctrl P"),"Togli una parte dal prodotto");
			imap.put(KeyStroke.getKeyStroke("ctrl M"),"Modifica Prodotto");
			imap.put(KeyStroke.getKeyStroke("ctrl C"),"Elimina Prodotto");
			imap.put(KeyStroke.getKeyStroke("ctrl Q"),"Cerca Prodotto");
			imap.put(KeyStroke.getKeyStroke("ctrl Z"),"Visualizza Lista Prodotti");
			imap.put(KeyStroke.getKeyStroke("ctrl P"),"Stampa");
			imap.put(KeyStroke.getKeyStroke("ctrl S"),"Salva");
			imap.put(KeyStroke.getKeyStroke("ctrl E"),"Esci");
			
			ActionMap amap = getActionMap();
			amap.put("Aggiungi Voce",azioneBottoneAggiungi);
			amap.put("Togli una parte dal prodotto",azioneBottonePartiziona);
			amap.put("Modifica Prodotto",azioneBottoneModifica);
			amap.put("Elimina Prodotto",azioneBottoneElimina);
			amap.put("Cerca Prodotto",azioneBottoneCerca);
			amap.put("Visualizza Lista Prodotti",azioneBottoneVisual);
			amap.put("Stampa",azioneBottoneStampa);
			amap.put("Salva",azioneBottoneSalva);
			amap.put("Esci",azioneBottoneEsci);
		}
		
		//azione bottone aggiungi
		public class AzioneBottoneAggiungi extends AbstractAction
		{
			public AzioneBottoneAggiungi(String nome, Icon icona)
			{
				putValue(Action.NAME, nome);
				putValue(Action.SMALL_ICON,icona);
				putValue(Action.SHORT_DESCRIPTION,"Aggiungi Voce");
			}
			public void actionPerformed(ActionEvent e)
			{
				FinestraAggiungiModificaProdotto finestraAggiungiModificaProdotto = new FinestraAggiungiModificaProdotto(getMotoreCongelatore());
				finestraAggiungiModificaProdotto.setVisible(true);
				getMotoreCongelatore().setMessaggioInformativo("Aggiungi Prodotto nel congelatore");
				etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
				checkSceltaProdotto.setSelected(false);
			}
		}
		
		//azione bottone partiziona
		public class AzioneBottonePartiziona extends AbstractAction
		{
			public AzioneBottonePartiziona(String nome, Icon icona)
			{
				putValue(Action.NAME, nome);
				putValue(Action.SMALL_ICON,icona);
				putValue(Action.SHORT_DESCRIPTION,"Togli una parte dal prodotto");
			}
			public void actionPerformed(ActionEvent e)
			{
				if(checkSceltaProdotto.isSelected()&&getMotoreCongelatore().getProdottoSelezionato()!=null)
				{
					FinestraPartizionaProdotto finestraPartizionaProdotto = 
							new FinestraPartizionaProdotto(getMotoreCongelatore(), getMotoreCongelatore().getProdottoSelezionato());
					finestraPartizionaProdotto.setVisible(true);
					getMotoreCongelatore().setMessaggioInformativo("Togli Una Parte Di Prodotto");
					etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
				}
				else if((!checkSceltaProdotto.isSelected())||getMotoreCongelatore().getProdottoSelezionato()!=null)
				{
					getMotoreCongelatore().setMessaggioInformativo("Nessun Prodotto Selezionato");
					etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
				}
				checkSceltaProdotto.setSelected(false);
			}
		}
		
		//azione bottone modifica
		public class AzioneBottoneModifica extends AbstractAction
		{
			public AzioneBottoneModifica(String nome, Icon icona)
			{
				putValue(Action.NAME, nome);
				putValue(Action.SMALL_ICON,icona);
				putValue(Action.SHORT_DESCRIPTION,"Modifica Prodotto");
			}
			public void actionPerformed(ActionEvent e)
			{
				if(checkSceltaProdotto.isSelected()&&getMotoreCongelatore().getProdottoSelezionato()!=null)
				{
					FinestraAggiungiModificaProdotto finestraAggiungiModificaProdotto = 
							new FinestraAggiungiModificaProdotto(getMotoreCongelatore(),getMotoreCongelatore().getProdottoSelezionato());
					finestraAggiungiModificaProdotto.setVisible(true);
					getMotoreCongelatore().setMessaggioInformativo("Modifica un prodotto gi‡ inserito");
					etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
				}
				else if((!checkSceltaProdotto.isSelected())||getMotoreCongelatore().getProdottoSelezionato()!=null)
				{
					getMotoreCongelatore().setMessaggioInformativo("Nessuna Voce Selezionata");
					etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
				}
				/*else if(!checkSceltaProdotto.isSelected())
				{
					getMotoreCongelatore().setMessaggioInformativo("Nessuna Voce Selezionata");
					etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
				}*/
				checkSceltaProdotto.setSelected(false);
			}
		}
		
		//azione bottone elimina
		public class AzioneBottoneElimina extends AbstractAction
		{
			public AzioneBottoneElimina(String nome, Icon icona)
			{
				putValue(Action.NAME, nome);
				putValue(Action.SMALL_ICON,icona);
				putValue(Action.SHORT_DESCRIPTION,"Elimina Prodotto");
			}
			public void actionPerformed(ActionEvent e)
			{
				if(checkSceltaProdotto.isSelected()&&getMotoreCongelatore().getProdottoSelezionato()!=null)
				{
					getMotoreCongelatore().setMessaggioInformativo("Elimina Prodotto dal Congelatore");
					etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
					
					int  i = JOptionPane.showConfirmDialog(getFinestraSuperiore(),"Vuoi davvero eliminare\n" + 
							"il prodotto n." + getMotoreCongelatore().getProdottoSelezionato().getIdProdotto() +
							"\n" + getMotoreCongelatore().getProdottoSelezionato().getNomeProdotto() 
							+ " ?","ATTENZIONE",JOptionPane.YES_NO_OPTION);
					if(i==0)
					{
						getMotoreCongelatore().eliminaProdotto(getMotoreCongelatore().getProdottoSelezionato().getIdProdotto());
						
						try
						{
							getMotoreCongelatore().setProdottoVisualizzato(getMotoreCongelatore().getElencoProdotti().get(0));
							getMotoreCongelatore().setIteratore(0);
							etichettaIdProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getIdProdotto()+"");
							etichettaNomeProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getNomeProdotto());
							etichettaDataInserimentoProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getStringaDataInserimento());
							etichettaDataScadenzaProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getStringaDataScadenza());
							etichettaUnit‡DiMisuraProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getUnit‡DiMisuraProdotto());
							etichettaQuantit‡Prodotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getQuantit‡Prodotto()+"");
							areaNote.setText(getMotoreCongelatore().getProdottoVisualizzato().getNote());
						}
						catch(IndexOutOfBoundsException eccezione)
						{
							System.out.println("Errore 7 nel cercare nel database dei prodotti");
							getMotoreCongelatore().setAvvioVisual(false);
							getMotoreCongelatore().setAvvioRicerca(false);
							getMotoreCongelatore().setMessaggioInformativo("E' probabile che non ci sia pi˘ niente nel congelatore");
							etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
						}
						catch(Exception eccezione)
						{
							System.out.println(eccezione.toString());
						}
						pannelloDestra.repaint();
						pannelloCentro.repaint();
						
					}
				}
				else if((!checkSceltaProdotto.isSelected()))
				{
					getMotoreCongelatore().setMessaggioInformativo("Nessuna Voce Selezionata");
					etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
				}
				checkSceltaProdotto.setSelected(false);
			}
		}
		
		//azione bottone cerca
		public class AzioneBottoneCerca extends AbstractAction
		{
			public AzioneBottoneCerca(String nome, Icon icona)
			{
				putValue(Action.NAME, nome);
				putValue(Action.SMALL_ICON,icona);
				putValue(Action.SHORT_DESCRIPTION,"Cerca Prodotto");
			}
			public void actionPerformed(ActionEvent e)
			{
				getMotoreCongelatore().setAvvioVisual(false);
				getMotoreCongelatore().setAvvioRicerca(true);
				if(getMotoreCongelatore().getElencoProdotti().size()<=0 || getMotoreCongelatore().getElencoProdotti()==null)
				{
					getMotoreCongelatore().setMessaggioInformativo("Non ci sono prodotti! Impossibile fare una ricerca");
					etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
					getMotoreCongelatore().setAvvioRicerca(false);
				}
				else if(getMotoreCongelatore().getElencoProdotti().size()>=1)
				{
					FinestraCerca finestraCerca = new FinestraCerca(getMotoreCongelatore());
					finestraCerca.setVisible(true);
					getMotoreCongelatore().getPannelloDestraPannelloPrincipale().repaint();
				}
			}
		}
		
		//azione bottone visual
		public class AzioneBottoneVisual extends AbstractAction
		{
			public AzioneBottoneVisual(String nome, Icon icona)
			{
				putValue(Action.NAME, nome);
				putValue(Action.SMALL_ICON, icona);
				putValue(Action.SHORT_DESCRIPTION, "Visualizza Lista Prodotti");
			}
			public void actionPerformed(ActionEvent e)
			{
				//getMotoreCongelatore().setAvvioVisual(true);
				getMotoreCongelatore().setAvvioRicerca(false);
				
				getMotoreCongelatore().setMessaggioInformativo("Scorri la lista dei prodotti");
				etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
				
				try
				{
					getMotoreCongelatore().setProdottoVisualizzato(getMotoreCongelatore().getElencoProdotti().get(getMotoreCongelatore().getIteratore()));
					getMotoreCongelatore().setAvvioVisual(true);
					pannelloCentro.visualizzaProdottoNelPannelloCentrale();
					
				}
				catch(Exception eccezione)
				{
					//getMotoreCongelatore().setProdottoSelezionato(null);//qua c'Ë un incongruenza NON CAPISCO PERCHÈ il SELEZIONATO
					System.out.println("Err 6 nella visualizzazione. Viene lanciata un eccezione se non trova il prodotto visualizzato");
					
					//gestione dell'eccezione
					getMotoreCongelatore().setMessaggioInformativo("Non ci sono prodotti da visualizzare");
					etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
					getMotoreCongelatore().setAvvioVisual(false);
					
				}
				//pannelloCentro.visualizzaProdottoNelPannelloCentrale();
				pannelloCentro.repaint();
				pannelloDestra.repaint();
			}
		}
		
		//azione bottone stampa
		public class AzioneBottoneStampa extends AbstractAction
		{
			public AzioneBottoneStampa(String nome, Icon icona)
			{
				putValue(Action.NAME, nome);
				putValue(Action.SHORT_DESCRIPTION, "Stampa");
				putValue(Action.SMALL_ICON, icona);
			}
			public void actionPerformed(ActionEvent e)
			{
				FinestraStampa finestraStampa = new FinestraStampa(getMotoreCongelatore());
				finestraStampa.setVisible(true);
			}
		}
		
		//azione bottone salva
		public class AzioneBottoneSalva extends AbstractAction
		{
			public AzioneBottoneSalva(String nome, Icon icona)
			{
				putValue(Action.NAME, nome);
				putValue(Action.SMALL_ICON, icona);
				putValue(Action.SHORT_DESCRIPTION, "Salva");
			}
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					getMotoreCongelatore().salvaDatabase();
					etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
					JOptionPane.showMessageDialog(getFinestraSuperiore(),"Salvataggio Database effettuato");
				}
				catch(Exception eccezione)
				{
					etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
				}
			}
		}
		
		//azione bottone esci
		public class AzioneBottoneEsci extends AbstractAction
		{
			public AzioneBottoneEsci(String nome, Icon icona)
			{
				putValue(Action.NAME, nome);
				putValue(Action.SMALL_ICON, icona);
				putValue(Action.SHORT_DESCRIPTION, "Esci");
			}
			public void actionPerformed(ActionEvent e)
			{
				//azione per il pulsante esci
				int  i = JOptionPane.showConfirmDialog(getFinestraSuperiore(),"Vuoi Davvero Uscire?","ATTENZIONE",JOptionPane.YES_NO_OPTION);
				if(i==0)
				{
					getFinestraSuperiore().dispose();
				}
			}
		}
	}
	
	
	//PannelloCentro
	public class PannelloCentro extends JPanel
	{
		public PannelloCentro()
		{
			//imposto questo pannello nel motore in modo da gestirlo altrove
			getMotoreCongelatore().setPannelloCentroPannelloPrincipale(this);
			
			//imposto il carattere da utilizzare in questo pannello
			getMotoreCongelatore().impostaCaratterePerVisualVoci();
			
			Font f = new Font(getMotoreCongelatore().getCarattereVisualVoci(),Font.PLAIN,25);
			Font fNote = new Font(getMotoreCongelatore().getCarattereVisualVoci(),Font.ITALIC,15);
			
			//creazione etichette
			Box boxVerticale = Box.createVerticalBox();
			
			//prima riga
			Box boxIdENomeProdotto = Box.createHorizontalBox();
			etichettaIdProdotto = new JLabel();
			etichettaIdProdotto.setFont(f);
			etichettaIdProdotto.setForeground(Color.ORANGE);
			etichettaNomeProdotto = new JLabel();
			etichettaNomeProdotto.setFont(f);
			etichettaNomeProdotto.setForeground(Color.YELLOW);
			etichettaIdProdotto.setVisible(false);
			etichettaNomeProdotto.setVisible(false);
			boxIdENomeProdotto.add(etichettaIdProdotto);
			boxIdENomeProdotto.add(Box.createHorizontalStrut(20));
			boxIdENomeProdotto.add(etichettaNomeProdotto);
			
			//seconda riga
			Box boxDataInserimento = Box.createHorizontalBox();
			etichettaDescrizioneDataInserimento = new JLabel();
			etichettaDescrizioneDataInserimento.setFont(f);
			etichettaDescrizioneDataInserimento.setForeground(Color.ORANGE);
			etichettaDataInserimentoProdotto = new JLabel();
			etichettaDataInserimentoProdotto.setFont(f);
			etichettaDataInserimentoProdotto.setForeground(Color.YELLOW);
			etichettaDescrizioneDataInserimento.setVisible(false);
			etichettaDataInserimentoProdotto.setVisible(false);
			boxDataInserimento.add(etichettaDescrizioneDataInserimento);
			boxDataInserimento.add(Box.createHorizontalStrut(20));
			boxDataInserimento.add(etichettaDataInserimentoProdotto);
			
			//terza riga
			Box boxDataScadenza = Box.createHorizontalBox();
			etichettaDescrizioneDataScadenza = new JLabel();
			etichettaDescrizioneDataScadenza.setFont(f);
			etichettaDescrizioneDataScadenza.setForeground(Color.ORANGE);
			etichettaDataScadenzaProdotto = new JLabel();
			etichettaDataScadenzaProdotto.setFont(f);
			etichettaDataScadenzaProdotto.setForeground(Color.YELLOW);
			etichettaDescrizioneDataScadenza.setVisible(false);
			etichettaDataScadenzaProdotto.setVisible(false);
			boxDataScadenza.add(etichettaDescrizioneDataScadenza);
			boxDataScadenza.add(Box.createHorizontalStrut(20));
			boxDataScadenza.add(etichettaDataScadenzaProdotto);
			
			//quarta riga
			Box boxQuantit‡ = Box.createHorizontalBox();
			etichettaDescrizioneQuantit‡ = new JLabel();
			etichettaDescrizioneQuantit‡.setFont(f);
			etichettaDescrizioneQuantit‡.setForeground(Color.ORANGE);
			etichettaUnit‡DiMisuraProdotto = new JLabel();
			etichettaUnit‡DiMisuraProdotto.setFont(f);
			etichettaUnit‡DiMisuraProdotto.setForeground(Color.YELLOW);
			etichettaQuantit‡Prodotto = new JLabel();
			etichettaQuantit‡Prodotto.setFont(f);
			etichettaQuantit‡Prodotto.setForeground(Color.YELLOW);
			etichettaDescrizioneQuantit‡.setVisible(false);
			etichettaUnit‡DiMisuraProdotto.setVisible(false);
			etichettaQuantit‡Prodotto.setVisible(false);
			boxQuantit‡.add(etichettaDescrizioneQuantit‡);
			boxQuantit‡.add(Box.createHorizontalStrut(20));
			boxQuantit‡.add(etichettaUnit‡DiMisuraProdotto);
			boxQuantit‡.add(Box.createHorizontalStrut(20));
			boxQuantit‡.add(etichettaQuantit‡Prodotto);
			
			//quinta riga
			Box boxNote = Box.createHorizontalBox();
			areaNote = new JTextArea(5,10);
			areaNote.setBackground(Color.BLACK);
			areaNote.setForeground(Color.RED);
			areaNote.setEditable(false);
			areaNote.setFont(fNote);
			pannelloScorrevole = new JScrollPane(areaNote);
			pannelloScorrevole.setVisible(false);
			boxNote.add(pannelloScorrevole);
			
			boxVerticale.add(Box.createVerticalStrut(15));
			boxVerticale.add(boxIdENomeProdotto);
			boxVerticale.add(Box.createVerticalStrut(15));
			boxVerticale.add(boxDataInserimento);
			boxVerticale.add(Box.createVerticalStrut(15));
			boxVerticale.add(boxDataScadenza);
			boxVerticale.add(Box.createVerticalStrut(15));
			boxVerticale.add(boxQuantit‡);
			boxVerticale.add(Box.createVerticalStrut(15));
			boxVerticale.add(boxNote);
			boxVerticale.add(Box.createVerticalStrut(15));
			this.add(boxVerticale);
			
			//SETTO LE ETICHETTE NEL MOTORE IN MODO DA POTERLE AGGIORNARE DINAMICAMENTE DURANTE LE OPERAZIONI
			//inoltre viene pi˘ facile gestirne la visibilit‡ o meno anche da altre pagine
			getMotoreCongelatore().setEtichettaIdProdotto(etichettaIdProdotto);
			getMotoreCongelatore().setEtichettaNomeProdotto(etichettaNomeProdotto);
			getMotoreCongelatore().setEtichettaDescrizioneDataInserimento(etichettaDescrizioneDataInserimento);
			getMotoreCongelatore().setEtichettaDataInserimentoProdotto(etichettaDataInserimentoProdotto);
			getMotoreCongelatore().setEtichettaDescrizioneDataScadenza(etichettaDescrizioneDataScadenza);
			getMotoreCongelatore().setEtichettaDataScadenzaProdotto(etichettaDataScadenzaProdotto);
			getMotoreCongelatore().setEtichettaUnit‡DiMisuraProdotto(etichettaUnit‡DiMisuraProdotto);
			getMotoreCongelatore().setEtichettaQuantit‡Prodotto(etichettaQuantit‡Prodotto);
			getMotoreCongelatore().setAreaNote(areaNote);
			getMotoreCongelatore().setPannelloScorrevoleAreaNote(pannelloScorrevole);
			getMotoreCongelatore().setEtichettaDescrizioneQuantit‡(etichettaDescrizioneQuantit‡);
			
			etichettaDescrizioneDataInserimento.setText("Data Inserimento");
			etichettaDescrizioneDataScadenza.setText("Data Scadenza");
			etichettaDescrizioneQuantit‡.setText("Quantit‡");
			
		}
		
		public void visualizzaProdottoNelPannelloCentrale()
		{
			if(getMotoreCongelatore().getAvvioVisual()&&getMotoreCongelatore().getProdottoVisualizzato()!=null
					&&getMotoreCongelatore().getAvvioRicerca()==false)
			{
				etichettaIdProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getIdProdotto()+"");
				etichettaNomeProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getNomeProdotto());
				etichettaDataInserimentoProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getStringaDataInserimento());
				etichettaDataScadenzaProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getStringaDataScadenza());
				etichettaUnit‡DiMisuraProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getUnit‡DiMisuraProdotto());
				etichettaQuantit‡Prodotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getQuantit‡Prodotto()+"");
				areaNote.setText(getMotoreCongelatore().getProdottoVisualizzato().getNote());
				etichettaIdProdotto.setVisible(true);
				etichettaNomeProdotto.setVisible(true);
				etichettaDescrizioneDataInserimento.setVisible(true);
				etichettaDataInserimentoProdotto.setVisible(true);
				etichettaDescrizioneDataScadenza.setVisible(true);
				etichettaDataScadenzaProdotto.setVisible(true);
				etichettaDescrizioneQuantit‡.setVisible(true);
				etichettaUnit‡DiMisuraProdotto.setVisible(true);
				etichettaQuantit‡Prodotto.setVisible(true);
				pannelloScorrevole.setVisible(true);
				
				pannelloDestra.repaint();
			}
			
			if(getMotoreCongelatore().getAvvioRicerca()&&getMotoreCongelatore().getProdottoVisualizzato()!=null
					&&getMotoreCongelatore().getAvvioVisual()==false)
			{
				etichettaIdProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getIdProdotto()+"");
				etichettaNomeProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getNomeProdotto());
				etichettaDataInserimentoProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getStringaDataInserimento());
				etichettaDataScadenzaProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getStringaDataScadenza());
				etichettaUnit‡DiMisuraProdotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getUnit‡DiMisuraProdotto());
				etichettaQuantit‡Prodotto.setText(getMotoreCongelatore().getProdottoVisualizzato().getQuantit‡Prodotto()+"");
				areaNote.setText(getMotoreCongelatore().getProdottoVisualizzato().getNote());
				etichettaIdProdotto.setVisible(true);
				etichettaNomeProdotto.setVisible(true);
				etichettaDescrizioneDataInserimento.setVisible(true);
				etichettaDataInserimentoProdotto.setVisible(true);
				etichettaDescrizioneDataScadenza.setVisible(true);
				etichettaDataScadenzaProdotto.setVisible(true);
				etichettaDescrizioneQuantit‡.setVisible(true);
				etichettaUnit‡DiMisuraProdotto.setVisible(true);
				etichettaQuantit‡Prodotto.setVisible(true);
				pannelloScorrevole.setVisible(true);
			}
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			if(getMotoreCongelatore().getAvvioVisual()==false&&getMotoreCongelatore().getAvvioRicerca()==false)
			{
				etichettaIdProdotto.setVisible(false);
				etichettaNomeProdotto.setVisible(false);
				etichettaDescrizioneDataInserimento.setVisible(false);
				etichettaDataInserimentoProdotto.setVisible(false);
				etichettaDescrizioneDataScadenza.setVisible(false);
				etichettaDataScadenzaProdotto.setVisible(false);
				etichettaDescrizioneQuantit‡.setVisible(false);
				etichettaUnit‡DiMisuraProdotto.setVisible(false);
				etichettaQuantit‡Prodotto.setVisible(false);
				pannelloScorrevole.setVisible(false);
				g2.drawImage(copertina,400,60,null);
				Font f = new Font(getMotoreCongelatore().getCarattereVisualVoci(),Font.PLAIN,17);
				g2.setFont(f);
				g2.setPaint(Color.YELLOW);
				g2.drawString("Buongiorno",170,100);
				g2.drawString("GESTIONE CONGELATORE v.4.1beta",60,130);
				g2.drawString("by Lupo 2018",150,160);
				g2.drawString("--- diritti riservati all'autore ---",80,190);
			}
		}
	}
	
	
	//PannelloSotto
	public class PannelloSotto extends JPanel
	{
		public PannelloSotto()
		{
			getMotoreCongelatore().setPannelloSottoPannelloPrincipale(this);
			etichettaMessaggioInformativo = new JLabel();
			getMotoreCongelatore().setEtichettaMessaggioInformativoPannelloPrincipale(etichettaMessaggioInformativo);
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
	
	
	//PannelloSinistra
	public class PannelloSinistra extends JPanel
	{
		
		public PannelloSinistra()
		{
			this.setLayout(new GridLayout(12,1));
			Icon iconaAvanti = new ImageIcon(getClass().getClassLoader().getResource("pulsante_avanti.gif"));
			Icon iconaAvantiPlus = new ImageIcon(getClass().getClassLoader().getResource("pulsante_avanti_plus.gif"));
			Icon iconaIndietro = new ImageIcon(getClass().getClassLoader().getResource("pulsante_indietro.gif"));
			Icon iconaIndietroPlus = new ImageIcon(getClass().getClassLoader().getResource("pulsante_indietro_plus.gif"));
			checkSceltaProdotto = new JCheckBox("Scegli");
			checkSceltaProdotto.setBackground(getMotoreCongelatore().getColorePannelloDestra());
			
			JLabel labelVuota = new JLabel("");
			this.add(new JLabel(""));
			bottoneAvanti = new JButton(iconaAvanti);
			this.add(bottoneAvanti);
			bottoneAvanti.addActionListener(new AzioneBottoneAvantiIndietro(1));
			
			bottoneIndietro = new JButton(iconaIndietro);
			this.add(bottoneIndietro);
			bottoneIndietro.addActionListener(new AzioneBottoneAvantiIndietro(-1));
			
			bottoneAvantiPlus = new JButton(iconaAvantiPlus);
			this.add(bottoneAvantiPlus);
			bottoneAvantiPlus.addActionListener(new AzioneBottoneAvantiIndietro(2));
			
			bottoneIndietroPlus = new JButton(iconaIndietroPlus);
			this.add(bottoneIndietroPlus);
			bottoneIndietroPlus.addActionListener(new AzioneBottoneAvantiIndietro(-2));
			
			this.add(new JLabel(""));
			this.add(checkSceltaProdotto);
			
			checkSceltaProdotto.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
					if(checkSceltaProdotto.isSelected()&&getMotoreCongelatore().getElencoProdotti()!=null
							&&getMotoreCongelatore().getElencoProdotti().size()>0
							&&getMotoreCongelatore().getAvvioVisual())
					{
						getMotoreCongelatore().setProdottoSelezionato(getMotoreCongelatore().getElencoProdotti().get(getMotoreCongelatore().getIteratore()));
					}
					else if(checkSceltaProdotto.isSelected() && getMotoreCongelatore().getElencoRicerca()!=null 
							&& getMotoreCongelatore().getElencoRicerca().size()>0 
							&& getMotoreCongelatore().getAvvioRicerca())
					{
						getMotoreCongelatore().setProdottoSelezionato(getMotoreCongelatore().getElencoRicerca().get(getMotoreCongelatore().getIteratore()));
					}
				}
			});
		}
		
		public class AzioneBottoneAvantiIndietro implements ActionListener
		{
			private int iter;
			public AzioneBottoneAvantiIndietro(int iter)
			{
				this.iter = iter;
			}
			public void actionPerformed(ActionEvent event)
			{
				if(getMotoreCongelatore().getAvvioVisual()&&getMotoreCongelatore().getElencoProdotti().size()>0
						&&getMotoreCongelatore().getElencoProdotti()!=null)
				{
					checkSceltaProdotto.setSelected(false);
					iteratoreVoceProdotto = getMotoreCongelatore().getIteratore() + iter;
					getMotoreCongelatore().setIteratore(iteratoreVoceProdotto);
					
					if(getMotoreCongelatore().getIteratore()>=getMotoreCongelatore().getElencoProdotti().size())
					{
						iteratoreVoceProdotto = getMotoreCongelatore().getElencoProdotti().size()-1;
						getMotoreCongelatore().setIteratore(iteratoreVoceProdotto);
					}
					else if(iteratoreVoceProdotto<0)
					{
						iteratoreVoceProdotto = 0;
						getMotoreCongelatore().setIteratore(iteratoreVoceProdotto);
					}
					
					if(getMotoreCongelatore().getElencoProdotti().size()>0)
					{
						getMotoreCongelatore().setProdottoVisualizzato(getMotoreCongelatore().getElencoProdotti().get(getMotoreCongelatore().getIteratore()));
						
					}
				}
				
				if(getMotoreCongelatore().getElencoProdotti()==null || getMotoreCongelatore().getElencoProdotti().size()==0)
				{
					getMotoreCongelatore().setMessaggioInformativo("Non ci Sono Prodotti");
					etichettaMessaggioInformativo.setText(getMotoreCongelatore().getMessaggioInformativo());
					checkSceltaProdotto.setSelected(false);
				}
				
				if(getMotoreCongelatore().getAvvioRicerca()&&getMotoreCongelatore().getElencoRicerca().size()>0
						&&getMotoreCongelatore().getElencoRicerca()!=null)
				{
					checkSceltaProdotto.setSelected(false);
					iteratoreVoceProdotto = getMotoreCongelatore().getIteratore()+iter;
					getMotoreCongelatore().setIteratore(iteratoreVoceProdotto);
					if(getMotoreCongelatore().getIteratore()>=getMotoreCongelatore().getElencoRicerca().size())
					{
						iteratoreVoceProdotto = getMotoreCongelatore().getElencoRicerca().size()-1;
						getMotoreCongelatore().setIteratore(getMotoreCongelatore().getElencoRicerca().size()-1);
						//System.out.println("iteratoreVoceProdotto = getMotoreCongelatore().getElencoRicerca().size()-1;" + iteratoreVoceProdotto);
						
					}
					else if(getMotoreCongelatore().getIteratore()<0)
					{
						iteratoreVoceProdotto=0;
						getMotoreCongelatore().setIteratore(iteratoreVoceProdotto);
						
					}
					if(getMotoreCongelatore().getElencoRicerca().size()>0)
					{
						getMotoreCongelatore().setProdottoVisualizzato(getMotoreCongelatore().getElencoRicerca().get(getMotoreCongelatore().getIteratore())); 
					}
				}
				
				getMotoreCongelatore().setIteratore(iteratoreVoceProdotto);
				//System.out.println("Iteratore dal motore: " + getMotoreCongelatore().getIteratore());
				pannelloCentro.visualizzaProdottoNelPannelloCentrale();
				//pannelloCentro.repaint();
				pannelloDestra.repaint();
			}
		}
	}
	
	
	//PannelloDestra
	public class PannelloDestra extends JPanel
	{
		public PannelloDestra()
		{
			getMotoreCongelatore().setPannelloDestraPannelloPrincipale(this);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			graficaPannelloDestro = (Graphics2D)g;
			
			semaforoRosso = new Ellipse2D.Double(81,98,40,40);
			semaforoArancione = new Ellipse2D.Double(81,151,40,40);
			semaforoVerde = new Ellipse2D.Double(81,204,40,40);
			
			if(getMotoreCongelatore().getAvvioVisual()&&getMotoreCongelatore().getAvvioRicerca()==false
					&&getMotoreCongelatore().getElencoProdotti().size()>0
					&&getMotoreCongelatore().getProdottoVisualizzato()!=null)
			{
				graficaPannelloDestro.setPaint(Color.BLACK);
				graficaPannelloDestro.drawImage(semaforo,15,70,null);
				graficaPannelloDestro.draw(semaforoRosso);
				graficaPannelloDestro.draw(semaforoArancione);
				graficaPannelloDestro.draw(semaforoVerde);
				graficaPannelloDestro.fill(semaforoArancione);
				graficaPannelloDestro.fill(semaforoRosso);
				graficaPannelloDestro.fill(semaforoVerde);
				getMotoreCongelatore().settaIlColorePerLeInformazioni();
				if(getMotoreCongelatore().getColoreGiallo())
				{
					graficaPannelloDestro.setPaint(Color.ORANGE);
					graficaPannelloDestro.fill(semaforoArancione);
				}
				if(getMotoreCongelatore().getColoreRosso())
				{
					graficaPannelloDestro.setPaint(Color.RED);
					graficaPannelloDestro.fill(semaforoRosso);
				}
				if(getMotoreCongelatore().getColoreVerde())
				{
					graficaPannelloDestro.setPaint(Color.GREEN);
					graficaPannelloDestro.fill(semaforoVerde);
				}
			}
			else if(getMotoreCongelatore().getAvvioVisual()==false&&getMotoreCongelatore().getAvvioRicerca())
			{
				graficaPannelloDestro.drawImage(lente,15,70,null);
			}
			else
			{
				graficaPannelloDestro.setFont(new Font(getMotoreCongelatore().getCarattereVisualVoci(),Font.PLAIN+Font.BOLD,10));
				graficaPannelloDestro.drawString("Con GesCon Vers4.1",10,100);
				graficaPannelloDestro.drawString("puoi gestire i prodotti",10,120);
				graficaPannelloDestro.drawString("contenuti nel tuo congelatore",10,140);
				graficaPannelloDestro.drawString("diminuendo gli sprechi e",10,160);
				graficaPannelloDestro.drawString("ottimizzandone l'utilizzo!",10,180);
			}
		}
	}
}
