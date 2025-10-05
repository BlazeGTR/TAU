package pl.pjatk.Zad1;

import org.springframework.stereotype.Component;

@Component
public class SortController {

    private final Sortowanie sortowanie;

    public SortController(Sortowanie sortowanie){this.sortowanie = sortowanie;}

    int[] sortuj(int[] array){
        return sortowanie.bubbleSort(array);
    }
}
