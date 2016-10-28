import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.util.datastructure.XYLocation;

/**
 * 
 */

/**
 * @author Pavo
 *
 */
public class Actions implements ActionsFunction {

	public Set<Action> actions(Object o) {
		// TODO Auto-generated method stub
		Map map = (Map) o;
		Set<Action> actions = new LinkedHashSet<Action>();
		
		XYLocation tony = map.getTony();
		
		ArrayList<String> res = new ArrayList<String>();
		
		Set<XYLocation> notValidLocations = new LinkedHashSet<XYLocation>();
		
		
		return null;
	}

	

}
