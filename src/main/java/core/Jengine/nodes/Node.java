package core.Jengine.nodes;

import java.util.*;

public abstract class Node  {

	private String name;
	private List<Node> childs = new ArrayList<Node>();
	private Node parentNode = null;


	public Node(String name) {
		if (name == null) {
			this.setName(toString());
		} else {
			this.setName(name);
		}
	}

	/**
	 * Return parent node if exists else return null
	 * @return Node
	 */
	public Node getParent() {
		return this.parentNode;
	}

	/**
	 * Return list with all childs
	 * @return List of Node
	 */
	public List<Node> getChilds() {
		return childs;
	}

	/**
	 * 
	 * @param index
	 * @return Node in on given index
	 * @throws IndexOutOfBoundsException if index is out range of childs array
	 */
	public Node getChild(Integer index) throws IndexOutOfBoundsException {
		return childs.get(index);
	}

	/**
	 * @return Return first child of node
	 */
	public Node getFirstChild() {
		return childs.get(0);
	}

	/**
	 * @return return last child of node
	 */
	public Node getLastChild() {
		return childs.get(childs.size() - 1);
	}

	/**
	 * @param child The node for add on list of childs
	 */
	public void addChild(Node child) {
		childs.add(child);
	}

	/**
	 * 
	 * @param child The node will be searched for the index
	 * @return Index of child
	 */
	public Integer indexOf(Node child) {
		return childs.indexOf(child);
	}

	/**
	 * @param name set node name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return name of node
	 */
	public String getName() {
		return this.name;
	}
}
