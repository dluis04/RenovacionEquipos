package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleInventario;

@Local
public interface SBInventarioLocal {

	public DetalleInventario crearDetalleInventario(DetalleInventario nuevo) throws Exception;

	public DetalleInventario actualizarDetalleInventario(DetalleInventario update) throws Exception;

	public DetalleInventario consultarDetalleDetalleInventario(String id) throws Exception;

	public List<DetalleInventario> consultarAllDetalleInventarioActivos() throws Exception;

}
