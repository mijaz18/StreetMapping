/* Author:Muhammad Usama Ijaz
 */
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Graphh extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Object graph="WHITE";
	static Object Path="GREEN";
	static String v1;
	static String v2;
	static Boolean check;
	String mp;
	static String one;
	static String two;
	static String three;
	static String four;
	static String five;
	protected JButton button;
	protected JTextField text;
	protected JTextField text2;
	protected JLabel label;
	protected JLabel label2;
	static PriorityQueue<Vertex> pq=new PriorityQueue<Vertex>();
	static List<Vertex> ss = new LinkedList<Vertex>();
	//Paints the graph and path
	public void paintComponent(Graphics g) {
		//Color set for background
		if(check==true) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		double rangex=Math.toDegrees(maxlat())-Math.toDegrees(minlat());
		double rangey=Math.toDegrees(maxlong())-Math.toDegrees(minlong());
		double minlon=Math.toDegrees(minlong());
		double minlat=Math.toDegrees(minlat());
		//Color set for graph
		if(graph.equals("RED")) {
			g.setColor(Color.RED);
			}else if(graph.equals("BLUE")) {
				g.setColor(Color.BLUE);
			}else if(graph.equals("YELLOW")) {
				g.setColor(Color.YELLOW);
			}else if(graph.equals("MAGENTA")) {
				g.setColor(Color.MAGENTA);
			}else if(graph.equals("PINK")) {
				g.setColor(Color.PINK);
			}else if(graph.equals("GREEN")) {
				g.setColor(Color.GREEN);
			}else if(graph.equals("LIGHT_GRAY")) {
				g.setColor(Color.LIGHT_GRAY);
			}else if(graph.equals("DARK_GRAY")) {
				g.setColor(Color.DARK_GRAY);
			}else if(graph.equals("ORANGE")) {
				g.setColor(Color.ORANGE);
			}else if(graph.equals("WHITE")) {
				g.setColor(Color.WHITE);
			}else if(graph.equals("CYAN")) {
				g.setColor(Color.CYAN);
			}
		//Graph drawn using list of edges
	for(Edge r:ee){
		double orlongv=Math.toDegrees(r.v.longitude);
		double orlatv=Math.toDegrees(r.v.latitude);
		double orlongw=Math.toDegrees(r.w.longitude);
		double orlatw=Math.toDegrees(r.w.latitude);
		double lat1 = this.getHeight()-((this.getHeight()*(orlongv-minlon))/rangey);
		double lon1 = (this.getWidth()*(orlatv-(minlat)))/rangex;
		double lat2 = this.getHeight()-((this.getHeight()*(orlongw -minlon))/rangey);
		double lon2 = (this.getWidth()*(orlatw  -(minlat)))/rangex;
		g.drawLine((int)lon1, (int)lat1, (int)lon2, (int)lat2);
	}
	//Color set for path
if(Path.equals("RED")) {
			g.setColor(Color.RED);
			}else if(Path.equals("BLUE")) {
				g.setColor(Color.BLUE);
			}else if(Path.equals("YELLOW")) {
				g.setColor(Color.YELLOW);
			}else if(Path.equals("MAGENTA")) {
				g.setColor(Color.MAGENTA);
			}else if(Path.equals("PINK")) {
				g.setColor(Color.PINK);
			}else if(Path.equals("GREEN")) {
				g.setColor(Color.GREEN);
			}else if(Path.equals("LIGHT_GRAY")) {
				g.setColor(Color.LIGHT_GRAY);
			}else if(Path.equals("DARK_GRAY")) {
				g.setColor(Color.DARK_GRAY);
			}else if(Path.equals("ORANGE")) {
				g.setColor(Color.ORANGE);
			}else if(Path.equals("WHITE")) {
				g.setColor(Color.WHITE);
			}else if(Path.equals("CYAN")) {
				g.setColor(Color.CYAN);
			}
      //Draws string and line of path
	for(int i=ss.size()-1;i>0;i--) {
		double orlongv=Math.toDegrees(ss.get(i).longitude);
		double orlatv=Math.toDegrees(ss.get(i).latitude);
		double orlongw=Math.toDegrees(ss.get(i-1).longitude);
		double orlatw=Math.toDegrees(ss.get(i-1).latitude);
		double lat1 = this.getHeight()-((this.getHeight()*(orlongv-minlon))/rangey);
		double lon1 = (this.getWidth()*(orlatv-(minlat)))/rangex;
		double lat2 = this.getHeight()-((this.getHeight()*(orlongw -minlon))/rangey);
		double lon2 = (this.getWidth()*(orlatw  -(minlat)))/rangex;
		if(i==1) {
		g.drawString(ss.get(i-1).ID, (int)lon1, (int)lat1);
		}
		if(i==ss.size()-1) {
		g.drawString(ss.get(i).ID, (int)lon2, (int)lat2);
		}
		g.drawLine((int)lon1, (int)lat1, (int)lon2, (int)lat2);
	}
		}
	}
	//Vertex Class
	static class Vertex implements Comparable<Vertex> {
		public final String ID;
		public double distance, longitude, latitude;
		public boolean known;
		public Vertex path;
		public List<Vertex> adjacent;

		public Vertex(String id, double longitude, double latitude) {
			this.ID = id;
			this.longitude = longitude;
			this.latitude = latitude;
			this.distance = Integer.MAX_VALUE;
			adjacent = new LinkedList<Vertex>();
			this.path = null;
			this.known=false;
		}

		@Override
		public int compareTo(Vertex o) {
			return(Double.compare(this.distance, o.distance));
		}
				}
	//Edge class
	static class Edge implements Comparable<Edge> {
		public final String ID;
		public double weight;
		public Vertex v, w;

		public Edge(String ID, Vertex v, Vertex w) {
			this.ID = ID;
			this.v = v;
			this.w = w;
			adjacent(v, w);
			adjacent(w, v);
			double longio = v.longitude;
			double longit = w.longitude;
			double lato = v.latitude;
			double latt = w.latitude;
			double radius = 3959;
			double square = Math.sin((latt - lato) / 2);
			double po = Math.pow(square, 2);
			double squarett = Math.sin((longit - longio) / 2);
			double pott = Math.pow(squarett, 2);
			double coslo = Math.cos(lato);
			double coslt = Math.cos(latt);
			double distance = 2 * radius * Math.asin(Math.sqrt(po + (coslo * coslt) * pott));
			weight = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return 0;
		}
	}

	static List<Vertex> vv = new LinkedList<Vertex>();
	static List<Edge> ee = new LinkedList<Edge>();
	static List<String> e = new LinkedList<String>();
	static HashMap<String, List<Vertex>> store;
	static HashMap<String, Double> longit=new HashMap<String, Double>();
	static HashMap<String, Double> latit=new HashMap<String, Double>();
	static HashMap<String, String> vo=new HashMap<String, String>();
	static HashMap<String, String> vt=new HashMap<String, String>();
	static HashMap<String, Vertex> getv=new HashMap<String, Vertex>();
	static HashMap<List<Vertex>, Edge> gett=new HashMap<List<Vertex>, Edge>();
	//Reads the file and stores the vertices and edges 
	public static void map(String filename) {
		try {
			store = new HashMap<String, List<Vertex>>();
			File file = new File(filename);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				StringTokenizer os = new StringTokenizer(line);
				while (os.hasMoreTokens()) {
					List<Vertex> l = new LinkedList<Vertex>();
					if (os.nextToken().equals("i")) {
						String id = os.nextToken();
						double longi = Math.toRadians(Double.valueOf(os.nextToken()));
						double lat = Math.toRadians(Double.valueOf(os.nextToken()));
						Vertex v = new Vertex(id, longi, lat);
						getv.put(id, v);
						vv.add(v);
						longit.put(id, longi);
						latit.put(id, lat);
						store.put(id, l);
					} else {
						String edge = os.nextToken();
						String v1 = os.nextToken();
						String v2 = os.nextToken();
						vo.put(edge, v1);
						vt.put(edge, v2);
						e.add(edge);
					}
				}
				
				stringBuffer.append("\n");
			}

			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String ss:e) {
			List<Vertex> temp = new LinkedList<Vertex>();
			Vertex v1=getv.get(vo.get(ss));
			Vertex v2=getv.get(vt.get(ss));
			Edge eee = new Edge(ss, v1, v2);
			ee.add(eee);
			temp.add(0,v1);
			temp.add(0,v2);
			gett.put(temp, eee);
		}
}
	//Returns vertex when distance is entered as a parameter
	public static Vertex getv(double d) {
		for(Vertex x: vv) {
			if(x.distance==d) {
				return x;
			}
		}
		return null;
	}
	//Returns shortest distance through priority queue
	public static void shortestDistance() {
		for(Vertex x: vv) {
			pq.add(x);
		}
	}
	//Dijkstra algorithm to calculate shortest path
	public static void dijkstra(Vertex i) {
			i.distance = 0;
			pq.add(i);
			while(pq.isEmpty()==false) {
				 i= pq.poll();
				i.known = true;
			for (Vertex w : i.adjacent) {
				if (w.known == false) {
					double longio = i.longitude;
					double longit = w.longitude;
					double lato = i.latitude;
					double latt = w.latitude;
					double radius = 3959;
					double square = Math.sin((latt - lato) / 2);
					double po = Math.pow(square, 2);
					double squarett = Math.sin((longit - longio) / 2);
					double pott = Math.pow(squarett, 2);
					double coslo = Math.cos(lato);
					double coslt = Math.cos(latt);
					double distance = 2 * radius * Math.asin(Math.sqrt(po + (coslo * coslt) * pott));
					double cvw = distance;
					if ((i.distance+cvw) < w.distance) {
						w.distance = i.distance+cvw;
						w.path = i;
						pq.add(w);
						w.known=true;
					}
			}
		}
		}
	}
	static Comparator<Edge> comp=new Comparator<Edge>() {
		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.ID.compareTo(o2.ID);
		}
		
	};
	
	public static Edge getE(Vertex i, Vertex j) {
		for (Edge e : ee) {
			if ((e.v.equals(i) && e.w.equals(j)) || (e.w.equals(i) && e.v.equals(j))) {
				return e;

			}
		}
		return null;
	}
	//Updates the adjaency list of each vertex
	public static void adjacent(Vertex i, Vertex j) {
		i.adjacent.add(j);

	}
	//Traverses through the linklist ss to find the shortest path
	public static List<Vertex> traversal(Vertex i, Vertex j) {
		ss.add(j);
		while(j.path!=null ) {
			j = j.path;
				ss.add(0, j);
			}
		return ss;
		}
	//Returns the Vertex when the ID is passed as a parameter
	public static Vertex getv(String s) {
		for(Vertex v:vv) {
			if(v.ID.equals(s)) {
				return v;
			}
		}
		return null;
	}
	//Returns the maximum longitude
	public static double maxlong() {
		double min=vv.get(0).longitude;
		for(Vertex i: vv) {
			if(i.longitude>min) {
				min=i.longitude;
			}
		}
		return min;
	}
	//Returns the maximum Latitude
	public static double maxlat() {
		double min=vv.get(0).latitude;
		for(Vertex i: vv) {
			if(i.latitude>min) {
				min=i.latitude;
			}
		}
		return min;
	}
	//Returns the minimum longitude
	public static double minlong() {
		double min=vv.get(0).longitude;
		for(Vertex i: vv) {
			if(i.longitude<min) {
				min=i.longitude;
			}
		}
		return min;
	}
	//Returns the minimum Latitude
	public static double minlat() {
		double min=vv.get(0).latitude;
		for(Vertex i: vv) {
			if(i.latitude<min) {
				min=i.latitude;
			}
		}
		return min;
	}
	//Resets every vertex
	public static void clear() {
		for(Vertex i: vv) {
			i.distance=Integer.MAX_VALUE;
			i.path=null;
			i.known=false;
		}
	}

	public static void main(String[] args) throws IOException {
		//For command line arguements
		List<String> x=new ArrayList<String>();
		int size=args.length;
		for(String s:args) {
			x.add(s);
		}
		 one=args[0];
		two=args[1];
		if(x.contains("--show")&& x.size()==2) {
			 check=true;
			 map(one);
				new Canvas().setVisible(true);
		 }else if(x.contains("--show") && size==5) {
			 int temp=x.indexOf("--directions");
		 four=x.get(temp+1);
		 five=x.get(temp+2);
		 check=true;
			 map(one);
		dijkstra(getv(four));
		traversal(getv(four), getv(five));
		 System.out.println("Path: ");
		 for(Vertex i:ss) {
			 System.out.println( i.ID);
		 }
		 double dis=ss.get(ss.size()-1).distance;
		 if(dis>=Integer.MAX_VALUE) {
			 System.out.println("Two points not connected");
		 }else {
		 System.out.println("Distance: "+ dis);
		 }
			new Canvas().setVisible(true);
		 }else if(!x.contains("--show")) {
			 int temp=x.indexOf("--directions");
			 three=x.get(temp+1);
			 four=x.get(temp+2);
			 check=false;
			 map(one);
				dijkstra(getv(three));
				traversal(getv(three), getv(four));
			 System.out.println("Path: ");
			 for(Vertex i:ss) {
				 System.out.println( i.ID);
			 }
			 double dis=ss.get(ss.size()-1).distance;
			 if(dis>=Integer.MAX_VALUE) {
				 System.out.println("Two points not connected");
			 }else {
			 System.out.println("Distance: "+ dis);
			 }
		 }
		
	}

	
	
	

}
