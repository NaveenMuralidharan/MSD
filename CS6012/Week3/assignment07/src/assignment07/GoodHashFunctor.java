package assignment07;

public class GoodHashFunctor implements HashFunctor{
    @Override
    public int hash(String item) {
        int hash = 0;
        for(int i=0; i<item.length(); i++){
            hash = (31 * hash) + item.charAt(i);
        }
        return Math.abs(hash);
    }
}
