import java.util.Scanner;
public class Heaps {
    public static Scanner sc=new Scanner(System.in);//zmienna do wczytywania danych ze standardowego wejscia
    public static int n;//zmienna statyczna dla biezacej ilosci roznych elementow w kolejce
    public static int p;//zmienna statyczna dla biezacej lacznej ilosci elementow w kolejce
    public static int N;//zmienna statyczna dla maksymalnej ilosci roznych elementow w kolejce
    public static int P;//zmienna statyczna dla maksymalnej lacznej ilosci elementow w kolejce
    public static void main(String[] args) {
        int zestawy=sc.nextInt();//wczytuje ilosc zestawow do zmiennej dla tego przeznaczonej
        for(int z=0;z<zestawy;z++){
            N=sc.nextInt();//wczytuje maksymalna ilosc roznych elementow w kolejce
            int[][] tab=new int[N][2];//tablica o rozmiarze maksymalnej ilosci roznych elementow w kolejce(w 0 kolumnie sam element ,w 1 kolumnie ilosc jego wystapien) do reprezentacji max-kopca
            P=sc.nextInt();//wczytuje maksymalna laczna ilosc elementow w kolejce
            sc.nextLine();//wczytuje przejscie na nowa linie
            n=0;//ustawiam biezaca ilosc roznych elementow w kolejce na 0
            p=0;//ustawiam biezaca laczna ilosc wszystkich elementow w kolejce na 0
            char c;//zmienna do wczytywania kolejnych polecen
            while(true){//petla dla wczytywania kolejnych polecen
                c=sc.next().charAt(0);//wczytuje polecenie
                if(c=='i'){//polecenie i(czyli operacja insert)
                    int nn=sc.nextInt();//wczytuje ilosc elementow do wstawiania do zmiennej nn do tego przeznaczonej
                    while(nn>0){//ide petla zmniejszajac zmienna nn tym samym wstaiajac kolejne elementy do kolejki(laczna ilosc tych elementow bedzie nn)
                        insert(tab,sc.nextInt());//wstawiam wczytany element do kolejki
                        nn--;//zmniejszam nn
                    }
                }
                else if(c=='g'){//polecenie g(usuwanie podanej przez uzytkownika ilosci elementow o aktualnie najwyzszym pryorytecie zgodnie z poleceniem)
                    int nn=sc.nextInt();//wczytuje ilosc elementow do usuwania do zmiennej nn do tego przeznaczonej
                    if(n>0){//jesli kolejka jest niepusta
                        int h=Math.min(nn,tab[0][1]);//do h wpisuje minimum pomiedzy podaba liczba elementow do usuniecia oraz ilosci elementow o najwiekszym pryorytecie(ty,m zapewniam ze wypisze ta ilosc elementow ktora usuwam)
                        System.out.println(getMax(tab,nn)+" "+h);//wypisuje element o najwyższym priorytecie oraz faktyczna liczbee usunieetych powtorzen
                    }
                    else
                        System.out.println("0 0");//jesli kolejka jest pusta drukuje 0 0 zgodnie z poleceniem
                }
                else if(c=='s'){//polecenie s(czyli sortowanie tablicy)
                    sort(tab);//sortuje tablice zgodnie z podanym poleceniem
                    for(int i=0;i<n;i++)//petla do wypisywania elementow kolejki w formacie podanym w poleceniu
                        System.out.print(tab[i][0]+" "+tab[i][1]+" ");
                    System.out.println();
                    break;//skoro s zawsze jest ostatnim poleceniem to robie break z petli
                }
            }
        }
    }
    //funckja swap sluzy do zamiany miejscami wierszy w tablicy(czyli elementow w kopcu bo tak naprawde 1 element to para -jego wartosc i ilosc wystapien)
    public static void swap(int[][] tab,int i,int j){
        int[][] temp=new int[1][2];//tablica pomocnicza do przechowywania jednego elementu kopca(para wartosc i ilosc wystapien)
        //zamieniam wartosci miejscami
        temp[0][0]=tab[i][0];
        temp[0][1]=tab[i][1];
        tab[i][0]=tab[j][0];
        tab[i][1]=tab[j][1];
        tab[j][0]=temp[0][0];
        tab[j][1]=temp[0][1];
    }
    //funkcja pomocnicza do wyszukiwania elementa w kopcu(jesli ten element jest w kopcu to zwraca jego indeks w tablicy jesli nie to liczbe -1)
    public static int search(int[][] tab,int x){
        for(int i=0;i<n;i++){
            if(tab[i][0]==x) {
                return i;
            }
        }
        return -1;
    }
    //funkcja insert do polecenia i
    public static void insert(int[][] tab,int x){
        if(p<P){//jesli laczna ilosc elelementow w kolejce nie przekracza i nie jest rowna maksymalnej ilosci elementow podanej przez uzytkownika
            int position = search(tab, x);//szukam element ktory wstawiam w kolejce
            if (position>=0){//jesli ten element jest juz w kolejce
                tab[position][1]++;//zwiekszam ilosc jego wystapien
                upheap(tab, position);//przesiwam w gore(skoro pryorytet tego elementa sie zwiekszyl)
            }
            else{//jesli tego elementu jesze nie ma w kolejce
                if (n==N){//sprawdzam czy biezaca ilosc roznych elementow w kolejce nie jest rowna maksymalnej ilosci roznych elementow w kolejce podanej przez uzytkownika
                    return;//wychodze z funkcji
                }
                //dopisuje lisc na koncu i przesiewam w gore
                tab[n][0] = x;
                tab[n][1] = 1;
                upheap(tab, n);
                n++;//zwiekszam biezaca ilosc roznych elementow w kolejce
            }
            p++;//zwiekszam biezaca laczna ilosc elementow w kolejce
        }
    }
    //funkcja do polecenia g ktora usuwa m elementow o najwiekszym pryorytecie z kolejki(lub jak m przekracza ta ilosc to usuwam wszystkie)
    public static int getMax(int[][] tab,int m){
        int max=tab[0][0];//zapamietuje element o najwiekszym pryorytecie z kolejki(skoro wszystkie te elementy moga zostac usuniete)
        if(m<tab[0][1]){
            tab[0][1]-=m;//zmniejszam ilosc wystapien korzenia(elementa o najwyzszym pryorytecie) o m(ilosc elementow do usuniecia podana przez uzytkownika)
            downheap(tab,0);//przesiwam w dol(skoro pryorytet korzenia sie zmienil)
            p-=m;//zmniejszam laczna ilosc elementow w kolejce o ilosc usuwanych elementow
        }
        else{
            n--;//zmniejszam biezaca ilosc roznych elementow w kolejce
            p-=tab[0][1];//zmniejszam laczna ilosc elementow w kolejce o ilosc usuwanych elementow
            //wstawiam ostatni lisc w miejsce korzenia i przesiewam w dol(skoro teraz pryorytet korzenia sie zmienil)
            tab[0][0]=tab[n][0];
            tab[0][1]=tab[n][1];
            downheap(tab,0);
        }
        return max;//zwracam zapamietany element o najwyzszym pryorytecie
    }
    //funkcja sortujaca dla polecenia s
    public static void sort(int[][] tab){
        int l=n;//zapamietuje ilosc elementow w kopcu(zeby pozniej przy wypisywaniu wiedziec ilosc elementow)
        //usuwam kolejne elelementy z kopca
        while(n>0){
            swap(tab,0,n-1);//zamieniam miejscami(tym samym wypycham najwiekszy elelemnt zawszd na koniec i mam w koncu posortowana rosnaco tablica)
            n--;//zmniejszam ilosc elementow w kopcu
            downheap(tab,0);//przesiwam w dol korzen(skoro jego pryorytet sie zmienil i stal sie mniejszy)
        }
        n=l;//znow nadpisuje ilosc elelementow w kopcu do n
    }
    //funkcja sprawdzajaca czy element o wartosci x1 i ilosci wystapien n1 ma wiekszy pryorytet niz element o wartosci x2 i ilosci wystapien n2
    public static boolean isLarger(int x1,int n1,int x2,int n2){
        if(n1>n2)//sprawdzam najpierw czy ilosc wystapien jest wieksza i jak jest to zwracam true
            return true;
        else if(n1<n2)//2 mozliwosc jak ilosc wystapien jest mniejsza wtedy zwracam false
            return false;
        //zostaje mozliwosc jak elementy maja tyle samo wystapien wtedy sprawdzam czy element ma wieksza wartosc i wtedy zwracam true
        if(x1>x2)
            return true;
        return false;//jak jest tyle samo wystapien ale wartosc jest mniejsza to zgodnie z poleceniem taki element jest mniejszy wiec zwracam false
    }
    //przesiewania kopca w dol
    public static void downheap(int[][] tab,int k){
        int child;//zmienna do indeksu dziecka o wyzszym pryorytecie
        //zapamietuje element o indeksie k zapisujac do pomocniczj tablicy temp do tego przeznaczonej
        int[] temp=new int[2];
        temp[0]=tab[k][0];
        temp[1]=tab[k][1];
        while(k<n/2){
            child=2*k+1;//przechodze do lewego dziecka
            if(child<n-1&&isLarger(tab[child+1][0],tab[child+1][1],tab[child][0],tab[child][1]))//wybieram wieksze dziecko(czyli wiekszy nastepnik)
                child++;
            if(!isLarger(tab[child][0],tab[child][1],temp[0],temp[1]))//jesli warunek kopca jest spelniony wychodze z petli
                break;
            //przesuwam aktualny element do gory
            tab[k][0]=tab[child][0];
            tab[k][1]=tab[child][1];
            k=child;
        }
        //wstawiam element k na jego wlasciwe miejsce
        tab[k][0]=temp[0];
        tab[k][1]=temp[1];
    }
    //przesiewania kopca w gore po sciezce od wezla k do korzenia
    public static void upheap(int[][] tab,int k){
        int parent=(k-1)/2;//indeks "rodzica" czyli poprzednika elementa o indeksie k
        //zapamietuje element o indeksie k zapisujac do pomocniczj tablicy temp do tego przeznaczonej
        int[] temp=new int[2];
        temp[0]=tab[k][0];
        temp[1]=tab[k][1];
        while(k>0&&!isLarger(tab[parent][0],tab[parent][1],temp[0],temp[1])){//domomentu jak k nie jest korzeniem oraz poprzednik k ma mniejszy pryorytet niz wezel od ktorego zaczelam przesiewac(zapamietany do temp)
            //przenosze wezel w dol
            tab[k][0]=tab[parent][0];
            tab[k][1]=tab[parent][1];
            k=parent;
            parent=(k-1)/2;//przechodze do "rodzica" czyli poprzednika
        }
        //wstawiam element k na jego wlasciwe miejsce
        tab[k][0]=temp[0];
        tab[k][1]=temp[1];
    }
}
