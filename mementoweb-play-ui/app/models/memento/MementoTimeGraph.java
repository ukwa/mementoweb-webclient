/**
 * 
 */
package models.memento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Andrew Jackson <Andrew.Jackson@bl.uk>
 *
 */
public class MementoTimeGraph {

	public class Series {
		public String key;
		public List<String[]> values;
	}
	
	public static List<Series> makeExample( ){
		List<Series> data = new ArrayList<Series>();
		MementoTimeGraph mtg = new MementoTimeGraph();
		Series series1 = mtg.new Series();
		series1.key = "A Key";
		series1.values = new ArrayList<String[]>();
		series1.values.add( new String[] {"10", "10"} );
		data.add(series1);
		return data;
	}

	public static Object makeYearwiseData(MementoQuery msb) {
		List<Series> data = new ArrayList<Series>();
		MementoTimeGraph mtg = new MementoTimeGraph();
		for( String host : msb.getHosts() ) {
			Series series = mtg.new Series();
			series.key = host;
			HashMap<Integer,Integer> byYear = new LinkedHashMap<Integer,Integer>();
			for( MementoBean m : msb.getMementos() ) {
				if( m.getArchiveHost().equals(host) ) {
					Integer year = m.getDateTime().getYear();
					Integer total = byYear.get(year);
					if( total == null ) total = new Integer(0);
					total = total + 1;
					byYear.put(year, total);
				}
			}
			// Now assemble:
			series.values = new ArrayList<String[]>();
			for( Integer year : byYear.keySet() ) {
				series.values.add(new String[] {""+year,""+byYear.get(year)} );
			}
			//
			data.add(series);
		}
		return data;
	}
	
}
