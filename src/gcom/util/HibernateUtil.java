package gcom.util;

import gcom.arrecadacao.ArrecadacaoContabilParametros;
import gcom.arrecadacao.ArrecadacaoDadosDiarios;
import gcom.arrecadacao.ArrecadacaoDadosDiariosAuxiliar;
import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContrato;
import gcom.arrecadacao.ArrecadadorContratoTarifa;
import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.ArrecadadorMovimentoItem;
import gcom.arrecadacao.ContratoDemanda;
import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.arrecadacao.DeducaoTipo;
import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.DevolucaoDadosDiarios;
import gcom.arrecadacao.DevolucaoDadosDiariosAuxiliar;
import gcom.arrecadacao.DevolucaoHistorico;
import gcom.arrecadacao.DevolucaoSituacao;
import gcom.arrecadacao.GuiaDevolucao;
import gcom.arrecadacao.MetasArrecadacao;
import gcom.arrecadacao.MovimentoCartaoRejeita;
import gcom.arrecadacao.RecebimentoTipo;
import gcom.arrecadacao.RegistroCodigo;
import gcom.arrecadacao.ResumoArrecadacao;
import gcom.arrecadacao.aviso.AvisoAcerto;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.AvisoDeducoes;
import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.big.BoletimInformacoesGerenciais;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoRetornoCodigo;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoCategoria;
import gcom.arrecadacao.pagamento.GuiaPagamentoCategoriaHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamentoHistorico;
import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.arrecadacao.pagamento.GuiaPagamentoParcelamentoCartao;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoCartaoDebito;
import gcom.arrecadacao.pagamento.PagamentoCartaoDebitoItem;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.arrecadacao.pagamento.SequenciaCartao;
import gcom.atendimentopublico.AgenciaReguladora;
import gcom.atendimentopublico.AgenciaReguladoraMunicipio;
import gcom.atendimentopublico.EspecificacaoPavimentacaoServicoTipo;
import gcom.atendimentopublico.EspecificacaoUnidadeCobranca;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.EmissaoOrdemCobrancaTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacaoConsumoTipo;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gcom.atendimentopublico.ligacaoagua.SupressaoTipo;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoCaixaInspecao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoAguasPluviais;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDestinoDejetos;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoDiametro;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoMaterial;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacaoConsumoTipo;
import gcom.atendimentopublico.ordemservico.AnormalidadeComandoOSS;
import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.BoletimOsConcluida;
import gcom.atendimentopublico.ordemservico.CapacidHidrComandoOSS;
import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.atendimentopublico.ordemservico.DataFiscalizacaoOsSeletiva;
import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.EquipeComponentes;
import gcom.atendimentopublico.ordemservico.EquipeEquipamentosEspeciais;
import gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipo;
import gcom.atendimentopublico.ordemservico.FiscalizacaoColetiva;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoAgua;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoEsgoto;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoHidrometroCapacidade;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacaoServicoACobrar;
import gcom.atendimentopublico.ordemservico.FotoSituacaoOrdemServico;
import gcom.atendimentopublico.ordemservico.LigacaoSitComandoOSS;
import gcom.atendimentopublico.ordemservico.Material;
import gcom.atendimentopublico.ordemservico.MaterialUnidade;
import gcom.atendimentopublico.ordemservico.MensagemAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.MotivoRejeicao;
import gcom.atendimentopublico.ordemservico.OSAtividadeExecucaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OSAtividadeMaterialProgramacaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OSAtividadeProgramacaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OSPriorizacaoTipo;
import gcom.atendimentopublico.ordemservico.OSProgramacaoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.OSProgramacaoCalibragem;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoAtividade;
import gcom.atendimentopublico.ordemservico.OrdemServicoBoletim;
import gcom.atendimentopublico.ordemservico.OrdemServicoFiscSit;
import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.atendimentopublico.ordemservico.OrdemServicoMovimento;
import gcom.atendimentopublico.ordemservico.OrdemServicoMovimentoHistorico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.OrdemServicoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.OsAtividadeMaterialExecucao;
import gcom.atendimentopublico.ordemservico.OsAtividadePeriodoExecucao;
import gcom.atendimentopublico.ordemservico.OsExecucaoEquipe;
import gcom.atendimentopublico.ordemservico.OsExecucaoEquipeComponentes;
import gcom.atendimentopublico.ordemservico.OsProgramNaoEncerMotivo;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoAtividade;
import gcom.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gcom.atendimentopublico.ordemservico.ServicoTipoGrupo;
import gcom.atendimentopublico.ordemservico.ServicoTipoMaterial;
import gcom.atendimentopublico.ordemservico.ServicoTipoMotivoEncerramento;
import gcom.atendimentopublico.ordemservico.ServicoTipoOperacao;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoSubgrupo;
import gcom.atendimentopublico.ordemservico.SupressaoMotivo;
import gcom.atendimentopublico.portal.QuestionarioSatisfacaoCliente;
import gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotReclamacao;
import gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotRetorno;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.atendimentopublico.registroatendimento.LocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidade;
import gcom.atendimentopublico.registroatendimento.LocalidadeSolicTipoGrupo;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.RAReiteracao;
import gcom.atendimentopublico.registroatendimento.RAReiteracaoFone;
import gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora;
import gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladoraFone;
import gcom.atendimentopublico.registroatendimento.RaEncerramentoComando;
import gcom.atendimentopublico.registroatendimento.RaEncerramentoComandoEspecificacoes;
import gcom.atendimentopublico.registroatendimento.RaEnderecoDescritivo;
import gcom.atendimentopublico.registroatendimento.RaMotivoReativacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoAnexo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoConta;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoPagamentoDuplicidade;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoDocumentoObrigatorio;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gcom.atendimentopublico.registroatendimento.SolicitanteFone;
import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.atendimentopublico.registroatendimento.VisualizacaoRegistroAtendimentoUrgencia;
import gcom.atualizacaocadastral.ClienteEnderecoRetorno;
import gcom.atualizacaocadastral.ClienteFoneRetorno;
import gcom.atualizacaocadastral.ClienteImovelRetorno;
import gcom.atualizacaocadastral.ClienteRetorno;
import gcom.atualizacaocadastral.ImagemRetorno;
import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.atualizacaocadastral.ImovelRamoAtividadeRetorno;
import gcom.atualizacaocadastral.ImovelRetorno;
import gcom.atualizacaocadastral.ImovelSubcategoriaRetorno;
import gcom.atualizacaocadastral.ImovelTipoOcupanteQuantidadeRetorno;
import gcom.batch.FuncionalidadeIniciada;
import gcom.batch.FuncionalidadeSituacao;
import gcom.batch.Processo;
import gcom.batch.ProcessoFuncionalidade;
import gcom.batch.ProcessoIniciado;
import gcom.batch.ProcessoSituacao;
import gcom.batch.ProcessoTipo;
import gcom.batch.Relatorio;
import gcom.batch.RelatorioGerado;
import gcom.batch.UnidadeIniciada;
import gcom.batch.UnidadeProcessamento;
import gcom.batch.UnidadeSituacao;
import gcom.batch.auxiliarbatch.CobrancaDocumentoControleGeracao;
import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.ContaBraile;
import gcom.cadastro.CpfTipo;
import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.EmailClienteAlterado;
import gcom.cadastro.EmpresaContratoCadastro;
import gcom.cadastro.EmpresaContratoCadastroAtributo;
import gcom.cadastro.EnvioEmail;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.VersaoMobile;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificado;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoBinario;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCritica;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCriticaTipo;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoLinha;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteContaHistorico;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteFoneAtualizacaoCadastral;
import gcom.cadastro.cliente.ClienteGuiaPagamento;
import gcom.cadastro.cliente.ClienteGuiaPagamentoHistorico;
import gcom.cadastro.cliente.ClienteHistorico;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.dadocensitario.FonteDadosCensitario;
import gcom.cadastro.dadocensitario.IbgeSetorCensitario;
import gcom.cadastro.dadocensitario.IbgeSetorCensitarioDado;
import gcom.cadastro.dadocensitario.LocalidadeDadosCensitario;
import gcom.cadastro.dadocensitario.MunicipioDadosCensitario;
import gcom.cadastro.descricaogenerica.DescricaoGenerica;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobranca;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.CepTipo;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.EnderecoTipo;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.funcionario.FuncionarioCargo;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.geografico.RegiaoDesenvolvimento;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.CadastroOcorrencia;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.Despejo;
import gcom.cadastro.imovel.EloAnormalidade;
import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelCadastroOcorrencia;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelDoacao;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.imovel.ImovelEloAnormalidade;
import gcom.cadastro.imovel.ImovelEnderecoAnterior;
import gcom.cadastro.imovel.ImovelHistorico;
import gcom.cadastro.imovel.ImovelImagem;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.cadastro.imovel.ImovelRamoAtividadeAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSituacao;
import gcom.cadastro.imovel.ImovelSituacaoTipo;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelSuprimido;
import gcom.cadastro.imovel.ImovelTipoCobertura;
import gcom.cadastro.imovel.ImovelTipoConstrucao;
import gcom.cadastro.imovel.ImovelTipoHabitacao;
import gcom.cadastro.imovel.ImovelTipoOcupante;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidade;
import gcom.cadastro.imovel.ImovelTipoOcupanteQuantidadeAtualizacaoCadastral;
import gcom.cadastro.imovel.ImovelTipoPropriedade;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.imovel.PiscinaVolumeFaixa;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.imovel.ReservatorioVolumeFaixa;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.VwImovelPrincipalCategoria;
import gcom.cadastro.localidade.AreaTipo;
import gcom.cadastro.localidade.CondicaoAbastecimentoAgua;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.GrauDificuldadeExecucao;
import gcom.cadastro.localidade.GrauIntermitencia;
import gcom.cadastro.localidade.GrauRiscoSegurancaFisica;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.LocalidadeClasse;
import gcom.cadastro.localidade.LocalidadePorte;
import gcom.cadastro.localidade.NivelPressao;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.QuadraPerfil;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.localidade.Zeis;
import gcom.cadastro.projeto.Projeto;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaAlteracaoHistorico;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.tarifasocial.RendaTipo;
import gcom.cadastro.tarifasocial.TarifaSocialCarta;
import gcom.cadastro.tarifasocial.TarifaSocialCartaDebito;
import gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.TarifaSocialMotivoCarta;
import gcom.cadastro.tarifasocial.TarifaSocialRevisaoMotivo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacionalMunicipio;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoCalcada;
import gcom.cadastro.unidade.UnidadeRepavimentadoraCustoPavimentoRua;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.cobranca.CicloMeta;
import gcom.cobranca.CicloMetaGrupo;
import gcom.cobranca.CmdEmpresaCobrancaContaLigacaoAguaSituacao;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaAcaoCronograma;
import gcom.cobranca.CobrancaAcaoOrdemServicoNaoAceitas;
import gcom.cobranca.CobrancaAcaoSituacao;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.CobrancaAtividadeComandoRota;
import gcom.cobranca.CobrancaBoletimDesconto;
import gcom.cobranca.CobrancaBoletimExecutado;
import gcom.cobranca.CobrancaBoletimMedicao;
import gcom.cobranca.CobrancaBoletimSucesso;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaCriterioLinha;
import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoFisc;
import gcom.cobranca.CobrancaDocumentoHistorico;
import gcom.cobranca.CobrancaDocumentoImpressao;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.CobrancaDocumentoItemHistorico;
import gcom.cobranca.CobrancaForma;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.CobrancaGrupoCronogramaMes;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.CobrancaSituacaoComando;
import gcom.cobranca.CobrancaSituacaoHistorico;
import gcom.cobranca.CobrancaSituacaoMotivo;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaExtensao;
import gcom.cobranca.ComandoEmpresaCobrancaContaGerencia;
import gcom.cobranca.ComandoEmpresaCobrancaContaImovelPerfil;
import gcom.cobranca.ComandoEmpresaCobrancaContaUnidadeNegocio;
import gcom.cobranca.CriterioSituacaoCobranca;
import gcom.cobranca.CriterioSituacaoLigacaoAgua;
import gcom.cobranca.CriterioSituacaoLigacaoEsgoto;
import gcom.cobranca.DocumentoEmissaoForma;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.DocumentosReceberFaixaDiasVencidos;
import gcom.cobranca.EmpresaCobrancaConta;
import gcom.cobranca.EmpresaCobrancaContaPagamentos;
import gcom.cobranca.ImovelNaoGerado;
import gcom.cobranca.IndicesAcrescimosImpontualidade;
import gcom.cobranca.MotivoNaoAceitacaoEncerramentoOS;
import gcom.cobranca.MotivoNaoGeracaoDocCobranca;
import gcom.cobranca.NegativCritCobrGrupo;
import gcom.cobranca.NegativCritElo;
import gcom.cobranca.NegativCritGerReg;
import gcom.cobranca.NegativCritNegRetMot;
import gcom.cobranca.NegativCritUndNeg;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.NegativacaoCriterioClienteTipo;
import gcom.cobranca.NegativacaoCriterioCpfTipo;
import gcom.cobranca.NegativacaoCriterioImovelPerfil;
import gcom.cobranca.NegativacaoCriterioLigacaoAgua;
import gcom.cobranca.NegativacaoCriterioLigacaoEsgoto;
import gcom.cobranca.NegativacaoCriterioSituacaoCobranca;
import gcom.cobranca.NegativacaoCriterioSituacaoEspecialCobranca;
import gcom.cobranca.NegativacaoCriterioSubcategoria;
import gcom.cobranca.NegativacaoImoveis;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorContrato;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorMovimento;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.NegativadorMovimentoRegItem;
import gcom.cobranca.NegativadorMovimentoRegParcelamento;
import gcom.cobranca.NegativadorMovimentoRegRetMot;
import gcom.cobranca.NegativadorRegistroTipo;
import gcom.cobranca.NegativadorResultadoSimulacao;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.cobranca.ParcelamentoGrupo;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.cobranca.ResumoCobrancaAcao;
import gcom.cobranca.ResumoCobrancaAcaoEventual;
import gcom.cobranca.ResumoCobrancaSituacaoEspecial;
import gcom.cobranca.ResumoNegativacao;
import gcom.cobranca.ResumoPendencia;
import gcom.cobranca.RotaAcaoCriterio;
import gcom.cobranca.UnidadeOrganizacionalTestemunha;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoCliente;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoItem;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamento;
import gcom.cobranca.contratoparcelamento.PrestacaoItemContratoParcelamento;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoes;
import gcom.cobranca.contratoparcelamento.TipoRelacao;
import gcom.cobranca.parcelamento.ParcDesctoInativVista;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.ParcelamentoFaixaDesconto;
import gcom.cobranca.parcelamento.ParcelamentoFaixaValor;
import gcom.cobranca.parcelamento.ParcelamentoItem;
import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacao;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.cobranca.parcelamento.ParcelamentoTipo;
import gcom.faturamento.ConsumoFaixaCategoria;
import gcom.faturamento.ConsumoFaixaLigacao;
import gcom.faturamento.ConsumoMinimoParametro;
import gcom.faturamento.ContaRevisaoFaixaValor;
import gcom.faturamento.DocumentoNaoEntregue;
import gcom.faturamento.ExtratoQuitacao;
import gcom.faturamento.ExtratoQuitacaoItem;
import gcom.faturamento.FaturamentoAtivCronRota;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoContabilParametros;
import gcom.faturamento.FaturamentoDados;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.faturamento.FaturamentoImediatoAjuste;
import gcom.faturamento.FaturamentoSituacaoComando;
import gcom.faturamento.FaturamentoSituacaoHistorico;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FaturamentoTipo;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.HistogramaAguaEconomia;
import gcom.faturamento.HistogramaAguaEconomiaSemQuadra;
import gcom.faturamento.HistogramaAguaLigacao;
import gcom.faturamento.HistogramaAguaLigacaoSemQuadra;
import gcom.faturamento.HistogramaEsgotoEconomia;
import gcom.faturamento.HistogramaEsgotoLigacao;
import gcom.faturamento.HistogramaEsgotoLigacaoSemQuadra;
import gcom.faturamento.ImpostoTipo;
import gcom.faturamento.ImpostoTipoAliquota;
import gcom.faturamento.LancamentoAgenciaReguladora;
import gcom.faturamento.MotivoInterferenciaTipo;
import gcom.faturamento.MovimentoContaCategoriaConsumoFaixa;
import gcom.faturamento.MovimentoContaImpostoDeduzido;
import gcom.faturamento.MovimentoContaPrefaturada;
import gcom.faturamento.MovimentoContaPrefaturadaCategoria;
import gcom.faturamento.Prescricao;
import gcom.faturamento.QualidadeAgua;
import gcom.faturamento.QualidadeAguaPadrao;
import gcom.faturamento.ReceitasAFaturarResumo;
import gcom.faturamento.ResumoFaturamentoSimulacao;
import gcom.faturamento.ResumoFaturamentoSimulacaoCredito;
import gcom.faturamento.ResumoFaturamentoSimulacaoDebito;
import gcom.faturamento.ResumoFaturamentoSituacaoEspecial;
import gcom.faturamento.TarifaTipoCalculo;
import gcom.faturamento.VencimentoAlternativo;
import gcom.faturamento.autoinfracao.AutoInfracaoSituacao;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.autoinfracao.AutosInfracaoDebitoACobrar;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixaHistorico;
import gcom.faturamento.conta.ContaCategoriaHistorico;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaImpostosDeduzidos;
import gcom.faturamento.conta.ContaImpostosDeduzidosHistorico;
import gcom.faturamento.conta.ContaImpressao;
import gcom.faturamento.conta.ContaImpressaoTermicaQtde;
import gcom.faturamento.conta.ContaMensagem;
import gcom.faturamento.conta.ContaMotivoCancelamento;
import gcom.faturamento.conta.ContaMotivoInclusao;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.faturamento.conta.ContaMotivoRetificacaoColuna;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.ContaTipo;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FaturaItem;
import gcom.faturamento.conta.FaturaItemHistorico;
import gcom.faturamento.conta.MotivoNaoEntregaDocumento;
import gcom.faturamento.conta.Refaturamento;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoARealizarCategoria;
import gcom.faturamento.credito.CreditoARealizarCategoriaHistorico;
import gcom.faturamento.credito.CreditoARealizarGeral;
import gcom.faturamento.credito.CreditoARealizarHistorico;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoCategoria;
import gcom.faturamento.credito.CreditoRealizadoCategoriaHistorico;
import gcom.faturamento.credito.CreditoRealizadoHistorico;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarCategoria;
import gcom.faturamento.debito.DebitoACobrarCategoriaHistorico;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoACobrarHistorico;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCobradoCategoria;
import gcom.faturamento.debito.DebitoCobradoCategoriaHistorico;
import gcom.faturamento.debito.DebitoCobradoHistorico;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoFaixaValore;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.financeiro.ContaAReceberContabil;
import gcom.financeiro.ContaContabil;
import gcom.financeiro.DevedoresDuvidososContabilParametro;
import gcom.financeiro.DocumentosAReceberFaixaResumo;
import gcom.financeiro.DocumentosAReceberResumo;
import gcom.financeiro.FaixaDocumentosAReceber;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.LancamentoResumo;
import gcom.financeiro.LancamentoResumoConta;
import gcom.financeiro.LancamentoResumoContaHistorico;
import gcom.financeiro.LancamentoResumoValorTipo;
import gcom.financeiro.ParametrosDevedoresDuvidosos;
import gcom.financeiro.ParametrosDevedoresDuvidososItem;
import gcom.financeiro.ResumoDevedoresDuvidosos;
import gcom.financeiro.ResumoFaturamento;
import gcom.financeiro.ResumoReceita;
import gcom.financeiro.ValorVolumesConsumidosNaoFaturado;
import gcom.financeiro.lancamento.LancamentoContabil;
import gcom.financeiro.lancamento.LancamentoContabilItem;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoOrigem;
import gcom.financeiro.lancamento.LancamentoTipo;
import gcom.financeiro.lancamento.LancamentoTipoItem;
import gcom.gerencial.arrecadacao.GArrecadacaoForma;
import gcom.gerencial.arrecadacao.GArrecadador;
import gcom.gerencial.arrecadacao.GDevolucaoSituacao;
import gcom.gerencial.arrecadacao.UnResumoArrecadacao;
import gcom.gerencial.arrecadacao.UnResumoArrecadacaoAguaEsgoto;
import gcom.gerencial.arrecadacao.UnResumoArrecadacaoCredito;
import gcom.gerencial.arrecadacao.UnResumoArrecadacaoOutro;
import gcom.gerencial.arrecadacao.UnResumoArrecadacaoPorAno;
import gcom.gerencial.arrecadacao.pagamento.GEpocaPagamento;
import gcom.gerencial.arrecadacao.pagamento.GPagamentoSituacao;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;
import gcom.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;
import gcom.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;
import gcom.gerencial.atendimentopublico.registroatendimento.GAtendimentoMotivoEncerramento;
import gcom.gerencial.atendimentopublico.registroatendimento.GMeioSolicitacao;
import gcom.gerencial.atendimentopublico.registroatendimento.GSolicitacaoTipo;
import gcom.gerencial.atendimentopublico.registroatendimento.GSolicitacaoTipoEspecificacao;
import gcom.gerencial.atendimentopublico.registroatendimento.UnResumoRegistroAtendimento;
import gcom.gerencial.atendimentopublico.registroatendimento.UnResumoRegistroAtendimentoPorAno;
import gcom.gerencial.cadastro.GEmpresa;
import gcom.gerencial.cadastro.Indicador;
import gcom.gerencial.cadastro.RgResumoLigacaoEconomia;
import gcom.gerencial.cadastro.UnResumoConsumoAgua;
import gcom.gerencial.cadastro.UnResumoIndicadorLigacaoEconomia;
import gcom.gerencial.cadastro.UnResumoLigacaoEconomia;
import gcom.gerencial.cadastro.cliente.GClienteTipo;
import gcom.gerencial.cadastro.cliente.GEsferaPoder;
import gcom.gerencial.cadastro.geografico.GBairro;
import gcom.gerencial.cadastro.geografico.GMicrorregiao;
import gcom.gerencial.cadastro.geografico.GMunicipio;
import gcom.gerencial.cadastro.geografico.GRegiao;
import gcom.gerencial.cadastro.imovel.GCategoria;
import gcom.gerencial.cadastro.imovel.GImovelPerfil;
import gcom.gerencial.cadastro.imovel.GSubcategoria;
import gcom.gerencial.cadastro.localidade.GGerenciaRegional;
import gcom.gerencial.cadastro.localidade.GLocalidade;
import gcom.gerencial.cadastro.localidade.GLocalidadePorte;
import gcom.gerencial.cadastro.localidade.GQuadra;
import gcom.gerencial.cadastro.localidade.GSetorComercial;
import gcom.gerencial.cadastro.localidade.GUnidadeNegocio;
import gcom.gerencial.cadastro.unidade.GUnidadeOrganizacional;
import gcom.gerencial.cobranca.FaixaValor;
import gcom.gerencial.cobranca.GDocumentoTipo;
import gcom.gerencial.cobranca.UnResumoIndicadoresCobranca;
import gcom.gerencial.cobranca.UnResumoParcelamento;
import gcom.gerencial.cobranca.UnResumoParcelamentoPorAno;
import gcom.gerencial.cobranca.UnResumoPendencia;
import gcom.gerencial.cobranca.UnResumoPendenciaSemQuadra;
import gcom.gerencial.faturamento.GConsumoTarifa;
import gcom.gerencial.faturamento.GFaturamentoGrupo;
import gcom.gerencial.faturamento.GImpostoTipo;
import gcom.gerencial.faturamento.UnResumoFaturamento;
import gcom.gerencial.faturamento.UnResumoIndicadoresFaturamento;
import gcom.gerencial.faturamento.UnResumoRefaturamento;
import gcom.gerencial.faturamento.credito.GCreditoOrigem;
import gcom.gerencial.faturamento.credito.GCreditoTipo;
import gcom.gerencial.faturamento.debito.GDebitoTipo;
import gcom.gerencial.financeiro.GFinanciamentoTipo;
import gcom.gerencial.financeiro.lancamento.GLancamentoItem;
import gcom.gerencial.financeiro.lancamento.GLancamentoItemContabil;
import gcom.gerencial.micromedicao.GRota;
import gcom.gerencial.micromedicao.UnResumoColetaEsgoto;
import gcom.gerencial.micromedicao.UnResumoHidrometro;
import gcom.gerencial.micromedicao.UnResumoIndicadorDesempenhoMicromedicao;
import gcom.gerencial.micromedicao.UnResumoIndicadorDesempenhoMicromedicaoRef2010;
import gcom.gerencial.micromedicao.UnResumoInstalacaoHidrometro;
import gcom.gerencial.micromedicao.UnResumoInstalacaoHidrometroPorAno;
import gcom.gerencial.micromedicao.UnResumoLeituraAnormalidade;
import gcom.gerencial.micromedicao.UnResumoMeta;
import gcom.gerencial.micromedicao.UnResumoMetasAcumulado;
import gcom.gerencial.micromedicao.consumo.GConsumoTipo;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroCapacidade;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroClasseMetrologica;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroDiametro;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroLocalArmazenagem;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroMarca;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroMotivoBaixa;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroSituacao;
import gcom.gerencial.micromedicao.hidrometro.GHidrometroTipo;
import gcom.gerencial.micromedicao.leitura.GLeituraSituacao;
import gcom.gerencial.micromedicao.medicao.GMedicaoTipo;
import gcom.gerencial.operacional.GDistritoOperacional;
import gcom.integracao.ServicoTerceiroAcompanhamentoServico;
import gcom.interceptor.Interceptador;
import gcom.micromedicao.ArquivoTextoRetornoIS;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresa;
import gcom.micromedicao.ArquivoTextoRoteiroEmpresaDivisao;
import gcom.micromedicao.ConsumoMinimoArea;
import gcom.micromedicao.ContratoEmpresaAditivo;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.ImovelTestesMedicaoConsumo;
import gcom.micromedicao.ItemServico;
import gcom.micromedicao.ItemServicoContrato;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.MovimentoArquivoTextoRetornoIS;
import gcom.micromedicao.MovimentoRoteiroEmpresa;
import gcom.micromedicao.RateioTipo;
import gcom.micromedicao.ReleituraMobile;
import gcom.micromedicao.ResumoAnormalidadeLeitura;
import gcom.micromedicao.Rota;
import gcom.micromedicao.RotaAtualizacaoSeq;
import gcom.micromedicao.RoteiroEmpresa;
import gcom.micromedicao.ServicoTipoCelular;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.TelemetriaLog;
import gcom.micromedicao.TelemetriaMov;
import gcom.micromedicao.TelemetriaMovReg;
import gcom.micromedicao.TelemetriaRetMot;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.ConsumoAnormalidadeAcao;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoHistoricoAnterior;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.consumo.ResumoAnormalidadeConsumo;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroClasseMetrologica;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.micromedicao.hidrometro.HidrometroMotivoBaixa;
import gcom.micromedicao.hidrometro.HidrometroMotivoMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroMovimentacao;
import gcom.micromedicao.hidrometro.HidrometroMovimentado;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.hidrometro.HidrometroRelojoaria;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.micromedicao.hidrometro.HidrometroTipo;
import gcom.micromedicao.hidrometro.RetornoControleHidrometro;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidadeConsumo;
import gcom.micromedicao.leitura.LeituraAnormalidadeLeitura;
import gcom.micromedicao.leitura.LeituraFaixaFalsa;
import gcom.micromedicao.leitura.LeituraFiscalizacao;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoHistoricoAnterior;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.operacional.Bacia;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.DivisaoEsgoto;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.ProducaoAgua;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.SetorFonteCaptacao;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.SistemaEsgoto;
import gcom.operacional.SistemaEsgotoTratamentoTipo;
import gcom.operacional.TipoCaptacao;
import gcom.operacional.ZonaAbastecimento;
import gcom.operacional.ZonaPressao;
import gcom.operacional.abastecimento.AbastecimentoProgramacao;
import gcom.operacional.abastecimento.ManutencaoProgramacao;
import gcom.seguranca.Atributo;
import gcom.seguranca.AtributoGrupo;
import gcom.seguranca.ConsultaCdl;
import gcom.seguranca.FuncionalidadeAtributo;
import gcom.seguranca.SegurancaParametro;
import gcom.seguranca.acesso.ControleLiberacaoPermissaoEspecial;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.seguranca.acesso.FuncionalidadeCategoria;
import gcom.seguranca.acesso.FuncionalidadeDependencia;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.GrupoAcesso;
import gcom.seguranca.acesso.GrupoFuncionalidadeOperacao;
import gcom.seguranca.acesso.Modulo;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.OperacaoOrdemExibicao;
import gcom.seguranca.acesso.OperacaoTabela;
import gcom.seguranca.acesso.OperacaoTipo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.SolicitacaoAcesso;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoGrupo;
import gcom.seguranca.acesso.usuario.SolicitacaoAcessoSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAlteracao;
import gcom.seguranca.acesso.usuario.UsuarioFavorito;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioGrupoRestricao;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.seguranca.acesso.usuario.UsuarioSenhaHistorico;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.SgbdTabela;
import gcom.seguranca.transacao.SgbdTabelaColuna;
import gcom.seguranca.transacao.Tabela;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaAtualizacaoCadastralSituacao;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.seguranca.transacao.TabelaColunaAtualizacaoCadastral;
import gcom.seguranca.transacao.TabelaLinhaAlteracao;
import gcom.seguranca.transacao.TabelaLinhaColunaAlteracao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.EntityKey;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static SessionFactory sessionFactoryGerencial;
	private static SessionFactory sessionFactoryIntegracaoSAM;

	private static Configuration configuration;
	private static Configuration configurationGerencial;

	private static HashMap<Integer, Long> tempoSession = new HashMap<Integer, Long>();

	public static Logger log;

	static {
		log = Logger.getLogger("GSAN_ENTIDADES_CONSULTAS");
		log.debug(";ClasseChamadaN2;MetodoChamadaN2;ClasseChamadaN1;MetodoChamadaN1;NomeEntidade;QtdEntidadesConsultadas;TempoConsulta;OutrasEntidadesConsultadas");
	}

	public static void inicializarSessionFactory() {

		try {

			configurationGerencial = new Configuration();
			configurationGerencial.setProperty("hibernate.connection.datasource", "java:/PostgresGerencialDS");

			configurationGerencial.addClass(UnResumoArrecadacao.class).addClass(UnResumoArrecadacaoAguaEsgoto.class).addClass(UnResumoArrecadacaoCredito.class)
					.addClass(UnResumoArrecadacaoOutro.class).addClass(UnResumoFaturamento.class).addClass(UnResumoColetaEsgoto.class)
					.addClass(UnResumoConsumoAgua.class).addClass(UnResumoLigacaoEconomia.class).addClass(RgResumoLigacaoEconomia.class)
					.addClass(GEsferaPoder.class).addClass(GClienteTipo.class).addClass(GCategoria.class).addClass(GSubcategoria.class).addClass(GRegiao.class)
					.addClass(GMicrorregiao.class).addClass(GMunicipio.class).addClass(GBairro.class).addClass(GEpocaPagamento.class)
					.addClass(GDocumentoTipo.class).addClass(GLocalidade.class).addClass(GLocalidadePorte.class).addClass(GLigacaoAguaPerfil.class)
					.addClass(GLigacaoAguaSituacao.class).addClass(GLigacaoEsgotoPerfil.class).addClass(GLigacaoEsgotoSituacao.class).addClass(GRota.class)
					.addClass(GPagamentoSituacao.class).addClass(GGerenciaRegional.class).addClass(GUnidadeNegocio.class).addClass(GSetorComercial.class)
					.addClass(GQuadra.class).addClass(GImovelPerfil.class).addClass(GConsumoTipo.class).addClass(GLancamentoItem.class)
					.addClass(GLancamentoItemContabil.class).addClass(GCreditoOrigem.class).addClass(GFinanciamentoTipo.class)
					.addClass(UnResumoRegistroAtendimento.class).addClass(UnResumoInstalacaoHidrometro.class).addClass(GMeioSolicitacao.class)
					.addClass(GSolicitacaoTipo.class).addClass(GSolicitacaoTipoEspecificacao.class).addClass(UnResumoParcelamento.class)
					.addClass(UnResumoLeituraAnormalidade.class).addClass(UnResumoHidrometro.class).addClass(GMedicaoTipo.class)
					.addClass(GHidrometroMarca.class).addClass(GHidrometroCapacidade.class).addClass(GHidrometroDiametro.class)
					.addClass(GHidrometroLocalArmazenagem.class).addClass(GHidrometroSituacao.class).addClass(GHidrometroTipo.class)
					.addClass(UnResumoRefaturamento.class).addClass(GEmpresa.class).addClass(GLeituraSituacao.class).addClass(GArrecadacaoForma.class)
					.addClass(GArrecadador.class).addClass(UnResumoMeta.class).addClass(UnResumoMetasAcumulado.class).addClass(GDebitoTipo.class)
					.addClass(GCreditoTipo.class).addClass(GImpostoTipo.class).addClass(Indicador.class).addClass(GConsumoTarifa.class)
					.addClass(GHidrometroClasseMetrologica.class).addClass(GHidrometroMotivoBaixa.class).addClass(GFaturamentoGrupo.class)
					.addClass(GUnidadeOrganizacional.class).addClass(FaixaValor.class).addClass(UnResumoIndicadoresFaturamento.class)
					.addClass(UnResumoIndicadorLigacaoEconomia.class).addClass(UnResumoPendencia.class).addClass(UnResumoPendenciaSemQuadra.class)
					.addClass(UnResumoIndicadorDesempenhoMicromedicao.class).addClass(GDevolucaoSituacao.class).addClass(GAtendimentoMotivoEncerramento.class)
					.addClass(GDistritoOperacional.class).addClass(UnResumoIndicadoresCobranca.class)
					.addClass(UnResumoIndicadorDesempenhoMicromedicaoRef2010.class).addClass(UnResumoArrecadacaoPorAno.class)
					.addClass(UnResumoRegistroAtendimentoPorAno.class).addClass(UnResumoInstalacaoHidrometroPorAno.class)
					.addClass(UnResumoParcelamentoPorAno.class);

			sessionFactoryGerencial = configurationGerencial.buildSessionFactory();


			configuration = new Configuration()
					// **********************************************/
					// CLASSES DO PACOTE gcom.atendimentopublico //
					// ********************************************//
					.addClass(CorteTipo.class)
					.addClass(EmissaoOrdemCobrancaTipo.class)
					.addClass(LigacaoAgua.class)
					.addClass(LigacaoAguaDiametro.class)
					.addClass(LigacaoAguaMaterial.class)
					.addClass(LigacaoAguaPerfil.class)
					.addClass(LigacaoAguaSituacao.class)
					.addClass(SupressaoTipo.class)
					.addClass(LigacaoEsgoto.class)
					.addClass(LigacaoEsgotoDiametro.class)
					.addClass(LigacaoEsgotoMaterial.class)
					.addClass(LigacaoEsgotoPerfil.class)
					.addClass(LigacaoEsgotoSituacao.class)
					.addClass(RegistroAtendimento.class)
					.addClass(AgenciaReguladoraMotReclamacao.class)
					.addClass(AgenciaReguladoraMotRetorno.class)
					.addClass(AtendimentoMotivoEncerramento.class)
					.addClass(AtendimentoRelacaoTipo.class)
					.addClass(LocalOcorrencia.class)
					.addClass(MeioSolicitacao.class)
					.addClass(RaDadosAgenciaReguladora.class)
					.addClass(RaDadosAgenciaReguladoraFone.class)
					.addClass(RaEnderecoDescritivo.class)
					.addClass(RaMotivoReativacao.class)
					.addClass(RegistroAtendimentoSolicitante.class)
					.addClass(RegistroAtendimentoUnidade.class)
					.addClass(SolicitacaoTipo.class)
					.addClass(SolicitacaoTipoEspecificacao.class)
					.addClass(SolicitacaoTipoGrupo.class)
					.addClass(SolicitanteFone.class)
					.addClass(Tramite.class)
					.addClass(EspecificacaoImovelSituacao.class)
					.addClass(EspecificacaoImovSitCriterio.class)
					.addClass(EspecificacaoTipoValidacao.class)
					.addClass(RaEncerramentoComando.class)
					.addClass(RaEncerramentoComandoEspecificacoes.class)
					.addClass(RegistroAtendimentoAnexo.class)
					.addClass(SolicitacaoDocumentoObrigatorio.class)
					.addClass(LocalidadeEspecificacaoUnidade.class)
					.addClass(RegistroAtendimentoConta.class)
					.addClass(RegistroAtendimentoPagamentoDuplicidade.class)
					.addClass(OrdemServicoMovimento.class)
					.addClass(OrdemServicoMovimentoHistorico.class)
					.addClass(OrdemServico.class)
					.addClass(ServicoTipo.class)
					.addClass(SupressaoMotivo.class)
					.addClass(Atividade.class)
					.addClass(Equipe.class)
					.addClass(EquipamentosEspeciais.class)
					.addClass(EquipeComponentes.class)
					.addClass(EspecificacaoServicoTipo.class)
					.addClass(FiscalizacaoColetiva.class)
					.addClass(Material.class)
					.addClass(MaterialUnidade.class)
					.addClass(OrdemServicoAtividade.class)
					.addClass(OrdemServicoProgramacao.class)
					.addClass(OrdemServicoUnidade.class)
					.addClass(OsAtividadeMaterialExecucao.class)
					.addClass(OsAtividadePeriodoExecucao.class)
					.addClass(OsExecucaoEquipe.class)
					.addClass(OsExecucaoEquipeComponentes.class)
					.addClass(OsProgramNaoEncerMotivo.class)
					.addClass(OsReferidaRetornoTipo.class)
					.addClass(ProgramacaoRoteiro.class)
					.addClass(ServicoCobrancaValor.class)
					.addClass(ServicoNaoCobrancaMotivo.class)
					.addClass(ServicoPerfilTipo.class)
					.addClass(ServicoTipoAtividade.class)
					.addClass(ServicoTipoGrupo.class)
					.addClass(ServicoTipoMaterial.class)
					.addClass(ServicoTipoOperacao.class)
					.addClass(ServicoTipoPrioridade.class)
					.addClass(ServicoTipoReferencia.class)
					.addClass(ServicoTipoSubgrupo.class)
					.addClass(LocalidadeSolicTipoGrupo.class)
					.addClass(FiscalizacaoSituacao.class)
					.addClass(FiscalizacaoSituacaoAgua.class)
					.addClass(FiscalizacaoSituacaoEsgoto.class)
					.addClass(FiscalizacaoSituacaoHidrometroCapacidade.class)
					.addClass(FiscalizacaoSituacaoServicoACobrar.class)
					.addClass(OrdemServicoPavimento.class)
					.addClass(BoletimOsConcluida.class)
					.addClass(DataFiscalizacaoOsSeletiva.class)
					.addClass(LigacaoOrigem.class)
					.addClass(VisualizacaoRegistroAtendimentoUrgencia.class)
					.addClass(OrdemServicoFiscSit.class)
					.addClass(MotivoRejeicao.class)
					.addClass(ServicoTipoBoletim.class)
					.addClass(OrdemServicoBoletim.class)
					.addClass(ContaBraile.class)
					.addClass(EspecificacaoPavimentacaoServicoTipo.class)
					.addClass(EspecificacaoUnidadeCobranca.class)
					.addClass(RAReiteracao.class)
					.addClass(RAReiteracaoFone.class)
					.addClass(OSProgramacaoCalibragem.class)
					.addClass(OSPriorizacaoTipo.class)
					.addClass(EquipeEquipamentosEspeciais.class)
					.addClass(ArquivoTextoAcompanhamentoServico.class)
					.addClass(OSAtividadeExecucaoAcompanhamentoServico.class)
					.addClass(OSAtividadeMaterialProgramacaoAcompanhamentoServico.class)
					.addClass(OrdemServicoSituacao.class)
					.addClass(OSAtividadeProgramacaoAcompanhamentoServico.class)
					.addClass(OSProgramacaoAcompanhamentoServico.class)
					.addClass(ServicoTipoMotivoEncerramento.class)
					.addClass(OrdemServicoFoto.class)
					.addClass(FotoSituacaoOrdemServico.class)
					.addClass(MensagemAcompanhamentoServico.class)
					.addClass(AgenciaReguladora.class)
					.addClass(AgenciaReguladoraMunicipio.class)

					// *************************************//
					// CLASSES DO PACOTE gcom.cadastro //
					// *************************************//
					.addClass(VersaoMobile.class)
					.addClass(CpfTipo.class)
					.addClass(Cliente.class)
					.addClass(ClienteHistorico.class)
					.addClass(ClienteEndereco.class)
					.addClass(ImovelCadastroOcorrencia.class)
					.addClass(ImovelEloAnormalidade.class)
					.addClass(ClienteFone.class)
					.addClass(ClienteImovel.class)
					.addClass(ClienteRelacaoTipo.class)
					.addClass(ClienteImovelEconomia.class)
					.addClass(ClienteImovelFimRelacaoMotivo.class)
					.addClass(ClienteTipo.class)
					.addClass(FoneTipo.class)
					.addClass(ClienteConta.class)
					.addClass(ClienteContaHistorico.class)
					.addClass(OrgaoExpedidorRg.class)
					.addClass(PessoaSexo.class)
					.addClass(Profissao.class)
					.addClass(RamoAtividade.class)
					.addClass(EsferaPoder.class)
					.addClass(ClienteGuiaPagamento.class)
					.addClass(ClienteGuiaPagamentoHistorico.class)
					.addClass(SituacaoAtualizacaoCadastral.class)
					.addClass(ClienteAtualizacaoCadastral.class)
					.addClass(ClienteFoneAtualizacaoCadastral.class)
					.addClass(LocalidadeDadosCensitario.class)
					.addClass(MunicipioDadosCensitario.class)
					.addClass(IbgeSetorCensitarioDado.class)
					.addClass(FonteDadosCensitario.class)
					.addClass(IbgeSetorCensitario.class)
					.addClass(Empresa.class)
					.addClass(EmpresaCobranca.class)
					.addClass(EmpresaCobrancaFaixa.class)
					.addClass(LogradouroCep.class)
					.addClass(Cep.class)
					.addClass(CepTipo.class)
					.addClass(EnderecoReferencia.class)
					.addClass(EnderecoTipo.class)
					.addClass(Logradouro.class)
					.addClass(LogradouroBairro.class)
					.addClass(LogradouroTipo.class)
					.addClass(LogradouroTitulo.class)
					.addClass(Funcionario.class)
					.addClass(FuncionarioCargo.class)
					.addClass(Bairro.class)
					.addClass(Microrregiao.class)
					.addClass(Municipio.class)
					.addClass(MunicipioFeriado.class)
					.addClass(Regiao.class)
					.addClass(RegiaoDesenvolvimento.class)
					.addClass(UnidadeFederacao.class)
					.addClass(BairroArea.class)
					.addClass(AreaConstruidaFaixa.class)
					.addClass(CadastroOcorrencia.class)
					.addClass(CategoriaTipo.class)
					.addClass(Categoria.class)
					.addClass(Despejo.class)
					.addClass(EloAnormalidade.class)
					.addClass(FonteAbastecimento.class)
					.addClass(Imovel.class)
					.addClass(ImovelHistorico.class)
					.addClass(ImovelCobrancaSituacao.class)
					.addClass(ImovelEconomia.class)
					.addClass(ImovelEnderecoAnterior.class)
					.addClass(ImovelPerfil.class)
					.addClass(ImovelSubcategoria.class)
					.addClass(ImovelRamoAtividade.class)
					.addClass(PavimentoRua.class)
					.addClass(PavimentoCalcada.class)
					.addClass(PiscinaVolumeFaixa.class)
					.addClass(PocoTipo.class)
					.addClass(ReservatorioVolumeFaixa.class)
					.addClass(Subcategoria.class)
					.addClass(ImovelContaEnvio.class)
					.addClass(ImovelDoacao.class)
					.addClass(EntidadeBeneficente.class)
					.addClass(ImovelTipoHabitacao.class)
					.addClass(ImovelTipoPropriedade.class)
					.addClass(ImovelTipoConstrucao.class)
					.addClass(ImovelTipoCobertura.class)
					.addClass(ImovelAtualizacaoCadastral.class)
					.addClass(ImovelSubcategoriaAtualizacaoCadastral.class)
					.addClass(ImovelProgramaEspecial.class)
					.addClass(ImovelSuprimido.class)
					.addClass(ImovelInscricaoAlterada.class)
					.addClass(ImovelRamoAtividadeAtualizacaoCadastral.class)
					.addClass(ImagemRetorno.class)
					.addClass(ImovelImagem.class)
					.addClass(GerenciaRegional.class)
					.addClass(Localidade.class)
					.addClass(LocalidadePorte.class)
					.addClass(LocalidadeClasse.class)
					.addClass(Quadra.class)
					.addClass(QuadraPerfil.class)
					.addClass(SetorComercial.class)
					.addClass(Zeis.class)
					.addClass(AreaTipo.class)
					.addClass(UnidadeNegocio.class)
					.addClass(QuadraFace.class)
					.addClass(NacionalFeriado.class)
					.addClass(SistemaParametro.class)
					.addClass(SistemaAlteracaoHistorico.class)
					.addClass(RendaTipo.class)
					.addClass(TarifaSocialCartaoTipo.class)
					.addClass(TarifaSocialExclusaoMotivo.class)
					.addClass(TarifaSocialDadoEconomia.class)
					.addClass(TarifaSocialRevisaoMotivo.class)
					.addClass(UnidadeOrganizacional.class)
					.addClass(UnidadeTipo.class)
					.addClass(ArquivoTextoAtualizacaoCadastral.class)
					.addClass(EmpresaContratoCadastro.class)
					.addClass(EmpresaContratoCadastroAtributo.class)
					.addClass(UnidadeOrganizacionalMunicipio.class)
					.addClass(AtualizacaoCadastralSimplificado.class)
					.addClass(AtualizacaoCadastralSimplificadoCritica.class)
					.addClass(AtualizacaoCadastralSimplificadoBinario.class)
					.addClass(AtualizacaoCadastralSimplificadoCriticaTipo.class)
					.addClass(AtualizacaoCadastralSimplificadoLinha.class)
					.addClass(Projeto.class)
					.addClass(EmpresaContratoCobranca.class)
					.addClass(DescricaoGenerica.class)
					.addClass(ImovelControleAtualizacaoCadastral.class)
					.addClass(ImovelRetorno.class)
					.addClass(ImovelSubcategoriaRetorno.class)
					.addClass(ImovelRamoAtividadeRetorno.class)
					.addClass(ClienteFoneRetorno.class)
					.addClass(ClienteRetorno.class)
					.addClass(ClienteEnderecoRetorno.class)
					.addClass(ClienteImovelRetorno.class)

					// *************************************//
					// CLASSES DO PACOTE gcom.cobranca //
					// *************************************//
					.addClass(CobrancaGrupo.class)
					.addClass(CobrancaSituacao.class)
					.addClass(CobrancaSituacaoHistorico.class)
					.addClass(CobrancaSituacaoMotivo.class)
					.addClass(CobrancaSituacaoTipo.class)
					.addClass(ParcelamentoGrupo.class)
					.addClass(CobrancaForma.class)
					.addClass(IndicesAcrescimosImpontualidade.class)
					.addClass(ResumoCobrancaSituacaoEspecial.class)
					.addClass(CobrancaAcaoSituacao.class)
					.addClass(CobrancaDebitoSituacao.class)
					.addClass(ResumoCobrancaAcao.class)
					.addClass(ParcelamentoFaixaValor.class)
					.addClass(NegativacaoComando.class)
					.addClass(NegativacaoCriterio.class)
					.addClass(NegativacaoCriterioClienteTipo.class)
					.addClass(NegativacaoCriterioCpfTipo.class)
					.addClass(NegativacaoCriterioImovelPerfil.class)
					.addClass(NegativacaoCriterioSubcategoria.class)
					.addClass(NegativacaoImoveis.class)
					.addClass(Negativador.class)
					.addClass(NegativadorContrato.class)
					.addClass(NegativadorExclusaoMotivo.class)
					.addClass(NegativadorMovimento.class)
					.addClass(NegativadorMovimentoReg.class)
					.addClass(NegativadorMovimentoRegItem.class)
					.addClass(NegativadorMovimentoRegRetMot.class)
					.addClass(NegativadorRegistroTipo.class)
					.addClass(NegativadorRetornoMotivo.class)
					.addClass(NegativCritCobrGrupo.class)
					.addClass(NegativCritElo.class)
					.addClass(NegativCritGerReg.class)
					.addClass(NegativCritUndNeg.class)
					.addClass(ResumoNegativacao.class)
					.addClass(NegativadorResultadoSimulacao.class)
					.addClass(UnidadeOrganizacionalTestemunha.class)
					.addClass(CriterioSituacaoCobranca.class)
					.addClass(CriterioSituacaoLigacaoAgua.class)
					.addClass(CriterioSituacaoLigacaoEsgoto.class)
					.addClass(NegativacaoCriterioLigacaoAgua.class)
					.addClass(NegativacaoCriterioLigacaoEsgoto.class)
					.addClass(EmpresaCobrancaConta.class)
					.addClass(EmpresaCobrancaContaPagamentos.class)
					.addClass(ComandoEmpresaCobrancaConta.class)
					.addClass(ComandoEmpresaCobrancaContaExtensao.class)
					.addClass(CobrancaSituacaoComando.class)
					.addClass(NegativadorMovimentoRegParcelamento.class)
					.addClass(ParcelamentoPagamentoCartaoCredito.class)
					.addClass(DocumentosReceberFaixaDiasVencidos.class)
					.addClass(NegativCritNegRetMot.class)
					.addClass(ParcDesctoInativVista.class)
					.addClass(CobrancaAcaoOrdemServicoNaoAceitas.class)
					.addClass(UnidadeRepavimentadoraCustoPavimentoRua.class)
					.addClass(UnidadeRepavimentadoraCustoPavimentoCalcada.class)
					.addClass(CobrancaBoletimMedicao.class)
					.addClass(CobrancaBoletimDesconto.class)
					.addClass(CobrancaBoletimExecutado.class)
					.addClass(CobrancaBoletimSucesso.class)
					.addClass(ComandoEmpresaCobrancaContaGerencia.class)
					.addClass(ComandoEmpresaCobrancaContaImovelPerfil.class)
					.addClass(ComandoEmpresaCobrancaContaUnidadeNegocio.class)
					.addClass(CmdEmpresaCobrancaContaLigacaoAguaSituacao.class)
					.addClass(MotivoNaoAceitacaoEncerramentoOS.class)
					.addClass(ComandoOrdemSeletiva.class)
					.addClass(LigacaoSitComandoOSS.class)
					.addClass(AnormalidadeComandoOSS.class)
					.addClass(CapacidHidrComandoOSS.class)

					// *************************************//
					// CLASSES DO PACOTE gcom.cobranca.contratoparcelamento //
					// *************************************//
					.addClass(ContratoParcelamentoRD.class)
					.addClass(QuantidadePrestacoes.class)
					.addClass(TipoRelacao.class)
					.addClass(ContratoParcelamento.class)
					.addClass(ContratoParcelamentoCliente.class)
					.addClass(PrestacaoContratoParcelamento.class)
					.addClass(ContratoParcelamentoItem.class)
					.addClass(PrestacaoItemContratoParcelamento.class)

					// *************************************//
					// CLASSES DO PACOTE gcom.faturamento //
					// *************************************//
					.addClass(QualidadeAgua.class)
					.addClass(ImpostoTipo.class)
					.addClass(ImpostoTipoAliquota.class)
					.addClass(FaturamentoGrupo.class)
					.addClass(FaturamentoSituacaoTipo.class)
					.addClass(FaturamentoAtividade.class)
					.addClass(FaturamentoAtividadeCronograma.class)
					.addClass(FaturamentoGrupoCronogramaMensal.class)
					.addClass(FaturamentoImediatoAjuste.class)
					.addClass(FaturamentoSituacaoMotivo.class)
					.addClass(FaturamentoSituacaoHistorico.class)
					.addClass(FaturamentoTipo.class)
					.addClass(FaturamentoAtivCronRota.class)
					.addClass(FaturamentoDados.class)
					.addClass(ResumoFaturamentoSimulacao.class)
					.addClass(ResumoFaturamentoSimulacaoDebito.class)
					.addClass(ResumoFaturamentoSimulacaoCredito.class)
					.addClass(VencimentoAlternativo.class)
					.addClass(ResumoFaturamentoSituacaoEspecial.class)
					.addClass(FaturamentoContabilParametros.class)
					.addClass(GuiaPagamentoGeral.class)
					.addClass(DocumentoNaoEntregue.class)
					.addClass(HistogramaAguaEconomia.class)
					.addClass(HistogramaAguaLigacao.class)
					.addClass(HistogramaEsgotoEconomia.class)
					.addClass(HistogramaEsgotoLigacao.class)
					.addClass(QualidadeAguaPadrao.class)
					.addClass(GuiaPagamentoItem.class)
					.addClass(FaturamentoSituacaoComando.class)
					.addClass(TarifaTipoCalculo.class)
					.addClass(GuiaPagamentoParcelamentoCartao.class)
					.addClass(MotivoInterferenciaTipo.class)
					.addClass(ExtratoQuitacao.class)
					.addClass(ExtratoQuitacaoItem.class)
					.addClass(Prescricao.class)
					.addClass(ConsumoMinimoParametro.class)
					.addClass(ContaCategoriaConsumoFaixa.class)
					.addClass(Conta.class)
					.addClass(ContaCategoria.class)
					.addClass(MotivoNaoEntregaDocumento.class)
					.addClass(Refaturamento.class)
					.addClass(Fatura.class)
					.addClass(FaturaItem.class)
					.addClass(ContaHistorico.class)
					.addClass(ContaImpostosDeduzidos.class)
					.addClass(ContaMotivoCancelamento.class)
					.addClass(ContaMotivoInclusao.class)
					.addClass(ContaMotivoRetificacao.class)
					.addClass(ContaMotivoRevisao.class)
					.addClass(ContaGeral.class)
					.addClass(ContaImpressao.class)
					.addClass(ContaCategoriaConsumoFaixaHistorico.class)
					.addClass(ContaCategoriaHistorico.class)
					.addClass(ContaImpostosDeduzidosHistorico.class)
					.addClass(ContaTipo.class)
					.addClass(ContaMotivoRetificacaoColuna.class)
					.addClass(DebitoCobrado.class)
					.addClass(DebitoTipo.class)
					.addClass(DebitoACobrar.class)
					.addClass(DebitoACobrarCategoria.class)
					.addClass(DebitoCobradoHistorico.class)
					.addClass(DebitoCobradoCategoria.class)
					.addClass(DebitoACobrarHistorico.class)
					.addClass(DebitoCreditoSituacao.class)
					.addClass(ContaMensagem.class)
					.addClass(DebitoACobrarGeral.class)
					.addClass(DebitoTipoVigencia.class)
					.addClass(CreditoRealizado.class)
					.addClass(CreditoARealizar.class)
					.addClass(CreditoARealizarCategoria.class)
					.addClass(CreditoRealizadoHistorico.class)
					.addClass(CreditoRealizadoCategoria.class)
					.addClass(CreditoTipo.class)
					.addClass(CreditoARealizarHistorico.class)
					.addClass(CreditoOrigem.class)
					.addClass(CreditoARealizarGeral.class)
					.addClass(ConsumoTarifa.class)
					.addClass(ConsumoTarifaVigencia.class)
					.addClass(ConsumoTarifaCategoria.class)
					.addClass(ConsumoTarifaFaixa.class)
					.addClass(DebitoFaixaValore.class)
					.addClass(AutoInfracaoSituacao.class)
					.addClass(AutosInfracao.class)
					.addClass(AutosInfracaoDebitoACobrar.class)
					.addClass(FaturaItemHistorico.class)
					.addClass(HistogramaAguaEconomiaSemQuadra.class)
					.addClass(HistogramaAguaLigacaoSemQuadra.class)
					.addClass(HistogramaEsgotoLigacaoSemQuadra.class)
					.addClass(ReceitasAFaturarResumo.class)
					.addClass(ContaImpressaoTermicaQtde.class)
					.addClass(LancamentoAgenciaReguladora.class)

					// *************************************//
					// CLASSES DO PACOTE gcom.micromedicao //
					// *************************************//
					.addClass(Rota.class)
					.addClass(RateioTipo.class)
					.addClass(ImovelTestesMedicaoConsumo.class)
					.addClass(ArquivoTextoRetornoIS.class)
					.addClass(MovimentoArquivoTextoRetornoIS.class)
					.addClass(HidrometroCapacidade.class)
					.addClass(Hidrometro.class)
					.addClass(HidrometroMotivoBaixa.class)
					.addClass(HidrometroClasseMetrologica.class)
					.addClass(HidrometroMarca.class)
					.addClass(HidrometroMovimentacao.class)
					.addClass(HidrometroMotivoMovimentacao.class)
					.addClass(HidrometroLocalArmazenagem.class)
					.addClass(HidrometroSituacao.class)
					.addClass(HidrometroDiametro.class)
					.addClass(HidrometroRelojoaria.class)
					.addClass(HidrometroInstalacaoHistorico.class)
					.addClass(HidrometroLocalInstalacao.class)
					.addClass(HidrometroTipo.class)
					.addClass(HidrometroProtecao.class)
					.addClass(HidrometroMovimentado.class)
					.addClass(Leiturista.class)
					.addClass(ArquivoTextoRoteiroEmpresa.class)
					.addClass(RoteiroEmpresa.class)
					.addClass(ServicoTipoCelular.class)
					.addClass(MovimentoRoteiroEmpresa.class)
					.addClass(ItemServico.class)
					.addClass(ContratoEmpresaServico.class)
					.addClass(ItemServicoContrato.class)
					.addClass(RetornoControleHidrometro.class)
					.addClass(TelemetriaLog.class)
					.addClass(TelemetriaMov.class)
					.addClass(TelemetriaMovReg.class)
					.addClass(TelemetriaRetMot.class)
					.addClass(ContratoEmpresaAditivo.class)
					.addClass(LeituraTipo.class)
					.addClass(LeituraSituacao.class)
					.addClass(LeituraFaixaFalsa.class)
					.addClass(LeituraAnormalidadeLeitura.class)
					.addClass(LeituraAnormalidade.class)
					.addClass(LeituraFiscalizacao.class)
					.addClass(LeituraAnormalidadeConsumo.class)
					.addClass(MedicaoHistorico.class)
					.addClass(MedicaoTipo.class)
					.addClass(ConsumoHistorico.class)
					.addClass(ConsumoTipo.class)
					.addClass(ConsumoAnormalidade.class)
					.addClass(LigacaoTipo.class)
					.addClass(ResumoAnormalidadeConsumo.class)
					.addClass(ResumoAnormalidadeLeitura.class)
					.addClass(ConsumoHistoricoAnterior.class)
					.addClass(MedicaoHistoricoAnterior.class)
					.addClass(SituacaoTransmissaoLeitura.class)
					.addClass(ConsumoMinimoArea.class)
					.addClass(ConsumoAnormalidadeAcao.class)
					.addClass(RotaAtualizacaoSeq.class)
					.addClass(ReleituraMobile.class)

					// ************************************//
					// CLASSES DO PACOTE gcom.financeiro //
					// ************************************//
					.addClass(LancamentoContabil.class)
					.addClass(LancamentoResumo.class)
					.addClass(LancamentoResumoValorTipo.class)
					.addClass(LancamentoResumoConta.class)
					.addClass(LancamentoResumoContaHistorico.class)
					.addClass(FinanciamentoTipo.class)
					.addClass(LancamentoContabilItem.class)
					.addClass(ContaContabil.class)
					.addClass(LancamentoOrigem.class)
					.addClass(ResumoFaturamento.class)
					.addClass(LancamentoItem.class)
					.addClass(LancamentoItemContabil.class)
					.addClass(LancamentoTipoItem.class)
					.addClass(LancamentoTipo.class)
					.addClass(DevedoresDuvidososContabilParametro.class)
					.addClass(ContaAReceberContabil.class)
					.addClass(ValorVolumesConsumidosNaoFaturado.class)
					.addClass(DocumentosAReceberResumo.class)
					.addClass(ResumoReceita.class)
					.addClass(FaixaDocumentosAReceber.class)
					.addClass(DocumentosAReceberFaixaResumo.class)
					
					// ************************************//
					// CLASSES DO PACOTE gcom.arrecadacao //
					// ************************************//
					.addClass(ResumoArrecadacao.class)
					.addClass(Banco.class)
					.addClass(Agencia.class)
					.addClass(Pagamento.class)
					.addClass(PagamentoSituacao.class)
					.addClass(GuiaPagamento.class)
					.addClass(GuiaPagamentoHistorico.class)
					.addClass(GuiaPagamentoCategoriaHistorico.class)
					.addClass(PagamentoCartaoDebito.class)
					.addClass(PagamentoCartaoDebitoItem.class)
					.addClass(SequenciaCartao.class)
					.addClass(DebitoAutomatico.class)
					.addClass(DebitoAutomaticoRetornoCodigo.class)
					.addClass(DebitoAutomaticoMovimento.class)
					.addClass(GuiaPagamentoCategoria.class)
					.addClass(MetasArrecadacao.class)
					.addClass(DevolucaoHistorico.class)
					.addClass(DevolucaoDadosDiarios.class)
					.addClass(DevolucaoDadosDiariosAuxiliar.class)
					.addClass(ArrecadacaoDadosDiariosAuxiliar.class)
					.addClass(BoletimInformacoesGerenciais.class)

					// *************************************//
					// CLASSES DO PACOTE gcom.operacional //
					// *************************************//
					.addClass(Bacia.class)
					.addClass(DistritoOperacional.class)
					.addClass(DivisaoEsgoto.class)
					.addClass(SistemaAbastecimento.class)
					.addClass(SistemaEsgoto.class)
					.addClass(SistemaEsgotoTratamentoTipo.class)
					.addClass(AbastecimentoProgramacao.class)
					.addClass(ManutencaoProgramacao.class)
					.addClass(SetorAbastecimento.class)
					.addClass(ZonaAbastecimento.class)
					.addClass(ZonaPressao.class)
					.addClass(ProducaoAgua.class)
				
					// ************************************//
					// CLASSES DO PACOTE gcom.seguranca //
					// ************************************//
					.addClass(AlteracaoTipo.class)
					.addClass(UsuarioTipo.class)
					.addClass(TabelaLinhaAlteracao.class)
					.addClass(TabelaLinhaColunaAlteracao.class)
					.addClass(TabelaColuna.class)
					.addClass(Tabela.class)
					.addClass(UsuarioAcao.class)
					.addClass(UsuarioFavorito.class)
					.addClass(GrupoAcesso.class)
					.addClass(UsuarioSenhaHistorico.class)
					.addClass(SgbdTabela.class).addClass(SgbdTabelaColuna.class).addClass(UsuarioSituacao.class).addClass(UsuarioPermissaoEspecial.class)
					.addClass(UsuarioAlteracao.class).addClass(UsuarioGrupoRestricao.class).addClass(UsuarioGrupo.class).addClass(UsuarioAbrangencia.class)
					.addClass(Usuario.class).addClass(ResolucaoDiretoria.class).addClass(CreditoRealizadoCategoriaHistorico.class)
					.addClass(CreditoARealizarCategoriaHistorico.class).addClass(DebitoCobradoCategoriaHistorico.class)
					.addClass(DebitoACobrarCategoriaHistorico.class).addClass(PermissaoEspecial.class).addClass(AvisoDeducoes.class)
					.addClass(AvisoBancario.class).addClass(AvisoAcerto.class).addClass(ArrecadadorMovimentoItem.class).addClass(ArrecadadorMovimento.class)
					.addClass(ArrecadadorContratoTarifa.class).addClass(ParcelamentoTipo.class).addClass(ParcelamentoSituacao.class)
					.addClass(ParcelamentoQuantidadeReparcelamento.class).addClass(ParcelamentoQuantidadePrestacao.class).addClass(ParcelamentoPerfil.class)
					.addClass(ParcelamentoItem.class).addClass(ParcelamentoDescontoInatividade.class).addClass(ParcelamentoDescontoAntiguidade.class)
					.addClass(Parcelamento.class).addClass(DocumentoTipo.class).addClass(DocumentoEmissaoForma.class).addClass(DevolucaoSituacao.class)
					.addClass(Devolucao.class).addClass(DeducaoTipo.class).addClass(GuiaDevolucao.class).addClass(GrupoFuncionalidadeOperacao.class)
					.addClass(Grupo.class).addClass(FuncionalidadeDependencia.class).addClass(Funcionalidade.class).addClass(ParcelamentoMotivoDesfazer.class)
					.addClass(PagamentoHistorico.class).addClass(OperacaoEfetuada.class).addClass(Operacao.class).addClass(OperacaoTipo.class)
					.addClass(OperacaoTabela.class).addClass(RegistroCodigo.class).addClass(ArrecadadorContrato.class).addClass(Arrecadador.class)
					.addClass(ArrecadacaoForma.class).addClass(CobrancaAcao.class).addClass(RotaAcaoCriterio.class)
					.addClass(CobrancaAcaoAtividadeComando.class).addClass(CobrancaCriterioLinha.class).addClass(CobrancaCriterio.class)
					.addClass(CobrancaAtividadeComandoRota.class).addClass(CobrancaAtividade.class).addClass(CobrancaAcaoCronograma.class)
					.addClass(CobrancaAcaoAtividadeCronograma.class).addClass(Modulo.class).addClass(ContratoDemanda.class)
					.addClass(ContratoMotivoCancelamento.class).addClass(CobrancaGrupoCronogramaMes.class).addClass(CobrancaDocumentoItem.class)
					.addClass(CobrancaDocumentoItemHistorico.class).addClass(CobrancaDocumento.class).addClass(CobrancaDocumentoHistorico.class)
					.addClass(ImovelSituacaoTipo.class).addClass(ImovelSituacao.class).addClass(ContaBancaria.class).addClass(ArrecadacaoDadosDiarios.class)
					.addClass(ResumoPendencia.class).addClass(RecebimentoTipo.class).addClass(ArrecadacaoContabilParametros.class).addClass(MotivoCorte.class)
					.addClass(UnidadeProcessamento.class).addClass(ProcessoIniciado.class).addClass(ProcessoSituacao.class)
					.addClass(ProcessoFuncionalidade.class).addClass(FuncionalidadeIniciada.class).addClass(FuncionalidadeSituacao.class)
					.addClass(Processo.class).addClass(ProcessoTipo.class).addClass(UnidadeIniciada.class).addClass(RelatorioGerado.class)
					.addClass(Relatorio.class).addClass(UnidadeSituacao.class).addClass(RamalLocalInstalacao.class)
					.addClass(ParametrosDevedoresDuvidosos.class).addClass(ParametrosDevedoresDuvidososItem.class).addClass(ResumoDevedoresDuvidosos.class)
					.addClass(DbVersaoBase.class).addClass(EnvioEmail.class).addClass(ResumoCobrancaAcaoEventual.class).addClass(ConsumoFaixaLigacao.class)
					.addClass(ConsumoFaixaCategoria.class).addClass(ContaRevisaoFaixaValor.class).addClass(OperacaoOrdemExibicao.class)
					.addClass(LigacaoEsgotoDestinoDejetos.class).addClass(LigacaoEsgotoCaixaInspecao.class).addClass(LigacaoEsgotoDestinoAguasPluviais.class)
					.addClass(LigacaoEsgotoEsgotamento.class).addClass(LigacaoAguaSituacaoConsumoTipo.class).addClass(LigacaoEsgotoSituacaoConsumoTipo.class)
					.addClass(FonteCaptacao.class).addClass(SetorFonteCaptacao.class).addClass(FuncionalidadeCategoria.class)
					.addClass(TabelaAtualizacaoCadastral.class).addClass(TabelaAtualizacaoCadastralSituacao.class)
					.addClass(TabelaColunaAtualizacaoCadastral.class).addClass(CicloMeta.class).addClass(Atributo.class).addClass(AtributoGrupo.class)
					.addClass(FuncionalidadeAtributo.class).addClass(CicloMetaGrupo.class).addClass(VwImovelPrincipalCategoria.class)
					.addClass(MovimentoContaImpostoDeduzido.class).addClass(MovimentoContaCategoriaConsumoFaixa.class)
					.addClass(MovimentoContaPrefaturadaCategoria.class).addClass(MovimentoContaPrefaturada.class).addClass(MotivoNaoGeracaoDocCobranca.class)
					.addClass(ImovelNaoGerado.class).addClass(TipoCaptacao.class).addClass(CobrancaDocumentoImpressao.class)
					.addClass(CobrancaDocumentoControleGeracao.class).addClass(GrauDificuldadeExecucao.class).addClass(GrauRiscoSegurancaFisica.class)
					.addClass(NivelPressao.class).addClass(GrauIntermitencia.class).addClass(CondicaoAbastecimentoAgua.class)
					.addClass(ArquivoTextoRoteiroEmpresaDivisao.class).addClass(MovimentoCartaoRejeita.class).addClass(EmailClienteAlterado.class)
					.addClass(CobrancaAcaoAtividadeComandoFiscalizacaoSituacao.class).addClass(CobrancaDocumentoFisc.class)
					.addClass(ControleLiberacaoPermissaoEspecial.class).addClass(ConsultaCdl.class).addClass(SolicitacaoAcessoSituacao.class)
					.addClass(SolicitacaoAcessoGrupo.class).addClass(SolicitacaoAcesso.class).addClass(NegativacaoCriterioSituacaoEspecialCobranca.class)
					.addClass(NegativacaoCriterioSituacaoCobranca.class).addClass(TarifaSocialCarta.class).addClass(TarifaSocialCartaDebito.class)
					.addClass(TarifaSocialComandoCarta.class).addClass(TarifaSocialMotivoCarta.class)
					.addClass(SegurancaParametro.class)

					// ************************************//
					// CLASSES DO PACOTE gcom.atendimentopublico.portal //
					// ************************************//
					.addClass(QuestionarioSatisfacaoCliente.class)

					.addClass(ServicoTerceiroAcompanhamentoServico.class)
					.addClass(ImovelTipoOcupante.class)
					.addClass(ImovelTipoOcupanteQuantidade.class)
					.addClass(ImovelTipoOcupanteQuantidadeAtualizacaoCadastral.class)
					.addClass(ImovelTipoOcupanteQuantidadeRetorno.class)
					
					.addClass(ParcelamentoFaixaDesconto.class)
			;

			configuration.setInterceptor(Interceptador.getInstancia());
			sessionFactory = configuration.buildSessionFactory();

		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException("Hibernate - Erro ao criar a SessionFactory");
		}

	}

	public static Session getSession() {
		Session retorno = null;

		try {
			retorno = sessionFactory.openSession();
			tempoSession.put(retorno.hashCode(), System.currentTimeMillis());
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException("Hibernate - Erro ao criar a Session");
		}

		return retorno;
	}

	public static StatelessSession getStatelessSession() {
		StatelessSession retorno = null;

		try {
			retorno = sessionFactory.openStatelessSession();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException("Hibernate - Erro ao criar a Session");
		}

		return retorno;
	}

	public static StatelessSession getStatelessSessionGerencial() {
		StatelessSession retorno = null;

		try {
			retorno = sessionFactoryGerencial.openStatelessSession();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException("Hibernate - Erro ao criar a Session");
		}

		return retorno;
	}

	public static Session getSessionGerencial() {
		Session retorno = null;

		try {
			retorno = sessionFactoryGerencial.openSession();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException("Hibernate - Erro ao criar a Session Gerencial");
		}

		return retorno;
	}

	public static void closeSession(Session session) {

		if (session != null) {
			try {

				Throwable t = new Throwable();
				StackTraceElement[] elements = t.getStackTrace();

				Long tempoInicialSession = tempoSession.get(session.hashCode());

				if (tempoInicialSession != null) {

					Long tempoTotalSession = System.currentTimeMillis() - tempoInicialSession;

					String mensagem = loggerEntidadesPorConsulta(session, elements, tempoTotalSession);
					if (mensagem != null && !mensagem.trim().equals("")) {
						log.debug(mensagem);
					}
				}
				session.close();
			} catch (HibernateException ex) {
				throw new SistemaException("Hibernate - Erro ao fechar a Session");
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}

		}
	}

	private static String loggerEntidadesPorConsulta(Session session, StackTraceElement[] elements, long tempoTotalSession) {

		String callerMethodName = elements[1].getMethodName();
		String callerClassName = elements[1].getClassName();
		String callerMethodName2Level = elements[2].getMethodName();
		String callerClassName2Level = elements[2].getClassName();

		String log = "";
		Map<String, Integer> entidades = new HashMap<String, Integer>();

		for (Object a : session.getStatistics().getEntityKeys()) {

			entidades.put(((EntityKey) a).getEntityName(), session.getStatistics().getEntityCount());

		}

		Iterator iterator = entidades.keySet().iterator();
		while (iterator.hasNext()) {

			String nomeEntidade = ((String) iterator.next());

			if (log.trim().equals("")) {
				log += ";" + callerClassName2Level + ";" + callerMethodName2Level + ";";
				log += callerClassName + ";" + callerMethodName + ";";
				log += nomeEntidade + ";";
				log += entidades.get(nomeEntidade) + ";" + tempoTotalSession + "; ";
			} else {
				log += " " + nomeEntidade + " ";
				log += entidades.get(nomeEntidade);

			}

		}

		tempoSession.remove(session.hashCode());

		return log;
	}


	public static void closeSession(StatelessSession session) {

		if (session != null) {
			try {
				session.close();
			} catch (HibernateException ex) {
				throw new SistemaException("Hibernate - Erro ao fechar a Session");
			}

		}
	}

	public static int getColumnSize(Class mappedClass, String propertyName) {
		Configuration cfg = HibernateUtil.getConfig();
		PersistentClass pClass = cfg.getClassMapping(mappedClass.getName());
		Column col = null;
		Property hibProp = null;

		try {
			hibProp = pClass.getProperty(propertyName);

			Iterator it = hibProp.getColumnIterator();

			while (it.hasNext()) {
				col = (Column) hibProp.getColumnIterator().next();
				break;
			}

		} catch (MappingException ex) {
			throw new SistemaException("Hibernate - Erro no mapeamento");
		}

		return col.getLength();
	}

	/**
	 * Método que obtém o nome da coluna no banco da propriedade passada Caso
	 * nao tenha, retorna null
	 * 
	 * @param mappedClass
	 *            Nome da classe
	 * @param propertyName
	 *            Nome da propriedade da classe
	 * @return nome da coluna
	 */
	public static String getNameColumn(Class mappedClass, String propertyName) {
		String retorno = null;
		Configuration cfg = HibernateUtil.getConfig();
		PersistentClass pClass = cfg.getClassMapping(mappedClass.getName());
		Column col = null;
		Property hibProp = null;

		try {
			hibProp = pClass.getProperty(propertyName);

			Iterator it = hibProp.getColumnIterator();

			while (it.hasNext()) {
				col = (Column) hibProp.getColumnIterator().next();
				break;
			}

			// retorno = col.getComment();
			// if (retorno == null || "".equals(retorno)) {
			if (col == null) {
				retorno = ConstantesDescricaoBanco.get(pClass.getTable().getName() + "." + propertyName);
			} else {
				retorno = ConstantesDescricaoBanco.get(pClass.getTable().getName() + "." + col.getName());
			}
			if (retorno == null && col != null) {

				retorno = col.getName();
			}

			if (col == null) {
				retorno = null;
			}
			// }

		} catch (MappingException ex) {
			try {

				hibProp = pClass.getIdentifierProperty();
				if (hibProp.getName().equalsIgnoreCase(propertyName)) {

					Iterator it = hibProp.getColumnIterator();

					while (it.hasNext()) {
						col = (Column) hibProp.getColumnIterator().next();
						break;
					}

					// retorno = col.getComment();
					// if (retorno == null || "".equals(retorno)) {
					// retorno = col.getName();
					// }

					retorno = ConstantesDescricaoBanco.get(pClass.getTable().getName() + "." + col.getName());
					if (retorno == null) {
						retorno = col.getName();
					}
				}

			} catch (MappingException eex) {
				eex.printStackTrace();
			}
		}

		return retorno;
	}

	/**
	 * Método que obtém o nome da tabela da classe passada
	 * 
	 * @param mappedClass
	 *            Nome da classe
	 * @return O String nome da tablea
	 */
	public static String getNameTable(Class mappedClass) {
		Configuration cfg = HibernateUtil.getConfig();
		PersistentClass pClass = cfg.getClassMapping(mappedClass.getName());

		String retorno = pClass.getTable().getComment();
		if (retorno == null || "".equals(retorno)) {
			retorno = ConstantesDescricaoBanco.get(pClass.getTable().getName());
			if (retorno == null) {
				retorno = pClass.getTable().getName();
			}

		}

		return retorno;
	}

	/**
	 * Retorna a que classe está mapeada a tabela passada
	 * 
	 * @param tableName
	 *            caminho da tabela
	 * @return caminho da classe
	 */
	public static String getClassName(String tableName) {
		Configuration cfg = HibernateUtil.getConfig();
		if (cfg != null) {
			Iterator iter = cfg.getClassMappings();
			while (iter.hasNext()) {
				PersistentClass classe = (PersistentClass) iter.next();
				if (classe.getTable().getName().equals(tableName)) {
					return classe.getClassName();
				}
			}
		}
		return null;
	}

	public static Configuration getConfig() {

		return configuration;
	}

	public static void main(String[] args) {

		getSession();

	}

	/**
	 * Retorna o valor de session
	 * 
	 * @return O valor de session
	 */
	public static StatelessSession getStatelessSessionIntegracaoSAM() {
		StatelessSession retorno = null;

		try {
			retorno = sessionFactoryIntegracaoSAM == null ? null : sessionFactoryIntegracaoSAM.openStatelessSession();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException("Hibernate - Erro ao criar a Session IntegracaoSAM");
		}

		return retorno;
	}

	/**
	 * Retorna o valor de session
	 * 
	 * @return O valor de session
	 */
	public static Session getSessionIntegracaoSAM() {
		Session retorno = null;

		try {
			retorno = sessionFactoryIntegracaoSAM == null ? null : sessionFactoryIntegracaoSAM.openSession();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new SistemaException("Hibernate - Erro ao criar a Session IntegracaoSAM");
		}

		return retorno;
	}

	public static String getDialect() {
		String retorno = "";
		retorno = configuration.getProperty("hibernate.dialect");
		return retorno;
	}

}
