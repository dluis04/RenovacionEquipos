package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.Ubicacion;

@Local
public interface SBUbicacionLocal {

	public Ubicacion crearUbicacion(Ubicacion nuevo) throws Exception;

	public Ubicacion actualizarUbicacion(Ubicacion update) throws Exception;

	public Ubicacion consultarDetalleUbicacion(String id) throws Exception;

	public List<Ubicacion> consultarAllUbicacionActivos() throws Exception;

}
