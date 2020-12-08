package day20201208;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。
 *
 * 形式上，斐波那契式序列是一个非负整数列表 F，且满足：
 *
 * 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
 * F.length >= 3；
 * 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
 * 另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
 *
 * 返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。
 *
 *  
 *
 * 示例 1：
 *
 * 输入："123456579"
 * 输出：[123,456,579]
 * 示例 2：
 *
 * 输入: "11235813"
 * 输出: [1,1,2,3,5,8,13]
 * 示例 3：
 *
 * 输入: "112358130"
 * 输出: []
 * 解释: 这项任务无法完成。
 * 示例 4：
 *
 * 输入："0123"
 * 输出：[]
 * 解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
 * 示例 5：
 *
 * 输入: "1101111"
 * 输出: [110, 1, 111]
 * 解释: 输出 [11,0,11,11] 也同样被接受。
 *  
 *
 * 提示：
 *
 * 1 <= S.length <= 200
 * 字符串 S 中只含有数字。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/12/8 19:42
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> res = solution.splitIntoFibonacci("0123");
        System.out.println(res);
    }
}

class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> list = new ArrayList<>();
        backtrack(list, 0, 0, S);
        return list;
    }

    public boolean backtrack(List<Integer> list, int pre, int index, String s) {
        if (index == s.length()) {
            return list.size() > 2;
        }
        int sum = 0;
        for (int i = index; i < s.length(); i++) {
            int a = s.charAt(i) - '0';
            if (i > index && sum == 0) {
                return false;
            }
            sum = 10 * sum + a;
            if (list.size() >= 2) {
                if (sum < pre) {
                    continue;
                }
                if (sum > pre) {
                    break;
                }
            }
            int p = 0;
            if (list.size() != 0) {
                p = list.get(list.size() - 1);
            }
            list.add(sum);
            if (backtrack(list, p + sum, i + 1, s)) {
                return true;
            } else {
                list.remove(list.size() - 1);
            }
        }
        return false;
    }
}