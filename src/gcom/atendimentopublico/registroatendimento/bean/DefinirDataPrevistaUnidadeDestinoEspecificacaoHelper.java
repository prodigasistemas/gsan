package gcom.atendimentopublico.registroatendimento.bean;

import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.util.Date;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [SB0003] - Define Data Prevista e Unidade Destino da Especificação do caso de uso
 * [UC0366] Inserir Registro de Atendimento
 * 
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper {
	
	private Date dataPrevista;
	
	private UnidadeOrganizacional unidadeOrganizacional;
	
	private String imovelObrigatorio;
	
	private String pavimentoRuaObrigatorio;
	
	private String pavimentoCalcadaObrigatorio;
	
	private String indFaltaAgua;
	
	private String indMatricula;
	
	
	public DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper(){}

	
	
	public String getPavimentoCalcadaObrigatorio() {
		return pavimentoCalcadaObrigatorio;
	}

	public void setPavimentoCalcadaObrigatorio(String pavimentoCalcadaObrigatorio) {
		this.pavimentoCalcadaObrigatorio = pavimentoCalcadaObrigatorio;
	}

	public String getPavimentoRuaObrigatorio() {
		return pavimentoRuaObrigatorio;
	}

	public void setPavimentoRuaObrigatorio(String pavimentoRuaObrigatorio) {
		this.pavimentoRuaObrigatorio = pavimentoRuaObrigatorio;
	}

	public String getImovelObrigatorio() {
		return imovelObrigatorio;
	}

	public void setImovelObrigatorio(String imovelObrigatorio) {
		this.imovelObrigatorio = imovelObrigatorio;
	}



	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}



	public String getIndFaltaAgua() {
		return indFaltaAgua;
	}



	public void setIndFaltaAgua(String indFaltaAgua) {
		this.indFaltaAgua = indFaltaAgua;
	}



	public String getIndMatricula() {
		return indMatricula;
	}



	public void setIndMatricula(String indMatricula) {
		this.indMatricula = indMatricula;
	}
	
	

}
