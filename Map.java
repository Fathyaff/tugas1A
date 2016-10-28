import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.util.datastructure.XYLocation;

/**
 * 
 */

/**
 * @author Pavo
 *
 */
public class Map {
	private int row;
	private int col;
	private XYLocation tony;
	private Set<XYLocation> item;
	private Set<XYLocation> barrier;
	
	public static final int TONY = 0;
	public static final int ITEM = 1;
	public static final int BARRIER = 2;
	
	public Map(int row, int col) {
		// TODO Auto-generated constructor stub
		this.row = row;
		this.col = col;
		
		item = new LinkedHashSet<XYLocation>();
		barrier = new LinkedHashSet<XYLocation>();
		
	}
	public void addIntoMap(int x, int y, int element) {
		// TODO Auto-generated method stub
		addIntoMap (new XYLocation(x,y), element);
	}
	public void addIntoMap(XYLocation xy, int element){
		System.out.println("xy" + element);
		switch (element){
			case TONY : tony = xy;break;
			case ITEM : item.add(xy);break;
			case BARRIER : barrier.add(xy);break;
		}
	}
	public int getTotalItem(){
		return item.size();
	}
	public boolean isAllItemTake(){
		return item.isEmpty();
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public XYLocation getTony() {
		return tony;
	}
	public void setTony(XYLocation tony) {
		this.tony = tony;
	}
	public Set<XYLocation> getItem() {
		return item;
	}
	public Set<XYLocation> getBarrier() {
		return barrier;
	}
}
