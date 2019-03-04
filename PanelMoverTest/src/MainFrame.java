import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;


/**
 * 	
 *
 */
public class MainFrame extends JFrame {
	
	private MovingRectanglePanel rectanglePanel;
	private Toolbar toolbar;
	
	
	// *****************************

	public MainFrame() throws Exception {
		super("Moving panel test");
		
		setLayout(new BorderLayout());
		
		rectanglePanel = new MovingRectanglePanel();
		toolbar = new Toolbar();
		
		toolbar.setToolbarListener(new ToolbarListener() {
			
			@Override
			public void flipDirectionEventOccured() {
				rectanglePanel.flipMovmentDirection();
			}
			
			@Override
			public void startMovementEventOccured() {
				rectanglePanel.startMovement();
			}

			@Override
			public void stopMovementEventOccured() {
				rectanglePanel.stopMovement();
			}

			@Override
			public void velocityChangedEventOccured(int velocity) {
				rectanglePanel.setVelocity(velocity);
				
			}
		});
		
		add(toolbar, BorderLayout.PAGE_START);
		add(rectanglePanel, BorderLayout.CENTER);

		setMinimumSize(new Dimension(500, 400));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
}
