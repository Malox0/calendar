--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4 (Postgres.app)
-- Dumped by pg_dump version 17.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: addresses; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.addresses (
    a_l_id integer,
    a_s_id bigint,
    a_firstname character varying(255),
    a_id character varying(255) NOT NULL,
    a_info character varying(255),
    a_lastname character varying(255),
    a_street character varying(255),
    a_supplement character varying(255),
    sc_a_id character varying(255)
);


--
-- Name: contacts; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.contacts (
    c_fax character varying(255),
    c_id character varying(255) NOT NULL,
    c_mail character varying(255),
    c_phonenumber character varying(255)
);




--
-- Name: locations; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.locations (
    l_id integer NOT NULL,
    l_postcode character varying(255),
    l_value character varying(255)
);


--
-- Name: marks; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.marks (
    m_id bigint NOT NULL,
    m_color integer NOT NULL,
    m_text character varying(100) NOT NULL
);


--
-- Name: marks_on_schedules; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.marks_on_schedules (
    mon_sc_id bigint NOT NULL,
    mon_m_id bigint NOT NULL
);


--
-- Name: salutation; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.salutation (
    s_id integer NOT NULL,
    s_value character varying(200)
);


--
-- Name: salutations; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.salutations (
    s_id bigint NOT NULL,
    s_value character varying(255)
);


--
-- Name: schedules; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.schedules (
    sc_id bigint NOT NULL,
    sc_from date NOT NULL,
    sc_until date NOT NULL,
    sc_from_time time without time zone NOT NULL,
    sc_until_time time without time zone NOT NULL,
    sc_allday boolean NOT NULL,
    sc_title character varying(250) NOT NULL,
    sc_description character varying(500) NOT NULL,
    sc_a_id character varying(100) NOT NULL,
    sc_m_id bigint NOT NULL
);


--
-- Name: addresses addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (a_id);


--
-- Name: contacts contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.contacts
    ADD CONSTRAINT contacts_pkey PRIMARY KEY (c_id);


-- Name: locations locations_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (l_id);


--
-- Name: marks_on_schedules marks_on_schedules_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.marks_on_schedules
    ADD CONSTRAINT marks_on_schedules_pk PRIMARY KEY (mon_sc_id, mon_m_id);


--
-- Name: marks marks_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.marks
    ADD CONSTRAINT marks_pk PRIMARY KEY (m_id);


--
-- Name: salutation salutation_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.salutation
    ADD CONSTRAINT salutation_pk PRIMARY KEY (s_id);


--
-- Name: salutations salutations_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.salutations
    ADD CONSTRAINT salutations_pkey PRIMARY KEY (s_id);


--
-- Name: schedules schedules_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.schedules
    ADD CONSTRAINT schedules_pk PRIMARY KEY (sc_id);


--
-- Name: marks_on_schedules marks_on_schedules_marks; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.marks_on_schedules
    ADD CONSTRAINT marks_on_schedules_marks FOREIGN KEY (mon_m_id) REFERENCES public.marks(m_id);


--
-- Name: marks_on_schedules marks_on_schedules_schedules; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.marks_on_schedules
    ADD CONSTRAINT marks_on_schedules_schedules FOREIGN KEY (mon_sc_id) REFERENCES public.schedules(sc_id);


--
-- Name: schedules schedules_addresses; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.schedules
    ADD CONSTRAINT schedules_addresses FOREIGN KEY (sc_a_id) REFERENCES public.addresses(a_id);


--
-- Name: schedules schedules_marks; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.schedules
    ADD CONSTRAINT schedules_marks FOREIGN KEY (sc_m_id) REFERENCES public.marks(m_id);


--
-- PostgreSQL database dump complete
--

