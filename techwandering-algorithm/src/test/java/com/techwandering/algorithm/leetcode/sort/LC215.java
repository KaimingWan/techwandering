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

import java.util.Arrays;

/**
 * Kth Largest Element in an array.
 *
 * @author wanshao create time is 2023/2/23
 **/
public class LC215 {

  class Solution {

    public int findKthLargest(int[] nums, int k) {
      Arrays.sort(nums);
      int n = nums.length;
      return nums[n - k];
    }

  }

}
