import java.util.ArrayList;
import java.util.List;

public class Main {

	static ArrayList<Polygon> poligons = new ArrayList<>();
	static Point[] romyJulies = new Point[2];
	static int noOfBuildings = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readInput();
		int blockBuildings = 0;
		for (Polygon poligon:poligons) {
			List<Point[]> lineSegments = poligon.getLineSegments();
			for (Point[] lineSegment: lineSegments) {
				if (lineSegmentCrossed(romyJulies, lineSegment)) {
					blockBuildings++;
				}
			}
		}
		
		System.out.println(blockBuildings);

	}
	
	private static boolean lineSegmentCrossed(Point[] lineSeg1,  Point[] lineSeg2) {
		// TODO Auto-generated method stub
		return false;
	}

	private static void readInput() {
		// TODO Auto-generated method stub
		
	}

}

class Polygon {
	ArrayList<Point> points = new ArrayList<>();

	public List<Point[]> getLineSegments() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

class Point {
	int x,y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}