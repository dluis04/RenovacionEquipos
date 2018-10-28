package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.DetalleListaComputo;

/**
 * Session Bean implementation class SBDetalleListaComputo
 */
@Stateless
public class SBDetalleListaComputo implements SBDetalleListaComputoLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBDetalleListaComputo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DetalleListaComputo crearDetalleListaComputo(DetalleListaComputo nuevo) throws Exception {
		DetalleListaComputo entity = (DetalleListaComputo) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public DetalleListaComputo actualizarDetalleListaComputo(DetalleListaComputo update) throws Exception {
		DetalleListaComputo entity = (DetalleListaComputo) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public DetalleListaComputo consultarDetalleDetalleListaComputo(String id) throws Exception {

		String query = "SELECT u FROM DetalleListaComputo u where u.idDetaListCompu='" + id + "' ";
		List<DetalleListaComputo> listDetalleListaComputo = sbFacade.executeQuery(query, null);
		DetalleListaComputo temp = null;

		for (DetalleListaComputo lists : listDetalleListaComputo) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<DetalleListaComputo> consultarAllDetalleListaComputoActivos() throws Exception {

		String query = "SELECT u FROM DetalleListaComputo u ";
		List<DetalleListaComputo> listDetalleListaComputo = sbFacade.executeQuery(query, null);

		return listDetalleListaComputo;
	}

	@Override
	public List<DetalleListaComputo> consultarAllDetalleListaComputo(String idComputador) throws Exception {
		String query = "SELECT u FROM DetalleListaComputo u where u.computador.idComputador='" + idComputador
				+ "' order by u.listaCheqeoComputador.orden asc ";
		List<DetalleListaComputo> listDetalleListaComputo = sbFacade.executeQuery(query, null);

		return listDetalleListaComputo;
	}
}
