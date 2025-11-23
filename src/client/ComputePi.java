package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;
import compute.Compute;

public class ComputePi {
    public static void main(String args[]) {
        // --- BLOQUE ELIMINADO (SecurityManager) ---

        try {
            String name = "Compute";

            // LÓGICA MEJORADA:
            // Si hay argumentos, úsalos. Si no, usa "localhost" y "20" decimales.
            String host = (args.length < 1) ? "localhost" : args[0];
            int decimales = (args.length < 2) ? 20 : Integer.parseInt(args[1]);

            System.out.println("Conectando a: " + host + " para calcular " + decimales + " decimales.");

            // Buscar el registro en el host (localhost por defecto)
            Registry registry = LocateRegistry.getRegistry(host);

            // Obtener el Stub del servidor
            Compute comp = (Compute) registry.lookup(name);

            // Crear la tarea
            Pi task = new Pi(decimales);

            // Ejecutar tarea remotamente
            BigDecimal pi = comp.executeTask(task);

            System.out.println("Resultado: " + pi);

        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }
}
