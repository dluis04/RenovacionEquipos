package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleInventario;

/**
 * Session Bean implementation class SBDetalleInventario
 */
@Stateless
public class SBDetalleInventario implements SBDetalleInventarioLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBDetalleInventario() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DetalleInventario crearDetalleInventario(DetalleInventario nuevo) throws Exception {
		DetalleInventario entity = (DetalleInventario) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public DetalleInventario actualizarDetalleInventario(DetalleInventario update) throws Exception {
		DetalleInventario entity = (DetalleInventario) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public DetalleInventario consultarDetalleDetalleInventario(String id) throws Exception {

		String query = "SELECT u FROM DetalleInventario u where u.idInventario='" + id + "' and u.idEstado='1' ";
		List<DetalleInventario> listDetalleInventario = sbFacade.executeQuery(query, null);
		DetalleInventario temp = null;

		for (DetalleInventario lists : listDetalleInventario) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<DetalleInventario> consultarAllDetalleInventarioActivos() throws Exception {

		String query = "SELECT u FROM DetalleInventario u where u.idEstado='1' ";
		List<DetalleInventario> listDetalleInventario = sbFacade.executeQuery(query, null);

		return listDetalleInventario;
	}

	@Override
	public List<DetalleInventario> consultarAllDetalleInventarioComputadorNuevos() throws Exception {
		String query = "SELECT u FROM DetalleInventario u where u.idEstado='1' and u.computador.idEstadoCompu='1' ";
		List<DetalleInventario> listDetalleInventario = sbFacade.executeQuery(query, null);

		return listDetalleInventario;
	}

	@Override
	public List<DetalleInventario> consultarAllDetalleInventarioComputadorBase() throws Exception {
		String query = "SELECT u FROM DetalleInventario u where u.idEstado='1' and u.computador.idEstadoCompu='2' and u.computador.idEstado='1' ";
		List<DetalleInventario> listDetalleInventario = sbFacade.executeQuery(query, null);

		return listDetalleInventario;
	}

	@Override
	public DetalleInventario consultarComputadoresInventarioById(String idComputador) throws Exception {
		String query = "SELECT u FROM DetalleInventario u where u.idEstado='1' and u.computador.idComputador='"
				+ idComputador + "' ";
		List<DetalleInventario> listDetalleInventario = sbFacade.executeQuery(query, null);

		DetalleInventario temp = null;
		for (DetalleInventario obj : listDetalleInventario) {
			temp = obj;
		}

		return temp;
	}

}
