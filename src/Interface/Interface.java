package Interface;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;

public class Interface extends SimpleApplication 
{

	public Interface() 
	{
		// TODO Auto-generated constructor stub
	}

	public Interface(AppState... initialStates) 
	{
		super(initialStates);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void simpleInitApp() 
	{
		Box b = new Box(1,1,1);
		Geometry geom = new Geometry("Box",b);
		Material mat = new Material(assetManager);
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) 
	{
		Interface i=new Interface();
		i.start();
		// TODO Auto-generated method stub

	}

}
