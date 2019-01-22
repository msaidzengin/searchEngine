import java.io.*;
import java.util.*;

public class DesktopSearch {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MyHashTable hash = oku();
		String find = "";
		do{
			System.out.print("Aranacak kelime :\n-");
			find = sc.next();
			long sn = System.nanoTime();
			long millis = System.currentTimeMillis() % 1000;
			bul(hash, find);
			long sn2 = System.nanoTime();
			long millis2 = System.currentTimeMillis() % 1000;
			System.out.println("Gecen sure: " + (millis2 - millis) + " mili seconds ("+((sn2/1000000.0) - (sn/1000000.0))+")");
		}while(!find.equals("0"));
		sc.close();
	}
	public static MyHashTable oku() {
		MyHashTable hash = new MyHashTable();
	    ObjectInputStream oku = null;
	    try{
	    	oku = new ObjectInputStream(new FileInputStream("invertedIndex"));
		    hash = (MyHashTable) oku.readObject();
		    oku.close();
	    }catch(Exception e){
	    	System.out.println("Hata: " + e.getMessage());
	    }
		return hash;
	}
	public static void bul(MyHashTable hash, String s) {
		s = s.toLowerCase();
		int key = hash.hash(s);
		if(hash.hash[key%hash.hash.length] == null)
			System.out.println("Boyle bir kelime bulunamadi.");
		else {
			int index = -1;
			for(int i=0; i<hash.hash[key%hash.hash.length].kelime.size(); i++) {
				if(hash.hash[key%hash.hash.length].kelime.get(i).equals(s)) {
					index = i;
				}
			}
			if(index == -1)
				System.out.println("Boyle bir kelime bulunamadi.");
			else {
				LinkedList<String> yeniFiles = new LinkedList<String>();
				LinkedList<Integer> yeniCounts = new LinkedList<Integer>();
				for(int i=0; i<hash.hash[key%hash.hash.length].files.get(index).file.size(); i++) {
					yeniFiles.add(hash.hash[key%hash.hash.length].files.get(index).file.get(i));
					yeniCounts.add(hash.hash[key%hash.hash.length].files.get(index).count.get(i));
				}
				int numbFiles = yeniFiles.size();
				if(numbFiles < 5) {
					for(int i=0; i<numbFiles; i++) {
						int max = maxIndex(yeniCounts, yeniFiles);
						System.out.println(yeniFiles.get(max) + ": " + yeniCounts.get(max));
						yeniCounts.set(max, null);
						yeniFiles.set(max, null);
					}
				}
				else {
					for(int i=0; i<5; i++) {
						int max = maxIndex(yeniCounts, yeniFiles);
						System.out.println(yeniFiles.get(max) + ": " + yeniCounts.get(max));
						yeniCounts.set(max, null);
						yeniFiles.set(max, null);
					}
				}
			}
		}
	}
	public static int maxIndex(LinkedList<Integer> list,LinkedList<String> list2) {
		int maxIndex = -1;
		int max = -1;
		for(int i=0; i<list.size(); i++) {
			if(list.get(i) != null)
				if(list.get(i) > max) {
					maxIndex = i;
					max = list.get(i);
				}
		}
		return maxIndex;
	}
}
