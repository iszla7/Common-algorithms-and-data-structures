import java.util.Scanner;
/*Program dziala na podstawie metody "dziel i zwyciezaj" dzielac cala tablice na polowe dopoki nie zostanie osiagniety przypadek bazowy rekurencji(w tym przypadku ekurencyjne wywolanie sie konczy jak otrzymamy tablice o jednym elemencie)*/
public class divide_and_conquer_algorithm{
    public static Scanner sc=new Scanner(System.in);//globalna zmienna scanner dla wczytywania danych w main
    //funkcja do zestawiania dwoch posortowanych tablic(lewa i prawa polowa )
    public static long merge(long tab[], int l, int m, int r)
    {
        long inwersje=0;
        long L[] = new long[m-l+1];//tablica pomocnicza do przechowywania lewej czesci sklejanej tablicy
        for (int i = l; i <= m; i++)//prepisuje lewa czesc tablicy do tablicy pomocniczej
            L[i-l] = tab[i];
        int i = 0;//indeks do tablicy pomocniczej
        int j = m+1;//indeks do prawej czesci tablicy ktora zestawiamy
        int k = l;//indeks do tablicy ktora zestawiam
        while (i < m-l+1 && j <= r) {//dopoki indeksy nie wychodza poza zakres lewej (dla i) i prawej(dla j) czesci zestawianej tablicy
            if (L[i] <= tab[j]) {//jesli element z lewej podtablicy jest mniejszy lub rowny od elementu z prawej to przypisuje do tablicy na ktorej operuje na k miejsce element z pomocniczej tablicy o indeksie i i dalej zwiekszam ineksy dla pomocniczej tablicy i zestawianej
                tab[k] = L[i];
                i++;
                k++;
            }
            else {//zostal przypadek jak element z lewej podtablicy jest wiekszy od elementu z prawej wtedy dodaje do ilosci inwersji (m-l+1-i) co jest dlugoscia lewej tablicy minus indeks na ktorym jestesmy teraz (zwiekszam od razu o tyle zeby zaoszczedzic czas skoro lewa podtablica juz jest posortowana to wiem ze jak element o indeksie i jest wiekszy od elementu z prawej podtablicy o indesie j to wszystkie elementy z lewej podtablicy o wiekszych indeksach tez beda wieksze od tego elementu z prawej )prypisuje do tablicy zestawianej element z prawej podtablicy o indeksie j na miejsce k po czym zwiekszam te indeksy
                inwersje+=(m-l+1-i);
                tab[k] = tab[j];
                j++;
                k++;
            }
        }
        while (i < m-l+1) {//jak w lewej czesci tablicy zostaly sie jakies niewpisane elementy(to znaczy ze sa wieksze od wszystkich wpisanych) to wpisuje po kolei (skoro lewa podtablica jest juz posortowana) do zestawianej tablicy ciagle ziekszajc indeksy dla tych tablic
            tab[k] = L[i];
            i++;
            k++;
        }
        //nie robie powyzszej petli dla prawej czesci tablicy skoro ona juz sie zawiera w zestawianej tablicy na wlasciwych miejscach
        return inwersje;//zwracam ilosc inwersji w biezacej zestawianej tablicy
    }
    //funkcja rekurencyjna
    public static long MergeSort(long arr[], int l, int r)
    {
        long inwersje=0;
        if (l < r) { //jesli lewa granica tablica jest mniejsza od prawej(tablica sklada sie wiecej niz z jednego elementu
            int m =  (r + l) / 2;//obliczam srodek czesci tablicy na ktorej operuje
            inwersje+=MergeSort(arr, l, m);//wywoluje ta sama funkcje(rekurencja) dla lewej polowy tablicy na ktorej operuje i dodaje do ilosci inwersji ilosc inwersji w lewej podtablicy(wartosc zwracana)
            inwersje+=MergeSort(arr, m + 1, r);//wywoluje ta sama funkcje(rekurencja) dla prawej podtablicy na ktorej operuje dodaje do ilosci inwersji ilosc inwersji w prawej podtablicy(wartosc zwracana)
            inwersje+=merge(arr, l, m, r);//zestawiam posortowane polowy tablicy na ktorej operuje oraz dodaje ilosc inwersji (wartosc zwracana przez merge) ilosc inwersji obliczonych przy zestawianiu tablicy
        }
        return inwersje;//zwracam ilosc inwersji w biezacej podatblicy
    }
    //w main wczytuje dane z standardowego wejscia i wywoluje funkcje MergeSort dla kazdej wczytanej tablicy
    public static void main(String[] args) {
        int zestawy=sc.nextInt();//wczytuje ilosc zestawow
        for(int i=0;i<zestawy;i++){//wczytywanie kolejnych zestawow danych
            long inwersje = 0;//zeruje zmienna inwersje dls kadego kolejnego zestawu danych skoro jest globalna
            int m=sc.nextInt();//dla kazdego zetsawu wczytuje ilosc eleemntow w tablicy
            long tab[]=new long[m];//deklaracja tablica
            for(int h=0;h<m;h++){//wczytywanie kolejnych elementow z tablicy
                tab[h]=sc.nextInt();
            }
            System.out.println(MergeSort(tab,0,m-1));//wywoluje funkcje rekurencyjna MergeSort i drukuje wartosc zwracana na standardowe wuyscie(ilosc inwersji w tej tablicy)
        }
    }
}
/*
Dane testowe:
Wejscie:
7
3
1 10 0
6
6 3 5 4 2 1
12
1 5 4 8 10 2 6 9 12 11 3 7
7
27 13 7 5 9 1 3
5
88 16 34 55 2
18
118 76 43 12 67 90 84 56 13 17 32 64 86 23 567 41 94 48
10
99 120 3 71 45 21 18 212 435 12
Wyjscie:
2
13
22
18
7
76
24
*/