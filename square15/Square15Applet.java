package square15;

/*
   By Roman Andronov
*/

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public
class Square15Applet
	extends JApplet
{
	public void
	init()
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			public void
			run()
			{
				createAppletGui();
			}
		});
	}

	private void
	createAppletGui()
	{
		if ( square15pnl == null )
		{
			square15pnl = new Square15Panel( this );
		}
	}

	private Square15Panel		square15pnl = null;
}