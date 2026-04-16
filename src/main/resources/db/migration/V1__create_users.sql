CREATE TABLE IF NOT EXISTS "users" (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    name VARCHAR(20) not null,
    surname VARCHAR(20) not null
);

COMMIT;