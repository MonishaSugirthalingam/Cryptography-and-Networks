import java.util.*;
//HashMap to TreeMap
class Main{
    static TreeMap<Integer,String> fun(Map<Integer,String> map){
        TreeMap<Integer,String> treemap=new TreeMap<>();
        for(Map.Entry<Integer,String> entry: map.entrySet() ){
            treemap.put(entry.getKey(),entry.getValue());
        }
        return treemap;
    }
    public static void main(String args[]){
        Map<Integer,String> hashmap=new HashMap<>();
        hashmap.put(1,"mango");
        hashmap.put(2,"apple");
        
        TreeMap<Integer,String> treemap=fun(hashmap);
        System.out.println(treemap);
    }
}