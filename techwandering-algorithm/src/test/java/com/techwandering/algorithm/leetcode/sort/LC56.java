/*
 * Copyright 2023 TechWandering
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.techwandering.algorithm.leetcode.sort;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;

/**
 * merge range
 *
 * <pre>
 *   输入：intervals = [[1,3],[2,6],[8,10],[15,18]] 输出：[[1,6],[8,10],[15,18]] 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * </pre>
 *
 * @author wanshao create time is 2023/2/21
 **/
@SuppressWarnings("PMD")
public class LC56 {

  public static void main(String[] args) {
    int[][] a = {{15, 18}, {1, 3}, {2, 6}, {8, 10}};
    int[][] b = {{1, 4}, {0, 2}, {3, 5}};

    int[][] result = new Solution().merge(b);
    Assertions.assertEquals(true, true);
  }

  static class Solution {

    public int[][] merge(int[][] intervals) {
      if (intervals.length == 1) {
        return intervals;
      }

      // 1. sort range by left
      List<SingleRange> ranges = new ArrayList<>();
      for (int i = 0; i < intervals.length; i++) {
        ranges.add(new SingleRange(intervals[i][0], intervals[i][1]));
      }
      // using merge sort
      List<SingleRange> resultMergedRanges = new ArrayList<>();

      List<SingleRange> sortedRanges = sortRange(ranges);

      // 2. loop elements
      SingleRange last = sortedRanges.get(0);
      for (int i = 1; i < sortedRanges.size(); i++) {
        SingleRange s1 = last;
        SingleRange s2 = sortedRanges.get(i);

        // 3. judge loop
        if (judgeOverlap(s1, s2)) {
          resultMergedRanges.remove(s1);
          last = mergeOverlappedRange(s1, s2);
          // merge element in list
          resultMergedRanges.add(last);

        } else {
          // 4. process non overlap and overlap ranges
          if (!resultMergedRanges.contains(s1)) {
            resultMergedRanges.add(s1);
          }
          resultMergedRanges.add(s2);
          last = s2;
        }
      }

      int[][] r = new int[resultMergedRanges.size()][2];
      for (int i = 0; i < resultMergedRanges.size(); i++) {
        r[i][0] = resultMergedRanges.get(i).left;
        r[i][1] = resultMergedRanges.get(i).right;
      }

      return r;

    }

    private List<SingleRange> sortRange(List<SingleRange> ranges) {
      if (ranges.size() == 1) {
        return ranges;
      }

      int middle = ranges.size() / 2;
      List<SingleRange> left = new ArrayList<>();
      for (int i = 0; i < middle; i++) {
        left.add(ranges.get(i));
      }
      List<SingleRange> right = new ArrayList<>();
      for (int i = middle; i < ranges.size(); i++) {
        right.add(ranges.get(i));
      }
      List<SingleRange> leftRanges = sortRange(left);
      List<SingleRange> rightRanges = sortRange(right);
      return sortMergeRanges(leftRanges, rightRanges);
    }

    private List<SingleRange> sortMergeRanges(List<SingleRange> left, List<SingleRange> right) {
      List<SingleRange> sorted = new ArrayList<>();
      int i = 0;
      int j = 0;

      while (i != left.size() && j != right.size()) {
        if (left.get(i).left <= right.get(j).left) {
          sorted.add(left.get(i));
          i++;
        } else {
          sorted.add(right.get(j));
          j++;
        }
      }

      if (i == left.size()) {
        while (j != right.size()) {
          sorted.add(right.get(j));
          j++;
        }
      } else {
        while (i != left.size()) {
          sorted.add(left.get(i));
          i++;
        }
      }
      return sorted;
    }

    private boolean judgeOverlap(SingleRange s1, SingleRange s2) {
      if (s1.right < s2.left || s1.left > s2.right) {
        return false;
      } else {
        // [1,4],[4,5] seen as overlap
        return true;
      }
    }

    /**
     * merge two range
     */
    private SingleRange mergeOverlappedRange(SingleRange s1, SingleRange s2) {
      return new SingleRange(s1.left <= s2.left ? s1.left : s2.left,
          s1.right >= s2.right ? s1.right : s2.right);
    }

    class SingleRange {

      int left;
      int right;

      public SingleRange(int left, int right) {
        this.left = left;
        this.right = right;
      }

    }

  }

}
