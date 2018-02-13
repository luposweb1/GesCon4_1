/**
 * @author mitic
 * 
 */
import javax.swing.*;
import java.io.*;
public class GesCon4_1
{
	public static void main(String[]args)
	{
		/*File f = new File (System.getProperty ("java.class.path"));
		File dir = f.getAbsoluteFile().getParentFile();
		System.out.println(dir);*/
		MotoreCongelatore motoreCongelatore = new MotoreCongelatore();
		FinestraPrincipale finestraPrincipale = new FinestraPrincipale(motoreCongelatore);
		finestraPrincipale.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		finestraPrincipale.setVisible(true);

	}
}
