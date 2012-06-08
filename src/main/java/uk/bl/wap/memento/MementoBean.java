package uk.bl.wap.memento;

import java.net.URI;
import java.net.URISyntaxException;

import dev.memento.Memento;
import dev.memento.SimpleDateTime;

public class MementoBean {

	private Memento m;

	public MementoBean(Memento m) {
		this.m = m;
	}

	public String getArchiveHost() {
		try {
			URI uri = new URI( m.getUrl() );
			return uri.getAuthority();
		} catch (URISyntaxException e) {
			System.err.println("Could not parse uri: "+m.getUrl());
			return null;
		}
		
	}
	
	public String getUrl() {
		return m.getUrl();
	}
	
	public SimpleDateTime getDateTime() {
		return m.getDateTime();
	}
	
	public String getRel() {
		return m.getRel();
	}
}
