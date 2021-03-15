import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * FishingLine is a line attached to a Fisher - it:
 *      * grows to bottom of screen
 *      * returns back to sender
 * 
 * @author Stephen Blythe
 * @version 8/2018
 */
public class FishingLine extends Actor
{
    private int size;       // current line length (pixels)
    private int growthRate;// how fast (in what direction) line is growing
    /**
     * constructs a fishing line of default length 1 and moves to 
     * bottom of screen at rate of two pixels per act call. 
     */
    public FishingLine()
    {
        size=1;       // as small as possible to start with
        growthRate=2; // grow towards bottom of screen
        redraw();     // generte the line's image
    }
    
    // redraws the imge for this fishing line, whic is a narrow
    //    vertical bar of the current size. 
    private void redraw()
    {
         // build an image that is just big enough 
        GreenfootImage img=new GreenfootImage(1,size);
        
        // fill the image with gray color
        img.setColor(Color.GRAY);
        img.fill();
        
        // use the image we just built as this object's image. 
        setImage(img);
    }
    
    /**
     * returns the existence of this Line in the world
     * 
     * @return true if this line is in a world, false otherwise
     */
    public boolean inWorld()
    {
        return getWorld()!=null;
    }
    
    /**
     * returns true if this line has been reeled out yet. 
     * 
     * @return true if the line has been reeeled out, false otherwise
     */
    public boolean isOut()
    {
        return inWorld();
    }

    /**
     * the x locator for the "hook" end of this line
     * 
     * @return the x loxation of the end of the line. 
     */
    public int endX()
    {
        return getX();
    }
    
    /**
     * the y locator for the "hook" end of this line
     * 
     * @return the y loxation of the end of the line. 
     */
    public int endY()
    {
        // y location is middle of image, need to add 1/2 of image to y
        return getY()+size/2;
    }
    
    /**
     * start sending the line out, even if it is already returning. 
     */
    public void cast()
    {
        growthRate=2;
    }
    
    /**
     * start reeling the line back in. 
     */
    public void startReelback()
    {
        growthRate= -Math.abs(growthRate);
    }
    
    /**
     * grow the line toward the bottom or shrink it toward the top,
     *   based on it's current growthRate
     */
    public void act() 
    {
        size+=growthRate; // move the line by this much - might be down or up
        
        // if the line hs no size left, renove it. 
        if (size<=0)
        {
            getWorld().removeObject(this); // remove this line from the world
            return; // no need to do anything else!
        }
        
        // move the line so it apppears to grow or shrink properly 
        setLocation(getX(), getY()+growthRate/Math.abs(growthRate));
        redraw();
        
        // if we're at bottom of world, start moving line back up. 
        if (getY()+size/2 >= getWorld().getHeight()-1)
            //growthRate=-growthRate;
            startReelback();
    }    
}
