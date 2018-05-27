package br.com.academiaDaryoku.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.primefaces.model.ScheduleEvent;

public class EventoScheduleModel implements ScheduleEvent, Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String title;
	private Date startDate;
	private Date endDate;
	private boolean allDay = false;
	private String styleClass;
	private Object data;
	private boolean editable = true;
	private String description;
	private List<TbTurma> tbTurma;
	private String subTitulo;

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public List<TbTurma> getTbTurma() {
		return tbTurma;
	}

	public void setTbTurma(List<TbTurma> tbTurma) {
		this.tbTurma = tbTurma;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	@Override
	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	@Override
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

}