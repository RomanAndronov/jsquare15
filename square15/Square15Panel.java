package square15;

/*
  By Roman Andronov
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.util.Random;

class Square15Panel
	extends JPanel
	implements ActionListener
{
	Square15Panel( RootPaneContainer rpc )
	{
		super();

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = gbc.weighty = 1.0 / BOARD_SIZE;

		init( rpc );
	}

	public void
	actionPerformed( ActionEvent ae )
	{
		Object		o = ae.getSource();

		if ( o instanceof JButton )
		{
			JButton		jb = ( JButton )o;

			if ( jb == jbNewGame )
			{
				remove( jbNewGame );
				newGame();
			}
		}
	}

	private void
	init( RootPaneContainer rpc )
	{
		gui = new Square15Gui( this );

		gui.init( rpc );

		newGame();
	}

	void
	newGame()
	{
		int			r = -1;
		int			c = -1;
		Random			rndm = new Random();

		movesCount = 0;
		gameOver = false;
		pnlBoard.removeAll();

		for ( int i = 0; i < BOARD_SIZE; i++ )
		{
			for ( int j = 0; j < BOARD_SIZE; j++ )
			{
				board[ i ][ j ].setView( null );
				board[ i ][ j ].setValue( 0 );
			}
		}

		for ( int i = 0; i < TOTAL_VIEWS; i++ )
		{
			while ( true )
			{
				r = rndm.nextInt( BOARD_SIZE );
				c = rndm.nextInt( BOARD_SIZE );
				if ( board[ r ][ c ].getView() == null )
				{
					board[ r ][ c ].setView( allViews[ i ] );
					allViews[ i ].setCurRow( r );
					allViews[ i ].setCurColumn( c );
					gbc.gridx = c;
					gbc.gridy = r;
					pnlBoard.add( allViews[ i ], gbc );
					break;
				}
			}
		}

		lblMovesCount.setText( "0" );
		lblGame.setText( "Game: on" );
		revalidate();
	}

	void
	checkEndOfGame()
	{
		SquareView		sv = null;

		for ( int i = 0; i < BOARD_SIZE; i++ )
		{
			for ( int j = 0; j < BOARD_SIZE; j++ )
			{
				sv = board[ i ][ j ].getView();
				if ( sv == null )
				{
					continue;
				}

				if ( sv.isHome() != true )
				{
					return;
				}
			}
		}

		gameOver = true;

		lblGame.setText( "Game: over" );
	}

	static final int			BOARD_SIZE = 4;
	static final int			TOTAL_VIEWS = 15;
    
	Square15Gui				gui = null;

	JPanel					pnlBoard = null;

	JPanel					pnlCtrls = null;
	JButton					jbNewGame = null;
	JLabel					lblNumOfMoves = null;
	JLabel					lblMovesCount = null;
	JLabel					lblGame = null;

	ArrowKeys				arrowKeys;
	Square[][]				board;
	SquareView[]				allViews;
	Move[]					moves;
	int					movesCount = 0;

	boolean					gameOver = true;

	private final GridBagConstraints	gbc = new GridBagConstraints();
}