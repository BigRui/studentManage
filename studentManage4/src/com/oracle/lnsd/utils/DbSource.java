package com.oracle.lnsd.utils;

import java.sql.Connection;

public interface DbSource {
	Connection getConnection();
}
