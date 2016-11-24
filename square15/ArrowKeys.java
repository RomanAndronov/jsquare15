package square15;

/*
  By Roman Andronov
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.KeyboardFocusManager;

class ArrowKeys
	extends KeyAdapter
{
	ArrowKeys( Square15Panel sq15pnl )
	{
		pnlSquare15 = sq15pnl;
	}

	public void
	keyPressed( KeyEvent ke )
	{
		int			curRow = -1;
		int			curCol = -1;
		int			newRow = -1;
		int			newCol = -1;
		SquareView		newView = null;
		SquareView		curView = null;
		Object			o = KeyboardFocusManager.
			getCurrentKeyboardFocusManager().
			getFocusOwner();
		int			kc = ke.getKeyCode();

		if ( !( o instanceof SquareView ) )
		{
			return;
		}


		if ( kc != KeyEvent.VK_UP &&
			kc != KeyEvent.VK_DOWN &&
			kc != KeyEvent.VK_LEFT &&
			kc != KeyEvent.VK_RIGHT )
		{
			return;
		}

		curView = ( SquareView )o;
		curRow = newRow = curView.getCurRow();
		curCol = newCol = curView.getCurColumn();

		if ( kc == KeyEvent.VK_UP )
		{
			newRow--;
		}
		else if ( kc == KeyEvent.VK_DOWN )
		{
			newRow++;
		}
		else if ( kc == KeyEvent.VK_LEFT )
		{
			newCol--;
		}
		else if ( kc == KeyEvent.VK_RIGHT )
		{
			newCol++;
		}

		if ( newRow < 0 ||
			newRow >= pnlSquare15.BOARD_SIZE ||
			newCol < 0 || 
			newCol >= pnlSquare15.BOARD_SIZE )
		{
			return;
		}

		newView = pnlSquare15.board[ newRow ][ newCol ].getView();
		if ( newView == null )
		{
			return;
		}
		
		newView.requestFocusInWindow();
	}

	private final Square15Panel		pnlSquare15;
}