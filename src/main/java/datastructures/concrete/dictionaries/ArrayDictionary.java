package datastructures.concrete.dictionaries;

import datastructures.interfaces.IDictionary;
import misc.exceptions.NoSuchKeyException;
import misc.exceptions.NotYetImplementedException;

/**
 * See IDictionary for more details on what this class should do
 */
public class ArrayDictionary<K, V> implements IDictionary<K, V> {
    // You may not change or rename this field: we will be inspecting
    // it using our private tests.
    private Pair<K, V>[] pairs;
    private int size;

    public ArrayDictionary() {
        pairs = makeArrayOfPairs(10);
        size = 0;
    }

    /**
     * This method will return a new, empty array of the given size
     * that can contain Pair<K, V> objects.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] makeArrayOfPairs(int arraySize) {
        return (Pair<K, V>[]) (new Pair[arraySize]);
    }

    @Override
    public V get(K key) {
        if(!this.containsKey(key)) {
            throw new NoSuchKeyException();
        }
        int index = getIndex(key);
        return pairs[index].value;
    }

    @Override
    public void put(K key, V value) {
        if(!this.containsKey(key)) {
           if(size == pairs.length) {
               pairs = copyOver(pairs);
           }
           pairs[size] = new Pair<K,V>(key, value);
           size++;
        }else{
          pairs[getIndex(key)].value = value;  
        }
    }
    /*
     * returns storage of pairs with double the storage space as the previous storage
     */
    private Pair<K,V>[] copyOver(Pair<K,V>[] oldArray) {
        Pair<K,V> [] result = makeArrayOfPairs(2*size);
        for(int i = 0; i < size; i++) {
            result[i] = oldArray[i];
        }
        return result;
    }

    @Override
    public V remove(K key) {
        if(!containsKey(key)) {
            throw new NoSuchKeyException();
        }
        int index = getIndex(key);
        V result = pairs[index].value;
        pairs[index] = new Pair<K,V>(pairs[size-1].key, pairs[size-1].value);
        pairs[size-1] = null;
        size--;
        return result;
    }

    @Override
    public boolean containsKey(K key) {
        for(int i = 0; i < size; i++) {
            if(sameKey(pairs[i].key, key)) {
                return true;
            }
        }
        return false;
    }

    /*
     * returns true if *that* key equals *other* key
     */
    private boolean sameKey(K that, K other) {
    return that == other || that != null && that.equals(other);      
    }
    
    /*
     * returns index value of pair containing given key
     */
    private int getIndex(K key) {
        int i = 0; 
        while(!sameKey(key, pairs[i].key)) {
         i++;
        }
        return i;
    }
    
    @Override
    public int size() {
        return size;
    }

    private static class Pair<K, V> {
        public K key;
        public V value;

        // You may add constructors and methods to this class as necessary.
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}
