package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Sede;

@Local
public interface SBSedeLocal {

	public Sede crearSede(Sede nuevo) throws Exception;

	public Sede actualizarSede(Sede update) throws Exception;

	public Sede consultarDetalleSede(String id) throws Exception;

	public List<Sede> consultarAllSedeActivos() throws Exception;

}
