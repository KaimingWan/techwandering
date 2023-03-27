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

/**
 * <pre>
 *    Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent,
 *    with the colors in the order red, white, and blue.We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *    You must solve this problem without using the library's sort function.
 *    Example 1:
 *    Input: nums = [2,0,2,1,1,0]
 *    Output: [0,0,1,1,2,2]
 *
 *    Example 2:
 *    Input: nums = [2,0,1]
 *    Output: [0,1,2]
 *
 *    requirement: use constant extra space; scan one round
 *
 * </pre>
 *
 * @author wanshao create time is 2023/2/22
 **/
@SuppressWarnings("PMD")

public class LC75 {

  public static void main(String[] args) {
    int[] nums = {2, 2, 1};
    new Solution().sortColors(nums);

  }

  /**
   * <pre>
   *   Thought: only three colors, just loop 2 times
   *   Step:
   *   1. first loop, move 1 to left
   *   2. second loop, move 2 to right,then 1 is in middle
   *
   * </pre>
   */
  static class Solution {

    public void sortColors(int[] nums) {
      // first loop
      int zeroCursor = 0;
      for (int i = 0; i < nums.length; i++) {
        if (i == zeroCursor && nums[i] == 0) {
          zeroCursor++;
          continue;
        }

        if (nums[i] == 0) {
          nums[i] = nums[zeroCursor];
          nums[zeroCursor] = 0;
          zeroCursor++;
        }
      }

      // second loop
      int twoCursor = nums.length - 1;
      for (int i = nums.length - 1; i >= zeroCursor; i--) {
        if (i == twoCursor && nums[i] == 2) {
          twoCursor--;
          continue;
        }
        if (nums[i] == 2) {
          nums[i] = nums[twoCursor];
          nums[twoCursor] = 2;
          twoCursor--;
        }
      }
    }

  }

}
