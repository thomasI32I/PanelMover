
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

/**
 * 
 */
public class MovingRectanglePanel extends JPanel {
	
	private MovingPanel movingPanel1;
	private MovingPanel movingPanel2;

	private MovingRectanglePanelListener panelSizeListener;

	private class MyComponentListener implements ComponentListener {

		@Override
		public void componentResized(ComponentEvent ce) {
			if (ce.getSource() == MovingRectanglePanel.this) {

				if (panelSizeListener != null) {
					panelSizeListener.sizeChangeOccured();
				}

				// testing purpose
				Component comp = ce.getComponent();
				Dimension dim = comp.getSize();
				System.out.println(dim);
			}
		}

		@Override
		public void componentMoved(ComponentEvent ce) {
		}

		@Override
		public void componentShown(ComponentEvent ce) {
		}

		@Override
		public void componentHidden(ComponentEvent ce) {

		}
	}


	/**
	 * 
	 */
	public MovingRectanglePanel() {

		Dimension dim = getPreferredSize();
		dim.height = 300;
		setPreferredSize(dim);
		
		//problem: when resizing the outer panel, error cases can occur at panel1
		movingPanel1 = new MovingPanel(this, 50, 50, Color.BLUE);
		movingPanel2 = new MovingPanel(this, 40, 40, Color.RED);
		movingPanel2.setMoveClockwise(false);
		
		this.setLayout(null);
		this.add(movingPanel1);
		this.add(movingPanel2);
		this.addComponentListener(new MyComponentListener());
		
		Thread thread1 = new Thread(movingPanel1);
		thread1.start();
		Thread thread2 = new Thread(movingPanel2);
		thread2.start();
		
	}

	public void setPanelSizeListener(MovingRectanglePanelListener panelSizeListener) {
		this.panelSizeListener = panelSizeListener;
	}
	
	public void flipMovmentDirection() {
		movingPanel1.flipMovmentDirection();
		movingPanel2.flipMovmentDirection();
	}
	
	public void stopMovement() {
		movingPanel1.setPaused(true);
		movingPanel2.setPaused(true);
	}
	
	public void startMovement() {
		movingPanel1.setPaused(false);
		movingPanel2.setPaused(false);
	}
	
	public void setVelocity(int velocity) {
		movingPanel1.setVelocity(velocity);
		movingPanel2.setVelocity(velocity);
	}

}
