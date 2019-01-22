import java.io.*;
import java.util.*;

public class SearchEngine {
	public static void main(String[] args) {

		Scanner  sc = new Scanner(System.in);
		System.out.println("Dosyalarin bulundugu klasoru giriniz :");
		String dosya = sc.nextLine();
		MyHashTable hash = klasorOku(dosya);
		dosyaYaz(hash);
		sc.close();
	}
	public static MyHashTable klasorOku(String dosya) {
		MyHashTable hash = new MyHashTable();
		File directoryPath = new File(dosya);
		for (File file : directoryPath.listFiles()) {
			Scanner input = null;
		    try{
		      input = new Scanner(new File(dosya + "/" + file.getName()),"UTF-8");
		    }catch(Exception e){
		      System.out.println("Hata: " + e.getMessage());
		    }
		    while(input.hasNextLine()){
		    	  Scanner line = new Scanner(input.nextLine());
		    	  	while (line.hasNext()) {
		              String word = line.next();
		              word = temizle(word);
		              if(!word.equals("")) {
		            	  hash.ekle(word,file.getName());
		              }
		          }
		    	  line.close();
		      }
		}
		return hash;
	}
	public static String temizle(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
	        if (  !(   c == '/' || c == '*' || c == '.'
	        		   || c == ',' || c == '\'' || c == '='
	        		   || c == '-' || c == '%' || c == '?'
	        		   || c == '!' || c == ':' || c == ';'
	        		   || c == '&' || c == '(' || c == ')'
	        		   || c == '"' || c == '<' || c == '>'
	        		   || c == '|' || c == '\\' || c == '_'
	        		   || c == '#' || c == '[' || c == ']'
	        		   || c == '@' || c == '$'
	        		   || (c >= '0' && c <= '9')
	        		  ) && ((c>='A' && c<='Z') || ((c>='a' && c<='z')))
	           ) {
	        	   sb.append(s.charAt(i));
	        }
	    }
		String r = sb.toString();
		r = r.toLowerCase();
		return r;
	}
	public static void dosyaYaz(MyHashTable hash) {
		 ObjectOutputStream out = null;
		  try {
		    out = new ObjectOutputStream(new FileOutputStream("invertedIndex"));
		   	out.writeObject(hash);
		    out.close();
		  } catch(Exception e) {
		    System.out.println("Hata: " + e.getMessage());
		  }
	}
}
