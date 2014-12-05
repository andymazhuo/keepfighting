package Google;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TopSort {
	
	//http://en.wikipedia.org/wiki/Topological_sorting, I named it dependency sort, or scheduling sort. 
	public static void main(String[] args) {
		List<String> input = new ArrayList<>();
		input.add("foo");
		input.add("cat");
		input.add("cao");
		input.add("bag");
		input.add("bat");
		input.add("aac");
		TopSort tp = new TopSort();
		System.out.println(tp.getTopOrder(input));
	}

	/**
	 * Given an string array sorted by characters in another string.
	   Return that sorting base string.
       Example. give you
       {foo, cat, cao, bag, bat, aac}
       return: fcbagto
	 */

	public String getTopOrder(List<String> input) {
		Collection<GNode> graph =  buildGraph(input);
		StringBuilder sb = new StringBuilder();
		topSort(graph, sb);
		return sb.toString();
	}
	

	private void topSort(Collection<GNode> graph, StringBuilder sb) {
		for(GNode n : graph) {
			if(!n.visited) visit(n, sb, graph);
		}
	}


	private void visit(GNode node, StringBuilder sb, Collection<GNode> graph) {
		node.visited = true;
		for(GNode n : node.neighbors) {
			if(!n.visited) visit(n, sb, graph);
		}
		sb.append(node.value);
	}

	private class GNode {
		List<GNode> neighbors;
		Character value;
		boolean visited = false;
		public GNode(Character c) {
			this.value = c;
			neighbors = new ArrayList<>();
		}
	}
	
	private Collection<GNode> buildGraph(List<String> input) {
		Map<Character, GNode> map = new HashMap<>();
		for(int i=0; i<input.size(); i++) {
			//updateGraph(input.get(i), map);
			if(i!=0) updateGraph(input.get(i-1), input.get(i), map);
		}
		return map.values();
	}

	private void updateGraph(String string1, String string2, Map<Character, GNode> map) {
		int index1=0, index2=0;x
		while(index1<string1.length() && string1.charAt(index1) == string2.charAt(index2)) {
			index1++; index2++;
		}
		if(index1==string1.length()) return;
		GNode target = getOrCreate(map, string1.charAt(index1));
		GNode  n = getOrCreate(map, string2.charAt(index2));
		if(!target.neighbors.contains(n)) {
			target.neighbors.add(n);
			System.out.println("node " + target.value + " added " + n.value);
		}
	}

	private void updateGraph(String string, Map<Character, GNode> map) {
		for(int i=0; i<string.length()-1; i++) {
			if(string.charAt(i) != string.charAt(i+1)) {
				GNode target = getOrCreate(map, string.charAt(i));
				GNode  n = getOrCreate(map, string.charAt(i+1));
				if(!target.neighbors.contains(n)) {
					target.neighbors.add(n);
					System.out.println("node " + target.value + " added " + n.value);
				}
			}
		}
	}

	private GNode getOrCreate(Map<Character, GNode> map, Character c) {
		GNode temp;
		if(map.keySet().contains(c)) {
			temp = map.get(c);
		} else {
			temp = new GNode(c);
			map.put(c, temp);
		}
		return temp;
	}
}
