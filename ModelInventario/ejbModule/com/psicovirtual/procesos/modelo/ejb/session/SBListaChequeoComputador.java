package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.inventario.ListaCheqeoComputador;

/**
 * Session Bean implementation class SBListaChequeoComputador
 */
@Stateless
public class SBListaChequeoComputador implements SBListaChequeoComputadorLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	/**
	 * Default constructor.
	 */
	public SBListaChequeoComputador() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ListaCheqeoComputador crearListaCheqeoComputador(ListaCheqeoComputador nuevo) throws Exception {
		ListaCheqeoComputador entity = (ListaCheqeoComputador) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public ListaCheqeoComputador actualizarListaCheqeoComputador(ListaCheqeoComputador update) throws Exception {
		ListaCheqeoComputador entity = (ListaCheqeoComputador) sbFacade.updateEntity(update);
		return entity;
	}

	@Override
	public ListaCheqeoComputador consultarDetalleListaCheqeoComputador(String id) throws Exception {

		String query = "SELECT u FROM ListaCheqeoComputador u where u.idListaCheqeoComputador='" + id
				+ "' and u.idEstado='1' ";
		List<ListaCheqeoComputador> listListaCheqeoComputador = sbFacade.executeQuery(query, null);
		ListaCheqeoComputador temp = null;

		for (ListaCheqeoComputador lists : listListaCheqeoComputador) {
			temp = lists;
		}
		return temp;
	}

	@Override
	public List<ListaCheqeoComputador> consultarAllListaCheqeoComputadorActivos() throws Exception {

		String query = "SELECT u FROM ListaCheqeoComputador u ";
		List<ListaCheqeoComputador> listListaCheqeoComputador = sbFacade.executeQuery(query, null);

		return listListaCheqeoComputador;
	}

	@Override
	public List<ListaCheqeoComputador> consultarAllListaCheqeoComputadorActivosNuevos() throws Exception {
		String query = "SELECT u FROM ListaCheqeoComputador u where u.tipoLista='NUEVO' and u.idEstado='1' ";
		List<ListaCheqeoComputador> listListaCheqeoComputador = sbFacade.executeQuery(query, null);

		return listListaCheqeoComputador;
	}

	@Override
	public List<ListaCheqeoComputador> consultarAllListaChequeoUESOrdenASCNuevo(String idUnidad) throws Exception {
		String query = "SELECT u FROM ListaCheqeoComputador u where u.tipoLista='NUEVO' and u.idEstado='1' "
				+ "and u.tipoUnidad='" + idUnidad + "' order by u.orden asc ";
		List<ListaCheqeoComputador> listListaCheqeoComputador = sbFacade.executeQuery(query, null);

		return listListaCheqeoComputador;
	}

}
