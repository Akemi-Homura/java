package EMT;

import java.util.*;

public class Num2Rmb {
	private String[] chineseNum = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��" };
	private String[] unit = { "", "ʮ", "��", "ǧ" };
	private String[] m_unit = { "", "��", "��" };
	private String[] a_unit = { "��", "��" };
	private String num;
	private String afterNum;
	// ����������
	private static String rmb;

	// ��ת�ַ����ĺ���
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

	// ���4λ��
	public void print4Num(String u) {
		// System.out.println("u="+u);
		int pre = 1;
		for (int i = 0; i < u.length(); i++) {
			int index = u.charAt(i) - '0';
			if (index != 0) {
				// ����������0
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
			rmb = rmb + "Ԫ";
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
		// ����������ݣ����븺��ͣ����
		while (true) {
			double wantTo = sc.nextDouble();

			if (wantTo < 0)
				break;
			// ���������������
			if (wantTo > 1e12) {
				System.out.println("��������ֹ���,���������С��1���ڵ���ֵ");
				continue;
			}
			rmb = "";
			// �ָ��С�����ֺ���������
			nr.divide(wantTo);
			// �����������
			nr.printNum();
			// ���С������
			nr.printAfterNum();
			System.out.println(rmb);
		}

		sc.close();

	}
}
