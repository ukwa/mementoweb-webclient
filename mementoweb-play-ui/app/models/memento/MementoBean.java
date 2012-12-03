package models.memento;

import java.net.URI;
import java.net.URISyntaxException;

import com.google.common.net.InternetDomainName;

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
			return getTopPrivateDomain(uri.getAuthority());
		} catch (URISyntaxException e) {
			System.err.println("Could not parse uri: "+m.getUrl());
			return null;
		}
		
	}
	
	private static String getTopPrivateDomain(String host) {
	    InternetDomainName domainName = InternetDomainName.from(host);
	    return domainName.topPrivateDomain().name();
	  }

	
	public String getUrl() {
		return m.getUrl();
	}
	
	public SimpleDateTime getDateTime() {
		if( m == null ) return null;
		return m.getDateTime();
	}
	
	public String getRel() {
		return m.getRel();
	}
}
