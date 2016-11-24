package square15;

/*
  By Roman Andronov
 */

class Move
{
	Move( int r, int c )
	{
		row = r;
		col = c;
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

	private final int		row;
	private final int		col;
}