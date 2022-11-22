package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        int[] Ns_arr = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        int M = 10000;
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        for(int nsi : Ns_arr){
            Ns.addLast(nsi);
            SLList<Integer> slList = new SLList<>();
            for(int i = 0; i < nsi; i++){
                slList.addLast(i);
            }
            int opCountsi = 0;
            Stopwatch sw = new Stopwatch();
            for(int i = 0; i < M; i++){
                slList.addLast(i);
                opCountsi++;
            }
            times.addLast(sw.elapsedTime());
            opCounts.addLast(opCountsi);
        }
        printTimingTable(Ns, times, opCounts);
    }

}
