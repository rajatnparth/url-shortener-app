package com.yebelo.urlshortener.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class IdConversionAlgo {

	public static final IdConversionAlgo INSTANCE = new IdConversionAlgo();

	private static List<Character> base62Chart;
	private static HashMap<Character, Integer> base62Map;

	private IdConversionAlgo() {
		initializeBase62ChartAndMap();
	}

	public void initializeBase62ChartAndMap() {
		base62Chart = new ArrayList<>();
		base62Map = new HashMap<>();
		
		// mapping a-z
		char base62Char = 'a';
		for (int i = 0; i < 26;i++) {
			
			base62Chart.add(base62Char);
			base62Map.put(base62Char, i);
			base62Char += 1;
		}
		
		// mapping A-Z
		base62Char = 'A';
		for (int i = 26; i < 52; i++) {
			
			base62Chart.add(base62Char);
			base62Map.put(base62Char, i);
			base62Char += 1;
		}

		// mapping 0-9
		base62Char = '0';
		for (int i = 52; i < 62; i++) {
			
			base62Chart.add(base62Char);
			base62Map.put(base62Char, i);
			base62Char += 1;
		}
	}
	
	
	public static String createUniqueID(Long id) {
        List<Integer> base62ID = convertBase10ToBase62ID(id);
        StringBuilder uniqueURLID = new StringBuilder();
        for (int digit: base62ID) {
            uniqueURLID.append(base62Chart.get(digit));
        }
        return uniqueURLID.toString();
    }

    private static List<Integer> convertBase10ToBase62ID(Long id) {
        List<Integer> digits = new LinkedList<>();
        while(id > 0) {
            int remainder = (int)(id % 62);
            ((LinkedList<Integer>) digits).addFirst(remainder);
            id /= 62;
        }
        return digits;
    }
    
    public static Long getDBKeyFromUrlIdUsingBase62Map(String uniqueID) {
        List<Character> base62IDs = new ArrayList<>();
        for (int i = 0; i < uniqueID.length(); ++i) {
            base62IDs.add(uniqueID.charAt(i));
        }
        Long dictionaryKey = convertBase62ToBase10ID(base62IDs);
        return dictionaryKey;
    }

    private static Long convertBase62ToBase10ID(List<Character> ids) {
        long id = 0L;
        for (int i = 0, exp = ids.size() - 1; i < ids.size(); ++i, --exp) {
            int base10 = base62Map.get(ids.get(i));
            id += (base10 * Math.pow(62.0, exp));
        }
        return id;
    }

}
