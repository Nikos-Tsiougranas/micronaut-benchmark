DO
$$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO benchmark values (
            nextval('benchmark_seq'),
            now()
        );
    END LOOP;
END
$$;