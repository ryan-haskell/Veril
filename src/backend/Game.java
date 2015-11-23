package backend;

import backend.Global.Direction;
import backend.interfaces.Display;
import backend.interfaces.Input;
import backend.interfaces.Input.*;

/**
 * Game - ties input, output, and world together.
 * Created by ryan on 9/24/15.
 */
public class Game
{
    private Display display;
    private Input input;
    private World world;
    
    private boolean runGame, inAnimation, inputEnabled;

    public Game(Display display, Input input)
    {
        //  Set up game
        this.display = display;
        this.input = input;
        
        display.setGame(this);
        this.world = new World();

        this.start();
    }
    
    //	start - runs game loop    
    private void start()
    {
    	this.runGame = true;
    	this.inAnimation = false;
        this.updateScreen();
    	this.enableInput();
    	
    	while(runGame)
    	{
    		step(System.currentTimeMillis());
    		
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    //	step - one cycle of the game loop    
    private void step(long startTime)
    {	
    	//	Read input
    	boolean[] moveCommands = input.getMoveCommands();
    	boolean[] devCommands = input.getDevCommands();
    	
    	boolean needDisplayUpdate = false;
			
		//	Perform logic for input (if enabled)
		if(inputEnabled == true)
		{
			this.disableInput();
			
			//	Handle move commands			
	    	for(int i = 0; i < moveCommands.length; i++)
	    	{
	    		//	If one of the directions is pressed:
	    		if(moveCommands[i] == true)
	    		{
	    			//	Get the command direction
	    			Direction dir = Direction.values()[i];
	    			
	    			//	If the player was moved in that direction:
	                if(world.movePlayer(dir)) 
	                {
	                	//	Start a slide animation
	        			this.inAnimation = true;
	                	display.updatePlayer(world.getPlayer());
	                    display.slide(dir);
	                    //	Don't listen to any other move commands
	                    break;
	                }
	    		}
	    	}
	    	
	    	//	Handle dev commands
	    	if(devCommands[DevCommand.RANDOMIZE.ordinal()])
	    	{
	    		needDisplayUpdate = true;
	    		world.generateRandomWorld();
	    	}
	    	
	    	//	If not in an animation:
	    	if(!this.inAnimation)
	    	{
	    		//	Enable input for next command
	    		this.enableInput();
	    	}
		}
		
		//	Display output
		if(needDisplayUpdate)
		{
			this.updateScreen();
		}
	
    }
    
    
//    public void userInput(MoveCommand cmd)
//    {	
//        switch (cmd)
//        {
//            case RANDOMIZE:
//                //world.generateRandomWorld();
//                break;
//        }
//
//    }

    public void animationComplete()
    {
    	this.enableInput();
    	this.inAnimation = false;
        this.updateScreen();
    }
    
    private void disableInput()
    {
    	this.inputEnabled = false;
    }
    
    private void enableInput()
    {
    	this.inputEnabled = true;
    }

    public void updateScreen()
    {
        display.render(world.getPlayerView(), world.getPlayer());
    }
}
