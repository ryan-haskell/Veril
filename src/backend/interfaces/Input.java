package backend.interfaces;

public interface Input 
{	
	public enum DevCommand {
		RANDOMIZE
	}
	
	public boolean[] getMoveCommands();
	public boolean[] getDevCommands();
}
