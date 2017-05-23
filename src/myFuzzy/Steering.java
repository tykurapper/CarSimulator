package myFuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;


public class Steering {
	public Steering() {
		
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



	public Double getValue(float deviation) {
		fb.setVariable("deviation", deviation);

		// Evaluate
		fb.evaluate();

		// Show output variable's chart
		fb.getVariable("steering").defuzzify();

		return fb.getVariable("steering").getValue();
	}
	
	
//	public static void main(String[] args) throws Exception {
//		String filename = "steering.fcl";
//		FIS fis = FIS.load(filename, true);
//
//		if (fis == null) {
//			System.err.println("Can't load file: '" + filename + "'");
//			System.exit(1);
//		}
//
//		// Get default function block
//		FunctionBlock fb = fis.getFunctionBlock(null);
//
//		// Set inputs
//		fb.setVariable("deviation", 0.2);
//		//fb.setVariable("service", 7.5);
//
//		// Evaluate
//		fb.evaluate();
//
//		// Show output variable's chart
//		fb.getVariable("steering").defuzzify();
//
//		// Print ruleSet
//		System.out.println(fb);
//		System.out.println("Steering: " + fb.getVariable("steering").getValue());
//
//	}
}
