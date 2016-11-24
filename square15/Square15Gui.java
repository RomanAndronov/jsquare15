package square15;

/*
   By Roman Andronov
 */

import java.awt.Insets;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

class Square15Gui
{
	Square15Gui( Square15Panel pnlsq15 )
	{
		pnlSquare15 = pnlsq15;
	}

	void
	init( RootPaneContainer rpc )
	{
		GridBagConstraints	gbc = new GridBagConstraints();

		gbc.gridx = gbc.gridy = 0;
		gbc.weightx = gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = INSETS;

		rpc.getContentPane().setLayout( new GridBagLayout() );

		pnlSquare15.setLayout( new GridBagLayout() );
		pnlSquare15.setBorder( BorderFactory.createLineBorder( CLRGRAY ) );
		rpc.getContentPane().add( pnlSquare15, gbc );

		pnlSquare15.pnlBoard = new JPanel();
		pnlSquare15.pnlBoard.setBorder( BorderFactory.createLineBorder( CLRGRAY ) );
		pnlSquare15.pnlBoard.setLayout( new GridBagLayout() );
		pnlSquare15.add( pnlSquare15.pnlBoard, gbc );
		mkBoardPnl();

		gbc.gridy = 1;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		pnlSquare15.pnlCtrls = new JPanel();
		pnlSquare15.pnlCtrls.setBorder( BorderFactory.createLineBorder( CLRGRAY ) );
		pnlSquare15.pnlCtrls.setLayout( new GridBagLayout() );
		pnlSquare15.add( pnlSquare15.pnlCtrls, gbc );
		mkCtrlsPnl();
	}

	private void
	mkBoardPnl()
	{
		int			svn = 0;
		SquareView		sv = null;

		pnlSquare15.arrowKeys = new ArrowKeys( pnlSquare15 );

		pnlSquare15.pnlBoard.setBackground( BOARD_CLR );

		pnlSquare15.moves =
			new Move[]
			{
				new Move( 0, -1 ), // Same row, previous column
				new Move( -1, 0 ), // Previous row, same column
				new Move( 0, 1 ), // Same row, next column
				new Move( 1, 0 ) // Next row, same column
			};

		pnlSquare15.allViews = new SquareView[ Square15Panel.TOTAL_VIEWS ];

		pnlSquare15.board =
			new Square[ Square15Panel.BOARD_SIZE ][ Square15Panel.BOARD_SIZE ];

		for ( int i = 0; i < Square15Panel.BOARD_SIZE; i++ )
		{
			for ( int j = 0; j < Square15Panel.BOARD_SIZE; j++ )
			{
				pnlSquare15.board[ i ][ j ] = new Square( i, j );
				if ( i == ( Square15Panel.BOARD_SIZE - 1 ) &&
					j == ( Square15Panel.BOARD_SIZE - 1 ) )
				{
					continue;
				}

				sv = new SquareView( pnlSquare15, svn + 1, i, j );
				pnlSquare15.allViews[ svn ] = sv;
				svn++;
			}
		}
	}

	private void
	mkCtrlsPnl()
	{
		GridBagConstraints	gbc = new GridBagConstraints();

		gbc.gridx = gbc.gridy = 0;
		gbc.insets = INSETS;

		pnlSquare15.pnlCtrls.setBackground( BOARD_CLR );
		
		pnlSquare15.jbNewGame = new JButton( "New Game" );
		pnlSquare15.jbNewGame.setMnemonic( KeyEvent.VK_N );
		pnlSquare15.jbNewGame.addActionListener( pnlSquare15 );
		pnlSquare15.pnlCtrls.add( pnlSquare15.jbNewGame, gbc );

		gbc.gridx = 1;
		pnlSquare15.lblNumOfMoves = new JLabel( "Moves Made: " );
		pnlSquare15.pnlCtrls.add( pnlSquare15.lblNumOfMoves, gbc );

		gbc.gridx = 2;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		pnlSquare15.lblMovesCount = new JLabel( "0" );
		pnlSquare15.pnlCtrls.add( pnlSquare15.lblMovesCount, gbc );

		gbc.gridx = 0;
		gbc.gridy= 1;
		gbc.gridwidth= 3;
		pnlSquare15.lblGame = new JLabel( "Game: on" );
		pnlSquare15.pnlCtrls.add( pnlSquare15.lblGame, gbc );
	}


	Square15Panel				pnlSquare15;

	static final Insets			INSETS = new Insets( 5, 5, 5, 5 );
	static final Color			CLRGRAY = Color.GRAY;
	static final Color			BOARD_CLR = new Color( 227, 224, 207 );
}