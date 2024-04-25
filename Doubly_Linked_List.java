import java.util.Scanner;//import scannera
/*ponizszy program przedstawia zaimplementowana liste jednokierunkowa ktorej wezlem jest lista dwukierunkowa i dostarcza metod
laczenia dwoch elementow listy jednokierunkowej(laczenie 2 list dwukierunkowych)
usuwania pierwszego/ostatniego elementu listy dwukierunkowej i tworzenie z niego nowego elementu listy jednokierunkowej
wyswietlania elementow listy jednokierunkowej
wyswietlania elementow pewnej listy dwukierunkowej(o podanych danych)
odwrocenia pewnej listy dwukierunkowej(o podanym znaczeniu) w czasie stalym
tworzenia nowej listy dwukierunkowej i dodawanie tej listy jako nowego wezla listy jednokierunkowej
wstawiania nowego elementu o pownej nazwie do pewnej listy dwukierunkowej(o podanych danych) na komnice/pocztek
*/
public class Doubly_Linked_List {//glowna klasa w ktora odbywa sie wczytywanie danych oraz wywolanie wszystkich funckji
    public static Scanner sc = new Scanner(System.in);//globalna zmienna scanner dla wczytywania danych w main
    public static void main(String[] args) {
        int zestawy=sc.nextInt();//do zmiennej zestawy wczystuje za pomoca scannera i newInt ilosc zestawow
        int polecenia;//zmienna do przechowywania ilosc polecen w kazdym zestawie
        String[] polecenie;//zmienna do przechowywania wczytanego polecenia
        ZABAWA zabawa=new ZABAWA();//robie nowa gre
        for(int i=0;i<zestawy;i++){//petla idzie od 0 do ilosci zestawow
            zabawa.first=null;//zeruje dla kazdego zestawu pierwszy elment w liscie zabawa
            polecenia=sc.nextInt();//wczytuje ilosc polecen w kazdym zestawie
            sc.nextLine();//pomijam znak nowej linii
            for(int h=0;h<polecenia;h++){//petla idzie od 0 do ilosci polecen
                polecenie=sc.nextLine().split(" ");//wczytuje do zmiennej typu String[] polecenie i za pomoca funkcji split rozdzielam po spacjach
                if(polecenie[0].equals("New")){zabawa.New(polecenie[1],polecenie[2]);}//jesli pierwsze slowo w poleceniu jest New to wywoluje odpowiadajaca funkcje
                else if(polecenie[0].equals("TrainsList")){zabawa.TrainsList();}//jesli pierwsze slowo w poleceniu jest Trainslist to wywoluje odpowiadajaca funkcje
                else if(polecenie[0].equals("InsertFirst")){zabawa.InsertFirst(polecenie[1],polecenie[2]);}//jesli pierwsze slowo w poleceniu jest InsertFirst to wywoluje odpowiadajaca funkcje
                else if(polecenie[0].equals("InsertLast")){zabawa.InsertLast(polecenie[1],polecenie[2]);}//jesli pierwsze slowo w poleceniu jest InsertLast to wywoluje odpowiadajaca funkcje
                else if(polecenie[0].equals("Display")){zabawa.Display(polecenie[1]);}//jesli pierwsze slowo w poleceniu jest Display to wywoluje odpowiadajaca funkcje
                else if(polecenie[0].equals("Reverse")){zabawa.Reverse(polecenie[1]);}//jesli pierwsze slowo w poleceniu jest Reverse to wywoluje odpowiadajaca funkcje
                else if(polecenie[0].equals("Union")){zabawa.Union(polecenie[1],polecenie[2]);}//jesli pierwsze slowo w poleceniu jest Union to wywoluje odpowiadajaca funkcje
                else if(polecenie[0].equals("DelLast")){zabawa.DelLast(polecenie[1],polecenie[2]);}//jesli pierwsze slowo w poleceniu jest DelLast to wywoluje odpowiadajaca funkcje
                else if(polecenie[0].equals("DelFirst")){zabawa.DelFirst(polecenie[1],polecenie[2]);}//jesli pierwsze slowo w poleceniu jest DelFirst to wywoluje odpowiadajaca funkcje
            }
        }
    }
}
class ZABAWA {
    public POCIAG first;//pierwszy element listy

    public ZABAWA(){}//konstruktor

    public void New(String np, String nw) {//funkcja dodajaca pociagi
        POCIAG p = new POCIAG(np, nw);//tworze nowy pociag o nazwie np zawieracjacy jeden wagon o nazwie nw
        if (first != null) {p.next = first;}//jesli pierwszy element listy nie wskazuje na null(co znaczy ze to nie jest pierwsze wywolanie funkcji New) to przypisuje p.next(referencja do nastepnika) first
        else {p.next = null;}//jesli pierwszy element listy wskazuje na null(co znaczy ze to jest pierwsze wywolanie funkcji New) to przypisuje p.next(referencja do nastepnika) null
        first = p;//przestawiam 1 element na stworzony pociag
    }
    public void Reverse(String s){ //odracam pociag zmieniajac miejscami first i last
        //szukam pociag o nazwie s
        POCIAG p = first; //ustawiam sie na poczatku listy
        while (!(p.nazwa).equals(s)) //ide petla dopoki nazwa bieazacego pociagu nie nedzie rowna podanej nazwie
            p = p.next;//przestawiam sie na nastepny pociag
        WAGON temp=p.first;
        p.first=p.last;
        p.last=temp;
    }
    public void InsertFirst(String s,String n){//funkcja ktora wstawia na poczatek pociagu nowy wagon o nazwie n(argument funkcji)
        //szukam pociag o nazwie s
        POCIAG p = first; //ustawiam sie na poczatku listy
        while (!(p.nazwa).equals(s)) //ide petla dopoki nazwa bieazacego pociagu nie nedzie rowna podanej nazwie
            p = p.next;//przestawiam sie na nastepny pociag
        if(p.first.prev==null){// poprzednik pierwszego wagonu jest rowny null("poczatek pociagu nie jest odwrocony")
            WAGON w=new WAGON(n,p.first,null);//tworze wagon o nazwie n poprzedniku null i nastepniku first
            p.first.prev=w;//przestawiam poprzednik first na nowy wagon
            p.first=w;//przestawiam first na nowy wagon
        }
        else{//nastepnik pierwszego wagonu jest rowny null("poczatek pociagu jest odwrocony")
            WAGON w=new WAGON(n,null,p.first);//tworze wagon o nazwie n nastepniku null i poprzedniku first
            p.first.next=w;//przestawiam nastepnik first na nowy wagon
            p.first=w;//przestawiam first na nowy wagon
        }
    }
    public void InsertLast(String s,String n){
        //szukam pociag o nazwie s
        POCIAG p = first; //ustawiam sie na poczatku listy
        while (!(p.nazwa).equals(s)) //ide petla dopoki nazwa bieazacego pociagu nie nedzie rowna podanej nazwie
            p = p.next;//przestawiam sie na nastepny pociag
        if(p.last.next==null){// nastepnik ostatniego wagonu jest rowny null("koniec pociagu nie jest odwrocony")
            WAGON w=new WAGON(n,null,p.last);//tworze wagon o nazwie n nastepniku null i poprzedniku last
            p.last.next=w;//przestawiam nastepnik last na nowy wagon
            p.last=w;//przestawiam last na nowy wagon
        }
        else{//poprzednik ostatniego wagonu jest rowny null("koniec pociagu jest odwrocony")
            WAGON w=new WAGON(n,p.last,null);//tworze wagon o nazwie n poprzedniku null i nastepniku last
            p.last.prev=w;//przestawiam poprzednik last na nowy wagon
            p.last=w;//przestawiam last na nowy wagon
        }
    }
    public void Display(String s){//funkcja do wyswietlenia pociagu
        //szukam pociag o nazwie s
        POCIAG p = first; //ustawiam sie na poczatku listy
        while (!(p.nazwa).equals(s)) //ide petla dopoki nazwa bieazacego pociagu nie nedzie rowna podanej nazwie
            p = p.next;//przestawiam sie na nastepny pociag
        System.out.print(p.nazwa+": ");//drukuje naswe pociagu oraz dwukropek i spacje
        boolean flag=true;//zmienna do chodzenia po pociagu(do prypadka zapetlania w pociagu jak next pociaga rowna sie pociagu dla ktorego next jest tym pociagiem)
        WAGON w=p.first;//ustawiam sie na pierwszy wagon
        WAGON ln=null;//zmienna do poprzednika wagona ustawiam na null
        while (w != null) {//dopoki biezacy pociag nie jest null
            if(w.next==ln){//jesli nastepnik pociagu rowna sie poprzedniemu wypisanemu zmieniam flag na false(zeby isc po prev'ach)
                flag=false;
            }
            if(w.prev==ln){//jesli npoprzednik pociagu rowna sie poprzedniemu wypisanemu zmieniam flag na true((zeby isc po next'ach)
                flag=true;
            }
            System.out.print(w.nazwa+" ");//drukuje nazwe pociagu wraz ze spacja
            ln=w;//prestawiam poprzednik na ten pociag
            if(flag==true) {//dla flag=true przestawiam sie na nastepnik biezacego pocuagu
                w = w.next;
            }
            else {//dla flag=false przestawiam sie na poprzednik biezacego pociagu
                w = w.prev;
            }
        }
        System.out.println();//drukuje znak nowej linii
    }
    public void TrainsList() {//funkcja do wypisywania pociagopw bioracych aktualnie udzial w grze
        POCIAG p = first;//ustawiam sie na poczatku listy
        System.out.print("Trains:");//drukuje na standardowe wyjscie"Trains:"
        while (p != null) {//dopoki biezacy pociag nie jest null drukuje spacje oraz nazwe biezacego pociagiu oraz przestawiam sie na nastepny
            System.out.print(" ");
            System.out.print(p.nazwa);
            p = p.next;
        }
        System.out.println();//drukuje znak nowej linii
    }
    public void Union(String t1,String t2){
        POCIAG p1 = first;//ustawiam sie na pierwszy pociag
        POCIAG prev_p2 = first;//ustawiam sie na pierwszy pociag
        POCIAG p2=first;//ustawiam sie na pierwszy pociag
        if((first.nazwa).equals(t2)) {//jesli pociag o nazwie t2 jest pierwszy w zabawie aktualnie to prev_p2(poprzednik pociagu o nazwie t2) ustawiam na null i dalej wyszukuje pociag o nazwie t1
            prev_p2 = null;
            while (!(p1.nazwa).equals(t1))
                p1=p1.next;
        }
        else{//jak pociag wskazawany first'em nie jest pociagiem o nazwie t2
            while (!(p1.nazwa).equals(t1)||!(prev_p2.next.nazwa).equals(t2)) {//ide petla do tej pory jak nazwa biezacego pociagu p1 nie jest rowna t1 oraz nazwa nastepnego dla biezacego poprzednika p2 nie jest rowna t2
                if(!(prev_p2.next.nazwa).equals(t2))//jesli nazwa nastepnika biezacego pociagu prev_p2 nie jest rowna t2 przestawiam prev_p2 na nastepny
                    prev_p2 = prev_p2.next;
                if(!(p1.nazwa).equals(t1))//jesli nazwa biezacego pociagu p1 nie jest rowna t1 przestawiam p1 na nastepny
                    p1=p1.next;
            }
            p2 = prev_p2.next;//przypisuje p2 nastepnik prev_p2
        }
        if(p1.last.next==null){//jesli nastepnik ostatniego wagonu jest null dla pociagu o nazwie t1("koniec nie jest odwrocony")
            if(p2.first.prev==null){//jesli poprzednik pierwszego wagonu pociagu o nazwie t2 jest ull("poczatek t2 nie jest odwrocony")
                p1.last.next=p2.first;//przestawiam nastepnik dla ostatniego wagonu pociagu o nazwie t1 na 1 wagon pociagu o nnazwie t2
                p2.first.prev=p1.last;//przestawiam poprzednik dla pierwszego wagonu pociagu o nazwie t2 na ostatni wagon pociagu o nazwie t1
                p1.last=p2.last;//przestawiam ostatni wagon pociagu o nazwie t1 na ostatni wagon pociagu o nazwie t2
            }
            else{//jesli nastepnik pierwszego wagonu pociagu o nazwie t2 jest ull("poczatek t2 jest odwrocony")
                p1.last.next=p2.first;//przestawiam nastepnik dla ostatniego wagonu pociagu o nazwie t1 na 1 wagon pociagu o nnazwie t2
                p2.first.next=p1.last;//przestawiam nastepnik dla pierwszego wagonu pociagu o nazwie t2 na ostatni wagon pociagu o nazwie t1
                p1.last=p2.last;//przestawiam ostatni wagon pociagu o nazwie t1 na ostatni wagon pociagu o nazwie t2
            }
        }
        else{//jesli poprzednik ostatniego wagonu jest null dla pociagu o nazwie t1("koniec jest odwrocony")
            if(p2.first.prev==null){//jesli poprzednik pierwszego wagonu pociagu o nazwie t2 jest ull("poczatek t2 nie jest odwrocony")
                p1.last.prev=p2.first;//przestawiam poprzednik dla ostatniego wagonu pociagu o nazwie t1 na 1 wagon pociagu o nnazwie t2
                p2.first.prev=p1.last;//przestawiam poprzednik dla pierwszego wagonu pociagu o nazwie t2 na ostatni wagon pociagu o nazwie t1
                p1.last=p2.last;//przestawiam ostatni wagon pociagu o nazwie t1 na ostatni wagon pociagu o nazwie t2
            }
            else{//jesli nastepnik pierwszego wagonu pociagu o nazwie t2 jest ull("poczatek t2 jest odwrocony")
                p1.last.prev=p2.first;//przestawiam poprzednik dla ostatniego wagonu pociagu o nazwie t1 na 1 wagon pociagu o nnazwie t2
                p2.first.next=p1.last;//przestawiam nastepnik dla pierwszego wagonu pociagu o nazwie t2 na ostatni wagon pociagu o nazwie t1
                p1.last=p2.last;//przestawiam ostatni wagon pociagu o nazwie t1 na ostatni wagon pociagu o nazwie t2
            }
        }
        //usuwam pociag o nazwie t2
        if (p2 == first) {//jesli p2 rownal sie first przestawiam first na jego nastepnik
            first = first.next;
        } else { //jak p2 nie byl rowny first przestawiam nastepnik poprzednika p2(prev_p2) na nastepnik p2
            prev_p2.next = p2.next;
        }
    }
    public void DelFirst(String t1,String t2){
        POCIAG prev_p1 = first;
        POCIAG p1 = first;
        if((first.nazwa).equals(t1)) {//jesli pociag o nazwie t1 jest pierwszy w zabawie aktualnie to prev_p1(poprzednik pociagu o nazwie t1) ustawiam na null
            prev_p1 = null;
        }
        else{//inaczej wyszukuje poprzednik p1 i dalej przestawiam p1 na nastepnik wyszukanego prev_p1
            while ( !(prev_p1.next.nazwa).equals(t1)) {
                prev_p1=prev_p1.next;
            }
            p1 = prev_p1.next;
        }
        if(p1.first.prev==null && p1.first.next==null) {//jesli pociag o nazwie t1 sklada sie z 1 wagonu
            //usuwam pociag o nazwie t1
            if(p1==first){//jesli ten pociag jest 1 w biezacej zabawie
                first=first.next;//przestawiam first na jego nastepnik
            }
            else{
                prev_p1.next=p1.next;//przestawiam nastepnik poprzednika pociagu o nazwie t1 na nastepnik tego pociagu
            }
            New(t2, p1.first.nazwa);//tworze nowy pociag o nazwie t2 zawierajacy jeden wagon o nazwie pierwszego wagonu z pociagu o nazwie t1
        }
        else{//jesli pociag o nazwie t1 sklada sie wiecej niz z 1 wagonu
            New(t2, p1.first.nazwa);//tworze nowy pociag o nazwie t2 zawierajacy jeden wagon o nazwie pierwszego wagonu z pociagu o nazwie t1
            if(p1.first.prev==null){//jesli poprzednik pierwszego wagonu pociagu o nazwie t1 jest rowny null("poczatek pociagu nie jest odwrocony")
                if(p1.first.next.prev==p1.first){//jesli poprzednik drugiego wagonu rowna sie pierwszemu wagonu pocigu o nazwie t1
                    p1.first.next.prev=null;//przestawiam poprzednik dla nastepnika first pociagu o nazwie t1 na null
                    p1.first=p1.first.next;//przestawiam dla pociagu o nazwie t1 first na jego nastepnik
                }
                else{//jesli nastepnik drugiego wagonu rowna sie pierwszemu wagonu pocigu o nazwie t1(jest zapetlenie)
                    p1.first.next.next=null;//przestawiam nastepnik dla nastepnika first pociagu o nazwie t1 na null
                    p1.first=p1.first.next;//przestawiam dla pociagu o nazwie t1 first na jego nastepnik
                }
            }
            else{//jesli nastepnik pierwszego wagonu pociagu o nazwie t1 jest rowny null("poczatek pociagu jest odwrocony")
                if(p1.first.prev.next==p1.first) {//jesli nastepnik drugiego wagonu rowna sie pierwszemu wagonu pocigu o nazwie t1
                    p1.first.prev.next = null;//przestawiam nastepnik dla poprzednika first pociagu o nazwie t1 na null
                    p1.first=p1.first.prev;//przestawiam dla pociagu o nazwie t1 first na jego poprzednik
                }
                else {//jesli poprzednik drugiego wagonu rowna sie pierwszemu wagonu pociagu o nazwie t1(jest zapetlenie)
                    p1.first.prev.prev = null;//przestawiam poprzednik dla poprzednika first pociagu o nazwie t1 na null
                    p1.first=p1.first.prev;//przestawiam dla pociagu o nazwie t1 first na jego poprzednik
                }
            }
        }
    }
    public void DelLast(String t1,String t2){
        POCIAG prev_p1 = first;
        POCIAG p1 = first;
        if((first.nazwa).equals(t1)) {//jesli pociag o nazwie t1 jest pierwszy w zabawie aktualnie to prev_p1(poprzednik pociagu o nazwie t1) ustawiam na null
            prev_p1 = null;
        }
        else{//inaczej wyszukuje poprzednik p1 i dalej przestawiam p1 na nastepnik wyszukanego prev_p1
            while ( !(prev_p1.next.nazwa).equals(t1)) {
                prev_p1=prev_p1.next;
            }
            p1 = prev_p1.next;
        }
        if(p1.last.prev==null && p1.last.next==null){//jesli pociag o nazwie t1 sklada sie z 1 wagonu
            //usuwam pociag o nazwie t1
            if(p1==first){//jesli ten pociag jest 1 w biezacej zabawie
                first=p1.next;//przestawiam first na jego nastepnik
            }
            else{
                prev_p1.next=p1.next;//przestawiam nastepnik poprzednika pociagu o nazwie t1 na nastepnik tego pociagu
            }
            New(t2, p1.last.nazwa);//tworze nowy pociag o nazwie t2 zawierajacy jeden wagon o nazwie ostatniego wagonu z pociagu o nazwie t1
        }
        else{//jesli pociag o nazwie t1 sklada sie wiecej niz z 1 wagonu
            New(t2, p1.last.nazwa);//tworze nowy pociag o nazwie t2 zawierajacy jeden wagon o nazwie ostatniego wagonu z pociagu o nazwie t1
            if(p1.last.next==null){//jesli nastepnik ostatniego wagonu pociagu o nazwie t1 jest rowny null("poczatek pociagu nie jest odwrocony")
                if(p1.last.prev.next==p1.last) {//jesli nastepnik przedostatniego wagonu rowna sie ostatniemu wagonu pociagu o nazwie t1
                    p1.last.prev.next = null;//przestawiam nastepnik dla poprzednika last(przedostatniego wagonu) pociagu o nazwie t1 na null
                    p1.last=p1.last.prev;//przestawiam dla pociagu o nazwie t1 last na jego poprzednik
                }
                else {//jesli poprzednik przedostatniego wagonu rowna sie ostatniemu wagonu pociagu o nazwie t1(jest zapetlenie)
                    p1.last.prev.prev = null;//przestawiam poprzednik dla poprzednika last(przedostatniego wagonu) pociagu o nazwie t1 na null
                    p1.last=p1.last.prev;//przestawiam dla pociagu o nazwie t1 last na jego poprzednik
                }
            }
            else{//jesli poprzednik ostatniego wagonu pociagu o nazwie t1 jest rowny null("poczatek pociagu jest odwrocony")
                if(p1.last.next.next==p1.last) {//jesli nastepnik przedostatniego wagonu rowna sie ostatniemu wagonu pociagu o nazwie t1(jest zapetlenie)
                    p1.last.next.next = null;//przestawiam nastepnik dla nastepnika last(przedostatniego wagonu) pociagu o nazwie t1 na null
                    p1.last=p1.last.next;//przestawiam dla pociagu o nazwie t1 last na jego nastepnik
                }
                else {//jesli poprzednik przedostatniego wagonu rowna sie ostatniemu wagonu pociagu o nazwie t1
                    p1.last.next.prev = null;//przestawiam poprzednik dla nastepnika last(przedostatniego wagonu) pociagu o nazwie t1 na null
                    p1.last=p1.last.next;//przestawiam dla pociagu o nazwie t1 last na jego nastepnik
                }
            }
        }
    }
}
class WAGON{
    public String nazwa;//nazwa wagonu
    public WAGON next;//poprzednik wagonu
    public WAGON prev;//nastepnik wagonu
    public WAGON(String n,WAGON ne,WAGON pr){nazwa=n;next=ne;prev=pr;}//konstruktor dla wagonu przypisuje do nazwy argument n a do nastepnika argument ne i do poprzednika pr
}
class POCIAG{
    String nazwa;//nazwa pociagu
    public POCIAG next;//nastepnik pociagu
    public WAGON first;//pierwszy wagon pociagu
    public WAGON last;//ostatni wagon pociagu
    public POCIAG(String n,String wn){nazwa=n;WAGON w=new WAGON(wn,null,null);first=w;last=first;}//konstruktor dla pociagu o nazwie n zawierajacy 1 wagon o nazwie wn first i last ustawiem na ten wagon
}
/*Wejscie:
1
16
New T1 W1
InsertFirst T1 W2
Display T1
New T2 V1
InsertFirst T2 V0
InsertLast T2 V2
Display T2
Union T1 T2
TrainsList
Display T1
DelLast T1 T2
InsertFirst T2 Z1
Display T2
Reverse T2
Union T2 T1
Display T2
Wyjscie:
T1: W2 W1
T2: V0 V1 V2
Trains: T1
T1: W2 W1 V0 V1 V2
T2: Z1 V2
T2: V2 Z1 W2 W1 V0 V1
*/