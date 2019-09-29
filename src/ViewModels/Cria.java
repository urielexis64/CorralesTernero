package ViewModels;

import java.util.Date;

public class Cria {

	private int idCria;
	private Date entryDate;
	private String musculeColor;
	private float weigth;
	private float fat;
	
	//fks
	private int idClasificacion;
	private int idCorral;
	
	public Cria(int idCria, Date entryDate, String musculeColor, float weigth, float fat) {
		this.idCria = idCria;
		this.entryDate = entryDate;
		this.musculeColor = musculeColor;
		this.weigth = weigth;
		this.fat = fat;
	}
	
	public int GetClasificationPerAtributes() {
		if(this.idClasificacion != 0) {
			return idClasificacion;
		}
		
		return idClasificacion;
	}

	public int getIdCria() {
		return idCria;
	}

	public void setIdCria(int idCria) {
		this.idCria = idCria;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getMusculeColor() {
		return musculeColor;
	}

	public void setMusculeColor(String musculeColor) {
		this.musculeColor = musculeColor;
	}

	public float getWeigth() {
		return weigth;
	}

	public void setWeigth(float weigth) {
		this.weigth = weigth;
	}

	public float getFat() {
		return fat;
	}

	public void setFat(float fat) {
		this.fat = fat;
	}

	public int getIdCorral() {
		return idCorral;
	}

	public void setIdCorral(int idCorral) {
		this.idCorral = idCorral;
	}
	
	
	
}
