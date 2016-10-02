package pack;

import javafx.scene.image.Image;

/**
 * Created by Adam Collins on 2016-09-27.
 */
public class Card
{
    private String suite;
    private String face;
    private int value;
    private Image img;
    private String url;
    Card(String suite, int value)
    {
        this.suite = suite;
        this.value = value;
        this.face = value+"";

            switch (value)
            {
                case 1: this.face = "ace";
                    break;
                case 11: this.face = "jack";
                    break;
                case 12: this.face = "queen";
                    break;
                case 13: this.face = "king";
                    break;
            }
        if(value==12) this.face = "queen";
        if(value==13) this.face = "king";
        loadImage();
    }

    public int getValue()
    {
        return value;
    }

    public String getFace()
    {
        return face;
    }

    public String getSuite()
    {
        return suite;
    }

    public String toString()
    {
        return face + " of " + suite+url;
    }

    public Image getImg()
    {
        return img;
    }

    private void loadImage()
    {
        url = "cards/" + getSuite() + "/" + getValue() + ".gif";

        try
        {
            img = new Image(url);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Could not find " + url);
        }


    }
}
