DO
$$
BEGIN
create TABLE IF NOT EXISTS benchmark(
    id numeric NOT NULL,
    created_at timestamp without time zone,
    CONSTRAINT benchmark_pk PRIMARY KEY (id)
);

CREATE SEQUENCE benchmark_seq
      START WITH 1
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      CACHE 1;
END
$$;