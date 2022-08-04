--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: kinetiqa; Type: DATABASE; Schema: -; Owner: doadmin
--

CREATE DATABASE kinetiqa WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.UTF-8';


ALTER DATABASE kinetiqa OWNER TO doadmin;

\connect kinetiqa

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- Name: courses; Type: TABLE; Schema: public; Owner: doadmin
--

CREATE TABLE public.courses (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    drug_id integer NOT NULL,
    when_added timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.courses OWNER TO doadmin;

--
-- Name: courses_id_seq; Type: SEQUENCE; Schema: public; Owner: doadmin
--

CREATE SEQUENCE public.courses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.courses_id_seq OWNER TO doadmin;

--
-- Name: courses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: doadmin
--

ALTER SEQUENCE public.courses_id_seq OWNED BY public.courses.id;


--
-- Name: drugs; Type: TABLE; Schema: public; Owner: doadmin
--

CREATE TABLE public.drugs (
    id integer NOT NULL,
    label_name character varying(100) NOT NULL,
    iupac character varying(1000) NOT NULL,
    description text DEFAULT ''::text NOT NULL,
    kinetics_plot text DEFAULT ''::text NOT NULL,
    photo_url text DEFAULT ''::text NOT NULL,
    standard_dosage_mg numeric(9,5) DEFAULT 0 NOT NULL,
    dosage_step_mg numeric(9,5) DEFAULT 1 NOT NULL
);


ALTER TABLE public.drugs OWNER TO doadmin;

--
-- Name: drugs_id_seq; Type: SEQUENCE; Schema: public; Owner: doadmin
--

CREATE SEQUENCE public.drugs_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.drugs_id_seq OWNER TO doadmin;

--
-- Name: drugs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: doadmin
--

ALTER SEQUENCE public.drugs_id_seq OWNED BY public.drugs.id;


--
-- Name: intakes; Type: TABLE; Schema: public; Owner: doadmin
--

CREATE TABLE public.intakes (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    drug_id integer NOT NULL,
    mass_intook_mg integer NOT NULL,
    time_when timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.intakes OWNER TO doadmin;

--
-- Name: intakes_id_seq; Type: SEQUENCE; Schema: public; Owner: doadmin
--

CREATE SEQUENCE public.intakes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.intakes_id_seq OWNER TO doadmin;

--
-- Name: intakes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: doadmin
--

ALTER SEQUENCE public.intakes_id_seq OWNED BY public.intakes.id;


--
-- Name: metabolisms; Type: TABLE; Schema: public; Owner: doadmin
--

CREATE TABLE public.metabolisms (
    id bigint NOT NULL,
    drug_id integer NOT NULL,
    metabolite_id integer NOT NULL,
    scale numeric(36,27) DEFAULT 1.0 NOT NULL
);


ALTER TABLE public.metabolisms OWNER TO doadmin;

--
-- Name: metabolism_id_seq; Type: SEQUENCE; Schema: public; Owner: doadmin
--

CREATE SEQUENCE public.metabolism_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.metabolism_id_seq OWNER TO doadmin;

--
-- Name: metabolism_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: doadmin
--

ALTER SEQUENCE public.metabolism_id_seq OWNED BY public.metabolisms.id;


--
-- Name: metabolites; Type: TABLE; Schema: public; Owner: doadmin
--

CREATE TABLE public.metabolites (
    id integer NOT NULL,
    label_name character varying(100) NOT NULL,
    iupac character varying(1000) NOT NULL,
    description text DEFAULT ''::text NOT NULL,
    kinetics_plot text DEFAULT ''::text NOT NULL,
    photo_url text DEFAULT ''::text NOT NULL
);


ALTER TABLE public.metabolites OWNER TO doadmin;

--
-- Name: metabolites_id_seq; Type: SEQUENCE; Schema: public; Owner: doadmin
--

CREATE SEQUENCE public.metabolites_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.metabolites_id_seq OWNER TO doadmin;

--
-- Name: metabolites_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: doadmin
--

ALTER SEQUENCE public.metabolites_id_seq OWNED BY public.metabolites.id;


--
-- Name: schedules; Type: TABLE; Schema: public; Owner: doadmin
--

CREATE TABLE public.schedules (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    drug_id integer NOT NULL,
    comment text DEFAULT ''::text,
    when_taken timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    dosage_mg numeric(9,5),
    week_day smallint,
    time_of_day time without time zone
);


ALTER TABLE public.schedules OWNER TO doadmin;

--
-- Name: schedule_id_seq; Type: SEQUENCE; Schema: public; Owner: doadmin
--

CREATE SEQUENCE public.schedule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.schedule_id_seq OWNER TO doadmin;

--
-- Name: schedule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: doadmin
--

ALTER SEQUENCE public.schedule_id_seq OWNED BY public.schedules.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: doadmin
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(100) NOT NULL,
    password_hash character varying(100) NOT NULL,
    password_salt character varying(100),
    google_auth character varying(100),
    person_name character varying(100),
    date_birth date,
    mass_kg numeric(5,2),
    height_cm integer,
    gender smallint
);


ALTER TABLE public.users OWNER TO doadmin;

--
-- Name: table_name_id_seq; Type: SEQUENCE; Schema: public; Owner: doadmin
--

CREATE SEQUENCE public.table_name_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.table_name_id_seq OWNER TO doadmin;

--
-- Name: table_name_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: doadmin
--

ALTER SEQUENCE public.table_name_id_seq OWNED BY public.users.id;


--
-- Name: courses id; Type: DEFAULT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.courses ALTER COLUMN id SET DEFAULT nextval('public.courses_id_seq'::regclass);


--
-- Name: drugs id; Type: DEFAULT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.drugs ALTER COLUMN id SET DEFAULT nextval('public.drugs_id_seq'::regclass);


--
-- Name: intakes id; Type: DEFAULT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.intakes ALTER COLUMN id SET DEFAULT nextval('public.intakes_id_seq'::regclass);


--
-- Name: metabolisms id; Type: DEFAULT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.metabolisms ALTER COLUMN id SET DEFAULT nextval('public.metabolism_id_seq'::regclass);


--
-- Name: metabolites id; Type: DEFAULT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.metabolites ALTER COLUMN id SET DEFAULT nextval('public.metabolites_id_seq'::regclass);


--
-- Name: schedules id; Type: DEFAULT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.schedules ALTER COLUMN id SET DEFAULT nextval('public.schedule_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.table_name_id_seq'::regclass);


--
-- Data for Name: courses; Type: TABLE DATA; Schema: public; Owner: doadmin
--

COPY public.courses (id, user_id, drug_id, when_added) FROM stdin;
4	1	3	2022-06-28 13:47:01.454677+00
11	1	2	2022-06-29 00:13:04.311581+00
12	1	6	2022-06-29 02:17:41.736271+00
13	1	4	2022-06-29 16:27:23.367982+00
14	1	5	2022-06-29 16:27:24.46537+00
\.


--
-- Data for Name: drugs; Type: TABLE DATA; Schema: public; Owner: doadmin
--

COPY public.drugs (id, label_name, iupac, description, kinetics_plot, photo_url, standard_dosage_mg, dosage_step_mg) FROM stdin;
3	Лоратадин	этиловый эфир 4-(8-хлор-5,6-дигидро-11H-бензо-[5,6]циклогепта[1,2-b]пиридин-11-илиден)-1-пиперидинкарбоновой кислоты	Лоратади́н — блокатор H1-гистаминовых рецепторов, препарат длительного действия.\n\nЛоратадин входит в перечень жизненно необходимых и важнейших лекарственных препаратов. Одобрен FDA в 1993 году.\n\nНа 2001 год являлся наиболее назначаемым в мире противоаллергическим препаратом с 1994 года (по данным компании IMS).	{"T_max": 69, "T_1/2": 504}	https://storage.googleapis.com/medicine-packaging/loratadin.png	10.00000	5.00000
5	Ибупрофен	(RS)-2-(4-изобутилфенил)-пропионовая кислота	Ибупрофе́н — лекарственное средство, нестероидный противовоспалительный препарат из группы производных пропионовой кислоты, обладающий болеутоляющим и жаропонижающим действием.\n\nОбщие побочные эффекты включают изжогу и сыпь, но по сравнению с другими НПВС он реже вызывает желудочно-кишечные кровотечения. Может увеличивать риск сердечной недостаточности, почечной и печёночной недостаточности. При низких дозах он не увеличивает риск сердечного приступа, однако это возможно при применении в более высоких дозах. Ибупрофен также может ухудшить состояние больных астмой. Пока неясно, безопасно ли применение лекарства на ранних сроках беременности, оно, по-видимому, вредно для более поздних сроков беременности и поэтому не рекомендуется. Как и другие НПВС, ибупрофен действует путём ингибирования выработки простагландинов за счёт снижения активности фермента циклооксигеназы. Ибупрофен может быть более слабым противовоспалительным, чем другие НПВС.\n\nМеханизм действия и профиль ибупрофена хорошо изучены, его эффективность клинически доказана, в связи с чем данный препарат входит в список важнейших лекарственных средств Всемирной организации здравоохранения, а также в перечень жизненно необходимых и важнейших лекарственных средств, утверждённый распоряжением Правительства Российской Федерации от 30.12.2009 № 2135-р.	{"T_max": 90, "T_1/2": 180}	https://storage.googleapis.com/medicine-packaging/ibuprofen.png	200.00000	50.00000
1	Парацетамол	N-(4-гидроксифенил)ацетамид	Парацетамо́л (лат. Paracetamolum) — лекарственное средство, анальгетик и антипиретик из группы анилидов, оказывает жаропонижающее действие. В некоторых западных странах известен под названием ацетаминофен (англ. Acetaminophen, APAP). Название образовано как сокращение от полного названия в химической номенклатуре: пара-ацетиламинофенол.\n\nЯвляется широко распространённым центральным ненаркотическим анальгетиком, обладает довольно слабыми противовоспалительными свойствами. Вместе с тем при приёме больших доз может вызывать нарушения работы печени, кровеносной системы и почек. Риски нарушений работы данных органов и систем увеличивается при одновременном принятии спиртного, поэтому лицам, употребляющим алкоголь, рекомендуют употреблять пониженную дозу парацетамола.\n\nПарацетамол не является нестероидным противовоспалительным препаратом, механизм его действия принципиально иной. В отличие от ибупрофена, аспирина и других НПВП, парацетамол воздействует на нервную систему и относится к другой классификационной группе.\n\nПарацетамол входит в список важнейших лекарственных средств Всемирной организации здравоохранения, а также в перечень жизненно необходимых и важнейших лекарственных препаратов РФ.	{"T_max": 75, "T_1/2": 132}	https://storage.googleapis.com/medicine-packaging/paracetamol.png	500.00000	50.00000
2	Флуоксетин	(RS)-N-метил-3-фенил-3-[4-(трифторметил)фенокси]пропан-1-амин	Флуоксети́н (лат. Fluoxetinum) — антидепрессант, один из основных представителей группы селективных ингибиторов обратного захвата серотонина. Антидепрессивное действие сочетается у него с психостимулирующим. Улучшает настроение, снижает напряжённость, тревожность и чувство страха, устраняет дисфорию. Не вызывает ортостатической гипотензии, седативного эффекта, не кардиотоксичен. Обладает анорексигенным свойством, которое применяется в медицине с целью борьбы с клинической нервной булимией.\n\nФлуоксетин предпочтителен при депрессиях, протекающих с моторной заторможенностью и гиперсомнией, он может плохо переноситься пациентами с психомоторным возбуждением, с тревогой и бессонницей — такого рода симптоматику он способен усугублять.\n\nФлуоксетин был впервые зарегистрирован в 1974 году учёными из Eli Lilly and Company. Он был представлен Управлению по санитарному надзору за качеством пищевых продуктов и медикаментов США в феврале 1977 года. Eli Lilly получила окончательное разрешение на продажу препарата в декабре 1987 года. Флуоксетин вышел из-под патентной защиты в августе 2001 года.\n\nК 2011 году, несмотря на то что на фармакологическом рынке появилось много антидепрессантов, флуоксетин занимал лидирующие позиции. Так, в США в 2010 году было выписано свыше 24,4 миллиона рецептов на флуоксетин, что сделало его третьим по популярности выписанным антидепрессантом после сертралина и циталопрама. В 2011 году в Великобритании было выписано 6 миллионов рецептов на флуоксетин.	{"T_max": 420, "T_1/2": 3600}	https://storage.googleapis.com/medicine-packaging/fluoxetine.png	20.00000	10.00000
7	Ацетилцистеин	(R)-2-ацетамидо-3-сульфанилпропановая кислота	Ацетилцистеин (N-ацетил-L-цистеин, NAC) — муколитическое, отхаркивающее, антиоксидантное средство. Применяют при инфекционных заболеваниях, которые могут сопровождаться образованием вязкой трудноотделяемой мокро́ты, в том числе при респираторных заболеваниях верхних и нижних дыхательных путей. Назначают также при отитах, ринитах и синуситах.\n\nАцетилцистеин входит в перечень ЖНВЛП Минздрава РФ.	{"T_max": 30, "T_1/2": 336}	https://storage.googleapis.com/medicine-packaging/nac.png	200.00000	100.00000
6	Ацетилсалициловая кислота	2-ацетилоксибензойная кислота	Ацетилсалици́ловая кислота́ (разг. аспири́н; лат. Acidum acetylsalicylicum) — лекарственное средство, оказывающее анальгезирующее (обезболивающее), жаропонижающее, противовоспалительное действие. Также ацетилсалициловая кислота является блокатором циклооксигеназы тромбоцитов (не путать с антикоагулянтами).\n\nМеханизм действия и профиль безопасности ацетилсалициловой кислоты хорошо изучены, её эффективность клинически апробирована, в связи с чем данный препарат входит в список важнейших лекарственных средств Всемирной организации здравоохранения, а также в перечень жизненно необходимых и важнейших лекарственных средств Российской Федерации.\n\nАцетилсалициловая кислота также широко известна под торговой маркой «Аспирин», запатентованной фирмой «Байер».	{"T_max": 30, "T_1/2": 31}	https://storage.googleapis.com/medicine-packaging/aspirin.png	500.00000	50.00000
4	Метформин	N,N-диметилимиддикарбоимид диамид	Метформи́н — таблетированное сахароснижающее лекарственное средство класса бигуанидов для приёма внутрь (торговое наименование Мерифатин). Этот препарат применяется при лечении сахарного диабета 2-го типа, особенно у лиц с избыточным весом и ожирением и при этом сохранённой нормальной функцией почек. Проводятся исследования по применению метформина при гестационном диабете и синдроме поликистозных яичников. Препарат исследован и для других заболеваний, при которых резистентность к инсулину может быть важным фактором.\n\nПри правильном назначении метформин вызывает мало побочных эффектов (среди которых чаще возникают желудочно-кишечные расстройства) и связан с низким риском гипогликемии. Лактоацидоз (накопление молочной кислоты в крови) может быть серьёзной проблемой при передозировке и при назначении людям с противопоказаниями, но в остальном значительного риска нет. Метформин способствует снижению уровня холестерина ЛПНП и триглицеридов и не вызывает увеличения массы тела. Один из наиболее значимых эффектов Метформина — достоверное снижение смертности от осложнений сердечно-сосудистых заболеваний при сахарном диабете. Входит в список важнейших лекарственных препаратов Всемирной организации здравоохранения наряду с другим пероральным антидиабетическим препаратом глибенкламидом.	{"T_max": 150, "T_1/2": 372}	https://storage.googleapis.com/medicine-packaging/metformin2.png	500.00000	50.00000
\.


--
-- Data for Name: intakes; Type: TABLE DATA; Schema: public; Owner: doadmin
--

COPY public.intakes (id, user_id, drug_id, mass_intook_mg, time_when) FROM stdin;
1	1	2	20	2022-06-28 21:17:33.693016+00
2	1	2	20	2022-06-28 21:17:35.240157+00
3	1	5	200	2022-06-28 21:17:47.183714+00
4	1	4	500	2022-06-28 21:18:07.558986+00
5	1	4	500	2022-06-28 21:19:30.053977+00
6	1	3	10	2022-06-28 21:58:50.991703+00
7	1	6	500	2022-06-29 02:17:50.417151+00
8	1	6	500	2022-06-29 02:17:51.137608+00
9	1	6	500	2022-06-29 02:17:51.891884+00
10	1	6	500	2022-06-29 02:17:52.767703+00
11	1	2	20	2022-06-29 10:18:54.098225+00
12	1	2	20	2022-06-29 10:18:54.369976+00
13	1	2	20	2022-06-29 10:18:56.246199+00
14	1	5	200	2022-06-29 16:27:33.757776+00
15	1	5	200	2022-06-29 16:27:35.036036+00
\.


--
-- Data for Name: metabolisms; Type: TABLE DATA; Schema: public; Owner: doadmin
--

COPY public.metabolisms (id, drug_id, metabolite_id, scale) FROM stdin;
\.


--
-- Data for Name: metabolites; Type: TABLE DATA; Schema: public; Owner: doadmin
--

COPY public.metabolites (id, label_name, iupac, description, kinetics_plot, photo_url) FROM stdin;
\.


--
-- Data for Name: schedules; Type: TABLE DATA; Schema: public; Owner: doadmin
--

COPY public.schedules (id, user_id, drug_id, comment, when_taken, dosage_mg, week_day, time_of_day) FROM stdin;
1	1	1		2022-06-28 21:17:22.76321+00	500.00000	2	21:17:22
2	1	1		2022-06-28 21:17:27.113378+00	500.00000	2	21:17:27
3	1	4		2022-06-28 21:17:27.868128+00	500.00000	2	21:17:27
4	1	5		2022-06-28 21:17:29.073777+00	200.00000	2	21:17:29
5	1	2		2022-06-28 21:17:30.552773+00	20.00000	2	21:17:30
6	1	4		2022-06-28 21:18:11.338148+00	500.00000	2	21:18:11
7	1	1		2022-06-28 21:18:12.293883+00	500.00000	2	21:18:12
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: doadmin
--

COPY public.users (id, email, password_hash, password_salt, google_auth, person_name, date_birth, mass_kg, height_cm, gender) FROM stdin;
1	aaaaa	bbbbb				2022-06-08	0.00	0	0
\.


--
-- Name: courses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: doadmin
--

SELECT pg_catalog.setval('public.courses_id_seq', 14, true);


--
-- Name: drugs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: doadmin
--

SELECT pg_catalog.setval('public.drugs_id_seq', 7, true);


--
-- Name: intakes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: doadmin
--

SELECT pg_catalog.setval('public.intakes_id_seq', 15, true);


--
-- Name: metabolism_id_seq; Type: SEQUENCE SET; Schema: public; Owner: doadmin
--

SELECT pg_catalog.setval('public.metabolism_id_seq', 1, false);


--
-- Name: metabolites_id_seq; Type: SEQUENCE SET; Schema: public; Owner: doadmin
--

SELECT pg_catalog.setval('public.metabolites_id_seq', 1, false);


--
-- Name: schedule_id_seq; Type: SEQUENCE SET; Schema: public; Owner: doadmin
--

SELECT pg_catalog.setval('public.schedule_id_seq', 7, true);


--
-- Name: table_name_id_seq; Type: SEQUENCE SET; Schema: public; Owner: doadmin
--

SELECT pg_catalog.setval('public.table_name_id_seq', 1, true);


--
-- Name: courses courses_pk; Type: CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pk PRIMARY KEY (id);


--
-- Name: drugs drugs_iupac_key; Type: CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.drugs
    ADD CONSTRAINT drugs_iupac_key UNIQUE (iupac);


--
-- Name: drugs drugs_label_name_key; Type: CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.drugs
    ADD CONSTRAINT drugs_label_name_key UNIQUE (label_name);


--
-- Name: drugs drugs_pk; Type: CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.drugs
    ADD CONSTRAINT drugs_pk PRIMARY KEY (id);


--
-- Name: intakes intakes_pk; Type: CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.intakes
    ADD CONSTRAINT intakes_pk PRIMARY KEY (id);


--
-- Name: metabolisms metabolism_drug_metabolite_key; Type: CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.metabolisms
    ADD CONSTRAINT metabolism_drug_metabolite_key UNIQUE (drug_id, metabolite_id);


--
-- Name: metabolisms metabolism_pk; Type: CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.metabolisms
    ADD CONSTRAINT metabolism_pk PRIMARY KEY (id);


--
-- Name: metabolites metabolites_iupac_key; Type: CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.metabolites
    ADD CONSTRAINT metabolites_iupac_key UNIQUE (iupac);


--
-- Name: metabolites metabolites_label_name_key; Type: CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.metabolites
    ADD CONSTRAINT metabolites_label_name_key UNIQUE (label_name);


--
-- Name: metabolites metabolites_pk; Type: CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.metabolites
    ADD CONSTRAINT metabolites_pk PRIMARY KEY (id);


--
-- Name: schedules schedule_pk; Type: CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.schedules
    ADD CONSTRAINT schedule_pk PRIMARY KEY (id);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pk; Type: CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);


--
-- Name: courses_user_drug_index; Type: INDEX; Schema: public; Owner: doadmin
--

CREATE INDEX courses_user_drug_index ON public.courses USING btree (user_id, drug_id);


--
-- Name: intakes_user_drug_index; Type: INDEX; Schema: public; Owner: doadmin
--

CREATE INDEX intakes_user_drug_index ON public.intakes USING btree (user_id, drug_id);


--
-- Name: schedule_user_drug_index; Type: INDEX; Schema: public; Owner: doadmin
--

CREATE INDEX schedule_user_drug_index ON public.schedules USING btree (user_id, drug_id);


--
-- Name: courses courses_drugs_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_drugs_id_fk FOREIGN KEY (drug_id) REFERENCES public.drugs(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: courses courses_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_users_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: intakes intakes_drugs_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.intakes
    ADD CONSTRAINT intakes_drugs_id_fk FOREIGN KEY (drug_id) REFERENCES public.drugs(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: intakes intakes_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.intakes
    ADD CONSTRAINT intakes_users_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: metabolisms metabolisms_drugs_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.metabolisms
    ADD CONSTRAINT metabolisms_drugs_id_fk FOREIGN KEY (drug_id) REFERENCES public.drugs(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: metabolisms metabolisms_metabolites_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.metabolisms
    ADD CONSTRAINT metabolisms_metabolites_id_fk FOREIGN KEY (metabolite_id) REFERENCES public.metabolites(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: schedules schedules_drugs_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.schedules
    ADD CONSTRAINT schedules_drugs_id_fk FOREIGN KEY (drug_id) REFERENCES public.drugs(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: schedules schedules_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: doadmin
--

ALTER TABLE ONLY public.schedules
    ADD CONSTRAINT schedules_users_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: DATABASE kinetiqa; Type: ACL; Schema: -; Owner: doadmin
--

GRANT ALL ON DATABASE kinetiqa TO apiuser;
GRANT CONNECT ON DATABASE kinetiqa TO readaccess;


--
-- Name: TABLE courses; Type: ACL; Schema: public; Owner: doadmin
--

GRANT SELECT ON TABLE public.courses TO readaccess;


--
-- Name: TABLE drugs; Type: ACL; Schema: public; Owner: doadmin
--

GRANT SELECT ON TABLE public.drugs TO readaccess;


--
-- Name: TABLE intakes; Type: ACL; Schema: public; Owner: doadmin
--

GRANT SELECT ON TABLE public.intakes TO readaccess;


--
-- Name: TABLE metabolisms; Type: ACL; Schema: public; Owner: doadmin
--

GRANT SELECT ON TABLE public.metabolisms TO readaccess;


--
-- Name: TABLE metabolites; Type: ACL; Schema: public; Owner: doadmin
--

GRANT SELECT ON TABLE public.metabolites TO readaccess;


--
-- Name: TABLE schedules; Type: ACL; Schema: public; Owner: doadmin
--

GRANT SELECT ON TABLE public.schedules TO readaccess;


--
-- Name: TABLE users; Type: ACL; Schema: public; Owner: doadmin
--

GRANT SELECT ON TABLE public.users TO readaccess;


--
-- PostgreSQL database dump complete
--

