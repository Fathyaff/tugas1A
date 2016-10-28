import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Tugas1A {
	public static void main(String[] args) throws Exception {
		String inputStrategi = args[0];
		String inputName = args[1];
		String outputName = args[2];
		File inputFile = new File(inputName);
		
		BufferedReader read = new BufferedReader (new FileReader(inputFile.getAbsoluteFile()));
		
		
		//Init the Map
		String mapSize = read.readLine();
		String[] sizeXY = mapSize.split(",");
		int row = Integer.parseInt(sizeXY[0]);
		int col = Integer.parseInt(sizeXY[1]);
		Map map = new Map(row,col);
		
		//init Tony
		String start = read.readLine();
		String[] startTony = start.split(",");
		int x = Integer.parseInt(startTony[0]);
		int y = Integer.parseInt(startTony[1]);
		map.addIntoMap(x, y, Map.TONY);
		
		//Init Item
		String itemPositions = read.readLine();
		String[] items = itemPositions.split(" ");
		for(int i = 0; i<items.length;i++){
			String xSplit = items[i].substring(1,2);
			String ySplit = items[i].substring(3,4);
			int xItem = Integer.parseInt(xSplit);
			int yItem = Integer.parseInt(ySplit);
			int element = Map.ITEM;
			map.addIntoMap(xItem, yItem, element);
		}
		System.out.println(map.getTotalItem()+ " barang");

		//Init Barrier
		String barrierPositions = read.readLine();
		String[] barriers = barrierPositions.split(" ");
		for(int i = 0; i<barriers.length;i++){
			String xSplit = barriers[i].substring(1,2);
			String ySplit = barriers[i].substring(3,4);
			int xBarr = Integer.parseInt(xSplit);
			int yBarr = Integer.parseInt(ySplit);
			int element = Map.BARRIER;
			map.addIntoMap(xBarr, yBarr, element);
			System.out.println(map.getItem().size() + " item");
		}
		System.out.println(map.getBarrier().size() +" barr");
		
		System.out.println(map.getTony() + " tony location");
		Game game = new Game(map);
		int strategi = 0;
		if (inputStrategi.equals("ids")){
			strategi = Game.IDS;
		}else if (inputStrategi.equals("A*")){
			strategi = Game.ASTAR;
		}
		String output = game.play(strategi);
		read.close();
		//Write an input File
		
		
		
		//Write an output file
		File outputFile = new File(outputName);
		 if (!outputFile.exists())
		      outputFile.createNewFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(outputFile.getAbsoluteFile()));
		out.write(output +"\n");
	    out.close(); 
	}
}
