package square15;

/*
   By Roman Andronov
 */

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

/*
   This will execute Square15 as a stand-alone
   Java program.

   To execute it as a Java applet consult the
   Square15Applet class in this package
*/

public
class Square15Frame
	extends JFrame
{
	public
	Square15Frame()
	{
		super();
		setTitle( "Square 15" );
		setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
	}

	public static void
	main( String[] args )
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			public void
			run()
			{
				Square15Frame		square15frm = new Square15Frame();

				square15frm.square15pnl = new Square15Panel( square15frm );
				square15frm.pack();
				square15frm.setLocationRelativeTo( null );
				square15frm.setVisible( true );
			}
		});

	}

	private Square15Panel		square15pnl = null;
}