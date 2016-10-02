package pack;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {


    static Deck d;
    static Label handType;
    static ArrayList<Card> hand;
    static BorderPane bPane;
    static HBox cards, ui;
    static Button dealHand;
    @Override
    public void start(Stage primaryStage) throws Exception
    {

        d = new Deck();
        hand = d.getCardHand(5);

        bPane = new BorderPane();

        cards = new HBox(10);
        cards.setPadding(new Insets(10,10,10,10));

        ui = new HBox(10);
        cards.setPadding(new Insets(15,10,10,10));


        primaryStage.setTitle("Random Poker Hands");
        dealHand = new Button("Deal");
        handType = new Label(bestHand(hand));


        dealHand.setOnAction((event)-> {
            deal();



        });
        cards.getChildren().addAll(new ImageView(hand.get(0).getImg()),
                new ImageView(hand.get(1).getImg()), new ImageView(hand.get(2).getImg()),
                new ImageView(hand.get(3).getImg()),new ImageView(hand.get(4).getImg()));

        ui.getChildren().addAll(dealHand,handType);


        bPane.setPadding(new Insets(10,10,10,10));
        Scene scene = new Scene(bPane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        bPane.setCenter(cards);
        bPane.setBottom(ui);
        primaryStage.show();

    }

    private static String bestHand(ArrayList<Card> hand)
    {
        String type = highCard(hand);
        System.out.println(straight(hand));
        if(!pairs(hand).equals("none"))
        {
            type = pairs(hand);
        }
        if(!straight(hand).equals("none"))
            type = straight(hand);

        return type;



    }

    private static String pairs(ArrayList<Card> hand)
    {
        String type = "";
        int highestNumOfCards = 1;
        Card bestCard = hand.get(0);
        for (int i = 0; i <hand.size(); i++)
        {

            int numOfCards = 1;
            for (int j = i+1; j < hand.size(); j++)
            {
                if(hand.get(i).getFace().equals(hand.get(j).getFace()))
                    numOfCards++;

            }
            if(numOfCards>highestNumOfCards)
            {
                highestNumOfCards = numOfCards;
                bestCard = hand.get(i);
            }
        }
        switch (highestNumOfCards)
        {
            case 2: return "pair of "+bestCard.getFace()+"s";
            case 3: return "triple "+bestCard.getFace()+"s";
            case 4: return "full house ";
        }
        return "none";

    }
    private static String highCard(ArrayList<Card> hand)
    {
        String type = "";
        Card highestCard = hand.get(0);
        for (Card c: hand)
        {
            if(c.getValue()==1)
            {
                highestCard = c;
                break;
            }
            if(c.getValue()>highestCard.getValue())
                highestCard = c;
        }
        type = highestCard.getFace()+" high card";
        return type;

    }

    private static String straight(ArrayList<Card> hand)
    {
        ArrayList<Card> sortedHand = new ArrayList<Card>();
        for(Card c: hand)
            sortedHand.add(c);

        boolean inOrder;
        do
        {
            inOrder = true;
            for (int i = 0; i < sortedHand.size() - 1; i++)
            {
                int j = i + 1;
                Card c1 = sortedHand.get(i);
                Card c2 = sortedHand.get(j);
                if (c1.getValue() > c2.getValue())
                {
                    Card tempCard = c2;
                    sortedHand.set(j, c1);
                    sortedHand.set(i, tempCard);
                    inOrder = false;
                }
            }
        }while (!inOrder);

        Card lastCard = sortedHand.get(sortedHand.size()-1);

        for(int i = sortedHand.size()-2; i>=0; i--)
        {
            Card c = sortedHand.get(i);
            //System.out.println((c.getValue())+ "!="+(lastCard.getValue()));
            if(c.getValue()+1!=lastCard.getValue())
                return "none";
            lastCard = c;
        }
        return "straight from "+sortedHand.get(0).getFace()+" to "+sortedHand.get(sortedHand.size()-1).getFace();
    }

    public static void deal()
    {
        d = new Deck();
        hand = d.getCardHand(5);
        handType = new Label(bestHand(hand));

        cards.getChildren().remove(0,cards.getChildren().size());
        ui.getChildren().remove(0,2);
        cards.getChildren().addAll(new ImageView(hand.get(0).getImg()), new ImageView(hand.get(1).getImg()), new ImageView(hand.get(2).getImg()),new ImageView(hand.get(3).getImg()),new ImageView(hand.get(4).getImg()));
        ui.getChildren().addAll(dealHand, handType);

        bPane.setCenter(cards);
        bPane.setBottom(ui);
    }

    private void autoPlay()
    {
        boolean running = true;
        while (running)
        {

        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
