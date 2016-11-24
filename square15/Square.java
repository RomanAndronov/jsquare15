package square15;

/*
  By Roman Andronov
 */

class Square
{
	Square( int r, int c )
	{
		row = r;
		col = c;
		value = 0;
		view = null;
	}

	int
	getRow()
	{
		return row;
	}

	int
	getColumn()
	{
		return col;
	}

	void
	setView( SquareView sv )
	{
		view = sv;
	}

	SquareView
	getView()
	{
		return view;
	}

	int
	getValue()
	{
		return value;
	}

	void
	setValue( int val )
	{
		value = val;
	}

	private final int	    row;
	private final int	    col;
	private int		    value;
	private SquareView	    view;
}