package logica.proplogic;

import java.util.Scanner;

public class Menu {

    private static final String MAINMENU = "PropLogic\n"
            + "1. New truth table\n"
            + "4. Homework's truth tables\n"
            + "5. Exit";
    private static final String OPTION1 = "Number of statements: ";
    private static final String MAINMENU2 = "PropLogic\n"
            + "1. New truth table\n"
            + "2. Operations\n"
            + "3. Clasify column\n"
            + "4. Homework's truth tables\n"
            + "5 Exit";
    private static final String OPTION2 = "1. Negation\n"
            + "2. Conjunction\n"
            + "3. Disjunction\n"
            + "4. Implication\n"
            + "5. Bi-implication\n"
            + "6. Back";
    private static final String OPTION21 = "Choose column operand: ";
    private static final String OPTION22 = "Choose column operands: ";
    private static final String OPTION3 = "Choose column to clasify: ";
    private static final String OPTION4 = "1. Exercise 1\n"
            + "2. Exercise 2\n"
            + "3. Excercise 3\n"
            + "4. Back\n";
    private static TruthTable tt;

    public static void mainMenu() {
        System.out.println("");
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        if (tt == null) {
            System.out.println(MAINMENU);
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println(OPTION1);
                        tt = new TruthTable(scanner.nextInt());
                        tt.print();
                        break;
                    case 4:
                        option4();
                        break;
                    case 5:
                        return;
                    default:
                        throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("error");
            }
        } else {
            System.out.println(MAINMENU2);
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println(OPTION1);
                        tt = new TruthTable(scanner.nextInt());
                        tt.print();
                        break;
                    case 2:
                        option2();
                        break;
                    case 3:
                        System.out.println(OPTION3);
                        tt.clasify(scanner.nextInt());
                        break;
                    case 4:
                        option4();
                        break;
                    case 5:
                        return;
                    default:
                        throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("error");
            }
        }
        mainMenu();
    }

    public static void option2() {
        System.out.println("");
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println(OPTION2);
        try {
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println(OPTION21);
                    tt.negation(scanner.nextInt());
                    tt.print();
                    break;
                case 2:
                    System.out.println(OPTION22);
                    tt.conjunction(scanner.nextInt(), scanner.nextInt());
                    tt.print();
                    break;
                case 3:
                    System.out.println(OPTION22);
                    tt.disjunction(scanner.nextInt(), scanner.nextInt());
                    tt.print();
                    break;
                case 4:
                    System.out.println(OPTION22);
                    tt.implication(scanner.nextInt(), scanner.nextInt());
                    tt.print();
                    break;
                case 5:
                    System.out.println(OPTION22);
                    tt.biImplication(scanner.nextInt(), scanner.nextInt());
                    tt.print();
                    break;
                case 6:
                    return;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public static void option4() {
        System.out.println("");
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println(OPTION4);
        try {
            switch (scanner.nextInt()) {
                case 1:
                    tt = new TruthTable(3);
                    tt.implication(0, 1);//3
                    tt.implication(1, 2);//4
                    tt.conjunction(3, 4);//5
                    tt.implication(0, 2);//6
                    tt.implication(5, 6);//7
                    tt.print();
                    tt.clasify(7);
                    break;
                case 2:
                    tt = new TruthTable(3);
                    tt.disjunction(0, 1);//3
                    tt.implication(0, 2);//4
                    tt.implication(1, 2);//5
                    tt.conjunction(3, 4);//6
                    tt.conjunction(6, 5);//7
                    tt.implication(7, 2);//8
                    tt.print();
                    tt.clasify(8);
                    break;
                case 3:
                    tt = new TruthTable(3);
                    tt.implication(1, 2);//3
                    tt.implication(0, 1);//4
                    tt.implication(0, 2);//5
                    tt.implication(0, 3);//6
                    tt.implication(4, 5);//7
                    tt.implication(6, 7);//8
                    tt.print();
                    tt.clasify(8);
                    break;
                case 4:
                    return;
                default:
                    throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
