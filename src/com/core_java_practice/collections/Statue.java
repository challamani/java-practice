package com.core_java_practice.collections;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 * **Problem statement**:
 * 
 * Bob the Adventurer is one step away from solving the mystery of an ancient Mayan tomb. 
 * He just approched the secret chamber where the secret Mayan scriptures are locked in a chest. 
 * 
 * There are N ancient statues in the room. After long thought,Bob figured out that in order to open the treasure chest he needs to stand in the middle of the room and hit every statue with a laser ray at the same time. 
 * Bob is a highly experienced adventurer, so setting multiple laser rays at the same time is not a problem for him. 
 * Moreover, every ray that he creates is perfectly straight and never changes direction at all. The middle of the room, where Bob is standing, has coordinates (0, 0). Every statue is located at some point with coordinates (x, y). 
 * Each statue is made of pure glass, so that if any ray hits it,it does not stop, but goes through the statue and continues beyond in the same, unchanged direction. 
 * Bob wonders how he can hit every ancient statue in the room using the fewest rays possible. Assume that the following declarations are given: struct Point2D { int x; int y; }; Write a function int solution(struct Point2D A[], int N); that, given an array of points A, 
 * representing the locations of the statues, returns the minimal number of rays that Bob must set in order to hit every statue in the room. 
 * 
 * For example, 
 * given an array A 
 * A[0].x = -1 A[0].y = -2 (statue 0) 
 * A[1].x = 1 A[1].y = 2 (statue 1) 
 * A[2].x = 2 A[2].y = 4 (statue 2) 
 * A[3].x = -3 A[3].y = 2 (statue 3) 
 * A[4].x = 2 A[4].y = -2 (statue 4) 
 * 
 * your function should return 4.
 * */
public class Statue {

	
	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);
		int statue =sc.nextInt();
		int i=0;
		List<Coordinate> quarter1 = new ArrayList<>();
		List<Coordinate> quarter2 = new ArrayList<>();
		List<Coordinate> quarter3 = new ArrayList<>();
		List<Coordinate> quarter4 = new ArrayList<>();
		
		while(++i <= statue){
			
			float x = sc.nextInt();
			float y = sc.nextInt();
			
			if(x>=0 && y>=0)
				quarter1.add(new Coordinate(x, y));
			else if(x < 0 && y >=0)
				quarter2.add(new Coordinate(x, y));
			else if(x >=0 && y < 0)
				quarter4.add(new Coordinate(x, y));
			else 
				quarter3.add(new Coordinate(x, y));
		}
		
		sc.close();
		
		int uniqueRaysCnt = 0;
		uniqueRaysCnt+= getUniqueSlopeSetCnt(quarter1); 
		uniqueRaysCnt+= getUniqueSlopeSetCnt(quarter2);
		uniqueRaysCnt+= getUniqueSlopeSetCnt(quarter3);
		uniqueRaysCnt+= getUniqueSlopeSetCnt(quarter4);
		
		System.out.println("Fewest rays possible:"+uniqueRaysCnt);
	}
	
	private static int getUniqueSlopeSetCnt(List<Coordinate> quarter){
		
		Set<Float> uniqueSlopeSet = new HashSet<>();
		for(Coordinate coordinate:quarter){
			 uniqueSlopeSet.add(coordinate.getY() / coordinate.getX());
		}
		
		return uniqueSlopeSet.size();
	}
	
	
}

class Coordinate{
	
	private float x;
	private float y;
	
	public Coordinate(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}
	
	
}
