package Algorithms;

import model.Polynomial;
import model.PolynomialOperations;

public class SequentialKaratsuba {

    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        System.out.println("In sequential Karatsuba");

        //x=x1 * B^m + x0
        //y=y1 * B^m + y0

        if (p1.getDegree() < 2 || p2.getDegree() < 2) {
            return SequentialRegular.multiply(p1, p2);
        }

        int len = Math.max(p1.getDegree(), p2.getDegree()) / 2;
        //System.out.println("len = " + len);

        Polynomial firstHalfP1 = new Polynomial(p1.getCoefficients().subList(0, len));
        //System.out.println("fP1 = " + firstHalfP1);
        Polynomial secondHalfP1 = new Polynomial(p1.getCoefficients().subList(len, p1.getCoefficients().size()));
        //System.out.println("sP1 = " + secondHalfP1);
        Polynomial firstHalfP2 = new Polynomial(p2.getCoefficients().subList(0, len));
        //System.out.println("fP2 = " + firstHalfP2);
        Polynomial secondHalfP2 = new Polynomial(p2.getCoefficients().subList(len, p2.getCoefficients().size()));
        //System.out.println("sP2 = " + secondHalfP2);


        //z0 = x0*y0
        Polynomial r1 = multiply(firstHalfP1, firstHalfP2);
        //System.out.println("r1= " + r1);

        //partial z1 = (x1+x0)*(y1+y0)
        Polynomial r2 = multiply(PolynomialOperations.add(firstHalfP1, secondHalfP1), PolynomialOperations.add(firstHalfP2, secondHalfP2));
        //System.out.println("r2= " + r2);

        //z2 = x1*y1
        Polynomial r3 = multiply(secondHalfP1, secondHalfP2);
        //System.out.println("r3 = " + r3);


        //final result
        Polynomial final_rez1 = PolynomialOperations.addZeros(r3, 2 * len);
        //System.out.println(final_rez1);


        //final z1 = (x1+x0)*(y1+y0) - z2 - z0
        //final z1 = partial z1 - z2 - z0
        Polynomial final_rez2 = PolynomialOperations.addZeros(PolynomialOperations.subtract(PolynomialOperations.subtract(r2, r3), r1), len);
        return PolynomialOperations.add(PolynomialOperations.add(final_rez1, final_rez2), r1);
    }

}
