package com.java.redblack;

public class RBTree<T extends Comparable<T>> {
    private RBTNode<T> mRoot; // 根节点
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public class RBTNode<T extends Comparable<T>> {
        boolean color; // 颜色
        T key; // 关键字(键值)
        RBTNode<T> left; // 左孩子
        RBTNode<T> right; // 右孩子
        RBTNode<T> parent; // 父结点

        public RBTNode(T key, boolean color, RBTNode<T> parent, RBTNode left, RBTNode right){
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public T getKey() {
            return key;
        }

        @Override
        public String toString() {
            return "" + key + (this.color == RED ? "(R)":"B");
        }
    }

    public RBTree(){
        this.mRoot = null;
    }

    private RBTNode<T> parentOf(RBTNode<T> node) {
        return node != null? node.parent : null;
    }

    private boolean colorOf(RBTNode<T> node) {
        return node != null ? node.color : BLACK;
    }

    private boolean isRed(RBTNode<T> node) {
        return ((node != null) &&(node.color == RED)) ? true : false;
    }

    private boolean isBlack(RBTNode<T> node){
        return !isRed(node);
    }

    private void setBlack(RBTNode<T> node) {
        if (node != null){
            node.color = BLACK;
        }
    }

    private void setRed(RBTNode<T> node) {
        if (node != null) {
            node.color = RED;
        }
    }

    private void setParent(RBTNode<T> node, RBTNode<T> parent){
        if (node != null) {
            node.parent = parent;
        }
    }

    private void setColor(RBTNode<T> node, boolean color){
        if (node != null){
            node.color = color;
        }
    }

    /**
     * 前序遍历“红黑树”
     * @param tree
     */
    private void preOrder (RBTNode tree){
        if (tree != null){
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /**
     * 中序遍历红黑树
     * @param tree
     */
    private void inOrder(RBTNode tree){
        if (tree != null){
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.right);
        }
    }

    public void inOrder(){
        inOrder(mRoot);
    }

    /**
     * 后序遍历红黑树
     * @param tree
     */
    private void postOrder(RBTNode tree){
        if (tree != null){
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }

    public void postOrder(){
        postOrder(mRoot);
    }

    /**
     * 递归实现 查找红黑树中键值为key的节点
     */
    private RBTNode<T> search(RBTNode<T> x, T key) {
        if (x == null) {
            return x;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            return search(x.left, key);
        }else if (cmp > 0) {
            return search(x.right, key);
        }else {
            return x;
        }
    }

    public RBTNode<T> search(T key) {
        return  search(mRoot, key);
    }

    /**
     * 非递归实现 查找红黑树中键值为key的节点
     */
    private RBTNode<T> iterativeSearch(RBTNode<T> x, T key) {
        while (x != null){
            int cmp = key.compareTo(x.key);

            if (cmp < 0) {
                x = x.left;
            }else if (cmp > 0){
                x = x.right;
            }else {
                return x;
            }
        }
        return x;
    }

    public RBTNode<T> iterativeSearch(T key){
        return iterativeSearch(mRoot, key);
    }

    /**
     * 查找最小结点，返回tree为根结点的红黑树的最小结点
     * @param tree
     */
    private RBTNode<T> minimum(RBTNode<T> tree) {
        if (tree == null){
            return null;
        }
        while (tree.left != null){
            tree = tree.left;
        }
        return tree;
    }

    public T minimun(){
        RBTNode<T> p = minimum(mRoot);
        if (p != null) {
            return p.key;
        }
        return null;
    }

    /**
     * 查找最大结点，返回tree为根结点的红黑树的最大结点
     * @param tree
     */

    private RBTNode<T> maximum(RBTNode<T> tree) {
        if (tree == null){
            return null;
        }

        while (tree.right != null){
            tree = tree.right;
        }
        return tree;
    }

    public T maximum() {
        RBTNode<T> p = maximum(mRoot);
        if (p != null){
            return p.key;
        }
        return null;
    }

    /**
     * 找出结点x 的后继结点。即查找红黑树中数据值大于该结点的最小结点
     * @param  x
     */

    public RBTNode<T> successor (RBTNode<T> x){
        // 如果x存在右孩子，则x的后继结点为以右孩子为根的子树的最小结点
        if (x.right != null){
            return minimum(x.right);
        }

        // 如果 x 没有右孩子。则x有以下两种可能：
        // 1. x是一个左孩子，则x的后继结点为它的父节点
        // 2. x是一个右孩子，则查找x的最低的父节点，并且该父节点要具有左孩子
        // 找到这个最低的父结点就是x的后继结点

        RBTNode<T> y = x.parent;
        while ((y != null) && (x == y.right)){
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
     *@param x
     */
    public RBTNode<T> predcessor(RBTNode<T> x) {
        return null;
    }





}
