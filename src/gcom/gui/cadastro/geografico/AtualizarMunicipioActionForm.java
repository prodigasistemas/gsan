package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC005] Manter Municipio [SB0001]Atualizar Municipio
 * 
 * @author Kássia Albuquerque
 * @date 08/01/2007
 */

public class AtualizarMunicipioActionForm extends ValidatorForm {
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

	private String indicadorUso;

	private String codigoIbge;
	
	private String indicadorRelacaoQuadraBairro;

	public String getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
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
	
	// Esse método carrega todos os valores da base de dados
	// necesários para exibição da tela AtualizarMunicipio.

	public Municipio setFormValues(Municipio municipio) {

		municipio.setId(new Integer(getCodigoMunicipio()));
		municipio.setNome(getNomeMunicipio());
		municipio.setDdd(new Short(getCodigoDdd()));

		UnidadeFederacao unidadeFederacao = new UnidadeFederacao();
		unidadeFederacao.setId(new Integer(getUnidadeFederacao()));
		municipio.setUnidadeFederacao(unidadeFederacao);

		Microrregiao microrregiao = new Microrregiao();
		microrregiao.setId(new Integer(getMicroregiao()));
		municipio.setMicrorregiao(microrregiao);

		RegiaoDesenvolvimento regiaoDesenvolvimento = new RegiaoDesenvolvimento();
		regiaoDesenvolvimento.setId(new Integer(getRegiaoDesenv()));
		municipio.setRegiaoDesenvolvimento(regiaoDesenvolvimento);

		municipio.setCepInicio(new Integer(getCepInicial()));
		municipio.setCepFim(new Integer(getCepFinal()));
		municipio.setDataConcessaoInicio(Util
				.converteStringParaDate(getDataInicioConcessao()));
		municipio.setDataConcessaoFim(Util
				.converteStringParaDate(getDataFimConcessao()));
		municipio.setIndicadorUso(new Short(getIndicadorUso()));
		municipio.setCodigoIbge(getCodigoIbge());
		// municipio.setUltimaAlteracao(new Date());
		
		if(getIndicadorRelacaoQuadraBairro() != null && getIndicadorRelacaoQuadraBairro().equals("1")){
			
			municipio.setIndicadorRelacaoQuadraBairro(ConstantesSistema.INDICADOR_USO_ATIVO);
		}else{
			municipio.setIndicadorRelacaoQuadraBairro(ConstantesSistema.INDICADOR_USO_DESATIVO);
		}

		return municipio;
	}

}
