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
	private List<Node> queryList;

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
		inference.callInferenceAlgorithms();
	}

	public void populateEvidences() {
		Scanner sc = new Scanner(System.in);

		evidenceMap = new HashMap<>();
		queryList = new ArrayList<>();

		boolean isFirstInput = true;
		int numEvidences = -1;
		int numQueries = -1;
		String[] lineArray;
		Node foundNode = null;

		System.out.print("\nPlease enter the values for \"N M\": ");
		while (sc.hasNext()) {
			lineArray = sc.nextLine().split("\\s");

			if (isFirstInput) {
				numEvidences = Integer.parseInt(lineArray[0]);
				numQueries = Integer.parseInt(lineArray[1]);
				isFirstInput = false;

				// Setting evidence to 0 for enumeration method
				if (METHOD.equalsIgnoreCase("e"))
					numEvidences = 0;

			} else if (numEvidences > 0) {
				if (!(lineArray[1].equalsIgnoreCase("t") || lineArray[1].equalsIgnoreCase("f"))) {
					System.out.println("Entry should be \"node t/f\"");
				}
				foundNode = alarmNet.getNodeByName(lineArray[0]);
				if (foundNode != null) {
					evidenceMap.put(foundNode, lineArray[1]);
					numEvidences--;
				}

			} else if (numQueries > 0) {
				foundNode = alarmNet.getNodeByName(lineArray[0]);
				if (foundNode != null) {
					queryList.add(foundNode);
					numQueries--;
				}
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

		HashMap<Node, String> entry;
		entry = new HashMap<>();

		System.out.println("Creating nodes");
		Node a = new Node("A");
		Node b = new Node("B");
		Node e = new Node("E");
		Node j = new Node("J");
		Node m = new Node("M");

		// Populating node A
		a.addParent(b);
		a.addParent(e);
		a.addChild(j);
		a.addChild(m);

		entry.put(b, "t");
		entry.put(e, "t");
		a.addNodeProbability(entry, 0.95f);
		entry = new HashMap<>();

		entry.put(b, "t");
		entry.put(e, "f");
		a.addNodeProbability(entry, 0.94f);
		entry = new HashMap<>();

		entry.put(b, "f");
		entry.put(e, "t");
		a.addNodeProbability(entry, 0.29f);
		entry = new HashMap<>();

		entry.put(b, "f");
		entry.put(e, "f");
		a.addNodeProbability(entry, 0.001f);
		entry = new HashMap<>();

		// Populating node B
		b.addChild(a);
		b.addNodeProbability(null, 0.001f);
		//b.addNodeProbability(null, 0.45f);

		// Populating node E
		e.addChild(a);
		e.addNodeProbability(null, 0.002f);
		//e.addNodeProbability(null, 0.60f);

		// Populating node J
		j.addParent(a);

		entry.put(a, "t");
		j.addNodeProbability(entry, 0.90f);
		entry = new HashMap<>();

		entry.put(a, "f");
		j.addNodeProbability(entry, 0.05f);
		entry = new HashMap<>();

		// Populating node M
		m.addParent(a);

		entry.put(a, "t");
		m.addNodeProbability(entry, 0.70f);
		entry = new HashMap<>();

		entry.put(a, "f");
		m.addNodeProbability(entry, 0.01f);

		System.out.println("Creating Bayes net");
		alarmNet.addNode(a);
		alarmNet.addNode(b);
		alarmNet.addNode(e);
		alarmNet.addNode(j);
		alarmNet.addNode(m);

		alarmNet.showBayesNet();
	}

	private void callInferenceAlgorithms() {
		
		List<int[][]> sampleList = new Sampler().getSamples(alarmNet);
		//int[][] sampleList = new Sampler().getSamples(alarmNet, NO_OF_SAMPLES);
		
		switch (METHOD) {
		case "p":
			PriorSampling priorSampling = new PriorSampling(alarmNet, evidenceMap, queryList, sampleList);
			priorSampling.infer();
			break;
		case "r":
			new RejectionSampling(alarmNet, evidenceMap, queryList, sampleList).infer();
			break;
		case "l":
			break;
		case "e":
			break;
		default:
			break;
		}
	}
}
