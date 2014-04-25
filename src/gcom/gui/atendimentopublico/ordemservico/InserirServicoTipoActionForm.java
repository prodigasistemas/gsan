package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoAtividade;
import gcom.atendimentopublico.ordemservico.ServicoTipoMaterial;
import gcom.atendimentopublico.ordemservico.ServicoTipoMotivoEncerramento;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoTipo;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <<Descrição da classe>>
 * 
 * @author lms, Pedro Alexandre
 * @date 01/08/2006, 13/12/2007
 */
public class InserirServicoTipoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	ServicoTipo servicoTipo = new ServicoTipo();
	
	String descricao;
	String abreviatura;
	String subgrupo;
	String valor;
	//String pavimento;
	String atualizacaoComercial;
	String servicoTerceirizado;
	String codigoServico;
	String tempoMedioExecucao;
	String idTipoDebito;
	String descricaoTipoDebito;
	String tipoCredito;
	String prioridadeServico;
	String idPerfilServico;
	String descricaoPerfilServico;
	String idTipoServicoReferencia;
	String descricaoTipoServicoReferencia;
	String atividadeUnica;
	String method;
	
	String atividadeId;
	String servicoTipoAtividadeId;
	String servicoTipoAtividadeDescricao;
	String servicoTipoAtividadeOrdemExecucao;
	
	String materialId;
	String servicoTipoMaterialId;
	String servicoTipoMaterialDescricao;
	String servicoTipoMaterialQuantidadePadrao;
	
	String indicadorVistoria;
	String indicadorFiscalizacaoInfracao;
	
	Collection servicoTipoAtividades = new ArrayList();
	Collection servicoTipoMateriais = new ArrayList();
	Collection servicoTipoMotivoEncerramento = new ArrayList();
	
	String indicadorPavimentoRua;
	String indicadorPavimentoCalcada;
	
	String indicadorPermiteAlterarValor;
	
	String indicativoTipoSevicoEconomias;
	
	String indicadorInformacoesBoletimMedicao;
	String indicadorOrdemSeletiva;
	String indicadorEnvioPesquisaSatisfacao;
	String indicativoPavimento;
	String indicativoReposicaoAsfalto;
	String indicativoReposicaoParalelo;
	String indicativoReposicaoCalcada;
	String indicativoObrigatoriedadeAtividade;
	
	String indicadorEmpresaCobranca;
	
	String indicadorProgramacaoAutomatica;
	
	String motivoEncerramentoId;
	String servicoTipoMotivoEncerramentoId;
	String servicoTipoMotivoEncerramentoDescricao;
	
	String indicadorInspecaoAnormalidade;
	
	String indicativoObrigatoriedadeAtividadeValor;
	String indicadorProgramacaoAutomaticaValor;
	
	public String getIndicadorInspecaoAnormalidade() {
		return indicadorInspecaoAnormalidade;
	}
	public void setIndicadorInspecaoAnormalidade(
			String indicadorInspecaoAnormalidade) {
		this.indicadorInspecaoAnormalidade = indicadorInspecaoAnormalidade;
	}
	String indicadorEncAutomaticoRAQndEncOS;
	
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public String getatividadeUnica() {
		return atividadeUnica;
	}
	public void setAtividadeUnica(String atividadeUnica) {
		this.atividadeUnica = atividadeUnica;
	}
	public String getAtualizacaoComercial() {
		return atualizacaoComercial;
	}
	public void setAtualizacaoComercial(String atualizacaoComercial) {
		this.atualizacaoComercial = atualizacaoComercial;
	}
	public String getCodigoServico() {
		return codigoServico;
	}
	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricaoPerfilServico() {
		return descricaoPerfilServico;
	}
	public void setDescricaoPerfilServico(String descricaoPerfilServico) {
		this.descricaoPerfilServico = descricaoPerfilServico;
	}
	public String getDescricaoTipoDebito() {
		return descricaoTipoDebito;
	}
	public void setDescricaoTipoDebito(String descricaoTipoDebito) {
		this.descricaoTipoDebito = descricaoTipoDebito;
	}
	public String getDescricaoTipoServicoReferencia() {
		return descricaoTipoServicoReferencia;
	}
	public void setDescricaoTipoServicoReferencia(
			String descricaoTipoServicoReferencia) {
		this.descricaoTipoServicoReferencia = descricaoTipoServicoReferencia;
	}
	public String getIdPerfilServico() {
		return idPerfilServico;
	}
	public void setIdPerfilServico(String idPerfilServico) {
		this.idPerfilServico = idPerfilServico;
	}
	public String getIdTipoDebito() {
		return idTipoDebito;
	}
	public void setIdTipoDebito(String idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}
	public String getIdTipoServicoReferencia() {
		return idTipoServicoReferencia;
	}
	public void setIdTipoServicoReferencia(String idTipoServicoReferencia) {
		this.idTipoServicoReferencia = idTipoServicoReferencia;
	}
/*	public String getPavimento() {
		return pavimento;
	}
	public void setPavimento(String pavimento) {
		this.pavimento = pavimento;
	}
*/	public String getPrioridadeServico() {
		return prioridadeServico;
	}
	public void setPrioridadeServico(String prioridadeServico) {
		this.prioridadeServico = prioridadeServico;
	}
	public String getServicoTerceirizado() {
		return servicoTerceirizado;
	}
	public void setServicoTerceirizado(String servicoTerceirizado) {
		this.servicoTerceirizado = servicoTerceirizado;
	}
	public String getSubgrupo() {
		return subgrupo;
	}
	public void setSubgrupo(String subgrupo) {
		this.subgrupo = subgrupo;
	}
	public String getTempoMedioExecucao() {
		return tempoMedioExecucao;
	}
	public void setTempoMedioExecucao(String tempoMedioExecucao) {
		this.tempoMedioExecucao = tempoMedioExecucao;
	}
	public String getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public ServicoTipo getServicoTipo() {
		return setFormValues(servicoTipo);
	}
	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}
	
	public String getServicoTipoAtividadeDescricao() {
		return servicoTipoAtividadeDescricao;
	}
	public void setServicoTipoAtividadeDescricao(String servicoTipoAtividadeDescricao) {
		this.servicoTipoAtividadeDescricao = servicoTipoAtividadeDescricao;
	}
	public String getServicoTipoAtividadeId() {
		return servicoTipoAtividadeId;
	}
	public void setServicoTipoAtividadeId(String servicoTipoAtividadeId) {
		this.servicoTipoAtividadeId = servicoTipoAtividadeId;
	}
	public String getServicoTipoAtividadeOrdemExecucao() {
		return servicoTipoAtividadeOrdemExecucao;
	}
	public void setServicoTipoAtividadeOrdemExecucao(
			String servicoTipoAtividadeOrdemExecucao) {
		this.servicoTipoAtividadeOrdemExecucao = servicoTipoAtividadeOrdemExecucao;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Collection<ServicoTipoAtividade> getServicoTipoAtividades() {
		return servicoTipoAtividades;
	}
	public Collection<ServicoTipoMaterial> getServicoTipoMateriais() {
		return servicoTipoMateriais;
	}
	public String getAtividadeId() {
		return atividadeId;
	}
	public void setAtividadeId(String atividadeId) {
		this.atividadeId = atividadeId;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getServicoTipoMaterialDescricao() {
		return servicoTipoMaterialDescricao;
	}
	public void setServicoTipoMaterialDescricao(String servicoTipoMaterialDescricao) {
		this.servicoTipoMaterialDescricao = servicoTipoMaterialDescricao;
	}
	public String getServicoTipoMaterialId() {
		return servicoTipoMaterialId;
	}
	public void setServicoTipoMaterialId(String servicoTipoMaterialId) {
		this.servicoTipoMaterialId = servicoTipoMaterialId;
	}
	public String getServicoTipoMaterialQuantidadePadrao() {
		return servicoTipoMaterialQuantidadePadrao;
	}
	public void setServicoTipoMaterialQuantidadePadrao(
			String servicoTipoMaterialQuantidadePadrao) {
		this.servicoTipoMaterialQuantidadePadrao = servicoTipoMaterialQuantidadePadrao;
	}
	public void addServicoTipoAtividade() {
		Atividade atv;
		ServicoTipoAtividade stp;
		atv = new Atividade();
		atv.setId(Integer.parseInt(getAtividadeId()));
		atv.setDescricao(getServicoTipoAtividadeDescricao());
		stp = new ServicoTipoAtividade();
		stp.setNumeroExecucao(Short.parseShort(getServicoTipoAtividadeOrdemExecucao()));
		stp.setAtividade(atv);
		servicoTipoAtividades.add(stp);
	}
	
	public void removeServicoTipoAtividade() {
		for (Iterator iter = getServicoTipoAtividades().iterator(); iter.hasNext();) {
			ServicoTipoAtividade stp = (ServicoTipoAtividade) iter.next();
			String string = "$" + stp.getAtividade().getId() + "$" + stp.getNumeroExecucao() + "$";
			if (string.equals(getServicoTipoAtividadeId())) {
				iter.remove();
			}
		}		
	}
	
	public void removeAllServicoTipoAtividade() {
		for (Iterator iter = getServicoTipoAtividades().iterator(); iter.hasNext();) {
			iter.next();
			iter.remove();
		}			
	}	
	
	public void addServicoTipoMaterial() {
		Material mat;
		ServicoTipoMaterial stm;
		mat = new Material();
		mat.setId(Integer.parseInt(getMaterialId()));
		mat.setDescricao(getServicoTipoMaterialDescricao());
		stm = new ServicoTipoMaterial();
		try {
			stm.setQuantidadePadrao(new BigDecimal(getServicoTipoMaterialQuantidadePadrao()));
		} catch (NumberFormatException e) {			
		}
		stm.setMaterial(mat);
		servicoTipoMateriais.add(stm);
	}
	
	public void removeServicoTipoMaterial() {
		for (Iterator iter = getServicoTipoMateriais().iterator(); iter.hasNext();) {
			ServicoTipoMaterial stm = (ServicoTipoMaterial) iter.next();
			String string = "$" + stm.getMaterial().getId() + "$";
			if (string.equals(getServicoTipoMaterialId())) {
				iter.remove();
			}
		}		
	}
	
	
	public void removeServicoTipoMotivoEncerramento() {
		for (Iterator iter = getServicoTipoMotivoEncerramento().iterator(); iter.hasNext();) {
			AtendimentoMotivoEncerramento stm = (AtendimentoMotivoEncerramento) iter.next();
		String string = "" + stm.getId();
			if (string.equals(getMotivoEncerramentoId())) {
				iter.remove();
			}
		}		
	}
	
	private ServicoTipo setFormValues(ServicoTipo servicoTipo) {
		
		//descricao
		servicoTipo.setDescricao(getDescricao());
		//abreviatura
		servicoTipo.setDescricaoAbreviada(getAbreviatura());		
		//subgrupo		
		Integer idSubgrupo = Util.converterStringParaInteger(getSubgrupo());		
		if (Util.validarNumeroMaiorQueZERO(idSubgrupo)) {
			ServicoTipoSubgrupo servicoTipoSubgrupo = new ServicoTipoSubgrupo();
			servicoTipoSubgrupo.setId(idSubgrupo);
			servicoTipo.setServicoTipoSubgrupo(servicoTipoSubgrupo);
		}
		//indicador atualização comercial
		if (Util.validarNumeroMaiorQueZERO(getAtualizacaoComercial())) {
			servicoTipo.setIndicadorAtualizaComercial(Short.parseShort(getAtualizacaoComercial()));
		}
/*		//indicador pavimento
		if (Util.validarNumeroMaiorQueZERO(getPavimento())) {
			servicoTipo.setIndicadorPavimento(Short.parseShort(getPavimento()));
		}
*/
		
		//indicador pavimento de rua
		if (Util.validarNumeroMaiorQueZERO(getIndicadorPavimentoRua())) {
			servicoTipo.setIndicadorPavimentoRua(Short.parseShort(getIndicadorPavimentoRua()));
		}

		//indicador pavimento de calçada
		if (Util.validarNumeroMaiorQueZERO(getIndicadorPavimentoCalcada())) {
			servicoTipo.setIndicadorPavimentoCalcada(Short.parseShort(getIndicadorPavimentoCalcada()));
		}
		
		//indicador permite alteração de valor
		if (Util.validarNumeroMaiorQueZERO(getIndicadorPermiteAlterarValor())) {
			servicoTipo.setIndicadorPermiteAlterarValor(Short.parseShort(getIndicadorPermiteAlterarValor()));
		}
		
		//indicador serviço terceirizado
		if (Util.validarNumeroMaiorQueZERO(getServicoTerceirizado())) {
			servicoTipo.setIndicadorTerceirizado(Short.parseShort(getServicoTerceirizado()));
		}
		//código tipo de serviço
		servicoTipo.setCodigoServicoTipo(getCodigoServico());
		//valor do serviço
		if (getValor() != null) {
			try {
				BigDecimal valorServico = new BigDecimal(getValor().replace(',', '.'));
				
				servicoTipo.setValor(valorServico);
			} catch (NumberFormatException e) {			
			}
		}
		//tempo médio execução
		if (Util.validarNumeroMaiorQueZERO(getTempoMedioExecucao())) {
			servicoTipo.setTempoMedioExecucao(Short.parseShort(getTempoMedioExecucao()));
		}
		//tipo débito		
		Integer idDebitoTipo = Util.converterStringParaInteger(getIdTipoDebito());	
		if (Util.validarNumeroMaiorQueZERO(idDebitoTipo)) {
			DebitoTipo debitoTipo = new DebitoTipo();
			debitoTipo.setId(idDebitoTipo);
			servicoTipo.setDebitoTipo(debitoTipo);
		}
		//tipo crébito		
		Integer idCreditoTipo = Util.converterStringParaInteger(getTipoCredito());	
		if (Util.validarNumeroMaiorQueZERO(idCreditoTipo)) {
			CreditoTipo creditoTipo = new CreditoTipo();
			creditoTipo.setId(idCreditoTipo);
			servicoTipo.setCreditoTipo(creditoTipo);
		}
		//prioridade		
		Integer idPrioridade = Util.converterStringParaInteger(getPrioridadeServico());	
		if (Util.validarNumeroMaiorQueZERO(idPrioridade)) {
			ServicoTipoPrioridade servicoTipoPrioridade = new ServicoTipoPrioridade();
			servicoTipoPrioridade.setId(idPrioridade);
			servicoTipo.setServicoTipoPrioridade(servicoTipoPrioridade);
		}
		//perfil		
		Integer idServicoTipoPerfil = Util.converterStringParaInteger(getIdPerfilServico());	
		if (Util.validarNumeroMaiorQueZERO(idServicoTipoPerfil)) {
			ServicoPerfilTipo servicoPerfilTipo = new ServicoPerfilTipo();
			servicoPerfilTipo.setId(idServicoTipoPerfil);
			servicoTipo.setServicoPerfilTipo(servicoPerfilTipo);
		}
		//tipo serviço referência		
		Integer idServicoTipoReferencia = Util.converterStringParaInteger(getIdTipoServicoReferencia());	
		if (Util.validarNumeroMaiorQueZERO(idServicoTipoReferencia)) {
			ServicoTipoReferencia servicoTipoReferencia = new ServicoTipoReferencia();
			servicoTipoReferencia.setId(idServicoTipoReferencia);
			servicoTipo.setServicoTipoReferencia(servicoTipoReferencia);
		}
		//indicador Vistoria
		if (Util.validarNumeroMaiorQueZERO(getIndicadorVistoria())) {
			servicoTipo.setIndicadorVistoria(Short.parseShort(getIndicadorVistoria()));
		}
		//indicador Fiscalizacao Infração
		if (Util.validarNumeroMaiorQueZERO(getIndicadorFiscalizacaoInfracao())) {
			servicoTipo.setIndicadorFiscalizacaoInfracao(Short.parseShort(getIndicadorFiscalizacaoInfracao()));
		}
		
		//indicadorEmpresaCobranca
		if (Util.validarNumeroMaiorQueZERO(getIndicadorEmpresaCobranca())) {
			servicoTipo.setIndicadorEmpresaCobranca(Short.parseShort(getIndicadorEmpresaCobranca()));
		}
		
		
		
		//indicadorProgramacaoAutomatica
		if(Util.validarNumeroMaiorQueZERO(getIndicadorProgramacaoAutomatica())) {
			servicoTipo.setIndicadorProgramacaoAutomatica(Short.parseShort(getIndicadorProgramacaoAutomatica()));
		}
		
		//data última alteração
		servicoTipo.setUltimaAlteracao(new Date());
		
		//atividades
		servicoTipo.setServicoTipoAtividades(getServicoTipoAtividades());
		
		//materiais
		servicoTipo.setServicoTipoMateriais(getServicoTipoMateriais());
		
		//indicador Tipo Sevico Economias
		if (Util.validarNumeroMaiorQueZERO(getIndicativoTipoSevicoEconomias())) {
			servicoTipo.setIndicativoTipoSevicoEconomias(Short.parseShort(getIndicativoTipoSevicoEconomias()));
		}
		
		//indicador Vistoria
		if (Util.validarNumeroMaiorQueZERO(getIndicativoObrigatoriedadeAtividade())) {
			servicoTipo.setIndicadorAtividade(Short.parseShort(getIndicativoObrigatoriedadeAtividade()));
		}
		
		return servicoTipo;
	}
	
	public void reset() {
		 servicoTipo = new ServicoTipo();
		
		 descricao = null;
		 abreviatura = null;
		 subgrupo = null;
		 valor = null;
		 //pavimento = null;
		 indicadorPavimentoRua = null;
		 indicadorPavimentoCalcada = null;
		 atualizacaoComercial = null;
		 servicoTerceirizado = null;
		 codigoServico = null;
		 tempoMedioExecucao = null;
		 idTipoDebito = null;
		 descricaoTipoDebito = null;
		 tipoCredito = null;
		 prioridadeServico = null;
		 idPerfilServico = null;
		 descricaoPerfilServico = null;
		 idTipoServicoReferencia = null;
		 descricaoTipoServicoReferencia = null;
		 atividadeUnica = null;
		 indicadorFiscalizacaoInfracao = null;
		 indicadorVistoria = null;
		 indicadorPermiteAlterarValor = null;
		 indicativoTipoSevicoEconomias = null;
		 indicativoObrigatoriedadeAtividade = null;
		 
		 indicadorEmpresaCobranca = null;
		 
		 indicadorProgramacaoAutomatica = null;
		 
		 method = null;
		
		 atividadeId = null;
		 servicoTipoAtividadeId = null;
		 servicoTipoAtividadeDescricao = null;
		 servicoTipoAtividadeOrdemExecucao = null;
		
		 materialId = null;
		 servicoTipoMaterialId = null;
		 servicoTipoMaterialDescricao = null;
		 servicoTipoMaterialQuantidadePadrao = null;
		 
		 servicoTipoAtividades = new ArrayList<ServicoTipoAtividade>();
		 servicoTipoMateriais = new ArrayList<ServicoTipoMaterial>();
		 servicoTipoMotivoEncerramento = new ArrayList<ServicoTipoMotivoEncerramento>();
		 
		 
	
		 
		 
	}
	public String getIndicadorFiscalizacaoInfracao() {
		return indicadorFiscalizacaoInfracao;
	}
	public void setIndicadorFiscalizacaoInfracao(
			String indicadorFiscalizacaoInfracao) {
		this.indicadorFiscalizacaoInfracao = indicadorFiscalizacaoInfracao;
	}
	public String getIndicadorVistoria() {
		return indicadorVistoria;
	}
	public void setIndicadorVistoria(String indicadorVistoria) {
		this.indicadorVistoria = indicadorVistoria;
	}
	public String getIndicadorPavimentoCalcada() {
		return indicadorPavimentoCalcada;
	}
	public void setIndicadorPavimentoCalcada(String indicadorPavimentoCalcada) {
		this.indicadorPavimentoCalcada = indicadorPavimentoCalcada;
	}
	public String getIndicadorPavimentoRua() {
		return indicadorPavimentoRua;
	}
	public void setIndicadorPavimentoRua(String indicadorPavimentoRua) {
		this.indicadorPavimentoRua = indicadorPavimentoRua;
	}
	public String getIndicadorPermiteAlterarValor() {
		return indicadorPermiteAlterarValor;
	}
	public void setIndicadorPermiteAlterarValor(String indicadorPermiteAlterarValor) {
		this.indicadorPermiteAlterarValor = indicadorPermiteAlterarValor;
	}
	public String getIndicativoTipoSevicoEconomias() {
		return indicativoTipoSevicoEconomias;
	}
	public void setIndicativoTipoSevicoEconomias(
			String indicativoTipoSevicoEconomias) {
		this.indicativoTipoSevicoEconomias = indicativoTipoSevicoEconomias;
	}
	public String getIndicadorInformacoesBoletimMedicao() {
		return indicadorInformacoesBoletimMedicao;
	}
	public void setIndicadorInformacoesBoletimMedicao(
			String indicadorInformacoesBoletimMedicao) {
		this.indicadorInformacoesBoletimMedicao = indicadorInformacoesBoletimMedicao;
	}
	public String getIndicadorOrdemSeletiva() {
		return indicadorOrdemSeletiva;
	}
	public void setIndicadorOrdemSeletiva(String indicadorOrdemSeletiva) {
		this.indicadorOrdemSeletiva = indicadorOrdemSeletiva;
	}
	public String getIndicadorEnvioPesquisaSatisfacao() {
		return indicadorEnvioPesquisaSatisfacao;
	}
	public void setIndicadorEnvioPesquisaSatisfacao(
			String indicadorEnvioPesquisaSatisfacao) {
		this.indicadorEnvioPesquisaSatisfacao = indicadorEnvioPesquisaSatisfacao;
	}
	public String getIndicativoPavimento() {
		return indicativoPavimento;
	}
	public void setIndicativoPavimento(String indicativoPavimento) {
		this.indicativoPavimento = indicativoPavimento;
	}
	public String getIndicativoReposicaoAsfalto() {
		return indicativoReposicaoAsfalto;
	}
	public void setIndicativoReposicaoAsfalto(String indicativoReposicaoAsfalto) {
		this.indicativoReposicaoAsfalto = indicativoReposicaoAsfalto;
	}
	public String getIndicativoReposicaoCalcada() {
		return indicativoReposicaoCalcada;
	}
	public void setIndicativoReposicaoCalcada(String indicativoReposicaoCalcada) {
		this.indicativoReposicaoCalcada = indicativoReposicaoCalcada;
	}
	public String getIndicativoReposicaoParalelo() {
		return indicativoReposicaoParalelo;
	}
	public void setIndicativoReposicaoParalelo(String indicativoReposicaoParalelo) {
		this.indicativoReposicaoParalelo = indicativoReposicaoParalelo;
	}
	public String getIndicadorEmpresaCobranca() {
		return indicadorEmpresaCobranca;
	}
	public void setIndicadorEmpresaCobranca(String indicadorEmpresaCobranca) {
		this.indicadorEmpresaCobranca = indicadorEmpresaCobranca;
	}
	public String getIndicadorProgramacaoAutomatica() {
		return indicadorProgramacaoAutomatica;
	}
	public void setIndicadorProgramacaoAutomatica(
			String indicadorProgramacaoAutomatica) {
		this.indicadorProgramacaoAutomatica = indicadorProgramacaoAutomatica;
	}
	public String getAtividadeUnica() {
		return atividadeUnica;
	}
	public String getIndicativoObrigatoriedadeAtividade() {
		return indicativoObrigatoriedadeAtividade;
	}
	public void setIndicativoObrigatoriedadeAtividade(String indicativoObrigatoriedadeAtividade) {
		this.indicativoObrigatoriedadeAtividade = indicativoObrigatoriedadeAtividade;
	}
	public String getIndicadorEncAutomaticoRAQndEncOS() {
		return indicadorEncAutomaticoRAQndEncOS;
	}
	public void setIndicadorEncAutomaticoRAQndEncOS(
			String indicadorEncAutomaticoRAQndEncOS) {
		this.indicadorEncAutomaticoRAQndEncOS = indicadorEncAutomaticoRAQndEncOS;
	}	
	public String getMotivoEncerramentoId() {
		return motivoEncerramentoId;
	}
	public String getServicoTipomotivoEncerramentoDescricao() {
		return servicoTipoMotivoEncerramentoDescricao;
	}
	public String getServicoTipomotivoEncerramentoId() {
		return servicoTipoMotivoEncerramentoId;
	}
	public void setMotivoEncerramentoId(String motivoEncerramentoId) {
		this.motivoEncerramentoId = motivoEncerramentoId;
	}
	public void setServicoTipoMotivoEncerramentoDescricao(
			String servicoTipoMotivoEncerramentoDescricao) {
		this.servicoTipoMotivoEncerramentoDescricao = servicoTipoMotivoEncerramentoDescricao;
	}
	public void setServicoTipoMotivoEncerramentoId(
			String servicoTipoMotivoEncerramentoId) {
		this.servicoTipoMotivoEncerramentoId = servicoTipoMotivoEncerramentoId;
	}
	public Collection getServicoTipoMotivoEncerramento() {
		return servicoTipoMotivoEncerramento;
	}
	public String getServicoTipoMotivoEncerramentoDescricao() {
		return servicoTipoMotivoEncerramentoDescricao;
	}
	public String getServicoTipoMotivoEncerramentoId() {
		return servicoTipoMotivoEncerramentoId;
	}
	public void setServicoTipoMotivoEncerramento(
			Collection servicoTipoMotivoEncerramento) {
		this.servicoTipoMotivoEncerramento = servicoTipoMotivoEncerramento;
	}
	public String getIndicadorProgramacaoAutomaticaValor() {
		return indicadorProgramacaoAutomaticaValor;
	}
	public void setIndicadorProgramacaoAutomaticaValor(
			String indicadorProgramacaoAutomaticaValor) {
		this.indicadorProgramacaoAutomaticaValor = indicadorProgramacaoAutomaticaValor;
	}
	public String getIndicativoObrigatoriedadeAtividadeValor() {
		return indicativoObrigatoriedadeAtividadeValor;
	}
	public void setIndicativoObrigatoriedadeAtividadeValor(
			String indicativoObrigatoriedadeAtividadeValor) {
		this.indicativoObrigatoriedadeAtividadeValor = indicativoObrigatoriedadeAtividadeValor;
	}
	
}
