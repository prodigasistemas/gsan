package gcom.gui.relatorio.faturamento;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class GerarRelatorioResumoFaturamentoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String mesAno;

	private String opcaoTotalizacao;

	private String gerenciaRegionalId;

	private String gerenciaRegionalporLocalidadeId;

	private String codigoLocalidade;
	private String descricaoLocalidade;
	
	private String codigoMunicipio;
	private String descricaoMunicipio;
	
	private String unidadeNegocioId;
	
	private String unidadeNegocioGerenciaRegionalId;
	
	private String opcaoRelatorio;

	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}

	public void setCodigoLocalidade(String codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}

	public String getGerenciaRegionalId() {
		return gerenciaRegionalId;
	}

	public void setGerenciaRegionalId(String gerenciaRegionalId) {
		this.gerenciaRegionalId = gerenciaRegionalId;
	}

	public String getGerenciaRegionalporLocalidadeId() {
		return gerenciaRegionalporLocalidadeId;
	}

	public void setGerenciaRegionalporLocalidadeId(
			String gerenciaRegionalporLocalidadeId) {
		this.gerenciaRegionalporLocalidadeId = gerenciaRegionalporLocalidadeId;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		mesAno = null;
		opcaoTotalizacao = null;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public String getUnidadeNegocioGerenciaRegionalId() {
		return unidadeNegocioGerenciaRegionalId;
	}

	public void setUnidadeNegocioGerenciaRegionalId(
			String unidadeNegocioGerenciaRegionalId) {
		this.unidadeNegocioGerenciaRegionalId = unidadeNegocioGerenciaRegionalId;
	}

	public String getUnidadeNegocioId() {
		return unidadeNegocioId;
	}

	public void setUnidadeNegocioId(String unidadeNegocioId) {
		this.unidadeNegocioId = unidadeNegocioId;
	}

	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}

	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}

	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}
}
