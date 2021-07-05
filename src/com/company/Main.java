package com.company;
import java.util.*;
import java.io.*;
class GameHelper{
	private  static final String alphabet = "abcdefg";
	private int gridLength=7;
	private int gridSize=49;
	private int [] grid=new int[gridSize];
	private int comCount=0;

	public String getUserInput(String prompt){
		String inputLine =null;
		System.out.println(prompt+ " ");
		try{
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine=is.readLine();
			if(inputLine.length()==0)return null;
		} catch (IOException e ){
			System.out.println("IOException: "+e );
		}
		return inputLine.toLowerCase();
	}
	public ArrayList<String>placeDotCom(int comSize){
		ArrayList<String>alphaCells=new ArrayList<String>();
		String temp=null;
		int[]coords=new int[comSize];
		int attempts=0;
		boolean success =false;
		int location =0;
		comCount++;
		int incr=1;
		if((comCount%2)==1){
			incr=gridLength;
		}
		while ((!success & attempts++<200)){
			location=(int)(Math.random()*gridSize);
			int x=0;
			success=true;
			while(success && x<comSize){
				if(grid[location]==0) {
					coords[x++] = location;
					location += incr;
					if (location >= gridSize) {
						success = false;
					}
					if (x > 0 && (location % gridLength == 0)) {
						success = false;
					}
				}
					else{
						success=false;
					}
				}
			}
		int x=0;
		int row=0;
		int column=0;
		while (x<comSize){
			grid[coords[x]]=1;
			row=(int)(coords[x]/gridLength);
			column=coords[x]%gridLength;
			temp=String.valueOf(alphabet.charAt(column));
			alphaCells.add(temp.concat(Integer.toString(row)));
			x++;
		}
		return alphaCells;
		}
	}

class DotComBust{
    private ArrayList<String>locationCells;
    private String name;
    public void setLocationCells(ArrayList<String>loc){
    	locationCells=loc;
	}
	public void setName(String n){
    	name =n;
	}
	public String checkYourself(String userInput){
    	String result="miss";
    	int index=locationCells.indexOf(userInput);
    	if(index>=0){
    		locationCells.remove(index);
		}
    	if(locationCells.isEmpty()){
    		result="kill";
    		System.out.println("Ouch! You sunk"+ name+" ");
		}
    	else {
    		result="hit";
		}
    	return result;
	  }
    }



public class Main {

    public static void main(String[] args) {
    	int numOfGuess=0;
    	GameHelper helper=new GameHelper();
	   SimpleDotCom dot= new SimpleDotCom();
	   int randomNum=(int) (Math.random()*5);
	   int[]locations={randomNum,randomNum+1,randomNum+2};
	   dot.setLocationCells(locations);
	   boolean isAlive=true;
	   while(isAlive==true){
	   	String guess=helper.getUserInput("enter a number");
	   	String result= dot.checkYourSelf(guess);
	   	numOfGuess++;
	   	if(result.equals("kill")){
	   		isAlive=false;
	   		System.out.println("you took "+numOfGuess+" guesses");
		}
	   }
    }
}
