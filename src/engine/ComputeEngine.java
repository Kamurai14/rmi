package engine;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import compute.Compute;
import compute.Task;

public class ComputeEngine implements Compute {

    public ComputeEngine() {
        super();
    }

    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }

    public static void main(String[] args) {
        // --- BLOQUE ELIMINADO ---
        // El SecurityManager ya no se recomienda para aplicaciones nuevas
        // y causa problemas si no tienes un archivo de políticas configurado.
        /*
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        */
        // ------------------------
        try {
            String name = "Compute";
            Compute engine = new ComputeEngine();
            Compute stub =
                    (Compute) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("Registro RMI creado en puerto 1099.");
            } catch (RemoteException e) {
                registry = LocateRegistry.getRegistry();
                System.out.println("Conectado a registro RMI existente.");
            }

            registry.rebind(name, stub);
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}
