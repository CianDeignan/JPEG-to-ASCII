package com.company;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class toASCII
{
    private final String density = " .:-=+*#%@"; // "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\|()1{}[]?-_+~<>i!lI;:,"^`'."
    private int[][] pixelValues;
    private int[][] red;
    private int[][] blue;
    private int[][] green;
    private int[][] averaged;
    private String[][] ASCII;

    public toASCII(File testImageF)
    {
        //Convert the image to a java image
        BufferedImage testImageBI = null;
        try {
            testImageBI = ImageIO.read(testImageF);
        }
        catch (IOException e) {
        }

        //set the arrays lengths equal to how large the picture is
        pixelValues = new int[testImageBI.getHeight()][testImageBI.getWidth()];
        red = new int[testImageBI.getHeight()][testImageBI.getWidth()];
        blue = new int[testImageBI.getHeight()][testImageBI.getWidth()];
        green = new int[testImageBI.getHeight()][testImageBI.getWidth()];
        averaged = new int[testImageBI.getHeight()][testImageBI.getWidth()];
        ASCII = new String[testImageBI.getHeight()][testImageBI.getWidth()];

        //convert the pixel values to colors using bitmaps, if the average of this (how bright the pixel is) is bigger
        //than half brightness, make it a 0, otherwise make it a space
        for(int y = 0; y < testImageBI.getHeight(); y++)
        {
            for(int x = 0; x < testImageBI.getWidth(); x++)
            {
                pixelValues[y][x] = testImageBI.getRGB(x,y);
                blue[y][x] = pixelValues[y][x] & 0xff;
                green[y][x] = (pixelValues[y][x] & 0xff00) >> 8;
                red[y][x] = (pixelValues[y][x] & 0xff0000) >> 16;
                averaged[y][x] = (red[y][x] + blue[y][x] + green[y][x])/3;

                if(averaged[y][x] <= (255/10)*1)
                {
                    ASCII[y][x] = density.substring(0,1) + " ";
                }
                else if(averaged[y][x] <= (255/10)*2)
                {
                    ASCII[y][x] = density.substring(1,2) + " ";
                }
                else if(averaged[y][x] <= (255/10)*3)
                {
                    ASCII[y][x] = density.substring(2,3) + " ";
                }
                else if(averaged[y][x] <= (255/10)*4)
                {
                    ASCII[y][x] = density.substring(3,4) + " ";
                }
                else if(averaged[y][x] <= (255/10)*5)
                {
                    ASCII[y][x] = density.substring(4,5) + " ";
                }
                else if(averaged[y][x] <= (255/10)*6)
                {
                    ASCII[y][x] = density.substring(5,6) + " ";
                }
                else if(averaged[y][x] <= (255/10)*7)
                {
                    ASCII[y][x] = density.substring(6,7) + " ";
                }
                else if(averaged[y][x] <= (255/10)*8)
                {
                    ASCII[y][x] = density.substring(7,8) + " ";
                }
                else
                {
                    ASCII[y][x] = density.substring(8,9) + " ";
                }

            }
        }

        String out = "";
        for (int Y = 0; Y < ASCII.length; Y++)
        {
            for (int X = 0; X < ASCII[Y].length; X++)
            {
                out += ASCII[Y][X];
            }
            out += "\n";
        }
        System.out.println(out);


    }
}
