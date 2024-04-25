import java.util.Scanner;
/*Program dziala w ten sposob ze przesyla prawa granice podanego zakresu powiekszona o 1(czyli szuka pierwszego wystapienia elementa najblizszego do
elementa wiekszego o 1 od prawej granicy zeby miec indeks+1 jako zwrocona wartosc funkcji gdzie indeks jest pozycja ostatniego wystapienia najblizszego do
prawej granicy elementu) i odejmuje od tego pierwsze wystapienie najblizszego elementa do lewej granicy .Z tego mam pozycje pierwszego i ostatniego wystepujacych w
tym zakresie elementow.Odejmuje i mam ilosc elementow w tym zakresie skoro tablica
jest posortowana.Zeby znalezc ilosc wystapien roznych elementow w tablicy i jak element
jest wiekszy od poprzedniego(a skoro tablica jest posortowana moze byc
wiekszy lub rowny) zwiekszam zmienna ktora odpowiada za ilosc tych roznych elementow
w tablice.Petle zaczynam od i=1 bo na 0 miejscu w tablice zawsze stoi nie spotykany do
 tego momentu element skoro jest 1 w tej tablicy,a wiec jako poczatkowa wartosc zmiennej
 ktora odpowiada za rozne wystapienia jest 1.  */
class Binary_search_first_occurrence {
    /*Zmienne:
    long[] TAB sluzy do przechowywania
    tablic z zeznaniami podatkowymi(zdefiniowalam
    globalnie zeby nie przesylac do metody SearchBinFirst
     skoro w poleceniu bylo powiedziane ze ta metoda ma 1 argument
     typu int)
     int different_numbers sluzy do przechowywania
    obliczonej ilosci roznych zeznan na liscie podatkowej
    int n-zmienna sluzy do przechowywania ilosci zestawow
    int citizens-zmiennna przechowywuje ilosc elemento w tablicy TAB(ilosc zeznan podatkowych na liscie)
    int ranges-ilosc podawanych zakresow
    long left-lewa granica zakresu
    long right-prawa granica zakresu */
    public static long[] TAB;
    public static long left,right;
    public static int different_numbers;
    public static Scanner sc = new Scanner(System.in);
    /*metoda SearchBinFirst jest troche przerobionym
    binary searchem.Rozni sie tym ze szuka pierwszego wystapienia takiego elementu
     ze on nalezy do TAB oraz jest najblizszy do x.Zmienilam tylko jeden przypadek:
     jak metoda trafi na element wiekszy lub rowny x to jeszcze sprawdza czy nie wyszla poza prawa granice zakresu oraz
      czy poprzedni element w tablicy
     jest mniejszy od x(w przypadku jak znaleziony element stoi na 0 miejscu w TAB to tez to nam pasuje
      bo nie istnieje w TAB element mniejszy od znalezionego) */
    public static int SearchBinFirst(long x) {
        int low = 0;
        int upp = TAB.length-1;
        int curr;
        while(low <= upp){
            curr = (low+upp)/2;
            if((curr==0 || TAB[curr-1]<x) && TAB[curr]>=x)
                return curr;
            else if(TAB[curr] < x)
                low = curr + 1;
            else
                upp = curr - 1;
        }
        return TAB.length;
    }
    /*metoda diff_elements() oblicza ilosc
    roznych zeznan na liscie podatkowej(skoro lista ta jest posortowana
     rosnaco to sprawdzam czy poprzedni element nie jest rowny temu elementowi
     zeby dla kazdego roznego elementu listy zeznan podatkowych dodawac do
     different_numbers tylko 1 dla kazdego elementu.petla sie zaczyna od indeksu tablicy 1(bo oczywiscie na 0 miejscu tablicy jest jakis nowy element
     skoro nie ma zadnego elementu przed nim) */
    public static void diff_elements() {
        different_numbers=1;
        for(int i=1;i<TAB.length;i++){
            if(TAB[i]>TAB[i-1])
                different_numbers++;
        }
        System.out.println(different_numbers);
    }
    public static void main(String[] args) {
        int n=sc.nextInt(); //za pomoca scannera oraz nextInt() wczytuje ilosc zestawow
        for(int i=0;i<n;i++){
            int citizens=sc.nextInt();//za pomoca scannera oraz nextInt() wczytuje ilosc elementow w tablicy(lista zeznan)
            TAB= new long[citizens];
            for(int h=0;h<citizens;h++) {
                TAB[h] = sc.nextLong();//za pomoca scannera oraz nextLong() wczytuje wczytuje elementy tablicy TAB
            }
            int ranges=sc.nextInt();//za pomoca scannera oraz nextInt() wczytuje ilosc podawanych zakresow
            for(int h=0;h<ranges;h++) {
                left=sc.nextLong();//za pomoca scannera wczytuje lewa granice podanego zakresu
                right=sc.nextLong()+1;//za pomoca scannera wczytuje prawa granice podanego zakresu i dodaje 1<zeby przesylac do metody SearchBinFirst wieksza cyfre
                if(left<=right)
                    System.out.println(SearchBinFirst(right)-SearchBinFirst(left)); //wypisuje dlugosc fragmentu pomiedzy pierwszym wystapieniem najblizszego do lewej granicy zakresu elementu oraz pierwszym wystapieniem najblizszego do (prawej granicy + 1) elementu
                else
                    System.out.println("0"); //jesli lewa granica jest wieksza od prawej to wypisuje 0
            }
            diff_elements();// w ostatnim wierszu wypisze ilosc roznych zeznan na liscie podatkow
        }
    }
}
/*
Dane testowe:
Wejscie:
1
5
-5 1 3 6 18
3
1 1
3 6
18 -5
Wyjscie:
1
2
0
5
 */