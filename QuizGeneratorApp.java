import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class to store each quiz question, options, and correct answer
class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex;

    public Question(String questionText, List<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean isCorrectAnswer(int userOption) {
        return userOption == correctAnswerIndex;
    }
}

// Class to represent a Quiz with a list of questions
class Quiz {
    private List<Question> questions;
    
    public Quiz() {
        questions = new ArrayList<>();
    }

    // Method to add a new question to the quiz
    public void addQuestion(Question question) {
        questions.add(question);
    }

    // Method to conduct the quiz and calculate the score
    public void takeQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + question.getQuestionText());
            
            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ": " + options.get(j));
            }

            System.out.print("Your answer (choose the option number): ");
            int userOption = scanner.nextInt() - 1; // zero-based index

            if (question.isCorrectAnswer(userOption)) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Wrong! The correct answer was: " + (question.getCorrectAnswerIndex() + 1) + "\n");
            }
        }

        System.out.println("Quiz finished! Your score: " + score + "/" + questions.size());
        if (score == questions.size()) {
            System.out.println("Excellent work!");
        } else if (score >= questions.size() / 2) {
            System.out.println("Good job!");
        } else {
            System.out.println("Keep practicing!");
        }
    }
}

public class QuizGeneratorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Quiz quiz = new Quiz();

        // Main menu
        while (true) {
            System.out.println("\n--- Quiz Menu ---");
            System.out.println("1. Create Quiz");
            System.out.println("2. Take Quiz");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt

            if (choice == 1) {
                // Create a quiz and add questions
                System.out.println("\nEnter the number of questions for the quiz:");
                int numberOfQuestions = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                for (int i = 0; i < numberOfQuestions; i++) {
                    System.out.println("Enter the text for question " + (i + 1) + ":");
                    String questionText = scanner.nextLine();

                    System.out.println("Enter the number of options for question " + (i + 1) + ":");
                    int numberOfOptions = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    List<String> options = new ArrayList<>();
                    for (int j = 0; j < numberOfOptions; j++) {
                        System.out.println("Enter option " + (j + 1) + ":");
                        options.add(scanner.nextLine());
                    }

                    System.out.println("Enter the number of the correct option (1 to " + numberOfOptions + "):");
                    int correctAnswer = scanner.nextInt() - 1; // zero-based index
                    scanner.nextLine(); // Consume newline

                    // Add the question to the quiz
                    Question question = new Question(questionText, options, correctAnswer);
                    quiz.addQuestion(question);
                }
            } else if (choice == 2) {
                // Take the quiz
                quiz.takeQuiz();
            } else if (choice == 3) {
                // Exit the program
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }
}
