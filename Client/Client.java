import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Obtém a referência do registro RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1400);

            // Obtém a referência do objeto remoto
            DistanceCalculator calculator = (DistanceCalculator) registry.lookup("DistanceCalculator");

            // Entrada do usuário
            Scanner scanner = new Scanner(System.in);
            double[] a = new double[4];
            double[] b = new double[4];
            double[] c = new double[4];

            System.out.println("Digite as características do objeto A:");
            for (int i = 0; i < 4; i++) {
                System.out.print("Característica " + (i + 1) + ": ");
                a[i] = scanner.nextDouble();
            }

            System.out.println("Digite as características do objeto B:");
            for (int i = 0; i < 4; i++) {
                System.out.print("Característica " + (i + 1) + ": ");
                b[i] = scanner.nextDouble();
            }

            System.out.println("Digite as características do objeto C:");
            for (int i = 0; i < 4; i++) {
                System.out.print("Característica " + (i + 1) + ": ");
                c[i] = scanner.nextDouble();
            }
            
            // Escolha do tipo de distância
            System.out.println("Escolha o tipo de distância (1 - Euclidiana, 2 - City Block): ");
            int choice = scanner.nextInt();
            
            double distanceAB, distanceAC, distanceBC;
            if (choice == 1) {
                distanceAB = calculator.calculateEuclideanDistance(a, b);
                distanceAC = calculator.calculateEuclideanDistance(a, c);
                distanceBC = calculator.calculateEuclideanDistance(b, c);
            } else {
                distanceAB = calculator.calculateCityBlockDistance(a, b);
                distanceAC = calculator.calculateCityBlockDistance(a, c);
                distanceBC = calculator.calculateCityBlockDistance(b, c);
            }

            System.out.println("Distância A-B: " + distanceAB);
            System.out.println("Distância A-C: " + distanceAC);
            System.out.println("Distância B-C: " + distanceBC);

            // Encontra o par mais similar
            if (distanceAB < distanceAC && distanceAB < distanceBC) {
                System.out.println("O par mais similar é A-B");
            } else if (distanceAC < distanceAB && distanceAC < distanceBC) {
                System.out.println("O par mais similar é A-C");
            } else {
                System.out.println("O par mais similar é B-C");
            }

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}
