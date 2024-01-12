package Algorithms;

import model.Polynomial;
import model.Task;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelRegular {
    private static int nrThreads = 4;

    public static Polynomial multiply(Polynomial p1, Polynomial p2) throws InterruptedException {
        System.out.println("In ParallelRegular");

        int sizeOfResultCoeffList = p1.getDegree() + p2.getDegree() + 1;

        //initialize list of coeffs with zeros for the result polynomial
        List<Integer> coeffs = IntStream.range(0, sizeOfResultCoeffList)
                .mapToObj(i -> 0)
                .collect(Collectors.toList());
        Polynomial res = new Polynomial(coeffs);
        //System.out.println("res = " + res.getCoefficients());


        //set up thread pool with a fixed number of threads
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nrThreads);
        int nrOfTasks = sizeOfResultCoeffList / nrThreads;
        if (nrOfTasks == 0) {
            nrOfTasks = 1;
        }

        int end;
        for (int i = 0; i < sizeOfResultCoeffList; i += nrOfTasks) {
            //create tasks to be executed
            end = i + nrOfTasks;
            //System.out.println("in pr end=" + end);
            Task task = new Task(i, end, p1, p2, res);
            //System.out.println(task);
            executor.execute(task);
            //Thread.sleep(3000);

        }

        executor.shutdown();
        executor.awaitTermination(50, TimeUnit.SECONDS);

        return res;

    }

}
