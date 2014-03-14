--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.2
-- Started on 2014-02-27 15:53:12 BRT

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 3673102)
-- Name: admindb; Type: SCHEMA; Schema: -; Owner: gsan_admin
--

CREATE SCHEMA admindb;


ALTER SCHEMA admindb OWNER TO gsan_admin;

--
-- TOC entry 7 (class 2615 OID 3673103)
-- Name: arrecadacao; Type: SCHEMA; Schema: -; Owner: gsan_admin
--

CREATE SCHEMA arrecadacao;


ALTER SCHEMA arrecadacao OWNER TO gsan_admin;

--
-- TOC entry 8 (class 2615 OID 3673104)
-- Name: atendimentopublico; Type: SCHEMA; Schema: -; Owner: gsan_admin
--

CREATE SCHEMA atendimentopublico;


ALTER SCHEMA atendimentopublico OWNER TO gsan_admin;

--
-- TOC entry 9 (class 2615 OID 3673105)
-- Name: cadastro; Type: SCHEMA; Schema: -; Owner: gsan_admin
--

CREATE SCHEMA cadastro;


ALTER SCHEMA cadastro OWNER TO gsan_admin;

--
-- TOC entry 10 (class 2615 OID 3673106)
-- Name: cobranca; Type: SCHEMA; Schema: -; Owner: gsan_admin
--

CREATE SCHEMA cobranca;


ALTER SCHEMA cobranca OWNER TO gsan_admin;

--
-- TOC entry 11 (class 2615 OID 3673107)
-- Name: corporativo; Type: SCHEMA; Schema: -; Owner: gsan_admin
--

CREATE SCHEMA corporativo;


ALTER SCHEMA corporativo OWNER TO gsan_admin;

--
-- TOC entry 12 (class 2615 OID 3673108)
-- Name: faturamento; Type: SCHEMA; Schema: -; Owner: gsan_admin
--

CREATE SCHEMA faturamento;


ALTER SCHEMA faturamento OWNER TO gsan_admin;

--
-- TOC entry 13 (class 2615 OID 3673109)
-- Name: financeiro; Type: SCHEMA; Schema: -; Owner: gsan_admin
--

CREATE SCHEMA financeiro;


ALTER SCHEMA financeiro OWNER TO gsan_admin;

--
-- TOC entry 14 (class 2615 OID 3673110)
-- Name: micromedicao; Type: SCHEMA; Schema: -; Owner: gsan_admin
--

CREATE SCHEMA micromedicao;


ALTER SCHEMA micromedicao OWNER TO gsan_admin;

--
-- TOC entry 15 (class 2615 OID 3673111)
-- Name: myschema; Type: SCHEMA; Schema: -; Owner: gsan_admin
--

CREATE SCHEMA myschema;


ALTER SCHEMA myschema OWNER TO gsan_admin;

--
-- TOC entry 16 (class 2615 OID 3673112)
-- Name: operacional; Type: SCHEMA; Schema: -; Owner: gsan_admin
--

CREATE SCHEMA operacional;


ALTER SCHEMA operacional OWNER TO gsan_admin;

--
-- TOC entry 483 (class 3079 OID 12595)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 5108 (class 0 OID 0)
-- Dependencies: 483
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 875 (class 1247 OID 3673115)
-- Name: bt_metap_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE bt_metap_type AS (
    magic integer,
    version integer,
    root integer,
    level integer,
    fastroot integer,
    fastlevel integer
);


ALTER TYPE public.bt_metap_type OWNER TO postgres;

--
-- TOC entry 878 (class 1247 OID 3673118)
-- Name: bt_page_items_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE bt_page_items_type AS (
    itemoffset integer,
    ctid tid,
    itemlen integer,
    nulls boolean,
    vars boolean,
    data text
);


ALTER TYPE public.bt_page_items_type OWNER TO postgres;

--
-- TOC entry 881 (class 1247 OID 3673121)
-- Name: bt_page_stats_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE bt_page_stats_type AS (
    blkno integer,
    type character(1),
    live_items integer,
    dead_items integer,
    avg_item_size double precision,
    page_size integer,
    free_size integer,
    btpo_prev integer,
    btpo_next integer,
    btpo integer,
    btpo_flags integer
);


ALTER TYPE public.bt_page_stats_type OWNER TO postgres;

--
-- TOC entry 884 (class 1247 OID 3673124)
-- Name: dblink_pkey_results; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE dblink_pkey_results AS (
    "position" integer,
    colname text
);


ALTER TYPE public.dblink_pkey_results OWNER TO postgres;

--
-- TOC entry 887 (class 1247 OID 3673127)
-- Name: hs_each; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE hs_each AS (
    key text,
    value text
);


ALTER TYPE public.hs_each OWNER TO postgres;

--
-- TOC entry 890 (class 1247 OID 3673128)
-- Name: lo; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN lo AS oid;


ALTER DOMAIN public.lo OWNER TO postgres;

--
-- TOC entry 891 (class 1247 OID 3673131)
-- Name: pgstatindex_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE pgstatindex_type AS (
    version integer,
    tree_level integer,
    index_size integer,
    root_block_no integer,
    internal_pages integer,
    leaf_pages integer,
    empty_pages integer,
    deleted_pages integer,
    avg_leaf_density double precision,
    leaf_fragmentation double precision
);


ALTER TYPE public.pgstatindex_type OWNER TO postgres;

--
-- TOC entry 894 (class 1247 OID 3673134)
-- Name: pgstattuple_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE pgstattuple_type AS (
    table_len bigint,
    tuple_count bigint,
    tuple_len bigint,
    tuple_percent double precision,
    dead_tuple_count bigint,
    dead_tuple_len bigint,
    dead_tuple_percent double precision,
    free_space bigint,
    free_percent double precision
);


ALTER TYPE public.pgstattuple_type OWNER TO postgres;

--
-- TOC entry 897 (class 1247 OID 3673137)
-- Name: statinfo; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE statinfo AS (
    word text,
    ndoc integer,
    nentry integer
);


ALTER TYPE public.statinfo OWNER TO postgres;

--
-- TOC entry 900 (class 1247 OID 3673140)
-- Name: tablefunc_crosstab_2; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE tablefunc_crosstab_2 AS (
    row_name text,
    category_1 text,
    category_2 text
);


ALTER TYPE public.tablefunc_crosstab_2 OWNER TO postgres;

--
-- TOC entry 903 (class 1247 OID 3673143)
-- Name: tablefunc_crosstab_3; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE tablefunc_crosstab_3 AS (
    row_name text,
    category_1 text,
    category_2 text,
    category_3 text
);


ALTER TYPE public.tablefunc_crosstab_3 OWNER TO postgres;

--
-- TOC entry 906 (class 1247 OID 3673150)
-- Name: tablefunc_crosstab_4; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE tablefunc_crosstab_4 AS (
    row_name text,
    category_1 text,
    category_2 text,
    category_3 text,
    category_4 text
);


ALTER TYPE public.tablefunc_crosstab_4 OWNER TO postgres;

--
-- TOC entry 909 (class 1247 OID 3673153)
-- Name: tokenout; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE tokenout AS (
    tokid integer,
    token text
);


ALTER TYPE public.tokenout OWNER TO postgres;

--
-- TOC entry 912 (class 1247 OID 3673156)
-- Name: tokentype; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE tokentype AS (
    tokid integer,
    alias text,
    descr text
);


ALTER TYPE public.tokentype OWNER TO postgres;

SET search_path = admindb, pg_catalog;

--
-- TOC entry 496 (class 1255 OID 3673157)
-- Name: registra_db_backup(integer, character varying, timestamp without time zone, timestamp without time zone, character varying, integer, text); Type: FUNCTION; Schema: admindb; Owner: postgres
--

CREATE FUNCTION registra_db_backup(icbackup integer, dsbackup character varying, tminicio timestamp without time zone, tmtermino timestamp without time zone, nntamarqbackup character varying, ictermino integer, dsobservacao text) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
  condicao integer;
  dtinicio timestamp without time zone;
  dtfim timestamp without time zone;
  tempo character varying(50);
BEGIN
dtinicio := tminicio;
dtfim := tmtermino;
tempo := dtfim - dtinicio;
INSERT INTO admindb.db_backup 
(dbbk_nmempresa, 
 dbbk_nmdatabase, 
 dbbk_icbackup, 
 dbbk_dsbackup, 
 dbbk_tminicio, 
 dbbk_tmtermino, 
 dbbk_hrduracao, 
 dbbk_nntamdatabase,
 dbbk_nntamarqbackup,
 dbdk_nmlogindba, 
 dbbk_ictermino,
 dbbk_dsobservacao)
VALUES
((select dbem_id from admindb.db_empresa),
 (select distinct catalog_name from information_schema.schemata),
 icbackup,
 dsbackup, 
 tminicio, 
 tmtermino, 
 tempo, 
 (select pg_size_pretty(sum(pg_total_relation_size(schemaname||'.'||tablename))::bigint) from pg_tables),
 nntamarqbackup,
 (select current_user),
 ictermino,
 dsobservacao);
condicao := 1;
RETURN condicao;
END;
$$;


ALTER FUNCTION admindb.registra_db_backup(icbackup integer, dsbackup character varying, tminicio timestamp without time zone, tmtermino timestamp without time zone, nntamarqbackup character varying, ictermino integer, dsobservacao text) OWNER TO postgres;

--
-- TOC entry 497 (class 1255 OID 3673158)
-- Name: registra_db_vacuum(integer, character varying, timestamp without time zone, timestamp without time zone, integer, text); Type: FUNCTION; Schema: admindb; Owner: postgres
--

CREATE FUNCTION registra_db_vacuum(icvacuum integer, dsvacuum character varying, tminicio timestamp without time zone, tmtermino timestamp without time zone, ictermino integer, dsobservacao text) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
  condicao integer;
  dtinicio timestamp without time zone;
  dtfim timestamp without time zone;
  tempo character varying(50);
BEGIN
dtinicio := tminicio;
dtfim := tmtermino;
tempo := dtfim - dtinicio;
INSERT INTO admindb.db_vacuum 
(dbva_nmempresa, 
 dbva_nmdatabase, 
 dbva_icvacuum, 
 dbva_dsvacuum, 
 dbva_tminicio, 
 dbva_tmtermino, 
 dbva_hrduracao, 
 dbva_nntamdatabase,
 dbva_nmlogindba, 
 dbva_ictermino,
 dbva_dsobservacao)
VALUES
((select dbem_id from admindb.db_empresa),
 (select distinct catalog_name from information_schema.schemata),
 icvacuum,
 dsvacuum, 
 tminicio, 
 tmtermino, 
 tempo, 
 (select pg_size_pretty(sum(pg_total_relation_size(schemaname||'.'||tablename))::bigint) from pg_tables),
 (select current_user),
 ictermino,
 dsobservacao);
condicao := 1;
RETURN condicao;
END;
$$;


ALTER FUNCTION admindb.registra_db_vacuum(icvacuum integer, dsvacuum character varying, tminicio timestamp without time zone, tmtermino timestamp without time zone, ictermino integer, dsobservacao text) OWNER TO postgres;

--
-- TOC entry 498 (class 1255 OID 3673159)
-- Name: registra_db_versao_base(date); Type: FUNCTION; Schema: admindb; Owner: postgres
--

CREATE FUNCTION registra_db_versao_base(dtversaobase date) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
  condicao integer;
BEGIN
INSERT INTO admindb.db_versao_base 
(dbvb_nmempresa,
 dbvb_nmdatabase,
 dbvb_dtversaobase,
 dbvb_nmlogindba)
VALUES
((select dbem_id from admindb.db_empresa),
 (select distinct catalog_name from information_schema.schemata),
 dtversaobase,
 (select current_user));
condicao := 1;
RETURN condicao;
END;
$$;


ALTER FUNCTION admindb.registra_db_versao_base(dtversaobase date) OWNER TO postgres;

--
-- TOC entry 499 (class 1255 OID 3673165)
-- Name: registra_db_versao_sincronismo(integer, date); Type: FUNCTION; Schema: admindb; Owner: postgres
--

CREATE FUNCTION registra_db_versao_sincronismo(icsincronismo integer, dtversaosincronismo date) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
  condicao integer;
BEGIN
INSERT INTO admindb.db_versao_sincronismo 

(dbvf_nmempresa,
 dbvf_nmdatabase,
 dbvf_icsincronismo,
 dbvf_dtversaosincronismo,
 dbvf_nmlogindba)
VALUES
((select dbem_id from admindb.db_empresa),
 (select distinct catalog_name from information_schema.schemata),
 icsincronismo,
 dtversaosincronismo,
 (select current_user));
condicao := 1;
RETURN condicao;
END;
$$;


ALTER FUNCTION admindb.registra_db_versao_sincronismo(icsincronismo integer, dtversaosincronismo date) OWNER TO postgres;

SET search_path = public, pg_catalog;

--
-- TOC entry 500 (class 1255 OID 3673166)
-- Name: _get_parser_from_curcfg(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION _get_parser_from_curcfg() RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $$ select prs_name from pg_ts_cfg where oid = show_curcfg() $$;


ALTER FUNCTION public._get_parser_from_curcfg() OWNER TO postgres;

--
-- TOC entry 503 (class 1255 OID 3673169)
-- Name: dblink(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink(text) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_record';


ALTER FUNCTION public.dblink(text) OWNER TO postgres;

--
-- TOC entry 501 (class 1255 OID 3673167)
-- Name: dblink(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink(text, text) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_record';


ALTER FUNCTION public.dblink(text, text) OWNER TO postgres;

--
-- TOC entry 504 (class 1255 OID 3673170)
-- Name: dblink(text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink(text, boolean) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_record';


ALTER FUNCTION public.dblink(text, boolean) OWNER TO postgres;

--
-- TOC entry 502 (class 1255 OID 3673168)
-- Name: dblink(text, text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink(text, text, boolean) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_record';


ALTER FUNCTION public.dblink(text, text, boolean) OWNER TO postgres;

--
-- TOC entry 505 (class 1255 OID 3673171)
-- Name: dblink_build_sql_delete(text, int2vector, integer, text[]); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_build_sql_delete(text, int2vector, integer, text[]) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_build_sql_delete';


ALTER FUNCTION public.dblink_build_sql_delete(text, int2vector, integer, text[]) OWNER TO postgres;

--
-- TOC entry 506 (class 1255 OID 3673172)
-- Name: dblink_build_sql_insert(text, int2vector, integer, text[], text[]); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_build_sql_insert(text, int2vector, integer, text[], text[]) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_build_sql_insert';


ALTER FUNCTION public.dblink_build_sql_insert(text, int2vector, integer, text[], text[]) OWNER TO postgres;

--
-- TOC entry 507 (class 1255 OID 3673178)
-- Name: dblink_build_sql_update(text, int2vector, integer, text[], text[]); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_build_sql_update(text, int2vector, integer, text[], text[]) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_build_sql_update';


ALTER FUNCTION public.dblink_build_sql_update(text, int2vector, integer, text[], text[]) OWNER TO postgres;

--
-- TOC entry 508 (class 1255 OID 3673179)
-- Name: dblink_cancel_query(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_cancel_query(text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_cancel_query';


ALTER FUNCTION public.dblink_cancel_query(text) OWNER TO postgres;

--
-- TOC entry 509 (class 1255 OID 3673180)
-- Name: dblink_close(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_close(text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_close';


ALTER FUNCTION public.dblink_close(text) OWNER TO postgres;

--
-- TOC entry 510 (class 1255 OID 3673181)
-- Name: dblink_close(text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_close(text, boolean) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_close';


ALTER FUNCTION public.dblink_close(text, boolean) OWNER TO postgres;

--
-- TOC entry 511 (class 1255 OID 3673182)
-- Name: dblink_close(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_close(text, text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_close';


ALTER FUNCTION public.dblink_close(text, text) OWNER TO postgres;

--
-- TOC entry 512 (class 1255 OID 3673183)
-- Name: dblink_close(text, text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_close(text, text, boolean) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_close';


ALTER FUNCTION public.dblink_close(text, text, boolean) OWNER TO postgres;

--
-- TOC entry 513 (class 1255 OID 3673184)
-- Name: dblink_connect(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_connect(text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_connect';


ALTER FUNCTION public.dblink_connect(text) OWNER TO postgres;

--
-- TOC entry 514 (class 1255 OID 3673185)
-- Name: dblink_connect(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_connect(text, text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_connect';


ALTER FUNCTION public.dblink_connect(text, text) OWNER TO postgres;

--
-- TOC entry 515 (class 1255 OID 3673193)
-- Name: dblink_current_query(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_current_query() RETURNS text
    LANGUAGE c
    AS '$libdir/dblink', 'dblink_current_query';


ALTER FUNCTION public.dblink_current_query() OWNER TO postgres;

--
-- TOC entry 516 (class 1255 OID 3673194)
-- Name: dblink_disconnect(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_disconnect() RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_disconnect';


ALTER FUNCTION public.dblink_disconnect() OWNER TO postgres;

--
-- TOC entry 517 (class 1255 OID 3673195)
-- Name: dblink_disconnect(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_disconnect(text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_disconnect';


ALTER FUNCTION public.dblink_disconnect(text) OWNER TO postgres;

--
-- TOC entry 518 (class 1255 OID 3673196)
-- Name: dblink_error_message(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_error_message(text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_error_message';


ALTER FUNCTION public.dblink_error_message(text) OWNER TO postgres;

--
-- TOC entry 521 (class 1255 OID 3673199)
-- Name: dblink_exec(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_exec(text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_exec';


ALTER FUNCTION public.dblink_exec(text) OWNER TO postgres;

--
-- TOC entry 519 (class 1255 OID 3673197)
-- Name: dblink_exec(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_exec(text, text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_exec';


ALTER FUNCTION public.dblink_exec(text, text) OWNER TO postgres;

--
-- TOC entry 522 (class 1255 OID 3673200)
-- Name: dblink_exec(text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_exec(text, boolean) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_exec';


ALTER FUNCTION public.dblink_exec(text, boolean) OWNER TO postgres;

--
-- TOC entry 520 (class 1255 OID 3673198)
-- Name: dblink_exec(text, text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_exec(text, text, boolean) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_exec';


ALTER FUNCTION public.dblink_exec(text, text, boolean) OWNER TO postgres;

--
-- TOC entry 523 (class 1255 OID 3673201)
-- Name: dblink_fetch(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_fetch(text, integer) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_fetch';


ALTER FUNCTION public.dblink_fetch(text, integer) OWNER TO postgres;

--
-- TOC entry 524 (class 1255 OID 3673202)
-- Name: dblink_fetch(text, integer, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_fetch(text, integer, boolean) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_fetch';


ALTER FUNCTION public.dblink_fetch(text, integer, boolean) OWNER TO postgres;

--
-- TOC entry 525 (class 1255 OID 3673203)
-- Name: dblink_fetch(text, text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_fetch(text, text, integer) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_fetch';


ALTER FUNCTION public.dblink_fetch(text, text, integer) OWNER TO postgres;

--
-- TOC entry 526 (class 1255 OID 3673210)
-- Name: dblink_fetch(text, text, integer, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_fetch(text, text, integer, boolean) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_fetch';


ALTER FUNCTION public.dblink_fetch(text, text, integer, boolean) OWNER TO postgres;

--
-- TOC entry 527 (class 1255 OID 3673211)
-- Name: dblink_get_connections(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_get_connections() RETURNS text[]
    LANGUAGE c
    AS '$libdir/dblink', 'dblink_get_connections';


ALTER FUNCTION public.dblink_get_connections() OWNER TO postgres;

--
-- TOC entry 528 (class 1255 OID 3673212)
-- Name: dblink_get_pkey(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_get_pkey(text) RETURNS SETOF dblink_pkey_results
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_get_pkey';


ALTER FUNCTION public.dblink_get_pkey(text) OWNER TO postgres;

--
-- TOC entry 529 (class 1255 OID 3673213)
-- Name: dblink_get_result(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_get_result(text) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_get_result';


ALTER FUNCTION public.dblink_get_result(text) OWNER TO postgres;

--
-- TOC entry 530 (class 1255 OID 3673214)
-- Name: dblink_get_result(text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_get_result(text, boolean) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_get_result';


ALTER FUNCTION public.dblink_get_result(text, boolean) OWNER TO postgres;

--
-- TOC entry 531 (class 1255 OID 3673215)
-- Name: dblink_is_busy(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_is_busy(text) RETURNS integer
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_is_busy';


ALTER FUNCTION public.dblink_is_busy(text) OWNER TO postgres;

--
-- TOC entry 532 (class 1255 OID 3673216)
-- Name: dblink_open(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_open(text, text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_open';


ALTER FUNCTION public.dblink_open(text, text) OWNER TO postgres;

--
-- TOC entry 533 (class 1255 OID 3673217)
-- Name: dblink_open(text, text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_open(text, text, boolean) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_open';


ALTER FUNCTION public.dblink_open(text, text, boolean) OWNER TO postgres;

--
-- TOC entry 534 (class 1255 OID 3673218)
-- Name: dblink_open(text, text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_open(text, text, text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_open';


ALTER FUNCTION public.dblink_open(text, text, text) OWNER TO postgres;

--
-- TOC entry 535 (class 1255 OID 3673219)
-- Name: dblink_open(text, text, text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_open(text, text, text, boolean) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_open';


ALTER FUNCTION public.dblink_open(text, text, text, boolean) OWNER TO postgres;

--
-- TOC entry 536 (class 1255 OID 3673220)
-- Name: dblink_send_query(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_send_query(text, text) RETURNS integer
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_send_query';


ALTER FUNCTION public.dblink_send_query(text, text) OWNER TO postgres;

--
-- TOC entry 537 (class 1255 OID 3673221)
-- Name: earth(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION earth() RETURNS double precision
    LANGUAGE sql IMMUTABLE
    AS $$SELECT '6378168'::float8$$;


ALTER FUNCTION public.earth() OWNER TO postgres;

--
-- TOC entry 538 (class 1255 OID 3673222)
-- Name: gc_to_sec(double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gc_to_sec(double precision) RETURNS double precision
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT CASE WHEN $1 < 0 THEN 0::float8 WHEN $1/earth() > pi() THEN 2*earth() ELSE 2*earth()*sin($1/(2*earth())) END$_$;


ALTER FUNCTION public.gc_to_sec(double precision) OWNER TO postgres;

--
-- TOC entry 539 (class 1255 OID 3673223)
-- Name: lo_oid(lo); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION lo_oid(lo) RETURNS oid
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT $1::pg_catalog.oid$_$;


ALTER FUNCTION public.lo_oid(lo) OWNER TO postgres;

--
-- TOC entry 540 (class 1255 OID 3673224)
-- Name: plpgsql_call_handler(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION plpgsql_call_handler() RETURNS language_handler
    LANGUAGE c
    AS '$libdir/plpgsql', 'plpgsql_call_handler';


ALTER FUNCTION public.plpgsql_call_handler() OWNER TO postgres;

--
-- TOC entry 541 (class 1255 OID 3673225)
-- Name: sec_to_gc(double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sec_to_gc(double precision) RETURNS double precision
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT CASE WHEN $1 < 0 THEN 0::float8 WHEN $1/(2*earth()) > 1 THEN pi()*earth() ELSE 2*earth()*asin($1/(2*earth())) END$_$;


ALTER FUNCTION public.sec_to_gc(double precision) OWNER TO postgres;

--
-- TOC entry 542 (class 1255 OID 3673226)
-- Name: xpath_list(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION xpath_list(text, text) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT xpath_list($1,$2,',')$_$;


ALTER FUNCTION public.xpath_list(text, text) OWNER TO postgres;

--
-- TOC entry 543 (class 1255 OID 3673227)
-- Name: xpath_nodeset(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION xpath_nodeset(text, text) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT xpath_nodeset($1,$2,'','')$_$;


ALTER FUNCTION public.xpath_nodeset(text, text) OWNER TO postgres;

--
-- TOC entry 544 (class 1255 OID 3673228)
-- Name: xpath_nodeset(text, text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION xpath_nodeset(text, text, text) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT xpath_nodeset($1,$2,'',$3)$_$;


ALTER FUNCTION public.xpath_nodeset(text, text, text) OWNER TO postgres;

SET search_path = admindb, pg_catalog;

--
-- TOC entry 192 (class 1259 OID 3673229)
-- Name: sequence_db_backup; Type: SEQUENCE; Schema: admindb; Owner: gsan_admin
--

CREATE SEQUENCE sequence_db_backup
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE admindb.sequence_db_backup OWNER TO gsan_admin;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 193 (class 1259 OID 3673231)
-- Name: db_backup; Type: TABLE; Schema: admindb; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE db_backup (
    dbbk_id integer DEFAULT nextval('sequence_db_backup'::regclass) NOT NULL,
    dbbk_nmempresa character varying(10) NOT NULL,
    dbbk_nmdatabase character varying(50) NOT NULL,
    dbbk_icbackup smallint NOT NULL,
    dbbk_dsbackup character varying(50),
    dbbk_tminicio timestamp without time zone,
    dbbk_tmtermino timestamp without time zone,
    dbbk_hrduracao character varying(50),
    dbbk_nntamdatabase character varying(10),
    dbbk_nntamarqbackup character varying(10),
    dbdk_nmlogindba character varying(10) NOT NULL,
    dbbk_ictermino smallint DEFAULT 1 NOT NULL,
    dbbk_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    dbbk_dsobservacao text
);


ALTER TABLE admindb.db_backup OWNER TO gsan_admin;

--
-- TOC entry 5110 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbbk_id; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbbk_id IS 'Id de db_backup';


--
-- TOC entry 5111 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbbk_nmempresa; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbbk_nmempresa IS 'Nome da empresa';


--
-- TOC entry 5112 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbbk_nmdatabase; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbbk_nmdatabase IS 'Nome do banco de dados';


--
-- TOC entry 5113 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbbk_icbackup; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbbk_icbackup IS 'Tipo do Backup: 1 = Backup Logico (pg_dump) , 2 = Backup Fisico (tar -czvf)';


--
-- TOC entry 5114 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbbk_dsbackup; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbbk_dsbackup IS 'Descricao do backup';


--
-- TOC entry 5115 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbbk_tminicio; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbbk_tminicio IS 'Data/Hora de inicio';


--
-- TOC entry 5116 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbbk_tmtermino; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbbk_tmtermino IS 'Data/Hora de termino';


--
-- TOC entry 5117 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbbk_hrduracao; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbbk_hrduracao IS 'Tempo total para realizar o backup';


--
-- TOC entry 5118 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbbk_nntamdatabase; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbbk_nntamdatabase IS 'Tamanho da base de dados';


--
-- TOC entry 5119 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbbk_nntamarqbackup; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbbk_nntamarqbackup IS 'Tamanho do arquivo backup gerado';


--
-- TOC entry 5120 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbdk_nmlogindba; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbdk_nmlogindba IS 'Nome login do DBA que executou o backup';


--
-- TOC entry 5121 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbbk_ictermino; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbbk_ictermino IS 'Indicador de condicao de termino: 1 = Normal, 2 = Com Anormalidade';


--
-- TOC entry 5122 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbbk_tmultimaalteracao; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbbk_tmultimaalteracao IS 'Momento da ultima execucao';


--
-- TOC entry 5123 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN db_backup.dbbk_dsobservacao; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_backup.dbbk_dsobservacao IS 'Observacao da condicao de termino/anormalidades';


--
-- TOC entry 194 (class 1259 OID 3673243)
-- Name: db_empresa; Type: TABLE; Schema: admindb; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE db_empresa (
    dbem_id character varying(10) NOT NULL,
    dbem_nmempresa character varying(100) NOT NULL,
    dbem_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE admindb.db_empresa OWNER TO gsan_admin;

--
-- TOC entry 5125 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN db_empresa.dbem_id; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_empresa.dbem_id IS 'Id nome abreviado empresa';


--
-- TOC entry 5126 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN db_empresa.dbem_nmempresa; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_empresa.dbem_nmempresa IS 'Nome da empresa';


--
-- TOC entry 5127 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN db_empresa.dbem_tmultimaalteracao; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_empresa.dbem_tmultimaalteracao IS 'Momento da ultima alteracao';


--
-- TOC entry 195 (class 1259 OID 3673247)
-- Name: db_index; Type: TABLE; Schema: admindb; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE db_index (
    schemaname name,
    relname name,
    indexrelname name,
    idx_scan bigint,
    idx_tup_read bigint,
    idx_tup_fetch bigint,
    data_atualizacao timestamp without time zone
);


ALTER TABLE admindb.db_index OWNER TO gsan_admin;

--
-- TOC entry 196 (class 1259 OID 3673250)
-- Name: db_index_jan2010; Type: TABLE; Schema: admindb; Owner: postgres; Tablespace: 
--

CREATE TABLE db_index_jan2010 (
    schemaname name,
    relname name,
    indexrelname name,
    idx_scan bigint,
    idx_tup_read bigint,
    idx_tup_fetch bigint
);


ALTER TABLE admindb.db_index_jan2010 OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 3673253)
-- Name: sequence_db_vacuum; Type: SEQUENCE; Schema: admindb; Owner: gsan_admin
--

CREATE SEQUENCE sequence_db_vacuum
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE admindb.sequence_db_vacuum OWNER TO gsan_admin;

--
-- TOC entry 198 (class 1259 OID 3673255)
-- Name: db_vacuum; Type: TABLE; Schema: admindb; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE db_vacuum (
    dbva_id integer DEFAULT nextval('sequence_db_vacuum'::regclass) NOT NULL,
    dbva_nmempresa character varying(10) NOT NULL,
    dbva_nmdatabase character varying(50) NOT NULL,
    dbva_icvacuum smallint NOT NULL,
    dbva_dsvacuum character varying(50),
    dbva_tminicio timestamp without time zone,
    dbva_tmtermino timestamp without time zone,
    dbva_hrduracao character varying(50),
    dbva_nntamdatabase character varying(10),
    dbva_nmlogindba character varying(10) NOT NULL,
    dbva_ictermino smallint DEFAULT 1 NOT NULL,
    dbva_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    dbva_dsobservacao text
);


ALTER TABLE admindb.db_vacuum OWNER TO gsan_admin;

--
-- TOC entry 5131 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN db_vacuum.dbva_id; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_vacuum.dbva_id IS 'Id de db_vacuum';


--
-- TOC entry 5132 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN db_vacuum.dbva_nmempresa; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_vacuum.dbva_nmempresa IS 'Nome da empresa';


--
-- TOC entry 5133 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN db_vacuum.dbva_nmdatabase; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_vacuum.dbva_nmdatabase IS 'Nome do banco de dados';


--
-- TOC entry 5134 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN db_vacuum.dbva_icvacuum; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_vacuum.dbva_icvacuum IS 'Tipo do vacuum: 1 = vacuum simples , 2 = vacuum analyze, 3 = vacuum full, 4 = vacuum full analyze, 5 = somente analyze';


--
-- TOC entry 5135 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN db_vacuum.dbva_dsvacuum; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_vacuum.dbva_dsvacuum IS 'Descricao do vacuum';


--
-- TOC entry 5136 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN db_vacuum.dbva_tminicio; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_vacuum.dbva_tminicio IS 'Data/Hora de inicio';


--
-- TOC entry 5137 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN db_vacuum.dbva_tmtermino; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_vacuum.dbva_tmtermino IS 'Data/Hora de termino';


--
-- TOC entry 5138 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN db_vacuum.dbva_hrduracao; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_vacuum.dbva_hrduracao IS 'Tempo total para realizar o vacuum';


--
-- TOC entry 5139 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN db_vacuum.dbva_nntamdatabase; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_vacuum.dbva_nntamdatabase IS 'Tamanho da base de dados';


--
-- TOC entry 5140 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN db_vacuum.dbva_nmlogindba; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_vacuum.dbva_nmlogindba IS 'Nome login do DBA que executou o vacuum';


--
-- TOC entry 5141 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN db_vacuum.dbva_ictermino; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_vacuum.dbva_ictermino IS 'Indicador de condicao de termino: 1 = Normal, 2 = Com Anormalidade';


--
-- TOC entry 5142 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN db_vacuum.dbva_tmultimaalteracao; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_vacuum.dbva_tmultimaalteracao IS 'Momento da ultima execucao';


--
-- TOC entry 5143 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN db_vacuum.dbva_dsobservacao; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_vacuum.dbva_dsobservacao IS 'Observacao da condicao de termino/anormalidades';


--
-- TOC entry 199 (class 1259 OID 3673264)
-- Name: sequence_db_versao_base; Type: SEQUENCE; Schema: admindb; Owner: gsan_admin
--

CREATE SEQUENCE sequence_db_versao_base
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE admindb.sequence_db_versao_base OWNER TO gsan_admin;

--
-- TOC entry 200 (class 1259 OID 3673272)
-- Name: db_versao_base; Type: TABLE; Schema: admindb; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE db_versao_base (
    dbvb_id integer DEFAULT nextval('sequence_db_versao_base'::regclass) NOT NULL,
    dbvb_nmempresa character varying(10) NOT NULL,
    dbvb_nmdatabase character varying(50) NOT NULL,
    dbvb_dtversaobase date NOT NULL,
    dbvb_nmlogindba character varying(10) NOT NULL,
    dbvb_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    dbvb_dsversaoaplicacao character varying(10)
);


ALTER TABLE admindb.db_versao_base OWNER TO gsan_admin;

--
-- TOC entry 5146 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN db_versao_base.dbvb_id; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_base.dbvb_id IS 'Id de db_versao_base';


--
-- TOC entry 5147 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN db_versao_base.dbvb_nmempresa; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_base.dbvb_nmempresa IS 'Nome da empresa';


--
-- TOC entry 5148 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN db_versao_base.dbvb_nmdatabase; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_base.dbvb_nmdatabase IS 'Nome da base de dados';


--
-- TOC entry 5149 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN db_versao_base.dbvb_dtversaobase; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_base.dbvb_dtversaobase IS 'Versao da base de dados';


--
-- TOC entry 5150 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN db_versao_base.dbvb_nmlogindba; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_base.dbvb_nmlogindba IS 'Nome login do DBA que executou o vacuum';


--
-- TOC entry 5151 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN db_versao_base.dbvb_tmultimaalteracao; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_base.dbvb_tmultimaalteracao IS 'Momento da execucao do script';


--
-- TOC entry 5152 (class 0 OID 0)
-- Dependencies: 200
-- Name: COLUMN db_versao_base.dbvb_dsversaoaplicacao; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_base.dbvb_dsversaoaplicacao IS 'Versão da aplicação batch e online compativel com a base publicada';


--
-- TOC entry 201 (class 1259 OID 3673277)
-- Name: sequence_db_versao_sincronismo; Type: SEQUENCE; Schema: admindb; Owner: gsan_admin
--

CREATE SEQUENCE sequence_db_versao_sincronismo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE admindb.sequence_db_versao_sincronismo OWNER TO gsan_admin;

--
-- TOC entry 202 (class 1259 OID 3673279)
-- Name: db_versao_sincronismo; Type: TABLE; Schema: admindb; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE db_versao_sincronismo (
    dbvf_id integer DEFAULT nextval('sequence_db_versao_sincronismo'::regclass) NOT NULL,
    dbvf_nmempresa character varying(10) NOT NULL,
    dbvf_nmdatabase character varying(50) NOT NULL,
    dbvf_icsincronismo integer DEFAULT 1 NOT NULL,
    dbvf_dtversaosincronismo date NOT NULL,
    dbvf_nmlogindba character varying(10) NOT NULL,
    dbvf_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    dbvf_dsversaoaplicacao character varying(10)
);


ALTER TABLE admindb.db_versao_sincronismo OWNER TO gsan_admin;

--
-- TOC entry 5155 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN db_versao_sincronismo.dbvf_id; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_sincronismo.dbvf_id IS 'Id de db_versao_sincronismo';


--
-- TOC entry 5156 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN db_versao_sincronismo.dbvf_nmempresa; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_sincronismo.dbvf_nmempresa IS 'Nome da empresa';


--
-- TOC entry 5157 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN db_versao_sincronismo.dbvf_nmdatabase; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_sincronismo.dbvf_nmdatabase IS 'Nome da base de dados';


--
-- TOC entry 5158 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN db_versao_sincronismo.dbvf_icsincronismo; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_sincronismo.dbvf_icsincronismo IS '1 = Sincronismo das Funcionalidades, 2 = Sincronismo Basica Gerencial';


--
-- TOC entry 5159 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN db_versao_sincronismo.dbvf_dtversaosincronismo; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_sincronismo.dbvf_dtversaosincronismo IS 'Versao do Sincronismo';


--
-- TOC entry 5160 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN db_versao_sincronismo.dbvf_nmlogindba; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_sincronismo.dbvf_nmlogindba IS 'Nome login do DBA que executou o vacuum';


--
-- TOC entry 5161 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN db_versao_sincronismo.dbvf_tmultimaalteracao; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_sincronismo.dbvf_tmultimaalteracao IS 'Momento da execucao do script';


--
-- TOC entry 5162 (class 0 OID 0)
-- Dependencies: 202
-- Name: COLUMN db_versao_sincronismo.dbvf_dsversaoaplicacao; Type: COMMENT; Schema: admindb; Owner: gsan_admin
--

COMMENT ON COLUMN db_versao_sincronismo.dbvf_dsversaoaplicacao IS 'Versão da aplicação batch e online compativel com o sincronismo publicado.';


--
-- TOC entry 203 (class 1259 OID 3673285)
-- Name: sequence_db_query_start; Type: SEQUENCE; Schema: admindb; Owner: gsan_admin
--

CREATE SEQUENCE sequence_db_query_start
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE admindb.sequence_db_query_start OWNER TO gsan_admin;

--
-- TOC entry 204 (class 1259 OID 3673287)
-- Name: vw_gerencial_caema_db_backup; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_caema_db_backup AS
    SELECT table1.dbbk_id, table1.dbbk_nmempresa, table1.dbbk_nmdatabase, CASE WHEN (table1.dbbk_icbackup = 1) THEN 'Backup Logico'::text WHEN (table1.dbbk_icbackup = 2) THEN 'Backup Fisico'::text ELSE 'Nao Definido'::text END AS tipobackup, table1.dbbk_dsbackup, table1.dbbk_tminicio, table1.dbbk_tmtermino, table1.dbbk_hrduracao, table1.dbbk_nntamdatabase, table1.dbbk_nntamarqbackup, table1.dbdk_nmlogindba, CASE WHEN (table1.dbbk_ictermino = 1) THEN 'Normal'::text ELSE 'Com Anormalidade'::text END AS condicaotermino, table1.dbbk_tmultimaalteracao, table1.dbbk_dsobservacao FROM public.dblink('dbname=gsan_gerencial hostaddr=200.167.148.214 user=gsan_dba password= db@. port=5432'::text, 'select * from admindb.db_backup'::text) table1(dbbk_id integer, dbbk_nmempresa character varying(10), dbbk_nmdatabase character varying(50), dbbk_icbackup smallint, dbbk_dsbackup character varying(50), dbbk_tminicio timestamp without time zone, dbbk_tmtermino timestamp without time zone, dbbk_hrduracao character varying(50), dbbk_nntamdatabase character varying(10), dbbk_nntamarqbackup character varying(10), dbdk_nmlogindba character varying(10), dbbk_ictermino smallint, dbbk_tmultimaalteracao timestamp without time zone, dbbk_dsobservacao text);


ALTER TABLE admindb.vw_gerencial_caema_db_backup OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 3673296)
-- Name: vw_gerencial_caema_db_vacuum; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_caema_db_vacuum AS
    SELECT table1.dbva_id, table1.dbva_nmempresa, table1.dbva_nmdatabase, CASE WHEN (table1.dbva_icvacuum = 1) THEN 'Vacuum Simples'::text WHEN (table1.dbva_icvacuum = 2) THEN 'Vacuum Analyze'::text WHEN (table1.dbva_icvacuum = 3) THEN 'Vacuum Full'::text WHEN (table1.dbva_icvacuum = 4) THEN 'Vacuum Full Analyze'::text WHEN (table1.dbva_icvacuum = 5) THEN 'Somente Analyze'::text ELSE 'Nao Definido'::text END AS tipovacuum, table1.dbva_dsvacuum, table1.dbva_tminicio, table1.dbva_tmtermino, table1.dbva_hrduracao, table1.dbva_nntamdatabase, table1.dbva_nmlogindba, CASE WHEN (table1.dbva_ictermino = 1) THEN 'Normal'::text ELSE 'Com Anormalidade'::text END AS condicaotermino, table1.dbva_tmultimaalteracao, table1.dbva_dsobservacao FROM public.dblink('dbname=gsan_gerencial hostaddr=200.167.148.214 user=gsan_dba password= db@. port=5432'::text, 'select * from admindb.db_vacuum'::text) table1(dbva_id integer, dbva_nmempresa character varying(10), dbva_nmdatabase character varying(50), dbva_icvacuum smallint, dbva_dsvacuum character varying(50), dbva_tminicio timestamp without time zone, dbva_tmtermino timestamp without time zone, dbva_hrduracao character varying(50), dbva_nntamdatabase character varying(10), dbva_nmlogindba character varying(10), dbva_ictermino smallint, dbva_tmultimaalteracao timestamp without time zone, dbva_dsobservacao text);


ALTER TABLE admindb.vw_gerencial_caema_db_vacuum OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 3673301)
-- Name: vw_gerencial_caema_db_versao_base; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_caema_db_versao_base AS
    SELECT table1.dbvb_id, table1.dbvb_nmempresa, table1.dbvb_nmdatabase, table1.dbvb_dtversaobase, table1.dbvb_nmlogindba, table1.dbvb_tmultimaalteracao FROM public.dblink('dbname=gsan_gerencial hostaddr=200.167.148.214 user=gsan_dba password= db@. port=5432'::text, 'select * from admindb.db_versao_base'::text) table1(dbvb_id integer, dbvb_nmempresa character varying(10), dbvb_nmdatabase character varying(50), dbvb_dtversaobase date, dbvb_nmlogindba character varying(10), dbvb_tmultimaalteracao timestamp without time zone);


ALTER TABLE admindb.vw_gerencial_caema_db_versao_base OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 3673305)
-- Name: vw_gerencial_caema_db_versao_sincronismo; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_caema_db_versao_sincronismo AS
    SELECT table1.dbvf_id, table1.dbvf_nmempresa, table1.dbvf_nmdatabase, CASE WHEN (table1.dbvf_icsincronismo = 1) THEN 'Sincronismo das Funcionalidades'::text WHEN (table1.dbvf_icsincronismo = 2) THEN 'Sincronismo Basicas do Gerencial'::text ELSE 'Nao Definido'::text END AS tiposincronismo, table1.dbvf_dtversaosincronismo, table1.dbvf_nmlogindba, table1.dbvf_tmultimaalteracao FROM public.dblink('dbname=gsan_gerencial hostaddr=200.167.148.214 user=gsan_dba password= db@. port=5432'::text, 'select * from admindb.db_versao_sincronismo'::text) table1(dbvf_id integer, dbvf_nmempresa character varying(10), dbvf_nmdatabase character varying(50), dbvf_icsincronismo integer, dbvf_dtversaosincronismo date, dbvf_nmlogindba character varying(10), dbvf_tmultimaalteracao timestamp without time zone);


ALTER TABLE admindb.vw_gerencial_caema_db_versao_sincronismo OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 3673309)
-- Name: vw_gerencial_caer_db_backup; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_caer_db_backup AS
    SELECT table1.dbbk_id, table1.dbbk_nmempresa, table1.dbbk_nmdatabase, CASE WHEN (table1.dbbk_icbackup = 1) THEN 'Backup Logico'::text WHEN (table1.dbbk_icbackup = 2) THEN 'Backup Fisico'::text ELSE 'Nao Definido'::text END AS tipobackup, table1.dbbk_dsbackup, table1.dbbk_tminicio, table1.dbbk_tmtermino, table1.dbbk_hrduracao, table1.dbbk_nntamdatabase, table1.dbbk_nntamarqbackup, table1.dbdk_nmlogindba, CASE WHEN (table1.dbbk_ictermino = 1) THEN 'Normal'::text ELSE 'Com Anormalidade'::text END AS condicaotermino, table1.dbbk_tmultimaalteracao, table1.dbbk_dsobservacao FROM public.dblink('dbname=gsan_gerencial hostaddr=201.56.20.62 user=gsan_dba password= db@. port=12345'::text, 'select * from admindb.db_backup'::text) table1(dbbk_id integer, dbbk_nmempresa character varying(10), dbbk_nmdatabase character varying(50), dbbk_icbackup smallint, dbbk_dsbackup character varying(50), dbbk_tminicio timestamp without time zone, dbbk_tmtermino timestamp without time zone, dbbk_hrduracao character varying(50), dbbk_nntamdatabase character varying(10), dbbk_nntamarqbackup character varying(10), dbdk_nmlogindba character varying(10), dbbk_ictermino smallint, dbbk_tmultimaalteracao timestamp without time zone, dbbk_dsobservacao text);


ALTER TABLE admindb.vw_gerencial_caer_db_backup OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 3673314)
-- Name: vw_gerencial_caer_db_vacuum; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_caer_db_vacuum AS
    SELECT table1.dbva_id, table1.dbva_nmempresa, table1.dbva_nmdatabase, CASE WHEN (table1.dbva_icvacuum = 1) THEN 'Vacuum Simples'::text WHEN (table1.dbva_icvacuum = 2) THEN 'Vacuum Analyze'::text WHEN (table1.dbva_icvacuum = 3) THEN 'Vacuum Full'::text WHEN (table1.dbva_icvacuum = 4) THEN 'Vacuum Full Analyze'::text WHEN (table1.dbva_icvacuum = 5) THEN 'Somente Analyze'::text ELSE 'Nao Definido'::text END AS tipovacuum, table1.dbva_dsvacuum, table1.dbva_tminicio, table1.dbva_tmtermino, table1.dbva_hrduracao, table1.dbva_nntamdatabase, table1.dbva_nmlogindba, CASE WHEN (table1.dbva_ictermino = 1) THEN 'Normal'::text ELSE 'Com Anormalidade'::text END AS condicaotermino, table1.dbva_tmultimaalteracao, table1.dbva_dsobservacao FROM public.dblink('dbname=gsan_gerencial hostaddr=201.56.20.62 user=gsan_dba password= db@. port=12345'::text, 'select * from admindb.db_vacuum'::text) table1(dbva_id integer, dbva_nmempresa character varying(10), dbva_nmdatabase character varying(50), dbva_icvacuum smallint, dbva_dsvacuum character varying(50), dbva_tminicio timestamp without time zone, dbva_tmtermino timestamp without time zone, dbva_hrduracao character varying(50), dbva_nntamdatabase character varying(10), dbva_nmlogindba character varying(10), dbva_ictermino smallint, dbva_tmultimaalteracao timestamp without time zone, dbva_dsobservacao text);


ALTER TABLE admindb.vw_gerencial_caer_db_vacuum OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 3673319)
-- Name: vw_gerencial_caer_db_versao_base; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_caer_db_versao_base AS
    SELECT table1.dbvb_id, table1.dbvb_nmempresa, table1.dbvb_nmdatabase, table1.dbvb_dtversaobase, table1.dbvb_nmlogindba, table1.dbvb_tmultimaalteracao FROM public.dblink('dbname=gsan_gerencial hostaddr=201.56.20.62 user=gsan_dba password= db@. port=12345'::text, 'select * from admindb.db_versao_base'::text) table1(dbvb_id integer, dbvb_nmempresa character varying(10), dbvb_nmdatabase character varying(50), dbvb_dtversaobase date, dbvb_nmlogindba character varying(10), dbvb_tmultimaalteracao timestamp without time zone);


ALTER TABLE admindb.vw_gerencial_caer_db_versao_base OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 3673323)
-- Name: vw_gerencial_caer_db_versao_sincronismo; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_caer_db_versao_sincronismo AS
    SELECT table1.dbvf_id, table1.dbvf_nmempresa, table1.dbvf_nmdatabase, CASE WHEN (table1.dbvf_icsincronismo = 1) THEN 'Sincronismo das Funcionalidades'::text WHEN (table1.dbvf_icsincronismo = 2) THEN 'Sincronismo Basicas do Gerencial'::text ELSE 'Nao Definido'::text END AS tiposincronismo, table1.dbvf_dtversaosincronismo, table1.dbvf_nmlogindba, table1.dbvf_tmultimaalteracao FROM public.dblink('dbname=gsan_gerencial hostaddr=201.56.20.62 user=gsan_dba password= db@. port=12345'::text, 'select * from admindb.db_versao_sincronismo'::text) table1(dbvf_id integer, dbvf_nmempresa character varying(10), dbvf_nmdatabase character varying(50), dbvf_icsincronismo integer, dbvf_dtversaosincronismo date, dbvf_nmlogindba character varying(10), dbvf_tmultimaalteracao timestamp without time zone);


ALTER TABLE admindb.vw_gerencial_caer_db_versao_sincronismo OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 3673327)
-- Name: vw_gerencial_caern_db_backup; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_caern_db_backup AS
    SELECT table1.dbbk_id, table1.dbbk_nmempresa, table1.dbbk_nmdatabase, CASE WHEN (table1.dbbk_icbackup = 1) THEN 'Backup Logico'::text WHEN (table1.dbbk_icbackup = 2) THEN 'Backup Fisico'::text ELSE 'Nao Definido'::text END AS tipobackup, table1.dbbk_dsbackup, table1.dbbk_tminicio, table1.dbbk_tmtermino, table1.dbbk_hrduracao, table1.dbbk_nntamdatabase, table1.dbbk_nntamarqbackup, table1.dbdk_nmlogindba, CASE WHEN (table1.dbbk_ictermino = 1) THEN 'Normal'::text ELSE 'Com Anormalidade'::text END AS condicaotermino, table1.dbbk_tmultimaalteracao, table1.dbbk_dsobservacao FROM public.dblink('dbname=gsan_gerencial hostaddr=200.149.240.132 user=gsan_dba password= db@. port=1215'::text, 'select * from admindb.db_backup'::text) table1(dbbk_id integer, dbbk_nmempresa character varying(10), dbbk_nmdatabase character varying(50), dbbk_icbackup smallint, dbbk_dsbackup character varying(50), dbbk_tminicio timestamp without time zone, dbbk_tmtermino timestamp without time zone, dbbk_hrduracao character varying(50), dbbk_nntamdatabase character varying(10), dbbk_nntamarqbackup character varying(10), dbdk_nmlogindba character varying(10), dbbk_ictermino smallint, dbbk_tmultimaalteracao timestamp without time zone, dbbk_dsobservacao text);


ALTER TABLE admindb.vw_gerencial_caern_db_backup OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 3673336)
-- Name: vw_gerencial_caern_db_vacuum; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_caern_db_vacuum AS
    SELECT table1.dbva_id, table1.dbva_nmempresa, table1.dbva_nmdatabase, CASE WHEN (table1.dbva_icvacuum = 1) THEN 'Vacuum Simples'::text WHEN (table1.dbva_icvacuum = 2) THEN 'Vacuum Analyze'::text WHEN (table1.dbva_icvacuum = 3) THEN 'Vacuum Full'::text WHEN (table1.dbva_icvacuum = 4) THEN 'Vacuum Full Analyze'::text WHEN (table1.dbva_icvacuum = 5) THEN 'Somente Analyze'::text ELSE 'Nao Definido'::text END AS tipovacuum, table1.dbva_dsvacuum, table1.dbva_tminicio, table1.dbva_tmtermino, table1.dbva_hrduracao, table1.dbva_nntamdatabase, table1.dbva_nmlogindba, CASE WHEN (table1.dbva_ictermino = 1) THEN 'Normal'::text ELSE 'Com Anormalidade'::text END AS condicaotermino, table1.dbva_tmultimaalteracao, table1.dbva_dsobservacao FROM public.dblink('dbname=gsan_gerencial hostaddr=200.149.240.132 user=gsan_dba password= db@. port=1215'::text, 'select * from admindb.db_vacuum'::text) table1(dbva_id integer, dbva_nmempresa character varying(10), dbva_nmdatabase character varying(50), dbva_icvacuum smallint, dbva_dsvacuum character varying(50), dbva_tminicio timestamp without time zone, dbva_tmtermino timestamp without time zone, dbva_hrduracao character varying(50), dbva_nntamdatabase character varying(10), dbva_nmlogindba character varying(10), dbva_ictermino smallint, dbva_tmultimaalteracao timestamp without time zone, dbva_dsobservacao text);


ALTER TABLE admindb.vw_gerencial_caern_db_vacuum OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 3673341)
-- Name: vw_gerencial_caern_db_versao_base; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_caern_db_versao_base AS
    SELECT table1.dbvb_id, table1.dbvb_nmempresa, table1.dbvb_nmdatabase, table1.dbvb_dtversaobase, table1.dbvb_nmlogindba, table1.dbvb_tmultimaalteracao FROM public.dblink('dbname=gsan_gerencial hostaddr=200.149.240.132 user=gsan_dba password= db@. port=1215'::text, 'select * from admindb.db_versao_base'::text) table1(dbvb_id integer, dbvb_nmempresa character varying(10), dbvb_nmdatabase character varying(50), dbvb_dtversaobase date, dbvb_nmlogindba character varying(10), dbvb_tmultimaalteracao timestamp without time zone);


ALTER TABLE admindb.vw_gerencial_caern_db_versao_base OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 3673345)
-- Name: vw_gerencial_caern_db_versao_sincronismo; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_caern_db_versao_sincronismo AS
    SELECT table1.dbvf_id, table1.dbvf_nmempresa, table1.dbvf_nmdatabase, CASE WHEN (table1.dbvf_icsincronismo = 1) THEN 'Sincronismo das Funcionalidades'::text WHEN (table1.dbvf_icsincronismo = 2) THEN 'Sincronismo Basicas do Gerencial'::text ELSE 'Nao Definido'::text END AS tiposincronismo, table1.dbvf_dtversaosincronismo, table1.dbvf_nmlogindba, table1.dbvf_tmultimaalteracao FROM public.dblink('dbname=gsan_gerencial hostaddr=200.149.240.132 user=gsan_dba password= db@. port=1215'::text, 'select * from admindb.db_versao_sincronismo'::text) table1(dbvf_id integer, dbvf_nmempresa character varying(10), dbvf_nmdatabase character varying(50), dbvf_icsincronismo integer, dbvf_dtversaosincronismo date, dbvf_nmlogindba character varying(10), dbvf_tmultimaalteracao timestamp without time zone);


ALTER TABLE admindb.vw_gerencial_caern_db_versao_sincronismo OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 3673349)
-- Name: vw_gerencial_compesa_db_backup; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_compesa_db_backup AS
    SELECT table1.dbbk_id, table1.dbbk_nmempresa, table1.dbbk_nmdatabase, CASE WHEN (table1.dbbk_icbackup = 1) THEN 'Backup Logico'::text WHEN (table1.dbbk_icbackup = 2) THEN 'Backup Fisico'::text ELSE 'Nao Definido'::text END AS tipobackup, table1.dbbk_dsbackup, table1.dbbk_tminicio, table1.dbbk_tmtermino, table1.dbbk_hrduracao, table1.dbbk_nntamdatabase, table1.dbbk_nntamarqbackup, table1.dbdk_nmlogindba, CASE WHEN (table1.dbbk_ictermino = 1) THEN 'Normal'::text ELSE 'Com Anormalidade'::text END AS condicaotermino, table1.dbbk_tmultimaalteracao, table1.dbbk_dsobservacao FROM public.dblink('dbname=gsan_gerencial hostaddr=10.10.0.17 user=gsan_dba password= db@. port=5432'::text, 'select * from admindb.db_backup'::text) table1(dbbk_id integer, dbbk_nmempresa character varying(10), dbbk_nmdatabase character varying(50), dbbk_icbackup smallint, dbbk_dsbackup character varying(50), dbbk_tminicio timestamp without time zone, dbbk_tmtermino timestamp without time zone, dbbk_hrduracao character varying(50), dbbk_nntamdatabase character varying(10), dbbk_nntamarqbackup character varying(10), dbdk_nmlogindba character varying(10), dbbk_ictermino smallint, dbbk_tmultimaalteracao timestamp without time zone, dbbk_dsobservacao text);


ALTER TABLE admindb.vw_gerencial_compesa_db_backup OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 3673354)
-- Name: vw_gerencial_compesa_db_vacuum; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_compesa_db_vacuum AS
    SELECT table1.dbva_id, table1.dbva_nmempresa, table1.dbva_nmdatabase, CASE WHEN (table1.dbva_icvacuum = 1) THEN 'Vacuum Simples'::text WHEN (table1.dbva_icvacuum = 2) THEN 'Vacuum Analyze'::text WHEN (table1.dbva_icvacuum = 3) THEN 'Vacuum Full'::text WHEN (table1.dbva_icvacuum = 4) THEN 'Vacuum Full Analyze'::text WHEN (table1.dbva_icvacuum = 5) THEN 'Somente Analyze'::text ELSE 'Nao Definido'::text END AS tipovacuum, table1.dbva_dsvacuum, table1.dbva_tminicio, table1.dbva_tmtermino, table1.dbva_hrduracao, table1.dbva_nntamdatabase, table1.dbva_nmlogindba, CASE WHEN (table1.dbva_ictermino = 1) THEN 'Normal'::text ELSE 'Com Anormalidade'::text END AS condicaotermino, table1.dbva_tmultimaalteracao, table1.dbva_dsobservacao FROM public.dblink('dbname=gsan_gerencial hostaddr=10.10.0.17 user=gsan_dba password= db@. port=5432'::text, 'select * from admindb.db_vacuum'::text) table1(dbva_id integer, dbva_nmempresa character varying(10), dbva_nmdatabase character varying(50), dbva_icvacuum smallint, dbva_dsvacuum character varying(50), dbva_tminicio timestamp without time zone, dbva_tmtermino timestamp without time zone, dbva_hrduracao character varying(50), dbva_nntamdatabase character varying(10), dbva_nmlogindba character varying(10), dbva_ictermino smallint, dbva_tmultimaalteracao timestamp without time zone, dbva_dsobservacao text);


ALTER TABLE admindb.vw_gerencial_compesa_db_vacuum OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 3673363)
-- Name: vw_gerencial_compesa_db_versao_base; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_compesa_db_versao_base AS
    SELECT table1.dbvb_id, table1.dbvb_nmempresa, table1.dbvb_nmdatabase, table1.dbvb_dtversaobase, table1.dbvb_nmlogindba, table1.dbvb_tmultimaalteracao FROM public.dblink('dbname=gsan_gerencial hostaddr=10.10.0.17 user=gsan_dba password= db@. port=5432'::text, 'select * from admindb.db_versao_base'::text) table1(dbvb_id integer, dbvb_nmempresa character varying(10), dbvb_nmdatabase character varying(50), dbvb_dtversaobase date, dbvb_nmlogindba character varying(10), dbvb_tmultimaalteracao timestamp without time zone);


ALTER TABLE admindb.vw_gerencial_compesa_db_versao_base OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 3673367)
-- Name: vw_gerencial_compesa_db_versao_sincronismo; Type: VIEW; Schema: admindb; Owner: postgres
--

CREATE VIEW vw_gerencial_compesa_db_versao_sincronismo AS
    SELECT table1.dbvf_id, table1.dbvf_nmempresa, table1.dbvf_nmdatabase, CASE WHEN (table1.dbvf_icsincronismo = 1) THEN 'Sincronismo das Funcionalidades'::text WHEN (table1.dbvf_icsincronismo = 2) THEN 'Sincronismo Basicas do Gerencial'::text ELSE 'Nao Definido'::text END AS tiposincronismo, table1.dbvf_dtversaosincronismo, table1.dbvf_nmlogindba, table1.dbvf_tmultimaalteracao FROM public.dblink('dbname=gsan_gerencial hostaddr=10.10.0.17 user=gsan_dba password= db@. port=5432'::text, 'select * from admindb.db_versao_sincronismo'::text) table1(dbvf_id integer, dbvf_nmempresa character varying(10), dbvf_nmdatabase character varying(50), dbvf_icsincronismo integer, dbvf_dtversaosincronismo date, dbvf_nmlogindba character varying(10), dbvf_tmultimaalteracao timestamp without time zone);


ALTER TABLE admindb.vw_gerencial_compesa_db_versao_sincronismo OWNER TO postgres;

SET search_path = arrecadacao, pg_catalog;

--
-- TOC entry 220 (class 1259 OID 3673371)
-- Name: g_arrecadacao_forma; Type: TABLE; Schema: arrecadacao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_arrecadacao_forma (
    arfm_id integer NOT NULL,
    arfm_cdarrecadacaoforma character(1),
    arfm_dsarrecadacaoforma character varying(60),
    arfm_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE arrecadacao.g_arrecadacao_forma OWNER TO gsan_admin;

--
-- TOC entry 221 (class 1259 OID 3673375)
-- Name: g_arrecadador; Type: TABLE; Schema: arrecadacao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_arrecadador (
    arrc_id integer NOT NULL,
    arrc_nninscricaoestadual character varying(20),
    arrc_cdagente smallint NOT NULL,
    arrc_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    arrc_icuso smallint DEFAULT 1 NOT NULL
);


ALTER TABLE arrecadacao.g_arrecadador OWNER TO gsan_admin;

--
-- TOC entry 222 (class 1259 OID 3673384)
-- Name: g_devolucao_situacao; Type: TABLE; Schema: arrecadacao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_devolucao_situacao (
    dvst_id integer NOT NULL,
    dvst_dsdevolucaosituacao character varying(20),
    dvst_dsabreviado character(8),
    dvst_icuso smallint,
    dvst_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE arrecadacao.g_devolucao_situacao OWNER TO gsan_admin;

--
-- TOC entry 223 (class 1259 OID 3673388)
-- Name: g_epoca_pagamento; Type: TABLE; Schema: arrecadacao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_epoca_pagamento (
    eppa_id integer NOT NULL,
    eppa_dsepocapagemento character varying(25),
    eppa_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE arrecadacao.g_epoca_pagamento OWNER TO gsan_admin;

--
-- TOC entry 224 (class 1259 OID 3673392)
-- Name: g_pagamento_situacao; Type: TABLE; Schema: arrecadacao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_pagamento_situacao (
    pgst_id integer NOT NULL,
    pgst_dspagamentosituacao character varying(20),
    pgst_dsabreviado character(8),
    pgst_icuso smallint,
    pgst_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE arrecadacao.g_pagamento_situacao OWNER TO gsan_admin;

--
-- TOC entry 225 (class 1259 OID 3673396)
-- Name: g_pagamento_situacao_x; Type: TABLE; Schema: arrecadacao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_pagamento_situacao_x (
    pgst_id integer NOT NULL,
    pgst_dspagamentosituacao character varying(20),
    pgst_dsabreviado character(8),
    pgst_icuso smallint,
    pgst_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    pgst_qtd integer
);


ALTER TABLE arrecadacao.g_pagamento_situacao_x OWNER TO gsan_admin;

--
-- TOC entry 226 (class 1259 OID 3673405)
-- Name: seq_un_resumo_arrecadacao; Type: SEQUENCE; Schema: arrecadacao; Owner: gsan_admin
--

CREATE SEQUENCE seq_un_resumo_arrecadacao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE arrecadacao.seq_un_resumo_arrecadacao OWNER TO gsan_admin;

--
-- TOC entry 227 (class 1259 OID 3673407)
-- Name: un_resumo_arrecadacao; Type: TABLE; Schema: arrecadacao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_arrecadacao (
    rear_id integer NOT NULL,
    uneg_id integer NOT NULL,
    rear_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    loca_id integer NOT NULL,
    stcm_id integer,
    loca_cdelo integer NOT NULL,
    qdra_id integer,
    rear_cdsetorcomercial integer NOT NULL,
    rear_nnquadra integer NOT NULL,
    iper_id integer,
    last_id integer,
    lest_id integer,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    rear_qtcontas integer NOT NULL,
    rear_icrecebidasnomes smallint NOT NULL,
    rota_id integer,
    eppa_id integer NOT NULL,
    rear_vlagua numeric(13,2) NOT NULL,
    rear_vlesgoto numeric(13,2) NOT NULL,
    rear_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    dotp_id integer NOT NULL,
    pgst_id integer,
    rear_vlnaoidentificado numeric(13,2) DEFAULT 0 NOT NULL,
    crog_idcredito integer,
    rear_vldocsrecebidoscredito numeric(13,2),
    lict_idcredito integer,
    rear_vldocsrecebidosoutros numeric(10,2),
    fntp_idoutros integer,
    lict_idoutros integer,
    arfm_id integer,
    arrc_id integer,
    rear_vlimpostos numeric(13,2),
    rear_ichidrometro smallint NOT NULL,
    rear_amreferenciadocumento integer,
    rear_cdrota integer,
    rear_vldevolucoesclassificadas numeric(13,2),
    rear_vldevolucoesnaoclassif numeric(13,2),
    dvst_idatual integer,
    rear_qtpagamentos integer NOT NULL
);


ALTER TABLE arrecadacao.un_resumo_arrecadacao OWNER TO gsan_admin;

--
-- TOC entry 5172 (class 0 OID 0)
-- Dependencies: 227
-- Name: COLUMN un_resumo_arrecadacao.rear_vldevolucoesclassificadas; Type: COMMENT; Schema: arrecadacao; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_arrecadacao.rear_vldevolucoesclassificadas IS 'Valor das devolucoes classificadas';


--
-- TOC entry 5173 (class 0 OID 0)
-- Dependencies: 227
-- Name: COLUMN un_resumo_arrecadacao.rear_vldevolucoesnaoclassif; Type: COMMENT; Schema: arrecadacao; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_arrecadacao.rear_vldevolucoesnaoclassif IS 'Valor das devolucoes nao classificadas';


--
-- TOC entry 5174 (class 0 OID 0)
-- Dependencies: 227
-- Name: COLUMN un_resumo_arrecadacao.dvst_idatual; Type: COMMENT; Schema: arrecadacao; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_arrecadacao.dvst_idatual IS 'Situacao da devolucao';


--
-- TOC entry 5175 (class 0 OID 0)
-- Dependencies: 227
-- Name: COLUMN un_resumo_arrecadacao.rear_qtpagamentos; Type: COMMENT; Schema: arrecadacao; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_arrecadacao.rear_qtpagamentos IS 'Quantidade de pagamentos';


--
-- TOC entry 228 (class 1259 OID 3673412)
-- Name: vw_arrecadacao_dados_diarios; Type: VIEW; Schema: arrecadacao; Owner: gsan_admin
--

CREATE VIEW vw_arrecadacao_dados_diarios AS
    SELECT table1.ardd_id, table1.ardd_amreferenciaarrecadacao, table1.arrc_id, table1.greg_id, table1.loca_id, table1.stcm_id, table1.rota_id, table1.qdra_id, table1.ardd_cdsetorcomercial, table1.ardd_nnquadra, table1.iper_id, table1.last_id, table1.lest_id, table1.catg_id, table1.epod_id, table1.ardd_ichidrometro, table1.dotp_id, table1.arfm_id, table1.ardd_dtpagamento, table1.ardd_qtpagamentos, table1.ardd_vlpagamentos, table1.ardd_tmultimaalteracao, table1.uneg_id FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 
user=gsan_olap password= ol@p port=5432'::text, 'select * from arrecadacao.arrecadacao_dados_diarios '::text) table1(ardd_id integer, ardd_amreferenciaarrecadacao integer, arrc_id integer, greg_id integer, loca_id integer, stcm_id integer, rota_id integer, qdra_id integer, ardd_cdsetorcomercial integer, ardd_nnquadra integer, iper_id integer, last_id integer, lest_id integer, catg_id integer, epod_id integer, ardd_ichidrometro smallint, dotp_id integer, arfm_id integer, ardd_dtpagamento date, ardd_qtpagamentos integer, ardd_vlpagamentos numeric(13,2), ardd_tmultimaalteracao timestamp without time zone, uneg_id integer);


ALTER TABLE arrecadacao.vw_arrecadacao_dados_diarios OWNER TO gsan_admin;

--
-- TOC entry 229 (class 1259 OID 3673417)
-- Name: vw_arrecadacao_forma; Type: VIEW; Schema: arrecadacao; Owner: gsan_admin
--

CREATE VIEW vw_arrecadacao_forma AS
    SELECT table1.arfm_id, table1.arfm_cdarrecadacaoforma, table1.arfm_dsarrecadacaoforma, table1.arfm_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 
user=gsan_olap password= ol@p port=5432'::text, 'select * from arrecadacao. arrecadacao_forma '::text) table1(arfm_id integer, arfm_cdarrecadacaoforma character(1), arfm_dsarrecadacaoforma character varying(60), arfm_tmultimaalteracao timestamp without time zone);


ALTER TABLE arrecadacao.vw_arrecadacao_forma OWNER TO gsan_admin;

--
-- TOC entry 230 (class 1259 OID 3673421)
-- Name: vw_arrecadador; Type: VIEW; Schema: arrecadacao; Owner: gsan_admin
--

CREATE VIEW vw_arrecadador AS
    SELECT table1.arrc_id, table1.arrc_nninscricaoestadual, table1.arrc_cdagente, table1.arrc_tmultimaalteracao, table1.arrc_icuso FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select arrc_id, arrc_nninscricaoestadual, arrc_cdagente, arrc_tmultimaalteracao ,arrc_icuso from arrecadacao.arrecadador '::text) table1(arrc_id integer, arrc_nninscricaoestadual character varying(20), arrc_cdagente smallint, arrc_tmultimaalteracao timestamp without time zone, arrc_icuso smallint);


ALTER TABLE arrecadacao.vw_arrecadador OWNER TO gsan_admin;

--
-- TOC entry 231 (class 1259 OID 3673429)
-- Name: vw_devolucao_situacao; Type: VIEW; Schema: arrecadacao; Owner: gsan_admin
--

CREATE VIEW vw_devolucao_situacao AS
    SELECT table1.dvst_id, table1.dvst_dsdevolucaosituacao, table1.dvst_dsabreviado, table1.dvst_icuso, table1.dvst_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from arrecadacao.devolucao_situacao'::text) table1(dvst_id integer, dvst_dsdevolucaosituacao character varying(20), dvst_dsabreviado character(8), dvst_icuso smallint, dvst_tmultimaalteracao timestamp without time zone);


ALTER TABLE arrecadacao.vw_devolucao_situacao OWNER TO gsan_admin;

--
-- TOC entry 232 (class 1259 OID 3673433)
-- Name: vw_pagamento_situacao; Type: VIEW; Schema: arrecadacao; Owner: gsan_admin
--

CREATE VIEW vw_pagamento_situacao AS
    SELECT table1.pgst_id, table1.pgst_dspagamentosituacao, table1.pgst_dsabreviado, table1.pgst_icuso, table1.pgst_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 
user=gsan_olap password= ol@p port=5432'::text, 'select * from arrecadacao.pagamento_situacao'::text) table1(pgst_id integer, pgst_dspagamentosituacao character varying(20), pgst_dsabreviado character(8), pgst_icuso smallint, pgst_tmultimaalteracao timestamp without time zone);


ALTER TABLE arrecadacao.vw_pagamento_situacao OWNER TO gsan_admin;

SET search_path = financeiro, pg_catalog;

--
-- TOC entry 233 (class 1259 OID 3673437)
-- Name: g_lancamento_item_contabil; Type: TABLE; Schema: financeiro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_lancamento_item_contabil (
    lict_id integer NOT NULL,
    lict_dsitemlancamentocontabil character varying(35) NOT NULL,
    lict_dsabreviado character(5) NOT NULL,
    lict_nnsequenciaimpressao smallint,
    lict_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    lcit_id integer NOT NULL,
    lict_icuso smallint NOT NULL
);


ALTER TABLE financeiro.g_lancamento_item_contabil OWNER TO gsan_admin;

SET search_path = arrecadacao, pg_catalog;

--
-- TOC entry 234 (class 1259 OID 3673445)
-- Name: vw_resumo_arrecadacao; Type: VIEW; Schema: arrecadacao; Owner: gsan_admin
--

CREATE VIEW vw_resumo_arrecadacao AS
    SELECT un_resumo_arrecadacao_agua_esgoto.rear_id, un_resumo_arrecadacao_agua_esgoto.uneg_id, un_resumo_arrecadacao_agua_esgoto.rear_amreferencia, un_resumo_arrecadacao_agua_esgoto.greg_id, un_resumo_arrecadacao_agua_esgoto.loca_id, un_resumo_arrecadacao_agua_esgoto.stcm_id, un_resumo_arrecadacao_agua_esgoto.loca_cdelo, un_resumo_arrecadacao_agua_esgoto.qdra_id, un_resumo_arrecadacao_agua_esgoto.rear_cdsetorcomercial, un_resumo_arrecadacao_agua_esgoto.rear_nnquadra, un_resumo_arrecadacao_agua_esgoto.iper_id, un_resumo_arrecadacao_agua_esgoto.last_id, un_resumo_arrecadacao_agua_esgoto.lest_id, un_resumo_arrecadacao_agua_esgoto.catg_id, un_resumo_arrecadacao_agua_esgoto.scat_id, un_resumo_arrecadacao_agua_esgoto.epod_id, un_resumo_arrecadacao_agua_esgoto.cltp_id, un_resumo_arrecadacao_agua_esgoto.lapf_id, un_resumo_arrecadacao_agua_esgoto.lepf_id, un_resumo_arrecadacao_agua_esgoto.rear_qtcontas, un_resumo_arrecadacao_agua_esgoto.rear_icrecebidasnomes, un_resumo_arrecadacao_agua_esgoto.rota_id, un_resumo_arrecadacao_agua_esgoto.eppa_id, un_resumo_arrecadacao_agua_esgoto.rear_vlagua, un_resumo_arrecadacao_agua_esgoto.rear_vlesgoto, un_resumo_arrecadacao_agua_esgoto.rear_tmultimaalteracao, un_resumo_arrecadacao_agua_esgoto.dotp_id, un_resumo_arrecadacao_agua_esgoto.pgst_id, un_resumo_arrecadacao_agua_esgoto.rear_vlnaoidentificado, un_resumo_arrecadacao_agua_esgoto.crog_idcredito, un_resumo_arrecadacao_agua_esgoto.rear_vldocsrecebidoscredito AS rear_vldocumentosrecebidoscredito, (SELECT li.lict_dsitemlancamentocontabil FROM financeiro.g_lancamento_item_contabil li WHERE (li.lict_id = un_resumo_arrecadacao_agua_esgoto.lict_idcredito)) AS descricaocredito, un_resumo_arrecadacao_agua_esgoto.rear_vldocsrecebidosoutros AS rear_vldocumentosrecebidosoutros, un_resumo_arrecadacao_agua_esgoto.fntp_idoutros, (SELECT li.lict_dsitemlancamentocontabil FROM financeiro.g_lancamento_item_contabil li WHERE (li.lict_id = un_resumo_arrecadacao_agua_esgoto.lict_idoutros)) AS descricaooutros, un_resumo_arrecadacao_agua_esgoto.arfm_id, un_resumo_arrecadacao_agua_esgoto.arrc_id FROM un_resumo_arrecadacao un_resumo_arrecadacao_agua_esgoto;


ALTER TABLE arrecadacao.vw_resumo_arrecadacao OWNER TO gsan_admin;

SET search_path = atendimentopublico, pg_catalog;

--
-- TOC entry 235 (class 1259 OID 3673450)
-- Name: g_atend_motivo_encmt; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_atend_motivo_encmt (
    amen_id integer NOT NULL,
    amen_dsmotivoencerramento character varying(50) NOT NULL,
    amen_dsabreviado character(8),
    amen_icuso smallint NOT NULL,
    amen_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    amen_icexecucao smallint NOT NULL,
    amen_icduplicidade smallint NOT NULL
);


ALTER TABLE atendimentopublico.g_atend_motivo_encmt OWNER TO gsan_admin;

--
-- TOC entry 236 (class 1259 OID 3673454)
-- Name: g_atendimento_motivo_encerramento_x; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_atendimento_motivo_encerramento_x (
    amen_id integer NOT NULL,
    amen_dsmotivoencerramento character varying(50) NOT NULL,
    amen_dsabreviado character(8),
    amen_icuso smallint NOT NULL,
    amen_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    amen_icexecucao smallint NOT NULL,
    amen_icduplicidade smallint NOT NULL,
    amen_qtd integer
);


ALTER TABLE atendimentopublico.g_atendimento_motivo_encerramento_x OWNER TO gsan_admin;

--
-- TOC entry 237 (class 1259 OID 3673458)
-- Name: g_ligacao_agua_perfil; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_ligacao_agua_perfil (
    lapf_id integer NOT NULL,
    lapf_dsligacaoaguaperfil character varying(20),
    lapf_icuso integer,
    lapf_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE atendimentopublico.g_ligacao_agua_perfil OWNER TO gsan_admin;

--
-- TOC entry 238 (class 1259 OID 3673466)
-- Name: g_ligacao_agua_perfil_x; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_ligacao_agua_perfil_x (
    lapf_id integer NOT NULL,
    lapf_dsligacaoaguaperfil character varying(20),
    lapf_icuso integer,
    lapf_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    lapf_qtd integer
);


ALTER TABLE atendimentopublico.g_ligacao_agua_perfil_x OWNER TO gsan_admin;

--
-- TOC entry 239 (class 1259 OID 3673470)
-- Name: g_ligacao_agua_situacao; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_ligacao_agua_situacao (
    last_id integer NOT NULL,
    last_dsligacaoaguasituacao character varying(20),
    last_icuso smallint,
    last_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    last_icativaagua smallint DEFAULT 1 NOT NULL,
    last_icdesligadaagua smallint DEFAULT 1 NOT NULL,
    last_iccadastradaagua smallint DEFAULT 1 NOT NULL,
    last_icanaliseagua integer DEFAULT 2 NOT NULL
);


ALTER TABLE atendimentopublico.g_ligacao_agua_situacao OWNER TO gsan_admin;

--
-- TOC entry 5188 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN g_ligacao_agua_situacao.last_icativaagua; Type: COMMENT; Schema: atendimentopublico; Owner: gsan_admin
--

COMMENT ON COLUMN g_ligacao_agua_situacao.last_icativaagua IS 'Indica se a situacao da ligacao de agua e de um imovel ativo';


--
-- TOC entry 5189 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN g_ligacao_agua_situacao.last_icdesligadaagua; Type: COMMENT; Schema: atendimentopublico; Owner: gsan_admin
--

COMMENT ON COLUMN g_ligacao_agua_situacao.last_icdesligadaagua IS 'Indica se a situacao da ligacao de água e de um imovel desligado';


--
-- TOC entry 5190 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN g_ligacao_agua_situacao.last_iccadastradaagua; Type: COMMENT; Schema: atendimentopublico; Owner: gsan_admin
--

COMMENT ON COLUMN g_ligacao_agua_situacao.last_iccadastradaagua IS 'Indica se a situacao da ligacao de água e de um imovel cadastrado';


--
-- TOC entry 5191 (class 0 OID 0)
-- Dependencies: 239
-- Name: COLUMN g_ligacao_agua_situacao.last_icanaliseagua; Type: COMMENT; Schema: atendimentopublico; Owner: gsan_admin
--

COMMENT ON COLUMN g_ligacao_agua_situacao.last_icanaliseagua IS 'Indica se a situacao de ligacao de agua e do imovel em analise';


--
-- TOC entry 240 (class 1259 OID 3673482)
-- Name: g_ligacao_agua_situacao_x; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_ligacao_agua_situacao_x (
    last_id integer NOT NULL,
    last_dsligacaoaguasituacao character varying(20),
    last_icuso smallint,
    last_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    last_icativaagua smallint DEFAULT 1 NOT NULL,
    last_icdesligadaagua smallint DEFAULT 1 NOT NULL,
    last_iccadastradaagua smallint DEFAULT 1 NOT NULL,
    last_icanaliseagua integer DEFAULT 2 NOT NULL,
    last_qtd integer
);


ALTER TABLE atendimentopublico.g_ligacao_agua_situacao_x OWNER TO gsan_admin;

--
-- TOC entry 241 (class 1259 OID 3673490)
-- Name: g_ligacao_esgoto_perfil; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_ligacao_esgoto_perfil (
    lepf_id integer NOT NULL,
    lepf_dsligacaoesgotoperfil character varying(20),
    lepf_icuso smallint,
    lepf_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    lepf_pcesgoto numeric(5,2)
);


ALTER TABLE atendimentopublico.g_ligacao_esgoto_perfil OWNER TO gsan_admin;

--
-- TOC entry 242 (class 1259 OID 3673494)
-- Name: g_ligacao_esgoto_perfil_x; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_ligacao_esgoto_perfil_x (
    lepf_id integer NOT NULL,
    lepf_dsligacaoesgotoperfil character varying(20),
    lepf_icuso smallint,
    lepf_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    lepf_pcesgoto numeric(5,2),
    lepf_qtd integer
);


ALTER TABLE atendimentopublico.g_ligacao_esgoto_perfil_x OWNER TO gsan_admin;

--
-- TOC entry 243 (class 1259 OID 3673502)
-- Name: g_ligacao_esgoto_situacao; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_ligacao_esgoto_situacao (
    lest_id integer NOT NULL,
    lest_dsligacaoesgotosituacao character varying(20),
    lest_icuso smallint,
    lest_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    lest_icativaesgoto smallint DEFAULT 1 NOT NULL,
    lest_icdesligadaesgoto smallint DEFAULT 1 NOT NULL,
    lest_iccadastradaesgoto smallint DEFAULT 1 NOT NULL,
    lest_icanaliseesgoto integer DEFAULT 2 NOT NULL
);


ALTER TABLE atendimentopublico.g_ligacao_esgoto_situacao OWNER TO gsan_admin;

--
-- TOC entry 5196 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN g_ligacao_esgoto_situacao.lest_icativaesgoto; Type: COMMENT; Schema: atendimentopublico; Owner: gsan_admin
--

COMMENT ON COLUMN g_ligacao_esgoto_situacao.lest_icativaesgoto IS 'Indica se a situacao da ligacao de esgoto e de um imovel ativo';


--
-- TOC entry 5197 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN g_ligacao_esgoto_situacao.lest_icdesligadaesgoto; Type: COMMENT; Schema: atendimentopublico; Owner: gsan_admin
--

COMMENT ON COLUMN g_ligacao_esgoto_situacao.lest_icdesligadaesgoto IS 'Indica se a situacao da ligacao de esgoto e de um imovel desligado';


--
-- TOC entry 5198 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN g_ligacao_esgoto_situacao.lest_iccadastradaesgoto; Type: COMMENT; Schema: atendimentopublico; Owner: gsan_admin
--

COMMENT ON COLUMN g_ligacao_esgoto_situacao.lest_iccadastradaesgoto IS 'Indica se a situacao da ligacao de esgoto é de um imovel cadastrado';


--
-- TOC entry 5199 (class 0 OID 0)
-- Dependencies: 243
-- Name: COLUMN g_ligacao_esgoto_situacao.lest_icanaliseesgoto; Type: COMMENT; Schema: atendimentopublico; Owner: gsan_admin
--

COMMENT ON COLUMN g_ligacao_esgoto_situacao.lest_icanaliseesgoto IS 'Indica se a situacao de ligacao de agua e do imovel em analise';


--
-- TOC entry 244 (class 1259 OID 3673510)
-- Name: g_ligacao_esgoto_situacao_x; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_ligacao_esgoto_situacao_x (
    lest_id integer NOT NULL,
    lest_dsligacaoesgotosituacao character varying(20),
    lest_icuso smallint,
    lest_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    lest_icativaesgoto smallint DEFAULT 1 NOT NULL,
    lest_icdesligadaesgoto smallint DEFAULT 1 NOT NULL,
    lest_iccadastradaesgoto smallint DEFAULT 1 NOT NULL,
    lest_icanaliseesgoto integer DEFAULT 2 NOT NULL,
    lest_qtd integer
);


ALTER TABLE atendimentopublico.g_ligacao_esgoto_situacao_x OWNER TO gsan_admin;

--
-- TOC entry 245 (class 1259 OID 3673518)
-- Name: g_meio_solicitacao; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_meio_solicitacao (
    meso_id integer NOT NULL,
    meso_dsmeiosolicitacao character varying(50) NOT NULL,
    meso_dsabreviado character(8),
    meso_icuso smallint NOT NULL,
    meso_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE atendimentopublico.g_meio_solicitacao OWNER TO gsan_admin;

--
-- TOC entry 246 (class 1259 OID 3673522)
-- Name: g_meio_solicitacao_x; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_meio_solicitacao_x (
    meso_id integer NOT NULL,
    meso_dsmeiosolicitacao character varying(50) NOT NULL,
    meso_dsabreviado character(8),
    meso_icuso smallint NOT NULL,
    meso_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    meso_qtd integer
);


ALTER TABLE atendimentopublico.g_meio_solicitacao_x OWNER TO gsan_admin;

--
-- TOC entry 247 (class 1259 OID 3673526)
-- Name: g_solicitacao_tipo; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_solicitacao_tipo (
    sotp_id integer NOT NULL,
    sotp_dssolicitacaotipo character varying(50),
    sotp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE atendimentopublico.g_solicitacao_tipo OWNER TO gsan_admin;

--
-- TOC entry 248 (class 1259 OID 3673530)
-- Name: g_solicitacao_tipo_espec; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_solicitacao_tipo_espec (
    step_id integer NOT NULL,
    step_dssolcttipoespec character varying(50),
    step_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE atendimentopublico.g_solicitacao_tipo_espec OWNER TO gsan_admin;

--
-- TOC entry 249 (class 1259 OID 3673534)
-- Name: g_solicitacao_tipo_especificacao_x; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_solicitacao_tipo_especificacao_x (
    step_id integer NOT NULL,
    step_dssolicitacaotipoespecificacao character varying(50),
    step_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    step_qtd integer
);


ALTER TABLE atendimentopublico.g_solicitacao_tipo_especificacao_x OWNER TO gsan_admin;

--
-- TOC entry 250 (class 1259 OID 3673538)
-- Name: g_solicitacao_tipo_x; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_solicitacao_tipo_x (
    sotp_id integer NOT NULL,
    sotp_dssolicitacaotipo character varying(50),
    sotp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    sotp_qtd integer
);


ALTER TABLE atendimentopublico.g_solicitacao_tipo_x OWNER TO gsan_admin;

--
-- TOC entry 251 (class 1259 OID 3673542)
-- Name: seq_un_resumo_ra; Type: SEQUENCE; Schema: atendimentopublico; Owner: gsan_admin
--

CREATE SEQUENCE seq_un_resumo_ra
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE atendimentopublico.seq_un_resumo_ra OWNER TO gsan_admin;

--
-- TOC entry 252 (class 1259 OID 3673544)
-- Name: un_resumo_ra; Type: TABLE; Schema: atendimentopublico; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_ra (
    rera_id integer NOT NULL,
    rera_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    iper_id integer,
    rera_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    loca_cdelo integer NOT NULL,
    last_id integer,
    meso_id integer NOT NULL,
    lest_id integer,
    rera_qtrageradas integer NOT NULL,
    sotp_id integer NOT NULL,
    catg_id integer,
    scat_id integer,
    rera_cdsetorcomercial integer,
    rera_qtrapendentes_no_prazo integer NOT NULL,
    rera_nnquadra integer,
    step_id integer,
    epod_id integer,
    rota_id integer,
    cltp_id integer,
    rera_qtrabloqueadas integer NOT NULL,
    lapf_id integer,
    rera_qtraencerradas_no_prazo integer NOT NULL,
    rera_icatendimentoonline integer NOT NULL,
    lepf_id integer,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    stcm_id integer,
    qdra_id integer,
    rera_qtrapendentes_fora_prazo integer,
    rera_qtraencerradas_fora_prazo integer,
    rera_unidade_solicitacao integer,
    rera_unidade_encerramento integer,
    rera_cdrota integer,
    amen_id integer,
    rera_qtrageradames_no_prazo integer DEFAULT 0 NOT NULL,
    rera_qtrageradames_fora_prazo integer DEFAULT 0 NOT NULL
);


ALTER TABLE atendimentopublico.un_resumo_ra OWNER TO gsan_admin;

--
-- TOC entry 5209 (class 0 OID 0)
-- Dependencies: 252
-- Name: COLUMN un_resumo_ra.amen_id; Type: COMMENT; Schema: atendimentopublico; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_ra.amen_id IS 'Id do motivo de encerramento';


--
-- TOC entry 5210 (class 0 OID 0)
-- Dependencies: 252
-- Name: COLUMN un_resumo_ra.rera_qtrageradames_no_prazo; Type: COMMENT; Schema: atendimentopublico; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_ra.rera_qtrageradames_no_prazo IS 'Quantidade de RA´s geradas no mes no prazo.';


--
-- TOC entry 5211 (class 0 OID 0)
-- Dependencies: 252
-- Name: COLUMN un_resumo_ra.rera_qtrageradames_fora_prazo; Type: COMMENT; Schema: atendimentopublico; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_ra.rera_qtrageradames_fora_prazo IS 'Quantidade de RA´s geradas no mes fora do prazo.';


--
-- TOC entry 253 (class 1259 OID 3673550)
-- Name: vw_atendimento_motivo_encerramento; Type: VIEW; Schema: atendimentopublico; Owner: gsan_admin
--

CREATE VIEW vw_atendimento_motivo_encerramento AS
    SELECT table1.amen_id, table1.amen_dsmotivoencerramento, table1.amen_dsabreviado, table1.amen_icuso, table1.amen_tmultimaalteracao, table1.amen_icexecucao, table1.amen_icduplicidade FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 
user=gsan_olap password= ol@p port=5432'::text, 'select * from atendimentopublico.atendimento_motivo_encerramento'::text) table1(amen_id integer, amen_dsmotivoencerramento character varying(50), amen_dsabreviado character(8), amen_icuso smallint, amen_tmultimaalteracao timestamp without time zone, amen_icexecucao smallint, amen_icduplicidade smallint);


ALTER TABLE atendimentopublico.vw_atendimento_motivo_encerramento OWNER TO gsan_admin;

--
-- TOC entry 254 (class 1259 OID 3673554)
-- Name: vw_ligacao_agua_perfil; Type: VIEW; Schema: atendimentopublico; Owner: gsan_admin
--

CREATE VIEW vw_ligacao_agua_perfil AS
    SELECT table1.lapf_id, table1.lapf_dsligacaoaguaperfil, table1.lapf_icuso, table1.lapf_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from atendimentopublico.ligacao_agua_perfil'::text) table1(lapf_id integer, lapf_dsligacaoaguaperfil character varying(20), lapf_icuso integer, lapf_tmultimaalteracao timestamp without time zone);


ALTER TABLE atendimentopublico.vw_ligacao_agua_perfil OWNER TO gsan_admin;

--
-- TOC entry 255 (class 1259 OID 3673558)
-- Name: vw_ligacao_agua_situacao; Type: VIEW; Schema: atendimentopublico; Owner: gsan_admin
--

CREATE VIEW vw_ligacao_agua_situacao AS
    SELECT table1.last_id, table1.last_dsligacaoaguasituacao, table1.last_icuso, table1.last_tmultimaalteracao, table1.last_dsabreviado, table1.last_icfaturamento, table1.last_nnconsumominimo, table1.last_icexistenciarede, table1.last_icexistencialigacao, table1.last_icabastecimento, table1.last_iccadastradaagua, table1.last_icativaagua, table1.last_icdesligadaagua, table1.last_icanaliseagua FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from atendimentopublico.ligacao_agua_situacao'::text) table1(last_id integer, last_dsligacaoaguasituacao character varying(20), last_icuso smallint, last_tmultimaalteracao timestamp without time zone, last_dsabreviado character(3), last_icfaturamento smallint, last_nnconsumominimo integer, last_icexistenciarede smallint, last_icexistencialigacao smallint, last_icabastecimento smallint, last_iccadastradaagua smallint, last_icativaagua smallint, last_icdesligadaagua smallint, last_icanaliseagua integer);


ALTER TABLE atendimentopublico.vw_ligacao_agua_situacao OWNER TO gsan_admin;

--
-- TOC entry 256 (class 1259 OID 3673568)
-- Name: vw_ligacao_esgoto_perfil; Type: VIEW; Schema: atendimentopublico; Owner: gsan_admin
--

CREATE VIEW vw_ligacao_esgoto_perfil AS
    SELECT table1.lepf_id, table1.lepf_dsligacaoesgotoperfil, table1.lepf_icuso, table1.lepf_tmultimaalteracao, table1.lepf_pcesgoto, table1.lepf_icprincipal FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from atendimentopublico.ligacao_esgoto_perfil'::text) table1(lepf_id integer, lepf_dsligacaoesgotoperfil character varying(20), lepf_icuso smallint, lepf_tmultimaalteracao timestamp without time zone, lepf_pcesgoto numeric(5,2), lepf_icprincipal smallint);


ALTER TABLE atendimentopublico.vw_ligacao_esgoto_perfil OWNER TO gsan_admin;

--
-- TOC entry 257 (class 1259 OID 3673572)
-- Name: vw_ligacao_esgoto_situacao; Type: VIEW; Schema: atendimentopublico; Owner: gsan_admin
--

CREATE VIEW vw_ligacao_esgoto_situacao AS
    SELECT table1.lest_id, table1.lest_dsligacaoesgotosituacao, table1.lest_icuso, table1.lest_tmultimaalteracao, table1.lest_dsabreviado, table1.lest_icfaturamento, table1.lest_nnvolumeminimo, table1.lest_icexistenciarede, table1.lest_icexistencialigacao, table1.lest_iccadastradaesgoto, table1.lest_icativaesgoto, table1.lest_icdesligadaesgoto, table1.lest_icanaliseesgoto FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from  atendimentopublico.ligacao_esgoto_situacao'::text) table1(lest_id integer, lest_dsligacaoesgotosituacao character varying(20), lest_icuso smallint, lest_tmultimaalteracao timestamp without time zone, lest_dsabreviado character(3), lest_icfaturamento smallint, lest_nnvolumeminimo integer, lest_icexistenciarede smallint, lest_icexistencialigacao smallint, lest_iccadastradaesgoto smallint, lest_icativaesgoto smallint, lest_icdesligadaesgoto smallint, lest_icanaliseesgoto integer);


ALTER TABLE atendimentopublico.vw_ligacao_esgoto_situacao OWNER TO gsan_admin;

--
-- TOC entry 258 (class 1259 OID 3673576)
-- Name: vw_meio_solicitacao; Type: VIEW; Schema: atendimentopublico; Owner: gsan_admin
--

CREATE VIEW vw_meio_solicitacao AS
    SELECT table1.meso_id, table1.meso_dsmeiosolicitacao, table1.meso_dsabreviado, table1.meso_icuso, table1.meso_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from atendimentopublico.meio_solicitacao'::text) table1(meso_id integer, meso_dsmeiosolicitacao character varying(50), meso_dsabreviado character(8), meso_icuso smallint, meso_tmultimaalteracao timestamp without time zone);


ALTER TABLE atendimentopublico.vw_meio_solicitacao OWNER TO gsan_admin;

--
-- TOC entry 259 (class 1259 OID 3673580)
-- Name: vw_solicit_tipo_especificacao; Type: VIEW; Schema: atendimentopublico; Owner: gsan_admin
--

CREATE VIEW vw_solicit_tipo_especificacao AS
    SELECT table1.step_id, table1.step_dssolicitacaotipoespecificacao, table1.step_nndiaprazo, table1.step_icpavimentocalcada, table1.step_icpavimentorua, table1.step_iccobrancamaterial, table1.step_icmatricula, table1.step_icparecerencerramento, table1.step_icgeracaodebito, table1.step_icgeracaocredito, table1.step_tmultimaalteracao, table1.sotp_id, table1.unid_id, table1.svtp_id, table1.step_icgeracaoordemservico, table1.step_iccliente, table1.esim_id, table1.step_icuso, table1.step_icligacaoagua, table1.step_icsolicitante, table1.step_icverificardebito, table1.dbtp_id, table1.step_vldebito, table1.step_icpermitealterarvalor, table1.step_iccobrarjuros, table1.step_icdocumentoobrigatorio, table1.step_icencerramentoautomatico, table1.step_idnovora, table1.step_icurgencia, table1.step_icvaldoresponsavel, table1.step_icinformarcontara, table1.step_nncodigoconstante, table1.step_icinformarpgtoduplicidade FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from atendimentopublico.solicitacao_tipo_especificacao'::text) table1(step_id integer, step_dssolicitacaotipoespecificacao character varying(50), step_nndiaprazo integer, step_icpavimentocalcada smallint, step_icpavimentorua smallint, step_iccobrancamaterial integer, step_icmatricula integer, step_icparecerencerramento integer, step_icgeracaodebito integer, step_icgeracaocredito integer, step_tmultimaalteracao timestamp without time zone, sotp_id integer, unid_id integer, svtp_id integer, step_icgeracaoordemservico smallint, step_iccliente smallint, esim_id integer, step_icuso smallint, step_icligacaoagua smallint, step_icsolicitante smallint, step_icverificardebito smallint, dbtp_id integer, step_vldebito numeric(13,2), step_icpermitealterarvalor smallint, step_iccobrarjuros smallint, step_icdocumentoobrigatorio smallint, step_icencerramentoautomatico smallint, step_idnovora integer, step_icurgencia integer, step_icvaldoresponsavel smallint, step_icinformarcontara integer, step_nncodigoconstante integer, step_icinformarpgtoduplicidade integer);


ALTER TABLE atendimentopublico.vw_solicit_tipo_especificacao OWNER TO gsan_admin;

--
-- TOC entry 260 (class 1259 OID 3673585)
-- Name: vw_solicitacao_tipo; Type: VIEW; Schema: atendimentopublico; Owner: gsan_admin
--

CREATE VIEW vw_solicitacao_tipo AS
    SELECT table1.sotp_id, table1.sotp_dssolicitacaotipo, table1.sotg_id, table1.sotp_tmultimaalteracao, table1.sotp_icfaltaagua, table1.sotp_ictarifasocial, table1.sotp_icuso, table1.sotp_icusosistema FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from atendimentopublico.solicitacao_tipo'::text) table1(sotp_id integer, sotp_dssolicitacaotipo character varying(50), sotg_id integer, sotp_tmultimaalteracao timestamp without time zone, sotp_icfaltaagua smallint, sotp_ictarifasocial smallint, sotp_icuso smallint, sotp_icusosistema smallint);


ALTER TABLE atendimentopublico.vw_solicitacao_tipo OWNER TO gsan_admin;

--
-- TOC entry 261 (class 1259 OID 3673589)
-- Name: vw_solicitacao_tipo_espec; Type: VIEW; Schema: atendimentopublico; Owner: gsan_admin
--

CREATE VIEW vw_solicitacao_tipo_espec AS
    SELECT table1.step_id, table1.step_dssolcttipoespec, table1.step_nndiaprazo, table1.step_icpavimentocalcada, table1.step_icpavimentorua, table1.step_iccobrancamaterial, table1.step_icmatricula, table1.step_icparecerencerramento, table1.step_icgeracaodebito, table1.step_icgeracaocredito, table1.step_tmultimaalteracao, table1.sotp_id, table1.unid_id, table1.svtp_id, table1.step_icgeracaoordemservico, table1.step_iccliente, table1.esim_id, table1.step_icuso, table1.step_icligacaoagua, table1.step_icsolicitante, table1.step_icverificardebito, table1.dbtp_id, table1.step_vldebito, table1.step_icpermitealterarvalor, table1.step_iccobrarjuros, table1.step_icdocumentoobrigatorio, table1.step_icencerramentoautomatico, table1.step_idnovora, table1.step_icurgencia, table1.step_icvaldocresponsavel, table1.step_icinformarcontara, table1.step_nncodigoconstante, table1.step_icinformarpgtoduplicidade, table1.step_icalterarvencimento FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from atendimentopublico.solicitacao_tipo_espec'::text) table1(step_id integer, step_dssolcttipoespec character varying(50), step_nndiaprazo integer, step_icpavimentocalcada smallint, step_icpavimentorua smallint, step_iccobrancamaterial integer, step_icmatricula integer, step_icparecerencerramento integer, step_icgeracaodebito integer, step_icgeracaocredito integer, step_tmultimaalteracao timestamp without time zone, sotp_id integer, unid_id integer, svtp_id integer, step_icgeracaoordemservico smallint, step_iccliente smallint, esim_id integer, step_icuso smallint, step_icligacaoagua smallint, step_icsolicitante smallint, step_icverificardebito smallint, dbtp_id integer, step_vldebito numeric(13,2), step_icpermitealterarvalor smallint, step_iccobrarjuros smallint, step_icdocumentoobrigatorio smallint, step_icencerramentoautomatico smallint, step_idnovora integer, step_icurgencia integer, step_icvaldocresponsavel smallint, step_icinformarcontara integer, step_nncodigoconstante integer, step_icinformarpgtoduplicidade integer, step_icalterarvencimento integer);


ALTER TABLE atendimentopublico.vw_solicitacao_tipo_espec OWNER TO gsan_admin;

--
-- TOC entry 262 (class 1259 OID 3673594)
-- Name: vw_solicitacao_tipo_especificacao; Type: VIEW; Schema: atendimentopublico; Owner: gsan_admin
--

CREATE VIEW vw_solicitacao_tipo_especificacao AS
    SELECT table1.step_id, table1.step_dssolicitacaotipoespecificacao, table1.step_nndiaprazo, table1.step_icpavimentocalcada, table1.step_icpavimentorua, table1.step_iccobrancamaterial, table1.step_icmatricula, table1.step_icparecerencerramento, table1.step_icgeracaodebito, table1.step_icgeracaocredito, table1.step_tmultimaalteracao, table1.sotp_id, table1.unid_id, table1.svtp_id, table1.step_icgeracaoordemservico, table1.step_iccliente, table1.esim_id, table1.step_icuso, table1.step_icligacaoagua, table1.step_icsolicitante, table1.step_icverificardebito, table1.dbtp_id, table1.step_vldebito, table1.step_icpermitealterarvalor, table1.step_iccobrarjuros, table1.step_icdocumentoobrigatorio, table1.step_icencerramentoautomatico, table1.step_idnovora, table1.step_icurgencia, table1.step_icvaldoresponsavel FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from atendimentopublico.solicitacao_tipo_especificacao'::text) table1(step_id integer, step_dssolicitacaotipoespecificacao character varying(50), step_nndiaprazo integer, step_icpavimentocalcada smallint, step_icpavimentorua smallint, step_iccobrancamaterial integer, step_icmatricula integer, step_icparecerencerramento integer, step_icgeracaodebito integer, step_icgeracaocredito integer, step_tmultimaalteracao timestamp without time zone, sotp_id integer, unid_id integer, svtp_id integer, step_icgeracaoordemservico smallint, step_iccliente smallint, esim_id integer, step_icuso smallint, step_icligacaoagua smallint, step_icsolicitante smallint, step_icverificardebito smallint, dbtp_id integer, step_vldebito numeric(13,2), step_icpermitealterarvalor smallint, step_iccobrarjuros smallint, step_icdocumentoobrigatorio smallint, step_icencerramentoautomatico smallint, step_idnovora integer, step_icurgencia integer, step_icvaldoresponsavel smallint);


ALTER TABLE atendimentopublico.vw_solicitacao_tipo_especificacao OWNER TO gsan_admin;

SET search_path = cadastro, pg_catalog;

--
-- TOC entry 263 (class 1259 OID 3673599)
-- Name: g_bairro; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_bairro (
    bair_id integer NOT NULL,
    muni_id integer NOT NULL,
    bair_cdbairro integer NOT NULL,
    bair_nmbairro character varying(30),
    bair_cdbairroprefeitura integer,
    bair_icuso smallint,
    bair_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE cadastro.g_bairro OWNER TO gsan_admin;

--
-- TOC entry 264 (class 1259 OID 3673603)
-- Name: g_bairro_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_bairro_x (
    bair_id integer NOT NULL,
    muni_id integer NOT NULL,
    bair_cdbairro integer NOT NULL,
    bair_nmbairro character varying(30),
    bair_cdbairroprefeitura integer,
    bair_icuso smallint,
    bair_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    bair_qtd integer
);


ALTER TABLE cadastro.g_bairro_x OWNER TO gsan_admin;

--
-- TOC entry 265 (class 1259 OID 3673607)
-- Name: g_bairro_xx; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_bairro_xx (
    bair_id integer NOT NULL,
    muni_id integer NOT NULL,
    bair_cdbairro integer NOT NULL,
    bair_nmbairro character varying(30),
    bair_cdbairroprefeitura integer,
    bair_icuso smallint,
    bair_qtd smallint
);


ALTER TABLE cadastro.g_bairro_xx OWNER TO gsan_admin;

--
-- TOC entry 266 (class 1259 OID 3673610)
-- Name: g_categoria; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_categoria (
    catg_id integer NOT NULL,
    catg_dscategoria character varying(15) NOT NULL,
    catg_dsabreviado character(3) NOT NULL,
    catg_nnconsumominimo integer,
    catg_nnconsumoestouro integer,
    catg_nnvezesmediaestouro smallint,
    catg_nnmediabaixoconsumo integer,
    catg_pcmediabaixoconsumo numeric(5,2),
    catg_nnconsumoalto integer,
    catg_nnvezesmediaaltoconsumo smallint,
    catg_icuso smallint,
    catg_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE cadastro.g_categoria OWNER TO gsan_admin;

--
-- TOC entry 267 (class 1259 OID 3673614)
-- Name: g_categoria_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_categoria_x (
    catg_id integer NOT NULL,
    catg_dscategoria character varying(15) NOT NULL,
    catg_dsabreviado character(3) NOT NULL,
    catg_nnconsumominimo integer,
    catg_nnconsumoestouro integer,
    catg_nnvezesmediaestouro smallint,
    catg_nnmediabaixoconsumo integer,
    catg_pcmediabaixoconsumo numeric(5,2),
    catg_nnconsumoalto integer,
    catg_nnvezesmediaaltoconsumo smallint,
    catg_icuso smallint,
    catg_qtd integer
);


ALTER TABLE cadastro.g_categoria_x OWNER TO gsan_admin;

--
-- TOC entry 268 (class 1259 OID 3673617)
-- Name: g_cliente_relacao_tipo; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_cliente_relacao_tipo (
    crtp_id integer NOT NULL,
    crtp_dsclienterelacaotipo character varying(30),
    crtp_icuso smallint,
    crtp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE cadastro.g_cliente_relacao_tipo OWNER TO gsan_admin;

--
-- TOC entry 269 (class 1259 OID 3673621)
-- Name: g_cliente_tipo; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_cliente_tipo (
    cltp_id integer NOT NULL,
    cltp_dsclientetipo character varying(50),
    cltp_icpessoafisicajuridica smallint,
    cltp_icuso smallint,
    cltp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    epod_id integer NOT NULL
);


ALTER TABLE cadastro.g_cliente_tipo OWNER TO gsan_admin;

--
-- TOC entry 270 (class 1259 OID 3673625)
-- Name: g_cliente_tipo_x; Type: TABLE; Schema: cadastro; Owner: postgres; Tablespace: 
--

CREATE TABLE g_cliente_tipo_x (
    cltp_id integer,
    cltp_dsclientetipo character varying(50),
    cltp_icpessoafisicajuridica smallint,
    cltp_icuso smallint,
    cltp_tmultimaalteracao timestamp without time zone,
    epod_id integer
);


ALTER TABLE cadastro.g_cliente_tipo_x OWNER TO postgres;

--
-- TOC entry 271 (class 1259 OID 3673632)
-- Name: g_empresa; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_empresa (
    empr_id integer NOT NULL,
    empr_nmempresa character varying(50),
    empr_dsemail character varying(80),
    empr_icuso smallint,
    empr_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    empr_icempresaprincipal smallint NOT NULL,
    empr_nmabreviadoempresa character varying(10)
);


ALTER TABLE cadastro.g_empresa OWNER TO gsan_admin;

--
-- TOC entry 272 (class 1259 OID 3673636)
-- Name: g_empresa_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_empresa_x (
    empr_id integer NOT NULL,
    empr_nmempresa character varying(50),
    empr_dsemail character varying(80),
    empr_icuso smallint,
    empr_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    empr_icempresaprincipal smallint NOT NULL,
    empr_nmabreviadoempresa character varying(10),
    empr_qdt integer
);


ALTER TABLE cadastro.g_empresa_x OWNER TO gsan_admin;

--
-- TOC entry 273 (class 1259 OID 3673640)
-- Name: g_esfera_poder; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_esfera_poder (
    epod_id integer NOT NULL,
    epod_dsesferapoder character varying(30),
    epod_icuso smallint,
    epod_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    epod_icpermitecndparaimovel smallint DEFAULT 2,
    epod_icpermitecndparacliente smallint DEFAULT 2
);


ALTER TABLE cadastro.g_esfera_poder OWNER TO gsan_admin;

--
-- TOC entry 5232 (class 0 OID 0)
-- Dependencies: 273
-- Name: COLUMN g_esfera_poder.epod_icpermitecndparaimovel; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_esfera_poder.epod_icpermitecndparaimovel IS 'Indicador se a esfera de poder permite a geracao  de certidao negativa para imovel (1-SIM, 2-NAO)';


--
-- TOC entry 5233 (class 0 OID 0)
-- Dependencies: 273
-- Name: COLUMN g_esfera_poder.epod_icpermitecndparacliente; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_esfera_poder.epod_icpermitecndparacliente IS 'Indicador se a esfera de poder permite a geracao  de certidao negativa para cliente (1-SIM, 2-NAO)';


--
-- TOC entry 274 (class 1259 OID 3673652)
-- Name: g_esfera_poder_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_esfera_poder_x (
    epod_id integer NOT NULL,
    epod_dsesferapoder character varying(30),
    epod_icuso smallint,
    epod_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    epod_icpermitecndparaimovel smallint DEFAULT 2,
    epod_icpermitecndparacliente smallint DEFAULT 2,
    epod_qtd integer
);


ALTER TABLE cadastro.g_esfera_poder_x OWNER TO gsan_admin;

--
-- TOC entry 275 (class 1259 OID 3673658)
-- Name: g_gerencia_regional; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_gerencia_regional (
    greg_id integer NOT NULL,
    greg_nmregional character varying(25) NOT NULL,
    greg_nmabreviado character(3) NOT NULL,
    greg_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    supt_id integer
);


ALTER TABLE cadastro.g_gerencia_regional OWNER TO gsan_admin;

--
-- TOC entry 5236 (class 0 OID 0)
-- Dependencies: 275
-- Name: COLUMN g_gerencia_regional.supt_id; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_gerencia_regional.supt_id IS 'Id da Superitendencia';


--
-- TOC entry 276 (class 1259 OID 3673662)
-- Name: g_gerencia_regional_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_gerencia_regional_x (
    greg_id integer NOT NULL,
    greg_nmregional character varying(25) NOT NULL,
    greg_nmabreviado character(3) NOT NULL,
    greg_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    supt_id integer,
    greg_qtd integer
);


ALTER TABLE cadastro.g_gerencia_regional_x OWNER TO gsan_admin;

--
-- TOC entry 277 (class 1259 OID 3673666)
-- Name: g_imovel_perfil; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_imovel_perfil (
    iper_id integer NOT NULL,
    iper_dsimovelperfil character varying(20),
    iper_icuso smallint,
    iper_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    iper_icgeracaoautomatica smallint NOT NULL,
    iper_icinserirmanterperfil smallint DEFAULT 1 NOT NULL,
    iper_icgerardadosleitura smallint DEFAULT 1 NOT NULL
);


ALTER TABLE cadastro.g_imovel_perfil OWNER TO gsan_admin;

--
-- TOC entry 5239 (class 0 OID 0)
-- Dependencies: 277
-- Name: COLUMN g_imovel_perfil.iper_icinserirmanterperfil; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_imovel_perfil.iper_icinserirmanterperfil IS 'Indicador para saber se pode ou nao inserir/manter o perfil: 1 - Pode, 2 - Nao pode, apenas com permissao especial.';


--
-- TOC entry 5240 (class 0 OID 0)
-- Dependencies: 277
-- Name: COLUMN g_imovel_perfil.iper_icgerardadosleitura; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_imovel_perfil.iper_icgerardadosleitura IS 'Indicador para geracao de dados do imovel para leitura';


--
-- TOC entry 278 (class 1259 OID 3673677)
-- Name: g_imovel_perfil_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_imovel_perfil_x (
    iper_id integer NOT NULL,
    iper_dsimovelperfil character varying(20),
    iper_icuso smallint,
    iper_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    iper_icgeracaoautomatica smallint NOT NULL,
    iper_icinserirmanterperfil smallint DEFAULT 1 NOT NULL,
    iper_icgerardadosleitura smallint DEFAULT 1 NOT NULL,
    iper_qtd integer
);


ALTER TABLE cadastro.g_imovel_perfil_x OWNER TO gsan_admin;

--
-- TOC entry 279 (class 1259 OID 3673683)
-- Name: g_imovel_situacao; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_imovel_situacao (
    imst_id integer NOT NULL,
    istp_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer,
    imst_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE cadastro.g_imovel_situacao OWNER TO gsan_admin;

--
-- TOC entry 280 (class 1259 OID 3673687)
-- Name: g_imovel_situacao_tipo; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_imovel_situacao_tipo (
    istp_id integer NOT NULL,
    istp_dsimovelsituacaotipo character varying(20),
    istp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE cadastro.g_imovel_situacao_tipo OWNER TO gsan_admin;

--
-- TOC entry 281 (class 1259 OID 3673691)
-- Name: g_imovel_situacao_tipo_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_imovel_situacao_tipo_x (
    istp_id integer NOT NULL,
    istp_dsimovelsituacaotipo character varying(20),
    istp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    istp_qtd integer
);


ALTER TABLE cadastro.g_imovel_situacao_tipo_x OWNER TO gsan_admin;

--
-- TOC entry 282 (class 1259 OID 3673695)
-- Name: g_imovel_situacao_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_imovel_situacao_x (
    imst_id integer NOT NULL,
    istp_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer,
    imst_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    istp_qtd integer
);


ALTER TABLE cadastro.g_imovel_situacao_x OWNER TO gsan_admin;

--
-- TOC entry 283 (class 1259 OID 3673699)
-- Name: g_localidade; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_localidade (
    loca_id integer NOT NULL,
    loca_nmlocalidade character varying(30) NOT NULL,
    loca_cdelo integer NOT NULL,
    greg_id integer NOT NULL,
    lpor_id integer,
    loca_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    uneg_id integer NOT NULL,
    loca_cdcentrocusto character varying(10),
    loca_icuso smallint,
    loca_nmmunicipio character varying(40),
    muni_idprincipal integer
);


ALTER TABLE cadastro.g_localidade OWNER TO gsan_admin;

--
-- TOC entry 5247 (class 0 OID 0)
-- Dependencies: 283
-- Name: COLUMN g_localidade.loca_icuso; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_localidade.loca_icuso IS 'Indicador de Uso (SIM = 1, NaO = 2)';


--
-- TOC entry 5248 (class 0 OID 0)
-- Dependencies: 283
-- Name: COLUMN g_localidade.loca_nmmunicipio; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_localidade.loca_nmmunicipio IS 'Nome do Municipio';


--
-- TOC entry 5249 (class 0 OID 0)
-- Dependencies: 283
-- Name: COLUMN g_localidade.muni_idprincipal; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_localidade.muni_idprincipal IS 'Identificador do municÃ­pio principal associado a localidade';


--
-- TOC entry 284 (class 1259 OID 3673707)
-- Name: g_localidade_porte; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_localidade_porte (
    lpor_id integer NOT NULL,
    lpor_dslocalidadeporte character varying(20),
    lpor_icuso smallint,
    lpor_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE cadastro.g_localidade_porte OWNER TO gsan_admin;

--
-- TOC entry 285 (class 1259 OID 3673711)
-- Name: g_localidade_porte_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_localidade_porte_x (
    lpor_id integer NOT NULL,
    lpor_dslocalidadeporte character varying(20),
    lpor_icuso smallint,
    lpor_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    lpor_qtd integer
);


ALTER TABLE cadastro.g_localidade_porte_x OWNER TO gsan_admin;

--
-- TOC entry 286 (class 1259 OID 3673715)
-- Name: g_localidade_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_localidade_x (
    loca_id integer NOT NULL,
    loca_nmlocalidade character varying(30) NOT NULL,
    loca_cdelo integer NOT NULL,
    greg_id integer NOT NULL,
    lpor_id integer,
    loca_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    uneg_id integer NOT NULL,
    loca_cdcentrocusto character varying(10),
    loca_icuso smallint,
    loca_soma integer
);


ALTER TABLE cadastro.g_localidade_x OWNER TO gsan_admin;

--
-- TOC entry 5253 (class 0 OID 0)
-- Dependencies: 286
-- Name: COLUMN g_localidade_x.loca_icuso; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_localidade_x.loca_icuso IS 'Indicador de Uso (SIM = 1, NaO = 2)';


--
-- TOC entry 287 (class 1259 OID 3673719)
-- Name: g_microrregiao; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_microrregiao (
    mreg_id integer NOT NULL,
    regi_id integer NOT NULL,
    mreg_nmmicrorregiao character varying(30),
    mreg_icuso smallint,
    mreg_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE cadastro.g_microrregiao OWNER TO gsan_admin;

--
-- TOC entry 288 (class 1259 OID 3673723)
-- Name: g_microrregiao_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_microrregiao_x (
    mreg_id integer NOT NULL,
    regi_id integer NOT NULL,
    mreg_nmmicrorregiao character varying(30),
    mreg_icuso smallint,
    mreg_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    mreg_qtd integer
);


ALTER TABLE cadastro.g_microrregiao_x OWNER TO gsan_admin;

--
-- TOC entry 289 (class 1259 OID 3673727)
-- Name: g_municipio; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_municipio (
    muni_id integer NOT NULL,
    muni_nmmunicipio character varying(30),
    muni_cdcepinicio integer,
    muni_cdcepfim integer,
    muni_cdddd smallint,
    mreg_id integer NOT NULL,
    muni_icuso smallint,
    muni_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    muni_dtconcessaoinicio date,
    muni_dtconcessaofim date
);


ALTER TABLE cadastro.g_municipio OWNER TO gsan_admin;

--
-- TOC entry 290 (class 1259 OID 3673735)
-- Name: g_municipio_x; Type: TABLE; Schema: cadastro; Owner: postgres; Tablespace: 
--

CREATE TABLE g_municipio_x (
    muni_id integer,
    muni_nmmunicipio character varying(30),
    muni_cdcepinicio integer,
    muni_cdcepfim integer,
    muni_cdddd smallint,
    mreg_id integer,
    muni_icuso smallint,
    muni_tmultimaalteracao timestamp without time zone,
    muni_dtconcessaoinicio date,
    muni_dtconcessaofim date
);


ALTER TABLE cadastro.g_municipio_x OWNER TO postgres;

--
-- TOC entry 291 (class 1259 OID 3673738)
-- Name: g_quadra; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_quadra (
    qdra_id integer NOT NULL,
    stcm_id integer NOT NULL,
    qdra_nnquadra integer NOT NULL,
    qdra_icredeagua smallint,
    qdra_icredeesgoto smallint,
    rota_id integer NOT NULL,
    qdra_nnrotasequencia smallint,
    qdra_dtimplantacao date,
    qdra_icuso smallint,
    qdra_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    diop_id integer
);


ALTER TABLE cadastro.g_quadra OWNER TO gsan_admin;

--
-- TOC entry 5258 (class 0 OID 0)
-- Dependencies: 291
-- Name: COLUMN g_quadra.diop_id; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_quadra.diop_id IS 'Id do Distrito Operacional';


--
-- TOC entry 292 (class 1259 OID 3673742)
-- Name: g_quadra_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_quadra_x (
    qdra_id integer NOT NULL,
    stcm_id integer NOT NULL,
    qdra_nnquadra integer NOT NULL,
    qdra_icredeagua smallint,
    qdra_icredeesgoto smallint,
    rota_id integer NOT NULL,
    qdra_nnrotasequencia smallint,
    qdra_dtimplantacao date,
    qdra_icuso smallint,
    qdra_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    diop_id integer,
    qdra_qtd integer
);


ALTER TABLE cadastro.g_quadra_x OWNER TO gsan_admin;

--
-- TOC entry 293 (class 1259 OID 3673746)
-- Name: g_regiao; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_regiao (
    regi_id integer NOT NULL,
    regi_nmregiao character varying(20),
    regi_icuso smallint,
    regi_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE cadastro.g_regiao OWNER TO gsan_admin;

--
-- TOC entry 294 (class 1259 OID 3673750)
-- Name: g_regiao_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_regiao_x (
    regi_id integer NOT NULL,
    regi_nmregiao character varying(20),
    regi_icuso smallint,
    regi_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    regi_qtd integer
);


ALTER TABLE cadastro.g_regiao_x OWNER TO gsan_admin;

--
-- TOC entry 295 (class 1259 OID 3673754)
-- Name: g_setor_comercial; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_setor_comercial (
    stcm_id integer NOT NULL,
    loca_id integer NOT NULL,
    stcm_cdsetorcomercial integer NOT NULL,
    stcm_nmsetorcomercial character varying(50) NOT NULL,
    stcm_icuso smallint,
    stcm_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    muni_id integer NOT NULL
);


ALTER TABLE cadastro.g_setor_comercial OWNER TO gsan_admin;

--
-- TOC entry 296 (class 1259 OID 3673758)
-- Name: g_setor_comercial_x; Type: TABLE; Schema: cadastro; Owner: postgres; Tablespace: 
--

CREATE TABLE g_setor_comercial_x (
    stcm_id integer,
    loca_id integer,
    stcm_cdsetorcomercial integer,
    stcm_nmsetorcomercial character varying(50),
    stcm_icuso smallint,
    stcm_tmultimaalteracao timestamp without time zone,
    muni_id integer
);


ALTER TABLE cadastro.g_setor_comercial_x OWNER TO postgres;

--
-- TOC entry 297 (class 1259 OID 3673761)
-- Name: g_subcategoria; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_subcategoria (
    scat_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_cdsubcategoria integer,
    scat_dssubcategoria character varying(50),
    scat_icuso smallint,
    scat_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    scat_cdgruposubcategoria integer,
    scat_icrural smallint DEFAULT 2 NOT NULL
);


ALTER TABLE cadastro.g_subcategoria OWNER TO gsan_admin;

--
-- TOC entry 5264 (class 0 OID 0)
-- Dependencies: 297
-- Name: COLUMN g_subcategoria.scat_icrural; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_subcategoria.scat_icrural IS 'Indicador se a subcategoria é rural';


--
-- TOC entry 298 (class 1259 OID 3673766)
-- Name: g_subcategoria_x; Type: TABLE; Schema: cadastro; Owner: postgres; Tablespace: 
--

CREATE TABLE g_subcategoria_x (
    scat_id integer,
    catg_id integer,
    scat_cdsubcategoria integer,
    scat_dssubcategoria character varying(50),
    scat_icuso smallint,
    scat_tmultimaalteracao timestamp without time zone,
    scat_cdgruposubcategoria integer,
    scat_icrural smallint
);


ALTER TABLE cadastro.g_subcategoria_x OWNER TO postgres;

--
-- TOC entry 299 (class 1259 OID 3673769)
-- Name: g_superintendencia; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_superintendencia (
    supt_id integer NOT NULL,
    supt_nmsuperint character varying(25),
    supt_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE cadastro.g_superintendencia OWNER TO gsan_admin;

--
-- TOC entry 5266 (class 0 OID 0)
-- Dependencies: 299
-- Name: COLUMN g_superintendencia.supt_id; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_superintendencia.supt_id IS 'Id da tabela';


--
-- TOC entry 5267 (class 0 OID 0)
-- Dependencies: 299
-- Name: COLUMN g_superintendencia.supt_nmsuperint; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_superintendencia.supt_nmsuperint IS 'Descricao do nome da superintendencia';


--
-- TOC entry 5268 (class 0 OID 0)
-- Dependencies: 299
-- Name: COLUMN g_superintendencia.supt_tmultimaalteracao; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN g_superintendencia.supt_tmultimaalteracao IS 'Data e Hora da ultima Alteracao';


--
-- TOC entry 300 (class 1259 OID 3673773)
-- Name: g_unidade_negocio; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_unidade_negocio (
    uneg_id integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_nmunidadenegocio character varying(50) NOT NULL,
    uneg_nmabreviado character(4) NOT NULL,
    uneg_icuso smallint NOT NULL,
    uneg_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    uneg_qtd integer
);


ALTER TABLE cadastro.g_unidade_negocio OWNER TO gsan_admin;

--
-- TOC entry 301 (class 1259 OID 3673777)
-- Name: g_unidade_negocio_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_unidade_negocio_x (
    uneg_id integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_nmunidadenegocio character varying(50) NOT NULL,
    uneg_nmabreviado character(4) NOT NULL,
    uneg_icuso smallint NOT NULL,
    uneg_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    uneg_qtd integer
);


ALTER TABLE cadastro.g_unidade_negocio_x OWNER TO gsan_admin;

--
-- TOC entry 302 (class 1259 OID 3673781)
-- Name: g_unidade_organizacional; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_unidade_organizacional (
    unid_id integer NOT NULL,
    unid_dsunidade character varying(80) NOT NULL,
    unid_dssiglaunidade character(5),
    unid_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE cadastro.g_unidade_organizacional OWNER TO gsan_admin;

--
-- TOC entry 303 (class 1259 OID 3673785)
-- Name: g_unidade_organizacional_x; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_unidade_organizacional_x (
    unid_id integer NOT NULL,
    unid_dsunidade character varying(80) NOT NULL,
    unid_dssiglaunidade character(5),
    unid_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    unid_qtd integer
);


ALTER TABLE cadastro.g_unidade_organizacional_x OWNER TO gsan_admin;

--
-- TOC entry 304 (class 1259 OID 3673789)
-- Name: indicador; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE indicador (
    indi_id integer NOT NULL,
    indi_dsindicador character(3) NOT NULL,
    indi_icuso smallint DEFAULT 1 NOT NULL,
    indi_dsindicadorhidrometro character(15) NOT NULL,
    indi_icusohidrometro smallint DEFAULT 1 NOT NULL,
    indi_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE cadastro.indicador OWNER TO gsan_admin;

--
-- TOC entry 5274 (class 0 OID 0)
-- Dependencies: 304
-- Name: COLUMN indicador.indi_id; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN indicador.indi_id IS 'Chave primaria do Indicador';


--
-- TOC entry 5275 (class 0 OID 0)
-- Dependencies: 304
-- Name: COLUMN indicador.indi_dsindicador; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN indicador.indi_dsindicador IS 'Descricao do indicador SIM NAO';


--
-- TOC entry 5276 (class 0 OID 0)
-- Dependencies: 304
-- Name: COLUMN indicador.indi_icuso; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN indicador.indi_icuso IS 'Indicador de Uso do indicador ';


--
-- TOC entry 5277 (class 0 OID 0)
-- Dependencies: 304
-- Name: COLUMN indicador.indi_dsindicadorhidrometro; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN indicador.indi_dsindicadorhidrometro IS 'Descricao do indicador de hidrometro';


--
-- TOC entry 5278 (class 0 OID 0)
-- Dependencies: 304
-- Name: COLUMN indicador.indi_icusohidrometro; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN indicador.indi_icusohidrometro IS 'Indicador de uso do indicador de hidrometro';


--
-- TOC entry 5279 (class 0 OID 0)
-- Dependencies: 304
-- Name: COLUMN indicador.indi_tmultimaalteracao; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN indicador.indi_tmultimaalteracao IS 'Data da ultima alteracao';


--
-- TOC entry 305 (class 1259 OID 3673795)
-- Name: rg_res_lig_econ; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE rg_res_lig_econ (
    rele_id integer NOT NULL,
    rele_amreferencia integer NOT NULL,
    regi_id integer NOT NULL,
    mreg_id integer NOT NULL,
    muni_id integer NOT NULL,
    bair_id integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    rele_ichidrometro smallint NOT NULL,
    rele_icvolumefixadoagua smallint NOT NULL,
    rele_icvolumefixadoesgoto smallint NOT NULL,
    rele_icpoco smallint NOT NULL,
    rele_qtligacoes integer NOT NULL,
    rele_qteconomias integer NOT NULL,
    rele_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    rele_ichidrometropoco smallint NOT NULL,
    rele_qtligacoesnovasagua integer DEFAULT 0 NOT NULL,
    rele_qtligacoesnovasesgoto integer DEFAULT 0 NOT NULL
);


ALTER TABLE cadastro.rg_res_lig_econ OWNER TO gsan_admin;

--
-- TOC entry 5281 (class 0 OID 0)
-- Dependencies: 305
-- Name: COLUMN rg_res_lig_econ.rele_qtligacoesnovasagua; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN rg_res_lig_econ.rele_qtligacoesnovasagua IS 'Quantidade de novas ligacoes de agua cadastradas no mes';


--
-- TOC entry 5282 (class 0 OID 0)
-- Dependencies: 305
-- Name: COLUMN rg_res_lig_econ.rele_qtligacoesnovasesgoto; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN rg_res_lig_econ.rele_qtligacoesnovasesgoto IS 'Quantidade de novas ligacoes de esgoto cadastradas no mes';


--
-- TOC entry 306 (class 1259 OID 3673801)
-- Name: seq_g_cliente_relacao_tipo; Type: SEQUENCE; Schema: cadastro; Owner: gsan_admin
--

CREATE SEQUENCE seq_g_cliente_relacao_tipo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cadastro.seq_g_cliente_relacao_tipo OWNER TO gsan_admin;

--
-- TOC entry 307 (class 1259 OID 3673807)
-- Name: seq_un_res_lig_econ; Type: SEQUENCE; Schema: cadastro; Owner: gsan_admin
--

CREATE SEQUENCE seq_un_res_lig_econ
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cadastro.seq_un_res_lig_econ OWNER TO gsan_admin;

--
-- TOC entry 308 (class 1259 OID 3673809)
-- Name: sequence_g_superintendencia; Type: SEQUENCE; Schema: cadastro; Owner: gsan_admin
--

CREATE SEQUENCE sequence_g_superintendencia
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cadastro.sequence_g_superintendencia OWNER TO gsan_admin;

--
-- TOC entry 309 (class 1259 OID 3673811)
-- Name: sequence_indicador; Type: SEQUENCE; Schema: cadastro; Owner: gsan_admin
--

CREATE SEQUENCE sequence_indicador
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cadastro.sequence_indicador OWNER TO gsan_admin;

--
-- TOC entry 310 (class 1259 OID 3673813)
-- Name: sequence_rg_resumo_ligacao_economia; Type: SEQUENCE; Schema: cadastro; Owner: gsan_admin
--

CREATE SEQUENCE sequence_rg_resumo_ligacao_economia
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cadastro.sequence_rg_resumo_ligacao_economia OWNER TO gsan_admin;

--
-- TOC entry 311 (class 1259 OID 3673815)
-- Name: sequence_un_resumo_ligacao_economia_ref_2007; Type: SEQUENCE; Schema: cadastro; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_ligacao_economia_ref_2007
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cadastro.sequence_un_resumo_ligacao_economia_ref_2007 OWNER TO gsan_admin;

--
-- TOC entry 312 (class 1259 OID 3673817)
-- Name: sequence_un_resumo_ligacao_economia_ref_2008; Type: SEQUENCE; Schema: cadastro; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_ligacao_economia_ref_2008
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cadastro.sequence_un_resumo_ligacao_economia_ref_2008 OWNER TO gsan_admin;

--
-- TOC entry 313 (class 1259 OID 3673819)
-- Name: sequence_un_resumo_ligacao_economia_ref_2009; Type: SEQUENCE; Schema: cadastro; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_ligacao_economia_ref_2009
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cadastro.sequence_un_resumo_ligacao_economia_ref_2009 OWNER TO gsan_admin;

--
-- TOC entry 314 (class 1259 OID 3673821)
-- Name: un_res_lig_econ; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_res_lig_econ (
    rele_id integer NOT NULL,
    rele_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    qdra_id integer NOT NULL,
    rota_id integer NOT NULL,
    rele_cdsetorcomercial integer NOT NULL,
    rele_nnquadra integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    rele_ichidrometro smallint NOT NULL,
    rele_icvolumefixadoagua smallint NOT NULL,
    rele_icvolumefixadoesgoto smallint NOT NULL,
    rele_icpoco smallint NOT NULL,
    rele_qtligacoes integer NOT NULL,
    rele_qteconomias integer NOT NULL,
    rele_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    rele_ichidrometropoco smallint NOT NULL,
    cstf_id integer DEFAULT 0,
    rele_cdrota integer,
    rele_qtligacoesnovasagua integer DEFAULT 0 NOT NULL,
    rele_qtligacoesnovasesgoto integer DEFAULT 0 NOT NULL,
    rele_qtligacoescortesmes integer,
    rele_qtligacoesreligadasmes integer
);


ALTER TABLE cadastro.un_res_lig_econ OWNER TO gsan_admin;

--
-- TOC entry 5292 (class 0 OID 0)
-- Dependencies: 314
-- Name: COLUMN un_res_lig_econ.rele_qtligacoesnovasagua; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN un_res_lig_econ.rele_qtligacoesnovasagua IS 'Quantidade de novas ligacoes de agua cadastradas no mes';


--
-- TOC entry 5293 (class 0 OID 0)
-- Dependencies: 314
-- Name: COLUMN un_res_lig_econ.rele_qtligacoesnovasesgoto; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN un_res_lig_econ.rele_qtligacoesnovasesgoto IS 'Quantidade de novas ligacoes de esgoto cadastradas no mes';


--
-- TOC entry 5294 (class 0 OID 0)
-- Dependencies: 314
-- Name: COLUMN un_res_lig_econ.rele_qtligacoescortesmes; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN un_res_lig_econ.rele_qtligacoescortesmes IS 'Quantidade de ligacoes de agua cortadas no mes';


--
-- TOC entry 5295 (class 0 OID 0)
-- Dependencies: 314
-- Name: COLUMN un_res_lig_econ.rele_qtligacoesreligadasmes; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN un_res_lig_econ.rele_qtligacoesreligadasmes IS 'Quantidade de ligacoes de agua religadas no mes';


--
-- TOC entry 315 (class 1259 OID 3673832)
-- Name: un_resi_lig_eco; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resi_lig_eco (
    rele_id integer NOT NULL,
    rele_idma integer,
    rele_amreferencia integer,
    rele_anoreferencia text,
    rele_mesreferencia text,
    greg_id integer,
    uneg_id integer,
    loca_id integer,
    loca_cdelo integer,
    stcm_id integer,
    qdra_id integer,
    rota_id integer,
    rele_cdsetorcomercial integer,
    rele_nnquadra integer,
    iper_id integer,
    last_id integer,
    lest_id integer,
    catg_id integer,
    scat_id integer,
    epod_id integer,
    cltp_id integer,
    lapf_id integer,
    lepf_id integer,
    cstf_id integer,
    rele_ichidrometro smallint,
    rele_icvolumefixadoagua smallint,
    rele_icvolumefixadoesgoto smallint,
    rele_icpoco smallint,
    rele_ichidrometropoco smallint,
    rele_qtligacoesativasagua integer,
    rele_qtligacoesativasaguama integer,
    rele_qtligacoesativasesgoto integer,
    rele_qtligacoesativasesgotoma integer,
    rele_qteconomiastotaisagua integer,
    rele_qteconomiastotaisaguama integer,
    rele_qteconomiastotaisesgoto integer,
    rele_qteconomiastotaisesgotoma integer,
    rele_qtcliaguapotenciaisfact integer,
    rele_qtcliagualigadoscortados integer,
    rele_qtclientesaguasuprimidos integer,
    rele_qteconaguapotenciaisfact integer,
    rele_qteconomiasagualigadoscortados integer,
    rele_qteconomiasaguasuprimidos integer,
    rele_qtcliesgotopotenciaisfact integer,
    rele_qtclientesesgotoligados integer,
    rele_qtcliesgotoligadosforauso integer,
    rele_qteconesgpotenciaisfact integer,
    rele_qteconomiasesgotoligados integer,
    rele_qteconesgligadosforauso integer,
    rele_tmultimaalteracao timestamp without time zone
);


ALTER TABLE cadastro.un_resi_lig_eco OWNER TO gsan_admin;

--
-- TOC entry 316 (class 1259 OID 3673846)
-- Name: un_resumo_ligacao_economia_ref_2007; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_ligacao_economia_ref_2007 (
    rele_id integer NOT NULL,
    rele_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    rele_cdsetorcomercial integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    rele_ichidrometro smallint NOT NULL,
    rele_icvolumefixadoagua smallint NOT NULL,
    rele_icvolumefixadoesgoto smallint NOT NULL,
    rele_icpoco smallint NOT NULL,
    rele_qtligacoes integer NOT NULL,
    rele_qteconomias integer NOT NULL,
    rele_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    rele_ichidrometropoco smallint NOT NULL,
    cstf_id integer NOT NULL,
    rele_qtligacoesnovasagua integer DEFAULT 0 NOT NULL,
    rele_qtligacoesnovasesgoto integer DEFAULT 0 NOT NULL
);


ALTER TABLE cadastro.un_resumo_ligacao_economia_ref_2007 OWNER TO gsan_admin;

--
-- TOC entry 5298 (class 0 OID 0)
-- Dependencies: 316
-- Name: COLUMN un_resumo_ligacao_economia_ref_2007.rele_qtligacoesnovasagua; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_ligacao_economia_ref_2007.rele_qtligacoesnovasagua IS 'Quantidade de novas ligacoes de agua cadastradas no mes';


--
-- TOC entry 5299 (class 0 OID 0)
-- Dependencies: 316
-- Name: COLUMN un_resumo_ligacao_economia_ref_2007.rele_qtligacoesnovasesgoto; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_ligacao_economia_ref_2007.rele_qtligacoesnovasesgoto IS 'Quantidade de novas ligacoes de esgoto cadastradas no mes';


--
-- TOC entry 317 (class 1259 OID 3673852)
-- Name: un_resumo_ligacao_economia_ref_2008; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_ligacao_economia_ref_2008 (
    rele_id integer NOT NULL,
    rele_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    rele_cdsetorcomercial integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    rele_ichidrometro smallint NOT NULL,
    rele_icvolumefixadoagua smallint NOT NULL,
    rele_icvolumefixadoesgoto smallint NOT NULL,
    rele_icpoco smallint NOT NULL,
    rele_qtligacoes integer NOT NULL,
    rele_qteconomias integer NOT NULL,
    rele_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    rele_ichidrometropoco smallint NOT NULL,
    cstf_id integer NOT NULL,
    rele_qtligacoesnovasagua integer DEFAULT 0 NOT NULL,
    rele_qtligacoesnovasesgoto integer DEFAULT 0 NOT NULL
);


ALTER TABLE cadastro.un_resumo_ligacao_economia_ref_2008 OWNER TO gsan_admin;

--
-- TOC entry 5301 (class 0 OID 0)
-- Dependencies: 317
-- Name: COLUMN un_resumo_ligacao_economia_ref_2008.rele_qtligacoesnovasagua; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_ligacao_economia_ref_2008.rele_qtligacoesnovasagua IS 'Quantidade de novas ligacoes de agua cadastradas no mes';


--
-- TOC entry 5302 (class 0 OID 0)
-- Dependencies: 317
-- Name: COLUMN un_resumo_ligacao_economia_ref_2008.rele_qtligacoesnovasesgoto; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_ligacao_economia_ref_2008.rele_qtligacoesnovasesgoto IS 'Quantidade de novas ligacoes de esgoto cadastradas no mes';


--
-- TOC entry 318 (class 1259 OID 3673858)
-- Name: un_resumo_ligacao_economia_ref_2009; Type: TABLE; Schema: cadastro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_ligacao_economia_ref_2009 (
    rele_id integer NOT NULL,
    rele_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    rele_cdsetorcomercial integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    rele_ichidrometro smallint NOT NULL,
    rele_icvolumefixadoagua smallint NOT NULL,
    rele_icvolumefixadoesgoto smallint NOT NULL,
    rele_icpoco smallint NOT NULL,
    rele_qtligacoes integer NOT NULL,
    rele_qteconomias integer NOT NULL,
    rele_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    rele_ichidrometropoco smallint NOT NULL,
    cstf_id integer NOT NULL,
    rele_qtligacoesnovasagua integer DEFAULT 0 NOT NULL,
    rele_qtligacoesnovasesgoto integer DEFAULT 0 NOT NULL
);


ALTER TABLE cadastro.un_resumo_ligacao_economia_ref_2009 OWNER TO gsan_admin;

--
-- TOC entry 5304 (class 0 OID 0)
-- Dependencies: 318
-- Name: COLUMN un_resumo_ligacao_economia_ref_2009.rele_qtligacoesnovasagua; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_ligacao_economia_ref_2009.rele_qtligacoesnovasagua IS 'Quantidade de novas ligacoes de agua cadastradas no mes';


--
-- TOC entry 5305 (class 0 OID 0)
-- Dependencies: 318
-- Name: COLUMN un_resumo_ligacao_economia_ref_2009.rele_qtligacoesnovasesgoto; Type: COMMENT; Schema: cadastro; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_ligacao_economia_ref_2009.rele_qtligacoesnovasesgoto IS 'Quantidade de novas ligacoes de esgoto cadastradas no mes';


--
-- TOC entry 319 (class 1259 OID 3673864)
-- Name: vw_bairro; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_bairro AS
    SELECT table1.bair_id, table1.muni_id, table1.bair_cdbairro, table1.bair_nmbairro, table1.bair_cdbairroprefeitura, table1.bair_icuso, table1.bair_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.bairro'::text) table1(bair_id integer, muni_id integer, bair_cdbairro integer, bair_nmbairro character varying(30), bair_cdbairroprefeitura integer, bair_icuso smallint, bair_tmultimaalteracao timestamp without time zone);


ALTER TABLE cadastro.vw_bairro OWNER TO gsan_admin;

--
-- TOC entry 320 (class 1259 OID 3673873)
-- Name: vw_categoria; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_categoria AS
    SELECT table1.catg_id, table1.catg_dscategoria, table1.catg_dsabreviado, table1.catg_nnconsumominimo, table1.catg_nnconsumoestouro, table1.catg_nnvezesmediaestouro, table1.catg_nnmediabaixoconsumo, table1.catg_pcmediabaixoconsumo, table1.catg_nnconsumoalto, table1.catg_nnvezesmediaaltoconsumo, table1.catg_icuso, table1.catg_tmultimaalteracao, table1.cgtp_id, table1.catg_nnconsumomaximoec, table1.catg_iccobrancaacrescimos, table1.catg_nnmaxeconomiasvalidacao, table1.catg_nnfatoreconomias, table1.catg_icpermissaoespecial FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.categoria'::text) table1(catg_id integer, catg_dscategoria character varying(15), catg_dsabreviado character(3), catg_nnconsumominimo integer, catg_nnconsumoestouro integer, catg_nnvezesmediaestouro numeric(3,1), catg_nnmediabaixoconsumo integer, catg_pcmediabaixoconsumo numeric(5,2), catg_nnconsumoalto integer, catg_nnvezesmediaaltoconsumo numeric(3,1), catg_icuso smallint, catg_tmultimaalteracao timestamp without time zone, cgtp_id integer, catg_nnconsumomaximoec integer, catg_iccobrancaacrescimos smallint, catg_nnmaxeconomiasvalidacao integer, catg_nnfatoreconomias smallint, catg_icpermissaoespecial smallint);


ALTER TABLE cadastro.vw_categoria OWNER TO gsan_admin;

--
-- TOC entry 321 (class 1259 OID 3673878)
-- Name: vw_cliente_relacao_tipo; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_cliente_relacao_tipo AS
    SELECT table1.crtp_id, table1.crtp_dsclienterelacaotipo, table1.crtp_icuso, table1.crtp_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.cliente_relacao_tipo'::text) table1(crtp_id integer, crtp_dsclienterelacaotipo character varying(30), crtp_icuso smallint, crtp_tmultimaalteracao timestamp without time zone);


ALTER TABLE cadastro.vw_cliente_relacao_tipo OWNER TO gsan_admin;

--
-- TOC entry 322 (class 1259 OID 3673882)
-- Name: vw_cliente_tipo; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_cliente_tipo AS
    SELECT table1.cltp_id, table1.cltp_dsclientetipo, table1.cltp_icpessoafisicajuridica, table1.cltp_icuso, table1.cltp_tmultimaalteracao, table1.epod_id FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.cliente_tipo'::text) table1(cltp_id integer, cltp_dsclientetipo character varying(50), cltp_icpessoafisicajuridica smallint, cltp_icuso smallint, cltp_tmultimaalteracao timestamp without time zone, epod_id integer);


ALTER TABLE cadastro.vw_cliente_tipo OWNER TO gsan_admin;

--
-- TOC entry 323 (class 1259 OID 3673886)
-- Name: vw_empresa; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_empresa AS
    SELECT table1.empr_id, table1.empr_nmempresa, table1.empr_dsemail, table1.empr_icuso, table1.empr_tmultimaalteracao, table1.empr_icempresaprincipal, table1.empr_nmabreviadoempresa, table1.empr_iccobranca, table1.empr_icleitura FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.empresa'::text) table1(empr_id integer, empr_nmempresa character varying(50), empr_dsemail character varying(80), empr_icuso smallint, empr_tmultimaalteracao timestamp without time zone, empr_icempresaprincipal smallint, empr_nmabreviadoempresa character varying(10), empr_iccobranca smallint, empr_icleitura smallint);


ALTER TABLE cadastro.vw_empresa OWNER TO gsan_admin;

--
-- TOC entry 324 (class 1259 OID 3673894)
-- Name: vw_esfera_poder; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_esfera_poder AS
    SELECT table1.epod_id, table1.epod_dsesferapoder, table1.epod_icuso, table1.epod_tmultimaalteracao, table1.epod_icpermitecndparaimovel, table1.epod_icpermitecndparacliente FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.esfera_poder'::text) table1(epod_id integer, epod_dsesferapoder character varying(30), epod_icuso smallint, epod_tmultimaalteracao timestamp without time zone, epod_icpermitecndparaimovel smallint, epod_icpermitecndparacliente smallint);


ALTER TABLE cadastro.vw_esfera_poder OWNER TO gsan_admin;

--
-- TOC entry 325 (class 1259 OID 3673898)
-- Name: vw_g_setor_comercial; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_g_setor_comercial AS
    SELECT g_setor_comercial.stcm_id, g_setor_comercial.loca_id, g_setor_comercial.stcm_cdsetorcomercial, ((((g_setor_comercial.loca_id)::text || '-'::text) || (g_setor_comercial.stcm_cdsetorcomercial)::text) || (g_setor_comercial.stcm_nmsetorcomercial)::text) AS stcm_nmsetorcomercial, g_setor_comercial.stcm_icuso, g_setor_comercial.stcm_tmultimaalteracao FROM g_setor_comercial;


ALTER TABLE cadastro.vw_g_setor_comercial OWNER TO gsan_admin;

--
-- TOC entry 326 (class 1259 OID 3673902)
-- Name: vw_gerencia_regional; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_gerencia_regional AS
    SELECT table1.greg_id, table1.greg_nmregional, table1.greg_nmabreviado, table1.logr_id, table1.greg_nnimovel, table1.greg_dscomplementoendereco, table1.edrf_id, table1.bair_id, table1.cep_id, table1.greg_nnfone, table1.greg_nnfoneramal, table1.greg_nnfax, table1.greg_dsemail, table1.greg_icuso, table1.greg_tmultimaalteracao, table1.lgbr_id, table1.lgcp_id, table1.clie_id, table1.greg_nncnpj, table1.supt_id FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.gerencia_regional'::text) table1(greg_id integer, greg_nmregional character varying(25), greg_nmabreviado character(3), logr_id integer, greg_nnimovel character(5), greg_dscomplementoendereco character varying(25), edrf_id integer, bair_id integer, cep_id integer, greg_nnfone character varying(9), greg_nnfoneramal character varying(4), greg_nnfax character varying(9), greg_dsemail character varying(40), greg_icuso smallint, greg_tmultimaalteracao timestamp without time zone, lgbr_id integer, lgcp_id integer, clie_id integer, greg_nncnpj character varying(14), supt_id integer);


ALTER TABLE cadastro.vw_gerencia_regional OWNER TO gsan_admin;

--
-- TOC entry 327 (class 1259 OID 3673914)
-- Name: vw_imovel_perfil; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_imovel_perfil AS
    SELECT table1.iper_id, table1.iper_dsimovelperfil, table1.iper_icuso, table1.iper_tmultimaalteracao, table1.iper_icgeracaoautomatica, table1.iper_icinserirmanterperfil, table1.iper_icgerardadosleitura, table1.iper_icbloquearetificacao, table1.scat_id, table1.iper_icgrandeconsumidor, table1.iper_icbloqueadadossocial, table1.iper_icgerardebitossegundaviaconta FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.imovel_perfil'::text) table1(iper_id integer, iper_dsimovelperfil character varying(20), iper_icuso smallint, iper_tmultimaalteracao timestamp without time zone, iper_icgeracaoautomatica smallint, iper_icinserirmanterperfil smallint, iper_icgerardadosleitura smallint, iper_icbloquearetificacao smallint, scat_id integer, iper_icgrandeconsumidor smallint, iper_icbloqueadadossocial smallint, iper_icgerardebitossegundaviaconta integer);


ALTER TABLE cadastro.vw_imovel_perfil OWNER TO gsan_admin;

--
-- TOC entry 328 (class 1259 OID 3673918)
-- Name: vw_imovel_situacao; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_imovel_situacao AS
    SELECT table1.imst_id, table1.istp_id, table1.last_id, table1.lest_id, table1.imst_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 
user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.imovel_situacao '::text) table1(imst_id integer, istp_id integer, last_id integer, lest_id integer, imst_tmultimaalteracao timestamp without time zone);


ALTER TABLE cadastro.vw_imovel_situacao OWNER TO gsan_admin;

--
-- TOC entry 329 (class 1259 OID 3673922)
-- Name: vw_imovel_situacao_tipo; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_imovel_situacao_tipo AS
    SELECT table1.istp_id, table1.istp_dsimovelsituacaotipo, table1.istp_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 
user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.imovel_situacao_tipo '::text) table1(istp_id integer, istp_dsimovelsituacaotipo character varying(20), istp_tmultimaalteracao timestamp without time zone);


ALTER TABLE cadastro.vw_imovel_situacao_tipo OWNER TO gsan_admin;

--
-- TOC entry 330 (class 1259 OID 3673926)
-- Name: vw_localidade; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_localidade AS
    SELECT table1.loca_id, table1.loca_nmlocalidade, table1.logr_id, table1.loca_nnimovel, table1.loca_dscomplementoendereco, table1.edrf_id, table1.cep_id, table1.loca_nnfone, table1.loca_nnfoneramal, table1.loca_nnfax, table1.loca_dsemail, table1.loca_nnconsumograndeusuario, table1.loca_cdelo, table1.greg_id, table1.lcla_id, table1.lpor_id, table1.loca_icuso, table1.bair_id, table1.loca_tmultimaalteracao, table1.lgbr_id, table1.lgcp_id, table1.loca_cdicms, table1.loca_cdcentrocusto, table1.uneg_id, table1.loca_icinformatizada, table1.clie_id, table1.hila_id, table1.loca_icbloqueio, table1.loca_icsede, table1.loca_cdcentrocustoesgoto, table1.muni_idprincipal FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.localidade'::text) table1(loca_id integer, loca_nmlocalidade character varying(30), logr_id integer, loca_nnimovel character(5), loca_dscomplementoendereco character varying(25), edrf_id integer, cep_id integer, loca_nnfone character varying(9), loca_nnfoneramal character varying(4), loca_nnfax character varying(9), loca_dsemail character varying(40), loca_nnconsumograndeusuario integer, loca_cdelo integer, greg_id integer, lcla_id integer, lpor_id integer, loca_icuso smallint, bair_id integer, loca_tmultimaalteracao timestamp without time zone, lgbr_id integer, lgcp_id integer, loca_cdicms character(10), loca_cdcentrocusto character varying(10), uneg_id integer, loca_icinformatizada smallint, clie_id integer, hila_id integer, loca_icbloqueio smallint, loca_icsede smallint, loca_cdcentrocustoesgoto character varying(10), muni_idprincipal integer);


ALTER TABLE cadastro.vw_localidade OWNER TO gsan_admin;

--
-- TOC entry 331 (class 1259 OID 3673931)
-- Name: vw_localidade_elo; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_localidade_elo AS
    SELECT a.loca_id, (SELECT b.loca_nmlocalidade FROM g_localidade b WHERE (b.loca_id = a.loca_cdelo)) AS localidade FROM g_localidade a ORDER BY a.loca_id;


ALTER TABLE cadastro.vw_localidade_elo OWNER TO gsan_admin;

--
-- TOC entry 332 (class 1259 OID 3673939)
-- Name: vw_localidade_porte; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_localidade_porte AS
    SELECT table1.lpor_id, table1.lpor_dslocalidadeporte, table1.lpor_icuso, table1.lpor_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.localidade_porte'::text) table1(lpor_id integer, lpor_dslocalidadeporte character varying(20), lpor_icuso smallint, lpor_tmultimaalteracao timestamp without time zone);


ALTER TABLE cadastro.vw_localidade_porte OWNER TO gsan_admin;

--
-- TOC entry 333 (class 1259 OID 3673943)
-- Name: vw_microrregiao; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_microrregiao AS
    SELECT table1.mreg_id, table1.regi_id, table1.mreg_nmmicrorregiao, table1.mreg_icuso, table1.mreg_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.microrregiao'::text) table1(mreg_id integer, regi_id integer, mreg_nmmicrorregiao character varying(30), mreg_icuso smallint, mreg_tmultimaalteracao timestamp without time zone);


ALTER TABLE cadastro.vw_microrregiao OWNER TO gsan_admin;

--
-- TOC entry 334 (class 1259 OID 3673947)
-- Name: vw_municipio; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_municipio AS
    SELECT table1.muni_id, table1.muni_nmmunicipio, table1.muni_cdcepinicio, table1.muni_cdcepfim, table1.muni_cdddd, table1.rdes_id, table1.mreg_id, table1.unfe_id, table1.muni_icuso, table1.muni_tmultimaalteracao, table1.muni_dtconcessaoinicio, table1.muni_dtconcessaofim, table1.muni_cdibge, table1.muni_icrelacionquadrabairro FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.municipio'::text) table1(muni_id integer, muni_nmmunicipio character varying(30), muni_cdcepinicio integer, muni_cdcepfim integer, muni_cdddd smallint, rdes_id integer, mreg_id integer, unfe_id integer, muni_icuso smallint, muni_tmultimaalteracao timestamp without time zone, muni_dtconcessaoinicio date, muni_dtconcessaofim date, muni_cdibge character varying(10), muni_icrelacionquadrabairro integer);


ALTER TABLE cadastro.vw_municipio OWNER TO gsan_admin;

--
-- TOC entry 335 (class 1259 OID 3673955)
-- Name: vw_quadra; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_quadra AS
    SELECT table1.qdra_id, table1.stcm_id, table1.qdra_nnquadra, table1.qdpf_id, table1.qdra_icredeagua, table1.qdra_icredeesgoto, table1.bair_id, table1.baci_id, table1.diop_id, table1.istc_id, table1.zeis_id, table1.rota_id, table1.qdra_nnrotasequencia, table1.qdra_dtimplantacao, table1.qdra_icuso, table1.qdra_tmultimaalteracao, table1.arti_id, table1.roem_id, table1.qdra_icautoincrementolote, table1.qdra_icbloqueio FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.quadra'::text) table1(qdra_id integer, stcm_id integer, qdra_nnquadra integer, qdpf_id integer, qdra_icredeagua smallint, qdra_icredeesgoto smallint, bair_id integer, baci_id integer, diop_id integer, istc_id integer, zeis_id integer, rota_id integer, qdra_nnrotasequencia smallint, qdra_dtimplantacao date, qdra_icuso smallint, qdra_tmultimaalteracao timestamp without time zone, arti_id integer, roem_id integer, qdra_icautoincrementolote smallint, qdra_icbloqueio smallint);


ALTER TABLE cadastro.vw_quadra OWNER TO gsan_admin;

--
-- TOC entry 336 (class 1259 OID 3673959)
-- Name: vw_regiao; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_regiao AS
    SELECT table1.regi_id, table1.regi_nmregiao, table1.regi_icuso, table1.regi_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.regiao'::text) table1(regi_id integer, regi_nmregiao character varying(20), regi_icuso smallint, regi_tmultimaalteracao timestamp without time zone);


ALTER TABLE cadastro.vw_regiao OWNER TO gsan_admin;

--
-- TOC entry 337 (class 1259 OID 3673963)
-- Name: vw_setor_comercial; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_setor_comercial AS
    SELECT table1.stcm_id, table1.loca_id, table1.stcm_cdsetorcomercial, table1.stcm_nmsetorcomercial, table1.stcm_icuso, table1.muni_id, table1.stcm_tmultimaalteracao, table1.stcm_icalternativo, table1.stcm_icbloqueio FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.setor_comercial'::text) table1(stcm_id integer, loca_id integer, stcm_cdsetorcomercial integer, stcm_nmsetorcomercial character varying(30), stcm_icuso smallint, muni_id integer, stcm_tmultimaalteracao timestamp without time zone, stcm_icalternativo smallint, stcm_icbloqueio smallint);


ALTER TABLE cadastro.vw_setor_comercial OWNER TO gsan_admin;

--
-- TOC entry 338 (class 1259 OID 3673967)
-- Name: vw_subcategoria; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_subcategoria AS
    SELECT table1.scat_id, table1.catg_id, table1.scat_cdsubcategoria, table1.scat_dssubcategoria, table1.scat_icuso, table1.scat_tmultimaalteracao, table1.scat_cdtarifasocial, table1.scat_nnfatorfiscalizacao, table1.scat_ictarifaconsumo, table1.scat_dsabreviada, table1.scat_cdgruposubcategoria, table1.scat_icsazonalidade, table1.scat_icrural FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from  cadastro.subcategoria'::text) table1(scat_id integer, catg_id integer, scat_cdsubcategoria integer, scat_dssubcategoria character varying(50), scat_icuso smallint, scat_tmultimaalteracao timestamp without time zone, scat_cdtarifasocial character(1), scat_nnfatorfiscalizacao smallint, scat_ictarifaconsumo smallint, scat_dsabreviada character varying(20), scat_cdgruposubcategoria integer, scat_icsazonalidade smallint, scat_icrural smallint);


ALTER TABLE cadastro.vw_subcategoria OWNER TO gsan_admin;

--
-- TOC entry 339 (class 1259 OID 3673971)
-- Name: vw_superintendencia; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_superintendencia AS
    SELECT table1.supt_id, table1.supt_nmsuperint, table1.supt_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.superintendencia'::text) table1(supt_id integer, supt_nmsuperint character varying(25), supt_tmultimaalteracao timestamp without time zone);


ALTER TABLE cadastro.vw_superintendencia OWNER TO gsan_admin;

--
-- TOC entry 340 (class 1259 OID 3673979)
-- Name: vw_un_resumo_ligacao_economia_mes_anterior; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_ligacao_economia_mes_anterior AS
    SELECT un_resumo_ligacao_economia.rele_id, CASE substr((un_resumo_ligacao_economia.rele_amreferencia)::text, 5, 2) WHEN '12'::text THEN (un_resumo_ligacao_economia.rele_amreferencia + 89) ELSE (un_resumo_ligacao_economia.rele_amreferencia + 1) END AS rele_amreferencia, un_resumo_ligacao_economia.greg_id, un_resumo_ligacao_economia.uneg_id, un_resumo_ligacao_economia.loca_id, un_resumo_ligacao_economia.loca_cdelo, un_resumo_ligacao_economia.stcm_id, un_resumo_ligacao_economia.qdra_id, un_resumo_ligacao_economia.rota_id, un_resumo_ligacao_economia.rele_cdsetorcomercial, un_resumo_ligacao_economia.rele_nnquadra, un_resumo_ligacao_economia.iper_id, un_resumo_ligacao_economia.last_id, un_resumo_ligacao_economia.lest_id, un_resumo_ligacao_economia.catg_id, un_resumo_ligacao_economia.scat_id, un_resumo_ligacao_economia.epod_id, un_resumo_ligacao_economia.cltp_id, un_resumo_ligacao_economia.lapf_id, un_resumo_ligacao_economia.lepf_id, un_resumo_ligacao_economia.rele_ichidrometro, un_resumo_ligacao_economia.rele_icvolumefixadoagua, un_resumo_ligacao_economia.rele_icvolumefixadoesgoto, un_resumo_ligacao_economia.rele_icpoco, un_resumo_ligacao_economia.rele_qtligacoes, un_resumo_ligacao_economia.rele_qteconomias, un_resumo_ligacao_economia.rele_tmultimaalteracao, un_resumo_ligacao_economia.rele_ichidrometropoco, un_resumo_ligacao_economia.cstf_id FROM un_res_lig_econ un_resumo_ligacao_economia WHERE (un_resumo_ligacao_economia.rele_amreferencia < (SELECT max(un_resumo_ligacao_economia.rele_amreferencia) AS max FROM un_res_lig_econ un_resumo_ligacao_economia));


ALTER TABLE cadastro.vw_un_resumo_ligacao_economia_mes_anterior OWNER TO gsan_admin;

--
-- TOC entry 341 (class 1259 OID 3673984)
-- Name: vw_un_resi_lig_eco; Type: VIEW; Schema: cadastro; Owner: postgres
--

CREATE VIEW vw_un_resi_lig_eco AS
    SELECT CASE WHEN (a.rele_id IS NULL) THEN 0 ELSE a.rele_id END AS rele_id, CASE WHEN (b.rele_id IS NULL) THEN 0 ELSE b.rele_id END AS rele_idma, CASE WHEN (a.rele_amreferencia IS NULL) THEN b.rele_amreferencia ELSE a.rele_amreferencia END AS rele_amreferencia, to_number(CASE WHEN (a.rele_amreferencia IS NULL) THEN substr((b.rele_amreferencia)::text, 1, 4) ELSE substr((a.rele_amreferencia)::text, 1, 4) END, (9999)::text) AS rele_anoreferencia, CASE WHEN (a.rele_amreferencia IS NULL) THEN CASE WHEN (substr((b.rele_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((b.rele_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((b.rele_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((b.rele_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((b.rele_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((b.rele_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((b.rele_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((b.rele_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((b.rele_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((b.rele_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((b.rele_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((b.rele_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END ELSE CASE WHEN (substr((a.rele_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((a.rele_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((a.rele_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((a.rele_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((a.rele_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((a.rele_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((a.rele_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((a.rele_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((a.rele_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((a.rele_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((a.rele_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((a.rele_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END END AS rele_mesreferencia, CASE WHEN (a.greg_id IS NULL) THEN b.greg_id ELSE a.greg_id END AS greg_id, CASE WHEN (a.uneg_id IS NULL) THEN b.uneg_id ELSE a.uneg_id END AS uneg_id, CASE WHEN (a.loca_id IS NULL) THEN b.loca_id ELSE a.loca_id END AS loca_id, CASE WHEN (a.loca_cdelo IS NULL) THEN b.loca_cdelo ELSE a.loca_cdelo END AS loca_cdelo, CASE WHEN (a.stcm_id IS NULL) THEN b.stcm_id ELSE a.stcm_id END AS stcm_id, CASE WHEN (a.qdra_id IS NULL) THEN b.qdra_id ELSE a.qdra_id END AS qdra_id, CASE WHEN (a.rota_id IS NULL) THEN b.rota_id ELSE a.rota_id END AS rota_id, CASE WHEN (a.rele_cdsetorcomercial IS NULL) THEN b.rele_cdsetorcomercial ELSE a.rele_cdsetorcomercial END AS rele_cdsetorcomercial, CASE WHEN (a.rele_nnquadra IS NULL) THEN b.rele_nnquadra ELSE a.rele_nnquadra END AS rele_nnquadra, CASE WHEN (a.iper_id IS NULL) THEN b.iper_id ELSE a.iper_id END AS iper_id, CASE WHEN (a.last_id IS NULL) THEN b.last_id ELSE a.last_id END AS last_id, CASE WHEN (a.lest_id IS NULL) THEN b.lest_id ELSE a.lest_id END AS lest_id, CASE WHEN (a.catg_id IS NULL) THEN b.catg_id ELSE a.catg_id END AS catg_id, CASE WHEN (a.scat_id IS NULL) THEN b.scat_id ELSE a.scat_id END AS scat_id, CASE WHEN (a.epod_id IS NULL) THEN b.epod_id ELSE a.epod_id END AS epod_id, CASE WHEN (a.cltp_id IS NULL) THEN b.cltp_id ELSE a.cltp_id END AS cltp_id, CASE WHEN (a.lapf_id IS NULL) THEN b.lapf_id ELSE a.lapf_id END AS lapf_id, CASE WHEN (a.lepf_id IS NULL) THEN b.lepf_id ELSE a.lepf_id END AS lepf_id, CASE WHEN (a.cstf_id IS NULL) THEN b.cstf_id ELSE a.cstf_id END AS cstf_id, CASE WHEN (a.rele_ichidrometro IS NULL) THEN b.rele_ichidrometro ELSE a.rele_ichidrometro END AS rele_ichidrometro, CASE WHEN (a.rele_icvolumefixadoagua IS NULL) THEN b.rele_icvolumefixadoagua ELSE a.rele_icvolumefixadoagua END AS rele_icvolumefixadoagua, CASE WHEN (a.rele_icvolumefixadoesgoto IS NULL) THEN b.rele_icvolumefixadoesgoto ELSE a.rele_icvolumefixadoesgoto END AS rele_icvolumefixadoesgoto, CASE WHEN (a.rele_icpoco IS NULL) THEN b.rele_icpoco ELSE a.rele_icpoco END AS rele_icpoco, CASE WHEN (a.rele_ichidrometropoco IS NULL) THEN b.rele_ichidrometropoco ELSE a.rele_ichidrometropoco END AS rele_ichidrometropoco, CASE WHEN (a.last_id IN (SELECT DISTINCT c.last_id FROM g_imovel_situacao c WHERE (c.istp_id = 1) ORDER BY c.last_id)) THEN a.rele_qtligacoes ELSE 0 END AS rele_qtligacoesativasagua, CASE WHEN (b.last_id IN (SELECT DISTINCT d.last_id FROM g_imovel_situacao d WHERE (d.istp_id = 1) ORDER BY d.last_id)) THEN b.rele_qtligacoes ELSE 0 END AS rele_qtligacoesativasaguama, CASE WHEN (a.lest_id IN (SELECT DISTINCT e.lest_id FROM g_imovel_situacao e WHERE (e.istp_id = 3) ORDER BY e.lest_id)) THEN a.rele_qtligacoes ELSE 0 END AS rele_qtligacoesativasesgoto, CASE WHEN (b.lest_id IN (SELECT DISTINCT f.lest_id FROM g_imovel_situacao f WHERE (f.istp_id = 3) ORDER BY f.lest_id)) THEN b.rele_qtligacoes ELSE 0 END AS rele_qtligacoesativasesgotoma, CASE WHEN (a.last_id IN (SELECT DISTINCT g.last_id FROM g_imovel_situacao g WHERE (g.istp_id = 4) ORDER BY g.last_id)) THEN a.rele_qteconomias ELSE 0 END AS rele_qteconomiastotaisagua, CASE WHEN (b.last_id IN (SELECT DISTINCT h.last_id FROM g_imovel_situacao h WHERE (h.istp_id = 4) ORDER BY h.last_id)) THEN b.rele_qteconomias ELSE 0 END AS rele_qteconomiastotaisaguama, CASE WHEN (a.lest_id IN (SELECT DISTINCT i.lest_id FROM g_imovel_situacao i WHERE (i.istp_id = 4) ORDER BY i.lest_id)) THEN a.rele_qteconomias ELSE 0 END AS rele_qteconomiastotaisesgoto, CASE WHEN (b.lest_id IN (SELECT DISTINCT j.lest_id FROM g_imovel_situacao j WHERE (j.istp_id = 4) ORDER BY j.lest_id)) THEN b.rele_qteconomias ELSE 0 END AS rele_qteconomiastotaisesgotoma, CASE WHEN (a.last_id = ANY (ARRAY[1, 2])) THEN a.rele_qtligacoes ELSE 0 END AS rele_qtclientesaguapotenciaisfactiveis, CASE WHEN (a.last_id = ANY (ARRAY[3, 4, 5])) THEN a.rele_qtligacoes ELSE 0 END AS rele_qtclientesagualigadoscortados, CASE WHEN (a.last_id = ANY (ARRAY[6, 7, 8])) THEN a.rele_qtligacoes ELSE 0 END AS rele_qtclientesaguasuprimidos, CASE WHEN (a.last_id = ANY (ARRAY[1, 2])) THEN a.rele_qteconomias ELSE 0 END AS rele_qteconomiasaguapotenciaisfactiveis, CASE WHEN (a.last_id = ANY (ARRAY[3, 4, 5])) THEN a.rele_qteconomias ELSE 0 END AS rele_qteconomiasagualigadoscortados, CASE WHEN (a.last_id = ANY (ARRAY[6, 7, 8])) THEN a.rele_qteconomias ELSE 0 END AS rele_qteconomiasaguasuprimidos, CASE WHEN (a.lest_id = ANY (ARRAY[1, 2])) THEN a.rele_qtligacoes ELSE 0 END AS rele_qtclientesesgotopotenciaisfactiveis, CASE WHEN (a.lest_id = 3) THEN a.rele_qtligacoes ELSE 0 END AS rele_qtclientesesgotoligados, CASE WHEN (a.lest_id = ANY (ARRAY[5, 6])) THEN a.rele_qtligacoes ELSE 0 END AS rele_qtclientesesgotoligadosforauso, CASE WHEN (a.lest_id = ANY (ARRAY[1, 2])) THEN a.rele_qteconomias ELSE 0 END AS rele_qteconomiasesgotopotenciaisfactiveis, CASE WHEN (a.lest_id = 3) THEN a.rele_qteconomias ELSE 0 END AS rele_qteconomiasesgotoligados, CASE WHEN (a.lest_id = ANY (ARRAY[5, 6])) THEN a.rele_qteconomias ELSE 0 END AS rele_qteconomiasesgotoligadosforauso, CASE WHEN (a.rele_tmultimaalteracao IS NULL) THEN b.rele_tmultimaalteracao ELSE a.rele_tmultimaalteracao END AS rele_tmultimaalteracao FROM (un_res_lig_econ a FULL JOIN vw_un_resumo_ligacao_economia_mes_anterior b ON ((((((((((((((((((((((((((a.rele_amreferencia = b.rele_amreferencia) AND (a.greg_id = b.greg_id)) AND (a.uneg_id = b.uneg_id)) AND (a.loca_id = b.loca_id)) AND (a.loca_cdelo = b.loca_cdelo)) AND (a.stcm_id = b.stcm_id)) AND (a.qdra_id = b.qdra_id)) AND (a.rota_id = b.rota_id)) AND (a.rele_cdsetorcomercial = b.rele_cdsetorcomercial)) AND (a.rele_nnquadra = b.rele_nnquadra)) AND (a.iper_id = b.iper_id)) AND (a.last_id = b.last_id)) AND (a.lest_id = b.lest_id)) AND (a.catg_id = b.catg_id)) AND (a.scat_id = b.scat_id)) AND (a.epod_id = b.epod_id)) AND (a.cltp_id = b.cltp_id)) AND (a.lapf_id = b.lapf_id)) AND (a.lepf_id = b.lepf_id)) AND (a.rele_ichidrometro = b.rele_ichidrometro)) AND (a.rele_icvolumefixadoagua = b.rele_icvolumefixadoagua)) AND (a.rele_icvolumefixadoesgoto = b.rele_icvolumefixadoesgoto)) AND (a.rele_icpoco = b.rele_icpoco)) AND (a.rele_ichidrometropoco = b.rele_ichidrometropoco)) AND (a.cstf_id = b.cstf_id))));


ALTER TABLE cadastro.vw_un_resi_lig_eco OWNER TO postgres;

--
-- TOC entry 342 (class 1259 OID 3673989)
-- Name: vw_un_resumo_ligacao_economia; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_ligacao_economia AS
    SELECT un_resumo_ligacao_economia.rele_id, un_resumo_ligacao_economia.rele_amreferencia, un_resumo_ligacao_economia.greg_id, un_resumo_ligacao_economia.uneg_id, un_resumo_ligacao_economia.loca_id, un_resumo_ligacao_economia.loca_cdelo, un_resumo_ligacao_economia.stcm_id, un_resumo_ligacao_economia.qdra_id, un_resumo_ligacao_economia.rota_id, un_resumo_ligacao_economia.rele_cdsetorcomercial, un_resumo_ligacao_economia.rele_nnquadra, un_resumo_ligacao_economia.iper_id, un_resumo_ligacao_economia.last_id, un_resumo_ligacao_economia.lest_id, un_resumo_ligacao_economia.catg_id, un_resumo_ligacao_economia.scat_id, un_resumo_ligacao_economia.epod_id, un_resumo_ligacao_economia.cltp_id, un_resumo_ligacao_economia.lapf_id, un_resumo_ligacao_economia.lepf_id, CASE WHEN (un_resumo_ligacao_economia.rele_ichidrometro = 1) THEN 'C/HIDROMETRO'::text WHEN (un_resumo_ligacao_economia.rele_ichidrometro = 2) THEN 'S/HIDROMETRO'::text ELSE 'NAO INFORMADO'::text END AS ichidrometro, un_resumo_ligacao_economia.rele_icvolumefixadoagua, un_resumo_ligacao_economia.rele_icvolumefixadoesgoto, un_resumo_ligacao_economia.rele_icpoco, un_resumo_ligacao_economia.rele_qtligacoes, un_resumo_ligacao_economia.rele_qteconomias, un_resumo_ligacao_economia.rele_tmultimaalteracao, CASE WHEN (un_resumo_ligacao_economia.rele_ichidrometropoco = 1) THEN 'C/HIDROMETRO'::text WHEN (un_resumo_ligacao_economia.rele_ichidrometropoco = 2) THEN 'S/HIDROMETRO'::text ELSE 'NAO INFORMADO'::text END AS ichidrometropoco, un_resumo_ligacao_economia.cstf_id FROM un_res_lig_econ un_resumo_ligacao_economia;


ALTER TABLE cadastro.vw_un_resumo_ligacao_economia OWNER TO gsan_admin;

--
-- TOC entry 343 (class 1259 OID 3673994)
-- Name: vw_unidade_negocio; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_unidade_negocio AS
    SELECT table1.uneg_id, table1.greg_id, table1.uneg_nmunidadenegocio, table1.uneg_nmabreviado, table1.uneg_icuso, table1.uneg_tmultimaalteracao, table1.clie_id, table1.uneg_nncnpj FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.unidade_negocio'::text) table1(uneg_id integer, greg_id integer, uneg_nmunidadenegocio character varying(50), uneg_nmabreviado character(4), uneg_icuso smallint, uneg_tmultimaalteracao timestamp without time zone, clie_id integer, uneg_nncnpj character varying(14));


ALTER TABLE cadastro.vw_unidade_negocio OWNER TO gsan_admin;

--
-- TOC entry 344 (class 1259 OID 3673998)
-- Name: vw_unidade_organizacional; Type: VIEW; Schema: cadastro; Owner: gsan_admin
--

CREATE VIEW vw_unidade_organizacional AS
    SELECT table1.unid_id, table1.unid_idsuperior, table1.unid_idcentralizadora, table1.unid_icesgoto, table1.unid_ictramite, table1.unid_dsunidade, table1.unid_dssiglaunidade, table1.unid_tmultimaalteracao, table1.empr_id, table1.loca_id, table1.greg_id, table1.meso_id, table1.untp_id, table1.unid_icabertura, table1.unid_icuso, table1.unid_iccentralatendimento, table1.unid_ictarifasocial, table1.unid_idrepavimentadora, table1.uneg_id, table1.unid_nncodigoconstante FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from cadastro.unidade_organizacional'::text) table1(unid_id integer, unid_idsuperior integer, unid_idcentralizadora integer, unid_icesgoto smallint, unid_ictramite smallint, unid_dsunidade character varying(80), unid_dssiglaunidade character(5), unid_tmultimaalteracao timestamp without time zone, empr_id integer, loca_id integer, greg_id integer, meso_id integer, untp_id integer, unid_icabertura smallint, unid_icuso smallint, unid_iccentralatendimento smallint, unid_ictarifasocial smallint, unid_idrepavimentadora integer, uneg_id integer, unid_nncodigoconstante integer);


ALTER TABLE cadastro.vw_unidade_organizacional OWNER TO gsan_admin;

SET search_path = cobranca, pg_catalog;

--
-- TOC entry 345 (class 1259 OID 3674003)
-- Name: faixa_valor; Type: TABLE; Schema: cobranca; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE faixa_valor (
    fxvl_id integer NOT NULL,
    fxvl_dsfaixavalor character varying(30),
    fxvl_tmultimaalteracao timestamp without time zone
);


ALTER TABLE cobranca.faixa_valor OWNER TO gsan_admin;

--
-- TOC entry 346 (class 1259 OID 3674006)
-- Name: g_documento_tipo; Type: TABLE; Schema: cobranca; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_documento_tipo (
    dotp_id integer NOT NULL,
    dotp_dsdocumentotipo character varying(30),
    dotp_dsabreviado character varying(10),
    dotp_icpagavel smallint,
    dotp_icuso smallint,
    dotp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE cobranca.g_documento_tipo OWNER TO gsan_admin;

--
-- TOC entry 347 (class 1259 OID 3674010)
-- Name: g_documento_tipo_x; Type: TABLE; Schema: cobranca; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_documento_tipo_x (
    dotp_id integer NOT NULL,
    dotp_dsdocumentotipo character varying(30),
    dotp_dsabreviado character varying(10),
    dotp_icpagavel smallint,
    dotp_icuso smallint,
    dotp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    dotp_qtd integer
);


ALTER TABLE cobranca.g_documento_tipo_x OWNER TO gsan_admin;

--
-- TOC entry 348 (class 1259 OID 3674014)
-- Name: seq_un_resumo_parcelamento; Type: SEQUENCE; Schema: cobranca; Owner: gsan_admin
--

CREATE SEQUENCE seq_un_resumo_parcelamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cobranca.seq_un_resumo_parcelamento OWNER TO gsan_admin;

--
-- TOC entry 349 (class 1259 OID 3674016)
-- Name: sequence_faixa_valor; Type: SEQUENCE; Schema: cobranca; Owner: gsan_admin
--

CREATE SEQUENCE sequence_faixa_valor
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cobranca.sequence_faixa_valor OWNER TO gsan_admin;

--
-- TOC entry 350 (class 1259 OID 3674018)
-- Name: sequence_un_resumo_indicadores_cobranca; Type: SEQUENCE; Schema: cobranca; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_indicadores_cobranca
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cobranca.sequence_un_resumo_indicadores_cobranca OWNER TO gsan_admin;

--
-- TOC entry 351 (class 1259 OID 3674023)
-- Name: sequence_un_resumo_pendencia; Type: SEQUENCE; Schema: cobranca; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_pendencia
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cobranca.sequence_un_resumo_pendencia OWNER TO gsan_admin;

--
-- TOC entry 352 (class 1259 OID 3674025)
-- Name: sequence_un_resumo_pendencia_ref_2010; Type: SEQUENCE; Schema: cobranca; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_pendencia_ref_2010
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cobranca.sequence_un_resumo_pendencia_ref_2010 OWNER TO gsan_admin;

--
-- TOC entry 353 (class 1259 OID 3674027)
-- Name: sequence_un_resumo_pendencia_sem_quadra; Type: SEQUENCE; Schema: cobranca; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_pendencia_sem_quadra
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cobranca.sequence_un_resumo_pendencia_sem_quadra OWNER TO gsan_admin;

--
-- TOC entry 354 (class 1259 OID 3674029)
-- Name: un_resumo_indicadores_cobranca; Type: TABLE; Schema: cobranca; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_indicadores_cobranca (
    rpen_id integer DEFAULT nextval('sequence_un_resumo_indicadores_cobranca'::regclass) NOT NULL,
    rpen_amreferencia integer NOT NULL,
    rpen_anoreferencia integer NOT NULL,
    rpen_mesreferencia character(6) NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    qdra_id integer NOT NULL,
    rota_id integer NOT NULL,
    rpen_cdsetorcomercial integer NOT NULL,
    rpen_nnquadra integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    rpen_qtcontaspendentesma integer NOT NULL,
    rpen_vlpendente_contama numeric(13,2) NOT NULL,
    rpen_qtligacoes integer NOT NULL,
    rpen_qtligacoesativas integer NOT NULL,
    rpen_qtdocumentos integer NOT NULL,
    rpen_qtcontaspendentes integer NOT NULL,
    rpen_vlpendente_total numeric(13,2) NOT NULL,
    rpen_vlpendente_conta numeric(13,2) NOT NULL,
    rpen_vlpendente_servicos numeric(13,2) NOT NULL,
    rpen_vlpendente_parcelamento numeric(13,2) NOT NULL,
    rele_qtligacoesativasagua integer NOT NULL,
    rele_qtligacoesinativasagua integer NOT NULL,
    rele_qtligacoessuprimidas integer NOT NULL,
    rele_qtligacoestotaisagua integer NOT NULL,
    rear_qtcontasrecebidas integer NOT NULL,
    rear_vlarrecadado numeric(13,2) NOT NULL,
    rear_vlarrecacadomesatevencimento numeric(13,2) NOT NULL,
    rear_vlarrecacadomesaposvencimento numeric(13,2) NOT NULL,
    rear_vlarrecacado2mes numeric(13,2) NOT NULL,
    rear_vlarrecacado3mes numeric(13,2) NOT NULL,
    rear_vlarrecacadoacumuladoate3mes numeric(13,2) NOT NULL,
    rear_vlarrecadado_parcelamento numeric(13,2) NOT NULL,
    refa_qtcontasfaturamentoliquido integer NOT NULL,
    refa_qtcontasfaturamentoliquidoma integer NOT NULL,
    refa_vlfaturamentoliquido numeric(13,2) NOT NULL,
    refa_vlfaturamentoliquidoma numeric(13,2) NOT NULL,
    repa_qtparcelamentos integer NOT NULL,
    repa_qtcontaseguias integer NOT NULL,
    repa_vlnegociado numeric(13,2) NOT NULL,
    repa_vlfinanciado numeric(13,2) NOT NULL,
    repa_vlparcelado numeric(13,2) NOT NULL,
    rlig_qtcortes integer NOT NULL,
    rlig_qtsupressoes integer NOT NULL,
    rlig_qtreligacoes integer NOT NULL,
    rlig_qtreestabelecimentos integer NOT NULL,
    rpen_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE cobranca.un_resumo_indicadores_cobranca OWNER TO gsan_admin;

--
-- TOC entry 355 (class 1259 OID 3674034)
-- Name: un_resumo_parcelamento; Type: TABLE; Schema: cobranca; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_parcelamento (
    repa_id integer NOT NULL,
    uneg_id integer NOT NULL,
    repa_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    loca_id integer NOT NULL,
    stcm_id integer NOT NULL,
    qdra_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    repa_cdsetorcomercial integer NOT NULL,
    repa_nnquadra integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    repa_qtcontas integer NOT NULL,
    repa_vlcontas numeric(13,2) NOT NULL,
    repa_vlguias numeric(13,2) NOT NULL,
    rota_id integer NOT NULL,
    repa_vlcreditos numeric(13,2) NOT NULL,
    repa_vldescacrescimo numeric(13,2) DEFAULT 0 NOT NULL,
    repa_qtguias integer NOT NULL,
    repa_vldescinatividade numeric(13,2) NOT NULL,
    repa_vlacrescimoimpontualidade numeric(13,2) DEFAULT 0 NOT NULL,
    repa_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    repa_qtparcelamentos smallint NOT NULL,
    repa_vldescantiguidade numeric(13,2) DEFAULT 0 NOT NULL,
    repa_qttotalparcelas integer NOT NULL,
    repa_qtmediaparcelas integer NOT NULL,
    repa_vljurosparcelamento numeric(13,2) NOT NULL,
    repa_vlentrada numeric(13,2) NOT NULL,
    repa_vldebacobrarparcelamentos numeric(13,2) NOT NULL,
    repa_vldebitosacobrartotal numeric(13,2) NOT NULL,
    repa_vldebacobraracrescimos numeric(13,2) NOT NULL,
    repa_vldebacobrarreligsancoes numeric(13,2) NOT NULL,
    cstf_id integer NOT NULL,
    repa_ichidrometro smallint,
    repa_qtreparcelamentos integer,
    repa_vloutrosdescontos numeric(13,2),
    repa_cdrota integer
);


ALTER TABLE cobranca.un_resumo_parcelamento OWNER TO gsan_admin;

--
-- TOC entry 356 (class 1259 OID 3674041)
-- Name: un_resumo_pendencia; Type: TABLE; Schema: cobranca; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_pendencia (
    rpen_id integer NOT NULL,
    rpen_amreferencia integer,
    greg_id integer,
    iper_id integer,
    last_id integer,
    loca_cdelo integer,
    lest_id integer,
    catg_id integer,
    scat_id integer,
    epod_id integer,
    cltp_id integer,
    lapf_id integer,
    lepf_id integer,
    rpen_cdsetorcomercial integer,
    rpen_nnquadra integer,
    rpen_tmultimaalteracao timestamp without time zone DEFAULT now(),
    uneg_id integer,
    rota_id integer,
    fntp_id integer,
    loca_id integer,
    stcm_id integer,
    qdra_id integer,
    rpen_icvofixadoagua integer,
    rpen_icvofixadoesgoto integer,
    dotp_id integer,
    rpen_amreferenciadocumento integer,
    rpen_ichidrometro smallint,
    rpen_icvencido smallint DEFAULT 2 NOT NULL,
    rpen_qtligacoes integer,
    rpen_qtdocumentos integer,
    rpen_vlpendente_agua numeric(13,2),
    rpen_vlpendente_esgoto numeric(13,2),
    rpen_vlpendente_debitos numeric(13,2),
    rpen_vlpendente_creditos numeric(13,2),
    rpen_vlpendente_impostos numeric(13,2),
    cstf_id integer NOT NULL,
    fxvl_id integer NOT NULL,
    rpen_cdrota integer
);


ALTER TABLE cobranca.un_resumo_pendencia OWNER TO gsan_admin;

--
-- TOC entry 5344 (class 0 OID 0)
-- Dependencies: 356
-- Name: COLUMN un_resumo_pendencia.rpen_icvencido; Type: COMMENT; Schema: cobranca; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_pendencia.rpen_icvencido IS 'Indicador de documento vencido';


--
-- TOC entry 357 (class 1259 OID 3674050)
-- Name: un_resumo_pendencia_sem_quadra; Type: TABLE; Schema: cobranca; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_pendencia_sem_quadra (
    rpsq_amreferencia integer,
    greg_id integer,
    iper_id integer,
    last_id integer,
    loca_cdelo integer,
    lest_id integer,
    catg_id integer,
    scat_id integer,
    epod_id integer,
    cltp_id integer,
    lapf_id integer,
    lepf_id integer,
    rpsq_cdsetorcomercial integer,
    uneg_id integer,
    fntp_id integer,
    loca_id integer,
    stcm_id integer,
    rpsq_icvofixadoagua integer,
    rpsq_icvofixadoesgoto integer,
    dotp_id integer,
    rpsq_amreferenciadocumento integer,
    rpsq_ichidrometro smallint,
    rpsq_icvencido smallint,
    rpsq_qtligacoes integer,
    rpsq_qtdocumentos integer,
    rpsq_vlpendente_agua numeric(13,2),
    rpsq_vlpendente_esgoto numeric(13,2),
    rpsq_vlpendente_debitos numeric(13,2),
    rpsq_vlpendente_creditos numeric(13,2),
    rpsq_vlpendente_impostos numeric(13,2),
    cstf_id integer NOT NULL,
    fxvl_id integer NOT NULL,
    rpmr_id integer,
    rpsq_tmultimaalteracao timestamp without time zone,
    rpsq_id integer DEFAULT nextval(('cobranca.sequence_un_resumo_pendencia_sem_quadra'::text)::regclass) NOT NULL
);


ALTER TABLE cobranca.un_resumo_pendencia_sem_quadra OWNER TO gsan_admin;

--
-- TOC entry 358 (class 1259 OID 3674054)
-- Name: vw_documento_tipo; Type: VIEW; Schema: cobranca; Owner: gsan_admin
--

CREATE VIEW vw_documento_tipo AS
    SELECT table1.dotp_id, table1.dotp_dsdocumentotipo, table1.dotp_dsabreviado, table1.dotp_icpagavel, table1.dotp_icuso, table1.dotp_tmultimaalteracao, table1.dotp_icagregador FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select * from cobranca.documento_tipo'::text) table1(dotp_id integer, dotp_dsdocumentotipo character varying(30), dotp_dsabreviado character varying(10), dotp_icpagavel smallint, dotp_icuso smallint, dotp_tmultimaalteracao timestamp without time zone, dotp_icagregador integer);


ALTER TABLE cobranca.vw_documento_tipo OWNER TO gsan_admin;

SET search_path = faturamento, pg_catalog;

--
-- TOC entry 359 (class 1259 OID 3674062)
-- Name: sequence_un_resumo_indicadores_faturamento; Type: SEQUENCE; Schema: faturamento; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_indicadores_faturamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE faturamento.sequence_un_resumo_indicadores_faturamento OWNER TO gsan_admin;

--
-- TOC entry 360 (class 1259 OID 3674064)
-- Name: un_resumo_indicadores_faturamento; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_indicadores_faturamento (
    refa_amreferencia integer NOT NULL,
    refa_anoreferencia text NOT NULL,
    refa_mesreferencia text NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    qdra_id integer NOT NULL,
    rota_id integer NOT NULL,
    refa_cdsetorcomercial integer NOT NULL,
    refa_nnquadra integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    crog_id integer,
    lict_id integer,
    dotp_id integer,
    fntp_id integer,
    dbtp_id integer,
    crti_id integer,
    refa_qtcontasemitidas bigint NOT NULL,
    rerf_qtcontasretificadas bigint NOT NULL,
    rerf_qtcontascanceladas bigint NOT NULL,
    rerf_qtcontasincluidas bigint NOT NULL,
    refa_qteconomiasfaturadas bigint NOT NULL,
    refa_vofaturadoagua bigint NOT NULL,
    rerf_vocanceladoagua bigint NOT NULL,
    rerf_voincluidoagua bigint NOT NULL,
    refa_vofaturadoesgoto bigint NOT NULL,
    rerf_vocanceladoesgoto bigint NOT NULL,
    rerf_voincluidoesgoto bigint NOT NULL,
    refa_vlfaturadoagua numeric NOT NULL,
    rerf_vlcanceladoagua numeric NOT NULL,
    rerf_vlincluidoagua numeric NOT NULL,
    refa_vlfaturadoesgoto numeric NOT NULL,
    rerf_vlcanceladoesgoto numeric NOT NULL,
    rerf_vlincluidoesgoto numeric NOT NULL,
    refa_vldocumentosfaturadoscredito numeric NOT NULL,
    rerf_vlcanceladocredito numeric NOT NULL,
    rerf_vlincluidocredito numeric NOT NULL,
    refa_vldocumentosfaturadosoutros numeric NOT NULL,
    rerf_vlcanceladooutros numeric NOT NULL,
    rerf_vlincluidooutros numeric NOT NULL,
    refa_vlacrescimoimpontualidade numeric NOT NULL,
    refa_qtcontasemitidasma bigint NOT NULL,
    rerf_qtcontasretificadasma bigint NOT NULL,
    rerf_qtcontascanceladasma bigint NOT NULL,
    rerf_qtcontasincluidasma bigint NOT NULL,
    refa_qteconomiasfaturadasma bigint NOT NULL,
    refa_vofaturadoaguama bigint NOT NULL,
    rerf_vocanceladoaguama bigint NOT NULL,
    rerf_voincluidoaguama bigint NOT NULL,
    refa_vofaturadoesgotoma bigint NOT NULL,
    rerf_vocanceladoesgotoma bigint NOT NULL,
    rerf_voincluidoesgotoma bigint NOT NULL,
    refa_vlfaturadoaguama numeric NOT NULL,
    rerf_vlcanceladoaguama numeric NOT NULL,
    rerf_vlincluidoaguama numeric NOT NULL,
    refa_vlfaturadoesgotoma numeric NOT NULL,
    rerf_vlcanceladoesgotoma numeric NOT NULL,
    rerf_vlincluidoesgotoma numeric NOT NULL,
    refa_vldocumentosfaturadoscreditoma numeric NOT NULL,
    rerf_vlcanceladocreditoma numeric NOT NULL,
    rerf_vlincluidocreditoma numeric NOT NULL,
    refa_vldocumentosfaturadosoutrosma numeric NOT NULL,
    rerf_vlcanceladooutrosma numeric NOT NULL,
    rerf_vlincluidooutrosma numeric NOT NULL,
    refa_vlacrescimoimpontualidadema numeric NOT NULL,
    refa_vlarrastos numeric NOT NULL,
    refa_vlparcelamento numeric NOT NULL,
    rerf_qtguiascanceladas bigint NOT NULL,
    refa_id integer DEFAULT nextval('sequence_un_resumo_indicadores_faturamento'::regclass) NOT NULL,
    refa_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE faturamento.un_resumo_indicadores_faturamento OWNER TO gsan_admin;

SET search_path = cobranca, pg_catalog;

--
-- TOC entry 361 (class 1259 OID 3674072)
-- Name: vw_un_resumo_unificado_cobranca; Type: VIEW; Schema: cobranca; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_unificado_cobranca AS
    ((((SELECT CASE substr((un_resumo_pendencia.rpen_amreferencia)::text, 5, 2) WHEN '12'::text THEN (un_resumo_pendencia.rpen_amreferencia + 89) ELSE (un_resumo_pendencia.rpen_amreferencia + 1) END AS rpen_amreferencia, un_resumo_pendencia.greg_id, un_resumo_pendencia.uneg_id, un_resumo_pendencia.loca_id, un_resumo_pendencia.loca_cdelo, un_resumo_pendencia.stcm_id, un_resumo_pendencia.qdra_id, un_resumo_pendencia.rota_id, un_resumo_pendencia.rpen_cdsetorcomercial, un_resumo_pendencia.rpen_nnquadra, un_resumo_pendencia.iper_id, un_resumo_pendencia.last_id, un_resumo_pendencia.lest_id, un_resumo_pendencia.catg_id, un_resumo_pendencia.scat_id, un_resumo_pendencia.epod_id, un_resumo_pendencia.cltp_id, COALESCE(un_resumo_pendencia.lapf_id, 0) AS lapf_id, COALESCE(un_resumo_pendencia.lepf_id, 0) AS lepf_id, CASE WHEN (un_resumo_pendencia.dotp_id = 1) THEN un_resumo_pendencia.rpen_qtdocumentos ELSE 0 END AS rpen_qtcontaspendentesma, CASE WHEN (un_resumo_pendencia.dotp_id = 1) THEN ((((COALESCE(un_resumo_pendencia.rpen_vlpendente_agua, 0.0) + COALESCE(un_resumo_pendencia.rpen_vlpendente_esgoto, 0.0)) + COALESCE(un_resumo_pendencia.rpen_vlpendente_debitos, 0.0)) - COALESCE(un_resumo_pendencia.rpen_vlpendente_creditos, 0.0)) - COALESCE(un_resumo_pendencia.rpen_vlpendente_impostos, (0)::numeric)) ELSE (0)::numeric END AS rpen_vlpendente_contama, 0 AS rpen_qtligacoes, 0 AS rpen_qtligacoesativas, 0 AS rpen_qtdocumentos, 0 AS rpen_qtcontaspendentes, 0.0 AS rpen_vlpendente_total, 0.0 AS rpen_vlpendente_conta, 0.0 AS rpen_vlpendente_servicos, 0 AS rpen_vlpendente_parcelamento, 0 AS rele_qtligacoesativasagua, 0 AS rele_qtligacoesinativasagua, 0 AS rele_qtligacoessuprimidas, 0 AS rele_qtligacoestotaisagua, 0 AS rear_qtcontasrecebidas, 0.0 AS rear_vlarrecadado, 0.0 AS rear_vlarrecacadomesatevencimento, 0.0 AS rear_vlarrecacadomesaposvencimento, 0.0 AS rear_vlarrecacado2mes, 0.0 AS rear_vlarrecacado3mes, 0.0 AS rear_vlarrecacadoacumuladoate3mes, 0.0 AS rear_vlarrecadado_parcelamento, 0 AS refa_qtcontasfaturamentoliquido, 0 AS refa_qtcontasfaturamentoliquidoma, 0.0 AS refa_vlfaturamentoliquido, 0.0 AS refa_vlfaturamentoliquidoma, 0 AS repa_qtparcelamentos, 0 AS repa_qtcontaseguias, 0.0 AS repa_vlnegociado, 0.0 AS repa_vlfinanciado, 0.0 AS repa_vlparcelado, 0 AS rlig_qtcortes, 0 AS rlig_qtsupressoes, 0 AS rlig_qtreligacoes, 0 AS rlig_qtreestabelecimentos FROM un_resumo_pendencia WHERE (un_resumo_pendencia.rpen_amreferencia < (SELECT max(un_resumo_pendencia.rpen_amreferencia) AS max FROM un_resumo_pendencia)) UNION ALL SELECT a.rpen_amreferencia, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.rpen_cdsetorcomercial, a.rpen_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, COALESCE(a.lapf_id, 0) AS lapf_id, COALESCE(a.lepf_id, 0) AS lepf_id, 0 AS rpen_qtcontaspendentesma, 0.0 AS rpen_vlpendente_contama, a.rpen_qtligacoes, CASE WHEN (a.last_id IN (SELECT DISTINCT c.last_id FROM cadastro.g_imovel_situacao c WHERE (c.istp_id = 1) ORDER BY c.last_id)) THEN a.rpen_qtligacoes ELSE 0 END AS rpen_qtligacoesativas, a.rpen_qtdocumentos, CASE WHEN (a.dotp_id = 1) THEN a.rpen_qtdocumentos ELSE 0 END AS rpen_qtcontaspendentes, ((((COALESCE(a.rpen_vlpendente_agua, 0.0) + COALESCE(a.rpen_vlpendente_esgoto, 0.0)) + COALESCE(a.rpen_vlpendente_debitos, 0.0)) - COALESCE(a.rpen_vlpendente_creditos, 0.0)) - COALESCE(a.rpen_vlpendente_impostos, 0.0)) AS rpen_vlpendente_total, CASE WHEN (a.dotp_id = 1) THEN ((((COALESCE(a.rpen_vlpendente_agua, 0.0) + COALESCE(a.rpen_vlpendente_esgoto, 0.0)) + COALESCE(a.rpen_vlpendente_debitos, 0.0)) - COALESCE(a.rpen_vlpendente_creditos, 0.0)) - COALESCE(a.rpen_vlpendente_impostos, 0.0)) ELSE 0.0 END AS rpen_vlpendente_conta, CASE WHEN (a.fntp_id = 1) THEN ((((COALESCE(a.rpen_vlpendente_agua, 0.0) + COALESCE(a.rpen_vlpendente_esgoto, 0.0)) + COALESCE(a.rpen_vlpendente_debitos, 0.0)) - COALESCE(a.rpen_vlpendente_creditos, 0.0)) - COALESCE(a.rpen_vlpendente_impostos, 0.0)) ELSE 0.0 END AS rpen_vlpendente_servicos, CASE WHEN (a.fntp_id = ANY (ARRAY[2, 3, 4, 8])) THEN ((((COALESCE(a.rpen_vlpendente_agua, 0.0) + COALESCE(a.rpen_vlpendente_esgoto, 0.0)) + COALESCE(a.rpen_vlpendente_debitos, 0.0)) - COALESCE(a.rpen_vlpendente_creditos, 0.0)) - COALESCE(a.rpen_vlpendente_impostos, 0.0)) ELSE 0.0 END AS rpen_vlpendente_parcelamento, 0 AS rele_qtligacoesativasagua, 0 AS rele_qtligacoesinativasagua, 0 AS rele_qtligacoessuprimidas, 0 AS rele_qtligacoestotaisagua, 0 AS rear_qtcontasrecebidas, 0.0 AS rear_vlarrecadado, 0.0 AS rear_vlarrecacadomesatevencimento, 0.0 AS rear_vlarrecacadomesaposvencimento, 0.0 AS rear_vlarrecacado2mes, 0.0 AS rear_vlarrecacado3mes, 0.0 AS rear_vlarrecacadoacumuladoate3mes, 0.0 AS rear_vlarrecadado_parcelamento, 0 AS refa_qtcontasfaturamentoliquido, 0 AS refa_qtcontasfaturamentoliquidoma, 0.0 AS refa_vlfaturamentoliquido, 0.0 AS refa_vlfaturamentoliquidoma, 0 AS repa_qtparcelamentos, 0 AS repa_qtcontaseguias, 0.0 AS repa_vlnegociado, 0.0 AS repa_vlfinanciado, 0.0 AS repa_vlparcelado, 0 AS rlig_qtcortes, 0 AS rlig_qtsupressoes, 0 AS rlig_qtreligacoes, 0 AS rlig_qtreestabelecimentos FROM un_resumo_pendencia a) UNION ALL SELECT a.rele_amreferencia AS rpen_amreferencia, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.rele_cdsetorcomercial AS rpen_cdsetorcomercial, a.rele_nnquadra AS rpen_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id, 0 AS rpen_qtcontaspendentesma, 0.0 AS rpen_vlpendente_contama, 0 AS rpen_qtligacoes, 0 AS rpen_qtligacoesativas, 0 AS rpen_qtdocumentos, 0 AS rpen_qtcontaspendentes, 0.0 AS rpen_vlpendente_total, 0.0 AS rpen_vlpendente_conta, 0.0 AS rpen_vlpendente_servicos, 0.0 AS rpen_vlpendente_parcelamento, CASE WHEN (a.last_id IN (SELECT DISTINCT b.last_id FROM cadastro.g_imovel_situacao b WHERE (b.istp_id = 1) ORDER BY b.last_id)) THEN a.rele_qtligacoes ELSE 0 END AS rele_qtligacoesativasagua, CASE WHEN (a.last_id = 5) THEN a.rele_qtligacoes ELSE 0 END AS rele_qtligacoesinativasagua, CASE WHEN (a.last_id = 6) THEN a.rele_qtligacoes ELSE 0 END AS rele_qtligacoessuprimidas, CASE WHEN (a.last_id IN (SELECT DISTINCT g.last_id FROM cadastro.g_imovel_situacao g WHERE (g.istp_id = 4) ORDER BY g.last_id)) THEN a.rele_qtligacoes ELSE 0 END AS rele_qtligacoestotaisagua, 0 AS rear_qtcontasrecebidas, 0.0 AS rear_vlarrecadado, 0.0 AS rear_vlarrecacadomesatevencimento, 0.0 AS rear_vlarrecacadomesaposvencimento, 0.0 AS rear_vlarrecacado2mes, 0.0 AS rear_vlarrecacado3mes, 0.0 AS rear_vlarrecacadoacumuladoate3mes, 0.0 AS rear_vlarrecadado_parcelamento, 0 AS refa_qtcontasfaturamentoliquido, 0 AS refa_qtcontasfaturamentoliquidoma, 0.0 AS refa_vlfaturamentoliquido, 0 AS refa_vlfaturamentoliquidoma, 0 AS repa_qtparcelamentos, 0 AS repa_qtcontaseguias, 0.0 AS repa_vlnegociado, 0.0 AS repa_vlfinanciado, 0.0 AS repa_vlparcelado, 0 AS rlig_qtcortes, 0 AS rlig_qtsupressoes, 0 AS rlig_qtreligacoes, 0 AS rlig_qtreestabelecimentos FROM cadastro.un_res_lig_econ a) UNION ALL SELECT a.rear_amreferencia AS rpen_amreferencia, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.rear_cdsetorcomercial AS rpen_cdsetorcomercial, a.rear_nnquadra AS rpen_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id, 0 AS rpen_qtcontaspendentesma, 0.0 AS rpen_vlpendente_contama, 0 AS rpen_qtligacoes, 0 AS rpen_qtligacoesativas, 0 AS rpen_qtdocumentos, 0 AS rpen_qtcontaspendentes, 0.0 AS rpen_vlpendente_total, 0.0 AS rpen_vlpendente_conta, 0.0 AS rpen_vlpendente_servicos, 0.0 AS rpen_vlpendente_parcelamento, 0 AS rele_qtligacoesativasagua, 0 AS rele_qtligacoesinativasagua, 0 AS rele_qtligacoessuprimidas, 0 AS rele_qtligacoestotaisagua, a.rear_qtcontas AS rear_qtcontasrecebidas, (((((COALESCE(a.rear_vlagua, 0.0) + COALESCE(a.rear_vlesgoto, 0.0)) + COALESCE(a.rear_vlnaoidentificado, 0.0)) + COALESCE(a.rear_vldocsrecebidosoutros, 0.0)) - COALESCE(a.rear_vldocsrecebidoscredito, 0.0)) - COALESCE(a.rear_vlimpostos, 0.0)) AS rear_vlarrecadado, CASE WHEN (a.eppa_id = 0) THEN (((((COALESCE(a.rear_vlagua, 0.0) + COALESCE(a.rear_vlesgoto, 0.0)) + COALESCE(a.rear_vlnaoidentificado, 0.0)) + COALESCE(a.rear_vldocsrecebidosoutros, 0.0)) - COALESCE(a.rear_vldocsrecebidoscredito, 0.0)) - COALESCE(a.rear_vlimpostos, 0.0)) ELSE (0)::numeric END AS rear_vlarrecacadomesatevencimento, CASE WHEN (a.eppa_id = 1) THEN (((((COALESCE(a.rear_vlagua, 0.0) + COALESCE(a.rear_vlesgoto, 0.0)) + COALESCE(a.rear_vlnaoidentificado, 0.0)) + COALESCE(a.rear_vldocsrecebidosoutros, 0.0)) - COALESCE(a.rear_vldocsrecebidoscredito, 0.0)) - COALESCE(a.rear_vlimpostos, 0.0)) ELSE 0.0 END AS rear_vlarrecacadomesaposvencimento, CASE WHEN (a.eppa_id = 2) THEN (((((COALESCE(a.rear_vlagua, 0.0) + COALESCE(a.rear_vlesgoto, 0.0)) + COALESCE(a.rear_vlnaoidentificado, 0.0)) + COALESCE(a.rear_vldocsrecebidosoutros, 0.0)) - COALESCE(a.rear_vldocsrecebidoscredito, 0.0)) - COALESCE(a.rear_vlimpostos, 0.0)) ELSE 0.0 END AS rear_vlarrecacado2mes, CASE WHEN (a.eppa_id = 3) THEN (((((COALESCE(a.rear_vlagua, 0.0) + COALESCE(a.rear_vlesgoto, 0.0)) + COALESCE(a.rear_vlnaoidentificado, 0.0)) + COALESCE(a.rear_vldocsrecebidosoutros, 0.0)) - COALESCE(a.rear_vldocsrecebidoscredito, 0.0)) - COALESCE(a.rear_vlimpostos, 0.0)) ELSE 0.0 END AS rear_vlarrecacado3mes, CASE WHEN (a.eppa_id <= 3) THEN (((((COALESCE(a.rear_vlagua, 0.0) + COALESCE(a.rear_vlesgoto, 0.0)) + COALESCE(a.rear_vlnaoidentificado, 0.0)) + COALESCE(a.rear_vldocsrecebidosoutros, 0.0)) - COALESCE(a.rear_vldocsrecebidoscredito, 0.0)) - COALESCE(a.rear_vlimpostos, 0.0)) ELSE 0.0 END AS rear_vlarrecacadoacumuladoate3mes, CASE WHEN (a.fntp_idoutros = ANY (ARRAY[2, 3, 4, 8])) THEN (((((COALESCE(a.rear_vlagua, 0.0) + COALESCE(a.rear_vlesgoto, 0.0)) + COALESCE(a.rear_vlnaoidentificado, 0.0)) + COALESCE(a.rear_vldocsrecebidosoutros, 0.0)) - COALESCE(a.rear_vldocsrecebidoscredito, 0.0)) - COALESCE(a.rear_vlimpostos, 0.0)) ELSE 0.0 END AS rear_vlarrecadado_parcelamento, 0 AS refa_qtcontasfaturamentoliquido, 0 AS refa_qtcontasfaturamentoliquidoma, 0.0 AS refa_vlfaturamentoliquido, 0.0 AS refa_vlfaturamentoliquidoma, 0 AS repa_qtparcelamentos, 0 AS repa_qtcontaseguias, 0.0 AS repa_vlnegociado, 0.0 AS repa_vlfinanciado, 0.0 AS repa_vlparcelado, 0 AS rlig_qtcortes, 0 AS rlig_qtsupressoes, 0 AS rlig_qtreligacoes, 0 AS rlig_qtreestabelecimentos FROM arrecadacao.un_resumo_arrecadacao a) UNION ALL SELECT a.refa_amreferencia AS rpen_amreferencia, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.refa_cdsetorcomercial AS rpen_cdsetorcomercial, a.refa_nnquadra AS rpen_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id, 0 AS rpen_qtcontaspendentesma, 0.0 AS rpen_vlpendente_contama, 0 AS rpen_qtligacoes, 0 AS rpen_qtligacoesativas, 0 AS rpen_qtdocumentos, 0 AS rpen_qtcontaspendentes, 0.0 AS rpen_vlpendente_total, 0.0 AS rpen_vlpendente_conta, 0.0 AS rpen_vlpendente_servicos, 0.0 AS rpen_vlpendente_parcelamento, 0.0 AS rele_qtligacoesativasagua, 0.0 AS rele_qtligacoesinativasagua, 0.0 AS rele_qtligacoessuprimidas, 0.0 AS rele_qtligacoestotaisagua, 0.0 AS rear_qtcontasrecebidas, 0.0 AS rear_vlarrecadado, 0.0 AS rear_vlarrecacadomesatevencimento, 0.0 AS rear_vlarrecacadomesaposvencimento, 0.0 AS rear_vlarrecacado2mes, 0.0 AS rear_vlarrecacado3mes, 0.0 AS rear_vlarrecacadoacumuladoate3mes, 0.0 AS rear_vlarrecadado_parcelamento, ((COALESCE(a.refa_qtcontasemitidas, (0)::bigint) - COALESCE(a.rerf_qtcontascanceladas, (0)::bigint)) + COALESCE(a.rerf_qtcontasincluidas, (0)::bigint)) AS refa_qtcontasfaturamentoliquido, ((COALESCE(a.refa_qtcontasemitidasma, (0)::bigint) - COALESCE(a.rerf_qtcontascanceladasma, (0)::bigint)) + COALESCE(a.rerf_qtcontasincluidasma, (0)::bigint)) AS refa_qtcontasfaturamentoliquidoma, (((((((((COALESCE(a.refa_vlfaturadoagua, 0.0) - COALESCE(a.rerf_vlcanceladoagua, 0.0)) + COALESCE(a.rerf_vlincluidoagua, 0.0)) + COALESCE(a.refa_vlfaturadoesgoto, 0.0)) - COALESCE(a.rerf_vlcanceladoesgoto, 0.0)) + COALESCE(a.rerf_vlincluidoesgoto, 0.0)) - ((COALESCE(a.refa_vldocumentosfaturadoscredito, 0.0) - COALESCE(a.rerf_vlcanceladocredito, 0.0)) + COALESCE(a.rerf_vlincluidocredito, (0)::numeric))) + COALESCE(a.refa_vldocumentosfaturadosoutros, 0.0)) - COALESCE(a.rerf_vlcanceladooutros, 0.0)) + COALESCE(a.rerf_vlincluidooutros, 0.0)) AS refa_vlfaturamentoliquido, (((((((((COALESCE(a.refa_vlfaturadoaguama, 0.0) - COALESCE(a.rerf_vlcanceladoaguama, 0.0)) + COALESCE(a.rerf_vlincluidoaguama, 0.0)) + COALESCE(a.refa_vlfaturadoesgotoma, 0.0)) - COALESCE(a.rerf_vlcanceladoesgotoma, 0.0)) + COALESCE(a.rerf_vlincluidoesgotoma, 0.0)) - ((COALESCE(a.refa_vldocumentosfaturadoscreditoma, 0.0) - COALESCE(a.rerf_vlcanceladocreditoma, 0.0)) + COALESCE(a.rerf_vlincluidocreditoma, 0.0))) + COALESCE(a.refa_vldocumentosfaturadosoutrosma, 0.0)) - COALESCE(a.rerf_vlcanceladooutrosma, 0.0)) + COALESCE(a.rerf_vlincluidooutrosma, 0.0)) AS refa_vlfaturamentoliquidoma, 0 AS repa_qtparcelamentos, 0 AS repa_qtcontaseguias, 0.0 AS repa_vlnegociado, 0.0 AS repa_vlfinanciado, 0.0 AS repa_vlparcelado, 0 AS rlig_qtcortes, 0 AS rlig_qtsupressoes, 0 AS rlig_qtreligacoes, 0 AS rlig_qtreestabelecimentos FROM faturamento.un_resumo_indicadores_faturamento a) UNION ALL SELECT a.repa_amreferencia AS rpen_amreferencia, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.repa_cdsetorcomercial AS rpen_cdsetorcomercial, a.repa_nnquadra AS rpen_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id, 0 AS rpen_qtcontaspendentesma, 0.0 AS rpen_vlpendente_contama, 0 AS rpen_qtligacoes, 0 AS rpen_qtligacoesativas, 0 AS rpen_qtdocumentos, 0 AS rpen_qtcontaspendentes, 0.0 AS rpen_vlpendente_total, 0.0 AS rpen_vlpendente_conta, 0.0 AS rpen_vlpendente_servicos, 0.0 AS rpen_vlpendente_parcelamento, 0 AS rele_qtligacoesativasagua, 0 AS rele_qtligacoesinativasagua, 0 AS rele_qtligacoessuprimidas, 0 AS rele_qtligacoestotaisagua, 0 AS rear_qtcontasrecebidas, 0.0 AS rear_vlarrecadado, 0.0 AS rear_vlarrecacadomesatevencimento, 0.0 AS rear_vlarrecacadomesaposvencimento, 0.0 AS rear_vlarrecacado2mes, 0.0 AS rear_vlarrecacado3mes, 0.0 AS rear_vlarrecacadoacumuladoate3mes, 0.0 AS rear_vlarrecadado_parcelamento, 0 AS refa_qtcontasfaturamentoliquido, 0 AS refa_qtcontasfaturamentoliquidoma, 0.0 AS refa_vlfaturamentoliquido, 0.0 AS refa_vlfaturamentoliquidoma, a.repa_qtparcelamentos, (a.repa_qtcontas + a.repa_qtguias) AS repa_qtcontaseguias, (((((((a.repa_vlcontas + a.repa_vlguias) + a.repa_vldebacobrarparcelamentos) - a.repa_vlcreditos) + a.repa_vlacrescimoimpontualidade) - a.repa_vldescacrescimo) - a.repa_vldescinatividade) - a.repa_vldescantiguidade) AS repa_vlnegociado, ((((((((a.repa_vlcontas + a.repa_vlguias) + a.repa_vldebacobrarparcelamentos) - a.repa_vlcreditos) + a.repa_vlacrescimoimpontualidade) - a.repa_vldescacrescimo) - a.repa_vldescinatividade) - a.repa_vldescantiguidade) - a.repa_vlentrada) AS repa_vlfinanciado, ((((((((a.repa_vlcontas + a.repa_vlguias) + a.repa_vldebacobrarparcelamentos) - a.repa_vlcreditos) + a.repa_vlacrescimoimpontualidade) - a.repa_vldescacrescimo) - a.repa_vldescinatividade) - a.repa_vldescantiguidade) + a.repa_vljurosparcelamento) AS repa_vlparcelado, 0 AS rlig_qtcortes, 0 AS rlig_qtsupressoes, 0 AS rlig_qtreligacoes, 0 AS rlig_qtreestabelecimentos FROM un_resumo_parcelamento a;


ALTER TABLE cobranca.vw_un_resumo_unificado_cobranca OWNER TO gsan_admin;

SET search_path = corporativo, pg_catalog;

--
-- TOC entry 362 (class 1259 OID 3674081)
-- Name: sequence_un_resumo_corporativo; Type: SEQUENCE; Schema: corporativo; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_corporativo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE corporativo.sequence_un_resumo_corporativo OWNER TO gsan_admin;

--
-- TOC entry 363 (class 1259 OID 3674083)
-- Name: un_resumo_corporativo; Type: TABLE; Schema: corporativo; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_corporativo (
    corr_id integer NOT NULL,
    coor_vlfaturamentoliquido numeric(13,2),
    coor_vlfaturamentoagua numeric(13,2),
    coor_vlfaturamentoesgoto numeric(13,2),
    coor_vlarrecadacao numeric(13,2),
    coor_indiceadimplencia numeric(13,2),
    coor_vofaturado integer,
    coor_voconsumido integer,
    coor_qtramaisligramaisliganalise integer,
    coor_qtramaiscortados integer,
    coor_qtligacoesativas integer,
    coor_qtligacoesinativas integer,
    coor_qtligacoesmedidas integer,
    coor_qtligacoesnaomedidas integer,
    coor_qthidrometroinstalados integer,
    coor_amreferencia integer NOT NULL,
    coor_anoreferencia integer NOT NULL,
    coor_tmultimaalteracao timestamp without time zone,
    coor_mesreferencia character(6) NOT NULL,
    coor_vlmetaarrecadacao numeric(13,2),
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    catg_id integer NOT NULL
);


ALTER TABLE corporativo.un_resumo_corporativo OWNER TO gsan_admin;

--
-- TOC entry 5352 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.corr_id; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.corr_id IS 'Identificador da tabela';


--
-- TOC entry 5353 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_vlfaturamentoliquido; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_vlfaturamentoliquido IS 'Valor do faturamento liquido';


--
-- TOC entry 5354 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_vlfaturamentoagua; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_vlfaturamentoagua IS 'Valor do faturamento agua';


--
-- TOC entry 5355 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_vlfaturamentoesgoto; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_vlfaturamentoesgoto IS 'Valor do faturamento esgoto';


--
-- TOC entry 5356 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_vlarrecadacao; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_vlarrecadacao IS 'Valor da arrecadacao';


--
-- TOC entry 5357 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_indiceadimplencia; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_indiceadimplencia IS 'Indice de adimplencia';


--
-- TOC entry 5358 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_vofaturado; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_vofaturado IS 'Volume faturado m3';


--
-- TOC entry 5359 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_voconsumido; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_voconsumido IS 'Volume consumido m3';


--
-- TOC entry 5360 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_qtramaisligramaisliganalise; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_qtramaisligramaisliganalise IS 'Quantidade de ramais ligados e ramais ligados em analise';


--
-- TOC entry 5361 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_qtramaiscortados; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_qtramaiscortados IS 'Quantidade de ramais cortados';


--
-- TOC entry 5362 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_qtligacoesativas; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_qtligacoesativas IS 'Quantidade de ligacoes ativas';


--
-- TOC entry 5363 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_qtligacoesinativas; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_qtligacoesinativas IS 'Quantidade de ligacoes inativas';


--
-- TOC entry 5364 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_qtligacoesmedidas; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_qtligacoesmedidas IS 'Quantidade de ligacoes medidas';


--
-- TOC entry 5365 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_qtligacoesnaomedidas; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_qtligacoesnaomedidas IS 'Quantidade de ligacoes nao medidas';


--
-- TOC entry 5366 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_qthidrometroinstalados; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_qthidrometroinstalados IS 'Quantidade de hidrometros instalados';


--
-- TOC entry 5367 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_amreferencia; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_amreferencia IS 'Ano mes de referencia';


--
-- TOC entry 5368 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_anoreferencia; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_anoreferencia IS 'Ano de referencia';


--
-- TOC entry 5369 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_tmultimaalteracao; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_tmultimaalteracao IS 'Ultima alteracao na tabela';


--
-- TOC entry 5370 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_mesreferencia; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_mesreferencia IS 'Mes de referencia';


--
-- TOC entry 5371 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.coor_vlmetaarrecadacao; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.coor_vlmetaarrecadacao IS 'Valor da meta de arrecadacao';


--
-- TOC entry 5372 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.greg_id; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.greg_id IS 'Id da gerencia regional';


--
-- TOC entry 5373 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.uneg_id; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.uneg_id IS 'Id da unidade de negocio';


--
-- TOC entry 5374 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.loca_id; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.loca_id IS 'Id da localidade';


--
-- TOC entry 5375 (class 0 OID 0)
-- Dependencies: 363
-- Name: COLUMN un_resumo_corporativo.catg_id; Type: COMMENT; Schema: corporativo; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_corporativo.catg_id IS 'Id da categoria';


SET search_path = faturamento, pg_catalog;

--
-- TOC entry 364 (class 1259 OID 3674099)
-- Name: g_consumo_tarifa; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_consumo_tarifa (
    cstf_id integer NOT NULL,
    cstf_dsconsumotarifa character varying(30),
    cstf_icuso smallint,
    cstf_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE faturamento.g_consumo_tarifa OWNER TO gsan_admin;

--
-- TOC entry 365 (class 1259 OID 3674103)
-- Name: g_consumo_tarifa_x; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_consumo_tarifa_x (
    cstf_id integer NOT NULL,
    cstf_dsconsumotarifa character varying(30),
    cstf_icuso smallint,
    cstf_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    csft_qtd integer
);


ALTER TABLE faturamento.g_consumo_tarifa_x OWNER TO gsan_admin;

--
-- TOC entry 366 (class 1259 OID 3674107)
-- Name: g_credito_origem; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_credito_origem (
    crog_id integer NOT NULL,
    crog_dscreditoorigem character varying(40),
    crog_dsabreviado character varying(10),
    crog_icuso smallint,
    crog_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE faturamento.g_credito_origem OWNER TO gsan_admin;

--
-- TOC entry 367 (class 1259 OID 3674111)
-- Name: g_credito_origem_x; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_credito_origem_x (
    crog_id integer NOT NULL,
    crog_dscreditoorigem character varying(40),
    crog_dsabreviado character varying(10),
    crog_icuso smallint,
    crog_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    crog_qtd integer
);


ALTER TABLE faturamento.g_credito_origem_x OWNER TO gsan_admin;

--
-- TOC entry 368 (class 1259 OID 3674123)
-- Name: g_credito_tipo; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_credito_tipo (
    crti_id integer NOT NULL,
    lict_id integer,
    crti_dscreditotipo character varying(30),
    crti_dsabreviado character varying(18),
    crti_icuso smallint,
    crti_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    crti_vllimite numeric(13,2),
    crti_icgeracaoautomatica smallint
);


ALTER TABLE faturamento.g_credito_tipo OWNER TO gsan_admin;

--
-- TOC entry 369 (class 1259 OID 3674127)
-- Name: g_credito_tipo_x; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_credito_tipo_x (
    crti_id integer NOT NULL,
    lict_id integer,
    crti_dscreditotipo character varying(30),
    crti_dsabreviado character varying(18),
    crti_icuso smallint,
    crti_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    crti_vllimite numeric(13,2),
    crti_icgeracaoautomatica smallint,
    crti_qtd integer
);


ALTER TABLE faturamento.g_credito_tipo_x OWNER TO gsan_admin;

--
-- TOC entry 370 (class 1259 OID 3674131)
-- Name: g_debito_tipo; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_debito_tipo (
    dbtp_id integer NOT NULL,
    lict_id integer NOT NULL,
    dbtp_dsdebitotipo character varying(30),
    dbtp_dsabreviado character varying(18),
    dbtp_icuso smallint,
    dbtp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    fntp_id integer NOT NULL,
    dbtp_vllimite numeric(13,2),
    dbtp_icgeracaoautomatica smallint NOT NULL,
    dbtp_icgeracaoconta smallint NOT NULL,
    dbtp_vlsugerido numeric(13,2)
);


ALTER TABLE faturamento.g_debito_tipo OWNER TO gsan_admin;

--
-- TOC entry 5383 (class 0 OID 0)
-- Dependencies: 370
-- Name: COLUMN g_debito_tipo.dbtp_vlsugerido; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN g_debito_tipo.dbtp_vlsugerido IS 'Valor sugerido';


--
-- TOC entry 371 (class 1259 OID 3674135)
-- Name: g_debito_tipo_x; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_debito_tipo_x (
    dbtp_id integer NOT NULL,
    lict_id integer NOT NULL,
    dbtp_dsdebitotipo character varying(30),
    dbtp_dsabreviado character varying(18),
    dbtp_icuso smallint,
    dbtp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    fntp_id integer NOT NULL,
    dbtp_vllimite numeric(13,2),
    dbtp_icgeracaoautomatica smallint NOT NULL,
    dbtp_icgeracaoconta smallint NOT NULL,
    dbtp_vlsugerido numeric(13,2),
    dbtp_qtd integer
);


ALTER TABLE faturamento.g_debito_tipo_x OWNER TO gsan_admin;

--
-- TOC entry 372 (class 1259 OID 3674139)
-- Name: g_fat_sit_motivo; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_fat_sit_motivo (
    ftsm_id integer NOT NULL,
    ftsm_dsfatsitmotivo character(50) NOT NULL,
    ftsm_icuso smallint NOT NULL,
    ftsm_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE faturamento.g_fat_sit_motivo OWNER TO gsan_admin;

--
-- TOC entry 5386 (class 0 OID 0)
-- Dependencies: 372
-- Name: COLUMN g_fat_sit_motivo.ftsm_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN g_fat_sit_motivo.ftsm_id IS 'Id do Tipo de situacao de faturamento';


--
-- TOC entry 5387 (class 0 OID 0)
-- Dependencies: 372
-- Name: COLUMN g_fat_sit_motivo.ftsm_dsfatsitmotivo; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN g_fat_sit_motivo.ftsm_dsfatsitmotivo IS 'Descricao do Tipo de Situacao de Faturamento';


--
-- TOC entry 5388 (class 0 OID 0)
-- Dependencies: 372
-- Name: COLUMN g_fat_sit_motivo.ftsm_icuso; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN g_fat_sit_motivo.ftsm_icuso IS 'Indicador de uso';


--
-- TOC entry 5389 (class 0 OID 0)
-- Dependencies: 372
-- Name: COLUMN g_fat_sit_motivo.ftsm_tmultimaalteracao; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN g_fat_sit_motivo.ftsm_tmultimaalteracao IS 'Dia e Hora da Ultima Ataualizacao';


--
-- TOC entry 373 (class 1259 OID 3674148)
-- Name: g_faturamento_grupo; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_faturamento_grupo (
    ftgr_id integer NOT NULL,
    ftgr_dsfaturamentogrupo character varying(25) NOT NULL,
    ftgr_dsabreviado character(3) NOT NULL,
    ftgr_icuso smallint,
    ftgr_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    ftgr_amreferencia integer,
    ftgr_nndiavencimento smallint,
    ftgr_icvencimentomesfatura smallint NOT NULL
);


ALTER TABLE faturamento.g_faturamento_grupo OWNER TO gsan_admin;

--
-- TOC entry 374 (class 1259 OID 3674152)
-- Name: g_faturamento_grupo_x; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_faturamento_grupo_x (
    ftgr_id integer NOT NULL,
    ftgr_dsfaturamentogrupo character varying(25) NOT NULL,
    ftgr_dsabreviado character(3) NOT NULL,
    ftgr_icuso smallint,
    ftgr_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    ftgr_amreferencia integer,
    ftgr_nndiavencimento smallint,
    ftgr_icvencimentomesfatura smallint NOT NULL,
    ftgr_qtd integer
);


ALTER TABLE faturamento.g_faturamento_grupo_x OWNER TO gsan_admin;

--
-- TOC entry 375 (class 1259 OID 3674156)
-- Name: g_faturamento_situacao_tipo; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_faturamento_situacao_tipo (
    ftst_id integer NOT NULL,
    ftst_dsfaturamentosituacaotipo character varying(50),
    ftst_icfaturamentoparalisacao smallint,
    ftst_icleituraparalisacao smallint,
    ftst_icuso smallint,
    ftst_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    lacs_idconsumoacobrarsemleitura integer NOT NULL,
    lacs_idconsumoacobrarcomleitura integer NOT NULL,
    lalt_idleituraafaturarsemleitura integer NOT NULL,
    lalt_idleituraafaturarcomleitura integer NOT NULL,
    ftst_icfaturamentoparalisacaoesgoto smallint DEFAULT 2 NOT NULL,
    ftst_icvalidoagua smallint DEFAULT 2 NOT NULL,
    ftst_icvalidoesgoto smallint DEFAULT 2 NOT NULL,
    ftst_icinformarconsumo smallint DEFAULT 2 NOT NULL,
    ftst_icinformarvolume smallint DEFAULT 2 NOT NULL,
    CONSTRAINT ck1_g_faturamento_situacao_tipo CHECK ((ftst_icvalidoagua = ANY (ARRAY[1, 2]))),
    CONSTRAINT ck2_g_faturamento_situacao_tipo CHECK ((ftst_icvalidoesgoto = ANY (ARRAY[1, 2])))
);


ALTER TABLE faturamento.g_faturamento_situacao_tipo OWNER TO gsan_admin;

--
-- TOC entry 5393 (class 0 OID 0)
-- Dependencies: 375
-- Name: COLUMN g_faturamento_situacao_tipo.ftst_icinformarconsumo; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN g_faturamento_situacao_tipo.ftst_icinformarconsumo IS 'Indicador se e para informar o consumo fixo.';


--
-- TOC entry 5394 (class 0 OID 0)
-- Dependencies: 375
-- Name: COLUMN g_faturamento_situacao_tipo.ftst_icinformarvolume; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN g_faturamento_situacao_tipo.ftst_icinformarvolume IS 'Indicador se e para informar o volume fixo.';


--
-- TOC entry 376 (class 1259 OID 3674167)
-- Name: g_imposto_tipo; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_imposto_tipo (
    imtp_id integer NOT NULL,
    imtp_dsimposto character varying(50) NOT NULL,
    imtp_dsabreviadaimposto character varying(10) NOT NULL,
    imtp_icuso smallint NOT NULL,
    imtp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE faturamento.g_imposto_tipo OWNER TO gsan_admin;

--
-- TOC entry 377 (class 1259 OID 3674171)
-- Name: g_imposto_tipo_x; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_imposto_tipo_x (
    imtp_id integer NOT NULL,
    imtp_dsimposto character varying(50) NOT NULL,
    imtp_dsabreviadaimposto character varying(10) NOT NULL,
    imtp_icuso smallint NOT NULL,
    imtp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    imtp_qtd integer
);


ALTER TABLE faturamento.g_imposto_tipo_x OWNER TO gsan_admin;

--
-- TOC entry 378 (class 1259 OID 3674175)
-- Name: seq_g_fat_sit_motivo; Type: SEQUENCE; Schema: faturamento; Owner: gsan_admin
--

CREATE SEQUENCE seq_g_fat_sit_motivo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE faturamento.seq_g_fat_sit_motivo OWNER TO gsan_admin;

--
-- TOC entry 379 (class 1259 OID 3674177)
-- Name: seq_un_resumo_faturamento; Type: SEQUENCE; Schema: faturamento; Owner: gsan_admin
--

CREATE SEQUENCE seq_un_resumo_faturamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE faturamento.seq_un_resumo_faturamento OWNER TO gsan_admin;

--
-- TOC entry 380 (class 1259 OID 3674183)
-- Name: sequence_un_resumo_indicadores_faturamento_ref_2007; Type: SEQUENCE; Schema: faturamento; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2007
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE faturamento.sequence_un_resumo_indicadores_faturamento_ref_2007 OWNER TO gsan_admin;

--
-- TOC entry 381 (class 1259 OID 3674185)
-- Name: sequence_un_resumo_indicadores_faturamento_ref_2008; Type: SEQUENCE; Schema: faturamento; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2008
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE faturamento.sequence_un_resumo_indicadores_faturamento_ref_2008 OWNER TO gsan_admin;

--
-- TOC entry 382 (class 1259 OID 3674187)
-- Name: sequence_un_resumo_indicadores_faturamento_ref_2009; Type: SEQUENCE; Schema: faturamento; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2009
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE faturamento.sequence_un_resumo_indicadores_faturamento_ref_2009 OWNER TO gsan_admin;

--
-- TOC entry 383 (class 1259 OID 3674193)
-- Name: sequence_un_resumo_refaturamento; Type: SEQUENCE; Schema: faturamento; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_refaturamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE faturamento.sequence_un_resumo_refaturamento OWNER TO gsan_admin;

--
-- TOC entry 384 (class 1259 OID 3674195)
-- Name: un_resumo_faturamento; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_faturamento (
    refa_id integer NOT NULL,
    refa_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    rota_id integer NOT NULL,
    qdra_id integer NOT NULL,
    refa_cdsetorcomercial integer NOT NULL,
    refa_nnquadra integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    refa_vofaturadoagua integer NOT NULL,
    refa_vofaturadoesgoto integer NOT NULL,
    refa_vlfaturadoagua numeric(13,2) NOT NULL,
    refa_vlfaturadoesgoto numeric(13,2) NOT NULL,
    refa_qtcontasemitidas integer NOT NULL,
    refa_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    crog_id integer,
    lict_id integer,
    dotp_id integer,
    fntp_id integer,
    refa_vldocsfaturadoscredito numeric(13,2),
    refa_qtdocsfaturadoscredito integer,
    refa_vldocsfaturadosoutros numeric(13,2),
    refa_qtdocsfaturadosoutros smallint,
    empr_id integer NOT NULL,
    refa_vlfinanincluido numeric(13,2),
    refa_vlfinancancelado numeric(13,2),
    refa_qteconomiasfaturadas integer,
    dbtp_id integer,
    crti_id integer,
    refa_ichidrometro integer NOT NULL,
    imtp_id integer,
    refa_vlimposto numeric(13,2),
    cstf_id integer NOT NULL,
    ftgr_id integer NOT NULL,
    refa_cdrota integer,
    refa_vljurosparcelamento numeric(13,2),
    refa_vlcreditoscobindevcan numeric(13,2),
    refa_vldescincondcan numeric(13,2),
    refa_vlguiadevolcancel numeric(13,2),
    refa_vlparcelamentoscan numeric(13,2),
    refa_vlcreditoscobindevinc numeric(13,2),
    refa_vldescincondinc numeric(13,2),
    refa_vlguiadevolincl numeric(13,2)
);


ALTER TABLE faturamento.un_resumo_faturamento OWNER TO gsan_admin;

--
-- TOC entry 5404 (class 0 OID 0)
-- Dependencies: 384
-- Name: COLUMN un_resumo_faturamento.refa_vljurosparcelamento; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_faturamento.refa_vljurosparcelamento IS 'Valor de juros de parcelamento';


--
-- TOC entry 5405 (class 0 OID 0)
-- Dependencies: 384
-- Name: COLUMN un_resumo_faturamento.refa_vlcreditoscobindevcan; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_faturamento.refa_vlcreditoscobindevcan IS 'Valor de creditos a realizar por cobrancas indevidas cancelados';


--
-- TOC entry 5406 (class 0 OID 0)
-- Dependencies: 384
-- Name: COLUMN un_resumo_faturamento.refa_vldescincondcan; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_faturamento.refa_vldescincondcan IS 'Valor de descontos incondicionais cancelados';


--
-- TOC entry 5407 (class 0 OID 0)
-- Dependencies: 384
-- Name: COLUMN un_resumo_faturamento.refa_vlguiadevolcancel; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_faturamento.refa_vlguiadevolcancel IS 'Valor de guias de devolucao de valores cobrados indevidamente canceladas';


--
-- TOC entry 5408 (class 0 OID 0)
-- Dependencies: 384
-- Name: COLUMN un_resumo_faturamento.refa_vlparcelamentoscan; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_faturamento.refa_vlparcelamentoscan IS 'Valor de parcelamentos cancelados';


--
-- TOC entry 5409 (class 0 OID 0)
-- Dependencies: 384
-- Name: COLUMN un_resumo_faturamento.refa_vlcreditoscobindevinc; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_faturamento.refa_vlcreditoscobindevinc IS 'Valor de creditos a realizar por cobrancas indevidas incluidos';


--
-- TOC entry 5410 (class 0 OID 0)
-- Dependencies: 384
-- Name: COLUMN un_resumo_faturamento.refa_vldescincondinc; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_faturamento.refa_vldescincondinc IS 'Valor de descontos incondicionais incluidos';


--
-- TOC entry 5411 (class 0 OID 0)
-- Dependencies: 384
-- Name: COLUMN un_resumo_faturamento.refa_vlguiadevolincl; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_faturamento.refa_vlguiadevolincl IS 'Valor de guias de devolucao de valores cobrados indevidamente incluidas';


--
-- TOC entry 385 (class 1259 OID 3674205)
-- Name: un_resumo_indicadores_faturamento_ref_2007; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_indicadores_faturamento_ref_2007 (
    refa_id integer DEFAULT nextval('sequence_un_resumo_indicadores_faturamento_ref_2007'::regclass) NOT NULL,
    refa_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    refa_cdsetorcomercial integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    crog_id integer,
    lict_id integer,
    dotp_id integer NOT NULL,
    fntp_id integer,
    dbtp_id integer,
    crti_id integer,
    refa_qtcontasemitidas integer NOT NULL,
    rerf_qtcontasretificadas integer NOT NULL,
    rerf_qtcontascanceladas integer NOT NULL,
    rerf_qtcontasincluidas integer NOT NULL,
    refa_qteconomiasfaturadas integer NOT NULL,
    refa_vofaturadoagua integer NOT NULL,
    rerf_vocanceladoagua integer NOT NULL,
    rerf_voincluidoagua integer NOT NULL,
    refa_vofaturadoesgoto integer NOT NULL,
    rerf_vocanceladoesgoto integer NOT NULL,
    rerf_voincluidoesgoto integer NOT NULL,
    refa_vlfaturadoagua numeric(13,2) NOT NULL,
    rerf_vlcanceladoagua numeric(13,2) NOT NULL,
    rerf_vlincluidoagua numeric(13,2) NOT NULL,
    refa_vlfaturadoesgoto numeric(13,2) NOT NULL,
    rerf_vlcanceladoesgoto numeric(13,2) NOT NULL,
    rerf_vlincluidoesgoto numeric(13,2) NOT NULL,
    refa_vldocumentosfaturadoscredito numeric(13,2) NOT NULL,
    rerf_vlcanceladocredito numeric(13,2) NOT NULL,
    rerf_vlincluidocredito numeric(13,2) NOT NULL,
    refa_vldocumentosfaturadosoutros numeric(13,2) NOT NULL,
    rerf_vlcanceladooutros numeric(13,2) NOT NULL,
    rerf_vlincluidooutros numeric(13,2) NOT NULL,
    refa_vlacrescimoimpontualidade numeric(13,2) NOT NULL,
    refa_qtcontasemitidasma integer NOT NULL,
    rerf_qtcontasretificadasma integer NOT NULL,
    rerf_qtcontascanceladasma integer NOT NULL,
    rerf_qtcontasincluidasma integer NOT NULL,
    refa_qteconomiasfaturadasma integer NOT NULL,
    refa_vofaturadoaguama integer NOT NULL,
    rerf_vocanceladoaguama integer NOT NULL,
    rerf_voincluidoaguama integer NOT NULL,
    refa_vofaturadoesgotoma integer NOT NULL,
    rerf_vocanceladoesgotoma integer NOT NULL,
    rerf_voincluidoesgotoma integer NOT NULL,
    refa_vlfaturadoaguama numeric(13,2) NOT NULL,
    rerf_vlcanceladoaguama numeric(13,2) NOT NULL,
    rerf_vlincluidoaguama numeric(13,2) NOT NULL,
    refa_vlfaturadoesgotoma numeric(13,2) NOT NULL,
    rerf_vlcanceladoesgotoma numeric(13,2) NOT NULL,
    rerf_vlincluidoesgotoma numeric(13,2) NOT NULL,
    refa_vldocumentosfaturadoscreditoma numeric(13,2) NOT NULL,
    rerf_vlcanceladocreditoma numeric(13,2) NOT NULL,
    rerf_vlincluidocreditoma numeric(13,2) NOT NULL,
    refa_vldocumentosfaturadosoutrosma numeric(13,2) NOT NULL,
    rerf_vlcanceladooutrosma numeric(13,2) NOT NULL,
    rerf_vlincluidooutrosma numeric(13,2) NOT NULL,
    refa_vlacrescimoimpontualidadema numeric(13,2) NOT NULL,
    refa_vlarrastos numeric(13,2) NOT NULL,
    refa_vlparcelamento numeric(13,2) NOT NULL,
    rerf_qtguiascanceladas integer NOT NULL,
    refa_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE faturamento.un_resumo_indicadores_faturamento_ref_2007 OWNER TO gsan_admin;

--
-- TOC entry 386 (class 1259 OID 3674210)
-- Name: un_resumo_indicadores_faturamento_ref_2008; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_indicadores_faturamento_ref_2008 (
    refa_id integer DEFAULT nextval('sequence_un_resumo_indicadores_faturamento_ref_2008'::regclass) NOT NULL,
    refa_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    refa_cdsetorcomercial integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    crog_id integer,
    lict_id integer,
    dotp_id integer NOT NULL,
    fntp_id integer,
    dbtp_id integer,
    crti_id integer,
    refa_qtcontasemitidas integer NOT NULL,
    rerf_qtcontasretificadas integer NOT NULL,
    rerf_qtcontascanceladas integer NOT NULL,
    rerf_qtcontasincluidas integer NOT NULL,
    refa_qteconomiasfaturadas integer NOT NULL,
    refa_vofaturadoagua integer NOT NULL,
    rerf_vocanceladoagua integer NOT NULL,
    rerf_voincluidoagua integer NOT NULL,
    refa_vofaturadoesgoto integer NOT NULL,
    rerf_vocanceladoesgoto integer NOT NULL,
    rerf_voincluidoesgoto integer NOT NULL,
    refa_vlfaturadoagua numeric(13,2) NOT NULL,
    rerf_vlcanceladoagua numeric(13,2) NOT NULL,
    rerf_vlincluidoagua numeric(13,2) NOT NULL,
    refa_vlfaturadoesgoto numeric(13,2) NOT NULL,
    rerf_vlcanceladoesgoto numeric(13,2) NOT NULL,
    rerf_vlincluidoesgoto numeric(13,2) NOT NULL,
    refa_vldocumentosfaturadoscredito numeric(13,2) NOT NULL,
    rerf_vlcanceladocredito numeric(13,2) NOT NULL,
    rerf_vlincluidocredito numeric(13,2) NOT NULL,
    refa_vldocumentosfaturadosoutros numeric(13,2) NOT NULL,
    rerf_vlcanceladooutros numeric(13,2) NOT NULL,
    rerf_vlincluidooutros numeric(13,2) NOT NULL,
    refa_vlacrescimoimpontualidade numeric(13,2) NOT NULL,
    refa_qtcontasemitidasma integer NOT NULL,
    rerf_qtcontasretificadasma integer NOT NULL,
    rerf_qtcontascanceladasma integer NOT NULL,
    rerf_qtcontasincluidasma integer NOT NULL,
    refa_qteconomiasfaturadasma integer NOT NULL,
    refa_vofaturadoaguama integer NOT NULL,
    rerf_vocanceladoaguama integer NOT NULL,
    rerf_voincluidoaguama integer NOT NULL,
    refa_vofaturadoesgotoma integer NOT NULL,
    rerf_vocanceladoesgotoma integer NOT NULL,
    rerf_voincluidoesgotoma integer NOT NULL,
    refa_vlfaturadoaguama numeric(13,2) NOT NULL,
    rerf_vlcanceladoaguama numeric(13,2) NOT NULL,
    rerf_vlincluidoaguama numeric(13,2) NOT NULL,
    refa_vlfaturadoesgotoma numeric(13,2) NOT NULL,
    rerf_vlcanceladoesgotoma numeric(13,2) NOT NULL,
    rerf_vlincluidoesgotoma numeric(13,2) NOT NULL,
    refa_vldocumentosfaturadoscreditoma numeric(13,2) NOT NULL,
    rerf_vlcanceladocreditoma numeric(13,2) NOT NULL,
    rerf_vlincluidocreditoma numeric(13,2) NOT NULL,
    refa_vldocumentosfaturadosoutrosma numeric(13,2) NOT NULL,
    rerf_vlcanceladooutrosma numeric(13,2) NOT NULL,
    rerf_vlincluidooutrosma numeric(13,2) NOT NULL,
    refa_vlacrescimoimpontualidadema numeric(13,2) NOT NULL,
    refa_vlarrastos numeric(13,2) NOT NULL,
    refa_vlparcelamento numeric(13,2) NOT NULL,
    rerf_qtguiascanceladas integer NOT NULL,
    refa_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    scat_id integer NOT NULL,
    qdra_id integer NOT NULL,
    rota_id integer NOT NULL
);


ALTER TABLE faturamento.un_resumo_indicadores_faturamento_ref_2008 OWNER TO gsan_admin;

--
-- TOC entry 387 (class 1259 OID 3674215)
-- Name: un_resumo_indicadores_faturamento_ref_2009; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_indicadores_faturamento_ref_2009 (
    refa_id integer DEFAULT nextval('sequence_un_resumo_indicadores_faturamento_ref_2009'::regclass) NOT NULL,
    refa_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    refa_cdsetorcomercial integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    crog_id integer,
    lict_id integer,
    dotp_id integer NOT NULL,
    fntp_id integer,
    dbtp_id integer,
    crti_id integer,
    refa_qtcontasemitidas integer NOT NULL,
    rerf_qtcontasretificadas integer NOT NULL,
    rerf_qtcontascanceladas integer NOT NULL,
    rerf_qtcontasincluidas integer NOT NULL,
    refa_qteconomiasfaturadas integer NOT NULL,
    refa_vofaturadoagua integer NOT NULL,
    rerf_vocanceladoagua integer NOT NULL,
    rerf_voincluidoagua integer NOT NULL,
    refa_vofaturadoesgoto integer NOT NULL,
    rerf_vocanceladoesgoto integer NOT NULL,
    rerf_voincluidoesgoto integer NOT NULL,
    refa_vlfaturadoagua numeric(13,2) NOT NULL,
    rerf_vlcanceladoagua numeric(13,2) NOT NULL,
    rerf_vlincluidoagua numeric(13,2) NOT NULL,
    refa_vlfaturadoesgoto numeric(13,2) NOT NULL,
    rerf_vlcanceladoesgoto numeric(13,2) NOT NULL,
    rerf_vlincluidoesgoto numeric(13,2) NOT NULL,
    refa_vldocumentosfaturadoscredito numeric(13,2) NOT NULL,
    rerf_vlcanceladocredito numeric(13,2) NOT NULL,
    rerf_vlincluidocredito numeric(13,2) NOT NULL,
    refa_vldocumentosfaturadosoutros numeric(13,2) NOT NULL,
    rerf_vlcanceladooutros numeric(13,2) NOT NULL,
    rerf_vlincluidooutros numeric(13,2) NOT NULL,
    refa_vlacrescimoimpontualidade numeric(13,2) NOT NULL,
    refa_qtcontasemitidasma integer NOT NULL,
    rerf_qtcontasretificadasma integer NOT NULL,
    rerf_qtcontascanceladasma integer NOT NULL,
    rerf_qtcontasincluidasma integer NOT NULL,
    refa_qteconomiasfaturadasma integer NOT NULL,
    refa_vofaturadoaguama integer NOT NULL,
    rerf_vocanceladoaguama integer NOT NULL,
    rerf_voincluidoaguama integer NOT NULL,
    refa_vofaturadoesgotoma integer NOT NULL,
    rerf_vocanceladoesgotoma integer NOT NULL,
    rerf_voincluidoesgotoma integer NOT NULL,
    refa_vlfaturadoaguama numeric(13,2) NOT NULL,
    rerf_vlcanceladoaguama numeric(13,2) NOT NULL,
    rerf_vlincluidoaguama numeric(13,2) NOT NULL,
    refa_vlfaturadoesgotoma numeric(13,2) NOT NULL,
    rerf_vlcanceladoesgotoma numeric(13,2) NOT NULL,
    rerf_vlincluidoesgotoma numeric(13,2) NOT NULL,
    refa_vldocumentosfaturadoscreditoma numeric(13,2) NOT NULL,
    rerf_vlcanceladocreditoma numeric(13,2) NOT NULL,
    rerf_vlincluidocreditoma numeric(13,2) NOT NULL,
    refa_vldocumentosfaturadosoutrosma numeric(13,2) NOT NULL,
    rerf_vlcanceladooutrosma numeric(13,2) NOT NULL,
    rerf_vlincluidooutrosma numeric(13,2) NOT NULL,
    refa_vlacrescimoimpontualidadema numeric(13,2) NOT NULL,
    refa_vlarrastos numeric(13,2) NOT NULL,
    refa_vlparcelamento numeric(13,2) NOT NULL,
    rerf_qtguiascanceladas integer NOT NULL,
    refa_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    scat_id integer NOT NULL,
    qdra_id integer NOT NULL,
    rota_id integer NOT NULL
);


ALTER TABLE faturamento.un_resumo_indicadores_faturamento_ref_2009 OWNER TO gsan_admin;

--
-- TOC entry 388 (class 1259 OID 3674220)
-- Name: un_resumo_refaturamento; Type: TABLE; Schema: faturamento; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_refaturamento (
    rerf_id integer NOT NULL,
    rerf_amreferencia integer NOT NULL,
    rerf_amreferenciaconta integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    stcm_id integer NOT NULL,
    rerf_cdsetorcomercial integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    crog_id integer,
    lict_id integer,
    dotp_id integer,
    cstf_id integer NOT NULL,
    fntp_id integer,
    dbtp_id integer,
    crtp_id integer,
    rerf_ichidrometro integer NOT NULL,
    rerf_qtcontasretificadas integer NOT NULL,
    rerf_qtcontascanceladas integer NOT NULL,
    rerf_vlcanceladoagua numeric(13,2) NOT NULL,
    rerf_vocanceladoagua integer NOT NULL,
    rerf_vlcanceladoesgoto numeric(13,2) NOT NULL,
    rerf_vocanceladoesgoto integer NOT NULL,
    rerf_vlimpostoscancelados numeric(13,2) NOT NULL,
    rerf_qtcontasincluidas integer NOT NULL,
    rerf_vlincluidoagua numeric(13,2) NOT NULL,
    rerf_voincluidoagua integer NOT NULL,
    rerf_vlincluidoesgoto numeric(13,2) NOT NULL,
    rerf_voincluidoesgoto integer NOT NULL,
    rerf_vlimpostosincluidos numeric(13,2) NOT NULL,
    rerf_vlincluidooutros numeric(10,2) NOT NULL,
    rerf_qtguiasincluidas integer NOT NULL,
    rerf_vlguiasincluidas numeric(13,2) NOT NULL,
    rerf_vlincluidocredito numeric(13,2) NOT NULL,
    rerf_qtguiascanceladas integer NOT NULL,
    rerf_vlcanceladocredito numeric(13,2) NOT NULL,
    rerf_vlcanceladooutros numeric(13,2) NOT NULL,
    rerf_vlguiascanceladas numeric(13,2) NOT NULL,
    rerf_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE faturamento.un_resumo_refaturamento OWNER TO gsan_admin;

--
-- TOC entry 5416 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.rerf_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.rerf_id IS 'Chave primaria da tabela';


--
-- TOC entry 5417 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.rerf_amreferencia; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.rerf_amreferencia IS 'Ano/Mês de Referência';


--
-- TOC entry 5418 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.rerf_amreferenciaconta; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.rerf_amreferenciaconta IS 'Ano/Mês da Conta';


--
-- TOC entry 5419 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.greg_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.greg_id IS 'Id da Gerencia Regional';


--
-- TOC entry 5420 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.uneg_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.uneg_id IS 'Id da Unidade d eNegocio';


--
-- TOC entry 5421 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.loca_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.loca_id IS 'Id da Localidade';


--
-- TOC entry 5422 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.stcm_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.stcm_id IS 'Id do Setor Comercial';


--
-- TOC entry 5423 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.rerf_cdsetorcomercial; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.rerf_cdsetorcomercial IS 'Código do Setor Comercial ';


--
-- TOC entry 5424 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.iper_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.iper_id IS 'Id do Perfil do Imovel';


--
-- TOC entry 5425 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.last_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.last_id IS 'Id da Situação da Lig. Agua';


--
-- TOC entry 5426 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.lest_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.lest_id IS 'Id da Situação da Lig. Esgoto';


--
-- TOC entry 5427 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.catg_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.catg_id IS 'Id da Categoria';


--
-- TOC entry 5428 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.scat_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.scat_id IS 'Id da Subcategoria';


--
-- TOC entry 5429 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.epod_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.epod_id IS 'Id da Esfera de Poder';


--
-- TOC entry 5430 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.cltp_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.cltp_id IS 'Id do Tipo de Cliente';


--
-- TOC entry 5431 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.lapf_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.lapf_id IS 'Id do Perfil da Lig. Agua';


--
-- TOC entry 5432 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.lepf_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.lepf_id IS 'Id do Perfil da Lig. Esgoto';


--
-- TOC entry 5433 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.crog_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.crog_id IS 'Id da Origem do Crédito';


--
-- TOC entry 5434 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.lict_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.lict_id IS 'Id da Linha de Credito';


--
-- TOC entry 5435 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.dotp_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.dotp_id IS 'Id do Tipo de Documento';


--
-- TOC entry 5436 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.cstf_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.cstf_id IS 'Id da Tarifa de Consumo';


--
-- TOC entry 5437 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.fntp_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.fntp_id IS 'Id do Tipo de Financiamento';


--
-- TOC entry 5438 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.dbtp_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.dbtp_id IS 'Id do Tipo de Débito';


--
-- TOC entry 5439 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.crtp_id; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.crtp_id IS 'Id do Tipo de Criterio de Cobranca';


--
-- TOC entry 5440 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.rerf_ichidrometro; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.rerf_ichidrometro IS 'Indicador de existência de Hidrômetro na Ligacacao';


--
-- TOC entry 5441 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.rerf_qtcontasretificadas; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.rerf_qtcontasretificadas IS 'Total de Contas RETIFICADAS';


--
-- TOC entry 5442 (class 0 OID 0)
-- Dependencies: 388
-- Name: COLUMN un_resumo_refaturamento.rerf_qtcontascanceladas; Type: COMMENT; Schema: faturamento; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_refaturamento.rerf_qtcontascanceladas IS 'Total de Contas CANCELADAS';


--
-- TOC entry 389 (class 1259 OID 3674224)
-- Name: vw_consumo_tarifa; Type: VIEW; Schema: faturamento; Owner: gsan_admin
--

CREATE VIEW vw_consumo_tarifa AS
    SELECT table1.cstf_id, table1.cstf_dsconsumotarifa, table1.cstf_icuso, table1.cstf_tmultimaalteracao, table1.lapf_id, table1.ttpc_id FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from faturamento.consumo_tarifa '::text) table1(cstf_id integer, cstf_dsconsumotarifa character varying(30), cstf_icuso smallint, cstf_tmultimaalteracao timestamp without time zone, lapf_id smallint, ttpc_id integer);


ALTER TABLE faturamento.vw_consumo_tarifa OWNER TO gsan_admin;

--
-- TOC entry 390 (class 1259 OID 3674228)
-- Name: vw_credito_origem; Type: VIEW; Schema: faturamento; Owner: gsan_admin
--

CREATE VIEW vw_credito_origem AS
    SELECT table1.crog_id, table1.crog_dscreditoorigem, table1.crog_dsabreviado, table1.crog_icuso, table1.crog_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select * from faturamento.credito_origem'::text) table1(crog_id integer, crog_dscreditoorigem character varying(40), crog_dsabreviado character varying(10), crog_icuso smallint, crog_tmultimaalteracao timestamp without time zone);


ALTER TABLE faturamento.vw_credito_origem OWNER TO gsan_admin;

--
-- TOC entry 391 (class 1259 OID 3674232)
-- Name: vw_credito_tipo; Type: VIEW; Schema: faturamento; Owner: gsan_admin
--

CREATE VIEW vw_credito_tipo AS
    SELECT table1.crti_id, table1.lict_id, table1.crti_dscreditotipo, table1.crti_dsabreviado, table1.crti_icuso, table1.crti_tmultimaalteracao, table1.crti_vllimite, table1.crti_icgeracaoautomatica, table1.crtp_nncodigoconstante FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from faturamento.credito_tipo'::text) table1(crti_id integer, lict_id integer, crti_dscreditotipo character varying(30), crti_dsabreviado character varying(18), crti_icuso smallint, crti_tmultimaalteracao timestamp without time zone, crti_vllimite numeric(13,2), crti_icgeracaoautomatica smallint, crtp_nncodigoconstante integer);


ALTER TABLE faturamento.vw_credito_tipo OWNER TO gsan_admin;

--
-- TOC entry 392 (class 1259 OID 3674236)
-- Name: vw_debito_tipo; Type: VIEW; Schema: faturamento; Owner: gsan_admin
--

CREATE VIEW vw_debito_tipo AS
    SELECT table1.dbtp_id, table1.lict_id, table1.dbtp_dsdebitotipo, table1.dbtp_dsabreviado, table1.dbtp_icuso, table1.dbtp_tmultimaalteracao, table1.fntp_id, table1.dbtp_vllimite, table1.dbtp_icgeracaoautomatica, table1.dbtp_icgeracaoconta, table1.dbtp_vlsugerido, table1.dbtp_valorlimiteinferior, table1.dbtp_nncodigoconstante, table1.dbtp_iccartaocredito, table1.cnct_id FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from faturamento.debito_tipo'::text) table1(dbtp_id integer, lict_id integer, dbtp_dsdebitotipo character varying(30), dbtp_dsabreviado character varying(18), dbtp_icuso smallint, dbtp_tmultimaalteracao timestamp without time zone, fntp_id integer, dbtp_vllimite numeric(13,2), dbtp_icgeracaoautomatica smallint, dbtp_icgeracaoconta smallint, dbtp_vlsugerido numeric(13,2), dbtp_valorlimiteinferior numeric(13,2), dbtp_nncodigoconstante integer, dbtp_iccartaocredito smallint, cnct_id integer);


ALTER TABLE faturamento.vw_debito_tipo OWNER TO gsan_admin;

--
-- TOC entry 393 (class 1259 OID 3674244)
-- Name: vw_faturamento_grupo; Type: VIEW; Schema: faturamento; Owner: gsan_admin
--

CREATE VIEW vw_faturamento_grupo AS
    SELECT table1.ftgr_id, table1.ftgr_dsfaturamentogrupo, table1.ftgr_dsabreviado, table1.ftgr_icuso, table1.ftgr_tmultimaalteracao, table1.ftgr_amreferencia, table1.ftgr_nndiavencimento, table1.ftgr_icvencimentomesfatura FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from faturamento.faturamento_grupo'::text) table1(ftgr_id integer, ftgr_dsfaturamentogrupo character varying(25), ftgr_dsabreviado character(3), ftgr_icuso smallint, ftgr_tmultimaalteracao timestamp without time zone, ftgr_amreferencia integer, ftgr_nndiavencimento smallint, ftgr_icvencimentomesfatura smallint);


ALTER TABLE faturamento.vw_faturamento_grupo OWNER TO gsan_admin;

--
-- TOC entry 394 (class 1259 OID 3674248)
-- Name: vw_faturamento_situacao_motivo; Type: VIEW; Schema: faturamento; Owner: gsan_admin
--

CREATE VIEW vw_faturamento_situacao_motivo AS
    SELECT table1.ftsm_id, table1.ftsm_dsfatsitmotivo, table1.ftsm_icuso, table1.ftsm_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from faturamento.faturamento_situacao_motivo'::text) table1(ftsm_id integer, ftsm_dsfatsitmotivo character varying(50), ftsm_icuso smallint, ftsm_tmultimaalteracao timestamp without time zone);


ALTER TABLE faturamento.vw_faturamento_situacao_motivo OWNER TO gsan_admin;

--
-- TOC entry 395 (class 1259 OID 3674252)
-- Name: vw_faturamento_situacao_tipo; Type: VIEW; Schema: faturamento; Owner: gsan_admin
--

CREATE VIEW vw_faturamento_situacao_tipo AS
    SELECT table1.ftst_id, table1.ftst_dsfaturamentosituacaotipo, table1.ftst_icfaturamentoparalisacao, table1.ftst_icleituraparalisacao, table1.ftst_icuso, table1.ftst_tmultimaalteracao, table1.lacs_idconsumoacobrarsemleitura, table1.lacs_idconsumoacobrarcomleitura, table1.lalt_idleituraafaturarsemleitura, table1.lalt_idleituraafaturarcomleitura, table1.ftst_icfaturamentoparalisacaoesgoto, table1.ftst_icvalidoagua, table1.ftst_icvalidoesgoto, table1.ftst_icinformarconsumo, table1.ftst_icinformarvolume FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from faturamento.faturamento_situacao_tipo'::text) table1(ftst_id integer, ftst_dsfaturamentosituacaotipo character varying(50), ftst_icfaturamentoparalisacao smallint, ftst_icleituraparalisacao smallint, ftst_icuso smallint, ftst_tmultimaalteracao timestamp without time zone, lacs_idconsumoacobrarsemleitura integer, lacs_idconsumoacobrarcomleitura integer, lalt_idleituraafaturarsemleitura integer, lalt_idleituraafaturarcomleitura integer, ftst_icfaturamentoparalisacaoesgoto smallint, ftst_icvalidoagua smallint, ftst_icvalidoesgoto smallint, ftst_icinformarconsumo smallint, ftst_icinformarvolume smallint);


ALTER TABLE faturamento.vw_faturamento_situacao_tipo OWNER TO gsan_admin;

--
-- TOC entry 396 (class 1259 OID 3674258)
-- Name: vw_imposto_tipo; Type: VIEW; Schema: faturamento; Owner: gsan_admin
--

CREATE VIEW vw_imposto_tipo AS
    SELECT table1.imtp_id, table1.imtp_dsimposto, table1.imtp_dsabreviadaimposto, table1.imtp_icuso, table1.imtp_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select * from faturamento.imposto_tipo'::text) table1(imtp_id integer, imtp_dsimposto character varying(50), imtp_dsabreviadaimposto character varying(10), imtp_icuso smallint, imtp_tmultimaalteracao timestamp without time zone);


ALTER TABLE faturamento.vw_imposto_tipo OWNER TO gsan_admin;

--
-- TOC entry 397 (class 1259 OID 3674264)
-- Name: vw_un_resumo_faturamento; Type: VIEW; Schema: faturamento; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_faturamento AS
    SELECT un_resumo_faturamento.refa_id, un_resumo_faturamento.refa_amreferencia, un_resumo_faturamento.greg_id, un_resumo_faturamento.uneg_id, un_resumo_faturamento.loca_id, un_resumo_faturamento.loca_cdelo, un_resumo_faturamento.stcm_id, un_resumo_faturamento.rota_id, un_resumo_faturamento.qdra_id, un_resumo_faturamento.refa_cdsetorcomercial, un_resumo_faturamento.refa_nnquadra, un_resumo_faturamento.iper_id, un_resumo_faturamento.last_id, un_resumo_faturamento.lest_id, un_resumo_faturamento.catg_id, un_resumo_faturamento.scat_id, un_resumo_faturamento.epod_id, un_resumo_faturamento.cltp_id, un_resumo_faturamento.lapf_id, un_resumo_faturamento.lepf_id, un_resumo_faturamento.refa_vofaturadoagua, un_resumo_faturamento.refa_vofaturadoesgoto, un_resumo_faturamento.refa_vlfaturadoagua, un_resumo_faturamento.refa_vlfaturadoesgoto, un_resumo_faturamento.refa_qtcontasemitidas, un_resumo_faturamento.refa_tmultimaalteracao, un_resumo_faturamento.crog_id, (SELECT li.lict_dsitemlancamentocontabil FROM financeiro.g_lancamento_item_contabil li WHERE (li.lict_id = un_resumo_faturamento.lict_id)) AS descricaoitemcontabil, un_resumo_faturamento.dotp_id, un_resumo_faturamento.fntp_id, un_resumo_faturamento.refa_vldocsfaturadoscredito AS refa_vldocumentosfaturadoscredito, un_resumo_faturamento.refa_qtdocsfaturadoscredito AS refa_qtdocumentosfaturadoscredito, un_resumo_faturamento.refa_vldocsfaturadosoutros AS refa_vldocumentosfaturadosoutros, un_resumo_faturamento.refa_qtdocsfaturadosoutros AS refa_qtdocumentosfaturadosoutros, un_resumo_faturamento.empr_id, un_resumo_faturamento.refa_vlfinanincluido, un_resumo_faturamento.refa_vlfinancancelado, un_resumo_faturamento.refa_qteconomiasfaturadas, (SELECT dt.dbtp_dsdebitotipo FROM g_debito_tipo dt WHERE (dt.dbtp_id = un_resumo_faturamento.dbtp_id)) AS descricaodebitotipo, (SELECT ct.crti_dscreditotipo FROM g_credito_tipo ct WHERE (ct.crti_id = un_resumo_faturamento.crti_id)) AS descricaocreditotipo, un_resumo_faturamento.refa_ichidrometro, un_resumo_faturamento.imtp_id, un_resumo_faturamento.refa_vlimposto FROM un_resumo_faturamento;


ALTER TABLE faturamento.vw_un_resumo_faturamento OWNER TO gsan_admin;

--
-- TOC entry 398 (class 1259 OID 3674269)
-- Name: vw_un_resumo_faturamento_agua_esgoto; Type: VIEW; Schema: faturamento; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_faturamento_agua_esgoto AS
    SELECT a.refa_id, a.refa_amreferencia, substr((a.refa_amreferencia)::text, 1, 4) AS refa_anoreferencia, CASE WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END AS refa_mesreferencia, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.refa_cdsetorcomercial, a.refa_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id, a.crog_id, a.lict_id, a.dotp_id, a.fntp_id, a.empr_id, a.refa_qtcontasemitidas, a.refa_qtcontasemitidas AS refa_qteconomiasfaturadas, a.refa_vofaturadoagua, a.refa_vofaturadoesgoto, a.refa_vlfaturadoagua, a.refa_vlfaturadoesgoto, a.refa_vldocsfaturadoscredito AS refa_vldocumentosfaturadoscredito, a.refa_vldocsfaturadosoutros AS refa_vldocumentosfaturadosoutros, CASE WHEN (a.lict_id = 2) THEN b.refa_vldocsfaturadosoutros ELSE (0)::numeric END AS refa_vlacrescimoimpontualidade, b.refa_qtcontasemitidas AS refa_qtcontasemitidasma, b.refa_qtcontasemitidas AS refa_qteconomiasfaturadasma, b.refa_vofaturadoagua AS refa_vofaturadoaguama, b.refa_vofaturadoesgoto AS refa_vofaturadoesgotoma, b.refa_vlfaturadoagua AS refa_vlfaturadoaguama, b.refa_vlfaturadoesgoto AS refa_vlfaturadoesgotoma, b.refa_vldocsfaturadoscredito AS refa_vldocumentosfaturadoscreditoma, b.refa_vldocsfaturadosoutros AS refa_vldocumentosfaturadosoutrosma, CASE WHEN (a.fntp_id = ANY (ARRAY[5, 6, 7])) THEN (a.refa_vlfinanincluido - a.refa_vlfinancancelado) ELSE (0)::numeric END AS refa_vlarrastos, CASE WHEN (a.fntp_id <> ALL (ARRAY[1, 5, 6, 7, 10])) THEN (a.refa_vlfinanincluido - a.refa_vlfinancancelado) ELSE (0)::numeric END AS refa_vlparcelamento, a.refa_tmultimaalteracao FROM (un_resumo_faturamento a LEFT JOIN un_resumo_faturamento b ON ((((((((((((((((((((((((CASE substr((a.refa_amreferencia)::text, 5, 2) WHEN '1'::text THEN ((a.refa_amreferencia - 89) = b.refa_amreferencia) ELSE ((a.refa_amreferencia - 1) = b.refa_amreferencia) END AND (a.greg_id = b.greg_id)) AND (a.uneg_id = b.uneg_id)) AND (a.loca_id = b.loca_id)) AND (a.loca_cdelo = b.loca_cdelo)) AND (a.stcm_id = b.stcm_id)) AND (a.qdra_id = b.qdra_id)) AND (a.rota_id = b.rota_id)) AND (a.refa_cdsetorcomercial = b.refa_cdsetorcomercial)) AND (a.refa_nnquadra = b.refa_nnquadra)) AND (a.iper_id = b.iper_id)) AND (a.last_id = b.last_id)) AND (a.lest_id = b.lest_id)) AND (a.catg_id = b.catg_id)) AND (a.scat_id = b.scat_id)) AND (a.epod_id = b.epod_id)) AND (a.cltp_id = b.cltp_id)) AND (a.lapf_id = b.lapf_id)) AND (a.lepf_id = b.lepf_id)) AND (a.crog_id = b.crog_id)) AND (a.lict_id = b.lict_id)) AND (a.dotp_id = b.dotp_id)) AND (a.fntp_id = b.fntp_id)) AND (a.empr_id = b.empr_id))));


ALTER TABLE faturamento.vw_un_resumo_faturamento_agua_esgoto OWNER TO gsan_admin;

--
-- TOC entry 399 (class 1259 OID 3674276)
-- Name: vw_un_resumo_faturamento_mes_anterior; Type: VIEW; Schema: faturamento; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_faturamento_mes_anterior AS
    SELECT a.refa_id, CASE substr((a.refa_amreferencia)::text, 5, 2) WHEN '12'::text THEN (a.refa_amreferencia + 89) ELSE (a.refa_amreferencia + 1) END AS refa_amreferencia, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.refa_cdsetorcomercial, a.refa_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id, a.crog_id, a.lict_id, a.dotp_id, a.fntp_id, a.empr_id, a.dbtp_id, a.crti_id, a.imtp_id, a.cstf_id, a.ftgr_id, a.refa_ichidrometro, a.refa_qtcontasemitidas, a.refa_qteconomiasfaturadas, a.refa_vofaturadoagua, a.refa_vofaturadoesgoto, a.refa_vlfaturadoagua, a.refa_vlfaturadoesgoto, a.refa_vldocsfaturadoscredito AS refa_vldocumentosfaturadoscredito, a.refa_vldocsfaturadosoutros AS refa_vldocumentosfaturadosoutros, a.refa_vlimposto, CASE WHEN (a.lict_id = 2) THEN a.refa_vldocsfaturadosoutros ELSE (0)::numeric END AS refa_vlacrescimoimpontualidade, CASE WHEN (a.fntp_id = ANY (ARRAY[5, 6, 7])) THEN (a.refa_vlfinanincluido - a.refa_vlfinancancelado) ELSE (0)::numeric END AS refa_vlarrastos, CASE WHEN (a.fntp_id <> ALL (ARRAY[1, 5, 6, 7, 10])) THEN (a.refa_vlfinanincluido - a.refa_vlfinancancelado) ELSE (0)::numeric END AS refa_vlparcelamento, a.refa_tmultimaalteracao FROM un_resumo_faturamento a WHERE (a.refa_amreferencia < (SELECT max(un_resumo_faturamento.refa_amreferencia) AS max FROM un_resumo_faturamento));


ALTER TABLE faturamento.vw_un_resumo_faturamento_mes_anterior OWNER TO gsan_admin;

--
-- TOC entry 400 (class 1259 OID 3674283)
-- Name: vw_un_resumo_faturamento_ind; Type: VIEW; Schema: faturamento; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_faturamento_ind AS
    SELECT CASE WHEN (a.refa_amreferencia IS NULL) THEN b.refa_amreferencia ELSE a.refa_amreferencia END AS refa_amreferencia, to_number(CASE WHEN (a.refa_amreferencia IS NULL) THEN substr((b.refa_amreferencia)::text, 1, 4) ELSE substr((a.refa_amreferencia)::text, 1, 4) END, (9999)::text) AS refa_anoreferencia, CASE WHEN (a.refa_amreferencia IS NULL) THEN CASE WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END ELSE CASE WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END END AS refa_mesreferencia, CASE WHEN (a.greg_id IS NULL) THEN b.greg_id ELSE a.greg_id END AS greg_id, CASE WHEN (a.uneg_id IS NULL) THEN b.uneg_id ELSE a.uneg_id END AS uneg_id, CASE WHEN (a.loca_id IS NULL) THEN b.loca_id ELSE a.loca_id END AS loca_id, CASE WHEN (a.loca_cdelo IS NULL) THEN b.loca_cdelo ELSE a.loca_cdelo END AS loca_cdelo, CASE WHEN (a.stcm_id IS NULL) THEN b.stcm_id ELSE a.stcm_id END AS stcm_id, CASE WHEN (a.qdra_id IS NULL) THEN b.qdra_id ELSE a.qdra_id END AS qdra_id, CASE WHEN (a.rota_id IS NULL) THEN b.rota_id ELSE a.rota_id END AS rota_id, CASE WHEN (a.refa_cdsetorcomercial IS NULL) THEN b.refa_cdsetorcomercial ELSE a.refa_cdsetorcomercial END AS refa_cdsetorcomercial, CASE WHEN (a.refa_nnquadra IS NULL) THEN b.refa_nnquadra ELSE a.refa_nnquadra END AS refa_nnquadra, CASE WHEN (a.iper_id IS NULL) THEN b.iper_id ELSE a.iper_id END AS iper_id, CASE WHEN (a.last_id IS NULL) THEN b.last_id ELSE a.last_id END AS last_id, CASE WHEN (a.lest_id IS NULL) THEN b.lest_id ELSE a.lest_id END AS lest_id, CASE WHEN (a.catg_id IS NULL) THEN b.catg_id ELSE a.catg_id END AS catg_id, CASE WHEN (a.scat_id IS NULL) THEN b.scat_id ELSE a.scat_id END AS scat_id, CASE WHEN (a.epod_id IS NULL) THEN b.epod_id ELSE a.epod_id END AS epod_id, CASE WHEN (a.cltp_id IS NULL) THEN b.cltp_id ELSE a.cltp_id END AS cltp_id, CASE WHEN (a.lapf_id IS NULL) THEN b.lapf_id ELSE a.lapf_id END AS lapf_id, CASE WHEN (a.lepf_id IS NULL) THEN b.lepf_id ELSE a.lepf_id END AS lepf_id, CASE WHEN (a.crog_id IS NULL) THEN b.crog_id ELSE a.crog_id END AS crog_id, CASE WHEN (a.lict_id IS NULL) THEN b.lict_id ELSE a.lict_id END AS lict_id, CASE WHEN (a.dotp_id IS NULL) THEN b.dotp_id ELSE a.dotp_id END AS dotp_id, CASE WHEN (a.fntp_id IS NULL) THEN b.fntp_id ELSE a.fntp_id END AS fntp_id, CASE WHEN (a.dbtp_id IS NULL) THEN b.dbtp_id ELSE a.dbtp_id END AS dbtp_id, CASE WHEN (a.crti_id IS NULL) THEN b.crti_id ELSE a.crti_id END AS crti_id, sum(CASE WHEN (a.refa_qtcontasemitidas IS NULL) THEN 0 ELSE a.refa_qtcontasemitidas END) AS refa_qtcontasemitidas, sum(CASE WHEN (a.refa_qteconomiasfaturadas IS NULL) THEN 0 ELSE a.refa_qteconomiasfaturadas END) AS refa_qteconomiasfaturadas, sum(CASE WHEN (a.refa_vofaturadoagua IS NULL) THEN 0 ELSE a.refa_vofaturadoagua END) AS refa_vofaturadoagua, sum(CASE WHEN (a.refa_vofaturadoesgoto IS NULL) THEN 0 ELSE a.refa_vofaturadoesgoto END) AS refa_vofaturadoesgoto, sum(CASE WHEN (a.refa_vlfaturadoagua IS NULL) THEN (0)::numeric ELSE a.refa_vlfaturadoagua END) AS refa_vlfaturadoagua, sum(CASE WHEN (a.refa_vlfaturadoesgoto IS NULL) THEN (0)::numeric ELSE a.refa_vlfaturadoesgoto END) AS refa_vlfaturadoesgoto, sum(CASE WHEN (a.refa_vldocsfaturadoscredito IS NULL) THEN (0)::numeric ELSE a.refa_vldocsfaturadoscredito END) AS refa_vldocumentosfaturadoscredito, sum(CASE WHEN (a.refa_vldocsfaturadosoutros IS NULL) THEN (0)::numeric ELSE a.refa_vldocsfaturadosoutros END) AS refa_vldocumentosfaturadosoutros, sum(CASE WHEN (a.refa_vlimposto IS NULL) THEN (0)::numeric ELSE a.refa_vlimposto END) AS refa_vlimposto, sum(CASE WHEN (a.lict_id = 2) THEN a.refa_vldocsfaturadosoutros ELSE (0)::numeric END) AS refa_vlacrescimoimpontualidade, sum(CASE WHEN (b.refa_qtcontasemitidas IS NULL) THEN 0 ELSE b.refa_qtcontasemitidas END) AS refa_qtcontasemitidasma, sum(CASE WHEN (b.refa_qteconomiasfaturadas IS NULL) THEN 0 ELSE b.refa_qteconomiasfaturadas END) AS refa_qteconomiasfaturadasma, sum(CASE WHEN (b.refa_vofaturadoagua IS NULL) THEN 0 ELSE b.refa_vofaturadoagua END) AS refa_vofaturadoaguama, sum(CASE WHEN (b.refa_vofaturadoesgoto IS NULL) THEN 0 ELSE b.refa_vofaturadoesgoto END) AS refa_vofaturadoesgotoma, sum(CASE WHEN (b.refa_vlfaturadoagua IS NULL) THEN (0)::numeric ELSE b.refa_vlfaturadoagua END) AS refa_vlfaturadoaguama, sum(CASE WHEN (b.refa_vlfaturadoesgoto IS NULL) THEN (0)::numeric ELSE b.refa_vlfaturadoesgoto END) AS refa_vlfaturadoesgotoma, sum(CASE WHEN (b.refa_vldocumentosfaturadoscredito IS NULL) THEN (0)::numeric ELSE b.refa_vldocumentosfaturadoscredito END) AS refa_vldocumentosfaturadoscreditoma, sum(CASE WHEN (b.refa_vldocumentosfaturadosoutros IS NULL) THEN (0)::numeric ELSE b.refa_vldocumentosfaturadosoutros END) AS refa_vldocumentosfaturadosoutrosma, sum(CASE WHEN (b.refa_vlimposto IS NULL) THEN (0)::numeric ELSE b.refa_vlimposto END) AS refa_vlimpostoma, sum(CASE WHEN (b.lict_id = 2) THEN b.refa_vldocumentosfaturadosoutros ELSE (0)::numeric END) AS refa_vlacrescimoimpontualidadema, sum(CASE WHEN (a.fntp_id = ANY (ARRAY[5, 6, 7])) THEN (a.refa_vlfinanincluido - a.refa_vlfinancancelado) ELSE (0)::numeric END) AS refa_vlarrastos, sum(CASE WHEN (a.fntp_id <> ALL (ARRAY[1, 5, 6, 7, 10])) THEN (a.refa_vlfinanincluido - a.refa_vlfinancancelado) ELSE (0)::numeric END) AS refa_vlparcelamento FROM (un_resumo_faturamento a FULL JOIN vw_un_resumo_faturamento_mes_anterior b ON (((((((((((((((((((((((((((((((a.refa_amreferencia = b.refa_amreferencia) AND (a.greg_id = b.greg_id)) AND (a.uneg_id = b.uneg_id)) AND (a.loca_id = b.loca_id)) AND (a.loca_cdelo = b.loca_cdelo)) AND (a.stcm_id = b.stcm_id)) AND (a.qdra_id = b.qdra_id)) AND (a.rota_id = b.rota_id)) AND (a.refa_cdsetorcomercial = b.refa_cdsetorcomercial)) AND (a.refa_nnquadra = b.refa_nnquadra)) AND (a.iper_id = b.iper_id)) AND (a.last_id = b.last_id)) AND (a.lest_id = b.lest_id)) AND (a.catg_id = b.catg_id)) AND (a.scat_id = b.scat_id)) AND (a.epod_id = b.epod_id)) AND (a.cltp_id = b.cltp_id)) AND (a.lapf_id = b.lapf_id)) AND (a.lepf_id = b.lepf_id)) AND (a.crog_id = b.crog_id)) AND (a.lict_id = b.lict_id)) AND (a.dotp_id = b.dotp_id)) AND (a.fntp_id = b.fntp_id)) AND (a.empr_id = b.empr_id)) AND (a.dbtp_id = b.dbtp_id)) AND (a.crti_id = b.crti_id)) AND (a.imtp_id = b.imtp_id)) AND (a.cstf_id = b.cstf_id)) AND (a.ftgr_id = b.ftgr_id)) AND (a.refa_ichidrometro = b.refa_ichidrometro)))) GROUP BY CASE WHEN (a.refa_amreferencia IS NULL) THEN b.refa_amreferencia ELSE a.refa_amreferencia END, to_number(CASE WHEN (a.refa_amreferencia IS NULL) THEN substr((b.refa_amreferencia)::text, 1, 4) ELSE substr((a.refa_amreferencia)::text, 1, 4) END, (9999)::text), CASE WHEN (a.refa_amreferencia IS NULL) THEN CASE WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END ELSE CASE WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((a.refa_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END END, CASE WHEN (a.greg_id IS NULL) THEN b.greg_id ELSE a.greg_id END, CASE WHEN (a.uneg_id IS NULL) THEN b.uneg_id ELSE a.uneg_id END, CASE WHEN (a.loca_id IS NULL) THEN b.loca_id ELSE a.loca_id END, CASE WHEN (a.loca_cdelo IS NULL) THEN b.loca_cdelo ELSE a.loca_cdelo END, CASE WHEN (a.stcm_id IS NULL) THEN b.stcm_id ELSE a.stcm_id END, CASE WHEN (a.qdra_id IS NULL) THEN b.qdra_id ELSE a.qdra_id END, CASE WHEN (a.rota_id IS NULL) THEN b.rota_id ELSE a.rota_id END, CASE WHEN (a.refa_cdsetorcomercial IS NULL) THEN b.refa_cdsetorcomercial ELSE a.refa_cdsetorcomercial END, CASE WHEN (a.refa_nnquadra IS NULL) THEN b.refa_nnquadra ELSE a.refa_nnquadra END, CASE WHEN (a.iper_id IS NULL) THEN b.iper_id ELSE a.iper_id END, CASE WHEN (a.last_id IS NULL) THEN b.last_id ELSE a.last_id END, CASE WHEN (a.lest_id IS NULL) THEN b.lest_id ELSE a.lest_id END, CASE WHEN (a.catg_id IS NULL) THEN b.catg_id ELSE a.catg_id END, CASE WHEN (a.scat_id IS NULL) THEN b.scat_id ELSE a.scat_id END, CASE WHEN (a.epod_id IS NULL) THEN b.epod_id ELSE a.epod_id END, CASE WHEN (a.cltp_id IS NULL) THEN b.cltp_id ELSE a.cltp_id END, CASE WHEN (a.lapf_id IS NULL) THEN b.lapf_id ELSE a.lapf_id END, CASE WHEN (a.lepf_id IS NULL) THEN b.lepf_id ELSE a.lepf_id END, CASE WHEN (a.crog_id IS NULL) THEN b.crog_id ELSE a.crog_id END, CASE WHEN (a.lict_id IS NULL) THEN b.lict_id ELSE a.lict_id END, CASE WHEN (a.dotp_id IS NULL) THEN b.dotp_id ELSE a.dotp_id END, CASE WHEN (a.fntp_id IS NULL) THEN b.fntp_id ELSE a.fntp_id END, CASE WHEN (a.dbtp_id IS NULL) THEN b.dbtp_id ELSE a.dbtp_id END, CASE WHEN (a.crti_id IS NULL) THEN b.crti_id ELSE a.crti_id END;


ALTER TABLE faturamento.vw_un_resumo_faturamento_ind OWNER TO gsan_admin;

SET search_path = financeiro, pg_catalog;

--
-- TOC entry 401 (class 1259 OID 3674288)
-- Name: g_financiamento_tipo; Type: TABLE; Schema: financeiro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_financiamento_tipo (
    fntp_id integer NOT NULL,
    fntp_dsfinanciamentotipo character varying(40),
    fntp_dsabreviado character varying(10),
    fntp_icuso smallint,
    fntp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    fntp_icinclusao smallint DEFAULT 2 NOT NULL
);


ALTER TABLE financeiro.g_financiamento_tipo OWNER TO gsan_admin;

--
-- TOC entry 402 (class 1259 OID 3674295)
-- Name: g_financiamento_tipo_x; Type: TABLE; Schema: financeiro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_financiamento_tipo_x (
    fntp_id integer NOT NULL,
    fntp_dsfinanciamentotipo character varying(40),
    fntp_dsabreviado character varying(10),
    fntp_icuso smallint,
    fntp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    fntp_icinclusao smallint DEFAULT 2 NOT NULL,
    fntp_qtd integer
);


ALTER TABLE financeiro.g_financiamento_tipo_x OWNER TO gsan_admin;

--
-- TOC entry 403 (class 1259 OID 3674302)
-- Name: g_lancamento_item; Type: TABLE; Schema: financeiro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_lancamento_item (
    lcit_id integer NOT NULL,
    lcit_dsitemlancamento character varying(40),
    lcit_dsabreviado character varying(10),
    lcit_icitemcontabil smallint,
    lcit_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE financeiro.g_lancamento_item OWNER TO gsan_admin;

--
-- TOC entry 404 (class 1259 OID 3674306)
-- Name: g_lancamento_item_x; Type: TABLE; Schema: financeiro; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_lancamento_item_x (
    lcit_id integer NOT NULL,
    lcit_dsitemlancamento character varying(40),
    lcit_dsabreviado character varying(10),
    lcit_icitemcontabil smallint,
    lcit_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    lcit_qtd integer
);


ALTER TABLE financeiro.g_lancamento_item_x OWNER TO gsan_admin;

--
-- TOC entry 405 (class 1259 OID 3674312)
-- Name: vw_financiamento_tipo; Type: VIEW; Schema: financeiro; Owner: gsan_admin
--

CREATE VIEW vw_financiamento_tipo AS
    SELECT table1.fntp_id, table1.fntp_dsfinanciamentotipo, table1.fntp_dsabreviado, table1.fntp_icuso, table1.fntp_tmultimaalteracao, table1.fntp_icinclusao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select * from financeiro.financiamento_tipo'::text) table1(fntp_id integer, fntp_dsfinanciamentotipo character varying(40), fntp_dsabreviado character varying(10), fntp_icuso smallint, fntp_tmultimaalteracao timestamp without time zone, fntp_icinclusao smallint);


ALTER TABLE financeiro.vw_financiamento_tipo OWNER TO gsan_admin;

--
-- TOC entry 406 (class 1259 OID 3674318)
-- Name: vw_lancamento_item; Type: VIEW; Schema: financeiro; Owner: gsan_admin
--

CREATE VIEW vw_lancamento_item AS
    SELECT table1.lcit_id, table1.lcit_dsitemlancamento, table1.lcit_dsabreviado, table1.lcit_icitemcontabil, table1.lcit_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select * from financeiro.lancamento_item'::text) table1(lcit_id integer, lcit_dsitemlancamento character varying(40), lcit_dsabreviado character varying(10), lcit_icitemcontabil smallint, lcit_tmultimaalteracao timestamp without time zone);


ALTER TABLE financeiro.vw_lancamento_item OWNER TO gsan_admin;

--
-- TOC entry 407 (class 1259 OID 3674322)
-- Name: vw_lancamento_item_contabil; Type: VIEW; Schema: financeiro; Owner: gsan_admin
--

CREATE VIEW vw_lancamento_item_contabil AS
    SELECT table1.lict_id, table1.lict_dsitemlancamentocontabil, table1.lict_dsabreviado, table1.lict_nnsequenciaimpressao, table1.lict_tmultimaalteracao, table1.lcit_id, table1.lict_icuso FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select * from financeiro.lancamento_item_contabil'::text) table1(lict_id integer, lict_dsitemlancamentocontabil character varying(35), lict_dsabreviado character(5), lict_nnsequenciaimpressao smallint, lict_tmultimaalteracao timestamp without time zone, lcit_id integer, lict_icuso smallint);


ALTER TABLE financeiro.vw_lancamento_item_contabil OWNER TO gsan_admin;

SET search_path = micromedicao, pg_catalog;

--
-- TOC entry 408 (class 1259 OID 3674328)
-- Name: g_consumo_tipo; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_consumo_tipo (
    cstp_id integer NOT NULL,
    cstp_dsconsumotipo character varying(20),
    cstp_dsabreviadaconsumotipo character varying(10),
    cstp_icuso smallint,
    cstp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.g_consumo_tipo OWNER TO gsan_admin;

--
-- TOC entry 409 (class 1259 OID 3674334)
-- Name: g_consumo_tipo_x; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_consumo_tipo_x (
    cstp_id integer NOT NULL,
    cstp_dsconsumotipo character varying(20),
    cstp_dsabreviadaconsumotipo character varying(10),
    cstp_icuso smallint,
    cstp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    cstp_qtd integer
);


ALTER TABLE micromedicao.g_consumo_tipo_x OWNER TO gsan_admin;

--
-- TOC entry 410 (class 1259 OID 3674338)
-- Name: g_hidr_classe_metrlg; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_hidr_classe_metrlg (
    hicm_id integer NOT NULL,
    hicm_dshidrclassemetrologica character varying(20),
    hicm_dsabrvhidmtclassemetl character(5),
    hicm_icuso smallint,
    hicm_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.g_hidr_classe_metrlg OWNER TO gsan_admin;

--
-- TOC entry 411 (class 1259 OID 3674344)
-- Name: g_hidr_local_armaz; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_hidr_local_armaz (
    hila_id integer NOT NULL,
    hila_dshidrlocalarmazenagem character varying(45),
    hila_dsabrvhidmtlocalarmz character(5),
    hila_icuso smallint,
    hila_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.g_hidr_local_armaz OWNER TO gsan_admin;

--
-- TOC entry 412 (class 1259 OID 3674350)
-- Name: g_hidr_motivo_baixa; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_hidr_motivo_baixa (
    himb_id integer NOT NULL,
    himb_dshidrometromotivobaixa character varying(20),
    himb_icuso smallint,
    himb_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.g_hidr_motivo_baixa OWNER TO gsan_admin;

--
-- TOC entry 413 (class 1259 OID 3674354)
-- Name: g_hidrometro_capacidade; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_hidrometro_capacidade (
    hicp_id integer NOT NULL,
    hicp_dshidrometrocapacidade character varying(20),
    hicp_dsabreviadahidrcapacidade character(6),
    hicp_nndigitosleituraminimo smallint,
    hicp_nndigitosleituramaximo smallint,
    hicp_icuso smallint,
    hicp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    hicp_nnordem smallint
);


ALTER TABLE micromedicao.g_hidrometro_capacidade OWNER TO gsan_admin;

--
-- TOC entry 414 (class 1259 OID 3674360)
-- Name: g_hidrometro_capacidade_x; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_hidrometro_capacidade_x (
    hicp_id integer NOT NULL,
    hicp_dshidrometrocapacidade character varying(20),
    hicp_dsabreviadahidrometrocapacidade character(6),
    hicp_nndigitosleituraminimo smallint,
    hicp_nndigitosleituramaximo smallint,
    hicp_icuso smallint,
    hicp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    hicp_nnordem smallint,
    hicp_qtd integer
);


ALTER TABLE micromedicao.g_hidrometro_capacidade_x OWNER TO gsan_admin;

--
-- TOC entry 415 (class 1259 OID 3674366)
-- Name: g_hidrometro_diametro; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_hidrometro_diametro (
    hidm_id integer NOT NULL,
    hidm_dshidrometrodiametro character varying(20) NOT NULL,
    hidm_dsabreviadahidrdiametro character(5) NOT NULL,
    hidm_icuso smallint,
    hidm_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    hidm_nnordem smallint
);


ALTER TABLE micromedicao.g_hidrometro_diametro OWNER TO gsan_admin;

--
-- TOC entry 416 (class 1259 OID 3674370)
-- Name: g_hidrometro_marca; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_hidrometro_marca (
    himc_id integer NOT NULL,
    himc_dshidrometromarca character varying(30) NOT NULL,
    himc_dsabreviadahidrmarca character(3) NOT NULL,
    himc_nndiarevisao smallint NOT NULL,
    himc_icuso smallint,
    himc_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.g_hidrometro_marca OWNER TO gsan_admin;

--
-- TOC entry 417 (class 1259 OID 3674376)
-- Name: g_hidrometro_situacao; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_hidrometro_situacao (
    hist_id integer NOT NULL,
    hist_dshidrometrosituacao character varying(20),
    hist_icuso smallint,
    hist_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.g_hidrometro_situacao OWNER TO gsan_admin;

--
-- TOC entry 418 (class 1259 OID 3674382)
-- Name: g_hidrometro_tipo; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_hidrometro_tipo (
    hitp_id integer NOT NULL,
    hitp_dshidrometrotipo character varying(20) NOT NULL,
    hitp_dcabreviadahidrometrotipo character(5) NOT NULL,
    hitp_icuso smallint,
    hitp_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.g_hidrometro_tipo OWNER TO gsan_admin;

--
-- TOC entry 419 (class 1259 OID 3674386)
-- Name: g_leitura_anormalidade; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_leitura_anormalidade (
    ltan_id integer NOT NULL,
    ltan_dsleituraanormalidade character varying(25) NOT NULL,
    ltan_dsabreviadaleituraanormalidade character(5),
    ltan_icrelativohidrometro smallint NOT NULL,
    ltan_icimovelsemhidrometro smallint,
    ltan_icusosistema smallint,
    ltan_icemissaoordemservico smallint NOT NULL,
    ltan_icuso smallint,
    ltan_icperdatarifasocial smallint,
    ltan_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.g_leitura_anormalidade OWNER TO gsan_admin;

--
-- TOC entry 420 (class 1259 OID 3674392)
-- Name: g_leitura_anormalidade_consumo; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_leitura_anormalidade_consumo (
    lacs_id integer NOT NULL,
    lacs_dsconsumoacobrar character varying(30),
    lacs_icsemleitura smallint,
    lacs_iccomleitura smallint,
    lacs_icuso smallint,
    lacs_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.g_leitura_anormalidade_consumo OWNER TO gsan_admin;

--
-- TOC entry 421 (class 1259 OID 3674398)
-- Name: g_leitura_anormalidade_leitura; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_leitura_anormalidade_leitura (
    lalt_id integer NOT NULL,
    lalt_dsleituraafaturar character varying(30),
    lalt_icsemleitura smallint,
    lalt_iccomleitura smallint,
    lalt_icuso smallint,
    lalt_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.g_leitura_anormalidade_leitura OWNER TO gsan_admin;

--
-- TOC entry 422 (class 1259 OID 3674402)
-- Name: g_leitura_situacao; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_leitura_situacao (
    ltst_id integer NOT NULL,
    ltst_dsleiturasituacao character varying(30),
    ltst_icuso smallint,
    ltst_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.g_leitura_situacao OWNER TO gsan_admin;

--
-- TOC entry 423 (class 1259 OID 3674408)
-- Name: g_medicao_tipo; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_medicao_tipo (
    medt_id integer NOT NULL,
    medt_dsmedicaotipo character varying(25),
    medt_icuso smallint,
    medt_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.g_medicao_tipo OWNER TO gsan_admin;

--
-- TOC entry 424 (class 1259 OID 3674414)
-- Name: g_rota; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_rota (
    rota_id integer NOT NULL,
    rota_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    stcm_id integer NOT NULL,
    rota_cdrota smallint NOT NULL,
    ftgr_id integer
);


ALTER TABLE micromedicao.g_rota OWNER TO gsan_admin;

--
-- TOC entry 5479 (class 0 OID 0)
-- Dependencies: 424
-- Name: COLUMN g_rota.ftgr_id; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN g_rota.ftgr_id IS 'id do grupo de faturamento';


--
-- TOC entry 425 (class 1259 OID 3674420)
-- Name: g_rota_x; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_rota_x (
    rota_id integer NOT NULL,
    rota_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    stcm_id integer NOT NULL,
    rota_cdrota smallint NOT NULL,
    ftgr_id integer,
    rota_qtd integer
);


ALTER TABLE micromedicao.g_rota_x OWNER TO gsan_admin;

--
-- TOC entry 5481 (class 0 OID 0)
-- Dependencies: 425
-- Name: COLUMN g_rota_x.ftgr_id; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN g_rota_x.ftgr_id IS 'id do grupo de faturamento';


--
-- TOC entry 426 (class 1259 OID 3674426)
-- Name: seq_un_res_ins_hidr; Type: SEQUENCE; Schema: micromedicao; Owner: gsan_admin
--

CREATE SEQUENCE seq_un_res_ins_hidr
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE micromedicao.seq_un_res_ins_hidr OWNER TO gsan_admin;

--
-- TOC entry 427 (class 1259 OID 3674430)
-- Name: seq_un_res_lt_anorm; Type: SEQUENCE; Schema: micromedicao; Owner: gsan_admin
--

CREATE SEQUENCE seq_un_res_lt_anorm
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE micromedicao.seq_un_res_lt_anorm OWNER TO gsan_admin;

--
-- TOC entry 428 (class 1259 OID 3674434)
-- Name: seq_un_resi_des_mmd; Type: SEQUENCE; Schema: micromedicao; Owner: gsan_admin
--

CREATE SEQUENCE seq_un_resi_des_mmd
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE micromedicao.seq_un_resi_des_mmd OWNER TO gsan_admin;

--
-- TOC entry 429 (class 1259 OID 3674438)
-- Name: seq_un_resumo_coleta_esgoto; Type: SEQUENCE; Schema: micromedicao; Owner: gsan_admin
--

CREATE SEQUENCE seq_un_resumo_coleta_esgoto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE micromedicao.seq_un_resumo_coleta_esgoto OWNER TO gsan_admin;

--
-- TOC entry 430 (class 1259 OID 3674442)
-- Name: seq_un_resumo_consumo_agua; Type: SEQUENCE; Schema: micromedicao; Owner: gsan_admin
--

CREATE SEQUENCE seq_un_resumo_consumo_agua
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE micromedicao.seq_un_resumo_consumo_agua OWNER TO gsan_admin;

--
-- TOC entry 431 (class 1259 OID 3674446)
-- Name: seq_un_resumo_hidrometro; Type: SEQUENCE; Schema: micromedicao; Owner: gsan_admin
--

CREATE SEQUENCE seq_un_resumo_hidrometro
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE micromedicao.seq_un_resumo_hidrometro OWNER TO gsan_admin;

--
-- TOC entry 432 (class 1259 OID 3674450)
-- Name: sequence_un_resumo_indicador_desempenho_micromedicao_ref_2007; Type: SEQUENCE; Schema: micromedicao; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2007
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE micromedicao.sequence_un_resumo_indicador_desempenho_micromedicao_ref_2007 OWNER TO gsan_admin;

--
-- TOC entry 433 (class 1259 OID 3674454)
-- Name: sequence_un_resumo_indicador_desempenho_micromedicao_ref_2008; Type: SEQUENCE; Schema: micromedicao; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2008
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE micromedicao.sequence_un_resumo_indicador_desempenho_micromedicao_ref_2008 OWNER TO gsan_admin;

--
-- TOC entry 434 (class 1259 OID 3674458)
-- Name: sequence_un_resumo_indicador_desempenho_micromedicao_ref_2009; Type: SEQUENCE; Schema: micromedicao; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2009
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE micromedicao.sequence_un_resumo_indicador_desempenho_micromedicao_ref_2009 OWNER TO gsan_admin;

--
-- TOC entry 435 (class 1259 OID 3674462)
-- Name: sequence_un_resumo_metas; Type: SEQUENCE; Schema: micromedicao; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_metas
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE micromedicao.sequence_un_resumo_metas OWNER TO gsan_admin;

--
-- TOC entry 436 (class 1259 OID 3674466)
-- Name: sequence_un_resumo_metas_acumulado; Type: SEQUENCE; Schema: micromedicao; Owner: gsan_admin
--

CREATE SEQUENCE sequence_un_resumo_metas_acumulado
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE micromedicao.sequence_un_resumo_metas_acumulado OWNER TO gsan_admin;

--
-- TOC entry 437 (class 1259 OID 3674470)
-- Name: un_res_ins_hidr; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_res_ins_hidr (
    reih_id integer NOT NULL,
    reih_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    reih_cdsetorcomercial integer NOT NULL,
    epod_id integer NOT NULL,
    reih_nnquadra integer NOT NULL,
    cltp_id integer NOT NULL,
    rota_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    reih_qthidr_instalado_ramal integer NOT NULL,
    reih_qthidr_substituido_ramal integer NOT NULL,
    reih_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    reih_qthidr_remanejado_ramal integer NOT NULL,
    reih_qthidr_retirado_ramal integer NOT NULL,
    reih_qthidr_dadosatualizados integer,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    stcm_id integer NOT NULL,
    qdra_id integer NOT NULL,
    reih_qthidr_instalado_poco integer NOT NULL,
    reih_qthidr_substituido_poco integer NOT NULL,
    reih_qthidr_retirado_poco integer NOT NULL,
    reih_qthidr_remanejado_poco integer NOT NULL,
    reih_qthidratualinstaladoramal integer,
    reih_qthidratualinstaladospoco integer,
    reih_cdrota integer,
    cstf_id integer,
    himc_id integer,
    hitp_id integer,
    hicp_id integer,
    hidm_id integer,
    hicm_id integer
);


ALTER TABLE micromedicao.un_res_ins_hidr OWNER TO gsan_admin;

--
-- TOC entry 5494 (class 0 OID 0)
-- Dependencies: 437
-- Name: COLUMN un_res_ins_hidr.cstf_id; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN un_res_ins_hidr.cstf_id IS 'Consumo Tarifa';


--
-- TOC entry 5495 (class 0 OID 0)
-- Dependencies: 437
-- Name: COLUMN un_res_ins_hidr.himc_id; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN un_res_ins_hidr.himc_id IS 'Marca do Hidrometro';


--
-- TOC entry 5496 (class 0 OID 0)
-- Dependencies: 437
-- Name: COLUMN un_res_ins_hidr.hitp_id; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN un_res_ins_hidr.hitp_id IS 'Tipo do Hidrometro';


--
-- TOC entry 5497 (class 0 OID 0)
-- Dependencies: 437
-- Name: COLUMN un_res_ins_hidr.hicp_id; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN un_res_ins_hidr.hicp_id IS 'Capacidade do Hidrometro';


--
-- TOC entry 5498 (class 0 OID 0)
-- Dependencies: 437
-- Name: COLUMN un_res_ins_hidr.hidm_id; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN un_res_ins_hidr.hidm_id IS 'Diametro do Hidrometro';


--
-- TOC entry 5499 (class 0 OID 0)
-- Dependencies: 437
-- Name: COLUMN un_res_ins_hidr.hicm_id; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN un_res_ins_hidr.hicm_id IS 'Classe do Hidrometro';


--
-- TOC entry 438 (class 1259 OID 3674478)
-- Name: un_res_lt_anorm; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_res_lt_anorm (
    relt_id integer NOT NULL,
    uneg_id integer NOT NULL,
    relt_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    qdra_id integer NOT NULL,
    relt_cdsetorcomercial integer NOT NULL,
    relt_nnquadra integer NOT NULL,
    iper_id integer NOT NULL,
    rota_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    medt_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    relt_qtleituras integer NOT NULL,
    ltan_id integer NOT NULL,
    relt_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    empr_id integer,
    ltst_id integer,
    cstf_id integer NOT NULL,
    ftgr_id integer NOT NULL,
    relt_cdrota integer,
    ltan_idanormalidadeinformada integer,
    relt_qtleituraseanorminformada integer
);


ALTER TABLE micromedicao.un_res_lt_anorm OWNER TO gsan_admin;

--
-- TOC entry 5501 (class 0 OID 0)
-- Dependencies: 438
-- Name: COLUMN un_res_lt_anorm.ltan_idanormalidadeinformada; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN un_res_lt_anorm.ltan_idanormalidadeinformada IS 'Acumula os cÃ³digos de anormalidades informadas pelo leiturista';


--
-- TOC entry 5502 (class 0 OID 0)
-- Dependencies: 438
-- Name: COLUMN un_res_lt_anorm.relt_qtleituraseanorminformada; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN un_res_lt_anorm.relt_qtleituraseanorminformada IS 'Acumula a quantidade de anormalidades e leituras informadas pelos leituristas';


--
-- TOC entry 439 (class 1259 OID 3674486)
-- Name: un_res_mt_acum; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_res_mt_acum (
    rema_id integer NOT NULL,
    rema_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    rota_id integer NOT NULL,
    qdra_id integer NOT NULL,
    rema_cdsetorcomercial integer NOT NULL,
    rema_nnquadra integer NOT NULL,
    iper_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    rema_cdgruposubcat integer,
    rema_qtligacoescadastradas integer NOT NULL,
    rema_qtligacoescortadas integer NOT NULL,
    rema_qtligacoessuprimidas integer NOT NULL,
    rema_qtligacoesativas integer NOT NULL,
    rema_qtligacoesativasdebito3m integer NOT NULL,
    rema_qtligacoesconsumomedido integer NOT NULL,
    rema_qtligacoesconsumonaomed integer NOT NULL,
    rema_qtligacoesconsumoate5m3 integer NOT NULL,
    rema_qtligacoesconsumomedia integer NOT NULL,
    rema_qteconomias integer NOT NULL,
    rema_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.un_res_mt_acum OWNER TO gsan_admin;

--
-- TOC entry 440 (class 1259 OID 3674490)
-- Name: un_resi_des_mmd; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resi_des_mmd (
    reca_id integer DEFAULT nextval('seq_un_resi_des_mmd'::regclass) NOT NULL,
    reca_amreferencia integer NOT NULL,
    reca_anoreferencia integer NOT NULL,
    reca_mesreferencia character(6) NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    qdra_id integer NOT NULL,
    rota_id integer NOT NULL,
    reca_cdsetorcomercial integer NOT NULL,
    reca_nnquadra integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    reca_qtligacoes_ativas integer NOT NULL,
    reca_qtligacoes_com_hidrometro integer NOT NULL,
    reca_qtligacoes_com_med_real integer NOT NULL,
    reca_qtlig_com_hidr_e_med_est integer NOT NULL,
    reca_qteconomias_ativas integer NOT NULL,
    reca_qteconomias_com_hidr integer NOT NULL,
    reca_qteconomias_com_med_real integer NOT NULL,
    reca_qtecon_com_hidr_e_med_est integer NOT NULL,
    reca_consumoagua_ativas integer NOT NULL,
    reca_consumoagua_com_hidr integer NOT NULL,
    reca_consumoagua_com_med_real integer NOT NULL,
    reca_cons_ag_com_hidr_med_est integer NOT NULL,
    reca_vofaturadoagua integer NOT NULL,
    reca_vofaturadoaguamedido integer NOT NULL,
    reca_vofaturadoaguanaomedido integer NOT NULL,
    rece_qtligacoes integer NOT NULL,
    rece_qteconomias integer NOT NULL,
    rece_voesgoto integer NOT NULL,
    rece_vofaturadoesgoto integer NOT NULL,
    rece_vofaturadoesgotomedido integer NOT NULL,
    rece_vofaturadoesgotonaomedido integer NOT NULL,
    relt_qtvisitas_realizadas integer NOT NULL,
    relt_qtleituras_efetuadas integer NOT NULL,
    relt_qtleituras_com_anorm_hidr integer NOT NULL,
    reih_qthidr_instalado_ramal integer NOT NULL,
    reih_qthidr_substituido_ramal integer NOT NULL,
    reih_qthidr_remanejado_ramal integer NOT NULL,
    reih_qthidr_retirado_ramal integer NOT NULL,
    reih_qthidratualinstaladoramal integer NOT NULL,
    reih_qthidr_instalado_poco integer NOT NULL,
    reih_qthidr_substituido_poco integer NOT NULL,
    reih_qthidr_remanejado_poco integer NOT NULL,
    reih_qthidr_retirado_poco integer NOT NULL,
    reih_qthidratualinstaladospoco integer NOT NULL,
    reih_qthidr_dadosatualizados integer NOT NULL,
    reca_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.un_resi_des_mmd OWNER TO gsan_admin;

--
-- TOC entry 441 (class 1259 OID 3674498)
-- Name: un_resumo_analitico; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_analitico (
    rena_cdmatriculaimovel integer,
    ltan_id integer,
    rena_nmendereco character varying(100),
    rena_cdimovelinscricao character varying(21),
    rena_nnleiturahidrometro integer,
    rena_ammovimento integer,
    greg_id integer,
    uneg_id integer,
    rena_dshidrometrolocalinst character varying(20),
    rena_nnhidrometro character(10),
    rena_nmusuario character varying(50)
);


ALTER TABLE micromedicao.un_resumo_analitico OWNER TO gsan_admin;

--
-- TOC entry 442 (class 1259 OID 3674503)
-- Name: un_resumo_coleta_esgoto; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_coleta_esgoto (
    rece_id integer NOT NULL,
    uneg_id integer NOT NULL,
    rece_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    qdra_id integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    rota_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    rece_qteconomias integer NOT NULL,
    lepf_id integer NOT NULL,
    rece_voesgoto integer NOT NULL,
    rece_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    rece_qtligacoes integer NOT NULL,
    rece_voexcedente integer NOT NULL,
    cstp_id integer NOT NULL,
    rece_cdsetorcomercial integer NOT NULL,
    rece_nnquadra integer NOT NULL,
    rece_icmedidoagua smallint NOT NULL,
    rece_icpoco smallint NOT NULL,
    rece_icmedidofontealternativa smallint NOT NULL,
    rece_icvlexcedente smallint NOT NULL,
    rece_pcesgoto numeric(5,2),
    rece_pccoleta numeric(5,2),
    rece_icvlminimoesgoto smallint NOT NULL,
    rece_vofaturado integer,
    rece_ichidrometro smallint,
    rece_cdrota integer,
    rece_icfaturamento smallint,
    ftst_id integer,
    ftsm_id integer
);


ALTER TABLE micromedicao.un_resumo_coleta_esgoto OWNER TO gsan_admin;

--
-- TOC entry 5507 (class 0 OID 0)
-- Dependencies: 442
-- Name: COLUMN un_resumo_coleta_esgoto.rece_icfaturamento; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_coleta_esgoto.rece_icfaturamento IS 'Indicador de Faturamento';


--
-- TOC entry 5508 (class 0 OID 0)
-- Dependencies: 442
-- Name: COLUMN un_resumo_coleta_esgoto.ftst_id; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_coleta_esgoto.ftst_id IS 'Tipo da Situação de Faturamento';


--
-- TOC entry 5509 (class 0 OID 0)
-- Dependencies: 442
-- Name: COLUMN un_resumo_coleta_esgoto.ftsm_id; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_coleta_esgoto.ftsm_id IS 'Motivo da Situação de Faturamento';


--
-- TOC entry 443 (class 1259 OID 3674510)
-- Name: un_resumo_consumo_agua; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_consumo_agua (
    reca_id integer NOT NULL,
    uneg_id integer NOT NULL,
    reca_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    qdra_id integer NOT NULL,
    reca_cdsetorcomercial integer NOT NULL,
    rota_id integer NOT NULL,
    reca_nnquadra integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    reca_qteconomias integer NOT NULL,
    lepf_id integer NOT NULL,
    reca_consumoagua integer NOT NULL,
    reca_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    reca_consumoexcedente integer NOT NULL,
    reca_qtligacoes integer NOT NULL,
    iper_id integer NOT NULL,
    cstp_id integer NOT NULL,
    reca_icvolumeexcedente smallint NOT NULL,
    reca_voconsumofaturado integer,
    reca_ichidrometro smallint,
    reca_cdrota integer,
    reca_icligacaofaturada smallint DEFAULT 1 NOT NULL
);


ALTER TABLE micromedicao.un_resumo_consumo_agua OWNER TO gsan_admin;

--
-- TOC entry 5511 (class 0 OID 0)
-- Dependencies: 443
-- Name: COLUMN un_resumo_consumo_agua.reca_icligacaofaturada; Type: COMMENT; Schema: micromedicao; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_consumo_agua.reca_icligacaofaturada IS ' Indicador para saber se a ligação e faturada ou nao:1 para Faturada,2 para Não faturada';


--
-- TOC entry 444 (class 1259 OID 3674517)
-- Name: un_resumo_hidrometro; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_hidrometro (
    rehi_id integer NOT NULL,
    rehi_amreferencia integer NOT NULL,
    hila_id integer,
    hitp_id integer NOT NULL,
    hist_id integer NOT NULL,
    rehi_nnanofabricacao smallint NOT NULL,
    himc_id integer NOT NULL,
    hidm_id integer NOT NULL,
    hicp_id integer NOT NULL,
    rehi_icmacro smallint,
    rehi_qthidrometro integer NOT NULL,
    rehi_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    himb_id integer,
    hicm_id integer
);


ALTER TABLE micromedicao.un_resumo_hidrometro OWNER TO gsan_admin;

--
-- TOC entry 445 (class 1259 OID 3674523)
-- Name: un_resumo_indicador_desempenho_micromedicao_ref_2007; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_indicador_desempenho_micromedicao_ref_2007 (
    reca_id integer DEFAULT nextval('sequence_un_resumo_indicador_desempenho_micromedicao_ref_2007'::regclass) NOT NULL,
    reca_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    reca_cdsetorcomercial integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    reca_qtligacoes_ativas integer NOT NULL,
    reca_qtligacoes_com_hidrometro integer NOT NULL,
    reca_qtligacoes_com_medicao_real integer NOT NULL,
    reca_qtligacoes_com_hidrometro_e_medicao_estimada integer NOT NULL,
    reca_qteconomias_ativas integer NOT NULL,
    reca_qteconomias_com_hidrometro integer NOT NULL,
    reca_qteconomias_com_medicao_real integer NOT NULL,
    reca_qteconomias_com_hidrometro_e_medicao_estimada integer NOT NULL,
    reca_consumoagua_ativas integer NOT NULL,
    reca_consumoagua_com_hidrometro integer NOT NULL,
    reca_consumoagua_com_medicao_real integer NOT NULL,
    reca_consumoagua_com_hidrometro_e_medicao_estimada integer NOT NULL,
    reca_vofaturadoagua integer NOT NULL,
    reca_vofaturadoaguamedido integer NOT NULL,
    reca_vofaturadoaguanaomedido integer NOT NULL,
    rece_qtligacoes integer NOT NULL,
    rece_qteconomias integer NOT NULL,
    rece_voesgoto integer NOT NULL,
    rece_vofaturadoesgoto integer NOT NULL,
    rece_vofaturadoesgotomedido integer NOT NULL,
    rece_vofaturadoesgotonaomedido integer NOT NULL,
    relt_qtvisitas_realizadas integer NOT NULL,
    relt_qtleituras_efetuadas integer NOT NULL,
    relt_qtleituras_com_anormalidade_hidrometro integer NOT NULL,
    reih_qthidrometro_instalado_ramal integer NOT NULL,
    reih_qthidrometro_substituido_ramal integer NOT NULL,
    reih_qthidrometro_remanejado_ramal integer NOT NULL,
    reih_qthidrometro_retirado_ramal integer NOT NULL,
    reih_qthidrometrosatualinstaladosramal integer NOT NULL,
    reih_qthidrometro_instalado_poco integer NOT NULL,
    reih_qthidrometro_substituido_poco integer NOT NULL,
    reih_qthidrometro_remanejado_poco integer NOT NULL,
    reih_qthidrometro_retirado_poco integer NOT NULL,
    reih_qthidrometrosatualinstaladospoco integer NOT NULL,
    reih_qthidrometro_dadosatualizados integer NOT NULL,
    reca_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    scat_id integer NOT NULL,
    qdra_id integer NOT NULL,
    rota_id integer NOT NULL
);


ALTER TABLE micromedicao.un_resumo_indicador_desempenho_micromedicao_ref_2007 OWNER TO gsan_admin;

--
-- TOC entry 446 (class 1259 OID 3674530)
-- Name: un_resumo_indicador_desempenho_micromedicao_ref_2008; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_indicador_desempenho_micromedicao_ref_2008 (
    reca_id integer DEFAULT nextval('sequence_un_resumo_indicador_desempenho_micromedicao_ref_2008'::regclass) NOT NULL,
    reca_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    reca_cdsetorcomercial integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    reca_qtligacoes_ativas integer NOT NULL,
    reca_qtligacoes_com_hidrometro integer NOT NULL,
    reca_qtligacoes_com_medicao_real integer NOT NULL,
    reca_qtligacoes_com_hidrometro_e_medicao_estimada integer NOT NULL,
    reca_qteconomias_ativas integer NOT NULL,
    reca_qteconomias_com_hidrometro integer NOT NULL,
    reca_qteconomias_com_medicao_real integer NOT NULL,
    reca_qteconomias_com_hidrometro_e_medicao_estimada integer NOT NULL,
    reca_consumoagua_ativas integer NOT NULL,
    reca_consumoagua_com_hidrometro integer NOT NULL,
    reca_consumoagua_com_medicao_real integer NOT NULL,
    reca_consumoagua_com_hidrometro_e_medicao_estimada integer NOT NULL,
    reca_vofaturadoagua integer NOT NULL,
    reca_vofaturadoaguamedido integer NOT NULL,
    reca_vofaturadoaguanaomedido integer NOT NULL,
    rece_qtligacoes integer NOT NULL,
    rece_qteconomias integer NOT NULL,
    rece_voesgoto integer NOT NULL,
    rece_vofaturadoesgoto integer NOT NULL,
    rece_vofaturadoesgotomedido integer NOT NULL,
    rece_vofaturadoesgotonaomedido integer NOT NULL,
    relt_qtvisitas_realizadas integer NOT NULL,
    relt_qtleituras_efetuadas integer NOT NULL,
    relt_qtleituras_com_anormalidade_hidrometro integer NOT NULL,
    reih_qthidrometro_instalado_ramal integer NOT NULL,
    reih_qthidrometro_substituido_ramal integer NOT NULL,
    reih_qthidrometro_remanejado_ramal integer NOT NULL,
    reih_qthidrometro_retirado_ramal integer NOT NULL,
    reih_qthidrometrosatualinstaladosramal integer NOT NULL,
    reih_qthidrometro_instalado_poco integer NOT NULL,
    reih_qthidrometro_substituido_poco integer NOT NULL,
    reih_qthidrometro_remanejado_poco integer NOT NULL,
    reih_qthidrometro_retirado_poco integer NOT NULL,
    reih_qthidrometrosatualinstaladospoco integer NOT NULL,
    reih_qthidrometro_dadosatualizados integer NOT NULL,
    reca_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    scat_id integer NOT NULL,
    qdra_id integer NOT NULL,
    rota_id integer NOT NULL
);


ALTER TABLE micromedicao.un_resumo_indicador_desempenho_micromedicao_ref_2008 OWNER TO gsan_admin;

--
-- TOC entry 447 (class 1259 OID 3674535)
-- Name: un_resumo_indicador_desempenho_micromedicao_ref_2009; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_indicador_desempenho_micromedicao_ref_2009 (
    reca_id integer DEFAULT nextval('sequence_un_resumo_indicador_desempenho_micromedicao_ref_2009'::regclass) NOT NULL,
    reca_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    reca_cdsetorcomercial integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    reca_qtligacoes_ativas integer NOT NULL,
    reca_qtligacoes_com_hidrometro integer NOT NULL,
    reca_qtligacoes_com_medicao_real integer NOT NULL,
    reca_qtligacoes_com_hidrometro_e_medicao_estimada integer NOT NULL,
    reca_qteconomias_ativas integer NOT NULL,
    reca_qteconomias_com_hidrometro integer NOT NULL,
    reca_qteconomias_com_medicao_real integer NOT NULL,
    reca_qteconomias_com_hidrometro_e_medicao_estimada integer NOT NULL,
    reca_consumoagua_ativas integer NOT NULL,
    reca_consumoagua_com_hidrometro integer NOT NULL,
    reca_consumoagua_com_medicao_real integer NOT NULL,
    reca_consumoagua_com_hidrometro_e_medicao_estimada integer NOT NULL,
    reca_vofaturadoagua integer NOT NULL,
    reca_vofaturadoaguamedido integer NOT NULL,
    reca_vofaturadoaguanaomedido integer NOT NULL,
    rece_qtligacoes integer NOT NULL,
    rece_qteconomias integer NOT NULL,
    rece_voesgoto integer NOT NULL,
    rece_vofaturadoesgoto integer NOT NULL,
    rece_vofaturadoesgotomedido integer NOT NULL,
    rece_vofaturadoesgotonaomedido integer NOT NULL,
    relt_qtvisitas_realizadas integer NOT NULL,
    relt_qtleituras_efetuadas integer NOT NULL,
    relt_qtleituras_com_anormalidade_hidrometro integer NOT NULL,
    reih_qthidrometro_instalado_ramal integer NOT NULL,
    reih_qthidrometro_substituido_ramal integer NOT NULL,
    reih_qthidrometro_remanejado_ramal integer NOT NULL,
    reih_qthidrometro_retirado_ramal integer NOT NULL,
    reih_qthidrometrosatualinstaladosramal integer NOT NULL,
    reih_qthidrometro_instalado_poco integer NOT NULL,
    reih_qthidrometro_substituido_poco integer NOT NULL,
    reih_qthidrometro_remanejado_poco integer NOT NULL,
    reih_qthidrometro_retirado_poco integer NOT NULL,
    reih_qthidrometrosatualinstaladospoco integer NOT NULL,
    reih_qthidrometro_dadosatualizados integer NOT NULL,
    reca_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    scat_id integer NOT NULL,
    qdra_id integer NOT NULL,
    rota_id integer NOT NULL
);


ALTER TABLE micromedicao.un_resumo_indicador_desempenho_micromedicao_ref_2009 OWNER TO gsan_admin;

--
-- TOC entry 448 (class 1259 OID 3674542)
-- Name: un_resumo_metas; Type: TABLE; Schema: micromedicao; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_metas (
    remt_id integer NOT NULL,
    remt_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    rota_id integer NOT NULL,
    qdra_id integer NOT NULL,
    remt_cdsetorcomercial integer NOT NULL,
    remt_nnquadra integer NOT NULL,
    iper_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    remt_cdgruposubcat integer,
    remt_qtligacoescadastradas integer NOT NULL,
    remt_qtligacoescortadas integer NOT NULL,
    remt_qtligacoessuprimidas integer NOT NULL,
    remt_qtligacoesativas integer NOT NULL,
    remt_qtligacoesativasdebito3m integer NOT NULL,
    remt_qtligacoesconsumomedido integer NOT NULL,
    remt_qtligacoesconsumonaomed integer NOT NULL,
    remt_qtligacoesconsumoate5m3 integer NOT NULL,
    remt_qtligacoesconsumomedia integer NOT NULL,
    remt_qteconomias integer NOT NULL,
    remt_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE micromedicao.un_resumo_metas OWNER TO gsan_admin;

--
-- TOC entry 449 (class 1259 OID 3674548)
-- Name: vw_consumo_tipo; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_consumo_tipo AS
    SELECT table1.cstp_id, table1.cstp_dsconsumotipo, table1.cstp_dsabreviadaconsumotipo, table1.cstp_icuso, table1.cstp_tmultimaalteracao, table1.cstp_iccalculomedia FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.consumo_tipo'::text) table1(cstp_id integer, cstp_dsconsumotipo character varying(20), cstp_dsabreviadaconsumotipo character varying(10), cstp_icuso smallint, cstp_tmultimaalteracao timestamp without time zone, cstp_iccalculomedia smallint);


ALTER TABLE micromedicao.vw_consumo_tipo OWNER TO gsan_admin;

--
-- TOC entry 450 (class 1259 OID 3674552)
-- Name: vw_hidrometro_capacidade; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_hidrometro_capacidade AS
    SELECT table1.hicp_id, table1.hicp_dshidrometrocapacidade, table1.hicp_dsabreviadahidrometrocapacidade, table1.hicp_nndigitosleituraminimo, table1.hicp_nndigitosleituramaximo, table1.hicp_icuso, table1.hicp_tmultimaalteracao, table1.hicp_nnordem, table1.hicp_cdhidrometrocapacidade FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.hidrometro_capacidade'::text) table1(hicp_id integer, hicp_dshidrometrocapacidade character varying(20), hicp_dsabreviadahidrometrocapacidade character(6), hicp_nndigitosleituraminimo smallint, hicp_nndigitosleituramaximo smallint, hicp_icuso smallint, hicp_tmultimaalteracao timestamp without time zone, hicp_nnordem smallint, hicp_cdhidrometrocapacidade character(1));


ALTER TABLE micromedicao.vw_hidrometro_capacidade OWNER TO gsan_admin;

--
-- TOC entry 451 (class 1259 OID 3674558)
-- Name: vw_hidrometro_classe_metrologica; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_hidrometro_classe_metrologica AS
    SELECT table1.hicm_id, table1.hicm_dshidrometroclassemetrologica, table1.hicm_dsabreviadahidrometroclassemetrologica, table1.hicm_icuso, table1.hicm_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.hidrometro_classe_metrologica '::text) table1(hicm_id integer, hicm_dshidrometroclassemetrologica character varying(20), hicm_dsabreviadahidrometroclassemetrologica character(5), hicm_icuso smallint, hicm_tmultimaalteracao timestamp without time zone);


ALTER TABLE micromedicao.vw_hidrometro_classe_metrologica OWNER TO gsan_admin;

--
-- TOC entry 452 (class 1259 OID 3674564)
-- Name: vw_hidrometro_diametro; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_hidrometro_diametro AS
    SELECT table1.hidm_id, table1.hidm_dshidrometrodiametro, table1.hidm_dsabreviadahidrometrodiametro, table1.hidm_icuso, table1.hidm_tmultimaalteracao, table1.hidm_nnordem FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.hidrometro_diametro'::text) table1(hidm_id integer, hidm_dshidrometrodiametro character varying(20), hidm_dsabreviadahidrometrodiametro character(5), hidm_icuso smallint, hidm_tmultimaalteracao timestamp without time zone, hidm_nnordem smallint);


ALTER TABLE micromedicao.vw_hidrometro_diametro OWNER TO gsan_admin;

--
-- TOC entry 453 (class 1259 OID 3674568)
-- Name: vw_hidrometro_local_armazenagem; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_hidrometro_local_armazenagem AS
    SELECT table1.hila_id, table1.hila_dshidrometrolocalarmazenagem, table1.hila_dsabreviadahidrometrolocalarmazenagem, table1.hila_icuso, table1.hila_tmultimaalteracao, table1.hila_icoficina FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.hidrometro_local_armazenagem'::text) table1(hila_id integer, hila_dshidrometrolocalarmazenagem character varying(45), hila_dsabreviadahidrometrolocalarmazenagem character(5), hila_icuso smallint, hila_tmultimaalteracao timestamp without time zone, hila_icoficina smallint);


ALTER TABLE micromedicao.vw_hidrometro_local_armazenagem OWNER TO gsan_admin;

--
-- TOC entry 454 (class 1259 OID 3674574)
-- Name: vw_hidrometro_marca; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_hidrometro_marca AS
    SELECT table1.himc_id, table1.himc_dshidrometromarca, table1.himc_dsabreviadahidrometromarca, table1.himc_nndiarevisao, table1.himc_icuso, table1.himc_tmultimaalteracao, table1.himc_cdhidrometromarca FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.hidrometro_marca'::text) table1(himc_id integer, himc_dshidrometromarca character varying(30), himc_dsabreviadahidrometromarca character(3), himc_nndiarevisao smallint, himc_icuso smallint, himc_tmultimaalteracao timestamp without time zone, himc_cdhidrometromarca character(1));


ALTER TABLE micromedicao.vw_hidrometro_marca OWNER TO gsan_admin;

--
-- TOC entry 455 (class 1259 OID 3674580)
-- Name: vw_hidrometro_motivo_baixa; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_hidrometro_motivo_baixa AS
    SELECT table1.himb_id, table1.himb_dshidrometromotivobaixa, table1.himb_icuso, table1.himb_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.hidrometro_motivo_baixa '::text) table1(himb_id integer, himb_dshidrometromotivobaixa character varying(20), himb_icuso smallint, himb_tmultimaalteracao timestamp without time zone);


ALTER TABLE micromedicao.vw_hidrometro_motivo_baixa OWNER TO gsan_admin;

--
-- TOC entry 456 (class 1259 OID 3674584)
-- Name: vw_hidrometro_situacao; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_hidrometro_situacao AS
    SELECT table1.hist_id, table1.hist_dshidrometrosituacao, table1.hist_icuso, table1.hist_tmultimaalteracao, table1.hist_ichidrometroextraviado FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.hidrometro_situacao'::text) table1(hist_id integer, hist_dshidrometrosituacao character varying(20), hist_icuso smallint, hist_tmultimaalteracao timestamp without time zone, hist_ichidrometroextraviado smallint);


ALTER TABLE micromedicao.vw_hidrometro_situacao OWNER TO gsan_admin;

--
-- TOC entry 457 (class 1259 OID 3674590)
-- Name: vw_hidrometro_tipo; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_hidrometro_tipo AS
    SELECT table1.hitp_id, table1.hitp_dshidrometrotipo, table1.hitp_dcabreviadahidrometrotipo, table1.hitp_icuso, table1.hitp_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.hidrometro_tipo'::text) table1(hitp_id integer, hitp_dshidrometrotipo character varying(20), hitp_dcabreviadahidrometrotipo character(5), hitp_icuso smallint, hitp_tmultimaalteracao timestamp without time zone);


ALTER TABLE micromedicao.vw_hidrometro_tipo OWNER TO gsan_admin;

--
-- TOC entry 458 (class 1259 OID 3674596)
-- Name: vw_leitura_anormalidade; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_leitura_anormalidade AS
    SELECT table1.ltan_id, table1.ltan_dsleituraanormalidade, table1.ltan_dcabreviadaleituraanormalidade, table1.ltan_icrelativohidrometro, table1.ltan_icimovelsemhidrometro, table1.ltan_icusosistema, table1.ltan_icemissaoordemservico, table1.ltan_icuso, table1.lacs_idconsumoacobrarsemleitura, table1.lacs_idconsumoacobrarcomleitura, table1.lalt_idleituraafaturarsemleitura, table1.lalt_idleituraafaturarcomleitura, table1.ltan_icperdatarifasocial, table1.ltan_tmultimaalteracao, table1.svtp_id, table1.ltan_nnfatorsemleitura, table1.ltan_nnfatorcomleitura, table1.ltan_icleitura, table1.ltan_dsabreviadaleituraanormalidade FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from 
micromedicao.leitura_anormalidade'::text) table1(ltan_id integer, ltan_dsleituraanormalidade character varying(25), ltan_dcabreviadaleituraanormalidade character(5), ltan_icrelativohidrometro smallint, ltan_icimovelsemhidrometro smallint, ltan_icusosistema smallint, ltan_icemissaoordemservico smallint, ltan_icuso smallint, lacs_idconsumoacobrarsemleitura integer, lacs_idconsumoacobrarcomleitura integer, lalt_idleituraafaturarsemleitura integer, lalt_idleituraafaturarcomleitura integer, ltan_icperdatarifasocial smallint, ltan_tmultimaalteracao timestamp without time zone, svtp_id integer, ltan_nnfatorsemleitura numeric(3,2), ltan_nnfatorcomleitura numeric(3,2), ltan_icleitura smallint, ltan_dsabreviadaleituraanormalidade character(5));


ALTER TABLE micromedicao.vw_leitura_anormalidade OWNER TO gsan_admin;

--
-- TOC entry 459 (class 1259 OID 3674601)
-- Name: vw_leitura_anormalidade_consumo; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_leitura_anormalidade_consumo AS
    SELECT table1.lacs_id, table1.lacs_dsconsumoacobrar, table1.lacs_icsemleitura, table1.lacs_iccomleitura, table1.lacs_icuso, table1.lacs_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.leitura_anormalidade_consumo'::text) table1(lacs_id integer, lacs_dsconsumoacobrar character varying(30), lacs_icsemleitura smallint, lacs_iccomleitura smallint, lacs_icuso smallint, lacs_tmultimaalteracao timestamp without time zone);


ALTER TABLE micromedicao.vw_leitura_anormalidade_consumo OWNER TO gsan_admin;

--
-- TOC entry 460 (class 1259 OID 3674607)
-- Name: vw_leitura_anormalidade_informada; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_leitura_anormalidade_informada AS
    SELECT g_leitura_anormalidade.ltan_id AS ltan_idanormalidadeinformada, g_leitura_anormalidade.ltan_dsleituraanormalidade, g_leitura_anormalidade.ltan_dsabreviadaleituraanormalidade, g_leitura_anormalidade.ltan_icrelativohidrometro, g_leitura_anormalidade.ltan_icimovelsemhidrometro, g_leitura_anormalidade.ltan_icusosistema, g_leitura_anormalidade.ltan_icemissaoordemservico, g_leitura_anormalidade.ltan_icuso, g_leitura_anormalidade.ltan_icperdatarifasocial, g_leitura_anormalidade.ltan_tmultimaalteracao FROM g_leitura_anormalidade;


ALTER TABLE micromedicao.vw_leitura_anormalidade_informada OWNER TO gsan_admin;

--
-- TOC entry 461 (class 1259 OID 3674613)
-- Name: vw_leitura_anormalidade_leitura; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_leitura_anormalidade_leitura AS
    SELECT table1.lalt_id, table1.lalt_dsleituraafaturar, table1.lalt_icsemleitura, table1.lalt_iccomleitura, table1.lalt_icuso, table1.lalt_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.leitura_anormalidade_leitura'::text) table1(lalt_id integer, lalt_dsleituraafaturar character varying(30), lalt_icsemleitura smallint, lalt_iccomleitura smallint, lalt_icuso smallint, lalt_tmultimaalteracao timestamp without time zone);


ALTER TABLE micromedicao.vw_leitura_anormalidade_leitura OWNER TO gsan_admin;

--
-- TOC entry 462 (class 1259 OID 3674617)
-- Name: vw_leitura_situacao; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_leitura_situacao AS
    SELECT table1.ltst_id, table1.ltst_dsleiturasituacao, table1.ltst_icuso, table1.ltst_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 
user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.leitura_situacao'::text) table1(ltst_id integer, ltst_dsleiturasituacao character varying(30), ltst_icuso smallint, ltst_tmultimaalteracao timestamp without time zone);


ALTER TABLE micromedicao.vw_leitura_situacao OWNER TO gsan_admin;

--
-- TOC entry 463 (class 1259 OID 3674623)
-- Name: vw_medicao_tipo; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_medicao_tipo AS
    SELECT table1.medt_id, table1.medt_dsmedicaotipo, table1.medt_icuso, table1.medt_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.medicao_tipo'::text) table1(medt_id integer, medt_dsmedicaotipo character varying(25), medt_icuso smallint, medt_tmultimaalteracao timestamp without time zone);


ALTER TABLE micromedicao.vw_medicao_tipo OWNER TO gsan_admin;

--
-- TOC entry 464 (class 1259 OID 3674629)
-- Name: vw_rota; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_rota AS
    SELECT table1.rota_id, table1.lttp_id, table1.empr_id, table1.rota_icconsumoajuste, table1.rota_icleiturafiscalizacaocortado, table1.rota_icleiturafiscalizacaosuprimido, table1.rota_icfaixafalsageracao, table1.rota_pcfaixafalsageracao, table1.rota_icleiturafiscalizacaogeracao, table1.rota_pcleiturafiscalizacaogeracao, table1.rota_icuso, table1.rota_tmultimaalteracao, table1.ftgr_id, table1.stcm_id, table1.rota_dtleituraajuste, table1.cbgr_id, table1.rota_cdrota, table1.empr_idcobranca, table1.leit_id, table1.rota_nnsequencialeiturista, table1.empr_identregacontas, table1.rota_icalternativa, table1.rota_ictransmissaooffline, table1.ftgr_idoriginal, table1.rota_nnlimiteimoveis, table1.rota_icseqleitura, table1.rota_nndiasconsumoajuste FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1 user=gsan_olap password= ol@p port=5432'::text, 'select * from micromedicao.rota'::text) table1(rota_id integer, lttp_id integer, empr_id integer, rota_icconsumoajuste smallint, rota_icleiturafiscalizacaocortado smallint, rota_icleiturafiscalizacaosuprimido smallint, rota_icfaixafalsageracao smallint, rota_pcfaixafalsageracao numeric(5,2), rota_icleiturafiscalizacaogeracao smallint, rota_pcleiturafiscalizacaogeracao numeric(5,2), rota_icuso smallint, rota_tmultimaalteracao timestamp without time zone, ftgr_id integer, stcm_id integer, rota_dtleituraajuste date, cbgr_id integer, rota_cdrota smallint, empr_idcobranca integer, leit_id integer, rota_nnsequencialeiturista integer, empr_identregacontas integer, rota_icalternativa smallint, rota_ictransmissaooffline smallint, ftgr_idoriginal integer, rota_nnlimiteimoveis integer, rota_icseqleitura integer, rota_nndiasconsumoajuste integer);


ALTER TABLE micromedicao.vw_rota OWNER TO gsan_admin;

--
-- TOC entry 465 (class 1259 OID 3674634)
-- Name: vw_un_resumo_indicador_coleta_esgoto; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_indicador_coleta_esgoto AS
    SELECT a.rece_amreferencia, substr((a.rece_amreferencia)::text, 1, 4) AS rece_anoreferencia, CASE WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END AS rece_mesreferencia, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.rece_cdsetorcomercial, a.rece_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id, sum(a.rece_qtligacoes) AS rece_qtligacoes, sum(a.rece_qteconomias) AS rece_qteconomias, sum(a.rece_voesgoto) AS rece_voesgoto, sum(a.rece_vofaturado) AS rece_vofaturadoesgoto, sum(CASE WHEN (a.cstp_id <> ALL (ARRAY[5, 7])) THEN a.rece_vofaturado ELSE 0 END) AS rece_vofaturadoesgotomedido, sum(CASE WHEN (a.cstp_id = ANY (ARRAY[5, 7])) THEN a.rece_vofaturado ELSE 0 END) AS rece_vofaturadoesgotonaomedido FROM un_resumo_coleta_esgoto a GROUP BY a.rece_amreferencia, substr((a.rece_amreferencia)::text, 1, 4), CASE WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((a.rece_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.rece_cdsetorcomercial, a.rece_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id;


ALTER TABLE micromedicao.vw_un_resumo_indicador_coleta_esgoto OWNER TO gsan_admin;

--
-- TOC entry 466 (class 1259 OID 3674641)
-- Name: vw_un_resumo_indicador_consumo_agua; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_indicador_consumo_agua AS
    SELECT a.reca_amreferencia, substr((a.reca_amreferencia)::text, 1, 4) AS reca_anoreferencia, CASE WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END AS reca_mesreferencia, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.reca_cdsetorcomercial, a.reca_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id, sum(a.reca_qtligacoes) AS reca_qtligacoes_ativas, sum(CASE WHEN (a.reca_ichidrometro = 1) THEN a.reca_qtligacoes ELSE 0 END) AS reca_qtligacoes_com_hidrometro, sum(CASE WHEN (a.cstp_id = 1) THEN a.reca_qtligacoes ELSE 0 END) AS reca_qtligacoes_com_medicao_real, sum(CASE WHEN ((a.reca_ichidrometro = 1) AND (a.cstp_id <> 1)) THEN a.reca_qtligacoes ELSE 0 END) AS reca_qtligacoes_com_hidrometro_e_medicao_estimada, sum(a.reca_qteconomias) AS reca_qteconomias_ativas, sum(CASE WHEN (a.reca_ichidrometro = 1) THEN a.reca_qteconomias ELSE 0 END) AS reca_qteconomias_com_hidrometro, sum(CASE WHEN (a.cstp_id = 1) THEN a.reca_qteconomias ELSE 0 END) AS reca_qteconomias_com_medicao_real, sum(CASE WHEN ((a.reca_ichidrometro = 1) AND (a.cstp_id <> 1)) THEN a.reca_qteconomias ELSE 0 END) AS reca_qteconomias_com_hidrometro_e_medicao_estimada, sum(a.reca_consumoagua) AS reca_consumoagua_ativas, sum(CASE WHEN (a.reca_ichidrometro = 1) THEN a.reca_consumoagua ELSE 0 END) AS reca_consumoagua_com_hidrometro, sum(CASE WHEN (a.cstp_id = 1) THEN a.reca_consumoagua ELSE 0 END) AS reca_consumoagua_com_medicao_real, sum(CASE WHEN ((a.reca_ichidrometro = 1) AND (a.cstp_id <> 1)) THEN a.reca_consumoagua ELSE 0 END) AS reca_consumoagua_com_hidrometro_e_medicao_estimada, sum(a.reca_voconsumofaturado) AS reca_vofaturadoagua, sum(CASE WHEN (a.cstp_id <> ALL (ARRAY[5, 7])) THEN a.reca_voconsumofaturado ELSE 0 END) AS reca_vofaturadoaguamedido, sum(CASE WHEN (a.cstp_id = ANY (ARRAY[5, 7])) THEN a.reca_voconsumofaturado ELSE 0 END) AS reca_vofaturadoaguanaomedido FROM un_resumo_consumo_agua a GROUP BY a.reca_amreferencia, substr((a.reca_amreferencia)::text, 1, 4), CASE WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((a.reca_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.reca_cdsetorcomercial, a.reca_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id;


ALTER TABLE micromedicao.vw_un_resumo_indicador_consumo_agua OWNER TO gsan_admin;

--
-- TOC entry 467 (class 1259 OID 3674648)
-- Name: vw_un_resumo_indicador_agua_esgoto; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_indicador_agua_esgoto AS
    SELECT CASE WHEN (a.reca_amreferencia IS NULL) THEN b.rece_amreferencia ELSE a.reca_amreferencia END AS reca_amreferencia, CASE WHEN (a.reca_amreferencia IS NULL) THEN b.rece_anoreferencia ELSE a.reca_anoreferencia END AS reca_anoreferencia, CASE WHEN (a.reca_amreferencia IS NULL) THEN b.rece_mesreferencia ELSE a.reca_mesreferencia END AS reca_mesreferencia, CASE WHEN (a.greg_id IS NULL) THEN b.greg_id ELSE a.greg_id END AS greg_id, CASE WHEN (a.uneg_id IS NULL) THEN b.uneg_id ELSE a.uneg_id END AS uneg_id, CASE WHEN (a.loca_id IS NULL) THEN b.loca_id ELSE a.loca_id END AS loca_id, CASE WHEN (a.loca_cdelo IS NULL) THEN b.loca_cdelo ELSE a.loca_cdelo END AS loca_cdelo, CASE WHEN (a.stcm_id IS NULL) THEN b.stcm_id ELSE a.stcm_id END AS stcm_id, CASE WHEN (a.qdra_id IS NULL) THEN b.qdra_id ELSE a.qdra_id END AS qdra_id, CASE WHEN (a.rota_id IS NULL) THEN b.rota_id ELSE a.rota_id END AS rota_id, CASE WHEN (a.reca_cdsetorcomercial IS NULL) THEN b.rece_cdsetorcomercial ELSE a.reca_cdsetorcomercial END AS reca_cdsetorcomercial, CASE WHEN (a.reca_nnquadra IS NULL) THEN b.rece_nnquadra ELSE a.reca_nnquadra END AS reca_nnquadra, CASE WHEN (a.iper_id IS NULL) THEN b.iper_id ELSE a.iper_id END AS iper_id, CASE WHEN (a.last_id IS NULL) THEN b.last_id ELSE a.last_id END AS last_id, CASE WHEN (a.lest_id IS NULL) THEN b.lest_id ELSE a.lest_id END AS lest_id, CASE WHEN (a.catg_id IS NULL) THEN b.catg_id ELSE a.catg_id END AS catg_id, CASE WHEN (a.scat_id IS NULL) THEN b.scat_id ELSE a.scat_id END AS scat_id, CASE WHEN (a.epod_id IS NULL) THEN b.epod_id ELSE a.epod_id END AS epod_id, CASE WHEN (a.cltp_id IS NULL) THEN b.cltp_id ELSE a.cltp_id END AS cltp_id, CASE WHEN (a.lapf_id IS NULL) THEN b.lapf_id ELSE a.lapf_id END AS lapf_id, CASE WHEN (a.lepf_id IS NULL) THEN b.lepf_id ELSE a.lepf_id END AS lepf_id, CASE WHEN (a.reca_qtligacoes_ativas IS NULL) THEN (0)::bigint ELSE a.reca_qtligacoes_ativas END AS reca_qtligacoes_ativas, CASE WHEN (a.reca_qtligacoes_com_hidrometro IS NULL) THEN (0)::bigint ELSE a.reca_qtligacoes_com_hidrometro END AS reca_qtligacoes_com_hidrometro, CASE WHEN (a.reca_qtligacoes_com_medicao_real IS NULL) THEN (0)::bigint ELSE a.reca_qtligacoes_com_medicao_real END AS reca_qtligacoes_com_medicao_real, CASE WHEN (a.reca_qtligacoes_com_hidrometro_e_medicao_estimada IS NULL) THEN (0)::bigint ELSE a.reca_qtligacoes_com_hidrometro_e_medicao_estimada END AS reca_qtligacoes_com_hidrometro_e_medicao_estimada, CASE WHEN (a.reca_qteconomias_ativas IS NULL) THEN (0)::bigint ELSE a.reca_qteconomias_ativas END AS reca_qteconomias_ativas, CASE WHEN (a.reca_qteconomias_com_hidrometro IS NULL) THEN (0)::bigint ELSE a.reca_qteconomias_com_hidrometro END AS reca_qteconomias_com_hidrometro, CASE WHEN (a.reca_qteconomias_com_medicao_real IS NULL) THEN (0)::bigint ELSE a.reca_qteconomias_com_medicao_real END AS reca_qteconomias_com_medicao_real, CASE WHEN (a.reca_qteconomias_com_hidrometro_e_medicao_estimada IS NULL) THEN (0)::bigint ELSE a.reca_qteconomias_com_hidrometro_e_medicao_estimada END AS reca_qteconomias_com_hidrometro_e_medicao_estimada, CASE WHEN (a.reca_consumoagua_ativas IS NULL) THEN (0)::bigint ELSE a.reca_consumoagua_ativas END AS reca_consumoagua_ativas, CASE WHEN (a.reca_consumoagua_com_hidrometro IS NULL) THEN (0)::bigint ELSE a.reca_consumoagua_com_hidrometro END AS reca_consumoagua_com_hidrometro, CASE WHEN (a.reca_consumoagua_com_medicao_real IS NULL) THEN (0)::bigint ELSE a.reca_consumoagua_com_medicao_real END AS reca_consumoagua_com_medicao_real, CASE WHEN (a.reca_consumoagua_com_hidrometro_e_medicao_estimada IS NULL) THEN (0)::bigint ELSE a.reca_consumoagua_com_hidrometro_e_medicao_estimada END AS reca_consumoagua_com_hidrometro_e_medicao_estimada, CASE WHEN (a.reca_vofaturadoagua IS NULL) THEN (0)::bigint ELSE a.reca_vofaturadoagua END AS reca_vofaturadoagua, CASE WHEN (a.reca_vofaturadoaguamedido IS NULL) THEN (0)::bigint ELSE a.reca_vofaturadoaguamedido END AS reca_vofaturadoaguamedido, CASE WHEN (a.reca_vofaturadoaguanaomedido IS NULL) THEN (0)::bigint ELSE a.reca_vofaturadoaguanaomedido END AS reca_vofaturadoaguanaomedido, CASE WHEN (b.rece_qtligacoes IS NULL) THEN (0)::bigint ELSE b.rece_qtligacoes END AS rece_qtligacoes, CASE WHEN (b.rece_qteconomias IS NULL) THEN (0)::bigint ELSE b.rece_qteconomias END AS rece_qteconomias, CASE WHEN (b.rece_voesgoto IS NULL) THEN (0)::bigint ELSE b.rece_voesgoto END AS rece_voesgoto, CASE WHEN (b.rece_vofaturadoesgoto IS NULL) THEN (0)::bigint ELSE b.rece_vofaturadoesgoto END AS rece_vofaturadoesgoto, CASE WHEN (b.rece_vofaturadoesgotomedido IS NULL) THEN (0)::bigint ELSE b.rece_vofaturadoesgotomedido END AS rece_vofaturadoesgotomedido, CASE WHEN (b.rece_vofaturadoesgotonaomedido IS NULL) THEN (0)::bigint ELSE b.rece_vofaturadoesgotonaomedido END AS rece_vofaturadoesgotonaomedido FROM (vw_un_resumo_indicador_consumo_agua a FULL JOIN vw_un_resumo_indicador_coleta_esgoto b ON ((((((((((((((((((((a.reca_amreferencia = b.rece_amreferencia) AND (a.greg_id = b.greg_id)) AND (a.uneg_id = b.uneg_id)) AND (a.loca_id = b.loca_id)) AND (a.loca_cdelo = b.loca_cdelo)) AND (a.stcm_id = b.stcm_id)) AND (a.qdra_id = b.qdra_id)) AND (a.rota_id = b.rota_id)) AND (a.reca_cdsetorcomercial = b.rece_cdsetorcomercial)) AND (a.reca_nnquadra = b.rece_nnquadra)) AND (a.iper_id = b.iper_id)) AND (a.last_id = b.last_id)) AND (a.lest_id = b.lest_id)) AND (a.catg_id = b.catg_id)) AND (a.scat_id = b.scat_id)) AND (a.epod_id = b.epod_id)) AND (a.cltp_id = b.cltp_id)) AND (a.lapf_id = b.lapf_id)) AND (a.lepf_id = b.lepf_id))));


ALTER TABLE micromedicao.vw_un_resumo_indicador_agua_esgoto OWNER TO gsan_admin;

--
-- TOC entry 468 (class 1259 OID 3674653)
-- Name: vw_un_resumo_indicador_hidrometro; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_indicador_hidrometro AS
    SELECT a.reih_amreferencia, substr((a.reih_amreferencia)::text, 1, 4) AS reih_anoreferencia, CASE WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END AS reih_mesreferencia, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.reih_cdsetorcomercial, a.reih_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id, sum(a.reih_qthidr_instalado_ramal) AS reih_qthidrometro_instalado_ramal, sum(a.reih_qthidr_substituido_ramal) AS reih_qthidrometro_substituido_ramal, sum(a.reih_qthidr_remanejado_ramal) AS reih_qthidrometro_remanejado_ramal, sum(a.reih_qthidr_retirado_ramal) AS reih_qthidrometro_retirado_ramal, sum(CASE WHEN (a.reih_qthidratualinstaladoramal IS NULL) THEN 0 ELSE a.reih_qthidratualinstaladoramal END) AS reih_qthidrometrosatualinstaladosramal, sum(a.reih_qthidr_instalado_poco) AS reih_qthidrometro_instalado_poco, sum(a.reih_qthidr_substituido_poco) AS reih_qthidrometro_substituido_poco, sum(a.reih_qthidr_remanejado_poco) AS reih_qthidrometro_remanejado_poco, sum(a.reih_qthidr_retirado_poco) AS reih_qthidrometro_retirado_poco, sum(CASE WHEN (a.reih_qthidratualinstaladospoco IS NULL) THEN 0 ELSE a.reih_qthidratualinstaladospoco END) AS reih_qthidrometrosatualinstaladospoco, sum(CASE WHEN (a.reih_qthidr_dadosatualizados IS NULL) THEN 0 ELSE a.reih_qthidr_dadosatualizados END) AS reih_qthidrometro_dadosatualizados FROM un_res_ins_hidr a GROUP BY a.reih_amreferencia, substr((a.reih_amreferencia)::text, 1, 4), CASE WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((a.reih_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.reih_cdsetorcomercial, a.reih_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id;


ALTER TABLE micromedicao.vw_un_resumo_indicador_hidrometro OWNER TO gsan_admin;

--
-- TOC entry 469 (class 1259 OID 3674660)
-- Name: vw_un_resumo_indicador_leitura_anormalidade; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_indicador_leitura_anormalidade AS
    SELECT a.relt_amreferencia, substr((a.relt_amreferencia)::text, 1, 4) AS relt_anoreferencia, CASE WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END AS relt_mesreferencia, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.relt_cdsetorcomercial, a.relt_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id, sum(CASE WHEN ((a.ltst_id = ANY (ARRAY[1, 3, 4])) OR (a.ltan_id <> 0)) THEN a.relt_qtleituras ELSE 0 END) AS relt_qtvisitas_realizadas, sum(CASE WHEN (a.ltst_id = ANY (ARRAY[1, 3, 4])) THEN a.relt_qtleituras ELSE 0 END) AS relt_qtleituras_efetuadas, sum(CASE WHEN ((a.ltan_id <> 0) AND (b.ltan_icrelativohidrometro = 1)) THEN a.relt_qtleituras ELSE 0 END) AS relt_qtleituras_com_anormalidade_hidrometro FROM (un_res_lt_anorm a LEFT JOIN g_leitura_anormalidade b ON ((a.ltan_id = b.ltan_id))) GROUP BY a.relt_amreferencia, substr((a.relt_amreferencia)::text, 1, 4), CASE WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((a.relt_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END, a.greg_id, a.uneg_id, a.loca_id, a.loca_cdelo, a.stcm_id, a.qdra_id, a.rota_id, a.relt_cdsetorcomercial, a.relt_nnquadra, a.iper_id, a.last_id, a.lest_id, a.catg_id, a.scat_id, a.epod_id, a.cltp_id, a.lapf_id, a.lepf_id;


ALTER TABLE micromedicao.vw_un_resumo_indicador_leitura_anormalidade OWNER TO gsan_admin;

--
-- TOC entry 470 (class 1259 OID 3674667)
-- Name: vw_un_resumo_indicador_anormalidade_hidrometro; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_indicador_anormalidade_hidrometro AS
    SELECT CASE WHEN (a.relt_amreferencia IS NULL) THEN b.reih_amreferencia ELSE a.relt_amreferencia END AS relt_amreferencia, CASE WHEN (a.relt_amreferencia IS NULL) THEN b.reih_anoreferencia ELSE a.relt_anoreferencia END AS relt_anoreferencia, CASE WHEN (a.relt_amreferencia IS NULL) THEN b.reih_mesreferencia ELSE a.relt_mesreferencia END AS relt_mesreferencia, CASE WHEN (a.greg_id IS NULL) THEN b.greg_id ELSE a.greg_id END AS greg_id, CASE WHEN (a.uneg_id IS NULL) THEN b.uneg_id ELSE a.uneg_id END AS uneg_id, CASE WHEN (a.loca_id IS NULL) THEN b.loca_id ELSE a.loca_id END AS loca_id, CASE WHEN (a.loca_cdelo IS NULL) THEN b.loca_cdelo ELSE a.loca_cdelo END AS loca_cdelo, CASE WHEN (a.stcm_id IS NULL) THEN b.stcm_id ELSE a.stcm_id END AS stcm_id, CASE WHEN (a.qdra_id IS NULL) THEN b.qdra_id ELSE a.qdra_id END AS qdra_id, CASE WHEN (a.rota_id IS NULL) THEN b.rota_id ELSE a.rota_id END AS rota_id, CASE WHEN (a.relt_cdsetorcomercial IS NULL) THEN b.reih_cdsetorcomercial ELSE a.relt_cdsetorcomercial END AS relt_cdsetorcomercial, CASE WHEN (a.relt_nnquadra IS NULL) THEN b.reih_nnquadra ELSE a.relt_nnquadra END AS relt_nnquadra, CASE WHEN (a.iper_id IS NULL) THEN b.iper_id ELSE a.iper_id END AS iper_id, CASE WHEN (a.last_id IS NULL) THEN b.last_id ELSE a.last_id END AS last_id, CASE WHEN (a.lest_id IS NULL) THEN b.lest_id ELSE a.lest_id END AS lest_id, CASE WHEN (a.catg_id IS NULL) THEN b.catg_id ELSE a.catg_id END AS catg_id, CASE WHEN (a.scat_id IS NULL) THEN b.scat_id ELSE a.scat_id END AS scat_id, CASE WHEN (a.epod_id IS NULL) THEN b.epod_id ELSE a.epod_id END AS epod_id, CASE WHEN (a.cltp_id IS NULL) THEN b.cltp_id ELSE a.cltp_id END AS cltp_id, CASE WHEN (a.lapf_id IS NULL) THEN b.lapf_id ELSE a.lapf_id END AS lapf_id, CASE WHEN (a.lepf_id IS NULL) THEN b.lepf_id ELSE a.lepf_id END AS lepf_id, CASE WHEN (a.relt_qtvisitas_realizadas IS NULL) THEN (0)::bigint ELSE a.relt_qtvisitas_realizadas END AS relt_qtvisitas_realizadas, CASE WHEN (a.relt_qtleituras_efetuadas IS NULL) THEN (0)::bigint ELSE a.relt_qtleituras_efetuadas END AS relt_qtleituras_efetuadas, CASE WHEN (a.relt_qtleituras_com_anormalidade_hidrometro IS NULL) THEN (0)::bigint ELSE a.relt_qtleituras_com_anormalidade_hidrometro END AS relt_qtleituras_com_anormalidade_hidrometro, CASE WHEN (b.reih_qthidrometro_instalado_ramal IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_instalado_ramal END AS reih_qthidrometro_instalado_ramal, CASE WHEN (b.reih_qthidrometro_substituido_ramal IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_substituido_ramal END AS reih_qthidrometro_substituido_ramal, CASE WHEN (b.reih_qthidrometro_remanejado_ramal IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_remanejado_ramal END AS reih_qthidrometro_remanejado_ramal, CASE WHEN (b.reih_qthidrometro_retirado_ramal IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_retirado_ramal END AS reih_qthidrometro_retirado_ramal, CASE WHEN (b.reih_qthidrometrosatualinstaladosramal IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometrosatualinstaladosramal END AS reih_qthidrometrosatualinstaladosramal, CASE WHEN (b.reih_qthidrometro_instalado_poco IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_instalado_poco END AS reih_qthidrometro_instalado_poco, CASE WHEN (b.reih_qthidrometro_substituido_poco IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_substituido_poco END AS reih_qthidrometro_substituido_poco, CASE WHEN (b.reih_qthidrometro_remanejado_poco IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_remanejado_poco END AS reih_qthidrometro_remanejado_poco, CASE WHEN (b.reih_qthidrometro_retirado_poco IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_retirado_poco END AS reih_qthidrometro_retirado_poco, CASE WHEN (b.reih_qthidrometrosatualinstaladospoco IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometrosatualinstaladospoco END AS reih_qthidrometrosatualinstaladospoco, CASE WHEN (b.reih_qthidrometro_dadosatualizados IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_dadosatualizados END AS reih_qthidrometro_dadosatualizados FROM (vw_un_resumo_indicador_leitura_anormalidade a FULL JOIN vw_un_resumo_indicador_hidrometro b ON ((((((((((((((((((((a.relt_amreferencia = b.reih_amreferencia) AND (a.greg_id = b.greg_id)) AND (a.uneg_id = b.uneg_id)) AND (a.loca_id = b.loca_id)) AND (a.loca_cdelo = b.loca_cdelo)) AND (a.stcm_id = b.stcm_id)) AND (a.qdra_id = b.qdra_id)) AND (a.rota_id = b.rota_id)) AND (a.relt_cdsetorcomercial = b.reih_cdsetorcomercial)) AND (a.relt_nnquadra = b.reih_nnquadra)) AND (a.iper_id = b.iper_id)) AND (a.last_id = b.last_id)) AND (a.lest_id = b.lest_id)) AND (a.catg_id = b.catg_id)) AND (a.scat_id = b.scat_id)) AND (a.epod_id = b.epod_id)) AND (a.cltp_id = b.cltp_id)) AND (a.lapf_id = b.lapf_id)) AND (a.lepf_id = b.lepf_id))));


ALTER TABLE micromedicao.vw_un_resumo_indicador_anormalidade_hidrometro OWNER TO gsan_admin;

--
-- TOC entry 471 (class 1259 OID 3674672)
-- Name: vw_un_resi_des_mmd; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_un_resi_des_mmd AS
    SELECT CASE WHEN (a.reca_amreferencia IS NULL) THEN b.relt_amreferencia ELSE a.reca_amreferencia END AS reca_amreferencia, CASE WHEN (a.reca_amreferencia IS NULL) THEN b.relt_anoreferencia ELSE a.reca_anoreferencia END AS reca_anoreferencia, CASE WHEN (a.reca_amreferencia IS NULL) THEN b.relt_mesreferencia ELSE a.reca_mesreferencia END AS reca_mesreferencia, CASE WHEN (a.greg_id IS NULL) THEN b.greg_id ELSE a.greg_id END AS greg_id, CASE WHEN (a.uneg_id IS NULL) THEN b.uneg_id ELSE a.uneg_id END AS uneg_id, CASE WHEN (a.loca_id IS NULL) THEN b.loca_id ELSE a.loca_id END AS loca_id, CASE WHEN (a.loca_cdelo IS NULL) THEN b.loca_cdelo ELSE a.loca_cdelo END AS loca_cdelo, CASE WHEN (a.stcm_id IS NULL) THEN b.stcm_id ELSE a.stcm_id END AS stcm_id, CASE WHEN (a.qdra_id IS NULL) THEN b.qdra_id ELSE a.qdra_id END AS qdra_id, CASE WHEN (a.rota_id IS NULL) THEN b.rota_id ELSE a.rota_id END AS rota_id, CASE WHEN (a.reca_cdsetorcomercial IS NULL) THEN b.relt_cdsetorcomercial ELSE a.reca_cdsetorcomercial END AS reca_cdsetorcomercial, CASE WHEN (a.reca_nnquadra IS NULL) THEN b.relt_nnquadra ELSE a.reca_nnquadra END AS reca_nnquadra, CASE WHEN (a.iper_id IS NULL) THEN b.iper_id ELSE a.iper_id END AS iper_id, CASE WHEN (a.last_id IS NULL) THEN b.last_id ELSE a.last_id END AS last_id, CASE WHEN (a.lest_id IS NULL) THEN b.lest_id ELSE a.lest_id END AS lest_id, CASE WHEN (a.catg_id IS NULL) THEN b.catg_id ELSE a.catg_id END AS catg_id, CASE WHEN (a.scat_id IS NULL) THEN b.scat_id ELSE a.scat_id END AS scat_id, CASE WHEN (a.epod_id IS NULL) THEN b.epod_id ELSE a.epod_id END AS epod_id, CASE WHEN (a.cltp_id IS NULL) THEN b.cltp_id ELSE a.cltp_id END AS cltp_id, CASE WHEN (a.lapf_id IS NULL) THEN b.lapf_id ELSE a.lapf_id END AS lapf_id, CASE WHEN (a.lepf_id IS NULL) THEN b.lepf_id ELSE a.lepf_id END AS lepf_id, CASE WHEN (a.reca_qtligacoes_ativas IS NULL) THEN (0)::bigint ELSE a.reca_qtligacoes_ativas END AS reca_qtligacoes_ativas, CASE WHEN (a.reca_qtligacoes_com_hidrometro IS NULL) THEN (0)::bigint ELSE a.reca_qtligacoes_com_hidrometro END AS reca_qtligacoes_com_hidrometro, CASE WHEN (a.reca_qtligacoes_com_medicao_real IS NULL) THEN (0)::bigint ELSE a.reca_qtligacoes_com_medicao_real END AS reca_qtligacoes_com_medicao_real, CASE WHEN (a.reca_qtligacoes_com_hidrometro_e_medicao_estimada IS NULL) THEN (0)::bigint ELSE a.reca_qtligacoes_com_hidrometro_e_medicao_estimada END AS reca_qtligacoes_com_hidrometro_e_medicao_estimada, CASE WHEN (a.reca_qteconomias_ativas IS NULL) THEN (0)::bigint ELSE a.reca_qteconomias_ativas END AS reca_qteconomias_ativas, CASE WHEN (a.reca_qteconomias_com_hidrometro IS NULL) THEN (0)::bigint ELSE a.reca_qteconomias_com_hidrometro END AS reca_qteconomias_com_hidrometro, CASE WHEN (a.reca_qteconomias_com_medicao_real IS NULL) THEN (0)::bigint ELSE a.reca_qteconomias_com_medicao_real END AS reca_qteconomias_com_medicao_real, CASE WHEN (a.reca_qteconomias_com_hidrometro_e_medicao_estimada IS NULL) THEN (0)::bigint ELSE a.reca_qteconomias_com_hidrometro_e_medicao_estimada END AS reca_qteconomias_com_hidrometro_e_medicao_estimada, CASE WHEN (a.reca_consumoagua_ativas IS NULL) THEN (0)::bigint ELSE a.reca_consumoagua_ativas END AS reca_consumoagua_ativas, CASE WHEN (a.reca_consumoagua_com_hidrometro IS NULL) THEN (0)::bigint ELSE a.reca_consumoagua_com_hidrometro END AS reca_consumoagua_com_hidrometro, CASE WHEN (a.reca_consumoagua_com_medicao_real IS NULL) THEN (0)::bigint ELSE a.reca_consumoagua_com_medicao_real END AS reca_consumoagua_com_medicao_real, CASE WHEN (a.reca_consumoagua_com_hidrometro_e_medicao_estimada IS NULL) THEN (0)::bigint ELSE a.reca_consumoagua_com_hidrometro_e_medicao_estimada END AS reca_consumoagua_com_hidrometro_e_medicao_estimada, CASE WHEN (a.reca_vofaturadoagua IS NULL) THEN (0)::bigint ELSE a.reca_vofaturadoagua END AS reca_vofaturadoagua, CASE WHEN (a.reca_vofaturadoaguamedido IS NULL) THEN (0)::bigint ELSE a.reca_vofaturadoaguamedido END AS reca_vofaturadoaguamedido, CASE WHEN (a.reca_vofaturadoaguanaomedido IS NULL) THEN (0)::bigint ELSE a.reca_vofaturadoaguanaomedido END AS reca_vofaturadoaguanaomedido, CASE WHEN (a.rece_qtligacoes IS NULL) THEN (0)::bigint ELSE a.rece_qtligacoes END AS rece_qtligacoes, CASE WHEN (a.rece_qteconomias IS NULL) THEN (0)::bigint ELSE a.rece_qteconomias END AS rece_qteconomias, CASE WHEN (a.rece_voesgoto IS NULL) THEN (0)::bigint ELSE a.rece_voesgoto END AS rece_voesgoto, CASE WHEN (a.rece_vofaturadoesgoto IS NULL) THEN (0)::bigint ELSE a.rece_vofaturadoesgoto END AS rece_vofaturadoesgoto, CASE WHEN (a.rece_vofaturadoesgotomedido IS NULL) THEN (0)::bigint ELSE a.rece_vofaturadoesgotomedido END AS rece_vofaturadoesgotomedido, CASE WHEN (a.rece_vofaturadoesgotonaomedido IS NULL) THEN (0)::bigint ELSE a.rece_vofaturadoesgotonaomedido END AS rece_vofaturadoesgotonaomedido, CASE WHEN (b.relt_qtvisitas_realizadas IS NULL) THEN (0)::bigint ELSE b.relt_qtvisitas_realizadas END AS relt_qtvisitas_realizadas, CASE WHEN (b.relt_qtleituras_efetuadas IS NULL) THEN (0)::bigint ELSE b.relt_qtleituras_efetuadas END AS relt_qtleituras_efetuadas, CASE WHEN (b.relt_qtleituras_com_anormalidade_hidrometro IS NULL) THEN (0)::bigint ELSE b.relt_qtleituras_com_anormalidade_hidrometro END AS relt_qtleituras_com_anormalidade_hidrometro, CASE WHEN (b.reih_qthidrometro_instalado_ramal IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_instalado_ramal END AS reih_qthidrometro_instalado_ramal, CASE WHEN (b.reih_qthidrometro_substituido_ramal IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_substituido_ramal END AS reih_qthidrometro_substituido_ramal, CASE WHEN (b.reih_qthidrometro_remanejado_ramal IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_remanejado_ramal END AS reih_qthidrometro_remanejado_ramal, CASE WHEN (b.reih_qthidrometro_retirado_ramal IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_retirado_ramal END AS reih_qthidrometro_retirado_ramal, CASE WHEN (b.reih_qthidrometrosatualinstaladosramal IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometrosatualinstaladosramal END AS reih_qthidrometrosatualinstaladosramal, CASE WHEN (b.reih_qthidrometro_instalado_poco IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_instalado_poco END AS reih_qthidrometro_instalado_poco, CASE WHEN (b.reih_qthidrometro_substituido_poco IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_substituido_poco END AS reih_qthidrometro_substituido_poco, CASE WHEN (b.reih_qthidrometro_remanejado_poco IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_remanejado_poco END AS reih_qthidrometro_remanejado_poco, CASE WHEN (b.reih_qthidrometro_retirado_poco IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_retirado_poco END AS reih_qthidrometro_retirado_poco, CASE WHEN (b.reih_qthidrometrosatualinstaladospoco IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometrosatualinstaladospoco END AS reih_qthidrometrosatualinstaladospoco, CASE WHEN (b.reih_qthidrometro_dadosatualizados IS NULL) THEN (0)::bigint ELSE b.reih_qthidrometro_dadosatualizados END AS reih_qthidrometro_dadosatualizados FROM (vw_un_resumo_indicador_agua_esgoto a FULL JOIN vw_un_resumo_indicador_anormalidade_hidrometro b ON ((((((((((((((((((((a.reca_amreferencia = b.relt_amreferencia) AND (a.greg_id = b.greg_id)) AND (a.uneg_id = b.uneg_id)) AND (a.loca_id = b.loca_id)) AND (a.loca_cdelo = b.loca_cdelo)) AND (a.stcm_id = b.stcm_id)) AND (a.qdra_id = b.qdra_id)) AND (a.rota_id = b.rota_id)) AND (a.reca_cdsetorcomercial = b.relt_cdsetorcomercial)) AND (a.reca_nnquadra = b.relt_nnquadra)) AND (a.iper_id = b.iper_id)) AND (a.last_id = b.last_id)) AND (a.lest_id = b.lest_id)) AND (a.catg_id = b.catg_id)) AND (a.scat_id = b.scat_id)) AND (a.epod_id = b.epod_id)) AND (a.cltp_id = b.cltp_id)) AND (a.lapf_id = b.lapf_id)) AND (a.lepf_id = b.lepf_id))));


ALTER TABLE micromedicao.vw_un_resi_des_mmd OWNER TO gsan_admin;

--
-- TOC entry 472 (class 1259 OID 3674679)
-- Name: vw_un_resumo_indicador_faturamento; Type: VIEW; Schema: micromedicao; Owner: gsan_admin
--

CREATE VIEW vw_un_resumo_indicador_faturamento AS
    SELECT b.refa_id, b.refa_amreferencia, substr((b.refa_amreferencia)::text, 1, 4) AS refa_anoreferencia, CASE WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '01'::text) THEN '01-Jan'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '02'::text) THEN '02-Fev'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '03'::text) THEN '03-Mar'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '04'::text) THEN '04-Abr'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '05'::text) THEN '05-Mai'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '06'::text) THEN '06-Jun'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '07'::text) THEN '07-Jul'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '08'::text) THEN '08-Ago'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '09'::text) THEN '09-Set'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '10'::text) THEN '10-Out'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '11'::text) THEN '11-Nov'::text WHEN (substr((b.refa_amreferencia)::text, 5, 2) = '12'::text) THEN '12-Dez'::text ELSE ''::text END AS refa_mesreferencia, b.greg_id, b.uneg_id, b.loca_id, b.loca_cdelo, b.stcm_id, b.qdra_id, b.rota_id, b.refa_cdsetorcomercial, b.refa_nnquadra, b.iper_id, b.last_id, b.lest_id, b.catg_id, b.scat_id, b.epod_id, b.cltp_id, b.lapf_id, b.lepf_id, b.refa_qtcontasemitidas, CASE WHEN (b.refa_ichidrometro = 1) THEN b.refa_qtcontasemitidas ELSE 0 END AS refa_qtcontasemitidascomhidrometro, CASE WHEN (b.refa_ichidrometro = 2) THEN b.refa_qtcontasemitidas ELSE 0 END AS refa_qtcontasemitidassemhidrometro, b.refa_qteconomiasfaturadas, CASE WHEN (b.refa_ichidrometro = 1) THEN b.refa_qteconomiasfaturadas ELSE 0 END AS refa_qteconomiasfaturadascomhidrometro, CASE WHEN (b.refa_ichidrometro = 2) THEN b.refa_qteconomiasfaturadas ELSE 0 END AS refa_qteconomiasfaturadassemhidrometro, b.refa_vofaturadoagua, CASE WHEN (b.refa_ichidrometro = 1) THEN b.refa_vofaturadoagua ELSE 0 END AS refa_vofaturadoaguamedido, CASE WHEN (b.refa_ichidrometro = 2) THEN b.refa_vofaturadoagua ELSE 0 END AS refa_vofaturadoaguanaomedido, b.refa_vofaturadoesgoto, CASE WHEN (b.refa_ichidrometro = 1) THEN b.refa_vofaturadoesgoto ELSE 0 END AS refa_vofaturadoesgotomedido, CASE WHEN (b.refa_ichidrometro = 2) THEN b.refa_vofaturadoesgoto ELSE 0 END AS refa_vofaturadoesgotonaomedido, b.refa_tmultimaalteracao FROM faturamento.un_resumo_faturamento b;


ALTER TABLE micromedicao.vw_un_resumo_indicador_faturamento OWNER TO gsan_admin;

SET search_path = myschema, pg_catalog;

--
-- TOC entry 473 (class 1259 OID 3674686)
-- Name: foo; Type: TABLE; Schema: myschema; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE foo (
    f1 integer NOT NULL,
    f2 text NOT NULL,
    f3 text[]
);


ALTER TABLE myschema.foo OWNER TO gsan_admin;

SET search_path = operacional, pg_catalog;

--
-- TOC entry 474 (class 1259 OID 3674694)
-- Name: g_distrito_operacional; Type: TABLE; Schema: operacional; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE g_distrito_operacional (
    diop_id integer NOT NULL,
    diop_dsdistritooperacional character varying(30),
    diop_dsabreviado character(3),
    diop_icuso smallint,
    diop_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE operacional.g_distrito_operacional OWNER TO gsan_admin;

--
-- TOC entry 5543 (class 0 OID 0)
-- Dependencies: 474
-- Name: COLUMN g_distrito_operacional.diop_id; Type: COMMENT; Schema: operacional; Owner: gsan_admin
--

COMMENT ON COLUMN g_distrito_operacional.diop_id IS 'id do distrito operacional';


--
-- TOC entry 5544 (class 0 OID 0)
-- Dependencies: 474
-- Name: COLUMN g_distrito_operacional.diop_dsdistritooperacional; Type: COMMENT; Schema: operacional; Owner: gsan_admin
--

COMMENT ON COLUMN g_distrito_operacional.diop_dsdistritooperacional IS 'descrição do distrito operacional ';


--
-- TOC entry 5545 (class 0 OID 0)
-- Dependencies: 474
-- Name: COLUMN g_distrito_operacional.diop_dsabreviado; Type: COMMENT; Schema: operacional; Owner: gsan_admin
--

COMMENT ON COLUMN g_distrito_operacional.diop_dsabreviado IS 'descricao abreviado';


--
-- TOC entry 5546 (class 0 OID 0)
-- Dependencies: 474
-- Name: COLUMN g_distrito_operacional.diop_icuso; Type: COMMENT; Schema: operacional; Owner: gsan_admin
--

COMMENT ON COLUMN g_distrito_operacional.diop_icuso IS 'indicador de uso';


--
-- TOC entry 5547 (class 0 OID 0)
-- Dependencies: 474
-- Name: COLUMN g_distrito_operacional.diop_tmultimaalteracao; Type: COMMENT; Schema: operacional; Owner: gsan_admin
--

COMMENT ON COLUMN g_distrito_operacional.diop_tmultimaalteracao IS 'ultima alteracao';


--
-- TOC entry 475 (class 1259 OID 3674702)
-- Name: vw_distrito_operacional; Type: VIEW; Schema: operacional; Owner: gsan_admin
--

CREATE VIEW vw_distrito_operacional AS
    SELECT table1.diop_id, table1.stab_id, table1.diop_dsdistritooperacional, table1.diop_dsabreviado, table1.diop_icuso, table1.zabs_id, table1.diop_tmultimaalteracao FROM public.dblink('dbname=gsan_comercial hostaddr=127.0.0.1
user=gsan_olap password= ol@p port=5432'::text, 'select * from operacional.distrito_operacional'::text) table1(diop_id integer, stab_id integer, diop_dsdistritooperacional character varying(30), diop_dsabreviado character(3), diop_icuso smallint, zabs_id integer, diop_tmultimaalteracao timestamp without time zone);


ALTER TABLE operacional.vw_distrito_operacional OWNER TO gsan_admin;

SET search_path = public, pg_catalog;

--
-- TOC entry 476 (class 1259 OID 3674708)
-- Name: foo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE foo (
    f1 integer NOT NULL,
    f2 text NOT NULL,
    f3 text[]
);


ALTER TABLE public.foo OWNER TO postgres;

--
-- TOC entry 477 (class 1259 OID 3674714)
-- Name: myremote_pg_proc; Type: VIEW; Schema: public; Owner: appvazamento
--

CREATE VIEW myremote_pg_proc AS
    SELECT t1.cd_rota FROM dblink('dbname=gsan_comercial
         hostaddr=10.20.100.21
                 user=postgres
                 password=C0sanpa2011.
                 port=5432'::text, 'SELECT rota_cdrota FROM micromedicao.rota;'::text) t1(cd_rota smallint);


ALTER TABLE public.myremote_pg_proc OWNER TO appvazamento;

SET default_with_oids = true;

--
-- TOC entry 478 (class 1259 OID 3674720)
-- Name: pg_ts_cfg; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pg_ts_cfg (
    ts_name text NOT NULL,
    prs_name text NOT NULL,
    locale text
);


ALTER TABLE public.pg_ts_cfg OWNER TO postgres;

--
-- TOC entry 479 (class 1259 OID 3674728)
-- Name: pg_ts_cfgmap; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pg_ts_cfgmap (
    ts_name text NOT NULL,
    tok_alias text NOT NULL,
    dict_name text[]
);


ALTER TABLE public.pg_ts_cfgmap OWNER TO postgres;

--
-- TOC entry 480 (class 1259 OID 3674734)
-- Name: pg_ts_dict; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pg_ts_dict (
    dict_name text NOT NULL,
    dict_init regprocedure,
    dict_initoption text,
    dict_lexize regprocedure NOT NULL,
    dict_comment text
);


ALTER TABLE public.pg_ts_dict OWNER TO postgres;

--
-- TOC entry 481 (class 1259 OID 3674742)
-- Name: pg_ts_parser; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pg_ts_parser (
    prs_name text NOT NULL,
    prs_start regprocedure NOT NULL,
    prs_nexttoken regprocedure NOT NULL,
    prs_end regprocedure NOT NULL,
    prs_headline regprocedure NOT NULL,
    prs_lextype regprocedure NOT NULL,
    prs_comment text
);


ALTER TABLE public.pg_ts_parser OWNER TO postgres;

SET default_with_oids = false;

--
-- TOC entry 482 (class 1259 OID 3674750)
-- Name: un_resumo_ligacao_economia; Type: TABLE; Schema: public; Owner: gsan_admin; Tablespace: 
--

CREATE TABLE un_resumo_ligacao_economia (
    rele_id integer NOT NULL,
    rele_amreferencia integer NOT NULL,
    greg_id integer NOT NULL,
    uneg_id integer NOT NULL,
    loca_id integer NOT NULL,
    loca_cdelo integer NOT NULL,
    stcm_id integer NOT NULL,
    qdra_id integer NOT NULL,
    rota_id integer NOT NULL,
    rele_cdsetorcomercial integer NOT NULL,
    rele_nnquadra integer NOT NULL,
    iper_id integer NOT NULL,
    last_id integer NOT NULL,
    lest_id integer NOT NULL,
    catg_id integer NOT NULL,
    scat_id integer NOT NULL,
    epod_id integer NOT NULL,
    cltp_id integer NOT NULL,
    lapf_id integer NOT NULL,
    lepf_id integer NOT NULL,
    rele_ichidrometro smallint NOT NULL,
    rele_icvolumefixadoagua smallint NOT NULL,
    rele_icvolumefixadoesgoto smallint NOT NULL,
    rele_icpoco smallint NOT NULL,
    rele_qtligacoes integer NOT NULL,
    rele_qteconomias integer NOT NULL,
    rele_tmultimaalteracao timestamp without time zone DEFAULT now() NOT NULL,
    rele_ichidrometropoco smallint NOT NULL,
    cstf_id integer DEFAULT 0,
    rele_cdrota integer,
    rele_qtligacoesnovasagua integer DEFAULT 0 NOT NULL,
    rele_qtligacoesnovasesgoto integer DEFAULT 0 NOT NULL,
    rele_qtligacoescortesmes integer,
    rele_qtligacoesreligadasmes integer
);


ALTER TABLE public.un_resumo_ligacao_economia OWNER TO gsan_admin;

--
-- TOC entry 5550 (class 0 OID 0)
-- Dependencies: 482
-- Name: COLUMN un_resumo_ligacao_economia.rele_qtligacoesnovasagua; Type: COMMENT; Schema: public; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_ligacao_economia.rele_qtligacoesnovasagua IS 'Quantidade de novas ligacoes de agua cadastradas no mes';


--
-- TOC entry 5551 (class 0 OID 0)
-- Dependencies: 482
-- Name: COLUMN un_resumo_ligacao_economia.rele_qtligacoesnovasesgoto; Type: COMMENT; Schema: public; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_ligacao_economia.rele_qtligacoesnovasesgoto IS 'Quantidade de novas ligacoes de esgoto cadastradas no mes';


--
-- TOC entry 5552 (class 0 OID 0)
-- Dependencies: 482
-- Name: COLUMN un_resumo_ligacao_economia.rele_qtligacoescortesmes; Type: COMMENT; Schema: public; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_ligacao_economia.rele_qtligacoescortesmes IS 'Quantidade de ligacoes de agua cortadas no mes';


--
-- TOC entry 5553 (class 0 OID 0)
-- Dependencies: 482
-- Name: COLUMN un_resumo_ligacao_economia.rele_qtligacoesreligadasmes; Type: COMMENT; Schema: public; Owner: gsan_admin
--

COMMENT ON COLUMN un_resumo_ligacao_economia.rele_qtligacoesreligadasmes IS 'Quantidade de ligacoes de agua religadas no mes';


--
-- TOC entry 5095 (class 0 OID 0)
-- Dependencies: 6
-- Name: admindb; Type: ACL; Schema: -; Owner: gsan_admin
--

REVOKE ALL ON SCHEMA admindb FROM PUBLIC;
REVOKE ALL ON SCHEMA admindb FROM gsan_admin;
GRANT ALL ON SCHEMA admindb TO gsan_admin;
GRANT USAGE ON SCHEMA admindb TO pg_aplic;
GRANT USAGE ON SCHEMA admindb TO pg_users;


--
-- TOC entry 5096 (class 0 OID 0)
-- Dependencies: 7
-- Name: arrecadacao; Type: ACL; Schema: -; Owner: gsan_admin
--

REVOKE ALL ON SCHEMA arrecadacao FROM PUBLIC;
REVOKE ALL ON SCHEMA arrecadacao FROM gsan_admin;
GRANT ALL ON SCHEMA arrecadacao TO gsan_admin;
GRANT USAGE ON SCHEMA arrecadacao TO pg_aplic;
GRANT USAGE ON SCHEMA arrecadacao TO pg_users;


--
-- TOC entry 5097 (class 0 OID 0)
-- Dependencies: 8
-- Name: atendimentopublico; Type: ACL; Schema: -; Owner: gsan_admin
--

REVOKE ALL ON SCHEMA atendimentopublico FROM PUBLIC;
REVOKE ALL ON SCHEMA atendimentopublico FROM gsan_admin;
GRANT ALL ON SCHEMA atendimentopublico TO gsan_admin;
GRANT USAGE ON SCHEMA atendimentopublico TO pg_aplic;
GRANT USAGE ON SCHEMA atendimentopublico TO pg_users;


--
-- TOC entry 5098 (class 0 OID 0)
-- Dependencies: 9
-- Name: cadastro; Type: ACL; Schema: -; Owner: gsan_admin
--

REVOKE ALL ON SCHEMA cadastro FROM PUBLIC;
REVOKE ALL ON SCHEMA cadastro FROM gsan_admin;
GRANT ALL ON SCHEMA cadastro TO gsan_admin;
GRANT USAGE ON SCHEMA cadastro TO pg_aplic;
GRANT USAGE ON SCHEMA cadastro TO pg_users;


--
-- TOC entry 5099 (class 0 OID 0)
-- Dependencies: 10
-- Name: cobranca; Type: ACL; Schema: -; Owner: gsan_admin
--

REVOKE ALL ON SCHEMA cobranca FROM PUBLIC;
REVOKE ALL ON SCHEMA cobranca FROM gsan_admin;
GRANT ALL ON SCHEMA cobranca TO gsan_admin;
GRANT USAGE ON SCHEMA cobranca TO pg_aplic;
GRANT USAGE ON SCHEMA cobranca TO pg_users;


--
-- TOC entry 5100 (class 0 OID 0)
-- Dependencies: 11
-- Name: corporativo; Type: ACL; Schema: -; Owner: gsan_admin
--

REVOKE ALL ON SCHEMA corporativo FROM PUBLIC;
REVOKE ALL ON SCHEMA corporativo FROM gsan_admin;
GRANT ALL ON SCHEMA corporativo TO gsan_admin;
GRANT USAGE ON SCHEMA corporativo TO pg_aplic;
GRANT USAGE ON SCHEMA corporativo TO pg_users;


--
-- TOC entry 5101 (class 0 OID 0)
-- Dependencies: 12
-- Name: faturamento; Type: ACL; Schema: -; Owner: gsan_admin
--

REVOKE ALL ON SCHEMA faturamento FROM PUBLIC;
REVOKE ALL ON SCHEMA faturamento FROM gsan_admin;
GRANT ALL ON SCHEMA faturamento TO gsan_admin;
GRANT USAGE ON SCHEMA faturamento TO pg_aplic;
GRANT USAGE ON SCHEMA faturamento TO pg_users;


--
-- TOC entry 5102 (class 0 OID 0)
-- Dependencies: 13
-- Name: financeiro; Type: ACL; Schema: -; Owner: gsan_admin
--

REVOKE ALL ON SCHEMA financeiro FROM PUBLIC;
REVOKE ALL ON SCHEMA financeiro FROM gsan_admin;
GRANT ALL ON SCHEMA financeiro TO gsan_admin;
GRANT USAGE ON SCHEMA financeiro TO pg_aplic;
GRANT USAGE ON SCHEMA financeiro TO pg_users;


--
-- TOC entry 5103 (class 0 OID 0)
-- Dependencies: 14
-- Name: micromedicao; Type: ACL; Schema: -; Owner: gsan_admin
--

REVOKE ALL ON SCHEMA micromedicao FROM PUBLIC;
REVOKE ALL ON SCHEMA micromedicao FROM gsan_admin;
GRANT ALL ON SCHEMA micromedicao TO gsan_admin;
GRANT USAGE ON SCHEMA micromedicao TO pg_aplic;
GRANT USAGE ON SCHEMA micromedicao TO pg_users;


--
-- TOC entry 5104 (class 0 OID 0)
-- Dependencies: 15
-- Name: myschema; Type: ACL; Schema: -; Owner: gsan_admin
--

REVOKE ALL ON SCHEMA myschema FROM PUBLIC;
REVOKE ALL ON SCHEMA myschema FROM gsan_admin;
GRANT ALL ON SCHEMA myschema TO gsan_admin;
GRANT USAGE ON SCHEMA myschema TO pg_aplic;
GRANT USAGE ON SCHEMA myschema TO pg_users;


--
-- TOC entry 5105 (class 0 OID 0)
-- Dependencies: 16
-- Name: operacional; Type: ACL; Schema: -; Owner: gsan_admin
--

REVOKE ALL ON SCHEMA operacional FROM PUBLIC;
REVOKE ALL ON SCHEMA operacional FROM gsan_admin;
GRANT ALL ON SCHEMA operacional TO gsan_admin;
GRANT USAGE ON SCHEMA operacional TO pg_aplic;
GRANT USAGE ON SCHEMA operacional TO pg_users;


--
-- TOC entry 5107 (class 0 OID 0)
-- Dependencies: 17
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


SET search_path = admindb, pg_catalog;

--
-- TOC entry 5109 (class 0 OID 0)
-- Dependencies: 192
-- Name: sequence_db_backup; Type: ACL; Schema: admindb; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_db_backup FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_db_backup FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_db_backup TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_db_backup TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_db_backup TO pg_users;


--
-- TOC entry 5124 (class 0 OID 0)
-- Dependencies: 193
-- Name: db_backup; Type: ACL; Schema: admindb; Owner: gsan_admin
--

REVOKE ALL ON TABLE db_backup FROM PUBLIC;
REVOKE ALL ON TABLE db_backup FROM gsan_admin;
GRANT ALL ON TABLE db_backup TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE db_backup TO pg_aplic;
GRANT SELECT ON TABLE db_backup TO pg_users;


--
-- TOC entry 5128 (class 0 OID 0)
-- Dependencies: 194
-- Name: db_empresa; Type: ACL; Schema: admindb; Owner: gsan_admin
--

REVOKE ALL ON TABLE db_empresa FROM PUBLIC;
REVOKE ALL ON TABLE db_empresa FROM gsan_admin;
GRANT ALL ON TABLE db_empresa TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE db_empresa TO pg_aplic;
GRANT SELECT ON TABLE db_empresa TO pg_users;


--
-- TOC entry 5129 (class 0 OID 0)
-- Dependencies: 195
-- Name: db_index; Type: ACL; Schema: admindb; Owner: gsan_admin
--

REVOKE ALL ON TABLE db_index FROM PUBLIC;
REVOKE ALL ON TABLE db_index FROM gsan_admin;
GRANT ALL ON TABLE db_index TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE db_index TO pg_aplic;
GRANT SELECT ON TABLE db_index TO pg_users;


--
-- TOC entry 5130 (class 0 OID 0)
-- Dependencies: 197
-- Name: sequence_db_vacuum; Type: ACL; Schema: admindb; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_db_vacuum FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_db_vacuum FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_db_vacuum TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_db_vacuum TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_db_vacuum TO pg_users;


--
-- TOC entry 5144 (class 0 OID 0)
-- Dependencies: 198
-- Name: db_vacuum; Type: ACL; Schema: admindb; Owner: gsan_admin
--

REVOKE ALL ON TABLE db_vacuum FROM PUBLIC;
REVOKE ALL ON TABLE db_vacuum FROM gsan_admin;
GRANT ALL ON TABLE db_vacuum TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE db_vacuum TO pg_aplic;
GRANT SELECT ON TABLE db_vacuum TO pg_users;


--
-- TOC entry 5145 (class 0 OID 0)
-- Dependencies: 199
-- Name: sequence_db_versao_base; Type: ACL; Schema: admindb; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_db_versao_base FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_db_versao_base FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_db_versao_base TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_db_versao_base TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_db_versao_base TO pg_users;


--
-- TOC entry 5153 (class 0 OID 0)
-- Dependencies: 200
-- Name: db_versao_base; Type: ACL; Schema: admindb; Owner: gsan_admin
--

REVOKE ALL ON TABLE db_versao_base FROM PUBLIC;
REVOKE ALL ON TABLE db_versao_base FROM gsan_admin;
GRANT ALL ON TABLE db_versao_base TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE db_versao_base TO pg_aplic;
GRANT SELECT ON TABLE db_versao_base TO pg_users;


--
-- TOC entry 5154 (class 0 OID 0)
-- Dependencies: 201
-- Name: sequence_db_versao_sincronismo; Type: ACL; Schema: admindb; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_db_versao_sincronismo FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_db_versao_sincronismo FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_db_versao_sincronismo TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_db_versao_sincronismo TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_db_versao_sincronismo TO pg_users;


--
-- TOC entry 5163 (class 0 OID 0)
-- Dependencies: 202
-- Name: db_versao_sincronismo; Type: ACL; Schema: admindb; Owner: gsan_admin
--

REVOKE ALL ON TABLE db_versao_sincronismo FROM PUBLIC;
REVOKE ALL ON TABLE db_versao_sincronismo FROM gsan_admin;
GRANT ALL ON TABLE db_versao_sincronismo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE db_versao_sincronismo TO pg_aplic;
GRANT SELECT ON TABLE db_versao_sincronismo TO pg_users;


--
-- TOC entry 5164 (class 0 OID 0)
-- Dependencies: 203
-- Name: sequence_db_query_start; Type: ACL; Schema: admindb; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_db_query_start FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_db_query_start FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_db_query_start TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_db_query_start TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_db_query_start TO pg_users;


SET search_path = arrecadacao, pg_catalog;

--
-- TOC entry 5165 (class 0 OID 0)
-- Dependencies: 220
-- Name: g_arrecadacao_forma; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_arrecadacao_forma FROM PUBLIC;
REVOKE ALL ON TABLE g_arrecadacao_forma FROM gsan_admin;
GRANT ALL ON TABLE g_arrecadacao_forma TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_arrecadacao_forma TO pg_aplic;
GRANT SELECT ON TABLE g_arrecadacao_forma TO pg_users;


--
-- TOC entry 5166 (class 0 OID 0)
-- Dependencies: 221
-- Name: g_arrecadador; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_arrecadador FROM PUBLIC;
REVOKE ALL ON TABLE g_arrecadador FROM gsan_admin;
GRANT ALL ON TABLE g_arrecadador TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_arrecadador TO pg_aplic;
GRANT SELECT ON TABLE g_arrecadador TO pg_users;


--
-- TOC entry 5167 (class 0 OID 0)
-- Dependencies: 222
-- Name: g_devolucao_situacao; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_devolucao_situacao FROM PUBLIC;
REVOKE ALL ON TABLE g_devolucao_situacao FROM gsan_admin;
GRANT ALL ON TABLE g_devolucao_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_devolucao_situacao TO pg_aplic;
GRANT SELECT ON TABLE g_devolucao_situacao TO pg_users;


--
-- TOC entry 5168 (class 0 OID 0)
-- Dependencies: 223
-- Name: g_epoca_pagamento; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_epoca_pagamento FROM PUBLIC;
REVOKE ALL ON TABLE g_epoca_pagamento FROM gsan_admin;
GRANT ALL ON TABLE g_epoca_pagamento TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_epoca_pagamento TO pg_aplic;
GRANT SELECT ON TABLE g_epoca_pagamento TO pg_users;


--
-- TOC entry 5169 (class 0 OID 0)
-- Dependencies: 224
-- Name: g_pagamento_situacao; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_pagamento_situacao FROM PUBLIC;
REVOKE ALL ON TABLE g_pagamento_situacao FROM gsan_admin;
GRANT ALL ON TABLE g_pagamento_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_pagamento_situacao TO pg_aplic;
GRANT SELECT ON TABLE g_pagamento_situacao TO pg_users;


--
-- TOC entry 5170 (class 0 OID 0)
-- Dependencies: 225
-- Name: g_pagamento_situacao_x; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_pagamento_situacao_x FROM PUBLIC;
REVOKE ALL ON TABLE g_pagamento_situacao_x FROM gsan_admin;
GRANT ALL ON TABLE g_pagamento_situacao_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_pagamento_situacao_x TO pg_aplic;
GRANT SELECT ON TABLE g_pagamento_situacao_x TO pg_users;


--
-- TOC entry 5171 (class 0 OID 0)
-- Dependencies: 226
-- Name: seq_un_resumo_arrecadacao; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE seq_un_resumo_arrecadacao FROM PUBLIC;
REVOKE ALL ON SEQUENCE seq_un_resumo_arrecadacao FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resumo_arrecadacao TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resumo_arrecadacao TO pg_aplic;
GRANT SELECT ON SEQUENCE seq_un_resumo_arrecadacao TO pg_users;


--
-- TOC entry 5176 (class 0 OID 0)
-- Dependencies: 227
-- Name: un_resumo_arrecadacao; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_arrecadacao FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_arrecadacao FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_arrecadacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_arrecadacao TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_arrecadacao TO pg_users;


--
-- TOC entry 5177 (class 0 OID 0)
-- Dependencies: 228
-- Name: vw_arrecadacao_dados_diarios; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_arrecadacao_dados_diarios FROM PUBLIC;
REVOKE ALL ON TABLE vw_arrecadacao_dados_diarios FROM gsan_admin;
GRANT ALL ON TABLE vw_arrecadacao_dados_diarios TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_arrecadacao_dados_diarios TO pg_aplic;
GRANT SELECT ON TABLE vw_arrecadacao_dados_diarios TO pg_users;


--
-- TOC entry 5178 (class 0 OID 0)
-- Dependencies: 229
-- Name: vw_arrecadacao_forma; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_arrecadacao_forma FROM PUBLIC;
REVOKE ALL ON TABLE vw_arrecadacao_forma FROM gsan_admin;
GRANT ALL ON TABLE vw_arrecadacao_forma TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_arrecadacao_forma TO pg_aplic;
GRANT SELECT ON TABLE vw_arrecadacao_forma TO pg_users;


--
-- TOC entry 5179 (class 0 OID 0)
-- Dependencies: 230
-- Name: vw_arrecadador; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_arrecadador FROM PUBLIC;
REVOKE ALL ON TABLE vw_arrecadador FROM gsan_admin;
GRANT ALL ON TABLE vw_arrecadador TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_arrecadador TO pg_aplic;
GRANT SELECT ON TABLE vw_arrecadador TO pg_users;


--
-- TOC entry 5180 (class 0 OID 0)
-- Dependencies: 231
-- Name: vw_devolucao_situacao; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_devolucao_situacao FROM PUBLIC;
REVOKE ALL ON TABLE vw_devolucao_situacao FROM gsan_admin;
GRANT ALL ON TABLE vw_devolucao_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_devolucao_situacao TO pg_aplic;
GRANT SELECT ON TABLE vw_devolucao_situacao TO pg_users;


--
-- TOC entry 5181 (class 0 OID 0)
-- Dependencies: 232
-- Name: vw_pagamento_situacao; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_pagamento_situacao FROM PUBLIC;
REVOKE ALL ON TABLE vw_pagamento_situacao FROM gsan_admin;
GRANT ALL ON TABLE vw_pagamento_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_pagamento_situacao TO pg_aplic;
GRANT SELECT ON TABLE vw_pagamento_situacao TO pg_users;


SET search_path = financeiro, pg_catalog;

--
-- TOC entry 5182 (class 0 OID 0)
-- Dependencies: 233
-- Name: g_lancamento_item_contabil; Type: ACL; Schema: financeiro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_lancamento_item_contabil FROM PUBLIC;
REVOKE ALL ON TABLE g_lancamento_item_contabil FROM gsan_admin;
GRANT ALL ON TABLE g_lancamento_item_contabil TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_lancamento_item_contabil TO pg_aplic;
GRANT SELECT ON TABLE g_lancamento_item_contabil TO pg_users;


SET search_path = arrecadacao, pg_catalog;

--
-- TOC entry 5183 (class 0 OID 0)
-- Dependencies: 234
-- Name: vw_resumo_arrecadacao; Type: ACL; Schema: arrecadacao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_resumo_arrecadacao FROM PUBLIC;
REVOKE ALL ON TABLE vw_resumo_arrecadacao FROM gsan_admin;
GRANT ALL ON TABLE vw_resumo_arrecadacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_resumo_arrecadacao TO pg_aplic;
GRANT SELECT ON TABLE vw_resumo_arrecadacao TO pg_users;


SET search_path = atendimentopublico, pg_catalog;

--
-- TOC entry 5184 (class 0 OID 0)
-- Dependencies: 235
-- Name: g_atend_motivo_encmt; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_atend_motivo_encmt FROM PUBLIC;
REVOKE ALL ON TABLE g_atend_motivo_encmt FROM gsan_admin;
GRANT ALL ON TABLE g_atend_motivo_encmt TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_atend_motivo_encmt TO pg_aplic;
GRANT SELECT ON TABLE g_atend_motivo_encmt TO pg_users;


--
-- TOC entry 5185 (class 0 OID 0)
-- Dependencies: 236
-- Name: g_atendimento_motivo_encerramento_x; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_atendimento_motivo_encerramento_x FROM PUBLIC;
REVOKE ALL ON TABLE g_atendimento_motivo_encerramento_x FROM gsan_admin;
GRANT ALL ON TABLE g_atendimento_motivo_encerramento_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_atendimento_motivo_encerramento_x TO pg_aplic;
GRANT SELECT ON TABLE g_atendimento_motivo_encerramento_x TO pg_users;


--
-- TOC entry 5186 (class 0 OID 0)
-- Dependencies: 237
-- Name: g_ligacao_agua_perfil; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_ligacao_agua_perfil FROM PUBLIC;
REVOKE ALL ON TABLE g_ligacao_agua_perfil FROM gsan_admin;
GRANT ALL ON TABLE g_ligacao_agua_perfil TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_ligacao_agua_perfil TO pg_aplic;
GRANT SELECT ON TABLE g_ligacao_agua_perfil TO pg_users;


--
-- TOC entry 5187 (class 0 OID 0)
-- Dependencies: 238
-- Name: g_ligacao_agua_perfil_x; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_ligacao_agua_perfil_x FROM PUBLIC;
REVOKE ALL ON TABLE g_ligacao_agua_perfil_x FROM gsan_admin;
GRANT ALL ON TABLE g_ligacao_agua_perfil_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_ligacao_agua_perfil_x TO pg_aplic;
GRANT SELECT ON TABLE g_ligacao_agua_perfil_x TO pg_users;


--
-- TOC entry 5192 (class 0 OID 0)
-- Dependencies: 239
-- Name: g_ligacao_agua_situacao; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_ligacao_agua_situacao FROM PUBLIC;
REVOKE ALL ON TABLE g_ligacao_agua_situacao FROM gsan_admin;
GRANT ALL ON TABLE g_ligacao_agua_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_ligacao_agua_situacao TO pg_aplic;
GRANT SELECT ON TABLE g_ligacao_agua_situacao TO pg_users;


--
-- TOC entry 5193 (class 0 OID 0)
-- Dependencies: 240
-- Name: g_ligacao_agua_situacao_x; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_ligacao_agua_situacao_x FROM PUBLIC;
REVOKE ALL ON TABLE g_ligacao_agua_situacao_x FROM gsan_admin;
GRANT ALL ON TABLE g_ligacao_agua_situacao_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_ligacao_agua_situacao_x TO pg_aplic;
GRANT SELECT ON TABLE g_ligacao_agua_situacao_x TO pg_users;


--
-- TOC entry 5194 (class 0 OID 0)
-- Dependencies: 241
-- Name: g_ligacao_esgoto_perfil; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_ligacao_esgoto_perfil FROM PUBLIC;
REVOKE ALL ON TABLE g_ligacao_esgoto_perfil FROM gsan_admin;
GRANT ALL ON TABLE g_ligacao_esgoto_perfil TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_ligacao_esgoto_perfil TO pg_aplic;
GRANT SELECT ON TABLE g_ligacao_esgoto_perfil TO pg_users;


--
-- TOC entry 5195 (class 0 OID 0)
-- Dependencies: 242
-- Name: g_ligacao_esgoto_perfil_x; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_ligacao_esgoto_perfil_x FROM PUBLIC;
REVOKE ALL ON TABLE g_ligacao_esgoto_perfil_x FROM gsan_admin;
GRANT ALL ON TABLE g_ligacao_esgoto_perfil_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_ligacao_esgoto_perfil_x TO pg_aplic;
GRANT SELECT ON TABLE g_ligacao_esgoto_perfil_x TO pg_users;


--
-- TOC entry 5200 (class 0 OID 0)
-- Dependencies: 243
-- Name: g_ligacao_esgoto_situacao; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_ligacao_esgoto_situacao FROM PUBLIC;
REVOKE ALL ON TABLE g_ligacao_esgoto_situacao FROM gsan_admin;
GRANT ALL ON TABLE g_ligacao_esgoto_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_ligacao_esgoto_situacao TO pg_aplic;
GRANT SELECT ON TABLE g_ligacao_esgoto_situacao TO pg_users;


--
-- TOC entry 5201 (class 0 OID 0)
-- Dependencies: 244
-- Name: g_ligacao_esgoto_situacao_x; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_ligacao_esgoto_situacao_x FROM PUBLIC;
REVOKE ALL ON TABLE g_ligacao_esgoto_situacao_x FROM gsan_admin;
GRANT ALL ON TABLE g_ligacao_esgoto_situacao_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_ligacao_esgoto_situacao_x TO pg_aplic;
GRANT SELECT ON TABLE g_ligacao_esgoto_situacao_x TO pg_users;


--
-- TOC entry 5202 (class 0 OID 0)
-- Dependencies: 245
-- Name: g_meio_solicitacao; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_meio_solicitacao FROM PUBLIC;
REVOKE ALL ON TABLE g_meio_solicitacao FROM gsan_admin;
GRANT ALL ON TABLE g_meio_solicitacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_meio_solicitacao TO pg_aplic;
GRANT SELECT ON TABLE g_meio_solicitacao TO pg_users;


--
-- TOC entry 5203 (class 0 OID 0)
-- Dependencies: 246
-- Name: g_meio_solicitacao_x; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_meio_solicitacao_x FROM PUBLIC;
REVOKE ALL ON TABLE g_meio_solicitacao_x FROM gsan_admin;
GRANT ALL ON TABLE g_meio_solicitacao_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_meio_solicitacao_x TO pg_aplic;
GRANT SELECT ON TABLE g_meio_solicitacao_x TO pg_users;


--
-- TOC entry 5204 (class 0 OID 0)
-- Dependencies: 247
-- Name: g_solicitacao_tipo; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_solicitacao_tipo FROM PUBLIC;
REVOKE ALL ON TABLE g_solicitacao_tipo FROM gsan_admin;
GRANT ALL ON TABLE g_solicitacao_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_solicitacao_tipo TO pg_aplic;
GRANT SELECT ON TABLE g_solicitacao_tipo TO pg_users;


--
-- TOC entry 5205 (class 0 OID 0)
-- Dependencies: 248
-- Name: g_solicitacao_tipo_espec; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_solicitacao_tipo_espec FROM PUBLIC;
REVOKE ALL ON TABLE g_solicitacao_tipo_espec FROM gsan_admin;
GRANT ALL ON TABLE g_solicitacao_tipo_espec TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_solicitacao_tipo_espec TO pg_aplic;
GRANT SELECT ON TABLE g_solicitacao_tipo_espec TO pg_users;


--
-- TOC entry 5206 (class 0 OID 0)
-- Dependencies: 249
-- Name: g_solicitacao_tipo_especificacao_x; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_solicitacao_tipo_especificacao_x FROM PUBLIC;
REVOKE ALL ON TABLE g_solicitacao_tipo_especificacao_x FROM gsan_admin;
GRANT ALL ON TABLE g_solicitacao_tipo_especificacao_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_solicitacao_tipo_especificacao_x TO pg_aplic;
GRANT SELECT ON TABLE g_solicitacao_tipo_especificacao_x TO pg_users;


--
-- TOC entry 5207 (class 0 OID 0)
-- Dependencies: 250
-- Name: g_solicitacao_tipo_x; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_solicitacao_tipo_x FROM PUBLIC;
REVOKE ALL ON TABLE g_solicitacao_tipo_x FROM gsan_admin;
GRANT ALL ON TABLE g_solicitacao_tipo_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_solicitacao_tipo_x TO pg_aplic;
GRANT SELECT ON TABLE g_solicitacao_tipo_x TO pg_users;


--
-- TOC entry 5208 (class 0 OID 0)
-- Dependencies: 251
-- Name: seq_un_resumo_ra; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE seq_un_resumo_ra FROM PUBLIC;
REVOKE ALL ON SEQUENCE seq_un_resumo_ra FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resumo_ra TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resumo_ra TO pg_aplic;
GRANT SELECT ON SEQUENCE seq_un_resumo_ra TO pg_users;


--
-- TOC entry 5212 (class 0 OID 0)
-- Dependencies: 252
-- Name: un_resumo_ra; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_ra FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_ra FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_ra TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_ra TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_ra TO pg_users;


--
-- TOC entry 5213 (class 0 OID 0)
-- Dependencies: 253
-- Name: vw_atendimento_motivo_encerramento; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_atendimento_motivo_encerramento FROM PUBLIC;
REVOKE ALL ON TABLE vw_atendimento_motivo_encerramento FROM gsan_admin;
GRANT ALL ON TABLE vw_atendimento_motivo_encerramento TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_atendimento_motivo_encerramento TO pg_aplic;
GRANT SELECT ON TABLE vw_atendimento_motivo_encerramento TO pg_users;


--
-- TOC entry 5214 (class 0 OID 0)
-- Dependencies: 254
-- Name: vw_ligacao_agua_perfil; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_ligacao_agua_perfil FROM PUBLIC;
REVOKE ALL ON TABLE vw_ligacao_agua_perfil FROM gsan_admin;
GRANT ALL ON TABLE vw_ligacao_agua_perfil TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_ligacao_agua_perfil TO pg_aplic;
GRANT SELECT ON TABLE vw_ligacao_agua_perfil TO pg_users;


--
-- TOC entry 5215 (class 0 OID 0)
-- Dependencies: 255
-- Name: vw_ligacao_agua_situacao; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_ligacao_agua_situacao FROM PUBLIC;
REVOKE ALL ON TABLE vw_ligacao_agua_situacao FROM gsan_admin;
GRANT ALL ON TABLE vw_ligacao_agua_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_ligacao_agua_situacao TO pg_aplic;
GRANT SELECT ON TABLE vw_ligacao_agua_situacao TO pg_users;


--
-- TOC entry 5216 (class 0 OID 0)
-- Dependencies: 256
-- Name: vw_ligacao_esgoto_perfil; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_ligacao_esgoto_perfil FROM PUBLIC;
REVOKE ALL ON TABLE vw_ligacao_esgoto_perfil FROM gsan_admin;
GRANT ALL ON TABLE vw_ligacao_esgoto_perfil TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_ligacao_esgoto_perfil TO pg_aplic;
GRANT SELECT ON TABLE vw_ligacao_esgoto_perfil TO pg_users;


--
-- TOC entry 5217 (class 0 OID 0)
-- Dependencies: 257
-- Name: vw_ligacao_esgoto_situacao; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_ligacao_esgoto_situacao FROM PUBLIC;
REVOKE ALL ON TABLE vw_ligacao_esgoto_situacao FROM gsan_admin;
GRANT ALL ON TABLE vw_ligacao_esgoto_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_ligacao_esgoto_situacao TO pg_aplic;
GRANT SELECT ON TABLE vw_ligacao_esgoto_situacao TO pg_users;


--
-- TOC entry 5218 (class 0 OID 0)
-- Dependencies: 258
-- Name: vw_meio_solicitacao; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_meio_solicitacao FROM PUBLIC;
REVOKE ALL ON TABLE vw_meio_solicitacao FROM gsan_admin;
GRANT ALL ON TABLE vw_meio_solicitacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_meio_solicitacao TO pg_aplic;
GRANT SELECT ON TABLE vw_meio_solicitacao TO pg_users;


--
-- TOC entry 5219 (class 0 OID 0)
-- Dependencies: 259
-- Name: vw_solicit_tipo_especificacao; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_solicit_tipo_especificacao FROM PUBLIC;
REVOKE ALL ON TABLE vw_solicit_tipo_especificacao FROM gsan_admin;
GRANT ALL ON TABLE vw_solicit_tipo_especificacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_solicit_tipo_especificacao TO pg_aplic;
GRANT SELECT ON TABLE vw_solicit_tipo_especificacao TO pg_users;


--
-- TOC entry 5220 (class 0 OID 0)
-- Dependencies: 260
-- Name: vw_solicitacao_tipo; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_solicitacao_tipo FROM PUBLIC;
REVOKE ALL ON TABLE vw_solicitacao_tipo FROM gsan_admin;
GRANT ALL ON TABLE vw_solicitacao_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_solicitacao_tipo TO pg_aplic;
GRANT SELECT ON TABLE vw_solicitacao_tipo TO pg_users;


--
-- TOC entry 5221 (class 0 OID 0)
-- Dependencies: 261
-- Name: vw_solicitacao_tipo_espec; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_solicitacao_tipo_espec FROM PUBLIC;
REVOKE ALL ON TABLE vw_solicitacao_tipo_espec FROM gsan_admin;
GRANT ALL ON TABLE vw_solicitacao_tipo_espec TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_solicitacao_tipo_espec TO pg_aplic;
GRANT SELECT ON TABLE vw_solicitacao_tipo_espec TO pg_users;


--
-- TOC entry 5222 (class 0 OID 0)
-- Dependencies: 262
-- Name: vw_solicitacao_tipo_especificacao; Type: ACL; Schema: atendimentopublico; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_solicitacao_tipo_especificacao FROM PUBLIC;
REVOKE ALL ON TABLE vw_solicitacao_tipo_especificacao FROM gsan_admin;
GRANT ALL ON TABLE vw_solicitacao_tipo_especificacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_solicitacao_tipo_especificacao TO pg_aplic;
GRANT SELECT ON TABLE vw_solicitacao_tipo_especificacao TO pg_users;


SET search_path = cadastro, pg_catalog;

--
-- TOC entry 5223 (class 0 OID 0)
-- Dependencies: 263
-- Name: g_bairro; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_bairro FROM PUBLIC;
REVOKE ALL ON TABLE g_bairro FROM gsan_admin;
GRANT ALL ON TABLE g_bairro TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_bairro TO pg_aplic;
GRANT SELECT ON TABLE g_bairro TO pg_users;


--
-- TOC entry 5224 (class 0 OID 0)
-- Dependencies: 264
-- Name: g_bairro_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_bairro_x FROM PUBLIC;
REVOKE ALL ON TABLE g_bairro_x FROM gsan_admin;
GRANT ALL ON TABLE g_bairro_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_bairro_x TO pg_aplic;
GRANT SELECT ON TABLE g_bairro_x TO pg_users;


--
-- TOC entry 5225 (class 0 OID 0)
-- Dependencies: 265
-- Name: g_bairro_xx; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_bairro_xx FROM PUBLIC;
REVOKE ALL ON TABLE g_bairro_xx FROM gsan_admin;
GRANT ALL ON TABLE g_bairro_xx TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_bairro_xx TO pg_aplic;
GRANT SELECT ON TABLE g_bairro_xx TO pg_users;


--
-- TOC entry 5226 (class 0 OID 0)
-- Dependencies: 266
-- Name: g_categoria; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_categoria FROM PUBLIC;
REVOKE ALL ON TABLE g_categoria FROM gsan_admin;
GRANT ALL ON TABLE g_categoria TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_categoria TO pg_aplic;
GRANT SELECT ON TABLE g_categoria TO pg_users;


--
-- TOC entry 5227 (class 0 OID 0)
-- Dependencies: 267
-- Name: g_categoria_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_categoria_x FROM PUBLIC;
REVOKE ALL ON TABLE g_categoria_x FROM gsan_admin;
GRANT ALL ON TABLE g_categoria_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_categoria_x TO pg_aplic;
GRANT SELECT ON TABLE g_categoria_x TO pg_users;


--
-- TOC entry 5228 (class 0 OID 0)
-- Dependencies: 268
-- Name: g_cliente_relacao_tipo; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_cliente_relacao_tipo FROM PUBLIC;
REVOKE ALL ON TABLE g_cliente_relacao_tipo FROM gsan_admin;
GRANT ALL ON TABLE g_cliente_relacao_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_cliente_relacao_tipo TO pg_aplic;
GRANT SELECT ON TABLE g_cliente_relacao_tipo TO pg_users;


--
-- TOC entry 5229 (class 0 OID 0)
-- Dependencies: 269
-- Name: g_cliente_tipo; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_cliente_tipo FROM PUBLIC;
REVOKE ALL ON TABLE g_cliente_tipo FROM gsan_admin;
GRANT ALL ON TABLE g_cliente_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_cliente_tipo TO pg_aplic;
GRANT SELECT ON TABLE g_cliente_tipo TO pg_users;


--
-- TOC entry 5230 (class 0 OID 0)
-- Dependencies: 271
-- Name: g_empresa; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_empresa FROM PUBLIC;
REVOKE ALL ON TABLE g_empresa FROM gsan_admin;
GRANT ALL ON TABLE g_empresa TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_empresa TO pg_aplic;
GRANT SELECT ON TABLE g_empresa TO pg_users;


--
-- TOC entry 5231 (class 0 OID 0)
-- Dependencies: 272
-- Name: g_empresa_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_empresa_x FROM PUBLIC;
REVOKE ALL ON TABLE g_empresa_x FROM gsan_admin;
GRANT ALL ON TABLE g_empresa_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_empresa_x TO pg_aplic;
GRANT SELECT ON TABLE g_empresa_x TO pg_users;


--
-- TOC entry 5234 (class 0 OID 0)
-- Dependencies: 273
-- Name: g_esfera_poder; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_esfera_poder FROM PUBLIC;
REVOKE ALL ON TABLE g_esfera_poder FROM gsan_admin;
GRANT ALL ON TABLE g_esfera_poder TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_esfera_poder TO pg_aplic;
GRANT SELECT ON TABLE g_esfera_poder TO pg_users;


--
-- TOC entry 5235 (class 0 OID 0)
-- Dependencies: 274
-- Name: g_esfera_poder_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_esfera_poder_x FROM PUBLIC;
REVOKE ALL ON TABLE g_esfera_poder_x FROM gsan_admin;
GRANT ALL ON TABLE g_esfera_poder_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_esfera_poder_x TO pg_aplic;
GRANT SELECT ON TABLE g_esfera_poder_x TO pg_users;


--
-- TOC entry 5237 (class 0 OID 0)
-- Dependencies: 275
-- Name: g_gerencia_regional; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_gerencia_regional FROM PUBLIC;
REVOKE ALL ON TABLE g_gerencia_regional FROM gsan_admin;
GRANT ALL ON TABLE g_gerencia_regional TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_gerencia_regional TO pg_aplic;
GRANT SELECT ON TABLE g_gerencia_regional TO pg_users;


--
-- TOC entry 5238 (class 0 OID 0)
-- Dependencies: 276
-- Name: g_gerencia_regional_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_gerencia_regional_x FROM PUBLIC;
REVOKE ALL ON TABLE g_gerencia_regional_x FROM gsan_admin;
GRANT ALL ON TABLE g_gerencia_regional_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_gerencia_regional_x TO pg_aplic;
GRANT SELECT ON TABLE g_gerencia_regional_x TO pg_users;


--
-- TOC entry 5241 (class 0 OID 0)
-- Dependencies: 277
-- Name: g_imovel_perfil; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_imovel_perfil FROM PUBLIC;
REVOKE ALL ON TABLE g_imovel_perfil FROM gsan_admin;
GRANT ALL ON TABLE g_imovel_perfil TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_imovel_perfil TO pg_aplic;
GRANT SELECT ON TABLE g_imovel_perfil TO pg_users;


--
-- TOC entry 5242 (class 0 OID 0)
-- Dependencies: 278
-- Name: g_imovel_perfil_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_imovel_perfil_x FROM PUBLIC;
REVOKE ALL ON TABLE g_imovel_perfil_x FROM gsan_admin;
GRANT ALL ON TABLE g_imovel_perfil_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_imovel_perfil_x TO pg_aplic;
GRANT SELECT ON TABLE g_imovel_perfil_x TO pg_users;


--
-- TOC entry 5243 (class 0 OID 0)
-- Dependencies: 279
-- Name: g_imovel_situacao; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_imovel_situacao FROM PUBLIC;
REVOKE ALL ON TABLE g_imovel_situacao FROM gsan_admin;
GRANT ALL ON TABLE g_imovel_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_imovel_situacao TO pg_aplic;
GRANT SELECT ON TABLE g_imovel_situacao TO pg_users;


--
-- TOC entry 5244 (class 0 OID 0)
-- Dependencies: 280
-- Name: g_imovel_situacao_tipo; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_imovel_situacao_tipo FROM PUBLIC;
REVOKE ALL ON TABLE g_imovel_situacao_tipo FROM gsan_admin;
GRANT ALL ON TABLE g_imovel_situacao_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_imovel_situacao_tipo TO pg_aplic;
GRANT SELECT ON TABLE g_imovel_situacao_tipo TO pg_users;


--
-- TOC entry 5245 (class 0 OID 0)
-- Dependencies: 281
-- Name: g_imovel_situacao_tipo_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_imovel_situacao_tipo_x FROM PUBLIC;
REVOKE ALL ON TABLE g_imovel_situacao_tipo_x FROM gsan_admin;
GRANT ALL ON TABLE g_imovel_situacao_tipo_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_imovel_situacao_tipo_x TO pg_aplic;
GRANT SELECT ON TABLE g_imovel_situacao_tipo_x TO pg_users;


--
-- TOC entry 5246 (class 0 OID 0)
-- Dependencies: 282
-- Name: g_imovel_situacao_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_imovel_situacao_x FROM PUBLIC;
REVOKE ALL ON TABLE g_imovel_situacao_x FROM gsan_admin;
GRANT ALL ON TABLE g_imovel_situacao_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_imovel_situacao_x TO pg_aplic;
GRANT SELECT ON TABLE g_imovel_situacao_x TO pg_users;


--
-- TOC entry 5250 (class 0 OID 0)
-- Dependencies: 283
-- Name: g_localidade; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_localidade FROM PUBLIC;
REVOKE ALL ON TABLE g_localidade FROM gsan_admin;
GRANT ALL ON TABLE g_localidade TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_localidade TO pg_aplic;
GRANT SELECT ON TABLE g_localidade TO pg_users;


--
-- TOC entry 5251 (class 0 OID 0)
-- Dependencies: 284
-- Name: g_localidade_porte; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_localidade_porte FROM PUBLIC;
REVOKE ALL ON TABLE g_localidade_porte FROM gsan_admin;
GRANT ALL ON TABLE g_localidade_porte TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_localidade_porte TO pg_aplic;
GRANT SELECT ON TABLE g_localidade_porte TO pg_users;


--
-- TOC entry 5252 (class 0 OID 0)
-- Dependencies: 285
-- Name: g_localidade_porte_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_localidade_porte_x FROM PUBLIC;
REVOKE ALL ON TABLE g_localidade_porte_x FROM gsan_admin;
GRANT ALL ON TABLE g_localidade_porte_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_localidade_porte_x TO pg_aplic;
GRANT SELECT ON TABLE g_localidade_porte_x TO pg_users;


--
-- TOC entry 5254 (class 0 OID 0)
-- Dependencies: 286
-- Name: g_localidade_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_localidade_x FROM PUBLIC;
REVOKE ALL ON TABLE g_localidade_x FROM gsan_admin;
GRANT ALL ON TABLE g_localidade_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_localidade_x TO pg_aplic;
GRANT SELECT ON TABLE g_localidade_x TO pg_users;


--
-- TOC entry 5255 (class 0 OID 0)
-- Dependencies: 287
-- Name: g_microrregiao; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_microrregiao FROM PUBLIC;
REVOKE ALL ON TABLE g_microrregiao FROM gsan_admin;
GRANT ALL ON TABLE g_microrregiao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_microrregiao TO pg_aplic;
GRANT SELECT ON TABLE g_microrregiao TO pg_users;


--
-- TOC entry 5256 (class 0 OID 0)
-- Dependencies: 288
-- Name: g_microrregiao_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_microrregiao_x FROM PUBLIC;
REVOKE ALL ON TABLE g_microrregiao_x FROM gsan_admin;
GRANT ALL ON TABLE g_microrregiao_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_microrregiao_x TO pg_aplic;
GRANT SELECT ON TABLE g_microrregiao_x TO pg_users;


--
-- TOC entry 5257 (class 0 OID 0)
-- Dependencies: 289
-- Name: g_municipio; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_municipio FROM PUBLIC;
REVOKE ALL ON TABLE g_municipio FROM gsan_admin;
GRANT ALL ON TABLE g_municipio TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_municipio TO pg_aplic;
GRANT SELECT ON TABLE g_municipio TO pg_users;


--
-- TOC entry 5259 (class 0 OID 0)
-- Dependencies: 291
-- Name: g_quadra; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_quadra FROM PUBLIC;
REVOKE ALL ON TABLE g_quadra FROM gsan_admin;
GRANT ALL ON TABLE g_quadra TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_quadra TO pg_aplic;
GRANT SELECT ON TABLE g_quadra TO pg_users;


--
-- TOC entry 5260 (class 0 OID 0)
-- Dependencies: 292
-- Name: g_quadra_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_quadra_x FROM PUBLIC;
REVOKE ALL ON TABLE g_quadra_x FROM gsan_admin;
GRANT ALL ON TABLE g_quadra_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_quadra_x TO pg_aplic;
GRANT SELECT ON TABLE g_quadra_x TO pg_users;


--
-- TOC entry 5261 (class 0 OID 0)
-- Dependencies: 293
-- Name: g_regiao; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_regiao FROM PUBLIC;
REVOKE ALL ON TABLE g_regiao FROM gsan_admin;
GRANT ALL ON TABLE g_regiao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_regiao TO pg_aplic;
GRANT SELECT ON TABLE g_regiao TO pg_users;


--
-- TOC entry 5262 (class 0 OID 0)
-- Dependencies: 294
-- Name: g_regiao_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_regiao_x FROM PUBLIC;
REVOKE ALL ON TABLE g_regiao_x FROM gsan_admin;
GRANT ALL ON TABLE g_regiao_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_regiao_x TO pg_aplic;
GRANT SELECT ON TABLE g_regiao_x TO pg_users;


--
-- TOC entry 5263 (class 0 OID 0)
-- Dependencies: 295
-- Name: g_setor_comercial; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_setor_comercial FROM PUBLIC;
REVOKE ALL ON TABLE g_setor_comercial FROM gsan_admin;
GRANT ALL ON TABLE g_setor_comercial TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_setor_comercial TO pg_aplic;
GRANT SELECT ON TABLE g_setor_comercial TO pg_users;


--
-- TOC entry 5265 (class 0 OID 0)
-- Dependencies: 297
-- Name: g_subcategoria; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_subcategoria FROM PUBLIC;
REVOKE ALL ON TABLE g_subcategoria FROM gsan_admin;
GRANT ALL ON TABLE g_subcategoria TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_subcategoria TO pg_aplic;
GRANT SELECT ON TABLE g_subcategoria TO pg_users;


--
-- TOC entry 5269 (class 0 OID 0)
-- Dependencies: 299
-- Name: g_superintendencia; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_superintendencia FROM PUBLIC;
REVOKE ALL ON TABLE g_superintendencia FROM gsan_admin;
GRANT ALL ON TABLE g_superintendencia TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_superintendencia TO pg_aplic;
GRANT SELECT ON TABLE g_superintendencia TO pg_users;


--
-- TOC entry 5270 (class 0 OID 0)
-- Dependencies: 300
-- Name: g_unidade_negocio; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_unidade_negocio FROM PUBLIC;
REVOKE ALL ON TABLE g_unidade_negocio FROM gsan_admin;
GRANT ALL ON TABLE g_unidade_negocio TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_unidade_negocio TO pg_aplic;
GRANT SELECT ON TABLE g_unidade_negocio TO pg_users;


--
-- TOC entry 5271 (class 0 OID 0)
-- Dependencies: 301
-- Name: g_unidade_negocio_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_unidade_negocio_x FROM PUBLIC;
REVOKE ALL ON TABLE g_unidade_negocio_x FROM gsan_admin;
GRANT ALL ON TABLE g_unidade_negocio_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_unidade_negocio_x TO pg_aplic;
GRANT SELECT ON TABLE g_unidade_negocio_x TO pg_users;


--
-- TOC entry 5272 (class 0 OID 0)
-- Dependencies: 302
-- Name: g_unidade_organizacional; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_unidade_organizacional FROM PUBLIC;
REVOKE ALL ON TABLE g_unidade_organizacional FROM gsan_admin;
GRANT ALL ON TABLE g_unidade_organizacional TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_unidade_organizacional TO pg_aplic;
GRANT SELECT ON TABLE g_unidade_organizacional TO pg_users;


--
-- TOC entry 5273 (class 0 OID 0)
-- Dependencies: 303
-- Name: g_unidade_organizacional_x; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_unidade_organizacional_x FROM PUBLIC;
REVOKE ALL ON TABLE g_unidade_organizacional_x FROM gsan_admin;
GRANT ALL ON TABLE g_unidade_organizacional_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_unidade_organizacional_x TO pg_aplic;
GRANT SELECT ON TABLE g_unidade_organizacional_x TO pg_users;


--
-- TOC entry 5280 (class 0 OID 0)
-- Dependencies: 304
-- Name: indicador; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE indicador FROM PUBLIC;
REVOKE ALL ON TABLE indicador FROM gsan_admin;
GRANT ALL ON TABLE indicador TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE indicador TO pg_aplic;
GRANT SELECT ON TABLE indicador TO pg_users;


--
-- TOC entry 5283 (class 0 OID 0)
-- Dependencies: 305
-- Name: rg_res_lig_econ; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE rg_res_lig_econ FROM PUBLIC;
REVOKE ALL ON TABLE rg_res_lig_econ FROM gsan_admin;
GRANT ALL ON TABLE rg_res_lig_econ TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE rg_res_lig_econ TO pg_aplic;
GRANT SELECT ON TABLE rg_res_lig_econ TO pg_users;


--
-- TOC entry 5284 (class 0 OID 0)
-- Dependencies: 306
-- Name: seq_g_cliente_relacao_tipo; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE seq_g_cliente_relacao_tipo FROM PUBLIC;
REVOKE ALL ON SEQUENCE seq_g_cliente_relacao_tipo FROM gsan_admin;
GRANT ALL ON SEQUENCE seq_g_cliente_relacao_tipo TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_g_cliente_relacao_tipo TO pg_aplic;
GRANT SELECT ON SEQUENCE seq_g_cliente_relacao_tipo TO pg_users;


--
-- TOC entry 5285 (class 0 OID 0)
-- Dependencies: 307
-- Name: seq_un_res_lig_econ; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE seq_un_res_lig_econ FROM PUBLIC;
REVOKE ALL ON SEQUENCE seq_un_res_lig_econ FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_res_lig_econ TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_res_lig_econ TO pg_aplic;
GRANT SELECT ON SEQUENCE seq_un_res_lig_econ TO pg_users;


--
-- TOC entry 5286 (class 0 OID 0)
-- Dependencies: 308
-- Name: sequence_g_superintendencia; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_g_superintendencia FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_g_superintendencia FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_g_superintendencia TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_g_superintendencia TO pg_aplic;


--
-- TOC entry 5287 (class 0 OID 0)
-- Dependencies: 309
-- Name: sequence_indicador; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_indicador FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_indicador FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_indicador TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_indicador TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_indicador TO pg_users;


--
-- TOC entry 5288 (class 0 OID 0)
-- Dependencies: 310
-- Name: sequence_rg_resumo_ligacao_economia; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_rg_resumo_ligacao_economia FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_rg_resumo_ligacao_economia FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_rg_resumo_ligacao_economia TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_rg_resumo_ligacao_economia TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_rg_resumo_ligacao_economia TO pg_users;


--
-- TOC entry 5289 (class 0 OID 0)
-- Dependencies: 311
-- Name: sequence_un_resumo_ligacao_economia_ref_2007; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2007 FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2007 FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2007 TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2007 TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2007 TO pg_users;


--
-- TOC entry 5290 (class 0 OID 0)
-- Dependencies: 312
-- Name: sequence_un_resumo_ligacao_economia_ref_2008; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2008 FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2008 FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2008 TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2008 TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2008 TO pg_users;


--
-- TOC entry 5291 (class 0 OID 0)
-- Dependencies: 313
-- Name: sequence_un_resumo_ligacao_economia_ref_2009; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2009 FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2009 FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2009 TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2009 TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_ligacao_economia_ref_2009 TO pg_users;


--
-- TOC entry 5296 (class 0 OID 0)
-- Dependencies: 314
-- Name: un_res_lig_econ; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_res_lig_econ FROM PUBLIC;
REVOKE ALL ON TABLE un_res_lig_econ FROM gsan_admin;
GRANT ALL ON TABLE un_res_lig_econ TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_res_lig_econ TO pg_aplic;
GRANT SELECT ON TABLE un_res_lig_econ TO pg_users;


--
-- TOC entry 5297 (class 0 OID 0)
-- Dependencies: 315
-- Name: un_resi_lig_eco; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resi_lig_eco FROM PUBLIC;
REVOKE ALL ON TABLE un_resi_lig_eco FROM gsan_admin;
GRANT ALL ON TABLE un_resi_lig_eco TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resi_lig_eco TO pg_aplic;
GRANT SELECT ON TABLE un_resi_lig_eco TO pg_users;


--
-- TOC entry 5300 (class 0 OID 0)
-- Dependencies: 316
-- Name: un_resumo_ligacao_economia_ref_2007; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_ligacao_economia_ref_2007 FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_ligacao_economia_ref_2007 FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_ligacao_economia_ref_2007 TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_ligacao_economia_ref_2007 TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_ligacao_economia_ref_2007 TO pg_users;


--
-- TOC entry 5303 (class 0 OID 0)
-- Dependencies: 317
-- Name: un_resumo_ligacao_economia_ref_2008; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_ligacao_economia_ref_2008 FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_ligacao_economia_ref_2008 FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_ligacao_economia_ref_2008 TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_ligacao_economia_ref_2008 TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_ligacao_economia_ref_2008 TO pg_users;


--
-- TOC entry 5306 (class 0 OID 0)
-- Dependencies: 318
-- Name: un_resumo_ligacao_economia_ref_2009; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_ligacao_economia_ref_2009 FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_ligacao_economia_ref_2009 FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_ligacao_economia_ref_2009 TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_ligacao_economia_ref_2009 TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_ligacao_economia_ref_2009 TO pg_users;


--
-- TOC entry 5307 (class 0 OID 0)
-- Dependencies: 319
-- Name: vw_bairro; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_bairro FROM PUBLIC;
REVOKE ALL ON TABLE vw_bairro FROM gsan_admin;
GRANT ALL ON TABLE vw_bairro TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_bairro TO pg_aplic;
GRANT SELECT ON TABLE vw_bairro TO pg_users;


--
-- TOC entry 5308 (class 0 OID 0)
-- Dependencies: 320
-- Name: vw_categoria; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_categoria FROM PUBLIC;
REVOKE ALL ON TABLE vw_categoria FROM gsan_admin;
GRANT ALL ON TABLE vw_categoria TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_categoria TO pg_aplic;
GRANT SELECT ON TABLE vw_categoria TO pg_users;


--
-- TOC entry 5309 (class 0 OID 0)
-- Dependencies: 321
-- Name: vw_cliente_relacao_tipo; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_cliente_relacao_tipo FROM PUBLIC;
REVOKE ALL ON TABLE vw_cliente_relacao_tipo FROM gsan_admin;
GRANT ALL ON TABLE vw_cliente_relacao_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_cliente_relacao_tipo TO pg_aplic;
GRANT SELECT ON TABLE vw_cliente_relacao_tipo TO pg_users;


--
-- TOC entry 5310 (class 0 OID 0)
-- Dependencies: 322
-- Name: vw_cliente_tipo; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_cliente_tipo FROM PUBLIC;
REVOKE ALL ON TABLE vw_cliente_tipo FROM gsan_admin;
GRANT ALL ON TABLE vw_cliente_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_cliente_tipo TO pg_aplic;
GRANT SELECT ON TABLE vw_cliente_tipo TO pg_users;


--
-- TOC entry 5311 (class 0 OID 0)
-- Dependencies: 323
-- Name: vw_empresa; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_empresa FROM PUBLIC;
REVOKE ALL ON TABLE vw_empresa FROM gsan_admin;
GRANT ALL ON TABLE vw_empresa TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_empresa TO pg_aplic;
GRANT SELECT ON TABLE vw_empresa TO pg_users;


--
-- TOC entry 5312 (class 0 OID 0)
-- Dependencies: 324
-- Name: vw_esfera_poder; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_esfera_poder FROM PUBLIC;
REVOKE ALL ON TABLE vw_esfera_poder FROM gsan_admin;
GRANT ALL ON TABLE vw_esfera_poder TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_esfera_poder TO pg_aplic;
GRANT SELECT ON TABLE vw_esfera_poder TO pg_users;


--
-- TOC entry 5313 (class 0 OID 0)
-- Dependencies: 325
-- Name: vw_g_setor_comercial; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_g_setor_comercial FROM PUBLIC;
REVOKE ALL ON TABLE vw_g_setor_comercial FROM gsan_admin;
GRANT ALL ON TABLE vw_g_setor_comercial TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_g_setor_comercial TO pg_aplic;
GRANT SELECT ON TABLE vw_g_setor_comercial TO pg_users;


--
-- TOC entry 5314 (class 0 OID 0)
-- Dependencies: 326
-- Name: vw_gerencia_regional; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_gerencia_regional FROM PUBLIC;
REVOKE ALL ON TABLE vw_gerencia_regional FROM gsan_admin;
GRANT ALL ON TABLE vw_gerencia_regional TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_gerencia_regional TO pg_aplic;
GRANT SELECT ON TABLE vw_gerencia_regional TO pg_users;


--
-- TOC entry 5315 (class 0 OID 0)
-- Dependencies: 327
-- Name: vw_imovel_perfil; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_imovel_perfil FROM PUBLIC;
REVOKE ALL ON TABLE vw_imovel_perfil FROM gsan_admin;
GRANT ALL ON TABLE vw_imovel_perfil TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_imovel_perfil TO pg_aplic;
GRANT SELECT ON TABLE vw_imovel_perfil TO pg_users;


--
-- TOC entry 5316 (class 0 OID 0)
-- Dependencies: 328
-- Name: vw_imovel_situacao; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_imovel_situacao FROM PUBLIC;
REVOKE ALL ON TABLE vw_imovel_situacao FROM gsan_admin;
GRANT ALL ON TABLE vw_imovel_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_imovel_situacao TO pg_aplic;
GRANT SELECT ON TABLE vw_imovel_situacao TO pg_users;


--
-- TOC entry 5317 (class 0 OID 0)
-- Dependencies: 329
-- Name: vw_imovel_situacao_tipo; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_imovel_situacao_tipo FROM PUBLIC;
REVOKE ALL ON TABLE vw_imovel_situacao_tipo FROM gsan_admin;
GRANT ALL ON TABLE vw_imovel_situacao_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_imovel_situacao_tipo TO pg_aplic;
GRANT SELECT ON TABLE vw_imovel_situacao_tipo TO pg_users;


--
-- TOC entry 5318 (class 0 OID 0)
-- Dependencies: 330
-- Name: vw_localidade; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_localidade FROM PUBLIC;
REVOKE ALL ON TABLE vw_localidade FROM gsan_admin;
GRANT ALL ON TABLE vw_localidade TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_localidade TO pg_aplic;
GRANT SELECT ON TABLE vw_localidade TO pg_users;


--
-- TOC entry 5319 (class 0 OID 0)
-- Dependencies: 331
-- Name: vw_localidade_elo; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_localidade_elo FROM PUBLIC;
REVOKE ALL ON TABLE vw_localidade_elo FROM gsan_admin;
GRANT ALL ON TABLE vw_localidade_elo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_localidade_elo TO pg_aplic;
GRANT SELECT ON TABLE vw_localidade_elo TO pg_users;


--
-- TOC entry 5320 (class 0 OID 0)
-- Dependencies: 332
-- Name: vw_localidade_porte; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_localidade_porte FROM PUBLIC;
REVOKE ALL ON TABLE vw_localidade_porte FROM gsan_admin;
GRANT ALL ON TABLE vw_localidade_porte TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_localidade_porte TO pg_aplic;
GRANT SELECT ON TABLE vw_localidade_porte TO pg_users;


--
-- TOC entry 5321 (class 0 OID 0)
-- Dependencies: 333
-- Name: vw_microrregiao; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_microrregiao FROM PUBLIC;
REVOKE ALL ON TABLE vw_microrregiao FROM gsan_admin;
GRANT ALL ON TABLE vw_microrregiao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_microrregiao TO pg_aplic;
GRANT SELECT ON TABLE vw_microrregiao TO pg_users;


--
-- TOC entry 5322 (class 0 OID 0)
-- Dependencies: 334
-- Name: vw_municipio; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_municipio FROM PUBLIC;
REVOKE ALL ON TABLE vw_municipio FROM gsan_admin;
GRANT ALL ON TABLE vw_municipio TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_municipio TO pg_aplic;
GRANT SELECT ON TABLE vw_municipio TO pg_users;


--
-- TOC entry 5323 (class 0 OID 0)
-- Dependencies: 335
-- Name: vw_quadra; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_quadra FROM PUBLIC;
REVOKE ALL ON TABLE vw_quadra FROM gsan_admin;
GRANT ALL ON TABLE vw_quadra TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_quadra TO pg_aplic;
GRANT SELECT ON TABLE vw_quadra TO pg_users;


--
-- TOC entry 5324 (class 0 OID 0)
-- Dependencies: 336
-- Name: vw_regiao; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_regiao FROM PUBLIC;
REVOKE ALL ON TABLE vw_regiao FROM gsan_admin;
GRANT ALL ON TABLE vw_regiao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_regiao TO pg_aplic;
GRANT SELECT ON TABLE vw_regiao TO pg_users;


--
-- TOC entry 5325 (class 0 OID 0)
-- Dependencies: 337
-- Name: vw_setor_comercial; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_setor_comercial FROM PUBLIC;
REVOKE ALL ON TABLE vw_setor_comercial FROM gsan_admin;
GRANT ALL ON TABLE vw_setor_comercial TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_setor_comercial TO pg_aplic;
GRANT SELECT ON TABLE vw_setor_comercial TO pg_users;


--
-- TOC entry 5326 (class 0 OID 0)
-- Dependencies: 338
-- Name: vw_subcategoria; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_subcategoria FROM PUBLIC;
REVOKE ALL ON TABLE vw_subcategoria FROM gsan_admin;
GRANT ALL ON TABLE vw_subcategoria TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_subcategoria TO pg_aplic;
GRANT SELECT ON TABLE vw_subcategoria TO pg_users;


--
-- TOC entry 5327 (class 0 OID 0)
-- Dependencies: 339
-- Name: vw_superintendencia; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_superintendencia FROM PUBLIC;
REVOKE ALL ON TABLE vw_superintendencia FROM gsan_admin;
GRANT ALL ON TABLE vw_superintendencia TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_superintendencia TO pg_aplic;
GRANT SELECT ON TABLE vw_superintendencia TO pg_users;


--
-- TOC entry 5328 (class 0 OID 0)
-- Dependencies: 340
-- Name: vw_un_resumo_ligacao_economia_mes_anterior; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_ligacao_economia_mes_anterior FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_ligacao_economia_mes_anterior FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_ligacao_economia_mes_anterior TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_ligacao_economia_mes_anterior TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_ligacao_economia_mes_anterior TO pg_users;


--
-- TOC entry 5329 (class 0 OID 0)
-- Dependencies: 341
-- Name: vw_un_resi_lig_eco; Type: ACL; Schema: cadastro; Owner: postgres
--

REVOKE ALL ON TABLE vw_un_resi_lig_eco FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resi_lig_eco FROM postgres;
GRANT ALL ON TABLE vw_un_resi_lig_eco TO postgres;
GRANT SELECT ON TABLE vw_un_resi_lig_eco TO pg_users;


--
-- TOC entry 5330 (class 0 OID 0)
-- Dependencies: 342
-- Name: vw_un_resumo_ligacao_economia; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_ligacao_economia FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_ligacao_economia FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_ligacao_economia TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_ligacao_economia TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_ligacao_economia TO pg_users;


--
-- TOC entry 5331 (class 0 OID 0)
-- Dependencies: 343
-- Name: vw_unidade_negocio; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_unidade_negocio FROM PUBLIC;
REVOKE ALL ON TABLE vw_unidade_negocio FROM gsan_admin;
GRANT ALL ON TABLE vw_unidade_negocio TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_unidade_negocio TO pg_aplic;
GRANT SELECT ON TABLE vw_unidade_negocio TO pg_users;


--
-- TOC entry 5332 (class 0 OID 0)
-- Dependencies: 344
-- Name: vw_unidade_organizacional; Type: ACL; Schema: cadastro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_unidade_organizacional FROM PUBLIC;
REVOKE ALL ON TABLE vw_unidade_organizacional FROM gsan_admin;
GRANT ALL ON TABLE vw_unidade_organizacional TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_unidade_organizacional TO pg_aplic;
GRANT SELECT ON TABLE vw_unidade_organizacional TO pg_users;


SET search_path = cobranca, pg_catalog;

--
-- TOC entry 5333 (class 0 OID 0)
-- Dependencies: 345
-- Name: faixa_valor; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON TABLE faixa_valor FROM PUBLIC;
REVOKE ALL ON TABLE faixa_valor FROM gsan_admin;
GRANT ALL ON TABLE faixa_valor TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE faixa_valor TO pg_aplic;
GRANT SELECT ON TABLE faixa_valor TO pg_users;


--
-- TOC entry 5334 (class 0 OID 0)
-- Dependencies: 346
-- Name: g_documento_tipo; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_documento_tipo FROM PUBLIC;
REVOKE ALL ON TABLE g_documento_tipo FROM gsan_admin;
GRANT ALL ON TABLE g_documento_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_documento_tipo TO pg_aplic;
GRANT SELECT ON TABLE g_documento_tipo TO pg_users;


--
-- TOC entry 5335 (class 0 OID 0)
-- Dependencies: 347
-- Name: g_documento_tipo_x; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_documento_tipo_x FROM PUBLIC;
REVOKE ALL ON TABLE g_documento_tipo_x FROM gsan_admin;
GRANT ALL ON TABLE g_documento_tipo_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_documento_tipo_x TO pg_aplic;
GRANT SELECT ON TABLE g_documento_tipo_x TO pg_users;


--
-- TOC entry 5336 (class 0 OID 0)
-- Dependencies: 348
-- Name: seq_un_resumo_parcelamento; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE seq_un_resumo_parcelamento FROM PUBLIC;
REVOKE ALL ON SEQUENCE seq_un_resumo_parcelamento FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resumo_parcelamento TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resumo_parcelamento TO pg_aplic;
GRANT SELECT ON SEQUENCE seq_un_resumo_parcelamento TO pg_users;


--
-- TOC entry 5337 (class 0 OID 0)
-- Dependencies: 349
-- Name: sequence_faixa_valor; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_faixa_valor FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_faixa_valor FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_faixa_valor TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_faixa_valor TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_faixa_valor TO pg_users;


--
-- TOC entry 5338 (class 0 OID 0)
-- Dependencies: 350
-- Name: sequence_un_resumo_indicadores_cobranca; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_indicadores_cobranca FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_indicadores_cobranca FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_indicadores_cobranca TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_indicadores_cobranca TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_indicadores_cobranca TO pg_users;


--
-- TOC entry 5339 (class 0 OID 0)
-- Dependencies: 351
-- Name: sequence_un_resumo_pendencia; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_pendencia FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_pendencia FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_pendencia TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_pendencia TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_pendencia TO pg_users;


--
-- TOC entry 5340 (class 0 OID 0)
-- Dependencies: 352
-- Name: sequence_un_resumo_pendencia_ref_2010; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_pendencia_ref_2010 FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_pendencia_ref_2010 FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_un_resumo_pendencia_ref_2010 TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_pendencia_ref_2010 TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_pendencia_ref_2010 TO pg_users;


--
-- TOC entry 5341 (class 0 OID 0)
-- Dependencies: 353
-- Name: sequence_un_resumo_pendencia_sem_quadra; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_pendencia_sem_quadra FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_pendencia_sem_quadra FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_pendencia_sem_quadra TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_pendencia_sem_quadra TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_pendencia_sem_quadra TO pg_users;


--
-- TOC entry 5342 (class 0 OID 0)
-- Dependencies: 354
-- Name: un_resumo_indicadores_cobranca; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_indicadores_cobranca FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_indicadores_cobranca FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_indicadores_cobranca TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_indicadores_cobranca TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_indicadores_cobranca TO pg_users;


--
-- TOC entry 5343 (class 0 OID 0)
-- Dependencies: 355
-- Name: un_resumo_parcelamento; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_parcelamento FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_parcelamento FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_parcelamento TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_parcelamento TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_parcelamento TO pg_users;


--
-- TOC entry 5345 (class 0 OID 0)
-- Dependencies: 356
-- Name: un_resumo_pendencia; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_pendencia FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_pendencia FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_pendencia TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_pendencia TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_pendencia TO pg_users;


--
-- TOC entry 5346 (class 0 OID 0)
-- Dependencies: 357
-- Name: un_resumo_pendencia_sem_quadra; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_pendencia_sem_quadra FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_pendencia_sem_quadra FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_pendencia_sem_quadra TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_pendencia_sem_quadra TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_pendencia_sem_quadra TO pg_users;


--
-- TOC entry 5347 (class 0 OID 0)
-- Dependencies: 358
-- Name: vw_documento_tipo; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_documento_tipo FROM PUBLIC;
REVOKE ALL ON TABLE vw_documento_tipo FROM gsan_admin;
GRANT ALL ON TABLE vw_documento_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_documento_tipo TO pg_aplic;
GRANT SELECT ON TABLE vw_documento_tipo TO pg_users;


SET search_path = faturamento, pg_catalog;

--
-- TOC entry 5348 (class 0 OID 0)
-- Dependencies: 359
-- Name: sequence_un_resumo_indicadores_faturamento; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_indicadores_faturamento FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_indicadores_faturamento FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_indicadores_faturamento TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_indicadores_faturamento TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_indicadores_faturamento TO pg_users;


--
-- TOC entry 5349 (class 0 OID 0)
-- Dependencies: 360
-- Name: un_resumo_indicadores_faturamento; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_indicadores_faturamento FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_indicadores_faturamento FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_indicadores_faturamento TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_indicadores_faturamento TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_indicadores_faturamento TO pg_users;


SET search_path = cobranca, pg_catalog;

--
-- TOC entry 5350 (class 0 OID 0)
-- Dependencies: 361
-- Name: vw_un_resumo_unificado_cobranca; Type: ACL; Schema: cobranca; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_unificado_cobranca FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_unificado_cobranca FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_unificado_cobranca TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_unificado_cobranca TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_unificado_cobranca TO pg_users;


SET search_path = corporativo, pg_catalog;

--
-- TOC entry 5351 (class 0 OID 0)
-- Dependencies: 362
-- Name: sequence_un_resumo_corporativo; Type: ACL; Schema: corporativo; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_corporativo FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_corporativo FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_un_resumo_corporativo TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_corporativo TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_corporativo TO pg_users;


--
-- TOC entry 5376 (class 0 OID 0)
-- Dependencies: 363
-- Name: un_resumo_corporativo; Type: ACL; Schema: corporativo; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_corporativo FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_corporativo FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_corporativo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_corporativo TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_corporativo TO pg_users;


SET search_path = faturamento, pg_catalog;

--
-- TOC entry 5377 (class 0 OID 0)
-- Dependencies: 364
-- Name: g_consumo_tarifa; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_consumo_tarifa FROM PUBLIC;
REVOKE ALL ON TABLE g_consumo_tarifa FROM gsan_admin;
GRANT ALL ON TABLE g_consumo_tarifa TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_consumo_tarifa TO pg_aplic;
GRANT SELECT ON TABLE g_consumo_tarifa TO pg_users;


--
-- TOC entry 5378 (class 0 OID 0)
-- Dependencies: 365
-- Name: g_consumo_tarifa_x; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_consumo_tarifa_x FROM PUBLIC;
REVOKE ALL ON TABLE g_consumo_tarifa_x FROM gsan_admin;
GRANT ALL ON TABLE g_consumo_tarifa_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_consumo_tarifa_x TO pg_aplic;
GRANT SELECT ON TABLE g_consumo_tarifa_x TO pg_users;


--
-- TOC entry 5379 (class 0 OID 0)
-- Dependencies: 366
-- Name: g_credito_origem; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_credito_origem FROM PUBLIC;
REVOKE ALL ON TABLE g_credito_origem FROM gsan_admin;
GRANT ALL ON TABLE g_credito_origem TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_credito_origem TO pg_aplic;
GRANT SELECT ON TABLE g_credito_origem TO pg_users;


--
-- TOC entry 5380 (class 0 OID 0)
-- Dependencies: 367
-- Name: g_credito_origem_x; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_credito_origem_x FROM PUBLIC;
REVOKE ALL ON TABLE g_credito_origem_x FROM gsan_admin;
GRANT ALL ON TABLE g_credito_origem_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_credito_origem_x TO pg_aplic;
GRANT SELECT ON TABLE g_credito_origem_x TO pg_users;


--
-- TOC entry 5381 (class 0 OID 0)
-- Dependencies: 368
-- Name: g_credito_tipo; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_credito_tipo FROM PUBLIC;
REVOKE ALL ON TABLE g_credito_tipo FROM gsan_admin;
GRANT ALL ON TABLE g_credito_tipo TO gsan_admin;
GRANT ALL ON TABLE g_credito_tipo TO postgres WITH GRANT OPTION;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_credito_tipo TO pg_aplic;
GRANT SELECT ON TABLE g_credito_tipo TO pg_users;


--
-- TOC entry 5382 (class 0 OID 0)
-- Dependencies: 369
-- Name: g_credito_tipo_x; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_credito_tipo_x FROM PUBLIC;
REVOKE ALL ON TABLE g_credito_tipo_x FROM gsan_admin;
GRANT ALL ON TABLE g_credito_tipo_x TO gsan_admin;
GRANT ALL ON TABLE g_credito_tipo_x TO postgres WITH GRANT OPTION;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_credito_tipo_x TO pg_aplic;
GRANT SELECT ON TABLE g_credito_tipo_x TO pg_users;


--
-- TOC entry 5384 (class 0 OID 0)
-- Dependencies: 370
-- Name: g_debito_tipo; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_debito_tipo FROM PUBLIC;
REVOKE ALL ON TABLE g_debito_tipo FROM gsan_admin;
GRANT ALL ON TABLE g_debito_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_debito_tipo TO pg_aplic;
GRANT SELECT ON TABLE g_debito_tipo TO pg_users;


--
-- TOC entry 5385 (class 0 OID 0)
-- Dependencies: 371
-- Name: g_debito_tipo_x; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_debito_tipo_x FROM PUBLIC;
REVOKE ALL ON TABLE g_debito_tipo_x FROM gsan_admin;
GRANT ALL ON TABLE g_debito_tipo_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_debito_tipo_x TO pg_aplic;
GRANT SELECT ON TABLE g_debito_tipo_x TO pg_users;


--
-- TOC entry 5390 (class 0 OID 0)
-- Dependencies: 372
-- Name: g_fat_sit_motivo; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_fat_sit_motivo FROM PUBLIC;
REVOKE ALL ON TABLE g_fat_sit_motivo FROM gsan_admin;
GRANT ALL ON TABLE g_fat_sit_motivo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_fat_sit_motivo TO pg_aplic;
GRANT SELECT ON TABLE g_fat_sit_motivo TO pg_users;


--
-- TOC entry 5391 (class 0 OID 0)
-- Dependencies: 373
-- Name: g_faturamento_grupo; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_faturamento_grupo FROM PUBLIC;
REVOKE ALL ON TABLE g_faturamento_grupo FROM gsan_admin;
GRANT ALL ON TABLE g_faturamento_grupo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_faturamento_grupo TO pg_aplic;
GRANT SELECT ON TABLE g_faturamento_grupo TO pg_users;


--
-- TOC entry 5392 (class 0 OID 0)
-- Dependencies: 374
-- Name: g_faturamento_grupo_x; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_faturamento_grupo_x FROM PUBLIC;
REVOKE ALL ON TABLE g_faturamento_grupo_x FROM gsan_admin;
GRANT ALL ON TABLE g_faturamento_grupo_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_faturamento_grupo_x TO pg_aplic;
GRANT SELECT ON TABLE g_faturamento_grupo_x TO pg_users;


--
-- TOC entry 5395 (class 0 OID 0)
-- Dependencies: 375
-- Name: g_faturamento_situacao_tipo; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_faturamento_situacao_tipo FROM PUBLIC;
REVOKE ALL ON TABLE g_faturamento_situacao_tipo FROM gsan_admin;
GRANT ALL ON TABLE g_faturamento_situacao_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_faturamento_situacao_tipo TO pg_aplic;
GRANT SELECT ON TABLE g_faturamento_situacao_tipo TO pg_users;


--
-- TOC entry 5396 (class 0 OID 0)
-- Dependencies: 376
-- Name: g_imposto_tipo; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_imposto_tipo FROM PUBLIC;
REVOKE ALL ON TABLE g_imposto_tipo FROM gsan_admin;
GRANT ALL ON TABLE g_imposto_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_imposto_tipo TO pg_aplic;
GRANT SELECT ON TABLE g_imposto_tipo TO pg_users;


--
-- TOC entry 5397 (class 0 OID 0)
-- Dependencies: 377
-- Name: g_imposto_tipo_x; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_imposto_tipo_x FROM PUBLIC;
REVOKE ALL ON TABLE g_imposto_tipo_x FROM gsan_admin;
GRANT ALL ON TABLE g_imposto_tipo_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_imposto_tipo_x TO pg_aplic;
GRANT SELECT ON TABLE g_imposto_tipo_x TO pg_users;


--
-- TOC entry 5398 (class 0 OID 0)
-- Dependencies: 378
-- Name: seq_g_fat_sit_motivo; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE seq_g_fat_sit_motivo FROM PUBLIC;
REVOKE ALL ON SEQUENCE seq_g_fat_sit_motivo FROM gsan_admin;
GRANT ALL ON SEQUENCE seq_g_fat_sit_motivo TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_g_fat_sit_motivo TO pg_aplic;
GRANT SELECT,UPDATE ON SEQUENCE seq_g_fat_sit_motivo TO pg_users;


--
-- TOC entry 5399 (class 0 OID 0)
-- Dependencies: 379
-- Name: seq_un_resumo_faturamento; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE seq_un_resumo_faturamento FROM PUBLIC;
REVOKE ALL ON SEQUENCE seq_un_resumo_faturamento FROM gsan_admin;
GRANT ALL ON SEQUENCE seq_un_resumo_faturamento TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resumo_faturamento TO pg_aplic;
GRANT SELECT ON SEQUENCE seq_un_resumo_faturamento TO pg_users;


--
-- TOC entry 5400 (class 0 OID 0)
-- Dependencies: 380
-- Name: sequence_un_resumo_indicadores_faturamento_ref_2007; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2007 FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2007 FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2007 TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2007 TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2007 TO pg_users;


--
-- TOC entry 5401 (class 0 OID 0)
-- Dependencies: 381
-- Name: sequence_un_resumo_indicadores_faturamento_ref_2008; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2008 FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2008 FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2008 TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2008 TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2008 TO pg_users;


--
-- TOC entry 5402 (class 0 OID 0)
-- Dependencies: 382
-- Name: sequence_un_resumo_indicadores_faturamento_ref_2009; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2009 FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2009 FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2009 TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2009 TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_indicadores_faturamento_ref_2009 TO pg_users;


--
-- TOC entry 5403 (class 0 OID 0)
-- Dependencies: 383
-- Name: sequence_un_resumo_refaturamento; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_refaturamento FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_refaturamento FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_un_resumo_refaturamento TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_refaturamento TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_refaturamento TO pg_users;


--
-- TOC entry 5412 (class 0 OID 0)
-- Dependencies: 384
-- Name: un_resumo_faturamento; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_faturamento FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_faturamento FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_faturamento TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_faturamento TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_faturamento TO pg_users;


--
-- TOC entry 5413 (class 0 OID 0)
-- Dependencies: 385
-- Name: un_resumo_indicadores_faturamento_ref_2007; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_indicadores_faturamento_ref_2007 FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_indicadores_faturamento_ref_2007 FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_indicadores_faturamento_ref_2007 TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_indicadores_faturamento_ref_2007 TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_indicadores_faturamento_ref_2007 TO pg_users;


--
-- TOC entry 5414 (class 0 OID 0)
-- Dependencies: 386
-- Name: un_resumo_indicadores_faturamento_ref_2008; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_indicadores_faturamento_ref_2008 FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_indicadores_faturamento_ref_2008 FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_indicadores_faturamento_ref_2008 TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_indicadores_faturamento_ref_2008 TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_indicadores_faturamento_ref_2008 TO pg_users;


--
-- TOC entry 5415 (class 0 OID 0)
-- Dependencies: 387
-- Name: un_resumo_indicadores_faturamento_ref_2009; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_indicadores_faturamento_ref_2009 FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_indicadores_faturamento_ref_2009 FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_indicadores_faturamento_ref_2009 TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_indicadores_faturamento_ref_2009 TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_indicadores_faturamento_ref_2009 TO pg_users;


--
-- TOC entry 5443 (class 0 OID 0)
-- Dependencies: 388
-- Name: un_resumo_refaturamento; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_refaturamento FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_refaturamento FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_refaturamento TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_refaturamento TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_refaturamento TO pg_users;


--
-- TOC entry 5444 (class 0 OID 0)
-- Dependencies: 389
-- Name: vw_consumo_tarifa; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_consumo_tarifa FROM PUBLIC;
REVOKE ALL ON TABLE vw_consumo_tarifa FROM gsan_admin;
GRANT ALL ON TABLE vw_consumo_tarifa TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_consumo_tarifa TO pg_aplic;
GRANT SELECT ON TABLE vw_consumo_tarifa TO pg_users;


--
-- TOC entry 5445 (class 0 OID 0)
-- Dependencies: 390
-- Name: vw_credito_origem; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_credito_origem FROM PUBLIC;
REVOKE ALL ON TABLE vw_credito_origem FROM gsan_admin;
GRANT ALL ON TABLE vw_credito_origem TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_credito_origem TO pg_aplic;
GRANT SELECT ON TABLE vw_credito_origem TO pg_users;


--
-- TOC entry 5446 (class 0 OID 0)
-- Dependencies: 391
-- Name: vw_credito_tipo; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_credito_tipo FROM PUBLIC;
REVOKE ALL ON TABLE vw_credito_tipo FROM gsan_admin;
GRANT ALL ON TABLE vw_credito_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_credito_tipo TO pg_aplic;
GRANT SELECT ON TABLE vw_credito_tipo TO pg_users;


--
-- TOC entry 5447 (class 0 OID 0)
-- Dependencies: 392
-- Name: vw_debito_tipo; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_debito_tipo FROM PUBLIC;
REVOKE ALL ON TABLE vw_debito_tipo FROM gsan_admin;
GRANT ALL ON TABLE vw_debito_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_debito_tipo TO pg_aplic;
GRANT SELECT ON TABLE vw_debito_tipo TO pg_users;


--
-- TOC entry 5448 (class 0 OID 0)
-- Dependencies: 393
-- Name: vw_faturamento_grupo; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_faturamento_grupo FROM PUBLIC;
REVOKE ALL ON TABLE vw_faturamento_grupo FROM gsan_admin;
GRANT ALL ON TABLE vw_faturamento_grupo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_faturamento_grupo TO pg_aplic;
GRANT SELECT ON TABLE vw_faturamento_grupo TO pg_users;


--
-- TOC entry 5449 (class 0 OID 0)
-- Dependencies: 394
-- Name: vw_faturamento_situacao_motivo; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_faturamento_situacao_motivo FROM PUBLIC;
REVOKE ALL ON TABLE vw_faturamento_situacao_motivo FROM gsan_admin;
GRANT ALL ON TABLE vw_faturamento_situacao_motivo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_faturamento_situacao_motivo TO pg_aplic;
GRANT SELECT ON TABLE vw_faturamento_situacao_motivo TO pg_users;


--
-- TOC entry 5450 (class 0 OID 0)
-- Dependencies: 395
-- Name: vw_faturamento_situacao_tipo; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_faturamento_situacao_tipo FROM PUBLIC;
REVOKE ALL ON TABLE vw_faturamento_situacao_tipo FROM gsan_admin;
GRANT ALL ON TABLE vw_faturamento_situacao_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_faturamento_situacao_tipo TO pg_aplic;
GRANT SELECT ON TABLE vw_faturamento_situacao_tipo TO pg_users;


--
-- TOC entry 5451 (class 0 OID 0)
-- Dependencies: 396
-- Name: vw_imposto_tipo; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_imposto_tipo FROM PUBLIC;
REVOKE ALL ON TABLE vw_imposto_tipo FROM gsan_admin;
GRANT ALL ON TABLE vw_imposto_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_imposto_tipo TO pg_aplic;
GRANT SELECT ON TABLE vw_imposto_tipo TO pg_users;


--
-- TOC entry 5452 (class 0 OID 0)
-- Dependencies: 397
-- Name: vw_un_resumo_faturamento; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_faturamento FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_faturamento FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_faturamento TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_faturamento TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_faturamento TO pg_users;


--
-- TOC entry 5453 (class 0 OID 0)
-- Dependencies: 398
-- Name: vw_un_resumo_faturamento_agua_esgoto; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_faturamento_agua_esgoto FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_faturamento_agua_esgoto FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_faturamento_agua_esgoto TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_faturamento_agua_esgoto TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_faturamento_agua_esgoto TO pg_users;


--
-- TOC entry 5454 (class 0 OID 0)
-- Dependencies: 399
-- Name: vw_un_resumo_faturamento_mes_anterior; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_faturamento_mes_anterior FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_faturamento_mes_anterior FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_faturamento_mes_anterior TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_faturamento_mes_anterior TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_faturamento_mes_anterior TO pg_users;


--
-- TOC entry 5455 (class 0 OID 0)
-- Dependencies: 400
-- Name: vw_un_resumo_faturamento_ind; Type: ACL; Schema: faturamento; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_faturamento_ind FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_faturamento_ind FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_faturamento_ind TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_faturamento_ind TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_faturamento_ind TO pg_users;


SET search_path = financeiro, pg_catalog;

--
-- TOC entry 5456 (class 0 OID 0)
-- Dependencies: 401
-- Name: g_financiamento_tipo; Type: ACL; Schema: financeiro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_financiamento_tipo FROM PUBLIC;
REVOKE ALL ON TABLE g_financiamento_tipo FROM gsan_admin;
GRANT ALL ON TABLE g_financiamento_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_financiamento_tipo TO pg_aplic;
GRANT SELECT ON TABLE g_financiamento_tipo TO pg_users;


--
-- TOC entry 5457 (class 0 OID 0)
-- Dependencies: 402
-- Name: g_financiamento_tipo_x; Type: ACL; Schema: financeiro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_financiamento_tipo_x FROM PUBLIC;
REVOKE ALL ON TABLE g_financiamento_tipo_x FROM gsan_admin;
GRANT ALL ON TABLE g_financiamento_tipo_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_financiamento_tipo_x TO pg_aplic;
GRANT SELECT ON TABLE g_financiamento_tipo_x TO pg_users;


--
-- TOC entry 5458 (class 0 OID 0)
-- Dependencies: 403
-- Name: g_lancamento_item; Type: ACL; Schema: financeiro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_lancamento_item FROM PUBLIC;
REVOKE ALL ON TABLE g_lancamento_item FROM gsan_admin;
GRANT ALL ON TABLE g_lancamento_item TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_lancamento_item TO pg_aplic;
GRANT SELECT ON TABLE g_lancamento_item TO pg_users;


--
-- TOC entry 5459 (class 0 OID 0)
-- Dependencies: 404
-- Name: g_lancamento_item_x; Type: ACL; Schema: financeiro; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_lancamento_item_x FROM PUBLIC;
REVOKE ALL ON TABLE g_lancamento_item_x FROM gsan_admin;
GRANT ALL ON TABLE g_lancamento_item_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_lancamento_item_x TO pg_aplic;
GRANT SELECT ON TABLE g_lancamento_item_x TO pg_users;


--
-- TOC entry 5460 (class 0 OID 0)
-- Dependencies: 405
-- Name: vw_financiamento_tipo; Type: ACL; Schema: financeiro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_financiamento_tipo FROM PUBLIC;
REVOKE ALL ON TABLE vw_financiamento_tipo FROM gsan_admin;
GRANT ALL ON TABLE vw_financiamento_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_financiamento_tipo TO pg_aplic;
GRANT SELECT ON TABLE vw_financiamento_tipo TO pg_users;


--
-- TOC entry 5461 (class 0 OID 0)
-- Dependencies: 406
-- Name: vw_lancamento_item; Type: ACL; Schema: financeiro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_lancamento_item FROM PUBLIC;
REVOKE ALL ON TABLE vw_lancamento_item FROM gsan_admin;
GRANT ALL ON TABLE vw_lancamento_item TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_lancamento_item TO pg_aplic;
GRANT SELECT ON TABLE vw_lancamento_item TO pg_users;


--
-- TOC entry 5462 (class 0 OID 0)
-- Dependencies: 407
-- Name: vw_lancamento_item_contabil; Type: ACL; Schema: financeiro; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_lancamento_item_contabil FROM PUBLIC;
REVOKE ALL ON TABLE vw_lancamento_item_contabil FROM gsan_admin;
GRANT ALL ON TABLE vw_lancamento_item_contabil TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_lancamento_item_contabil TO pg_aplic;
GRANT SELECT ON TABLE vw_lancamento_item_contabil TO pg_users;


SET search_path = micromedicao, pg_catalog;

--
-- TOC entry 5463 (class 0 OID 0)
-- Dependencies: 408
-- Name: g_consumo_tipo; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_consumo_tipo FROM PUBLIC;
REVOKE ALL ON TABLE g_consumo_tipo FROM gsan_admin;
GRANT ALL ON TABLE g_consumo_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_consumo_tipo TO pg_aplic;
GRANT SELECT ON TABLE g_consumo_tipo TO pg_users;


--
-- TOC entry 5464 (class 0 OID 0)
-- Dependencies: 409
-- Name: g_consumo_tipo_x; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_consumo_tipo_x FROM PUBLIC;
REVOKE ALL ON TABLE g_consumo_tipo_x FROM gsan_admin;
GRANT ALL ON TABLE g_consumo_tipo_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_consumo_tipo_x TO pg_aplic;
GRANT SELECT ON TABLE g_consumo_tipo_x TO pg_users;


--
-- TOC entry 5465 (class 0 OID 0)
-- Dependencies: 410
-- Name: g_hidr_classe_metrlg; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_hidr_classe_metrlg FROM PUBLIC;
REVOKE ALL ON TABLE g_hidr_classe_metrlg FROM gsan_admin;
GRANT ALL ON TABLE g_hidr_classe_metrlg TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_hidr_classe_metrlg TO pg_aplic;
GRANT SELECT ON TABLE g_hidr_classe_metrlg TO pg_users;


--
-- TOC entry 5466 (class 0 OID 0)
-- Dependencies: 411
-- Name: g_hidr_local_armaz; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_hidr_local_armaz FROM PUBLIC;
REVOKE ALL ON TABLE g_hidr_local_armaz FROM gsan_admin;
GRANT ALL ON TABLE g_hidr_local_armaz TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_hidr_local_armaz TO pg_aplic;
GRANT SELECT ON TABLE g_hidr_local_armaz TO pg_users;


--
-- TOC entry 5467 (class 0 OID 0)
-- Dependencies: 412
-- Name: g_hidr_motivo_baixa; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_hidr_motivo_baixa FROM PUBLIC;
REVOKE ALL ON TABLE g_hidr_motivo_baixa FROM gsan_admin;
GRANT ALL ON TABLE g_hidr_motivo_baixa TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_hidr_motivo_baixa TO pg_aplic;
GRANT SELECT ON TABLE g_hidr_motivo_baixa TO pg_users;


--
-- TOC entry 5468 (class 0 OID 0)
-- Dependencies: 413
-- Name: g_hidrometro_capacidade; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_hidrometro_capacidade FROM PUBLIC;
REVOKE ALL ON TABLE g_hidrometro_capacidade FROM gsan_admin;
GRANT ALL ON TABLE g_hidrometro_capacidade TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_hidrometro_capacidade TO pg_aplic;
GRANT SELECT ON TABLE g_hidrometro_capacidade TO pg_users;


--
-- TOC entry 5469 (class 0 OID 0)
-- Dependencies: 414
-- Name: g_hidrometro_capacidade_x; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_hidrometro_capacidade_x FROM PUBLIC;
REVOKE ALL ON TABLE g_hidrometro_capacidade_x FROM gsan_admin;
GRANT ALL ON TABLE g_hidrometro_capacidade_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_hidrometro_capacidade_x TO pg_aplic;
GRANT SELECT ON TABLE g_hidrometro_capacidade_x TO pg_users;


--
-- TOC entry 5470 (class 0 OID 0)
-- Dependencies: 415
-- Name: g_hidrometro_diametro; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_hidrometro_diametro FROM PUBLIC;
REVOKE ALL ON TABLE g_hidrometro_diametro FROM gsan_admin;
GRANT ALL ON TABLE g_hidrometro_diametro TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_hidrometro_diametro TO pg_aplic;
GRANT SELECT ON TABLE g_hidrometro_diametro TO pg_users;


--
-- TOC entry 5471 (class 0 OID 0)
-- Dependencies: 416
-- Name: g_hidrometro_marca; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_hidrometro_marca FROM PUBLIC;
REVOKE ALL ON TABLE g_hidrometro_marca FROM gsan_admin;
GRANT ALL ON TABLE g_hidrometro_marca TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_hidrometro_marca TO pg_aplic;
GRANT SELECT ON TABLE g_hidrometro_marca TO pg_users;


--
-- TOC entry 5472 (class 0 OID 0)
-- Dependencies: 417
-- Name: g_hidrometro_situacao; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_hidrometro_situacao FROM PUBLIC;
REVOKE ALL ON TABLE g_hidrometro_situacao FROM gsan_admin;
GRANT ALL ON TABLE g_hidrometro_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_hidrometro_situacao TO pg_aplic;
GRANT SELECT ON TABLE g_hidrometro_situacao TO pg_users;


--
-- TOC entry 5473 (class 0 OID 0)
-- Dependencies: 418
-- Name: g_hidrometro_tipo; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_hidrometro_tipo FROM PUBLIC;
REVOKE ALL ON TABLE g_hidrometro_tipo FROM gsan_admin;
GRANT ALL ON TABLE g_hidrometro_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_hidrometro_tipo TO pg_aplic;
GRANT SELECT ON TABLE g_hidrometro_tipo TO pg_users;


--
-- TOC entry 5474 (class 0 OID 0)
-- Dependencies: 419
-- Name: g_leitura_anormalidade; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_leitura_anormalidade FROM PUBLIC;
REVOKE ALL ON TABLE g_leitura_anormalidade FROM gsan_admin;
GRANT ALL ON TABLE g_leitura_anormalidade TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_leitura_anormalidade TO pg_aplic;
GRANT SELECT ON TABLE g_leitura_anormalidade TO pg_users;


--
-- TOC entry 5475 (class 0 OID 0)
-- Dependencies: 420
-- Name: g_leitura_anormalidade_consumo; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_leitura_anormalidade_consumo FROM PUBLIC;
REVOKE ALL ON TABLE g_leitura_anormalidade_consumo FROM gsan_admin;
GRANT ALL ON TABLE g_leitura_anormalidade_consumo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_leitura_anormalidade_consumo TO pg_aplic;
GRANT SELECT ON TABLE g_leitura_anormalidade_consumo TO pg_users;


--
-- TOC entry 5476 (class 0 OID 0)
-- Dependencies: 421
-- Name: g_leitura_anormalidade_leitura; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_leitura_anormalidade_leitura FROM PUBLIC;
REVOKE ALL ON TABLE g_leitura_anormalidade_leitura FROM gsan_admin;
GRANT ALL ON TABLE g_leitura_anormalidade_leitura TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_leitura_anormalidade_leitura TO pg_aplic;
GRANT SELECT ON TABLE g_leitura_anormalidade_leitura TO pg_users;


--
-- TOC entry 5477 (class 0 OID 0)
-- Dependencies: 422
-- Name: g_leitura_situacao; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_leitura_situacao FROM PUBLIC;
REVOKE ALL ON TABLE g_leitura_situacao FROM gsan_admin;
GRANT ALL ON TABLE g_leitura_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_leitura_situacao TO pg_aplic;
GRANT SELECT ON TABLE g_leitura_situacao TO pg_users;


--
-- TOC entry 5478 (class 0 OID 0)
-- Dependencies: 423
-- Name: g_medicao_tipo; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_medicao_tipo FROM PUBLIC;
REVOKE ALL ON TABLE g_medicao_tipo FROM gsan_admin;
GRANT ALL ON TABLE g_medicao_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_medicao_tipo TO pg_aplic;
GRANT SELECT ON TABLE g_medicao_tipo TO pg_users;


--
-- TOC entry 5480 (class 0 OID 0)
-- Dependencies: 424
-- Name: g_rota; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_rota FROM PUBLIC;
REVOKE ALL ON TABLE g_rota FROM gsan_admin;
GRANT ALL ON TABLE g_rota TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_rota TO pg_aplic;
GRANT SELECT ON TABLE g_rota TO pg_users;


--
-- TOC entry 5482 (class 0 OID 0)
-- Dependencies: 425
-- Name: g_rota_x; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_rota_x FROM PUBLIC;
REVOKE ALL ON TABLE g_rota_x FROM gsan_admin;
GRANT ALL ON TABLE g_rota_x TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_rota_x TO pg_aplic;
GRANT SELECT ON TABLE g_rota_x TO pg_users;


--
-- TOC entry 5483 (class 0 OID 0)
-- Dependencies: 426
-- Name: seq_un_res_ins_hidr; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE seq_un_res_ins_hidr FROM PUBLIC;
REVOKE ALL ON SEQUENCE seq_un_res_ins_hidr FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_res_ins_hidr TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_res_ins_hidr TO pg_aplic;
GRANT SELECT ON SEQUENCE seq_un_res_ins_hidr TO pg_users;


--
-- TOC entry 5484 (class 0 OID 0)
-- Dependencies: 427
-- Name: seq_un_res_lt_anorm; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE seq_un_res_lt_anorm FROM PUBLIC;
REVOKE ALL ON SEQUENCE seq_un_res_lt_anorm FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_res_lt_anorm TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_res_lt_anorm TO pg_aplic;
GRANT SELECT ON SEQUENCE seq_un_res_lt_anorm TO pg_users;


--
-- TOC entry 5485 (class 0 OID 0)
-- Dependencies: 428
-- Name: seq_un_resi_des_mmd; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE seq_un_resi_des_mmd FROM PUBLIC;
REVOKE ALL ON SEQUENCE seq_un_resi_des_mmd FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resi_des_mmd TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resi_des_mmd TO pg_aplic;
GRANT SELECT ON SEQUENCE seq_un_resi_des_mmd TO pg_users;


--
-- TOC entry 5486 (class 0 OID 0)
-- Dependencies: 429
-- Name: seq_un_resumo_coleta_esgoto; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE seq_un_resumo_coleta_esgoto FROM PUBLIC;
REVOKE ALL ON SEQUENCE seq_un_resumo_coleta_esgoto FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resumo_coleta_esgoto TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resumo_coleta_esgoto TO pg_aplic;
GRANT SELECT ON SEQUENCE seq_un_resumo_coleta_esgoto TO pg_users;


--
-- TOC entry 5487 (class 0 OID 0)
-- Dependencies: 430
-- Name: seq_un_resumo_consumo_agua; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE seq_un_resumo_consumo_agua FROM PUBLIC;
REVOKE ALL ON SEQUENCE seq_un_resumo_consumo_agua FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resumo_consumo_agua TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resumo_consumo_agua TO pg_aplic;
GRANT SELECT ON SEQUENCE seq_un_resumo_consumo_agua TO pg_users;


--
-- TOC entry 5488 (class 0 OID 0)
-- Dependencies: 431
-- Name: seq_un_resumo_hidrometro; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE seq_un_resumo_hidrometro FROM PUBLIC;
REVOKE ALL ON SEQUENCE seq_un_resumo_hidrometro FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resumo_hidrometro TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE seq_un_resumo_hidrometro TO pg_aplic;
GRANT SELECT ON SEQUENCE seq_un_resumo_hidrometro TO pg_users;


--
-- TOC entry 5489 (class 0 OID 0)
-- Dependencies: 432
-- Name: sequence_un_resumo_indicador_desempenho_micromedicao_ref_2007; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2007 FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2007 FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2007 TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2007 TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2007 TO pg_users;


--
-- TOC entry 5490 (class 0 OID 0)
-- Dependencies: 433
-- Name: sequence_un_resumo_indicador_desempenho_micromedicao_ref_2008; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2008 FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2008 FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2008 TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2008 TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2008 TO pg_users;


--
-- TOC entry 5491 (class 0 OID 0)
-- Dependencies: 434
-- Name: sequence_un_resumo_indicador_desempenho_micromedicao_ref_2009; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2009 FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2009 FROM gsan_admin;
GRANT ALL ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2009 TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2009 TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_indicador_desempenho_micromedicao_ref_2009 TO pg_users;


--
-- TOC entry 5492 (class 0 OID 0)
-- Dependencies: 435
-- Name: sequence_un_resumo_metas; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_metas FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_metas FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_metas TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_metas TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_metas TO pg_users;


--
-- TOC entry 5493 (class 0 OID 0)
-- Dependencies: 436
-- Name: sequence_un_resumo_metas_acumulado; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON SEQUENCE sequence_un_resumo_metas_acumulado FROM PUBLIC;
REVOKE ALL ON SEQUENCE sequence_un_resumo_metas_acumulado FROM gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_metas_acumulado TO gsan_admin;
GRANT SELECT,UPDATE ON SEQUENCE sequence_un_resumo_metas_acumulado TO pg_aplic;
GRANT SELECT ON SEQUENCE sequence_un_resumo_metas_acumulado TO pg_users;


--
-- TOC entry 5500 (class 0 OID 0)
-- Dependencies: 437
-- Name: un_res_ins_hidr; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_res_ins_hidr FROM PUBLIC;
REVOKE ALL ON TABLE un_res_ins_hidr FROM gsan_admin;
GRANT ALL ON TABLE un_res_ins_hidr TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_res_ins_hidr TO pg_aplic;
GRANT SELECT ON TABLE un_res_ins_hidr TO pg_users;


--
-- TOC entry 5503 (class 0 OID 0)
-- Dependencies: 438
-- Name: un_res_lt_anorm; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_res_lt_anorm FROM PUBLIC;
REVOKE ALL ON TABLE un_res_lt_anorm FROM gsan_admin;
GRANT ALL ON TABLE un_res_lt_anorm TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_res_lt_anorm TO pg_aplic;
GRANT SELECT ON TABLE un_res_lt_anorm TO pg_users;


--
-- TOC entry 5504 (class 0 OID 0)
-- Dependencies: 439
-- Name: un_res_mt_acum; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_res_mt_acum FROM PUBLIC;
REVOKE ALL ON TABLE un_res_mt_acum FROM gsan_admin;
GRANT ALL ON TABLE un_res_mt_acum TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_res_mt_acum TO pg_aplic;
GRANT SELECT ON TABLE un_res_mt_acum TO pg_users;


--
-- TOC entry 5505 (class 0 OID 0)
-- Dependencies: 440
-- Name: un_resi_des_mmd; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resi_des_mmd FROM PUBLIC;
REVOKE ALL ON TABLE un_resi_des_mmd FROM gsan_admin;
GRANT ALL ON TABLE un_resi_des_mmd TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resi_des_mmd TO pg_aplic;
GRANT SELECT ON TABLE un_resi_des_mmd TO pg_users;


--
-- TOC entry 5506 (class 0 OID 0)
-- Dependencies: 441
-- Name: un_resumo_analitico; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_analitico FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_analitico FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_analitico TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_analitico TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_analitico TO pg_users;


--
-- TOC entry 5510 (class 0 OID 0)
-- Dependencies: 442
-- Name: un_resumo_coleta_esgoto; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_coleta_esgoto FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_coleta_esgoto FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_coleta_esgoto TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_coleta_esgoto TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_coleta_esgoto TO pg_users;


--
-- TOC entry 5512 (class 0 OID 0)
-- Dependencies: 443
-- Name: un_resumo_consumo_agua; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_consumo_agua FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_consumo_agua FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_consumo_agua TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_consumo_agua TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_consumo_agua TO pg_users;


--
-- TOC entry 5513 (class 0 OID 0)
-- Dependencies: 444
-- Name: un_resumo_hidrometro; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_hidrometro FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_hidrometro FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_hidrometro TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_hidrometro TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_hidrometro TO pg_users;


--
-- TOC entry 5514 (class 0 OID 0)
-- Dependencies: 445
-- Name: un_resumo_indicador_desempenho_micromedicao_ref_2007; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2007 FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2007 FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2007 TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2007 TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2007 TO pg_users;


--
-- TOC entry 5515 (class 0 OID 0)
-- Dependencies: 446
-- Name: un_resumo_indicador_desempenho_micromedicao_ref_2008; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2008 FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2008 FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2008 TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2008 TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2008 TO pg_users;


--
-- TOC entry 5516 (class 0 OID 0)
-- Dependencies: 447
-- Name: un_resumo_indicador_desempenho_micromedicao_ref_2009; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2009 FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2009 FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2009 TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2009 TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_indicador_desempenho_micromedicao_ref_2009 TO pg_users;


--
-- TOC entry 5517 (class 0 OID 0)
-- Dependencies: 448
-- Name: un_resumo_metas; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_metas FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_metas FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_metas TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_metas TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_metas TO pg_users;


--
-- TOC entry 5518 (class 0 OID 0)
-- Dependencies: 449
-- Name: vw_consumo_tipo; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_consumo_tipo FROM PUBLIC;
REVOKE ALL ON TABLE vw_consumo_tipo FROM gsan_admin;
GRANT ALL ON TABLE vw_consumo_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_consumo_tipo TO pg_aplic;
GRANT SELECT ON TABLE vw_consumo_tipo TO pg_users;


--
-- TOC entry 5519 (class 0 OID 0)
-- Dependencies: 450
-- Name: vw_hidrometro_capacidade; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_hidrometro_capacidade FROM PUBLIC;
REVOKE ALL ON TABLE vw_hidrometro_capacidade FROM gsan_admin;
GRANT ALL ON TABLE vw_hidrometro_capacidade TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_hidrometro_capacidade TO pg_aplic;
GRANT SELECT ON TABLE vw_hidrometro_capacidade TO pg_users;


--
-- TOC entry 5520 (class 0 OID 0)
-- Dependencies: 451
-- Name: vw_hidrometro_classe_metrologica; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_hidrometro_classe_metrologica FROM PUBLIC;
REVOKE ALL ON TABLE vw_hidrometro_classe_metrologica FROM gsan_admin;
GRANT ALL ON TABLE vw_hidrometro_classe_metrologica TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_hidrometro_classe_metrologica TO pg_aplic;
GRANT SELECT ON TABLE vw_hidrometro_classe_metrologica TO pg_users;


--
-- TOC entry 5521 (class 0 OID 0)
-- Dependencies: 452
-- Name: vw_hidrometro_diametro; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_hidrometro_diametro FROM PUBLIC;
REVOKE ALL ON TABLE vw_hidrometro_diametro FROM gsan_admin;
GRANT ALL ON TABLE vw_hidrometro_diametro TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_hidrometro_diametro TO pg_aplic;
GRANT SELECT ON TABLE vw_hidrometro_diametro TO pg_users;


--
-- TOC entry 5522 (class 0 OID 0)
-- Dependencies: 453
-- Name: vw_hidrometro_local_armazenagem; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_hidrometro_local_armazenagem FROM PUBLIC;
REVOKE ALL ON TABLE vw_hidrometro_local_armazenagem FROM gsan_admin;
GRANT ALL ON TABLE vw_hidrometro_local_armazenagem TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_hidrometro_local_armazenagem TO pg_aplic;
GRANT SELECT ON TABLE vw_hidrometro_local_armazenagem TO pg_users;


--
-- TOC entry 5523 (class 0 OID 0)
-- Dependencies: 454
-- Name: vw_hidrometro_marca; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_hidrometro_marca FROM PUBLIC;
REVOKE ALL ON TABLE vw_hidrometro_marca FROM gsan_admin;
GRANT ALL ON TABLE vw_hidrometro_marca TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_hidrometro_marca TO pg_aplic;
GRANT SELECT ON TABLE vw_hidrometro_marca TO pg_users;


--
-- TOC entry 5524 (class 0 OID 0)
-- Dependencies: 455
-- Name: vw_hidrometro_motivo_baixa; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_hidrometro_motivo_baixa FROM PUBLIC;
REVOKE ALL ON TABLE vw_hidrometro_motivo_baixa FROM gsan_admin;
GRANT ALL ON TABLE vw_hidrometro_motivo_baixa TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_hidrometro_motivo_baixa TO pg_aplic;
GRANT SELECT ON TABLE vw_hidrometro_motivo_baixa TO pg_users;


--
-- TOC entry 5525 (class 0 OID 0)
-- Dependencies: 456
-- Name: vw_hidrometro_situacao; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_hidrometro_situacao FROM PUBLIC;
REVOKE ALL ON TABLE vw_hidrometro_situacao FROM gsan_admin;
GRANT ALL ON TABLE vw_hidrometro_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_hidrometro_situacao TO pg_aplic;
GRANT SELECT ON TABLE vw_hidrometro_situacao TO pg_users;


--
-- TOC entry 5526 (class 0 OID 0)
-- Dependencies: 457
-- Name: vw_hidrometro_tipo; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_hidrometro_tipo FROM PUBLIC;
REVOKE ALL ON TABLE vw_hidrometro_tipo FROM gsan_admin;
GRANT ALL ON TABLE vw_hidrometro_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_hidrometro_tipo TO pg_aplic;
GRANT SELECT ON TABLE vw_hidrometro_tipo TO pg_users;


--
-- TOC entry 5527 (class 0 OID 0)
-- Dependencies: 458
-- Name: vw_leitura_anormalidade; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_leitura_anormalidade FROM PUBLIC;
REVOKE ALL ON TABLE vw_leitura_anormalidade FROM gsan_admin;
GRANT ALL ON TABLE vw_leitura_anormalidade TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_leitura_anormalidade TO pg_aplic;
GRANT SELECT ON TABLE vw_leitura_anormalidade TO pg_users;


--
-- TOC entry 5528 (class 0 OID 0)
-- Dependencies: 459
-- Name: vw_leitura_anormalidade_consumo; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_leitura_anormalidade_consumo FROM PUBLIC;
REVOKE ALL ON TABLE vw_leitura_anormalidade_consumo FROM gsan_admin;
GRANT ALL ON TABLE vw_leitura_anormalidade_consumo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_leitura_anormalidade_consumo TO pg_aplic;
GRANT SELECT ON TABLE vw_leitura_anormalidade_consumo TO pg_users;


--
-- TOC entry 5529 (class 0 OID 0)
-- Dependencies: 460
-- Name: vw_leitura_anormalidade_informada; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_leitura_anormalidade_informada FROM PUBLIC;
REVOKE ALL ON TABLE vw_leitura_anormalidade_informada FROM gsan_admin;
GRANT ALL ON TABLE vw_leitura_anormalidade_informada TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_leitura_anormalidade_informada TO pg_aplic;
GRANT SELECT ON TABLE vw_leitura_anormalidade_informada TO pg_users;


--
-- TOC entry 5530 (class 0 OID 0)
-- Dependencies: 461
-- Name: vw_leitura_anormalidade_leitura; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_leitura_anormalidade_leitura FROM PUBLIC;
REVOKE ALL ON TABLE vw_leitura_anormalidade_leitura FROM gsan_admin;
GRANT ALL ON TABLE vw_leitura_anormalidade_leitura TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_leitura_anormalidade_leitura TO pg_aplic;
GRANT SELECT ON TABLE vw_leitura_anormalidade_leitura TO pg_users;


--
-- TOC entry 5531 (class 0 OID 0)
-- Dependencies: 462
-- Name: vw_leitura_situacao; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_leitura_situacao FROM PUBLIC;
REVOKE ALL ON TABLE vw_leitura_situacao FROM gsan_admin;
GRANT ALL ON TABLE vw_leitura_situacao TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_leitura_situacao TO pg_aplic;
GRANT SELECT ON TABLE vw_leitura_situacao TO pg_users;


--
-- TOC entry 5532 (class 0 OID 0)
-- Dependencies: 463
-- Name: vw_medicao_tipo; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_medicao_tipo FROM PUBLIC;
REVOKE ALL ON TABLE vw_medicao_tipo FROM gsan_admin;
GRANT ALL ON TABLE vw_medicao_tipo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_medicao_tipo TO pg_aplic;
GRANT SELECT ON TABLE vw_medicao_tipo TO pg_users;


--
-- TOC entry 5533 (class 0 OID 0)
-- Dependencies: 464
-- Name: vw_rota; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_rota FROM PUBLIC;
REVOKE ALL ON TABLE vw_rota FROM gsan_admin;
GRANT ALL ON TABLE vw_rota TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_rota TO pg_aplic;
GRANT SELECT ON TABLE vw_rota TO pg_users;


--
-- TOC entry 5534 (class 0 OID 0)
-- Dependencies: 465
-- Name: vw_un_resumo_indicador_coleta_esgoto; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_indicador_coleta_esgoto FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_indicador_coleta_esgoto FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_indicador_coleta_esgoto TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_indicador_coleta_esgoto TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_indicador_coleta_esgoto TO pg_users;


--
-- TOC entry 5535 (class 0 OID 0)
-- Dependencies: 466
-- Name: vw_un_resumo_indicador_consumo_agua; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_indicador_consumo_agua FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_indicador_consumo_agua FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_indicador_consumo_agua TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_indicador_consumo_agua TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_indicador_consumo_agua TO pg_users;


--
-- TOC entry 5536 (class 0 OID 0)
-- Dependencies: 467
-- Name: vw_un_resumo_indicador_agua_esgoto; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_indicador_agua_esgoto FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_indicador_agua_esgoto FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_indicador_agua_esgoto TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_indicador_agua_esgoto TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_indicador_agua_esgoto TO pg_users;


--
-- TOC entry 5537 (class 0 OID 0)
-- Dependencies: 468
-- Name: vw_un_resumo_indicador_hidrometro; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_indicador_hidrometro FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_indicador_hidrometro FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_indicador_hidrometro TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_indicador_hidrometro TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_indicador_hidrometro TO pg_users;


--
-- TOC entry 5538 (class 0 OID 0)
-- Dependencies: 469
-- Name: vw_un_resumo_indicador_leitura_anormalidade; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_indicador_leitura_anormalidade FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_indicador_leitura_anormalidade FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_indicador_leitura_anormalidade TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_indicador_leitura_anormalidade TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_indicador_leitura_anormalidade TO pg_users;


--
-- TOC entry 5539 (class 0 OID 0)
-- Dependencies: 470
-- Name: vw_un_resumo_indicador_anormalidade_hidrometro; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_indicador_anormalidade_hidrometro FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_indicador_anormalidade_hidrometro FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_indicador_anormalidade_hidrometro TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_indicador_anormalidade_hidrometro TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_indicador_anormalidade_hidrometro TO pg_users;


--
-- TOC entry 5540 (class 0 OID 0)
-- Dependencies: 471
-- Name: vw_un_resi_des_mmd; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resi_des_mmd FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resi_des_mmd FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resi_des_mmd TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resi_des_mmd TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resi_des_mmd TO pg_users;


--
-- TOC entry 5541 (class 0 OID 0)
-- Dependencies: 472
-- Name: vw_un_resumo_indicador_faturamento; Type: ACL; Schema: micromedicao; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_un_resumo_indicador_faturamento FROM PUBLIC;
REVOKE ALL ON TABLE vw_un_resumo_indicador_faturamento FROM gsan_admin;
GRANT ALL ON TABLE vw_un_resumo_indicador_faturamento TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_un_resumo_indicador_faturamento TO pg_aplic;
GRANT SELECT ON TABLE vw_un_resumo_indicador_faturamento TO pg_users;


SET search_path = myschema, pg_catalog;

--
-- TOC entry 5542 (class 0 OID 0)
-- Dependencies: 473
-- Name: foo; Type: ACL; Schema: myschema; Owner: gsan_admin
--

REVOKE ALL ON TABLE foo FROM PUBLIC;
REVOKE ALL ON TABLE foo FROM gsan_admin;
GRANT ALL ON TABLE foo TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE foo TO pg_aplic;
GRANT SELECT ON TABLE foo TO pg_users;


SET search_path = operacional, pg_catalog;

--
-- TOC entry 5548 (class 0 OID 0)
-- Dependencies: 474
-- Name: g_distrito_operacional; Type: ACL; Schema: operacional; Owner: gsan_admin
--

REVOKE ALL ON TABLE g_distrito_operacional FROM PUBLIC;
REVOKE ALL ON TABLE g_distrito_operacional FROM gsan_admin;
GRANT ALL ON TABLE g_distrito_operacional TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE g_distrito_operacional TO pg_aplic;
GRANT SELECT ON TABLE g_distrito_operacional TO pg_users;


--
-- TOC entry 5549 (class 0 OID 0)
-- Dependencies: 475
-- Name: vw_distrito_operacional; Type: ACL; Schema: operacional; Owner: gsan_admin
--

REVOKE ALL ON TABLE vw_distrito_operacional FROM PUBLIC;
REVOKE ALL ON TABLE vw_distrito_operacional FROM gsan_admin;
GRANT ALL ON TABLE vw_distrito_operacional TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vw_distrito_operacional TO pg_aplic;
GRANT SELECT ON TABLE vw_distrito_operacional TO pg_users;


SET search_path = public, pg_catalog;

--
-- TOC entry 5554 (class 0 OID 0)
-- Dependencies: 482
-- Name: un_resumo_ligacao_economia; Type: ACL; Schema: public; Owner: gsan_admin
--

REVOKE ALL ON TABLE un_resumo_ligacao_economia FROM PUBLIC;
REVOKE ALL ON TABLE un_resumo_ligacao_economia FROM gsan_admin;
GRANT ALL ON TABLE un_resumo_ligacao_economia TO gsan_admin;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE un_resumo_ligacao_economia TO pg_aplic;
GRANT SELECT ON TABLE un_resumo_ligacao_economia TO pg_users;


-- Completed on 2014-02-27 15:53:17 BRT

--
-- PostgreSQL database dump complete
--

