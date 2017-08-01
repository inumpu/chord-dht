package my_chord_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Panel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class chord {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    public JTextArea window1;
    public JTextArea window2;
    public JTextArea window3;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    chord window = new chord();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public chord() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	
        frame = new JFrame();
        frame.setBounds(200, 200, 550, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(18, 6, 512, 350);
        frame.getContentPane().add(tabbedPane);
        //bootstrap
        JPanel panel = new JPanel();
        tabbedPane.addTab("chord", null, panel, null);
        panel.setLayout(null);
        panel.setLayout(null);
        
        JLabel lblClienttype = new JLabel("Enter Data");
        lblClienttype.setBounds(29, 41, 106, 15);
        panel.add(lblClienttype);    
        
        JLabel lblM = new JLabel("m");
        lblM.setBounds(29, 82, 106, 15);
        panel.add(lblM);
        
        textField = new JTextField();
        textField.setBounds(133, 80, 231, 19);
        panel.add(textField);
        textField.setColumns(10);
        
        JLabel lblA = new JLabel("a:");
        lblA.setBounds(31, 121, 70, 15);
        panel.add(lblA);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(133, 119, 231, 19);
        panel.add(textField_2);
        
        JLabel lblC = new JLabel("c:");
        lblC.setBounds(31, 173, 70, 15);
        panel.add(lblC);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(133, 167, 231, 19);
        panel.add(textField_3);
        
        JButton btnNewButton = new JButton("Caluculate");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	Chord_init.init_data(Integer.parseInt(textField.getText()), Integer.parseInt(textField_2.getText()), Integer.parseInt(textField_3.getText()), window1, window2, window3);	
        	}
        });
        btnNewButton.setBounds(159, 237, 117, 29);
        panel.add(btnNewButton);
        
        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Operations", null, panel_1, null);
        panel_1.setLayout(null);
        
        JLabel lblSearch = new JLabel("search:");
        lblSearch.setBounds(44, 60, 61, 16);
        panel_1.add(lblSearch);
        
        textField_4 = new JTextField();
        textField_4.setBounds(155, 44, 164, 49);
        panel_1.add(textField_4);
        textField_4.setColumns(10);
        
        JLabel lblAddNode = new JLabel("Add Node:");
        lblAddNode.setBounds(44, 155, 83, 16);
        panel_1.add(lblAddNode);
        
        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(155, 150, 164, 49);
        panel_1.add(textField_5);
        
        JButton btnNewButton_1 = new JButton("Search");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        			Chord_init.search(Integer.parseInt(textField_4.getText()));
        	}
        });
        btnNewButton_1.setBounds(66, 245, 117, 29);
        panel_1.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Add node");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Chord_init.insert_node(Integer.parseInt(textField_5.getText()));
        	}
        });
        btnNewButton_2.setBounds(272, 245, 117, 29);
        panel_1.add(btnNewButton_2);
        
        JScrollPane scrollPane1 = new JScrollPane();
        tabbedPane.addTab("Initial", null, scrollPane1, null);
        
        window1 = new JTextArea();
        window1.setEditable(false);
        window1.setLineWrap(true);
        window1.setWrapStyleWord(true);
        scrollPane1.setViewportView(window1);
      
        
        JScrollPane scrollPane2 = new JScrollPane();
        tabbedPane.addTab("Join", null, scrollPane2, null);
        
        window2 = new JTextArea();
        window2.setEditable(false);
        window2.setLineWrap(true);
        window2.setWrapStyleWord(true);
        scrollPane2.setViewportView(window2);
        
        
        JScrollPane scrollPane3 = new JScrollPane();
        tabbedPane.addTab("stabilization", null, scrollPane3, null);
        
        window3 = new JTextArea();
        window3.setEditable(false);
        window3.setLineWrap(true);
        window3.setWrapStyleWord(true);
        scrollPane3.setViewportView(window3);
    
        
        
    }
    }






class Chord_init
{
	static int m;
	static int a;
	static int c;
	static Chord_main cm;
	static JTextArea win1;
	static JTextArea win2;
	static JTextArea win3;
	
			static void init_data(int m,int a, int c, JTextArea win1, JTextArea win2, JTextArea win3)
			{
				Chord_init.m=m;
				int total_number_nodes = (int)Math.pow(2, m);
				cm = new Chord_main();
				cm.initialize_windows(win1, win2, win3);
				cm.generate_active_nodes(a, c, total_number_nodes);
				Chord_main.print_info("\nActive nodes are ", 1);
				for(int i=0;i<cm.active_nodes.size();i++)
				{
					Chord_main.print_info(cm.active_nodes.get(i).getNode()+" ",1);
				}
				
				cm.generate_finger_nodes(m);
				cm.display_node_info(1);
				//run initial stabilize here
				for(int i=0;i<cm.active_nodes.size();i++)
				{
					//new stabilize(cm.active_nodes.get(i)).run();
				}
			}
			
			static void search(int node)
			{
				cm.lookup(cm.numbers.get(0), node);
			}
			
			static void insert_node(int node)
			{
				cm.add_node(node, m);
			}
	
}
