package gcom.gui.faturamento;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.QualidadeAgua;
import gcom.operacional.FiltroSistemaAbastecimento;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.SistemaAbastecimento;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0596] Atualizar Qualidade de Agua
 * 
 * @author Rômulo Aurélio
 * @date 19/09/2008
 */

public class AtualizarQualidadeAguaActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String idQualidadeAgua;

	private String referencia;

	private String idLocalidade;

	private String localidadeDescricao;

	private String idSetorComercial;

	private String setorComercialDescricao;

	private String fonteCaptacao;

	private String indiceMensalTurbidez;

	private String padraoTurbidez;

	private String indiceMensalCloroResidual;

	private String padraoCloroResidual;

	private String indiceMensalPH;

	private String padraoPH;

	private String indiceMensalCor;

	private String padraoCor;

	private String indiceMensalFluor;

	private String padraoFluor;

	private String indiceMensalFerro;

	private String padraoFerro;

	private String indiceMensalColiformesTotais;

	private String padraoColiformesTotais;

	private String indiceMensalColiformesFecais;

	private String padraoColiformesFecais;

	private String indiceMensalNitrato;

	private String padraoNitrato;

	private String indiceMensalColiformesTermotolerantes;

	private String padraoColiformesTermotolerantes;

	private String quantidadeTurbidezExigidas;

	private String quantidadeTurbidezAnalisadas;

	private String quantidadeTurbidezConforme;

	private String quantidadeCorExigidas;

	private String quantidadeCorAnalisadas;

	private String quantidadeCorConforme;

	private String quantidadeCloroExigidas;

	private String quantidadeCloroAnalisadas;

	private String quantidadeCloroConforme;

	private String quantidadeFluorExigidas;

	private String quantidadeFluorAnalisadas;

	private String quantidadeFluorConforme;

	private String quantidadeColiformesTotaisExigidas;

	private String quantidadeColiformesTotaisAnalisadas;

	private String quantidadeColiformesTotaisConforme;

	private String quantidadeColiformesFecaisExigidas;

	private String quantidadeColiformesFecaisAnalisadas;

	private String quantidadeColiformesFecaisConforme;

	private String quantidadeColiformesTermotolerantesExigidas;

	private String quantidadeColiformesTermotolerantesAnalisadas;

	private String quantidadeColiformesTermotolerantesConforme;
	
	private String padraoAlcalinidade;
	
	private String indiceMensalAlcalinidade;
	
	private String quantidadeAlcalinidadeExigidas;
	
	private String quantidadeAlcalinidadeAnalisadas;
	
	private String quantidadeAlcalinidadeConforme;
	
	private String sistemaAbastecimento;

	public String getSistemaAbastecimento() {
		return sistemaAbastecimento;
	}

	public void setSistemaAbastecimento(String sistemaAbastecimento) {
		this.sistemaAbastecimento = sistemaAbastecimento;
	}

	public String getIndiceMensalAlcalinidade() {
		return indiceMensalAlcalinidade;
	}

	public void setIndiceMensalAlcalinidade(String indiceMensalAlcalinidade) {
		this.indiceMensalAlcalinidade = indiceMensalAlcalinidade;
	}

	public String getQuantidadeAlcalinidadeAnalisadas() {
		return quantidadeAlcalinidadeAnalisadas;
	}

	public void setQuantidadeAlcalinidadeAnalisadas(
			String quantidadeAlcalinidadeAnalisadas) {
		this.quantidadeAlcalinidadeAnalisadas = quantidadeAlcalinidadeAnalisadas;
	}

	public String getQuantidadeAlcalinidadeConforme() {
		return quantidadeAlcalinidadeConforme;
	}

	public void setQuantidadeAlcalinidadeConforme(
			String quantidadeAlcalinidadeConforme) {
		this.quantidadeAlcalinidadeConforme = quantidadeAlcalinidadeConforme;
	}

	public String getQuantidadeAlcalinidadeExigidas() {
		return quantidadeAlcalinidadeExigidas;
	}

	public void setQuantidadeAlcalinidadeExigidas(
			String quantidadeAlcalinidadeExigidas) {
		this.quantidadeAlcalinidadeExigidas = quantidadeAlcalinidadeExigidas;
	}

	public String getFonteCaptacao() {
		return fonteCaptacao;
	}

	public void setFonteCaptacao(String fonteCaptacao) {
		this.fonteCaptacao = fonteCaptacao;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getIndiceMensalCloroResidual() {
		return indiceMensalCloroResidual;
	}

	public void setIndiceMensalCloroResidual(String indiceMensalCloroResidual) {
		this.indiceMensalCloroResidual = indiceMensalCloroResidual;
	}

	public String getIndiceMensalColiformesFecais() {
		return indiceMensalColiformesFecais;
	}

	public void setIndiceMensalColiformesFecais(
			String indiceMensalColiformesFecais) {
		this.indiceMensalColiformesFecais = indiceMensalColiformesFecais;
	}

	public String getIndiceMensalColiformesTotais() {
		return indiceMensalColiformesTotais;
	}

	public void setIndiceMensalColiformesTotais(
			String indiceMensalColiformesTotais) {
		this.indiceMensalColiformesTotais = indiceMensalColiformesTotais;
	}

	public String getIndiceMensalCor() {
		return indiceMensalCor;
	}

	public void setIndiceMensalCor(String indiceMensalCor) {
		this.indiceMensalCor = indiceMensalCor;
	}

	public String getIndiceMensalFerro() {
		return indiceMensalFerro;
	}

	public void setIndiceMensalFerro(String indiceMensalFerro) {
		this.indiceMensalFerro = indiceMensalFerro;
	}

	public String getIndiceMensalFluor() {
		return indiceMensalFluor;
	}

	public void setIndiceMensalFluor(String indiceMensalFluor) {
		this.indiceMensalFluor = indiceMensalFluor;
	}

	public String getIndiceMensalNitrato() {
		return indiceMensalNitrato;
	}

	public void setIndiceMensalNitrato(String indiceMensalNitrato) {
		this.indiceMensalNitrato = indiceMensalNitrato;
	}

	public String getIndiceMensalPH() {
		return indiceMensalPH;
	}

	public void setIndiceMensalPH(String indiceMensalPH) {
		this.indiceMensalPH = indiceMensalPH;
	}

	public String getIndiceMensalTurbidez() {
		return indiceMensalTurbidez;
	}

	public void setIndiceMensalTurbidez(String indiceMensalTurbidez) {
		this.indiceMensalTurbidez = indiceMensalTurbidez;
	}

	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}

	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}

	public String getPadraoCloroResidual() {
		return padraoCloroResidual;
	}

	public void setPadraoCloroResidual(String padraoCloroResidual) {
		this.padraoCloroResidual = padraoCloroResidual;
	}

	public String getPadraoColiformesFecais() {
		return padraoColiformesFecais;
	}

	public void setPadraoColiformesFecais(String padraoColiformesFecais) {
		this.padraoColiformesFecais = padraoColiformesFecais;
	}

	public String getPadraoColiformesTotais() {
		return padraoColiformesTotais;
	}

	public void setPadraoColiformesTotais(String padraoColiformesTotais) {
		this.padraoColiformesTotais = padraoColiformesTotais;
	}

	public String getPadraoCor() {
		return padraoCor;
	}

	public void setPadraoCor(String padraoCor) {
		this.padraoCor = padraoCor;
	}

	public String getPadraoFerro() {
		return padraoFerro;
	}

	public void setPadraoFerro(String padraoFerro) {
		this.padraoFerro = padraoFerro;
	}

	public String getPadraoFluor() {
		return padraoFluor;
	}

	public void setPadraoFluor(String padraoFluor) {
		this.padraoFluor = padraoFluor;
	}

	public String getPadraoNitrato() {
		return padraoNitrato;
	}

	public void setPadraoNitrato(String padraoNitrato) {
		this.padraoNitrato = padraoNitrato;
	}

	public String getPadraoPH() {
		return padraoPH;
	}

	public void setPadraoPH(String padraoPH) {
		this.padraoPH = padraoPH;
	}

	public String getPadraoTurbidez() {
		return padraoTurbidez;
	}

	public void setPadraoTurbidez(String padraoTurbidez) {
		this.padraoTurbidez = padraoTurbidez;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}

	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}

	public String getIdQualidadeAgua() {
		return idQualidadeAgua;
	}

	public void setIdQualidadeAgua(String idQualidadeAgua) {
		this.idQualidadeAgua = idQualidadeAgua;
	}

	// SETA TODOS OS CAMPOS DO OBJETO QUALIDADE AGUA

	public QualidadeAgua setDadosQualidadeAgua(QualidadeAgua qualidadeAgua) {

		Fachada fachada = Fachada.getInstancia();

		// Referencia
		qualidadeAgua.setAnoMesReferencia(new Integer(Util
				.formatarMesAnoParaAnoMesSemBarra(this.getReferencia())));

		// Localidade
		if (getIdLocalidade() != null && !getIdLocalidade().equals("")) {

			Localidade localidade = new Localidade();
			localidade.setId(Integer.parseInt(getIdLocalidade()));
			qualidadeAgua.setLocalidade(localidade);
		}

		// Setor Comercial

		if (getIdSetorComercial() != null
				&& !getIdSetorComercial().toString().trim()
						.equalsIgnoreCase("")) {
			if (getIdLocalidade() != null
					&& !getIdLocalidade().toString().trim()
							.equalsIgnoreCase("")) {

				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				filtroSetorComercial.limparListaParametros();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, new Integer(
								getIdLocalidade())));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						new Integer(getIdSetorComercial())));

				Collection colecaoSetorComerciais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				SetorComercial setorComercialPesquisa = new SetorComercial();
				setorComercialPesquisa = (SetorComercial) colecaoSetorComerciais
						.iterator().next();
				SetorComercial setorComercial = new SetorComercial();

				setorComercial.setId(setorComercialPesquisa.getId());
				qualidadeAgua.setSetorComercial(setorComercial);

			}

		}

		// Fonte Captação
		if(getFonteCaptacao() != null && 
			!getFonteCaptacao().equals("") &&
			!getFonteCaptacao().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ){
			
			FonteCaptacao fonte = new FonteCaptacao();
			fonte.setId(new Integer(getFonteCaptacao()));
			
			qualidadeAgua.setFonteCaptacao(fonte);
		}
		
		// Sistema Abastecimento
		if(getSistemaAbastecimento() != null && !getSistemaAbastecimento().equals("")
				&& !getSistemaAbastecimento().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FiltroSistemaAbastecimento filtroSistemaAbastecimento = new FiltroSistemaAbastecimento();
			
			filtroSistemaAbastecimento.adicionarParametro(new ParametroSimples
					(FiltroSistemaAbastecimento.DESCRICAO, getSistemaAbastecimento()));
			
			Collection colecaoSistemaAbastecimento = fachada.pesquisar(filtroSistemaAbastecimento,
					SistemaAbastecimento.class.getName());
			
			SistemaAbastecimento sistemaAbastecimentoPesquisa = new SistemaAbastecimento();
			
			sistemaAbastecimentoPesquisa = (SistemaAbastecimento) colecaoSistemaAbastecimento.iterator().next();
			
			SistemaAbastecimento sistemaAbastecimento = new SistemaAbastecimento();
						
			sistemaAbastecimento.setId(new Integer(sistemaAbastecimentoPesquisa.getId()));
			
			qualidadeAgua.setSistemaAbastecimento(sistemaAbastecimento);
			
		}

		// Turbidez

		if (getIndiceMensalTurbidez() != null
				&& !getIndiceMensalTurbidez().equals("")) {

			qualidadeAgua
					.setNumeroIndiceTurbidez(Util
							.formatarMoedaRealparaBigDecimal(getIndiceMensalTurbidez()));
		}

		// Cloro Residual

		if (getIndiceMensalCloroResidual() != null
				&& !getIndiceMensalCloroResidual().equals("")) {

			qualidadeAgua
					.setNumeroCloroResidual(Util
							.formatarMoedaRealparaBigDecimal(getIndiceMensalCloroResidual()));
		}

		// PH

		if (getIndiceMensalPH() != null && !getIndiceMensalPH().equals("")) {

			qualidadeAgua.setNumeroIndicePh(Util
					.formatarMoedaRealparaBigDecimal(getIndiceMensalPH()));
		}

		// Cor

		if (getIndiceMensalCor() != null && !getIndiceMensalCor().equals("")) {

			qualidadeAgua.setNumeroIndiceCor(Util
					.formatarMoedaRealparaBigDecimal(getIndiceMensalCor()));
		}

		// Flúor

		if (getIndiceMensalFluor() != null
				&& !getIndiceMensalFluor().equals("")) {

			qualidadeAgua.setNumeroIndiceFluor(Util
					.formatarMoedaRealparaBigDecimal(getIndiceMensalFluor()));
		}

		// Ferro

		if (getIndiceMensalFerro() != null
				&& !getIndiceMensalFerro().equals("")) {

			qualidadeAgua.setNumeroIndiceFerro(Util
					.formatarMoedaRealparaBigDecimal(getIndiceMensalFerro()));
		}

		// Coliformes Totais

		if (getIndiceMensalColiformesTotais() != null
				&& !getIndiceMensalColiformesTotais().equals("")) {

			qualidadeAgua
					.setNumeroIndiceColiformesTotais(Util
							.formatarMoedaRealparaBigDecimal(getIndiceMensalColiformesTotais()));
		}

		// Coliformes Fecais

		if (getIndiceMensalColiformesFecais() != null
				&& !getIndiceMensalColiformesFecais().equals("")) {

			qualidadeAgua
					.setNumeroIndiceColiformesFecais(Util
							.formatarMoedaRealparaBigDecimal(getIndiceMensalColiformesFecais()));
		}

		// Nitrato

		if (getIndiceMensalNitrato() != null
				&& !getIndiceMensalNitrato().equals("")) {

			qualidadeAgua.setNumeroNitrato(Util
					.formatarMoedaRealparaBigDecimal(getIndiceMensalNitrato()));
		}

		// Coliforms TermoTolerantes

		if (getIndiceMensalColiformesTermotolerantes() != null
				&& !getIndiceMensalColiformesTermotolerantes().equals("")) {

			qualidadeAgua
					.setNumeroIndiceColiformesTermotolerantes(Util
							.formatarMoedaRealparaBigDecimal(getIndiceMensalColiformesTermotolerantes()));
		}
		
		// Alcalinidade
		
		if (getIndiceMensalAlcalinidade() != null
				&& !getIndiceMensalAlcalinidade().equals("")) {
			
			qualidadeAgua.setNumeroIndiceAlcalinidade(Util.
					formatarMoedaRealparaBigDecimal(getIndiceMensalAlcalinidade()));
			
		}
		
		//se o valor chegar vazio da pagina setar como vazio no banco
		//Flávio Leonardo CRC-1419 data: 17/03/09

		// Quantidade Turbidez Analisadas

		if (getQuantidadeTurbidezAnalisadas() != null
				&& !getQuantidadeTurbidezAnalisadas().equals("")) {

			qualidadeAgua.setQuantidadeTurbidezAnalisadas(new Integer(
					getQuantidadeTurbidezAnalisadas()));
		}else{
			qualidadeAgua.setQuantidadeTurbidezAnalisadas(null);
		}

		// Quantidade Turbidez Exigidas

		if (getQuantidadeTurbidezExigidas() != null
				&& !getQuantidadeTurbidezExigidas().equals("")) {

			qualidadeAgua.setQuantidadeTurbidezExigidas(new Integer(
					getQuantidadeTurbidezExigidas()));
		}else{
			qualidadeAgua.setQuantidadeTurbidezExigidas(null);
		}

		// Quantidade Turbidez Conforme

		if (getQuantidadeTurbidezConforme() != null
				&& !getQuantidadeTurbidezConforme().equals("")) {

			qualidadeAgua.setQuantidadeTurbidezConforme(new Integer(
					getQuantidadeTurbidezConforme()));
		}else{
			qualidadeAgua.setQuantidadeTurbidezConforme(null);
		}

		// Quantidade Cor Exigidas

		if (getQuantidadeCorExigidas() != null
				&& !getQuantidadeCorExigidas().equals("")) {

			qualidadeAgua.setQuantidadeCorExigidas(new Integer(
					getQuantidadeCorExigidas()));
		}else{
			qualidadeAgua.setQuantidadeCorExigidas(null);
		}

		// Quantidade Cor Analisadas

		if (getQuantidadeCorAnalisadas() != null
				&& !getQuantidadeCorAnalisadas().equals("")) {

			qualidadeAgua.setQuantidadeCorAnalisadas(new Integer(
					getQuantidadeCorAnalisadas()));
		}else{
			qualidadeAgua.setQuantidadeCorAnalisadas(null);
		}

		// Quantidade Cor Conforme

		if (getQuantidadeCorConforme() != null
				&& !getQuantidadeCorConforme().equals("")) {

			qualidadeAgua.setQuantidadeCorConforme(new Integer(
					getQuantidadeCorConforme()));
		}else{
			qualidadeAgua.setQuantidadeCorConforme(null);
		}

		// Quantidade Cloro Exigidas

		if (getQuantidadeCloroExigidas() != null
				&& !getQuantidadeCloroExigidas().equals("")) {

			qualidadeAgua.setQuantidadeCloroExigidas(new Integer(
					getQuantidadeCloroExigidas()));
		}else{
			qualidadeAgua.setQuantidadeCloroExigidas(null);
		}

		// Quantidade Cloro Analisadas

		if (getQuantidadeCloroAnalisadas() != null
				&& !getQuantidadeCloroAnalisadas().equals("")) {

			qualidadeAgua.setQuantidadeCloroAnalisadas(new Integer(
					getQuantidadeCloroAnalisadas()));
		}else{
			qualidadeAgua.setQuantidadeCloroAnalisadas(null);
		}

		// Quantidade Cloro Conforme

		if (getQuantidadeCloroConforme() != null
				&& !getQuantidadeCloroConforme().equals("")) {

			qualidadeAgua.setQuantidadeCloroConforme(new Integer(
					getQuantidadeCloroConforme()));
		}else{
			qualidadeAgua.setQuantidadeCloroConforme(null);
		}

		// Quantidade Fluor Exigidas

		if (getQuantidadeFluorExigidas() != null
				&& !getQuantidadeFluorExigidas().equals("")) {

			qualidadeAgua.setQuantidadeFluorExigidas(new Integer(
					getQuantidadeFluorExigidas()));
		}else{
			qualidadeAgua.setQuantidadeFluorExigidas(null);
		}

		// Quantidade Fluor Analisadas

		if (getQuantidadeFluorAnalisadas() != null
				&& !getQuantidadeFluorAnalisadas().equals("")) {

			qualidadeAgua.setQuantidadeFluorAnalisadas(new Integer(
					getQuantidadeFluorAnalisadas()));
		}else{
			qualidadeAgua.setQuantidadeFluorAnalisadas(null);
		}

		// Quantidade Fluor Conforme

		if (getQuantidadeFluorConforme() != null
				&& !getQuantidadeFluorConforme().equals("")) {

			qualidadeAgua.setQuantidadeFluorConforme(new Integer(
					getQuantidadeFluorConforme()));
		}else{
			qualidadeAgua.setQuantidadeFluorConforme(null);
		}

		// Quantidade Coliformes Totais Exigidas

		if (getQuantidadeColiformesTotaisExigidas() != null
				&& !getQuantidadeColiformesTotaisExigidas().equals("")) {

			qualidadeAgua.setQuantidadeColiformesTotaisExigidas(new Integer(
					getQuantidadeColiformesTotaisExigidas()));
		}else{
			qualidadeAgua.setQuantidadeColiformesTotaisExigidas(null);
		}

		// Quantidade Coliformes Totais Analisadas

		if (getQuantidadeColiformesTotaisAnalisadas() != null
				&& !getQuantidadeColiformesTotaisAnalisadas().equals("")) {

			qualidadeAgua.setQuantidadeColiformesTotaisAnalisadas(new Integer(
					getQuantidadeColiformesTotaisAnalisadas()));
		}else{
			qualidadeAgua.setQuantidadeColiformesTotaisAnalisadas(null);
		}

		// Quantidade Coliformes Totais Conforme

		if (getQuantidadeColiformesTotaisConforme() != null
				&& !getQuantidadeColiformesTotaisConforme().equals("")) {

			qualidadeAgua.setQuantidadeColiformesTotaisConforme(new Integer(
					getQuantidadeColiformesTotaisConforme()));
		}else{
			qualidadeAgua.setQuantidadeColiformesTotaisConforme(null);
		}

		// Quantidade Coliformes Fecais Exigidas

		if (getQuantidadeColiformesFecaisExigidas() != null
				&& !getQuantidadeColiformesFecaisExigidas().equals("")) {

			qualidadeAgua.setQuantidadeColiformesFecaisExigidas(new Integer(
					getQuantidadeColiformesFecaisExigidas()));
		}else{
			qualidadeAgua.setQuantidadeColiformesFecaisExigidas(null);
		}

		// Quantidade Coliformes Fecais Analisadas

		if (getQuantidadeColiformesFecaisAnalisadas() != null
				&& !getQuantidadeColiformesFecaisAnalisadas().equals("")) {

			qualidadeAgua.setQuantidadeColiformesFecaisAnalisadas(new Integer(
					getQuantidadeColiformesFecaisAnalisadas()));
		}else{
			qualidadeAgua.setQuantidadeColiformesFecaisAnalisadas(null);
		}

		// Quantidade Coliformes Fecais Conforme

		if (getQuantidadeColiformesFecaisConforme() != null
				&& !getQuantidadeColiformesFecaisConforme().equals("")) {

			qualidadeAgua.setQuantidadeColiformesFecaisConforme(new Integer(
					getQuantidadeColiformesFecaisConforme()));
		}else{
			qualidadeAgua.setQuantidadeColiformesFecaisConforme(null);
		}

		// Quantidade Coliformes Termotolerantes Exigidas

		if (getQuantidadeColiformesTermotolerantesExigidas() != null
				&& !getQuantidadeColiformesTermotolerantesExigidas().equals("")) {

			qualidadeAgua
					.setQuantidadeColiformesTermotolerantesExigidas(new Integer(
							getQuantidadeColiformesTermotolerantesExigidas()));
		}else{
			qualidadeAgua
			.setQuantidadeColiformesTermotolerantesExigidas(null);
		}

		// Quantidade Coliformes Termotolerantes Analisadas

		if (getQuantidadeColiformesTermotolerantesAnalisadas() != null
				&& !getQuantidadeColiformesTermotolerantesAnalisadas().equals(
						"")) {

			qualidadeAgua
					.setQuantidadeColiformesTermotolerantesAnalisadas(new Integer(
							getQuantidadeColiformesTermotolerantesAnalisadas()));
		}else{
			qualidadeAgua
			.setQuantidadeColiformesTermotolerantesAnalisadas(null);
		}

		// Quantidade Coliformes Termotolerantes Conforme

		if (getQuantidadeColiformesTermotolerantesConforme() != null
				&& !getQuantidadeColiformesTermotolerantesConforme().equals("")) {

			qualidadeAgua
					.setQuantidadeColiformesTermotolerantesConforme(new Integer(
							getQuantidadeColiformesTermotolerantesConforme()));
		}else{
			qualidadeAgua
			.setQuantidadeColiformesTermotolerantesConforme(null);
		}
		
		// Quantidade Alcalinidade Exigidas
		
		if (getQuantidadeAlcalinidadeExigidas() != null
				&& !getQuantidadeAlcalinidadeExigidas().equals("")){
			
			qualidadeAgua.setQuantidadeAlcalinidadeExigidas(new Integer(getQuantidadeAlcalinidadeExigidas()));
			
		}else{
			
			qualidadeAgua.setQuantidadeAlcalinidadeExigidas(null);
			
		}
		
		//Quantidade Alcalinidade Analisadas
		
		if (getQuantidadeAlcalinidadeAnalisadas() != null
				&& !getQuantidadeAlcalinidadeAnalisadas().equals("")){
					
			qualidadeAgua.setQuantidadeAlcalinidadeAnalisadas(new Integer(getQuantidadeAlcalinidadeAnalisadas()));		
					
		}else{
			
			qualidadeAgua.setQuantidadeAlcalinidadeAnalisadas(null);
			
		}
		
		//Quantidade Alcalinidade Conforme
		
		if (getQuantidadeAlcalinidadeConforme() != null
				&& !getQuantidadeAlcalinidadeConforme().equals("")){
			
			qualidadeAgua.setQuantidadeAlcalinidadeConforme(new Integer(getQuantidadeAlcalinidadeConforme()));
			
		}else{
			
			qualidadeAgua.setQuantidadeAlcalinidadeConforme(null);
			
		}

		return qualidadeAgua;

	}

	public String getMesAno(Integer anoMes) {
		String anoMesStr = anoMes + "";
		String mesAno = "";

		mesAno = anoMesStr.substring(0, 4) + "/" + anoMesStr.substring(4, 6);

		return mesAno;
	}

	public String getIndiceMensalColiformesTermotolerantes() {
		return indiceMensalColiformesTermotolerantes;
	}

	public void setIndiceMensalColiformesTermotolerantes(
			String indiceMensalColiformesTermotolerantes) {
		this.indiceMensalColiformesTermotolerantes = indiceMensalColiformesTermotolerantes;
	}

	public String getPadraoColiformesTermotolerantes() {
		return padraoColiformesTermotolerantes;
	}

	public void setPadraoColiformesTermotolerantes(
			String padraoColiformesTermotolerantes) {
		this.padraoColiformesTermotolerantes = padraoColiformesTermotolerantes;
	}

	public String getQuantidadeCloroAnalisadas() {
		return quantidadeCloroAnalisadas;
	}

	public void setQuantidadeCloroAnalisadas(String quantidadeCloroAnalisadas) {
		this.quantidadeCloroAnalisadas = quantidadeCloroAnalisadas;
	}

	public String getQuantidadeCloroConforme() {
		return quantidadeCloroConforme;
	}

	public void setQuantidadeCloroConforme(String quantidadeCloroConforme) {
		this.quantidadeCloroConforme = quantidadeCloroConforme;
	}

	public String getQuantidadeCloroExigidas() {
		return quantidadeCloroExigidas;
	}

	public void setQuantidadeCloroExigidas(String quantidadeCloroExigidas) {
		this.quantidadeCloroExigidas = quantidadeCloroExigidas;
	}

	public String getQuantidadeColiformesFecaisAnalisadas() {
		return quantidadeColiformesFecaisAnalisadas;
	}

	public void setQuantidadeColiformesFecaisAnalisadas(
			String quantidadeColiformesFecaisAnalisadas) {
		this.quantidadeColiformesFecaisAnalisadas = quantidadeColiformesFecaisAnalisadas;
	}

	public String getQuantidadeColiformesFecaisConforme() {
		return quantidadeColiformesFecaisConforme;
	}

	public void setQuantidadeColiformesFecaisConforme(
			String quantidadeColiformesFecaisConforme) {
		this.quantidadeColiformesFecaisConforme = quantidadeColiformesFecaisConforme;
	}

	public String getQuantidadeColiformesFecaisExigidas() {
		return quantidadeColiformesFecaisExigidas;
	}

	public void setQuantidadeColiformesFecaisExigidas(
			String quantidadeColiformesFecaisExigidas) {
		this.quantidadeColiformesFecaisExigidas = quantidadeColiformesFecaisExigidas;
	}

	public String getQuantidadeColiformesTermotolerantesAnalisadas() {
		return quantidadeColiformesTermotolerantesAnalisadas;
	}

	public void setQuantidadeColiformesTermotolerantesAnalisadas(
			String quantidadeColiformesTermotolerantesAnalisadas) {
		this.quantidadeColiformesTermotolerantesAnalisadas = quantidadeColiformesTermotolerantesAnalisadas;
	}

	public String getQuantidadeColiformesTermotolerantesConforme() {
		return quantidadeColiformesTermotolerantesConforme;
	}

	public void setQuantidadeColiformesTermotolerantesConforme(
			String quantidadeColiformesTermotolerantesConforme) {
		this.quantidadeColiformesTermotolerantesConforme = quantidadeColiformesTermotolerantesConforme;
	}

	public String getQuantidadeColiformesTermotolerantesExigidas() {
		return quantidadeColiformesTermotolerantesExigidas;
	}

	public void setQuantidadeColiformesTermotolerantesExigidas(
			String quantidadeColiformesTermotolerantesExigidas) {
		this.quantidadeColiformesTermotolerantesExigidas = quantidadeColiformesTermotolerantesExigidas;
	}

	public String getQuantidadeColiformesTotaisAnalisadas() {
		return quantidadeColiformesTotaisAnalisadas;
	}

	public void setQuantidadeColiformesTotaisAnalisadas(
			String quantidadeColiformesTotaisAnalisadas) {
		this.quantidadeColiformesTotaisAnalisadas = quantidadeColiformesTotaisAnalisadas;
	}

	public String getQuantidadeColiformesTotaisConforme() {
		return quantidadeColiformesTotaisConforme;
	}

	public void setQuantidadeColiformesTotaisConforme(
			String quantidadeColiformesTotaisConforme) {
		this.quantidadeColiformesTotaisConforme = quantidadeColiformesTotaisConforme;
	}

	public String getQuantidadeColiformesTotaisExigidas() {
		return quantidadeColiformesTotaisExigidas;
	}

	public void setQuantidadeColiformesTotaisExigidas(
			String quantidadeColiformesTotaisExigidas) {
		this.quantidadeColiformesTotaisExigidas = quantidadeColiformesTotaisExigidas;
	}

	public String getQuantidadeCorAnalisadas() {
		return quantidadeCorAnalisadas;
	}

	public void setQuantidadeCorAnalisadas(String quantidadeCorAnalisadas) {
		this.quantidadeCorAnalisadas = quantidadeCorAnalisadas;
	}

	public String getQuantidadeCorConforme() {
		return quantidadeCorConforme;
	}

	public void setQuantidadeCorConforme(String quantidadeCorConforme) {
		this.quantidadeCorConforme = quantidadeCorConforme;
	}

	public String getQuantidadeCorExigidas() {
		return quantidadeCorExigidas;
	}

	public void setQuantidadeCorExigidas(String quantidadeCorExigidas) {
		this.quantidadeCorExigidas = quantidadeCorExigidas;
	}

	public String getQuantidadeFluorAnalisadas() {
		return quantidadeFluorAnalisadas;
	}

	public void setQuantidadeFluorAnalisadas(String quantidadeFluorAnalisadas) {
		this.quantidadeFluorAnalisadas = quantidadeFluorAnalisadas;
	}

	public String getQuantidadeFluorConforme() {
		return quantidadeFluorConforme;
	}

	public void setQuantidadeFluorConforme(String quantidadeFluorConforme) {
		this.quantidadeFluorConforme = quantidadeFluorConforme;
	}

	public String getQuantidadeFluorExigidas() {
		return quantidadeFluorExigidas;
	}

	public void setQuantidadeFluorExigidas(String quantidadeFluorExigidas) {
		this.quantidadeFluorExigidas = quantidadeFluorExigidas;
	}

	public String getQuantidadeTurbidezAnalisadas() {
		return quantidadeTurbidezAnalisadas;
	}

	public void setQuantidadeTurbidezAnalisadas(
			String quantidadeTurbidezAnalisadas) {
		this.quantidadeTurbidezAnalisadas = quantidadeTurbidezAnalisadas;
	}

	public String getQuantidadeTurbidezConforme() {
		return quantidadeTurbidezConforme;
	}

	public void setQuantidadeTurbidezConforme(String quantidadeTurbidezConforme) {
		this.quantidadeTurbidezConforme = quantidadeTurbidezConforme;
	}

	public String getQuantidadeTurbidezExigidas() {
		return quantidadeTurbidezExigidas;
	}

	public void setQuantidadeTurbidezExigidas(String quantidadeTurbidezExigidas) {
		this.quantidadeTurbidezExigidas = quantidadeTurbidezExigidas;
	}

	public String getPadraoAlcalinidade() {
		return padraoAlcalinidade;
	}

	public void setPadraoAlcalinidade(String padraoAlcalinidade) {
		this.padraoAlcalinidade = padraoAlcalinidade;
	}

}
