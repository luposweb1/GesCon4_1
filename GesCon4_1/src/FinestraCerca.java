import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FinestraCerca extends JFrame
{
	private MotoreCongelatore motoreCongelatore;
	public final int LARGHEZZA_FINESTRA = 920;
	public final int ALTEZZA_FINESTRA = 600;
	
	public FinestraCerca(MotoreCongelatore motoreCongelatore)
	{
		this.setMotoreCongelatore(motoreCongelatore);
		this.setSize(LARGHEZZA_FINESTRA,ALTEZZA_FINESTRA);
		Dimension dimensioniSchermoUtente = this.getMotoreCongelatore().getToolkit().getScreenSize();
		this.setLocation((dimensioniSchermoUtente.width-LARGHEZZA_FINESTRA)/2, (dimensioniSchermoUtente.height-ALTEZZA_FINESTRA)/2);
		this.setTitle(this.getMotoreCongelatore().getTitoloFinestre());
		this.setResizable(false);
		this.setIconImage(this.getMotoreCongelatore().getIconaProgramma());
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent event)
			{
				int  i = JOptionPane.showConfirmDialog(null,"Confermando non verrà\n"	+ "visualizzata la ricerca!\nConfermi di voler uscire?","ATTENZIONE",JOptionPane.YES_NO_OPTION);
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
		PannelloCerca pannelloCerca = new PannelloCerca(getMotoreCongelatore(),this);
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
