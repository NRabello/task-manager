-- =========================
-- ROLES
-- =========================

INSERT INTO role (name, created_at, active)
VALUES
    ('ROLE_ADMIN', NOW(), true),
    ('ROLE_USER', NOW(), true);

-- =========================
-- TASK STATUS
-- =========================

INSERT INTO task_status (name, color, created_at, active)
VALUES
    ('To Do', '#949292', NOW(), true),
    ('In Progress', '#0A67C2', NOW(), true),
    ('Done', '#0CCF13', NOW(), true);