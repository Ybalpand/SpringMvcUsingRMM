package com.mycompany.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

// @JsonSerialize annotation to use DateSerializer class for Date conversion from Java type to JSON format and vice versa.

public class Document implements Serializable{
	
	private static final long serialVersionUID = -1095769722667565836L;
	
	private int documentId;
	private String documentName;
	private Date documentExpiryDate;
	private String documentAddress;
	
	public Document() {
		super();
	}

	public Document(int documentId, String documentName, Date documentExpiryDate, String documentAddress) {
		super();
		this.documentId = documentId;
		this.documentName = documentName;
		this.documentExpiryDate = documentExpiryDate;
		this.documentAddress = documentAddress;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	//@JsonSerialize(using=DateSerializer.class)
	public Date getDocumentExpiryDate() {
		return documentExpiryDate;
	}
	
	public void setDocumentExpiryDate(Date documentExpiryDate) {
		this.documentExpiryDate = documentExpiryDate;
	}

	public String getDocumentAddress() {
		return documentAddress;
	}

	public void setDocumentAddress(String documentAddress) {
		this.documentAddress = documentAddress;
	}

	@Override
	public String toString() {
		return "Document [documentId=" + documentId + ", documentName=" + documentName + ", documentExpiryDate="
				+ documentExpiryDate + ", documentAddress=" + documentAddress + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + documentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (documentId != other.documentId)
			return false;
		return true;
	}

	
}
