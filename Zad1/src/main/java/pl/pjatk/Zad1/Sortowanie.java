package pl.pjatk.Zad1;

import org.springframework.stereotype.Service;

@Service
public class Sortowanie {

    public int[] bubbleSort(int[] arr) {
        int[] result = arr.clone();
        int n = result.length;
        for (int i = 0; i < n - 1; i++) { // Outer loop for passes
            for (int j = 0; j < n - i - 1; j++) { // Inner loop for comparisons
                if (result[j] > result[j + 1]) { // Swap if elements are in the wrong order
                    int temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }
        return result;
    }
}
