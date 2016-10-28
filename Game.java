import java.util.Iterator;
import java.util.Properties;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.IterativeDeepeningSearch;

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
		
		switch(strategy){
			case IDS: output = playIDS();break;
			//case ASTAR: output = playASTAR();break;
		}
		return output;
	}
	/**
	 * Game is playing in current strategy
	 *
	 */
	public boolean solution(){
		return false;
	}
	
	private String playIDS() throws Exception{
		Goal goaltest = new Goal();
		Result resultFunction = new Result();
		Actions actionFunction = new Actions();
		Problem problem = new Problem(map, actionFunction, resultFunction, goaltest);
		Search search = new IterativeDeepeningSearch();
		SearchAgent agent = new SearchAgent(problem, search);
		String output = getPathCost(agent.getInstrumentation()) + "\n";
		System.out.println(getNumberOfNodesExpanded(agent.getInstrumentation()));
		
		return output;
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