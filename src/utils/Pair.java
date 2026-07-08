package binaryTree;

import utils.TreeNode;

public class Pair{
    private final TreeNode key;
    private final Integer value;

    public Pair(TreeNode key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public TreeNode getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}
