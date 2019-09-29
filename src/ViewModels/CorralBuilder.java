package ViewModels;

import java.util.LinkedList;
import java.util.List;

public class CorralBuilder implements IBuilder{

	private List<PropertyListItem> properties;
	
	public CorralBuilder() {
		properties = new LinkedList<PropertyListItem>();
		properties.add(new PropertyListItem(0,"ID Corral:",""));
		properties.add(new PropertyListItem(1,"Alimentacion",""));
	}
	
	public List<PropertyListItem> getProperties(){
		return properties;
	}
}
