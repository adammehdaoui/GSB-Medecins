-- Database: medecins

-- DROP DATABASE medecins;

CREATE DATABASE medecins
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'French_France.1252'
    LC_CTYPE = 'French_France.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- Table: public.pays

-- DROP TABLE public.pays;

CREATE TABLE IF NOT EXISTS public.pays
(
    id bigint NOT NULL,
    nom character varying(255) COLLATE pg_catalog."default",
    code_iso character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT pays_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.pays
    OWNER to postgres;

-- Table: public.departement

-- DROP TABLE public.departement;

CREATE TABLE IF NOT EXISTS public.departement
(
    id integer NOT NULL,
    libelle character varying(255) COLLATE pg_catalog."default",
    pays_id bigint,
    codepostal character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT departement_pkey PRIMARY KEY (id),
    CONSTRAINT fk2n9v469i17oomjtuewplf1agv FOREIGN KEY (pays_id)
        REFERENCES public.pays (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.departement
    OWNER to postgres;

-- Table: public.specialitecomplementaire

-- DROP TABLE public.specialitecomplementaire;

CREATE TABLE IF NOT EXISTS public.specialitecomplementaire
(
    id bigint NOT NULL,
    libelle character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT specialitecomplementaire_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.specialitecomplementaire
    OWNER to postgres;

-- Table: public.medecin

-- DROP TABLE public.medecin;

CREATE TABLE IF NOT EXISTS public.medecin
(
    id integer NOT NULL DEFAULT nextval('medecin_id_seq'::regclass),
    nom character varying(30) COLLATE pg_catalog."default" NOT NULL,
    prenom character varying(30) COLLATE pg_catalog."default" NOT NULL,
    adresse character varying(80) COLLATE pg_catalog."default" NOT NULL,
    tel character varying(15) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    specialitecomplementaire_id bigint,
    departement_id integer,
    CONSTRAINT medecin_pkey PRIMARY KEY (id),
    CONSTRAINT fkkixkj0llfwxgo1ivid3x4f175 FOREIGN KEY (departement_id)
        REFERENCES public.departement (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkn9wotrggvol9y1amauotseuxf FOREIGN KEY (specialitecomplementaire_id)
        REFERENCES public.specialitecomplementaire (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.medecin
    OWNER to postgres;

-- Trigger: trig_save_med

-- DROP TRIGGER trig_save_med ON public.medecin;

CREATE TRIGGER trig_save_med
    AFTER DELETE
    ON public.medecin
    FOR EACH ROW
    EXECUTE FUNCTION public.save_med();

-- Table: public.backupmed

-- DROP TABLE public.backupmed;

CREATE TABLE IF NOT EXISTS public.backupmed
(
    id integer NOT NULL DEFAULT nextval('backupmed_id_seq'::regclass),
    nom character varying(255) COLLATE pg_catalog."default",
    prenom character varying(255) COLLATE pg_catalog."default",
    adresse character varying(255) COLLATE pg_catalog."default",
    tel character varying(255) COLLATE pg_catalog."default",
    spe_id integer,
    dep_id integer,
    CONSTRAINT backupmed_pkey PRIMARY KEY (id),
    CONSTRAINT backupmed_dep_id_fkey FOREIGN KEY (dep_id)
        REFERENCES public.departement (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT backupmed_spe_id_fkey FOREIGN KEY (spe_id)
        REFERENCES public.specialitecomplementaire (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.backupmed
    OWNER to postgres;