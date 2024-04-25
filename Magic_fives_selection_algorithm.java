import java.util.Scanner;
/*
Uzasadnienie zlozonosci:
Zlozonosc skalada sie z kilku rzeczy:
1)skoro sortujemy co najwyzej 5-elementowe podtablice to zlozonosc sortowania 5-elementowych oodtablic bedzie O(n)
2)szukanie mediany wsrod median(T(n/5),gdzie T(n) zlozonosc algorytmu dla poczatkowych danych,skoro posortowalismy 5-elementowe podtablice to wyznaczamy median z n/5 median posortowanych podtablic)
3)po znalezeniu mediany median musimy przejsc przez tablice i przerobic ja w ten sposob zeby wszystkie elementy o kluczach mniejszcyh od mediany median staly prze elementami o kluczach rownych M,a elementy o kluczach wiekszych niz M po elementach o kluczach ronych M(zlozonosc pesymistyczna tego bedzie liniowa wiec O(n))
4) elementow o kluczach wiekszych niz M bedzie nie mniej niz n/4 analogicznie dla elementow o kluczach mniejszych niz M .A wiec w pesymistycznym przypadku podtablica z elementami mniejszymi/wiekszymi bedzie rozmiaru 3n/4 a wiec tutaj mamy zlozonosc T(3n/4)
T(n)=O(n)+O(n)+T(n/5)+T(3n/4)
T(n)=1 dla n=1-przypadek bazowy
wiec przez indukcje mozna dowiesc ze zlozonosc pesymistyczna T(n)=O(n)(istotne bedzie w tym dowodzie to ze n/5+3n/4<n a wiec nie zwiekszamy zlozonosci
zlozonosc srednia bedzie tego samego rzedu.
 */
public class Magic_fives_selection_algorithm{
    public static Scanner sc=new Scanner(System.in);//zmienna do wczytywania danych ze standardowego wejscia
    public static void swap(int[] tab,int i,int j){//funkcja do zmianiania miejscami elementow z tablicy
        int temp=tab[i];//zmienna pomocnicza do przehowywania  wartosci z jednej z komorek
        tab[i]=tab[j];
        tab[j]=temp;
    }
    public static void InsertionSort(int[] tab,int L,int R){//insertion sort dla sortowania podtablic o rozmiarze mniejszym lub rownym 5
        int tmp,j;
        for(int i=L+1 ;i<R;i++){
            tmp=tab[i];//kopiujemy wyróżniony element do zmiennej tmp
            j=i-1;//zaczynamy od elementu i-1
            while(j>=L&&tmp<tab[j]){//dopóki nie wyszliśmy poza początek tablicy oraz tmp jest mniejszy niż tab[j]
                tab[j+1]=tab[j];//przesuń element j-ty element w prawo
                j--;
            }
            tab[j+1]=tmp;//wstaw wyróżniony element tmp po tab[j]
        }
    }
    public static void partition(int[] tab,int L,int R,int M) {//funckja partition dla przerobienia tablicy na taka ze po lewej stronie od elementa/elementow o kluczu M beda elementy mniejsze a po prawej wieksze
        int i = L;//ustawiam zmienna do lewego liczniku(wszystkie elementy o indeksach mniejszych niz wartosc tej zmiennej beda mniejsze od M)
        int j = R;//ustawiam zmienna do prawego liczniku(wszystkie elementy o indeksach wiekszych niz wartosc tej zmiennej beda wieksze od M)
        int curr = L;//ustawiam zmienna do biezacego elementu
        while (curr <= j) {//petla dzaila do momentu jak zmienna wskazyjaca na biezacy element nie przekroczy wartosci zmiennej wskazujacej na taki element ze wszystkie po nim sa wieksze od M(bo nie ma sensu isc dalej)
            while (curr <= j && tab[curr] < M) {//jak biezacy element jest mniejszy zamieniam go z elementem ktory stoi za najblizszym mniejszym od M elementem takim ze przed nim wszystkie elementy sa mniejsze od M
                swap(tab, i, curr);
                i++;//zwiekszam wartosc zmiennej wszkazujacej na prawy licznik skoro wiem ze przed nim wszystkie elementy sa mniejsze od M
                curr++;//zwiekszam zmienna wskazujaca na biezacy element
            }
            while (curr <= j && tab[curr] > M) {//jak biezacy element jest mniejszy zamieniam go z elementem ktory stoi przed najblizszym wiekszym od M elementem takim ze po nim wszystkie elementy sa wieksze od M
                swap(tab, curr, j);
                j--;//zmniejszam wartosc zmiennej wszkazujacej na prawy licznik skoro wiem ze po nim wszystkie elementy sa wieksze od M
            }
            while (curr <= j && tab[curr] == M)
                curr++;//zwiekszam zmienna wskazujaca na biezacy element(skoro biezacy element jest rowny M)
        }
    }
    public static int Select(int k,int[] tab,int L,int R){//funkcja opierajaca sie o algorytm magicznych piatek
        if(R==L)//jesli mam tablice 1 elementowa to zwracam ten element
            return tab[R];
        int i=L;//ustawiam lewa granice na lewa granice tablicy
        int ilosc=0;//zmienna do liczenia ilosci podtablic z co najwyzej 5 elementowych(czyli to tez bedzie ilosc median)
        while(i<=R){//do momentu jak zmienna wskazujaca na lewa granice biezacej podtablicy jest mniejsza lub rowna od konca biezacej tablicy
            InsertionSort(tab,i,Math.min(i+5,R+1));//sortuje za pomoca Insertion Sort tablicy o rozmiarze 5 lub mniej
            swap(tab,L+ilosc,(Math.min(i+5,R+1)+i)/2);//przezucam mediane tej podtablicy  na poczatek tablicy(skoro jest juz posortowany i mi to nic niue psuje)
            ilosc++;//zwiekszam ilosc posortowanych podtablic(czyli median tez)
            i=i+5;//zwiekszam zmienna wskazujaca na lewa granice biezacej podtablicy
        }
        //czyli z algorytmu podanego w skrypcie zbior srodkowych elementow bedzie na poczatku tablicy(od elementu L do L+ilosc-1 gdzie L- indeks skrajnie lewego elementu w tablicy)
        int M=Select(ilosc/2,tab,L,L+ilosc-1);//rekurencyjnie obliczam mediane median
        partition(tab,L,R,M);//przegladam tablice i zmieniam tak aby na poczatku tablicy znajdowaly sie lementy mniejsze od M a po elementach o kluczach rownych M beda wystepowac wylacznie elementy om kluczach wiekszych od M
        int lb;//zmienna do przechowywania informacja o pierwszym wystapieniu elementu o kluczu M w tablicy
        int rb=L;//zmienna do przechowywania informacja o ostatnim wystapieniu elementu o kluczu M w tablicy
        for(lb=L;lb<=R;lb++){//ide petla po calej tablicy do momentu az nie napotkam element o kluczu M
            if(tab[lb]==M){
                rb=lb;//jak ten element zostanie napotkany to ide od biezacego indeksu petla while do momentu az nie napotkam element o kluczu wiekszym niz M
                while(rb<=R&&tab[rb]==M)
                    rb++;
                break;//wychodze z petli for skoro wyznaczylam prawa i lewa granice podtablicy o elementach o kluczach rownych M
            }
        }
        if(k<=lb-L)//jesli k(indeks szukanego elementu jest mniejszy lub rowny dlugosci podtablicy  z elementami o kluczach mniejszych od M
            return Select(k,tab,L,lb-1);//rekurencyjnie przechodze do lewej podtablicy(elementy mniejszy od mediany median M)
        else if(k<=rb-L)//jesli k(indeks szukanego elementu jest mniejszy lub rowny sumie dlugosci podtablicy  z elementami o kluczach mniejszych od M oraz podtablicy  z elementami o kl;uczach rownych M(czyli indeks k jest indeks z tej 2 podtablicy gdzie klucze elementow sa rowne medianie median)
            return M;//zwracam mediane median
        return Select(k-(rb-L),tab,rb,R);//3 przypadek jak k jest wieksze od sumy dlugosci pierwszych dwoch podtablic czyli element k-ty co do wielkosci jest wsrod wiekszych od mediany median biezacej podtablicy
    }
    public static void main(String[] args){
        int zestawy=sc.nextInt();//wczytuje ilosc zestawow do zmiennej przyznaczonej do tego
        for(int i=0;i<zestawy;i++){//ide petla po ilosci zestawow
            int elementy=sc.nextInt();//wczytuje ilosc elementow do zmiennej przyznaczonej do tego
            int[] tab=new int[elementy];//tablica do przechowywania kluczy elementow
            for(int h=0;h<elementy;h++)
                tab[h]=sc.nextInt();//wczytuje kolejne elementy do tablicy
            int zapytania=sc.nextInt();//wczytuje ilosc zapytan
            int[] pytania=new int[zapytania];//tablica do zapytan
            for(int h=0;h<zapytania;h++){//ide petla po ilsci zapytan
                pytania[h]=sc.nextInt();//wczytuje kolejne zapytanie
            }
            for(int h=0;h<zapytania;h++) {//ide petla po ilsci zapytan
                if (pytania[h] > 0 && pytania[h] <= elementy)//jesli indeks szukanego co do wielkosci elemntu jest wiekszy od 0 i mniejszy lub rowny od ilosci elementow w tablicy
                    System.out.println(pytania[h] + " " + Select(pytania[h], tab, 0, tab.length - 1));//drukuje na wyjscie liczbe k(indeks szukanego co do wilkosci elementu) oraz ten element znaleziony za pomoca funkcji Select
                else
                    System.out.println(pytania[h] + " brak");//inaczej drukuje na wyjscie liczbe k(indeks szukanego co do wilkosci elementu) i slowo "brak"
            }
        }
    }
}
/*
Dane testowe:
Wejscie:
7
5
20 12 10 15 2
2
1 3
9
29 72 98 13 87 66 52 51 36
4
5 8 1 7
8
10 14 27 33 35 19 42 44
3
3 5 1
15
5 1 14 11 15 10 3 2 13 12 8 7 9 6 4
7
2 5 8 11 13 14 17
8
4 3 2 10 22 1 5 6
2
7 3
10
12 11 13 5 6 17 19 32 45 52
3
12 5 4
8
9 7 6 15 17 5 10 11
5
1 2 5 6 7 8
Wyjscie:
1 2
3 12

5 52
8 87
1 13
7 72

3 19
5 33
1 10

2 2
5 5
8 8
11 11
13 13
14 14
17 brak

7 10
3 3

12 brak
5 13
4 12

1 5
2 6
5 10
6 11
7 15
 */