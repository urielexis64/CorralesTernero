package Models;

import java.sql.Statement;

import Utils.SQLConnectionHelper;
import ViewModels.Cria;

public class CriaModel implements ISQLModel{

	private Statement connHelper;
	
	public CriaModel() {
		connHelper = SQLConnectionHelper.getConexion();
	}
	
	public boolean insertEntity(String[] cria) {
		String statement = "insert into Crias values (CriaID ="+cria[0]+",FechaEntrada ="+cria[1]+",Grasa ="+cria[2]+",Peso ="+cria[3]+",ColorMusculo ="+cria[4]+",ClasificacionID ="+cria[5]+",CorralID = "+cria[6]+")";
		return false;
	}
	
}
