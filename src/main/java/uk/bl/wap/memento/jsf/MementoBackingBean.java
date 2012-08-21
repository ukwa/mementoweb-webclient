/**
 * 
 */
package uk.bl.wap.memento.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import uk.bl.wap.memento.MementoSearchBean;


/**
 * @author Andrew Jackson <Andrew.Jackson@bl.uk>
 *
 */
@ManagedBean
@ViewScoped
public class MementoBackingBean extends MementoSearchBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7116498827412293266L;

	public boolean isUrlSet() {
		if( this.getUrl() == null ) return false;
		if( "".equals(this.getUrl())) return false;
		if( "http://".equals(this.getUrl())) return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see uk.bl.wap.memento.MementoSearchBean#setUrl(java.lang.String)
	 */
	@Override
	public void setUrl(String url) {
		super.setUrl(url);
		if( this.getErrorMessage() != null ) {
			FacesMessage fm = new FacesMessage(this.getErrorMessage());
        	FacesContext.getCurrentInstance().addMessage("Memento Error", fm);
        }
	}
	
	/**
	 * @return
	 */
	public String submit() {
		System.out.println("Got: "+this.getUrl());
		// This should work, but doesn't work for me.
		//return "success?faces-redirect=true&IncludeViewParams=true";
		// This works fine.
		return "/search.xhtml?faces-redirect=true&url=" + this.getUrl();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getAbsoluteUrl() {
		final ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
		String url = ectx.getRequestScheme()
		  + "://" + ectx.getRequestServerName()
		  + ":" + ectx.getRequestServerPort()
		  + "" + ectx.getRequestContextPath();
		return url;
	}		
}
