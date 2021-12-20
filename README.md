# Sorting Madness
![example workflow](https://github.com/MathewKnee/SortingMadness-I5_2_Gamma/actions/workflows/ci.yml/badge.svg)
<hr>
<br>
Aplikacja służąca do sortowania zbiorów danych różnymi algorytmami. Ma pomóc użytkownikowi w ocenie, która metoda może się najlepiej sprawdzić w konkretnych przypadkach oraz, oczywiście, w uporządkowaniu danych. Dane liczbowe są sortowane standardowo, natomiast tekstowe - leksykograficznie. Należy zaimplementować co najmniej 6 różnych metod sortowania. Aplikacja będzie dostępna poprzez GUI, a także jako zdalne API, dzięki czemu można zintegrować z istniejącymi narzędziami.
<hr>

### Jak używać
Sortowanie obiektów:
<ul>
  <li> Ścieżka: /sortingMadness
  <li> Typ żądania: POST, GET
  <li> Schemat żądania REST:
    
 ```json
    {
      "keysToSort": ["id"],
      "sortingParameters": [
        {
          "sortingAlgorithms": "quicksort",
          "maxIterations": 10,
          "directions": "ASC"
        },
        {
          "sortingAlgorithms": "quicksort",
          "maxIterations": 10,
          "directions": "DESC"
        }
      ],
      "data": [
        {
          "id": "20",
          "name": "test1"
        },
        {
          "id": "30",
          "name": "test2"
        },{
          "id": "10",
          "name": "test3"
        }
      ]
    }
```
    
  <li> Parametry:
    <ul>
      <li> keysToSort - lista kluczy według których sortowane będą obiekty
      <li> sortingParameters - parametry dla sortowania 
        <ul>
          <li> sortingAlgorithms - nazwa algorytmu (quicksort, heapsort, insertionsort, mergesort, bubblesort, cocktailsort)
          <li> maxIterations - maksymalna ilość iteracji(jeżeli mniejsza bądź równa 0 to brak limitu)
          <li> directions - kierunek sortowania ASC - rosnący lub DESC - malejący
        </ul>
      <li> data - lista obiektów typu JSON do posortowania
    </ul>
</ul>
<br>
<br>
Sortowanie prostych typów:
<ul>
  <li> Ścieżka: /sortingMadnessSimple
  <li> Typ żądania: POST, GET
  <li> Schemat żądania REST:
    
 ```json
    {
      "sortingParameters": [
        {
          "sortingAlgorithms": "quicksort",
          "maxIterations": 10,
          "directions": "ASC"
        },
        {
          "sortingAlgorithms": "quicksort",
          "maxIterations": 10,
          "directions": "DESC"
        }
      ],
      "data": [9,8,7,6,5,4,3,2,1]
    }
```
    
  <li> Parametry:
    <ul>
      <li> sortingParameters - parametry dla sortowania 
        <ul>
          <li> sortingAlgorithms - nazwa algorytmu (quicksort, heapsort, insertionsort, mergesort, bubblesort, cocktailsort)
          <li> maxIterations - maksymalna ilość iteracji(jeżeli mniejsza bądź równa 0 to brak limitu)
          <li> directions - kierunek sortowania ASC - rosnący lub DESC - malejący
        </ul>
      <li> data - lista obiektów prostego typu
    </ul>
</ul>


