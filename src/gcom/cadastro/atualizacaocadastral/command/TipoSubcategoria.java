package gcom.cadastro.atualizacaocadastral.command;

public enum TipoSubcategoria {

	RESIDENCIAL1("R1", 1, 1),	RESIDENCIAL2("R2", 1, 2),	RESIDENCIAL3("R3", 1, 3),	RESIDENCIAL4("R4", 1, 4),
	COMERCIAL1("C1", 2, 5), COMERCIAL2("C2", 2, 6),	COMERCIAL3("C3", 2, 7), COMERCIAL4("C4", 2, 8),	
	INDUSTRIAL1("I1", 3, 9), INDUSTRIAL2("I2", 3, 10), INDUSTRIAL3("I3", 3, 11), INDUSTRIAL4("I4", 3, 12),
	PUBLICO1("P1", 4, 13),	PUBLICO2("P2", 4, 14),	PUBLICO3("P3", 4, 15),	PUBLICO4("P4", 4, 16);
	
	private final String codigo;
	private final int idCategoria;
	private final int idSubcategoria;
	
	private TipoSubcategoria(String codigo, int idCategoria, int idSubcategoria){
		this.codigo = codigo;
		this.idCategoria = idCategoria;
		this.idSubcategoria = idSubcategoria;
	}
	
	public static TipoSubcategoria getByCodigo(String codigo){
		for (TipoSubcategoria tipo: TipoSubcategoria.values()){
			if (tipo.codigo.equalsIgnoreCase(codigo)){
				return tipo;
			}
		}
		return null;
	}
	
	public int getIdSubcategoria(){
		return idSubcategoria;
	}
	
	public int getIdCategoria(){
		return idCategoria;
	}
}
