import java.util.Iterator;
import java.util.Properties;

import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.util.datastructure.XYLocation;

/**
 * 
 */

/**
 * @author Pavo
 *
 */

public class Game {
	public static final int IDS = 0;
	public static final int ASTAR = 1;
	
	private static final char TONY = 'T';
	private static final char ITEM = 'I';
	private static final char BARRIER = 'B';
	private static final char NULL = 'N';
	private Map map;
	public Game(Map map) {
		// TODO Auto-generated constructor stub
		this.map = map;
	}
	/**
	 * Game is playing in current strategy
	 * @throws Exception 
	 *
	 */
	public String play(int strategy) throws Exception{
		if(!solution()){
			return "No Solution";
		}
		String output = "";
		System.out.println(strategy + "s");
		switch(strategy){
			case IDS: output = playIDS();break;
			//case ASTAR: output = playASTAR();break;
		}
		return output;
	}
	
	private String playIDS() throws Exception{
		Goal goaltest = new Goal();
		Result resultFunction = new Result();
		Actions actionFunction = new Actions();
		Problem problem = new Problem(map, actionFunction, resultFunction, goaltest);
		Search search = new IterativeDeepeningSearch();
		SearchAgent agent = new SearchAgent(problem, search);
		String output = getPathCost(agent.getInstrumentation()) + "\n";
		System.out.println("haaaii");
		System.out.println(getNumberOfNodesExpanded(agent.getInstrumentation()));
		
		return output;
	}
	
	/**
	 * Game is playing in current strategy
	 *
	 */
	private boolean solution(){
		boolean solution = false;
		char[][] map1 = new char[map.getRow()][map.getCol()];
		for(int i = 0;i<map.getRow();i++){
			for (int j = 0; j< map.getCol();j++){
				XYLocation present = new XYLocation(i+1,j+1);
				char a = NULL;
				
				if(present.equals(map.getTony())){
					a = TONY;
				}
				for(XYLocation location : map.getItem()){
					if(present.equals(location)) a = ITEM;
				}
				
				for(XYLocation location : map.getBarrier()){
					if(present.equals(location)) a = BARRIER;
				}
				
				map1[i][j] = a;
			}
			solution = true;
			for (XYLocation item : map.getItem()){
				int x = item.getXCoOrdinate() -1;
				int y = item.getYCoOrdinate() -1;
				boolean[][] visited = new boolean[map.getRow()][map.getCol()];
				boolean pathToItem = checkSolution(map1, visited, x,y,ITEM);
				boolean pathToTony = checkSolution(map1, visited, x,y,TONY);
				System.out.println("Path to item " + pathToItem);
				System.out.println("Path to tony " + pathToTony);
				
				solution = pathToItem && pathToTony;
				System.out.println("solution " + solution);
				if(!solution) break;
			}
			
		}
		return solution;
	}
	
	private boolean checkSolution(char[][] map1, boolean[][] visited, int x,
			int y, char goal) {
		// TODO Auto-generated method stub
		if(validCoor(map1, x,y) || map1[x][y] == BARRIER || visited[x][y]){
			return false;
		}
		if(map1[x][y] == goal){return true;}
		visited[x][y] = true;
		return checkSolution(map1, visited, x, y-1, goal) || checkSolution(map1, visited, x+1, y, goal)
			|| checkSolution(map1, visited, x, y+1, goal) || checkSolution(map1, visited, x-1, y, goal);
	}
	
	private boolean validCoor(char[][] map1, int  x, int y){
		return x >= 0 && y >= 0 && x < map1.length && y < map1[0].length;
	}
	
	
	private String getPathCost(Properties properties) {
	    String res = "";
	    Iterator<Object> keys = properties.keySet().iterator();
	    while (keys.hasNext()) {
	      String key = (String) keys.next();
	      if (key.equals("pathCost"))
	        res = "" + (int) Double.parseDouble(properties.getProperty(key));
	    }
	    return res;
	  }

	  /**
	   * Returns the number of nodes expanded in a search.
	   *
	   * @param properties  The agent's instrumentation.
	   * @return the number of expanded nodes.
	   */
	  private String getNumberOfNodesExpanded(Properties properties) {
	    String res = "";
	    Iterator<Object> keys = properties.keySet().iterator();
	    while (keys.hasNext()) {
	      String key = (String) keys.next();
	      if (key.equals("nodesExpanded"))
	        res = "" + Integer.parseInt(properties.getProperty(key));
	    }
	    return res;
	  }

}
