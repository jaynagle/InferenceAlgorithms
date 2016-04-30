/**
 * 
 */
package com.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.model.BayesNet;
import com.model.Node;

/**
 * @author Ankit Sadana, Jay Nagle
 *
 */
public class Inference {

	private static String METHOD;
	private static int NO_OF_SAMPLES;
	private BayesNet alarmNet;

	private Map<Node, String> evidenceMap;
	private Map<Node, String> queryList;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		METHOD = args[0];
		NO_OF_SAMPLES = Integer.parseInt(args[1]);

		System.out.println("Method value: " + METHOD);
		System.out.println("Number of samples expected: " + NO_OF_SAMPLES);
		
		Inference inference = new Inference();

		// Create the Alarm network as given in book.
		inference.createAlarmNetwork();

		// Store Evidences and Query
		inference.populateEvidences();

		// Do Actual inference now...
	}

	public void populateEvidences() {
		Scanner sc = new Scanner(System.in);

		evidenceMap = new HashMap<>();
		queryList = new HashMap<>();

		boolean isFirstInput = true;
		int numEvidences = -1;
		int numQueries = -1;
		String[] lineArray;

		System.out.print("\nPlease enter the values for \"N M\": ");
		while (sc.hasNext()) {
			
			if (isFirstInput) {
				lineArray = sc.nextLine().split(" ");
				numEvidences = Integer.parseInt(lineArray[0]);
				numQueries = Integer.parseInt(lineArray[1]);
				isFirstInput = false;
				
				// Setting evidence to 0 for enumeration method
				if(METHOD.equalsIgnoreCase("e"))
					numEvidences = 0;
				
			} else if (numEvidences > 0) {
				lineArray = sc.nextLine().split(" ");
				evidenceMap.put(alarmNet.getNodeByName(lineArray[0]), lineArray[1]);
				numEvidences--;
				
			} else if (numQueries > 0) {
				lineArray = sc.nextLine().split(" ");
				queryList.put(alarmNet.getNodeByName(lineArray[0]), lineArray[1]);
				numQueries--;
			}

			if (numQueries == 0 && numEvidences == 0) {
				System.out.println("Doing inference");
				break;
			}
		}
		sc.close();
	}

	/**
	 * This method creates an instance of Bayes Network for the Alarm network
	 * given in Russel Norvig Book.
	 */
	private void createAlarmNetwork() {
		alarmNet = new BayesNet();

		System.out.println("Creating nodes");
		Node a = new Node("A");
		Node b = new Node("B");
		Node e = new Node("E");
		Node j = new Node("J");
		Node m = new Node("M");

		a.addParent(b);
		a.addParent(e);
		a.addChild(j);
		a.addChild(m);

		b.addChild(a);
		e.addChild(a);
		j.addParent(a);
		m.addParent(a);

		System.out.println("Creating Bayes net");
		alarmNet.addNode(a);
		alarmNet.addNode(b);
		alarmNet.addNode(e);
		alarmNet.addNode(j);
		alarmNet.addNode(m);
		
		alarmNet.showBayesNet();
	}
}
