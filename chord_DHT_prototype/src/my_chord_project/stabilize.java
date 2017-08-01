package my_chord_project;

import java.sql.Timestamp;

public class stabilize extends Thread {
	
	Chord_node cn;
	public stabilize(Chord_node cn)
	{
		this.cn = cn;
	}
	
	public void run()
	{
		
		
		while(true)
		{
			try
			{
				//ask sucessor about it's prdessor and check it's node with return value
				java.util.Date date= new java.util.Date();
				Chord_main.print_info("\n######## Running stabilize for "+cn.getNode()+" ##########",3);
				Chord_main.print_info("\nTime stamp:"+new Timestamp(date.getTime()), 3);
				Chord_main.print_info("\nAsking "+cn.getNode()+" succesor "+Chord_main.get_active_node_reference(cn.getSucessor()).getNode()+" for it's predessor",3);
				
				if(Chord_main.get_active_node_reference(cn.getSucessor()).getPredecessor() == cn.getNode())
				{
					Chord_main.print_info("\nEverything seems fine.. predessor is" +cn.getNode(),3);
				}
				else
				{
					Chord_main.print_info("\nThere may be a node join for node" +Chord_main.get_active_node_reference(cn.getSucessor()).getPredecessor()+"\nUpdating succesor information",3);
					cn.setSucessor(Chord_main.get_active_node_reference(cn.getSucessor()).getPredecessor());
				}
				Thread.sleep(30000);
			}
			catch(Exception e)
			{
				
			}
		}
	}

}
