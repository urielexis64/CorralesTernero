package ViewModels;

import java.util.*;

public class CriaBuilder implements IBuilder {
	
	private List<PropertyListItem> properties;
	
	public CriaBuilder() {
		properties = new LinkedList<PropertyListItem>();
		properties.add(new PropertyListItem(0,"ID Cria:",""));
		properties.add(new PropertyListItem(1,"Peso",""));
		properties.add(new PropertyListItem(2,"	Grasa",""));
		properties.add(new PropertyListItem(3,"Color de Musculo",""));
		properties.add(new PropertyListItem(4,"ID Corral",""));
	}
	
	public List<PropertyListItem> getProperties(){
		return properties;
	}

}
