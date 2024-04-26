CREATE TABLE IF NOT EXISTS Post
(
    id
    INT
    NOT
    NULL,
    user_id
    INT
    NOT
    NULL,
    title
    VARCHAR
(
    255
) NOT NULL,
    body TEXT NOT NULL,
    version INT,
    PRIMARY KEY
(
    id
)
    );