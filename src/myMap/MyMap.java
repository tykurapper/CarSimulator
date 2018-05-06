package myMap;

import java.awt.Graphics;
import java.util.ArrayList;

import direction.Direction;
import entities.machines.StraightTurningPoint;
import entities.machines.TurningPoint;
import path.Line;
import path.Point;

public class MyMap {
	private ArrayList<TurningPoint> map;
	private ArrayList<Point> point;
	private int[][] graph;
	public static final int INFINITY = 9999;
	public static final int MAX = 30;
	
	public MyMap(){
		map = new ArrayList<TurningPoint>();
		point = new ArrayList<Point>();
		graph = new int[MAX][MAX];
		int i, j;
		for(i = 0; i < 30; i++)
			for(j = 0; j < 30; j++)
				graph[i][j] = -1;
	}
	public MyMap(String arg0){
		map = new ArrayList<TurningPoint>();
		point = new ArrayList<Point>();
		graph = new int[MAX][MAX];
		int i, j;
		for(i = 0; i < 30; i++)
			for(j = 0; j < 30; j++)
				graph[i][j] = -1;
		if(arg0.equals("case 1")){
			addPoint(new Point(20, 20)); 	//1
			addPoint(new Point(399, 20)); 	//2
			addPoint(new Point(779, 20)); 	//3
			addPoint(new Point(199, 199));	//4
			addPoint(new Point(399, 199));	//5
			addPoint(new Point(599, 199));	//6
			addPoint(new Point(20, 299));	//7
			addPoint(new Point(199, 299));	//8
			addPoint(new Point(399, 299));	//9
			addPoint(new Point(599, 299));	//10
			addPoint(new Point(779, 299));	//11
			addPoint(new Point(499, 399));	//12
			addPoint(new Point(20, 579));	//13
			addPoint(new Point(779, 579));	//14
			
			add(new StraightTurningPoint(getPoint(0), getPoint(3), TurningPoint.DEFAULT_RADIUS, new Direction(1, 1), new Line(34, 6, 213, 185), new Line(6, 34, 185, 213)));
			add(new StraightTurningPoint(getPoint(3), getPoint(0), TurningPoint.DEFAULT_RADIUS, new Direction(-1, -1), new Line(6, 34, 185, 213), new Line(34, 6, 213, 185)));
			add(new StraightTurningPoint(getPoint(1), getPoint(4), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new Line(419, 20, 419, 199), new Line(379, 20, 379, 199)));
			add(new StraightTurningPoint(getPoint(4), getPoint(1), TurningPoint.DEFAULT_RADIUS, new Direction(0, -1), new Line(379, 20, 379, 199), new Line(419, 20, 419, 199)));
			add(new StraightTurningPoint(getPoint(2), getPoint(5), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 1), new Line(793, 34, 613, 213), new Line(765, 6, 585, 185)));
			add(new StraightTurningPoint(getPoint(5), getPoint(2), TurningPoint.DEFAULT_RADIUS, new Direction(1, -1), new Line(765, 6, 585, 185), new Line(793, 34, 613, 213)));
			add(new StraightTurningPoint(getPoint(3), getPoint(4), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(199, 179, 399, 179), new Line(199, 219, 399, 219)));
			add(new StraightTurningPoint(getPoint(4), getPoint(3), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 0), new Line(199, 219, 399, 219), new Line(199, 179, 399, 179)));
			add(new StraightTurningPoint(getPoint(4), getPoint(5), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(399, 179, 599, 179), new Line(399, 219, 599, 219)));
			add(new StraightTurningPoint(getPoint(5), getPoint(4), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 0), new Line(399, 219, 599, 219), new Line(399, 179, 599, 179)));
			add(new StraightTurningPoint(getPoint(3), getPoint(7), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new Line(219, 199, 219, 299), new Line(179, 199, 179, 299)));
			add(new StraightTurningPoint(getPoint(7), getPoint(3), TurningPoint.DEFAULT_RADIUS, new Direction(0, -1), new Line(179, 199, 179, 299), new Line(219, 199, 219, 299)));
			add(new StraightTurningPoint(getPoint(4), getPoint(8), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new Line(419, 199, 419, 299), new Line(379, 199, 379, 299)));
			add(new StraightTurningPoint(getPoint(8), getPoint(4), TurningPoint.DEFAULT_RADIUS, new Direction(0, -1), new Line(379, 199, 379, 299), new Line(419, 199, 419, 299)));
			add(new StraightTurningPoint(getPoint(5), getPoint(9), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new Line(619, 199, 619, 299), new Line(579, 199, 579, 299)));
			add(new StraightTurningPoint(getPoint(9), getPoint(5), TurningPoint.DEFAULT_RADIUS, new Direction(0, -1), new Line(579, 199, 579, 299), new Line(619, 199, 619, 299)));
			add(new StraightTurningPoint(getPoint(6), getPoint(7), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(20, 279, 199, 279), new Line(20, 319, 199, 319)));
			add(new StraightTurningPoint(getPoint(7), getPoint(6), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 0), new Line(20, 319, 199, 319), new Line(20, 279, 199, 279)));
			add(new StraightTurningPoint(getPoint(7), getPoint(8), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(199, 279, 399, 279), new Line(199, 319, 399, 319)));
			add(new StraightTurningPoint(getPoint(8), getPoint(7), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 0), new Line(399, 319, 199, 319), new Line(399, 279, 199, 279)));
			add(new StraightTurningPoint(getPoint(8), getPoint(9), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(399, 279, 599, 279), new Line(399, 319, 599, 319)));
			add(new StraightTurningPoint(getPoint(9), getPoint(8), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 0), new Line(599, 319, 399, 319), new Line(599, 279, 399, 279)));
			add(new StraightTurningPoint(getPoint(9), getPoint(10), TurningPoint.DEFAULT_RADIUS, new Direction(1, 0), new Line(599, 279, 779, 279), new Line(599, 319, 779, 319)));
			add(new StraightTurningPoint(getPoint(10), getPoint(9), TurningPoint.DEFAULT_RADIUS, new Direction(-1, 0), new Line(779, 319, 599, 319), new Line(779, 279, 599, 279)));
			add(new StraightTurningPoint(getPoint(6), getPoint(12), TurningPoint.DEFAULT_RADIUS, new Direction(0, 1), new Line(40, 299, 40, 579), new Line(0, 299, 0, 579)));
			add(new StraightTurningPoint(getPoint(12), getPoint(6), TurningPoint.DEFAULT_RADIUS, new Direction(0, -1), new Line(0, 299, 0, 579), new Line(40, 299, 40, 579)));
			add(new StraightTurningPoint(getPoint(8), getPoint(12), TurningPoint.DEFAULT_RADIUS, new Direction(1, -1), new Line(410, 315, 32, 595), new Line(388, 283, 8, 562)));
			add(new StraightTurningPoint(getPoint(12), getPoint(8), TurningPoint.DEFAULT_RADIUS, new Direction(1, -1), new Line(388, 283, 8, 562), new Line(410, 315, 32, 595)));
		}
	}
	public void addPoint(Point p){
		point.add(p);
	}
	public TurningPoint getTPfromPoint(int i, int j){
		if(graph[i][j] != -1)
			return map.get(graph[i][j]);
		else 
			return null;
	}
	public void add(TurningPoint tp){
		map.add(tp);
		int i, j;
		i = point.indexOf(tp.getStart());
		j = point.indexOf(tp.getCenter());
		graph[i][j] = map.indexOf(tp);
	}
	public void render(Graphics g){
		int i;
		for(i = 0; i < map.size(); i++)
			map.get(i).render(g);
		for(i = 0; i < point.size(); i++)
			{
				point.get(i).render(g);
				g.drawString(""+(i+1),(int) point.get(i).getX(),(int) point.get(i).getY());
			}
	}
	public Point getPoint(int i){
		return point.get(i);
	}
	public TurningPoint get(int i){
		return map.get(i);
	}
	public int[] dijkstra(int startnode,int finish){
		return dijkstra(graph, 14, startnode, finish);
	}
	private int[] dijkstra(int graph[][], int n,int startnode, int finish){
		if(startnode >= n || finish >= n)
			return null;
		int[][] cost = new int[MAX][MAX];
		int[] distance = new int[MAX];
		int[] pred = new int[MAX];
	    int[] visited = new int[MAX];
	    int count,mindistance,nextnode,i,j;
	    nextnode = 0;
	    for(i=0;i<n;i++)
	        for(j=0;j<n;j++)
	        	if(i==j)
	        		cost[i][j] = 0;
	        	else 
	        		if(graph[i][j]== -1)
	                cost[i][j]=INFINITY;
	            else
	                cost[i][j]=1;
	    for(i=0;i<n;i++)
	    {
	        distance[i]=cost[startnode][i];
	        pred[i]=startnode;
	        visited[i]=0;
	    }
	    distance[startnode]=0;
	    visited[startnode]=1;
	    count=1;
	    
	    while(count<n-1)
	    {
	        mindistance=INFINITY;
	        
	        //nextnode gives the node at minimum distance
	        for(i=0;i<n;i++)
	            if(distance[i]<mindistance && visited[i] == 0)
	            {
	                mindistance=distance[i];
	                nextnode=i;
	            }
	            
	            //check if a better path exists through nextnode            
	            visited[nextnode]=1;
	            for(i=0;i<n;i++)
	                if(visited[i] == 0)
	                    if(mindistance+cost[nextnode][i]<distance[i])
	                    {
	                        distance[i]=mindistance+cost[nextnode][i];
	                        pred[i]=nextnode;
	                    }
	        count++;
	    }
	    
	    if(distance[finish] < INFINITY){
	    	System.out.println("Distance from " +startnode + " to "+ finish + " = " + distance[finish]);
//	    	System.out.print(finish);
	    	int k = distance[finish] + 1;
		    int[] path = new int[k];
		    path[--k] = finish;
		    j = finish;
		    do {	    	
		    	j = pred[j];
		    	path[--k] = j;
//		    	System.out.print( " <- " + j);
		    }
		    while(j!=startnode);
		    System.out.print("\n");
		    for(i = 0; i < distance[finish]; i++)
		    	System.out.print(path[i] + " > ");
		    System.out.print(path[distance[finish]] + "\n");
		    return path;
	    }
	    else return null;
//	    	System.out.println(pred[j]);
	    //print the path and distance of each node
//	    for(i=0;i<n;i++)
//	        if(i!=startnode && i == finish)
//	        {
//	            System.out.println("\nDistance of node " +i + " = " + distance[i]);
////	            printf("\nPath=%d",i);
//	            
//	            j=i;
//	            do
//	            {
//	                j=pred[j];
////	                printf("<-%d",j);
//	            }while(j!=startnode);
//	    }
	}
}
