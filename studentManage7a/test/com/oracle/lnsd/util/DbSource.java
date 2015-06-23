package com.oracle.lnsd.util;

import java.sql.Connection;

public interface DbSource {
	Connection getConnection();
}
