package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PolynomialOperations {

    public static Polynomial add(Polynomial p1, Polynomial p2) {
        int minDegree = Math.min(p1.getDegree(), p2.getDegree());
        //System.out.println(minDegree);
        int maxDegree = Math.max(p1.getDegree(), p2.getDegree());
        //System.out.println(maxDegree);
        List<Integer> coeffs = new ArrayList<>(maxDegree + 1);

        for (int i = 0; i <= minDegree; i++) {
            coeffs.add(p1.getCoefficients().get(i) + p2.getCoefficients().get(i));
        }
        //System.out.println(coeffs);

        if (minDegree != maxDegree) {
            if (maxDegree == p1.getDegree()) {
                for (int i = minDegree + 1; i <= maxDegree; i++)
                    coeffs.add(p1.getCoefficients().get(i));
            } else {
                for (int i = minDegree + 1; i <= maxDegree; i++) {
                    coeffs.add(p2.getCoefficients().get(i));
                }
            }
        }
        return new Polynomial(coeffs);


    }

    public static Polynomial subtract(Polynomial p1, Polynomial p2) {
        int minDegree = Math.min(p1.getDegree(), p2.getDegree());
        int maxDegree = Math.max(p1.getDegree(), p2.getDegree());
        List<Integer> coeffs = new ArrayList<>();

        for (int i = 0; i <= minDegree; i++) {
            coeffs.add(p1.getCoefficients().get(i) - p2.getCoefficients().get(i));
        }

        if (minDegree != maxDegree) {
            if (maxDegree == p1.getDegree()) {
                for (int i = minDegree + 1; i <= maxDegree; i++) {
                    coeffs.add(p1.getCoefficients().get(i));
                }
            } else {
                for (int i = minDegree + 1; i <= maxDegree; i++) {
                    coeffs.add(p2.getCoefficients().get(i));
                }
            }
        }

        int i = coeffs.size() - 1;
        if (i >= 0 && coeffs.get(i) == 0)
            coeffs.remove(i);

        return new Polynomial(coeffs);
    }

    public static Polynomial addZeros(Polynomial p, int offset) {
        List<Integer> coeffs = IntStream.range(0, offset).mapToObj(i -> 0).collect(Collectors.toList());
        coeffs.addAll(p.getCoefficients());
        //System.out.println(" in addzero" + new Polynomial(coeffs));

        return new Polynomial(coeffs);
    }

}
