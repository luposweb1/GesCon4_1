import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class FinestraPrincipale extends JFrame
{
	private MotoreCongelatore motoreCongelatore;
	public final int LARGHEZZA_FINESTRA = 920;
	public final int ALTEZZA_FINESTRA = 600;
	
	public FinestraPrincipale(MotoreCongelatore motoreCongelatore)
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
				int  i = JOptionPane.showConfirmDialog(null,"Confermando verrà annullato\n"	+ "tutto il lavoro non salvato!\nConfermi di voler uscire?","ATTENZIONE",JOptionPane.YES_NO_OPTION);
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
		PannelloPrincipale pannelloPrincipale = new PannelloPrincipale(this.getMotoreCongelatore(),this);
		contenitore.add(pannelloPrincipale);
	}
	
	public void setMotoreCongelatore(MotoreCongelatore motoreCongelatore)
	{
		this.motoreCongelatore=motoreCongelatore;
	}
	public MotoreCongelatore getMotoreCongelatore()
	{
		return this.motoreCongelatore;
	}
}
