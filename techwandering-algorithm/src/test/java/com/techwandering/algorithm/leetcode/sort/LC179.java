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
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;

/**
 * <pre>
 *   Find the max value after rebuild numbers in array
 *   input：nums = [3,30,34,5,9] output："9534330"
 * </pre>
 *
 * @author wanshao create time is 2023/2/22
 **/
public class LC179 {

  /**
   * <pre>
   *   try string join and compare. 3,30 will compare 330 and 303, use the larger one
   * </pre>
   */

  public static void main(String[] args) {
    int[] nums = {3, 30, 34, 5, 9};
    Assertions.assertEquals("9534330", new Solution().largestNumber(nums));
  }

  static class Solution {

    public String largestNumber(int[] nums) {
      return Arrays.stream(Arrays.stream(nums).boxed().toArray()).map(String::valueOf)
          .sorted((x, y) -> (y + x).compareTo(x + y)).collect(
              Collectors.collectingAndThen(Collectors.joining(""),
                  s -> s.charAt(0) == '0' ? "0" : s));
    }

  }

}
