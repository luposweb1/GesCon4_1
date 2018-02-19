import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class PannelloStampa extends JPanel
{
	private JFrame finestraSuperiore;
	private MotoreCongelatore motoreCongelatore;
	public PannelloStampa(JFrame finestraSuperiore, MotoreCongelatore motoreCongelatore)
	{
		this.setMotoreCongelatore(motoreCongelatore);
		this.setFinestraSuperiore(finestraSuperiore);
	}
	
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
}
