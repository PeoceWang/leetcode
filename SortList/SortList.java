//============================================================================
// Sort List 
// Sort a linked list in O(n log n) time using constant space complexity.
//============================================================================

#include <iostream>
using namespace std;

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    class Pair {
        ListNode frontHead;
        ListNode backHead;
        Pair(ListNode f, ListNode b) {
            frontHead = f;
            backHead = b;
        }
    }

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) return head;
        Pair splited = split(head);
        splited.frontHead = mergeSort(splited.frontHead);
        splited.backHead = mergeSort(splited.backHead);
        return merge(splited);
    }

    private ListNode merge(Pair splitted) {
        ListNode frontNode = splitted.frontHead, backNode = splitted.backHead;
        ListNode head = new ListNode(-1), curNode = head;
        while (frontNode != null || backNode != null) {
            if (backNode == null || (frontNode!= null && frontNode.val < backNode.val)) {
                curNode.next = frontNode;
                frontNode = frontNode.next;
                curNode = curNode.next;
            }
            else {
                curNode.next = backNode;
                backNode = backNode.next;
                curNode = curNode.next;
            }
        }
        return head.next;
    }

    private Pair split(ListNode head) {
        ListNode firstHead = new ListNode(-1), frontNode = firstHead;
        ListNode backHead = new ListNode(-1), backNode = backHead;
        while (head != null) {
            frontNode.next = head;
            head = head.next;
            frontNode = frontNode.next;
            if (head == null)
                break;
            backNode.next = head;
            head = head.next;
            backNode = backNode.next;
        }
        frontNode.next = null;
        backNode.next = null;
        return new Pair(firstHead.next, backHead.next);
    }

    private static void print(ListNode head) {
        for (; head != null; head = head.next)
            System.out.print(head.val + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        ListNode head;

        head = new ListNode(4);
        head.next = new ListNode(3);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        print(sol.sortList(head));

        head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(4);
        print(sol.sortList(head));

        head = new ListNode(2);
        head.next = new ListNode(1);
        print(sol.sortList(head));
    }
}