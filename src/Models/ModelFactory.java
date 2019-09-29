package Models;

import ViewModels.CorralBuilder;
import ViewModels.CriaBuilder;
import ViewModels.IBuilder;

public class ModelFactory {

	public final static int CRIA_MODEL = 0;
	public final static int CORRAL_MODEL = 1;
	
	public static ISQLModel getModel(int type) {
		switch(type) {
		case CRIA_MODEL: return new CriaModel();

		case CORRAL_MODEL: return null;
			
		default: return null;
		}
	}
}
