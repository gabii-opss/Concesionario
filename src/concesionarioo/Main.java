/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concesionarioo;

import Repositorio.RepositorioAsesor;
import Repositorio.RepositorioCliente;
import Repositorio.RepositorioCompra;
import Repositorio.RepossitorioVehiculo;
import Repositorio.iRepoAsesor;
import Repositorio.iRepoCliente;
import Repositorio.iRepoCompra;
import Repositorio.iRepoVehiculo;
import Servicio.CalculadoraPago;
import Servicio.ClientService;
import Servicio.CompraService;
import Servicio.ConcesionarioService;
import Servicio.ServicioNotificacionSwing;
import Servicio.VehiculoService;
import Servicio.iCalculadoraPago;
import Servicio.iServicioNotificacion;
import Swing.Menu;

/**
 *
 * @author gabri
 */
public class Main {
      public static void main(String[] args) {

        // Repositorios
        iRepoAsesor repoAsesor = new RepositorioAsesor();
        iRepoCliente repoCliente = new RepositorioCliente();
        iRepoVehiculo repoVehiculo = new RepossitorioVehiculo();
        iRepoCompra repoCompra = new RepositorioCompra();

        // Servicio de notificación (simple)
        iServicioNotificacion notificacionService = new ServicioNotificacionSwing();
        iCalculadoraPago calculadoraPago = new CalculadoraPago();
        CompraService compraService = new CompraService(
                repoCompra,
                calculadoraPago,
                notificacionService
        );

        // Servicios
        ClientService clientService = new ClientService(repoCliente, repoAsesor, notificacionService);
        VehiculoService vehiculoService = new VehiculoService(repoVehiculo);
         try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }


        // ConcesionarioService centralCliente();
        ConcesionarioService concesionario = new ConcesionarioService(
                clientService,
                vehiculoService,
                compraService,
                repoAsesor
        );

        javax.swing.SwingUtilities.invokeLater(() ->
                new Menu(concesionario, vehiculoService).setVisible(true)
        );
    }
    
}
