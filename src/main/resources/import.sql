insert into users (login, password_hash, role, score_max_sum, score_sum, signup_date, test_passed_number) values ('admin', '$2a$10$m6adcUp.RZaCrVmlzh6ftOrpDpldkShN8R/c5lC/ZwhH6KXIjnoqO', 'ADMIN', 0, 0, '2021-10-30', 0);

insert into test (max_scores, title) values (4, 'Примерный тест');

insert into question (number, question_kind, text, test_id) values (1, 'SINGLE_CHOICE', 'вопрос1', 1);
insert into question (number, question_kind, text, test_id) values (2, 'MULTIPLY_CHOICE', 'вопрос2', 1);

insert into answer (number, is_correct, text, question_number, question_test_id) values (1, false, 'неправильный ответ', 1, 1);
insert into answer (number, is_correct, text, question_number, question_test_id) values (2, true, 'правильный ответ', 1, 1);
insert into answer (number, is_correct, text, question_number, question_test_id) values (3, false, 'неправильный ', 1, 1);


insert into answer (number, is_correct, text, question_number, question_test_id) values (1, true, 'правильный ответ', 2, 1);
insert into answer (number, is_correct, text, question_number, question_test_id) values (2, false, 'неправильный ответ', 2, 1);
insert into answer (number, is_correct, text, question_number, question_test_id) values (3, true, 'правильный ', 2, 1);
