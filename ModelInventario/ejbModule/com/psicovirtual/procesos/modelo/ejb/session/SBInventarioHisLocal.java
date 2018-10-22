package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleInventarioHistorial;

@Local
public interface SBInventarioHisLocal {

	public DetalleInventarioHistorial crearDetalleInventarioHistorial(DetalleInventarioHistorial nuevo)
			throws Exception;

	public DetalleInventarioHistorial actualizarDetalleInventarioHistorial(DetalleInventarioHistorial update)
			throws Exception;

	public DetalleInventarioHistorial consultarDetalleDetalleInventarioHistorial(String id) throws Exception;

	public List<DetalleInventarioHistorial> consultarAllDetalleInventarioHistorialActivos() throws Exception;

}
