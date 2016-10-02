package pack;

import java.util.ArrayList;

/**
 * Created by Adam Collins on 2016-09-27.
 */
public class Deck
{
    ArrayList<Card> deck;
    Deck()
    {
        deck = new ArrayList(52);

        for (int i = 1; i <= 13; i++)
        {
            deck.add(new Card("clubs", i));
            deck.add(new Card("hearts", i));
            deck.add(new Card("spades", i));
            deck.add((new Card("diamonds", i)));
        }

    }

    public void printDeck()
    {
        for (Card c: deck)
            System.out.println(c);
    }

    public ArrayList<Card> getCardHand(int numOfCards)
    {
        ArrayList hand = new ArrayList<Card>();

        for (int i = 0; i < numOfCards; i++)
        {
            hand.add(deck.remove((int)(Math.random()*deck.size())));
        }
        return hand;
    }


}
