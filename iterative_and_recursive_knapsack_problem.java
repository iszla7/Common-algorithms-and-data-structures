import java.util.Scanner;//omport scannera
//program predstawia dwie funkcje rozwizajuce problem plecakowy dla podanej pojemnosci plecaka oraz elementow podanych do jego wypelnienia wypisujac elementy ktorych kombinacja moze wypelnic plecak czy BRAK jak nie istnieje takiej kombinacji
//jedna funkcja jest rekurencyjna natomiast druga jest jej symulacja iteracyjna
public class iterative_and_recursive_knapsack_problem{
    public static Scanner sc=new Scanner(System.in);//globalna zmienna scanner dla wczytywania danych w main
    public static String s;//zmienna globalna do przechowywania wyrazenia koniecznego
    //finkcja rekurencyjna
    public static boolean rec_pakuj(int pojemnosc, int[] tab, int indeks) {
        //jesli pojemnosc jest rowna zero udalo sie wypelnic plecak istniejacymi elementami wiec baza rekurencji zostala osiagnieta
        if(pojemnosc==0)
            return true;
        //jesli indeks jest rowny dlugosci tablicy lub pojemnosc jest mniejsza od 0 zwracam flase skoro nie udalo sie wypelnic plecak "idac ta sciezka"
        if(indeks==tab.length || pojemnosc<0){
            return false;
        }
        //jesli udalo sie znalezc kombinacje elementow takich ze w kombinacji z elementem o indeksie indeks wypelniaja plecak to dodaje do wyrazenia koniecznego element o indeksie indeks i zwracam true
        if(rec_pakuj(pojemnosc-tab[indeks],tab,indeks+1)) {
            s=tab[indeks]+" "+s;
            return true;
        }
        //jak nie istnieje taka kombinacja elementow ktore wraz z elementem o indeksie indeks wypelniaja plecak wywoluje rekurencyjnie funkcje dla niezmienionej pojemnosci i ide dalej po tablicy
        return rec_pakuj(pojemnosc, tab, indeks + 1);
    }
    //funkcja iteracyjna wykorzystujaca stos dla imitacji rekurencji
    public static void iter_pakuj(int pojemnosc,int[] tab){
        STACK stos=new STACK(tab.length); //tworze stos o maksymalnej pojemnosci rownej ilosci elemntow do wypelnienia plecaka
        int indeks=0;//ustawiam indeks na 0
        //petla nieskonczona do imitacji funckji rekurencyjnej
        while(true) {
            //jesli pojemnosc jest rowna zero udalo sie wypelnic plecak istniejacymi elementami to wychodze z petli skoro zostal osiagniety cel
            if(pojemnosc==0)
                break;
            /*jesli indeks jest rowny dlugosci tablicy lub pojemnosc jest mniejsza od 0:
              zdejmuje ze stosu ostatni dodany element dodaje jego wage do pojemnosci
              oraz indeksowi przypisuje indeks nastepnego ze tym elementem ktore zedjmuje
            */
            if(indeks==tab.length || pojemnosc<0){
                indeks=stos.pop();
                pojemnosc+=tab[indeks];
                indeks++;
                //jesli stos jest pusty oraz indeks nadal jest rowny dlugosci tablicy znaczy to ze nie istnieje taka kombinacja elementow ktora moze wypelnic plecak wiec wychodze z petli
                if(stos.isEmpty()&&indeks==tab.length)
                    break;
                //jesli indeks jest rowny dlugosci tablicy wykonuje powyzsze operacje(przypadek kiedy indeks byl rowny dlugosci tablicy a poprzedni dodany pzed tym elemnt do stosu jest jego poprzednikiem w tablicy)
                if(indeks==tab.length) {
                    indeks=stos.pop();
                    pojemnosc+=tab[indeks];
                    indeks++;
                }
            }
            //od pojemnosci odejmuje wage aktualnbego elementu dodaje go indeks na stos oraz zwiekszam indeks
            pojemnosc-=tab[indeks];
            stos.push(indeks);
            indeks++;
        }
        s="";//wyczyszczam zmienna globalna do przechowywania wyrazenia koniecznego
        if(pojemnosc==0){//jesli udalo sie wypelnic plecak to dodaje do wyrazenia koniecznego kolejne elementy ze stosu
            while (!stos.isEmpty()) {
                s = tab[stos.pop()] + " " + s;
            }
        }
        else //jesli nie udalo sie wypelnic plecak przypisuje zmiennej do przechowywania wyrazenia koniecznego "BRAK"
            s="BRAK";
    }
    public static void main(String[] args) {
       int zestawy=sc.nextInt();//do zmiennej zestawy wczytuje za pomoca scannera oraz newInt ilosc zestawow
       int elementy;//zmienna do przechowywania ilosci elementow ktore moga wypelnic plecak
       int pojemnosc;//zmienna do przechowywania pojemnoci plecaka
       for(int i=0;i<zestawy;i++){
           pojemnosc=sc.nextInt();//do zmiennej pojemnosc wczytuje za pomoca scannera oraz newInt pojemnosc plecaka
           elementy=sc.nextInt();//do zmiennej elementy wczytuje za pomoca scannera oraz newInt ilosc elementow do wypelnienia plecaka
           int tab[]=new int[elementy];//tworze tablice z wagami elemntow ktore moga wypelnic plecak
           // wczytuje za pomoca scannera oraz newInt kolejne wagi elementow do tablicy
           for(int h=0;h<elementy;h++){
               tab[h]=sc.nextInt();
           }
           s="";//wyczyszczam zmienna globalna do przechowywania wyrazenia koniecznego
           //jesli funkcja rekurencyjna zwrocila true(czyli istnieje kombinacja elementow z podanych ktore moga wypelnic plecak)
           if(rec_pakuj(pojemnosc,tab,0)){
               System.out.println("REC:  "+pojemnosc+" = " + s);//wypisuje na standardowe wyjscie "REC: " pojemnosc plecaka = wyrazenie konieczne z globalnej zmiennej
               iter_pakuj(pojemnosc,tab);//wywoluje funkcje iteracyjna(imitacja rwekurencyjnej)
               System.out.println("ITER: "+pojemnosc+" = " +s);//wypisuje na standardowe wyjscie "ITER: " pojemnosc plecaka = wyrazenie konieczne z globalnej zmiennej
           }
           //jesli funkcja rekurencyjna zwrocila false(czyli nie istnieje kombinacja elementow z podanych ktore moga wypelnic plecak)
           else{
               System.out.println("BRAK");//wypisuje na standardowe wyjscie "BRAK"
           }
       }
    }
}
//implementacja stosu
class STACK {
    private int maxSize;//maksymalna pojemnosc stosu
    private static int[] Elem;//tablica typu int zawierajaca elementy stosu
    private static int t;//zmienna ktora wskazuje na wierzcholek stosu

    public STACK(int size) { //konstruktor
        maxSize = size;//maksymalny rozmiar stosu
        Elem = new int[maxSize];//tworzenie tablicy
        t = maxSize;//ustawiam zmienna wskazujaca wierzcholek
    }

    public static void push(int x) {Elem[--t] = x;} //dodaje element na stos
    public int pop() {return Elem[t++];}//zdejmuje element ze stosu
    public int top() {return Elem[t];}//zwracam element znajdyjacy sie na wierzcholku stosu
    public boolean isEmpty() {return (t == maxSize);}//sprawdzam czy stos jest pusty
}
/*
Dane testowe:
Wejscie:
5
12
3
9 4 2
21
4
5 8 12 16
43
17
1 2 4 6 7 9 11 23 28 29 31 33 37 39 41 45 53
31
7
1 12 13 17 18 31 26
Wyjscie:
BRAK
REC:  21 = 5 16
ITER: 21 = 5 16
REC:  43 = 1 2 4 6 7 23
ITER: 43 = 1 2 4 6 7 23
REC:  31 = 1 12 18
ITER: 31 = 1 12 18 
 */