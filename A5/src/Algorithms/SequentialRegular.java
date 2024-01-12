package Algorithms;

import model.Polynomial;

import java.util.ArrayList;
import java.util.List;

public class SequentialRegular {

    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        System.out.println("In sequential regular");

        //System.out.println(p1.getDegree());
        List<Integer> coeffs = new ArrayList<>();
        for (int i = 0; i <= p1.getDegree() + p2.getDegree(); i++) {
            coeffs.add(0);
        }
        //System.out.println(coeffs);

        Polynomial res = new Polynomial(coeffs);

        for (int i = 0; i <= p1.getDegree(); i++) {
            for (int j = 0; j <= p2.getDegree(); j++) {
                int val = res.getCoefficients().get(i + j) + p1.getCoefficients().get(i) * p2.getCoefficients().get(j);
                res.getCoefficients().set(i + j, val);
            }
        }

        return res;

    }

}
