package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.OperacionServicio;

@Local
public interface SBOperacionServicioLocal {

	public OperacionServicio crearOperacionServicio(OperacionServicio nuevo) throws Exception;

	public OperacionServicio actualizarOperacionServicio(OperacionServicio update) throws Exception;

	public OperacionServicio consultarDetalleOperacionServicio(String id) throws Exception;

	public List<OperacionServicio> consultarAllOperacionServicioActivos() throws Exception;

}
