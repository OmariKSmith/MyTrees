package com.btree.model;

import com.btree.Tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/* SEARCH
    Iterative-Tree-Search(x, key)
    while x ≠ NIL and key ≠ x.key then
        if key < x.key then
            x := x.left
        else
            x := x.right
        end if
    repeat
    return x
-----------------------------------------
    INSERT
    BST-Insert(T, z)
      y := NIL
    x := T.root
    while x ≠ NIL do
      y := x
      if z.key < x.key then
        x := x.left
      else
        x := x.right
      end if
    repeat
    z.parent := y
    if y = NIL then
      T.root := z
    else if z.key < y.key then
      y.left := z
    else
      y.right := z
    end if
   ---------------------------------------

   DELETE
    BST-Delete(BST, D)
     if D.left = NIL then
       Shift-Nodes(BST, D, D.right)
     else if D.right = NIL then
       Shift-Nodes(BST, D, D.left)
     else
       E := BST-Successor(D)
       if E.parent ≠ D then
         Shift-Nodes(BST, E, E.right)
         E.right := D.right
         E.right.parent := E
       end if
       Shift-Nodes(BST, D, E)
       E.left := D.left
       E.left.parent := E
     end if

     Shift-Nodes(BST, u, v)
     if u.parent = NIL then
       BST.root := v
     else if u = u.parent.left then
       u.parent.left := v
     else
       u.parent.right := v
     end if
     if v ≠ NIL then
       v.parent := u.parent
     end if
     ---------------------------------------
*/
public class BinaryTree<E extends Comparable<E>> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    public BinaryTree(){
    }

    public BinaryTree(E [] objects){
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    public static class TreeNode<E>{
        public E element;
        public TreeNode<E> left;
        public TreeNode<E> right;

        // 1.
        public TreeNode(E e) {
            element = e;
        }
    }

    @Override
    public boolean search(E e) {
        // IF tree IS empty return false
        TreeNode<E> current = root;

        while (current != null) {
            //IF e < root.element THEN search left subtree
            System.out.println("@Search current element = " + current.element) ;
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            //IF e > root.element THEN search right subtree

            else if (e.compareTo(current.element) > 0) {
                current = current.right;
                if(current != null)
                System.out.println("current.right " + current.right);
            }
            else
                return true;
        }
        return false;
    }

    @Override
    public boolean insert(E element) {
        // CREATE PARENT NODE
        // IF tree IS empty
        if(root == null){
            root = createNewNode(element);
        }

        // IF tree IS NOT empty
        // INSERT NEW NODE

        // If current node element < 0

        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            System.out.println("@insert Value of root = " + root.element);
            while (current != null)
                if (element.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (element.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else
                    return false;

                if (element.compareTo(parent.element) < 0)
                    parent.left = createNewNode(element);

                else
                    parent.right = createNewNode(element);
            }
        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e){
        System.out.println("the value of element = " + e );
        return new TreeNode<>(e);
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    protected void inorder(TreeNode<E> root){
        if(root == null) return;
        inorder(root.left);
        System.out.println("@inorder "+root.element + " is element   ");
        inorder(root.right);
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    public void postorder(TreeNode<E> root) {
        if(root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.println("@Postorder "+ root.element + " is element ");
    }

    @Override
    public void preorder() {
        System.out.println("ROOT IS  " + root.element);
        preorder(root);
    }

    public void preorder(TreeNode<E> root){
        if(root == null){
            return;
        }

        System.out.println("@Preorder "+root.element + " is element ");
        preorder(root.left);
        preorder(root.right);

    }


    @Override
    public int getSize() {
        return size;
    }

    public TreeNode<E> getRoot(){
        return root;
    }

    public ArrayList<TreeNode <E>> path(E e){
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;

        while (current != null){
            list.add(current);
            if(e.compareTo(current.element) < 0){
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0){
                current = current.right;
            }
            else
                break;
        }
        return list;
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null){
            if (e.compareTo(current.element) < 0){
                parent = current;
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else break;
        }

        if (current == null){
            return  false;
        }

        if(current.left == null){
            if(parent == null){
                root = current.right;
            }
            else {
                if(e.compareTo(parent.element) < 0){
                    root = current.right;
                }

                else{
                    parent.right = current.right;
                }
            }

        }

        else{
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while(rightMost != null){
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }
            current.element = rightMost.element;

            if(parentOfRightMost.right == rightMost){
                parentOfRightMost.right = rightMost.left;
            }
            else{
                parentOfRightMost.right = rightMost.left;
            }

        }
        size--;
        return true;
    }


    private class  InorderIterator implements Iterator<E>{
        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public InorderIterator(){
            inorder();
        }

        private void inorder(){
            inorder(root);
        }
        private void inorder(TreeNode<E> root){
            if(root == null){
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            if (current < list.size()){
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        @Override
        public void remove() {
            if(current == 0){
                throw new IllegalStateException();
            }
            delete(list.get(--current));
            list.clear();
            inorder();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }


}
