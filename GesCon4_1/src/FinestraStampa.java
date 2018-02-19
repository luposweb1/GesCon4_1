import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class FinestraStampa extends JFrame
{
	private final int LARGHEZZA_FINESTRA = 600;
	private final int ALTEZZA_FINESTRA = 400;
	private MotoreCongelatore motoreCongelatore;
	public FinestraStampa(MotoreCongelatore motoreCongelatore)
	{
		this.setMotoreCongelatore(motoreCongelatore);
		
	}
	
	public void setMotoreCongelatore(MotoreCongelatore motoreCongelatore)
	{
		this.motoreCongelatore = motoreCongelatore;
	}
	public MotoreCongelatore getMotoreCongelatore()
	{
		return this.motoreCongelatore;
	}
}
