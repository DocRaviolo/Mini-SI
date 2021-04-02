create table if not exists plat
(
    id serial not null
    constraint plat_pk
    primary key,
    nom varchar,
    prix_unitaire double precision,
    prix_de_revient double precision
);

alter table plat owner to postgres;

create table if not exists serveur
(
    id serial not null
    constraint serveur_pk
    primary key,
    nom varchar
);

alter table serveur owner to postgres;

create table if not exists site
(
    id serial not null
    constraint table_pk
    primary key,
    nom varchar,
    nb_places integer
);

alter table site owner to postgres;

create table if not exists facture
(
    id serial not null
    constraint facture_pk
    primary key,
    idx_site integer
    constraint facture_table_id_fk
    references site,
    idx_serveur integer
    constraint facture_serveur_id_fk
    references serveur
);

alter table facture owner to postgres;

create table if not exists join_plat_facture
(
    id serial not null
    constraint join_plat_facture_pk
    primary key,
    idx_plat integer
    constraint join_plat_facture_plat_id_fk
    references plat,
    idx_facture integer
    constraint join_plat_facture_facture_id_fk
    references facture,
    quantite integer
);

alter table join_plat_facture owner to postgres;

