package gcom.gui.cadastro.geografico;



import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0001]	INSERIR MUNICIPIO
 * 
 * @author Kássia Albuquerque
 * @date 13/12/2006
 */

public class InserirMunicipioActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String codigoMunicipio;
	
	private String nomeMunicipio;

	private String codigoDdd;
	
	private String unidadeFederacao;
	
	private String microregiao;
	
	private String regiaoDesenv;

	private String cepInicial;
	
	private String cepFinal;
	
	private String dataInicioConcessao;
	
	private String dataFimConcessao;
	
	private String codigoIbge;
	
	private String indicadorRelacaoQuadraBairro;
	

	public String getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public String getCepFinal() {
		return cepFinal;
	}

	public void setCepFinal(String cepFinal) {
		this.cepFinal = cepFinal;
	}

	public String getCepInicial() {
		return cepInicial;
	}

	public void setCepInicial(String cepInicial) {
		this.cepInicial = cepInicial;
	}

	public String getCodigoDdd() {
		return codigoDdd;
	}

	public void setCodigoDdd(String codigoDdd) {
		this.codigoDdd = codigoDdd;
	}

	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getDataFimConcessao() {
		return dataFimConcessao;
	}

	public void setDataFimConcessao(String dataFimConcessao) {
		this.dataFimConcessao = dataFimConcessao;
	}

	public String getDataInicioConcessao() {
		return dataInicioConcessao;
	}

	public void setDataInicioConcessao(String dataInicioConcessao) {
		this.dataInicioConcessao = dataInicioConcessao;
	}

	public String getMicroregiao() {
		return microregiao;
	}

	public void setMicroregiao(String microregiao) {
		this.microregiao = microregiao;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getRegiaoDesenv() {
		return regiaoDesenv;
	}

	public void setRegiaoDesenv(String regiaoDesenv) {
		this.regiaoDesenv = regiaoDesenv;
	}

	public String getUnidadeFederacao() {
		return unidadeFederacao;
	}

	public void setUnidadeFederacao(String unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public String getIndicadorRelacaoQuadraBairro() {
		return indicadorRelacaoQuadraBairro;
	}

	public void setIndicadorRelacaoQuadraBairro(String indicadorRelacaoQuadraBairro) {
		this.indicadorRelacaoQuadraBairro = indicadorRelacaoQuadraBairro;
	}

	
	public Municipio setFormValues(Municipio municipio) {
		
		
		// Metodo usado para setar todos os valores do Form nn base de dados

		municipio.setId(new Integer(getCodigoMunicipio()));
		municipio.setNome(getNomeMunicipio());		
		municipio.setDdd(new Short(getCodigoDdd()));
		
		if(getUnidadeFederacao() != null && !getUnidadeFederacao().equals("")){
			
			  UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
			  unidadeFederacao.setId(Integer.parseInt(getUnidadeFederacao()));
			  municipio.setUnidadeFederacao(unidadeFederacao);
			}
		
		if(getMicroregiao() != null && !getMicroregiao().equals("")){
			
			  Microrregiao microrregiao = new Microrregiao();
			  microrregiao.setId(Integer.parseInt(getMicroregiao()));
			  municipio.setMicrorregiao(microrregiao);
			}
		
		if(getRegiaoDesenv() != null && !getRegiaoDesenv().equals("")){
			
			RegiaoDesenvolvimento regiaoDesenv = new RegiaoDesenvolvimento();
			regiaoDesenv.setId(Integer.parseInt(getRegiaoDesenv()));
			municipio.setRegiaoDesenvolvimento(regiaoDesenv);
			}
		
		if(getCodigoIbge() != null && !getCodigoIbge().equals("")){
			
			municipio.setCodigoIbge(getCodigoIbge());
			}
		
		municipio.setCepInicio(new Integer (getCepInicial()));
		municipio.setCepFim(new Integer (getCepFinal()));
		municipio.setDataConcessaoInicio(Util.converteStringParaDate(getDataInicioConcessao()));
		municipio.setDataConcessaoFim(Util.converteStringParaDate(getDataFimConcessao()));
		municipio.setUltimaAlteracao(new Date());
		municipio.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		if(getIndicadorRelacaoQuadraBairro() != null && getIndicadorRelacaoQuadraBairro().equals("1")){
			
			municipio.setIndicadorRelacaoQuadraBairro(ConstantesSistema.INDICADOR_USO_ATIVO);
		}else{
			municipio.setIndicadorRelacaoQuadraBairro(ConstantesSistema.INDICADOR_USO_DESATIVO);
		}
		
		return municipio;
	}

}
