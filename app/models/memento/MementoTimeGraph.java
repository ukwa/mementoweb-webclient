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
		public List<Point> values;
	}
	
	public class Point {
		public double x;
		public double y;
	}
	
	public static List<Series> makeExample( ){
		List<Series> data = new ArrayList<Series>();
		MementoTimeGraph mtg = new MementoTimeGraph();
		Series series1 = mtg.new Series();
		series1.key = "A Key";
		series1.values = new ArrayList<Point>();
		series1.values.add( mtg.new Point() );
		data.add(series1);
		return data;
	}

	public static Object makeYearwiseData(MementoQuery msb) {
		List<Series> data = new ArrayList<Series>();
		MementoTimeGraph mtg = new MementoTimeGraph();
		// Build up data summary:
		int minYear = -1, maxYear = -1;
		for( String host : msb.getHosts() ) {
			for( MementoBean m : msb.getMementos() ) {
				if( host.equals(m.getArchiveHost()) ) {
					Integer year = m.getDateTime().getYear();
					// Also record min/max:
					if( minYear == -1 || year.intValue() < minYear) minYear = year.intValue();
					if( maxYear == -1 || year.intValue() > maxYear) maxYear = year.intValue();
				}
			}
		}
		// Convert to series:
		for( String host : msb.getHosts() ) {
			Series series = mtg.new Series();
			series.key = host;		
			HashMap<Integer,Integer> byYear = new LinkedHashMap<Integer,Integer>();
			for( MementoBean m : msb.getMementos() ) {
				if( host.equals(m.getArchiveHost()) ) {
					Integer year = m.getDateTime().getYear();
					Integer total = byYear.get(year);
					if( total == null ) total = new Integer(0);
					total = total + 1;
					byYear.put(year, total);
				}
			}
			// Now assemble:
			series.values = new ArrayList<Point>();
			for( int year = minYear; year <= maxYear; year++ ) {
				Integer key = new Integer(year);
				Integer value = byYear.get(year);
				Point p = mtg.new Point();
				p.x = key;
				if( value == null ) {
					p.y = 0;
				} else {
					p.y = value;
				}
				series.values.add(p);
			}
			//
			data.add(series);
		}
		return data;
	}
	
}
