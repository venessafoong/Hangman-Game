import java.util.*;

public class Hangman
{
    static ArrayList<String> words = new ArrayList<>();
    static String wordToGuess;
    static ArrayList<Character> wordToGuessList = new ArrayList<>();
    static ArrayList<Character> hiddenWordList = new ArrayList <>(wordToGuessList.size());
    static char guess;
    static int lives = 8;
    static ArrayList<Character> guessedLetters = new ArrayList<>();



    public static void chooseWord()
    {
        //list of words

        Collections.addAll(words,"LIGHT", "GREAT", "QUEEN", "KOALA", "BOAT", "TEAPOT");

        //generate word to guess

        Random rand = new Random();
        int intRandom = rand.nextInt(words.size());

        wordToGuess = words.get(intRandom);

        //hide word
        for (int i = 0; i < wordToGuess.length(); i++)
        {
            hiddenWordList.add('-');
        }

    }

    public static void gameInfo()
    {
        Scanner input = new Scanner(System.in);

        //  word status
        System.out.print("Your word now looks like this: ");
        for (int i = 0; i < hiddenWordList.size(); i++)
        {
            System.out.print(hiddenWordList.get(i));
        }

        // letters guessed
        guessedLetters();

        //  guesses left
        System.out.printf("\nYou have %d guesses left ", lives);

        //  new guess
        System.out.print("\nYour guess: ");
        guess = Character.toUpperCase(input.next().charAt(0));

    }

    public static void correctLetterGuessed()
    {
        System.out.println("That guess is correct.");

        for (int i = 0; i < wordToGuess.length(); i++)
        {
            if (wordToGuess.charAt(i) == guess)
            {
                hiddenWordList.set(i, guess);
            }
        }

        if (! hiddenWordList.contains('-'))
        {
            System.out.println("You guessed the word " + wordToGuess);
            System.out.println("You win.");
        } else
        {
            gameInfo();
        }
    }

    public static void wrongLetterGuessed()
    {
        System.out.printf("There are no %s's in the word.\n", guess);
        lives--;
        if (lives == 0)
        {
            System.out.println("You are completely hung.");
            System.out.println("The word was " + wordToGuess);
            System.out.println("You lose.");
        } else
        {
            gameInfo();
        }
    }

    public static void guessedLetters()
    {
        //  previous guesses
        if (guessedLetters.contains(guess))
        {
            lives++;
            System.out.print("\nLetter has been guessed before. Please pick another letter.");
        } else
        {
            guessedLetters.add(guess);
        }
        System.out.print("\nYour guesses so far: " );
        for (int i = 0; i < guessedLetters.size(); i++)
        {
            System.out.print(guessedLetters.get(i));
        }
    }

    public static void main(String[] args)
    {
        System.out.println("Welcome to Hangman!");
        chooseWord();
        gameInfo();

        while(lives > 0 && hiddenWordList.contains('-'))
        {
            if (wordToGuess.contains(Character.toString(guess)))
            {
                correctLetterGuessed();
            } else
            {
                wrongLetterGuessed();
            }
        }
    }
}

