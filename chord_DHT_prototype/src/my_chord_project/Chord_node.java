package my_chord_project;

/*
 * This class is a data structure for Chord node
 */
public class Chord_node {
	
	int predecessor;
	int sucessor;
	int node;
	int [][] finger_table;
	
	public int getNode() {
		return node;
	}
	public void setNode(int node) {
		this.node = node;
	}
	public synchronized int getPredecessor() {
		return predecessor;
	}
	public synchronized void setPredecessor(int predecessor) {
		this.predecessor = predecessor;
	}
	public synchronized int getSucessor() {
		return sucessor;
	}
	public synchronized void setSucessor(int sucessor) {
		this.sucessor = sucessor;
	}
	public int[][] getFinger_tabel() {
		return finger_table;
	}
	public void setFinger_tabel(int[][] finger_table) {
		this.finger_table = finger_table;
	}
	
	public void print_object(int window)
	{
		Chord_main.print_info("\n########NODE INFORMATION############",window);
		Chord_main.print_info("\nNode name is"+node,window);
		for(int i=0;i<finger_table.length;i++)
		{
			Chord_main.print_info("\n"+finger_table[i][0]+"----->"+finger_table[i][1],window);
		}
		Chord_main.print_info("\nSucessor is"+sucessor,window);
		Chord_main.print_info("\nPredecessor is"+predecessor,window);
	}
}

