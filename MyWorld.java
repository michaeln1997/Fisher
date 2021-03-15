import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MyWorld describes a basic world for the fishing game.
 * 
 * @author Stepehen Blythe and Michael Nunes
 * @version 8/2018 --30/10/2018 
 */
public class MyWorld extends World
{
   private ScoreBoard time; // scoreboard that is is in the world.
   private Fish fish; // Fish that it is in the world.
   private int lastValue; // current last high score value of the last game.
   private int speed; // current speed of fish
   /**
     * Constructor for objects of class MyWorld.
     * adds a fisher to the world
     * adds a scoreboard to the world.
     * adds fishes to the world.
     */
   public MyWorld()
   {    
       // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
       super(800, 600, 1); 
       
       speed = 1; // starting number of the fish speed.
       
       
       time= new ScoreBoard(120); // The scoreboard construction with 120 seconds so I can add to the world.
       addObject(time, getWidth()/2, 6); //Adding the scoreboard to the world with a X location of the width divide by 2 and Y location of 6.
                                         // at the top of the world
        
       Fisher fisher = new Fisher();// The fisher construction so I can add to the world.
       addObject(fisher, getWidth()/2,32); // Adding the fisher with at the top of the world under the scoreboard.
       
       int howmanyFish = 13; // the number of fishes that we need to have in the world. 
       int ylocationFish = 110; // the start Y location for the fishes.
       while(howmanyFish>0) // check if the number of fiches are greater than 0.
       {
           int xlocationFish = Greenfoot.getRandomNumber(getWidth()); // allows to give to the fish a random number in the X location 
                                                                      // inside the width of the world
           fish = new Fish(speed); //The fish construction so I can add to the world.
           addObject(fish,xlocationFish,ylocationFish); // Adding the fish to the world with X Location of the world width and Y location
           howmanyFish--; // minus 1 to the number of fish that I need to add to the world.
           ylocationFish = ylocationFish + 38; // increase 38 to the Y location of the fish.  
       }
   }
   
   /**
     *  the scoreboard that it is in the world
     * @return the scoreboard that it is in the world
     */
   public ScoreBoard getScoreboard()
   {
        return time; // return the scoreboard that it is in the world.
   }
    
   /**
     * removes all Fish, Fishingline, and Person objects from
     *   this world. Likey useful to call when the current 
     *   game ends. 
     */
   public void removeGamePieces()
   {
        // remove all fish ...
        removeObjects(getObjects(Fish.class));
            
        //remove any lines ...
        removeObjects(getObjects(FishingLine.class));
            
        // remove any fishers ...
        removeObjects(getObjects(Fisher.class));
   }
   
   /**
    * 
    * @param caughtFish the fish to add to the world
    */
   public void addFish(Fish caughtFish)
   {
    speed = caughtFish.getSpeed() * 2;  // get the speed of the fish and multiply by 2.
    caughtFish = new Fish(speed); // The caughtFish construction with the speed of the fish
    addObject(caughtFish,Greenfoot.getRandomNumber(getWidth()+1),Greenfoot.getRandomNumber(getHeight())+140);// add the fish to the world 
                                        // with a X location random and Y location between 140 and the bottom of the world. 
   }
    
   /**
     * handle key presses, etc ...
     * Act - do whatever the world wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * remove all the game pieces is the time is less or equal to 0
     * set the current score to the high score 
     * and if the user press N start a new game
     */
   public void act()
   {
      if (time.getTimeLeft()<=0) // check if the time is less than 0.
      {
          this.removeGamePieces();  // remove all the actor from the world. 
          if(time.getScore()>time.getHighScore()) //check if the current score is higher than high score.
          { 
             time.setHighScore(time.getScore()); // this put the current score to the high score. 
             lastValue = time.getHighScore(); // gives back the high score found in this ScoreBoard
            }
          if(Greenfoot.isKeyDown("n"))// check if the user is preesing N
          {
             time= new ScoreBoard(120); // the construction of the score board
             time.setHighScore(lastValue); //allows to set the high score with last value of the last game
             addObject(time, getWidth()/2, 6);//Adding the scoreboard to the world with a X location of the width divide by 2 
                                            // Y location of 6. at the top of the world.
             speed = 1; // starting number of the fish speed.                      
        
             Fisher fisher = new Fisher(); // the construction of the fisher.
             addObject(fisher, getWidth()/2,32); // // Adding the fisher with at the top of the world under the scoreboard.
                                                  
             int howmanyFish = 13; // the number of fishes that we need to have in the world. 
             int ylocationFish = 110; // the start Y location for the fishes.
        
             while(howmanyFish>0) //  check if the number of fiches are greater than 0.
             {
                 int xlocationFish = Greenfoot.getRandomNumber(getWidth()); // allows to give to the fish a random number in the X location
                                                                            // inside the width of the world
                 addObject(new Fish(speed),xlocationFish,ylocationFish); //The fish construction so I can add to the world.
                 howmanyFish--; // minus 1 to the number of fiches that I need to add to the world.
                 ylocationFish = ylocationFish + 38; // increase 38 to the Y location of the fish. 
             }
          }
      }
   }
}
