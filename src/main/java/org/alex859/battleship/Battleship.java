package org.alex859.battleship;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alex859.battleship.actions.Action;
import org.alex859.battleship.actions.MoveForward;
import org.alex859.battleship.actions.Shoot;
import org.alex859.battleship.actions.SpinLeft;
import org.alex859.battleship.actions.SpinRight;
import org.alex859.battleship.exceptions.InvalidBattleshipFormatException;
import org.alex859.battleship.exceptions.InvalidPositionException;
import org.alex859.battleship.model.Board;
import org.alex859.battleship.model.Position;
import org.alex859.battleship.model.Ship;
import org.apache.log4j.Logger;

/**
 * Tha main class of the application. Contains validation and game logic
 * of the game
 * @author alex859
 *
 */
public class Battleship {
	private Board board;
	/**
	 * Action strings extracted from the input
	 */
	private List<String> actionStrings=new ArrayList<String>();
	
	/**
	 * Input validation pattern
	 */
	private static String pattern="\\(\\d+,\\s\\d+\\)"
							   +  "\n"
							   +  "(\\(\\d+,\\s\\d+\\,\\s\\D\\)\\s?)*"
							   +  "(\n\\(\\d+,\\s\\d+\\)\\s?\\D*)*";
	
	/**
	 * Pattern that describes the format of the line containing the ships
	 */
	private static Pattern shipLinePattern=Pattern.compile(".*?(\\(\\d+,\\s\\d+\\,\\s\\D\\)).*?");
	
	/**
	 * Pattern that describes the format of the line containing an action
	 */
	private static Pattern actionPattern=Pattern.compile("\\((\\d+),\\s(\\d+)\\)\\s?(\\D*)");
	
	/**
	 * Actions that can be performed on a ship
	 */
	private static Map<String, Action> actions;
	static{
		actions=new HashMap<String, Action>();
		actions.put("M", new MoveForward());
		actions.put("L", new SpinLeft());
		actions.put("R", new SpinRight());
		actions.put("SHOOT", new Shoot());
	}
	
	private static final Logger logger=Logger.getLogger(Battleship.class);
	
	/**
	 * Create a new game from a string having:
	 * - in the fist line the dimensions of the board (i.e. (5, 5))
	 * - in the second line the description of the ships (i.e. (1, 2, E) (2, 2, W))
	 * - a list of actions to be performed (i.e. (1, 2) LMRM or simply (1, 2) for the shoot)
	 * @param str The string describing the game
	 */
	public Battleship(String str){
		if(str.matches(pattern)){
			String[] lines=str.split("\n");
			//the first line is the board
			this.board=new Board(lines[0]);
			//the second line should contain the ships
			Matcher shipsMatcher=shipLinePattern.matcher(lines[1]);
			while(shipsMatcher.find()){
				try{
					Ship s=new Ship(shipsMatcher.group());
					this.board.addShip(s);
					logger.info("Added ship "+s);
				}catch(InvalidPositionException e){
					logger.warn("Invalid ship position, the ship will be skipped. "+e.getMessage());
				}
			}
			//from line 3 onwards each line is an action to be performed
			if(lines.length>2){
				for(int i=2;i<lines.length;i++){
					actionStrings.add(lines[i]);
				}
			}
		}else{
			throw new InvalidBattleshipFormatException();
		}
	}
	
	/**
	 * Executes the actions previously read from the input
	 */
	public void play(){
		for(String s:this.actionStrings){
			//first of all decode the line
			Matcher actionMatcher=actionPattern.matcher(s);
			if(actionMatcher.matches()){
				int x=Integer.parseInt(actionMatcher.group(1));
				int y=Integer.parseInt(actionMatcher.group(2));
				Position p=new Position(x, y);
				//first of all check if the position contains a ship
				Ship ship=this.board.getShipsMap().get(p);
				//we can run the action only if we have found
				if(ship!=null){
					//now we have to check if there is the letter array
					//to understand if it is a shot or not
					String actionLetters=actionMatcher.group(3);
					Action action=null;
					if(actionLetters.length()>0){
						//for each letter get the corresponding action
						for(int i=0;i<actionLetters.length();i++){
							String actionChar=String.valueOf(actionLetters.charAt(i));
							action=actions.get(actionChar);
							if(action!=null){
								Position oldPosition=new Position(ship.getPosition());
								ship.execute(action);
								//if position has changed, update the board
								if(!oldPosition.equals(ship.getPosition())){
									//try to add the ship in the newly computed position
									try {
										this.board.addShip(ship);
										//the ship has been moved: we remove the old one
										this.board.removeShipAt(oldPosition);
									} catch (InvalidPositionException e) {
										//rollback to the old position
										ship.setPosition(oldPosition);
										logger.warn("Unable to move the ship. " + e.getMessage());
									}
								}
							}else{
								logger.warn("Action "+actionChar+" not recognized: ignored.");
							}
						}
					}else{
						//shoot the ship!
						action=actions.get("SHOOT");
						ship.execute(action);
					}
				}
			}
		}
	}

	public Board getBoard() {
		return board;
	}

	public List<String> getActionStrings() {
		return actionStrings;
	}
	
	private static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        sb.append(line);
	        while ((line=br.readLine()) != null) {
	        	sb.append("\n");
	            sb.append(line);
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	
	/**
	 * Reads the battleship description from the command line:
	 * 
	 * @param args A single argument: the name of the file containing the battleship description
	 */
	public static void main(String[] args) {
		if(args.length==1){
			String filename=args[0];
			try {
				String input=Battleship.readFile(filename);
				Battleship b=new Battleship(input);
				b.play();
				System.out.println(b.getBoard());
			} catch (FileNotFoundException e) {
				System.out.println("File not found: "+filename);
			} catch(IOException e){
				System.out.println("Error reading file: "+filename);
			} catch(InvalidBattleshipFormatException e){
				System.out.println("Invalid Battleship format");
			} catch (Exception e){
				System.out.println(e.getMessage());
			} finally{
				System.out.println("Merry Christmas!");
			}
		}else{
			System.out.println("You need to specify an input file");
		}
	}

	
}
