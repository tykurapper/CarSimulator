package myFuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class ObstacleHandle {
	public ObstacleHandle(){
		
	}
	
	private FunctionBlock fb;
	
	public void setFb(String fileName) {
		String filename = fileName;
		FIS fis = FIS.load(filename, true);

		if (fis == null) {
			System.err.println("Can't load file: '" + filename + "'");
			System.exit(1);
		}

		// Get default function block
		FunctionBlock fb = fis.getFunctionBlock(null);
		this.fb = fb;

		// Set inputs
		//fb.setVariable("deviation", 0.2);
		//fb.setVariable("service", 7.5);

		// Evaluate
		//fb.evaluate();

		// Show output variable's chart
		//fb.getVariable("steering").defuzzify();

		// Print ruleSet
//		System.out.println(fb);
//		System.out.println("Steering: " + fb.getVariable("steering").getValue());
		
		//this.fb = fb;
//		return fb;
	}
	
	public FunctionBlock getFb() {
		return fb;
	}
	
	public Double getValue(float deviation, double distance) {
		fb.setVariable("deviation", deviation);
		fb.setVariable("distance", distance);

		// Evaluate
		fb.evaluate();

		// Show output variable's chart
		fb.getVariable("speed").defuzzify();

		return fb.getVariable("speed").getValue();
	}
}
