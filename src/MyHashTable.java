import java.io.*;
import java.util.*;

public class MyHashTable implements Serializable{
	public class Node implements Serializable{
		public LinkedList<String> kelime = new LinkedList<String>();
		public LinkedList<Files> files = new LinkedList<Files>();
		public Node(String k, String f) {
			kelime.add(k);
			files.add(new Files(f));
		}
	}
	public class Files implements Serializable{
		public LinkedList<String> file = new LinkedList<String>();
		public LinkedList<Integer> count = new LinkedList<Integer>();
		public Files(String f) {
			file.add(f);
			count.add(1);
		}
	}
	Node[] hash = new Node[61843]; //prime number
	int size = 0;
	public void ekle(String k, String file) {
		int key = hash(k);
		if(hash[key%hash.length] == null) {
			hash[key%hash.length] = new Node(k,file);
			size++;
		}
		else {
			if(hash[key%hash.length].kelime.contains(k)) {
				for(int i=0; i<hash[key%hash.length].kelime.size(); i++) {
					if(hash[key%hash.length].kelime.get(i).equals(k)) {
						if(hash[key%hash.length].files.get(i).file.contains(file)) {
							for(int j=0; j<hash[key%hash.length].files.get(i).file.size(); j++) {
								if(hash[key%hash.length].files.get(i).file.get(j).equals(file))
									hash[key%hash.length].files.get(i).count.set(j, hash[key%hash.length].files.get(i).count.get(j) + 1 );
							}
						}
						else {
							hash[key%hash.length].files.get(i).file.add(file);
							hash[key%hash.length].files.get(i).count.add(1);
						}
					}
				}
			}
			else {
				hash[key%hash.length].kelime.add(k);
				hash[key%hash.length].files.add(new Files(file));
			}
		}

		if((hash.length*10.0)/7 < size)
			kapasiteArttir();
	}
	public int hash(String isim) {
		int k = 0;
		for(int i=0; i<isim.length(); i++)
			k += (i+1)*(i+1) * (int)isim.charAt(i);
		return k;
	}
	public void kapasiteArttir() {
		Node[] h = new Node[hash.length*2];
		for(Node n: hash)
			if(n != null) {
				int key = hash(n.kelime.get(0));
					h[key%h.length] = n;
			}
		hash = h;
	}
}
