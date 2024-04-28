import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DistanceCalculator extends Remote {
    double calculateEuclideanDistance(double[] a, double[] b) throws RemoteException;
    double calculateCityBlockDistance(double[] a, double[] b) throws RemoteException;
}
