package logica.proplogic;

import java.util.ArrayList;

public class TruthTable {

    private final int statements;
    private final int rows;
    private ArrayList<boolean[]> formulae;
    private ArrayList<String> headers;

    public TruthTable(int statements) throws Exception {
        if (statements > 10 || statements < 1) {
            throw new Exception();
        }
        this.statements = statements;
        this.rows = (int) Math.pow(2, this.statements);
        this.headers = new ArrayList();
        for (int i = 0; i < statements; i++) {
            headers.add(Character.toString((char) 112 + i));
        }
        this.formulae = new ArrayList();
        for (int i = 0; i < statements; i++) {
            formulae.add(new boolean[rows]);
        }
        String values = Integer.toBinaryString(this.rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.statements; j++) {
                formulae.get(j)[i] = values.charAt(j + 1) != '0';
            }
            values = Integer.toBinaryString(Integer.parseInt(values, 2) + 1);
        }
    }

    public int getStatements() {
        return statements;
    }

    public ArrayList<boolean[]> getFormulae() {
        return formulae;
    }

    public void setFormulae(ArrayList<boolean[]> formulae) {
        this.formulae = formulae;
    }

    public void negation(int operandIndex) throws Exception {
        if (operandIndex < 0 || operandIndex > formulae.size() - 1) {
            throw new Exception();
        }
        boolean[] values = new boolean[rows];
        for (int i = 0; i < rows; i++) {
            values[i] = !formulae.get(operandIndex)[i];
        }
        formulae.add(values);
        headers.add("¬(".concat(headers.get(operandIndex).concat(")")));
    }

    public void disjunction(int operandIndex1, int operandIndex2) throws Exception {
        if (operandIndex1 < 0 || operandIndex1 > formulae.size() - 1 || operandIndex2 < 0 || operandIndex2 > formulae.size() - 1) {
            throw new Exception();
        }
        boolean[] values = new boolean[rows];
        for (int i = 0; i < rows; i++) {
            values[i] = formulae.get(operandIndex1)[i] || formulae.get(operandIndex2)[i];
        }
        formulae.add(values);
        headers.add("(".concat(headers.get(operandIndex1).concat(") ∨ (".concat(headers.get(operandIndex2).concat(")")))));
    }

    public void conjunction(int operandIndex1, int operandIndex2) throws Exception {
        if (operandIndex1 < 0 || operandIndex1 > formulae.size() - 1 || operandIndex2 < 0 || operandIndex2 > formulae.size() - 1) {
            throw new Exception();
        }
        boolean[] values = new boolean[rows];
        for (int i = 0; i < rows; i++) {
            values[i] = formulae.get(operandIndex1)[i] && formulae.get(operandIndex2)[i];
        }
        formulae.add(values);
        headers.add("(".concat(headers.get(operandIndex1).concat(") ∧ (".concat(headers.get(operandIndex2).concat(")")))));
    }

    public void implication(int operandIndex1, int operandIndex2) throws Exception {
        if (operandIndex1 < 0 || operandIndex1 > formulae.size() - 1 || operandIndex2 < 0 || operandIndex2 > formulae.size() - 1) {
            throw new Exception();
        }
        boolean[] values = new boolean[rows];
        for (int i = 0; i < rows; i++) {
            values[i] = !formulae.get(operandIndex1)[i] || formulae.get(operandIndex2)[i];
        }
        formulae.add(values);
        headers.add("(".concat(headers.get(operandIndex1).concat(") \u2192 (".concat(headers.get(operandIndex2).concat(")")))));
    }

    public void biImplication(int operandIndex1, int operandIndex2) throws Exception {
        if (operandIndex1 < 0 || operandIndex1 > formulae.size() - 1 || operandIndex2 < 0 || operandIndex2 > formulae.size() - 1) {
            throw new Exception();
        }
        boolean[] values = new boolean[rows];
        for (int i = 0; i < rows; i++) {
            values[i] = formulae.get(operandIndex1)[i] == formulae.get(operandIndex2)[i];
        }
        formulae.add(values);
        headers.add("(".concat(headers.get(operandIndex1).concat(") \u27F7 (".concat(headers.get(operandIndex2).concat(")")))));
    }

    public void print() {
        System.out.println("");
        Integer separation = 5;
        ArrayList<Integer> separations = new ArrayList();
        for (String header : headers) {
            if (header.length() < 7) {
                separation = 7;
            } else {
                separation = header.length() + 2;
            }
            separations.add(separation);
        }
        for (int i = 0; i < headers.size(); i++) {
            System.out.printf("%-".concat(separations.get(i).toString().concat("s")), i);
        }
        System.out.println("");
        for (int i = 0; i < headers.size(); i++) {
            System.out.printf("%-".concat(separations.get(i).toString().concat("s")), headers.get(i));
        }
        System.out.println("");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < formulae.size(); j++) {
                System.out.printf("%-".concat(separations.get(j).toString().concat("s")), formulae.get(j)[i]);
            }
            System.out.println("");
        }
    }

    public void clasify(int formulaIndex) {
        System.out.println("");
        if (formulae.get(formulaIndex)[0]) {
            for (int i = 1; i < rows; i++) {
                if (!formulae.get(formulaIndex)[i]) {
                    System.out.println("Contingency");
                    return;
                }
            }
            System.out.println("Tautology");
        } else {
            for (int i = 1; i < rows; i++) {
                if (formulae.get(formulaIndex)[i]) {
                    System.out.println("Contingency");
                    return;
                }
            }
            System.out.println("Contradiction");
        }

    }
}
