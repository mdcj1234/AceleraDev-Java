package br.com.codenation;

import java.security.InvalidParameterException;
import java.util.*;

import static java.util.Arrays.sort;

public class StatisticUtil {

	public static int average(int[] elements) {

		OptionalDouble media = Arrays.stream(elements).average();

		if(media.isPresent())
			return (int) media.getAsDouble();
		else
			return 0;
	}

	public static int mode(int[] elements) {
		if(elements.length == 0){ throw new InvalidParameterException(); }

		HashMap<Integer,Integer> freq = new HashMap<>();
		int count1  = 0;
		int count2  = 0;
		int moda1 = 0;

		for (int element : elements) {

			if (freq.get(element) != null) {

				int count = freq.get(element) + 1;
				freq.put(element, count);

				if (count > count1) {
					count1 = count;
					moda1 = element;
				}
			} else
				freq.put(element, 1);
		}

		freq.clear();
		for (int element : elements) {

			if (freq.get(element) != null) {

				int count = freq.get(element) + 1;
				freq.put(element, count);

				if (count > count2)
					count2 = count;

			} else if(element != moda1)
				freq.put(element, 1);
		}

		if(count2 < count1)
			return moda1;
		else
			return 0;
	}

	public static int median(int[] elements) {
		if(elements.length == 0){ throw new InvalidParameterException(); }

		sort(elements);
		
		int n = elements.length;
		if(n % 2 == 1){
			return elements[n/2];
		} else {
			int a = elements[n/2];
			int b = elements[n/2 - 1];

			return (a + b)/2;
		}
	}
}