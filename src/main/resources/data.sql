insert into users(email, first_name, last_name, encr_pass, patronymic, role, username)
values ('testuser123@test.com', 'testuser', 'testuser', '$2a$12$HXzutl7tj6P8YUs/7/301.CpuHyHVAUuhgvQ9akbw7uxJHh0zaMRy', 'testuser', 'ADMIN', 'testuser123')
ON CONFLICT DO NOTHING;