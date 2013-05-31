package com.julong.tools;

public class PasswordUtil {

	// PasswordTable
	// * 0 1 2 3 4 5 6 7 8 9
	// 0 0 1 2 3 4 5 6 7 8 9
	// 1 1 2 3 4 5 6 7 8 9 0
	// 2 2 3 4 5 6 7 8 9 0 1
	// 3 3 4 5 6 7 8 9 0 1 2
	// 4 4 5 6 7 8 9 0 1 2 3
	// 5 5 6 7 8 9 0 1 2 3 4
	// 6 6 7 8 9 0 1 2 3 4 5
	// 7 7 8 9 0 1 2 3 4 5 6
	// 8 8 9 0 1 2 3 4 5 6 7
	// 9 9 0 1 2 3 4 5 6 7 8

	private static int[][] passworddTable = new int[][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 },
			{ 2, 3, 4, 5, 6, 7, 8, 9, 0, 1 }, { 3, 4, 5, 6, 7, 8, 9, 0, 1, 2 },
			{ 4, 5, 6, 7, 8, 9, 0, 1, 2, 3 }, { 5, 6, 7, 8, 9, 0, 1, 2, 3, 4 },
			{ 6, 7, 8, 9, 0, 1, 2, 3, 4, 5 }, { 7, 8, 9, 0, 1, 2, 3, 4, 5, 6 },
			{ 8, 9, 0, 1, 2, 3, 4, 5, 6, 7 }, { 9, 0, 1, 2, 3, 4, 5, 6, 7, 8 } };

	public static String calcNewPay(String card) {
		String first = card.substring(0, 6);
		String end = card.substring(card.length() - 6, card.length());
		StringBuilder pwd = new StringBuilder();
		pwd.append(passworddTable[Integer.parseInt(String.valueOf(first
				.charAt(0)))][Integer.parseInt(String.valueOf(end.charAt(0)))]);
		pwd.append(passworddTable[Integer.parseInt(String.valueOf(first
				.charAt(1)))][Integer.parseInt(String.valueOf(end.charAt(1)))]);
		pwd.append(passworddTable[Integer.parseInt(String.valueOf(first
				.charAt(2)))][Integer.parseInt(String.valueOf(end.charAt(2)))]);
		pwd.append(passworddTable[Integer.parseInt(String.valueOf(first
				.charAt(3)))][Integer.parseInt(String.valueOf(end.charAt(3)))]);
		pwd.append(passworddTable[Integer.parseInt(String.valueOf(first
				.charAt(4)))][Integer.parseInt(String.valueOf(end.charAt(4)))]);
		pwd.append(passworddTable[Integer.parseInt(String.valueOf(first
				.charAt(5)))][Integer.parseInt(String.valueOf(end.charAt(5)))]);
		return pwd.toString();
	}

	public static String calcSelect(String card) {
		String end = card.substring(card.length() - 6, card.length());
		return end;
	}

	public static String cardOldPwd(String card) {
		double d = card.length() / 2.0;
		String[] singular = new String[(int) Math.ceil(d)];
		String[] complex = new String[(int) Math.ceil(d)];
		int sindex = 0;
		int cindex = 0;
		for (int l = 0; l < card.length(); l++) {
			if (l % 2 == 0) {
				singular[sindex] = String.valueOf(card.charAt(l));
				sindex++;
			} else {
				complex[cindex] = String.valueOf(card.charAt(l));
				cindex++;
			}
		}
		StringBuilder sin = new StringBuilder();
		for (String s : singular) {
			if (s != null) {
				sin.append(s);
			}
		}

		StringBuilder com = new StringBuilder();
		for (String s : complex) {
			if (s != null) {
				com.append(s);
			}
		}
		Long s = Long.parseLong(sin.toString());
		Long c = Long.parseLong(com.toString());
		Long ret = 0L;
		if (s > c) {
			ret = s - c;
		} else {
			ret = c - s;
		}
		return String.valueOf(ret);
	}
}
