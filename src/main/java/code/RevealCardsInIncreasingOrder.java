package code;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * https://leetcode.com/problems/reveal-cards-in-increasing-order/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-12-03 11:01
 */
public class RevealCardsInIncreasingOrder {

    /**
     * construct the deck with reversed process in descending order
     *
     * 1. take bottom card to the top
     * 2. put next card on the top
     *
     * @param deck
     * @return
     */
    public int[] deckRevealedIncreasing(int[] deck) {
        int len = deck.length;
        Deque<Integer> deque = new ArrayDeque<>();

        Arrays.sort(deck);
        for(int i = len - 1; i >=0; i--) {
            int card = deck[i];
            if(deque.size() > 1) {
                deque.addFirst(deque.removeLast());
            }
            deque.addFirst(card);
        }

        int[] result = new int[len];
        for(int i = 0; i < len; i++) {
            result[i] = deque.removeFirst();
        }
        return result;
    }

}
