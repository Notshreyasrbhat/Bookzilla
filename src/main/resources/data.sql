-- Password for admin is 'admin', user is 'password'
INSERT INTO users (name, email, password, role) VALUES ('Admin User', 'admin', '{noop}admin', 'ADMIN');
INSERT INTO users (name, email, password, role) VALUES ('Alice Reader', 'alice@tracker.com', '{bcrypt}$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'USER');

-- 4 Programming Books
INSERT INTO content (title, author, genre, description, total_pages) VALUES ('Clean Code', 'Robert C. Martin', 'PROGRAMMING', 'A Handbook of Agile Software Craftsmanship.', 464);
INSERT INTO content (title, author, genre, description, total_pages) VALUES ('Design Patterns', 'Erich Gamma', 'PROGRAMMING', 'Elements of Reusable Object-Oriented Software.', 395);
INSERT INTO content (title, author, genre, description, total_pages) VALUES ('Effective Java', 'Joshua Bloch', 'PROGRAMMING', 'Best practices for the Java programming language.', 412);
INSERT INTO content (title, author, genre, description, total_pages) VALUES ('The Pragmatic Programmer', 'Andrew Hunt', 'PROGRAMMING', 'Your journey to mastery.', 320);

-- 3 Productivity Books
INSERT INTO content (title, author, genre, description, total_pages) VALUES ('Atomic Habits', 'James Clear', 'PRODUCTIVITY', 'An Easy & Proven Way to Build Good Habits & Break Bad Ones.', 320);
INSERT INTO content (title, author, genre, description, total_pages) VALUES ('Deep Work', 'Cal Newport', 'PRODUCTIVITY', 'Rules for Focused Success in a Distracted World.', 304);
INSERT INTO content (title, author, genre, description, total_pages) VALUES ('Getting Things Done', 'David Allen', 'PRODUCTIVITY', 'The Art of Stress-Free Productivity.', 352);

-- 4 Fiction Books
INSERT INTO content (title, author, genre, description, total_pages) VALUES ('The Hobbit', 'J.R.R. Tolkien', 'FICTION', 'A great adventure in Middle-Earth.', 310);
INSERT INTO content (title, author, genre, description, total_pages) VALUES ('1984', 'George Orwell', 'FICTION', 'A dystopian social science fiction novel and cautionary tale.', 328);
INSERT INTO content (title, author, genre, description, total_pages) VALUES ('Dune', 'Frank Herbert', 'FICTION', 'A science fiction masterpiece.', 412);
INSERT INTO content (title, author, genre, description, total_pages) VALUES ('Fahrenheit 451', 'Ray Bradbury', 'FICTION', 'A novel about a future American society where books are outlawed.', 249);
