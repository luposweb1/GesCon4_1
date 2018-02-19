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
		this.setTitle(this.getMotoreCongelatore().getTitoloFinestre() + " Tabella");
		this.setSize(LARGHEZZA_FINESTRA,ALTEZZA_FINESTRA);
		Dimension d = getMotoreCongelatore().getToolkit().getScreenSize();
		this.setLocation((d.width-LARGHEZZA_FINESTRA)/2, (d.height-ALTEZZA_FINESTRA)/2);
		this.setResizable(false);
		this.setIconImage(this.getMotoreCongelatore().getIconaProgramma());
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				int  i = JOptionPane.showConfirmDialog(null,"Confermando non verrà\n"
					+ "stampato nessun prodotto!","ATTENZIONE",JOptionPane.YES_NO_OPTION);
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
		
		Container contentPane = this.getContentPane();
		PannelloStampa pannelloStampa = new PannelloStampa(this, getMotoreCongelatore());
		contentPane.add(pannelloStampa);
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
