import javax.swing.*;

public class FinestraCerca extends JFrame
{
	private MotoreCongelatore motoreCongelatore;
	public FinestraCerca(MotoreCongelatore motoreCongelatore)
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
