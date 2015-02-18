package com.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class DoLunch {

	BufferedReader br;
	ArrayList<String> inputAddress, location, path, resultPath;
	String avoid, edges, peggyStartAddress, samStartAddress;
	int countLocation, adjMatrice[][], row, col, index, temp[][];
	String []avoidLoc;
	String []peggyStartLoc;
	String []samStartLoc;
	
	// Constructor initialization
	public DoLunch() {
		
		br = new BufferedReader(new InputStreamReader(System.in)); 
		inputAddress = new ArrayList<String>();
		location = new ArrayList<String>();
		path  = new ArrayList<String>();
		resultPath  = new ArrayList<String>();
		countLocation = 0;
		index = -5;
	}
	
	
	// Input map location
	void input() throws IOException{
		
		System.out.println("Map: ");
		while (!(edges = br.readLine()).equals("")){
			inputAddress.add(edges);
			String[] node = edges.split(" ");
			location = checkNewLocation(node);		// call checkNewMethod()		
		}
	}
	
	
	// check for new locations and store it in an ArrayList location
	ArrayList<String> checkNewLocation(String[] node){
		for(int i = 0; i<node.length; i++){
			
			// check if location arraylist is empty
			if(location.isEmpty()){
				location.add(node[i]);
				location.add(node[i+1]);
				break;
			}
			
			// check if location arraylist contain the element node[i]
			if(location.contains(node[i])){
				continue;
			}
			
			else{	
				location.add(node[i]);		// match not found, insert the node[i] element to location arraylist
			}
			
		}
		
		Collections.sort(location);			// sort location arraylist
		return location;
	}
		
		
	// Location to avoid
	void avoidLocation() throws IOException{
		
		System.out.println("Avoid: ");
		avoid = br.readLine();
			
	}
	
	
	// Peggy's start location
	void peggyStartLocation() throws IOException{
		
		System.out.println("Peggy: ");
		
		// check if start location is empty
		if((peggyStartAddress = br.readLine()).equals("")){
			
			System.out.println("Enter atleast one start location of Peggy");
			peggyStartAddress = br.readLine();
		}
		
	}
	
	
	// Sam's start location
	void samStartLocation() throws IOException{

		System.out.println("Sam: ");
		
		// check if start location is empty
		if((samStartAddress = br.readLine()).equals("")){
			
			System.out.println("Enter atleast one start location of Sam");
			samStartAddress = br.readLine();
		}
		
		adjMatrice(location);
	}
	
	
	// form an adjacent matrice for the given input location
	void adjMatrice(ArrayList<String> loc){
		
		countLocation = loc.size();
		System.out.println("lenght =" + countLocation);
		
		
		adjMatrice= new int[countLocation][countLocation];		// create a 2D matrice
		
		// initially set the adjMatrix to 0
		for(int i = 0; i<countLocation; i++){
			for(int j=0; j<countLocation; j++){
				adjMatrice[i][j] = 0;				
			}
		}
			
		// locate the address pair and set to one 1 in the adjacent matrix if found
		checkEdges();
	}
	
	
	// check  for address pair
	void checkEdges(){
		
		Iterator<String> i = inputAddress.iterator();
		while(i.hasNext()){
			
			String addrPair = i.next();
			String []node = addrPair.split(" ");
						
			for(int j = 0; j< location.size(); j++){
				int c = 0;
				
				if(location.get(j).equals(node[c])){
					row = j;
				}
				
				if(location.get(j).contains(node[c+1])){
					col = j;
				}		
			}
			
			/*System.out.println("row =" + row);
			System.out.println("col =" + col);*/
			
			// set to 1 if an address pair is found
			adjMatrice[row][col] = 1;
			adjMatrice[col][row] = 1;
				
		}
		
				
		temp = new int[countLocation][countLocation];
		
		System.out.println("original adj matrix :");
		System.out.println(location);
		
		for(int k = 0; k<countLocation; k++){
			for(int j = 0; j<countLocation; j++){
				temp[k][j] = adjMatrice[k][j];
				System.out.print("  " + adjMatrice[k][j] + " ");
			}
			System.out.print("\n");
		}
		
		pathTraversal();
		
	}
	
	
	// trace the path of the location
	void pathTraversal(){
		
		int flag_avoid = 0, flag_dest = 0;
			
		avoidLoc = avoid.split(" ");
		peggyStartLoc = peggyStartAddress.split(" ");
		samStartLoc = samStartAddress.split(" ");
		
		System.out.print("\n avoid loc = ");
		for(int i = 0; i<avoidLoc.length; i++){
			System.out.print(avoidLoc[i] + " ");
		}
		
		System.out.print("\n peggy start loc = ");
		for(int j = 0; j<peggyStartLoc.length; j++){
			System.out.print(peggyStartLoc[j] + " ");
		}
		
		System.out.print("\n sam start loc = ");
		for(int k = 0; k<samStartLoc.length; k++){
			System.out.print(samStartLoc[k] + " ");
		}
		
		
		// start traversing from peggy's first start location
		for(int i = 0; i<peggyStartLoc.length; i++){
			
			adjMatrice = temp;
			path.clear();
			
			for(int x = 0; x<location.size(); x++){
				if(peggyStartLoc[i].equals(location.get(x))){
					index = x;
					break;
				}
			}
			
			
			String vertex = location.get(index);
			
			flag_avoid = checkAvoid(vertex, index);
			if( flag_avoid == 0){
				
				
				if(path.isEmpty()){
					path.add(vertex);
				}
				else if(!path.contains(vertex)){
					path.add(vertex);
				}
			
			}
			else{
				continue;
			}
			
			flag_dest = checkDest(vertex, index);
			if(flag_dest == 1){
				if(!path.contains(vertex)){
					path.add(vertex);
				}
				
				continue;
			}
			
			searchPath(index);	
		}
		
		outputPath();
		
	}
	
	
	// check if the node is a destination node
	int checkDest(String vertex, int i){
		int flag = 0;
		
		for(int k = 0; k<samStartLoc.length; k++){
			
			if(vertex.equals(samStartLoc[k])){
				
				flag = 1;
				for(int z= 0; z<location.size(); z++){
					
					if(location.get(z).equals(samStartLoc[k])){
					    
					    adjMatrice[i][z] = 5;	// destination reached
						adjMatrice[z][i] = 5;	// destination reached
						break;
					}
				}
			}
		}
		
		if(!path.contains(vertex)){
			path.add(vertex);
		}
		
		//System.out.println("index = " + index);	
		
		return flag;
	}
	

	// check if the node is an avoid node
	int checkAvoid(String vertex, int i){
		int flag = 0;	
		
		for(int k = 0; k<avoidLoc.length; k++){
			
			if(vertex.equals(avoidLoc[k])){
				
				flag = 1;
				for(int z= 0; z<location.size(); z++){
					
					if(location.get(z).equals(avoidLoc[k])){
					    
					    adjMatrice[i][z] = 3;		// if it is an avoid location
						adjMatrice[z][i] = 3;		// if it is an avoid location
						break;
					}
				}	
			}
		}
			
		return flag;
	}
	
	
	// search for the path to the next adjacent node
	ArrayList<String> searchPath(int index){
		
		int flag = 0;
		int flagAvoid = 0;
		int flagDest = 0;
		
		for(int i = 0; i<countLocation; i++){
		
			int len;
			
			// check if there is a path to another node
			if(adjMatrice[index][i] == 1){
				
				flagAvoid = checkAvoid(location.get(i), index);
				if(flagAvoid == 1){

					System.out.println("\n");
					System.out.println("path " + path);
					System.out.println("adj matrix :");
					System.out.println(location);
					
					for(int k = 0; k<countLocation; k++){
						for(int j = 0; j<countLocation; j++){
							System.out.print("  " + adjMatrice[k][j] + " ");
						}
						System.out.print("\n");
					}
					
					continue;
				}
			
				flag = checkDest(location.get(i), index);
				if (flag == 1){
					
					
					System.out.println("\n");
					System.out.println("path " + path);
					System.out.println("adj matrix :");
					System.out.println(location);
					
					for(int k = 0; k<countLocation; k++){
						for(int j = 0; j<countLocation; j++){
							System.out.print("  " + adjMatrice[k][j] + " ");
						}
						System.out.print("\n");
					}
					
					
					
					for(int j = 0; j<path.size(); j++){
						
						if(!(resultPath.contains(path.get(j)))){
							resultPath.add(path.get(j));
						}
					}
					
					System.out.println("result path = "+ resultPath);
					
					len = path.size();
					if(len >= 1){
						path.remove(len-1);
					}
					
					
						
					len = path.size();
					for(int x = 0; x<location.size(); x++){
						
						if(path.get(len-1).equals(location.get(x))){
							index = x;
							break;
						}
					}
					
					flagDest = 1;		
				}
				
				if(flag == 0){
					adjMatrice[index][i] = 2;		// set matrice element to 2 when node visited
					adjMatrice[i][index] = 2;
					
					//System.out.println("index = " + index);
					System.out.println("\n");
					System.out.println("path =" + path);
					System.out.println("adj matrix :");
					System.out.println(location);
					
					for(int k = 0; k<countLocation; k++){
						for(int j = 0; j<countLocation; j++){
							System.out.print("  " + adjMatrice[k][j] + " ");
						}
						System.out.print("\n");
					}
					
					if(!path.contains(location.get(i))){
						path.add(location.get(i));
					}
					
					
					index = i;
					searchPath(index);
				}
			}
				
			else if(i == (countLocation-1) && adjMatrice[index][i] == 0){
				
				len = path.size();
				if(len >= 1){
					path.remove(len-1);
				}
				
				if(path.isEmpty()){
					//System.out.println("no path found");
					break;
				}
				
				else{
					
					len = path.size();
					for(int x = 0; x<location.size(); x++){
						
						if(path.get(len-1).equals(location.get(x))){
							index = x;
							break;
						}
					}
					searchPath(index);
				}
					
			}
			if(flagDest == 1){
				break;
			}
		}
		
		return path;
	}
	
	
	void outputPath(){
		
		if(!resultPath.isEmpty()){
			
			Collections.sort(resultPath);
			
			System.out.println("\n output path");
			for(String s : resultPath){
				System.out.println(s);
			}
		}
		else{
			System.out.println("No common location found");
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		
		DoLunch ob = new DoLunch();
	
		ob.input();
		ob.avoidLocation();
		ob.peggyStartLocation();
		ob.samStartLocation();	
	}
}
