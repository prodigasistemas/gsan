package gcom.atendimentopublico.registroatendimento.bean;


/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [UC0409] Obter Indicador de Existência de Hidrômetro na Ligaçaõ de Água e no Poço
 * 
 * @author Ana Maria
 * @date 14/08/2006
 */
public class ObterIndicadorExistenciaHidrometroHelper {
	
	private Short indicadorLigacaoAgua;
	
	private Short indicadorPoco;
	
	public ObterIndicadorExistenciaHidrometroHelper(){}

	public Short getIndicadorLigacaoAgua() {
		return indicadorLigacaoAgua;
	}

	public void setIndicadorLigacaoAgua(Short indicadorLigacaoAgua) {
		this.indicadorLigacaoAgua = indicadorLigacaoAgua;
	}

	public Short getIndicadorPoco() {
		return indicadorPoco;
	}

	public void setIndicadorPoco(Short indicadorPoco) {
		this.indicadorPoco = indicadorPoco;
	}


}
