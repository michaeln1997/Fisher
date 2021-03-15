import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Fisher can move left or right and has a FishingLine associated
 * with it. 
 * 
 * @author Michael Nunes 
 * @version 30/10/2018
 */
public class Fisher extends Actor
{
   private FishingLine fishingLine; // the instance variable to create a fishing line construction
   /**
     * constructs a fisher of default.
     * the Fisher has a Fishing line to cast.
     */
   public Fisher ()
   {
    fishingLine = new FishingLine(); // The FishingLine construction to be added to the fisher. 
   }
  
   /**
     * Act - do whatever the Fisher wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * adds a fishing line to the fisher.
     * move the fisher to the right by one pixel
     * moves the fisher to the left by one pixel
     * cast the line
     */
    public void act() 
    {
        if (fishingLine.isOut()==false) //Check if the fishing line is not out in the world. 
        {
           if (Greenfoot.isKeyDown("left"))// Check if the left button gets pressed by the user 
           {
            this.move(-1); // the fisher is moving to the left by one pixel
           }
           if (Greenfoot.isKeyDown("right")) //check if the right button  gets pressed by the user
           {
            this.move(1); //the fisher is moving to the right by one pixel
           }
           if (Greenfoot.isKeyDown("space")) // chech if the space bar button gets preesed by the user
           {
               getWorld().addObject(fishingLine, this.getX(), this.getY()); // add a fishing line to the fisher in the world 
                                                                            // with the same location of the fisher
               fishingLine.cast(); //start sending the line out, even if it is already returning. 
           }
        }  
    }
}
