import java.util.ArrayList;

public class ConfigurationNode {
	
	private ConfigurationNode parent;
	private ArrayList<ConfigurationNode> children;
	private String moveTaken;
	
	public ConfigurationNode(String moveTaken) {
		this.moveTaken = moveTaken;
		
		children = new ArrayList<ConfigurationNode>();
	}
	
	public void addChild() {
		children.add();
	}

	public ConfigurationNode getParent() {
		return parent;
	}

	public void setParent(ConfigurationNode parent) {
		this.parent = parent;
	}

	public ArrayList<ConfigurationNode> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<ConfigurationNode> children) {
		this.children = children;
	}

	public String getMoveTaken() {
		return moveTaken;
	}

	public void setMoveTaken(String moveTaken) {
		this.moveTaken = moveTaken;
	}
	

}
