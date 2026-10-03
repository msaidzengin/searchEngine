import java.io.Serializable;
import java.util.LinkedList;

public class MyHashTable implements Serializable {
	public class Node implements Serializable {
		public LinkedList<String> kelime = new LinkedList<String>();
		public LinkedList<Files> files = new LinkedList<Files>();

		public Node(String k, String f) {
			kelime.add(k);
			files.add(new Files(f));
		}
	}

	public class Files implements Serializable {
		public LinkedList<String> file = new LinkedList<String>();
		public LinkedList<Integer> count = new LinkedList<Integer>();

		public Files(String f) {
			file.add(f);
			count.add(1);
		}
	}

	Node[] hash = new Node[61843]; // prime number
	int size = 0;

	public void ekle(String k, String file) {
		int index = hash(k) % hash.length;
		if (hash[index] == null) {
			hash[index] = new Node(k, file);
			size++;
		} else if (hash[index].kelime.contains(k)) {
			for (int i = 0; i < hash[index].kelime.size(); i++) {
				if (hash[index].kelime.get(i).equals(k)) {
					Files entry = hash[index].files.get(i);
					if (entry.file.contains(file)) {
						for (int j = 0; j < entry.file.size(); j++) {
							if (entry.file.get(j).equals(file)) {
								entry.count.set(j, entry.count.get(j) + 1);
							}
						}
					} else {
						entry.file.add(file);
						entry.count.add(1);
					}
				}
			}
		} else {
			hash[index].kelime.add(k);
			hash[index].files.add(new Files(file));
		}

		if ((hash.length * 10.0) / 7 < size) {
			kapasiteArttir();
		}
	}

	public int hash(String isim) {
		int k = 0;
		for (int i = 0; i < isim.length(); i++) {
			k += (i + 1) * (i + 1) * (int) isim.charAt(i);
		}
		return k;
	}

	public void kapasiteArttir() {
		Node[] h = new Node[hash.length * 2];
		for (Node n : hash) {
			if (n != null) {
				int key = hash(n.kelime.get(0));
				h[key % h.length] = n;
			}
		}
		hash = h;
	}
}
