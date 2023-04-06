import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * https://leetcode.com/problems/binary-tree-pruning/
 *
 *
 */
public class Leetcode_814_Binary_Tree_Pruning {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return root;

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if (root.left == null && root.right == null && root.val == 0) return null;

        return root;
    }

    @Test
    public void pruneTree() {

        // given
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);

        // when
        final var result = pruneTree(root);

        // then
        assertAll(
                () -> assertEquals(result.val, 1)
                , () -> assertNull(result.left)
                , () -> assertEquals(result.right.val, 0)
                , () -> assertEquals(result.right.right.val, 1)
                , () -> assertNull(result.right.left)
                , () -> assertNull(result.right.right.left)
                , () -> assertNull(result.right.right.right)
        );
    }
}
