package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Polynomial {
    private List<Integer> coefficients;
    private int degree;

    public Polynomial(List<Integer> coefficients) {
        this.coefficients = coefficients;
        this.degree = coefficients.size() - 1;
    }

    private void generateCoeffs(int degree) {
        coefficients = new ArrayList<>();
        var r = new Random();
        for (int i = 0; i <= degree; i++) {
            var coeff = r.nextInt(10) * (r.nextBoolean() ? 1 : -1);
            while (coeff == 0 && i == degree) {
                coeff = r.nextInt(10) * (r.nextBoolean() ? 1 : -1);
            }
            coefficients.add(coeff);
        }
    }

    public Polynomial(int degree) {
        this.degree = degree;
        generateCoeffs(degree);
    }

    public List<Integer> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(List<Integer> coefficients) {
        this.coefficients = coefficients;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }


    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (coefficients.get(degree) != 0) {
            stringBuilder.append(coefficients.get(degree)).append("x^").append(degree);
        }

        for (int i = degree - 1; i > 0; --i) {
            if (coefficients.get(i) != 0) {
                if (coefficients.get(i) > 0) {
                    stringBuilder.append("+");
                }
                stringBuilder.append(coefficients.get(i)).append("x^").append(i);
            }
        }

        if (coefficients.get(0) != 0) {
            if (coefficients.get(0) > 0) {
                stringBuilder.append("+");
            }
            stringBuilder.append(coefficients.get(0));
        }

        return stringBuilder.toString();
    }
}
