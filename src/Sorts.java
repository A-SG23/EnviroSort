import java.util.ArrayList;
import java.util.Arrays;

public class Sorts {
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(2, 7, 34, 4, 2, 7, 3, 24, 75, 24));
		System.out.println(arr);
		Sorts sort = new Sorts();
		// System.out.println(sort.gnomeSort(arr));
		System.out.println(sort.bubbleSort(arr));
		System.out.println(sort.selectionSort(arr));
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
		int size = ints.size();
		for(int i = 0; i < size - 1; i++) {
			for(int j = 0; j < size - i -1; j++) {
				if(ints.get(j) > ints.get(j + 1)) {
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
		int size = ints.size();
		for(int i = 0; i < size - 1; i++) {
			int min = i;
			for(int j = 0; j < size; j++) {
				if(ints.get(j) < ints.get(i)) min = j;
			}
			int t = arr.get(min);
			arr.set(min, i);
			arr.set(i, t);
		}
		return ints;
	}
	/*
	public ArrayList<Integer> selectionSort(ArrayList<Integer> ints) {
	
	}

	public ArrayList<Integer> quickSort(ArrayList<Integer> ints) {
	
	}*/
}
