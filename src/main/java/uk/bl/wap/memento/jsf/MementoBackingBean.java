/**
 * 
 */
package uk.bl.wap.memento.jsf;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;

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
	
	/**
	 * @return
	 */
	public PieChartModel getHostsPieChart() {
		PieChartModel pieModel = new PieChartModel();
		for( String host : this.getHostCounts().keySet() ) {
			pieModel.set(host, this.getHostCounts().get(host));
		}
		return pieModel;
	}
	
	/**
	 * @return
	 */
	public String getHostPieData() {
		String val = "";
		Map<String,Integer> hc = sortByValue(this.getHostCounts());
		for( String host : hc.keySet() ) {
			val += "{ label: \""+host+"\", value: "+hc.get(host)+" },";
		}
		return val;
		
	}
	
	private static Map<String, Integer> sortByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
                return (m2.getValue()).compareTo(m1.getValue());
            }
        });

        Map<String, Integer> result = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
	
}
