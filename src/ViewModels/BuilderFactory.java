package ViewModels;

public class BuilderFactory {
	
	public final static int CRIA_BUILDER = 0;
	public final static int CORRAL_BUILDER = 1;
	
	public static IBuilder getBuilder(int type) {
		switch(type) {
		case CRIA_BUILDER: return new CriaBuilder();

		case CORRAL_BUILDER: return new CorralBuilder();
			
		default: return null;
		}
	}

}
