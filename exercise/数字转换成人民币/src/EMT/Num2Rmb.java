package EMT;

import java.util.*;

public class Num2Rmb {
	private String[] chineseNum = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	private String[] unit = { "", "十", "百", "千" };
	private String[] m_unit = { "", "万", "亿" };
	private String[] a_unit = { "角", "分" };
	private String num;
	private String afterNum;
	// 储存输出结果
	private static String rmb;

	// 翻转字符串的函数
	public String reverseString(String u) {
		StringBuffer temp = new StringBuffer(u);
		return temp.reverse().toString();
	}

	public void divide(double money) {
		long first = (long) money;
		num = new Long(first).toString();
		num = reverseString(num);
		afterNum = new Long(Math.round((money - first) * 100)).toString();
	}

	// 输出4位数
	public void print4Num(String u) {
		// System.out.println("u="+u);
		int pre = 1;
		for (int i = 0; i < u.length(); i++) {
			int index = u.charAt(i) - '0';
			if (index != 0) {
				// 处理连续的0
				if (pre == 0)
					rmb = rmb + chineseNum[pre];
				rmb = rmb + chineseNum[index] + unit[u.length() - i - 1];
			}
			pre = index;
		}
	}

	public void printNum() {
		// int M_level = (num.length() - 1) / 4;
		for (int i = num.length(); i > 0;) {
			int u = i % 4;
			if (u == 0)
				u = 4;
			print4Num(reverseString(num.substring(i - u, i)));
			rmb = rmb + m_unit[(i - 1) / 4];
			i = i - u;
		}
		if (num.charAt(0) != '0' || num.length() > 1)
			rmb = rmb + "元";
	}

	public void printAfterNum() {
		for (int i = 0; i < Math.min(afterNum.length(), 2); i++) {
			int index = afterNum.charAt(i) - '0';
			if (index != 0) {
				// System.out.println("i="+i+" index="+index);
				rmb = rmb + chineseNum[index] + a_unit[i];

			}

		}
	}

	public static void main(String[] args) {
		Num2Rmb nr = new Num2Rmb();
		Scanner sc = new Scanner(System.in);
		// 处理多组数据，输入负数停下来
		while (true) {
			double wantTo = sc.nextDouble();

			if (wantTo < 0)
				break;
			// 输入过大，重新输入
			if (wantTo > 1e12) {
				System.out.println("输入的数字过大,请输入面额小于1万亿的数值");
				continue;
			}
			rmb = "";
			// 分割成小数部分和整数部分
			nr.divide(wantTo);
			// 输出整数部分
			nr.printNum();
			// 输出小数部分
			nr.printAfterNum();
			System.out.println(rmb);
		}

		sc.close();

	}
}
