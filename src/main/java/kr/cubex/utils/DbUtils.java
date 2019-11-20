package kr.cubex.utils;

import java.sql.SQLException;

public class DbUtils {
	//
	public static final int				MYSQL_DUPLICATE_KEY		= 1062;
	//
	public static final int				ERR_DB_DUPLICATE_KEY	= MYSQL_DUPLICATE_KEY;

	//
	public static int getErrorCode(Exception e) {
		int			nErrCode = -1;
		Exception 	ex = e;

		while (ex != null) {
			if (ex instanceof SQLException) {
				SQLException	sqe = (SQLException) ex;
				nErrCode = sqe.getErrorCode();
				break;
			}

			ex = (Exception) ex.getCause();
		}
		return nErrCode;
	}

	public static boolean isDuplicateKey(int nDbErrCode) {
		return nDbErrCode == ERR_DB_DUPLICATE_KEY;
	}
}
