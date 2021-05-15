public class Main {
    public static void main(String[] args){
        Character[] chars = {'e','z','g','a','m'};
        char a = '1';
        char b = '-';
        String[] words = {"aliRi","Rezaz" , "Hamed" , "Mahsa"};


        Sort sort = new Sort();

        sort.LSDradixSort(words,5);
        for (int i = 0 ; i < words.length ; i++){
            System.out.print(words[i] + ",");
        }
    }
}
