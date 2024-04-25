import java.util.Scanner;
//program tworzy drzewo z podanego porzadku inorder oraz preorder/postorder i pozniej wypisuje w brakujacym porzadku oraz porzadku levelorder
public class Binary_trees{
    public static Scanner sc=new Scanner(System.in);//zmienna do wczytywania danych ze standardowego wejscia
    public static void main(String[] args){
        int zestawy=sc.nextInt();//zmienna do przechowywania ilosci zestawow
        for(int i=1;i<=zestawy;i++){//ide petla po ilosci zestawow
            int elementy=sc.nextInt();//wczytuje ilosc elementow w kolejnem drzewie
            int[] porder=new int[elementy];//tablica do przechowywania elementow drzewa w porzadku pre lub post order
            int[] inorder=new int[elementy];//tablica do przechowywania elementow w porzadku inorder
            sc.nextLine();//wczytuje przejscie na nowa linie
            String line=sc.nextLine();//wczytuje informacje w jakim porzadku zostaja podane dane pre/post order
            for(int j=0;j<elementy;j++){
                porder[j]=sc.nextInt();//wczytuje kolejne elementy do tablicy
            }
            sc.nextLine();//wczytuje przejscie na nowa linie
            sc.nextLine();//wczytuje linie z slowem "INORDER"
            for(int j=0;j<elementy;j++){
                inorder[j]=sc.nextInt();//wczytuje kolejne elementy do tablicy
            }
            System.out.println("ZESTAW "+i);//drukuje na standardowe wyjscie "ZESTAW" i numer zestawu
            if(line.equals("PREORDER")){//jesli porzadek w ktorym podane najpierw dane byl preorder
                BT t=new BT();
                t.set_root(t.BinaryTreeFromPreorder(porder,inorder,0,elementy,0,elementy));
                System.out.println("POSTORDER");//drukuje slowo "POSTORDER"
                t.postorder(t.get_root());//drukuje drzewo w porzadku postorder
                System.out.println();//przejscie na nowa linie
                System.out.println("LEVELORDER");//drukuje slowo "LEVELORDER"
                t.levelorder();//drukuje drzewo w porzadku levelorder
            }
            else{//jesli porzadek w ktorym podane najpierw dane byl postorder
                BT t=new BT();
                t.set_root(t.BinaryTreeFromPostorder(porder,inorder,0,elementy,0,elementy));
                System.out.println("PREORDER");//drukuje slowo "PREORDER"
                t.preorder(t.get_root());//drukuje drzewo w porzadku preorder
                System.out.println();//przejscie na nowa linie
                System.out.println("LEVELORDER");//drukuje slowo "LEVELORDER"
                t.levelorder();//drukuje drzewo w porzadku levelorder
            }
            System.out.println();//przejscie na nowa linie
        }
    }
}
class node{//klasa do wezlow drzewa
    private int a;//dane tego wezla
    private node left;//lewe "dziecko"
    private node right;//prawe "dziecko"
    public node(int n){a=n;left=null;right=null;}//konstruktor
    public void set_left(node n){left=n;}//setter dla lewego "dziecka"
    public void set_right(node n){right=n;}//setter dla prawego "dziecka"
    public node get_left(){return left;}//getter dla lewego "dziecka"
    public node get_right(){return right;}//getter dla prawego "dziecka"
    public int get_key(){return a;}//getter dla danych tego wezla
}
class BT{//klasa dla drzewa
    private node root;//korzen drzewa
    public BT(){root = null;}//konstruktor
    public void set_root(node r){root = r;}//setter dla korzenia
    public node get_root() {return root;}//getter dla korzenia
    //funkcja do tworzenia drzewa z podanych danych w porzadku preorder i inorder
    public node BinaryTreeFromPreorder(int[] p, int[] i, int LI, int RI, int LP, int RP) {
        if (LP>=RP || LI>=RI){//baza rekurencji(jesli lewa granica jakiejkolwiek z 2 tablic wychodzi poza prawa)
            return null;
        }
        int j;//zmienna do przechowywania informacji o tym jaki indeks ma biezacy element(skrajnie lewy w preorder) w tablicy z elementami w porzadku inorder
        for (j=LI;j<RI;j++){//szukam skrajnie lewy element(wezel ktory tworze) w tablicy z elementami w porzadku inorder
            if (i[j]==p[LP]){
                break;
            }
        }
        node n = new node(p[LP]);//tworze wezel o danych ze skrajnie lewego elementu z preorder
        n.set_left(BinaryTreeFromPreorder(p, i, LI, j, LP + 1, LP + (j - LI)+1));//rekurencyjnie tworze lewe "dziecko"
        n.set_right(BinaryTreeFromPreorder(p, i, j + 1, RI, LP+(j - LI)+1, RP));//rekurencyjnie tworze prawe "dziecko"
        return n;//zwracam wezel
    }
    //funkcja do tworzenia drzewa z podanych danych w porzadku postorder i inorder
    public node BinaryTreeFromPostorder(int[] p, int[] i, int LI, int RI, int LP, int RP) {
        if (LP>=RP || LI>=RI){//baza rekurencji(jesli lewa granica jakiejkolwiek z 2 tablic wychodzi poza prawa)
            return null;
        }
        int j;
        for (j=LI;j<RI;j++){//zmienna do przechowywania informacji o tym jaki indeks ma biezacy element(skrajnie lewy w postorder) w tablicy z elementami w porzadku inorder
            if (i[j]==p[RP-1]){//szukam skrajnie prawy element(wezel ktory tworze) w tablicy z elementami w porzadku inorder
                break;
            }
        }
        node n=new node(p[RP-1]);//tworze wezel o danych ze skrajnie prawego elementu z preorder
        n.set_left(BinaryTreeFromPostorder(p, i, LI, j, LP, LP + (j - LI)));//rekurencyjnie tworze lewe "dziecko"
        n.set_right(BinaryTreeFromPostorder(p, i, j + 1, RI, LP + (j - LI), RP - 1));//rekurencyjnie tworze prawe "dziecko"
        return n;//zwracam wezel
    }
    //funkcja do obliczenia ilosci poziomow na najglebszej galezi
    public int height(node n) {
        if (n == null)//baza rekurencji
            return 0;
        else{//inaczej zwracam maksymalna wartosc z dlugosci lewej i prawej galezi +1 (dodaje biezacy poziom)
            return (1+Math.max(height(n.get_left()), height(n.get_right())));
        }
    }
    //funkcja do wyswietlania drzewa w porzadku postorder
    public void postorder(node n) {
        if(n!=null){//baza rekurencji bedzie przypadek jak doszlam do konca galezi
            postorder(n.get_left());//ide rekurencyjnie do lewej galezi tego elementa
            postorder(n.get_right());//ide rekurencyjnie do prawej galezi tego elementa
            System.out.print(n.get_key() + " ");//wypisuje dane biezacego elementa
        }
    }
    //funkcja do wyswietlania drzewa w porzadku preorder
    public void preorder(node n) {
        if(n!=null) {//baza rekurencji bedzie przypadek jak doszlam do konca galezi
            System.out.print(n.get_key() + " ");//wypisuje dane biezacego elementa
            preorder(n.get_left());//ide rekurencyjnie do lewej galezi tego elementa
            preorder(n.get_right());//ide rekurencyjnie do prawej galezi tego elementa
        }
    }
    public void print_level(node n, int l){//funkcja drukujaca poziom o numerze l(korzen jest 0 poziomem)
        if (n != null){//jesli istnieje element
            //baza rekurencji doszlam do wymaganego pozioma wiec drukuje dane tego elementa
            if (l==0){System.out.print(n.get_key() + " ");return;}
            //inaczej ide rekurencyjnie do lewej i prawej galezi kolejno
            print_level(n.get_left(), l - 1);
            print_level(n.get_right(), l - 1);
        }
    }
    //wyswietlanie wrzesz
    public void levelorder() {
        int h=height(root);//obliczam ilosc poziomow na najglebszej sciezce
        for (int i=0;i<h;i++){
            print_level(root,i);//dla kazdego pozioma wywoluje funkcje ktora wyswietla 1 poziom
        }
    }
}
/*
Dane testowe
Wejscie:
3
9
PREORDER
8 3 1 6 4 7 10 14 13
INORDER
3 1 6 4 7 8 10 14 13
13
POSTORDER
20 10 5 12 22 21 36 30 28 40 38 48 25
INORDER
20 10 5 12 22 21  25 36 30 28 40 38 48
10
PREORDER
50 17 12 9 14 23 72 54 67 76
INORDER
17 12 9 14 23  50 72 54 67 76
Wyjscie:
ZESTAW 1
POSTORDER
7 4 6 1 3 13 14 10 8
LEVELORDER
8 3 10 1 14 6 13 4 7
ZESTAW 2
PREORDER
25 21 22 12 5 10 20 48 38 40 28 30 36
LEVELORDER
25 21 48 22 38 12 40 5 28 10 30 20 36
ZESTAW 3
POSTORDER
23 14 9 12 17 76 67 54 72 50
LEVELORDER
50 17 72 12 54 9 67 14 76 23
 */