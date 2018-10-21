package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Ciudad;

@Local
public interface SBCiudadLocal {

	public Ciudad crearCiudad(Ciudad nuevo) throws Exception;

	public Ciudad actualizarCiudad(Ciudad update) throws Exception;

	public Ciudad consultarDetalleCiudad(String id) throws Exception;

	public List<Ciudad> consultarAllCiudadActivos() throws Exception;

}
