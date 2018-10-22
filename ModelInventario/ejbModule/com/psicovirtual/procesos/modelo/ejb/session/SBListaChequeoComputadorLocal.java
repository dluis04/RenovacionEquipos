package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.inventario.ListaCheqeoComputador;

@Local
public interface SBListaChequeoComputadorLocal {

	public ListaCheqeoComputador crearListaCheqeoComputador(ListaCheqeoComputador nuevo) throws Exception;

	public ListaCheqeoComputador actualizarListaCheqeoComputador(ListaCheqeoComputador update) throws Exception;

	public ListaCheqeoComputador consultarDetalleListaCheqeoComputador(String id) throws Exception;

	public List<ListaCheqeoComputador> consultarAllListaCheqeoComputadorActivos() throws Exception;
}
