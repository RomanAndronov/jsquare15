package square15;

/*
  By Roman Andronov
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JButton;

class SquareView
	extends JButton 
	implements ActionListener
{
	SquareView( Square15Panel sq15pnl, int v, int hr, int hc )
	{
		super( Integer.toString( v ) );

		pnlSquare15 = sq15pnl;

		value = v;
		homeRow = curRow = hr;
		homeCol = curCol = hc;

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = gbc.weighty = 1.0 / Square15Panel.BOARD_SIZE;

		setFont( SV_FONT );
		setBackground( SV_BG_CLR );
		setForeground( SV_FG_CLR );
		setMinimumSize( SV_DIMENSION );
		setPreferredSize( SV_DIMENSION );

		addActionListener( this );
		addKeyListener( pnlSquare15.arrowKeys );
	}

	int
	getValue()
	{
		return value;
	}
	
	int
	getCurRow()
	{
		return curRow;
	}
	
	int
	getCurColumn()
	{
		return curCol;
	}

	void
	setCurRow( int cr )
	{
		if ( cr >= Square15Panel.BOARD_SIZE )
		{
			return;
		}

		curRow = cr;
	}

	void
	setCurColumn( int cc )
	{
		if ( cc >= Square15Panel.BOARD_SIZE )
		{
			return;
		}

		curCol = cc;
	}

	boolean
	isHome()
	{
		boolean		rv = false;

		if ( curRow == homeRow && curCol == homeCol )
		{
			rv = true;
		}

		return rv;
	}

	public void 
	actionPerformed( ActionEvent ae )
	{
		int			r;
		int			c;

		if ( pnlSquare15.gameOver == true )
		{
			return;
		}

		for ( int m = 0; m < pnlSquare15.moves.length; m++ )
		{
			r = curRow + pnlSquare15.moves[ m ].getRow();
			c = curCol + pnlSquare15.moves[ m ].getColumn();
			if ( r < 0 || r >= Square15Panel.BOARD_SIZE ||
				c < 0 || c >= Square15Panel.BOARD_SIZE )
			{
				continue;
			}

			if ( pnlSquare15.board[ r ][ c ].getView() != null )
			{
				continue;
			}

			gbc.gridx = c;
			gbc.gridy = r;

			pnlSquare15.pnlBoard.remove( this );
			pnlSquare15.pnlBoard.add( this, gbc );

			pnlSquare15.board[ curRow ][ curCol ].setView( null );
			pnlSquare15.board[ r ][ c ].setView( this );
			pnlSquare15.pnlBoard.revalidate();

			requestFocusInWindow();

			curRow = r;
			curCol = c;

			pnlSquare15.movesCount++;
			pnlSquare15.lblMovesCount.setText( "" + pnlSquare15.movesCount );

			break;
		}

		pnlSquare15.checkEndOfGame();
	}

	private static final int		SV_SIZE = 77;
	private static final Dimension		SV_DIMENSION =
		new Dimension( SV_SIZE, SV_SIZE );
	private static final Font		SV_FONT =
		new Font( "Tahoma", Font.BOLD, 14 );
	private static final Color		SV_BG_CLR =
		new Color( 197, 213, 203 );
	private static final Color		SV_FG_CLR =
		new Color( 114, 120, 116 );


	private final int			value;
	private final int			homeRow;
	private final int			homeCol;
	private final Square15Panel		pnlSquare15;
	private final GridBagConstraints	gbc = new GridBagConstraints();

	private int				curRow;
	private int				curCol;
}