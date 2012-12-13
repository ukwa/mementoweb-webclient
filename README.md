Memento Web Client
==================

This is a very simple web client that exposes web archive holdings via the Memento protocol.

Given a URL, the client uses one or more known TimeGates to look up all known archival holdings, them summarises them and provides links.

To Do List
----------

 * FIXME Add http:// if not supplied.
 * FIXME Snapshot table CSS (next/prev/etc)
 * FIXME Ensure UKWA can take a URL parameter for nominations, and link it in.
 * FIXME Add a menu item to UKWA to link to BlueBox.
 * Add archive column? collapse? mementos by host.
 * No LIVE?
 * TODO Use Future pattern for long calls to avoid AskTimeoutException (see on graphs failing to load. JS?).
 * Shorten display of long URLs to avoid breaking the layout.
 * Add Help, esp. for the bookmarklet, as popovers (http://twitter.github.com/bootstrap/javascript.html#popovers)
 * Add busy logos/warning while screenshots are loaded?
 * Add text that displays if there is no JavaScript?
 * Allow years to be selected, in turn filtering the list of Mementos to show a year-long graph etc.
 * Add a lots-of-screenshots timeline.
 * Use timeline screenshots to estimate degree of change over time (e.g. even just colour changes).
 * Make screenshots cope better with none-HTML content?


Memento Issues
==============

One minor issue appears to be that the lanl.gov aggregate TimeGate does not appear to aggregate everything. Things like individual Wikipedia pages are (e.g. ) not coming up, despite the fact they are in the UK Web Archive and indeed reported by our TimeGate.  They are also clearly stored elsewhere, e.g. http://wayback.archive.org/web/*/http://en.wikipedia.org/wiki/Wikipedia:Wikipedia_is_an_encyclopedia

curl -I http://www.webarchive.org.uk/waybacktg/memento/timegate/http://en.wikipedia.org/wiki/Wikipedia:Wikipedia_is_an_encyclopedia

Leads via a redirect to the timebundle

http://www.webarchive.org.uk/waybacktg/ore/timemap/rdf/http://en.wikipedia.org/wiki/Wikipedia:Wikipedia_is_an_encyclopedia

Which is the RDF form, so need an RDF parser to extract the instances.

It appears the Aggregator is still using a proxy, so we should move Memento to production.

http://mementoweb.org/depot/proxy/BL/

curl "http://mementoproxy.lanl.gov/bl/timemap/http://en.wikipedia.org/wiki/Wikipedia:Wikipedia_is_an_encyclopedia"
<html><body><br/><center><table width='800px'><tr><td><div style='background-color: #e0e0e0; padding: 10px;'><br/><center><b>Error: 404</b></center>Unknown time map serialization<br/><br/></div></td></tr></table></body></html>


Note that 

curl  http://api.wayback.archive.org/list/timemap/link/http://en.wikipedia.org/wiki/Wikipedia:Wikipedia_is_an_encyclopedia

Works fine, so I wonder if the error on the BL proxy is throwing the whole aggregation off?


Known TimeGates
---------------
http://mementoproxy.lanl.gov/aggr/timegate/
http://mementoproxy.lanl.gov/google/timegate/
http://www.webarchive.org.uk/wayback/memento/timegate/

See also http://mementoweb.org/depot/

Notes
-----
[http://www.slideshare.net/hvdsomp/memento-updated-technical-details-february-2010][1] 
See also the JISC Repositories mailing list discussion.[https://www.jiscmail.ac.uk/cgi-bin/webadmin?A2=ind1002&L=JISC-REPOSITORIES&T=0&F=&S=&P=10604][2] 
[http://arxiv.org/abs/1003.3661][3] 

[http://lists.w3.org/Archives/Public/public-lod/2010Mar/0169.html][4] 


  [1]: http://www.slideshare.net/hvdsomp/memento-updated-technical-details-february-2010
  [2]: https://www.jiscmail.ac.uk/cgi-bin/webadmin?A2=ind1002&amp;L=JISC-REPOSITORIES&amp;T=0&amp;F=&amp;S=&amp;P=10604
  [3]: http://arxiv.org/abs/1003.3661
  [4]: http://lists.w3.org/Archives/Public/public-lod/2010Mar/0169.html


