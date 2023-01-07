package bstmap;

import edu.princeton.cs.algs4.BST;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private static class BSTNode<K extends Comparable<K>, V>{
        K key;
        V value;
        BSTNode<K, V> left;
        BSTNode<K, V> right;
        BSTNode<K, V> parent;

        BSTNode(){}
        BSTNode(K key, V value){
            this.key = key;
            this.value = value;
        }
        BSTNode(K key, V value, BSTNode<K, V> prevNode){
            this.key = key;
            this.value = value;
            this.parent = prevNode;
        }

        void put(BSTNode<K, V> node, BSTNode<K, V> prevNode) {
            if(node == null || node.key == null){
                System.out.println("input node is null!");
            }else if(this.key == null){
                this.key = node.key;
                this.value = node.value;
                this.left = node.left;
                this.right = node.right;
                this.parent = prevNode;
            }else if(node.key.compareTo(this.key) > 0){
                if(this.right != null){
                    this.right.put(node, this.right);
                }else{
                    this.right = node;
                    this.right.parent = prevNode;
                }
            }else if(node.key.compareTo(this.key) < 0){
                if(this.left != null){
                    this.left.put(node, this.left);
                }else{
                    this.left = node;
                    this.left.parent = prevNode;
                }
            }
        }

        BSTNode<K, V> get(K key){
            BSTNode<K, V> result = null;
            if(this.key == null){
                return null;
            }else if(key.compareTo(this.key) == 0){
                result = this;
            }else if(key.compareTo(this.key) > 0){
                if(this.right == null){
                    result = null;
                }else{
                    result = this.right.get(key);
                }
            }else if(key.compareTo(this.key) < 0){
                if(this.left == null){
                    result = null;
                }else{
                    result = this.left.get(key);
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
                if(this.right == null){
                    return false;
                }else{
                    result = this.right.containsKey(key);
                }
            }else if(key.compareTo(this.key) < 0){
                if(this.left == null){
                    return false;
                }else{
                    result = this.left.containsKey(key);
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

        K keySet(BSTNode<K, V> node, Set<K> set){
            K key = node.key;
            while (key != null){
                set.add(node.key);
                K left = keySet(node.left, set);
                K right = keySet(node.right, set);
                if(left == null && right == null){
                    key = null;
                }
            }
            return null;
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
        BSTNode<K, V> result = this.root.get(key);
        if(result == null){
            return null;
        }else{
            return result.value;
        }
    }

    @Override
    public int size() {
        return this.root.size();
    }

    @Override
    public void put(K key, V value) {
        BSTNode<K, V> node = new BSTNode<>(key, value);
        this.root.put(node, this.root);
    }

    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();
        this.root.keySet(this.root, result);
        return result;
    }

    @Override
    public V remove(K key) {
        BSTNode<K, V> target = this.root.get(key);
        if (target == null) {
            return null;
        }else if(target.key == this.root.key){
            BSTNode<K, V> targetLeft = target.left;
            BSTNode<K, V> targetRight = target.right;
            if(targetLeft == null && targetRight == null){
                this.root = new BSTNode<>();
            }else if(targetLeft == null){
                this.root = targetRight;
                this.root.parent = null;
            }else if(targetRight == null){
                this.root = targetLeft;
                this.root.parent = null;
            }else{
                if(targetLeft.size() > targetRight.size()){
                    this.root = targetLeft;
                    this.root.parent = null;
                    this.root.put(targetRight, this.root);
                }else{
                    this.root = targetRight;
                    this.root.parent = null;
                    this.root.put(targetLeft, this.root);
                }
            }
        }else{
            BSTNode<K, V> parent = target.parent;
            BSTNode<K, V> targetLeft = target.left;
            BSTNode<K, V> targetRight = target.right;
            if(parent.left != null && parent.left.key == target.key){
                parent.left = null;
            }else{
                parent.right = null;
            }
            parent.put(targetLeft, parent);
            parent.put(targetRight, parent);
        }
        return target.value;
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
