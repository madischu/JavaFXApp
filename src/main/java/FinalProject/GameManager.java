package FinalProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controls the trivia game logic: question loading, scoring, and progression.
 */
public class GameManager {

        private List<Question> questions;
        public List<Question> getQuestions() {
                return questions;
        }
    private int currentIndex;
    private int score;
    private int maxQuestions;
    private List<Question> easyQuestions = new ArrayList<>();
    private List<Question> mediumQuestions = new ArrayList<>();
    private List<Question> hardQuestions = new ArrayList<>();

    public GameManager() {
        this.currentIndex = 0;
        this.score = 0;
        this.maxQuestions = 10; // adjustable
    }

    public void loadQuestionsForDifficulty(String difficulty) {
        this.questions = new ArrayList<>();
        if (easyQuestions.isEmpty() && mediumQuestions.isEmpty() && hardQuestions.isEmpty()) {
            loadAllQuestions();
        }
        List<Question> source;
        switch (difficulty.toLowerCase()) {
            case "easy": source = easyQuestions; break;
            case "medium": source = mediumQuestions; break;
            case "hard": source = hardQuestions; break;
            default: source = easyQuestions;
        }
        Collections.shuffle(source);
        for (int i = 0; i < Math.min(maxQuestions, source.size()); i++) {
            questions.add(source.get(i));
        }
        currentIndex = 0;
        score = 0;
    }

    private void loadAllQuestions() {
        easyQuestions.add(new MultipleChoiceQuestion(
                "What house at Hogwarts does Harry Potter belong to?",
                new String[]{"Hufflepuff", "Ravenclaw", "Gryffindor", "Slytherin"},
                "Gryffindor",
                "Houses",
                1,
                "Harry was sorted into Gryffindor in his first year."
        ));

        easyQuestions.add(new MultipleChoiceQuestion(
                "What position does Harry play on his Quidditch team?",
                new String[]{"Keeper", "Beater", "Chaser", "Seeker"},
                "Seeker",
                "Quidditch",
                1,
                "Harry is the Seeker for Gryffindor."
        ));

        easyQuestions.add(new MultipleChoiceQuestion(
                "What is the name of Harry's pet owl?",
                new String[]{"Errol", "Hedwig", "Crookshanks", "Scabbers"},
                "Hedwig",
                "Animals",
                1,
                "Hedwig was given to Harry as a birthday gift by Hagrid."
        ));

        easyQuestions.add(new MultipleChoiceQuestion(
                "What object must be caught to end a Quidditch match?",
                new String[]{"Quaffle", "Bludger", "Golden Snitch", "Broomstick"},
                "Golden Snitch",
                "Quidditch",
                1,
                "Catching the Golden Snitch ends the match."
        ));

        easyQuestions.add(new MultipleChoiceQuestion(
                "What platform at King's Cross Station do students use to board the Hogwarts Express?",
                new String[]{"Platform 8 3/4", "Platform 9", "Platform 9 3/4", "Platform 10"},
                "Platform 9 3/4",
                "Plot",
                1,
                "Students board the Hogwarts Express from Platform 9 3/4."
        ));

        easyQuestions.add(new MultipleChoiceQuestion(
                "Who is the headmaster of Hogwarts during most of Harry's time there?",
                new String[]{"Severus Snape", "Albus Dumbledore", "Minerva McGonagall", "Rubeus Hagrid"},
                "Albus Dumbledore",
                "Characters",
                1,
                "Dumbledore is the headmaster for most of the series."
        ));

        easyQuestions.add(new MultipleChoiceQuestion(
                "What spell is used to disarm an opponent?",
                new String[]{"Expelliarmus", "Accio", "Lumos", "Alohomora"},
                "Expelliarmus",
                "Spells",
                1,
                "Expelliarmus is the Disarming Charm."
        ));

        easyQuestions.add(new MultipleChoiceQuestion(
                "What magical creature is Dobby?",
                new String[]{"Goblin", "House Elf", "Centaur", "Troll"},
                "House Elf",
                "Creatures",
                1,
                "Dobby is a house elf who serves the Malfoy family for a majority of the series."
        ));

        easyQuestions.add(new MultipleChoiceQuestion(
                "What are Gryffindor's house colors?",
                new String[]{"Red and Gold", "Blue and Silver", "Green and Silver", "Yellow and Black"},
                "Red and Gold",
                "Plot",
                1,
                "Gryffindor's colors are red and gold."
        ));

        easyQuestions.add(new MultipleChoiceQuestion(
                "What is the name of the Weasley's family home?",
                new String[]{"The Burrow", "Hogsmeade", "Diagon Alley", "Number 12 Grimmauld Place"},
                "The Burrow",
                "Characters",
                1,
                "The Burrow is the Weasley family home."
        ));

        mediumQuestions.add(new MultipleChoiceQuestion(
                "Who kills Professor Dumbledore?",
                new String[]{"Lord Voldemort", "Bellatrix Lestrange", "Draco Malfoy", "Severus Snape"},
                "Severus Snape",
                "Plot",
                2,
                "Snape kills Dumbledore at the end of Half-Blood Prince."
        ));

        mediumQuestions.add(new MultipleChoiceQuestion(
                "What magical ability does Harry share with Voldemort?",
                new String[]{"Invisibility", "Parseltongue", "Apparition", "Legilimency"},
                "Parseltongue",
                "Magic",
                2,
                "Both can speak Parseltongue, the language of snakes."
        ));

        mediumQuestions.add(new MultipleChoiceQuestion(
                "Who was the Half-Blood Prince?",
                new String[]{"James Potter", "Sirius Black", "Severus Snape", "Tom Riddle"},
                "Severus Snape",
                "Characters",
                2,
                "The Half-Blood Prince was Severus Snape."
        ));

        mediumQuestions.add(new MultipleChoiceQuestion(
                "What form does Harry's Patronus take?",
                new String[]{"Phoenix", "Stag", "Otter", "Wolf"},
                "Stag",
                "Magic",
                2,
                "Harry’s Patronus is a stag, like his father’s Animagus form."
        ));

        mediumQuestions.add(new MultipleChoiceQuestion(
                "What is the name of the village near Hogwarts where students can go on weekends?",
                new String[]{"Hogsmeade", "Godric's Hollow", "Little Whinging", "Ottery St. Catchpole"},
                "Hogsmeade",
                "Plot",
                2,
                "Hogsmeade is the only all-wizarding village in Britain."
        ));

        mediumQuestions.add(new MultipleChoiceQuestion(
                "What does the Imperius Curse do?",
                new String[]{"Kills instantly", "Tortures painfully", "Controls a person's actions", "Heals wounds"},
                "Controls a person's actions",
                "Curses",
                2,
                "The Imperius Curse allows the caster to control the victim."
        ));

        mediumQuestions.add(new MultipleChoiceQuestion(
                "What is the name of the caretaker's cat?",
                new String[]{"Mrs. Norris", "Crookshanks", "Mrs. Borris", "Fang"},
                "Mrs. Norris",
                "Characters",
                2,
                "Mrs. Norris is the caretaker's cat."
        ));

        mediumQuestions.add(new MultipleChoiceQuestion(
                "What type of creature do Harry and Ron follow into the Forbidden Forest in their second year?",
                new String[]{"Spiders", "Unicorns", "Thestrals", "Acromantulas"},
                "Spiders",
                "Creatures",
                2,
                "Aragog is a giant spider, an Acromantula."
        ));

        mediumQuestions.add(new MultipleChoiceQuestion(
                "What is the core of Harry's wand?",
                new String[]{"Dragon heartstring", "Unicorn hair", "Phoenix feather", "Thestral tail hair"},
                "Phoenix feather",
                "Magic",
                2,
                "Harry's wand contains a phoenix feather from Fawkes, Dumbledore's phoenix."
        ));

        mediumQuestions.add(new MultipleChoiceQuestion(
                "Who takes over teaching Potions after Snape becomes the Defense Against the Dark Arts professor?",
                new String[]{"Horace Slughorn", "Gilderoy Lockhart", "Remus Lupin", "Dolores Umbridge"},
                "Horace Slughorn",
                "Characters",
                2,
                "Horace Slughorn takes over teaching Potions after Snape."
        ));

        hardQuestions.add(new MultipleChoiceQuestion(
                "Who is the heir of Slytherin?",
                new String[]{"Draco Malfoy", "Tom Riddle", "Severus Snape", "Harry Potter"},
                "Tom Riddle",
                "Plot",
                3,
                "Tom Riddle (Voldemort) was the true heir of Slytherin."
        ));

        hardQuestions.add(new MultipleChoiceQuestion(
                "In the book series, how many times does Seamus Finnigan accidentally cause an explosion?",
                new String[]{"Once", "Twice", "Three times", "Four times"},
                "Three times",
                "Characters",
                3,
                "Seamus is known for his explosive mishaps, particularly in Potions class. He causes 3 accidental explosions, and one on purpose during the final battle."
        ));

        hardQuestions.add(new MultipleChoiceQuestion(
                "Who was the first Triwizard Champion chosen by the Goblet of Fire?",
                new String[]{"Cedric Diggory", "Fleur Delacour", "Viktor Krum", "Harry Potter"},
                "Cedric Diggory",
                "Plot",
                3,
                "Cedric Diggory was the first champion chosen by the Goblet of Fire."
        ));

        hardQuestions.add(new MultipleChoiceQuestion(
                "What does the potion Felix Felicis do?",
                new String[]{"Grants luck", "Heals injuries", "Enhances strength", "Allows flight"},
                "Grants luck",
                "Potions",
                3,
                "Felix Felicis, also known as Liquid Luck, grants the drinker extraordinary luck."
        ));

        hardQuestions.add(new MultipleChoiceQuestion(
                "What is the vault number at Gringotts that held the Sorcerer's Stone before it was moved?",
                new String[]{"713", "666", "394", "221B"},
                "713",
                "Plot",
                3,
                "The Sorcerer's Stone was kept in vault 713 at Gringotts."
        ));

        hardQuestions.add(new MultipleChoiceQuestion(
                "What is Dumbledore's full name?",
                new String[]{"Albus Percival Wulfric Brian Dumbledore", "Albus Brian Percival Wulfric Dumbledore", "Albus Percival Ignatius Brian Dumbledore", "Albus Pius Wulfric Brian Dumbledore"},
                "Albus Percival Wulfric Brian Dumbledore",
                "Characters",
                3,
                "Dumbledore's full name is Albus Percival Wulfric Brian Dumbledore."
        ));

        hardQuestions.add(new MultipleChoiceQuestion(
                "What is the exact number of Sickles in a Galleon?",
                new String[]{"17", "29", "21", "15"},
                "17",
                "Currency",
                3,
                "There are 17 Sickles in a Galleon."
        ));

        hardQuestions.add(new MultipleChoiceQuestion(
                "In Tom Riddle's diary, what date does he show Harry to prove Hagrid opened the Chamber of Secrets?",
                new String[]{"January 13th", "June 13th", "July 13th", "September 13th"},
                "June 13th",
                "Plot",
                3,
                "Tom Riddle shows Harry a memory from June 13th to prove Hagrid opened the Chamber of Secrets."
        ));

        hardQuestions.add(new MultipleChoiceQuestion(
                "Which row number in the Department of Mysteries contains the Prophecy about Harry and Voldemort?",
                new String[]{"Row 73", "Row 97", "Row 66", "Row 98"},
                "Row 97",
                "Plot",
                3,
                "The Prophecy about Harry and Voldemort is located in Row 97 of the Department of Mysteries."
        ));

        hardQuestions.add(new MultipleChoiceQuestion(
                "What is Dumbledore's favourite jam flavor, mentioned in the first book?",
                new String[]{"Strawberry", "Raspberry", "Blackcurrant", "Elderberry"},
                "Strawberry",
                "Characters",
                3,
                "Dumbledore's favourite jam flavor is Strawberry."
        ));

    }

    public Question getNextQuestion() {
        if (currentIndex < questions.size()) {
            return questions.get(currentIndex++);
        }
        return null;
    }

    public void incrementScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public boolean hasMoreQuestions() {
        return currentIndex < maxQuestions && currentIndex < questions.size();
    }
}
