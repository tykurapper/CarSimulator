package core;

import javax.swing.UnsupportedLookAndFeelException;

import display.Display;

public class Launcher {
	public static void main(String[] args) {

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    try {
						javax.swing.UIManager.setLookAndFeel(info.getClassName());
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e) {
						e.printStackTrace();
					}
                    break;
                }
            }

		Simulator simulator = new Simulator("CarSimulator", 800, 600);
		simulator.start();
	}
}
