package Repositorio;

import concesionarioo.*;
public interface iRepoAsesor extends iRepositorioBase<Asesor>,
        iSeleccionAleatoria<Asesor>,
        iBuscablePorId<Asesor, String> {

}