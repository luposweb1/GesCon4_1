import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class PannelloCerca extends JPanel
{
	private MotoreCongelatore motoreCongelatore;
	private JFrame finestraSuperiore;
	
	private PannelloSopra pannelloSopra;
	private PannelloCentro pannelloCentro;
	private PannelloSotto pannelloSotto;
	private PannelloSinistra pannelloSinistra;
	
	public PannelloCerca(MotoreCongelatore motoreCongelatore, JFrame finestraSuperiore)
	{
		this.setFinestraSuperiore(finestraSuperiore);
		this.setMotoreCongelatore(motoreCongelatore);
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
			
		}
	}
	
	public class PannelloSinistra extends JPanel
	{
		public PannelloSinistra()
		{
			
		}
	}
}
