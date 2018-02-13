import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class PannelloCerca extends JPanel
{
	private MotoreCongelatore motoreCongelatore;
	private JFrame finestraSuperiore;
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
		//prova
	}
}
