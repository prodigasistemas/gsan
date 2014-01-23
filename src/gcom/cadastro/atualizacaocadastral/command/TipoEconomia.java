package gcom.cadastro.atualizacaocadastral.command;

public enum TipoEconomia {
	RESIDENCIAL('R', "RESIDENCIAL"), COMERCIAL('C', "COMERCIAL"), PUBLICO('P', "PUBLICO"), INDUSTRIAL('I', "INDUSTRIAL");

	private final char codigo;
	private final String descricao;
	
	private TipoEconomia(char codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static TipoEconomia getByCodigo(char codigo){
		for (TipoEconomia tipo: TipoEconomia.values()){
			if (tipo.codigo == codigo){
				return tipo;
			}
		}
		return null;
	}
	
	public char getCodigo(){
		return codigo;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
