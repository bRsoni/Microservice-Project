import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

        public static void main(String[] args) {

    duplicatecharcter("better butter");
    String str = "";
    if(palindromString(str)){
        System.out.println(str +"-->  is palindrome");
    }else{
        System.out.println(str+"-->  is not a palindrome");
    }
    int i[] = {2,5,5,3,4};
            smallestElementArray(i);



    }
    //Duplicate Charcter in stirng

   public static void duplicatecharcter(String str){

      /* HashMap<Character ,Integer> charcount = new HashMap<>();
       char [] strarr = str.toCharArray();
       for(char c : strarr){
           if(charcount.containsKey(c)){
                charcount.put(c, charcount.get(c)+1);
           }else{
               charcount.put(c,1);
           }

       }
       Set<Character> charsInString = charcount.keySet();

       System.out.println("Duplicate Characters In "+str);
       for (Character ch : charsInString)
       {
           if(charcount.get(ch) > 1)
          {

               System.out.println(ch +" : "+ charcount.get(ch));
          }
       }
*/
       Map<Character, Long> charFrequency = str.chars()
               .mapToObj(c -> (char) c)
               .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

       // Filter out characters with frequency greater than 1 (i.e., duplicates)
       charFrequency.entrySet().stream()
               .filter(entry -> entry.getValue() > 1)
               .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
   }

   public static boolean palindromString(String str){
            if(str.length()==0 || str.length()==1){
                return true;
            }
            if(str.charAt(0) == str.charAt(str.length()-1 )){
                return palindromString(str.substring(1,str.length()-1));
            }
            return false;
   }

   public static void smallestElementArray(int [] arr){
            int min = arr[0];
            for (int i=0 ; i<= arr.length-1 ; i++){
                if(min > arr[i]){
                    min = arr[i];
                }
            }
       System.out.println("minimum value is "+min);
   }


}