/*Ponizszy  program za pomoca stosu przeksztalca wyrazenia w postaci infiksowej na postac onp oraz robi to w inna strone*/
import java.util.Scanner;
public class RPNtoPN_PNtoRPN {
    public static Scanner sc = new Scanner(System.in);//globalna zmienna scanner dla wczytywania danych w main
    public static boolean check(String s){//funkcja ktora imituje automat skonczony weryfikujacy czy wyrazenie w postaci infiksowej jest poprawne
        int rb=0;//zmienna do liczenia prawych nawiasow
        int lb=0;//zmienna do liczenia lewych nawiasow
        boolean start=true; //zmienna odpowiada za stan poczatkowy
        boolean end=false;//zmienna odpowiada za stan akceptujacy
        boolean a=false;//zmienna odpowiada za stan 2 z automatu w poleceniu
        /*Ponizsza petla jest imitacja automatu skonczonego ktory weryfikuje czy wyrazenie jest poprawne
        Za kazdy z stanow odpowiada specjalna zmienna logiczna
        W kazdej iteracji petli tylko jedna z 3 zmiennych logicznych moze miec znaczenie true
        dwie pozostale w tym momencie maja znaczenie false*/
        for(int i=0;i<s.length();i++){ //petla idzie po calym wyrazeniu
            char x=s.charAt(i);//pobieram znak z i-tej pozycji w wyrazeniu
            if(rb>lb)//jesli ilosc prawych nawiasow w jakims momencie bedzie wieksza od ilosci lewych to funkcja zwraca false(skoro nie jest mozliwa taka stuacja w poprawnym wyrazeniu)
                return false;
            /*jesli zmienna start jest prawdziwa to znaczy ze automat jest w stanie poczatkowym(stan 0 z polecenia)
            po przeczytaniu poprzedniego elementa wyrazenia*/
            if(start==true){
                //jesli przeczytana zostala litera to przechodze do stanu akceptujacego
                if(x>='a'&&x<='z'){
                    start=false;
                    end=true;
                }
                /*jesli przeczytany zostal lewy nawias to zostaje w stanie poczatkowym,zwiekszam licznik lewych nawiasow oraz sprawdzam czy nie doszlo do stuacji kiedy poprzedni element
                byl prawym nawiasem i jak doszlo do takiej sytuacji(np. )(a) to funkcja zwraca false*/
                else if(x=='('){
                    if(i>1&&s.charAt(i-1)==')')
                        return false;
                    lb++;
                    start=true;
                }
                //jesli w przeczytany zostal prawy nawias to funkcja zwroci flase skoro dla poprawnego wyrazenia to nie moze zajsc
                else if(x==')'){
                    return false;
                }
                //jesli w przeczytany zostal unarny operator to przechodze do stanu 2(z automatu z polecenia)
                else if(unarnosc(x)){
                    start=false;
                    a=true;
                }
                //pozostaje przypadek w ktorym przeczytany zostal binarny operator i w tym przypadku funkcja zwraca flase skoro dla poprawnego wyrazenia w postaci infiksowej takie nie moze sie zdarzyc
                else {
                    return false;
                }
            }
            /*jesli zmienna a jest prawdziwa to znaczy ze automat jest w stanie 2(z automatu z polecenia)
            po przeczytaniu poprzedniego elementa wyrazenia*/
            else if(a==true){
                //jesli przeczytana zostala litera to przechodze do stanu akceptujacego
                if(x>='a'&&x<='z'){
                    a=false;
                    end=true;
                }
                /*jesli przeczytany zostal lewy nawias to przechodze do stanu poczatkowego,zwiekszam licznik lewych nawiasow oraz sprawdzam czy nie doszlo do stuacji kiedy poprzedni element
                byl prawym nawiasem i jak doszlo do takiej sytuacji(np. )(a) to funkcja zwraca false*/
                else if(x=='('){
                    if(i>1&&s.charAt(i-1)==')')
                        return false;
                    lb++;
                    a=false;
                    start=true;
                }
                //jesli w przeczytany zostal prawy nawias to funkcja zwroci flase skoro dla poprawnego wyrazenia to nie moze zajsc
                else if(x==')'){
                    return false;
                }
                //jesli w przeczytany zostal unarny operator to zostaje w tym stanie
                else if(unarnosc(x)){
                    a=true;
                }
                //pozostaje przypadek w ktorym przeczytany zostal binarny operator i w tym przypadku funkcja zwraca flase skoro dla poprawnego wyrazenia w postaci infiksowej takie nie moze sie zdarzyc
                else{
                    return false;
                }
            }
            /*jesli zmienna start jest prawdziwa to znaczy ze automat jest w stanie akceptujacym(stan 1 z polecenia)
            po przeczytaniu poprzedniego elementa wyrazenia*/
            else if(end==true){
                //jesli przeczytana zostala litera to funckja zwraca flase skoro nie jest to mozliwe w tym stanie dla poprawnego wyrazenia
                if(x>='a'&&x<='z'){
                    return false;
                }
                //jesli w przeczytany zostal lewy nawias to funkcja zwroci flase skoro dla poprawnego wyrazenia to nie moze zajsc
                else if(x=='('){
                    return false;
                }
                /*jesli przeczytany zostal prawy nawias to zostaje w stanie akceptujacym,zwiekszam licznik prawych nawiasow oraz sprawdzam czy nie doszlo do stuacji kiedy poprzedni element
                byl prawym nawiasem i jak doszlo do takiej sytuacji(np. )(a) to funkcja zwraca false*/
                else if(x==')'){
                    if(i>1&&s.charAt(i-1)=='(')
                        return false;
                    rb++;
                    end=true;
                }
                //jesli przeczytany zostal unarny operator to funkcja zwroci false skoro dla poprawnego wyrazenioa w tym stanie nie jest to mozliwe
                else if(unarnosc(x)){
                    return false;
                }
                //pozostaje przypadek w ktorym przeczytany zostal binarny operator i w tym przypadku przechodze do stanu poczatkowego(0 z automatu z polecenia)
                else{
                    end=false;
                    start=true;
                }
            }
        }
        /*jesli ilosc prawych i lewych nawiasow nie jest rowna to zwrcam false(czyli wyrazenie nie jest poprawne)*/
        if(rb-lb!=0)
            return false;
        return end;//funkcja zwraca znaczenie zmiennej odpowiadajacej za stan koncowy.Skoro w kazdej iteracji tylko jedna zmienna moze miec znaczenie true,to jak end==true to znaczy ze stan na ktorym skonczylo sie wyrazenie wejsciowe jest stanem akceptujacym,a wiec wyrazenie jest poprawne
    }
    //ponizsza funkcja dodaje spacje przed kazdym symbolem wyrazenia za pomoca petli dodajac po kolei spacje i elementy z wyrazenia podanego jako argument i zwraca nowy obiekt typu string
    public static String addspaces(String s){
        String result="";
        for(int i=0;i<s.length();i++) {
            result += " ";
            result+=s.charAt(i);
        }
        return result;
    }
    /*ponizsza funkcja oczyszcza wyrazenie w postaci infiksowej od zbednych znakow(wszsytkie symbole oprocz operatorow,nawiasow i malych liter alfabetu angielskiego)
    idac mala petla po wyrazeniu i jak napotka operator/nawias/mala litere to dodaje do zwracanej przez ta funkcje zmiennej typu string*/
    public static String clearINFtoONP(String s){
        String line="";
        for(int i=0;i<s.length();i++){
            if(operator(s.charAt(i))||s.charAt(i)==')'||s.charAt(i)=='('||(s.charAt(i)>='a' && s.charAt(i)<='z'))
                line+=s.charAt(i);
        }
        return line;
    }
    /*ponizsza funkcja oczyszcza wyrazenie w postaci onp od zbednych znakow(wszsytkie symbole oprocz operatorow i malych liter alfabetu angielskiego)
    idac mala petla po wyrazeniu i jak napotka operator/mala litere to dodaje do zwracanej przez ta funkcje zmiennej typu string*/
    public static String clearONPtoINF(String s){
        String line="";
        for(int i=0;i<s.length();i++){
            if(operator(s.charAt(i))||(s.charAt(i)>='a' && s.charAt(i)<='z'))
                line+=s.charAt(i);
        }
        return line;
    }
    //funckja ktora sprawdza czy podany symbol jest operatorem
    public static boolean operator(char x){
        if(x=='!'||x=='~'||x=='^'||x=='*'||x=='/'||x=='%'||x=='+'||x=='-'||x=='<'||x=='>'||x=='?'||x=='&'||x=='|'||x=='=')
            return true;
        else
            return false;
    }
    //funckja ktora sprawdza czy podany symbol jest unarnym operatorem
    public static boolean unarnosc(char x){
        if(x=='!'||x=='~')
            return true;
        else
            return false;
    }
    //funckja ktora sprawdza czy podany symbol jest prawostronnym/lewostronnym operatorem(zwraca p dla prawostronnych i l w pozostalych
    // przypadkach ale zawsze jest wywolana tylko po sprawdzeniu czy symbol jest operatorem wiec l zwroci tylko dla lewostronnych operatorow
    public static char lacznosc(char x){
        if(x=='!'||x=='~'||x=='='||x=='^')
            return 'p';
        else return 'l';
    }
    //funckja zwraca priorytet symbola(do tablicy pryorytetow dodalam jeszcze litery bo w funckji ONPtoINF mam drugi stos dla pojedynczych symboli zeby miec
    // najmniejsza ilosc nawiasow w wyrazaniu inf po przeksztalceniu i dodalam te litery aby nie dodawac nawiasy do pojedynczych symboli(zeby nie doszlo do sytuacji (a) )
    public static int priority(char x){
        if(x>='a' && x<='z')
            return 11;
        if(x==')' || x=='(')
            return 10;
        else if(x=='!'||x=='~')
            return 9;
        else if(x=='^')
            return 8;
        else if(x=='*'||x=='/'||x=='%')
            return 7;
        else if(x=='+' || x=='-')
            return 6;
        else if(x=='<' || x=='>')
            return 5;
        else if(x=='?')
            return 4;
        else if(x=='&')
            return 3;
        else if(x=='|')
            return 2;
        else if(x=='=')
            return 1;
        return 11;
    }
    //ponizsza funkcja sluzy do przeksztalcenia wyrazenia w postaci infiksowej na postac onp
    public static String INFtoONP(String s){
        if(!check(s))//na poczatku sprawdzam za pomoca funkcji check czy wyrazenie w postaci infiksowej jest poprawne i zwracam " error" jak wyrazenie nie jest poprawne
            return " error";
        String result="";//sluzy do przechowywania wyrazenia koniecznego w onp
        stack stos=new stack(s.length());//robie stos o maksymalnym rozmiarze rownym dlugosci wyrazenia podanego jako argument
        for(int i=0;i<s.length();i++){//ide petla po calym wyrazeniu
            char x=s.charAt(i);//pobieram kolejny symbol z wyrazenia
            if(operator(x)){//jesli przeczytany zostal operator
                if(lacznosc(x)=='l'){//jak jest lewostronnie laczny dopoki jest stos nie jest pusty i na wierscholku stosu znajduje sie operator taki ze jego priorytet jest wiekszy lub rowny od priorytetu przeczytanego operatora dodaje do koniecznego wyrazenia operator ze stosu
                    while(!stos.isEmpty()&& operator(stos.look().charAt(0)) && priority(x)<=priority(stos.look().charAt(0))){result+=stos.pop();}
                }
                else{//jak jest prawostronnie laczny dopoki jest stos nie jest pusty i na wierscholku stosu znajduje sie operator taki ze jego priorytet jest wiekszy od priorytetu przeczytanego operatora dodaje do koniecznego wyrazenia operator ze stosu
                    while(!stos.isEmpty()&&operator(stos.look().charAt(0)) && priority(x)<priority(stos.look().charAt(0))){result+=stos.pop();}
                }
                stos.push(String.valueOf(x));//dodaje na stos przeczytany operator
            }
            else if(x=='(')//jesli przeczytany zostal lewy nawias to dodaje go na stos
                stos.push(String.valueOf(x));
            else if(x==')'){//jesli przeczytany zostal prawy nawias to dopoki na wierzcholku stosu nie jest lewy nawias dodaje do wyrazenia koniecznego element z wierszcholku stosu
                while(stos.look().charAt(0)!='(')
                    result+=stos.pop();
                stos.pop();//zdejmuje lewy nawias ze stosu
            }
            else//pozostaje przypadek jak przeczytana zostala mala litera alfabetu angielskiego  i w tym przypadku dodaje do wyrazenia koniecznego ta litere
                result+=x;
        }
        while(!stos.isEmpty())//dopoki stos nie jest pusty dodaje do wyrazenia koniecznego pozpstale elementy
            result+=stos.pop();
        return addspaces(result);//zwracam rozdzielone spacjami wyrazenie konieczne
    }
    //ponizsza funkcja sluzy do przeksztalcenia wyrazenia w postaci onp na postac infiksowa
    public static String ONPtoINF(String s){
        stack stos=new stack(s.length());//robie stos o maksymalnym rozmiarze rownym dlugosci wyrazenia podanego jako argument(do przeksztalcenia wyrazenia)
        stack st=new stack(s.length());//robie stos o maksymalnym rozmiarze rownym dlugosci wyrazenia podanego jako argument(zeby w koncowym wyrazeniu byla najmniejsza ilosc nawiasow w tym stosie beda przechowywane pojedyncze elementy wyrazenia poczatkowego)
        for(int i=0;i<s.length();i++) {//ide petla po calym wyrazeniu poczatkowym
            char x = s.charAt(i);//pobieram kolejny symbol z wyrazenia
            if (x <= 'z' && x >= 'a') {//jesli przeczytana zostala operanda(mala litera alfabetu angielskiego) to dodaje ja na dwa stosy
                stos.push(String.valueOf(x));
                st.push(String.valueOf(x));
            } else if (operator(x)) {//jesli przeczytany zostal operator
                if (unarnosc(x)) {//jesli to jest unarny operator
                    if (stos.isEmpty())//sprawdzam czy stos nie jest pusty i jak jest to znaczy ze wyrazenie nie jest obliczalne a wiec nie jest poprawne i zwracam " error"
                        return " error";
                    String a="";//do przechowywania elementu  pobranego ze szczytu stosu do przeksztalecenia wyrazenia
                    if (priority(st.pop().charAt(0)) < priority(x))//jesli priorytet przeczytanego symbola jest wiekszy(skoro wszystkie unarne operatory sa prawostronne) od elementu znajdujacego sie na szczycie stosu do przechowywania pojedynczych elementow z wyrazenia to pobieram ze stosu do przeksztalcenia wyrazenia i dodaje do niego nawiasy
                        a = "(" + stos.pop() + ")";
                    else//jesli priorytet okazal sie mniejszy lub rowny to po prostu zdejmuje element ze szczytu stosu i NIE dodaje nawiasow
                        a=stos.pop();
                    stos.push(x + a);//dodaje na stos do przeksztalcenia wyrazenia operator i pobrany ze szczytu stosu element jako jesen obiekt typu string
                } else {//jesli operator jest binarny
                    if (lacznosc(x) == 'l') {//jesli jest lewostronny
                        if (stos.isEmpty())//sprawdzam czy stos nie jest pusty i jak jest to znaczy ze wyrazenie nie jest obliczalne a wiec nie jest poprawne i zwracam " error"
                            return " error";
                        String b="";//do przechowywania elementu  pobranego ze szczytu stosu do przeksztalecenia wyrazenia
                        if (priority(st.pop().charAt(0)) <= priority(x))//jesli priorytet przeczytanego symbola jest wiekszy lub rowny(skoro operator przeczytany jest lewostronnestronny i wiec na pocztku wykonujemy co to jest od operatora po lewej stronie a pobiearny element ze sczytu stosu bedzie po prawej stronie od operatora) od elementu znajdujacego sie na szczycie stosu do przechowywania pojedynczych elementow z wyrazenia to pobieram ze stosu do przeksztalcenia wyrazenia i dodaje do niego nawiasy
                            b = "(" + stos.pop() + ")";
                        else//jesli priorytet okazal sie mniejszy to po prostu zdejmuje element ze szczytu stosu i NIE dodaje nawiasow
                            b=stos.pop();
                        if (stos.isEmpty())//sprawdzam czy stos nie jest pusty i jak jest to znaczy ze wyrazenie nie jest obliczalne a wiec nie jest poprawne i zwracam " error"
                            return " error";
                        String a="";//do przechowywania elementu  pobranego ze szczytu stosu do przeksztalecenia wyrazenia
                        if (priority(st.pop().charAt(0)) < priority(x))//jesli priorytet przeczytanego symbola jest wiekszy lub rowny(skoro operator przeczytany jest lewostronnestronny i wiec na pocztku wykonujemy co to jest od operatora po lewej stronie a pobiearny element ze sczytu stosu bedzie po lewej stronie od operatora) od elementu znajdujacego sie na szczycie stosu do przechowywania pojedynczych elementow z wyrazenia to pobieram ze stosu do przeksztalcenia wyrazenia i dodaje do niego nawiasy
                            a = "(" + stos.pop() + ")";
                        else
                            a=stos.pop();//jesli priorytet okazal sie mniejszy lub rowny to po prostu zdejmuje element ze szczytu stosu i NIE dodaje nawiasow
                        stos.push(a + x + b);//dodaje do stosu wyrazenie zlozone z dwoch pobranych ze szczytu stosu  elementow i rozdzielam ich opeartorem
                    }
                    else{//jesli jest prawostronny
                        if (stos.isEmpty())//sprawdzam czy stos nie jest pusty i jak jest to znaczy ze wyrazenie nie jest obliczalne a wiec nie jest poprawne i zwracam " error"
                            return " error";
                        String b="";//do przechowywania elementu  pobranego ze szczytu stosu do przeksztalecenia wyrazenia
                        if (priority(st.pop().charAt(0)) < priority(x))//jesli priorytet przeczytanego symbola jest wiekszy(skoro przeczytany zostal prawotronny operator a wiec na poczatku dla tego operatora wykonujemy co to jest od niego po prawej stronie  i pobierany element ze szczytu stosu bedzie po prawej stronie od operatora) od elementu znajdujacego sie na szczycie stosu do przechowywania pojedynczych elementow z wyrazenia to pobieram ze stosu do przeksztalcenia wyrazenia i dodaje do niego nawiasy
                            b = "(" + stos.pop() + ")";
                        else//jesli priorytet okazal sie mniejszy lub rowny to po prostu zdejmuje element ze szczytu stosu i NIE dodaje nawiasow
                            b=stos.pop();
                        if (stos.isEmpty())//sprawdzam czy stos nie jest pusty i jak jest to znaczy ze wyrazenie nie jest obliczalne a wiec nie jest poprawne i zwracam " error"
                            return " error";
                        String a="";//do przechowywania elementu  pobranego ze szczytu stosu do przeksztalecenia wyrazenia
                        if (priority(st.pop().charAt(0)) <= priority(x))//jesli priorytet przeczytanego symbola jest wiekszy lub rowny(skoro przeczytany zostal prawotronny operator a wiec na poczatku dla tego operatora wykonujemy co to jest od niego po prawej stronie a pobierany element bedzie po lewej stronie od operatora) od elementu znajdujacego sie na szczycie stosu do przechowywania pojedynczych elementow z wyrazenia to pobieram ze stosu do przeksztalcenia wyrazenia i dodaje do niego nawiasy
                            a = "(" + stos.pop() + ")";
                        else//jesli priorytet okazal sie mniejszy to po prostu zdejmuje element ze szczytu stosu i NIE dodaje nawiasow
                            a=stos.pop();
                        stos.push(a + x + b); //dodaje do stosu wyrazenie zlozone z dwoch pobranych ze szczytu stosu  elementow i rozdzielam ich opeartorem
                    }
                }
                st.push(String.valueOf(x));//dodaje przecvzytany opeartor na stos dla elementow
            }
        }
        if(stos.isEmpty())//jesli stos okazal sie pusty zwracam " error" skoro to znaczy ze wyrazenie bylo puste
            return " error";
        String result=addspaces(stos.pop());//do wyrazenia koncowego dodaje wyrazenie ze szczytu stosu w ktoreym wszystkie lement rozdzielam spacjami
        if(!stos.isEmpty())//jesli stos w tym momencie nie jest pusty to znaczy ze wyrazenie nie jest obliczalne a wiec zwracam " error"
            return " error";
        return result;//zwracam wyrazenie w postaci infiksowej rozdzielone spacjami
    }
    public static void main(String[] args) {
        int n=sc.nextInt();//wczytuje ilosc zestawow danych
        sc.nextLine();//pomijam znak nowej linii
        for(int i=0;i<n;i++){
            String l=sc.nextLine();//czytam kolejny zestaw danych
            if(l.charAt(0)=='I'){//jak pierwszy symbol w przeczytanej linii jest I to wywoluje funkcje INFtoONP ktora przeksztalci wyrazenie w postaci infiksowej na onp i wydrukuje wynik na standardowe wyjscie
                System.out.println("ONP:"+INFtoONP(clearINFtoONP(l)));
            }
            else//jak pierwszy symbol w przeczytanej linii nie jest I(to znaczy ze zostalo wczytane wyrazenie w postaci onp) to wywoluje funkcje ONPtoINF ktora przeksztalci wyrazenie w postaci onp na infiksowa i wydrukuje wynik na standardowe wyjscie
                System.out.println("INF:"+ONPtoINF(clearONPtoINF(l)));
        }
    }
}
class stack{
    private int maxSize;
    private String[] Elem;
    private int t;
    public stack(int size){//konstruktor stosu
        maxSize = size;//ustawiam rozmiar stosu
        Elem = new String[maxSize];//inisjulizuje tablice o rozmirze rownym rozmiaru stosu
        t = maxSize; }//ustawiam indeks wierzcholku stosu
    public void push(String x) {//funkcja wtawiajaca na szczyt stosu nowy element podany jako argument
        Elem[-- t] = x;
    }
    public String pop(){//funkcja zdejmuje element ze szczytu stosu(przestawiajac indeks) i zwraca ten element
        return Elem[t ++];
    }
    public String look(){ //funckja ktora zwraca element znajdujacy sie na szczycie stosu
        return Elem[t];
    }
    public boolean isEmpty(){//funkcja sprawdza czy stos jest pusty
        return (t == maxSize);
    }
}
/*Dane testowe:
Wejscie:
4
ONP: abc*-
INF: p=o
INF: ~(q>w)=(s&~q)
INF: f>(g>h)
INF: a*(b+c)
ONP: pt-ft**
INF: a-b*c+d
INF: a*
ONP: ab+cd-*
ONP: abc-*+
Wyjscie:
INF: a - b * c
ONP: p o =
ONP: q w > ~ s q ~ & =
ONP: f g h > >
ONP: a b c + *
INF: ( p - t ) * ( f * t )
ONP: a b c * - d +
ONP: error
INF: ( a + b ) * ( c - d )
INF: error
*/