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

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <pre>
 * ref: https://leetcode.cn/problems/sort-list/
 * requirement: time O(nlogn), storage O(1)
 * </pre>
 *
 * @author wanshao create time is 2023/2/19
 **/
@Slf4j
@SuppressWarnings("PMD")
public class LC148 {

  @Test
  void LC148TestBottomUp() {
    ListNode listNode = new ListNode(3);
    ListNode head = listNode;
    int maxLen = 8;
    for (int i = 0; i < maxLen; i++) {
      listNode.next = new ListNode(RandomUtils.nextInt(0, 20));
      listNode = listNode.next;
    }

    ListNode result = new Solution().sortList(head);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < maxLen; i++) {
      sb.append(result.val).append(",");
      result = result.next;
    }
    log.info("Result is " + sb);
    Assertions.assertThat(true).isEqualTo(true);
  }

  @Test
  void LC148TestRecursion() {
    ListNode listNode = new ListNode(3);
    ListNode head = listNode;
    int maxLen = 8;
    for (int i = 0; i < maxLen; i++) {
      listNode.next = new ListNode(RandomUtils.nextInt(0, 20));
      listNode = listNode.next;
    }

    ListNode result = new Solution().recursionSort(head);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < maxLen; i++) {
      sb.append(result.val).append(",");
      result = result.next;
    }
    log.info("Result is " + sb);
    Assertions.assertThat(true).isEqualTo(true);
  }

  public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

  }

  class Solution {

    public ListNode recursionSort(ListNode head) {
      if (head == null || head.next == null) {
        return head;
      }

      ListNode slow = head;
      ListNode fast = head;
      while (fast != null) {
        fast = fast.next;
        // pay attention to the judge condition
        if (fast != null && fast.next != null) {
          fast = fast.next;
        } else {
          // 2 elements, slow point to the first, fast point to the fast
          break;
        }

        slow = slow.next;
      }

      ListNode h1 = head;
      ListNode h2 = slow.next;
      // set left list end
      slow.next = null;
      ListNode h1Sorted = recursionSort(h1);
      ListNode h2Sorted = recursionSort(h2);
      return merge(h1Sorted, h2Sorted);
    }

    public ListNode sortList(ListNode head) {
      if (head == null) {
        return head;
      }
      int len = 0;
      ListNode node = head;
      // get list length
      while (node != null) {
        len++;
        node = node.next;
      }

      // use dummy head to relate new merged head
      ListNode dummyHead = new ListNode(0, head);

      for (int subLen = 1; subLen < len; subLen <<= 1) {
        ListNode prev = dummyHead;
        ListNode cur = dummyHead.next;
        while (cur != null) {
          ListNode head1 = cur;
          for (int i = 1; i < subLen && cur.next != null; i++) {
            // move to sublist end,then next is head2
            cur = cur.next;
          }

          // set head2
          ListNode head2 = cur.next;
          // make head1 end (important!!!!)
          cur.next = null;
          // point cur to head2
          cur = head2;

          // iterator head2 to find the next round position and set subList end
          for (int i = 1; i < subLen && cur != null && cur.next != null; i++) {
            cur = cur.next;
          }

          // next point to the parts after head2
          ListNode next = null;
          if (cur != null) {
            // last node's next is remaining part
            next = cur.next;
            // set head2 end (important!!!!)
            cur.next = null;
          }

          ListNode merged = merge(head1, head2);
          prev.next = merged;
          // loop to last node to support prev point to new merged node list
          while (prev.next != null) {
            prev = prev.next;
          }

          // point to the remaining part
          cur = next;
        }
      }

      return dummyHead.next;

    }

    /**
     * merge two ordered list. just compare head and add to new nodeList to generate new ordered
     * list
     */
    public ListNode merge(ListNode head1, ListNode head2) {
      ListNode cur1 = head1;
      ListNode cur2 = head2;
      ListNode rsNode = new ListNode(0);
      ListNode rsNodeHead = rsNode;
      while (cur1 != null && cur2 != null) {
        if (cur1.val < cur2.val) {
          rsNode.next = cur1;
          rsNode = rsNode.next;
          cur1 = cur1.next;

        } else {
          rsNode.next = cur2;
          rsNode = rsNode.next;
          cur2 = cur2.next;
        }
      }

      if (cur1 == null) {
        rsNode.next = cur2;
      } else {
        rsNode.next = cur1;
      }

      rsNodeHead = rsNodeHead.next;
      return rsNodeHead;
    }

  }

}
