package gcom.atendimentopublico.bean;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;

import java.io.Serializable;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class DadosLigacoesBoletimCadastroHelper implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LigacaoAgua ligacaoAgua;
    
    private LigacaoEsgoto ligacaoEsgoto;
    
	public DadosLigacoesBoletimCadastroHelper(){}

	/**
	 * @return Retorna o campo ligacaoAgua.
	 */
	public LigacaoAgua getLigacaoAgua() {
		return ligacaoAgua;
	}

	/**
	 * @param ligacaoAgua O ligacaoAgua a ser setado.
	 */
	public void setLigacaoAgua(LigacaoAgua ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}

	/**
	 * @return Retorna o campo ligacaoEsgoto.
	 */
	public LigacaoEsgoto getLigacaoEsgoto() {
		return ligacaoEsgoto;
	}

	/**
	 * @param ligacaoEsgoto O ligacaoEsgoto a ser setado.
	 */
	public void setLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}

	

}
