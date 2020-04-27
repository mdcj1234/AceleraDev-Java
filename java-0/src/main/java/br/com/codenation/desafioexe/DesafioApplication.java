package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		ArrayList<Integer> list = new ArrayList<>();

		list.add(0);
		list.add(1);
		int num = list.get(1) + list.get(0);

		for(int i = 2; num <= 377; i++) {
			list.add(num);
			num = list.get(i) + list.get(i-1);
		}

		return list;
	}

	public static Boolean isFibonacci(Integer a) {

		List<Integer> list = fibonacci();

		return list.contains(a);
	}

}