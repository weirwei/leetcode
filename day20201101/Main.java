package day20201101;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *
 * 说明：
 *
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * 示例 2：
 *
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weirwei 2020/11/1 22:33
 */
public class Main {
    public static void main(String[] args) {
//        String s = "pineapplepenapple";
//        String[] in = new String[]{"apple", "pen", "applepen", "pine", "pineapple"};
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String[] in = new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
//        String s = "catsanddog";
//        String[] in = new String[]{"cat", "cats", "and", "sand", "dog"};
        List<String> wordDict = new ArrayList<>();
        Collections.addAll(wordDict, in);
        List<String> res = new Solution().wordBreak(s, wordDict);
        System.out.println(res);
    }
}

class Solution {
    private final List<String> res = new ArrayList<>();
    private HashSet<String> wordDictSet;
    private boolean[] dp;
    public List<String> wordBreak(String s, List<String> wordDict) {
        wordDictSet = new HashSet<>(wordDict);

        dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j <= s.length(); j++) {
                if (wordDictSet.contains(s.substring(i, j)) && dp[i]) {
                    dp[j] = true;
                }
            }
        }
        if (!dp[s.length()]) return res;
        fun(s, 0, "", "");
        return res;
    }

    private void fun(String s, int start, String words, String tmp) {
        if (s.length() == start) {
            words += tmp;
            res.add(words);
            return;
        } else if (!tmp.isEmpty()){
            words += tmp + " ";
        }
        int i = start;
        while (i <= s.length()) {
            tmp = s.substring(start, i);
            if (wordDictSet.contains(tmp) && dp[i]) {
                fun(s, i, words, tmp);
            }
            i++;
        }

    }
}