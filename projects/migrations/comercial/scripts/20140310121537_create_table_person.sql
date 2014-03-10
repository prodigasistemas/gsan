-- // create table person
-- Migration SQL that makes the change goes here.
create table public.person (
  id integer not null,
  name varchar(30)
);

ALTER TABLE public.person
  OWNER TO gsan_admin;

-- //@UNDO
-- SQL to undo the change goes here.
drop table public.person;

