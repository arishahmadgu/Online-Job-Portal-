CREATE DATABASE job_portal;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200),
    email VARCHAR(200) UNIQUE,
    password VARCHAR(200)
);

CREATE TABLE recruiters (
    id INT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(200),
    email VARCHAR(200) UNIQUE,
    password VARCHAR(200)
);

CREATE TABLE jobs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    company VARCHAR(255),
    location VARCHAR(255),
    description TEXT,
    skills VARCHAR(255),
    recruiter_id INT,
    FOREIGN KEY (recruiter_id) REFERENCES recruiters(id)
);

CREATE TABLE applications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    job_id INT,
    resume VARCHAR(255),
    status VARCHAR(50) DEFAULT 'Applied',
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (job_id) REFERENCES jobs(id)
);

*/
