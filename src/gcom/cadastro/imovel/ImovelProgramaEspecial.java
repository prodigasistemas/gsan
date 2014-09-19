package gcom.cadastro.imovel;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;

import java.util.Date;

public class ImovelProgramaEspecial extends ObjetoTransacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Short FORMA_SUSPENSAO_OPERADOR = new Short("1");
	
	public static final Short FORMA_SUSPENSAO_BATCH = new Short("2");
	
	private Integer id;
	private Imovel imovel;
	private String descricaoDocumentos;
	private Integer mesAnoInicioPrograma;
	private Integer mesAnoSaidaPrograma;
	private Short formaSuspensao;
	private Date dataApresentacaoDocumentos;
	private ImovelPerfil imovelPerfil;
	private Usuario usuarioResponsavel;
	private Usuario usuarioSuspensao;
	private Date dataSuspensao;
	private Date dataInclusao;
	private Date ultimaAlteracao;
	private String numeroBolsaFamilia; 
	
	
	public ImovelProgramaEspecial() {

	}

	public String getMesAnoInicio(){
		String anoMesStr = this.mesAnoInicioPrograma + "";
		String mesAno = "";
		
		mesAno = anoMesStr.substring(4,6) + "/" + anoMesStr.substring(0,4)  ;
		
		return mesAno;
	}
	public String getMesAnoSaida(){
		String anoMesStr = this.mesAnoSaidaPrograma + "";
		String mesAno = "";
		
		if(this.mesAnoSaidaPrograma!=null && !this.mesAnoSaidaPrograma.equals("")){
		mesAno = anoMesStr.substring(4,6) + "/" + anoMesStr.substring(0,4)  ;
		}
		return mesAno;
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Imovel getImovel() {
		return imovel;
	}


	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}


	public String getDescricaoDocumentos() {
		return descricaoDocumentos;
	}


	public void setDescricaoDocumentos(String descricaoDocumentos) {
		this.descricaoDocumentos = descricaoDocumentos;
	}


	public Integer getMesAnoInicioPrograma() {
		return mesAnoInicioPrograma;
	}


	public void setMesAnoInicioPrograma(Integer mesAnoInicioPrograma) {
		this.mesAnoInicioPrograma = mesAnoInicioPrograma;
	}

	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}


	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}


	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}


	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}


	@Override
	public Filtro retornaFiltro() {
		
		return null;
	}


	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}


	@Override
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}


	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
		
	}


	public Date getDataApresentacaoDocumentos() {
		return dataApresentacaoDocumentos;
	}


	public void setDataApresentacaoDocumentos(Date dataApresentacaoDocumentos) {
		this.dataApresentacaoDocumentos = dataApresentacaoDocumentos;
	}


	public Date getDataSuspensao() {
		return dataSuspensao;
	}


	public void setDataSuspensao(Date dataSuspensao) {
		this.dataSuspensao = dataSuspensao;
	}


	public Date getDataInclusao() {
		return dataInclusao;
	}


	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}


	public Integer getMesAnoSaidaPrograma() {
		return mesAnoSaidaPrograma;
	}


	public void setMesAnoSaidaPrograma(Integer mesAnoSaidaPrograma) {
		this.mesAnoSaidaPrograma = mesAnoSaidaPrograma;
	}


	public Usuario getUsuarioSuspensao() {
		return usuarioSuspensao;
	}


	public void setUsuarioSuspensao(Usuario usuarioSuspensao) {
		this.usuarioSuspensao = usuarioSuspensao;
	}


	public Short getFormaSuspensao() {
		return formaSuspensao;
	}


	public void setFormaSuspensao(Short formaSuspensao) {
		this.formaSuspensao = formaSuspensao;
	}

	public String getNumeroBolsaFamilia() {
		return numeroBolsaFamilia;
	}

	public void setNumeroBolsaFamilia(String numeroBolsaFamilia) {
		this.numeroBolsaFamilia = numeroBolsaFamilia;
	}


}
