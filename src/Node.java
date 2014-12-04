import java.util.ArrayList;
import java.util.List;


public class Node {
	List<Node> children;
	String value;
	
	public Node(String value) {
		this.value = value;
		this.children = new ArrayList<>();
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}
	
	public void addChild(Node c) {
		this.children.add(c);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
