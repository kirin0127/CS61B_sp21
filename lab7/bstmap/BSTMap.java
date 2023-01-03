package bstmap;

import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private static class BSTNode<K extends Comparable<K>, V>{
        K key;
        V value;
        BSTNode<K, V> left;
        BSTNode<K, V> right;

        BSTNode(){}
        BSTNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        void put(BSTNode<K, V> node){
            if(this.key == null){
                this.key = node.key;
                this.value = node.value;
            }else if(node.key.compareTo(this.key) > 0){
                if(this.left != null){
                    this.left.put(node);
                }else{
                    this.left = new BSTNode<>(node.key, node.value);
                }
            }else if(node.key.compareTo(this.key) < 0){
                if(this.right != null){
                    this.right.put(node);
                }else{
                    this.right = new BSTNode<>(node.key, node.value);
                }
            }
        }

        V get(K key){
            V result = null;
            if(this.key == null){
                return null;
            }else if(key.compareTo(this.key) == 0){
                result = this.value;
            }else if(key.compareTo(this.key) > 0){
                if(this.left == null){
                    result = null;
                }else{
                    result = this.left.get(key);
                }
            }else if(key.compareTo(this.key) < 0){
                if(this.right == null){
                    result = null;
                }else{
                    result = this.right.get(key);
                }
            }
            return result;
        }

        boolean containsKey(K key) {
            boolean result = false;
            if(this.key == null){
                return false;
            }else if(key.compareTo(this.key) == 0){
                result = true;
            }else if(key.compareTo(this.key) > 0){
                if(this.left == null){
                    return false;
                }else{
                    result = this.left.containsKey(key);
                }
            }else if(key.compareTo(this.key) < 0){
                if(this.right == null){
                    return false;
                }else{
                    result = this.right.containsKey(key);
                }
            }
            return result;
        }

        int size(){
            if(this.key == null){
                return 0;
            }else{
                if(this.left == null){
                    this.left = new BSTNode<>();
                }
                if(this.right == null){
                    this.right = new BSTNode<>();
                }
                return 1 + this.left.size() + this.right.size();
            }
        }
    }

    private BSTNode<K, V> root;

    BSTMap(){
        this.root = new BSTNode<>();
    }

    @Override
    public void clear() {
        this.root = new BSTNode<>();
    }

    @Override
    public boolean containsKey(K key) {
        return this.root.containsKey(key);
    }

    @Override
    public V get(K key) {
        return this.root.get(key);
    }

    @Override
    public int size() {
        return this.root.size();
    }

    @Override
    public void put(K key, V value) {
        BSTNode<K, V> node = new BSTNode<>(key, value);
        this.root.put(node);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
