import java.util.Scanner;
public class Kadane_algorithm_2D {
    public static Scanner sc = new Scanner(System.in);
    public static void KadaneFor2D(int[][] tab, int rows, int columns,int number) {
        int k,max = 0, lb = 0, rb = 0, bb = 0, tb = 0;
        boolean all_negative=true;
        /*
        Za pomoca petli zagniezdzonej ktora fiksuje
        lewa i prawa granice podtablicy po kolei sprawdzajac wszystkie mozliwe podtablice
         */
        for (int l = 0; l < columns; l++) {
            k=0;
            for (int r = l; r < columns; r++) {
                /*Za pomoca jeszcze jednej petli dodaje
                do kolejnych elementow kolumny l(fiksuje lewa granice podtablicy)
                elementy z kolumny r(fiksuje prawa granice podtablicy).Wspolczynnik k jest
                wykorzystywany dla przypadku kiedy l jest rowne r zeby nie podwajac elementy z podtablicy
                zawierajacej 1 kolumne,po pierwszej iteracji petli ze zmienna r dla kazdego l
                wspolczynnik k jest zamieniany z 0 na 1
                Myk polega na tym ze ta petla tworzy nam jednowymiarowa podtablice
                z kazdej poczatkowej przechowywujac ja w skrajnie lewej kolumnie kazdej podtablicy
                */
                for (int i = 0; i < rows; i++) {
                    tab[i][l] += k*tab[i][r];
                }
                k=1;
                int current_sum=0,current_start=0;
                /*
                Stad zaczyna sie troche otformatowany algorytm Kadane:
                Idac petla od 0 do ostatniego elementa wiersza l tablicy i dodajac je
                po kolei do biezacej sumy podtablicy mozna otrzymac kilka
                interesujacych przypadkow opisanych w warunkach za pomoca if
                 */
                for (int i=0; i < rows; i++){
                    current_sum = current_sum + tab[i][l];  // operacja dominujaca
                    /*
                    ten fragment sprawdza czy nie jest biezaca podtablica typu 0...0 liczba:
                    przypisuje gornemu ograniczeniu numer biezacej iteracji
                     */
                    if(current_sum==tab[i][l] && current_sum>0){
                        all_negative = false;
                        current_start = i;
                    }
                    /*przypadek z ujemna suma:
                    zeruje biezaca sume oraz przypisuje
                    startowemu elementowi wartosc nastepna za ta na ktorej iteracji petli(czyli elemencie tablicy)
                    jestem.Oprocz tego
                    ten przypadek swiadczy ze istnieje co najmniej jeden nieujemny element tablicy
                    a wiec zmieniam wartosc zmiennej logicznej all_negative z false na true*/
                    if (current_sum < 0){
                        current_sum = 0;
                        current_start = i+1;
                    }
                    /*
                    przypadek jesli biezaca suma jest wieksza od maksymalnej znalezionej do tego momentu:
                    przypisuje nowe znaczenia lewej,prawej i dolnej granicam oraz maksymalnej sumie.Oprocz tego
                    ten przypadek swiadczy ze istnieje co najmniej jeden nieujemny element tablicy
                    a wiec zmieniam wartosc zmiennej logicznej all_negative z false na true
                     */
                    else if (current_sum > max){
                        max = current_sum;
                        tb = current_start;
                        bb = i;
                        lb = l;
                        rb = r;
                        all_negative=false;
                    }
                    /*
                    przypadek jesli biezaca suma jest rowna maksymalnej znalezionej do tego momentu:
                    dokonam jescze jednego sprawdzenie dotyczacego rozmiaru biezacej podtablicy i jak ten rozmiar
                    jest mniejszy od rozmiaru maksymalnej znalezionej do tego momentu podtablicy
                    przypisuje nowe znaczenia lewej,prawej i dolnej granicam oraz maksymalnej sumie.Oprocz tego
                    ten przypadek swiadczy ze istnieje co najmniej jeden nieujemny element tablicy
                    a wiec zmieniam wartosc zmiennej logicznej all_negative z false na true
                     */
                    else if(current_sum == max && (i-current_start+1)*(r-l+1)<(bb-tb+1)*(rb-lb+1)){
                        tb = current_start;
                        bb = i;
                        lb = l;
                        rb = r;
                        all_negative=false;
                    }
                    /*
                    przypadek jesli biezaca suma oraz rozmiar biezacej podtablicy sa rowne sumie i rozmiarowi maksymalnej znalezionej do tego momentu podtablicy :
                  dokonam jescze jednego sprawdzenie dotyczacego leksykograficzego porzadku wymiarow biezacej podtablicy(w kolejnosci gorne ograniczenie,dolne ograniczenie,lewe ograniczenie,prawe ograniczenie)
                  i jak otrzymuje liczbe mniejsza od tej liczby dla maksymalnej znalezionej tablicy do tego momentu
                    przypisuje nowe znaczenia lewej,prawej i dolnej granicam oraz maksymalnej sumie.Oprocz tego
                    ten przypadek swiadczy ze istnieje co najmniej jeden nieujemny element tablicy
                    a wiec zmieniam wartosc zmiennej logicznej all_negative z false na true
                     */
                    else if(current_sum == max && (i-current_start+1)*(r-l+1)==(bb-tb+1)*(rb-lb+1) && 1000*current_start+100*i+10*l+r<1000*tb+100*bb+10*lb+rb){
                        tb = current_start;
                        bb = i;
                        lb = l;
                        rb = r;
                        all_negative=false;
                    }
                    /*
                    ostatni przypadek dotyczy sytuacji jak napotkamy po raz pierwszy 0 wsrod liczb ujemnych:
                    przypisuje nowe znaczenia lewej,prawej i dolnej granicam oraz maksymalnej sumie.Oprocz tego
                    ten przypadek swiadczy ze istnieje co najmniej jeden nieujemny element tablicy
                    a wiec zmieniam wartosc zmiennej logicznej all_negative z false na true
                     */
                    else if (current_sum == 0 && all_negative==true){
                        all_negative = false;
                        tb = current_start;
                        bb = i;
                        lb = l;
                        rb = r;
                    }
                }
            }
        }
        /*
        dwie wariacji wyjscia:
        1:dla tablicy skladajacej sie wylacznie z liczb mniejszych od 0
        2:dla przeciwnego przypadku
        */
        if(all_negative)
            System.out.println(number+":"+" n = "+rows+" m = "+columns+", ms = "+max+", mst is empty");
        else
            System.out.println(number+":"+" n = "+rows+" m = "+columns+", ms = "+max+", mst = "+"a["+tb+".."+bb+"]["+lb+".."+rb+"]");

    }
        public static void main(String[] args){
        /*
        program wczytuje dane z standardowego wejscia za pomoca pojedynczej zmiennej klasy Scanner sc
        i metod nextInt(do wczytania danych typu int),nextLine(do wczytania znaku nowej linii),next().charAt[0](do wczytania znaku :)
        przypisuje numer zestawu do zmiennej number,
        ilosc wierszy do zmiennej rows oraz ilosc kolumn do zmiennej columns.
        Tworze tablice TAB typu int dla przechowywania tablicy,Za pomoca petli for wczytuje i zapisuje do
        kolejnych elementow tablicy TAB znaczenia(kazdy nowy element wczytuje za pomoca nextInt i po zakonczeniu
        wiersza wykorzystuje nextLine() zeby przejsc na nowa linie).Wywoluje funkcje
        statyczna z klasy Source KadaneFor2D()
         */
            int n=sc.nextInt();
            int rows,columns,number;
            for(int i=0;i<n;i++){
                number=sc.nextInt();
                sc.next();
                rows=sc.nextInt();
                columns=sc.nextInt();
                //line=sc.nextLine();
                int[][] TAB= new int[rows][columns];
                for(int h=0;h<rows;h++) {
                    for(int g=0;g<columns;g++) {
                        TAB[h][g] = sc.nextInt();
                    }
                }
                KadaneFor2D(TAB,rows,columns,number);
            }
         }
}