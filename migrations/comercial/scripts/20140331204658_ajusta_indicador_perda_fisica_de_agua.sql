-- // ajusta indicador perda fisica de agua
-- Migration SQL that makes the change goes here.

-- Function: operacao.geraindicador(date)

-- DROP FUNCTION operacao.geraindicador(date);

CREATE OR REPLACE FUNCTION operacao.geraindicador(mesreferencia date)
  RETURNS void AS
$BODY$
DECLARE
  dataInicial date := mesReferencia;
  dataFinal date := operacao.last_day(mesReferencia);
  diasMes integer := operacao.dias_nummes(extract(month from mesReferencia), extract(year from mesReferencia));
  referenciaAux integer := extract(year from mesReferencia) * 100 + extract(month from mesReferencia);  
  rowcount integer;
BEGIN
  RAISE NOTICE 'Chamada da funcao de geracao de indicadores';
  RAISE NOTICE 'Data de referencia: (%) ', dataInicial;
  RAISE NOTICE 'Referencia Aux: (%) ', referenciaAux;
  
  DELETE FROM operacao.indicador_mensal
   WHERE indm_data = dataInicial;

  --INDICADOR 1 - Consumo Produtos Químicos
  INSERT INTO operacao.indicador_mensal 
  SELECT A.etav_referencia, 1 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.unop_tipooperacional, A.unop_id,
         operacao.ISNULL(CAST(B.consumo_produto AS NUMERIC), 0.0), 
         A.volume_agua, 
         (CASE WHEN A.volume_agua = 0 THEN 0 ELSE (operacao.ISNULL(CAST(B.consumo_produto AS NUMERIC), 0.0)::numeric / A.volume_agua::numeric) * 100 END)
    FROM (
  SELECT A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eta_id AS unop_id, SUM(F.etas_volume) AS volume_agua
    FROM operacao.eta_volume A
   INNER JOIN operacao.eta_volume_saida F ON A.etav_id = F.etav_id
   INNER JOIN operacao.eta B ON A.eta_id = B.eta_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eta_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 2
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   WHERE A.etav_referencia = dataInicial
   GROUP BY A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eta_id) AS A
  LEFT JOIN (
  SELECT A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 2 AS unop_tipooperacional, A.eta_id AS unop_id, SUM(B.conp_quantidade) AS consumo_produto
    FROM operacao.consumoeta A
   INNER JOIN operacao.consumoeta_produto B ON A.cons_id = B.cons_id
   WHERE A.cons_data BETWEEN dataInicial AND dataFinal 
   GROUP BY A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.eta_id
  UNION ALL 
  SELECT A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 3 AS unop_tipooperacional, A.eat_id AS unop_id, SUM(B.conp_quantidade) AS consumo_produto
    FROM operacao.consumoeat A
   INNER JOIN operacao.consumoeat_produto B ON A.cons_id = B.cons_id
   WHERE A.cons_data BETWEEN dataInicial AND dataFinal
   GROUP BY A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.eat_id) AS B ON A.unop_tipooperacional = B.unop_tipooperacional AND A.unop_id = B.unop_id;
   
  --INDICADOR 2 - Produtividade do Capital em Produto Químico
  INSERT INTO operacao.indicador_mensal
  SELECT A.etav_referencia, 2 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.unop_tipooperacional, A.unop_id,
         A.volume_agua, 
         operacao.ISNULL(CAST(B.consumo_produto AS NUMERIC), 0.0), 
         (CASE WHEN operacao.ISNULL(CAST(B.consumo_produto AS NUMERIC), 0.0) = 0 THEN 0 ELSE (A.volume_agua::numeric / operacao.ISNULL(CAST(B.consumo_produto AS NUMERIC), 0.0)::numeric) * 100 END)
    FROM (
  SELECT A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eta_id AS unop_id, SUM(F.etas_volume) AS volume_agua
    FROM operacao.eta_volume A
   INNER JOIN operacao.eta_volume_saida F ON A.etav_id = F.etav_id
   INNER JOIN operacao.eta B ON A.eta_id = B.eta_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eta_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 2
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   WHERE A.etav_referencia = dataInicial 
   GROUP BY A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eta_id) AS A
  LEFT JOIN (
  SELECT A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 2 AS unop_tipooperacional, A.eta_id AS unop_id, SUM(B.conp_quantidade * (SELECT tbpp_preco FROM operacao.tabelapreco_produto X INNER JOIN operacao.tabelapreco Z ON X.tabp_id = Z.tabp_id WHERE Z.tabp_vigencia <= A.cons_data AND X.prod_id = B.prod_id ORDER BY Z.tabp_vigencia DESC LIMIT 1)) AS consumo_produto
    FROM operacao.consumoeta A
   INNER JOIN operacao.consumoeta_produto B ON A.cons_id = B.cons_id
   WHERE A.cons_data BETWEEN dataInicial AND dataFinal
   GROUP BY A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.eta_id
  UNION ALL 
  SELECT A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 3 AS unop_tipooperacional, A.eat_id AS unop_id, SUM(B.conp_quantidade * (SELECT tbpp_preco FROM operacao.tabelapreco_produto X INNER JOIN operacao.tabelapreco Z ON X.tabp_id = Z.tabp_id WHERE Z.tabp_vigencia <= A.cons_data AND X.prod_id = B.prod_id ORDER BY Z.tabp_vigencia DESC LIMIT 1)) AS consumo_produto
    FROM operacao.consumoeat A
   INNER JOIN operacao.consumoeat_produto B ON A.cons_id = B.cons_id
   WHERE A.cons_data BETWEEN dataInicial AND dataFinal 
   GROUP BY A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.eat_id) AS B ON A.unop_tipooperacional = B.unop_tipooperacional AND A.unop_id = B.unop_id;

  --INDICADOR 3 - Produtividade do Capital em Energia Elétrica
  --volume total de água tratada / gasto total com produto qumico na produção
  INSERT INTO operacao.indicador_mensal
  SELECT A.etav_referencia, 3 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.unop_tipooperacional, A.unop_id, 
         A.volume_agua, 
         B.valor_energia, 
         (CASE WHEN B.valor_energia = 0 THEN 0 ELSE (A.volume_agua::numeric / B.valor_energia::numeric) * 100 END)
    FROM (
  SELECT A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eta_id AS unop_id, SUM(F.etas_volume) AS volume_agua
    FROM operacao.eta_volume A
   INNER JOIN operacao.eta_volume_saida F ON A.etav_id = F.etav_id
   INNER JOIN operacao.eta B ON A.eta_id = B.eta_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eta_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 2
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   WHERE A.etav_referencia = dataInicial
   GROUP BY A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eta_id) AS A
  LEFT JOIN (
  SELECT D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional as unop_tipooperacional, ucop_idoperacional AS unop_id, enld_vlr_TotalP * ucop_percentual / 100 AS valor_energia
    FROM operacao.energiaeletrica A
   INNER JOIN operacao.energiaeletrica_dados B ON A.enel_id = B.enel_id
   INNER JOIN operacao.unidade_consumidora C ON B.enld_uc = C.ucon_uc
   INNER JOIN cadastro.unidade_negocio D ON C.uneg_id = D.uneg_id
   INNER JOIN operacao.unidade_consumidora_operacional E ON C.ucon_id = E.ucon_id
   WHERE A.enel_referencia = dataInicial
   ) AS B ON A.greg_id = B.greg_id AND A.uneg_id = B.uneg_id AND A.muni_id = B.muni_id AND A.loca_id = B.loca_id AND A.unop_tipooperacional = B.unop_tipooperacional AND A.unop_id = B.unop_id;

  --INDICADOR 4 - Horas Paradas nos Sistemas
  INSERT INTO operacao.indicador_mensal
  SELECT DISTINCT dataInicial AS referencia, 4 AS indicador, B.greg_id, A.uneg_id, A.muni_id, A.loca_id, ucop_tipooperacional as unop_tipooperacional, ucop_idoperacional AS unop_id, 
         operacao.ISNULL(CAST(total_horas - total_trabalhado AS NUMERIC), CAST(0 AS NUMERIC)) AS total_parado, operacao.ISNULL(total_horas, 0),
             (case operacao.ISNULL(total_horas, 0) WHEN 0 THEN 0 ELSE (operacao.ISNULL(total_horas - operacao.ISNULL(CAST(total_horas - total_trabalhado AS NUMERIC), CAST(0 AS NUMERIC)),0) / total_horas) * 100 END) AS indicador
    FROM operacao.unidade_consumidora A
   INNER JOIN cadastro.unidade_negocio B ON A.uneg_id = B.uneg_id
   INNER JOIN operacao.unidade_consumidora_operacional C ON A.ucon_id = C.ucon_id
    LEFT JOIN (  SELECT unop_tipooperacional, unop_id, unop_cmb * 24 * diasMes AS total_horas
       FROM (
      SELECT 1 as unop_tipooperacional, eeab_id AS unop_id, eeab_cmb AS unop_cmb FROM operacao.eeab
      UNION ALL
      SELECT 2 as unop_tipooperacional, eta_id AS unop_id, eta_cmb AS unop_cmb FROM operacao.eta
      UNION ALL
      SELECT 3 as unop_tipooperacional, eeat_id AS unop_id, eeat_cmb AS unop_cmb FROM operacao.eeat
      UNION ALL
      SELECT 4 as unop_tipooperacional, rso_id AS unop_id, rso_cmb AS unop_cmb FROM operacao.rso) A
      ) D ON C.ucop_tipooperacional = D.unop_tipooperacional AND C.ucop_idoperacional = D.unop_id
    LEFT JOIN (   
      SELECT unop_tipooperacional, unop_id, referencia, SUM(total_trabalhado) AS total_trabalhado
        FROM (
      SELECT 1 AS unop_tipooperacional, B.eeab_id AS unop_id, A.eabc_qtdcmb * A.eabc_horacmb AS total_trabalhado, B.eabh_referencia AS referencia FROM operacao.eeab_horas_cmb A INNER JOIN operacao.eeab_horas b ON A.eebh_id = B.eabh_id
      UNION ALL   
      SELECT 2 AS unop_tipooperacional, B.eta_id AS unop_id, A.etac_qtdcmb * A.etac_horacmb AS total_trabalhado, B.etah_referencia AS referencia FROM operacao.eta_horas_cmb A INNER JOIN operacao.eta_horas b ON A.etah_id = B.etah_id
      UNION ALL
      SELECT 3 AS unop_tipooperacional, B.eeat_id AS unop_id, A.eatc_qtdcmb * A.eatc_horacmb AS total_trabalhado, B.eath_referencia AS referencia FROM operacao.eeat_horas_cmb A INNER JOIN operacao.eeat_horas b ON A.eath_id = B.eath_id
      UNION ALL
      SELECT 4 AS unop_tipooperacional, B.rso_id AS unop_id, A.rsoc_qtdcmb * A.rsoc_horacmb AS total_trabalhado, B.rsoh_referencia AS referencia FROM operacao.rso_horas_cmb A INNER JOIN operacao.rso_horas b ON A.rsoh_id = B.rsoh_id) A
      WHERE referencia = dataInicial
      GROUP BY unop_tipooperacional, unop_id, referencia) E ON C.ucop_tipooperacional = E.unop_tipooperacional AND C.ucop_idoperacional = E.unop_id
  ORDER BY greg_id, uneg_id, muni_id, loca_id, unop_tipooperacional, unop_id;
  
  --INDICADOR 101 - Conformidade da demanda de potência elétrica contratada
  --ultrapassagem da demanda de potência elétrica medida em R$ / despesas totais com energia elétrica em R$
  INSERT INTO operacao.indicador_mensal
  SELECT A.enel_referencia AS referencia, 101 AS indicador, D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional as unop_tipooperacional, ucop_idoperacional AS unop_id,
         SUM((enld_vlr_Ult_Pt + enld_vlr_Ult_FP + enld_vlr_Ult_Cv) * ucop_percentual / 100) as demanda_medida, 
         SUM(enld_vlr_TotalP * ucop_percentual / 100) as demanda_contratada, 
         (CASE WHEN SUM((enld_dcv + enld_dfs + enld_dps) * ucop_percentual) = 0 THEN 0 ELSE (SUM((enld_Dem_Med_Cv + enld_Dem_Med_Fp + enld_Dem_Med_Pt) * ucop_percentual)::numeric / SUM((enld_dcv + enld_dfs + enld_dps) * ucop_percentual)::numeric) * 100 END)
    FROM operacao.energiaeletrica A
   INNER JOIN operacao.energiaeletrica_dados B ON A.enel_id = B.enel_id
   INNER JOIN operacao.unidade_consumidora C ON B.enld_uc = C.ucon_uc
   INNER JOIN cadastro.unidade_negocio D ON C.uneg_id = D.uneg_id
   INNER JOIN operacao.unidade_consumidora_operacional E ON C.ucon_id = E.ucon_id
   WHERE A.enel_referencia = dataInicial
   GROUP BY A.enel_referencia, D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional, ucop_idoperacional;
/*
  --demanda de potência elétrica medida em Kw / demanda de potência elétrica contratada em Kw
  INSERT INTO operacao.indicador_mensal
  SELECT A.enel_referencia AS referencia, 101 AS indicador, D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional as unop_tipooperacional, ucop_idoperacional AS unop_id,
         SUM((enld_Dem_Med_Cv + enld_Dem_Med_Fp + enld_Dem_Med_Pt) * ucop_percentual) as demanda_medida, 
         SUM((enld_dcv + enld_dfs + enld_dps) * ucop_percentual) as demanda_contratada, 
         (CASE WHEN SUM((enld_dcv + enld_dfs + enld_dps) * ucop_percentual) = 0 THEN 0 ELSE (SUM((enld_Dem_Med_Cv + enld_Dem_Med_Fp + enld_Dem_Med_Pt) * ucop_percentual)::numeric / SUM((enld_dcv + enld_dfs + enld_dps) * ucop_percentual)::numeric) * 100 END)
    FROM operacao.energiaeletrica A
   INNER JOIN operacao.energiaeletrica_dados B ON A.enel_id = B.enel_id
   INNER JOIN operacao.unidade_consumidora C ON B.enld_uc = C.ucon_uc
   INNER JOIN cadastro.unidade_negocio D ON C.uneg_id = D.uneg_id
   INNER JOIN operacao.unidade_consumidora_operacional E ON C.ucon_id = E.ucon_id
   WHERE A.enel_referencia = dataInicial
   GROUP BY A.enel_referencia, D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional, ucop_idoperacional;
*/
  --INDICADOR 102 - Despesa decorrente do Fator de Potência Elétrica
  --despesa pelo Fator de Potência Elétrica em R$ / despesas totais com energia elétrica em R$
  INSERT INTO operacao.indicador_mensal
  SELECT A.enel_referencia AS referencia, 102 AS indicador, D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional as unop_tipooperacional, ucop_idoperacional AS unop_id, 
         SUM((enld_vlr_DRe_Cv + enld_vlr_DRe_Pt + enld_vlr_DRe_FP + enld_vlr_ERe_FP + enld_vlr_ERe_Cv + enld_vlr_ERe_Pt) * ucop_percentual / 100) as demanda_medida, 
         SUM(enld_vlr_TotalP * ucop_percentual / 100) as demanda_contratada, 
         (CASE WHEN SUM(enld_dcv * ucop_percentual / 100) = 0 THEN 0 ELSE (SUM(enld_Dem_Med_Cv * ucop_percentual)::numeric / SUM(enld_dcv * ucop_percentual)::numeric) * 100 END)
    FROM operacao.energiaeletrica A
   INNER JOIN operacao.energiaeletrica_dados B ON A.enel_id = B.enel_id
   INNER JOIN operacao.unidade_consumidora C ON B.enld_uc = C.ucon_uc
   INNER JOIN cadastro.unidade_negocio D ON C.uneg_id = D.uneg_id
   INNER JOIN operacao.unidade_consumidora_operacional E ON C.ucon_id = E.ucon_id
   WHERE A.enel_referencia = dataInicial
   GROUP BY A.enel_referencia, D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional, ucop_idoperacional;

  --INDICADOR 103 - Macromedidores
  INSERT INTO operacao.indicador_mensal (indm_data, indc_id, indm_vlr1, indm_vlr2, indm_vlrtotal)
  SELECT DISTINCT dataInicial AS referencia, 103 AS indicador, 
                  operacao.ISNULL(qtdMedidor::numeric,0.00), operacao.ISNULL(qtdMedidoresInstalados::numeric, 0.00) 
                  ,(CASE WHEN qtdMedidor <= 0.00 THEN 0 ELSE (operacao.ISNULL(qtdMedidoresInstalados::numeric, 0.00) / operacao.ISNULL(qtdMedidor::numeric, 0.00)) * 100 END)
    FROM (  SELECT COUNT(*) AS qtdMedidoresInstalados 
       FROM (
      SELECT 1 as unop_tipooperacional, eeab_id AS unop_id, mmed_dtinstalacao FROM operacao.eeab_medidor eab
      UNION ALL
      SELECT 2 as unop_tipooperacional, eta_id AS unop_id, mmed_dtinstalacao FROM operacao.eta_medidor eta
      UNION ALL
      SELECT 3 as unop_tipooperacional, eeat_id AS unop_id, mmed_dtinstalacao FROM operacao.eeat_medidor eat) U
      WHERE  mmed_dtinstalacao <= dataFinal
      ) A
    LEFT JOIN (  SELECT 103 AS Indicador, COUNT(*) AS qtdMedidor
       FROM (
      SELECT * from operacao.macro_medidor where mmed_datacadastro <= dataFinal) M
      ) B ON indicador = 103;

  --INDICADOR 104 - Rede Cadastrada
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 104 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         A.rdin_cadastrada, A.rdin_existente, 
         (CASE WHEN A.rdin_existente = 0 THEN 0 ELSE (A.rdin_cadastrada::numeric / A.rdin_existente::numeric) * 100 END)
    FROM operacao.rede_instalada A
         WHERE A.rdin_referencia = dataInicial;   

  --INDICADOR 201 - Prazo Médio de Atendimento de Ordens de Serviço ESGOTO
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 201 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         B.qtdDias, B.qtdOS, 
         (CASE WHEN B.qtdOS = 0 THEN 0 ELSE (B.qtdDias::numeric / B.qtdOS::numeric)  END)
    FROM (SELECT DISTINCT A.greg_id, B.uneg_id, E.muni_id, C.loca_id 
      FROM cadastro.gerencia_regional A 
     INNER JOIN cadastro.unidade_negocio B ON A.greg_id = B.greg_id 
     INNER JOIN cadastro.localidade C ON A.greg_id = C.greg_id AND B.uneg_id = C.uneg_ID 
     INNER JOIN cadastro.setor_comercial D ON C.loca_id = D.loca_id and d.stcm_icalternativo = 2
     INNER JOIN cadastro.municipio E ON D.muni_id = E.muni_id) AS A
   INNER JOIN (SELECT ra.loca_id, SUM(date_part('day', date_trunc('day', orse_tmencerramento) - date_trunc('day',orse_tmgeracao))) AS qtdDias, count(*) AS qtdOS  
           from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156 
           WHERE date(orse_tmencerramento) BETWEEN dataInicial AND dataFinal
      AND orse_tmgeracao > '20110101' 
      AND svtp_cdservicotipo = 'O' 
      AND (os.svtp_id=743 OR os.svtp_id=788 OR os.svtp_id=794 OR
           os.svtp_id=875 OR os.svtp_id=878 OR os.svtp_id=879 OR
           os.svtp_id=884 OR os.svtp_id=886 OR os.svtp_id=889 OR
           os.svtp_id=891 OR os.svtp_id=893 OR os.svtp_id=894 OR
           os.svtp_id=900 OR os.svtp_id=1012 OR os.svtp_id=1204 OR
           os.svtp_id=1205)
          GROUP BY ra.loca_id) AS B ON A.loca_id = B.loca_id;

  --INDICADOR 202 - Índice de Eficiência de Retirada de Vazamento (prazo: 24h) - %
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 202 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         operacao.ISNULL(C.qtdOSPrazo::numeric,0), operacao.ISNULL(B.qtdOSExec::numeric,0), 
         (CASE WHEN operacao.ISNULL(C.qtdOSPrazo,0) = 0 THEN 0 ELSE ( operacao.ISNULL(B.qtdOSExec::numeric,0) /  operacao.ISNULL(C.qtdOSPrazo::numeric,0)) * 100 END)
    FROM (SELECT DISTINCT A.greg_id, B.uneg_id, E.muni_id, C.loca_id 
      FROM cadastro.gerencia_regional A 
     INNER JOIN cadastro.unidade_negocio B ON A.greg_id = B.greg_id 
     INNER JOIN cadastro.localidade C ON A.greg_id = C.greg_id AND B.uneg_id = C.uneg_ID 
     INNER JOIN cadastro.setor_comercial D ON C.loca_id = D.loca_id and d.stcm_icalternativo = 2
     INNER JOIN cadastro.municipio E ON D.muni_id = E.muni_id) AS A
    LEFT JOIN (SELECT ra.loca_id, count(*) AS qtdOSExec  
            from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156
           WHERE date(orse_tmencerramento) BETWEEN dataInicial AND dataFinal 
       AND orse_tmgeracao > '20110101' 
       AND svtp_cdservicotipo= 'O' 
             AND (os.svtp_id=466 OR os.svtp_id=670 OR os.svtp_id=671 OR os.svtp_id=672 OR os.svtp_id=673 
                             OR os.svtp_id=697 OR os.svtp_id=698 OR os.svtp_id=699 OR os.svtp_id=956 OR os.svtp_id=957 
                             OR os.svtp_id=958 OR os.svtp_id=959 OR os.svtp_id=983 OR os.svtp_id=984 OR os.svtp_id=985)
           GROUP BY ra.loca_id) AS B ON A.loca_id = B.loca_id
          LEFT JOIN (SELECT ra.loca_id, COUNT(*) AS qtdOSPrazo  
            from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156
           WHERE date(orse_tmencerramento) BETWEEN dataInicial AND dataFinal 
             AND svtp_cdservicotipo= 'O'
             AND date_part('day', date_trunc('day', orse_tmencerramento) - date_trunc('day', orse_tmgeracao)) <= st.svtp_nntempomedioexecucao
             AND (os.svtp_id=466 OR os.svtp_id=670 OR os.svtp_id=671 OR os.svtp_id=672 OR os.svtp_id=673 
                             OR os.svtp_id=697 OR os.svtp_id=698 OR os.svtp_id=699 OR os.svtp_id=956 OR os.svtp_id=957 
                             OR os.svtp_id=958 OR os.svtp_id=959 OR os.svtp_id=983 OR os.svtp_id=984 OR os.svtp_id=985)
           GROUP BY ra.loca_id) AS C ON A.loca_id = C.loca_id;    

  --INDICADOR 203 - Índice de Eficiência de Retirada de Vazamento (prazo: 24h) - Nº Dias
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 203 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         operacao.ISNULL(B.qtdDias::numeric,0),  operacao.ISNULL(C.qtdOSExec::numeric,0), 
         (CASE WHEN operacao.ISNULL(C.qtdOSExec,0) = 0 THEN 0 ELSE ( operacao.ISNULL(B.qtdDias::numeric,0) /  operacao.ISNULL(C.qtdOSExec::numeric,0)) END)
    FROM (SELECT DISTINCT A.greg_id, B.uneg_id, E.muni_id, C.loca_id 
      FROM cadastro.gerencia_regional A 
     INNER JOIN cadastro.unidade_negocio B ON A.greg_id = B.greg_id 
     INNER JOIN cadastro.localidade C ON A.greg_id = C.greg_id AND B.uneg_id = C.uneg_ID 
     INNER JOIN cadastro.setor_comercial D ON C.loca_id = D.loca_id and d.stcm_icalternativo = 2
     INNER JOIN cadastro.municipio E ON D.muni_id = E.muni_id) AS A
    LEFT JOIN (SELECT ra.loca_id, SUM(date_part('day', date_trunc('day', orse_tmencerramento) - date_trunc('day', orse_tmgeracao))) AS qtdDias 
      from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156 
           WHERE date(orse_tmencerramento) BETWEEN dataInicial AND dataFinal
       AND orse_tmgeracao > '20110101' 
       AND svtp_cdservicotipo= 'O' 
       AND (os.svtp_id=466 OR os.svtp_id=670 OR os.svtp_id=671 OR os.svtp_id=672 OR os.svtp_id=673 
                             OR os.svtp_id=697 OR os.svtp_id=698 OR os.svtp_id=699 OR os.svtp_id=956 OR os.svtp_id=957 
                             OR os.svtp_id=958 OR os.svtp_id=959 OR os.svtp_id=983 OR os.svtp_id=984 OR os.svtp_id=985)
           GROUP BY ra.loca_id) AS B ON A.loca_id = B.loca_id
          LEFT JOIN (SELECT ra.loca_id, count(*) AS qtdOSExec  
            from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156
           WHERE date(orse_tmencerramento) BETWEEN dataInicial AND dataFinal 
       AND orse_tmgeracao > '20110101' 
       AND svtp_cdservicotipo= 'O' 
             AND (os.svtp_id=466 OR os.svtp_id=670 OR os.svtp_id=671 OR os.svtp_id=672 OR os.svtp_id=673 
                             OR os.svtp_id=697 OR os.svtp_id=698 OR os.svtp_id=699 OR os.svtp_id=956 OR os.svtp_id=957 
                             OR os.svtp_id=958 OR os.svtp_id=959 OR os.svtp_id=983 OR os.svtp_id=984 OR os.svtp_id=985)
           GROUP BY ra.loca_id) AS C ON A.loca_id = C.loca_id;    

  --INDICADOR 204 - Taxa de Retirada de Vazamento de ÁGUA
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 204 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         operacao.ISNULL(B.qtdOSExec::numeric,0),  operacao.ISNULL(C.qtdOS::numeric,0), 
         (CASE WHEN operacao.ISNULL(C.qtdOS,0) = 0 THEN 0 ELSE ( operacao.ISNULL(B.qtdOSExec::numeric,0) /  operacao.ISNULL(C.qtdOS::numeric,0)) * 100 END)
    FROM (SELECT DISTINCT A.greg_id, B.uneg_id, E.muni_id, C.loca_id 
      FROM cadastro.gerencia_regional A 
     INNER JOIN cadastro.unidade_negocio B ON A.greg_id = B.greg_id 
     INNER JOIN cadastro.localidade C ON A.greg_id = C.greg_id AND B.uneg_id = C.uneg_ID 
     INNER JOIN cadastro.setor_comercial D ON C.loca_id = D.loca_id and d.stcm_icalternativo = 2
     INNER JOIN cadastro.municipio E ON D.muni_id = E.muni_id) AS A
    LEFT JOIN (SELECT ra.loca_id, count(*) AS qtdOSExec  
      from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156 
            WHERE date(orse_tmencerramento) BETWEEN dataInicial AND dataFinal
       AND orse_tmgeracao > '20110101' 
       AND svtp_cdservicotipo= 'O' 
       AND (os.svtp_id=466 OR os.svtp_id=670 OR os.svtp_id=671 OR os.svtp_id=672 OR os.svtp_id=673 
                             OR os.svtp_id=697 OR os.svtp_id=698 OR os.svtp_id=699 OR os.svtp_id=956 OR os.svtp_id=957 
                             OR os.svtp_id=958 OR os.svtp_id=959 OR os.svtp_id=983 OR os.svtp_id=984 OR os.svtp_id=985)
           GROUP BY ra.loca_id) AS B ON A.loca_id = B.loca_id
          LEFT JOIN (SELECT ra.loca_id, count(*) AS qtdOS
            from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156 
           WHERE date(orse_tmgeracao) BETWEEN dataInicial AND dataFinal
             AND svtp_cdservicotipo= 'O'
             AND (os.svtp_id=466 OR os.svtp_id=670 OR os.svtp_id=671 OR os.svtp_id=672 OR os.svtp_id=673 
                             OR os.svtp_id=697 OR os.svtp_id=698 OR os.svtp_id=699 OR os.svtp_id=956 OR os.svtp_id=957 
                             OR os.svtp_id=958 OR os.svtp_id=959 OR os.svtp_id=983 OR os.svtp_id=984 OR os.svtp_id=985)
           GROUP BY ra.loca_id) AS C ON A.loca_id = C.loca_id;    

  --INDICADOR 205 - Índice de Tratamento de ESGOTO
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 205 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         A.etev_volumetratado, A.etev_volumecoletado, 
         (CASE WHEN A.etev_volumecoletado = 0 THEN 0 ELSE (A.etev_volumetratado::numeric / A.etev_volumecoletado::numeric) * 100 END)
    FROM operacao.ete_volume A
         WHERE A.etev_referencia = dataInicial;  

  --INDICADOR 206 - Índice de Conformidade da Qualidade ÁGUA
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 206 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         A.anac_conformidade, A.anac_analisada, 
         (CASE WHEN A.anac_analisada = 0 THEN 0 ELSE (A.anac_conformidade::numeric / A.anac_analisada::numeric) * 100 END)
    FROM operacao.analise_clinica A
         WHERE A.anac_referencia = dataInicial;

   --INDICADOR 209 - Índice de  Macromedição Total
  INSERT INTO operacao.indicador_mensal
  SELECT A.eatv_referencia, 209 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.unop_tipooperacional, A.unop_id,
         operacao.ISNULL(A.volume_saida_eat_fixo::numeric, 0),
         operacao.ISNULL(B.volume_saida_eat_portatil::numeric, 0),
         (CASE WHEN operacao.ISNULL(volume_saida_eat_portatil::numeric,0) = 0 THEN 0 ELSE (operacao.ISNULL(volume_saida_eat_portatil::numeric,0) /  operacao.ISNULL(volume_saida_eat_portatil::numeric,0)) * 100 END)
    FROM (
  SELECT A.eatv_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eeat_id AS unop_id, SUM(F.eats_volume) AS volume_saida_eat_fixo
    FROM operacao.eeat_volume A
   INNER JOIN operacao.eeat_volume_saida F ON A.eatv_id = F.eatv_id
   INNER JOIN operacao.eeat B ON A.eeat_id = B.eeat_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eeat_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 3
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   INNER JOIN operacao.macro_medidor G ON F.mmed_idsaida = G.mmed_id
   WHERE G.mmed_tipo = 2
   GROUP BY A.eatv_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eeat_id) AS A
   LEFT JOIN (
  SELECT A.eatv_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eeat_id AS unop_id, SUM(F.eats_volume) AS volume_saida_eat_portatil
    FROM operacao.eeat_volume A
   INNER JOIN operacao.eeat_volume_saida F ON A.eatv_id = F.eatv_id
   INNER JOIN operacao.eeat B ON A.eeat_id = B.eeat_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eeat_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 3
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   INNER JOIN operacao.macro_medidor G ON F.mmed_idsaida = G.mmed_id
   WHERE G.mmed_tipo = 1
   GROUP BY A.eatv_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eeat_id) 
   AS B ON 
   A.greg_id = B.greg_id AND 
   A.uneg_id = B.uneg_id AND 
   A.muni_id = B.muni_id AND 
   A.loca_id = B.loca_id AND
   A.unop_id = B.unop_id AND 
   A.eatv_referencia = B.eatv_referencia
   WHERE A.eatv_referencia = dataInicial;

        --INDICADOR 210 - Índice de Perda Física de ÁGUA
  INSERT INTO operacao.indicador_mensal        
  SELECT A.etav_referencia, 210 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.unop_tipooperacional, A.unop_id,
    (CASE WHEN operacao.ISNULL((A.volume_saida_eta - (A.volume_saida_eta - B.volume_saida_eat) - B.volume_saida_eat)::numeric,0) = 0
       THEN 0
     ELSE A.volume_saida_eta - (A.volume_saida_eta - B.volume_saida_eat) - B.volume_saida_eat
     END),
    (CASE WHEN operacao.ISNULL((A.volume_saida_eta - (A.volume_saida_eta - B.volume_saida_eat) )::numeric,0) = 0
       THEN 0
     ELSE A.volume_saida_eta - (A.volume_saida_eta - B.volume_saida_eat) 
     END),    
    (CASE WHEN operacao.ISNULL((A.volume_saida_eta - (A.volume_saida_eta - B.volume_saida_eat))::numeric,0) = 0 
       THEN 0 
     ELSE ( operacao.ISNULL( (A.volume_saida_eta - (A.volume_saida_eta - B.volume_saida_eat) - B.volume_saida_eat)::numeric,0) /  operacao.ISNULL((A.volume_saida_eta - (A.volume_saida_eta - B.volume_saida_eat))::numeric,0)) * 100 
     END)
  FROM (
  SELECT A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eta_id AS unop_id, SUM(F.etas_volume) AS volume_saida_eta
    FROM operacao.eta_volume A
   INNER JOIN operacao.eta_volume_saida F ON A.etav_id = F.etav_id
   INNER JOIN operacao.eta B ON A.eta_id = B.eta_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eta_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 2
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   GROUP BY A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eta_id) AS A
   LEFT JOIN (
  SELECT A.eatv_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eeat_id AS unop_id, SUM(F.eats_volume) AS volume_saida_eat
    FROM operacao.eeat_volume A
   INNER JOIN operacao.eeat_volume_saida F ON A.eatv_id = F.eatv_id
   INNER JOIN operacao.eeat B ON A.eeat_id = B.eeat_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eeat_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 3
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   GROUP BY A.eatv_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eeat_id) AS B ON A.greg_id = B.greg_id AND A.uneg_id = B.uneg_id AND A.muni_id = B.muni_id AND A.loca_id = B.loca_id AND A.etav_referencia = B.eatv_referencia
   WHERE A.etav_referencia = dataInicial;


  --INDICADOR 211 - Índice de Clientes com Precariedade de Abastecimento de ÁGUA
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 211 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0,
             operacao.ISNULL(B.qtdPrecariedade::numeric,0),  operacao.ISNULL(C.qtdEconomias::numeric,0),
             (CASE WHEN operacao.ISNULL(C.qtdEconomias,0) = 0 THEN 0 ELSE ( operacao.ISNULL(B.qtdPrecariedade::numeric,0) /  operacao.ISNULL(C.qtdEconomias::numeric,0)) * 100 END)
        FROM (SELECT DISTINCT A.greg_id, B.uneg_id, E.muni_id, C.loca_id
              FROM cadastro.gerencia_regional A
             INNER JOIN cadastro.unidade_negocio B ON A.greg_id = B.greg_id
             INNER JOIN cadastro.localidade C ON A.greg_id = C.greg_id AND B.uneg_id = C.uneg_ID
             INNER JOIN cadastro.setor_comercial D ON C.loca_id = D.loca_id and d.stcm_icalternativo = 2
             INNER JOIN cadastro.municipio E ON D.muni_id = E.muni_id) AS A
        LEFT JOIN (SELECT loca_id, SUM(imov_qteconomia) as qtdPrecariedade
    FROM faturamento.fatur_situacao_hist, cadastro.imovel
                WHERE faturamento.fatur_situacao_hist.ftsm_id = 3
                  AND (ftsh_amfaturamentoretirada is null or ftsh_amfaturamentoretirada > referenciaAux)
                  AND ftsh_amfatmtsitinicio <= referenciaAux and ftsh_amfaturamentosituacaofim >= referenciaAux
                  AND faturamento.fatur_situacao_hist.imov_id = cadastro.imovel.imov_id
                  AND last_id=3
                GROUP BY loca_id) AS B ON A.loca_id = B.loca_id  
        LEFT JOIN (SELECT loca_id, qtdEconomias
                    FROM dblink('host=10.20.100.21 port=5432 dbname=gsan_gerencial user=gsan_operacao password=oper001',
                        'SELECT loca_id, SUM(rele_qteconomias) as qtdEconomias FROM cadastro.un_res_lig_econ WHERE last_id = 3 AND cast(rele_amreferencia as text) = ' || cast(referenciaAux as text)  || ' GROUP BY loca_id')
                        AS resultado(loca_id integer, qtdEconomias numeric)) 
                        AS C ON A.loca_id = C.loca_id;
END;  
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION operacao.geraindicador(date)
  OWNER TO gsan_admin;












-- //@UNDO
-- SQL to undo the change goes here.




-- Function: operacao.geraindicador(date)

-- DROP FUNCTION operacao.geraindicador(date);

CREATE OR REPLACE FUNCTION operacao.geraindicador(mesreferencia date)
  RETURNS void AS
$BODY$
DECLARE
  dataInicial date := mesReferencia;
  dataFinal date := operacao.last_day(mesReferencia);
  diasMes integer := operacao.dias_nummes(extract(month from mesReferencia), extract(year from mesReferencia));
  referenciaAux integer := extract(year from mesReferencia) * 100 + extract(month from mesReferencia);  
  rowcount integer;
BEGIN
  RAISE NOTICE 'Chamada da funcao de geracao de indicadores';
  RAISE NOTICE 'Data de referencia: (%) ', dataInicial;
  RAISE NOTICE 'Referencia Aux: (%) ', referenciaAux;
  
  DELETE FROM operacao.indicador_mensal
   WHERE indm_data = dataInicial;

  --INDICADOR 1 - Consumo Produtos Químicos
  INSERT INTO operacao.indicador_mensal 
  SELECT A.etav_referencia, 1 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.unop_tipooperacional, A.unop_id,
         operacao.ISNULL(CAST(B.consumo_produto AS NUMERIC), 0.0), 
         A.volume_agua, 
         (CASE WHEN A.volume_agua = 0 THEN 0 ELSE (operacao.ISNULL(CAST(B.consumo_produto AS NUMERIC), 0.0)::numeric / A.volume_agua::numeric) * 100 END)
    FROM (
  SELECT A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eta_id AS unop_id, SUM(F.etas_volume) AS volume_agua
    FROM operacao.eta_volume A
   INNER JOIN operacao.eta_volume_saida F ON A.etav_id = F.etav_id
   INNER JOIN operacao.eta B ON A.eta_id = B.eta_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eta_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 2
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   WHERE A.etav_referencia = dataInicial
   GROUP BY A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eta_id) AS A
  LEFT JOIN (
  SELECT A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 2 AS unop_tipooperacional, A.eta_id AS unop_id, SUM(B.conp_quantidade) AS consumo_produto
    FROM operacao.consumoeta A
   INNER JOIN operacao.consumoeta_produto B ON A.cons_id = B.cons_id
   WHERE A.cons_data BETWEEN dataInicial AND dataFinal 
   GROUP BY A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.eta_id
  UNION ALL 
  SELECT A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 3 AS unop_tipooperacional, A.eat_id AS unop_id, SUM(B.conp_quantidade) AS consumo_produto
    FROM operacao.consumoeat A
   INNER JOIN operacao.consumoeat_produto B ON A.cons_id = B.cons_id
   WHERE A.cons_data BETWEEN dataInicial AND dataFinal
   GROUP BY A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.eat_id) AS B ON A.unop_tipooperacional = B.unop_tipooperacional AND A.unop_id = B.unop_id;
   
  --INDICADOR 2 - Produtividade do Capital em Produto Químico
  INSERT INTO operacao.indicador_mensal
  SELECT A.etav_referencia, 2 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.unop_tipooperacional, A.unop_id,
         A.volume_agua, 
         operacao.ISNULL(CAST(B.consumo_produto AS NUMERIC), 0.0), 
         (CASE WHEN operacao.ISNULL(CAST(B.consumo_produto AS NUMERIC), 0.0) = 0 THEN 0 ELSE (A.volume_agua::numeric / operacao.ISNULL(CAST(B.consumo_produto AS NUMERIC), 0.0)::numeric) * 100 END)
    FROM (
  SELECT A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eta_id AS unop_id, SUM(F.etas_volume) AS volume_agua
    FROM operacao.eta_volume A
   INNER JOIN operacao.eta_volume_saida F ON A.etav_id = F.etav_id
   INNER JOIN operacao.eta B ON A.eta_id = B.eta_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eta_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 2
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   WHERE A.etav_referencia = dataInicial 
   GROUP BY A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eta_id) AS A
  LEFT JOIN (
  SELECT A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 2 AS unop_tipooperacional, A.eta_id AS unop_id, SUM(B.conp_quantidade * (SELECT tbpp_preco FROM operacao.tabelapreco_produto X INNER JOIN operacao.tabelapreco Z ON X.tabp_id = Z.tabp_id WHERE Z.tabp_vigencia <= A.cons_data AND X.prod_id = B.prod_id ORDER BY Z.tabp_vigencia DESC LIMIT 1)) AS consumo_produto
    FROM operacao.consumoeta A
   INNER JOIN operacao.consumoeta_produto B ON A.cons_id = B.cons_id
   WHERE A.cons_data BETWEEN dataInicial AND dataFinal
   GROUP BY A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.eta_id
  UNION ALL 
  SELECT A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 3 AS unop_tipooperacional, A.eat_id AS unop_id, SUM(B.conp_quantidade * (SELECT tbpp_preco FROM operacao.tabelapreco_produto X INNER JOIN operacao.tabelapreco Z ON X.tabp_id = Z.tabp_id WHERE Z.tabp_vigencia <= A.cons_data AND X.prod_id = B.prod_id ORDER BY Z.tabp_vigencia DESC LIMIT 1)) AS consumo_produto
    FROM operacao.consumoeat A
   INNER JOIN operacao.consumoeat_produto B ON A.cons_id = B.cons_id
   WHERE A.cons_data BETWEEN dataInicial AND dataFinal 
   GROUP BY A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.eat_id) AS B ON A.unop_tipooperacional = B.unop_tipooperacional AND A.unop_id = B.unop_id;

  --INDICADOR 3 - Produtividade do Capital em Energia Elétrica
  --volume total de água tratada / gasto total com produto qumico na produção
  INSERT INTO operacao.indicador_mensal
  SELECT A.etav_referencia, 3 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.unop_tipooperacional, A.unop_id, 
         A.volume_agua, 
         B.valor_energia, 
         (CASE WHEN B.valor_energia = 0 THEN 0 ELSE (A.volume_agua::numeric / B.valor_energia::numeric) * 100 END)
    FROM (
  SELECT A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eta_id AS unop_id, SUM(F.etas_volume) AS volume_agua
    FROM operacao.eta_volume A
   INNER JOIN operacao.eta_volume_saida F ON A.etav_id = F.etav_id
   INNER JOIN operacao.eta B ON A.eta_id = B.eta_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eta_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 2
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   WHERE A.etav_referencia = dataInicial
   GROUP BY A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eta_id) AS A
  LEFT JOIN (
  SELECT D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional as unop_tipooperacional, ucop_idoperacional AS unop_id, enld_vlr_TotalP * ucop_percentual / 100 AS valor_energia
    FROM operacao.energiaeletrica A
   INNER JOIN operacao.energiaeletrica_dados B ON A.enel_id = B.enel_id
   INNER JOIN operacao.unidade_consumidora C ON B.enld_uc = C.ucon_uc
   INNER JOIN cadastro.unidade_negocio D ON C.uneg_id = D.uneg_id
   INNER JOIN operacao.unidade_consumidora_operacional E ON C.ucon_id = E.ucon_id
   WHERE A.enel_referencia = dataInicial
   ) AS B ON A.greg_id = B.greg_id AND A.uneg_id = B.uneg_id AND A.muni_id = B.muni_id AND A.loca_id = B.loca_id AND A.unop_tipooperacional = B.unop_tipooperacional AND A.unop_id = B.unop_id;

  --INDICADOR 4 - Horas Paradas nos Sistemas
  INSERT INTO operacao.indicador_mensal
  SELECT DISTINCT dataInicial AS referencia, 4 AS indicador, B.greg_id, A.uneg_id, A.muni_id, A.loca_id, ucop_tipooperacional as unop_tipooperacional, ucop_idoperacional AS unop_id, 
         operacao.ISNULL(CAST(total_horas - total_trabalhado AS NUMERIC), CAST(0 AS NUMERIC)) AS total_parado, operacao.ISNULL(total_horas, 0),
             (case operacao.ISNULL(total_horas, 0) WHEN 0 THEN 0 ELSE (operacao.ISNULL(total_horas - operacao.ISNULL(CAST(total_horas - total_trabalhado AS NUMERIC), CAST(0 AS NUMERIC)),0) / total_horas) * 100 END) AS indicador
    FROM operacao.unidade_consumidora A
   INNER JOIN cadastro.unidade_negocio B ON A.uneg_id = B.uneg_id
   INNER JOIN operacao.unidade_consumidora_operacional C ON A.ucon_id = C.ucon_id
    LEFT JOIN (  SELECT unop_tipooperacional, unop_id, unop_cmb * 24 * diasMes AS total_horas
       FROM (
      SELECT 1 as unop_tipooperacional, eeab_id AS unop_id, eeab_cmb AS unop_cmb FROM operacao.eeab
      UNION ALL
      SELECT 2 as unop_tipooperacional, eta_id AS unop_id, eta_cmb AS unop_cmb FROM operacao.eta
      UNION ALL
      SELECT 3 as unop_tipooperacional, eeat_id AS unop_id, eeat_cmb AS unop_cmb FROM operacao.eeat
      UNION ALL
      SELECT 4 as unop_tipooperacional, rso_id AS unop_id, rso_cmb AS unop_cmb FROM operacao.rso) A
      ) D ON C.ucop_tipooperacional = D.unop_tipooperacional AND C.ucop_idoperacional = D.unop_id
    LEFT JOIN (   
      SELECT unop_tipooperacional, unop_id, referencia, SUM(total_trabalhado) AS total_trabalhado
        FROM (
      SELECT 1 AS unop_tipooperacional, B.eeab_id AS unop_id, A.eabc_qtdcmb * A.eabc_horacmb AS total_trabalhado, B.eabh_referencia AS referencia FROM operacao.eeab_horas_cmb A INNER JOIN operacao.eeab_horas b ON A.eebh_id = B.eabh_id
      UNION ALL   
      SELECT 2 AS unop_tipooperacional, B.eta_id AS unop_id, A.etac_qtdcmb * A.etac_horacmb AS total_trabalhado, B.etah_referencia AS referencia FROM operacao.eta_horas_cmb A INNER JOIN operacao.eta_horas b ON A.etah_id = B.etah_id
      UNION ALL
      SELECT 3 AS unop_tipooperacional, B.eeat_id AS unop_id, A.eatc_qtdcmb * A.eatc_horacmb AS total_trabalhado, B.eath_referencia AS referencia FROM operacao.eeat_horas_cmb A INNER JOIN operacao.eeat_horas b ON A.eath_id = B.eath_id
      UNION ALL
      SELECT 4 AS unop_tipooperacional, B.rso_id AS unop_id, A.rsoc_qtdcmb * A.rsoc_horacmb AS total_trabalhado, B.rsoh_referencia AS referencia FROM operacao.rso_horas_cmb A INNER JOIN operacao.rso_horas b ON A.rsoh_id = B.rsoh_id) A
      WHERE referencia = dataInicial
      GROUP BY unop_tipooperacional, unop_id, referencia) E ON C.ucop_tipooperacional = E.unop_tipooperacional AND C.ucop_idoperacional = E.unop_id
  ORDER BY greg_id, uneg_id, muni_id, loca_id, unop_tipooperacional, unop_id;
  
  --INDICADOR 101 - Conformidade da demanda de potência elétrica contratada
  --ultrapassagem da demanda de potência elétrica medida em R$ / despesas totais com energia elétrica em R$
  INSERT INTO operacao.indicador_mensal
  SELECT A.enel_referencia AS referencia, 101 AS indicador, D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional as unop_tipooperacional, ucop_idoperacional AS unop_id,
         SUM((enld_vlr_Ult_Pt + enld_vlr_Ult_FP + enld_vlr_Ult_Cv) * ucop_percentual / 100) as demanda_medida, 
         SUM(enld_vlr_TotalP * ucop_percentual / 100) as demanda_contratada, 
         (CASE WHEN SUM((enld_dcv + enld_dfs + enld_dps) * ucop_percentual) = 0 THEN 0 ELSE (SUM((enld_Dem_Med_Cv + enld_Dem_Med_Fp + enld_Dem_Med_Pt) * ucop_percentual)::numeric / SUM((enld_dcv + enld_dfs + enld_dps) * ucop_percentual)::numeric) * 100 END)
    FROM operacao.energiaeletrica A
   INNER JOIN operacao.energiaeletrica_dados B ON A.enel_id = B.enel_id
   INNER JOIN operacao.unidade_consumidora C ON B.enld_uc = C.ucon_uc
   INNER JOIN cadastro.unidade_negocio D ON C.uneg_id = D.uneg_id
   INNER JOIN operacao.unidade_consumidora_operacional E ON C.ucon_id = E.ucon_id
   WHERE A.enel_referencia = dataInicial
   GROUP BY A.enel_referencia, D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional, ucop_idoperacional;
/*
  --demanda de potência elétrica medida em Kw / demanda de potência elétrica contratada em Kw
  INSERT INTO operacao.indicador_mensal
  SELECT A.enel_referencia AS referencia, 101 AS indicador, D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional as unop_tipooperacional, ucop_idoperacional AS unop_id,
         SUM((enld_Dem_Med_Cv + enld_Dem_Med_Fp + enld_Dem_Med_Pt) * ucop_percentual) as demanda_medida, 
         SUM((enld_dcv + enld_dfs + enld_dps) * ucop_percentual) as demanda_contratada, 
         (CASE WHEN SUM((enld_dcv + enld_dfs + enld_dps) * ucop_percentual) = 0 THEN 0 ELSE (SUM((enld_Dem_Med_Cv + enld_Dem_Med_Fp + enld_Dem_Med_Pt) * ucop_percentual)::numeric / SUM((enld_dcv + enld_dfs + enld_dps) * ucop_percentual)::numeric) * 100 END)
    FROM operacao.energiaeletrica A
   INNER JOIN operacao.energiaeletrica_dados B ON A.enel_id = B.enel_id
   INNER JOIN operacao.unidade_consumidora C ON B.enld_uc = C.ucon_uc
   INNER JOIN cadastro.unidade_negocio D ON C.uneg_id = D.uneg_id
   INNER JOIN operacao.unidade_consumidora_operacional E ON C.ucon_id = E.ucon_id
   WHERE A.enel_referencia = dataInicial
   GROUP BY A.enel_referencia, D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional, ucop_idoperacional;
*/
  --INDICADOR 102 - Despesa decorrente do Fator de Potência Elétrica
  --despesa pelo Fator de Potência Elétrica em R$ / despesas totais com energia elétrica em R$
  INSERT INTO operacao.indicador_mensal
  SELECT A.enel_referencia AS referencia, 102 AS indicador, D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional as unop_tipooperacional, ucop_idoperacional AS unop_id, 
         SUM((enld_vlr_DRe_Cv + enld_vlr_DRe_Pt + enld_vlr_DRe_FP + enld_vlr_ERe_FP + enld_vlr_ERe_Cv + enld_vlr_ERe_Pt) * ucop_percentual / 100) as demanda_medida, 
         SUM(enld_vlr_TotalP * ucop_percentual / 100) as demanda_contratada, 
         (CASE WHEN SUM(enld_dcv * ucop_percentual / 100) = 0 THEN 0 ELSE (SUM(enld_Dem_Med_Cv * ucop_percentual)::numeric / SUM(enld_dcv * ucop_percentual)::numeric) * 100 END)
    FROM operacao.energiaeletrica A
   INNER JOIN operacao.energiaeletrica_dados B ON A.enel_id = B.enel_id
   INNER JOIN operacao.unidade_consumidora C ON B.enld_uc = C.ucon_uc
   INNER JOIN cadastro.unidade_negocio D ON C.uneg_id = D.uneg_id
   INNER JOIN operacao.unidade_consumidora_operacional E ON C.ucon_id = E.ucon_id
   WHERE A.enel_referencia = dataInicial
   GROUP BY A.enel_referencia, D.greg_id, C.uneg_id, C.muni_id, C.loca_id, ucop_tipooperacional, ucop_idoperacional;

  --INDICADOR 103 - Macromedidores
  INSERT INTO operacao.indicador_mensal (indm_data, indc_id, indm_vlr1, indm_vlr2, indm_vlrtotal)
  SELECT DISTINCT dataInicial AS referencia, 103 AS indicador, 
                  operacao.ISNULL(qtdMedidor::numeric,0.00), operacao.ISNULL(qtdMedidoresInstalados::numeric, 0.00) 
                  ,(CASE WHEN qtdMedidor <= 0.00 THEN 0 ELSE (operacao.ISNULL(qtdMedidoresInstalados::numeric, 0.00) / operacao.ISNULL(qtdMedidor::numeric, 0.00)) * 100 END)
    FROM (  SELECT COUNT(*) AS qtdMedidoresInstalados 
       FROM (
      SELECT 1 as unop_tipooperacional, eeab_id AS unop_id, mmed_dtinstalacao FROM operacao.eeab_medidor eab
      UNION ALL
      SELECT 2 as unop_tipooperacional, eta_id AS unop_id, mmed_dtinstalacao FROM operacao.eta_medidor eta
      UNION ALL
      SELECT 3 as unop_tipooperacional, eeat_id AS unop_id, mmed_dtinstalacao FROM operacao.eeat_medidor eat) U
      WHERE  mmed_dtinstalacao <= dataFinal
      ) A
    LEFT JOIN (  SELECT 103 AS Indicador, COUNT(*) AS qtdMedidor
       FROM (
      SELECT * from operacao.macro_medidor where mmed_datacadastro <= dataFinal) M
      ) B ON indicador = 103;

  --INDICADOR 104 - Rede Cadastrada
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 104 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         A.rdin_cadastrada, A.rdin_existente, 
         (CASE WHEN A.rdin_existente = 0 THEN 0 ELSE (A.rdin_cadastrada::numeric / A.rdin_existente::numeric) * 100 END)
    FROM operacao.rede_instalada A
         WHERE A.rdin_referencia = dataInicial;   

  --INDICADOR 201 - Prazo Médio de Atendimento de Ordens de Serviço ESGOTO
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 201 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         B.qtdDias, B.qtdOS, 
         (CASE WHEN B.qtdOS = 0 THEN 0 ELSE (B.qtdDias::numeric / B.qtdOS::numeric)  END)
    FROM (SELECT DISTINCT A.greg_id, B.uneg_id, E.muni_id, C.loca_id 
      FROM cadastro.gerencia_regional A 
     INNER JOIN cadastro.unidade_negocio B ON A.greg_id = B.greg_id 
     INNER JOIN cadastro.localidade C ON A.greg_id = C.greg_id AND B.uneg_id = C.uneg_ID 
     INNER JOIN cadastro.setor_comercial D ON C.loca_id = D.loca_id and d.stcm_icalternativo = 2
     INNER JOIN cadastro.municipio E ON D.muni_id = E.muni_id) AS A
   INNER JOIN (SELECT ra.loca_id, SUM(date_part('day', date_trunc('day', orse_tmencerramento) - date_trunc('day',orse_tmgeracao))) AS qtdDias, count(*) AS qtdOS  
           from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156 
           WHERE date(orse_tmencerramento) BETWEEN dataInicial AND dataFinal
      AND orse_tmgeracao > '20110101' 
      AND svtp_cdservicotipo = 'O' 
      AND (os.svtp_id=743 OR os.svtp_id=788 OR os.svtp_id=794 OR
           os.svtp_id=875 OR os.svtp_id=878 OR os.svtp_id=879 OR
           os.svtp_id=884 OR os.svtp_id=886 OR os.svtp_id=889 OR
           os.svtp_id=891 OR os.svtp_id=893 OR os.svtp_id=894 OR
           os.svtp_id=900 OR os.svtp_id=1012 OR os.svtp_id=1204 OR
           os.svtp_id=1205)
          GROUP BY ra.loca_id) AS B ON A.loca_id = B.loca_id;

  --INDICADOR 202 - Índice de Eficiência de Retirada de Vazamento (prazo: 24h) - %
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 202 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         operacao.ISNULL(C.qtdOSPrazo::numeric,0), operacao.ISNULL(B.qtdOSExec::numeric,0), 
         (CASE WHEN operacao.ISNULL(C.qtdOSPrazo,0) = 0 THEN 0 ELSE ( operacao.ISNULL(B.qtdOSExec::numeric,0) /  operacao.ISNULL(C.qtdOSPrazo::numeric,0)) * 100 END)
    FROM (SELECT DISTINCT A.greg_id, B.uneg_id, E.muni_id, C.loca_id 
      FROM cadastro.gerencia_regional A 
     INNER JOIN cadastro.unidade_negocio B ON A.greg_id = B.greg_id 
     INNER JOIN cadastro.localidade C ON A.greg_id = C.greg_id AND B.uneg_id = C.uneg_ID 
     INNER JOIN cadastro.setor_comercial D ON C.loca_id = D.loca_id and d.stcm_icalternativo = 2
     INNER JOIN cadastro.municipio E ON D.muni_id = E.muni_id) AS A
    LEFT JOIN (SELECT ra.loca_id, count(*) AS qtdOSExec  
            from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156
           WHERE date(orse_tmencerramento) BETWEEN dataInicial AND dataFinal 
       AND orse_tmgeracao > '20110101' 
       AND svtp_cdservicotipo= 'O' 
             AND (os.svtp_id=466 OR os.svtp_id=670 OR os.svtp_id=671 OR os.svtp_id=672 OR os.svtp_id=673 
                             OR os.svtp_id=697 OR os.svtp_id=698 OR os.svtp_id=699 OR os.svtp_id=956 OR os.svtp_id=957 
                             OR os.svtp_id=958 OR os.svtp_id=959 OR os.svtp_id=983 OR os.svtp_id=984 OR os.svtp_id=985)
           GROUP BY ra.loca_id) AS B ON A.loca_id = B.loca_id
          LEFT JOIN (SELECT ra.loca_id, COUNT(*) AS qtdOSPrazo  
            from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156
           WHERE date(orse_tmencerramento) BETWEEN dataInicial AND dataFinal 
             AND svtp_cdservicotipo= 'O'
             AND date_part('day', date_trunc('day', orse_tmencerramento) - date_trunc('day', orse_tmgeracao)) <= st.svtp_nntempomedioexecucao
             AND (os.svtp_id=466 OR os.svtp_id=670 OR os.svtp_id=671 OR os.svtp_id=672 OR os.svtp_id=673 
                             OR os.svtp_id=697 OR os.svtp_id=698 OR os.svtp_id=699 OR os.svtp_id=956 OR os.svtp_id=957 
                             OR os.svtp_id=958 OR os.svtp_id=959 OR os.svtp_id=983 OR os.svtp_id=984 OR os.svtp_id=985)
           GROUP BY ra.loca_id) AS C ON A.loca_id = C.loca_id;    

  --INDICADOR 203 - Índice de Eficiência de Retirada de Vazamento (prazo: 24h) - Nº Dias
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 203 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         operacao.ISNULL(B.qtdDias::numeric,0),  operacao.ISNULL(C.qtdOSExec::numeric,0), 
         (CASE WHEN operacao.ISNULL(C.qtdOSExec,0) = 0 THEN 0 ELSE ( operacao.ISNULL(B.qtdDias::numeric,0) /  operacao.ISNULL(C.qtdOSExec::numeric,0)) END)
    FROM (SELECT DISTINCT A.greg_id, B.uneg_id, E.muni_id, C.loca_id 
      FROM cadastro.gerencia_regional A 
     INNER JOIN cadastro.unidade_negocio B ON A.greg_id = B.greg_id 
     INNER JOIN cadastro.localidade C ON A.greg_id = C.greg_id AND B.uneg_id = C.uneg_ID 
     INNER JOIN cadastro.setor_comercial D ON C.loca_id = D.loca_id and d.stcm_icalternativo = 2
     INNER JOIN cadastro.municipio E ON D.muni_id = E.muni_id) AS A
    LEFT JOIN (SELECT ra.loca_id, SUM(date_part('day', date_trunc('day', orse_tmencerramento) - date_trunc('day', orse_tmgeracao))) AS qtdDias 
      from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156 
           WHERE date(orse_tmencerramento) BETWEEN dataInicial AND dataFinal
       AND orse_tmgeracao > '20110101' 
       AND svtp_cdservicotipo= 'O' 
       AND (os.svtp_id=466 OR os.svtp_id=670 OR os.svtp_id=671 OR os.svtp_id=672 OR os.svtp_id=673 
                             OR os.svtp_id=697 OR os.svtp_id=698 OR os.svtp_id=699 OR os.svtp_id=956 OR os.svtp_id=957 
                             OR os.svtp_id=958 OR os.svtp_id=959 OR os.svtp_id=983 OR os.svtp_id=984 OR os.svtp_id=985)
           GROUP BY ra.loca_id) AS B ON A.loca_id = B.loca_id
          LEFT JOIN (SELECT ra.loca_id, count(*) AS qtdOSExec  
            from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156
           WHERE date(orse_tmencerramento) BETWEEN dataInicial AND dataFinal 
       AND orse_tmgeracao > '20110101' 
       AND svtp_cdservicotipo= 'O' 
             AND (os.svtp_id=466 OR os.svtp_id=670 OR os.svtp_id=671 OR os.svtp_id=672 OR os.svtp_id=673 
                             OR os.svtp_id=697 OR os.svtp_id=698 OR os.svtp_id=699 OR os.svtp_id=956 OR os.svtp_id=957 
                             OR os.svtp_id=958 OR os.svtp_id=959 OR os.svtp_id=983 OR os.svtp_id=984 OR os.svtp_id=985)
           GROUP BY ra.loca_id) AS C ON A.loca_id = C.loca_id;    

  --INDICADOR 204 - Taxa de Retirada de Vazamento de ÁGUA
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 204 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         operacao.ISNULL(B.qtdOSExec::numeric,0),  operacao.ISNULL(C.qtdOS::numeric,0), 
         (CASE WHEN operacao.ISNULL(C.qtdOS,0) = 0 THEN 0 ELSE ( operacao.ISNULL(B.qtdOSExec::numeric,0) /  operacao.ISNULL(C.qtdOS::numeric,0)) * 100 END)
    FROM (SELECT DISTINCT A.greg_id, B.uneg_id, E.muni_id, C.loca_id 
      FROM cadastro.gerencia_regional A 
     INNER JOIN cadastro.unidade_negocio B ON A.greg_id = B.greg_id 
     INNER JOIN cadastro.localidade C ON A.greg_id = C.greg_id AND B.uneg_id = C.uneg_ID 
     INNER JOIN cadastro.setor_comercial D ON C.loca_id = D.loca_id and d.stcm_icalternativo = 2
     INNER JOIN cadastro.municipio E ON D.muni_id = E.muni_id) AS A
    LEFT JOIN (SELECT ra.loca_id, count(*) AS qtdOSExec  
      from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156 
            WHERE date(orse_tmencerramento) BETWEEN dataInicial AND dataFinal
       AND orse_tmgeracao > '20110101' 
       AND svtp_cdservicotipo= 'O' 
       AND (os.svtp_id=466 OR os.svtp_id=670 OR os.svtp_id=671 OR os.svtp_id=672 OR os.svtp_id=673 
                             OR os.svtp_id=697 OR os.svtp_id=698 OR os.svtp_id=699 OR os.svtp_id=956 OR os.svtp_id=957 
                             OR os.svtp_id=958 OR os.svtp_id=959 OR os.svtp_id=983 OR os.svtp_id=984 OR os.svtp_id=985)
           GROUP BY ra.loca_id) AS B ON A.loca_id = B.loca_id
          LEFT JOIN (SELECT ra.loca_id, count(*) AS qtdOS
            from atendimentopublico.ordem_servico os
            inner join atendimentopublico.registro_atendimento ra on ra.rgat_id = os.rgat_id
            inner join atendimentopublico.servico_tipo st on os.svtp_id = st.svtp_id
            inner join cadastro.localidade l on l.loca_id = ra.loca_id and  l.loca_icuso=1 and ra.loca_id <>156 
           WHERE date(orse_tmgeracao) BETWEEN dataInicial AND dataFinal
             AND svtp_cdservicotipo= 'O'
             AND (os.svtp_id=466 OR os.svtp_id=670 OR os.svtp_id=671 OR os.svtp_id=672 OR os.svtp_id=673 
                             OR os.svtp_id=697 OR os.svtp_id=698 OR os.svtp_id=699 OR os.svtp_id=956 OR os.svtp_id=957 
                             OR os.svtp_id=958 OR os.svtp_id=959 OR os.svtp_id=983 OR os.svtp_id=984 OR os.svtp_id=985)
           GROUP BY ra.loca_id) AS C ON A.loca_id = C.loca_id;    

  --INDICADOR 205 - Índice de Tratamento de ESGOTO
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 205 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         A.etev_volumetratado, A.etev_volumecoletado, 
         (CASE WHEN A.etev_volumecoletado = 0 THEN 0 ELSE (A.etev_volumetratado::numeric / A.etev_volumecoletado::numeric) * 100 END)
    FROM operacao.ete_volume A
         WHERE A.etev_referencia = dataInicial;  

  --INDICADOR 206 - Índice de Conformidade da Qualidade ÁGUA
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 206 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0, 
         A.anac_conformidade, A.anac_analisada, 
         (CASE WHEN A.anac_analisada = 0 THEN 0 ELSE (A.anac_conformidade::numeric / A.anac_analisada::numeric) * 100 END)
    FROM operacao.analise_clinica A
         WHERE A.anac_referencia = dataInicial;

   --INDICADOR 209 - Índice de  Macromedição Total
  INSERT INTO operacao.indicador_mensal
  SELECT A.eatv_referencia, 209 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.unop_tipooperacional, A.unop_id,
         operacao.ISNULL(A.volume_saida_eat_fixo::numeric, 0),
         operacao.ISNULL(B.volume_saida_eat_portatil::numeric, 0),
         (CASE WHEN operacao.ISNULL(volume_saida_eat_portatil::numeric,0) = 0 THEN 0 ELSE (operacao.ISNULL(volume_saida_eat_portatil::numeric,0) /  operacao.ISNULL(volume_saida_eat_portatil::numeric,0)) * 100 END)
    FROM (
  SELECT A.eatv_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eeat_id AS unop_id, SUM(F.eats_volume) AS volume_saida_eat_fixo
    FROM operacao.eeat_volume A
   INNER JOIN operacao.eeat_volume_saida F ON A.eatv_id = F.eatv_id
   INNER JOIN operacao.eeat B ON A.eeat_id = B.eeat_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eeat_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 3
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   INNER JOIN operacao.macro_medidor G ON F.mmed_idsaida = G.mmed_id
   WHERE G.mmed_tipo = 2
   GROUP BY A.eatv_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eeat_id) AS A
   LEFT JOIN (
  SELECT A.eatv_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eeat_id AS unop_id, SUM(F.eats_volume) AS volume_saida_eat_portatil
    FROM operacao.eeat_volume A
   INNER JOIN operacao.eeat_volume_saida F ON A.eatv_id = F.eatv_id
   INNER JOIN operacao.eeat B ON A.eeat_id = B.eeat_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eeat_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 3
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   INNER JOIN operacao.macro_medidor G ON F.mmed_idsaida = G.mmed_id
   WHERE G.mmed_tipo = 1
   GROUP BY A.eatv_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eeat_id) 
   AS B ON 
   A.greg_id = B.greg_id AND 
   A.uneg_id = B.uneg_id AND 
   A.muni_id = B.muni_id AND 
   A.loca_id = B.loca_id AND
   A.unop_id = B.unop_id AND 
   A.eatv_referencia = B.eatv_referencia
   WHERE A.eatv_referencia = dataInicial;

        --INDICADOR 210 - Índice de Perda Física de ÁGUA
  INSERT INTO operacao.indicador_mensal
  SELECT A.etav_referencia, 210 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, A.unop_tipooperacional, A.unop_id,
         A.volume_saida_eta - (A.volume_saida_eta - B.volume_saida_eat) - B.volume_saida_eat,
         A.volume_saida_eta - (A.volume_saida_eta - B.volume_saida_eat),
         (CASE WHEN operacao.ISNULL((A.volume_saida_eta - (A.volume_saida_eta - B.volume_saida_eat))::numeric,0) = 0 
         THEN 0 
         ELSE ( operacao.ISNULL( (A.volume_saida_eta - (A.volume_saida_eta - B.volume_saida_eat) - B.volume_saida_eat)::numeric,0) /  operacao.ISNULL((A.volume_saida_eta - (A.volume_saida_eta - B.volume_saida_eat))::numeric,0)) * 100 END)
    FROM (
  SELECT A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eta_id AS unop_id, SUM(F.etas_volume) AS volume_saida_eta
    FROM operacao.eta_volume A
   INNER JOIN operacao.eta_volume_saida F ON A.etav_id = F.etav_id
   INNER JOIN operacao.eta B ON A.eta_id = B.eta_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eta_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 2
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   GROUP BY A.etav_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eta_id) AS A
   LEFT JOIN (
  SELECT A.eatv_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, 2 AS unop_tipooperacional, B.eeat_id AS unop_id, SUM(F.eats_volume) AS volume_saida_eat
    FROM operacao.eeat_volume A
   INNER JOIN operacao.eeat_volume_saida F ON A.eatv_id = F.eatv_id
   INNER JOIN operacao.eeat B ON A.eeat_id = B.eeat_id 
   INNER JOIN operacao.unidade_consumidora_operacional C ON B.eeat_id = C.ucop_idoperacional AND C.ucop_tipooperacional = 3
   INNER JOIN operacao.unidade_consumidora D ON C.ucon_id = D.ucon_id
   INNER JOIN cadastro.unidade_negocio E ON D.uneg_id = E.uneg_id
   GROUP BY A.eatv_referencia, E.greg_id, D.uneg_id, D.muni_id, D.loca_id, B.eeat_id) AS B ON A.greg_id = B.greg_id AND A.uneg_id = B.uneg_id AND A.muni_id = B.muni_id AND A.loca_id = B.loca_id AND A.etav_referencia = B.eatv_referencia
   WHERE A.etav_referencia = dataInicial;


  --INDICADOR 211 - Índice de Clientes com Precariedade de Abastecimento de ÁGUA
  INSERT INTO operacao.indicador_mensal
  SELECT dataInicial AS referencia, 211 AS indicador, A.greg_id, A.uneg_id, A.muni_id, A.loca_id, 0 , 0,
             operacao.ISNULL(B.qtdPrecariedade::numeric,0),  operacao.ISNULL(C.qtdEconomias::numeric,0),
             (CASE WHEN operacao.ISNULL(C.qtdEconomias,0) = 0 THEN 0 ELSE ( operacao.ISNULL(B.qtdPrecariedade::numeric,0) /  operacao.ISNULL(C.qtdEconomias::numeric,0)) * 100 END)
        FROM (SELECT DISTINCT A.greg_id, B.uneg_id, E.muni_id, C.loca_id
              FROM cadastro.gerencia_regional A
             INNER JOIN cadastro.unidade_negocio B ON A.greg_id = B.greg_id
             INNER JOIN cadastro.localidade C ON A.greg_id = C.greg_id AND B.uneg_id = C.uneg_ID
             INNER JOIN cadastro.setor_comercial D ON C.loca_id = D.loca_id and d.stcm_icalternativo = 2
             INNER JOIN cadastro.municipio E ON D.muni_id = E.muni_id) AS A
        LEFT JOIN (SELECT loca_id, SUM(imov_qteconomia) as qtdPrecariedade
    FROM faturamento.fatur_situacao_hist, cadastro.imovel
                WHERE faturamento.fatur_situacao_hist.ftsm_id = 3
                  AND (ftsh_amfaturamentoretirada is null or ftsh_amfaturamentoretirada > referenciaAux)
                  AND ftsh_amfatmtsitinicio <= referenciaAux and ftsh_amfaturamentosituacaofim >= referenciaAux
                  AND faturamento.fatur_situacao_hist.imov_id = cadastro.imovel.imov_id
                  AND last_id=3
                GROUP BY loca_id) AS B ON A.loca_id = B.loca_id  
        LEFT JOIN (SELECT loca_id, qtdEconomias
                    FROM dblink('host=10.20.100.21 port=5432 dbname=gsan_gerencial user=gsan_operacao password=oper001',
                        'SELECT loca_id, SUM(rele_qteconomias) as qtdEconomias FROM cadastro.un_res_lig_econ WHERE last_id = 3 AND cast(rele_amreferencia as text) = ' || cast(referenciaAux as text)  || ' GROUP BY loca_id')
                        AS resultado(loca_id integer, qtdEconomias numeric)) 
                        AS C ON A.loca_id = C.loca_id;
END;  
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION operacao.geraindicador(date)
  OWNER TO gsan_admin;
