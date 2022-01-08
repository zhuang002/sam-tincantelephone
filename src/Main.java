import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static ArrayList<Polygon> polygons = new ArrayList<>();
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
		
		// check if lineSeg1 is perpendicular to x axis
		if (lineSeg1[0].x == lineSeg1[1].x) {
			// check if lineSeg2 is perpendicular to x axis
			if (lineSeg2[0].x == lineSeg2[1].x) {
				// both lineSegs are perpendicular to x axis
				if (lineSeg1[0].x == lineSeg2[0].x) {
					// 2 line segs are on a same line.
					if (isOverlap(lineSeg1[0].y, lineSeg1[1].y, lineSeg2[0].y, lineSeg2[1].y)) {
						return true;
					} return false;
				} else {
					// 2 lineSegs are in parallel
					return false;
				}
			} else {
				// lineSeg1 vertical but lineSeg2 is not.
				return verticalLineCross(lineSeg1, lineSeg2);
				
			}
		} else {
			// check if lineSeg2 is perpendicular to x axis
			if (lineSeg2[0].x == lineSeg2[1].x) {
				// lineSeg2 is vertical, but lineSeg1 is not.
				return verticalLineCross(lineSeg2, lineSeg1);
			} else {
				// both 2 lineSegs are not vertical
				Point crossPoint = getCrossPointNonVertical(lineSeg1,lineSeg2);
				if (crossPoint ==  null) return false;
				if (crossPoint.x<-1000 || crossPoint.x>1000 || crossPoint.y<-1000 || crossPoint.y>1000) {
					return false;
				}
				return isOnLineSeg(crossPoint,lineSeg1) && isOnLineSeg(crossPoint,lineSeg2);
			}
		}
	}

	private static Point getCrossPointNonVertical(Point[] lineSeg1, Point[] lineSeg2) {
		// TODO Auto-generated method stub
		double k1 = (lineSeg1[0].y-lineSeg1[1].y) / (lineSeg1[0].x - lineSeg1[1].x);
		double m1 = lineSeg1[0].y - k1*lineSeg1[0].x;
		
		double k2 = (lineSeg2[0].y-lineSeg2[1].y) / (lineSeg2[0].x - lineSeg2[1].x);
		double m2 = lineSeg2[0].y - k2*lineSeg2[0].x;
		
		if (k1==k2) {
			if (isOverlap(lineSeg1[0].x, lineSeg1[1].x, lineSeg2[0].x, lineSeg2[1].x))
				return new Point(-5000, -5000);
			else return null;
		}
		
		double x = (m2-m1)/(k1-k2);
		double y = k1*x+m1;
		return new Point(x,y);
	}

	private static boolean isOverlap(double y1, double y2, double y3, double y4) {
		// TODO Auto-generated method stub
		if (isInMiddle(y1,y2,y3)) 
			return true;
		else return isInMiddle(y3,y4, y1);
	}

	private static boolean isOnLineSeg(Point crossPoint, Point[] lineSeg) {
		// TODO Auto-generated method stub
		if (lineSeg[0].x == lineSeg[1].x) {
			// is a vertical line. I need to check y.
			return isInMiddle(lineSeg[0].y, lineSeg[1].y, crossPoint.y);
		} else {
			// is not a vertical line. we check x.
			return isInMiddle(lineSeg[0].x, lineSeg[1].x, crossPoint.x);
		}
	}

	private static boolean isInMiddle(double x1, double x2, double m) {
		if (x1<m) {
			// x1 on the left of m
			return x2>=m;
		} else if (x1>m) {
			// x1 on the right of m
			return x2<=m;
		} else {// m equals to x2, check x2 
			return true;
		}
	}

	private static boolean verticalLineCross(Point[] lineSeg1, Point[] lineSeg2) {
		// TODO Auto-generated method stub
		double x = lineSeg1[0].x;
		double k = (lineSeg2[0].y - lineSeg2[1].y)/(lineSeg2[0].x- lineSeg2[1].x);
		double m = lineSeg2[0].y - k*lineSeg2[0].x;
		double y = k*lineSeg2[0].x + m;
		
		Point crossPoint = new Point(x,y);
		return isOnLineSeg(crossPoint,lineSeg1) && isOnLineSeg(crossPoint,lineSeg2);
	}

	private static void readInput() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		romyJulies[0] = new Point(sc.nextDouble(), sc.nextDouble());
		romyJulies[1] = new Point(sc.nextDouble(), sc.nextDouble());
		
		int noPolygon = sc.nextInt();
		for (int i=0;i<noPolygon;i++) {
			Polygon polygon = new Polygon();
			int noPoints = sc.nextInt();
			for (int j=0;j<noPoints;j++) {
				polygon.points.add(new Point(sc.nextDouble(), sc.nextDouble()));
			}
			polygons.add(polygon);
		}
	}

}

class Polygon {
	ArrayList<Point> points = new ArrayList<>();

	public List<Point[]> getLineSegments() {
		// TODO Auto-generated method stub
		ArrayList<Point[]> segs = new ArrayList<>();
		
		for (int i=0;i<points.size();i++) {
			Point[] seg = new Point[2];
			seg[0] = points.get(i);
			seg[1] = points.get((i+1)%points.size());
		}
		return segs;
	}
	
	
}

class Point {
	double x,y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}