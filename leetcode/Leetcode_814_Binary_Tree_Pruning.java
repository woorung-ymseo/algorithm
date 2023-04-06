import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


/**
 * https://leetcode.com/problems/binary-tree-pruning/
 *
 *
 */
public class Leetcode_814_Binary_Tree_Pruning {

    @Test
    void test(){
        String[] t = new String[10];

        t[0] = "23";
        t[1] = "11";
        t[2] = "21";
        t[3] = "22";
        t[4] = "123";
        t[5] = "321";
        t[6] = "231";
        t[7] = "111";
        t[8] = "2";
        t[9] = "6675";
        new ArrayList<String>().add("a");

        Map<Character, String> m = new HashMap<>();
        List<String> ss = Arrays.stream(t).sorted().collect(Collectors.toList());
        for (String a : ss) {

            final var c = a.charAt(0);

            System.out.println(a);
        }

//        for (String aa : t) {
//            final var length = aa.length();
//            final var val = m.get(length);
//
//
//            if (val != null) {
//                StringBuilder sb = new StringBuilder(val);
//                sb.append(",").append(aa);
//
//                m.put(length, sb.toString());
//            } else {
//                m.put(length, aa);
//            }
//        }
//
//
        List<String> sorted = Arrays.stream(t).sorted((o1, o2) -> o2.length() - o1.length()).collect(Collectors.toList());
//
//        StringBuilder builder = new StringBuilder();
//
//        for (String a : sorted) {
//            if (builder.indexOf(a) > -1) return false;
//
//            builder.append(a);
//        }
//
//        return false;
    }

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
