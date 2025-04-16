drop database `openfishjs`;
create database `openfishjs`;
use `openfishjs`;

CREATE TABLE fish (
    id	INT primary key auto_increment,
    species	VARCHAR(512),
    description	VARCHAR(512),
    wiki	VARCHAR(512),
    rarity	VARCHAR(512),
    distance	INT,
    min_weight	float,
    max_weight	float
);

INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('1', 'Atlantic Cod', 'A cold-water fish known for its flaky white flesh, historically significant in North Atlantic fisheries', 'https://en.wikipedia.org/wiki/Atlantic_cod', 'common', '100', '5', '12');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('2', 'Bluefin Tuna', 'A large, fast-swimming pelagic fish prized for sushi and sashimi', 'https://en.wikipedia.org/wiki/Bluefin_tuna', 'common', '20', '225', '250');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('3', 'Clownfish', 'A small, colorful reef fish famous for its symbiotic relationship with sea anemones', 'https://en.wikipedia.org/wiki/Clownfish', 'common', '100', '2', '3');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('4', 'Great White Shark', 'A formidable apex predator found in coastal surface waters of all major oceans', 'https://en.wikipedia.org/wiki/Great_white_shark', 'rare', '50', '680', '1100');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('5', 'Rainbow Trout', 'A freshwater species popular among anglers, known for its vibrant coloration', 'https://en.wikipedia.org/wiki/Rainbow_trout', 'common', '30', '0.5', '2.50');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('6', 'Common Carp', 'A widespread freshwater fish, often found in ponds and lakes', 'https://en.wikipedia.org/wiki/Common_carp', 'common', '10', '2', '14');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('7', 'Atlantic Salmon', 'An anadromous fish species prized for its rich, flavorful meat', 'https://en.wikipedia.org/wiki/Atlantic_salmon', 'rare', '20', '3.6', '5.4');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('8', 'Guppy', 'A small, colorful freshwater fish commonly kept in aquariums', 'https://en.wikipedia.org/wiki/Guppy', 'common', '5', '0.002', '0.005');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('9', 'Swordfish', 'A large, predatory fish recognizable by its long, flat bill', 'https://en.wikipedia.org/wiki/Swordfish', 'common', '30', '50', '250');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('10', 'Pufferfish', 'Known for its ability to inflate and its potent toxins', 'https://en.wikipedia.org/wiki/Tetraodontidae', 'rare', '50', '0.5', '2');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('11', 'Anglerfish', 'Deep-sea dwellers with a bioluminescent lure to attract prey', 'https://en.wikipedia.org/wiki/Anglerfish', 'epic', '130', '0.3', '18');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('12', 'Barracuda', 'A sleek, predatory fish with a fearsome reputation', 'https://en.wikipedia.org/wiki/Barracuda', 'rare', '115', '1.5', '23');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('13', 'Betta Fish', 'Also known as Siamese fighting fish, noted for their vivid colors and aggression', 'https://en.wikipedia.org/wiki/Betta', 'common', '5', '0.002', '0.005');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('14', 'Bluegill', 'A common freshwater fish in North America, popular among anglers', 'https://en.wikipedia.org/wiki/Bluegill', 'common', '5', '0.1', '2');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('15', 'Catfish', 'Named for their prominent barbels, resembling a cat\'s whiskers', 'https://en.wikipedia.org/wiki/Catfish', 'common', '10', '0.5', '50');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('16', 'Chinook Salmon', 'The largest species of Pacific salmon, also known as king salmon', 'https://en.wikipedia.org/wiki/Chinook_salmon', 'rare', '70', '4.5', '22');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('17', 'Discus Fish', 'A popular aquarium fish known for its distinctive shape and bright colors', 'https://en.wikipedia.org/wiki/Discus_(fish)', 'rare', '0', '0.1', '0.25');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('18', 'Electric Eel', 'Capable of generating powerful electric shocks for hunting and self-defense', 'https://en.wikipedia.org/wiki/Electric_eel', 'epic', '0', '1.5', '20');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('19', 'Flounder', 'A flatfish species known for its unique body shape and both eyes on one side', 'https://en.wikipedia.org/wiki/Flounder', 'rare', '80', '0.5', '14');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('20', 'Giant Trevally', 'A large predatory fish found in tropical waters, known for its strength', 'https://en.wikipedia.org/wiki/Giant_trevally', 'rare', '90', '10', '80');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('21', 'Goldfish', 'One of the most common aquarium fish, known for its golden-orange coloration', 'https://en.wikipedia.org/wiki/Goldfish', 'common', '5', '0.1', '1.5');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('22', 'Haddock', 'A North Atlantic fish, important in commercial fisheries', 'https://en.wikipedia.org/wiki/Haddock', 'rare', '60', '0.5', '3.6');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('23', 'Halibut', 'A large flatfish species, valued for its meat', 'https://en.wikipedia.org/wiki/Halibut', 'rare', '40', '4', '200');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('24', 'Herring', 'A small, oily fish found in large schools, significant in global fisheries', 'https://en.wikipedia.org/wiki/Herring', 'common', '110', '0.1', '1.5');
INSERT INTO fish (id, species, description, wiki, rarity, distance, min_weight, max_weight) VALUES ('25', 'Lionfish', 'Known for its venomous spines and striking appearance', 'https://en.wikipedia.org/wiki/Lionfish', 'epic', '85', '0.5', '1.5');


create table `users` (
	id int not null auto_increment primary key,
    email varchar(255) not null unique,
    password varchar(255) not null,
    points int not null,
    role varchar(255),
    username varchar(255) not null unique
);

create table `caughtfish` (
	id int not null auto_increment primary key,
    user_id int not null,
    fish_id int not null,
    amount int not null,
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (fish_id) references fish(id) on delete cascade
);
