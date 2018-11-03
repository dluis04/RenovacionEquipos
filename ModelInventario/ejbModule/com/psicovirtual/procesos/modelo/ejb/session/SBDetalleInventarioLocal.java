package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleInventario;

@Local
public interface SBDetalleInventarioLocal {

	public DetalleInventario crearDetalleInventario(DetalleInventario nuevo) throws Exception;

	public DetalleInventario actualizarDetalleInventario(DetalleInventario update) throws Exception;

	public DetalleInventario consultarDetalleDetalleInventario(String id) throws Exception;

	public List<DetalleInventario> consultarAllDetalleInventarioActivos() throws Exception;

	public List<DetalleInventario> consultarAllDetalleInventarioComputadorNuevos() throws Exception;

	public List<DetalleInventario> consultarAllDetalleInventarioComputadorBase() throws Exception;

	public DetalleInventario consultarComputadoresInventarioById(String idComputador) throws Exception;

}
