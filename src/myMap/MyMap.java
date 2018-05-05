package myMap;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.machines.TurningPoint;
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
			point.get(i).render(g);
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
	    System.out.println("\nDistance of node " +finish + " = " + distance[finish] + "\n");
	    if(distance[finish] < INFINITY){
	    	System.out.print(finish);
	    	int k = distance[finish] + 1;
		    int[] path = new int[k];
		    path[--k] = finish;
		    j = finish;
		    do {	    	
		    	j = pred[j];
		    	path[--k] = j;
		    	System.out.print( " <- " + j);
		    }
		    while(j!=startnode);
		    System.out.print("\n");
		    for(i = 0; i < distance[finish] + 1; i++)
		    	System.out.print(path[i] + " > ");
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
