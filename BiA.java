package xulydaluong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.lang.Math;
public class BiA extends JFrame
{
	BufferedImage img;
	Graphics g;

	public static void main(String[] args) {
		new BiA();
	}
	Ball b[] = new Ball[3]; 
	static int w = 500, h = 300;
	Random rand = new Random();
	public BiA() {
		this.setTitle("Game Bia");
		this.setSize(w, h);
		img = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
		g = img.getGraphics();
		this.setDefaultCloseOperation(3);
		
		for (int i=0;i<b.length;i++) {
			double r = 20;
			double x = rand.nextDouble()*(w-2*r)+r;
			double y = rand.nextDouble()*(h-2*r)+r;

			double vx = rand.nextDouble()-0.5;
			double vy = rand.nextDouble()-0.5;
			b[i] = new Ball(x, y, r, vx, vy);
			b[i].start();
		}
		this.setVisible(true);
	
	}
	public void paint(Graphics g1) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLUE);
		for (int i=0;i<b.length;i++) {
			int x = (int)b[i].x;
			int y = (int)b[i].y;
			int r = (int)b[i].r;
			g.drawOval(x-r, y-r, r*2, r*2);
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		g1.drawImage(img, 0,0, null);
		this.repaint(); 
	}

}

class Ball extends Thread{
	double x,y;
	double r;
	double vx,vy;
	public Ball(double x,double y, double r, double vx, double vy)
	{
		this.x = x;
		this.y = y;
		this.r = r;
		this.vx = vx;
		this.vy = vy;	
	}
	public void run() {
		while(true) {
			x += vx;
			y += vy;
			
			//Cham tuong thi quay lai
			if (x-r<=0 || x+r>=BiA.w) vx = -vx;
			if (y-r<=0 || y+r>=BiA.h) vy = -vy;
			
			//Cham bong cung xu ly va cham
			//double distance = maht.sprt((b[1].x-b[2].x)*(b[1].x-b[2].x)-(b[1].y-b[2].y)*(b[1].y-b[2].y);
			//if (distance = 2*r) vacham = 1;
			
			//Ma sat
			if (Math.abs(vx) >=0) 
				{ 
				if (vx > 0) vx = vx - 0.00015;
				else vx = vx + 0.00015;
				}
			if (Math.abs(vy) >=0) 
			{ 
			if (vy > 0) vy = vy - 0.00015;
			else vy = vy + 0.00015;
			}
		
			
			
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}