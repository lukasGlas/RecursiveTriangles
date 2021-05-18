import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Random;

import javax.swing.JFrame;

public class Window extends Canvas
{
	private static int s; //maximum size for a equilateral triangle to be shown on screen
	
public static void main(String[] args)
{
	//sets width and height of the window
	//values can be changed for testing purposes
	int x = 500; //width;
	int y = 500; //height
	//create a window, which
	JFrame frame = new JFrame("Recursive Triangles");
	//creates a canvas
	Canvas canvas = new Window ();
	//sets the size
	canvas.setSize(x, y);
	//takes the shorter screen size (usually y), for the triangle to be shown and stay equilateral (task #4)
	if (y < x)
		s = y;
	else
		s = x;
	//the drawing object (canvas) is added to the frame
	frame.add(canvas);
	//resize frame to fit canvas
	frame.pack();
	//making the window visible
	frame.setVisible(true);
	//terminates program when closing the window
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

public void paint(Graphics g)
//draws on the canvas
{
	//draw triangle with base length of 250 (task #1)
	/**
	g.drawLine(0, 250, 250/2, 0);
	g.drawLine(250/2, 0, 250, 250);
	g.drawLine(250, 250, 0, 250);
	*/
	//draw max size triangle (task #1)
	g.drawLine(0, s, s/2, 0);
	g.drawLine(s/2, 0, s, s);
	g.drawLine(s, s, 0, s);
	//draw smaller triangles (task #2)
	/**
	//lower left
	g.drawLine(0, s, s/4, s/2);
	g.drawLine(s/4, s/2, s/2, s);
	g.drawLine(s/2, s, 0, s);
	//lower right... wait there's a smarter way to do this...
	*/
	//draw central, separating triangle (task #2)
	g.drawLine(s/4, s/2, s/4*3, s/2);
	g.drawLine(s/4*3, s/2, s/2, s);
	g.drawLine(s/2, s, s/4, s/2);
	//put the coordinates into Points to enable usage of our drawTriangle method
	Point a = new Point(0,s);
	Point b = new Point(s/2,0);
	Point c = new Point(s,s);
	
	drawTriangle(a, b, c, s, g);
	
	/**
	
	g.setColor(Color.red); // for task #3
	
	//draw a Sierpinski triangle (task #2) WIP & probably wrong
	int rs = s; //size of the recursive triangle
	while (rs > 50)
	{
		g.drawLine(rs/4, rs/2, rs/4*3, rs/2);
		g.drawLine(rs/4*3, rs/2, rs/2, rs);
		g.drawLine(rs/2, rs, rs/4, rs/2);
		rs = rs/2;
	}
	*/
}

private void drawTriangle(Point a, Point b, Point c, int size, Graphics g)
{
//draw the lines of the triangle
	g.drawLine(a.x, a.y, b.x, b.y);
	g.drawLine(b.x, b.y, c.x, c.y);	
	g.drawLine(c.x, c.y, a.x, a.y);
//call method to calculate midpoints
	Point ab = calcMidPoint(a,b);
	Point bc = calcMidPoint(b,c);
	Point ac = calcMidPoint(c,a);	
//termination	
	if(size>10)
{
//drawing the smaller triangles
drawTriangle(a, ab, ac, size/2, g);			
drawTriangle(ab, b, bc, size/2, g);		
drawTriangle(ac, bc, c, size/2, g);	
//g.setColor(Color.red);
Color z = new Color(new Random().nextInt(0xFFFFFF));
g.setColor(z);
fillTriangle (ab, bc, ac, size/2, g);
}
	
}

//this fills in the triangle with color by creating a new polygon
void fillTriangle(Point a, Point b, Point c, int size, Graphics g) {
	Polygon poly = new Polygon();
	poly.addPoint(a.x,a.y);
	poly.addPoint(b.x,b.y);
	poly.addPoint(c.x,c.y);
	g.fillPolygon(poly);
}

private Point calcMidPoint(Point one, Point two)
{
int midX = (one.x + two.x) /2;
int midY = (one.y + two.y) /2;
Point mPoint = new Point(midX, midY);
return mPoint;
}
}

