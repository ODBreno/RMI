import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class DistanceCalculatorImpl extends UnicastRemoteObject implements DistanceCalculator {

    protected DistanceCalculatorImpl() throws RemoteException {
        super();
    }

    @Override
    public double calculateEuclideanDistance(double[] a, double[] b) throws RemoteException {
        if (a.length != b.length) {
            throw new RemoteException("Os vetores devem ter o mesmo tamanho");
        }
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(Math.abs(a[i] - b[i]), 2);
        }
        System.out.println("Calculando distância euclidiana...");
        return Math.sqrt(sum);
    }

    @Override
    public double calculateCityBlockDistance(double[] a, double[] b) throws RemoteException {
        if (a.length != b.length) {
            throw new RemoteException("Os vetores devem ter o mesmo tamanho");
        }
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.abs(a[i] - b[i]);
        }
        System.out.println("Calculando distância City Block...");
        return sum;
    }

    public static void main(String[] args) {
        try {
            // Cria uma instância do objeto remoto
            DistanceCalculatorImpl calculator = new DistanceCalculatorImpl();

            // Registra o objeto remoto no registro RMI
            Registry registry = LocateRegistry.createRegistry(1100);
            registry.rebind("DistanceCalculator", calculator);
            System.out.println("Servidor RMI pronto...");

        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}