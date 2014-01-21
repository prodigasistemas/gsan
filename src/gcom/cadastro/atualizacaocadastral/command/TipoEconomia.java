package gcom.cadastro.atualizacaocadastral.command;

public enum TipoEconomia {
	RESIDENCIAL('R'), COMERCIAL('C'), PUBLICA('P'), INDUSTRIAL('I');

	private final char codigo;
	
	private TipoEconomia(char codigo){
		this.codigo = codigo;
	}
	
	public static TipoEconomia getByCodigo(char codigo){
		for (TipoEconomia tipo: TipoEconomia.values()){
			if (tipo.codigo == codigo){
				return tipo;
			}
		}
		return null;
	}
}
