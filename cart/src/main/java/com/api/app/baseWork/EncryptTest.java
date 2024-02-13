package com.api.app.baseWork;

import com.common.utils.CryptoUtil;

public class EncryptTest {
	public static void main(String[] args) {
		test();
	}
	public static void test() {
		System.out.println("1......................" + CryptoUtil.encOneWay("1"));
	}
}
