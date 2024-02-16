-- Drop the database if it already exists to avoid conflicts
DROP DATABASE IF EXISTS library;

-- Create a new database named "library"
CREATE DATABASE library;

-- Switch to the newly created database
USE library;

-- Define the Orders table to store information about orders
CREATE TABLE Orders (
    id INT AUTO_INCREMENT,
    order_date DATE,
    total_price DOUBLE,
    order_status VARCHAR(50),
    PRIMARY KEY(id)
);

-- Define the Models table to store information about models (assuming it refers to employee models or user models)
CREATE TABLE Models (
    id INT AUTO_INCREMENT,
    name VARCHAR(50),
    surname VARCHAR(50),
    dob DATE,
    username VARCHAR(50),
    password VARCHAR(50),
    PRIMARY KEY(id)
);

-- Define the Employees table to store information about employees
CREATE TABLE Employees (
    id INT AUTO_INCREMENT,
    working_role VARCHAR(50),
    idModel INT,
    FOREIGN KEY(idModel) REFERENCES Models(id) ON UPDATE CASCADE ON DELETE SET NULL,
    PRIMARY KEY(id)
);

-- Define the Customers table to store information about customers
CREATE TABLE Customers (
    id INT AUTO_INCREMENT,
    idModel INT,
    FOREIGN KEY(idModel) REFERENCES Models(id) ON UPDATE CASCADE ON DELETE SET NULL,
    PRIMARY KEY(id)
);

-- Define the Authors table to store information about authors
CREATE TABLE Authors (
    id INT AUTO_INCREMENT,
    name VARCHAR(50),
    surname VARCHAR(50),
    dob DATE,
    biography VARCHAR(250),
    PRIMARY KEY(id)
);

-- Define the Books table to store information about books
CREATE TABLE Books (
    id INT AUTO_INCREMENT,
    title VARCHAR(100),
    type VARCHAR(100),
    num_pages INT,
    price DOUBLE,
    idAuthor INT,
    FOREIGN KEY(idAuthor) REFERENCES Authors(id) ON UPDATE CASCADE ON DELETE SET NULL,
    PRIMARY KEY(id)
);

-- Define the Orders_Books table to establish the many-to-many relationship between orders and books
CREATE TABLE Orders_Books (
    id_book INT NOT NULL,
    id_order INT NOT NULL,
    id_customer INT,
    FOREIGN KEY(id_customer) REFERENCES Customers(id),
    FOREIGN KEY(id_book) REFERENCES Books(id),
    FOREIGN KEY(id_order) REFERENCES Orders(id),
    PRIMARY KEY(id_book, id_order)
);



-- Insert sample data into the Authors table
INSERT INTO Authors (name, surname, dob, biography) VALUES
('John', 'Doe', '1980-05-15', 'John Doe is a prolific author known for his mystery novels.'),
('Jane', 'Smith', '1975-10-22', 'Jane Smith is a bestselling author of young adult fiction.'),
('Michael', 'Johnson', '1990-03-08', 'Michael Johnson is an emerging author specializing in science fiction.'),
('Emily', 'Brown', '1988-12-30', 'Emily Brown is an award-winning poet with several published collections.'),
('David', 'Wilson', '1965-07-11', 'David Wilson is a renowned non-fiction author focusing on historical biographies.'),
('Sarah', 'Taylor', '1982-09-05', 'Sarah Taylor is a fantasy author known for her intricate world-building.'),
('Christopher', 'Lee', '1970-04-18', 'Christopher Lee is a novelist exploring themes of existentialism and identity.'),
('Amanda', 'Clark', '1995-01-25', 'Amanda Clark is a debut novelist making waves in the literary world.'),
('Robert', 'Martinez', '1983-06-14', 'Robert Martinez writes gripping thrillers that keep readers on the edge of their seats.'),
('Laura', 'Garcia', '1978-11-03', 'Laura Garcia is a celebrated author of romance novels with a flair for drama.'),
('Daniel', 'Jones', '1986-02-28', 'Daniel Jones is an up-and-coming author of historical fiction.'),
('Jennifer', 'White', '1992-07-17', 'Jennifer White writes heartwarming stories that resonate with readers of all ages.'),
('William', 'Miller', '1973-09-11', 'William Miller is a bestselling author of crime thrillers known for his intricate plots.'),
('Stephanie', 'Adams', '1984-04-09', 'Stephanie Adams writes thought-provoking literary fiction exploring human relationships.'),
('Alex', 'Johnson', '1990-08-21', 'Alex Johnson is a rising star in the world of fantasy literature, known for intricate world-building and compelling characters.'),
('Rachel', 'Roberts', '1982-03-14', 'Rachel Roberts is a versatile author who has written across multiple genres, including romance, mystery, and historical fiction.'),
('Samuel', 'Wong', '1977-06-30', 'Samuel Wong is a seasoned author with a knack for crafting suspenseful thrillers that keep readers on the edge of their seats.'),
('Elena', 'Perez', '1993-12-05', 'Elena Perez is a talented writer known for her poignant poetry collections that delve into themes of love, loss, and identity.'),
('Mark', 'Thompson', '1968-09-28', 'Mark Thompson is an accomplished non-fiction author specializing in biographies of historical figures and events.'),
('Jessica', 'Evans', '1989-02-12', 'Jessica Evans is an emerging voice in the field of young adult fiction, praised for her authentic portrayal of teenage experiences.'),
('Ryan', 'Scott', '1971-05-19', 'Ryan Scott is a bestselling author of suspense novels, renowned for his intricate plots and unexpected twists.'),
('Samantha', 'Baker', '1985-11-26', 'Samantha Baker is a prolific romance author known for her heartwarming stories and strong emotional resonance with readers.'),
('Kevin', 'Nguyen', '1996-04-03', 'Kevin Nguyen is a promising debut novelist whose work explores themes of cultural identity and belonging.'),
('Julia', 'Gonzalez', '1980-07-08', 'Julia Gonzalez is an acclaimed author of literary fiction, celebrated for her lyrical prose and insightful exploration of human relationships.'),
('Eric', 'Hernandez', '1979-10-15', 'Eric Hernandez is a versatile writer known for his contributions to various genres, including science fiction, fantasy, and horror.'),
('Michelle', 'Carter', '1983-01-31', 'Michelle Carter is a bestselling author of psychological thrillers, known for her skillful character development and chilling narratives.'),
('Peter', 'Russell', '1974-03-24', 'Peter Russell is an award-winning author of historical fiction, praised for his meticulous research and vivid storytelling.'),
('Lauren', 'Kelly', '1991-06-09', 'Lauren Kelly is an up-and-coming author specializing in contemporary romance, with a focus on diverse and inclusive narratives.'),
('Gregory', 'Adams', '1976-08-13', 'Gregory Adams is a seasoned writer of mystery novels, admired for his complex plots and attention to detail.'),
('Natalie', 'Lopez', '1987-02-18', 'Natalie Lopez is a versatile author known for her contributions to both children\'s literature and young adult fiction, with a talent for creating engaging and relatable characters.'),
('Jonathan', 'Cruz', '1981-09-22', 'Jonathan Cruz is an established author of fantasy novels, known for his immersive world-building and epic storytelling.'),
('Amy', 'Stewart', '1994-12-07', 'Amy Stewart is a promising young writer who has garnered attention for her poignant literary fiction exploring themes of identity and self-discovery.'),
('Benjamin', 'Ward', '1972-04-16', 'Benjamin Ward is a bestselling author of crime fiction, known for his gritty and realistic portrayal of urban life and criminal underworld.'),
('Melissa', 'Morales', '1988-10-25', 'Melissa Morales is a talented playwright and novelist, known for her insightful exploration of social issues and complex characters.');

-- Insert sample data into the Books table
INSERT INTO Books (title, type, num_pages, price, idAuthor) VALUES 
('The Great Gatsby', 'Fiction', 180, 15.99, 1),
('To Kill a Mockingbird', 'Fiction', 281, 12.50, 2),
('1984', 'Dystopian', 328, 10.99, 3),
('The Hobbit', 'Fantasy', 310, 14.75, 4),
('The Catcher in the Rye', 'Fiction', 224, 9.25, 5),
('Harry Potter and the Sorcerer''s Stone', 'Fantasy', 320, 16.99, 6),
('Pride and Prejudice', 'Romance', 279, 11.25, 7),
('The Lord of the Rings', 'Fantasy', 1178, 25.99, 4),
('The Hunger Games', 'Dystopian', 374, 13.50, 8),
('The Da Vinci Code', 'Mystery', 454, 14.75, 9),
('The Alchemist', 'Fiction', 197, 9.99, 10),
('The Road', 'Post-apocalyptic', 241, 12.99, 11),
('Brave New World', 'Dystopian', 288, 11.49, 12),
('The Shining', 'Horror', 447, 13.75, 13),
('Gone with the Wind', 'Historical Fiction', 1037, 18.25, 14),
('The Chronicles of Narnia', 'Fantasy', 767, 22.99, 15),
('The Hitchhiker''s Guide to the Galaxy', 'Science Fiction', 193, 10.99, 16),
('The Picture of Dorian Gray', 'Gothic Fiction', 245, 12.50, 17),
('Frankenstein', 'Gothic Horror', 280, 11.99, 18),
('Moby-Dick', 'Adventure', 654, 20.75, 19),
('A Tale of Two Cities', 'Historical Fiction', 337, 14.25, 20),
('The Girl with the Dragon Tattoo', 'Thriller', 465, 15.99, 21),
('The Catcher in the Rye', 'Fiction', 224, 9.25, 5),
('The Odyssey', 'Epic Poetry', 384, 16.50, 22),
('The Grapes of Wrath', 'Historical Fiction', 464, 17.99, 23);

-- Insert sample data into the Models table (assuming it refers to employee models or user models)

INSERT INTO Models (name, surname, dob, username, password)
VALUES 
('Alice', 'Smith', '1995-07-15', 'alice_model', 'password123'),
('Bob', 'Johnson', '1990-03-25', 'bob_model', 'securepass'),
('Carla', 'Garcia', '1988-11-03', 'carla_model', 'safepassword'),
('Emma', 'Brown', '1993-09-12', 'emma_model', 'emma123'),
('Daniel', 'Martinez', '1987-04-28', 'daniel_model', 'danielpass'),
('Sophia', 'Anderson', '1998-12-05', 'sophia_model', 'sophia_pass'),
('James', 'Wilson', '1991-06-20', 'james_model', 'password1234'),
('Olivia', 'Taylor', '1996-02-17', 'olivia_model', 'olivia_pass'),
('Liam', 'Moore', '1985-08-30', 'liam_model', 'liam123'),
('Ava', 'Thomas', '1989-10-08', 'ava_model', 'avapass'),
('Noah', 'Harris', '1994-03-14', 'noah_model', 'noahpass'),
('Isabella', 'Clark', '1986-11-22', 'isabella_model', 'isabella123'),
('Mia', 'Walker', '1992-07-01', 'mia_model', 'miapass'),
('Oliver', 'Taylor', '1990-09-05', 'oliver_model', 'oliverpass'),
('Sophia', 'Roberts', '1988-05-18', 'sophia_model', 'sophiapass');

-- Insert sample data into the Customers table
INSERT INTO Customers (idModel)
VALUES (1), (2), (3), (4), (5);

-- Insert sample data into the Employees table
INSERT INTO Employees (working_role, idModel) 
VALUES 
    ('Librarian', 6),
    ('Circulation Clerk', 7),
    ('Reference Librarian', 8),
    ('Acquisitions Specialist', 9),
    ('Cataloger', 10),
    ('Assistant Librarian', 11),
    ('Technical Services Librarian', 12),
    ('Library Director', 13),
    ('Youth Services Librarian', 14),
    ('Archivist', 15);
    

select * from models m inner join employees e on e.idModel = m.id;

select * from Models m inner join Customers c ON m.id = c.idModel;
select c.id from models m inner join customers c on m.id = c.idModel where m.id = 3;





