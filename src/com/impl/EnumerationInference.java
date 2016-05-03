/**
 * 
 */
package com.impl;

import java.util.List;
import java.util.Map;

import com.model.BayesNet;
import com.model.Node;

/**
 * @author Ankit Sadana, Jay Nagle
 *
 */
public class EnumerationInference {

	private BayesNet alarmNet;
	private Map<Node, String> evidenceMap;
	private List<Node> querList;
	
	public EnumerationInference(BayesNet alarmNet, Map<Node, String> evidenceMap, List<Node> queryList) {
		this.alarmNet = alarmNet;
		this.evidenceMap = evidenceMap;
		this.querList = queryList;
	}
	
	public void infer() {
		
	}

}
