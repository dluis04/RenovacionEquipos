package com.psicovirtual.procesos.modelo.ejb.entity.inventario;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TIPO_COMPUTO database table.
 * 
 */
@Entity
@Table(name="TIPO_COMPUTO")
@NamedQuery(name="TipoComputo.findAll", query="SELECT t FROM TipoComputo t")
public class TipoComputo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TIPO")
	private int idTipo;

	@Column(name="AD")
	private String ad;

	@Column(name="ESPECIFICACION_TD")
	private String especificacionTd;

	@Column(name="ID_ESTADO")
	private int idEstado;

	@Column(name="TIPO_COMPUTO")
	private String tipoComputo;

	//bi-directional many-to-one association to Computador
	@OneToMany(mappedBy="tipoComputo")
	private List<Computador> computadors;

	public TipoComputo() {
	}

	public int getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getAd() {
		return this.ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getEspecificacionTd() {
		return this.especificacionTd;
	}

	public void setEspecificacionTd(String especificacionTd) {
		this.especificacionTd = especificacionTd;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getTipoComputo() {
		return this.tipoComputo;
	}

	public void setTipoComputo(String tipoComputo) {
		this.tipoComputo = tipoComputo;
	}

	public List<Computador> getComputadors() {
		return this.computadors;
	}

	public void setComputadors(List<Computador> computadors) {
		this.computadors = computadors;
	}

	public Computador addComputador(Computador computador) {
		getComputadors().add(computador);
		computador.setTipoComputo(this);

		return computador;
	}

	public Computador removeComputador(Computador computador) {
		getComputadors().remove(computador);
		computador.setTipoComputo(null);

		return computador;
	}

}