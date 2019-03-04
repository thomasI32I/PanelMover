import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * 
 *
 */
public class Toolbar extends JPanel implements ActionListener {

	private JButton flipDirectionButton;
	private JButton startMovementButton;
	private JButton stopMovementButton;
	private JComboBox<Integer> velocityList;

	private ToolbarListener toolbarListener;

	/**
	 * Constructor
	 */
	public Toolbar() {

		setBorder(BorderFactory.createEtchedBorder());

		flipDirectionButton = new JButton("Flip Direction");
		flipDirectionButton.addActionListener(this);
		startMovementButton = new JButton("Start");
		startMovementButton.addActionListener(this);
		stopMovementButton = new JButton("Stop");
		stopMovementButton.setEnabled(false);
		stopMovementButton.addActionListener(this);

		Integer[] velocities = { 5, 10, 25, 50, 100 };
		velocityList = new JComboBox<Integer>(velocities);
		velocityList.setSelectedIndex(2);

		velocityList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<Integer> cb = (JComboBox<Integer>) e.getSource();
				Integer value = (Integer) cb.getSelectedItem();
				int velocity;
				switch (value) {
				case 5: // 5%
					velocity = 20;
					break;
				case 10: // 10%
					velocity = 10;
					break;
				case 25: // 25%
					velocity = 4;
					break;
				case 50: // 50%
					velocity = 2;
					break;
				case 100: // 100%
					velocity = 1;
					break;
				default: // 25%
					velocity = 4;
				}

				toolbarListener.velocityChangedEventOccured(velocity);
				System.out.println("velocity change occured!");
			}
		});

		setLayout(new FlowLayout(FlowLayout.LEFT));

		add(flipDirectionButton);
		add(startMovementButton);
		add(stopMovementButton);
		add(velocityList);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton clicked = (JButton) e.getSource();

		if (clicked == flipDirectionButton) {
			if (toolbarListener != null) {
				toolbarListener.flipDirectionEventOccured();
			}
		}

		if (clicked == startMovementButton) {
			if (toolbarListener != null) {
				toolbarListener.startMovementEventOccured();
			}
			startMovementButton.setEnabled(false);
			stopMovementButton.setEnabled(true);
		}

		if (clicked == stopMovementButton) {
			if (toolbarListener != null) {
				toolbarListener.stopMovementEventOccured();
			}
			startMovementButton.setEnabled(true);
			stopMovementButton.setEnabled(false);
		}

	}

	public void setToolbarListener(ToolbarListener toolbarListener) {
		this.toolbarListener = toolbarListener;
	}

}