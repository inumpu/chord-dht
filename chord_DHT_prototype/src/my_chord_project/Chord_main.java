package my_chord_project;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextArea;

/*
 *TODO: MAke the lookup work 
 *
 */

public class Chord_main {
	/*
	 * this method is used to generate active nodes randomly
	 */
	static ArrayList<Chord_node> active_nodes = new ArrayList<Chord_node>();
	int total_nodes;
	static ArrayList<Integer> numbers;
	public static JTextArea window1;
	public static JTextArea window2;
	public static JTextArea window3;
	
	
	
	public void initialize_windows(JTextArea win1,JTextArea win2,JTextArea win3)
	{
		this.window1 = win1;
		this.window2 = win2;
		this.window3 = win3;
	}
	
	public void generate_active_nodes(int a,int c,int total_number_nodes)
	{
		
		int x=3;
		total_nodes = total_number_nodes;
		numbers= new ArrayList<Integer>();
		x = ((a*x)+c)%total_number_nodes;
		numbers.add(x);	
		while(true)
		{
			x = ((a*x)+c)%total_number_nodes;		
			if(numbers.contains(x))
			{
				break;
			}
			numbers.add(x);			
		}
		Chord_node temp;
		Collections.sort(numbers);
		for(int i=0;i<numbers.size();i++)
		{
			temp = new Chord_node();
			temp.node = numbers.get(i);	
			if(i==0)
			{
				temp.sucessor = numbers.get(i+1);
				temp.predecessor = numbers.get(numbers.size()-1);
			}
			else if(i==numbers.size()-1)
			{
				temp.sucessor = numbers.get(0);
				temp.predecessor = numbers.get(i-1);
			}
			else
			{
				temp.sucessor = numbers.get(i+1);
				temp.predecessor = numbers.get(i-1);
			}
			
			active_nodes.add(temp);
		}
		
	}
	
	//iterates over all nodes to cluculate finger tables
	public void generate_finger_nodes(int m)
	{
		for(int i=0;i<active_nodes.size();i++)
		{
			calucaulate_finger_table(active_nodes.get(i), m);
		}	
	}
	
	//atomic method to caluculate finger table
	public Chord_node calucaulate_finger_table(Chord_node c, int m)
	{
		int finger_table[][] = new int[m][2];
		for(int i=0;i<m;i++)
		{
			int num = (c.node + (int)Math.pow(2, i))%total_nodes;
			finger_table[i][0] = num;
			finger_table[i][1] = get_successor(num);
		}
		c.setFinger_tabel(finger_table);
		return c;
	}
	
	
	//get sucessor node for a given random node from "numbers" information
	public int get_successor(int node)
	{
		for(int i=0; i<numbers.size(); i++)
		{
			if (node <= numbers.get(i))
			{
				return numbers.get(i);
			}
			
		}
		return numbers.get(0);
	}
	
	//takes active node number and returns Chord_node object as reference
	public synchronized static Chord_node get_active_node_reference(int node)
	{
		for(Chord_node cn : active_nodes)
		{
			if(cn.getNode() == node)
			{
				return cn;
			}
		}
		return null;
	}
	/*TODO:fix search 30 between 29 and 9
	 *
	 */
	//search for a succesor to a given random node
	public Chord_node lookup(int search_node, int key_node)
	{
		Chord_node search_node_obj = get_active_node_reference(search_node);
		print_info("\n##############Search##############", 1);
		print_info("\nStarted searching for node "+key_node, 1);
		//! (search_node_obj.getNode() <= key_node && search_node_obj.sucessor >= key_node)
		while(true)
		{
			int[][] finger_tabel = search_node_obj.getFinger_tabel();
			int i;
			for(i=0;i<finger_tabel.length;i++)
			{
				//assuming starting value in finger table will be always less than or equal
				if(finger_tabel[i][0] >= key_node)
				{
					if(i==0)
					{
						//assuming that it will break soon
						i=1;
					}
					print_info("\nNearest predeceesor is "+finger_tabel[i-1][0], 1);
					break;
				}
			}
			if(((finger_tabel[i-1][0]<=key_node)&&(finger_tabel[i-1][1]>=key_node)) || get_successor(key_node) == finger_tabel[i-1][1])
			{
				print_info("\nNode "+key_node+" lies between "+finger_tabel[i-1][0]+"and"+finger_tabel[i-1][1], 1);
				return get_active_node_reference(finger_tabel[i-1][1]);
			}
			else
			{
				print_info("\nNode "+key_node+"doesn't lies between "+finger_tabel[i-1][0]+" and it's sucessor"+finger_tabel[i-1][1], 1);
				print_info("\nGoing to search in node "+finger_tabel[i-1][1], 1);
				search_node_obj = get_active_node_reference(finger_tabel[i-1][1]);
			}
		}
		
	}
	
	public static void print_info(String msg, int window)
	{
		//basic chord initialization finger tables, 
		if(window==1)
		{
			//System.out.println(msg);
			String text = window1.getText();
			window1.setText(text+msg);
		}
		
		// node joining
		if(window==2)
		{
			//System.out.println(msg);
			String text = window2.getText();
			window2.setText(text+msg);
		}
		
		if(window==3)
		{
			//System.out.println(msg);
			String text = window3.getText();
			window3.setText(text+msg);
		}
			
		
	}
	
	
	//display all nodes information
	public void display_node_info(int window)
	{
		for(int i=0;i<active_nodes.size();i++)
		{
			active_nodes.get(i).print_object(window);
		}
	}
	
	//return successor from numbers also adjust position in numbers
	// 0----->predecessor
	// 1----->successor
	public int[] insert_into_numbers(int node)
	{
		int[] near_elements = new int[2];
		for(int i=0;i<numbers.size();i++)
		{
			
			if(node < numbers.get(i))
			{
				int successor = numbers.get(i); 
				near_elements[1] = successor;
				if(i==0)
				{
					int predessor = numbers.get(numbers.size()-1);
					near_elements[0] = predessor;
				}
				else
				{
					int predessor = numbers.get(i-1);
					near_elements[0] = predessor;
				}
				numbers.add(i, node);
				return near_elements;
			}
			if(i==(numbers.size()-1))
			{
			near_elements[0] = numbers.get(i);
			near_elements[1] = numbers.get(0);
			numbers.add(node);
			return near_elements;
			}
		}
		return near_elements;
	}
   
	public void fix_fingertables(int node)
	{
		for(Chord_node c: active_nodes)
		{
			int[][] fing_table = c.getFinger_tabel();
			int node_name = c.getNode();
			for(int i=0; i<fing_table.length; i++)
			{
				// (node >= fing_table[i][0] && node<fing_table[i][1])||((node >= fing_table[i][0])&&(fing_table[i][1]==numbers.get(0)))
				if( get_successor(fing_table[i][0]) == node)
				{
					print_info("\nchanging node "+node_name+" finger entry "+fing_table[i][0]+"---"+fing_table[i][1]+"to "+fing_table[i][0]+"---"+node, 2);
				}
			}
			
		}
	}
	
	public void add_node(int node, int m)
    {
    	
    	//add the node 
    	//insert into numbers
    	//Initialize successor with successor in numbers and initialize predecessor
    	// *******check first and last elements of number
    	print_info("\nAdding node "+node+" to the chord \n Looking into nodes directory", 2);
    	Chord_node cn = new Chord_node();
    	cn.setNode(node);
    	int[] nearelements = insert_into_numbers(node);
    	cn.setPredecessor(nearelements[0]);
    	cn.setSucessor(nearelements[1]);
    	print_info("\npredecessor for node "+node+" is "+nearelements[0]+"\nsucessor for node "+node+" is "+nearelements[1], 2);
    	Chord_node succesor_node = get_active_node_reference(cn.getSucessor());
    	print_info("\nSetting "+node+"as predessor to node "+nearelements[1], 2);
    	succesor_node.setPredecessor(node);
    	print_info("\nstarted caluculating finger node for "+node,2);
    	calucaulate_finger_table(cn, m);
    	cn.print_object(2);
    	active_nodes.add(cn);
    	print_info("\nUpdating other nodes finger tables .......", 2);
    	fix_fingertables(node); 
    	print_info("\nNew finger tables are", 2);
    	generate_finger_nodes(m);
    	display_node_info(2);
    	new stabilize(cn).start();
    }
    
	
	public static void main(String args[])
	{
		Scanner s = new Scanner(System.in);
		System.out.println("\nEnter m value:");
		int m = s.nextInt();
		int total_number_nodes = (int)Math.pow(2, m);
		System.out.println("\nNumber of nodes in: "+total_number_nodes);
		System.out.println("\nEnter a value:");
		int a = s.nextInt();
		System.out.println("\nEnter c value:");
		int c = s.nextInt();
		Chord_main cm = new Chord_main();
		cm.generate_active_nodes(a, c, total_number_nodes);
	
		
		
		
		for(int i=0;i<cm.active_nodes.size();i++)
		{
			System.out.print(cm.active_nodes.get(i).getNode()+" ");
		}
		
		cm.generate_finger_nodes(m);
		cm.display_node_info(1);
		while(true)
		{
			//test for searching
			
		System.out.println("\nEnter node to search");
		Chord_node cn = cm.lookup(cm.numbers.get(0), s.nextInt());
		System.out.println("------->"+cn.getNode());
	
			
			/*
			System.out.println("Enter node to insert");
			cm.add_node(s.nextInt(), m);
			*/
		}
		
	}
	
}
