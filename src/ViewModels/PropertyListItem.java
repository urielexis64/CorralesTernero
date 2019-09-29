package ViewModels;

public class PropertyListItem {

	private int index;
	private String name;
	private String regEx;
	
	public PropertyListItem(int index, String name, String regEx) {
		this.index = index;
		this.name = name;
		this.regEx = regEx;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegEx() {
		return regEx;
	}

	public void setRegEx(String regEx) {
		this.regEx = regEx;
	}
	
	
	
}
