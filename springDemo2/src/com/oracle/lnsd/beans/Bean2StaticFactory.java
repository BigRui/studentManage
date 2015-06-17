package com.oracle.lnsd.beans;

public class Bean2StaticFactory {
	/**
	 * 简单的静态工厂方法
	 * @return
	 */
	public static Bean2 build() {
		return new Bean2();
	}
}
