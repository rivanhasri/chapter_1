CREATE OR REPLACE PROCEDURE public.delete_user(req_id bigint)
    LANGUAGE plpgsql
AS $procedure$
BEGIN
    DELETE FROM users WHERE id = req_id;
END;
$procedure$;
