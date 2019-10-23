/* Author:Muhammad Usama Ijaz
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Canvas extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String v1;
	static String v2;
	Graphh g;
	protected JComboBox<String> com;
	protected JComboBox<String> com1;
	protected JComboBox<String> com2;
	protected JButton Button1;
	protected JLabel label1;
	protected JLabel label4;
	protected JLabel label5;
	protected JLabel label6;
	protected JLabel label7;
	protected JLabel label2;
	protected JLabel label3;
	protected JTextField text1;
	protected JTextField text2;
public Canvas() {
	//All labels, ComboBox, and textfields initialized
	setTitle("Graph");
	setSize(500,500);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(new BorderLayout());
	JPanel panel=new JPanel();
	JPanel panel1=new JPanel();
	String[] array= {"BLACK","BLUE","CYAN","YELLOW","MAGENTA","PINK","ORANGE","RED","WHITE","GREEN","LIGHT_GRAY","DARK_GRAY"};
	com=new JComboBox<String>(array);
	com1=new JComboBox<String>(array);
	com2=new JComboBox<String>(array);
	Button1=new JButton("Go!");
	Button1.addActionListener(this);
	label1=new JLabel("Vertex 1");
	label4=new JLabel("COLOR OPTIONS");
	label6=new JLabel("Graph");
	label5=new JLabel("Path");
	text1=new JTextField(5);
	com.addActionListener(this);
	com1.addActionListener(this);
	text1.addActionListener(this);
	label2=new JLabel("Vertex 2");
	text2=new JTextField(5);
	text2.addActionListener(this);
	label3=new JLabel("Distance: 0");
	panel1.add(label4);
	panel1.add(label5);
	panel1.add(com);
	panel1.add(label6);
	panel1.add(com1);
	panel.add(label1);
	panel.add(text1);
	panel.add(label2);
	panel.add(text2);
	panel.add(Button1);
	panel.add(label3);
	add(panel1,BorderLayout.SOUTH);
	add(panel,BorderLayout.NORTH);
	 g=new Graphh();
	g.setPreferredSize(new Dimension(getWidth(),getHeight()));
	add(g,BorderLayout.CENTER);
	pack();
}
	public static void main(String[] args) {
		//

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(Button1)) {
			//Gets the first vertex from textfield
			v1=text1.getText();
			//Gets the second vertex from textfield
			v2=text2.getText();
			//Clears the list containing vertices for path
			Graphh.ss.clear();
			//Resets the vertices
			Graphh.clear();
			Graphh.graph=com1.getSelectedItem();
			Graphh.Path=com.getSelectedItem();
			//Dijkstra and traversal called to calculate shortest path
			Graphh.dijkstra(Graphh.getv(v1));
			Graphh.traversal(Graphh.getv(v1), Graphh.getv(v2));
			label3.setText("Distance: "+ Graphh.ss.get(Graphh.ss.size()-1).distance);
			if(Graphh.check==true) {
			repaint();	
			}
		}
		
		
	}
	public static String getV1() {
		return v1;
	}
	public void setV1(String v1) {
		Canvas.v1 = v1;
	}
	public static String getV2() {
		return v2;
	}
	public void setV2(String v2) {
		Canvas.v2 = v2;
	}
	
}
