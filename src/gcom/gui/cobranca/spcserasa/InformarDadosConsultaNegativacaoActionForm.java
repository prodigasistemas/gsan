package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InformarDadosConsultaNegativacaoActionForm extends ActionForm {

	private static final long serialVersionUID = -8210603670067985068L;
	
	public static final String ACOMPANHAMENTO = "1";
	public static final String EXCLUSOES = "2";
	public static final String RESUMO = "3";
	
	private String idNegativador;
	private String[] arrayNegativador;

	private String nomeNegativador;

	private String periodoEnvioNegativacaoInicio;

	private String periodoEnvioNegativacaoFim;

	private String ultimaAtualizacao;

	private String indicadorRelExclusao;

	private String periodoExclusaoNegativacaoInicio;

	private String periodoExclusaoNegativacaoFim;

	private String idNegativadorExclusaoMotivo;

	private String tituloComando;

	private String idNegativacaoComando;

	private String cobrancaGrupo;
	private Collection collCobrancaGrupo;
	private String[] arrayCobrancaGrupo;

	private String gerenciaRegional;
	private Collection collGerenciaRegional;
	private String[] arrayGerenciaRegional;

	private String unidadeNegocio;
	private Collection collUnidadeNegocio;
	private String[] arrayUnidadeNegocio;

	private String imovelPerfil;
	private Collection collImovelPerfil;
	private String[] arrayImovelPerfil;

	private String categoria;
	private Collection collCategoria;
	private String[] arrayCategoria;

	private String idEloPolo;

	private String descricaoEloPolo;

	private String idLocalidade;

	private String descricaoLocalidade;

	private String idSetorComercial;

	private String descricaoSetorComercial;

	private String idQuadra;

	private String descricaoQuadra;

	private String idGrupoCobranca;

	private String idGerenciaRegional;

	private String idUnidadeNegocio;

	private String idImovelPerfil;

	private String idCategoria;

	private String idEsferaPoder;

	private String tipoCliente;
	private Collection collTipoCliente;
	private String[] arrayTipoCliente;

	private String esferaPoder;
	private Collection collEsferaPoder;
	private String[] arrayEsferaPoder;

	private String okEloPolo;

	private String okLocalidade;

	private String okSetorComercial;

	private String okQuadra;

	private String indicadorApenasNegativacoesRejeitadas;
	private String motivoRejeicao;
	private Collection collMotivoRejeicao;
	private String[] arrayMotivoRejeicao;
	private String indicadorRelAcompanhamentoClientesNegativados;

	private String ligacaoAguaSituacao;
	private Collection collLigacaoAguaSituacao;
	private String[] arrayLigacaoAguaSituacao;
	private String ligacaoEsgotoSituacao;
	private Collection collLigacaoEsgotoSituacao;
	private String[] arrayLigacaoEsgotoSituacao;

	private String tipoConsulta;
	
	private String tipoRelatorioNegativacao;
	
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		this.idNegativador = "";
		this.periodoEnvioNegativacaoInicio = "";
		this.periodoEnvioNegativacaoFim = "";
		this.ultimaAtualizacao = "";
		this.tituloComando = "";
		this.cobrancaGrupo = "";
		this.collCobrancaGrupo = new ArrayList();
		this.arrayCobrancaGrupo = new String[0];

		this.gerenciaRegional = "";
		this.collGerenciaRegional = new ArrayList();
		this.arrayGerenciaRegional = new String[0];

		this.unidadeNegocio = "";
		this.collUnidadeNegocio = new ArrayList();
		this.arrayUnidadeNegocio = new String[0];

		this.imovelPerfil = "";
		this.collImovelPerfil = new ArrayList();
		this.arrayImovelPerfil = new String[0];

		this.categoria = "";
		this.collCategoria = new ArrayList();
		this.arrayCategoria = new String[0];

		this.tipoCliente = "";
		this.collTipoCliente = new ArrayList();
		this.arrayTipoCliente = new String[0];

		this.esferaPoder = "";
		this.collEsferaPoder = new ArrayList();
		this.arrayEsferaPoder = new String[0];

		this.motivoRejeicao = "";
		this.collMotivoRejeicao = new ArrayList();
		this.arrayMotivoRejeicao = new String[0];

		this.okEloPolo = "";
		this.okLocalidade = "";
		this.okSetorComercial = "";
		this.okQuadra = "";

		this.indicadorRelExclusao = "";
		this.periodoExclusaoNegativacaoInicio = "";
		this.periodoExclusaoNegativacaoFim = "";
		this.idNegativadorExclusaoMotivo = "";

		this.idEloPolo = "";
		this.descricaoEloPolo = "";

		this.idLocalidade = "";
		this.descricaoLocalidade = "";

		this.idSetorComercial = "";
		this.descricaoSetorComercial = "";

		this.idQuadra = "";
		this.descricaoQuadra = "";
	}

	public String getIdNegativador() {
		return idNegativador;
	}

	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}

	public String[] getArrayNegativador() {
		return arrayNegativador;
	}

	public void setArrayNegativador(String[] arrayNegativador) {
		this.arrayNegativador = arrayNegativador;
	}

	public String getNomeNegativador() {
		return nomeNegativador;
	}

	public void setNomeNegativador(String nomeNegativador) {
		this.nomeNegativador = nomeNegativador;
	}

	public String getPeriodoEnvioNegativacaoInicio() {
		return periodoEnvioNegativacaoInicio;
	}

	public void setPeriodoEnvioNegativacaoInicio(String periodoEnvioNegativacaoInicio) {
		this.periodoEnvioNegativacaoInicio = periodoEnvioNegativacaoInicio;
	}

	public String getPeriodoEnvioNegativacaoFim() {
		return periodoEnvioNegativacaoFim;
	}

	public void setPeriodoEnvioNegativacaoFim(String periodoEnvioNegativacaoFim) {
		this.periodoEnvioNegativacaoFim = periodoEnvioNegativacaoFim;
	}

	public String getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(String ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public String getIndicadorRelExclusao() {
		return indicadorRelExclusao;
	}

	public void setIndicadorRelExclusao(String indicadorRelExclusao) {
		this.indicadorRelExclusao = indicadorRelExclusao;
	}

	public String getPeriodoExclusaoNegativacaoInicio() {
		return periodoExclusaoNegativacaoInicio;
	}

	public void setPeriodoExclusaoNegativacaoInicio(String periodoExclusaoNegativacaoInicio) {
		this.periodoExclusaoNegativacaoInicio = periodoExclusaoNegativacaoInicio;
	}

	public String getPeriodoExclusaoNegativacaoFim() {
		return periodoExclusaoNegativacaoFim;
	}

	public void setPeriodoExclusaoNegativacaoFim(String periodoExclusaoNegativacaoFim) {
		this.periodoExclusaoNegativacaoFim = periodoExclusaoNegativacaoFim;
	}

	public String getIdNegativadorExclusaoMotivo() {
		return idNegativadorExclusaoMotivo;
	}

	public void setIdNegativadorExclusaoMotivo(String idNegativadorExclusaoMotivo) {
		this.idNegativadorExclusaoMotivo = idNegativadorExclusaoMotivo;
	}

	public String getTituloComando() {
		return tituloComando;
	}

	public void setTituloComando(String tituloComando) {
		this.tituloComando = tituloComando;
	}

	public String getIdNegativacaoComando() {
		return idNegativacaoComando;
	}

	public void setIdNegativacaoComando(String idNegativacaoComando) {
		this.idNegativacaoComando = idNegativacaoComando;
	}

	public String getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	public void setCobrancaGrupo(String cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}

	public Collection getCollCobrancaGrupo() {
		return collCobrancaGrupo;
	}

	public void setCollCobrancaGrupo(Collection collCobrancaGrupo) {
		this.collCobrancaGrupo = collCobrancaGrupo;
	}

	public String[] getArrayCobrancaGrupo() {
		return arrayCobrancaGrupo;
	}

	public void setArrayCobrancaGrupo(String[] arrayCobrancaGrupo) {
		this.arrayCobrancaGrupo = arrayCobrancaGrupo;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public Collection getCollGerenciaRegional() {
		return collGerenciaRegional;
	}

	public void setCollGerenciaRegional(Collection collGerenciaRegional) {
		this.collGerenciaRegional = collGerenciaRegional;
	}

	public String[] getArrayGerenciaRegional() {
		return arrayGerenciaRegional;
	}

	public void setArrayGerenciaRegional(String[] arrayGerenciaRegional) {
		this.arrayGerenciaRegional = arrayGerenciaRegional;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Collection getCollUnidadeNegocio() {
		return collUnidadeNegocio;
	}

	public void setCollUnidadeNegocio(Collection collUnidadeNegocio) {
		this.collUnidadeNegocio = collUnidadeNegocio;
	}

	public String[] getArrayUnidadeNegocio() {
		return arrayUnidadeNegocio;
	}

	public void setArrayUnidadeNegocio(String[] arrayUnidadeNegocio) {
		this.arrayUnidadeNegocio = arrayUnidadeNegocio;
	}

	public String getImovelPerfil() {
		return imovelPerfil;
	}

	public void setImovelPerfil(String imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	public Collection getCollImovelPerfil() {
		return collImovelPerfil;
	}

	public void setCollImovelPerfil(Collection collImovelPerfil) {
		this.collImovelPerfil = collImovelPerfil;
	}

	public String[] getArrayImovelPerfil() {
		return arrayImovelPerfil;
	}

	public void setArrayImovelPerfil(String[] arrayImovelPerfil) {
		this.arrayImovelPerfil = arrayImovelPerfil;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Collection getCollCategoria() {
		return collCategoria;
	}

	public void setCollCategoria(Collection collCategoria) {
		this.collCategoria = collCategoria;
	}

	public String[] getArrayCategoria() {
		return arrayCategoria;
	}

	public void setArrayCategoria(String[] arrayCategoria) {
		this.arrayCategoria = arrayCategoria;
	}

	public String getIdEloPolo() {
		return idEloPolo;
	}

	public void setIdEloPolo(String idEloPolo) {
		this.idEloPolo = idEloPolo;
	}

	public String getDescricaoEloPolo() {
		return descricaoEloPolo;
	}

	public void setDescricaoEloPolo(String descricaoEloPolo) {
		this.descricaoEloPolo = descricaoEloPolo;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public String getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}

	public String getDescricaoQuadra() {
		return descricaoQuadra;
	}

	public void setDescricaoQuadra(String descricaoQuadra) {
		this.descricaoQuadra = descricaoQuadra;
	}

	public String getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	public void setIdGrupoCobranca(String idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIdImovelPerfil() {
		return idImovelPerfil;
	}

	public void setIdImovelPerfil(String idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getIdEsferaPoder() {
		return idEsferaPoder;
	}

	public void setIdEsferaPoder(String idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Collection getCollTipoCliente() {
		return collTipoCliente;
	}

	public void setCollTipoCliente(Collection collTipoCliente) {
		this.collTipoCliente = collTipoCliente;
	}

	public String[] getArrayTipoCliente() {
		return arrayTipoCliente;
	}

	public void setArrayTipoCliente(String[] arrayTipoCliente) {
		this.arrayTipoCliente = arrayTipoCliente;
	}

	public String getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(String esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public Collection getCollEsferaPoder() {
		return collEsferaPoder;
	}

	public void setCollEsferaPoder(Collection collEsferaPoder) {
		this.collEsferaPoder = collEsferaPoder;
	}

	public String[] getArrayEsferaPoder() {
		return arrayEsferaPoder;
	}

	public void setArrayEsferaPoder(String[] arrayEsferaPoder) {
		this.arrayEsferaPoder = arrayEsferaPoder;
	}

	public String getOkEloPolo() {
		return okEloPolo;
	}

	public void setOkEloPolo(String okEloPolo) {
		this.okEloPolo = okEloPolo;
	}

	public String getOkLocalidade() {
		return okLocalidade;
	}

	public void setOkLocalidade(String okLocalidade) {
		this.okLocalidade = okLocalidade;
	}

	public String getOkSetorComercial() {
		return okSetorComercial;
	}

	public void setOkSetorComercial(String okSetorComercial) {
		this.okSetorComercial = okSetorComercial;
	}

	public String getOkQuadra() {
		return okQuadra;
	}

	public void setOkQuadra(String okQuadra) {
		this.okQuadra = okQuadra;
	}

	public String getIndicadorApenasNegativacoesRejeitadas() {
		return indicadorApenasNegativacoesRejeitadas;
	}

	public void setIndicadorApenasNegativacoesRejeitadas(String indicadorApenasNegativacoesRejeitadas) {
		this.indicadorApenasNegativacoesRejeitadas = indicadorApenasNegativacoesRejeitadas;
	}

	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}

	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}

	public Collection getCollMotivoRejeicao() {
		return collMotivoRejeicao;
	}

	public void setCollMotivoRejeicao(Collection collMotivoRejeicao) {
		this.collMotivoRejeicao = collMotivoRejeicao;
	}

	public String[] getArrayMotivoRejeicao() {
		return arrayMotivoRejeicao;
	}

	public void setArrayMotivoRejeicao(String[] arrayMotivoRejeicao) {
		this.arrayMotivoRejeicao = arrayMotivoRejeicao;
	}

	public String getIndicadorRelAcompanhamentoClientesNegativados() {
		return indicadorRelAcompanhamentoClientesNegativados;
	}

	public void setIndicadorRelAcompanhamentoClientesNegativados(String indicadorRelAcompanhamentoClientesNegativados) {
		this.indicadorRelAcompanhamentoClientesNegativados = indicadorRelAcompanhamentoClientesNegativados;
	}

	public String getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(String ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public Collection getCollLigacaoAguaSituacao() {
		return collLigacaoAguaSituacao;
	}

	public void setCollLigacaoAguaSituacao(Collection collLigacaoAguaSituacao) {
		this.collLigacaoAguaSituacao = collLigacaoAguaSituacao;
	}

	public String[] getArrayLigacaoAguaSituacao() {
		return arrayLigacaoAguaSituacao;
	}

	public void setArrayLigacaoAguaSituacao(String[] arrayLigacaoAguaSituacao) {
		this.arrayLigacaoAguaSituacao = arrayLigacaoAguaSituacao;
	}

	public String getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(String ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public Collection getCollLigacaoEsgotoSituacao() {
		return collLigacaoEsgotoSituacao;
	}

	public void setCollLigacaoEsgotoSituacao(Collection collLigacaoEsgotoSituacao) {
		this.collLigacaoEsgotoSituacao = collLigacaoEsgotoSituacao;
	}

	public String[] getArrayLigacaoEsgotoSituacao() {
		return arrayLigacaoEsgotoSituacao;
	}

	public void setArrayLigacaoEsgotoSituacao(String[] arrayLigacaoEsgotoSituacao) {
		this.arrayLigacaoEsgotoSituacao = arrayLigacaoEsgotoSituacao;
	}

	public String getTipoRelatorioNegativacao() {
		return tipoRelatorioNegativacao;
	}
	
	public void setTipoRelatorioNegativacao(String tipoRelatorioNegativacao) {
		this.tipoRelatorioNegativacao = tipoRelatorioNegativacao;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
}
