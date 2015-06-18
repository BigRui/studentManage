package com.oracle.lnsd;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void test() {
		try {
			Date date = DateUtils.parseDate("2013-12-1 12:3:30:500", "yyyy-MM-dd HH:mm:ss:SS");
			System.out.println(DateFormatUtils.format(date,
					"yyyy/MM/dd HH-mm-ss-SS"));
			
			Date d1 = DateUtils.truncate(date, Calendar.DATE);
			System.out.println(DateFormatUtils.format(d1, "yyyy/MM/dd HH:mm:ss:SS"));
			Date d2 = DateUtils.ceiling(date, Calendar.DATE);
			d2 = DateUtils.addMilliseconds(d2, -1);
			System.out.println(DateFormatUtils.format(d2, "yyyy/MM/dd HH:mm:ss:SS"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
