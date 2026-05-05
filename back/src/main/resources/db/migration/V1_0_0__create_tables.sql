-- =========================
-- BASE TABLES
-- =========================

CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    created_at DATETIME,
    updated_at DATETIME,
    active BOOLEAN
);

CREATE TABLE organization (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    active BOOLEAN
);

CREATE TABLE task_status (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    active BOOLEAN
);

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    name VARCHAR(255),
    role_id BIGINT,
    enabled BOOLEAN NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    active BOOLEAN,
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES role(id)
);

-- =========================
-- WORK ITEM (JOINED)
-- =========================

CREATE TABLE work_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    code INT UNIQUE,
    description TEXT,
    effort DOUBLE,
    user_id BIGINT,
    start_date DATETIME,
    target_date DATETIME,
    end_date DATETIME,
    completed_work DOUBLE,
    remaining_work DOUBLE,
    created_at DATETIME,
    updated_at DATETIME,
    active BOOLEAN,
    organization_id BIGINT,

    CONSTRAINT fk_workitem_user FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE task (
    id BIGINT PRIMARY KEY,
    crud_tested BOOLEAN,
    multiple_browsers_tested BOOLEAN,
    evidence_attached BOOLEAN,
    acceptance_criteria TEXT,
    description_of_impediment TEXT,
    status_id BIGINT NOT NULL,

    CONSTRAINT fk_task_workitem FOREIGN KEY (id) REFERENCES work_item(id),
    CONSTRAINT fk_task_status FOREIGN KEY (status_id) REFERENCES task_status(id)
);

-- =========================
-- RELATION TABLE
-- =========================

CREATE TABLE user_organization (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    organization_id BIGINT NOT NULL,
    role VARCHAR(100),
    created_at DATETIME,
    updated_at DATETIME,
    active BOOLEAN,

    CONSTRAINT fk_userorg_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_userorg_org FOREIGN KEY (organization_id) REFERENCES organization(id)
);