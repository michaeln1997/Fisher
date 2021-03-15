import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Fish moves left to right and the right to left, and the left to right ...
 *   ... until it touched the hook end of a FishingLine. at that point, it just 
 *   moves with the line. 

 * @author Michael Nunes 
 * @version 30/10/2018
 */
public class Fish extends Actor
{
   private int normalSpeed; // current speed of the fish.
   private int currYValue; // current Value of the fish.
   
   /**
    * constructor to build a "default" scoreboard
    * 
    * @param startSpeed the amount of speed to start the fish move.
    */
   public Fish (int startSpeed)
   {
       normalSpeed = startSpeed; // we start with the requested amount of speed
       if (normalSpeed<=8) // check if the normal speed is less than 8.
       {
           normalSpeed = normalSpeed; // normal speed is equal the requested amount of speed
       }
       else // check if the normal speed is bigger than 8
       {
           normalSpeed = 8; // the starting value is 8;
       }
   }
   
   /**
    * give back the current speed.
    * @return return the current speed of the fish. 
    */
   public int getSpeed()
   {
       return this.normalSpeed; // return the current speed of the fish. 
   }
   
   /**
     * When the fish is already in the world, get his Y location and divided by 100 and then multiply by 100.
     * @param W is the world which this object is added it.
     */
   protected void addedToWorld(World w)// W is the world which this object is added it.
   {                
       currYValue = (this.getY()/100)*100; // the value of the fish when they are already in the world.
   }
   
   /**
    * Act - do whatever the fish wants to do. This method is called whenever
    * the 'Act' or 'Run' button gets pressed in the environment.
    * turns fish to 180 or 90 degrees 
    * move the fish with the normal speed.
    * adds fish to the world 
    * remove fish from the world
    * make the fishing line to come back to the fisher.
    */
   public void act() 
   {
      this.move(normalSpeed); // the fish is moving with the start amount of speed.
      if ((getX() <=0 || getX() >= getWorld().getWidth() -1 ))// check if the X location of the fish is less or equal to 0.
                                                              // or check if the X location is greater or equal to the width of the world.
      {
          this.turn(180); // the fish is turning 180 degress
      }
      FishingLine touch = (FishingLine) this.getOneIntersectingObject(FishingLine.class); // check if fishing Line is intersecting the fish.
      if (touch!=null && touch.endY()+20>=this.getY() && touch.endY()-20 <= this.getY()) // checks if the fish Y location is within 20
                                                                                          // and Y location of the end of the fishing Line.
      {
        if(this.getRotation() == 0) // check if the fish has a rotation of 0
        {
            this.turn(-90); // turn the fish to -90 degress
        }
        if (this.getRotation()==180) // check if the fish has a rotation of 180
        {
            this.turn(90); // turn the fish to 90 degress
        }
        this.setLocation(touch.getX(),touch.endY());  // the fish get a setlocation of the X location of the fishing line
                                                      // and the end of the Y location of the fishing line/
        touch.startReelback(); // when the fishing line get intersecting with the fish, start reeling back the fishingline to the fisher.
      }
      if (getY()<=32) // check if the Y location of the fish is less than or equal to 32.
      {
            MyWorld w = (MyWorld) getWorld();// allows to call methods from class MyWorld.
            w.addFish(this); // I am calling the method addFish from my world.
            ScoreBoard time3 = w.getScoreboard(); //Allows to call a methods from class Scoreboard.
            time3.incScore(currYValue); // increase the scoreboaard by currYValue in the world.
            getWorld().removeObject(this);// the fish gets remove from the world.
      }
   }      
}    

