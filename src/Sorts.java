import java.util.ArrayList;
import java.util.Arrays;

public class Sorts {
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			arr.add((int)(Math.random()*30)+1);
		}
		
		
		System.out.println(arr);
		Sorts sort = new Sorts();
		// System.out.println(sort.gnomeSort(arr));
		System.out.println(sort.bubbleSort(arr));
		// System.out.println(sort.selectionSort(arr));
		//System.out.println(sort.gnomeSort(arr));
	}
	
	public ArrayList<Integer> gnomeSort(ArrayList<Integer> arr) {
		ArrayList<Integer> ints = arr;
		int size = ints.size();
		int i = 0;
		while(i < size) {
			if(i == 0) i++;
			if(ints.get(i) > ints.get(i - 1)) i++;
			else {
				int t = ints.get(i);
				ints.set(i, ints.get(i - 1));
				ints.set(i - 1, t);
				i--;
			}
		}
		return ints;
	}

	public ArrayList<Integer> bubbleSort(ArrayList<Integer> arr) {
		ArrayList<Integer> ints = arr;
		int length = ints.size();
		for(int i = 0; i < length - 1; i++) {
			for(int j = 0; j < length - i - 1; j++) {
				if(ints.get(j).compareTo(ints.get(j + 1)) > 0) {
					int t = ints.get(j);
					ints.set(j, j + 1);
					ints.set(j + 1, t);
				}
			}
		}
		return ints;
	}
	
	public ArrayList<Integer> selectionSort(ArrayList<Integer> arr) {
		ArrayList<Integer> ints = arr; 
		int n = ints.size();
		 for (int i = 0; i < n-1; i++)
	        {
	            // Find the minimum element in unsorted array
	            int min_idx = i;
	            for (int j = i+1; j < n; j++)
	                if (ints.get(j) < ints.get(min_idx))
	                    min_idx = j;
	  
	            // Swap the found minimum element with the first
	            // element
	            int temp = ints.get(min_idx);
	            ints.set(min_idx, ints.get(i));
	            ints.set(i, temp);
	        }
		 return ints;
	}
	
	/*
	public ArrayList<Integer> selectionSort(ArrayList<Integer> ints) {
	
	}

	public ArrayList<Integer> quickSort(ArrayList<Integer> ints) {
	
	}*/
}
