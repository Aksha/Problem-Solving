package com.madhukar;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by madhukm on 8/23/17.
 *
 *
 * Given a binary tree where all the right nodes are either empty or leaf nodes, flip it upside down
 * and turn it into a tree with left leaf nodes.
 * In the original tree, if a node has a right child, it also must have a left child.
 *
 * for example, turn these:
 *
 *        1                1
 *       / \              / \
 *      2   3            2   3
 *     /
 *    4
 *   / \
 *  5   6
 *
 * into these:
 *
 *        1               1
 *       /               /
 *      2---3           2---3
 *     /
 *    4
 *   /
 *  5---6
 *
 * where 5 is the new root node for the left tree, and 2 for the right tree.
 * oriented correctly:
 *
 *     5                  2
 *    / \                / \
 *   6   4              3   1
 *        \
 *         2
 *        / \
 *       3   1
 *
 *
 */
public class FlipTreeUpsideDown {
    public static class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode flipTreeUpSideDown(TreeNode root) {
        if( root == null || root.left == null || root.right == null) {
            return root;
        }
        return flipTreeUpSideDown(root, null, null);
    }

    private static TreeNode flipTreeUpSideDown(final TreeNode root, final TreeNode left, final TreeNode right) {
        TreeNode newRoot = root.left;
        TreeNode newRootLeft = root.right;
        TreeNode newRootRight = root;
        root.left = left;
        root.right = right;
        if( newRoot == null) {
            return root;
        }
        return flipTreeUpSideDown(newRoot, newRootLeft, newRootRight);
    }


    public static void main(String[] args) {
        /**
         *     1
         *    / \
         *   2   3
         */
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Before Flipping:");
        printLevelWise(root);
        TreeNode flipped = flipTreeUpSideDown(root);
        System.out.println("After Flipping:");
        printLevelWise(flipped);


        TreeNode child = new TreeNode(5, new TreeNode(6), new TreeNode(7));
        root = new TreeNode(1, new TreeNode(2, child, new TreeNode(4)), new TreeNode(3));
        System.out.println("Before Flipping:");
        printLevelWise(root);
        flipped = flipTreeUpSideDown(root);
        System.out.println("After Flipping:");
        printLevelWise(flipped);


        child = new TreeNode(4, new TreeNode(5), new TreeNode(6));
        root = new TreeNode(1, new TreeNode(2, child, null), new TreeNode(3));
        System.out.println("Before Flipping:");
        printLevelWise(root);
        flipped = flipTreeUpSideDown(root);
        System.out.println("After Flipping:");
        printLevelWise(flipped);
    }

    private static List<List<TreeNode>> traverseLevels(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<TreeNode>> levels = new LinkedList<>();

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            List<TreeNode> level = new ArrayList<>(nodes.size());
            levels.add(level);

            for (TreeNode node : new ArrayList<>(nodes)) {
                level.add(node);
                if(node == null) {
                    nodes.poll();
                    continue;
                }
                //if (node.left != null) {
                    nodes.add(node.left);
                //}
                //if (node.right != null) {
                    nodes.add(node.right);
                //}
                nodes.poll();
            }
        }
        return levels;
    }

    public static void printLevelWise(TreeNode root) {
        List<List<TreeNode>> levels = traverseLevels(root);
        int rows = levels.size();
        for (List<TreeNode> level : levels) {
            for(int i=0;i<rows;i++) {
                System.out.print(" ");
            }
            rows--;
            for (TreeNode node : level) {
                System.out.print(node == null?"n ":node.val + " ");
            }
            System.out.println();
        }
    }
}
